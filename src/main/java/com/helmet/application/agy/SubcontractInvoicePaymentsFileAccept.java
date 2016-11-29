package com.helmet.application.agy;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import com.helmet.bean.Client;
import com.helmet.bean.SubcontractInvoice;
import com.helmet.bean.nhs.Payments;
import com.helmet.bean.nhs.PaymentLine;

public class SubcontractInvoicePaymentsFileAccept extends NhsFileUploadCommon
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    ActionForward actionForward = null;
    SubcontractInvoice subcontractInvoice = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Client client = (Client)dynaForm.get("client");
    String accept = (String)dynaForm.get("accept");
    client = agyService.getClientUser(client.getClientId());
    String subcontractInvoiceIds = null;
    Payments payments = (Payments)request.getSession().getAttribute("payments");
    if (payments == null)
    {
      // Backing Report vanished from Session!
      errors.add("subcontractInvoicePaymentsAccept", new ActionMessage("error.subcontractInvoicePaymentsFile.vanishedFromSession"));
    }
    else
    {
      try
      {
        if (payments.getFileName().equals(accept))
        {
          // Payments found in Session. Process it...
          subcontractInvoiceIds = updatePaymentsOnSubcontractInvoices(subcontractInvoice, payments, messageResources);
        }
        else
        {
          request.getSession().removeAttribute("payments");
          errors.add("subcontractInvoicePaymentsAccept", new ActionMessage("error.subcontractInvoicePaymentsFile.notAsAccepted", accept));
        }
      }
      catch (Exception e)
      {
        errors.add("subcontractInvoicePaymentsAccept", new ActionMessage("error.subcontractInvoicePaymentsFile.exception", e.getMessage()));
      }
    }
    logger.exit("Out going !!!");
    if (errors.isEmpty())
    {
      return getSuccessForwardWithParameters(mapping, payments, subcontractInvoiceIds);
    }
    else
    {
      saveErrors(request, errors);
      actionForward = mapping.findForward("error");
    }
    return actionForward;
  }
 
  private String updatePaymentsOnSubcontractInvoices(SubcontractInvoice subcontractInvoice, Payments payments, MessageResources messageResources)
  {
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    StringBuffer subcontractInvoiceIds = new StringBuffer();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
    String subcontractInvoicePaymentsAcceptStatus  = FileHandler.getInstance().getNhsPaymentsAcceptStatus();
    for (PaymentLine paymentLine : payments.getPaymentLines())
    {
      if (paymentLine.getStatus().equalsIgnoreCase(subcontractInvoicePaymentsAcceptStatus))
      {
        Calendar calendar = Calendar.getInstance();
        Date todaysDate = new Date(calendar.getTimeInMillis());
        subcontractInvoice = paymentLine.getSubcontractInvoice();
        subcontractInvoice.setPaidDate(todaysDate);
        subcontractInvoice.setRemittanceAdvice(payments.getFileName());
        DecimalFormat decimalFormat = getDecimalFormat();
        String totalPayment = AgyConstants.POUND_SIGN + decimalFormat.format(payments.getTotalPayment());
        subcontractInvoice.appendNotes(messageResources.getMessage("text.payments.updatedFrom", payments.getFileName(), totalPayment, sdf.format(new java.util.Date())));
        agyService.updateSubcontractInvoicePaidDate(subcontractInvoice, getConsultantLoggedIn().getConsultantId());
        if (subcontractInvoiceIds.length() > 0)
        {
          subcontractInvoiceIds.append(",");
        }
        subcontractInvoiceIds.append(subcontractInvoice.getSubcontractInvoiceId());
      }            
    }
    return subcontractInvoiceIds.toString();
  }
  
  private ActionForward getSuccessForwardWithParameters(ActionMapping mapping, Payments payments, String subcontractInvoiceIds)
  {
    ActionForward actionForward = mapping.findForward("success");
    String fullPath = encode(actionForward.getPath() + "?subcontractInvoiceIds=" + subcontractInvoiceIds + "&subcontractInvoicePaymentsFilename=" + payments.getFileName().replace(" ", "%20"));
    return new ActionForward(actionForward.getName(), fullPath, actionForward.getRedirect());

  }
}
