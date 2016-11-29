package com.helmet.application.agy;

import java.math.BigDecimal;
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
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.DressCode;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Uplift;

public class OrderStaffCopy7 extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    Integer page = (Integer)dynaForm.get("page");
    Boolean complete = (Boolean)dynaForm.get("complete");
    if (isCancelled(request))
    {
      dynaForm.set("page", page - 1);
      return mapping.findForward("back");
    }

    ClientUser client = (ClientUser) dynaForm.get("client");
    if (client.getClientId() == null || client.getClientId() == 0) { return mapping.findForward("orderStaffCopy"); }

    ReasonForRequest reasonForRequest = (ReasonForRequest) dynaForm.get("reasonForRequest");
    if (reasonForRequest.getReasonForRequestId() == null || reasonForRequest.getReasonForRequestId() == 0) { return mapping.findForward("orderStaffCopy"); }
    LocationUser locationUser = (LocationUser) dynaForm.get("location");
    if (locationUser.getLocationId() == null || locationUser.getLocationId() == 0) { return mapping.findForward("orderStaffCopy"); }
    JobProfileUser jobProfileUser = (JobProfileUser) dynaForm.get("jobProfile");
    if (jobProfileUser.getJobProfileId() == null || jobProfileUser.getJobProfileId() == 0) { return mapping.findForward("orderStaffCopy"); }
    String dates = (String) dynaForm.get("dates");
    if (dates == null || "".equals(dates)) { return mapping.findForward("orderStaffCopy"); }
    BookingDate[] bookingDates = (BookingDate[]) dynaForm.get("bookingDates");
    if (bookingDates == null || bookingDates.length == 0) { return mapping.findForward("orderStaffCopy"); }
    ClientAgencyJobProfileGradeUser[] clientAgencyJobProfileGradeUserArray = (ClientAgencyJobProfileGradeUser[]) dynaForm.get("clientAgencyJobProfileGradeUserArray");
    if (clientAgencyJobProfileGradeUserArray.length == 0) { return mapping.findForward("orderStaffCopy"); }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    List<PublicHoliday> publicHolidays = agyService.getPublicHolidaysForClient(client.getClientId());
    List<Uplift> uplifts = agyService.getUpliftsForClient(client.getClientId());

    // CALCULATE 'UPLIFTED' RATE

    BigDecimal hourlyRate = (BigDecimal) dynaForm.get("hourlyRate");

    if (hourlyRate == null) { return mapping.findForward("orderStaffCopy"); }
    BigDecimal rrp = null;
    BigDecimal totalHours = null;
    rrp = Utilities.calculateIt(bookingDates, hourlyRate, publicHolidays, uplifts, true);
    Boolean undefinedShift = (Boolean) dynaForm.get("undefinedShift");
    if (undefinedShift)
    {
      totalHours = (BigDecimal) dynaForm.get("totalHours");
      rrp = totalHours.multiply(hourlyRate);
    }
    //    else
    //    {
    //      rrp = Utilities.calculateIt(bookingDates, hourlyRate, publicHolidays, uplifts, true);
    //    }
    dynaForm.set("rrp", rrp);

    for (int i = 0; i < clientAgencyJobProfileGradeUserArray.length; i++)
    {
      ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser = clientAgencyJobProfileGradeUserArray[i];
      clientAgencyJobProfileGradeUser = agyService.getClientAgencyJobProfileGradeUser(clientAgencyJobProfileGradeUser.getClientAgencyJobProfileGradeId());
      BigDecimal value = null;
      if (undefinedShift)
      {
        // Undefined Shift. Calculate the Value.
        value = totalHours.multiply(clientAgencyJobProfileGradeUser.getRate());
      }
      else
      {
        value = Utilities.calculateIt(bookingDates, clientAgencyJobProfileGradeUser.getRate(), publicHolidays, uplifts);
      }
      clientAgencyJobProfileGradeUser.setValue(value);
      clientAgencyJobProfileGradeUserArray[i] = clientAgencyJobProfileGradeUser;
    }

    List<DressCode> list = agyService.getDressCodesForLocation(locationUser.getLocationId());
    dynaForm.set("list", list);
    Integer noOfDressCodes = list.size();
    if (noOfDressCodes == 1)
    {
      dynaForm.set("dressCode", list.get(0));
    }
    dynaForm.set("noOfDressCodes", noOfDressCodes);

    if (noOfDressCodes < 2 && !locationUser.getSingleCandidateShow() && !locationUser.getCvRequiredShow() && !locationUser.getInterviewRequiredShow() && !locationUser.getCanProvideAccommodationShow()
        && !locationUser.getCarRequiredShow())
    {
      // nothing to show so skip the step
      //
      // determine whether the user is going forwards (next) or backwards (back)
      boolean userPressedNext = page == 6;
      // miss out this step and based on the mapping config go to the next
      if (userPressedNext)
      {
        dynaForm.set("page", page + 1);
        return mapping.findForward("noSpecificsSuccess");
      }
      else
      {
        dynaForm.set("page", page - 1);
        return mapping.findForward("noSpecificsBack");
      }
    }
    if (complete)
    {
      return mapping.findForward("complete");
    }
    else
    {
      return mapping.findForward("success");
    }
  }

}
