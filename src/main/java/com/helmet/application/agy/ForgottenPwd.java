package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.bean.Consultant;
import com.helmet.bean.User;

public class ForgottenPwd extends Action
{
  protected transient Log logger = LogFactory.getLog(getClass());

  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) 
  {
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    Consultant consultant = new Consultant();
    User user = new User();
    user.setLogin("xyz");
    consultant.setUser(user);
    dynaForm.set("consultant", consultant);
    dynaForm.set("agencyCode", "PJRGN");
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    return mapping.findForward("success");
  }
}
