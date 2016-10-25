package com.helmet.application;

import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

public class FileHandler {

  private static FileHandler fileHandler;

  protected transient Log logger = LogFactory.getLog(getClass());

  private String appServerLocation;

  private String tempFileLocation = System.getenv("OPENSHIFT_DATA_DIR");

  private String tempFileFolder = "/temp";

  private String fileLocation = System.getenv("OPENSHIFT_DATA_DIR");

  private String fileFolder = "documents";

  private String photoFileLocation = System.getenv("OPENSHIFT_DATA_DIR");

  private String photoFileFolder = "/documents/photos";

  private String logoFileLocation = System.getenv("OPENSHIFT_DATA_DIR");

  private String logoFileFolder = "/documents/logos";

  private String cvFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String cvFileFolder = "/documents/cvs";

  private String receiptFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String receiptFileFolder = "/documents/receipts";

  private String invoiceFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String invoiceFileFolder = "/documents/invoices";

  private String applicantFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String applicantFileFolder = "/documents/applicants";

  private String consultantFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String consultantFileFolder = "/documents/consultants";

  private String nhsBookingFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String nhsBookingFileFolder = "/documents/nhsBookings";
  
  private String nhsInvoiceFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String nhsInvoiceFileFolder = "/documents/nhsInvoices";
  
  private String nhsBackingReportFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String nhsBackingReportFileFolder = "/documents/nhsBackingReports";
  
  private String nhsPaymentsFileLocation = System.getenv("OPENSHIFT_DATA_DIR");;

  private String nhsPaymentsFileFolder = "/documents/nhsPayments";
  
  private String nhsPaymentsAcceptStatus = "Paid in full";
  
  public static FileHandler getInstance() {
    if (fileHandler == null) {
      // NOT instantiated yet so create it.
      synchronized (FileHandler.class) {
        // Only ONE thread at a time here!
        if (fileHandler == null) {
          fileHandler = new FileHandler();
        }
      }
    }
    return fileHandler;
  }

  public String getAppServerLocation() {
    return appServerLocation;
  }

  public void setAppServerLocation(String appServerLocation) {
    this.appServerLocation = appServerLocation;
  }

  public String getFileLocation() {
    return fileLocation;
  }

  public void setFileLocation(String fileLocation) {
    this.fileLocation = fileLocation;
  }

  public String getTempFileLocation() {
    return tempFileLocation;
  }

  public void setTempFileLocation(String tempFileLocation) {
    this.tempFileLocation = tempFileLocation;
  }

  public String getFileFolder() {
    return fileFolder;
  }

  public void setFileFolder(String fileFolder) {
    this.fileFolder = fileFolder;
  }

  public String getTempFileFolder() {
    return tempFileFolder;
  }

  public void setTempFileFolder(String tempFileFolder) {
    this.tempFileFolder = tempFileFolder;
  }

  public String getPhotoFileFolder() {
    return photoFileFolder;
  }

  public void setPhotoFileFolder(String photoFileFolder) {
    this.photoFileFolder = photoFileFolder;
  }

  public String getPhotoFileLocation() {
    return photoFileLocation;
  }

  public void setPhotoFileLocation(String photoFileLocation) {
    this.photoFileLocation = photoFileLocation;
  }

  public String getLogoFileFolder() {
    return logoFileFolder;
  }

  public void setLogoFileFolder(String logoFileFolder) {
    this.logoFileFolder = logoFileFolder;
  }

  public String getLogoFileLocation() {
    return logoFileLocation;
  }

  public void setLogoFileLocation(String logoFileLocation) {
    this.logoFileLocation = logoFileLocation;
  }

  public String getCvFileFolder() {
    return cvFileFolder;
  }

  public void setCvFileFolder(String cvFileFolder) {
    this.cvFileFolder = cvFileFolder;
  }

  public String getCvFileLocation() {
    return cvFileLocation;
  }

