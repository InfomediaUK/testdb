package com.helmet.application.agy;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.SubcontractInvoiceItemHistory;

public class NhsBookingEdit extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    MessageResources messageResources = getResources(request);
    NhsBookingUser nhsBookingUser = (NhsBookingUser)dynaForm.get("nhsBookingUser");
    Integer weekToShow = (Integer)dynaForm.get("weekToShow");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    nhsBookingUser = agyService.getNhsBookingUser(nhsBookingUser.getNhsBookingId());
    if (nhsBookingUser.getBookingId() > 0)
    {
      // NHS Booking has been Booked.
      Booking booking = agyService.getBooking(nhsBookingUser.getBookingId());
      dynaForm.set("bookingStatus", messageResources.getMessage(booking.getStatusDescriptionKey()));
    }    
    if (nhsBookingUser.getBookingGradeId() > 0)
    {
      // NHS Booking has been Booked.
      BookingGrade bookingGrade = agyService.getBookingGrade(nhsBookingUser.getBookingGradeId());
      dynaForm.set("bookingGradeStatus", messageResources.getMessage(bookingGrade.getStatusDescriptionKey()));
    }
    if (nhsBookingUser.getApplicantPaidDate() != null)
    {
      dynaForm.set("applicantPaidDateStr", sdf.format(nhsBookingUser.getApplicantPaidDate()));
    }
    List<SubcontractInvoiceItemHistory> listSubcontractInvoiceItemHistory = agyService.getSubcontractInvoiceItemHistoryForBankReqNum(nhsBookingUser.getBankReqNum());
    dynaForm.set("nhsBookingUser", nhsBookingUser);
    dynaForm.set("weekToShow", weekToShow);
    if (listSubcontractInvoiceItemHistory.size() > 0)
    {
      dynaForm.set("listSubcontractInvoiceItemHistory", listSubcontractInvoiceItemHistory);
    }
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
