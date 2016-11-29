package com.helmet.application.agy;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingGradeAgyEntity;

public class BookingGradeView extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    BookingGradeAgyEntity bookingGradeAgyEntity = (BookingGradeAgyEntity) dynaForm.get("bookingGrade");

    bookingGradeAgyEntity = agyService.getBookingGradeAgyEntity(bookingGradeAgyEntity.getBookingGradeId(), getConsultantLoggedIn().getAgencyId());

    if (bookingGradeAgyEntity == null) { return mapping.findForward("illegalaccess"); }

    if (!bookingGradeAgyEntity.getViewed())
    {
      // update the bookingGrade as viewed
      int rc = agyService.updateBookingGradeAsViewed(bookingGradeAgyEntity.getBookingGradeId(), bookingGradeAgyEntity.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());
    }

    BigDecimal chargeRateValue = new BigDecimal(0);
    BigDecimal payRateValue = new BigDecimal(0);
    BigDecimal wageRateValue = new BigDecimal(0);

    if (bookingGradeAgyEntity.getBookingDateUsers() != null)
    {

      for (BookingDateUser bookingDate : bookingGradeAgyEntity.getBookingDateUsers())
      {
        chargeRateValue = chargeRateValue.add(bookingDate.getChargeRateValue());
        payRateValue = payRateValue.add(bookingDate.getPayRateValue());
        wageRateValue = wageRateValue.add(bookingDate.getWageRateValue());
      }

    }

    if (bookingGradeAgyEntity.getUndefinedShift())
    {
      // Booking using an Undefined Shift so use 'total' values. 
      dynaForm.set("chargeRateValue", bookingGradeAgyEntity.getValue());
      dynaForm.set("payRateValue", bookingGradeAgyEntity.getPayRateValue());
      dynaForm.set("wageRateValue", bookingGradeAgyEntity.getWageRateValue());
    }
    else
    {
      dynaForm.set("chargeRateValue", chargeRateValue);
      dynaForm.set("payRateValue", payRateValue);
      dynaForm.set("wageRateValue", wageRateValue);
    }

    dynaForm.set("bookingGrade", bookingGradeAgyEntity);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
