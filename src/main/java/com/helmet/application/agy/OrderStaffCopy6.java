package com.helmet.application.agy;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Utilities;
import com.helmet.bean.BookingDate;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Shift;
import com.helmet.bean.Uplift;

public class OrderStaffCopy6 extends OrderStaffCopyBase
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
    if (client.getClientId() == null || client.getClientId() == 0) 
    { 
      return mapping.findForward("orderStaffCopy"); 
    }

    ReasonForRequest reasonForRequest = (ReasonForRequest) dynaForm.get("reasonForRequest");
    if (reasonForRequest.getReasonForRequestId() == null || reasonForRequest.getReasonForRequestId() == 0) 
    { 
      return mapping.findForward("orderStaffCopy"); 
    }
    LocationUser locationUser = (LocationUser) dynaForm.get("location");
    if (locationUser.getLocationId() == null || locationUser.getLocationId() == 0) 
    { 
      return mapping.findForward("orderStaffCopy"); 
    }
    JobProfileUser jobProfileUser = (JobProfileUser) dynaForm.get("jobProfile");
    if (jobProfileUser.getJobProfileId() == null || jobProfileUser.getJobProfileId() == 0) 
    { 
      return mapping.findForward("orderStaffCopy"); 
    }
    String dates = (String) dynaForm.get("dates");
    if (dates == null || "".equals(dates)) 
    { 
      return mapping.findForward("orderStaffCopy"); 
    }
    BookingDate[] bookingDates = (BookingDate[]) dynaForm.get("bookingDates");
    if (bookingDates == null || bookingDates.length == 0) 
    { 
      return mapping.findForward("orderStaffCopy"); 
    }

    Integer onlyShiftId = getOnlyShiftId(bookingDates);
    Boolean undefinedShift = (Boolean)dynaForm.get("undefinedShift");
    
    List<Shift> shifts = (List<Shift>) dynaForm.get("shifts");

    BigDecimal totalHours = new BigDecimal(0);
    for (BookingDate bookingDate : bookingDates)
    {
      for (Shift shift : shifts)
      {
        if (shift.getShiftId().equals(bookingDate.getShiftId()))
        {
          if (!shift.getUndefined())
          {
            totalHours = totalHours.add(shift.getNoOfHours());
          }
          bookingDate.setShift(shift);
          break;
        }
      }
      // ensure all bookingDates have had a shift assigned
      if (bookingDate.getShiftStartTime() == null)
      {
        ActionMessages errors = new ActionMessages();
        MessageResources messageResources = getResources(request);
        errors.add("orderStaffCopy", new ActionMessage("errors.pleaseMakeASelection"));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
    }
    if (totalHours.equals(0))
    {
      // Must be an undefined shift. Preserve value on form.
      totalHours = (BigDecimal) dynaForm.get("totalHours");
    }
    dynaForm.set("totalHours", totalHours);
    if (onlyShiftId == null)
    {
      dynaForm.set("shiftName", null);
    }
    else
    {
      Shift onlyShift = getShiftFromList(onlyShiftId, shifts);
      dynaForm.set("shiftName", onlyShift.getName());
    }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();


    BigDecimal hourlyRate = (BigDecimal) dynaForm.get("hourlyRate");
//    if (hourlyRate == null)
//    {
//      LocationJobProfile locationJobProfile = agyService.getLocationJobProfileForLocationAndJobProfile(locationUser.getLocationId(), jobProfileUser.getJobProfileId());
//      hourlyRate = locationJobProfile.getRate();
//    }
//    dynaForm.set("hourlyRate", hourlyRate);
    List<PublicHoliday> publicHolidays = agyService.getPublicHolidaysForClient(client.getClientId());
    List<Uplift> uplifts = agyService.getUpliftsForClient(client.getClientId());
    BigDecimal rrp = calculateUpliftedRate(agyService, publicHolidays, uplifts, bookingDates, shifts, totalHours, hourlyRate);
    dynaForm.set("rrp", rrp);


    loadFormWithClientAgencyJobProfileGradeStuff(dynaForm, agyService, jobProfileUser, null, bookingDates, publicHolidays, uplifts, totalHours, hourlyRate, rrp, undefinedShift);    

    
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
