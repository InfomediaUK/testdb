package com.helmet.application.agy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.util.MessageResources;

import com.helmet.api.AdminService;
import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.ApplicantClientBooking;
import com.helmet.bean.BookingUser;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.ClientUser;
import com.helmet.bean.IdDocument;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class AgencyWorkerChecklist extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  private Font TD_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new BaseColor(0, 0, 0));
  private Font TD_FONT_WARNING = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new BaseColor(255, 0, 0));
  private Font TD_FONTBOLD = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));
  private Font TD_FONTBIGBOLD = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  protected boolean doIt(MessageResources messageResources, 
                      Agency agency, 
                      Applicant applicant,
                      ClientUser client,
                      BookingUser bookingUser, 
                      ApplicantClientBooking applicantClientBooking, 
                      BookingGradeAgyEntity bookingGradeAgyEntity, 
                      BookingGradeApplicantUserEntity bookingGradeApplicant,
                      Date checklistCreatedTime,
                      String fileName) 
  throws Exception 
  {
    logger.debug("AgencyWorkerChecklist - doIt() starts...");
    boolean result = false;
    SimpleDateFormat mdf = new SimpleDateFormat(messageResources.getMessage("format.mediumDateFormat"));
    SimpleDateFormat ldf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
    SimpleDateFormat ldtf = new SimpleDateFormat(messageResources.getMessage("format.longDateTimeFormat"));
    Document document = new Document(PageSize.A4, 35, 35, 25, 20);
    if (bookingUser == null || backUpExistingFile(fileName, applicant.getApplicantId(), bookingUser.getBookingId()))
    {
      // BookingGradeApplicant Checklist and backed up ok.
      PdfWriter.getInstance(document, new FileOutputStream(fileName));
      if (document != null)
      {
        logger.debug("AgencyWorkerChecklist - document is not null.");
        try
        {
          document.open();
          document.add(createHeaderTable(agency, applicant, client));
          document.add(createTitle(bookingUser, checklistCreatedTime, messageResources, ldtf));
          document.add(createFirstTableRows1And2(bookingUser, bookingGradeApplicant, messageResources));
          document.add(createFirstTableRow3(applicant, bookingGradeAgyEntity, bookingGradeApplicant, messageResources));
          document.add(createFirstTableRow4And5(bookingGradeAgyEntity, bookingGradeApplicant, messageResources, ldf));
          document.add(createSecondTable(bookingUser, bookingGradeAgyEntity, bookingGradeApplicant, messageResources));
          Paragraph paragraph = new Paragraph(messageResources.getMessage("label.applicantChecklist.part2"), TD_FONTBOLD);
          paragraph.setIndentationLeft(26);
          document.add(paragraph);
          document.add(createThirdTableRow1(applicant, messageResources));
          document.add(createThirdTableRow2(applicant, applicantClientBooking, bookingGradeAgyEntity, messageResources, mdf));
          document.add(createThirdTableRow3And4(applicant, messageResources, mdf));
          document.add(createFourthTable(applicant, messageResources, mdf));
          document.add(createFifthTable(applicant, messageResources, mdf));
          document.add(createSixthTable(applicant, messageResources, mdf));
          document.add(createSeventhTable(applicant, messageResources, mdf));
          document.add(createEighthTable(bookingGradeAgyEntity, applicant, client, messageResources, mdf));
          paragraph = new Paragraph(messageResources.getMessage("label.applicantChecklist.submittedAgencyWorker"), TD_FONT);
          paragraph.setIndentationLeft(26);
          paragraph.setIndentationRight(26);
          paragraph.setLeading(8);
          document.add(paragraph);
          document.add(createNinthTable(messageResources, mdf));
          result = true;
          logger.debug("AgencyWorkerChecklist - document completed.");
        }
        finally
        {
          document.close();
          logger.debug("AgencyWorkerChecklist - document closed.");
        }
      }      
    }
    else
    {
      logger.debug("AgencyWorkerChecklist - Checklist Not created.");
    }
    return result;
  }
  
  private boolean backUpExistingFile(String fileName, Integer applicantId, Integer bookingId)
  {
    logger.debug("AgencyWorkerChecklist.backUpExistingFile - Starts. fileName=[" + fileName + "]  applicantId=[" + applicantId + "] bookingId=[" + bookingId + "]");
    boolean result = false;
    File checklistFile = new File(fileName);
    if (checklistFile.exists())
    {
      // File exists, back it up.
      logger.debug("AgencyWorkerChecklist.backUpExistingFile - File exists...");
      File backupFile = null;
      AgencyWorkerChecklistFile agencyWorkerChecklistFile = null;
      File folder = new File(fileName.substring(0, fileName.lastIndexOf("/")));
      // Get all Agency Worker Checklists for this Applicant for this Booking included deleted ones.
      AgencyWorkerChecklistFileListFilter filter = new AgencyWorkerChecklistFileListFilter(applicantId, bookingId, true);
      File[] files = folder.listFiles(filter);
      if (files != null)
      {
        int greatestBackupNumber = 0;
        for (File file : files)
        {
          agencyWorkerChecklistFile = new AgencyWorkerChecklistFile(file.getName(), file.lastModified());
          if (agencyWorkerChecklistFile.getBackupNumber() > greatestBackupNumber)
          {
            greatestBackupNumber = agencyWorkerChecklistFile.getBackupNumber();
          }
        }
        DecimalFormat df3 = new DecimalFormat("#000");
        String backupFileUrl = fileName.replace("000.pdf", df3.format(++greatestBackupNumber) + ".bak.pdf");
        backupFile = new File(backupFileUrl);
      }
      result = checklistFile.renameTo(backupFile);
    }
    else
    {
      // File does NOT exist. No need to back it up.
      result = true;
    }
    logger.debug("AgencyWorkerChecklist.backUpExistingFile - Ends result=[" + result + "]");
    return result;
  }
  /**
   * Creates header table.
   * 
   * @return header table
   */
  private PdfPTable createHeaderTable(Agency agency, Applicant applicant, ClientUser client)
  {
    logger.debug("AgencyWorkerChecklist.createHeaderTable - Starts...");
    // a table with three columns
    float[] columnWidths = {20, 30, 50}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(90);
    Image agencyInvoiceLogo = null;
    Image applicantPhoto = null;
    Image clientLogo = null;
    Integer clientLogoWidth = null;
    Integer clientLogoHeight = null;
    try
    {
      String fileName = FileHandler.getInstance().getFileLocation() + agency.getInvoiceLogoUrl();
      File agencyInvoiceLogoFile = new File(fileName);
      if (agencyInvoiceLogoFile.exists())
      {
        agencyInvoiceLogo = Image.getInstance(fileName);
      }
      fileName = FileHandler.getInstance().getFileLocation() + applicant.getPhotoDocumentUrl();
      File applicantPhotoFile = new File(fileName);
      if (applicantPhotoFile.exists())
      {
        applicantPhoto    = Image.getInstance(fileName);
      }
      if (client != null)
      {
        fileName = FileHandler.getInstance().getFileLocation() + client.getLogoUrl();
        File clientLogoFile = new File(fileName);
        if (clientLogoFile.exists())
        {
          clientLogo = Image.getInstance(fileName);
          clientLogoWidth = client.getLogoWidth();
          clientLogoHeight = client.getLogoHeight();
        }
      }      
    }
    catch (MalformedURLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (BadElementException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   // Applicant Photo Cell.
    PdfPCell photoCell = new PdfPCell(new Paragraph("Applicant Photo"));
    if (applicantPhoto != null)
    {
      applicantPhoto.scaleAbsoluteHeight(120);
      applicantPhoto.scaleAbsoluteWidth(applicantPhoto.getWidth() * (applicantPhoto.getScaledHeight() / applicantPhoto.getHeight()));
      photoCell = new PdfPCell(applicantPhoto);
    }
    photoCell.setVerticalAlignment(Element.ALIGN_TOP);
    photoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
    photoCell.setBorder(Rectangle.NO_BORDER);
    table.addCell(photoCell);
    // Agency Logo Cell.
    PdfPCell agencyLogoCell = new PdfPCell(new Paragraph("MMJ Logo"));
    if (agencyInvoiceLogo != null)
    {
//       scale to be 40 pixels high
//       Float imageHeight = 40F;
//       mmjLogo.scaleAbsoluteHeight(imageHeight);
//       mmjLogo.scaleAbsoluteWidth(mmjLogo.getWidth() * (mmjLogo.getScaledHeight() /
//       mmjLogo.getHeight()));
      // scale to be 100%
      // mmjLogo.scalePercent(100);
      agencyInvoiceLogo.scaleAbsoluteHeight(40);
      agencyInvoiceLogo.scaleAbsoluteWidth(agencyInvoiceLogo.getWidth() * (agencyInvoiceLogo.getScaledHeight() / agencyInvoiceLogo.getHeight()));
      agencyLogoCell = new PdfPCell(agencyInvoiceLogo);
    }
    agencyLogoCell.setVerticalAlignment(Element.ALIGN_TOP);
    agencyLogoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    agencyLogoCell.setBorder(Rectangle.NO_BORDER);
    table.addCell(agencyLogoCell);
    // Client Logo Cell.
    PdfPCell clientLogoCell = new PdfPCell(new Paragraph("Client Logo"));
    if (clientLogo != null)
    {
      clientLogo.scaleAbsoluteWidth(250);
      clientLogo.scaleAbsoluteHeight(clientLogoHeight * (clientLogo.getScaledWidth() / clientLogoWidth));
      clientLogoCell = new PdfPCell(clientLogo);
    }
    clientLogoCell.setVerticalAlignment(Element.ALIGN_TOP);
    clientLogoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    clientLogoCell.setBorder(Rectangle.NO_BORDER);
    table.addCell(clientLogoCell);
    logger.debug("AgencyWorkerChecklist.createHeaderTable - Ends.");
    return table;
  }
  /**
   * Creates title.
   * 
   * @return first title
   */
  private Paragraph createTitle(BookingUser bookingUser, Date checklistCreatedTime, MessageResources messageResources, SimpleDateFormat ldtf)
  {
    logger.debug("AgencyWorkerChecklist.createTitle - Starts...");
    // Agency Worker Placement Checklist
    StringBuffer title = new StringBuffer(messageResources.getMessage("label.applicantChecklist.agencyWorkerPlacementChecklist"));
    if (bookingUser != null)
    {
      // Booking No.
      title.append(" - ");
      title.append(messageResources.getMessage("label.applicantChecklist.bookingNo"));
      title.append(bookingUser.getBookingId());
    }
    title.append(" - ");
    title.append(ldtf.format(checklistCreatedTime));
    Paragraph paragraph = new Paragraph(title.toString(), TD_FONTBIGBOLD);
    paragraph.setIndentationLeft(26);
    logger.debug("AgencyWorkerChecklist.createTitle - Ends...");
    return paragraph;
  }
  /**
   * Creates first table rows 1 and 2.
   * 
   * @return first table rows 1 and 2
   */
  private PdfPTable createFirstTableRows1And2(BookingUser bookingUser, BookingGradeApplicantUserEntity bookingGradeApplicant, MessageResources messageResources)
  {
    logger.debug("AgencyWorkerChecklist.createFirstTableRows1And2 - Starts...");
    float[] columnWidths = {20, 40, 20, 20}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(90);
    table.setSpacingBefore(10);
    PdfPCell cell;
    // First Row. Authority Name (Location)
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.authorityName"), TD_FONTBOLD));
    cell.setFixedHeight(40);
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      StringBuffer authorityNameText = new StringBuffer(bookingGradeApplicant.getClientName());
      authorityNameText.append(", ");
      authorityNameText.append(bookingGradeApplicant.getLocationName());
      authorityNameText.append(", ");
      authorityNameText.append(bookingGradeApplicant.getSiteName());
      if (StringUtils.isNotEmpty(bookingGradeApplicant.getLocationDescription()))
      {
        authorityNameText.append(" (");
        authorityNameText.append(bookingGradeApplicant.getLocationDescription());
        authorityNameText.append(")");
      }
      cell = new PdfPCell(new Paragraph(authorityNameText.toString(), TD_FONT));
    }
    table.addCell(cell);
    // Authority Reference No. (if provided)
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.authorityReferenceNo"), TD_FONTBOLD));
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(bookingGradeApplicant.getClientCode(), TD_FONT));
    }
    table.addCell(cell);
    // Second Row. Reason for Booking (if provided)
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.reasonForBooking"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (bookingUser == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      String reasonForBookingText = bookingUser.getReasonForRequestName();
      cell = new PdfPCell(new Paragraph(reasonForBookingText, TD_FONT));
    }
    cell.setColspan(3);
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createFirstTableRows1And2 - Ends...");
    return table;
  }
  /**
   * Creates first table row 3.
   * 
   * @return first table row 3
   */
  private PdfPTable createFirstTableRow3(Applicant applicant, BookingGradeAgyEntity bookingGradeAgyEntity, BookingGradeApplicantUserEntity bookingGradeApplicant, MessageResources messageResources)
  {
    logger.debug("AgencyWorkerChecklist.createFirstTableRow3 - Starts...");
    // Third Row.
    float[] row3ColumnWidths = {20, 20, 15, 25, 8, 12}; // percentage
    PdfPTable table = new PdfPTable(row3ColumnWidths);
    table.setWidthPercentage(90);
    // AFC Job Title
    PdfPCell cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.afcJobTitle"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      StringBuffer afcJobTitleText = new StringBuffer(bookingGradeApplicant.getJobProfileName());
      if (StringUtils.isNotEmpty(bookingGradeAgyEntity.getJobFamilyCode())
          && StringUtils.isNotEmpty(bookingGradeAgyEntity.getJobSubFamilyCode())
          && StringUtils.isNotEmpty(bookingGradeAgyEntity.getJobProfileCode()))
      {
        afcJobTitleText.append(" (");
        afcJobTitleText.append(bookingGradeAgyEntity.getJobFamilyCode());
        afcJobTitleText.append("."); 
        afcJobTitleText.append(bookingGradeAgyEntity.getJobSubFamilyCode());
        afcJobTitleText.append("."); 
        afcJobTitleText.append(bookingGradeAgyEntity.getJobProfileCode());
        afcJobTitleText.append(")");
      }
      cell = new PdfPCell(new Paragraph(afcJobTitleText.toString(), TD_FONT));
    }
    table.addCell(cell);
    // Band/Spine Point
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.bandSpinePoint"), TD_FONTBOLD));
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(bookingGradeApplicant.getGradeName(), TD_FONT));
    }
    table.addCell(cell);
    // EPP?
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.epp"), TD_FONTBOLD));
    table.addCell(cell);
    if (StringUtils.isEmpty(applicant.getIvsEppFilename()))
    {
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.no"), TD_FONT));
    }
    else
    {
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.yes"), TD_FONT));
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createFirstTableRow3 - Ends...");
    return table;
  }

  /**
   * Creates first table row 4 and 5
   * 
   * @return first table
   */
  private PdfPTable createFirstTableRow4And5(BookingGradeAgyEntity bookingGradeAgyEntity, BookingGradeApplicantUserEntity bookingGradeApplicant, MessageResources messageResources, SimpleDateFormat ldf)
  {
    logger.debug("AgencyWorkerChecklist.createFirstTableRow4And5 - Starts...");
    DecimalFormat df = new DecimalFormat("#,##0.00");
    // Fourth Row.
    float[] row4And5ColumnWidths = {20, 35, 15, 20, 10}; // percentage
    PdfPTable table = new PdfPTable(row4And5ColumnWidths);
    table.setWidthPercentage(90);
    table.setSpacingAfter(10);
    // Placement Date From
    PdfPCell cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.placementDateFrom"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(ldf.format(bookingGradeApplicant.getMinBookingDate()), TD_FONT));
    }
    table.addCell(cell);
    // Placement Date To
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.placementDateTo"), TD_FONTBOLD));
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(ldf.format(bookingGradeApplicant.getMaxBookingDate()), TD_FONT));
    }
    cell.setColspan(2);
    table.addCell(cell);
    // Fifth Row.
    // Proposed Working Pattern
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.proposedWorkingPattern"), TD_FONTBOLD));
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      StringBuffer proposedWorkingPatternText = new StringBuffer(messageResources.getMessage("label.days")); 
      String shiftName = bookingGradeApplicant.getShiftName();
      proposedWorkingPatternText.append(" : ");
      proposedWorkingPatternText.append(bookingGradeAgyEntity.getNoOfBookingDates());
      proposedWorkingPatternText.append(" - ");
      proposedWorkingPatternText.append(messageResources.getMessage("label.shift"));
      proposedWorkingPatternText.append(" : ");
      proposedWorkingPatternText.append((shiftName == null ? messageResources.getMessage("label.varied") : shiftName));
      cell = new PdfPCell(new Paragraph(proposedWorkingPatternText.toString(), TD_FONT));
    }
    cell.setFixedHeight(20);
    cell.setColspan(2);
    table.addCell(cell);
    // Total Number of Hours Booked
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.totalNumberOfHoursBooked"), TD_FONTBOLD));
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(df.format(bookingGradeApplicant.getNoOfHours()), TD_FONT));
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createFirstTableRow4And5 - Ends...");
    return table;
  }

  /**
   * Creates second table
   * 
   * @return second table
   */
  private PdfPTable createSecondTable(BookingUser bookingUser, BookingGradeAgyEntity bookingGradeAgyEntity, BookingGradeApplicantUserEntity bookingGradeApplicant, MessageResources messageResources)
  {
    logger.debug("AgencyWorkerChecklist.createSecondTable - Starts...");
    DecimalFormat df = new DecimalFormat("#,##0.00");
    // a table with two columns
    float[] columnWidths = {20, 80}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(90);
    //table.setSpacingAfter(10);
    PdfPCell cell;
    // Hourly Charge Incl VAT
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.hourlyCharge"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      StringBuffer hourlyChargeText = new StringBuffer(messageResources.getMessage("label.currencySymbolActual"));
      hourlyChargeText.append(df.format(bookingGradeApplicant.getChargeRate()));
      hourlyChargeText.append(" (");
      // Including VAT
      hourlyChargeText.append(messageResources.getMessage("label.applicantChecklist.excludingVat"));
      hourlyChargeText.append(" ");
//      hourlyChargeText.append(df.format(bookingGradeApplicant.getChargeRateVatRate()));
      hourlyChargeText.append(")");
      cell = new PdfPCell(new Paragraph(hourlyChargeText.toString(), TD_FONT));
    }
    table.addCell(cell);
    // Travel and/or Other Disembursments
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.travelAndOrOtherDisembursments"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (bookingGradeApplicant == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      StringBuffer expenses = new StringBuffer();
      if (bookingGradeApplicant.getBookingExpensesCount().getValue() == 0)
      {
        expenses.append(messageResources.getMessage("label.none"));
      }
      else
      {
        for (BookingExpense bookingExpense : bookingGradeAgyEntity.getBookingExpenses())
        {
          if (StringUtils.isNotEmpty(bookingExpense.getExpenseDescription()))
          {
            if (expenses.length() > 0)
            {
              expenses.append(", ");
            }
            expenses.append(bookingExpense.getExpenseDescription());
          }          
        }
      }
      cell = new PdfPCell(new Paragraph(expenses.toString(), TD_FONT));
    }
    table.addCell(cell);
    // Accomodation Required
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.accomodationRequired"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (bookingUser == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      if (bookingUser.getCanProvideAccommodation())
      {
        cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.yes"), TD_FONT));
      }
      else
      {
        cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.no"), TD_FONT));
      }
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createSecondTable - Ends...");
    return table;
  }
  
  /**
   * Creates third table row 1.
   * 
   * @return third table row 1
   */
  private PdfPTable createThirdTableRow1(Applicant applicant, MessageResources messageResources)
  {
    logger.debug("AgencyWorkerChecklist.createThirdTableRow1 - Starts...");
    // Third Row.
    float[] row3ColumnWidths = {25, 45, 20, 10}; // percentage
    PdfPTable table = new PdfPTable(row3ColumnWidths);
    table.setSpacingBefore(5);
    table.setWidthPercentage(90);
    PdfPCell cell;
    // Agency Worker's Full Name
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.agencyWorkersFullName"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph(applicant.getFullName(), TD_FONT));
    table.addCell(cell);
    // Full CV Attached
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.fullCvAttached"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (StringUtils.isEmpty(applicant.getCvFilename()))
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.available"), TD_FONT));
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createThirdTableRow1 - Ends...");
    return table;
  }
  
  /**
   * Creates third table row 2.
   * 
   * @return third table row 2
   */
  private PdfPTable createThirdTableRow2(Applicant applicant, ApplicantClientBooking applicantClientBooking, BookingGradeAgyEntity bookingGradeAgyEntity, MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.createThirdTableRow2 - Starts...");
    // Third Row.
    float[] columnWidths = {25, 20, 15, 10, 20, 10}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(90);
    PdfPCell cell;
    // Previously Worked at the Authority As Above?
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.previouslyWorkedAtTheAuthority"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (applicantClientBooking == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      // Booking
      StringBuffer previouslyWorkedText = new StringBuffer(messageResources.getMessage("label.applicantChecklist.booking"));
      previouslyWorkedText.append(" ");
      previouslyWorkedText.append(applicantClientBooking.getBookingId());
      previouslyWorkedText.append("\n");
      previouslyWorkedText.append(mdf.format(applicantClientBooking.getMinBookingDate()));
      previouslyWorkedText.append(" - ");
      previouslyWorkedText.append(mdf.format(applicantClientBooking.getMaxBookingDate()));
      cell = new PdfPCell(new Paragraph(previouslyWorkedText.toString(), TD_FONT));
    }
    table.addCell(cell);
    // Available For Full Placement Period?
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.availableForFullPlacementPeriod"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (bookingGradeAgyEntity == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      
      cell = new PdfPCell(new Paragraph(bookingGradeAgyEntity.getSingleCandidate() ? messageResources.getMessage("label.yes") : messageResources.getMessage("label.no"), TD_FONT));
    }
    table.addCell(cell);
    // Recent Photograph Attached
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.recentPhotographAttached"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (StringUtils.isEmpty(applicant.getPhotoFilename()))
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.seeAbove"), TD_FONT));
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createThirdTableRow2 - Ends...");
    return table;
  }
  
  /**
   * Creates third table row 3 and 4
   * 
   * @return third table row 3 and 4
   */
  private PdfPTable createThirdTableRow3And4(Applicant applicant, MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.createThirdTableRow3And4 - Starts...");
    String verifiedIdText = null;
    String nationalityAndImmigrationText = null;
    String idDocumentExpiryDate = null;
    String visaExpiryDate = null;
    // Third Row.
    float[] columnWidths = {25, 55, 10, 10}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setSpacingAfter(10);
    table.setWidthPercentage(90);
    PdfPCell cell;
    // Verified ID
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.verifiedId"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (StringUtils.isNotEmpty(applicant.getIdDocumentFilename()) && StringUtils.isNotEmpty(applicant.getIdDocumentNumber()))
    {
      // Both IdDocument Filename and IdDocument Number have been entered.
      verifiedIdText = messageResources.getMessage("label.applicantChecklist.idDocument");
      if (applicant.getIdDocument() != null && applicant.getIdDocument() > 0)
      {
        AdminService adminService = ServiceFactory.getInstance().getAdminService();
        IdDocument idDocumentType = adminService.getIdDocument(applicant.getIdDocument());
        idDocumentExpiryDate = applicant.getIdDocumentExpiryDate()== null ? messageResources.getMessage("label.applicantChecklist.notGiven") : mdf.format(applicant.getIdDocumentExpiryDate());
        verifiedIdText += " (" + idDocumentType.getName() + ") " + messageResources.getMessage("label.applicantChecklist.expires") + ": " + idDocumentExpiryDate;;
      }
      cell = new PdfPCell(new Paragraph(verifiedIdText, TD_FONT));
    }
    else
    {
      // Either of IdDocument Filename or IdDocument Number have NOT been entered. Blank Cell.
      cell = new PdfPCell();
    }
    table.addCell(cell);
    // Attached
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.attached"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (StringUtils.isEmpty(verifiedIdText))
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.available"), TD_FONT));
    }
    table.addCell(cell);
    // Fourth Row. Nationality and Immigration Status (Right to Work in UK)
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.nationalityAndImmigration"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (applicant.getRequiresVisa())
    {
      visaExpiryDate = applicant.getVisaExpiryDate()== null ? messageResources.getMessage("label.applicantChecklist.notGiven") : mdf.format(applicant.getVisaExpiryDate());
      String messageKey = "label.visaType" + applicant.getVisaType();
      nationalityAndImmigrationText = messageResources.getMessage("label.applicantChecklist.visa") + " (" + messageResources.getMessage(messageKey) + ") " + messageResources.getMessage("label.applicantChecklist.expires") + ": " + visaExpiryDate;
      cell = new PdfPCell(new Paragraph(nationalityAndImmigrationText, TD_FONT));
    }
    else
    {
      cell = new PdfPCell();
    }
    table.addCell(cell);
    // Attached
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.attached"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (StringUtils.isEmpty(nationalityAndImmigrationText))
    {
      table.addCell("");
    }
    else
    {
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.available"), TD_FONT));
      table.addCell(cell);
    }
    logger.debug("AgencyWorkerChecklist.createThirdTableRow3And4 - Ends...");
    return table;
  }
  
  /**
   * Creates fourth table.
   * 
   * @return fourth table
   */
  private PdfPTable createFourthTable(Applicant applicant, MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.createFourthTable - Starts...");
    String crbIssueDate = applicant.getCrbIssueDate() == null ? "" : mdf.format(applicant.getCrbIssueDate());
    String hpcLastCheckedDate = applicant.getHpcLastCheckedDate() == null ? "" : mdf.format(applicant.getHpcLastCheckedDate());
    // First Row.
    float[] columnWidths = {30, 20, 25, 25}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setSpacingAfter(10);
    table.setWidthPercentage(90);
    PdfPCell cell;
    // HPC/GDC/RPSGB Registration
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.professionalRegistration"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.ahpRegistrationType" + applicant.getAhpRegistrationType()), TD_FONT));
    table.addCell(cell);
    // Enhanced CRB disclosure No.
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.enhancedCrbDisclosureNo"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (StringUtils.isEmpty(applicant.getCrbDisclosureNumber()))
    {
      cell = new PdfPCell();
    }
    else
    {
      cell = new PdfPCell(new Paragraph(applicant.getCrbDisclosureNumber(), TD_FONT));
    }
    table.addCell(cell);
    // Second Row. HPC/GDC/RPSGB Registration No.
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.professionalRegistrationNo"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    cell = new PdfPCell(createMandatoryParagraph(applicant.getHpcNumber(), messageResources));
    table.addCell(cell);
    // Date of CRB Issue
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.dateOfCrbIssue"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    cell = new PdfPCell(createMandatoryParagraph(crbIssueDate, messageResources));
    table.addCell(cell);
    // Third Row. HPC/GDC/RPSGB Registration Last Checked
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.professionalRegistrationLastChecked"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    cell = new PdfPCell(createMandatoryParagraph(hpcLastCheckedDate, messageResources));
    table.addCell(cell);
    // Alert Notification?
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.alertNotification"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    String messageKey = "label.hpcAlertNotification" + applicant.getHpcAlertNotification();
    cell = new PdfPCell(new Paragraph(messageResources.getMessage(messageKey), TD_FONT));
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createFourthTable - Ends...");
    return table;
  }

  /**
   * Creates fifth table.
   * 
   * @return fifth table
   */
  private PdfPTable createFifthTable(Applicant applicant, MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.createFifthTable - Starts...");
    // First Row.
    float[] columnWidths = {24, 50, 13, 13}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setSpacingAfter(10);
    table.setWidthPercentage(90);
    PdfPCell cell;
    // Certificate of Fitness For Employment Issued By
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.certificateOfFitness"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (applicant.getFitToWorkIssuedBy() == null || applicant.getFitToWorkIssuedBy() == 0)
    {
      cell = new PdfPCell();
    }
    else
    {
      String messageKey = "label.fitToWorkIssuedBy" + applicant.getFitToWorkIssuedBy();
      cell = new PdfPCell(new Paragraph(messageResources.getMessage(messageKey), TD_FONT));
    }
    table.addCell(cell);
    // Certificate of Fitness For Employment Date of Issue
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.dateOfIssue"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (applicant.getFitToWorkExpiryDate() == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(applicant.getFitToWorkExpiryDate());
      calendar.add(Calendar.YEAR, -1);
      String fitToWorkIssuedDate = mdf.format(calendar.getTime());
      cell = new PdfPCell(new Paragraph(fitToWorkIssuedDate, TD_FONT));
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createFifthTable - Ends...");
    return table;
  }

  /**
   * Creates sixth table.
   * 
   * @return sixth table
   */
  private PdfPTable createSixthTable(Applicant applicant, MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.createSixthTable - Starts...");
    String trainingDateOfIssueText = null;
    // First Row.
    float[] columnWidths = {24, 50, 13, 13}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setSpacingAfter(10);
    table.setWidthPercentage(90);
    PdfPCell cell;
    // Life Support Training
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.lifeSupportTraining"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (applicant.getTrainingFilename() == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      StringBuffer training = new StringBuffer();
      if (applicant.getManualHandlingTraining())
      {
        training.append(messageResources.getMessage("label.manualHandlingTraining"));
        training.append(". ");
      }
      if (applicant.getBasicLifeSupportTraining())
      {
        training.append(messageResources.getMessage("label.basicLifeSupportTraining"));
        training.append(". ");
      }
      if (applicant.getNeonatalLifeSupportTraining())
      {
        training.append(messageResources.getMessage("label.neonatalLifeSupportTraining"));
        training.append(". ");
      }
      if (applicant.getElearningTraining())
      {
        training.append(messageResources.getMessage("label.elearningTraining"));
        training.append(". ");
      }
      if (applicant.getPovaTraining())
      {
        training.append(messageResources.getMessage("label.povaTraining"));
        training.append(". ");
      }
      if (applicant.getPaediatricLifeSupportFilename() != null)
      {
        training.append("\n");
        training.append(messageResources.getMessage("label.paediatricLifeSupport"));
        training.append(" ");
      }
      cell = new PdfPCell(new Paragraph(training.toString(), TD_FONT));
    }
    table.addCell(cell);
    // Date of Issue
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.dateOfIssue"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    if (applicant.getTrainingExpiryDate() == null)
    {
      if (applicant.getPaediatricLifeSupportIssuedDate() != null)
      {
        trainingDateOfIssueText = "\n" + mdf.format(applicant.getPaediatricLifeSupportIssuedDate());
      }
      cell = new PdfPCell();
    }
    else
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(applicant.getTrainingExpiryDate());
      calendar.add(Calendar.YEAR, -1);
      trainingDateOfIssueText = mdf.format(calendar.getTime());
      if (applicant.getPaediatricLifeSupportIssuedDate() != null)
      {
        trainingDateOfIssueText += "\n" + mdf.format(applicant.getPaediatricLifeSupportIssuedDate());
      }
      cell = new PdfPCell(new Paragraph(trainingDateOfIssueText, TD_FONT));
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createSixthTable - Ends...");
    return table;
  }

  /**
   * Creates seventh table
   * 
   * @return seventh table
   */
  private PdfPTable createSeventhTable(Applicant applicant, MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.createSeventhTable - Starts...");
    String dateOfLastAppraisalText = null;
    // First Row.
    float[] columnWidths = {20, 10, 25, 10, 20, 15}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setSpacingAfter(10);
    table.setWidthPercentage(90);
    PdfPCell cell;
    // Competent in Oral\nand Written English
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.competentInOral"), TD_FONTBOLD));
    cell.setFixedHeight(30);
    table.addCell(cell);
    if (applicant.getLanguageCompetency())
    {
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.yes"), TD_FONT));
    }
    else
    {
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.no"), TD_FONT));
    }
    table.addCell(cell);
    // Two References Available
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.twoReferencesAvailable"), TD_FONTBOLD));
    cell.setFixedHeight(30);
    table.addCell(cell);
    if (StringUtils.isNotEmpty(applicant.getReference1Filename()) && applicant.getReference1Status() == 3 &&
        StringUtils.isNotEmpty(applicant.getReference2Filename()) && applicant.getReference2Status() == 3
       )
    {
      // Both References have been Received.
      cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.yes"), TD_FONT));
    }
    else
    {
      cell = new PdfPCell();
    }      
    table.addCell(cell);
    // Date of Last Appraisal This is the 6 Week and 12 Week Assessment
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.dateOfLastAppraisal"), TD_FONTBOLD));
    cell.setFixedHeight(30);
    table.addCell(cell);
    if (applicant.getAssessment12Week() && applicant.getAssessment12WeekDate() != null)
    {
      // Has 12 Week Assessment.
      dateOfLastAppraisalText = messageResources.getMessage("label.applicantChecklist.week12") + ":\n" + mdf.format(applicant.getAssessment12WeekDate());
      cell = new PdfPCell(new Paragraph(dateOfLastAppraisalText, TD_FONT));
    }
    else if (applicant.getPerformanceEvaluation() && applicant.getPerformanceEvaluationDate() != null)
    {
      // Has 6 Week Assessment.
      dateOfLastAppraisalText = messageResources.getMessage("label.applicantChecklist.week6") + ":\n" + mdf.format(applicant.getPerformanceEvaluationDate());
      cell = new PdfPCell(new Paragraph(dateOfLastAppraisalText, TD_FONT));
    }
    else
    {
      // Has NO Assessment.
      cell = new PdfPCell();
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createSeventhTable - Ends...");
    return table;
  }

  /**
   * Creates eighth table
   * 
   * @return eighth table
   */
  private PdfPTable createEighthTable(BookingGradeAgyEntity bookingGradeAgyEntity, Applicant applicant, ClientUser client, MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.createEighthTable - Starts...");
    // a table with three columns
    float[] columnWidths = {20, 80}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(90);
    table.setSpacingAfter(10);
    PdfPCell cell;
    // Other Information as Required by the Authority
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.otherInformation"), TD_FONTBOLD));
    cell.setFixedHeight(30);
    table.addCell(cell);
    if (bookingGradeAgyEntity == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      StringBuffer otherInformation = new StringBuffer();
      if (StringUtils.isNotEmpty(bookingGradeAgyEntity.getDressCodeName()))
      {
        otherInformation.append(messageResources.getMessage("label.dressCode"));
        otherInformation.append(": ");
        otherInformation.append(bookingGradeAgyEntity.getDressCodeName());
      }
      if (StringUtils.isNotEmpty(client.getAgencyWorkerChecklistOther()))
      {
        // Get Client Specific Information.
        String others = client.getAgencyWorkerChecklistOther().replaceAll("  ", " ");
        StringTokenizer st = new StringTokenizer(others, " ");
        String other = null;
        while (st.hasMoreTokens())
        {
          other = st.nextToken();
          otherInformation.append(getOtherInformation(other, applicant, messageResources, mdf));
          otherInformation.append(". ");
        }
      }      
      cell = new PdfPCell(new Paragraph(otherInformation.toString(), TD_FONT));
    }
    table.addCell(cell);
    logger.debug("AgencyWorkerChecklist.createEighthTable - Ends...");
    return table;
  }

  private String getOtherInformation(String propertyName, Applicant applicant, MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.getOtherInformation - Starts... PropertyName =" + propertyName);
    Class applicantClass = applicant.getClass();
    Class userClass      = applicant.getUser().getClass();
    Class addressClass   = applicant.getAddress().getClass();
    Object sourceObject  = null;
    Method method        = null;
    String methodName    = null;
    try
    {
      // The matcher.group() will be something like %fullName% and the method will be: getFullName.
      methodName = "get" + StringUtils.capitalize(propertyName.substring(propertyName.lastIndexOf(".") + 1));
      int dotCount = StringUtils.countMatches(propertyName, ".");
      if (dotCount == 1)
      {
        // Eg. applicant.niNumber
        method = applicantClass.getMethod(methodName, new Class[] {});
        sourceObject = applicant;
      }
      else
      {
        // Has 2 dots. Eg. applicant.user.dateOfBirth or applicant.address.postalCode 
        if (propertyName.substring(propertyName.indexOf(".") + 1, propertyName.lastIndexOf(".")).equalsIgnoreCase("user"))
        {
          // User method.
          method = userClass.getMethod(methodName, new Class[] {});
          sourceObject = applicant.getUser();
        }
        else
        {
          // Address method.
          method = addressClass.getMethod(methodName, new Class[] {});
          sourceObject = applicant.getAddress();
        }
      }
      if (method.getReturnType() == String.class)
      {
        // String type.
        String value = (String)method.invoke(sourceObject, new Object[0]);
        return messageResources.getMessage("label." + propertyName.substring(propertyName.lastIndexOf(".") + 1)) + ": " + value;
      }
      else if (method.getReturnType() == Integer.class)
      {
        // Integer type.
        Integer value = (Integer)method.invoke(sourceObject, new Object[0]);
        return messageResources.getMessage("label." + propertyName.substring(propertyName.lastIndexOf(".") + 1)) + ": " + value.toString();
      }
      else if (method.getReturnType() == Boolean.class)
      {
        // Boolean type.
        Boolean value = (Boolean)method.invoke(sourceObject, new Object[0]);
        return messageResources.getMessage("label." + propertyName.substring(propertyName.lastIndexOf(".") + 1)) + ": " + value.toString();
      }
      else if (method.getReturnType() == Date.class)
      {
        // Date type.
        Date value = (Date)method.invoke(sourceObject, new Object[0]);
        return messageResources.getMessage("label." + propertyName.substring(propertyName.lastIndexOf(".") + 1)) + ": " + mdf.format(value);
      }
      else if (method.getReturnType().getName() == "char")
      {
        // Char type.
        Object value = method.invoke(sourceObject, new Object[0]);
        return messageResources.getMessage("label." + propertyName.substring(propertyName.lastIndexOf(".") + 1)) + ": " + value;
      }
    }
    catch (SecurityException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalArgumentException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalAccessException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NoSuchMethodException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logger.debug("AgencyWorkerChecklist.getOtherInformation - Ends...");
    return "";
  }
  /**
   * Creates ninth table
   * 
   * @return ninth table
   */
  private PdfPTable createNinthTable(MessageResources messageResources, SimpleDateFormat mdf)
  {
    logger.debug("AgencyWorkerChecklist.createNinthTable - Starts...");
    String name = getConsultantLoggedIn().getUser().getFullName();
    Image signature = null;
    try
    {
      String fileName = null;
      if (getConsultantLoggedIn().getSignatureFileUrl() == null)
      {
        logger.debug("Consultant {} does NOT have a signature file URL", getConsultantLoggedIn().getUser().getFullName());
      }
      else
      {
        fileName = FileHandler.getInstance().getConsultantFileLocation() + getConsultantLoggedIn().getSignatureFileUrl();
        File signatureFile = new File(fileName);
        if (signatureFile.exists())
        {
          signature = Image.getInstance(fileName);
        }
      }
    }
    catch (MalformedURLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (BadElementException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // a table with three columns
    float[] columnWidths = {10, 40, 10, 40}; // percentage
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(90);
    table.setSpacingBefore(10);
    table.setSpacingAfter(10);
    PdfPCell cell;
    // Name
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.name"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    table.addCell(new Paragraph(name, TD_FONT));
    // Position
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.position"), TD_FONTBOLD));
    cell.setFixedHeight(20);
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph(getConsultantLoggedIn().getJobTitle(), TD_FONT));
    table.addCell(cell);
    // Signature
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.signature"), TD_FONTBOLD));
    cell.setFixedHeight(40);
    table.addCell(cell);
    if (signature == null)
    {
      cell = new PdfPCell();
    }
    else
    {
      signature.scaleAbsoluteHeight(38);
      signature.scaleAbsoluteWidth(signature.getWidth() * (signature.getScaledHeight() / signature.getHeight()));
      cell = new PdfPCell(signature);
      cell.setVerticalAlignment(Element.ALIGN_CENTER);
      cell.setHorizontalAlignment(Element.ALIGN_LEFT);
      cell.setPadding(1);
    }
    table.addCell(cell);
    // Date
    cell = new PdfPCell(new Paragraph(messageResources.getMessage("label.applicantChecklist.date"), TD_FONTBOLD));
    cell.setFixedHeight(40);
    table.addCell(cell);
    table.addCell(new Paragraph(mdf.format(new java.util.Date()), TD_FONT));
    logger.debug("AgencyWorkerChecklist.createNinthTable - Ends...");
    return table;
  }
 
  private Paragraph createMandatoryParagraph(String text, MessageResources messageResources)
  {
    return new Paragraph(StringUtils.isEmpty(text) ? messageResources.getMessage("label.applicantChecklist.missingData") : text, StringUtils.isEmpty(text) ? TD_FONT_WARNING : TD_FONT);
  }
}
