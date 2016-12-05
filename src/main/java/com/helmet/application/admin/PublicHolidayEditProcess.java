package com.helmet.application.admin;

import java.sql.Date;

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
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.PublicHoliday;


public class PublicHolidayEditProcess extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
     	PublicHoliday publicHoliday = (PublicHoliday)dynaForm.get("publicHoliday");
    	String phDate = (String)dynaForm.get("phDate");

		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
    	try {
			publicHoliday.setPhDate(Date.valueOf(phDate));
		} catch (IllegalArgumentException e) {
            errors.add("publicHoliday", new ActionMessage("errors.invalid", messageResources.getMessage("label.date")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
		}
		
     	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		try {
		  int rowCount = adminService.updatePublicHoliday(publicHoliday, getAdministratorLoggedIn().getAdministratorId());
		}  
        catch (DuplicateDataException e) {
            errors.add("publicHoliday", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
            saveErrors(request, errors);
    		return mapping.getInputForward();
        }

        logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?client.clientId=" + publicHoliday.getClientId(),
    	                         actionForward.getRedirect());
    }

}
