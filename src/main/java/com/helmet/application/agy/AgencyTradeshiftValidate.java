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
import com.helmet.application.TradeshiftHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.jersey.tradeshift.TradeshiftBase;
import com.helmet.xml.jaxb.model.tradeshift.CompanyAccountInfo;

public class AgencyTradeshiftValidate extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    logger.entry("In coming !!!");
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());

    String hostName = TradeshiftHandler.getInstance().getHostName();
    String tenantIdHeaderName = TradeshiftHandler.getInstance().getTenantIdHeaderName();
    String userAgent = TradeshiftHandler.getInstance().getUserAgent();
    String restUrlPart = TradeshiftHandler.getInstance().getRestUrlPart();
    String documentsUrlPart = TradeshiftHandler.getInstance().getDocumentsUrlPart();
    String dispatcherUrlPart = TradeshiftHandler.getInstance().getDispatcherUrlPart();
    String documentProfileId = TradeshiftHandler.getInstance().getDocumentProfileId();
    String documentTypeCode = TradeshiftHandler.getInstance().getDocumentTypeCode();
    TradeshiftBase tradeshiftBase = new TradeshiftBase(hostName, tenantIdHeaderName, userAgent, restUrlPart, documentsUrlPart, dispatcherUrlPart, documentProfileId, documentTypeCode);
    // Set up Agency specific values.
    tradeshiftBase.setConsumerKey(agency.getTradeshiftConsumerKey());
    tradeshiftBase.setConsumerSecret(agency.getTradeshiftConsumerSecret());
    tradeshiftBase.setTokenKey(agency.getTradeshiftTokenKey());
    tradeshiftBase.setTokenSecret(agency.getTradeshiftTokenSecret());
    tradeshiftBase.setTenantId(agency.getTradeshiftTenantId());
    CompanyAccountInfo agencyAccountInfo = null;
    if (tradeshiftBase.authorised())
    {
      try
      {
//        String documentId = tradeshiftBase.validate();
        tradeshiftBase.getConnections();
        agencyAccountInfo = tradeshiftBase.getCompanyAccounInfo(agency.getTradeshiftTenantId());
      }
      catch (Exception e)
      {
        ActionMessages errors = new ActionMessages();
        errors.add("agencyTradeshiftValidate", new ActionMessage("error.exception", e.getMessage()));
        saveErrors(request, errors);
        logger.debug("EXCEPTION: " + e.getMessage());
        return mapping.getInputForward();
      }
    }
    else
    {
      ActionMessages errors = new ActionMessages();
      errors.add("agencyTradeshiftValidate", new ActionMessage("error.exception", "NOT Authorised!"));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    
    dynaForm.set("hostName", hostName);
    dynaForm.set("tenantIdHeaderName", tenantIdHeaderName);
    dynaForm.set("userAgent", userAgent);
    dynaForm.set("restUrlPart", restUrlPart);
    dynaForm.set("documentsUrlPart", documentsUrlPart);
    dynaForm.set("dispatcherUrlPart", dispatcherUrlPart);
    dynaForm.set("documentProfileId", documentProfileId);
    dynaForm.set("documentTypeCode", documentTypeCode);
    dynaForm.set("companyName", agencyAccountInfo.getCompanyName());
    dynaForm.set("country", agencyAccountInfo.getCountry());
    dynaForm.set("state", agencyAccountInfo.getState());
    
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
