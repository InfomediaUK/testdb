package com.helmet.application.agy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.PdfHandler;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeApplicantDateUserEntity;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.Booking;
import com.helmet.bean.Client;
import com.helmet.bean.JobProfile;
import com.helmet.bean.Location;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.Site;
import com.helmet.bean.nhs.BackingReport;
import com.helmet.bean.nhs.BackingReportColumnHeadings;
import com.helmet.bean.nhs.BackingReportError;
import com.helmet.bean.nhs.BackingReportLine;
import com.helmet.bean.nhs.BackingReportLineError;
import com.helmet.bean.nhs.BackingReportShift;
import com.helmet.bean.NhsBooking;

public class NhsBackingReportFileUploadProcess extends NhsFileUploadCommon
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());
  // First Column Headings.
  private static final String DATE_COLUMN = "Date";
  private static final String REF_NUM_COLUMN = "Ref Num";
  private static final String AGENCY_WORKER_NAME_COLUMN = "Agency Worker Name";
  private static final String AGENCY_WORKER_UNIQUE_ID_COLUMN = "Agency Worker Unique Id";
  private static final String TRUST_COLUMN = "Trust";
  private static final String WARD_COLUMN = "Ward";
  private static final String ASSIGNMENT_COLUMN = "Assignment";
  private static final String CONTRACT_COLUMN = "Contract";
  private static final String ACTUAL_COLUMN = "Actual";
  private static final String COMMISSION_COLUMN = "Commission";
  private static final String TOTAL_COST_COLUMN = "Total Cost";
  private static final String RATE_COLUMN = "Rate";
  // Second Column Headings.
  private static final String CONTRACT_START_COLUMN = "(Contract)Start";
  private static final String CONTRACT_END_COLUMN = "(Contract)End";
  private static final String CONTRACT_BREAK_IN_MINUTES_COLUMN = "(Contract)Break In Minutes";
  private static final String CONTRACT_TOTAL_COLUMN = "(Contract)Total";
  private static final String ACTUAL_START_COLUMN = "(Actual)Start";
  private static final String ACTUAL_END_COLUMN = "(Actual)End";
  private static final String ACTUAL_BREAK_IN_MINUTES_COLUMN = "(Actual)Break In Minutes";
  private static final String ACTUAL_TOTAL_COLUMN = "(Actual)Total";

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    ActionForward actionForward = null;
    NhsBackingReport nhsBackingReport = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Client client = (Client)dynaForm.get("client");
    FormFile nhsBackingReportFile = (FormFile)dynaForm.get("nhsBackingReportFormFile");
    String nhsBackingReportFilename = nhsBackingReportFile.getFileName();
    BackingReport backingReport = new BackingReport(nhsBackingReportFilename);
    client = agyService.getClientUser(client.getClientId());
    backingReport.setClientId(client.getClientId());
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    backingReport.setAgencyHasSubcontractors(agency.getHasSubcontractors());
    if (StringUtils.isEmpty(nhsBackingReportFilename))
    {
      errors.add("nhsBackingReportUpload", new ActionMessage("error.nhsBackingReportFile.notSupplied"));
    }
    else
    {
      // Now upload the NHS Backing Report file. It goes under the folder that matches its AgencyId.
      String fileUrl = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportFilename;
      String filePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + fileUrl;
      uploadFile(nhsBackingReportFile, filePath);
      HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
      String columnNumbersFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + FileHandler.getInstance().getNhsBackingReportFileFolder();
      columnNumbers(columnMap, columnNumbersFilePath, errors, "nhsBackingReportUpload", "error.nhsBackingReportColumnNumbersFile");
      if (errors.isEmpty())
      {
        try
        {
          readBackingReport(client, backingReport, filePath, columnMap);
          // Quick validation on file integrity. Throw exceptions if invalid.
          validateMoneyTotals(messageResources, backingReport);
          setHosptalAndWardText(backingReport);
// Could validate only one hospital/ward after Feb 2014...          
//          backingReport.setHospital(getHospitalsText(backingReport));
//          backingReport.setWard(getWardsText(backingReport));
          nhsBackingReport = agyService.getNhsBackingReportForName(backingReport.getName(), false);
          backingReport.setNhsBackingReport(nhsBackingReport);
          validateBackingReport(agyService, messageResources, backingReport, client);
          if (agency.getHasSubcontractors())
          {
            // 4SW, calculate Money Totals as some Backing Report Lines may be removed.
            backingReport.zeroiseCommission();
            backingReport.recalculateMoneyTotals();
          }
          if (!backingReport.getValid())
          {
            errors.add("nhsBackingReportUpload", new ActionMessage("error.nhsBookingFile.errorCount", backingReport.getErrorCount()));
          }
        }
        catch (Exception e)
        {
          errors.add("nhsBackingReportUpload", new ActionMessage("error.nhsBackingReportFile.exception", e.getClass().getName() + " : " + e.getMessage()));
        }
      }
    }
    logger.exit("Out going !!!");
    if (errors.isEmpty())
    {
      dynaForm.set("accept", backingReport.getName());
      dynaForm.set("nhsBackingReportFilename", nhsBackingReportFilename);
      dynaForm.set("backingReport", backingReport);
      request.getSession().setAttribute("backingReport", backingReport);
      return mapping.findForward("attentionRequired");
//      actionForward = mapping.findForward("success");
//      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?nhsBackingReportUser.nhsBackingReportId=" + nhsBackingReport.getNhsBackingReportId(), actionForward.getRedirect());
    }
    else
    {
      saveErrors(request, errors);
      if (backingReport.getErrorCount() > 0)
      {
        dynaForm.set("accept", backingReport.getName());
        dynaForm.set("nhsBackingReportFilename", nhsBackingReportFilename);
        dynaForm.set("backingReport", backingReport);
        if (backingReport.getCanAccept())
        {
          request.getSession().setAttribute("backingReport", backingReport);
        }
        else
        {
          request.getSession().removeAttribute("backingReport");
        }
        actionForward = mapping.findForward("attentionRequired");
      }
      else
      {
        actionForward = mapping.findForward("error");
      }
    }
    return actionForward;
  }
 
  private void readBackingReport(Client client, BackingReport backingReport, String filePath, HashMap<String, Integer> columnMap)
    throws Exception
  {
    Boolean totalLineFound = false;
    List<BackingReportLine> backingReportLines = new ArrayList<BackingReportLine>();
    String backingReportName = null;
    BufferedReader br = null;
    String line = "";
    try
    {
      br = new BufferedReader(new FileReader(filePath));
      // First line contains Backing Report Name.
      line = br.readLine();
      if (line.startsWith("backing report ") || line.startsWith("Backing report ") || line.startsWith("Backing Report "))
      {
        backingReportName = line.substring("backing report ".length(), line.indexOf(","));
        if (backingReportName.length() > 8)
        {
          throw new Exception("First line 'Backing Report Name' > 8 characters long: [" + backingReportName + "]");
        }
        backingReport.setName(backingReportName.trim());
        System.out.println(backingReportName);
      }
      else
      {
        throw new Exception("First line in file does NOT start with 'backing report'...");
      }
      // Booking Report line OK, now read first column headings.
      line = br.readLine();
      String[] columnHeadings1 = line.split(",");
      System.out.println("Column Headings 1 = " + columnHeadings1.length);
      BackingReportColumnHeadings backingReportColumnHeadings = new BackingReportColumnHeadings();
      backingReportColumnHeadings.setDate(columnHeadings1[columnMap.get(DATE_COLUMN)]);
      backingReportColumnHeadings.setRefNum(columnHeadings1[columnMap.get(REF_NUM_COLUMN)]);
      backingReportColumnHeadings.setAgencyWorkerName(columnHeadings1[columnMap.get(AGENCY_WORKER_NAME_COLUMN)]);
      backingReportColumnHeadings.setTrust(columnHeadings1[columnMap.get(TRUST_COLUMN)]);
      backingReportColumnHeadings.setWard(columnHeadings1[columnMap.get(WARD_COLUMN)]);
      backingReportColumnHeadings.setAssignment(columnHeadings1[columnMap.get(ASSIGNMENT_COLUMN)]);
      backingReportColumnHeadings.setContract(columnHeadings1[columnMap.get(CONTRACT_COLUMN)]);
      backingReportColumnHeadings.setActual(columnHeadings1[columnMap.get(ACTUAL_COLUMN)]);
      backingReportColumnHeadings.setCommission(columnHeadings1[columnMap.get(COMMISSION_COLUMN)]);
      backingReportColumnHeadings.setTotalCost(columnHeadings1[columnMap.get(TOTAL_COST_COLUMN)]);
      backingReportColumnHeadings.setRate(columnHeadings1[columnMap.get(RATE_COLUMN)]);
      // First Column Headings OK, now read second column headings.
      line = br.readLine();
      String[] columnHeadings2 = line.split(",");
      System.out.println("Column Headings 2 = " + columnHeadings2.length);
      backingReportColumnHeadings.setContractStart(columnHeadings2[columnMap.get(CONTRACT_START_COLUMN)]);
      backingReportColumnHeadings.setContractEnd(columnHeadings2[columnMap.get(CONTRACT_END_COLUMN)]);
      backingReportColumnHeadings.setContractBreakInMinutes(columnHeadings2[columnMap.get(CONTRACT_BREAK_IN_MINUTES_COLUMN)]);
      backingReportColumnHeadings.setContractTotal(columnHeadings2[columnMap.get(CONTRACT_TOTAL_COLUMN)]);
      backingReportColumnHeadings.setActualStart(columnHeadings2[columnMap.get(ACTUAL_START_COLUMN)]);
      backingReportColumnHeadings.setActualEnd(columnHeadings2[columnMap.get(ACTUAL_END_COLUMN)]);
      backingReportColumnHeadings.setActualBreakInMinutes(columnHeadings2[columnMap.get(ACTUAL_BREAK_IN_MINUTES_COLUMN)]);
      backingReportColumnHeadings.setActualTotal(columnHeadings2[columnMap.get(ACTUAL_TOTAL_COLUMN)]);
      String headingsErrors = backingReportColumnHeadings.validateColumnHeadings();
      if (StringUtils.isNotEmpty(headingsErrors))
      {
        throw new Exception("Found empty column headings: " + headingsErrors);
      }
      backingReport.setBackingReportColumnHeadings(backingReportColumnHeadings);
      // All Column Headings OK, now read data. Each 'row' is made up of two lines (the second contains the ward in column 5).
      String[] columnData = null;
      BackingReportLine backingReportLine = null;
      int lineCount = 0;
      while ((line = br.readLine()) != null)
      {
        columnData = line.split(",");
        if (lineCount % 2 == 0)
        {
          // First of two data lines OR last (total) line.
          // WAS if (isDate(columnData[0]))
          if (line.startsWith(",,,,,,,,,,,,,,,"))
          {
            // The last (total) line.
            totalLineFound = true;
            backingReport = fillBackingReportFromLastLine(backingReport, columnData, columnMap);
            backingReport.setBackingReportLines(backingReportLines);
            System.out.println(backingReport);
          }
          else
          {
            // First of the two data lines.
            if (isDate(columnData[0]))
            {
              backingReportLine = fillBackingReportLineFromFirstLine(backingReport, columnData, columnMap);
              for (String cell : columnData)
              {
                System.out.print(cell + "|");
              }
              System.out.println("Column Data = " + columnData.length);
              if (!backingReportLine.getTrust().equals(client.getNhsName())) 
              { 
                throw new Exception("Backing Report Line found is for " + backingReportLine.getTrust() + ", not for client (" + client.getName() + ")."); 
              }
            }
            else
            {
              throw new Exception("First column of first data line not date format dd/mm/yyyy: " + columnData[0]); 
            }
          }
        }
        else
        {
          // Second of the two data lines.
          backingReportLine.setWard(columnData[columnMap.get(WARD_COLUMN)]);
          backingReportLines.add(backingReportLine);
        }
        lineCount++;
      }
      if (backingReportLines.size() > 0)
      {
        // Set the Trust on the Backing Report to be that of the first Backing Report Line. There can ONLY BE ONE Trust.
        backingReport.setTrust(backingReportLines.get(0).getTrust());
      }
      if (totalLineFound == false)
      {
        throw new Exception("Total Line NOT found. File not complete, cannot continue.");  
      }
    }
    finally
    {
      if (br != null)
      {
        br.close();
      }
    }
  }
  
  private BackingReportLine fillBackingReportLineFromFirstLine(BackingReport backingReport, String[] columnData, HashMap<String, Integer> columnMap) 
    throws ParseException
  {
    BackingReportLine backingReportLine = new BackingReportLine(backingReport);
    DateFormat dateFormat = new SimpleDateFormat(getDateFormat());
    dateFormat.setLenient(false);
    DateFormat timeFormat = new SimpleDateFormat(getTimeFormat());
    timeFormat.setLenient(false);
    java.util.Date date = dateFormat.parse(columnData[columnMap.get(DATE_COLUMN)]);
    backingReportLine.setDate(new Date(date.getTime()));
    backingReportLine.setBankReqNum(columnData[columnMap.get(REF_NUM_COLUMN)]);
    backingReportLine.setAgencyWorkerName(columnData[columnMap.get(AGENCY_WORKER_NAME_COLUMN)]);
    backingReportLine.setAgencyWorkerUniqueId(columnData[columnMap.get(AGENCY_WORKER_UNIQUE_ID_COLUMN)]);
    backingReportLine.setTrust(columnData[columnMap.get(TRUST_COLUMN)]);
    backingReportLine.setHospital(columnData[columnMap.get(WARD_COLUMN)]);
    backingReportLine.setAssignment(columnData[columnMap.get(ASSIGNMENT_COLUMN)]);
    // Set up Contract Shift details.
    BackingReportShift contractBackingReportShift = new BackingReportShift();
    Time time = new Time(timeFormat.parse(columnData[columnMap.get(CONTRACT_START_COLUMN)]).getTime());
    contractBackingReportShift.setStartTime(time);
    time = new Time(timeFormat.parse(columnData[columnMap.get(CONTRACT_END_COLUMN)]).getTime());
    contractBackingReportShift.setEndTime(time);
    contractBackingReportShift.setBreakMinutes(Integer.parseInt(columnData[columnMap.get(CONTRACT_BREAK_IN_MINUTES_COLUMN)]));
    contractBackingReportShift.setWorkedMinutes(getWorkedMinutes(columnData[columnMap.get(CONTRACT_TOTAL_COLUMN)]));
    contractBackingReportShift.setWorkedTime(columnData[columnMap.get(CONTRACT_TOTAL_COLUMN)]);
    backingReportLine.setContractShift(contractBackingReportShift);
    // Set up Actual Shift details.
    BackingReportShift actualBackingReportShift = new BackingReportShift();
    time = new Time(timeFormat.parse(columnData[columnMap.get(ACTUAL_START_COLUMN)]).getTime());
    actualBackingReportShift.setStartTime(time);
    time = new Time(timeFormat.parse(columnData[columnMap.get(ACTUAL_END_COLUMN)]).getTime());
    actualBackingReportShift.setEndTime(time);
    actualBackingReportShift.setBreakMinutes(Integer.parseInt(columnData[columnMap.get(ACTUAL_BREAK_IN_MINUTES_COLUMN)]));
    actualBackingReportShift.setWorkedMinutes(getWorkedMinutes(columnData[columnMap.get(ACTUAL_TOTAL_COLUMN)]));
    actualBackingReportShift.setWorkedTime(columnData[columnMap.get(ACTUAL_TOTAL_COLUMN)]);
    backingReportLine.setActualShift(actualBackingReportShift);
  
    DecimalFormat decimalFormat = getDecimalFormat();
    backingReportLine.setCommission((BigDecimal)decimalFormat.parse(columnData[columnMap.get(COMMISSION_COLUMN)]));
    backingReportLine.setTotalCost((BigDecimal)decimalFormat.parse(columnData[columnMap.get(TOTAL_COST_COLUMN)]));
    backingReportLine.setRate(columnData[columnMap.get(RATE_COLUMN)]);
    return backingReportLine;
  }

  private BackingReport fillBackingReportFromLastLine(BackingReport backingReport, String[] columnData, HashMap<String, Integer> columnMap)
  throws Exception
{
  DecimalFormat decimalFormat = getDecimalFormat();
  BigDecimal commission = (BigDecimal)decimalFormat.parse(columnData[columnMap.get(COMMISSION_COLUMN)]);
  backingReport.setTotalCommission(commission);
  BigDecimal totalCost = (BigDecimal)decimalFormat.parse(columnData[columnMap.get(TOTAL_COST_COLUMN)]);
  backingReport.setTotalCost(totalCost);
  BigDecimal grandTotal = (BigDecimal)decimalFormat.parse(columnData[columnMap.get(RATE_COLUMN)]);
  if (grandTotal.compareTo(commission.add(totalCost)) != 0)
  {
    throw new Exception("Last line in file: " + Arrays.toString(columnData) + " Commission (" + commission + ") + Total Cost (" + totalCost + ") does NOT equal Grand Total (" + grandTotal + ").");
  }
  return backingReport;
}

  private Integer getWorkedMinutes(String workedHoursMinutes)
  {
    Pattern pattern = Pattern.compile(getTIME_REGEX());
    Integer hours = null;
    Integer minutes = null;
    Matcher matcher = pattern.matcher(workedHoursMinutes);
    if (matcher.find())
    {
      String hh = matcher.group(1);
      hours = Integer.parseInt(hh);
      String mm = matcher.group(2);
      minutes = Integer.parseInt(mm);
      return (hours * 60) + minutes;
    }
    return -1;
  }
  
  private void validateBackingReport(AgyService agyService, MessageResources messageResources, BackingReport backingReport, Client client)
  {
    Boolean fatalError = false;
    DateFormat dateFormat = new SimpleDateFormat(getDateFormat());
    String errorMessage = null;
    String view = null;
    BookingGradeApplicantUser bookingGradeApplicantUser = null;
    NhsBackingReport nhsBackingReport = backingReport.getNhsBackingReport();
    List<BookingDateUserApplicant> listBookingDateUserApplicant = null;
    if (nhsBackingReport != null && nhsBackingReport.getActive())
    {
      // This Backing Report has an existing ACTIVE NhsBackingReport record in the database.
      listBookingDateUserApplicant = getBookingDateUserApplicantListForBackingReportName(agyService, nhsBackingReport.getStartDate(), nhsBackingReport.getEndDate(), nhsBackingReport.getName());
      view = listBookingDateUserApplicant.size() == 0 ? "DETAIL" : "LIST";
      if (!nhsBackingReport.getAgencyId().equals(getConsultantLoggedIn().getAgencyId()))
      {
        // This Backing Report is for a different Agency! FATAL ERROR!!!
        AgencyUser nhsBackingReportAgency = agyService.getAgencyUser(nhsBackingReport.getAgencyId());
        errorMessage = messageResources.getMessage("error.nhsBackingReport.notForThisAgency", nhsBackingReportAgency.getName());
        backingReport.setAgencyInvalid(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage));
        fatalError = true;
      }
      if (!nhsBackingReport.getClientId().equals(backingReport.getClientId()))
      {
        // This Backing Report is for a different Client! FATAL ERROR!!!
        Client nhsBackingReportClient = agyService.getClientUser(nhsBackingReport.getClientId());
        errorMessage = messageResources.getMessage("error.nhsBackingReport.notForClient", nhsBackingReportClient.getName());
        backingReport.setClientInvalid(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage));
        fatalError = true;
      }
      if (nhsBackingReport.getCompleteDate() != null)
      {
        // This Backing Report is already completed...
        errorMessage = messageResources.getMessage("error.nhsBackingReport.alreadyCompleted", dateFormat.format(nhsBackingReport.getCompleteDate()));
        backingReport.setBookingReportCompleted(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage));
        fatalError = true;
      }
      if (StringUtils.isNotEmpty(nhsBackingReport.getDocumentationFileName()) && StringUtils.isNotEmpty(nhsBackingReport.getTradeshiftDocumentId()))
      {
        // This Backing Report already has Documentation which has been sent to Tradeshift.
        errorMessage = messageResources.getMessage("error.nhsBackingReport.documentationAlreadyUploadedToTradeshift", nhsBackingReport.getTradeshiftDocumentId());
        backingReport.setDocumentationAlreadySentToTradeshift(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage));
        fatalError = true;
      }
      if (fatalError == false)
      {
        errorMessage = messageResources.getMessage("error.nhsBackingReport.alreadyExists", nhsBackingReport.getName());
        backingReport.setNameInvalid(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage, view));
        if (!(nhsBackingReport.getStartDate().compareTo(backingReport.getStartDate()) == 0 && nhsBackingReport.getEndDate().compareTo(backingReport.getEndDate()) == 0))
        {
          // This Backing Report is for a different Date Range...
          errorMessage = messageResources.getMessage("error.nhsBackingReport.dateRangeChanged", dateFormat.format(nhsBackingReport.getStartDate()), dateFormat.format(nhsBackingReport.getEndDate()));
          backingReport.setNameInvalid(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage, view));
        }
        if (nhsBackingReport.getAgreedValue().compareTo(backingReport.getGrandTotalCost()) != 0)
        {
          // This Backing Report is for a different Grand Total Cost...
          errorMessage = messageResources.getMessage("error.nhsBackingReport.grandTotalCostChanged", nhsBackingReport.getAgreedValue());
          backingReport.setGrandTotalCostInvalid(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage, view));
        }
        if (StringUtils.isNotEmpty(nhsBackingReport.getDocumentationFileName()) && StringUtils.isEmpty(nhsBackingReport.getTradeshiftDocumentId()))
        {
          // This Backing Report already has Documentation but it has NOT yet been sent to Tradeshift.
          errorMessage = messageResources.getMessage("error.nhsBackingReport.alreadyHasDocumentation", nhsBackingReport.getDocumentationFileName());
          backingReport.setHasDocumentation(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage, view));
        }
        if (listBookingDateUserApplicant.size() != backingReport.getBackingReportLines().size())
        {
          // The number of BookingDateUserApplicants on the NHS Backing Report does NOT equal the number of lines in the Backing Report.
          errorMessage = messageResources.getMessage("error.nhsBackingReport.numberOfLinesDiffer", listBookingDateUserApplicant.size(), backingReport.getBackingReportLines().size());
          backingReport.setNumberOfLinesDiffer(new BackingReportError(nhsBackingReport.getNhsBackingReportId(), errorMessage, view));
        }
      }      
    }
    if (fatalError == false)
    {
      // No Fatal errors so far, continue with validation...
      for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
      {
        // For each Backing Report Line, get it's NHS Booking...
        NhsBooking nhsBooking = agyService.getActiveNhsBookingForBankReqNum(getConsultantLoggedIn().getAgencyId(), backingReportLine.getBankReqNum());
        if (nhsBooking == null)
        {
          // NHS Booking NOT found. Create warning.
//          errorMessage = messageResources.getMessage("error.nhsBooking.notFound", backingReportLine.getBankReqNum());
//          backingReportLine.nhsBookingNotFound(new BackingReportLineError(errorMessage));
          Applicant applicant = getApplicantForAgencyWorkerName(agyService, messageResources, backingReportLine);
          if (applicant != null)
          {
            // An Applicant has been found matching the AgencyWorkerName...
            List<Booking> bookingList = agyService.getBookingsForBookingReference(backingReportLine.getBankReqNum());
            if (bookingList.size() == 0)
            {
              // No Bookings found with Booking Reference same as Bank Req Number.
              errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingOrNhsBookingNotFound", backingReportLine.getBankReqNum());
              backingReportLine.setBankReqNumInvalid(new BackingReportLineError(errorMessage));
            }
            else
            {
              // One or more Bookings found with BookingReference same as Bank Req Number. 
              Booking booking = null;
              Integer completedBookingCount = 0;
              if (bookingList.size() == 1)
              {
                // Only ONE Booking found.
                booking = bookingList.get(0);
                if (!(backingReportLine.getDate().before(booking.getMinBookingDate()) || backingReportLine.getDate().after(booking.getMaxBookingDate())))
                {
                  // Booking is for the right date (range).
                  if (booking.getStatus().equals(booking.BOOKING_STATUS_COMPLETED))
                  {
                    ++completedBookingCount;
                  }
                }
              }
              else
              {
                // More than ONE Booking found.
                ListIterator listIterator = bookingList.listIterator(bookingList.size());
                Boolean bookingMatched = null;
                while (listIterator.hasPrevious())
                {
                  booking = (Booking) listIterator.previous();
                  if (booking.getActive())
                  {
                    // Active Booking found.
                    if (!(backingReportLine.getDate().before(booking.getMinBookingDate()) || backingReportLine.getDate().after(booking.getMaxBookingDate())))
                    {
                      // Booking is for the right date (range).
                      if (booking.getStatus().equals(booking.BOOKING_STATUS_COMPLETED))
                      {
                        ++completedBookingCount;
                      }
                      bookingGradeApplicantUser = agyService.getBookingGradeApplicantUserForBookingFilledSingleCandidate(booking.getBookingId());
                      if (bookingGradeApplicantUser == null)
                      {
                        // BookingGradeApplicantUser NOT found for Booking.
                        errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingGradeApplicantUserNotFound", booking.getBookingId());
                        backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
                      }
                      else
                      {
                        // BookingGradeApplicantUser found for Booking.
                        bookingMatched = checkBookingMatchesBackingReport(agyService, messageResources, backingReportLine, bookingGradeApplicantUser, applicant);
                        if (!bookingMatched)
                        {
                          // Booking does NOT match Backing Report. Remove it from the list.
                          listIterator.remove();
                        }
                      }
                    }
                  }
                  else
                  {
                    // Booking NOT Active. Remove it from the list.
                    listIterator.remove();
                  }
                }
              }              
              // Now check any Bookings that are still in the list...
              if (bookingList.size() == 0)
              {
                // No Booking found that matches the Backing Report BankReqNum. Re-select Booking List.
                bookingList = agyService.getBookingsForBookingReference(backingReportLine.getBankReqNum());
                String bookingIds = getMultipleBookingIds(bookingList, false);
                errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingsFoundButDoNotMatchBackingReport", bookingIds);
                backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
              }
              else
              {
                // One or more Bookings found.
                if (completedBookingCount == 0)
                {
                  // No Completed Booking found that matches the Backing Report BankReqNum.
                  if (bookingList.size() > 1)
                  {
                    // More than ONE Booking found that matches the Backing Report BankReqNum.
                    String bookingIds = getMultipleBookingIds(bookingList, false);
                    errorMessage = messageResources.getMessage("error.nhsBackingReport.moreThanOneBookingFound", bookingIds);
                    backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
                  }
                  else
                  {
                    // ONE Booking found but it's NOT Completed.
                    booking = bookingList.get(0);
                    backingReportLine.setBookingId(booking.getBookingId());
                    bookingGradeApplicantUser = agyService.getBookingGradeApplicantUserForBookingFilledSingleCandidate(booking.getBookingId());
                    errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingFoundButNotComplete", booking.getBookingId().toString());
                    backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
                    if (bookingGradeApplicantUser == null)
                    {
                      // BookingGradeApplicantUser NOT found for Booking.
                      errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingGradeApplicantUserNotFound", booking.getBookingId());
                      backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
                    }
                    else
                    {
                      backingReportLine.setBookingGradeId(bookingGradeApplicantUser.getBookingGradeId());
                      validateHospitalWardAndAssignment(agyService, messageResources, backingReportLine, bookingGradeApplicantUser, applicant);
                      validateApplicantAndBooking(agyService, messageResources, backingReportLine, bookingGradeApplicantUser, applicant);
                    }
                  }
                }
                else
                {
                  if (completedBookingCount > 1)
                  {
                    // More than ONE Completed Booking found that matches the Backing Report.
                    String bookingIds = getMultipleBookingIds(bookingList, true);
                    errorMessage = messageResources.getMessage("error.nhsBackingReport.moreThanOneCompletedBookingFound", bookingIds);
                    backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
                  }
                  else
                  {
                    // ONE Completed Booking found that matches the Backing Report.
                    for (Booking bookingToCheck : bookingList)
                    {
                      if (booking.getStatus().equals(bookingToCheck.BOOKING_STATUS_COMPLETED))
                      {
                        booking = bookingToCheck;
                        break;
                      }
                    }
                    bookingGradeApplicantUser = agyService.getBookingGradeApplicantUserForBookingFilledSingleCandidate(booking.getBookingId());
                    if (bookingGradeApplicantUser == null)
                    {
                      // BookingGradeApplicantUser NOT found for Booking.
                      errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingGradeApplicantUserNotFound", booking.getBookingId());
                      backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
                    }
                    else
                    {
                      validateHospitalWardAndAssignment(agyService, messageResources, backingReportLine, bookingGradeApplicantUser, applicant);
                      validateApplicantAndBooking(agyService, messageResources, backingReportLine, bookingGradeApplicantUser, applicant);
                    }
                  }
                }
              }
            }
          }          
        }
        else
        {
          // NHS Booking found, continue with validation. This is the 'NORMAL SITUATION'...
          validateAgainstNhsBooking(agyService, messageResources, backingReportLine, nhsBooking);
          bookingGradeApplicantUser = agyService.getBookingGradeApplicantUserForBookingFilledSingleCandidate(nhsBooking.getBookingId());
          if (bookingGradeApplicantUser == null)
          {
            // BookingGradeApplicantUser NOT found for Booking.
            errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingGradeApplicantUserNotFound", nhsBooking.getBookingId());
            backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
          }
          else
          {
            validateAgainstBookingAndNhsBooking(agyService, messageResources, backingReportLine, bookingGradeApplicantUser, nhsBooking);
          }
        }
      }
//      if (listBookingDateUserApplicant != null)
//      {
//        // Now process the BookingDateUserApplicants...
//        ListIterator listIterator = listBookingDateUserApplicant.listIterator(listBookingDateUserApplicant.size());
//        BookingDateUserApplicant bookingDateUserApplicant = null;
//        String bankReqNum = null;
//        while (listIterator.hasPrevious())
//        {
//          // For each BookingDateUserApplicants (in reverse order)...
//          bookingDateUserApplicant = (BookingDateUserApplicant) listIterator.previous();
//          bankReqNum = bookingDateUserApplicant.getBookingReference().substring(AgyConstants.NHS_BOOKINGS_BANK_REQUEST_NUMBER_LABEL.length());
//          for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
//          {
//            // For each Backing Report Line...
//            if (backingReportLine.getBankReqNum().equals(bankReqNum))
//            {
//              // Found matching BookingDateUserApplicant and Backing Report Line.
//              backingReportLine.setBookingId(bookingDateUserApplicant.getBookingId());
//              backingReportLine.setBookingGradeId(bookingDateUserApplicant.getBookingGradeId());
//              backingReportLine.setBookingDateId(bookingDateUserApplicant.getBookingDateId());
//              // Now remove the BookingDateUserApplicant from its list.
//              listIterator.remove();
//              break;
//            }
//          }
//        }
//        System.out.println(listBookingDateUserApplicant.size());
//      }      
    }    
  }

  private Applicant getApplicantForAgencyWorkerName(AgyService agyService, MessageResources messageResources, BackingReportLine backingReportLine)
  {
    Applicant applicant = null;
    String errorMessage = null;
    List<Applicant> applicantList = agyService.getApplicantsForNhsStaffName(getConsultantLoggedIn().getAgencyId(), backingReportLine.getAgencyWorkerName());
    if (applicantList.size() == 0)
    {
      errorMessage = messageResources.getMessage("error.nhsBackingReport.applicantNotFound", backingReportLine.getAgencyWorkerName());
      backingReportLine.setAgencyWorkerNameInvalid(new BackingReportLineError(errorMessage));
      if (backingReportLine.getBackingReport().getAgencyHasSubcontractors())
      {
        // 4SW. One of their workers.
        if (backingReportLine.getTotalCost().compareTo(new BigDecimal(0)) != 0)
        {
          // This line has a Total Cost. Zeroise the value...
          backingReportLine.setTotalCost(new BigDecimal(0));
          backingReportLine.setTotalCostChanged(true);
        }
      }
    }
    else
    {
      if (applicantList.size() > 1)
      {
        errorMessage = messageResources.getMessage("error.nhsBackingReport.applicantMoreThanOneFound", backingReportLine.getAgencyWorkerName());
        backingReportLine.setAgencyWorkerNameInvalid(new BackingReportLineError(errorMessage));
      }
      else
      {
        applicant = applicantList.get(0);
      }
    }
    return applicant;
  }
  
  private void validateAgainstNhsBooking(AgyService agyService, MessageResources messageResources, BackingReportLine backingReportLine, NhsBooking nhsBooking)
  {
    DateFormat dateFormat = new SimpleDateFormat(getDateFormat());
    DateFormat timeFormat = new SimpleDateFormat(getTimeFormat());
    String errorMessage = null;
    if (nhsBooking.getSubcontractInvoiceId() > 0)
    {
      // NHS Booking has Subcontract Invoice. This could lead to double invoicing!!!!!
      backingReportLine.setSubcontractInvoiceNumber(nhsBooking.getSubcontractInvoiceNumber());
      errorMessage = messageResources.getMessage("error.nhsBackingReport.nhsBookingHasSubcontractInvoice", nhsBooking.getBankReqNum(), nhsBooking.getSubcontractInvoiceNumber());
      backingReportLine.alreadyInvoiced(new BackingReportLineError(errorMessage));
    }
    if (nhsBooking.getValue().equals(BigDecimal.ZERO))
    {
      // NHS Booking has Zero Value.
      errorMessage = messageResources.getMessage("error.nhsBackingReport.nhsBookingHasZeroValue", nhsBooking.getBankReqNum());
      backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
    }
    if (!backingReportLine.getDate().equals(nhsBooking.getDate()))
    {
      // Date on Backing Report is NOT the same as the Date on the NHS Booking.
      errorMessage = messageResources.getMessage("error.nhsBackingReport.dateChanged", dateFormat.format(nhsBooking.getDate()));
      backingReportLine.setDateInvalid(new BackingReportLineError(errorMessage));
    }
    if (!backingReportLine.getAgencyWorkerName().equals(nhsBooking.getStaffName()))
    {
      // Agency Worker Name on Backing Report is NOT the same as the Staff Name on the NHS Booking.
      if (backingReportLine.getAgencyWorkerName().equalsIgnoreCase(nhsBooking.getStaffName()))
      {
        // Agency Worker Name on Backing Report is NOT the same case as the Staff Name on the NHS Booking.
        errorMessage = messageResources.getMessage("error.nhsBackingReport.staffNameCaseChanged", nhsBooking.getStaffName());
        backingReportLine.setAgencyWorkerNameCaseChanged(new BackingReportLineError(errorMessage));
      }
      else
      {
        // Agency Worker Name on Backing Report is NOT the same as the Staff Name on the NHS Booking.
        errorMessage = messageResources.getMessage("error.nhsBackingReport.staffNameChanged", nhsBooking.getStaffName());
        backingReportLine.setAgencyWorkerNameInvalid(new BackingReportLineError(errorMessage));
      }
    }
    if (!backingReportLine.getHospital().equals(nhsBooking.getLocation()))
    {
      // Hospital on Backing Report is NOT the same as the Location on the NHS Booking.
      errorMessage = messageResources.getMessage("error.nhsBackingReport.hospitalChanged", nhsBooking.getLocation());
      backingReportLine.setHospitalInvalid(new BackingReportLineError(errorMessage));
    }
    if (!backingReportLine.getWard().equals(nhsBooking.getWard()))
    {
      // Ward on Backing Report is NOT the same as the Ward on the NHS Booking.
      errorMessage = messageResources.getMessage("error.nhsBackingReport.wardChanged", nhsBooking.getWard());
      backingReportLine.setWardInvalid(new BackingReportLineError(errorMessage));
    }
    if (!backingReportLine.getAssignment().equals(nhsBooking.getAssignment()))
    {
      // Assignment on Backing Report is NOT the same as the Assignment on the NHS Booking.
      errorMessage = messageResources.getMessage("error.nhsBackingReport.assignmentChanged", nhsBooking.getAssignment());
      backingReportLine.setAssignmentInvalid(new BackingReportLineError(errorMessage));
    }
    if (!backingReportLine.getContractShift().getStartTime().equals(nhsBooking.getStartTime()))
    {
      // Contract Start Time on Backing Report is NOT the same as the Start Time on the NHS Booking.
      errorMessage = messageResources.getMessage("error.nhsBackingReport.contractStartTimeChanged", timeFormat.format(nhsBooking.getStartTime()));
      backingReportLine.setContractStartTimeInvalid(new BackingReportLineError(errorMessage));
    }
    if (!backingReportLine.getContractShift().getEndTime().equals(nhsBooking.getEndTime()))
    {
      // Contract End Time on Backing Report is NOT the same as the End Time on the NHS Booking.
      errorMessage = messageResources.getMessage("error.nhsBackingReport.contractEndTimeChanged", timeFormat.format(nhsBooking.getEndTime()));
      backingReportLine.setContractEndTimeInvalid(new BackingReportLineError(errorMessage));
    }
    if (backingReportLine.getTotalCost().compareTo(nhsBooking.getValue()) != 0)
    {
      // Total Cost has been changed from that on NHS Booking. Use value from NHS Booking.
      if (backingReportLine.getBackingReport().getAgencyHasSubcontractors())
      {
        // 4SW.
        backingReportLine.setTotalCost(nhsBooking.getValue());
        backingReportLine.setTotalCostChanged(true);
      } 
    }
  }
 
  private void validateAgainstBookingAndNhsBooking(AgyService agyService, MessageResources messageResources, BackingReportLine backingReportLine, BookingGradeApplicantUser bookingGradeApplicantUser, NhsBooking nhsBooking)
  {
    String errorMessage = null;
    BookingGradeApplicantUserEntity bookingGradeApplicantUserEntity = null;
    BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity = null;
    if (bookingGradeApplicantUser.getBookingGradeId().equals(nhsBooking.getBookingGradeId()) && 
        bookingGradeApplicantUser.getApplicantId().equals(nhsBooking.getApplicantId()))
    {
      // Booking Grade and Applicant match the NHS Booking. 
      backingReportLine.setBookingId(bookingGradeApplicantUser.getBookingId());
      backingReportLine.setBookingGradeId(bookingGradeApplicantUser.getBookingGradeId());
      bookingGradeApplicantUserEntity = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicantUser.getBookingGradeApplicantId());
      if (bookingGradeApplicantUserEntity.getBookingGradeApplicantDateUserEntities().size() == 1)
      {
        // Booking is ONLY one day. Cool...
        bookingGradeApplicantDateUserEntity = bookingGradeApplicantUserEntity.getBookingGradeApplicantDateUserEntities().get(0);
        validateBackingReportLineAgainstBooking(agyService, messageResources, backingReportLine, bookingGradeApplicantUser, bookingGradeApplicantDateUserEntity);
      }
      else
      {
        errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingForMoreThanOneDay", bookingGradeApplicantUser.getBookingId().toString(), bookingGradeApplicantUserEntity.getBookingGradeApplicantDateUserEntities().size());
        backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
      }
    }
  }

  private Boolean validateBackingReportLineAgainstBooking(AgyService agyService, MessageResources messageResources, BackingReportLine backingReportLine, BookingGradeApplicantUser bookingGradeApplicantUser, BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity)
  {
    Boolean valid = true;
    DateFormat timeFormat = new SimpleDateFormat(getTimeFormat());
    String errorMessage = null;
    if (backingReportLine.getDate().equals(bookingGradeApplicantDateUserEntity.getBookingDate()))
    {
      // Booking looks good. Set BookingId, BookingGradeId and BookingDateId on BackingReportLine.
      backingReportLine.setBookingId(bookingGradeApplicantUser.getBookingId());
      backingReportLine.setBookingGradeId(bookingGradeApplicantUser.getBookingGradeId());
      backingReportLine.setBookingDateId(bookingGradeApplicantDateUserEntity.getBookingDateId());
      backingReportLine.setApplicantOriginalAgencyId(bookingGradeApplicantUser.getApplicantOriginalAgencyId());
      // Now check times...
      if (!(backingReportLine.getContractShift().getStartTime().equals(bookingGradeApplicantDateUserEntity.getShiftStartTime())))
      {
        // Shift Start Times do NOT match.
        errorMessage = messageResources.getMessage("error.nhsBackingReport.ShiftStartTimeDoesNotMatch", 
            timeFormat.format(backingReportLine.getContractShift().getStartTime()), 
            bookingGradeApplicantUser.getBookingId().toString(), 
            timeFormat.format(bookingGradeApplicantDateUserEntity.getShiftStartTime()));
        backingReportLine.setContractStartTimeInvalid(new BackingReportLineError(errorMessage));
        valid = false;
      }
      if (!(backingReportLine.getContractShift().getEndTime().equals(bookingGradeApplicantDateUserEntity.getShiftEndTime())))
      {
        // Shift End Times do NOT match.
        errorMessage = messageResources.getMessage("error.nhsBackingReport.ShiftEndTimeDoesNotMatch", 
            timeFormat.format(backingReportLine.getContractShift().getEndTime()), 
            bookingGradeApplicantUser.getBookingId().toString(), 
            timeFormat.format(bookingGradeApplicantDateUserEntity.getShiftEndTime()));
        backingReportLine.setContractEndTimeInvalid(new BackingReportLineError(errorMessage));
        valid = false;
      }
      System.out.println("AgencyHasSubcontractors: " + backingReportLine.getBackingReport().getAgencyHasSubcontractors());
      if (bookingGradeApplicantDateUserEntity.getWorkedStartTime() == null)
      {
        // Worked Start Time NOT set.
        errorMessage = messageResources.getMessage("error.nhsBackingReport.workedStartTimeNotEntered", bookingGradeApplicantDateUserEntity.getBookingId().toString());
        backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
        valid = false;
      }
      else
      {
        if (!backingReportLine.getActualShift().getStartTime().equals(bookingGradeApplicantDateUserEntity.getWorkedStartTime()))
        {
          // Actual Start Time on Backing Report is NOT the same as the Start Time on the Booking.
          if (backingReportLine.getBackingReport().getAgencyHasSubcontractors())
          {
            // 4SW. Set Actual Shift Start Time to that of the Booking.
            backingReportLine.getActualShift().setStartTime(bookingGradeApplicantDateUserEntity.getWorkedStartTime());
            backingReportLine.getActualShift().setStartTimeChanged(true);
          }
          else
          {
            errorMessage = messageResources.getMessage("error.nhsBackingReport.actualStartTimeChanged", timeFormat.format(bookingGradeApplicantDateUserEntity.getWorkedStartTime()));
            backingReportLine.setActualStartTimeInvalid(new BackingReportLineError(bookingGradeApplicantUser.getBookingGradeApplicantId(), errorMessage));
            valid = false;
          }
        }
      }                  
      if (bookingGradeApplicantDateUserEntity.getWorkedEndTime() == null)
      {
        // Worked End Time NOT set.
        errorMessage = messageResources.getMessage("error.nhsBackingReport.workedEndTimeNotEntered", bookingGradeApplicantDateUserEntity.getBookingId().toString());
        backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
        valid = false;
      }
      else
      {
        if (!backingReportLine.getActualShift().getEndTime().equals(bookingGradeApplicantDateUserEntity.getWorkedEndTime()))
        {
          if (backingReportLine.getBackingReport().getAgencyHasSubcontractors())
          {
            // 4SW. Set Actual Shift End Time to that of the Booking.
            backingReportLine.getActualShift().setEndTime(bookingGradeApplicantDateUserEntity.getWorkedEndTime());
            backingReportLine.getActualShift().setEndTimeChanged(true);
          }
          else
          {
            // Actual End Time on Backing Report is NOT the same as the End Time on the Booking.
            errorMessage = messageResources.getMessage("error.nhsBackingReport.actualEndTimeChanged", timeFormat.format(bookingGradeApplicantDateUserEntity.getWorkedEndTime()));
            backingReportLine.setActualEndTimeInvalid(new BackingReportLineError(bookingGradeApplicantUser.getBookingGradeApplicantId(), errorMessage));
            valid = false;
          }
        }
      }
    }
    return valid;
  }
  // This validation is ONLY necessary when a NHS Booking record does not exist.
  private void validateHospitalWardAndAssignment(AgyService agyService, MessageResources messageResources, BackingReportLine backingReportLine, BookingGradeApplicantUser bookingGradeApplicantUser, Applicant applicant)
  {
    String errorMessage = null;
    Site site = agyService.getSite(bookingGradeApplicantUser.getSiteId());
    // This is a bit of a frig. Use the first line of the address if the NHS Location is not set. Historical mistake in data.
    String nhsLocation = site.getNhsLocation() == null ? site.getAddress().getAddress1() : site.getNhsLocation();
    if (nhsLocation.equals(backingReportLine.getHospital()))
    {
      Location location = agyService.getLocation(bookingGradeApplicantUser.getLocationId());
      String nhsWard = location.getNhsWard() == null ? location.getName() : location.getNhsWard();
      if (nhsWard.equals(backingReportLine.getWard()))
      {
        JobProfile jobProfile = agyService.getJobProfileUser(bookingGradeApplicantUser.getJobProfileId());
        String nhsAssignment = jobProfile.getNhsAssignment();
        if (nhsAssignment.equals(backingReportLine.getAssignment()))
        {
          // All looks good...
        }
        else
        {
          errorMessage = messageResources.getMessage("error.nhsBackingReport.nhsAssignmentOnBookingNotMatchedAssignmentOnBackingReport", nhsAssignment);
          backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
        }
      }
      else
      {
        errorMessage = messageResources.getMessage("error.nhsBackingReport.nhsWardOnBookingNotMatchedWardOnBackingReport", nhsWard);
        backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
      }
    }
    else
    {
      errorMessage = messageResources.getMessage("error.nhsBackingReport.nhsLocationOnBookingNotMatchedSiteOnBackingReport", nhsLocation);
      backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
    }
  }
  
  // Validates a single completed Booking against the Backing Report.
  private void validateApplicantAndBooking(AgyService agyService, MessageResources messageResources, BackingReportLine backingReportLine, BookingGradeApplicantUser bookingGradeApplicantUser, Applicant applicant)
  {
    String errorMessage = null;
    BookingGradeApplicantUserEntity bookingGradeApplicantUserEntity = null;
    BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity = null;
    if (applicant.getApplicantId().equals(bookingGradeApplicantUser.getApplicantId()))
    {
      // Applicant matched on BookingGradeApplicant...
      bookingGradeApplicantUserEntity = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicantUser.getBookingGradeApplicantId());
      if (bookingGradeApplicantUserEntity.getBookingGradeApplicantDateUserEntities().size() == 1)
      {
        // Booking is ONLY one day. Cool...
        bookingGradeApplicantDateUserEntity = bookingGradeApplicantUserEntity.getBookingGradeApplicantDateUserEntities().get(0);
        validateBackingReportLineAgainstBooking(agyService, messageResources, backingReportLine, bookingGradeApplicantUser, bookingGradeApplicantDateUserEntity);      
      }
      else
      {
        errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingForMoreThanOneDay", bookingGradeApplicantUser.getBookingId(), bookingGradeApplicantUserEntity.getBookingGradeApplicantDateUserEntities().size());
        backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
      }
    }
    else
    {
      errorMessage = messageResources.getMessage("error.nhsBackingReport.bookingNotForApplicant", bookingGradeApplicantUser.getBookingId(), backingReportLine.getAgencyWorkerName());
      backingReportLine.setBookingInvalid(new BackingReportLineError(errorMessage));
    }
  }
  
  // NOTE. This is NOT validation, it sets no error messages. It just checks if the booking matches the backing report.
  private Boolean checkBookingMatchesBackingReport(AgyService agyService, MessageResources messageResources, BackingReportLine backingReportLine, BookingGradeApplicantUser bookingGradeApplicantUser, Applicant applicant)
  {
    Boolean matched = false;
    BookingGradeApplicantUserEntity bookingGradeApplicantUserEntity = null;
    BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity = null;
    if (applicant.getApplicantId().equals(bookingGradeApplicantUser.getApplicantId()))
    {
      // Applicant matched on BookingGradeApplicant...
      bookingGradeApplicantUserEntity = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicantUser.getBookingGradeApplicantId());
      if (bookingGradeApplicantUserEntity.getBookingGradeApplicantDateUserEntities().size() == 1)
      {
        // Booking is ONLY one day. Cool...
        bookingGradeApplicantDateUserEntity = bookingGradeApplicantUserEntity.getBookingGradeApplicantDateUserEntities().get(0);
        if (backingReportLine.getDate().equals(bookingGradeApplicantDateUserEntity.getBookingDate()))
        {
          // Booking is for the right date.
          backingReportLine.setBookingDateId(bookingGradeApplicantDateUserEntity.getBookingDateId());
          if (backingReportLine.getContractShift().getStartTime().equals(bookingGradeApplicantDateUserEntity.getShiftStartTime()) &&
              backingReportLine.getContractShift().getEndTime().equals(bookingGradeApplicantDateUserEntity.getShiftEndTime()))
          {
            // Shift start and end times match.
            if (bookingGradeApplicantDateUserEntity.getWorkedStartTime() != null &&
                bookingGradeApplicantDateUserEntity.getWorkedEndTime()   != null &&
                backingReportLine.getActualShift().getStartTime().equals(bookingGradeApplicantDateUserEntity.getWorkedStartTime()) &&
                backingReportLine.getActualShift().getEndTime().equals(bookingGradeApplicantDateUserEntity.getWorkedEndTime()))
            {
              // Actual/Worked Start and end times match.
              Site site = agyService.getSite(bookingGradeApplicantUser.getSiteId());
              // This is a bit of a frig. Use the first line of the address if the NHS Location is not set. Historical mistake in data.
              String nhsLocation = site.getNhsLocation() == null ? site.getAddress().getAddress1() : site.getNhsLocation();
              if (nhsLocation.equals(backingReportLine.getHospital()))
              {
                // NHS Location matches Hospital.
                Location location = agyService.getLocation(bookingGradeApplicantUser.getLocationId());
                String nhsWard = location.getNhsWard() == null ? location.getName() : location.getNhsWard();
                if (nhsWard.equals(backingReportLine.getWard()))
                {
                  // NHS Ward matches Ward.
                  JobProfile jobProfile = agyService.getJobProfileUser(bookingGradeApplicantUser.getJobProfileId());
                  String nhsAssignment = jobProfile.getNhsAssignment();
                  if (nhsAssignment.equals(backingReportLine.getAssignment()))
                  {
                    // NHS Assignment matches Assignment. All looks good...
                    matched = true;
                  }
                }
              }
            }
          }
        }
      }
    }
    return matched;
  }
  
  private String validateOnlyOneHospital(MessageResources messageResources, BackingReport backingReport)
    throws Exception
  {
    String nhsHospital = null;
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      // For each Backing Report Line...
      if (nhsHospital == null)
      {
        nhsHospital = backingReportLine.getHospital();
      }
      if (!nhsHospital.equals(backingReportLine.getHospital()))
      {
        // ONLY ONE Hospital allowed on Backing Report.
        throw new Exception(messageResources.getMessage("error.nhsBackingReport.onlyOneHospitalAllowed"));
      }
    }
    return nhsHospital;
  }

  private void setHosptalAndWardText(BackingReport backingReport)
  {
    List<String> hospitalList = new ArrayList<String>();
    List<String> wardList = new ArrayList<String>();
    HashMap<String, Integer> hospitalMap = new HashMap<String, Integer>();
    HashMap<String, Integer> wardMap = new HashMap<String, Integer>();
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      // For each Backing Report Line...
      if (hospitalMap.containsKey(backingReportLine.getHospital()))
      {
        Integer count = hospitalMap.get(backingReportLine.getHospital());
        ++count;
        hospitalMap.put(backingReportLine.getHospital(), count);
      }
      else
      {
        hospitalMap.put(backingReportLine.getHospital(), new Integer(1));
        hospitalList.add(backingReportLine.getHospital());
      }
      if (wardMap.containsKey(backingReportLine.getWard()))
      {
        Integer count = wardMap.get(backingReportLine.getWard());
        ++count;
        wardMap.put(backingReportLine.getWard(), count);
      }
      else
      {
        wardMap.put(backingReportLine.getWard(), new Integer(1));
        wardList.add(backingReportLine.getWard());
      }
    }
    if (hospitalList.size() == 1)
    {
      // Only ONE Hospital.
      backingReport.setHospital(hospitalList.get(0));
      if (wardList.size() == 1)
      {
        // Only ONE Ward.
        backingReport.setWard(wardList.get(0));
      }
      else
      {
        if (wardList.size() == 2)
        {
          // Only TWO Wards.
          backingReport.setWard(wardList.get(0) + " and " + wardList.get(1));
        }
        else
        {
          // THREE or more Wards.
          backingReport.setWard(wardList.get(0) + " and " + wardList.size() + " more wards.");
        }
      }
    }
    else
    {
      // More than ONE Hospital. Don't bother to set Ward.
      if (hospitalList.size() == 2)
      {
        // Only TWO Hospitals.
        backingReport.setHospital(hospitalList.get(0) + " and " + hospitalList.get(1));
      }
      else
      {
        // THREE or more Hospitals.
        backingReport.setHospital(hospitalList.get(0) + " and " + hospitalList.size() + " more hospitals.");
      }
    }
    hospitalMap.clear();
    wardMap.clear();
  }
  
  private String getHospitalsText(BackingReport backingReport)
  {
    HashMap<String, Integer> hospitalMap = new HashMap<String, Integer>();
    StringBuffer nhsHospitals = new StringBuffer();
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      // For each Backing Report Line...
      if (hospitalMap.containsKey(backingReportLine.getHospital()))
      {
        Integer count = hospitalMap.get(backingReportLine.getHospital());
        ++count;
        hospitalMap.put(backingReportLine.getHospital(), count);
      }
      else
      {
        hospitalMap.put(backingReportLine.getHospital(), new Integer(1));
        if (nhsHospitals.length() > 0)
        {
          nhsHospitals.append(", ");
        }
        nhsHospitals.append(backingReportLine.getHospital());
     }
    }
    hospitalMap.clear();
    return nhsHospitals.toString();
  }

  private String getWardsText(BackingReport backingReport)
  {
    HashMap<String, Integer> wardMap = new HashMap<String, Integer>();
    StringBuffer nhsWards = new StringBuffer();
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      // For each Backing Report Line...
      if (wardMap.containsKey(backingReportLine.getWard()))
      {
        Integer count = wardMap.get(backingReportLine.getWard());
        ++count;
        wardMap.put(backingReportLine.getWard(), count);
      }
      else
      {
        wardMap.put(backingReportLine.getWard(), new Integer(1));
        if (nhsWards.length() > 0)
        {
          nhsWards.append(", ");
        }
        nhsWards.append(backingReportLine.getWard());
     }
    }
    wardMap.clear();
    return nhsWards.toString();
  }

  // No longer used as some Backing REports were for more than one ward.
  private String validateOnlyOneWard(MessageResources messageResources, BackingReport backingReport)
    throws Exception
  {
    String nhsWard = null;
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      // For each Backing Report Line...
      if (nhsWard == null)
      {
        nhsWard = backingReportLine.getWard();
      }
      if (!nhsWard.equals(backingReportLine.getWard()))
      {
        // ONLY ONE Ward allowed on Backing Report.
        throw new Exception(messageResources.getMessage("error.nhsBackingReport.onlyOneWardAllowed"));
      }
    }
    return nhsWard;
  }

  private void validateMoneyTotals(MessageResources messageResources, BackingReport backingReport)
    throws Exception
  {
    BigDecimal totalCost = new BigDecimal(0);
    BigDecimal totalCommission = new BigDecimal(0);
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      // For each Backing Report Line...
      totalCommission = totalCommission.add(backingReportLine.getCommission());
      totalCost = totalCost.add(backingReportLine.getTotalCost());
    }
    if (backingReport.getTotalCommission().compareTo(totalCommission) != 0)
    {
      // Accumulated Total Commission does NOT equal Total Commission on Backing Report.
      throw new Exception(messageResources.getMessage("error.nhsBackingReport.accumulatedTotalCommissionNotCorrect", totalCommission, backingReport.getTotalCommission()));
    }
    if (backingReport.getTotalCost().compareTo(totalCost) != 0)
    {
      // Accumulated Grand Total Cost does NOT equal Grand Total Cost on Backing Report.
      throw new Exception(messageResources.getMessage("error.nhsBackingReport.accumulatedTotalCostNotCorrect", totalCost, backingReport.getTotalCost()));
    }
  }

  // Duplicate of method in NhsBackingReportCommon...
  private List<BookingDateUserApplicant> getBookingDateUserApplicantListForBackingReportName(AgyService agyService, Date startDateToSearch, Date endDateToSearch, String nhsBackingReportName)
  {
    Integer bookingGradeStatus = null;
    Integer bookingDateStatus = null;
    Integer workedStatus = BookingGrade.BOOKINGGRADE_STATUS_CLOSED;
    List<BookingDateUserApplicant> listBookingDateUserApplicant = agyService.getBookingDateUserApplicantsForAgencyBackingReport(getConsultantLoggedIn().getAgencyId(), null, null, null, null, bookingGradeStatus,
        bookingDateStatus, workedStatus, null, null, null, null, null, null, null, null, startDateToSearch, endDateToSearch, nhsBackingReportName);
    return listBookingDateUserApplicant;
  }
  
  private void validateNhsBackingReport(NhsBackingReport nhsBackingReport, BackingReport backingReport, ActionMessages errors)
  {
    DateFormat dateFormat = new SimpleDateFormat(getDateFormat());
    if (!nhsBackingReport.getAgencyId().equals(getConsultantLoggedIn().getAgencyId()))
    {
      errors.add("nhsBackingReportUpload", new ActionMessage("error.nhsBackingReport.notForThisAgency"));
    }
    if (!nhsBackingReport.getClientId().equals(backingReport.getClientId()))
    {
      errors.add("nhsBackingReportUpload", new ActionMessage("error.nhsBackingReport.notForClient", backingReport.getTrust()));
    }
    if (nhsBackingReport.getAgreedValue().compareTo(backingReport.getGrandTotalCost()) != 0)
    {
      errors.add("nhsBackingReportUpload", new ActionMessage("error.nhsBackingReportExists.agreedValueDifferent", nhsBackingReport.getAgreedValue(), backingReport.getGrandTotalCost()));
    }
    if (!nhsBackingReport.getStartDate().equals(backingReport.getStartDate()))
    {
      errors.add("nhsBackingReportUpload", new ActionMessage("error.nhsBackingReportExists.startDateDifferent", dateFormat.format(nhsBackingReport.getStartDate()), dateFormat.format(backingReport.getStartDate())));
    }
    if (!nhsBackingReport.getEndDate().equals(backingReport.getEndDate()))
    {
      errors.add("nhsBackingReportUpload", new ActionMessage("error.nhsBackingReportExists.endDateDifferent", dateFormat.format(nhsBackingReport.getEndDate()), dateFormat.format(backingReport.getEndDate())));
    }
  }
  
  private String getMultipleBookingIds(List<Booking> bookingList, Boolean completedRequired)
  {
    StringBuffer bookingIds = new StringBuffer();
    for (Booking booking : bookingList)
    {
      if (completedRequired)
      {
        if (booking.getStatus().equals(booking.BOOKING_STATUS_COMPLETED))
        {
          if (bookingIds.length() > 0)
          {
            bookingIds.append(", ");
          }
          bookingIds.append(booking.getBookingId());
        }
      }
      else
      {
        if (bookingIds.length() > 0)
        {
          bookingIds.append(", ");
        }
        bookingIds.append(booking.getBookingId());
      }
    }
    return bookingIds.toString();
  }
  
}
