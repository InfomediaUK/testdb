package com.helmet.application.agy;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.nhs.Payments;
import com.helmet.bean.nhs.PaymentLine;

public class NhsPaymentsFileAccept extends NhsFileUploadCommon
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    ActionForward actionForward = null;
    NhsBackingReport nhsBackingReport = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Client client = (Client)dynaForm.get("client");
    String accept = (String)dynaForm.get("accept");
    client = agyService.getClientUser(client.getClientId());
    String nhsBackingReportIds = null;
    Payments payments = (Payments)request.getSession().getAttribute("payments");
    if (payments == null)
    {
      // Backing Report vanished from Session!
      errors.add("nhsPaymentsAccept", new ActionMessage("error.nhsPaymentsFile.vanishedFromSession"));
    }
    else
    {
      try
      {
        if (payments.getFileName().equals(accept))
        {
          // Payments found in Session. Process it...
          nhsBackingReportIds = updatePaymentsOnNhsBackingReorts(nhsBackingReport, payments, messageResources);
        }
        else
        {
          request.getSession().removeAttribute("payments");
          errors.add("nhsPaymentsAccept", new ActionMessage("error.nhsPaymentsFile.notAsAccepted", accept));
        }
      }
      catch (Exception e)
      {
        errors.add("nhsPaymentsAccept", new ActionMessage("error.nhsPaymentsFile.exception", e.getMessage()));
      }
    }
    logger.exit("Out going !!!");
    if (errors.isEmpty())
    {
      return getSuccessForwardWithParameters(mapping, payments, nhsBackingReportIds);
    }
    else
    {
      saveErrors(request, errors);
      actionForward = mapping.findForward("error");
    }
    return actionForward;
  }
 
  private String updatePaymentsOnNhsBackingReorts(NhsBackingReport nhsBackingReport, Payments payments, MessageResources messageResources)
  {
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    StringBuffer nhsBackingReportIds = new StringBuffer();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
    String nhsPaymentsAcceptStatus  = FileHandler.getInstance().getNhsPaymentsAcceptStatus();
    for (PaymentLine paymentLine : payments.getPaymentLines())
    {
      if (paymentLine.getStatus().equalsIgnoreCase(nhsPaymentsAcceptStatus))
      {
        nhsBackingReport = paymentLine.getNhsBackingReport();
        nhsBackingReport.setPaidValue(paymentLine.getPayment());
        nhsBackingReport.setCompleteDate(paymentLine.getDate());
        DecimalFormat decimalFormat = getDecimalFormat();
        String totalPayment = AgyConstants.POUND_SIGN + decimalFormat.format(payments.getTotalPayment());
        nhsBackingReport.appendComment(messageResources.getMessage("text.payments.updatedFrom", payments.getFileName(), totalPayment, sdf.format(new java.util.Date())));
        agyService.updateNhsBackingReport(nhsBackingReport, getConsultantLoggedIn().getConsultantId());
        if (nhsBackingReportIds.length() > 0)
        {
          nhsBackingReportIds.append(",");
        }
        nhsBackingReportIds.append(nhsBackingReport.getNhsBackingReportId());
      }            
    }
    return nhsBackingReportIds.toString();
  }
  
  private ActionForward getSuccessForwardWithParameters(ActionMapping mapping, Payments payments, String nhsBackingReportIds)
  {
    ActionForward actionForward = mapping.findForward("success");
    String fullPath = encode(actionForward.getPath() + "?nhsBackingReportIds=" + nhsBackingReportIds + "&nhsPaymentsFilename=" + payments.getFileName().replace(" ", "%20"));
    return new ActionForward(actionForward.getName(), fullPath, actionForward.getRedirect());

  }
}
