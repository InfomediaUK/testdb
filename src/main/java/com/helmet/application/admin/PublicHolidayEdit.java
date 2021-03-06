package com.helmet.application.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.ClientUser;
import com.helmet.bean.PublicHoliday;


public class PublicHolidayEdit extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
     	PublicHoliday publicHoliday = (PublicHoliday)dynaForm.get("publicHoliday");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		publicHoliday = adminService.getPublicHoliday(publicHoliday.getPublicHolidayId());
		ClientUser client = adminService.getClientUser(publicHoliday.getClientId());
    	
		String phDate = new SimpleDateFormat("yyyy-MM-dd").format(publicHoliday.getPhDate());
			
		dynaForm.set("client", client); 
		dynaForm.set("publicHoliday", publicHoliday); 
		dynaForm.set("phDate", phDate); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
