package com.helmet.application.agy;

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
import com.helmet.bean.ClientUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.ReasonForRequest;

public class OrderStaff2 extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    if (isCancelled(request))
    {
      dynaForm.set("page", (Integer) dynaForm.get("page") - 1);
      return mapping.findForward("back");
    }

    ClientUser client = (ClientUser) dynaForm.get("client");
    if (client.getClientId() == null || client.getClientId() == 0) 
    { 
      return mapping.findForward("orderStaff"); 
    }

    ReasonForRequest reasonForRequest = (ReasonForRequest) dynaForm.get("reasonForRequest");
    if (reasonForRequest.getReasonForRequestId() == null || reasonForRequest.getReasonForRequestId() == 0) 
    { 
      return mapping.findForward("orderStaff"); 
    }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    reasonForRequest = agyService.getReasonForRequest(reasonForRequest.getReasonForRequestId());
    dynaForm.set("reasonForRequest", reasonForRequest);

    List<LocationUser> list = agyService.getLocationUsersForAgency(getConsultantLoggedIn().getAgencyId(), client.getClientId(), null);
    dynaForm.set("list", list);
    //
    LocationUser locationUser = (LocationUser) dynaForm.get("location");
    
    if (locationUser.getLocationId() == null && list.size() == 1)
    {
      // First time thru and with a single entry list.
      dynaForm.set("location", list.get(0));
    }
    
    if (locationUser.getLocationId() != null && locationUser.getLocationId() != 0)
    {
      dynaForm.set("origLocationId", locationUser.getLocationId());
    }

    return mapping.findForward("success");
  }

}
