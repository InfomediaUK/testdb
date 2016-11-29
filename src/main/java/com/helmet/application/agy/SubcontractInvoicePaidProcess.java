package com.helmet.application.agy;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.SubcontractInvoice;

public class SubcontractInvoicePaidProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    SubcontractInvoice subcontractInvoiceUser = (SubcontractInvoice)dynaForm.get("subcontractInvoiceUser");
    String paidDateStr = (String)dynaForm.get("paidDateStr");
    String remittanceAdvice = subcontractInvoiceUser.getRemittanceAdvice();
    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
    }   
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    subcontractInvoiceUser = agyService.getSubcontractInvoiceUser(subcontractInvoiceUser.getSubcontractInvoiceId());
    if (subcontractInvoiceUser != null)
    {
      if (StringUtils.isEmpty(paidDateStr))
      {
        subcontractInvoiceUser.setPaidDate(null);
      }
      else
      {
        Date paidDate = convertDate(paidDateStr, sdf, errors, messageResources, "label.paidDate");
        subcontractInvoiceUser.setPaidDate(paidDate);
        subcontractInvoiceUser.setRemittanceAdvice(remittanceAdvice);
      }
      int rowCount = agyService.updateSubcontractInvoicePaidDate(subcontractInvoiceUser, getConsultantLoggedIn().getConsultantId());
    }    
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
  }

}
