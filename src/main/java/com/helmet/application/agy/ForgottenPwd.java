package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helmet.bean.Consultant;
import com.helmet.bean.User;

public class ForgottenPwd extends Action
{
  private static final Logger logger = LoggerFactory.getLogger(ForgottenPwd.class);

  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) 
  {
    logger.debug("Entering");
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    Consultant consultant = new Consultant();
    User user = new User();
    user.setLogin("xyz");
    consultant.setUser(user);
    dynaForm.set("consultant", consultant);
    dynaForm.set("agencyCode", "PJRGN");
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    logger.debug("Leaving");
    return mapping.findForward("success");
  }
}
