package com.helmet.application.agy;


import java.util.ArrayList;
import java.util.List;

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

import com.helmet.api.AdminService;
import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.TradeshiftHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.Client;
import com.helmet.jersey.tradeshift.TradeshiftBase;
import com.helmet.xml.jaxb.model.tradeshift.ClientTradeshiftDetails;
import com.helmet.xml.jaxb.model.tradeshift.CompanyAccountInfo;
import com.helmet.xml.jaxb.model.tradeshift.Connection;
import com.helmet.xml.jaxb.model.tradeshift.ConnectionList;
import com.helmet.xml.jaxb.model.tradeshift.TradeshiftConstants;

public class AgencyTradeshiftRefresh extends AgyAction
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
    dynaForm.set("hostName", hostName);
    dynaForm.set("tenantIdHeaderName", tenantIdHeaderName);
    dynaForm.set("userAgent", userAgent);
    dynaForm.set("restUrlPart", restUrlPart);
    dynaForm.set("documentsUrlPart", documentsUrlPart);
    dynaForm.set("dispatcherUrlPart", dispatcherUrlPart);
    dynaForm.set("documentProfileId", documentProfileId);
    dynaForm.set("documentTypeCode", documentTypeCode);
    TradeshiftBase tradeshiftBase = new TradeshiftBase(hostName, tenantIdHeaderName, userAgent, restUrlPart, documentsUrlPart, dispatcherUrlPart, documentProfileId, documentTypeCode);
    // Set up Agency specific values.
    tradeshiftBase.setConsumerKey(agency.getTradeshiftConsumerKey());
    tradeshiftBase.setConsumerSecret(agency.getTradeshiftConsumerSecret());
    tradeshiftBase.setTokenKey(agency.getTradeshiftTokenKey());
    tradeshiftBase.setTokenSecret(agency.getTradeshiftTokenSecret());
    tradeshiftBase.setTenantId(agency.getTradeshiftTenantId());
    CompanyAccountInfo clientAccountInfo = null;
    List<ClientTradeshiftDetails> changedClientList = new ArrayList<ClientTradeshiftDetails>();
    if (tradeshiftBase.authorised())
    {
      try
      {
//        String documentId = tradeshiftBase.validate();
        ConnectionList connectionList = tradeshiftBase.getConnections();
        for (Connection connection : connectionList.getConnections())
        {
          if (connection.getConnectionType().equals(TradeshiftConstants.TRADESHIFT_CONNECTION_TYPE))
          {
            if (connection.getState().equals(TradeshiftConstants.TRADESHIFT_CONNECTION_STATE_ACCEPTED))
            {
              String companyAccountId = connection.getCompanyAccountId();
              clientAccountInfo = tradeshiftBase.getCompanyAccounInfo(companyAccountId);
              String tradeshiftSbsPayablesCode = clientAccountInfo.getIdentifier(TradeshiftConstants.IDENTIFIER_SCHEME, TradeshiftConstants.IDENTIFIER_PAYABLES);
              tradeshiftSbsPayablesCode = tradeshiftSbsPayablesCode.substring(tradeshiftSbsPayablesCode.indexOf(":") + 1);
              // Set up ClientTradeshiftDetails with values found on Tradeshift.
              ClientTradeshiftDetails clientTradeshiftDetails = new ClientTradeshiftDetails();
              clientTradeshiftDetails.setSbsPayablesCode(tradeshiftSbsPayablesCode);
              clientTradeshiftDetails.setNewCompanyAccountId(clientAccountInfo.getCompanyAccountId());
              clientTradeshiftDetails.setNewState(clientAccountInfo.getState());
              AdminService adminService = ServiceFactory.getInstance().getAdminService();
              List<Client> clients = adminService.getClientsForTradeshiftSbsPayablesCode(tradeshiftSbsPayablesCode);
              for(Client client : clients)
              {
                if (client == null)
                {
                  // Client for SBS Payables Code NOT Found.
                  clientTradeshiftDetails.setClientName(clientAccountInfo.getCompanyName());
                  clientTradeshiftDetails.setOldCompanyAccountId("Not Found");
                  changedClientList.add(clientTradeshiftDetails);
                }
                else
                {
                  // Client found.
                  clientTradeshiftDetails.setClientName(client.getName());
                  clientTradeshiftDetails.setOldCompanyAccountId(client.getTradeshiftCompanyAccountId());
                  clientTradeshiftDetails.setOldState(client.getTradeshiftState());
                  client.setTradeshiftCompanyAccountId(clientAccountInfo.getCompanyAccountId());
                  client.setTradeshiftState(clientAccountInfo.getState());
                  if (clientTradeshiftDetails.changed())
                  {
                    adminService.updateClientTradeshiftDetails(client, getConsultantLoggedIn().getConsultantId());
                    changedClientList.add(clientTradeshiftDetails);
                  }
                }
              }              
            }
          }
        }
      }
      catch (Exception e)
      {
        ActionMessages errors = new ActionMessages();
        errors.add("agencyTradeshiftValidate", new ActionMessage("error.exception", e.getMessage()));
        saveErrors(request, errors);
        logger.debug("EXCEPTION: " + e.getMessage());
        return mapping.findForward("error");
      }
    }
    else
    {
      ActionMessages errors = new ActionMessages();
      errors.add("agencyTradeshiftValidate", new ActionMessage("error.exception", "NOT Authorised!"));
      saveErrors(request, errors);
      return mapping.findForward("error");
    }
    
    dynaForm.set("changedClientList", changedClientList);
    
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
