package com.helmet.application.agy;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.MultipartEmailer;
import com.helmet.application.PdfHandler;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportDocumentationSendProcess extends NhsBackingReportDocumentationCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    ActionForward actionForward = null;
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    String cssFileName = FileHandler.getInstance().getEmailTemplateRealPath("/agy/site.css");
    String serverName = request.getServerName();
    String fromEmailAddress = (String) dynaForm.get("fromEmailAddress");
    String toEmailAddress = (String) dynaForm.get("toEmailAddress");
    String ccEmailAddress = (String) dynaForm.get("ccEmailAddress");
    String bccEmailAddress = (String) dynaForm.get("bccEmailAddress");
    String subject = (String) dynaForm.get("subject");
    String message = (String) dynaForm.get("message");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    SimpleDateFormat sdf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
    Calendar calendar = Calendar.getInstance();
    Date todaysDate = new Date(calendar.getTimeInMillis());
    NhsBackingReportUser nhsBackingReportUser = (NhsBackingReportUser)dynaForm.get("nhsBackingReportUser");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    nhsBackingReportUser = agyService.getNhsBackingReportUser(nhsBackingReportUser.getNhsBackingReportId());
    if (nhsBackingReportUser != null)
    {
      String[] attachments = null;
      String invoiceFileName = PdfHandler.getInstance().getNhsInvoiceFileName(agency, nhsBackingReportUser);
      String invoiceFilePath = FileHandler.getInstance().getNhsInvoiceFileLocation() + 
          FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + 
          agency.getAgencyId() + "/" + invoiceFileName;
      File invoiceFileToAttach = new File(invoiceFilePath);
      if (!invoiceFileToAttach.exists())
      {
        errors.add("sendEmail", new ActionMessage("errors.doesNotExist", invoiceFilePath));
      }
      String documentationFileName = nhsBackingReportUser.getDocumentationFileName();
      String documentationFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + 
          FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + 
          agency.getAgencyId() + "/" + documentationFileName;
      File documentationFileToAttach = new File(documentationFilePath);
      if (!documentationFileToAttach.exists())
      {
        errors.add("sendEmail", new ActionMessage("errors.doesNotExist", documentationFileToAttach));
      }
      File rejectedDocumentationFileToAttach = null;
      if (StringUtils.isEmpty(nhsBackingReportUser.getRejectedDocumentationFileName()))
      {
        attachments = new String[2];
      }
      else
      {
        attachments = new String[3];
        String rejectedDocumentationFileName = nhsBackingReportUser.getRejectedDocumentationFileName();
        String rejectedDocumentationFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + 
            FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + 
            agency.getAgencyId() + "/" + rejectedDocumentationFileName;
        rejectedDocumentationFileToAttach = new File(rejectedDocumentationFilePath);
        if (!rejectedDocumentationFileToAttach.exists())
        {
          errors.add("sendEmail", new ActionMessage("errors.doesNotExist", rejectedDocumentationFileToAttach));
        }
        attachments[2] = rejectedDocumentationFilePath;
      }
      if (!errors.isEmpty())
      {
        saveErrors(request, errors);
        dynaForm.set("nhsBackingReportUser", nhsBackingReportUser);
        return mapping.getInputForward();
      }
      attachments[0] = invoiceFilePath;
      attachments[1] = documentationFilePath;
      MultipartEmailer multipartEmailer = new MultipartEmailer(agency, messageResources, cssFileName, serverName, fromEmailAddress, toEmailAddress, ccEmailAddress, bccEmailAddress, subject, message, attachments);
      int status = multipartEmailer.sendEmail();
      if (status == 0)
      {
        nhsBackingReportUser.setSubcontractDocumentationSentDate(todaysDate);
        int rowCount = agyService.updateNhsBackingReportSubcontractDocumentationSentDate(nhsBackingReportUser, getConsultantLoggedIn().getConsultantId());
      }
      else
      {
        // an error has occurred ... inform the user ...
        String errorKey = "error.email." + status;
        errors.add("sendEmail", new ActionMessage(errorKey));
        saveErrors(request, errors);
        dynaForm.set("nhsBackingReportUser", nhsBackingReportUser);
        setInvoiceFormFields(agency, dynaForm, nhsBackingReportUser, messageResources, sdf);
        setDocumentationFormFields(agency, dynaForm, nhsBackingReportUser, messageResources, sdf);
        setRejectedDocumentationFormFields(agency, dynaForm, nhsBackingReportUser, messageResources, sdf);
        actionForward =  mapping.getInputForward();
        return actionForward;
      }
    }    
    logger.exit("Out going !!!");
    actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?nhsBackingReportUser.nhsBackingReportId=" + nhsBackingReportUser.getNhsBackingReportId(), actionForward.getRedirect());
  }

}
