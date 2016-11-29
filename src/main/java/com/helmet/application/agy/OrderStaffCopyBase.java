package com.helmet.application.agy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientAgencyJobProfileUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Expense;
import com.helmet.bean.Grade;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Shift;
import com.helmet.bean.Uplift;


public class OrderStaffCopyBase extends AgyAction 
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  @Override
  protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    // TODO Auto-generated method stub
    return null;
  }

  protected Boolean isUndefinedShift(List<Shift> shifts)
  {
    Boolean undefinedShift = new Boolean(false);
    for (Shift shift: shifts)
    {
      if (shift.getUndefined())
      {
        // Undefined Shift.
        undefinedShift = true;
        break;
      }
    }
    return undefinedShift;
  }
  
  
  protected Boolean isUndefinedShift(BookingDate[] bookingDates)
  {
    Boolean undefinedShift = new Boolean(false);
    for (BookingDate bookingDate: bookingDates)
    {
      if (bookingDate.getShiftStartTime().equals(bookingDate.getShiftEndTime()))
      {
        // Undefined Shift.
        undefinedShift = true;
        break;
      }
    }
    return undefinedShift;
  }

  protected Integer getOnlyShiftId(BookingDate[] bookingDates)
  {
    Integer shiftId = null;
    Integer lastBookingDateShiftId = 0;
    boolean oneShiftForBookingDates = true;
    for (BookingDate bookingDate : bookingDates)
    {
      if (lastBookingDateShiftId.equals(0))
      {
        // first one
        lastBookingDateShiftId = bookingDate.getShiftId();
      }
      if (!bookingDate.getShiftId().equals(lastBookingDateShiftId))
      {
        // more than one shift selected so set the boolean to false and break
        // out of loop
        oneShiftForBookingDates = false;
        break;
      }
    }
    if (oneShiftForBookingDates)
    {
      shiftId = lastBookingDateShiftId;
    }
    return shiftId;
  }

  protected BigDecimal getNoOfHoursForShift(Integer shiftId, List<Shift> shifts)
  {
    BigDecimal shiftNoOfHours = new BigDecimal(0);
    for (Shift shift : shifts)
    {
      if (shift.getShiftId().equals(shiftId))
      {
        shiftNoOfHours = shift.getNoOfHours();
      }
    }
    return shiftNoOfHours;
  }
  
  protected Shift getShiftFromList(Integer shiftId, List<Shift> shifts)
  {
    for (Shift shift : shifts)
    {
      if (shift.getShiftId().equals(shiftId))
      {
        return shift;
      }
    }
    return null;
  }
  
  protected BigDecimal calculateTotalHoursFromBookingDates(BookingDate[] bookingDates, BigDecimal shiftNoOfHours, Boolean undefinedShift)
  {
    BigDecimal totalHours = new BigDecimal(0);
    if (!undefinedShift)
    {
      for (BookingDate bookingDate : bookingDates)
      {
        if (bookingDate.getShiftId() == null)
        {
          totalHours = totalHours.add(shiftNoOfHours);
        }
        else
        {
          totalHours = totalHours.add(bookingDate.getShiftNoOfHours());
        }
      }
    }    
    return totalHours;
  }
  
  protected Expense[] buildExpenseArray(String[] selectedExpenses, AgyService agyService)
  {
    // The Expense Array includes a dummy entry ([0]) for html CheckBox usage.
    Expense[] expenseArray = new Expense[selectedExpenses.length - 1]; 
    int e = 0;
    for (String expenseIdStr : selectedExpenses)
    {
      int expenseId = Integer.parseInt(expenseIdStr);
      if (expenseId > 0)
      {
        expenseArray[e] = agyService.getExpense(expenseId);
        e++;
      }
    }
    return expenseArray;
  }

  protected BigDecimal calculateUpliftedRate(AgyService agyService, List<PublicHoliday> publicHolidays, List<Uplift> uplifts, BookingDate[] bookingDates, List<Shift> shifts, BigDecimal totalHours, BigDecimal hourlyRate)
  {
    // CALCULATE 'UPLIFTED' RATE. NOTE. Updates BookingDates.
    BigDecimal rrp = Utilities.calculateIt(bookingDates, hourlyRate, publicHolidays, uplifts, true);      
    if (shifts.size() == 1 && shifts.get(0).getUndefined())
    {
      rrp = totalHours.multiply(hourlyRate);
    }
    return rrp;
  }
  
  protected void loadFormWithClientAgencyJobProfileGradeStuff(DynaValidatorForm dynaForm, 
                                                              AgyService agyService, 
                                                              JobProfileUser jobProfileUser,
                                                              Integer gradeId,
                                                              BookingDate[] bookingDates,
                                                              List<PublicHoliday> publicHolidays, 
                                                              List<Uplift> uplifts, 
                                                              BigDecimal totalHours, 
                                                              BigDecimal hourlyRate, 
                                                              BigDecimal rrp, 
                                                              Boolean undefinedShift
                                                             )
  {
    List<Grade> grades = agyService.getGradesForJobProfile(jobProfileUser.getJobProfileId());
    // Get all clientAgencyJobProfileUsers (client agencies that can supply the job profile).
    List<ClientAgencyJobProfileUser> clientAgencyJobProfileUsers = agyService.getClientAgencyJobProfileUsersForJobProfileAndAgency(jobProfileUser.getJobProfileId(), getConsultantLoggedIn().getAgencyId());
    // get all client agency grades - the rates - VERY SLOW QUERY
    List<ClientAgencyJobProfileGrade> clientAgencyJobProfileGradeList = agyService.getClientAgencyJobProfileGradesForJobProfileAndAgency(jobProfileUser.getJobProfileId(), getConsultantLoggedIn().getAgencyId());
    int noOfClientAgencyJobProfiles = clientAgencyJobProfileUsers.size();
    int noOfGrades = grades.size();
    // Create a vector of vectors of clientAgencyJobProfileGrades - grades on the outside, clientagencies on the inside.
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
    // the rrp and mark it then mark those above +1 and those below -1

    for (int ca = 0; ca < noOfClientAgencyJobProfiles; ca++)
    {
      BigDecimal closestRate = new BigDecimal(0);
      int closestGradeIndex = -1;
      int usedGradeIndex = -1;
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
          if (gradeId != null)
          {
            if (clientAgencyJobProfileGrade.getGradeId().equals(gradeId))
            {
              usedGradeIndex = g;
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
        ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser = null;
        if (usedGradeIndex == -1)
        {
          clientAgencyJobProfileGradeUser = agyService.getClientAgencyJobProfileGradeUser(closestClientAgencyJobProfileGrade.getClientAgencyJobProfileGradeId());
          clientAgencyJobProfileGradeUser.setValue(closestClientAgencyJobProfileGrade.getValue());
        }
        else
        {
          ClientAgencyJobProfileGrade usedClientAgencyJobProfileGrade = ((ClientAgencyJobProfileGrade) ((Vector) clientAgencyJobProfileGrades.get(usedGradeIndex)).get(ca));
          clientAgencyJobProfileGradeUser = agyService.getClientAgencyJobProfileGradeUser(usedClientAgencyJobProfileGrade.getClientAgencyJobProfileGradeId());
          clientAgencyJobProfileGradeUser.setValue(usedClientAgencyJobProfileGrade.getValue());
                 }
        caga[ca] = clientAgencyJobProfileGradeUser;
      }

    }

    dynaForm.set("clientAgencyJobProfileUsers", clientAgencyJobProfileUsers);
    dynaForm.set("grades", grades);
    dynaForm.set("clientAgencyJobProfileGrades", clientAgencyJobProfileGrades);
    dynaForm.set("clientAgencyJobProfileGradeUserArray", caga);
  }
  
}
