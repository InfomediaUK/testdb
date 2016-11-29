package com.helmet.application.agy;

import java.io.File;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.PdfHandler;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportView extends NhsBackingReportCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");
    NhsBackingReportUser nhsBackingReportUser = (NhsBackingReportUser)dynaForm.get("nhsBackingReportUser");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    nhsBackingReportUser = agyService.getNhsBackingReportUser(nhsBackingReportUser.getNhsBackingReportId());
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    String nhsInvoiceFile = PdfHandler.getInstance().getNhsInvoiceFileFolder(agency) + "/" + PdfHandler.getInstance().getNhsInvoiceFileName(agency, nhsBackingReportUser);
    File fileNhsInvoice = PdfHandler.getInstance().getNhsInvoiceFile(agency, nhsBackingReportUser);
    if (StringUtils.isNotEmpty(nhsBackingReportUser.getDocumentationFileName()))
    {
      String nhsBackingReportDocumentationFile = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getDocumentationFileName();
      String nhsBackingReportDocumentationFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + nhsBackingReportDocumentationFile;
      File fileNhsBackingReportDocumentation = new File(nhsBackingReportDocumentationFilePath);
      dynaForm.set("nhsBackingReportDocumentationFile", fileNhsBackingReportDocumentation.exists() ? nhsBackingReportDocumentationFile : nhsBackingReportUser.getDocumentationFileName() + "Not Found.");
      dynaForm.set("nhsBackingReportDocumentationFileDate", new Date(fileNhsBackingReportDocumentation.lastModified()));
      long fileSize = fileNhsBackingReportDocumentation.length() / 1000;
      dynaForm.set("nhsBackingReportDocumentationFileSize", new Long(fileSize));
    }
    if (StringUtils.isNotEmpty(nhsBackingReportUser.getRejectedDocumentationFileName()))
    {
      String nhsBackingReportRejectedDocumentationFile = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getRejectedDocumentationFileName();
      String nhsBackingReportRejectedDocumentationFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + nhsBackingReportRejectedDocumentationFile;
      File fileNhsBackingReportRejectedDocumentation = new File(nhsBackingReportRejectedDocumentationFilePath);
      dynaForm.set("nhsBackingReportRejectedDocumentationFile", fileNhsBackingReportRejectedDocumentation.exists() ? nhsBackingReportRejectedDocumentationFile : nhsBackingReportUser.getDocumentationFileName() + "Not Found.");
      dynaForm.set("nhsBackingReportRejectedDocumentationFileDate", new Date(fileNhsBackingReportRejectedDocumentation.lastModified()));
      long fileSize = fileNhsBackingReportRejectedDocumentation.length() / 1000;
      dynaForm.set("nhsBackingReportRejectedDocumentationFileSize", new Long(fileSize));
    }
    dynaForm.set("nhsBackingReportUser", nhsBackingReportUser);
    if (fileNhsInvoice.exists())
    {
      dynaForm.set("nhsInvoiceFile", nhsInvoiceFile);
      dynaForm.set("nhsInvoiceFileDate", new Date(fileNhsInvoice.lastModified()));
    }
    setUpFormForSubcontractInvoice(nhsBackingReportUser, dynaForm);
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
