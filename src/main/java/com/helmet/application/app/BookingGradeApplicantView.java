package com.helmet.application.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AppService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.PdfPageEvent;
import com.helmet.application.Utilities;
import com.helmet.application.agy.AgyConstants;
import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateExpenseUser;
import com.helmet.bean.BookingDateHour;
import com.helmet.bean.BookingGradeApplicantDateUserEntity;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.BookingUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.NhsBooking;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BookingGradeApplicantView extends AppAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  private BaseColor DEFAULT_BACKGROUNDCOLOR = new BaseColor(255, 255, 255);

  private Font DEFAULT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));

  private Font DEFAULT_FONTBOLD = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));

  private Font TH_FONT = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  private Font TH_FONTBIG = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  private BaseColor TH_BACKGROUNDCOLOR = new BaseColor(224, 224, 224);

  private Font TD_FONT = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new BaseColor(0, 0, 0));

  private Font TD_FONTBOLD = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  private Font TD_FONTBIG = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new BaseColor(0, 0, 0));

  private Font TD_FONTBIGBOLD = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  private Font SUMMARY_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));

  private Font FOOTER_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new BaseColor(0, 0, 0));

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    AppService appService = ServiceFactory.getInstance().getAppService();

    BookingGradeApplicantUserEntity bookingGradeApplicant = null;
    BookingUser booking = null;
    NhsBooking nhsBooking = null;
    
    Integer bookingGradeApplicantId = AppUtilities.getCurrentBookingGradeApplicantId(request);

    String show = AppUtilities.getShow(request);
    if (StringUtils.isEmpty(show))
    {
      // show Not in Session, default to weekly.
      show = new String(AppConstants.WEEKLY);
    }
    if (StringUtils.isNotEmpty((String) dynaForm.get("show")))
    {
      // show on form. Override current value.
      show = (String) dynaForm.get("show");
    }
    // Get weekToShow from the Session.
    Integer weekToShow = AppUtilities.getWeekToShow(request);
    if (weekToShow == null)
    {
      // weekToShow Not in Session, default to Current Week.
      weekToShow = new Integer(0);
    }

    if (show.equals(AppConstants.WEEKLY))
    {
      // Showing weekly so determine which week to show 0=current, -1=last week,
      // 1=next week
      if (dynaForm.get("weekToShow") != null)
      {
        // weekToShow on form. Override current value.
        weekToShow = (Integer) dynaForm.get("weekToShow");
      }
      // find out this weeks monday

      Date startDate = Utilities.getStartOfWeek(weekToShow);
      Date endDate = Utilities.getEndOfWeek(weekToShow);

      dynaForm.set("startDate", startDate);
      dynaForm.set("endDate", endDate);

      // System.out.println(startDate + " " + endDate);

      bookingGradeApplicant = appService.getBookingGradeApplicantUserEntityForDateRange(bookingGradeApplicantId, startDate, endDate);

      booking = appService.getBookingUser(bookingGradeApplicant.getBookingId());
      
      if (StringUtils.isNotEmpty(booking.getBookingReference()) && booking.getBookingReference().startsWith(AgyConstants.NHS_BOOKINGS_BANK_REQUEST_NUMBER_LABEL))
      {
        // This is a NHS Booking. Get it.
        String bankReqNum = booking.getBookingReference().substring(booking.getBookingReference().indexOf(" ") + 1);
        nhsBooking = appService.getActiveNhsBookingForBankReqNum(bookingGradeApplicant.getAgencyId(), bankReqNum);
        if (nhsBooking == null)
        {
          // An Active NHS Booking has NOT been found, try for an inactive one. Problems...
          nhsBooking = appService.getNhsBookingForBankReqNum(bookingGradeApplicant.getAgencyId(), bankReqNum);
        }        
      }
        
      if (nhsBooking == null || (nhsBooking != null && nhsBooking.getActive()))
      {
        // Normal Booking or an ACTIVE NHS Booking.
        try
        {
          generatePDF(request, bookingGradeApplicant, booking);
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }      

    }
    else
    {
      // determine whether to show ALL or ONLY outstanding
      Boolean showOnlyOutstanding = (Boolean) dynaForm.get("showOnlyOutstanding");
      //
      bookingGradeApplicant = appService.getBookingGradeApplicantUserEntity(bookingGradeApplicantId, show.equals(AppConstants.OUTSTANDING));

    }

    // in ancestor class
    sortShiftTotals(bookingGradeApplicant.getBookingGradeApplicantDateUserEntities(), dynaForm);

    dynaForm.set("bookingGradeApplicant", bookingGradeApplicant);
    if (booking != null)
    {
      dynaForm.set("bookingReference", booking.getBookingReference());
      if (nhsBooking == null)
      {
        // No NHS Booking. Something hideous has gone wrong...
        dynaForm.set("nhsBookingError", "No NHS Booking found. Please call the help desk...");
      }
      else
      {
        // NHS Booking
        dynaForm.set("nhsBooking", nhsBooking);
        if (nhsBooking.getBookingId().equals(booking.getBookingId()))
        {
          // NHS Booking same as Booking. Disallow changes if NHS Booking has been deleted.
          dynaForm.set("allowChange", nhsBooking.getActive());
        }
        else
        {
          // Something hideous has gone wrong...
          dynaForm.set("nhsBookingError", nhsBooking.toString());
        }
      }      
    }
    Boolean allowPrint = new Boolean(true);
    for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity : bookingGradeApplicant.getBookingGradeApplicantDateUserEntities())
    {
      if (bookingGradeApplicantDateUserEntity.getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_DRAFT)
      {
        allowPrint = new Boolean(false);
        break;
      }
    }
    dynaForm.set("allowPrint", allowPrint);
    dynaForm.set("show", show);
    AppUtilities.setShow(request, show);
    dynaForm.set("weekToShow", weekToShow);
    AppUtilities.setWeekToShow(request, weekToShow);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

  private void generatePDF(HttpServletRequest request, BookingGradeApplicantUserEntity bookingGradeApplicant, BookingUser booking) 
    throws Exception
  {

    AppService appService = ServiceFactory.getInstance().getAppService();

    ClientUser client = appService.getClientUser(bookingGradeApplicant.getClientId());
    AgencyUser agency = appService.getAgencyUser(bookingGradeApplicant.getAgencyId());
    JobProfileUser jobProfile = appService.getJobProfileUser(bookingGradeApplicant.getJobProfileId());

    MessageResources messageResources = getResources(request);
    SimpleDateFormat mdf = new SimpleDateFormat(messageResources.getMessage("format.mediumDateFormat"));
    SimpleDateFormat ldf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
    SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
    DecimalFormat df = new DecimalFormat("#0.00");
    DecimalFormat df3 = new DecimalFormat("#000");
    DecimalFormat dfFactor = new DecimalFormat("#0.##");

    String currencySymbol = messageResources.getMessage("label.currencySymbolActual");

    PdfPCell blankCell = new PdfPCell();

    PdfPCell blankCell2 = new PdfPCell();
    blankCell2.setColspan(2);

    PdfPCell blankCell3 = new PdfPCell();
    blankCell3.setColspan(3);

    PdfPCell blankCell4 = new PdfPCell();
    blankCell4.setColspan(4);

    PdfPCell blankCell5 = new PdfPCell();
    blankCell5.setColspan(5);

    PdfPCell blankCell6 = new PdfPCell();
    blankCell6.setColspan(6);

    PdfPCell blankCell9 = new PdfPCell();
    blankCell9.setColspan(9);

    Document doc = new Document(PageSize.A4, 35, 35, 25, 35);

    // pdf
    String tempFileNamePdf = request.getSession().getId() + ".pdf";
    String tempFilePathPdf = FileHandler.getInstance().getTempFileLocation() + FileHandler.getInstance().getTempFileFolder() + "/" + tempFileNamePdf;

    PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream(tempFilePathPdf));

    pdfWriter.setBoxSize("art", new Rectangle(35, 25, 100, 788));
    PdfPageEvent pdfPageEvent = new PdfPageEvent();
    pdfPageEvent.setFreeText(agency.getFreeText2());
    pdfPageEvent.setFreeTextFont(FOOTER_FONT);
    pdfWriter.setPageEvent(pdfPageEvent);

    doc.open();

    float[] headerColumnWidths = { 40, 60 }; // percentage

    PdfPTable headerTable = new PdfPTable(headerColumnWidths);
    headerTable.setWidthPercentage(100);

    Image agencyLogo = null;

    if (agency.getLogoFilename() != null && !"".equals(agency.getLogoFilename()))
    {
      try
      {
        agencyLogo = Image.getInstance(FileHandler.getInstance().getFileLocation() + agency.getInvoiceLogoUrl());
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
    }

    PdfPCell logoCell = new PdfPCell(new Paragraph(agency.getName()));

    if (agencyLogo != null)
    {

      // scale to be 40 pixels high
      // Float imageHeight = 40F;
      // mmjLogo.scaleAbsoluteHeight(imageHeight);
      // mmjLogo.scaleAbsoluteWidth(mmjLogo.width() * (mmjLogo.scaledHeight() /
      // mmjLogo.height()));

      // scale to be 100%
      // mmjLogo.scalePercent(100);

      logoCell = new PdfPCell(agencyLogo);
    }

    logoCell.setVerticalAlignment(Element.ALIGN_TOP);
    logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
    logoCell.setBorder(Rectangle.NO_BORDER);

    headerTable.addCell(logoCell);

    PdfPTable agencyAddressTable = new PdfPTable(1);

    agencyAddressTable.addCell(getCellBold(messageResources.getMessage("label.vatRegNo") + " " + agency.getVatNumber(), Element.ALIGN_RIGHT));
    agencyAddressTable.addCell(getCell(agency.getName(), Element.ALIGN_RIGHT));
    // agencyAddressTable.addCell(getCell(agency.getAddress().getFullAddress(),
    // Element.ALIGN_RIGHT));
    if (agency.getAddress().getAddress1() != null && !"".equals(agency.getAddress().getAddress1()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getAddress1(), Element.ALIGN_RIGHT));
    }
    if (agency.getAddress().getAddress2() != null && !"".equals(agency.getAddress().getAddress2()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getAddress2(), Element.ALIGN_RIGHT));
    }
    if (agency.getAddress().getAddress3() != null && !"".equals(agency.getAddress().getAddress3()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getAddress3(), Element.ALIGN_RIGHT));
    }
    if (agency.getAddress().getAddress4() != null && !"".equals(agency.getAddress().getAddress4()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getAddress4(), Element.ALIGN_RIGHT));
    }
    if (agency.getAddress().getPostalCode() != null && !"".equals(agency.getAddress().getPostalCode()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getPostalCode(), Element.ALIGN_RIGHT));
    }
    // agencyAddressTable.addCell(getCell(agency.getCountryName(),
    // Element.ALIGN_RIGHT));

    if (agency.getTelephoneNumber() != null && !"".equals(agency.getTelephoneNumber()))
    {
      agencyAddressTable.addCell(getCell(messageResources.getMessage("label.tel") + " " + agency.getTelephoneNumber(), Element.ALIGN_RIGHT));
    }
    if (agency.getFaxNumber() != null && !"".equals(agency.getFaxNumber()))
    {
      agencyAddressTable.addCell(getCell(messageResources.getMessage("label.fax") + " " + agency.getFaxNumber(), Element.ALIGN_RIGHT));
    }

    PdfPCell agencyAddressCell = new PdfPCell(agencyAddressTable);
    agencyAddressCell.setBorder(Rectangle.NO_BORDER);

    headerTable.addCell(agencyAddressCell);

    headerTable.setSpacingAfter(10);
    doc.add(headerTable);

    PdfPTable clientAddressTable = new PdfPTable(1);
    clientAddressTable.setWidthPercentage(100);

    // BookingUser booking =
    // appService.getBookingUser(bookingGradeApplicant.getBookingId());

    if (booking.getAccountContactName() == null || "".equals(booking.getAccountContactName()))
    {

      if (client.getAccountContactName() == null || "".equals(client.getAccountContactName()))
      {
        clientAddressTable.addCell(getCell(client.getName()));
      }
      else
      {
        clientAddressTable.addCell(getCell(client.getAccountContactName()));
      }

    }
    else
    {
      clientAddressTable.addCell(getCell(booking.getAccountContactName()));
    }

    if (booking.getAccountContactAddress().getAddress1() == null || "".equals(booking.getAccountContactAddress().getAddress1()))
    {

      if (client.getAccountContactAddress().getAddress1() == null || "".equals(client.getAccountContactAddress().getAddress1()))
      {
        // clientAddressTable.addCell(getCell(client.getAddress().getFullAddress()));

        if (client.getAddress().getAddress1() != null && !"".equals(client.getAddress().getAddress1()))
        {
          clientAddressTable.addCell(getCell(client.getAddress().getAddress1()));
        }
        if (client.getAddress().getAddress2() != null && !"".equals(client.getAddress().getAddress2()))
        {
          clientAddressTable.addCell(getCell(client.getAddress().getAddress2()));
        }
        if (client.getAddress().getAddress3() != null && !"".equals(client.getAddress().getAddress3()))
        {
          clientAddressTable.addCell(getCell(client.getAddress().getAddress3()));
        }
        if (client.getAddress().getAddress4() != null && !"".equals(client.getAddress().getAddress4()))
        {
          clientAddressTable.addCell(getCell(client.getAddress().getAddress4()));
        }
        if (client.getAddress().getPostalCode() != null && !"".equals(client.getAddress().getPostalCode()))
        {
          clientAddressTable.addCell(getCell(client.getAddress().getPostalCode()));
        }

        // clientAddressTable.addCell(getCell(client.getCountryName()));
      }
      else
      {
        // clientAddressTable.addCell(getCell(client.getAccountContactAddress().getFullAddress()));

        if (client.getAccountContactAddress().getAddress1() != null && !"".equals(client.getAccountContactAddress().getAddress1()))
        {
          clientAddressTable.addCell(getCell(client.getAccountContactAddress().getAddress1()));
        }
        if (client.getAccountContactAddress().getAddress2() != null && !"".equals(client.getAccountContactAddress().getAddress2()))
        {
          clientAddressTable.addCell(getCell(client.getAccountContactAddress().getAddress2()));
        }
        if (client.getAccountContactAddress().getAddress3() != null && !"".equals(client.getAccountContactAddress().getAddress3()))
        {
          clientAddressTable.addCell(getCell(client.getAccountContactAddress().getAddress3()));
        }
        if (client.getAccountContactAddress().getAddress4() != null && !"".equals(client.getAccountContactAddress().getAddress4()))
        {
          clientAddressTable.addCell(getCell(client.getAccountContactAddress().getAddress4()));
        }
        if (client.getAccountContactAddress().getPostalCode() != null && !"".equals(client.getAccountContactAddress().getPostalCode()))
        {
          clientAddressTable.addCell(getCell(client.getAccountContactAddress().getPostalCode()));
        }

        // clientAddressTable.addCell(getCell(client.getAccountContactCountryName()));
      }

    }
    else
    {
      // clientAddressTable.addCell(getCell(booking.getAccountContactAddress().getFullAddress()));

      if (booking.getAccountContactAddress().getAddress1() != null && !"".equals(booking.getAccountContactAddress().getAddress1()))
      {
        clientAddressTable.addCell(getCell(booking.getAccountContactAddress().getAddress1()));
      }
      if (booking.getAccountContactAddress().getAddress2() != null && !"".equals(booking.getAccountContactAddress().getAddress2()))
      {
        clientAddressTable.addCell(getCell(booking.getAccountContactAddress().getAddress2()));
      }
      if (booking.getAccountContactAddress().getAddress3() != null && !"".equals(booking.getAccountContactAddress().getAddress3()))
      {
        clientAddressTable.addCell(getCell(booking.getAccountContactAddress().getAddress3()));
      }
      if (booking.getAccountContactAddress().getAddress4() != null && !"".equals(booking.getAccountContactAddress().getAddress4()))
      {
        clientAddressTable.addCell(getCell(booking.getAccountContactAddress().getAddress4()));
      }
      if (booking.getAccountContactAddress().getPostalCode() != null && !"".equals(booking.getAccountContactAddress().getPostalCode()))
      {
        clientAddressTable.addCell(getCell(booking.getAccountContactAddress().getPostalCode()));
      }

      // clientAddressTable.addCell(getCell(booking.getAccountContactCountryName()));
    }

    clientAddressTable.setSpacingAfter(10);
    doc.add(clientAddressTable);

    PdfPTable bookingTable = new PdfPTable(1);
    bookingTable.setWidthPercentage(100);

    if (booking.getBookingReference() == null || "".equals(booking.getBookingReference()))
    {
      bookingTable.addCell(getCellBold(messageResources.getMessage("label.bookingNo") + " " + df3.format(booking.getBookingId())));
    }
    else
    {
      // bookingReference (client PO Number)
      bookingTable.addCell(getCellBold(messageResources.getMessage("label.bookingReference") + " " + booking.getBookingReference() + "-" + df3.format(booking.getBookingId())));
    }

    String jobProfileDetail = bookingGradeApplicant.getJobProfileName();

    if (jobProfile.getJobFamilyCode() != null && !"".equals(jobProfile.getJobFamilyCode()) && jobProfile.getJobSubFamilyCode() != null && !"".equals(jobProfile.getJobSubFamilyCode())
        && jobProfile.getCode() != null && !"".equals(jobProfile.getCode()))
    {
      // BTT Code
      jobProfileDetail += " - " + jobProfile.getJobFamilyCode() + "." + jobProfile.getJobSubFamilyCode() + "." + jobProfile.getCode();
    }

    jobProfileDetail += " - " + bookingGradeApplicant.getGradeName();
    bookingTable.addCell(getCell(bookingGradeApplicant.getApplicantFirstName() + " " + bookingGradeApplicant.getApplicantLastName() + " - " + jobProfileDetail));
    bookingTable.addCell(getCell(bookingGradeApplicant.getLocationName() + ", " + bookingGradeApplicant.getSiteName() + ", " + client.getName()));

    bookingTable.setSpacingAfter(10);
    doc.add(bookingTable);

    BigDecimal totalNoOfHours = new BigDecimal(0);
    BigDecimal totalExpenseValue = new BigDecimal(0);

    float[] detailColumnWidths = new float[6 + bookingGradeApplicant.getUpliftFactors().size()];

    detailColumnWidths[0] = 10;
    detailColumnWidths[1] = 18;
    detailColumnWidths[2] = 18;
    detailColumnWidths[3] = 12;
    detailColumnWidths[4] = 6;
    int i = 4;
    for (BigDecimal upliftFactor : bookingGradeApplicant.getUpliftFactors())
    {
      i++;
      detailColumnWidths[i] = 5;
    }
    detailColumnWidths[5 + bookingGradeApplicant.getUpliftFactors().size()] = 35;

    // float[] detailColumnWidths = {10,18,17,12,7,36}; // percentage
    //
    PdfPTable detailTable = new PdfPTable(detailColumnWidths);

    // PdfPTable detailTable = new PdfPTable(6 +
    // bookingGradeApplicant.getUpliftFactors().size());

    detailTable.setWidthPercentage(100);

    detailTable.addCell(getTHCell(messageResources.getMessage("label.shiftNo")));
    detailTable.addCell(getTHCell(messageResources.getMessage("label.date")));
    detailTable.addCell(getTHCell(messageResources.getMessage("label.times")));
    detailTable.addCell(getTHCell(messageResources.getMessage("label.break")));
    detailTable.addCell(getTHCell(messageResources.getMessage("label.qty")));

    for (BigDecimal upliftFactor : bookingGradeApplicant.getUpliftFactors())
    {
      detailTable.addCell(getTHCellRight("x" + dfFactor.format(upliftFactor)));
    }

    detailTable.addCell(getTHCell(messageResources.getMessage("label.comment")));

    for (BookingGradeApplicantDateUserEntity bookingDate : bookingGradeApplicant.getBookingGradeApplicantDateUserEntities())
    {

      detailTable.addCell(getTHCell(df3.format(bookingDate.getBookingId()) + "." + df3.format(bookingDate.getBookingDateId())));

      detailTable.addCell(getTHCell(ldf.format(bookingDate.getBookingDate())));

      if (bookingDate.getWorkedNoOfHours() == null)
      {

        detailTable.addCell(blankCell);
        detailTable.addCell(blankCell);
        detailTable.addCell(blankCell);

        for (BigDecimal upliftFactor : bookingGradeApplicant.getUpliftFactors())
        {
          detailTable.addCell(blankCell);
        }

        detailTable.addCell(blankCell);

      }
      else
      {

        if (bookingDate.getWorkedNoOfHours().compareTo(new BigDecimal(0)) == 0)
        {

          // no worked hours so - output the reason (text)
          detailTable.addCell(blankCell);
          detailTable.addCell(blankCell);
          detailTable.addCell(getTDCellRight(df.format(bookingDate.getWorkedNoOfHours())));

          for (BigDecimal upliftFactor : bookingGradeApplicant.getUpliftFactors())
          {
            detailTable.addCell(blankCell);
          }

          detailTable.addCell(getTDCell(bookingDate.getComment()));

        }
        else
        {

          String times = tdf.format(bookingDate.getWorkedStartTime()) + " - " + tdf.format(bookingDate.getWorkedEndTime());
          if (bookingDate.getWorkedBreakNoOfHours().compareTo(new BigDecimal(0)) > 0)
          {
            times += " (" + df.format(bookingDate.getWorkedBreakNoOfHours()) + ")";
          }
          detailTable.addCell(getTDCell(times));

          if (bookingDate.getWorkedBreakNoOfHours().compareTo(new BigDecimal(0)) > 0)
          {
            String breakTimes = tdf.format(bookingDate.getWorkedBreakStartTime()) + " - " + tdf.format(bookingDate.getWorkedBreakEndTime());
            detailTable.addCell(getTDCell(breakTimes));
          }
          else
          {
            detailTable.addCell(blankCell);
          }

          String hours = df.format(bookingDate.getWorkedNoOfHours());

          // if (bookingDate.getHasUplift()) {
          // hours = "* " + hours;
          // }

          detailTable.addCell(getTDCellRight(hours));

          for (BigDecimal upliftFactor : bookingGradeApplicant.getUpliftFactors())
          {
            BigDecimal upliftHours = new BigDecimal(0);
            for (BookingDateHour bookingDateHour : bookingDate.getBookingDateHours())
            {
              if (bookingDateHour.getUpliftFactor().compareTo(upliftFactor) == 0)
              {
                BigDecimal portionOfHour = bookingDateHour.getChargeRateValue().compareTo(new BigDecimal(0)) < 0 ? bookingDateHour.getPortionOfHour().multiply(new BigDecimal(-1)) : bookingDateHour
                    .getPortionOfHour();
                upliftHours = upliftHours.add(portionOfHour);
              }
            }
            detailTable.addCell(getTDCellBigRight(df.format(upliftHours)));
          }

          detailTable.addCell(getTDCell(bookingDate.getComment()));

          if (bookingDate.getExpenseValue().compareTo(new BigDecimal(0)) > 0)
          {

            for (BookingDateExpenseUser bookingDateExpense : bookingDate.getBookingDateExpenseUsers())
            {

              detailTable.addCell(blankCell4);

              if (bookingDateExpense.getIsMultiplier())
              {
                detailTable.addCell(getTDCellRight(df.format(bookingDateExpense.getQty())));
              }
              else
              {
                detailTable.addCell(getTDCellRight("1.00"));
              }

              for (BigDecimal upliftFactor : bookingGradeApplicant.getUpliftFactors())
              {
                detailTable.addCell(blankCell);
              }

              String expenseDetail = bookingDateExpense.getExpenseName();

              if (bookingDateExpense.getIsMultiplier())
              {
                expenseDetail += " @ " + currencySymbol + df.format(bookingDateExpense.getExpenseMultiplier()) + " = " + currencySymbol + df.format(bookingDateExpense.getValue());
              }
              else
              {
                expenseDetail += " @ " + currencySymbol + df.format(bookingDateExpense.getQty());
              }

              detailTable.addCell(getTDCell(expenseDetail));

              if (bookingDateExpense.getText() != null && !"".equals(bookingDateExpense.getText()))
              {
                detailTable.addCell(blankCell5);
                for (BigDecimal upliftFactor : bookingGradeApplicant.getUpliftFactors())
                {
                  detailTable.addCell(blankCell);
                }
                detailTable.addCell(getTDCell(bookingDateExpense.getText()));
              }
              //                            
              totalExpenseValue = totalExpenseValue.add(bookingDateExpense.getValue());
            }
          }

        }

        //                            
        totalNoOfHours = totalNoOfHours.add(bookingDate.getWorkedNoOfHours());

      }

    }

    detailTable.setSpacingAfter(10);
    doc.add(detailTable);

    float[] summaryDetailColumnWidths = { 50, 50 }; // percentage
    PdfPTable summaryDetailTable = new PdfPTable(summaryDetailColumnWidths);
    summaryDetailTable.setWidthPercentage(36);
    summaryDetailTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

    PdfPCell summaryLabelCell = getTHCell(messageResources.getMessage("label.summaryUppercase"));
    summaryLabelCell.setColspan(2);
    summaryLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    summaryDetailTable.addCell(summaryLabelCell);

    summaryDetailTable.addCell(blankCell2);

    summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.noOfHours")));
    summaryDetailTable.addCell(getTDCellRight(df.format(totalNoOfHours)));

    // if there is any uplifted hours - show the break down

    for (BigDecimal upliftFactor : bookingGradeApplicant.getUpliftFactors())
    {
      summaryDetailTable.addCell(getTHCell("x" + dfFactor.format(upliftFactor)));
      // TODO - not efficient!
      BigDecimal upliftHours = new BigDecimal(0);
      for (BookingGradeApplicantDateUserEntity bookingDate : bookingGradeApplicant.getBookingGradeApplicantDateUserEntities())
      {
        if (bookingDate.getBookingDateHours() != null)
        {
          for (BookingDateHour bookingDateHour : bookingDate.getBookingDateHours())
          {
            if (bookingDateHour.getUpliftFactor().compareTo(upliftFactor) == 0)
            {
              BigDecimal portionOfHour = bookingDateHour.getChargeRateValue().compareTo(new BigDecimal(0)) < 0 ? bookingDateHour.getPortionOfHour().multiply(new BigDecimal(-1)) : bookingDateHour
                  .getPortionOfHour();
              upliftHours = upliftHours.add(portionOfHour);
            }
          }
        }
      }
      summaryDetailTable.addCell(getTDCellRight(df.format(upliftHours)));
    }

    if (totalExpenseValue.compareTo(new BigDecimal(0)) > 0)
    {
      summaryDetailTable.addCell(blankCell2);
      summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.expenses")));
      summaryDetailTable.addCell(getTDCellRight(currencySymbol + df.format(totalExpenseValue)));
    }

    summaryDetailTable.setSpacingAfter(10);
    doc.add(summaryDetailTable);

    float[] signatureColumnWidths = { 25, 75 }; // percentage
    PdfPTable signatureDetailTable = new PdfPTable(signatureColumnWidths);
    signatureDetailTable.setWidthPercentage(100);

    signatureDetailTable.addCell(getCell(messageResources.getMessage("label.contractorSignature")));
    signatureDetailTable.addCell(getCell("......................................................................"));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(messageResources.getMessage("label.printName")));
    signatureDetailTable.addCell(getCell(bookingGradeApplicant.getApplicantFirstName() + " " + bookingGradeApplicant.getApplicantLastName()));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(messageResources.getMessage("label.date")));
    signatureDetailTable.addCell(getCell("......................................................................"));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.setSpacingBefore(10);
    doc.add(signatureDetailTable);

    float[] declarationColumnWidths = { 100 }; // percentage
    PdfPTable declarationTable = new PdfPTable(declarationColumnWidths);
    declarationTable.setWidthPercentage(100);
    declarationTable.addCell(getCell(messageResources.getMessage("label.contractorDeclaration"), Element.ALIGN_LEFT, 0, FOOTER_FONT));
    declarationTable.setSpacingAfter(10);
    doc.add(declarationTable);

    signatureDetailTable = new PdfPTable(signatureColumnWidths);
    signatureDetailTable.setWidthPercentage(100);
    signatureDetailTable.addCell(getCell(messageResources.getMessage("label.managerSignature")));
    signatureDetailTable.addCell(getCell("......................................................................"));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(messageResources.getMessage("label.printName")));
    signatureDetailTable.addCell(getCell("......................................................................"));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(""));
    signatureDetailTable.addCell(getCell(messageResources.getMessage("label.date")));
    signatureDetailTable.addCell(getCell("......................................................................"));
    signatureDetailTable.setSpacingBefore(10);
    signatureDetailTable.setSpacingAfter(10);
    doc.add(signatureDetailTable);

    declarationTable = new PdfPTable(declarationColumnWidths);
    declarationTable.setWidthPercentage(100);
    declarationTable.addCell(getCell(messageResources.getMessage("label.managerDeclaration"), Element.ALIGN_LEFT, 0, FOOTER_FONT));
    declarationTable.setSpacingAfter(10);
    doc.add(declarationTable);

    float[] footerColumnWidths = { 85, 15 }; // percentage

    PdfPTable footerTable = new PdfPTable(footerColumnWidths);
    footerTable.setWidthPercentage(100);
    footerTable.setExtendLastRow(true);

    Image mmjLogo = null;

