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
import com.helmet.bean.ReasonForRequest;

public class OrderStaff1 extends AgyAction
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
    if (client.getClientId() == null || client.getClientId() == 0) { return mapping.findForward("orderStaff"); }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    client = agyService.getClientUser(client.getClientId());
    dynaForm.set("client", client);

    List<ReasonForRequest> list = agyService.getReasonForRequestsForClient(client.getClientId());
    dynaForm.set("list", list);
    ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
    if (reasonForRequest.getReasonForRequestId() == null && list.size() == 1)
    {
      // First time thru and with a single entry list.
      dynaForm.set("reasonForRequest", list.get(0));
    }

    return mapping.findForward("success");
  }

}
