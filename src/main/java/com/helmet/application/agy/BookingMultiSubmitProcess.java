package com.helmet.application.agy;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyBookingMultiLockAction;


public class BookingMultiSubmitProcess extends AgyBookingMultiLockAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    String bookingIds = (String)dynaForm.get("bookingIds");
    StringTokenizer st = new StringTokenizer(bookingIds, ",");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    int rc = 0;
    while (st.hasMoreTokens())
    {
      // 0 (zero) passed as noOfChanges as this is always called for a NEW booking for and agency
      Integer bookingId = Integer.valueOf(st.nextToken());
      rc += agyService.updateBookingOpen(bookingId, 0, getConsultantLoggedIn().getConsultantId());
    }
    logger.debug(rc + " Bookings Opened");
    logger.exit("Out going !!!");

    return mapping.findForward("success");

  }

}
