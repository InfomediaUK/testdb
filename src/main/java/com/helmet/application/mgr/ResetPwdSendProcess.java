package com.helmet.application.mgr;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.MailHandler;
import com.helmet.bean.Manager;


public class ResetPwdSendProcess extends Action {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
      	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

      	Manager manager = (Manager)dynaForm.get("manager");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

        manager = mgrService.getManager(manager.getManagerId());
        
        // what if no email address
        if (manager.getUser().getEmailAddress() == null || "".equals(manager.getUser().getEmailAddress())) {
    		ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("login", new ActionMessage("errors.noEmailAddress", messageResources.getMessage("label.manager")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
        }

        // should now generate a new pwd
      	String newPwd = new SimpleDateFormat("ssmmHHSSS").format(new Date());
        
      	// update the manager record
      	int rc = mgrService.updateManagerPwd(manager.getManagerId(), newPwd, MgrConstants.RESETPWDHINT, manager.getNoOfChanges(), manager.getManagerId());

      	// send email

      	System.out.println(newPwd);

		// TODO temporarily send an email to kevin@matchmyjob.co.uk
		//
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    	String from = "admin@matchmyjob.co.uk";
        String to = "kevin@matchmyjob.co.uk"; // manager.getUser().getEmailAddress()
		if (MailHandler.getInstance().getSendResetPwdEmail()) {
			to = manager.getUser().getEmailAddress();
		}
        String subject = "MMJ Password Reset";

        StringBuffer content = new StringBuffer();
        content.append("\n");
        content.append("MMJ Password Reset");
        content.append("\n\n");
        content.append("New Password - ");
        content.append(newPwd);
        content.append("\n\n");
        content.append("You will be asked to change this when you next login.");
        content.append("\n");

        String contentType = "text/plain";

        int status = MailHandler.getInstance().sendSingleMail(from, to, subject, content.toString(), contentType);
        System.out.println(status);

		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
      	
      	
      	
      	
        
        dynaForm.set("manager", manager);

        return mapping.findForward("success");
    }

}
