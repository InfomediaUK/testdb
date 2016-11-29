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
import com.helmet.bean.BookingGradeAgy;


public class BookingGradeList extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    String bookingGradeStatusStr = (String) dynaForm.get("bookingGradeStatus");

    if (bookingGradeStatusStr != null && !bookingGradeStatusStr.equals(""))
    {
      try
      {
        Integer.parseInt(bookingGradeStatusStr);
      }
      catch (NumberFormatException e)
      {
        bookingGradeStatusStr = null;
      }
    }
    else
    {
      bookingGradeStatusStr = null;
    }

    String bookingGradeApplicantDateStatusStr = (String) dynaForm.get("bookingGradeApplicantDateStatus");

    if (bookingGradeApplicantDateStatusStr != null && !bookingGradeApplicantDateStatusStr.equals(""))
    {
      try
      {
        Integer.parseInt(bookingGradeApplicantDateStatusStr);
      }
      catch (NumberFormatException e)
      {
        bookingGradeApplicantDateStatusStr = null;
      }
    }
    else
    {
      bookingGradeApplicantDateStatusStr = null;
    }

    String viewedStr = (String) dynaForm.get("viewed");
    Boolean viewed = viewedStr == null ? null : viewedStr.equals("true");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    List<BookingGradeAgy> list = null;

    if (bookingGradeStatusStr != null)
    {
      Integer bookingGradeStatus = Integer.parseInt(bookingGradeStatusStr);
      list = agyService.getBookingGradeAgysForAgencyAndStatus(getConsultantLoggedIn().getAgencyId(), bookingGradeStatus, true, viewed);
    }
    else if (bookingGradeApplicantDateStatusStr != null)
    {
      Integer bookingGradeApplicantDateStatus = Integer.parseInt(bookingGradeApplicantDateStatusStr);
      list = agyService.getBookingGradeAgysForBookingGradeApplicantDateAgencyAndStatus(getConsultantLoggedIn().getAgencyId(), bookingGradeApplicantDateStatus, null);
    }
    else if (viewedStr != null && !viewedStr.equals(""))
    {
      list = agyService.getBookingGradeAgysForAgencyUnviewed(getConsultantLoggedIn().getAgencyId(), true);
    }
    else
    {
      list = agyService.getBookingGradeAgysForAgency(getConsultantLoggedIn().getAgencyId(), null);
    }

    dynaForm.set("list", list);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
