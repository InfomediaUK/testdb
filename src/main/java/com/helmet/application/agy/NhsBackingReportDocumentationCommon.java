package com.helmet.application.agy;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.FileHandler;
import com.helmet.application.PdfHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.NhsBackingReportUser;

public abstract class NhsBackingReportDocumentationCommon extends AgyAction
{

  protected void setInvoiceFormFields(AgencyUser agency, DynaValidatorForm dynaForm, NhsBackingReportUser nhsBackingReportUser, MessageResources messageResources, SimpleDateFormat sdf)
  {
    // Used to form NHS Invoice url.
    String nhsInvoiceFile = PdfHandler.getInstance().getNhsInvoiceFileFolder(agency) + "/" + PdfHandler.getInstance().getNhsInvoiceFileName(agency, nhsBackingReportUser); 
    // Used for link text in jsp.
    String nhsInvoiceFileName = PdfHandler.getInstance().getNhsInvoiceFileName(agency, nhsBackingReportUser); 
    File fileNhsInvoice = PdfHandler.getInstance().getNhsInvoiceFile(agency, nhsBackingReportUser);
    dynaForm.set("nhsInvoiceFile", fileNhsInvoice.exists() ? nhsInvoiceFile : "");
    dynaForm.set("nhsInvoiceFileName", fileNhsInvoice.exists() ? nhsInvoiceFileName : "");
    String nhsInvoiceLinkTitle = messageResources.getMessage("title.viewNhsInvoice") + " - " + sdf.format(new Date(fileNhsInvoice.lastModified()));
    dynaForm.set("nhsInvoiceLinkTitle", nhsInvoiceLinkTitle);

  }
  
  protected void setDocumentationFormFields(AgencyUser agency, DynaValidatorForm dynaForm, NhsBackingReportUser nhsBackingReportUser, MessageResources messageResources, SimpleDateFormat sdf)
  {
    String nhsBackingReportFile = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getDocumentationFileName();
    String nhsBackingReportFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + nhsBackingReportFile;
    File fileNhsBackingReport = new File(nhsBackingReportFilePath);
    dynaForm.set("nhsBackingReportDocumentationFile", fileNhsBackingReport.exists() ? nhsBackingReportFile : "");
    String nhsBackingReportDocumentationLinkTitle = messageResources.getMessage("title.viewNhsBackingReportDocumentation") + " - " + sdf.format(new Date(fileNhsBackingReport.lastModified()));;
    dynaForm.set("nhsBackingReportDocumentationLinkTitle", nhsBackingReportDocumentationLinkTitle);
  }
  
  protected void setRejectedDocumentationFormFields(AgencyUser agency, DynaValidatorForm dynaForm, NhsBackingReportUser nhsBackingReportUser, MessageResources messageResources, SimpleDateFormat sdf)
  {
    String nhsBackingReportRejectedFile = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getRejectedDocumentationFileName();
    String nhsBackingReportRejectedFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + nhsBackingReportRejectedFile;
    File fileNhsBackingReportRejected = new File(nhsBackingReportRejectedFilePath);
    dynaForm.set("nhsBackingReportRejectedDocumentationFile", fileNhsBackingReportRejected.exists() ? nhsBackingReportRejectedFile : "");
    String nhsBackingReportRejectedDocumentationLinkTitle = messageResources.getMessage("title.viewNhsBackingReportRejectedDocumentation") + " - " + sdf.format(new Date(fileNhsBackingReportRejected.lastModified()));;
    dynaForm.set("nhsBackingReportRejectedDocumentationLinkTitle", nhsBackingReportRejectedDocumentationLinkTitle);
  }
  
}
