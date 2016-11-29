package com.helmet.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.AgencyInvoiceCredit;
import com.helmet.bean.AgencyInvoiceUserEntity;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateExpenseUser;
import com.helmet.bean.BookingDateHour;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingDateUserApplicantEntity;
import com.helmet.bean.BookingUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Shift;
import com.helmet.bean.Uplift;
import com.helmet.bean.UpliftMinute;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.HeaderFooter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PRAcroForm;
import com.itextpdf.text.pdf.PdfCopy;
//import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.SimpleBookmark;

public class Utilities
{

  private static final int PUBLIC_HOLIDAY_DOW = 0;

  private static final BigDecimal divisor100 = new BigDecimal(100);

  private static final BaseColor DEFAULT_BACKGROUNDCOLOR = new BaseColor(255, 255, 255);

  private static final Font DEFAULT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));

  private static final Font DEFAULT_FONTBOLD = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));

  private static final Font TH_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));

  private static final Font TH_FONTBIG = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  private static final BaseColor TH_BACKGROUNDCOLOR = new BaseColor(224, 224, 224);

  private static final Font TD_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new BaseColor(0, 0, 0));

  private static final Font TD_FONTBOLD = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));

  private static final Font TD_FONTBIG = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new BaseColor(0, 0, 0));

  private static final Font TD_FONTBIGBOLD = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  private static final Font SUMMARY_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));

  private static final Font FOOTER_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new BaseColor(0, 0, 0));

  // Open named file and return the contents.
  public static String openFile(String fileName) throws IOException
  {
    // Open a file of the given name.
    File file = new File(fileName);

    // Get the size of the opened file.
    int size = (int) file.length();

    // Set to zero a counter for counting the number of
    // characters that have been read from the file.
    int chars_read = 0;

    // Create an input reader based on the file, so we can read its data.
    // FileReader handles international character encoding conversions.
    FileReader in = new FileReader(file);
    // Create a character array of the size of the file,
    // to use as a data buffer, into which we will read
    // the text data.
    char[] data = new char[size];
    try
    {
      // Read all available characters into the buffer.
      while (in.ready())
      {
        // Increment the count for each character read,
        // and accumulate them in the data buffer.
        chars_read += in.read(data, chars_read, size - chars_read);
      }
    }
    finally
    {
      in.close();
    }
    return new String(data, 0, chars_read);
  }

  // Save named file with the contents.
  public static void saveFile(String fileName, String contents) throws IOException
  {
    // Open a file of the current name.
    File file = new File(fileName);

    // Create an output writer that will write to that file.
    // FileWriter handles international characters encoding conversions.
    FileWriter out = new FileWriter(file);
    try
    {
      out.write(contents);
    }
    finally
    {
      out.close();
    }
  }

  public static BigDecimal calculateIt(BookingDate[] bookingDates, BigDecimal rate, List<PublicHoliday> publicHolidays, List<Uplift> uplifts)
  {

    return calculateIt(bookingDates, rate, publicHolidays, uplifts, false);

  }

  public static BigDecimal calculateIt(BookingDate[] bookingDates, BigDecimal rate, List<PublicHoliday> publicHolidays, List<Uplift> uplifts, boolean updateBookingDates)
  {

    BigDecimal totalValue = new BigDecimal(0);

    for (BookingDate bookingDate : bookingDates)
    {

      java.sql.Date theDate = bookingDate.getBookingDate();

      Shift shift = new Shift();
      shift.setUseShiftUplift(bookingDate.getShiftUseUplift());
      shift.setUpliftFactor(bookingDate.getShiftUpliftFactor());
      shift.setUpliftValue(bookingDate.getShiftUpliftValue());
      shift.setNoOfHours(bookingDate.getShiftNoOfHours());
      //
      shift.setStartTime(bookingDate.getShiftStartTime());
      shift.setEndTime(bookingDate.getShiftEndTime());
      shift.setBreakStartTime(bookingDate.getShiftBreakStartTime());
      shift.setBreakEndTime(bookingDate.getShiftBreakEndTime());

      BigDecimal value = null;
      if (shift.getUndefined())
      {
        value = new BigDecimal(0);
      }
      else
      {
        value = calculateIt(theDate, shift, rate, publicHolidays, uplifts);
      }

      if (updateBookingDates)
      {
        bookingDate.setValue(value);
      }

      totalValue = totalValue.add(value);

    }
    return totalValue;
  }

  public static List<BookingDateHour> calculateIt(BookingDateUserApplicant bookingDate, List<PublicHoliday> publicHolidays, List<Uplift> uplifts)
  {

    // for this particular date, using the 'worked' shift details held and the
    // wtd, ni etc
    // calculate the workedValue (chargeRateValue) and the workedPayRateValue by
    // working out the details for each hour and then
    // summing them together, these hourly recordsw ill eventaull be returned
    // and saved as bookingDateHour records.

    // for each hour of the shift and the break calculate the value (break
    // values will be negative)
    // then loop through them to calculate the values for the date

    List<BookingDateHour> bookingDateHours = getBookingDateHours(bookingDate, publicHolidays, uplifts);

    BigDecimal workedPayRateValue = new BigDecimal(0);
    BigDecimal workedWtdValue = new BigDecimal(0);
    BigDecimal workedNiValue = new BigDecimal(0);
    BigDecimal workedCommissionValue = new BigDecimal(0);
    BigDecimal workedWageRateValue = new BigDecimal(0);

    BigDecimal workedChargeRateVatValue = new BigDecimal(0);
    BigDecimal workedPayRateVatValue = new BigDecimal(0);
    BigDecimal workedWtdVatValue = new BigDecimal(0);
    BigDecimal workedNiVatValue = new BigDecimal(0);
    BigDecimal workedCommissionVatValue = new BigDecimal(0);

    BigDecimal workedVatValue = new BigDecimal(0);

    boolean hasUplift = false;
    for (BookingDateHour bookingDateHour : bookingDateHours)
    {
      workedPayRateValue = workedPayRateValue.add(bookingDateHour.getPayRateValue());
      workedWtdValue = workedWtdValue.add(bookingDateHour.getWtdValue());
      workedNiValue = workedNiValue.add(bookingDateHour.getNiValue());
      workedCommissionValue = workedCommissionValue.add(bookingDateHour.getCommissionValue());
      workedWageRateValue = workedWageRateValue.add(bookingDateHour.getWageRateValue());

      workedChargeRateVatValue = workedChargeRateVatValue.add(bookingDateHour.getChargeRateVatValue());
      workedPayRateVatValue = workedPayRateVatValue.add(bookingDateHour.getPayRateVatValue());
      workedWtdVatValue = workedWtdVatValue.add(bookingDateHour.getWtdVatValue());
      workedNiVatValue = workedNiVatValue.add(bookingDateHour.getNiVatValue());
      workedCommissionVatValue = workedCommissionVatValue.add(bookingDateHour.getCommissionVatValue());

      if (bookingDateHour.getUpliftFactor().compareTo(new BigDecimal(1)) != 0 || bookingDateHour.getUpliftValue().compareTo(new BigDecimal(0)) != 0)
      {
        // bookingDate has an uplift (either factor or value)
        hasUplift = true;
      }

    }

    bookingDate.setHasUplift(hasUplift);

    bookingDate.setWorkedPayRateValue(round(workedPayRateValue));
    bookingDate.setWorkedWtdValue(round(workedWtdValue));
    bookingDate.setWorkedNiValue(round(workedNiValue));
    bookingDate.setWorkedCommissionValue(round(workedCommissionValue));
    bookingDate.setWorkedWageRateValue(round(workedWageRateValue));

    bookingDate.setWorkedChargeRateVatValue(round(workedChargeRateVatValue));
    bookingDate.setWorkedPayRateVatValue(round(workedPayRateVatValue));
    bookingDate.setWorkedWtdVatValue(round(workedWtdVatValue));
    bookingDate.setWorkedNiVatValue(round(workedNiVatValue));
    bookingDate.setWorkedCommissionVatValue(round(workedCommissionVatValue));

    // set charge rate based on other values - not by adding the hourly figures
    bookingDate.setWorkedChargeRateValue(bookingDate.getWorkedPayRateValue().add(bookingDate.getWorkedWtdValue()).add(bookingDate.getWorkedNiValue()).add(bookingDate.getWorkedCommissionValue()));
    bookingDate.setWorkedVatValue(bookingDate.getWorkedChargeRateVatValue().add(
        bookingDate.getWorkedPayRateVatValue().add(bookingDate.getWorkedWtdVatValue()).add(bookingDate.getWorkedNiVatValue()).add(bookingDate.getWorkedCommissionVatValue())));

    return bookingDateHours;

  }

  public static List<BookingDateHour> getBookingDateHours(BookingDateUserApplicant bookingDate, List<PublicHoliday> publicHolidays, List<Uplift> uplifts)
  {

    List<BookingDateHour> bookingDateHours = new ArrayList<BookingDateHour>();

    if (bookingDate.getWorkedNoOfHours().compareTo(new BigDecimal(0)) > 0)
    {

      // set up Calendars so the 'bit's of the start and end times can be retrieved.
      Calendar shiftStartCalendar = Calendar.getInstance();
      shiftStartCalendar.setTime(bookingDate.getWorkedStartTime());
      Calendar shiftEndCalendar = Calendar.getInstance();
      shiftEndCalendar.setTime(bookingDate.getWorkedEndTime());

      Calendar theStartCalendar = Calendar.getInstance();
      theStartCalendar.setTime(bookingDate.getBookingDate());
      // create a calendar for the start of the shift ...
      theStartCalendar.set(Calendar.HOUR_OF_DAY, shiftStartCalendar.get(Calendar.HOUR_OF_DAY));
      theStartCalendar.set(Calendar.MINUTE, shiftStartCalendar.get(Calendar.MINUTE));
      theStartCalendar.set(Calendar.SECOND, shiftStartCalendar.get(Calendar.SECOND));
      theStartCalendar.set(Calendar.MILLISECOND, shiftStartCalendar.get(Calendar.MILLISECOND));

      Calendar theEndCalendar = Calendar.getInstance();
      theEndCalendar.setTime(bookingDate.getBookingDate());
      if (bookingDate.getWorkedEndTime().before(bookingDate.getWorkedStartTime()))
      {
        theEndCalendar.add(Calendar.DAY_OF_MONTH, 1);
      }
      // create a calendar for the end of the shift ...
      theEndCalendar.set(Calendar.HOUR_OF_DAY, shiftEndCalendar.get(Calendar.HOUR_OF_DAY));
      theEndCalendar.set(Calendar.MINUTE, shiftEndCalendar.get(Calendar.MINUTE));
      theEndCalendar.set(Calendar.SECOND, shiftEndCalendar.get(Calendar.SECOND));
      theEndCalendar.set(Calendar.MILLISECOND, shiftEndCalendar.get(Calendar.MILLISECOND));

      Calendar breakStartCalendar = null;
      Calendar breakEndCalendar = null;
      Calendar theBreakStartCalendar = null;
      Calendar theBreakEndCalendar = null;

      if (bookingDate.getWorkedBreakStartTime() != null && bookingDate.getWorkedBreakEndTime() != null)
      {

        breakStartCalendar = Calendar.getInstance();
        breakStartCalendar.setTime(bookingDate.getWorkedBreakStartTime());
        breakEndCalendar = Calendar.getInstance();
        breakEndCalendar.setTime(bookingDate.getWorkedBreakEndTime());

        theBreakStartCalendar = Calendar.getInstance();
        theBreakStartCalendar.setTime(bookingDate.getBookingDate());
        if (bookingDate.getWorkedBreakStartTime().before(bookingDate.getWorkedStartTime()))
        {
          theBreakStartCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        // create a calendar for the start of the break ...
        theBreakStartCalendar.set(Calendar.HOUR_OF_DAY, breakStartCalendar.get(Calendar.HOUR_OF_DAY));
        theBreakStartCalendar.set(Calendar.MINUTE, breakStartCalendar.get(Calendar.MINUTE));
        theBreakStartCalendar.set(Calendar.SECOND, breakStartCalendar.get(Calendar.SECOND));
        theBreakStartCalendar.set(Calendar.MILLISECOND, breakStartCalendar.get(Calendar.MILLISECOND));

        theBreakEndCalendar = Calendar.getInstance();
        theBreakEndCalendar.setTime(bookingDate.getBookingDate());
        if (bookingDate.getWorkedBreakEndTime().before(bookingDate.getWorkedStartTime()))
        {
          theBreakEndCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        // create a calendar for the end of the break ...
        theBreakEndCalendar.set(Calendar.HOUR_OF_DAY, breakEndCalendar.get(Calendar.HOUR_OF_DAY));
        theBreakEndCalendar.set(Calendar.MINUTE, breakEndCalendar.get(Calendar.MINUTE));
        theBreakEndCalendar.set(Calendar.SECOND, breakEndCalendar.get(Calendar.SECOND));
        theBreakEndCalendar.set(Calendar.MILLISECOND, breakEndCalendar.get(Calendar.MILLISECOND));

      }

      List<BookingDateHour> workedBookingDateHours = getWorkedBookingDateHours(theStartCalendar, theEndCalendar, theBreakStartCalendar, theBreakEndCalendar, bookingDate, publicHolidays, uplifts);

      bookingDateHours.addAll(workedBookingDateHours);

      //			List<BookingDateHour> workedBookingDateHours = getWorkedBookingDateHours(theStartCalendar, theEndCalendar, bookingDate, publicHolidays, uplifts);
      //			
      //			bookingDateHours.addAll(workedBookingDateHours);
      //			
      //			// AND THE BREAK !!!
      //			
      //		    if (bookingDate.getWorkedBreakStartTime() != null && bookingDate.getWorkedBreakEndTime() != null) {
      //		
      //				Calendar breakStartCalendar = Calendar.getInstance();
      //				breakStartCalendar.setTime(bookingDate.getWorkedBreakStartTime());
      //				Calendar breakEndCalendar = Calendar.getInstance();
      //				breakEndCalendar.setTime(bookingDate.getWorkedBreakEndTime());
      //		    	
      //				theStartCalendar.setTime(bookingDate.getBookingDate());
      //				if (bookingDate.getWorkedBreakStartTime().before(bookingDate.getWorkedStartTime())) {
      //					theStartCalendar.add(Calendar.DAY_OF_MONTH, 1);
      //				}
      //				// create a calendar for the start of the break ...
      //				theStartCalendar.set(Calendar.HOUR_OF_DAY, breakStartCalendar.get(Calendar.HOUR_OF_DAY));
      //				theStartCalendar.set(Calendar.MINUTE, breakStartCalendar.get(Calendar.MINUTE));
      //				theStartCalendar.set(Calendar.SECOND, breakStartCalendar.get(Calendar.SECOND));
      //				theStartCalendar.set(Calendar.MILLISECOND, breakStartCalendar.get(Calendar.MILLISECOND));
      //		
      //				theEndCalendar.setTime(bookingDate.getBookingDate());
      //				if (bookingDate.getWorkedBreakEndTime().before(bookingDate.getWorkedStartTime())) {
      //					theEndCalendar.add(Calendar.DAY_OF_MONTH, 1);
      //				}
      //				// create a calendar for the end of the break ...
      //				theEndCalendar.set(Calendar.HOUR_OF_DAY, breakEndCalendar.get(Calendar.HOUR_OF_DAY));
      //				theEndCalendar.set(Calendar.MINUTE, breakEndCalendar.get(Calendar.MINUTE));
      //				theEndCalendar.set(Calendar.SECOND, breakEndCalendar.get(Calendar.SECOND));
      //				theEndCalendar.set(Calendar.MILLISECOND, breakEndCalendar.get(Calendar.MILLISECOND));
      //		    	
      //				List<BookingDateHour> breakBookingDateHours = getBreakBookingDateHours(theStartCalendar, theEndCalendar, bookingDate, publicHolidays, uplifts);
      //				
      //				bookingDateHours.addAll(breakBookingDateHours);
      //				
      //		
      //		    }

    }

    return bookingDateHours;

  }

  public static List<BookingDateHour> getWorkedBookingDateHours(Calendar theStartCalendar, Calendar theEndCalendar, BookingDateUserApplicant bookingDate, List<PublicHoliday> publicHolidays,
      List<Uplift> uplifts)
  {

    return getBookingDateHours(theStartCalendar, theEndCalendar, bookingDate, publicHolidays, uplifts, new BigDecimal(1.0F));

  }

  public static List<BookingDateHour> getWorkedBookingDateHours(Calendar theStartCalendar, Calendar theEndCalendar, Calendar breakStartCalendar, Calendar breakEndCalendar,
      BookingDateUserApplicant bookingDate, List<PublicHoliday> publicHolidays, List<Uplift> uplifts)
  {

    return getBookingDateHours(theStartCalendar, theEndCalendar, breakStartCalendar, breakEndCalendar, bookingDate, publicHolidays, uplifts, new BigDecimal(1.0F));

  }

  public static List<BookingDateHour> getBreakBookingDateHours(Calendar theStartCalendar, Calendar theEndCalendar, BookingDateUserApplicant bookingDate, List<PublicHoliday> publicHolidays,
      List<Uplift> uplifts)
  {

    return getBookingDateHours(theStartCalendar, theEndCalendar, bookingDate, publicHolidays, uplifts, new BigDecimal(-1.0F));

  }

  public static List<BookingDateHour> getBookingDateHours(Calendar theStartCalendar, Calendar theEndCalendar, BookingDateUserApplicant bookingDate, List<PublicHoliday> publicHolidays,
      List<Uplift> uplifts, BigDecimal factor)
  {

    return getBookingDateHours(theStartCalendar, theEndCalendar, null, null, bookingDate, publicHolidays, uplifts, factor);

  }

  public static List<BookingDateHour> getBookingDateHours(Calendar theStartCalendar, Calendar theEndCalendar, Calendar breakStartCalendar, Calendar breakEndCalendar,
      BookingDateUserApplicant bookingDate, List<PublicHoliday> publicHolidays, List<Uplift> uplifts, BigDecimal factor)
  {
    SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
    sdft.setTimeZone(theStartCalendar.getTimeZone());
    System.out.println("getBookingDateHours - theStartCalendar=" + sdft.format(theStartCalendar.getTime()) + "  theEndCalendar=" + sdft.format(theEndCalendar.getTime()));
    // Set the Hour Start to the Calendar Start (zeroise seconds and milliseconds). Eg. 07:45
    Calendar hourStartCalendar = (Calendar)theStartCalendar.clone();
    hourStartCalendar.set(Calendar.SECOND, 0);
    hourStartCalendar.set(Calendar.MILLISECOND, 0);
    // Set the Hour End Calendar to be 1 hour after the start of the Start Hour Calendar.
    Calendar hourEndCalendar = (Calendar)theStartCalendar.clone(); // Eg. 07:45
    System.out.println("hourEndCalendar=" + sdft.format(hourEndCalendar.getTime()));
    System.out.println("hourEndCalendar=" + sdft.format(hourEndCalendar.getTime()));
    if (hourEndCalendar.get(Calendar.MINUTE) > 0)
    {
      // Minutes after 00, change the minute to 00.
      hourEndCalendar.set(Calendar.MINUTE, 0);
    }
    setHourEndTime(hourEndCalendar, theEndCalendar);
    System.out.println("First: hourStartCalendar=" + sdft.format(hourStartCalendar.getTime()) + "  hourEndCalendar=" + sdft.format(hourEndCalendar.getTime()));

    List<BookingDateHour> bookingDateHours = new ArrayList<BookingDateHour>();

    BigDecimal timeCounter = new BigDecimal(0);

    boolean breakStarted = false;
    boolean breakEnded = false;

    while (true)
    {

      int dow = theStartCalendar.get(Calendar.DAY_OF_WEEK);
      int hod = theStartCalendar.get(Calendar.HOUR_OF_DAY);

      // calculate the portionOfTheHour
      Timestamp currentTimestamp = new Timestamp(theStartCalendar.getTimeInMillis());
      // add an hour
      theStartCalendar.add(Calendar.HOUR_OF_DAY, 1);
      // zeroise the minutes, seconds and milliseconds
      theStartCalendar.set(Calendar.MINUTE, 0);
      theStartCalendar.set(Calendar.SECOND, 0);
      theStartCalendar.set(Calendar.MILLISECOND, 0);

      // taking break into consideration !!!

      long milliSeconds = 0;

      if (breakStartCalendar == null || breakEnded)
      {

        // no break or break has already been processed

        if (theStartCalendar.before(theEndCalendar))
        {
          milliSeconds = theStartCalendar.getTimeInMillis() - currentTimestamp.getTime();
        }
        else
        {
          milliSeconds = theEndCalendar.getTimeInMillis() - currentTimestamp.getTime();
        }
      }
      else
      {

        // there is a break and it hasn't been processed

        if (breakStarted)
        { // but not ended - this is a break that goes through an o'clock

          if (breakEndCalendar.before(theStartCalendar))
          {

            // break ends in this same hour but didn't start in it - check the shift doesn't end in the hour as well

            if (theStartCalendar.before(theEndCalendar))
            {
              milliSeconds = milliSeconds + (theStartCalendar.getTimeInMillis() - breakEndCalendar.getTimeInMillis());
            }
            else
            {
              milliSeconds = milliSeconds + (theEndCalendar.getTimeInMillis() - breakEndCalendar.getTimeInMillis());
            }
            breakEnded = true;

          }

        }
        else
        {

          if (breakStartCalendar.before(theStartCalendar))
          {

            milliSeconds = breakStartCalendar.getTimeInMillis() - currentTimestamp.getTime();
            breakStarted = true;

            if (breakEndCalendar.before(theStartCalendar))
            {

              // break ends in the same hour as it starts - check the shift doesn't end in the hour as well

              if (theStartCalendar.before(theEndCalendar))
              {
                milliSeconds = milliSeconds + (theStartCalendar.getTimeInMillis() - breakEndCalendar.getTimeInMillis());
              }
              else
              {
                milliSeconds = milliSeconds + (theEndCalendar.getTimeInMillis() - breakEndCalendar.getTimeInMillis());
              }
              breakEnded = true;

            }
          }
          else
          {

            // break not started and not in this hour so process as normal 

            if (theStartCalendar.before(theEndCalendar))
            {
              milliSeconds = theStartCalendar.getTimeInMillis() - currentTimestamp.getTime();
            }
            else
            {
              milliSeconds = theEndCalendar.getTimeInMillis() - currentTimestamp.getTime();
            }

          }
        }
      }

      //			if (theStartCalendar.before(theEndCalendar)) {
      //	            milliSeconds = theStartCalendar.getTimeInMillis() - currentTimestamp.getTime();
      //			}
      //			else {
      //				milliSeconds = theEndCalendar.getTimeInMillis() - currentTimestamp.getTime();
      //			}

      BigDecimal portionOfHour = new BigDecimal(milliSeconds / 3600000.0F);

      timeCounter = timeCounter.add(portionOfHour);

      boolean extraProcessed = false;

      BigDecimal upliftFactor = new BigDecimal(1);
      BigDecimal upliftValue = new BigDecimal(0);

      if (bookingDate.getShiftUseUplift())
      {
        // use shift defined uplifts          
        upliftFactor = bookingDate.getShiftUpliftFactor();
        upliftValue = bookingDate.getShiftUpliftValue();
      }
      else
      {
        //
        // get the current hours uplift
        //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (PublicHoliday publicHoliday : publicHolidays)
        {
          // odd way to compare to dates but ???
          if (sdf.format(publicHoliday.getPhDate()).equals(sdf.format(theStartCalendar.getTime())))
          {
            dow = PUBLIC_HOLIDAY_DOW; // 
            break;
          }
        }
        for (Uplift uplift : uplifts)
        {
          if (uplift.getUpliftDay() == dow && uplift.getUpliftHour() == hod)
          {
            // Uplift found for day and hour.
            if (uplift.getUpliftMinutePeriod().equals(0) || uplift.getUpliftMinutes() == null)
            {
              // Normal situation. No UpliftMinutes...
              upliftFactor = uplift.getUpliftFactor();
              upliftValue = uplift.getUpliftValue();
            }
            else
            {
              // Has UpliftMinutes. Calculate the Uplift Factor & Value from the UpliftMinute records.
              System.out.println("hourStartCalendar=" + sdft.format(hourStartCalendar.getTime()) + "  hourEndCalendar=" + sdft.format(hourEndCalendar.getTime()));
              upliftFactor = calculateUpliftFactorFromUpliftMinutes(uplift, hourStartCalendar, hourEndCalendar);
              upliftValue = calculateUpliftValueFromUpliftMinutes(uplift, hourStartCalendar, hourEndCalendar);
            }
            break;
          }
        }

        //
        // time counter stuff !!! for > blah hours !!!
        //              

        if (bookingDate.getShiftOvertimeNoOfHours().compareTo(new BigDecimal(0)) > 0 && // overtimeNoOfHours > 0
            timeCounter.compareTo(bookingDate.getShiftOvertimeNoOfHours()) > 0 && // noOfHours exceeds overtimeNoOfHours
            (bookingDate.getShiftOvertimeUpliftFactor().compareTo(upliftFactor) > 0 || // AND bigger overtimeUpliftFactor
            bookingDate.getShiftOvertimeUpliftFactor().compareTo(new BigDecimal(0)) == 0 // OR overtimeUpliftFactor = 0 (no overtime)
            ))
        {

          // more than standard hours and there are uplift details to process

          if (!extraProcessed)
          {

            // if there is an extra (non-uplifted bit to process - do it here
            BigDecimal upliftedPortionOfHour = timeCounter.subtract(bookingDate.getShiftOvertimeNoOfHours());
            BigDecimal nonUpliftedPortionOfHour = portionOfHour.subtract(upliftedPortionOfHour);

            if (nonUpliftedPortionOfHour.compareTo(new BigDecimal(0)) > 0)
            {

              // there is a bit of non-uplifted hour to process

              bookingDateHours.add(makeBookingDateHour(bookingDate, hod, upliftFactor, upliftValue, nonUpliftedPortionOfHour, factor));
              // the reduce the portionOfHour
              portionOfHour = upliftedPortionOfHour;

            }

            extraProcessed = true;
          }

          upliftFactor = bookingDate.getShiftOvertimeUpliftFactor();

          // NOTE - No overtimeUpliftValue !!!
          // NOTE - No overtimeUpliftValue !!!
          // NOTE - No overtimeUpliftValue !!!
          //
          // upliftValue = bookingDate.getShiftUpliftValue();
          //

        }

      }

      bookingDateHours.add(makeBookingDateHour(bookingDate, hod, upliftFactor, upliftValue, portionOfHour, factor));

      if (!theStartCalendar.before(theEndCalendar))
      {
        //
        break;
      }
      // Increment Hour Start. 
      hourStartCalendar.add(Calendar.HOUR_OF_DAY, 1);
      // All Hour Starts with the exception of the first Hour Start will be an hour boundary. Eg. 09:00, 10:00, 11:00 etc.
      hourStartCalendar.set(Calendar.MINUTE, 0);
      setHourEndTime(hourEndCalendar, theEndCalendar);
    }

    return bookingDateHours;

  }

  private static void setHourEndTime(Calendar hourEndCalendar, Calendar theEndCalendar)
  {
    hourEndCalendar.add(Calendar.HOUR_OF_DAY, 1); // Eg. 08:00
    if (hourEndCalendar.after(theEndCalendar))
    {
      // End Hour after End Calendar. Use End Calendar as End Hour. Eg. End Calendar 09:45, End Hour 10:00. Use 09:45.
      hourEndCalendar.set(Calendar.HOUR_OF_DAY, theEndCalendar.get(Calendar.HOUR_OF_DAY));
      hourEndCalendar.set(Calendar.MINUTE, theEndCalendar.get(Calendar.MINUTE));
      hourEndCalendar.set(Calendar.SECOND, theEndCalendar.get(Calendar.SECOND));
    }
  }
  
  // UpliftFactor is the average over the required part of the hour.
  private static BigDecimal calculateUpliftFactorFromUpliftMinutes(Uplift uplift, Calendar hourStartCalendar, Calendar hourEndCalendar)
  {
    BigDecimal upliftFactor = new BigDecimal(0);
    int endMinute = hourEndCalendar.get(Calendar.MINUTE) == 0 ? 60 : hourEndCalendar.get(Calendar.MINUTE);
    int minuteCount = 0;
    for (UpliftMinute upliftMinute : uplift.getUpliftMinutes())
    {
      // For each UpliftMinute...
      System.out.println("upliftMinute=" + upliftMinute.getUpliftMinuteAsString());
      if (upliftMinute.getUpliftMinute() >= hourStartCalendar.get(Calendar.MINUTE) && upliftMinute.getUpliftMinute() < endMinute)
      {
        // UpliftMinute is in range. 
        upliftFactor = upliftFactor.add(upliftMinute.getUpliftFactor());
        ++minuteCount;
      }
    }
    upliftFactor = upliftFactor.divide(new BigDecimal(minuteCount));
    System.out.println("upliftFactor=" + upliftFactor);
    return upliftFactor;
  }

  // UpliftValue is the total over the required part of the hour. 
  private static BigDecimal calculateUpliftValueFromUpliftMinutes(Uplift uplift, Calendar hourStartCalendar, Calendar hourEndCalendar)
  {
    BigDecimal upliftValue = new BigDecimal(0);
    int endMinute = hourEndCalendar.get(Calendar.MINUTE) == 0 ? 60 : hourEndCalendar.get(Calendar.MINUTE);
    for (UpliftMinute upliftMinute : uplift.getUpliftMinutes())
    {
      // For each UpliftMinute...
      System.out.println("upliftMinute=" + upliftMinute.getUpliftMinuteAsString());
      if (upliftMinute.getUpliftMinute() >= hourStartCalendar.get(Calendar.MINUTE) && upliftMinute.getUpliftMinute() < endMinute)
      {
        // UpliftMinute is in range. 
        upliftValue = upliftValue.add(upliftMinute.getUpliftValue());
      }
    }
    System.out.println("upliftValue=" + upliftValue);
    return upliftValue;
  }

// Lyndon - Reinstated and changed 05/05/2016
// Lyndon - WAS AS BELOW 15/07/2013
  private static BookingDateHour makeBookingDateHour(BookingDateUserApplicant bookingDate, Integer hod, BigDecimal upliftFactor, BigDecimal upliftValue, BigDecimal portionOfHour, BigDecimal factor)
  {

    BookingDateHour bookingDateHour = new BookingDateHour();

    bookingDateHour.setBookingDateId(bookingDate.getBookingDateId());
    bookingDateHour.setHourOfDay(hod);
    bookingDateHour.setChargeRate(bookingDate.getChargeRate());
    bookingDateHour.setPayRate(bookingDate.getPayRate());
    bookingDateHour.setWageRate(bookingDate.getWageRate());

    bookingDateHour.setPortionOfHour(portionOfHour);

    bookingDateHour.setUpliftFactor(upliftFactor);
    bookingDateHour.setUpliftValue(upliftValue);

    BigDecimal upliftedChargeRate = round((bookingDate.getChargeRate().multiply(upliftFactor)).add(upliftValue));
    BigDecimal upliftedPayRate = round((bookingDate.getPayRate().multiply(upliftFactor)).add(upliftValue));
    BigDecimal upliftedWageRate = round((bookingDate.getWageRate().multiply(upliftFactor)).add(upliftValue));
    // commissionRate = (chargeRate - (payRate + wtdRate + niRate)).
    BigDecimal commissionRate = round((bookingDate.getChargeRate().subtract((bookingDate.getPayRate().add(bookingDate.getWtdRate().add(bookingDate.getNiRate())))))); 
    
    bookingDateHour.setUpliftedChargeRate(upliftedChargeRate);
    bookingDateHour.setUpliftedPayRate(upliftedPayRate);
    bookingDateHour.setUpliftedWageRate(upliftedWageRate);

    bookingDateHour.setChargeRateValue(factor.multiply(bookingDateHour.getUpliftedChargeRate().multiply(bookingDateHour.getPortionOfHour())));

    bookingDateHour.setChargeRateVatValue(bookingDateHour.getChargeRateValue().multiply((bookingDate.getChargeRateVatRate().divide(divisor100, 5, RoundingMode.HALF_UP))));

    bookingDateHour.setPayRateValue(factor.multiply(bookingDateHour.getUpliftedPayRate().multiply(bookingDateHour.getPortionOfHour())));

    bookingDateHour.setPayRateVatValue(bookingDateHour.getPayRateValue().multiply((bookingDate.getPayRateVatRate().divide(divisor100, 5, RoundingMode.HALF_UP))));

    bookingDateHour.setWtdRate(round(bookingDateHour.getUpliftedPayRate().multiply((bookingDate.getWtdPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)))));
    bookingDateHour.setWtdValue(factor.multiply(bookingDateHour.getWtdRate().multiply(bookingDateHour.getPortionOfHour())));

    bookingDateHour.setWtdVatValue(bookingDateHour.getWtdValue().multiply((bookingDate.getWtdVatRate().divide(divisor100, 5, RoundingMode.HALF_UP))));

    bookingDateHour.setNiRate(round((bookingDateHour.getUpliftedPayRate().add(bookingDateHour.getWtdRate())).multiply((bookingDate.getNiPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)))));

    bookingDateHour.setNiValue(factor.multiply(bookingDateHour.getNiRate().multiply(bookingDateHour.getPortionOfHour())));

    bookingDateHour.setNiVatValue(bookingDateHour.getNiValue().multiply((bookingDate.getNiVatRate().divide(divisor100, 5, RoundingMode.HALF_UP))));

    // LYNDON 10/10/2012 WAS: bookingDateHour.setCommissionRate(round(bookingDateHour.getUpliftedChargeRate().subtract((bookingDateHour.getUpliftedPayRate().add(bookingDateHour.getWtdRate().add(bookingDateHour.getNiRate()))))));
    bookingDateHour.setCommissionRate(commissionRate);
    // LYNDON 10/10/2012 WAS: bookingDateHour.setCommissionValue(bookingDateHour.getChargeRateValue().subtract((bookingDateHour.getPayRateValue().add(bookingDateHour.getWtdValue().add(bookingDateHour.getNiValue())))));
    bookingDateHour.setCommissionValue(commissionRate.multiply(portionOfHour));

    bookingDateHour.setCommissionVatValue(bookingDateHour.getCommissionValue().multiply((bookingDate.getCommissionVatRate().divide(divisor100, 5, RoundingMode.HALF_UP))));

    bookingDateHour.setWageRateValue(factor.multiply(bookingDateHour.getUpliftedWageRate().multiply(bookingDateHour.getPortionOfHour())));

    return bookingDateHour;

  }

  
  
  public static BigDecimal calculateIt(java.sql.Date theDate, Shift shift, BigDecimal rate, List<PublicHoliday> publicHolidays, List<Uplift> uplifts)
  {

    // for each date 
    BigDecimal value = new BigDecimal(0);
    BigDecimal shiftValue = new BigDecimal(0);
    BigDecimal breakValue = new BigDecimal(0);

    if (shift.getUseShiftUplift())
    {
      // shift has and uplift - which overrides any hourly ones

      // rounded
      BigDecimal upliftedRate = round(rate.multiply(shift.getUpliftFactor()));
      // add on the hourly uplift value
      upliftedRate = upliftedRate.add(shift.getUpliftValue());
      // multipy the upliftedRate by the number of hours and round it
      shiftValue = round(shift.getNoOfHours().multiply(upliftedRate));
    }
    else
    {

      // set up Calendars so the 'bit's of the start and end times can be retrieved.
      Calendar shiftStartCalendar = Calendar.getInstance();
      shiftStartCalendar.setTime(shift.getStartTime());
      Calendar shiftEndCalendar = Calendar.getInstance();
      shiftEndCalendar.setTime(shift.getEndTime());

      Calendar theStartCalendar = Calendar.getInstance();
      theStartCalendar.setTime(theDate);
      // create a calendar for the start of the shift ...
      theStartCalendar.set(Calendar.HOUR_OF_DAY, shiftStartCalendar.get(Calendar.HOUR_OF_DAY));
      theStartCalendar.set(Calendar.MINUTE, shiftStartCalendar.get(Calendar.MINUTE));
      theStartCalendar.set(Calendar.SECOND, shiftStartCalendar.get(Calendar.SECOND));
      theStartCalendar.set(Calendar.MILLISECOND, shiftStartCalendar.get(Calendar.MILLISECOND));

      Calendar theEndCalendar = Calendar.getInstance();
      theEndCalendar.setTime(theDate);
      if (shift.getEndTime().before(shift.getStartTime()))
      {
        theEndCalendar.add(Calendar.DAY_OF_MONTH, 1);
      }
      // create a calendar for the end of the shift ...
      theEndCalendar.set(Calendar.HOUR_OF_DAY, shiftEndCalendar.get(Calendar.HOUR_OF_DAY));
      theEndCalendar.set(Calendar.MINUTE, shiftEndCalendar.get(Calendar.MINUTE));
      theEndCalendar.set(Calendar.SECOND, shiftEndCalendar.get(Calendar.SECOND));
      theEndCalendar.set(Calendar.MILLISECOND, shiftEndCalendar.get(Calendar.MILLISECOND));

      shiftValue = getValue(theStartCalendar, theEndCalendar, rate, publicHolidays, uplifts);

      //			System.out.println(theStartCalendar.getTime().toString() + " - " + theEndCalendar.getTime().toString() + " = " + shiftValue);

      // AND THE BREAK !!!

      if (shift.getBreakStartTime() != null && shift.getBreakEndTime() != null)
      {

        Calendar breakStartCalendar = Calendar.getInstance();
        breakStartCalendar.setTime(shift.getBreakStartTime());
        Calendar breakEndCalendar = Calendar.getInstance();
        breakEndCalendar.setTime(shift.getBreakEndTime());

        theStartCalendar.setTime(theDate);
        if (shift.getBreakStartTime().before(shift.getStartTime()))
        {
          theStartCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        // create a calendar for the start of the break ...
        theStartCalendar.set(Calendar.HOUR_OF_DAY, breakStartCalendar.get(Calendar.HOUR_OF_DAY));
        theStartCalendar.set(Calendar.MINUTE, breakStartCalendar.get(Calendar.MINUTE));
        theStartCalendar.set(Calendar.SECOND, breakStartCalendar.get(Calendar.SECOND));
        theStartCalendar.set(Calendar.MILLISECOND, breakStartCalendar.get(Calendar.MILLISECOND));

        theEndCalendar.setTime(theDate);
        if (shift.getBreakEndTime().before(shift.getStartTime()))
        {
          theEndCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        // create a calendar for the end of the break ...
        theEndCalendar.set(Calendar.HOUR_OF_DAY, breakEndCalendar.get(Calendar.HOUR_OF_DAY));
        theEndCalendar.set(Calendar.MINUTE, breakEndCalendar.get(Calendar.MINUTE));
        theEndCalendar.set(Calendar.SECOND, breakEndCalendar.get(Calendar.SECOND));
        theEndCalendar.set(Calendar.MILLISECOND, breakEndCalendar.get(Calendar.MILLISECOND));

        breakValue = getValue(theStartCalendar, theEndCalendar, rate, publicHolidays, uplifts);

        //    			System.out.println(theStartCalendar.getTime().toString() + " - " + theEndCalendar.getTime().toString() + " = " + breakValue);
      }
    }
    value = shiftValue.subtract(breakValue);
    return value;

  }

  private static BigDecimal getValue(Calendar theStartCalendar, Calendar theEndCalendar, BigDecimal rate, List<PublicHoliday> publicHolidays, List<Uplift> uplifts)
  {

    BigDecimal value = new BigDecimal(0);

    while (true)
    {
      // get the current hours uplift
      int dow = theStartCalendar.get(Calendar.DAY_OF_WEEK);
      int hod = theStartCalendar.get(Calendar.HOUR_OF_DAY);

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      for (PublicHoliday publicHoliday : publicHolidays)
      {
        // odd way to compare to dates but ???
        if (sdf.format(publicHoliday.getPhDate()).equals(sdf.format(theStartCalendar.getTime())))
        {
          dow = PUBLIC_HOLIDAY_DOW; // 
          break;
        }
      }

//      BigDecimal upliftFactor = new BigDecimal(1);
//      BigDecimal upliftValue = new BigDecimal(0);
      Uplift hodUplift = null;
      for (Uplift uplift : uplifts)
      {
        if (uplift.getUpliftDay() == dow && uplift.getUpliftHour() == hod)
        {
          hodUplift = uplift;
//          upliftFactor = uplift.getUpliftFactor();
//          upliftValue = uplift.getUpliftValue();
          break;
        }
      }
      if (hodUplift.getUpliftMinutePeriod().equals(0) || hodUplift.getUpliftMinutes() == null)
      {
        // No UpliftMinute data available. Normal situation.
        value = value.add(getUpliftedValue(theStartCalendar, theEndCalendar, rate, hodUplift));
        System.out.println("No UpliftMinute data available. Rate=" + rate);
      }
      else
      {
        System.out.println("\n");
        System.out.println(theStartCalendar.getTime().toString()
            + " " + theEndCalendar.getTime().toString()
            + " " + rate
            + " " + hodUplift.getUpliftMinutesAsString());
        BigDecimal minutePeriodRate = rate.divide(new BigDecimal(60 / hodUplift.getUpliftMinutePeriod()), 5, RoundingMode.HALF_UP);
        value = value.add(getUpliftedMinuteValue(theStartCalendar, theEndCalendar, minutePeriodRate, hodUplift));
        System.out.println("UpliftMinute data available. Rate=" + rate + " Minute Period (" + hodUplift.getUpliftMinutePeriod() + ") Rate=" + minutePeriodRate + "  value=" + value);
      }
//      // rounded - rate * upliftFactor
//      BigDecimal upliftedRate = round(upliftFactor.multiply(rate));
//
//      BigDecimal currentHourRate = upliftedRate.add(upliftValue);
//
//      //			System.out.println(theStartCalendar.getTime().toString()
//      //					+ " " + rate
//      //					+ " " + upliftFactor
//      //					+ " " + upliftValue
//      //	                + " " + currentHourRate);
//
//      Timestamp currentTimestamp = new Timestamp(theStartCalendar.getTimeInMillis());
//      // add an hour
//      theStartCalendar.add(Calendar.HOUR_OF_DAY, 1);
//      // zeroise the minutes, seconds and milliseconds
//      theStartCalendar.set(Calendar.MINUTE, 0);
//      theStartCalendar.set(Calendar.SECOND, 0);
//      theStartCalendar.set(Calendar.MILLISECOND, 0);
//
//      long milliSeconds = 0;
//      if (theStartCalendar.before(theEndCalendar))
//      {
//        milliSeconds = theStartCalendar.getTimeInMillis() - currentTimestamp.getTime();
//      }
//      else
//      {
//        milliSeconds = theEndCalendar.getTimeInMillis() - currentTimestamp.getTime();
//      }
//
//      BigDecimal portionOfHour = new BigDecimal(milliSeconds / 3600000.0F);
//
//      // rounded
//      value = value.add(round(currentHourRate.multiply(portionOfHour)));

      if (!theStartCalendar.before(theEndCalendar))
      {
        //
        break;
      }

    }

    return value.setScale(2, RoundingMode.HALF_UP);

  }

  private static BigDecimal getUpliftedValue(Calendar theStartCalendar, Calendar theEndCalendar, BigDecimal rate, Uplift uplift)
  {
    BigDecimal upliftFactor = uplift.getUpliftFactor();
    BigDecimal upliftValue = new BigDecimal(0);
    BigDecimal value = null;
    // rounded - rate * upliftFactor
    BigDecimal upliftedRate = round(upliftFactor.multiply(rate));

    BigDecimal currentHourRate = upliftedRate.add(upliftValue);

    //      System.out.println(theStartCalendar.getTime().toString()
    //          + " " + rate
    //          + " " + upliftFactor
    //          + " " + upliftValue
    //                  + " " + currentHourRate);

    Timestamp currentTimestamp = new Timestamp(theStartCalendar.getTimeInMillis());
    // add an hour
    theStartCalendar.add(Calendar.HOUR_OF_DAY, 1);
    // zeroise the minutes, seconds and milliseconds
    theStartCalendar.set(Calendar.MINUTE, 0);
    theStartCalendar.set(Calendar.SECOND, 0);
    theStartCalendar.set(Calendar.MILLISECOND, 0);

    long milliSeconds = 0;
    if (theStartCalendar.before(theEndCalendar))
    {
      milliSeconds = theStartCalendar.getTimeInMillis() - currentTimestamp.getTime();
    }
    else
    {
      milliSeconds = theEndCalendar.getTimeInMillis() - currentTimestamp.getTime();
    }

    BigDecimal portionOfHour = new BigDecimal(milliSeconds / 3600000.0F);

    // rounded
    value = round(currentHourRate.multiply(portionOfHour));
    System.out.println("Portion of Hour=" + portionOfHour + "  Value=" + value);
    return value;
  }
  
  private static BigDecimal getUpliftedMinuteValue(Calendar theStartCalendar, Calendar theEndCalendar, BigDecimal minutePeriodRate, Uplift uplift)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    sdf.setTimeZone(theStartCalendar.getTimeZone());
    BigDecimal msUpliftMinutePeriod = new BigDecimal(uplift.getUpliftMinutePeriod() * 60 * 1000);
    long longUpliftMinutePeriod = uplift.getUpliftMinutePeriod().longValue() * 60 * 1000;
    System.out.println("theStartCalendar=" + sdf.format(theStartCalendar.getTime()) + "  theEndCalendar=" + sdf.format(theEndCalendar.getTime()) + "  minutePeriodRate=" + minutePeriodRate + "  msUpliftMinutePeriod=" + msUpliftMinutePeriod);
    BigDecimal value = new BigDecimal(0);;
    BigDecimal upliftedMinuteRate = null;
    BigDecimal currentMinuteRate = null;
    BigDecimal currentMinuteValue = null;
    BigDecimal portionOfMinutePeriod = null;
    Calendar theMinutePeriodCalendar = (Calendar)theStartCalendar.clone();
    for (UpliftMinute upliftMinute : uplift.getUpliftMinutes())
    {
      // For each UpliftMinute in the hour.
      upliftedMinuteRate = upliftMinute.getUpliftFactor().multiply(minutePeriodRate);
      currentMinuteRate  = upliftedMinuteRate.add(upliftMinute.getUpliftValue());
      // Add the UpliftMinutePeriod to the MinutePeriod calendar.
      theMinutePeriodCalendar.add(Calendar.MINUTE, uplift.getUpliftMinutePeriod());
      // Zeroise the seconds and milliseconds.
      theMinutePeriodCalendar.set(Calendar.SECOND, 0);
      theMinutePeriodCalendar.set(Calendar.MILLISECOND, 0);
      System.out.println("theMinutePeriodCalendar=" + sdf.format(theMinutePeriodCalendar.getTime()));
      if (theMinutePeriodCalendar.before(theEndCalendar))
      {
        value = value.add(currentMinuteRate);
        System.out.println("Before - value=" + value + "  Current Minute Rate=" + currentMinuteRate);
      }
      else
      {
        // Equal or After. End loop...
        if (theMinutePeriodCalendar.equals(theEndCalendar))
        {
          value = value.add(currentMinuteRate);
          System.out.println("Equal - value=" + value + "  Current Minute Rate=" + currentMinuteRate);
        }
        else
        {
          // After. Calculate the part of the period left at the end and its value.
          BigDecimal partOfPeriodInMillis = new BigDecimal(longUpliftMinutePeriod - (theMinutePeriodCalendar.getTimeInMillis() - theEndCalendar.getTimeInMillis()));
          portionOfMinutePeriod = partOfPeriodInMillis.divide(msUpliftMinutePeriod);
          currentMinuteValue = round(currentMinuteRate.multiply(portionOfMinutePeriod));
          value = value.add(currentMinuteValue);
          System.out.println("After - value=" + value + "  Current Minute Value=" + currentMinuteValue + "  portionOfMinutePeriod=" + portionOfMinutePeriod);
        }
        break;
      }
    }
    // Add an hour to the Start time.
    theStartCalendar.add(Calendar.HOUR_OF_DAY, 1);
    // Zeroise the minutes, seconds and milliseconds
    theStartCalendar.set(Calendar.MINUTE, 0);
    theStartCalendar.set(Calendar.SECOND, 0);
    theStartCalendar.set(Calendar.MILLISECOND, 0);
    return value;
  }
  
  public static double round(double val)
  {
    return round(val, 2);
  }

  public static float round(float val)
  {
    return round(val, 2);
  }

  public static BigDecimal round(BigDecimal val)
  {
    return round(val, 2);
  }

  public static BigDecimal round(BigDecimal val, int places)
  {
    return val.setScale(places, RoundingMode.HALF_UP);
  }

  public static double round(double val, int places)
  {
    long factor = (long) Math.pow(10, places);

    // Shift the decimal the correct number of places
    // to the right.
    val = val * factor;

    // Round to the nearest integer.
    long tmp = Math.round(val);

    // Shift the decimal the correct number of places
    // back to the left.
    return (double) tmp / factor;
  }

  public static float round(float val, int places)
  {
    double doubleVal = (double) val;
    //    	long factor = (long)Math.pow(10, places + 1);
    //    	double x = 1.0F / factor;
    //    	doubleVal += x;
    return (float) round(doubleVal, places);
  }

  public static ActionForward whereToNow(ActionMapping mapping, HttpServletRequest request)
  {

    // Go to where we were trying to go before we got sent to login page
    HttpSession session = request.getSession();
    String requestedURL = (String) session.getAttribute(Constants.REQUESTED_URL);

    if (requestedURL == null)
    {
      // If they went straight to the login page
      return mapping.findForward("success");
    }
    else
    {
      session.removeAttribute(Constants.REQUESTED_URL);

      // Get the servlet path which will be /webapp/rest of path
      URL url = null;
      try
      {
        url = new URL(requestedURL);
      }
      catch (MalformedURLException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      String redirectUrlServletPath = url.getPath();
      String query = url.getQuery();

      // Remove the /webapp/
      String regexp = request.getContextPath();
      redirectUrlServletPath = redirectUrlServletPath.replaceFirst(regexp, "");

      // Append the query
      if (query != null && query.length() > 0)
      {
        redirectUrlServletPath += "?";
        redirectUrlServletPath += query;
      }

      ActionForward forward = new ActionForward("loginRedirect", redirectUrlServletPath, true);
      // TODO !!!
      // Was until 06/09/2016 Lyndon
      //forward.setContextRelative(true);
      return forward;
    }

  }

  public static void redirectToPage(HttpServletRequest hreq, HttpServletResponse hres, String page) throws UnsupportedEncodingException, IOException
  {
    // Construct the full URL
    StringBuffer fullUrl = new StringBuffer();
    String requestURL = hreq.getRequestURL().toString();
    // TODO - needs a bit more investigation - added because of odd bug where URL has a double slash in it?
    requestURL = requestURL.replaceFirst(hreq.getRequestURI(), hreq.getRequestURI().replaceFirst("//", "/"));
    fullUrl.append(requestURL);
    // Append the parameters if there are any
    String query = hreq.getQueryString();
    if (query != null)
    {
      fullUrl.append("?");
      fullUrl.append(query);
    }

    // Save the URL on the session
    hreq.getSession().setAttribute(Constants.REQUESTED_URL, fullUrl.toString());

    // go to access denied

    //	    try {
    //			hreq.getRequestDispatcher(hreq.getContextPath() + page).forward(hreq, hres);
    //		} catch (ServletException e) {
    //			// TODO Auto-generated catch block
    //			e.printStackTrace();
    //		}

    hres.sendRedirect(hreq.getContextPath() + page);
  }

  private static final String[] cleanPathSuffixes = { ".jsp", ".js", ".css", ".do", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "XML", "PDF", "Process",
      "Finish" };

  public static String cleanPath(String path)
  {
    // NOTE - suffixes are processed in the order they appear in the array
    //		System.out.println(path);
    for (String suffix : cleanPathSuffixes)
    {
      if (path.endsWith(suffix))
      {
        path = path.substring(0, path.length() - suffix.length());
        //				System.out.println(path);
      }
    }
    return path;
  }

  public static Date getStartOfWeek(Integer weekToShow)
  {

    return getDate(weekToShow, -1, Calendar.MONDAY);

  }

  public static Date getEndOfWeek(Integer weekToShow)
  {

    return getDate(weekToShow, 1, Calendar.SUNDAY);

  }

  public static Date getDate(Integer weekToShow, Integer direction, Integer dayOfWeek)
  {

    Date now = new Date(System.currentTimeMillis());

    Integer daysToShuffle = weekToShow * 7;

    Calendar theDate = Calendar.getInstance();
    theDate.setTime(now);
    theDate.add(Calendar.DAY_OF_WEEK, daysToShuffle);

    while (theDate.get(Calendar.DAY_OF_WEEK) != dayOfWeek)
    {
      theDate.add(Calendar.DAY_OF_WEEK, direction);
    }
    return new Date(theDate.getTimeInMillis());
  }

  public static String makeNiceEmailAddress(String name, String emailAddress)
  {

    if (emailAddress == null || "".equals(emailAddress))
    {
      return name;
    }
    else if (name == null || "".equals(name))
    {
      return emailAddress;
    }
    else
    {
      return name + " <" + emailAddress + ">";
    }

  }

  public static void suckInFile(String fileName, StringBuffer sb)
  {

    File cssFile = new File(fileName);

    if (cssFile.exists())
    {

      BufferedReader input = null;
      //declared here only to make visible to finally clause
      try
      {
        //use buffering, reading one line at a time
        //FileReader always assumes default encoding is OK!
        input = new BufferedReader(new FileReader(cssFile));
        String line = null; //not declared within while loop
        /*
         * readLine is a bit quirky :
         * it returns the content of a line MINUS the newline.
         * it returns null only for the END of the stream.
         * it returns an empty String if two newlines appear in a row.
         */
        while ((line = input.readLine()) != null)
        {
          sb.append(line);
          sb.append("\n");
        }
      }
      catch (FileNotFoundException ex)
      {
        ex.printStackTrace();
      }
      catch (IOException ex)
      {
        ex.printStackTrace();
      }
      finally
      {
        try
        {
          if (input != null)
          {
            //flush and close both "input" and its underlying FileReader
            input.close();
          }
        }
        catch (IOException ex)
        {
          ex.printStackTrace();
        }
      }
    }

  }

  public static BigDecimal calculateRoundedVat(BigDecimal value, BigDecimal vatRate)
  {
    return round(calculateVat(value, vatRate));
  }

  public static BigDecimal calculateVat(BigDecimal value, BigDecimal vatRate)
  {
    return value.multiply(vatRate.divide(divisor100, 5, RoundingMode.HALF_UP));
  }

  public static void generateInvoicePDF(HttpServletRequest request, MessageResources messageResources, AgencyInvoiceUserEntity agencyInvoice) throws Exception
  {

    generateInvoiceCreditPDF(request, messageResources, null, agencyInvoice);

  }

  public static void generateInvoiceCreditPDF(HttpServletRequest request, MessageResources messageResources, AgencyInvoiceCredit agencyInvoiceCredit, AgencyInvoiceUserEntity agencyInvoice)
      throws Exception
  {

  }

  private static final PdfPCell getCell(String text)
  {
    return getCell(text, Element.ALIGN_LEFT);
  }

  private static final PdfPCell getCell(String text, int horizontalAlignment)
  {
    return getCell(text, horizontalAlignment, Rectangle.NO_BORDER);
  }

  private static final PdfPCell getCell(String text, int horizontalAlignment, int border)
  {
    return getCell(text, horizontalAlignment, border, DEFAULT_FONT);
  }

  private static final PdfPCell getCellBold(String text)
  {
    return getCellBold(text, Element.ALIGN_LEFT);
  }

  private static final PdfPCell getCellBold(String text, int horizontalAlignment)
  {
    return getCellBold(text, horizontalAlignment, Rectangle.NO_BORDER);
  }

  private static final PdfPCell getCellBold(String text, int horizontalAlignment, int border)
  {
    return getCell(text, horizontalAlignment, border, DEFAULT_FONTBOLD);
  }

  private static final PdfPCell getCell(String text, int horizontalAlignment, int border, Font font)
  {
    return getCell(text, horizontalAlignment, border, font, DEFAULT_BACKGROUNDCOLOR);
  }

  private static final PdfPCell getCell(String text, int horizontalAlignment, int border, Font font, BaseColor backgroundColor)
  {

    PdfPCell myCell = new PdfPCell(new Paragraph(text, font));
    myCell.setHorizontalAlignment(horizontalAlignment);
    myCell.setBorder(border);
    myCell.setBackgroundColor(backgroundColor);
    //            myCell.setBackgroundColor(new BaseColor(123, 123, 123));
    return myCell;

  }

  private static final PdfPCell getTDCell(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.BOX, TD_FONT);
  }

  private static final PdfPCell getTDCellRight(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TD_FONT);
  }

  private static final PdfPCell getTDCellRightBold(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TD_FONTBOLD);
  }

  private static final PdfPCell getTHCell(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.BOX, TH_FONT, TH_BACKGROUNDCOLOR);
  }

  private static final PdfPCell getTHCellRight(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TH_FONT, TH_BACKGROUNDCOLOR);
  }

  private static final PdfPCell getTDCellBig(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.BOX, TD_FONTBIG);
  }

  private static final PdfPCell getTDCellBigRight(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TD_FONTBIG);
  }

  private static final PdfPCell getTDCellBigRightBold(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TD_FONTBIGBOLD);
  }

  private static final PdfPCell getTHCellBig(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.BOX, TH_FONTBIG, TH_BACKGROUNDCOLOR);
  }

  private static final PdfPCell getTHCellBigRight(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TH_FONTBIG, TH_BACKGROUNDCOLOR);
  }

}
