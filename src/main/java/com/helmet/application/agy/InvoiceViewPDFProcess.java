package com.helmet.application.agy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.PdfPageEvent;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingDateExpenseUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingDateUserApplicantEntity;
import com.helmet.bean.BookingUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.InvoiceAgencyUserEntity;
import com.helmet.bean.JobProfileUser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoiceViewPDFProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  private BaseColor DEFAULT_BACKGROUNDCOLOR = new BaseColor(255, 255, 255);

  private Font DEFAULT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));

  private Font DEFAULT_FONTBOLD = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));

  private Font TH_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));

  private Font TH_FONTBIG = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  private BaseColor TH_BACKGROUNDCOLOR = new BaseColor(224, 224, 224);

  private Font TD_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new BaseColor(0, 0, 0));

  private Font TD_FONTBOLD = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));

  private Font TD_FONTBIG = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new BaseColor(0, 0, 0));

  private Font TD_FONTBIGBOLD = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(0, 0, 0));

  private Font SUMMARY_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));

  private Font FOOTER_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new BaseColor(0, 0, 0));

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    InvoiceAgencyUserEntity invoiceAgency = (InvoiceAgencyUserEntity) dynaForm.get("invoiceAgency");
    ClientUser client = (ClientUser) dynaForm.get("client");
    AgencyUser agency = (AgencyUser) dynaForm.get("agency");

    try
    {
      doIt(request, client, agency, invoiceAgency);
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    logger.exit("Out going !!!");

    return mapping.findForward("success");

  }

  private void doIt(HttpServletRequest request, ClientUser client, AgencyUser agency, InvoiceAgencyUserEntity invoiceAgency) throws Exception
  {

    MessageResources messageResources = getResources(request);
    SimpleDateFormat mdf = new SimpleDateFormat(messageResources.getMessage("format.mediumDateFormat"));
    SimpleDateFormat ldf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
    SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
    DecimalFormat df = new DecimalFormat("#0.00");
    DecimalFormat df3 = new DecimalFormat("#000");

    String currencySymbol = messageResources.getMessage("label.currencySymbolActual");

    PdfPCell blankCell = new PdfPCell();

    PdfPCell blankCell2 = new PdfPCell();
    blankCell2.setColspan(2);

    PdfPCell blankCell3 = new PdfPCell();
    blankCell3.setColspan(3);

    PdfPCell blankCell6 = new PdfPCell();
    blankCell6.setColspan(6);

    PdfPCell blankCell9 = new PdfPCell();
    blankCell9.setColspan(9);

    Document doc = new Document(PageSize.A4, 35, 35, 25, 35);

    String fileName = "ia" + invoiceAgency.getInvoiceId() + "." + invoiceAgency.getInvoiceAgencyId() + ".pdf";

    String tempFilePath = FileHandler.getInstance().getTempFileLocation() + FileHandler.getInstance().getTempFileFolder() + "/" + fileName;

    PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream(tempFilePath));

    //        String agencyAndInvoice = agency.getName() + " " + invoiceAgency.getInvoiceId() + "." + invoiceAgency.getInvoiceAgencyId();
    //        
    //        HeaderFooter hd = new HeaderFooter(new Paragraph(agencyAndInvoice), false);
    //        hd.setBorder(Rectangle.NO_BORDER);
    //        doc.setHeader(hd);

