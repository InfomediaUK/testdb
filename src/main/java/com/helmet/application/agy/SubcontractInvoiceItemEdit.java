package com.helmet.application.agy;

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
import com.helmet.bean.SubcontractInvoiceItemUser;

public class SubcontractInvoiceItemEdit extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    SubcontractInvoiceItemUser subcontractInvoiceItemUser = (SubcontractInvoiceItemUser)dynaForm.get("subcontractInvoiceItemUser");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    subcontractInvoiceItemUser = agyService.getSubcontractInvoiceItemUser(subcontractInvoiceItemUser.getSubcontractInvoiceItemId());
    dynaForm.set("subcontractInvoiceItemUser", subcontractInvoiceItemUser);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
