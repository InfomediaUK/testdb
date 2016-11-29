package com.helmet.application.agy;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.ActionDispatcher;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationJobProfileUser;
import com.helmet.bean.Site;

public class LocationJobProfileActionDispatcher extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    protected ActionDispatcher dispatcher = new ActionDispatcher(this, ActionDispatcher.MAPPING_FLAVOR);

    public ActionForward doExecute(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
                	
    	try {
			return dispatcher.execute(mapping, form, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
    }
 
    public ActionForward add(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

		DynaValidatorForm dynaForm = (DynaValidatorForm) form;

		logger.entry("In coming !!!");

		Location location = (Location)dynaForm.get("location");
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		// need to get client of location - using the site of the location
		Site site = agyService.getSite(location.getSiteId());

		List<JobProfileUser> list = agyService.getJobProfileUsersNotForLocation(site.getClientId(), location.getLocationId());
		
		dynaForm.set("list", list);

		logger.exit("Out going !!!");

		return mapping.findForward("success");
    }
    
    public ActionForward addProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Location location = (Location)dynaForm.get("location");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
	    	                         actionForward.getRedirect());
			
		}		
		
    	String[] selectedItems = (String[])dynaForm.get("selectedItems");
		
     	AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            LocationJobProfile ljp = new LocationJobProfile();
            ljp.setLocationId(location.getLocationId());
            ljp.setJobProfileId(new Integer(st.nextToken().trim()));
            int rowCount = agyService.insertLocationJobProfile(ljp, getConsultantLoggedIn().getConsultantId());
    	}

		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		LocationJobProfileUser locationJobProfile = (LocationJobProfileUser)dynaForm.get("locationJobProfile");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + locationJobProfile.getLocationId(),
	    	                         actionForward.getRedirect());
		}		
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		locationJobProfile = agyService.getLocationJobProfileUser(locationJobProfile.getLocationJobProfileId());
		
		dynaForm.set("locationJobProfile", locationJobProfile); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		LocationJobProfileUser locationJobProfile = (LocationJobProfileUser)dynaForm.get("locationJobProfile");
	
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
	
		locationJobProfile = agyService.getLocationJobProfileUser(locationJobProfile.getLocationJobProfileId());
	
		dynaForm.set("locationJobProfile", locationJobProfile); 
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		LocationJobProfile locationJobProfile = (LocationJobProfile)dynaForm.get("locationJobProfile");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?locationJobProfile.locationJobProfileId=" + locationJobProfile.getLocationJobProfileId(),
	    	                         actionForward.getRedirect());
		}		
		
     	ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		try {
			int rowCount = agyService.updateLocationJobProfileRate(locationJobProfile.getLocationJobProfileId(), 
					                                               locationJobProfile.getRate(), 
					                                               locationJobProfile.getNoOfChanges(), 
					                                               getConsultantLoggedIn().getConsultantId());
		}  
		catch (DuplicateDataException e) {
			errors.add("locationJobProfile", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + locationJobProfile.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward remove(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

		DynaValidatorForm dynaForm = (DynaValidatorForm) form;

		logger.entry("In coming !!!");

		Location location = (Location)dynaForm.get("location");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		List<LocationJobProfileUser> list = agyService.getLocationJobProfileUsersForLocation(location.getLocationId());

		dynaForm.set("list", list);

		logger.exit("Out going !!!");

		return mapping.findForward("success");
    }
    
    public ActionForward removeProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Location location = (Location)dynaForm.get("location");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
	    	                         actionForward.getRedirect());
		}		

    	String[] selectedItems = (String[])dynaForm.get("selectedItems");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            Integer locationJobProfileId = new Integer(st.nextToken().trim());
            Integer noOfChanges = new Integer(st.nextToken().trim());
    		int rowCount = agyService.deleteLocationJobProfile(locationJobProfileId, noOfChanges, getConsultantLoggedIn().getConsultantId());
    	}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
}