//    HeaderFooter foot = new HeaderFooter(new Paragraph(agency.getFreeText2(), FOOTER_FONT), false);
//    foot.setBorder(Rectangle.NO_BORDER);
//    foot.setAlignment(Element.ALIGN_CENTER);
//    doc.setFooter(foot);
    pdfWriter.setBoxSize("art", new Rectangle(35, 25, 100, 788));
    PdfPageEvent invoicePageEvent = new PdfPageEvent();
    invoicePageEvent.setFreeText(agency.getFreeText2());
    invoicePageEvent.setFreeTextFont(FOOTER_FONT);
    pdfWriter.setPageEvent(invoicePageEvent);

    doc.open();

    if (invoiceAgency.getAllTheSameBookingAndApplicant())
    {

      float[] headerColumnWidths = { 40, 60 }; // percentage

      PdfPTable headerTable = new PdfPTable(headerColumnWidths);
      headerTable.setWidthPercentage(100);

      Image agencyInvoiceLogo = null;

      if (agency.getInvoiceLogoFilename() != null && !"".equals(agency.getInvoiceLogoFilename()))
      {
        try
        {
          agencyInvoiceLogo = Image.getInstance(FileHandler.getInstance().getFileLocation() + agency.getInvoiceLogoUrl());
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

      // TODO - currently using the first one !!!
      BookingDateUserApplicant xxx = invoiceAgency.getBookingDateUserApplicants().get(0);

      PdfPCell logoCell = new PdfPCell(new Paragraph(agency.getName()));

      if (agencyInvoiceLogo != null)
      {

        //     scale to be 40 pixels high         
        //              Float imageHeight = 40F;
        //              mmjLogo.scaleAbsoluteHeight(imageHeight);
        //              mmjLogo.scaleAbsoluteWidth(mmjLogo.width() * (mmjLogo.scaledHeight() / mmjLogo.height()));

        //     scale to be 100%         
        //                mmjLogo.scalePercent(100);

        logoCell = new PdfPCell(agencyInvoiceLogo);
      }

      logoCell.setVerticalAlignment(Element.ALIGN_TOP);
      logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      logoCell.setBorder(Rectangle.NO_BORDER);

      headerTable.addCell(logoCell);

      PdfPTable agencyAddressTable = new PdfPTable(1);

      agencyAddressTable.addCell(getCellBold(messageResources.getMessage("label.vatRegNo") + " " + agency.getVatNumber(), Element.ALIGN_RIGHT));
      agencyAddressTable.addCell(getCell(agency.getName(), Element.ALIGN_RIGHT));
      //        agencyAddressTable.addCell(getCell(agency.getAddress().getFullAddress(), Element.ALIGN_RIGHT));
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
      //        agencyAddressTable.addCell(getCell(agency.getCountryName(), Element.ALIGN_RIGHT));

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

      float[] clientColumnWidths = { 60, 40 }; // percentage

      PdfPTable clientTable = new PdfPTable(clientColumnWidths);
      clientTable.setWidthPercentage(100);

      PdfPTable clientAddressTable = new PdfPTable(1);

      // TODO xxx !!!
//      MgrService mgrService = ServiceFactory.getInstance().getMgrService();

      BookingUser booking = null;//mgrService.getBookingUser(xxx.getBookingId());

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
          //            clientAddressTable.addCell(getCell(client.getAddress().getFullAddress()));

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

          //            clientAddressTable.addCell(getCell(client.getCountryName()));
        }
        else
        {
          //            clientAddressTable.addCell(getCell(client.getAccountContactAddress().getFullAddress()));

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

          //            clientAddressTable.addCell(getCell(client.getAccountContactCountryName()));
        }

      }
      else
      {
        //          clientAddressTable.addCell(getCell(booking.getAccountContactAddress().getFullAddress()));

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

        //          clientAddressTable.addCell(getCell(booking.getAccountContactCountryName()));
      }

      PdfPCell clientAddressCell = new PdfPCell(clientAddressTable);
      clientAddressCell.setBorder(Rectangle.NO_BORDER);

      clientTable.addCell(clientAddressCell);

      PdfPTable invoiceDetailTable = new PdfPTable(1);

      String creationTimestamp = mdf.format(invoiceAgency.getCreationTimestamp());
      invoiceDetailTable.addCell(getCell(messageResources.getMessage("label.invoiceDate") + " " + creationTimestamp, Element.ALIGN_RIGHT));
      //        invoiceDetailTable.addCell(getCell(client.getReference(), Element.ALIGN_RIGHT));

      // TODO - invoice should REALLY be payment batch
      // for now if invoiceAgency.reference exists use that as invoice number - otherwise use current invoiceno
      if (invoiceAgency.getReference() == null || "".equals(invoiceAgency.getReference()))
      {
        invoiceDetailTable.addCell(getCellBold(messageResources.getMessage("label.invoiceNo") + " " + invoiceAgency.getInvoiceId(), Element.ALIGN_RIGHT));
      }
      else
      {
        // agencyInvoice reference (agency invoice number)
        invoiceDetailTable.addCell(getCellBold(messageResources.getMessage("label.invoiceNo") + " " + invoiceAgency.getReference() + "-" + invoiceAgency.getInvoiceId(), Element.ALIGN_RIGHT));
      }

      PdfPCell invoiceDetailCell = new PdfPCell(invoiceDetailTable);
      invoiceDetailCell.setBorder(Rectangle.NO_BORDER);

      clientTable.addCell(invoiceDetailCell);

      clientTable.setSpacingAfter(10);
      doc.add(clientTable);

      PdfPTable bookingTable = new PdfPTable(1);
      bookingTable.setWidthPercentage(100);

      if (xxx.getBookingReference() == null || "".equals(xxx.getBookingReference()))
      {
        bookingTable.addCell(getCellBold(messageResources.getMessage("label.bookingNo") + " " + df3.format(xxx.getBookingId())));
      }
      else
      {
        // bookingReference (client PO Number)
        bookingTable.addCell(getCellBold(messageResources.getMessage("label.bookingReference") + " " + xxx.getBookingReference() + "-" + df3.format(xxx.getBookingId())));
      }

      //        bookingTable.addCell(getCell(xxx.getApplicantFirstName() + " " + xxx.getApplicantLastName()));
      String jobProfileDetail = xxx.getJobProfileName();

      AgyService agyService = ServiceFactory.getInstance().getAgyService();
      JobProfileUser jobProfile = agyService.getJobProfileUser(xxx.getJobProfileId());

      if (jobProfile.getJobFamilyCode() != null && !"".equals(jobProfile.getJobFamilyCode()) && jobProfile.getJobSubFamilyCode() != null && !"".equals(jobProfile.getJobSubFamilyCode())
          && jobProfile.getCode() != null && !"".equals(jobProfile.getCode()))
      {
        // BTT Code
        jobProfileDetail += " - " + jobProfile.getJobFamilyCode() + "." + jobProfile.getJobSubFamilyCode() + "." + jobProfile.getCode();
      }

      jobProfileDetail += " - " + xxx.getGradeName();
      bookingTable.addCell(getCell(xxx.getApplicantFirstName() + " " + xxx.getApplicantLastName() + " - " + jobProfileDetail));
      bookingTable.addCell(getCell(xxx.getLocationName() + ", " + xxx.getSiteName() + ", " + client.getName()));

      bookingTable.setSpacingAfter(10);
      doc.add(bookingTable);

      float[] detailColumnWidths = { 16, 14, 15, 6, 20, 7, 8, 6, 8 }; // percentage

      PdfPTable detailTable = new PdfPTable(detailColumnWidths);
      detailTable.setWidthPercentage(100);

      detailTable.addCell(getTHCell(messageResources.getMessage("label.shiftNo")));
      detailTable.addCell(getTHCell(messageResources.getMessage("label.date")));
      detailTable.addCell(getTHCell(messageResources.getMessage("label.times")));
      detailTable.addCell(getTHCell(messageResources.getMessage("label.qty")));
      detailTable.addCell(getTHCell(messageResources.getMessage("label.details")));
      detailTable.addCell(getTHCell(messageResources.getMessage("label.unitPriceShort")));
      detailTable.addCell(getTHCell(messageResources.getMessage("label.net")));
      detailTable.addCell(getTHCell(messageResources.getMessage("label.vatRateShort")));
      detailTable.addCell(getTHCell(messageResources.getMessage("label.vat")));

      for (BookingDateUserApplicantEntity bookingDate : invoiceAgency.getBookingDateUserApplicants())
      {

        detailTable.addCell(getTHCell(df3.format(bookingDate.getBookingId()) + "." + df3.format(bookingDate.getBookingDateId())));

        detailTable.addCell(getTHCell(ldf.format(bookingDate.getBookingDate())));

        if (bookingDate.getWorkedNoOfHours().compareTo(new BigDecimal(0)) == 0)
        {

          // no worked hours so - output the reason (text)
          detailTable.addCell(blankCell);
          detailTable.addCell(getTDCellRight(df.format(bookingDate.getWorkedNoOfHours())));
          PdfPCell bookingDateCommentCell = getTDCell(bookingDate.getComment());
          bookingDateCommentCell.setColspan(5);
          detailTable.addCell(bookingDateCommentCell);

        }
        else
        {

          String times = tdf.format(bookingDate.getWorkedStartTime()) + " - " + tdf.format(bookingDate.getWorkedEndTime());
          if (bookingDate.getWorkedBreakNoOfHours().compareTo(new BigDecimal(0)) > 0)
          {
            times += " (" + df.format(bookingDate.getWorkedBreakNoOfHours()) + ")";
          }
          if (bookingDate.getHasUplift())
          {
            times += " *";
          }
          detailTable.addCell(getTDCell(times));

          detailTable.addCell(getTDCellRight(df.format(bookingDate.getWorkedNoOfHours())));

          if (bookingDate.getChargeRateVatRate().compareTo(new BigDecimal(0)) == 0)
          {

            detailTable.addCell(getTDCell(messageResources.getMessage("label.commission")));
            detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getCommissionRate())));
            detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedCommissionValue())));
            if (bookingDate.getCommissionVatRate().compareTo(new BigDecimal(0)) > 0)
            {
              detailTable.addCell(getTDCellRight(df.format(bookingDate.getCommissionVatRate())));
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedCommissionVatValue())));
            }
            else
            {
              detailTable.addCell(blankCell);
              detailTable.addCell(blankCell);
            }

            //

            detailTable.addCell(blankCell3);
            detailTable.addCell(blankCell);

            detailTable.addCell(getTDCell(messageResources.getMessage("label.payRate")));
            detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getPayRate())));
            detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedPayRateValue())));
            if (bookingDate.getPayRateVatRate().compareTo(new BigDecimal(0)) > 0)
            {
              detailTable.addCell(getTDCellRight(df.format(bookingDate.getPayRateVatRate())));
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedPayRateVatValue())));
            }
            else
            {
              detailTable.addCell(blankCell);
              detailTable.addCell(blankCell);
            }

            // wtd

            if (bookingDate.getWorkedWtdValue().compareTo(new BigDecimal(0)) > 0)
            {

              detailTable.addCell(blankCell3);
              detailTable.addCell(blankCell);

              detailTable.addCell(getTDCell(messageResources.getMessage("label.wtd") + " @ " + df.format(bookingDate.getWtdPercentage())));
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWtdRate())));
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedWtdValue())));
              if (bookingDate.getWtdVatRate().compareTo(new BigDecimal(0)) > 0)
              {
                detailTable.addCell(getTDCellRight(df.format(bookingDate.getWtdVatRate())));
                detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedWtdVatValue())));
              }
              else
              {
                detailTable.addCell(blankCell);
                detailTable.addCell(blankCell);
              }

            }

            // ni

            if (bookingDate.getWorkedNiValue().compareTo(new BigDecimal(0)) > 0)
            {

              detailTable.addCell(blankCell3);
              detailTable.addCell(blankCell);

              detailTable.addCell(getTDCell(messageResources.getMessage("label.ni") + " @ " + df.format(bookingDate.getNiPercentage())));
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getNiRate())));
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedNiValue())));
              if (bookingDate.getNiVatRate().compareTo(new BigDecimal(0)) > 0)
              {
                detailTable.addCell(getTDCellRight(df.format(bookingDate.getNiVatRate())));
                detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedNiVatValue())));
              }
              else
              {
                detailTable.addCell(blankCell);
                detailTable.addCell(blankCell);
              }

            }

          }
          else
          {

            detailTable.addCell(getTDCell(messageResources.getMessage("label.chargeRate")));
            detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getChargeRate())));
            detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedChargeRateValue())));
            if (bookingDate.getChargeRateVatRate().compareTo(new BigDecimal(0)) > 0)
            {
              detailTable.addCell(getTDCellRight(df.format(bookingDate.getChargeRateVatRate())));
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDate.getWorkedChargeRateVatValue())));
            }
            else
            {
              detailTable.addCell(blankCell);
              detailTable.addCell(blankCell);
            }

          }

          for (BookingDateExpenseUser bookingDateExpense : bookingDate.getBookingDateExpenses())
          {

            detailTable.addCell(blankCell3);

            if (bookingDateExpense.getIsMultiplier())
            {
              detailTable.addCell(getTDCellRight(df.format(bookingDateExpense.getQty())));
            }
            else
            {
              detailTable.addCell(getTDCellRight("1.00"));
            }

            detailTable.addCell(getTDCell(bookingDateExpense.getExpenseName()));

            if (bookingDateExpense.getIsMultiplier())
            {
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDateExpense.getExpenseMultiplier())));
            }
            else
            {
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDateExpense.getQty())));
            }

            detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDateExpense.getValue())));

            if (bookingDateExpense.getExpenseVatRate().compareTo(new BigDecimal(0)) > 0)
            {
              detailTable.addCell(getTDCellRight(df.format(bookingDateExpense.getExpenseVatRate())));
              detailTable.addCell(getTDCellRight(currencySymbol + df.format(bookingDateExpense.getVatValue())));
            }
            else
            {
              detailTable.addCell(blankCell);
              detailTable.addCell(blankCell);
            }

            if (bookingDateExpense.getText() != null && !"".equals(bookingDateExpense.getText()))
            {
              detailTable.addCell(blankCell3);
              detailTable.addCell(blankCell);
              PdfPCell expenseTextCell = getTDCell(bookingDateExpense.getText());
              expenseTextCell.setColspan(5);
              detailTable.addCell(expenseTextCell);
            }

          }

        }

      }

      //

      detailTable.addCell(blankCell9);

      //

      PdfPCell subTotalCell = getTHCell(messageResources.getMessage("label.subTotal"));
      subTotalCell.setColspan(6);
      subTotalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      detailTable.addCell(subTotalCell);

      detailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getTotalNetValue())));

      detailTable.addCell(blankCell);

      detailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getVatValue())));

      //

      detailTable.addCell(blankCell9);

      //

      PdfPCell totalLabelCell = getTHCellBig(messageResources.getMessage("label.totalUppercase"));
      totalLabelCell.setColspan(6);
      totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

      detailTable.addCell(totalLabelCell);

      PdfPCell totalCell = getTDCellBigRightBold(currencySymbol + df.format(invoiceAgency.getTotalValue()));
      totalCell.setColspan(3);
      detailTable.addCell(totalCell);

      //

      detailTable.setSpacingAfter(10);
      doc.add(detailTable);

      float[] summaryColumnWidths = { 70, 30 }; // percentage
      PdfPTable summaryTable = new PdfPTable(summaryColumnWidths);
      summaryTable.setWidthPercentage(100);
      summaryTable.setSpacingBefore(10);
      summaryTable.setSpacingAfter(10);

      PdfPCell summaryLeftCell = getCell(agency.getFreeText(), Element.ALIGN_LEFT, Rectangle.NO_BORDER, SUMMARY_FONT);
      summaryLeftCell.setVerticalAlignment(Element.ALIGN_TOP);

      summaryTable.addCell(summaryLeftCell);

      float[] summaryDetailColumnWidths = { 50, 50 }; // percentage
      PdfPTable summaryDetailTable = new PdfPTable(summaryDetailColumnWidths);

      PdfPCell summaryLabelCell = getTHCell(messageResources.getMessage("label.summaryUppercase"));
      summaryLabelCell.setColspan(2);
      summaryLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      summaryDetailTable.addCell(summaryLabelCell);

      summaryDetailTable.addCell(blankCell2);

      summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.noOfHours")));
      summaryDetailTable.addCell(getTDCellRight(df.format(invoiceAgency.getNoOfHours())));

      summaryDetailTable.addCell(blankCell2);

      // TODO - currently using the first one !!!

      if (xxx.getChargeRateVatRate().compareTo(new BigDecimal(0)) == 0)
      {

        summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.commission")));
        summaryDetailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getCommissionValue())));
        summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.payRate")));
        summaryDetailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getPayRateValue())));

        if (invoiceAgency.getWtdValue().compareTo(new BigDecimal(0)) > 0)
        {
          summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.wtd")));
          summaryDetailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getWtdValue())));
        }

        if (invoiceAgency.getNiValue().compareTo(new BigDecimal(0)) > 0)
        {
          summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.ni")));
          summaryDetailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getNiValue())));
        }

      }
      else
      {

        summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.chargeRate")));
        summaryDetailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getChargeRateValue())));

      }

      if (invoiceAgency.getExpenseValue().compareTo(new BigDecimal(0)) > 0)
      {
        summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.expenses")));
        summaryDetailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getExpenseValue())));
      }
      summaryDetailTable.addCell(getTHCell(messageResources.getMessage("label.vat")));
      summaryDetailTable.addCell(getTDCellRight(currencySymbol + df.format(invoiceAgency.getVatValue())));

      summaryDetailTable.addCell(blankCell2);

      summaryDetailTable.addCell(getTHCellBig(messageResources.getMessage("label.totalUppercase")));
      summaryDetailTable.addCell(getTDCellBigRightBold(currencySymbol + df.format(invoiceAgency.getTotalValue())));

      PdfPCell summaryRightCell = new PdfPCell(summaryDetailTable);
      summaryRightCell.setBorder(Rectangle.NO_BORDER);
      summaryRightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      summaryRightCell.setVerticalAlignment(Element.ALIGN_TOP);

      summaryTable.addCell(summaryRightCell);

      doc.add(summaryTable);

      float[] footerColumnWidths = { 85, 15 }; // percentage

      PdfPTable footerTable = new PdfPTable(footerColumnWidths);
      footerTable.setWidthPercentage(100);
      footerTable.setExtendLastRow(true);

      Image mmjLogo = null;

      String serverNamePrefix = request.getServerName().substring(0, request.getServerName().indexOf("."));
      serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
      String mmjLogoFilename = "/images/" + serverNamePrefix + "master-logo.jpg";

      try
      {
        mmjLogo = Image.getInstance(FileHandler.getInstance().getFileLocation() + mmjLogoFilename);
        //        mmjLogo = Image.getInstance(request.getSession().getServletContext().getRealPath(mmjLogoFilename));
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

        //     scale to be 30 pixels high         
        Float imageHeight = 30F;
        mmjLogo.scaleAbsoluteHeight(imageHeight);
        mmjLogo.scaleAbsoluteWidth(mmjLogo.getWidth() * (mmjLogo.getScaledHeight() / mmjLogo.getHeight()));

        //     scale to be 100%         
        //                mmjLogo.scalePercent(100);

        PdfPCell footerLogoCell = new PdfPCell(mmjLogo);
        footerLogoCell.setBorder(Rectangle.NO_BORDER);
        footerLogoCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        footerLogoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        footerTable.addCell(footerLogoCell);

      }

      doc.add(footerTable);

    }
    else
    {

      doc.add(new Paragraph(messageResources.getMessage("error.noCanDo")));

    }

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
    //        myCell.setBackgroundColor(new BaseColor(123, 123, 123));
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

