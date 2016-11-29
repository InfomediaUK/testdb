package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingGradeApplicantUserEntity extends BookingGradeApplicantUser
{

  private IntValue bookingExpensesCount;

  private BigDecimal totalHours = new BigDecimal(0);

  private BigDecimal totalRRPValue = new BigDecimal(0);

  private BigDecimal totalAgreedValue = new BigDecimal(0);

  private BigDecimal totalActualValue = new BigDecimal(0);

  private BigDecimal totalAgreedPayValue = new BigDecimal(0);

  private BigDecimal totalActualPayValue = new BigDecimal(0);

  private BigDecimal totalAgreedWageValue = new BigDecimal(0);

  private BigDecimal totalActualWageValue = new BigDecimal(0);

  private BigDecimal totalActualHours = new BigDecimal(0);

  private BigDecimal totalExpenseValue = new BigDecimal(0);

  private BigDecimal totalExpenseVatValue = new BigDecimal(0);
  
  private Boolean undefinedShift = new Boolean(false);

  private List<BookingGradeApplicantDateUserEntity> bookingGradeApplicantDateUserEntities;

  private List<BigDecimal> upliftFactors;

  public List<BookingGradeApplicantDateUserEntity> getBookingGradeApplicantDateUserEntities()
  {
    return bookingGradeApplicantDateUserEntities;
  }

  public void setBookingGradeApplicantDateUserEntities(List<BookingGradeApplicantDateUserEntity> bookingGradeApplicantDateUserEntities)
  {
    this.bookingGradeApplicantDateUserEntities = bookingGradeApplicantDateUserEntities;
    undefinedShift = checkForUndefinedShift();
    sortTotals();
  }

  public List<BigDecimal> getUpliftFactors()
  {
    return upliftFactors;
  }

  public void setUpliftFactors(List<BigDecimal> upliftFactors)
  {
    this.upliftFactors = upliftFactors;
  }

  public void sortUpliftFactors()
  {
    upliftFactors = new ArrayList<BigDecimal>();

    for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity : bookingGradeApplicantDateUserEntities)
    {
      if (bookingGradeApplicantDateUserEntity.getBookingDateHours() != null)
      {
        for (BookingDateHour bookingDateHour : bookingGradeApplicantDateUserEntity.getBookingDateHours())
        {
          // check if the upliftFactor already exists in the list
          boolean upliftExists = false;
          for (BigDecimal upliftFactor : upliftFactors)
          {
            if (upliftFactor.compareTo(bookingDateHour.getUpliftFactor()) == 0)
            {
              // already exists
              upliftExists = true;
              break;
            }
          }
          if (!upliftExists)
          {
            upliftFactors.add(bookingDateHour.getUpliftFactor());
          }
        }
      }
    }

    // remove uplift stuff if only one and = 1
    if (upliftFactors.size() == 1 && upliftFactors.get(0).compareTo(new BigDecimal(1)) == 0)
    {
      upliftFactors.remove(0);
    }

    Collections.sort(upliftFactors);

  }

  public BigDecimal getTotalActualHours()
  {
    return totalActualHours;
  }

  public void setTotalActualHours(BigDecimal totalActualHours)
  {
    this.totalActualHours = totalActualHours;
  }

  public BigDecimal getTotalActualValue()
  {
    return totalActualValue;
  }

  public void setTotalActualValue(BigDecimal totalActualValue)
  {
    this.totalActualValue = totalActualValue;
  }

  public BigDecimal getTotalAgreedValue()
  {
    return totalAgreedValue;
  }

  public void setTotalAgreedValue(BigDecimal totalAgreedValue)
  {
    this.totalAgreedValue = totalAgreedValue;
  }

  public BigDecimal getTotalHours()
  {
    return totalHours;
  }

  public void setTotalHours(BigDecimal totalHours)
  {
    this.totalHours = totalHours;
  }

  public BigDecimal getTotalRRPValue()
  {
    return totalRRPValue;
  }

  public void setTotalRRPValue(BigDecimal totalRRPValue)
  {
    this.totalRRPValue = totalRRPValue;
  }

  public BigDecimal getTotalActualPayValue()
  {
    return totalActualPayValue;
  }

  public void setTotalActualPayValue(BigDecimal totalActualPayValue)
  {
    this.totalActualPayValue = totalActualPayValue;
  }

  public BigDecimal getTotalAgreedPayValue()
  {
    return totalAgreedPayValue;
  }

  public void setTotalAgreedPayValue(BigDecimal totalAgreedPayValue)
  {
    this.totalAgreedPayValue = totalAgreedPayValue;
  }

  public BigDecimal getTotalExpenseValue()
  {
    return totalExpenseValue;
  }

  public void setTotalExpenseValue(BigDecimal totalExpenseValue)
  {
    this.totalExpenseValue = totalExpenseValue;
  }

  public BigDecimal getTotalExpenseVatValue()
  {
    return totalExpenseVatValue;
  }

  public void setTotalExpenseVatValue(BigDecimal totalExpenseVatValue)
  {
    this.totalExpenseVatValue = totalExpenseVatValue;
  }

  public BigDecimal getTotalActualWageValue()
  {
    return totalActualWageValue;
  }

  public void setTotalActualWageValue(BigDecimal totalActualWageValue)
  {
    this.totalActualWageValue = totalActualWageValue;
  }

  public BigDecimal getTotalAgreedWageValue()
  {
    return totalAgreedWageValue;
  }

  public void setTotalAgreedWageValue(BigDecimal totalAgreedWageValue)
  {
    this.totalAgreedWageValue = totalAgreedWageValue;
  }

  public IntValue getBookingExpensesCount()
  {
    return bookingExpensesCount;
  }

  public void setBookingExpensesCount(IntValue bookingExpensesCount)
  {
    this.bookingExpensesCount = bookingExpensesCount;
    // here because the uplifts stuff is loaded at the same time as the expenses
    sortUpliftFactors();
  }

  public Boolean checkForUndefinedShift()
  {
    for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser : bookingGradeApplicantDateUserEntities)
    {
      if (bookingGradeApplicantDateUser.getUndefinedShift())
      {
        return new Boolean(true);
      }
    }
    return new Boolean(false);
  }
  
  protected void sortTotals()
  {

    for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser : bookingGradeApplicantDateUserEntities)
    {
      if (   bookingGradeApplicantDateUser.getStatus() == BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OPEN
          || bookingGradeApplicantDateUser.getStatus() == BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OFFERED
          || bookingGradeApplicantDateUser.getStatus() == BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED)
      {
        // LYNDON. Put in defensive checks on values. 13/07/2011.
        if (bookingGradeApplicantDateUser.getShiftNoOfHours() != null && bookingGradeApplicantDateUser.getUndefinedShift() == false)
        {
          // NOT an Undefined Shift, so total up ShiftNoOfHours.
          totalHours = totalHours.add(bookingGradeApplicantDateUser.getShiftNoOfHours());
        }        
        if (bookingGradeApplicantDateUser.getBookingDateValue() != null)
        {
          totalRRPValue = totalRRPValue.add(bookingGradeApplicantDateUser.getBookingDateValue());
        }        
        if (bookingGradeApplicantDateUser.getValue() != null && bookingGradeApplicantDateUser.getUndefinedShift() == false)
        {
          // NOT an Undefined Shift, so total up Value.
          totalAgreedValue = totalAgreedValue.add(bookingGradeApplicantDateUser.getValue());
        }        
        if (bookingGradeApplicantDateUser.getPayRateValue() != null && bookingGradeApplicantDateUser.getUndefinedShift() == false)
        {
          // NOT an Undefined Shift, so total up PayRateValue.
          totalAgreedPayValue = totalAgreedPayValue.add(bookingGradeApplicantDateUser.getPayRateValue());
        }        
        if (bookingGradeApplicantDateUser.getWageRateValue() != null && bookingGradeApplicantDateUser.getUndefinedShift() == false)
        {
          // NOT an Undefined Shift, so total up WageRateValue.
          totalAgreedWageValue = totalAgreedWageValue.add(bookingGradeApplicantDateUser.getWageRateValue());
        }        
        if (bookingGradeApplicantDateUser.getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED
            || bookingGradeApplicantDateUser.getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED)
        {
          // ONLY authorized AND invoiced
          totalActualValue = totalActualValue.add(bookingGradeApplicantDateUser.getWorkedChargeRateValue());
          totalActualHours = totalActualHours.add(bookingGradeApplicantDateUser.getWorkedNoOfHours());

          totalActualPayValue = totalActualPayValue.add(bookingGradeApplicantDateUser.getWorkedPayRateValue());
          totalActualWageValue = totalActualWageValue.add(bookingGradeApplicantDateUser.getWorkedWageRateValue());

          totalExpenseValue = totalExpenseValue.add(bookingGradeApplicantDateUser.getExpenseValue());
          totalExpenseVatValue = totalExpenseVatValue.add(bookingGradeApplicantDateUser.getExpenseVatValue());
        }

      }
    }
    if (undefinedShift)
    {
      // Undefined Shift, so total calculate values.
      totalHours = getBookingNoOfHours();
      totalAgreedValue = totalHours.multiply(getRate());
      totalAgreedPayValue = totalHours.multiply(getPayRate());
      totalAgreedWageValue = totalHours.multiply(getWageRate());
    }
  }

  public Date getMinBookingDate()
  {

    Date returnValue = null;
    if (bookingGradeApplicantDateUserEntities != null)
    {
      for (int i = 0; i < bookingGradeApplicantDateUserEntities.size(); i++)
      {
        if (bookingGradeApplicantDateUserEntities.get(i).getBookingDateStatus() != BookingDate.BOOKINGDATE_STATUS_CANCELLED)
        {
          returnValue = bookingGradeApplicantDateUserEntities.get(i).getBookingDate();
          break;
        }
      }
    }
    return returnValue;

  }

  public Time getMinBookingDateShiftStartTime()
  {

    Time returnValue = null;
    if (bookingGradeApplicantDateUserEntities != null)
    {
      for (int i = 0; i < bookingGradeApplicantDateUserEntities.size(); i++)
      {
        if (bookingGradeApplicantDateUserEntities.get(i).getBookingDateStatus() != BookingDate.BOOKINGDATE_STATUS_CANCELLED)
        {
          returnValue = bookingGradeApplicantDateUserEntities.get(i).getShiftStartTime();
          break;
        }
      }
    }
    return returnValue;

  }

  public Date getMaxBookingDate()
  {

    Date returnValue = null;
    if (bookingGradeApplicantDateUserEntities != null)
    {
      for (int i = bookingGradeApplicantDateUserEntities.size() - 1; i > -1; i--)
      {
        if (bookingGradeApplicantDateUserEntities.get(i).getBookingDateStatus() != BookingDate.BOOKINGDATE_STATUS_CANCELLED)
        {
          returnValue = bookingGradeApplicantDateUserEntities.get(i).getBookingDate();
          break;
        }
      }
    }
    return returnValue;

  }

  public String getShiftName()
  {
    String shiftName = null;
    for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity : bookingGradeApplicantDateUserEntities)
    {
      if (shiftName == null)
      {
        shiftName = bookingGradeApplicantDateUserEntity.getShiftName();
      }
      else
      {
        if (!bookingGradeApplicantDateUserEntity.getShiftName().equals(shiftName)) { return null; }
      }
    }
    return shiftName;
  }

  public BigDecimal getNoOfHours()
  {
    BigDecimal noOfHours = new BigDecimal(0);
    for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity : bookingGradeApplicantDateUserEntities)
    {
      noOfHours = noOfHours.add(bookingGradeApplicantDateUserEntity.getShiftNoOfHours());
    }
    return noOfHours;
  }

}