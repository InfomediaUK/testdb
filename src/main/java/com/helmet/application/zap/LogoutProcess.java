package com.helmet.application.zap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helmet.application.app.AppUtilities;
import com.helmet.application.app.abztract.AppAction;


public class LogoutProcess extends AppAction 
{
    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) 
    {
    	AppUtilities.invalidateSession(request);
     	return mapping.findForward("success");
    }
}