  public void setCvFileLocation(String cvFileLocation) {
    this.cvFileLocation = cvFileLocation;
  }

  public String getReceiptFileFolder() {
    return receiptFileFolder;
  }

  public void setReceiptFileFolder(String receiptFileFolder) {
    this.receiptFileFolder = receiptFileFolder;
  }

  public String getReceiptFileLocation() {
    return receiptFileLocation;
  }

  public void setReceiptFileLocation(String receiptFileLocation) {
    this.receiptFileLocation = receiptFileLocation;
  }

  public String getInvoiceFileFolder() {
    return invoiceFileFolder;
  }

  public void setInvoiceFileFolder(String invoiceFileFolder) {
    this.invoiceFileFolder = invoiceFileFolder;
  }

  public String getInvoiceFileLocation() {
    return invoiceFileLocation;
  }

  public void setInvoiceFileLocation(String invoiceFileLocation) {
    this.invoiceFileLocation = invoiceFileLocation;
  }

  public String getApplicantFileFolder() {
    return applicantFileFolder;
  }

  public void setApplicantFileFolder(String applicantFileFolder) {
    this.applicantFileFolder = applicantFileFolder;
  }

  public String getApplicantFileLocation() {
    return applicantFileLocation;
  }

  public void setApplicantFileLocation(String applicantFileLocation) {
    this.applicantFileLocation = applicantFileLocation;
  }

  public String getConsultantFileFolder()
  {
    return consultantFileFolder;
  }

  public void setConsultantFileFolder(String consultantFileFolder)
  {
    this.consultantFileFolder = consultantFileFolder;
  }

  public String getConsultantFileLocation()
  {
    return consultantFileLocation;
  }

  public void setConsultantFileLocation(String consultantFileLocation)
  {
    this.consultantFileLocation = consultantFileLocation;
  }

  
  public String getNhsBookingFileFolder()
  {
    return nhsBookingFileFolder;
  }

  public void setNhsBookingFileFolder(String nhsBookingFileFolder)
  {
    this.nhsBookingFileFolder = nhsBookingFileFolder;
  }

  public String getNhsBookingFileLocation()
  {
    return nhsBookingFileLocation;
  }

  public void setNhsBookingFileLocation(String nhsBookingFileLocation)
  {
    this.nhsBookingFileLocation = nhsBookingFileLocation;
  }

  public String getNhsInvoiceFileFolder()
  {
    return nhsInvoiceFileFolder;
  }

  public void setNhsInvoiceFileFolder(String nhsInvoiceFileFolder)
  {
    this.nhsInvoiceFileFolder = nhsInvoiceFileFolder;
  }

  public String getNhsInvoiceFileLocation()
  {
    return nhsInvoiceFileLocation;
  }

  public void setNhsInvoiceFileLocation(String nhsInvoiceFileLocation)
  {
    this.nhsInvoiceFileLocation = nhsInvoiceFileLocation;
  }

  public String getNhsBackingReportFileFolder()
  {
    return nhsBackingReportFileFolder;
  }

  public void setNhsBackingReportFileFolder(String nhsBackingReportFileFolder)
  {
    this.nhsBackingReportFileFolder = nhsBackingReportFileFolder;
  }

  public String getNhsBackingReportFileLocation()
  {
    return nhsBackingReportFileLocation;
  }

  public void setNhsBackingReportFileLocation(String nhsBackingReportFileLocation)
  {
    this.nhsBackingReportFileLocation = nhsBackingReportFileLocation;
  }

  public String getNhsPaymentsFileFolder()
  {
    return nhsPaymentsFileFolder;
  }

  public void setNhsPaymentsFileFolder(String nhsPaymentsFileFolder)
  {
    this.nhsPaymentsFileFolder = nhsPaymentsFileFolder;
  }

  public String getNhsPaymentsFileLocation()
  {
    return nhsPaymentsFileLocation;
  }

