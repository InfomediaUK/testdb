package com.helmet.application.admin;

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

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.MailHandler;
import com.helmet.bean.Administrator;


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

      	Administrator administrator = (Administrator)dynaForm.get("administrator");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		administrator = adminService.getAdministrator(administrator.getAdministratorId());
        
        // what if no email address
        if (administrator.getUser().getEmailAddress() == null || "".equals(administrator.getUser().getEmailAddress())) {
    		ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("login", new ActionMessage("errors.noEmailAddress", messageResources.getMessage("label.consultant")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
        }

        // should now generate a new pwd
      	String newPwd = new SimpleDateFormat("ssmmHHSSS").format(new Date());
        
      	// update the administrator record
      	int rc = adminService.updateAdministratorPwd(administrator.getAdministratorId(), newPwd, AdminConstants.RESETPWDHINT, administrator.getNoOfChanges(), administrator.getAdministratorId());

      	// send email

      	System.out.println(newPwd);

		// TODO temporarily send an email to kevin@matchmyjob.co.uk
		//
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    	String from = "admin@matchmyjob.co.uk";
        String to = "kevin@matchmyjob.co.uk"; // administrator.getUser().getEmailAddress()
		if (MailHandler.getInstance().getSendResetPwdEmail()) {
			to = administrator.getUser().getEmailAddress();
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
      	
      	
      	
      	
        
        dynaForm.set("administrator", administrator);

        return mapping.findForward("success");
    }

}
