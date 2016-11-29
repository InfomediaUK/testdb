package com.helmet.application.agy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientAgencyJobProfileUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Grade;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Shift;
import com.helmet.bean.Uplift;

public class OrderStaff6 extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    if (isCancelled(request))
    {
      dynaForm.set("page", (Integer) dynaForm.get("page") - 1);
      return mapping.findForward("back");
    }

    ClientUser client = (ClientUser) dynaForm.get("client");
    if (client.getClientId() == null || client.getClientId() == 0) 
    { 
      return mapping.findForward("orderStaff"); 
    }

    ReasonForRequest reasonForRequest = (ReasonForRequest) dynaForm.get("reasonForRequest");
    if (reasonForRequest.getReasonForRequestId() == null || reasonForRequest.getReasonForRequestId() == 0) 
    { 
      return mapping.findForward("orderStaff"); 
    }
    LocationUser locationUser = (LocationUser) dynaForm.get("location");
    if (locationUser.getLocationId() == null || locationUser.getLocationId() == 0) 
    { 
      return mapping.findForward("orderStaff"); 
    }
    JobProfileUser jobProfileUser = (JobProfileUser) dynaForm.get("jobProfile");
    if (jobProfileUser.getJobProfileId() == null || jobProfileUser.getJobProfileId() == 0) 
    { 
      return mapping.findForward("orderStaff"); 
    }
    String dates = (String) dynaForm.get("dates");
    if (dates == null || "".equals(dates)) 
    { 
      return mapping.findForward("orderStaff"); 
    }
    BookingDate[] bookingDates = (BookingDate[]) dynaForm.get("bookingDates");
    if (bookingDates == null || bookingDates.length == 0) 
    { 
      return mapping.findForward("orderStaff"); 
    }

    Boolean undefinedShift = (Boolean)dynaForm.get("undefinedShift");
    
    List<Shift> shifts = (List<Shift>) dynaForm.get("shifts");

    //BigDecimal totalHours = (BigDecimal) dynaForm.get("totalHours");
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
          // bookingDate.setShift(shift);

          bookingDate.setShiftName(shift.getName());
          bookingDate.setShiftCode(shift.getCode());
          bookingDate.setShiftUseUplift(shift.getUseShiftUplift());
          bookingDate.setShiftUpliftFactor(shift.getUpliftFactor());
          bookingDate.setShiftUpliftValue(shift.getUpliftValue());
          bookingDate.setShiftStartTime(shift.getStartTime());
          bookingDate.setShiftEndTime(shift.getEndTime());
          bookingDate.setShiftBreakStartTime(shift.getBreakStartTime());
          bookingDate.setShiftBreakEndTime(shift.getBreakEndTime());
          bookingDate.setShiftNoOfHours(shift.getNoOfHours());
          bookingDate.setShiftBreakNoOfHours(shift.getBreakNoOfHours());
          bookingDate.setShiftOvertimeNoOfHours(shift.getOvertimeNoOfHours());
          bookingDate.setShiftOvertimeUpliftFactor(shift.getOvertimeUpliftFactor());

          break;
        }
      }
      // ensure all bookingDates have had a shift assigned
      if (bookingDate.getShiftStartTime() == null)
      {
        ActionMessages errors = new ActionMessages();
        MessageResources messageResources = getResources(request);
        errors.add("orderStaff", new ActionMessage("errors.pleaseMakeASelection"));
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

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<Grade> list = agyService.getGradesForJobProfile(jobProfileUser.getJobProfileId());
    dynaForm.set("list", list);

    // CALCULATE 'UPLIFTED' RATE

    List<PublicHoliday> publicHolidays = agyService.getPublicHolidaysForClient(client.getClientId());
    List<Uplift> uplifts = agyService.getUpliftsForClient(client.getClientId());

    LocationJobProfile locationJobProfile = agyService.getLocationJobProfileForLocationAndJobProfile(locationUser.getLocationId(), jobProfileUser.getJobProfileId());

    BigDecimal hourlyRate = (BigDecimal) dynaForm.get("hourlyRate");

    if (hourlyRate == null)
    {
      hourlyRate = locationJobProfile.getRate();
      dynaForm.set("hourlyRate", hourlyRate);
    }
    BigDecimal rrp = null;
    rrp = Utilities.calculateIt(bookingDates, hourlyRate, publicHolidays, uplifts, true);      
    if (shifts.size() == 1 && shifts.get(0).getUndefined())
    {
      rrp = totalHours.multiply(hourlyRate);
    }
//    else
//    {
//      rrp = Utilities.calculateIt(bookingDates, hourlyRate, publicHolidays, uplifts, true);      
//    }
    dynaForm.set("rrp", rrp);

    // get all grades
    List<Grade> grades = list; // mgrService.getGradesForJobProfile(jobProfileUser.getJobProfileId());

    // get all clientAgencyJobProfileUsers (client agencies that can suppli the
    // job profile)
    List<ClientAgencyJobProfileUser> clientAgencyJobProfileUsers = agyService.getClientAgencyJobProfileUsersForJobProfileAndAgency(jobProfileUser.getJobProfileId(), getConsultantLoggedIn()
        .getAgencyId());
    // List<ClientAgencyJobProfileUser> clientAgencyJobProfileUsers =
    // agyService.getClientAgencyJobProfileUsersForJobProfile(jobProfileUser.getJobProfileId());

    // get all client agency grades - the rates
    List<ClientAgencyJobProfileGrade> clientAgencyJobProfileGradeList = agyService.getClientAgencyJobProfileGradesForJobProfileAndAgency(jobProfileUser.getJobProfileId(), getConsultantLoggedIn()
        .getAgencyId());
    // List<ClientAgencyJobProfileGrade> clientAgencyJobProfileGradeList =
    // agyService.getClientAgencyJobProfileGradesForJobProfile(jobProfileUser.getJobProfileId());

    int noOfClientAgencyJobProfiles = clientAgencyJobProfileUsers.size();
    int noOfGrades = grades.size();

    // create a vector of vectors of clientAgencyJobProfileGrades - grades on
    // the outside, clientagencies on the inside
    Vector clientAgencyJobProfileGrades = new Vector();
    for (int i = 0; i < noOfGrades; i++)
    {
      Vector vi = new Vector();
      for (int j = 0; j < noOfClientAgencyJobProfiles; j++)
      {
        vi.add(null);
      }
      clientAgencyJobProfileGrades.add(vi);
    }

    for (ClientAgencyJobProfileGrade clientAgencyJobProfileGrade : clientAgencyJobProfileGradeList)
    {

      clientAgencyJobProfileGrade.setRank(999);

      // find clientAgency index
      int clientAgencyIndex = -1;
      for (int i = 0; i < clientAgencyJobProfileUsers.size(); i++)
      {
        ClientAgencyJobProfileUser cajpu = (ClientAgencyJobProfileUser) clientAgencyJobProfileUsers.get(i);
        if (cajpu.getClientAgencyJobProfileId().equals(clientAgencyJobProfileGrade.getClientAgencyJobProfileId()))
        {
          clientAgencyIndex = i;
          break;
        }
      }
      // find grade index
      int gradeIndex = -1;
      for (int i = 0; i < grades.size(); i++)
      {
        Grade grade = (Grade) grades.get(i);
        if (grade.getGradeId().equals(clientAgencyJobProfileGrade.getGradeId()))
        {
          gradeIndex = i;
          break;
        }
      }
      if (clientAgencyIndex > -1 && gradeIndex > -1)
      {
        // update vector
        ((Vector) clientAgencyJobProfileGrades.get(gradeIndex)).set(clientAgencyIndex, clientAgencyJobProfileGrade);
      }
    }

    boolean firstTime = false;

    ClientAgencyJobProfileGradeUser[] caga = (ClientAgencyJobProfileGradeUser[]) dynaForm.get("clientAgencyJobProfileGradeUserArray");
    if (caga.length == 0)
    {
      firstTime = true;
      caga = new ClientAgencyJobProfileGradeUser[noOfClientAgencyJobProfiles];
    }

    // Loop though each agency, find the grade that is closest (but under) to
    // the rrp and mark it
    // then mark those above +1 and those below -1

    for (int ca = 0; ca < noOfClientAgencyJobProfiles; ca++)
    {
      BigDecimal closestRate = new BigDecimal(0);
      int closestGradeIndex = -1;
      for (int g = 0; g < noOfGrades; g++)
      {
        ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = ((ClientAgencyJobProfileGrade) ((Vector) clientAgencyJobProfileGrades.get(g)).get(ca));
        if (clientAgencyJobProfileGrade != null)
        {
          // CALCULATE 'UPLIFTED' RATE
          BigDecimal currentValue = null;
          BigDecimal commission = null;
          if (clientAgencyJobProfileGrade.getPayRate().equals(0))
          {
            currentValue = Utilities.calculateIt(bookingDates, clientAgencyJobProfileGrade.getRate(), publicHolidays, uplifts);
          }
          else
          {
            currentValue = Utilities.calculateIt(bookingDates, clientAgencyJobProfileGrade.getPayRate(), publicHolidays, uplifts);
            commission = (clientAgencyJobProfileGrade.getRate().subtract(clientAgencyJobProfileGrade.getPayRate())).multiply(totalHours);
            currentValue = currentValue.add(commission);
          }
          if (undefinedShift)
          {
            // Undefined Shift. Calculate the Value.
            clientAgencyJobProfileGrade.setValue(totalHours.multiply(clientAgencyJobProfileGrade.getRate()));
          }
          else
          {  
            clientAgencyJobProfileGrade.setValue(currentValue);
          }
          //
          if (currentValue.compareTo(rrp) <= 0)
          { // currentValue <= rrp
            // cheaper than recommended
            if (closestGradeIndex == -1 || (rrp.subtract(currentValue).compareTo(rrp.subtract(closestRate)) <= 0))
            {
              // first or closer
              closestGradeIndex = g;
              closestRate = currentValue;
            }
          }
        }
      }
      if (closestGradeIndex == -1)
      {
        // none found <= rrp so go for the cheapest
        for (int g = 0; g < noOfGrades; g++)
        {
          ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = ((ClientAgencyJobProfileGrade) ((Vector) clientAgencyJobProfileGrades.get(g)).get(ca));
          if (clientAgencyJobProfileGrade != null)
          {

            BigDecimal currentValue = clientAgencyJobProfileGrade.getValue();
            // 
            if (closestGradeIndex == -1 || (rrp.subtract(currentValue).compareTo(rrp.subtract(closestRate)) >= 0))
            {
              // first or closer
              closestGradeIndex = g;
              closestRate = currentValue;
            }
          }
        }
      }

      ClientAgencyJobProfileGrade closestClientAgencyJobProfileGrade = ((ClientAgencyJobProfileGrade) ((Vector) clientAgencyJobProfileGrades.get(closestGradeIndex)).get(ca));
      if (closestClientAgencyJobProfileGrade != null)
      {
        closestClientAgencyJobProfileGrade.setRank(0);
      }

      // loop through the others marking accordingly
      for (int g = closestGradeIndex + 1; g < noOfGrades; g++)
      {
        ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = ((ClientAgencyJobProfileGrade) ((Vector) clientAgencyJobProfileGrades.get(g)).get(ca));
        if (clientAgencyJobProfileGrade != null)
        {
          clientAgencyJobProfileGrade.setRank(g - closestGradeIndex);
        }
      }
      for (int g = closestGradeIndex - 1; g >= 0; g--)
      {
        ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = ((ClientAgencyJobProfileGrade) ((Vector) clientAgencyJobProfileGrades.get(g)).get(ca));
        if (clientAgencyJobProfileGrade != null)
        {
          clientAgencyJobProfileGrade.setRank(g - closestGradeIndex);
        }
      }

      if (firstTime)
      {
        caga[ca] = agyService.getClientAgencyJobProfileGradeUser(closestClientAgencyJobProfileGrade.getClientAgencyJobProfileGradeId());
      }

    }

    dynaForm.set("clientAgencyJobProfileUsers", clientAgencyJobProfileUsers);
    dynaForm.set("grades", grades);
    dynaForm.set("clientAgencyJobProfileGrades", clientAgencyJobProfileGrades);

    dynaForm.set("clientAgencyJobProfileGradeUserArray", caga);

    return mapping.findForward("success");
  }

}