  public void setNhsPaymentsFileLocation(String nhsPaymentsFileLocation)
  {
    this.nhsPaymentsFileLocation = nhsPaymentsFileLocation;
  }

  public String getNhsPaymentsAcceptStatus()
  {
    return nhsPaymentsAcceptStatus;
  }

  public void setNhsPaymentsAcceptStatus(String nhsPaymentsAcceptStatus)
  {
    this.nhsPaymentsAcceptStatus = nhsPaymentsAcceptStatus;
  }

  public void convertTiffToPdf(String tiffFilePath, String pdfFilePath) {

    Document document = null;
    try {
      int pages = 0;
            RandomAccessFileOrArray ra = null;
            int comps = 0;
            try {
                ra = new RandomAccessFileOrArray(tiffFilePath);
                comps = TiffImage.getNumberOfPages(ra);
            }
            catch (Throwable e) {
                logger.error("Exception in " + tiffFilePath + " " + e.getMessage());
            }

            logger.debug("Processing: " + tiffFilePath);
        
            for (int c = 0; c < comps; ++c) {

              // for each page
              
              try {
                    Image img = TiffImage.getTiffImage(ra, c + 1);
                    
                    if (img != null) {
                        
                      logger.debug("page " + (c + 1));
                      logger.debug("width " + img.getWidth() + " height " + img.getHeight());
                      logger.debug("plainWidth " + img.getPlainWidth() + " plainHeight " + img.getPlainHeight());

                        // web a4 page is 595 x 842 @ 72 dpi
                        // we need to convert from current dpi to web dpi
                        
                        float dipX = img.getDpiX();
                        float dipY = img.getDpiY();

                        logger.debug("dipX " + dipX + " dipY " + dipY);
                        
                        float imgWidth = (img.getWidth() / dipX) * 72;
                        float imgHeight = (img.getHeight() / dipY) * 72;
                        
                        logger.debug("imgWidth " + imgWidth + " imgHeight " + imgHeight);

                        Rectangle pageSize = imgHeight > imgWidth ? PageSize.A4 : PageSize.A4.rotate();

                        if (document == null) {
                        
                          // set the orientation of the initial page
                      document = new Document(pageSize);
                      PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
                    
                    document.setMargins(0, 0, 0, 0);
                    
                    document.open();
                        }
                        else {
                          // set the orientation of the new page
                          document.setPageSize(pageSize);
                          document.newPage();
                        }
                        
                        logger.debug("pageWidth " + document.getPageSize().getWidth() + " pageHeight " + document.getPageSize().getHeight());

                        // calculate the max page width/height to determine whether the image needs to be scaled
                        float maxPageWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                        float maxPageHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();

                        logger.debug("maxPageWidth " + maxPageWidth + " maxPageHeight " + maxPageHeight);
                        
                        if (img.getScaledWidth() > maxPageWidth) {
                          float widthFactor = maxPageWidth / imgWidth;
                            img.scaleAbsoluteWidth(maxPageWidth);
                            img.scaleAbsoluteHeight(imgHeight * (widthFactor));
                        }

                        logger.debug("img.getScaledWidth " + img.getScaledWidth() + " img.getScaledHeight " + img.getScaledHeight());

                        if (img.getScaledHeight() > maxPageHeight) {
                          float heightFactor = maxPageHeight / imgHeight;
                            img.scaleAbsoluteHeight(maxPageHeight);
                            img.scaleAbsoluteWidth(imgWidth * (heightFactor));
                        }
                        
                        logger.debug("img.getScaledWidth " + img.getScaledWidth() + " img.getScaledHeight " + img.getScaledHeight());

                      document.add(img);
            
                        ++pages;
                    }
                }
                catch (Throwable e) {
                  logger.error("Exception " + tiffFilePath + " page " + (c + 1) + " " + e.getMessage());
                }
      }
      ra.close();
      document.close();
    } catch (Throwable e) {
      e.printStackTrace();
    }
    
  }

  
  
}