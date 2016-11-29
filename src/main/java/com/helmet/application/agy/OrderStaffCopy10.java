package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.ClientUser;


public class OrderStaffCopy10 extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    Integer page = (Integer)dynaForm.get("page");

    if (isCancelled(request)) 
    {
      dynaForm.set("page", page - 1);
      return mapping.findForward("back");
    }

    // checks !!!
    ClientUser client = (ClientUser) dynaForm.get("client");
    if (client.getClientId() == null || client.getClientId() == 0) { return mapping.findForward("orderStaff"); }

    return mapping.findForward("success");
  }

}
