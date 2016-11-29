package com.helmet.application.agy;

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
import com.helmet.bean.Booking;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeApplicantUserEntity;

public class BookingGradeApplicantView extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    BookingGradeApplicantUserEntity bookingGradeApplicant = (BookingGradeApplicantUserEntity) dynaForm.get("bookingGradeApplicant");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    bookingGradeApplicant = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicant.getBookingGradeApplicantId());

    if (bookingGradeApplicant == null) { return mapping.findForward("illegalaccess"); }

    // temporary - booking Id needed for locking

    BookingGrade bg = agyService.getBookingGrade(bookingGradeApplicant.getBookingGradeId());
    Booking booking = agyService.getBooking(bg.getBookingId());

    dynaForm.set("bookingId", bg.getBookingId());

    dynaForm.set("bookingGradeApplicant", bookingGradeApplicant);

    dynaForm.set("booking", booking);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
