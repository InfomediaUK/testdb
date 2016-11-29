package com.helmet.application.agy;

import java.util.List;

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
import com.helmet.application.agy.abztract.AgyAction;

public class BookingDatesView extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    String[] selectedBookingDates = (String[]) dynaForm.get("selectedBookingDates");

    String bookingDateIdStrs = "";

    for (String bookingDateIdStr : selectedBookingDates)
    {
      if (!"".equals(bookingDateIdStrs))
      {
        bookingDateIdStrs = bookingDateIdStrs + ",";
      }
      bookingDateIdStrs = bookingDateIdStrs + bookingDateIdStr;
    }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    List bookingDateUserApplicants = agyService.getBookingDateUserApplicantsForAgencyAndBookingDates(getConsultantLoggedIn().getAgencyId(), bookingDateIdStrs);

    // in ancestor class
    sortShiftTotals(bookingDateUserApplicants, dynaForm);

    dynaForm.set("bookingDateIdStrs", bookingDateIdStrs);
    dynaForm.set("list", bookingDateUserApplicants);

    logger.exit("Out going !!!");

    return mapping.findForward("success");

  }

}