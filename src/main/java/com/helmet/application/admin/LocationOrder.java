package com.helmet.application.admin;

import java.util.List;

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
import com.helmet.bean.Location;
import com.helmet.bean.SiteUser;


public class LocationOrder extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
		SiteUser site = (SiteUser)dynaForm.get("site");
    	
    	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		site = adminService.getSiteUser(site.getSiteId());
		ClientUser client = adminService.getClientUser(site.getClientId());
		List<Location> list = adminService.getLocationsForSite(site.getSiteId(), getShowOnlyActive());

		dynaForm.set("client", client); 
		dynaForm.set("site", site); 
     	dynaForm.set("list", list);

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
