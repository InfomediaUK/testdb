package com.helmet.servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helmet.application.FileHandler;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFServlet extends HttpServlet
{
	/** 
	* 
	* 
	*/
	public PDFServlet()
	{
		super();
	}

	/**
	 *  
	 * 
	 * we implement doGet so that this servlet will process all 
	 * HTTP GET requests
	 * 
	 * @param req HTTP request object 
	 * @param resp HTTP response object
	 * 
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws javax.servlet.ServletException, java.io.IOException
	{
		DocumentException ex = null;
		
		ByteArrayOutputStream baosPDF = null;
		
		try
		{
			
			try {
				doXXX();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("xxx");
			}			
			baosPDF = generatePDFDocumentBytes(req, this.getServletContext());
			
			StringBuffer sbFilename = new StringBuffer();
			sbFilename.append("filename_");
			sbFilename.append(System.currentTimeMillis());
			sbFilename.append(".pdf");

			////////////////////////////////////////////////////////
			// Note: 
			//
			// It is important to set the HTTP response headers 
			// before writing data to the servlet's OutputStream 
			//
			////////////////////////////////////////////////////////
			//
			//
			// Read the HTTP 1.1 specification for full details
			// about the Cache-Control header
			//
			resp.setHeader("Cache-Control", "max-age=30");
			
			resp.setContentType("application/pdf");
			
			//
			//
			// The Content-disposition header is explained
			// in RFC 2183
			//
			//    http://www.ietf.org/rfc/rfc2183.txt
			//
			// The Content-disposition value will be in one of 
			// two forms:
			//
			//   1)  inline; filename=foobar.pdf
			//   2)  attachment; filename=foobar.pdf
			//
			// In this servlet, we use "inline"
			//
			StringBuffer sbContentDispValue = new StringBuffer();
			sbContentDispValue.append("inline");
			sbContentDispValue.append("; filename=");
			sbContentDispValue.append(sbFilename);
							
			resp.setHeader(
				"Content-disposition",
				sbContentDispValue.toString());

			resp.setContentLength(baosPDF.size());

			ServletOutputStream sos;

			sos = resp.getOutputStream();
			
			baosPDF.writeTo(sos);
			
			sos.flush();
		}
		catch (DocumentException dex)
		{
			resp.setContentType("text/html");
			PrintWriter writer = resp.getWriter();
			writer.println(
					this.getClass().getName() 
					+ " caught an exception: " 
					+ dex.getClass().getName()
					+ "<br>");
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

	/**
	 *  
	 * @param req must be non-null
	 * 
	 * @return a non-null output stream. The output stream contains
	 *         the bytes for the PDF document
	 * 
	 * @throws DocumentException
	 * 
	 */
	protected ByteArrayOutputStream generatePDFDocumentBytes(
		final HttpServletRequest req,
		final ServletContext ctx)
		throws DocumentException
		
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
			
//			HeaderFooter header = new HeaderFooter(
//					new Phrase("This is a header."),
//					false);
//			HeaderFooter footer = new HeaderFooter(
//					new Phrase("This is a footer."),
//					false);
//
//			doc.setHeader(header);
//			doc.setFooter(footer);
		
			doc.open();
			
			doc.add(new Paragraph(
						"This document was created by a class named: "
						+ this.getClass().getName()));
						
			doc.add(new Paragraph(
						"This document was created on "
						+ new java.util.Date()));

			String strServerInfo = ctx.getServerInfo();
			
			if (strServerInfo != null)
			{
				doc.add(new Paragraph(
						"Servlet engine: " + strServerInfo));
			}
			
			doc.add(new Paragraph(
						"This is a multi-page document."));
			
			doc.add( makeGeneralRequestDetailsElement(req) );
			
			doc.newPage();
			
			doc.add( makeHTTPHeaderInfoElement(req) );
			
			doc.newPage();
			
			doc.add( makeHTTPParameterInfoElement(req) );
			
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

		if (baosPDF.size() < 1)
		{
			throw new DocumentException(
				"document has "
				+ baosPDF.size()
				+ " bytes");		
		}
		return baosPDF;
	}
	
	/**
	 * 
	 * @param req HTTP request object
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

		PdfPTable tab = makeTableFromMap(
				"HTTP header name",
				"HTTP header value",
				mapHeaders);
		
		return (Element) tab;
	}

	/**
	 *  
	 * @param req HTTP request object 
	 * @return an iText Element object
	 * 
	 */
	protected Element makeGeneralRequestDetailsElement(
						final HttpServletRequest req)
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
		
		tab = makeTableFromMap(
						"Request info", 
						"Value",
						mapRequestDetails);
		
		return (Element) tab;
	}

	/**
	 * 
	 * 
	 * @param req HTTP request object
	 * @return an iText Element object
	 * 
	 */
	protected Element makeHTTPParameterInfoElement(
					final HttpServletRequest req)
	{
		Map mapParameters = null;
		
		mapParameters = new java.util.TreeMap(req.getParameterMap());

		PdfPTable tab = null;

		tab = makeTableFromMap(
				"HTTP parameter name",
				"HTTP parameter value",
				mapParameters);
		
		return (Element) tab;
	}
	
	/**
	 *
	 * @param firstColumnTitle
	 * @param secondColumnTitle
	 * @param m map containing the data for column 1 and column 2
	 * 
	 * @return an iText PdfPTable
	 * 
	 */
	private static PdfPTable makeTableFromMap(
			final String firstColumnTitle,
			final String secondColumnTitle,
			final java.util.Map m)
	{
		PdfPTable tab = null;

    tab = new PdfPTable(2 /* columns */);
		
//		tab.setBorderWidth(1.0f);
//		tab.setPadding(5);
//		tab.setSpacing(5);

		tab.addCell(new PdfPCell(new Paragraph(firstColumnTitle)));
		tab.addCell(new PdfPCell(new Paragraph(secondColumnTitle)));
		
//		tab.endHeaders();

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
				
				tab.addCell(new PdfPCell(new Paragraph(strName)));
				tab.addCell(new PdfPCell(new Paragraph(strValue)));
			}
		}
		
		return tab;
	}

	
	private void doXXX() throws Exception {

		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
		
    	String tempFilePath = FileHandler.getInstance().getTempFileLocation() + 
        FileHandler.getInstance().getTempFileFolder() + 
        "/" + "myPdf.pdf";
		
        PdfWriter.getInstance(doc, new FileOutputStream(tempFilePath));
        Paragraph phrase = new Paragraph("Hello World");
//        HeaderFooter hd = new HeaderFooter(phrase, false);
//        doc.setHeader(hd);
//        HeaderFooter foot = new HeaderFooter(new Paragraph("Created by usacoder"), true);
//        doc.setFooter(foot);
                
        doc.open();
        doc.add(new Paragraph("new paragraph"));
        
        Paragraph title2 = new Paragraph("This is Chapter 2", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new BaseColor(0, 0, 255)));
        Chapter chapter2 = new Chapter(title2, 2);
