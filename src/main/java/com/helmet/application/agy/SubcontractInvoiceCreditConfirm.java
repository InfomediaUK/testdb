package com.helmet.application.agy;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceUser;

public class SubcontractInvoiceCreditConfirm extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    SubcontractInvoiceUser subcontractInvoiceUser = (SubcontractInvoiceUser)dynaForm.get("subcontractInvoiceUser");
    String creditDateStr = (String)dynaForm.get("creditDateStr");
    String notes = (String)dynaForm.get("notes");
    Date creditDate =  convertDate(creditDateStr, sdf, errors, messageResources, "label.creditDate");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    subcontractInvoiceUser = agyService.getSubcontractInvoiceUser(subcontractInvoiceUser.getSubcontractInvoiceId());
    subcontractInvoiceUser.setDate(creditDate);
    subcontractInvoiceUser.setNotes(notes);
    // Fill list with SubcontractInvoiceItems for the SubcontractInvoice.
    List<SubcontractInvoiceItem> listSubcontractInvoiceItems = agyService.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
    dynaForm.set("subcontractInvoiceUser", subcontractInvoiceUser);
    dynaForm.set("list", listSubcontractInvoiceItems);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
