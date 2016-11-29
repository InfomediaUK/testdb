package com.helmet.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.util.MessageResources;

import com.helmet.bean.Agency;
import com.helmet.bean.Client;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.SubcontractInvoiceItemUser;
import com.helmet.bean.SubcontractInvoiceUser;
import com.helmet.bean.nhs.BackingReport;
import com.helmet.bean.nhs.BackingReportLine;
import com.itextpdf.text.BadElementException;
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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfHandler
{

  private static PdfHandler pdfHandler;

  private transient XLogger logger = XLoggerFactory.getXLogger(getClass());

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

  private Font FOOTER_FONT = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.NORMAL, new BaseColor(0, 0, 0));
  
  private DecimalFormat df = new DecimalFormat("#0.00");
  
  private DecimalFormat df3 = new DecimalFormat("#000");

  private String watermarkText;

  public static PdfHandler getInstance()
  {
    if (pdfHandler == null)
    {
      // NOT instantiated yet so create it.
      synchronized (PdfHandler.class)
      {
        // Only ONE thread at a time here!
        if (pdfHandler == null)
        {
          pdfHandler = new PdfHandler();
        }
      }
    }
    return pdfHandler;
  }

  public String getWatermarkText()
  {
    return watermarkText;
  }

  public void setWatermarkText(String waterMark)
  {
    this.watermarkText = waterMark;
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

  private PdfPCell getAdditionalInfoCell(String text)
  {
    return getCell(text, Element.ALIGN_LEFT, Rectangle.NO_BORDER, TD_FONT, DEFAULT_BACKGROUNDCOLOR);
  }

  private Font getSummaryFont()
  {
    return SUMMARY_FONT;
  }

  private Font getFooterFont()
  {
    return FOOTER_FONT;
  }

  public String getNhsInvoiceNumber(Agency agency, String backingReportName)
  {
    String nhsInvoiceNumber = backingReportName;
    return agency.getHasSubcontractors() ? "PJ" + nhsInvoiceNumber : nhsInvoiceNumber;
  }

  public String getNhsInvoiceFileName(Agency agency, NhsBackingReport nhsBackingReport)
  {
    String nhsInvoiceFileName = nhsBackingReport.getName() + ".pdf";
    return agency.getHasSubcontractors() ? "PJ" + nhsInvoiceFileName : nhsInvoiceFileName;
  }

  public String getNhsInvoiceFileFolder(Agency agency)
  {
    return FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + agency.getAgencyId();
  }
  
  public String getNhsInvoiceFolderPath(Agency agency)
  {
    return FileHandler.getInstance().getNhsInvoiceFileLocation() + getNhsInvoiceFileFolder(agency);
  }
  
  public File getNhsInvoiceFile(Agency agency, NhsBackingReport nhsBackingReport)
  {
    String nhsInvoiceFileName = getNhsInvoiceFileName(agency, nhsBackingReport);
    String nhsInvoiceFolderPath = getNhsInvoiceFolderPath(agency);
    return new File(nhsInvoiceFolderPath + "/" + nhsInvoiceFileName);
  }

  public void deleteNhsFile(File nhsFile)
  {
    nhsFile.delete();
  }

  public String writeNhsInvoice(MessageResources messageResources, Client client, Agency agency, NhsBackingReport nhsBackingReport, String nhsLocation, String nhsWard, java.util.Date invoiceDate, String serverName)
    throws Exception
  {
    String fileName = getNhsInvoiceFileName(agency, nhsBackingReport);
    String nhsInvoiceFolderPath = getNhsInvoiceFolderPath(agency);
    String nhsInvoiceFilePath = nhsInvoiceFolderPath + "/" + fileName;
    File nhsInvoiceFile = getNhsInvoiceFile(agency, nhsBackingReport);
    // For Java 7 use Files...
    File folder = new File(nhsInvoiceFolderPath);
    if (!folder.exists())
    {
      folder.mkdirs();
    }
    if (nhsInvoiceFile.exists())
    {
      nhsInvoiceFile.delete();
    }
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

    PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream(nhsInvoiceFilePath));
    pdfWriter.setBoxSize("art", new Rectangle(35, 25, 100, 788));
    PdfPageEvent pdfPageEvent = new PdfPageEvent();
    pdfPageEvent.setFreeText(agency.getFreeText2());
    pdfPageEvent.setFreeTextFont(getFooterFont());
    pdfWriter.setPageEvent(pdfPageEvent);

    doc.open();

    float[] headerColumnWidths = { 40, 60 }; // percentage

    PdfPTable headerTable = new PdfPTable(headerColumnWidths);
    headerTable.setWidthPercentage(100);

    Image agencyInvoiceLogo = null;

    if (StringUtils.isNotEmpty(agency.getInvoiceLogoFilename()))
    {
      try
      {
        String imagePath = FileHandler.getInstance().getFileLocation() + agency.getInvoiceLogoUrl();
        agencyInvoiceLogo = Image.getInstance(imagePath);
      }
      catch (IOException e)
      {
        logger.error("Error getting Invoice Logo: {}", e);
      }
    }

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

    PdfPTable agencyAddressTable = getAgencyAddressTable(messageResources, agency, Element.ALIGN_RIGHT, true);

    PdfPCell agencyAddressCell = new PdfPCell(agencyAddressTable);
    agencyAddressCell.setBorder(Rectangle.NO_BORDER);

    headerTable.addCell(agencyAddressCell);

    headerTable.setSpacingAfter(10);
    doc.add(headerTable);

    float[] clientColumnWidths = { 60, 40 }; // percentage

    PdfPTable clientTable = new PdfPTable(clientColumnWidths);
    clientTable.setWidthPercentage(100);

    PdfPTable clientAddressTable = new PdfPTable(1);

    if (StringUtils.isEmpty(client.getAccountContactName()))
    {
      // No Account Contact Name so use Client Name.
      clientAddressTable.addCell(getCell(client.getName()));
    }
    else
    {
      // Account Contact Name available so use it.
      clientAddressTable.addCell(getCell(client.getAccountContactName()));
    }

    if (StringUtils.isEmpty(client.getAccountContactAddress().getAddress1()))
    {
      // No Account Address so use Client Address.
      if (StringUtils.isNotEmpty(client.getAddress().getAddress1()))
      {
        clientAddressTable.addCell(getCell(client.getAddress().getAddress1()));
      }
      if (StringUtils.isNotEmpty(client.getAddress().getAddress2()))
      {
        clientAddressTable.addCell(getCell(client.getAddress().getAddress2()));
      }
      if (StringUtils.isNotEmpty(client.getAddress().getAddress3()))
      {
        clientAddressTable.addCell(getCell(client.getAddress().getAddress3()));
      }
      if (StringUtils.isNotEmpty(client.getAddress().getAddress4()))
      {
        clientAddressTable.addCell(getCell(client.getAddress().getAddress4()));
      }
      if (StringUtils.isNotEmpty(client.getAddress().getPostalCode()))
      {
        clientAddressTable.addCell(getCell(client.getAddress().getPostalCode()));
      }
    }
    else
    {
      // Account Address available so use it.
      if (StringUtils.isNotEmpty(client.getAccountContactAddress().getAddress1()))
      {
        clientAddressTable.addCell(getCell(client.getAccountContactAddress().getAddress1()));
      }
      if (StringUtils.isNotEmpty(client.getAccountContactAddress().getAddress2()))
      {
        clientAddressTable.addCell(getCell(client.getAccountContactAddress().getAddress2()));
      }
      if (StringUtils.isNotEmpty(client.getAccountContactAddress().getAddress3()))
      {
        clientAddressTable.addCell(getCell(client.getAccountContactAddress().getAddress3()));
      }
      if (StringUtils.isNotEmpty(client.getAccountContactAddress().getAddress4()))
      {
        clientAddressTable.addCell(getCell(client.getAccountContactAddress().getAddress4()));
      }
      if (StringUtils.isNotEmpty(client.getAccountContactAddress().getPostalCode()))
      {
        clientAddressTable.addCell(getCell(client.getAccountContactAddress().getPostalCode()));
      }
    }

    PdfPCell clientAddressCell = new PdfPCell(clientAddressTable);
    clientAddressCell.setBorder(Rectangle.NO_BORDER);

    clientTable.addCell(clientAddressCell);

    PdfPTable invoiceDetailTable = new PdfPTable(1);

    String invoiceDateStr = mdf.format(invoiceDate);
    invoiceDetailTable.addCell(getCellBold(messageResources.getMessage("label.invoiceNo") + " " + getNhsInvoiceNumber(agency, nhsBackingReport.getName()), Element.ALIGN_RIGHT));
    invoiceDetailTable.addCell(getCell(messageResources.getMessage("label.invoiceDate") + " " + invoiceDateStr, Element.ALIGN_RIGHT));

    PdfPCell invoiceDetailCell = new PdfPCell(invoiceDetailTable);
    invoiceDetailCell.setBorder(Rectangle.NO_BORDER);

    clientTable.addCell(invoiceDetailCell);
    clientTable.setSpacingAfter(10);
    doc.add(clientTable);

    PdfPTable detailTable = null;
    if (StringUtils.isEmpty(nhsLocation) && StringUtils.isEmpty(nhsWard))
    {
      // Neither NHS Location or NHS Ward supplied.
      float[] detailColumnWidths = { 20, 80 }; // percentage
      detailTable = new PdfPTable(detailColumnWidths);
      detailTable.addCell(getTHCell(messageResources.getMessage("label.backingReport")));
      detailTable.addCell(getTHCellRight(messageResources.getMessage("label.total")));
      detailTable.addCell(getTDCell(nhsBackingReport.getName()));
      detailTable.addCell(getTDCellRight(currencySymbol + df.format(nhsBackingReport.getAgreedValue())));
    }
    else
    {
      // NHS Location and maybe NHS Ward supplied.
      float[] detailColumnWidths = { 20, 60, 20 }; // percentage
      detailTable = new PdfPTable(detailColumnWidths);
      detailTable.addCell(getTHCell(messageResources.getMessage("label.backingReport")));
      if (StringUtils.isEmpty(nhsWard))
      {
        detailTable.addCell(getTHCell(messageResources.getMessage("label.location")));
      }
      else
      {
        detailTable.addCell(getTHCell(messageResources.getMessage("label.location") + " - " + messageResources.getMessage("label.ward")));
      }
      detailTable.addCell(getTHCellRight(messageResources.getMessage("label.total")));
      detailTable.addCell(getTDCell(nhsBackingReport.getName()));
      if (StringUtils.isEmpty(nhsWard))
      {
        detailTable.addCell(getTDCell(nhsLocation));
      }
      else
      {
        detailTable.addCell(getTDCell(nhsLocation + ", " + nhsWard));
      }
      detailTable.addCell(getTDCellRight(currencySymbol + df.format(nhsBackingReport.getAgreedValue())));
    }

    detailTable.setWidthPercentage(100);

    detailTable.setSpacingAfter(10);
    doc.add(detailTable);


    float[] summaryColumnWidths = { 100 }; // percentage
    PdfPTable summaryTable = new PdfPTable(summaryColumnWidths);
    summaryTable.setWidthPercentage(100);
    summaryTable.setSpacingBefore(10);
    summaryTable.setSpacingAfter(10);

    PdfPCell summaryLeftCell = getCell(agency.getFreeText(), Element.ALIGN_LEFT, Rectangle.NO_BORDER, getSummaryFont());

    summaryLeftCell.setVerticalAlignment(Element.ALIGN_TOP);

    summaryTable.addCell(summaryLeftCell);

    doc.add(summaryTable);

    doc.add(buildPaymentTermsTable(messageResources));
    doc.add(buildPaymentInstructionsTable(messageResources));
    doc.add(buildBankDetailsTable(messageResources));
    doc.add(buildInitiativeTable(messageResources));
    doc.add(buildFooterTable(messageResources, serverName));

    doc.newPage();

    doc.close();
    
    return fileName;
  }

  public String writeSubcontractInvoice(MessageResources messageResources, Agency toAgency, Agency fromAgency, NhsBackingReport nhsBackingReport, String nhsLocation, String nhsWard, java.util.Date invoiceDate)
  throws Exception
  {
    String fileName = nhsBackingReport.getName() + "SUB.pdf";
    String subcontractInvoiceFolderPath = FileHandler.getInstance().getNhsInvoiceFileLocation() + 
      FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + fromAgency.getAgencyId() + "/a" + toAgency.getAgencyId();
    String subcontractInvoiceFilePath = subcontractInvoiceFolderPath + "/" + fileName;
    File subcontractInvoiceFile = new File(subcontractInvoiceFilePath);
    // For Java 7 use Files...
    File folder = new File(subcontractInvoiceFolderPath);
    if (!folder.exists())
    {
      folder.mkdirs();
    }
    if (subcontractInvoiceFile.exists())
    {
      subcontractInvoiceFile.delete();
    }
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
  
    PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream(subcontractInvoiceFilePath));
    pdfWriter.setBoxSize("art", new Rectangle(35, 25, 100, 788));
    PdfPageEvent pdfPageEvent = new PdfPageEvent();
    pdfPageEvent.setFreeText(fromAgency.getFreeText2());
    pdfPageEvent.setFreeTextFont(getFooterFont());
    pdfWriter.setPageEvent(pdfPageEvent);
  
    doc.open();
  
    float[] headerColumnWidths = { 40, 60 }; // percentage
  
    PdfPTable headerTable = new PdfPTable(headerColumnWidths);
    headerTable.setWidthPercentage(100);
  
    Image fromAgencyInvoiceLogo = null;
  
    if (StringUtils.isNotEmpty(fromAgency.getInvoiceLogoFilename()))
    {
      try
      {
        String imagePath = FileHandler.getInstance().getFileLocation() + fromAgency.getInvoiceLogoUrl();
        fromAgencyInvoiceLogo = Image.getInstance(imagePath);
      }
      catch (IOException e)
      {
        logger.error("Error getting Invoice Logo: {}", e);
      }
    }
  
    PdfPCell logoCell = new PdfPCell(new Paragraph(fromAgency.getName()));
  
    if (fromAgencyInvoiceLogo != null)
    {
  
      //     scale to be 40 pixels high
      //              Float imageHeight = 40F;
      //              mmjLogo.scaleAbsoluteHeight(imageHeight);
      //              mmjLogo.scaleAbsoluteWidth(mmjLogo.width() * (mmjLogo.scaledHeight() / mmjLogo.height()));
  
      //     scale to be 100%
      //                mmjLogo.scalePercent(100);
  
      logoCell = new PdfPCell(fromAgencyInvoiceLogo);
    }
  
    logoCell.setVerticalAlignment(Element.ALIGN_TOP);
    logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
    logoCell.setBorder(Rectangle.NO_BORDER);
  
    headerTable.addCell(logoCell);
  
    PdfPTable fromAgencyAddressTable = getAgencyAddressTable(messageResources, fromAgency, Element.ALIGN_RIGHT, true);
  
    PdfPCell fromAgencyAddressCell = new PdfPCell(fromAgencyAddressTable);
    fromAgencyAddressCell.setBorder(Rectangle.NO_BORDER);
  
    headerTable.addCell(fromAgencyAddressCell);
  
    headerTable.setSpacingAfter(10);
    doc.add(headerTable);
  
    float[] toAgencyColumnWidths = { 60, 40 }; // percentage
  
    PdfPTable toAgencyTable = new PdfPTable(toAgencyColumnWidths);
    toAgencyTable.setWidthPercentage(100);
  
    PdfPTable toAgencyAddressTable =  getAgencyAddressTable(messageResources, toAgency, Element.ALIGN_LEFT, false);;
  
    PdfPCell toAgencyAddressCell = new PdfPCell(toAgencyAddressTable);
    toAgencyAddressCell.setBorder(Rectangle.NO_BORDER);
  
    toAgencyTable.addCell(toAgencyAddressCell);
  
    PdfPTable invoiceDetailTable = new PdfPTable(1);
  
    String invoiceDateStr = mdf.format(invoiceDate);
    invoiceDetailTable.addCell(getCellBold(messageResources.getMessage("label.invoiceNo") + " " + nhsBackingReport.getName() + "SUB", Element.ALIGN_RIGHT));
    invoiceDetailTable.addCell(getCell(messageResources.getMessage("label.invoiceDate") + " " + invoiceDateStr, Element.ALIGN_RIGHT));
  
    PdfPCell invoiceDetailCell = new PdfPCell(invoiceDetailTable);
    invoiceDetailCell.setBorder(Rectangle.NO_BORDER);
  
    toAgencyTable.addCell(invoiceDetailCell);
    toAgencyTable.setSpacingAfter(10);
    doc.add(toAgencyTable);
  
    PdfPTable detailTable = null;
    if (StringUtils.isEmpty(nhsLocation) && StringUtils.isEmpty(nhsWard))
    {
      // Neither NHS Location or NHS Ward supplied.
      float[] detailColumnWidths = { 20, 80 }; // percentage
      detailTable = new PdfPTable(detailColumnWidths);
      detailTable.addCell(getTHCell(messageResources.getMessage("label.backingReport")));
      detailTable.addCell(getTHCellRight(messageResources.getMessage("label.total")));
      detailTable.addCell(getTDCell(nhsBackingReport.getName()));
      detailTable.addCell(getTDCellRight(currencySymbol + df.format(nhsBackingReport.getSubcontractValue())));
    }
    else
    {
      // NHS Location and maybe NHS Ward supplied.
      float[] detailColumnWidths = { 20, 60, 20 }; // percentage
      detailTable = new PdfPTable(detailColumnWidths);
      detailTable.addCell(getTHCell(messageResources.getMessage("label.backingReport")));
      if (StringUtils.isEmpty(nhsWard))
      {
        detailTable.addCell(getTHCell(messageResources.getMessage("label.location")));
      }
      else
      {
        detailTable.addCell(getTHCell(messageResources.getMessage("label.location") + " - " + messageResources.getMessage("label.ward")));
      }
      detailTable.addCell(getTHCellRight(messageResources.getMessage("label.total")));
      detailTable.addCell(getTDCell(nhsBackingReport.getName()));
      if (StringUtils.isEmpty(nhsWard))
      {
        detailTable.addCell(getTDCell(nhsLocation));
      }
      else
      {
        detailTable.addCell(getTDCell(nhsLocation + ", " + nhsWard));
      }
      detailTable.addCell(getTDCellRight(currencySymbol + df.format(nhsBackingReport.getSubcontractValue())));
    }
  
    detailTable.setWidthPercentage(100);
  
    detailTable.setSpacingAfter(10);
    doc.add(detailTable);
  
  
    float[] summaryColumnWidths = { 100 }; // percentage
    PdfPTable summaryTable = new PdfPTable(summaryColumnWidths);
    summaryTable.setWidthPercentage(100);
    summaryTable.setSpacingBefore(10);
    summaryTable.setSpacingAfter(10);
  
    PdfPCell summaryLeftCell = getCell(fromAgency.getFreeText(), Element.ALIGN_LEFT, Rectangle.NO_BORDER, getSummaryFont());
  
    summaryLeftCell.setVerticalAlignment(Element.ALIGN_TOP);
  
    summaryTable.addCell(summaryLeftCell);
  
    doc.add(summaryTable);
  
    doc.newPage();
  
    doc.close();
    
    return fileName;
  }

  public String writeSubcontractInvoice(MessageResources messageResources, Agency toAgency, Agency fromAgency, SubcontractInvoiceUser subcontractInvoiceUser, String serverName)
  throws Exception
  {
    String invoiceNumber = subcontractInvoiceUser.getSubcontractInvoiceNumber();
    String relatedInvoiceNumber = subcontractInvoiceUser.getRelatedSubcontractInvoiceNumber();
    String fileName = invoiceNumber + ".pdf";
    String subcontractInvoiceFolderPath = FileHandler.getInstance().getNhsInvoiceFileLocation() + 
      FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + fromAgency.getAgencyId() + "/a" + toAgency.getAgencyId();
    String subcontractInvoiceFilePath = subcontractInvoiceFolderPath + "/" + fileName;
    File subcontractInvoiceFile = new File(subcontractInvoiceFilePath);
    // For Java 7 use Files...
    File folder = new File(subcontractInvoiceFolderPath);
    if (!folder.exists())
    {
      folder.mkdirs();
    }
    if (subcontractInvoiceFile.exists())
    {
      subcontractInvoiceFile.delete();
    }
    SimpleDateFormat ddf = new SimpleDateFormat(messageResources.getMessage("format.dayDateFormat"));
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
  
    PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream(subcontractInvoiceFilePath));
    if (StringUtils.isNotEmpty(getWatermarkText()))
    {
      pdfWriter.setPageEvent(new PdfWatermark(getWatermarkText()));
    }
    pdfWriter.setBoxSize("art", new Rectangle(35, 25, 100, 788));
    PdfPageEvent pdfPageEvent = new PdfPageEvent();
    pdfPageEvent.setFreeText(fromAgency.getFreeText2());
    pdfPageEvent.setFreeTextFont(getFooterFont());
    pdfWriter.setPageEvent(pdfPageEvent);
  
    doc.open();
  
    float[] headerColumnWidths = { 40, 60 }; // percentage
  
    PdfPTable headerTable = new PdfPTable(headerColumnWidths);
    headerTable.setWidthPercentage(100);
  
    Image fromAgencyInvoiceLogo = null;
  
    if (StringUtils.isNotEmpty(fromAgency.getInvoiceLogoFilename()))
    {
      try
      {
        String imagePath = FileHandler.getInstance().getFileLocation() + fromAgency.getInvoiceLogoUrl();
        fromAgencyInvoiceLogo = Image.getInstance(imagePath);
      }
      catch (IOException e)
      {
        logger.error("Error getting Invoice Logo: {}", e);
      }
    }
  
    PdfPCell logoCell = new PdfPCell(new Paragraph(fromAgency.getName()));
  
    if (fromAgencyInvoiceLogo != null)
    {
  
      //     scale to be 40 pixels high
      //              Float imageHeight = 40F;
      //              mmjLogo.scaleAbsoluteHeight(imageHeight);
      //              mmjLogo.scaleAbsoluteWidth(mmjLogo.width() * (mmjLogo.scaledHeight() / mmjLogo.height()));
  
      //     scale to be 100%
      //                mmjLogo.scalePercent(100);
  
      logoCell = new PdfPCell(fromAgencyInvoiceLogo);
    }
  
    logoCell.setVerticalAlignment(Element.ALIGN_TOP);
    logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
    logoCell.setBorder(Rectangle.NO_BORDER);
  
    headerTable.addCell(logoCell);
  
    PdfPTable fromAgencyAddressTable = getAgencyAddressTable(messageResources, fromAgency, Element.ALIGN_RIGHT, true);
  
    PdfPCell fromAgencyAddressCell = new PdfPCell(fromAgencyAddressTable);
    fromAgencyAddressCell.setBorder(Rectangle.NO_BORDER);
  
    headerTable.addCell(fromAgencyAddressCell);
  
    headerTable.setSpacingAfter(10);
    doc.add(headerTable);
  
    float[] toAgencyColumnWidths = { 60, 40 }; // percentage
  
    PdfPTable toAgencyTable = new PdfPTable(toAgencyColumnWidths);
    toAgencyTable.setWidthPercentage(100);
  
    PdfPTable toAgencyAddressTable =  getAgencyAddressTable(messageResources, toAgency, Element.ALIGN_LEFT, false);;
  
    PdfPCell toAgencyAddressCell = new PdfPCell(toAgencyAddressTable);
    toAgencyAddressCell.setBorder(Rectangle.NO_BORDER);
  
    toAgencyTable.addCell(toAgencyAddressCell);
  
    PdfPTable invoiceDetailTable = new PdfPTable(1);
  
    String invoiceDateStr = mdf.format(subcontractInvoiceUser.getDate());
    if (subcontractInvoiceUser.getValue().compareTo(new BigDecimal(0)) > 0)
    {
      // Positive: Invoice.
      invoiceDetailTable.addCell(getCellBold(messageResources.getMessage("label.invoiceNo") + " " + invoiceNumber, Element.ALIGN_RIGHT));
      invoiceDetailTable.addCell(getCell(messageResources.getMessage("label.invoiceDate") + " " + invoiceDateStr, Element.ALIGN_RIGHT));
    } 
    else
    {
      // Negative: Credit Note.
      invoiceDetailTable.addCell(getCellBold(messageResources.getMessage("label.creditNo") + " " + invoiceNumber, Element.ALIGN_RIGHT));
      invoiceDetailTable.addCell(getCellBold(messageResources.getMessage("label.relatedInvoiceNo") + " " + relatedInvoiceNumber, Element.ALIGN_RIGHT));
      invoiceDetailTable.addCell(getCell(messageResources.getMessage("label.creditDate") + " " + invoiceDateStr, Element.ALIGN_RIGHT));
    } 
      
    PdfPCell invoiceDetailCell = new PdfPCell(invoiceDetailTable);
    invoiceDetailCell.setBorder(Rectangle.NO_BORDER);
  
    toAgencyTable.addCell(invoiceDetailCell);
    toAgencyTable.setSpacingAfter(10);
    doc.add(toAgencyTable);
  
    float[] detailColumnWidths = { 60, 20, 20 }; // percentage
    PdfPTable detailTable = new PdfPTable(detailColumnWidths);
    String noOfBookings = subcontractInvoiceUser.getListSubcontractInvoiceItemUser().size() == 1 ? "1 Booking" : subcontractInvoiceUser.getListSubcontractInvoiceItemUser().size() + " Bookings";
    detailTable.addCell(getTHCell(messageResources.getMessage("label.trust") + ", " + messageResources.getMessage("label.hospital") + ", " + messageResources.getMessage("label.ward") + " (" + noOfBookings + ")"));
    detailTable.addCell(getTHCell(messageResources.getMessage("label.period")));
    detailTable.addCell(getTHCell(messageResources.getMessage("label.jobProfile") + " - " + messageResources.getMessage("label.assignment")));
    detailTable.addCell(getTDCell(subcontractInvoiceUser.getClientNhsName() + ", " + 
                                  subcontractInvoiceUser.getSiteNhsLocation() + ", " + 
                                  subcontractInvoiceUser.getLocationNhsWard()
                                  ));
    detailTable.addCell(getTDCell(mdf.format(subcontractInvoiceUser.getStartDate()) + " - " + mdf.format(subcontractInvoiceUser.getEndDate()))); 
    detailTable.addCell(getTDCell(subcontractInvoiceUser.getJobProfileName() + " (" + subcontractInvoiceUser.getJobFamilyCode() + "." + subcontractInvoiceUser.getJobSubFamilyCode() + ") - " + subcontractInvoiceUser.getJobProfileNhsAssignment())); 
    detailTable.setWidthPercentage(100);
    detailTable.setSpacingAfter(10);
    doc.add(detailTable);
 
    // 10, 15, 10, 10, 10, 30, 15
    float[] shiftColumnWidths = { 10, 12, 10, 9, 9, 30, 10, 10 }; // percentage
    PdfPTable shiftTable = new PdfPTable(shiftColumnWidths);
    shiftTable.addCell(getTHCell(messageResources.getMessage("label.bankRequest")));
    shiftTable.addCell(getTHCell(messageResources.getMessage("label.date")));
    shiftTable.addCell(getTHCell(messageResources.getMessage("label.start")));
    shiftTable.addCell(getTHCell(messageResources.getMessage("label.end")));
    shiftTable.addCell(getTHCell(messageResources.getMessage("label.hours")));
    shiftTable.addCell(getTHCell(messageResources.getMessage("label.worker")));
    shiftTable.addCell(getTHCell(messageResources.getMessage("label.rate")));
    shiftTable.addCell(getTHCellRight(messageResources.getMessage("label.value")));

    PdfPCell wideCell = null;
    for (SubcontractInvoiceItemUser subcontractInvoiceItemUser : subcontractInvoiceUser.getListSubcontractInvoiceItemUser())
    {
      shiftTable.addCell(getTDCell(subcontractInvoiceItemUser.getBankReqNum())); 
      shiftTable.addCell(getTDCell(ldf.format(subcontractInvoiceItemUser.getDate()))); 
      shiftTable.addCell(getTDCell(tdf.format(subcontractInvoiceItemUser.getWorkedStartTime()))); 
      shiftTable.addCell(getTDCell(tdf.format(subcontractInvoiceItemUser.getWorkedEndTime()))); 
      shiftTable.addCell(getTDCell(df.format(subcontractInvoiceItemUser.getWorkedNoOfHours()))); 
      shiftTable.addCell(getTDCell(subcontractInvoiceItemUser.getStaffName())); 
      shiftTable.addCell(getTDCellRight(currencySymbol + df.format(subcontractInvoiceItemUser.getRate()))); 
      shiftTable.addCell(getTDCellRight(currencySymbol + df.format(subcontractInvoiceItemUser.getValue()))); 
      if (StringUtils.isNotEmpty(subcontractInvoiceItemUser.getComment()))
      {
        wideCell = getTDCell(subcontractInvoiceItemUser.getComment());
        wideCell.setColspan(8);
        shiftTable.addCell(wideCell); 
      }
    }
    if (subcontractInvoiceUser.getValue().compareTo(new BigDecimal(0)) > 0)
    {
      // Positive: Invoice.
      wideCell = getTDCellRightBold("Invoice Total");
    }
    else
    {
      // Negative: Credit Note.
      wideCell = getTDCellRightBold("Credit Note Total");
    }
    wideCell.setColspan(7);
    shiftTable.addCell(wideCell); 
    shiftTable.addCell(getTDCellRightBold(currencySymbol + df.format(subcontractInvoiceUser.getValue()))); 

    shiftTable.setWidthPercentage(100);
    shiftTable.setSpacingAfter(10);
    doc.add(shiftTable);

    float[] notesColumnWidths = { 100 }; // percentage
    if (StringUtils.isNotEmpty(subcontractInvoiceUser.getNotes()))
    {
      PdfPTable notesTable = new PdfPTable(notesColumnWidths);
      notesTable.addCell(getTHCell(messageResources.getMessage("label.notes")));
      notesTable.addCell(getTDCell(subcontractInvoiceUser.getNotes()));
      notesTable.setWidthPercentage(100);
      notesTable.setSpacingAfter(10);
      doc.add(notesTable);
    }
    
    if (subcontractInvoiceUser.getValue().compareTo(new BigDecimal(0)) > 0)
    {
      // Positive: Invoice.
      doc.add(buildPaymentTermsTable(messageResources));
      doc.add(buildPaymentInstructionsTable(messageResources));
      doc.add(buildBankDetailsTable(messageResources));
    }    
    doc.add(buildInitiativeTable(messageResources));
    doc.add(buildFooterTable(messageResources, serverName));
    doc.newPage();
  
    doc.close();
    
    return fileName;
  }

  private PdfPTable getAgencyAddressTable(MessageResources messageResources, Agency agency, int alignment, Boolean allDetails)
  {
    PdfPTable agencyAddressTable = new PdfPTable(1);

    if (allDetails)
    {
      agencyAddressTable.addCell(getCellBold(messageResources.getMessage("label.vatRegNo") + " " + agency.getVatNumber(), alignment));
    }    
    agencyAddressTable.addCell(getCell(agency.getName(), alignment));

    if (StringUtils.isNotEmpty(agency.getAddress().getAddress1()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getAddress1(), alignment));
    }
    if (StringUtils.isNotEmpty(agency.getAddress().getAddress2()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getAddress2(), alignment));
    }
    if (StringUtils.isNotEmpty(agency.getAddress().getAddress3()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getAddress3(), alignment));
    }
    if (StringUtils.isNotEmpty(agency.getAddress().getAddress4()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getAddress4(), alignment));
    }
    if (StringUtils.isNotEmpty(agency.getAddress().getPostalCode()))
    {
      agencyAddressTable.addCell(getCell(agency.getAddress().getPostalCode(), alignment));
    }
    if (allDetails)
    {
      if (StringUtils.isNotEmpty(agency.getTelephoneNumber()))
      {
        agencyAddressTable.addCell(getCell(messageResources.getMessage("label.tel") + " " + agency.getTelephoneNumber(), alignment));
      }
      if (StringUtils.isNotEmpty(agency.getFaxNumber()))
      {
        agencyAddressTable.addCell(getCell(messageResources.getMessage("label.fax") + " " + agency.getFaxNumber(), alignment));
      }
    }    
    return agencyAddressTable;
  }

  private PdfPTable buildPaymentTermsTable(MessageResources messageResources)
  {
    float[] paymentTermsColumnWidths = { 100 }; // percentage
    PdfPTable paymentTermsTable = new PdfPTable(paymentTermsColumnWidths);
    paymentTermsTable.setWidthPercentage(100);
    paymentTermsTable.setSpacingAfter(10);
    paymentTermsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.paymentTerms")));
    return paymentTermsTable;
  }

  private PdfPTable buildPaymentInstructionsTable(MessageResources messageResources)
  {
    float[] paymentInstructionsColumnWidths = { 100 }; // percentage
    PdfPTable paymentInstructionsTable = new PdfPTable(paymentInstructionsColumnWidths);
    paymentInstructionsTable.setWidthPercentage(100);
    paymentInstructionsTable.setSpacingAfter(10);
    paymentInstructionsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.remitFundsTo")));
    return paymentInstructionsTable;
  }
  
  private PdfPTable buildBankDetailsTable(MessageResources messageResources)
  {
    float[] bankDetailsColumnWidths = { 50, 50 }; // percentage
    PdfPTable bankDetailsTable = new PdfPTable(bankDetailsColumnWidths);
    bankDetailsTable.setWidthPercentage(30);
    bankDetailsTable.setHorizontalAlignment(Element.ALIGN_LEFT);
    bankDetailsTable.setSpacingAfter(10);
    bankDetailsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.bankName")));
    bankDetailsTable.addCell(getAdditionalInfoCell(""));
    bankDetailsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.accountName")));
    bankDetailsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("label.accountName")));
    bankDetailsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.sortCode")));
    bankDetailsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("label.sortCode")));
    bankDetailsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.accountNumber")));
    bankDetailsTable.addCell(getAdditionalInfoCell(messageResources.getMessage("label.accountNumber")));
    return bankDetailsTable;
  }

  private PdfPTable buildInitiativeTable(MessageResources messageResources)
  {
    float[] initiativeColumnWidths = { 100 }; // percentage
    PdfPTable initiativeTable = new PdfPTable(initiativeColumnWidths);
    initiativeTable.setWidthPercentage(100);
    initiativeTable.setSpacingAfter(10);
    initiativeTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.remittanceAdviceEmailAddress") + " " + messageResources.getMessage("label.remittanceAdviceEmailAddress")));
    initiativeTable.addCell(getAdditionalInfoCell(""));
    initiativeTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.greenInitiative")));
    initiativeTable.addCell(getAdditionalInfoCell(messageResources.getMessage("text.subcontractInvoice.greenInitiativeLineCharge") + " " + messageResources.getMessage("label.greenInitiativeLineCharge")));
    return initiativeTable;
  }

  private PdfPTable buildFooterTable(MessageResources messageResources, String serverName)
  {
    float[] footerColumnWidths = { 85, 15 }; // percentage

    PdfPTable footerTable = new PdfPTable(footerColumnWidths);
    footerTable.setWidthPercentage(100);
    footerTable.setExtendLastRow(true);

    Image mmjLogo = null;

    String serverNamePrefix = serverName.substring(0, serverName.indexOf("."));
    serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
    String mmjLogoFilename = "/images/" + serverNamePrefix + "master-logo.jpg";

    try
    {
      mmjLogo = Image.getInstance(FileHandler.getInstance().getFileLocation() + mmjLogoFilename);
      //        mmjLogo = Image.getInstance(request.getSession().getServletContext().getRealPath(mmjLogoFilename));
    }
    catch (BadElementException|IOException e)
    {
      logger.error("Error getting Invoice Logo: {}", e);
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

    return footerTable;
  }
  
  // This is basically a PDF version of a spreadsheet.
  public String writeNhsBackingReportPdf(Agency agency, BackingReport backingReport) 
    throws Exception
  {
    String fileName = "NHS Backing Report " + backingReport.getName() + ".pdf";
    String nhsBackingReportFolderPath = FileHandler.getInstance().getNhsBackingReportFileLocation() + FileHandler.getInstance().getNhsBackingReportFileFolder();
    String nhsBackingReportFilePath = nhsBackingReportFolderPath + "/a" + agency.getAgencyId() + "/" + fileName;
    File nhsBackingReportFile = new File(nhsBackingReportFilePath);
    // For Java 7 use Files...
    File folder = new File(nhsBackingReportFolderPath);
    if (!folder .exists())
    {
      folder.mkdirs();
    }
    if (nhsBackingReportFile.exists())
    {
      nhsBackingReportFile.delete();
    }
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setLenient(false);
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    timeFormat.setLenient(false);
    Font fontBold = new Font(FontFamily.HELVETICA, 7, Font.BOLD, BaseColor.BLACK);
    Font fontNormal = new Font(FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.BLACK);
    Document document = new Document(PageSize.A3.rotate(), 0, 0, 0, 0);
    float[] columnWidths = new float[] {7f, 6f, 20f, 24f, 10f, 7f, 4f, 4f, 9f, 4f, 4f, 4f, 9f, 4f, 7f, 6f, 6f};
    PdfWriter.getInstance(document, new FileOutputStream(nhsBackingReportFilePath));
    try 
    {
      document.open();
      PdfPTable table = new PdfPTable(17);
      table.setWidths(columnWidths);
      PdfPCell cell;
      // Backing Report Name Line
      cell = new PdfPCell(new Phrase("Backing Report " + backingReport.getName(), fontBold));
      cell.setColspan(17);
      table.addCell(cell);
  
      // Column Headings.
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getDate(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getRefNum(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getAgencyWorkerName(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getTrust(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getWard(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getAssignment(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      // Contract spans 4 cells
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContract(), fontBold));
      cell.setColspan(4);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);
      // Actual spans 4 cells
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActual(), fontBold));
      cell.setColspan(4);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getCommission(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getTotalCost(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getRate(), fontBold));
      cell.setRowspan(2);
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContractStart(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContractEnd(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContractBreakInMinutes(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContractTotal(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActualStart(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActualEnd(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActualBreakInMinutes(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActualTotal(), fontBold));
      table.addCell(cell);
      
      for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
      {
        if (agency.getHasSubcontractors())
        {
          // 4SW.
          if (backingReportLine.getAgencyWorkerNameValid() && backingReportLine.notAlreadyInvoiced())
          {
            // 4SW workers will have had their AgencyWorkerName flagged as invalid. We do NOT want them to show...
            writeBackingReportLine(agency, table, backingReportLine, dateFormat, timeFormat, fontNormal);
          } 
        }
        else
        {
          // NOT 4SW, normal process.
          writeBackingReportLine(agency, table, backingReportLine, dateFormat, timeFormat, fontNormal);
        }
      }
      // Total Line
      cell = new PdfPCell();
      cell.setColspan(14);
      table.addCell(cell);
      // 4SW does NOT get to see Commission.
      cell = new PdfPCell(new Phrase(agency.getHasSubcontractors() ? "" : df.format(backingReport.getTotalCommission()), fontBold));
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(df.format(backingReport.getTotalCost()), fontBold));
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(df.format(backingReport.getGrandTotalCost()), fontBold));
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      table.addCell(cell);
  
      document.add(table);
    }
    finally
    {
      document.close();
    }
    return fileName;
  }

  public String getNhsBackingReportFolderPath()
  {
    return FileHandler.getInstance().getNhsBackingReportFileLocation() + FileHandler.getInstance().getNhsBackingReportFileFolder();
  }
  
  public String getNhsBackingReportFullFolderPath(Agency agency)
  {
    return getNhsBackingReportFolderPath() + "/a" + agency.getAgencyId() + "/";
  }
  
  public String getNhsBackingReportRejectedFilePath(Agency agency, BackingReport backingReport)
  {
    return getNhsBackingReportFolderPath() + "/a" + agency.getAgencyId() + "/" + getNhsBackingReportRejectedFileName(backingReport);
  }
  
  public String getNhsBackingReportRejectedFileName(BackingReport backingReport)
  {
    return "NHS Backing Report " + backingReport.getName() + " Rejected" + ".pdf";
  }
  
  public String getNhsBackingReportRejectedFolderPath(Agency agency, BackingReport backingReport)
  {
    String fileName = getNhsBackingReportRejectedFileName(backingReport);
    String nhsBackingReportFolderPath = getNhsBackingReportFolderPath();
    String nhsBackingReportFilePath = nhsBackingReportFolderPath + "/a" + agency.getAgencyId() + "/" + fileName;
    return nhsBackingReportFilePath;
  }
  
  // These are the Backing Report Lines that were rejected. Either because they are NOT a subcontracted worker or the line has already been invoiced.
  public String writeNhsBackingReportRejectedPdf(Agency agency, BackingReport backingReport) 
    throws Exception
  {
    String fileName = getNhsBackingReportRejectedFileName(backingReport);
    String nhsBackingReportFilePath = getNhsBackingReportRejectedFilePath(agency, backingReport);
    File nhsBackingReportFile = new File(nhsBackingReportFilePath);
    // For Java 7 use Files...
    File folder = new File(getNhsBackingReportFolderPath());
    if (!folder .exists())
    {
      folder.mkdirs();
    }
    if (nhsBackingReportFile.exists())
    {
      nhsBackingReportFile.delete();
    }
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setLenient(false);
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    timeFormat.setLenient(false);
    Font fontBold = new Font(FontFamily.HELVETICA, 7, Font.BOLD, BaseColor.BLACK);
    Font fontNormal = new Font(FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.BLACK);
    Document document = new Document(PageSize.A3.rotate(), 0, 0, 0, 0);
    float[] columnWidths = new float[] {7f, 6f, 20f, 24f, 10f, 7f, 4f, 4f, 9f, 4f, 4f, 4f, 9f, 4f, 7f, 6f, 6f};
    PdfWriter.getInstance(document, new FileOutputStream(nhsBackingReportFilePath));
    try 
    {
      document.open();
      PdfPTable table = new PdfPTable(17);
      table.setWidths(columnWidths);
      PdfPCell cell;
      // Backing Report Name Line
      cell = new PdfPCell(new Phrase("Backing Report " + backingReport.getName() + " *** REJECTED LINES ***", fontBold));
      cell.setColspan(17);
      table.addCell(cell);
  
      // Column Headings.
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getDate(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getRefNum(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getAgencyWorkerName(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getTrust(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getWard(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getAssignment(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      // Contract spans 4 cells
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContract(), fontBold));
      cell.setColspan(4);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);
      // Actual spans 4 cells
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActual(), fontBold));
      cell.setColspan(4);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase("Invoice", fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getTotalCost(), fontBold));
      cell.setRowspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getRate(), fontBold));
      cell.setRowspan(2);
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContractStart(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContractEnd(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContractBreakInMinutes(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getContractTotal(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActualStart(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActualEnd(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActualBreakInMinutes(), fontBold));
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(backingReport.getBackingReportColumnHeadings().getActualTotal(), fontBold));
      table.addCell(cell);
      BigDecimal totalCost = new BigDecimal(0);
      for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
      {
        if (agency.getHasSubcontractors())
        {
          // 4SW.
          if (backingReportLine.getAgencyWorkerNameNotValid())
          {
            // Not a subcontracted worker.
            backingReportLine.setAgencyWorkerName(backingReportLine.getAgencyWorkerName().toUpperCase());
            writeBackingReportLine(agency, table, backingReportLine, dateFormat, timeFormat, fontNormal);
            totalCost = totalCost.add(backingReportLine.getTotalCost());
          }
          else
          {
            if (backingReportLine.getAlreadyInvoiced())
            {
              // Already has a subcontract invoice.
              writeBackingReportLine(agency, table, backingReportLine, dateFormat, timeFormat, fontNormal);
              totalCost = totalCost.add(backingReportLine.getTotalCost());
            }
          }
        }
      }
      // Total Line
      cell = new PdfPCell();
      cell.setColspan(14);
      table.addCell(cell);
      // 4SW does NOT get to see Commission.
      cell = new PdfPCell(new Phrase(agency.getHasSubcontractors() ? "" : df.format(backingReport.getTotalCommission()), fontBold));
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(df.format(totalCost), fontBold));
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(df.format(totalCost), fontBold));
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      table.addCell(cell);
  
      document.add(table);
    }
    finally
    {
      document.close();
    }
    return fileName;
  }

  private void writeBackingReportLine(Agency agency, PdfPTable table, BackingReportLine backingReportLine, DateFormat dateFormat, DateFormat timeFormat, Font fontNormal)
  {
    PdfPCell cell;
    table.addCell(new Phrase(dateFormat.format(backingReportLine.getDate()), fontNormal));
    table.addCell(new Phrase(backingReportLine.getBankReqNum(), fontNormal));
    table.addCell(new Phrase(backingReportLine.getAgencyWorkerName(), fontNormal));
    table.addCell(new Phrase(backingReportLine.getTrust(), fontNormal));
    table.addCell(new Phrase(backingReportLine.getHospital() + "\n" + backingReportLine.getWard(), fontNormal));
    table.addCell(new Phrase(backingReportLine.getAssignment(), fontNormal));
    table.addCell(new Phrase(timeFormat.format(backingReportLine.getContractShift().getStartTime()), fontNormal));
    table.addCell(new Phrase(timeFormat.format(backingReportLine.getContractShift().getEndTime()), fontNormal));
    table.addCell(new Phrase(df.format(backingReportLine.getContractShift().getBreakMinutes()), fontNormal));
    table.addCell(new Phrase(backingReportLine.getContractShift().getWorkedTime(), fontNormal));
    table.addCell(new Phrase(timeFormat.format(backingReportLine.getActualShift().getStartTime()), fontNormal));
    table.addCell(new Phrase(timeFormat.format(backingReportLine.getActualShift().getEndTime()), fontNormal));
    table.addCell(new Phrase(df.format(backingReportLine.getActualShift().getBreakMinutes()), fontNormal));
    table.addCell(new Phrase(backingReportLine.getActualShift().getWorkedTime(), fontNormal));
    if (agency.getHasSubcontractors())
    {
      // 4SW
      if (backingReportLine.getAlreadyInvoiced())
      {
        cell = new PdfPCell(new Phrase(backingReportLine.getSubcontractInvoiceNumber(), fontNormal));
      }
      else
      {
        cell = new PdfPCell(new Phrase("", fontNormal));
      }
    }
    else
    {
      cell = new PdfPCell(new Phrase(df.format(backingReportLine.getCommission()), fontNormal));
    }
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(df.format(backingReportLine.getTotalCost()), fontNormal));
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(backingReportLine.getRate(), fontNormal));
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell(cell);
  }
}