//        doc.add(chapter2);
        Paragraph someText = new Paragraph("This is some text");
        chapter2.add(someText);
        Paragraph title21 = new Paragraph("This is Section 1 in Chapter 2", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new BaseColor(255, 0, 0)));
        Section section1 = chapter2.addSection(title21);
        Paragraph someSectionText = new Paragraph("This is some silly paragraph in a chapter and/or section. It contains some text to test the functionality of Chapters and Section.");
        section1.add(someSectionText);
        Paragraph title211 = new Paragraph("This is SubSection 1 in Section 1 in Chapter 2", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new BaseColor(255, 0, 0)));
        Section section11 = section1.addSection(40, title211, 2);
        section11.add(someSectionText);
        doc.newPage();

        doc.add(chapter2);

        
        doc.newPage();
        
        
		PdfPTable table = new PdfPTable(3);
		PdfPCell cell = new PdfPCell(new Paragraph("header with colspan 3", FontFactory.getFont(FontFactory.TIMES, 4, Font.NORMAL, new BaseColor(123, 123, 123))));
		cell.setColspan(3);
		
		table.addCell(cell);
		table.addCell("1.1");
		table.addCell("2.1");
		table.addCell("3.1");
		table.addCell("1.2");

		PdfPTable tableInner = new PdfPTable(2);
		tableInner.addCell("1.1");
		tableInner.addCell("2.1");
		tableInner.addCell("1.2");
		tableInner.addCell("2.2");
        cell = new PdfPCell(tableInner);
        cell.setPadding(0);
		table.addCell(cell);
		
//		table.addCell("2.2");
		
		Image mmjLogo = null;
		
		try {
			mmjLogo = Image.getInstance(FileHandler.getInstance().getFileLocation() + "/images/master-logo.jpg");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PdfPCell logoCell = new PdfPCell(new Paragraph("no image"));
        if (mmjLogo != null) {
        	
        	System.out.println(mmjLogo.getWidth() + " " + mmjLogo.getScaledWidth());
        	System.out.println(mmjLogo.getHeight() + " " + mmjLogo.getScaledHeight());
        	
        	Float imageHeight = 40F;
        	mmjLogo.scaleAbsoluteHeight(imageHeight);
        	mmjLogo.scaleAbsoluteWidth(mmjLogo.getWidth() * (mmjLogo.getScaledHeight() / mmjLogo.getHeight()));
        	
        	System.out.println(mmjLogo.getWidth() + " " + mmjLogo.getScaledWidth());
        	System.out.println(mmjLogo.getHeight() + " " + mmjLogo.getScaledHeight());

        	logoCell = new PdfPCell(mmjLogo);

            mmjLogo.scalePercent(100);
        }
		
		table.addCell(logoCell);
		
//		table.addCell("3.2");

		cell = new PdfPCell(new Paragraph("cell test1"));
		cell.setBorderColor(new BaseColor(255, 0, 0));
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("cell test2", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, new BaseColor(255, 0, 0))));
		cell.setColspan(2);
		cell.setBackgroundColor(new BaseColor(0xC0, 0xC0, 0xC0));
		table.addCell(cell);
        doc.add(table);
		table.setWidthPercentage(100);
        doc.add(table);
		table.setWidthPercentage(50);
		table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        doc.add(table);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
        doc.add(table);

        if (mmjLogo != null) {
            doc.add(mmjLogo);
        }

        doc.newPage();
        
        
        
        doc.close();		
		
	}
	
	
}