//    String serverNamePrefix = request.getServerName().substring(0, request.getServerName().indexOf("."));
//    serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
//    String mmjLogoFilename = "/images/" + serverNamePrefix + "master-logo.jpg";
    String serverName = request.getServerName();
    String serverNamePrefix = null;
    if (serverName.indexOf(".") == -1)
    {
      serverNamePrefix = "local";
    }
    else
    {
      serverNamePrefix = serverName.substring(0, serverName.indexOf("."));
    }
    serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
    String mmjLogoFilename = "/images/" + serverNamePrefix + "master-logo.jpg";

    try
    {
      mmjLogo = Image.getInstance(FileHandler.getInstance().getFileLocation() + mmjLogoFilename);
      // mmjLogo =
      // Image.getInstance(request.getSession().getServletContext().getRealPath(mmjLogoFilename));
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

    if (mmjLogo == null)
    {

      PdfPCell footerCell = new PdfPCell(new Phrase("Produced by Match My Job", FOOTER_FONT));
      footerCell.setBorder(Rectangle.NO_BORDER);
      footerCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
      footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      footerCell.setColspan(2);
      footerTable.addCell(footerCell);

    }
    else
    {

      PdfPCell footerTextCell = getCell("Produced by", Element.ALIGN_RIGHT, Rectangle.NO_BORDER, FOOTER_FONT);
      footerTextCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
      footerTextCell.setPaddingBottom(0);
      footerTable.addCell(footerTextCell);

      // scale to be 30 pixels high
      Float imageHeight = 30F;
      mmjLogo.scaleAbsoluteHeight(imageHeight);
      mmjLogo.scaleAbsoluteWidth(mmjLogo.getWidth() * (mmjLogo.getScaledHeight() / mmjLogo.getHeight()));

      // scale to be 100%
      // mmjLogo.scalePercent(100);

      PdfPCell footerLogoCell = new PdfPCell(mmjLogo);
      footerLogoCell.setBorder(Rectangle.NO_BORDER);
      footerLogoCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
      footerLogoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      footerTable.addCell(footerLogoCell);

    }

    doc.add(footerTable);

    doc.newPage();

    doc.close();

  }

  private PdfPCell getCell(String text)
  {
    return getCell(text, Element.ALIGN_LEFT);
  }

  private PdfPCell getCell(String text, int horizontalAlignment)
  {
    return getCell(text, horizontalAlignment, Rectangle.NO_BORDER);
  }

  private PdfPCell getCell(String text, int horizontalAlignment, int border)
  {
    return getCell(text, horizontalAlignment, border, DEFAULT_FONT);
  }

  private PdfPCell getCellBold(String text)
  {
    return getCellBold(text, Element.ALIGN_LEFT);
  }

  private PdfPCell getCellBold(String text, int horizontalAlignment)
  {
    return getCellBold(text, horizontalAlignment, Rectangle.NO_BORDER);
  }

  private PdfPCell getCellBold(String text, int horizontalAlignment, int border)
  {
    return getCell(text, horizontalAlignment, border, DEFAULT_FONTBOLD);
  }

  private PdfPCell getCell(String text, int horizontalAlignment, int border, Font font)
  {
    return getCell(text, horizontalAlignment, border, font, DEFAULT_BACKGROUNDCOLOR);
  }

  private PdfPCell getCell(String text, int horizontalAlignment, int border, Font font, BaseColor backgroundColor)
  {

    PdfPCell myCell = new PdfPCell(new Paragraph(text, font));
    myCell.setHorizontalAlignment(horizontalAlignment);
    myCell.setBorder(border);
    myCell.setBackgroundColor(backgroundColor);
    // myCell.setBackgroundColor(new BaseColor(123, 123, 123));
    return myCell;

  }

  private PdfPCell getTDCell(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.BOX, TD_FONT);
  }

  private PdfPCell getTDCellRight(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TD_FONT);
  }

  private PdfPCell getTDCellRightBold(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TD_FONTBOLD);
  }

  private PdfPCell getTHCell(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.BOX, TH_FONT, TH_BACKGROUNDCOLOR);
  }

  private PdfPCell getTHCellRight(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TH_FONT, TH_BACKGROUNDCOLOR);
  }

  private PdfPCell getTDCellBig(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.BOX, TD_FONTBIG);
  }

  private PdfPCell getTDCellBigRight(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TD_FONTBIG);
  }

  private PdfPCell getTDCellBigRightBold(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TD_FONTBIGBOLD);
  }

  private PdfPCell getTHCellBig(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.BOX, TH_FONTBIG, TH_BACKGROUNDCOLOR);
  }

  private PdfPCell getTHCellBigRight(String text)
  {
    return getCell(text, Element.ALIGN_RIGHT, Rectangle.BOX, TH_FONTBIG, TH_BACKGROUNDCOLOR);
  }

}
