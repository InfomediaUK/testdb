package com.helmet.application.agy;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.NhsBackingReport;

public class BookingDatesAddRemoveBackingReport extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    String bookingDateIdStrs = (String)dynaForm.get("bookingDateIdStrs");
    String backingReport = (String)dynaForm.get("backingReport");

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    validateBackingReportNames(agyService, errors, backingReport);
    
    List<BookingDate> listBookingDate = null;
    List<NhsBackingReport> listNhsBackingReport = null;
    if (errors.isEmpty()) 
    {
      listBookingDate = getBookingDates(agyService, bookingDateIdStrs);
      listNhsBackingReport = getNewNhsBackingReports(agyService, backingReport);
      validateBookingDatesAgainstNhsBackingReportDates(agyService, errors, listBookingDate, listNhsBackingReport);
    }

    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    int rowCount = agyService.updateBookingDatesBackingReport(bookingDateIdStrs, backingReport, getConsultantLoggedIn().getConsultantId());

    logger.exit("Out going !!!");

    ActionForward forward = mapping.findForward("success");
    forward = new ActionForward(forward.getName(), forward.getPath() + "?workedStatus=2", forward.getRedirect());
    return forward;

  }

  protected void validateBackingReportNames(AgyService agyService, ActionMessages errors, String backingReport)
  {
    StringTokenizer stBackingReport = new StringTokenizer(backingReport, ",");
    while (stBackingReport.hasMoreTokens())
    {
      String backingReportName = stBackingReport.nextToken();
      if (backingReportName.startsWith("-") || backingReportName.startsWith("+"))
      {
        // Remove minus/plus sign from BackingReport name.
        backingReportName = backingReportName.substring(1);
      }
      if (backingReportName.length() < AgyConstants.BACKING_REPORT_NAME_MINIMUM_LENGTH || backingReportName.length() > AgyConstants.BACKING_REPORT_NAME_MAXIMUM_LENGTH)
      {
        // BackingReport Name is NOT correct length.
        if (backingReportName.length() < AgyConstants.BACKING_REPORT_NAME_MINIMUM_LENGTH)
        {
          errors.add("nhsBackingReport", new ActionMessage("errors.minlength", "Backing Report Name: " + backingReportName, AgyConstants.BACKING_REPORT_NAME_MINIMUM_LENGTH));
        }
        if (backingReportName.length() > AgyConstants.BACKING_REPORT_NAME_MAXIMUM_LENGTH)
        {
          errors.add("nhsBackingReport", new ActionMessage("errors.maxlength", "Backing Report Name: " + backingReportName, AgyConstants.BACKING_REPORT_NAME_MAXIMUM_LENGTH));
        }
      }
      else
      {
        NhsBackingReport nhsBackingReport = agyService.getNhsBackingReportForName(backingReportName, true);
        if (nhsBackingReport == null)
        {
          errors.add("nhsBackingReport", new ActionMessage("errors.doesNotExist", "Backing Report: " + backingReportName));
        }
      }
    }    
  }

  protected List<BookingDate> getBookingDates(AgyService agyService, String bookingDateIdStrs)
  {
    List<BookingDate> listBookingDate = new ArrayList<BookingDate>();
    BookingDate bookingDate = null;
    StringTokenizer stBookingDateId = new StringTokenizer(bookingDateIdStrs, ",");
    while (stBookingDateId.hasMoreTokens())
    {
      String bookingDateIdStr = stBookingDateId.nextToken();
      Integer bookingDateId = Integer.parseInt(bookingDateIdStr);
      // for each bookingDate call the updateBookingDateAuthorized method in
      // this class.
      bookingDate = agyService.getBookingDate(bookingDateId);
      listBookingDate.add(bookingDate);
    }
    return listBookingDate;
  }
  
  protected List<NhsBackingReport> getNewNhsBackingReports(AgyService agyService, String backingReports)
  {
    List<NhsBackingReport> listNhsBackingReport = new ArrayList<NhsBackingReport>();
    NhsBackingReport nhsBackingReport = null;
    StringTokenizer stBackingReport = new StringTokenizer(backingReports, ",");
    while (stBackingReport.hasMoreTokens())
    {
      String nhsBackingReportName = stBackingReport.nextToken();
      if (!nhsBackingReportName.startsWith("-"))
      {
        nhsBackingReport = agyService.getNhsBackingReportForName(nhsBackingReportName, true);
        listNhsBackingReport.add(nhsBackingReport);
      }
    }
    return listNhsBackingReport;
  }

  // Validate that each BookingDate is within ALL NHSBackingReports date range.
  protected void validateBookingDatesAgainstNhsBackingReportDates(AgyService agyService, ActionMessages errors, List<BookingDate> listBookingDate, List<NhsBackingReport> listNhsBackingReport)
  {
    for (BookingDate bookingDate : listBookingDate)
    {
      for (NhsBackingReport nhsBackingReport : listNhsBackingReport)
      {
        if (bookingDate.getBookingDate().before(nhsBackingReport.getStartDate()) || bookingDate.getBookingDate().after(nhsBackingReport.getEndDate()))
        {
          DecimalFormat df3 = new DecimalFormat("#000");
          SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
          String dateRange = sdf.format(nhsBackingReport.getStartDate()) + " - " + sdf.format(nhsBackingReport.getEndDate());
          String timesheetNumber = df3.format(bookingDate.getBookingId()) + "." + df3.format(bookingDate.getBookingDateId()) + " (" + sdf.format(bookingDate.getBookingDate()) + ")";
          errors.add("nhsBackingReport", new ActionMessage("errors.bookingDate.outsideNhsBackingReportDateRange", timesheetNumber, nhsBackingReport.getName(), dateRange));
        }
      }
    }
  }

}