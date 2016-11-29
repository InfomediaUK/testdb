package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.MailHandler;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;


public class SendEmail extends AgyAction 
{

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) 
    {
      DynaValidatorForm dynaForm = (DynaValidatorForm) form;
  
      String referer = request.getHeader("Referer");
  
      String fromEmailAddress = getConsultantLoggedIn().getUser().getEmailAddress();
      String fromFullName = getConsultantLoggedIn().getUser().getFullName();
  
      String fromNiceEmailAddress = Utilities.makeNiceEmailAddress(fromFullName, fromEmailAddress);
  
      dynaForm.set("referer", referer);
      dynaForm.set("fromEmailAddress", fromNiceEmailAddress);
  
      // bcc to self
      dynaForm.set("bccEmailAddress", fromNiceEmailAddress);

      dynaForm.set("hostName", MailHandler.getInstance().getHostName());
      dynaForm.set("hostValue", MailHandler.getInstance().getHostValue());
      dynaForm.set("portName", MailHandler.getInstance().getPortName());
      dynaForm.set("portValue", MailHandler.getInstance().getPortValue());
      dynaForm.set("userName", MailHandler.getInstance().getUserName());

      ActionForward af = setFormProperties(mapping, dynaForm, request);
  
      if (af != null) { return af; }
  
      return mapping.findForward("success");
    }

    protected ActionForward setFormProperties(ActionMapping mapping, DynaValidatorForm dynaForm, HttpServletRequest request) 
    {
    	// override in descendant classes
    	return null;
    }

}
