package com.helmet.application.agy;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.SubcontractInvoiceUser;

public class SubcontractInvoicesForRemittanceAdvice extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    String remittanceAdvice = (String)dynaForm.get("remittanceAdvice");
    BigDecimal totalValue = new BigDecimal(0);
    // Fill list with SubcontractInvoices.
    List<SubcontractInvoiceUser> list = agyService.getSubcontractInvoiceUsersForRemittanceAdvice(getConsultantLoggedIn().getAgencyId(), remittanceAdvice);
    for (SubcontractInvoiceUser subcontractInvoiceUser : list)
    {
      totalValue = totalValue.add(subcontractInvoiceUser.getValue());
    }
    // A list of SubcontractInvoiceUsers.
    dynaForm.set("list", list);
    dynaForm.set("totalValue", totalValue);
    dynaForm.set("remittanceAdvice", remittanceAdvice);
    dynaForm.set("recordCount", list.size());
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
