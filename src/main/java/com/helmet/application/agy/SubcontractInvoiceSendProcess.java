package com.helmet.application.agy;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.SubcontractInvoice;
import com.helmet.bean.SubcontractInvoiceItem;

public class SubcontractInvoiceSendProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    String cssFileName = request.getSession().getServletContext().getRealPath("/agy/site.css");
    String serverName = request.getServerName();
    String fromEmailAddress = (String) dynaForm.get("fromEmailAddress");
    String toEmailAddress = (String) dynaForm.get("toEmailAddress");
    String ccEmailAddress = (String) dynaForm.get("ccEmailAddress");
    String bccEmailAddress = (String) dynaForm.get("bccEmailAddress");
    String subject = (String) dynaForm.get("subject");
    String message = (String) dynaForm.get("message");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    Calendar calendar = Calendar.getInstance();
    Date todaysDate = new Date(calendar.getTimeInMillis());
    SubcontractInvoice subcontractInvoiceUser = (SubcontractInvoice)dynaForm.get("subcontractInvoiceUser");
    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
    }   
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    AgencyUser toAgency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    subcontractInvoiceUser = agyService.getSubcontractInvoiceUser(subcontractInvoiceUser.getSubcontractInvoiceId());
    if (subcontractInvoiceUser != null)
    {
      String fileName = subcontractInvoiceUser.getSubcontractInvoiceFileName();
      String filePath = FileHandler.getInstance().getNhsInvoiceFileLocation() + 
                        FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + 
                        subcontractInvoiceUser.getFromAgencyId() + "/a" + subcontractInvoiceUser.getToAgencyId() + "/" + fileName;
      File fileToAttach = new File(filePath);
      if (!fileToAttach.exists())
      {
        errors.add("sendEmail", new ActionMessage("errors.doesNotExist", filePath));
        saveErrors(request, errors);
        dynaForm.set("subcontractInvoiceUser", subcontractInvoiceUser);
        List<SubcontractInvoiceItem> listSubcontractInvoiceItems = agyService.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
        dynaForm.set("list", listSubcontractInvoiceItems);
        return mapping.getInputForward();
      }
      String[] attachments = new String[1];
      attachments[0] = filePath;
      MultipartEmailer multipartEmailer = new MultipartEmailer(toAgency, messageResources, cssFileName, serverName, fromEmailAddress, toEmailAddress, ccEmailAddress, bccEmailAddress, subject, message, attachments);
      int status = multipartEmailer.sendEmail();
      if (status == 0)
      {
        subcontractInvoiceUser.setSentDate(todaysDate);
      int rowCount = agyService.updateSubcontractInvoiceSentDate(subcontractInvoiceUser, getConsultantLoggedIn().getConsultantId());
      }
      else
      {
        // an error has occurred ... inform the user ...
        String errorKey = "error.email." + status;
        errors.add("sendEmail", new ActionMessage(errorKey));
        saveErrors(request, errors);
        dynaForm.set("subcontractInvoiceUser", subcontractInvoiceUser);
        List<SubcontractInvoiceItem> listSubcontractInvoiceItems = agyService.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
        dynaForm.set("list", listSubcontractInvoiceItems);
        return mapping.getInputForward();
      }
    }    
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
  }

}
