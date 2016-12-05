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
import com.helmet.bean.DressCode;
import com.helmet.bean.SiteUser;


public class DressCodeOrder extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
		Location location = (Location)dynaForm.get("location");
    	
    	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		location = adminService.getLocation(location.getLocationId());
		SiteUser site = adminService.getSiteUser(location.getSiteId());
		ClientUser client = adminService.getClientUser(site.getClientId());
		List<DressCode> list = adminService.getDressCodesForLocation(location.getLocationId(), getShowOnlyActive());

		dynaForm.set("client", client); 
		dynaForm.set("site", site); 
		dynaForm.set("location", location); 
     	dynaForm.set("list", list);

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
