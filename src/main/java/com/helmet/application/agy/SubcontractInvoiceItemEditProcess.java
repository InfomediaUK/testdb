package com.helmet.application.agy;

import java.math.BigDecimal;
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
import com.helmet.bean.SubcontractInvoice;
import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceItemUser;

public class SubcontractInvoiceItemEditProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    SubcontractInvoiceItem subcontractInvoiceItem = (SubcontractInvoiceItem)dynaForm.get("subcontractInvoiceItemUser");
    subcontractInvoiceItem.setComment(subcontractInvoiceItem.getComment().trim());
    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceItem.getSubcontractInvoiceId(), actionForward.getRedirect());
    }   
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    SubcontractInvoiceItemUser oldSubcontractInvoiceItem = agyService.getSubcontractInvoiceItemUser(subcontractInvoiceItem.getSubcontractInvoiceItemId());
    int rowCount = agyService.updateSubcontractInvoiceItem(subcontractInvoiceItem, getConsultantLoggedIn().getConsultantId());
    if (oldSubcontractInvoiceItem.getValue().compareTo(subcontractInvoiceItem.getValue()) != 0)
    {
      // Value was CHANGED. Must now update SubcontractInvoice and ultimately re-issue the Invoice.
      SubcontractInvoice subcontractInvoice = agyService.getSubcontractInvoice(subcontractInvoiceItem.getSubcontractInvoiceId());
      List<SubcontractInvoiceItem> listSubcontractInvoiceItems = agyService.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceItem.getSubcontractInvoiceId());
      BigDecimal value = new BigDecimal(0);
      for (SubcontractInvoiceItem sII : listSubcontractInvoiceItems)
      {
        value = value.add(sII.getValue());
      }
      subcontractInvoice.setValue(value);
      rowCount += agyService.updateSubcontractInvoiceValue(subcontractInvoice, getConsultantLoggedIn().getConsultantId());
    }
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceItem.getSubcontractInvoiceId(), actionForward.getRedirect());
  }

}
