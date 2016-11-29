package com.helmet.application.agy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.PdfHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.SubcontractInvoiceItemUser;
import com.helmet.bean.SubcontractInvoiceUser;

public class SubcontractInvoiceReissue extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    SubcontractInvoiceUser subcontractInvoiceUser = (SubcontractInvoiceUser)dynaForm.get("subcontractInvoiceUser");
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    subcontractInvoiceUser = agyService.getSubcontractInvoiceUser(subcontractInvoiceUser.getSubcontractInvoiceId());
    // Fill list with SubcontractInvoiceItems for the SubcontractInvoice.
    List<SubcontractInvoiceItemUser> listSubcontractInvoiceItemUser = agyService.getSubcontractInvoiceItemUsersForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
    subcontractInvoiceUser.setListSubcontractInvoiceItemUser(listSubcontractInvoiceItemUser);
    try
    {
      String serverName = request.getServerName();
      AgencyUser toAgency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
      AgencyUser fromAgency = agyService.getAgencyUser(subcontractInvoiceUser.getFromAgencyId());
      PdfHandler.getInstance().writeSubcontractInvoice(messageResources, toAgency, fromAgency, subcontractInvoiceUser, serverName);
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    dynaForm.set("subcontractInvoiceUser", subcontractInvoiceUser);
    dynaForm.set("list", listSubcontractInvoiceItemUser);
    dynaForm.set("noOfNhsBookings", listSubcontractInvoiceItemUser.size());
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
  }

}
