package com.helmet.application.agy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceUser;

public class SubcontractInvoiceView extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SubcontractInvoiceUser subcontractInvoiceUser = (SubcontractInvoiceUser)dynaForm.get("subcontractInvoiceUser");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    subcontractInvoiceUser = agyService.getSubcontractInvoiceUser(subcontractInvoiceUser.getSubcontractInvoiceId());
    // Fill list with SubcontractInvoiceItems for the SubcontractInvoice.
    List<SubcontractInvoiceItem> listSubcontractInvoiceItems = agyService.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
    if (StringUtils.isNotEmpty(subcontractInvoiceUser.getRemittanceAdvice()))
    {
      String remittanceAdviceFileName = subcontractInvoiceUser.getRemittanceAdvice();
      String remittanceAdviceFilePath = FileHandler.getInstance().getNhsPaymentsFileLocation() + com.helmet.application.FileHandler.getInstance().getNhsPaymentsFileFolder() + 
        "/a" + subcontractInvoiceUser.getToAgencyId() + "/" + remittanceAdviceFileName;
      File remittanceAdviceFile = new File(remittanceAdviceFilePath);
      subcontractInvoiceUser.setRemittanceAdviceIsFile(remittanceAdviceFile.exists());
    }    
    dynaForm.set("subcontractInvoiceUser", subcontractInvoiceUser);
    if (mapping.getName().equals("SubcontractInvoiceCreditFormAgy"))
    {
      dynaForm.set("creditDateStr", subcontractInvoiceUser.getPaidDate() == null ? "" : sdf.format(subcontractInvoiceUser.getPaidDate()));
    }
    else
    {
      dynaForm.set("paidDateStr", subcontractInvoiceUser.getPaidDate() == null ? "" : sdf.format(subcontractInvoiceUser.getPaidDate()));
    }
    dynaForm.set("list", listSubcontractInvoiceItems);
    dynaForm.set("noOfNhsBookings", listSubcontractInvoiceItems.size());
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
