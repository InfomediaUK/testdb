package com.helmet.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.helmet.bean.BookingDateUserApplicant;

public class BookingDateUserApplicantReportGroup extends ClientSiteLocationReportGroup
{
  private List<BookingDateUserApplicant> listBookingDateUserApplicant;
  BigDecimal totalHours = new BigDecimal(0);
  BigDecimal totalAgreedValue = new BigDecimal(0); // Charge Rate
  BigDecimal totalAgreedWageRateValue = new BigDecimal(0); // Wage Rate
  BigDecimal totalActualValue = new BigDecimal(0); // Charge Rate
  BigDecimal totalActualWageRateValue = new BigDecimal(0); // Wage Rate
  BigDecimal totalActualHours = new BigDecimal(0);
  BigDecimal totalActualVatValue = new BigDecimal(0);
  BigDecimal totalExpenseValue = new BigDecimal(0);
  BigDecimal totalExpenseVatValue = new BigDecimal(0);
  BigDecimal totalTotalVatValue = new BigDecimal(0);
  BigDecimal totalActualTotalValue = new BigDecimal(0);

  BigDecimal totalNetValue = new BigDecimal(0);
  BigDecimal totalVatValue = new BigDecimal(0);
  BigDecimal totalValue = new BigDecimal(0);
  
  public BookingDateUserApplicantReportGroup(BookingDateUserApplicant bookingDateUserApplicant)
  {
    super(bookingDateUserApplicant.getClientId(), bookingDateUserApplicant.getClientName(), bookingDateUserApplicant.getSiteId(), bookingDateUserApplicant.getSiteName(), bookingDateUserApplicant.getLocationId(), bookingDateUserApplicant.getLocationName());
    listBookingDateUserApplicant = new ArrayList<BookingDateUserApplicant>();
  }


  public void addBookingDateUserApplicantToList(BookingDateUserApplicant bookingDateUserApplicant)
  {
    listBookingDateUserApplicant.add(bookingDateUserApplicant);
  }

  public List<BookingDateUserApplicant> getListBookingDateUserApplicant()
  {
    return listBookingDateUserApplicant;
  }

  public int getSize()
  {
    return listBookingDateUserApplicant.size();
  }


  public BigDecimal getTotalActualHours()
  {
    return totalActualHours;
  }


  public BigDecimal getTotalActualTotalValue()
  {
    return totalActualTotalValue;
  }


  public BigDecimal getTotalActualValue()
  {
    return totalActualValue;
  }


  public BigDecimal getTotalActualVatValue()
  {
    return totalActualVatValue;
  }


  public BigDecimal getTotalActualWageRateValue()
  {
    return totalActualWageRateValue;
  }


  public BigDecimal getTotalAgreedValue()
  {
    return totalAgreedValue;
  }


  public BigDecimal getTotalAgreedWageRateValue()
  {
    return totalAgreedWageRateValue;
  }


  public BigDecimal getTotalExpenseValue()
  {
    return totalExpenseValue;
  }


  public BigDecimal getTotalExpenseVatValue()
  {
    return totalExpenseVatValue;
  }


  public BigDecimal getTotalHours()
  {
    return totalHours;
  }


  public BigDecimal getTotalNetValue()
  {
    return totalNetValue;
  }


  public BigDecimal getTotalTotalVatValue()
  {
    return totalTotalVatValue;
  }


  public BigDecimal getTotalValue()
  {
    return totalValue;
  }


  public BigDecimal getTotalVatValue()
  {
    return totalVatValue;
  }

  // NOTE. This method is logically the same as:   protected void sortShiftTotals(List list, DynaValidatorForm dynaForm)
  //       in com.helmet.application.agy.abztract.AgyAction. Any changes MUST be done in both places.
  public void sortShiftTotals()
  {
    for (BookingDateUserApplicant bookingDateUserApplicant : listBookingDateUserApplicant)
    {
      // LYNDON. The following 'add' code did NOT have the defensive check for null in it. 11/07/2011
      if (bookingDateUserApplicant.getShiftNoOfHours() != null && bookingDateUserApplicant.getUndefinedShift() == false)
      {
        totalHours = totalHours.add(bookingDateUserApplicant.getShiftNoOfHours());
      }
      if (bookingDateUserApplicant.getChargeRateValue() != null && bookingDateUserApplicant.getUndefinedShift() == false)
      {
        totalAgreedValue = totalAgreedValue.add(bookingDateUserApplicant.getChargeRateValue());
      }
      // LYNDON. The following 'add' code did NOT have the defensive check for null in it. 11/07/2011
      if (bookingDateUserApplicant.getWageRateValue() != null && bookingDateUserApplicant.getUndefinedShift() == false)
      {
        totalAgreedWageRateValue = totalAgreedWageRateValue.add(bookingDateUserApplicant.getWageRateValue());
      }
      // if (bookingDateUserApplicant.getWorkedStatus() ==
      // BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED ||
      // bookingDateUserApplicant.getWorkedStatus() ==
      // BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED) {
      // ONLY authorized AND invoiced

      if (bookingDateUserApplicant.getWorkedChargeRateValue() != null)
      {
        totalActualValue = totalActualValue.add(bookingDateUserApplicant.getWorkedChargeRateValue());
      }
      if (bookingDateUserApplicant.getWorkedWageRateValue() != null)
      {
        totalActualWageRateValue = totalActualWageRateValue.add(bookingDateUserApplicant.getWorkedWageRateValue());
      }
      if (bookingDateUserApplicant.getWorkedNoOfHours() != null)
      {
        totalActualHours = totalActualHours.add(bookingDateUserApplicant.getWorkedNoOfHours());
      }
      if (bookingDateUserApplicant.getWorkedVatValue() != null)
      {
        totalActualVatValue = totalActualVatValue.add(bookingDateUserApplicant.getWorkedVatValue());
      }
      if (bookingDateUserApplicant.getExpenseValue() != null)
      {
        totalExpenseValue = totalExpenseValue.add(bookingDateUserApplicant.getExpenseValue());
      }
      if (bookingDateUserApplicant.getExpenseVatValue() != null)
      {
        totalExpenseVatValue = totalExpenseVatValue.add(bookingDateUserApplicant.getExpenseVatValue());
      }
      if (bookingDateUserApplicant.getTotalVatValue() != null)
      {
        totalTotalVatValue = totalTotalVatValue.add(bookingDateUserApplicant.getTotalVatValue());
      }
      if (bookingDateUserApplicant.getWorkedTotalValue() != null)
      {
        totalActualTotalValue = totalActualTotalValue.add(bookingDateUserApplicant.getWorkedTotalValue());
      }

      // }

      if (bookingDateUserApplicant.getWorkedTotalNetValue() != null)
      {
        totalNetValue = totalNetValue.add(bookingDateUserApplicant.getWorkedTotalNetValue());
      }
      if (bookingDateUserApplicant.getTotalVatValue() != null)
      {
        totalVatValue = totalVatValue.add(bookingDateUserApplicant.getTotalVatValue());
      }
      if (bookingDateUserApplicant.getWorkedTotalValue() != null)
      {
        totalValue = totalValue.add(bookingDateUserApplicant.getWorkedTotalValue());
      }
    }
  }
}
