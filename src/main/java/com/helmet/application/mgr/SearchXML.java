package com.helmet.application.mgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.SiteUser;



public class SearchXML extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	String type = request.getParameter("type");

    	StringBuffer sb = new StringBuffer();
    	
    	sb.append("<?xml version=\"1.0\"?>");
    	sb.append("<list>");

    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();

    	// need to cater for ampersands in names !!!!
 
       	if ("site".equals(type)) {

        	List<SiteUser> list = mgrService.getSiteUsersForManager(getManagerLoggedIn().getManagerId());

        	if (list != null) {
            	for (SiteUser site: list) {
            		sb.append("<item id=\"");
            		sb.append(site.getSiteId());
            		sb.append("\" name=\"");
            		sb.append(site.getName().replaceAll("&", "&amp;"));
            		sb.append("\"/>");
            	}
        	}
    	}
    	
    	if ("location".equals(type)) {

        	String siteId = request.getParameter("siteId");
        	
    		List<LocationUser> list = null;
        	
        	if (siteId == null || "".equals(siteId)) {
        	    //
        		list = mgrService.getLocationUsersForManager(getManagerLoggedIn().getManagerId());
        	}
        	else {
        	    //
        		list = mgrService.getLocationUsersForManagerForSite(getManagerLoggedIn().getManagerId(), Integer.parseInt(siteId));
        	}
        	
        	if (list != null) {
            	for (LocationUser locationUser: list) {
            		sb.append("<item id=\"");
            		sb.append(locationUser.getLocationId());
            		sb.append("\" name=\"");
            		sb.append(locationUser.getName().replaceAll("&", "&amp;"));
            		sb.append(", ");
            		sb.append(locationUser.getSiteName().replaceAll("&", "&amp;"));
            		sb.append("\"/>");
            	}
        	}
    	}
    	
    	if ("jobProfile".equals(type)) {

        	String siteId = request.getParameter("siteId");
        	String locationId = request.getParameter("locationId");
        	
        	List<JobProfileUser> list = null;
        	
        	if (siteId == null || "".equals(siteId)) {
        	    // no site
            	if (locationId == null || "".equals(locationId)) {
            		// no location selected so get all 'distinct' jobprofiles the manager has access to
                	list = mgrService.getJobProfileUsersForManager(getManagerLoggedIn().getManagerId());
            	}
            	else {
            		// location selected so get all jobprofiles the manager has access to for the location
                	list = mgrService.getJobProfileUsersForManagerForLocation(getManagerLoggedIn().getManagerId(), Integer.parseInt(locationId));
            	}
        	}
        	else {
            	if (locationId == null || "".equals(locationId)) {
            		// no location selected so get all 'distinct' jobprofiles the manager has access to
                	list = mgrService.getJobProfileUsersForManagerForSite(getManagerLoggedIn().getManagerId(), Integer.parseInt(siteId));
            	}
            	else {
            		// location selected so get all jobprofiles the manager has access to for the location
                	list = mgrService.getJobProfileUsersForManagerForLocation(getManagerLoggedIn().getManagerId(), Integer.parseInt(locationId));
            	}
        		
        	}
            	
        	if (list != null) {
            	for (JobProfileUser jobProfileUser: list) {
            		sb.append("<item id=\"");
            		sb.append(jobProfileUser.getJobProfileId());
            		sb.append("\" name=\"");
            		sb.append(jobProfileUser.getName().replaceAll("&", "&amp;"));
            		sb.append("\"/>");
            	}
        	}

    	}
    	sb.append("</list>");    	
    	
		response.setHeader("Cache-Control", "max-age=30");
		response.setContentType("application/xml");
    response.setCharacterEncoding("UTF-8");
		
		response.setContentLength(sb.length());
	
		ServletOutputStream sos;
	
		try {
		
			sos = response.getOutputStream();
					
			sos.print(sb.toString());
					
			sos.flush();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	logger.exit("Out going !!!");
    	
    	return null;

    }

}	