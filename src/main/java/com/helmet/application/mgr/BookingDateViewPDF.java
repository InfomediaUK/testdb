package com.helmet.application.mgr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.BookingDateUserApplicantEntity;
import com.helmet.bean.Client;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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

public class BookingDateViewPDF extends MgrAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  Float FONT_SIZE = 10.0F;

  Float MINIMUM_ROW_HEIGHT = 18.0F;

  Float BORDER_WIDTH = 1.0F;

  BaseColor BORDER_COLOR = new BaseColor(0xE0, 0xE0, 0xE0);

  Float CELL_PADDING = 1.5F;

  Float CELL_SPACING = 0.0F;

  Float LEADING = 10.0F;

  BaseColor TITLE_BACKGROUND_COLOR = new BaseColor(0xF0, 0xF0, 0xF0);

  Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA, FONT_SIZE, Font.BOLD);

  Font VALUE_FONT = FontFactory.getFont(FontFactory.HELVETICA, FONT_SIZE, Font.NORMAL);

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    BookingDateUserApplicantEntity bookingDate = (BookingDateUserApplicantEntity) dynaForm.get("bookingDate");

    MgrService mgrService = ServiceFactory.getInstance().getMgrService();

    bookingDate = mgrService.getBookingDateUserApplicantEntityForManagerAndBookingDate(getManagerLoggedIn().getManagerId(), bookingDate.getBookingDateId());

    DocumentException ex = null;

    ByteArrayOutputStream baosPDF = null;

    try
    {
      try
      {
        baosPDF = generatePDFDocumentBytes(bookingDate, request, servlet.getServletContext());

        StringBuffer sbFilename = new StringBuffer();
        sbFilename.append("filename_");
        sbFilename.append(System.currentTimeMillis());
        sbFilename.append(".pdf");

        // //////////////////////////////////////////////////////
        // Note:
        //
        // It is important to set the HTTP response headers
        // before writing data to the servlet's OutputStream
        //
        // //////////////////////////////////////////////////////
        //
        //
        // Read the HTTP 1.1 specification for full details
        // about the Cache-Control header
        //
        response.setHeader("Cache-Control", "max-age=30");

        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");

        //
        //
        // The Content-disposition header is explained
        // in RFC 2183
        //
        // http://www.ietf.org/rfc/rfc2183.txt
        //
        // The Content-disposition value will be in one of
        // two forms:
        //
        // 1) inline; filename=foobar.pdf
        // 2) attachment; filename=foobar.pdf
        //
        // In this servlet, we use "inline"
        //
        StringBuffer sbContentDispValue = new StringBuffer();
        sbContentDispValue.append("inline");
        sbContentDispValue.append("; filename=");
        sbContentDispValue.append(sbFilename);

        response.setHeader("Content-disposition", sbContentDispValue.toString());

        response.setContentLength(baosPDF.size());

        ServletOutputStream sos;

        sos = response.getOutputStream();

        baosPDF.writeTo(sos);

        sos.flush();
      }
      catch (DocumentException dex)
      {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(this.getClass().getName() + " caught an exception: " + dex.getClass().getName() + "<br>");
        writer.println("<pre>");
        dex.printStackTrace(writer);
        writer.println("</pre>");
      }
      finally
      {
        if (baosPDF != null)
        {
          baosPDF.reset();
        }
      }
    }
    catch (IOException ioex)
    {

    }

    logger.exit("Out going !!!");

    return null;

  }

  /**
   * 
   * @param req
   *          must be non-null
   * 
   * @return a non-null output stream. The output stream contains the bytes for
   *         the PDF document
   * 
   * @throws DocumentException
   * 
   */
  protected ByteArrayOutputStream generatePDFDocumentBytes(BookingDateUserApplicantEntity bookingDate, final HttpServletRequest req, final ServletContext ctx) throws DocumentException

  {
    Document doc = new Document();

    ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
    PdfWriter docWriter = null;

    try
    {
      docWriter = PdfWriter.getInstance(doc, baosPDF);

      doc.addAuthor(this.getClass().getName());
      doc.addCreationDate();
      doc.addProducer();
      doc.addCreator(this.getClass().getName());
      doc.addTitle("This is a title.");
      doc.addKeywords("pdf, itext, Java, open source, http");

      doc.setPageSize(PageSize.A4);

      // HeaderFooter footer = new HeaderFooter(
      // new Phrase("This is a footer."),
      // false);
      //
      // doc.setFooter(footer);

      doc.open();

      Client client = MgrUtilities.getCurrentClient(req);

      String clientLogoFilename = FileHandler.getInstance().getLogoFileLocation() + client.getLogoUrl();

      Image mmjLogo = null;
      Image clientLogo = null;

      try
      {
        mmjLogo = Image.getInstance(FileHandler.getInstance().getFileLocation() + "/images/master-logo.jpg");
        // mmjLogo =
        // Image.getInstance(FileHandler.getInstance().getFileLocation() +
        // "/images/mmj_logo_small.jpg");
        // clientLogo =
        // Image.getInstance(FileHandler.getInstance().getFileLocation() +
        // "/temp/logos/BTPCT_logo_right.gif");
        clientLogo = Image.getInstance(clientLogoFilename);
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

      if (mmjLogo != null && clientLogo != null)
      {

        PdfPTable tab = null;

        try
        {
          tab = new PdfPTable(2); // number of columns
          tab.setTotalWidth(100F); // width as a percentage
          float[] widths = { 0.50f, 0.50f }; // column widths
          tab.setWidths(widths);
        }
        catch (BadElementException ex)
        {
          throw new RuntimeException(ex);
        }

        tab.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        // tab.setBorderWidth(0F);
        // // ???
        // tab.setBorderColor(Color.WHITE);
        // tab.setPadding(0F);
        // tab.setSpacing(0F);
        //				
        // tab.endHeaders();

        PdfPCell leftCell = new PdfPCell(mmjLogo);
        leftCell.setBorder(Rectangle.NO_BORDER);
        leftCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        leftCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        tab.addCell(leftCell);

        PdfPCell rightCell = new PdfPCell(clientLogo);
        rightCell.setBorder(Rectangle.NO_BORDER);
        rightCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        rightCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        tab.addCell(rightCell);

        // TODO blank line NEEDED ???

        Paragraph para = new Paragraph();
        para.add(tab);
        doc.add(para);
      }

      doc.add(makeHeaderElement(bookingDate, req));

      // MessageResources messageResources = getResources(req);
      // DecimalFormat df = new DecimalFormat("000");
      // DecimalFormat nf = new DecimalFormat("0.00");
      // SimpleDateFormat sdf = new
      // SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
      // SimpleDateFormat tf = new
      // SimpleDateFormat(messageResources.getMessage("format.timeFormat"));
      //			
      // doc.add(new Paragraph(
      // df.format(bookingDate.getBookingId()) + "." +
      // df.format(bookingDate.getBookingDateId())));
      // doc.add(new Paragraph(
      // bookingDate.getLocationName() + " (" + bookingDate.getSiteName() +
      // ")"));
      // doc.add(new Paragraph(
      // bookingDate.getJobProfileName()));
      // doc.add(new Paragraph(
      // sdf.format(bookingDate.getBookingDate())));
      // doc.add(new Paragraph(
      // bookingDate.getShiftName()));
      // doc.add(new Paragraph(
      // tf.format(bookingDate.getShiftStartTime()) + " - " +
      // tf.format(bookingDate.getShiftEndTime())));
      // doc.add(new Paragraph(
      // tf.format(bookingDate.getShiftBreakStartTime()) + " - " +
      // tf.format(bookingDate.getShiftBreakEndTime()) +
      // " (" + nf.format(bookingDate.getShiftBreakNoOfHours()) + ")"));
      // doc.add(new Paragraph(
      // nf.format(bookingDate.getShiftNoOfHours())));
      // doc.add(new Paragraph(
      // messageResources.getMessage("label.currencySymbolText") +
      // nf.format(bookingDate.getValue())));
      // doc.add(new Paragraph(
      // messageResources.getMessage(bookingDate.getStatusDescriptionKey())));

      // doc.add(new Paragraph(
      // "This document was created by a class named: "
      // + this.getClass().getName()));
      //						
      // doc.add(new Paragraph(
      // "This document was created on "
      // + new java.util.Date()));
      //
      // String strServerInfo = ctx.getServerInfo();
      //			
      // if (strServerInfo != null)
      // {
      // doc.add(new Paragraph(
      // "Servlet engine: " + strServerInfo));
      // }
      //			
      // doc.add(new Paragraph(
      // "This is a multi-page document."));
      //			
      // doc.add( makeGeneralRequestDetailsElement(req) );
      //			
      // doc.newPage();
      //			
      // doc.add( makeHTTPHeaderInfoElement(req) );
      //			
      // doc.newPage();
      //			
      // doc.add( makeHTTPParameterInfoElement(req) );

    }
    catch (DocumentException dex)
    {
      baosPDF.reset();
      throw dex;
    }
    finally
    {
      if (doc != null)
      {
        doc.close();
      }
      if (docWriter != null)
      {
        docWriter.close();
      }
    }

    if (baosPDF.size() < 1) { throw new DocumentException("document has " + baosPDF.size() + " bytes"); }
    return baosPDF;
  }

  /**
   * 
   * @param req
   *          HTTP request object
   * @return an iText Element object
   * 
   */
  protected Element makeHTTPHeaderInfoElement(final HttpServletRequest req)
  {
    Map mapHeaders = new java.util.TreeMap();

    Enumeration enumHeaderNames = req.getHeaderNames();
    while (enumHeaderNames.hasMoreElements())
    {
      String strHeaderName = (String) enumHeaderNames.nextElement();
      String strHeaderValue = req.getHeader(strHeaderName);

      if (strHeaderValue == null)
      {
        strHeaderValue = "";
      }
      mapHeaders.put(strHeaderName, strHeaderValue);
    }

    PdfPTable tab = makeTableFromMap("HTTP header name", "HTTP header value", mapHeaders);

    return (Element) tab;
  }

  /**
   * 
   * @param req
   *          HTTP request object
   * @return an iText Element object
   * 
   */
  protected Element makeGeneralRequestDetailsElement(final HttpServletRequest req)
  {
    Map mapRequestDetails = new TreeMap();

    mapRequestDetails.put("Scheme", req.getScheme());

    mapRequestDetails.put("HTTP method", req.getMethod());

    mapRequestDetails.put("AuthType", req.getAuthType());

    mapRequestDetails.put("QueryString", req.getQueryString());

    mapRequestDetails.put("ContextPath", req.getContextPath());

    mapRequestDetails.put("Request URI", req.getRequestURI());

    mapRequestDetails.put("Protocol", req.getProtocol());

    mapRequestDetails.put("Remote address", req.getRemoteAddr());

    mapRequestDetails.put("Remote host", req.getRemoteHost());

    mapRequestDetails.put("Server name", req.getServerName());

    mapRequestDetails.put("Server port", "" + req.getServerPort());

    mapRequestDetails.put("Preferred locale", req.getLocale().toString());

    PdfPTable tab = null;

    tab = makeTableFromMap("Request info", "Value", mapRequestDetails);

    return (Element) tab;
  }

  protected Element makeHeaderElement(BookingDateUserApplicantEntity bookingDate, final HttpServletRequest req)
  {

    MessageResources messageResources = getResources(req);
    DecimalFormat df = new DecimalFormat("000");
    DecimalFormat nf = new DecimalFormat("0.00");
    SimpleDateFormat ldf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
    SimpleDateFormat mdf = new SimpleDateFormat(messageResources.getMessage("format.mediumDateFormat"));
    SimpleDateFormat tf = new SimpleDateFormat(messageResources.getMessage("format.timeFormat"));

    Vector<String[]> details1 = new Vector<String[]>();

    PdfPTable tab1 = null;

    details1.add(new String[] { messageResources.getMessage("label.shiftNo"), df.format(bookingDate.getBookingId()) + "." + df.format(bookingDate.getBookingDateId()) });
    details1.add(new String[] { messageResources.getMessage("label.location"), bookingDate.getLocationName() + " (" + bookingDate.getSiteName() + ")" });
    details1.add(new String[] { messageResources.getMessage("label.jobProfile"), bookingDate.getJobProfileName() });
    details1.add(new String[] { messageResources.getMessage("label.date"), ldf.format(bookingDate.getBookingDate()) });
    details1.add(new String[] { messageResources.getMessage("label.shift"), bookingDate.getShiftName() });
    details1.add(new String[] { messageResources.getMessage("label.time"), tf.format(bookingDate.getShiftStartTime()) + " - " + tf.format(bookingDate.getShiftEndTime()) });
    details1.add(new String[] { messageResources.getMessage("label.break"),
        tf.format(bookingDate.getShiftBreakStartTime()) + " - " + tf.format(bookingDate.getShiftBreakEndTime()) + " (" + nf.format(bookingDate.getShiftBreakNoOfHours()) + ")" });
    details1.add(new String[] { messageResources.getMessage("label.noOfHours"), nf.format(bookingDate.getShiftNoOfHours()) });
    details1.add(new String[] { messageResources.getMessage("label.rrpValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getValue()) });
    details1.add(new String[] { messageResources.getMessage("label.status"), messageResources.getMessage(bookingDate.getStatusDescriptionKey()) });

    tab1 = makeTableFromVector(details1);

    Vector<String[]> details2 = new Vector<String[]>();

    details2.add(new String[] { messageResources.getMessage("label.name"), bookingDate.getApplicantFirstName() + " " + bookingDate.getApplicantLastName() });
    details2.add(new String[] { messageResources.getMessage("label.agency"), bookingDate.getAgencyName() });
    details2.add(new String[] { messageResources.getMessage("label.gender"), messageResources.getMessage(bookingDate.getApplicantGenderDescriptionKey()) });
    details2.add(new String[] { messageResources.getMessage("label.dateOfBirth"), mdf.format(bookingDate.getApplicantDateOfBirth()) });
    details2.add(new String[] { messageResources.getMessage("label.agreedValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getChargeRateValue()) });
    details2.add(new String[] { messageResources.getMessage("label.actualTime"), tf.format(bookingDate.getWorkedStartTime()) + " - " + tf.format(bookingDate.getWorkedEndTime()) });
    details2.add(new String[] { messageResources.getMessage("label.actualBreak"),
        tf.format(bookingDate.getWorkedBreakStartTime()) + " - " + tf.format(bookingDate.getWorkedBreakEndTime()) + " (" + nf.format(bookingDate.getWorkedBreakNoOfHours()) + ")" });
    details2.add(new String[] { messageResources.getMessage("label.actualNoOfHours"), nf.format(bookingDate.getWorkedNoOfHours()) });
    details2.add(new String[] { messageResources.getMessage("label.actualValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getWorkedChargeRateValue()) });
    details2.add(new String[] { messageResources.getMessage("label.workedPayRateValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getWorkedPayRateValue()) });
    details2.add(new String[] { messageResources.getMessage("label.workedWtdValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getWorkedWtdValue()) });
    details2.add(new String[] { messageResources.getMessage("label.workedNiValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getWorkedNiValue()) });
    details2
        .add(new String[] { messageResources.getMessage("label.workedCommissionValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getWorkedCommissionValue()) });
    details2.add(new String[] { messageResources.getMessage("label.workedVatValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getWorkedVatValue()) });
    details2.add(new String[] { messageResources.getMessage("label.workedWageRateValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getWorkedWageRateValue()) });
    details2.add(new String[] { messageResources.getMessage("label.workedTotalValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getWorkedTotalValue()) });
    details2.add(new String[] { messageResources.getMessage("label.expenseValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getExpenseValue()) });
    details2.add(new String[] { messageResources.getMessage("label.expenseVatValue"), messageResources.getMessage("label.currencySymbolText") + nf.format(bookingDate.getExpenseVatValue()) });

    PdfPTable tab2 = null;

    tab2 = makeTableFromVector(details2);

    Paragraph para = new Paragraph();
    para.add(tab1);
    para.add(tab2);

    return (Element) para;

  }

  /**
   * 
   * 
   * @param req
   *          HTTP request object
   * @return an iText Element object
   * 
   */
  protected Element makeHTTPParameterInfoElement(final HttpServletRequest req)
  {
    Map mapParameters = null;

    mapParameters = new java.util.TreeMap(req.getParameterMap());

    PdfPTable tab = null;

    tab = makeTableFromMap("HTTP parameter name", "HTTP parameter value", mapParameters);

    return (Element) tab;
  }

  /**
   * 
   * @param firstColumnTitle
   * @param secondColumnTitle
   * @param m
   *          map containing the data for column 1 and column 2
   * 
   * @return an iText PdfPTable
   * 
   */
  private PdfPTable makeTableFromMap(final String firstColumnTitle, final String secondColumnTitle, final java.util.Map m)
  {
    PdfPTable tab = null;

    tab = new PdfPTable(2 /* columns */);

    tab.getDefaultCell().setBorderWidth(BORDER_WIDTH);
    tab.getDefaultCell().setBorderColor(BORDER_COLOR);
    tab.getDefaultCell().setPadding(CELL_PADDING);
// Lyndon. Could NOT find equivalent for new version.
//    tab.setSpacing(CELL_SPACING);

    // Cell firstCell = new Cell(firstColumnTitle);
    // firstCell.setBackgroundColor(new Color(0xF0, 0xF0, 0xF0));
    //		
    // tab.addCell(firstCell);
    // tab.addCell(new Cell(secondColumnTitle));

//  Lyndon. Could NOT find equivalent for new version.
//    tab.endHeaders();

    if (m.keySet().size() == 0)
    {
      PdfPCell c = new PdfPCell(new Paragraph("none"));
      c.setColspan(tab.getNumberOfColumns());
      tab.addCell(c);
    }
    else
    {
      Iterator iter = m.keySet().iterator();
      while (iter.hasNext())
      {
        String strName = (String) iter.next();
        Object value = m.get(strName);
        String strValue = null;
        if (value == null)
        {
          strValue = "";
        }
        else if (value instanceof String[])
        {
          String[] aValues = (String[]) value;
          strValue = aValues[0];
        }
        else
        {
          strValue = value.toString();
        }

        PdfPCell nameCell = new PdfPCell(new Paragraph(strName));
        nameCell.setLeading(LEADING, 0f);
        nameCell.setBackgroundColor(TITLE_BACKGROUND_COLOR);
        nameCell.setBorderColor(BORDER_COLOR);
        tab.addCell(nameCell);

        PdfPCell valueCell = new PdfPCell(new Paragraph(strValue));
        valueCell.setLeading(LEADING, 0f);
        valueCell.setBorderColor(BORDER_COLOR);
        tab.addCell(valueCell);
      }
    }

    return tab;
  }

  private PdfPTable makeTableFromVector(Vector<String[]> v)
  {
    PdfPTable tab = null;

    try
    {
      tab = new PdfPTable(2); // number of columns
      tab.setWidthPercentage(100F); // width as a percentage
      float[] widths = { 0.25f, 0.75f }; // column widths
      tab.setWidths(widths);
    }
    catch (BadElementException ex)
    {
      throw new RuntimeException(ex);
    }
    catch (DocumentException ex)
    {
      throw new RuntimeException(ex);
    }

    // tab.setBorderWidth(0);
    // tab.setBorderColor(borderColor);
    // tab.setPadding(cellPadding);
    // tab.setSpacing(cellSpacing);
    //		
    // tab.endHeaders();

    if (v.size() == 0)
    {
      // Cell c = new Cell("none");
      // c.setColspan(tab.columns());
      // tab.addCell(c);
    }
    else
    {
      Iterator iter = v.iterator();
      while (iter.hasNext())
      {
        String[] nameValue = (String[]) iter.next();

        String name = nameValue[0];
        String value = nameValue[1];

        PdfPCell nameCell = new PdfPCell(new Phrase(name, TITLE_FONT));

        nameCell.setMinimumHeight(MINIMUM_ROW_HEIGHT);
        nameCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        nameCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        nameCell.setBackgroundColor(TITLE_BACKGROUND_COLOR);
        nameCell.setBorderWidth(BORDER_WIDTH);
        nameCell.setBorderColor(BORDER_COLOR);
        tab.addCell(nameCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, VALUE_FONT));

        valueCell.setMinimumHeight(MINIMUM_ROW_HEIGHT);
        valueCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        valueCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        valueCell.setBorderColor(BORDER_COLOR);
        valueCell.setBorderWidth(BORDER_WIDTH);
        tab.addCell(valueCell);
      }
    }

    return tab;
  }

}