package com.helmet.application.agy;



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
import com.helmet.application.PdfHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportInvoice extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    logger.entry("In coming !!!");
    NhsBackingReportUser nhsBackingReportUser = (NhsBackingReportUser)dynaForm.get("nhsBackingReportUser");
    String nhsLocation = (String)dynaForm.get("nhsLocation");
    String nhsWard = (String)dynaForm.get("nhsWard");
    java.util.Date invoiceDate = new java.util.Date();
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    nhsBackingReportUser = agyService.getNhsBackingReportUser(nhsBackingReportUser.getNhsBackingReportId());
    ClientUser client = agyService.getClientUser(nhsBackingReportUser.getClientId());
    AgencyUser fromAgency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    AgencyUser toAgency = null;
    String serverName = request.getServerName();
    try
    {
      PdfHandler.getInstance().writeNhsInvoice(messageResources, client, fromAgency, nhsBackingReportUser, nhsLocation, nhsWard, invoiceDate, serverName);
      if (nhsBackingReportUser.getSubcontractAgencyId() > 0)
      {
        // Backing Report has been subcontracted...
        toAgency = agyService.getAgencyUser(nhsBackingReportUser.getSubcontractAgencyId());
        PdfHandler.getInstance().writeSubcontractInvoice(messageResources, toAgency, fromAgency, nhsBackingReportUser, nhsLocation, nhsWard, invoiceDate);
      }
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
      ActionMessages errors = new ActionMessages();
      errors.add("nhsBackingReportInvoice", new ActionMessage("error.exception", e.getMessage()));
      saveErrors(request, errors);
      logger.debug("EXCEPTION: " + e.getMessage());
      return mapping.getInputForward();
    }
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?nhsBackingReportUser.nhsBackingReportId=" + nhsBackingReportUser.getNhsBackingReportId(), actionForward.getRedirect());
  }

}
