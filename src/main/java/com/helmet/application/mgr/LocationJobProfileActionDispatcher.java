package com.helmet.application.mgr;

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

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationJobProfileUser;

public class LocationJobProfileActionDispatcher extends MgrAction {

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

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		List<JobProfileUser> list = mgrService.getJobProfileUsersNotForLocation(getManagerLoggedIn().getClientId(), location.getLocationId());
		
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
		
     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            LocationJobProfile ljp = new LocationJobProfile();
            ljp.setLocationId(location.getLocationId());
            ljp.setJobProfileId(new Integer(st.nextToken().trim()));
            int rowCount = mgrService.insertLocationJobProfile(ljp, getManagerLoggedIn().getManagerId());
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
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		locationJobProfile = mgrService.getLocationJobProfileUser(locationJobProfile.getLocationJobProfileId());
		
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
	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
	
		locationJobProfile = mgrService.getLocationJobProfileUser(locationJobProfile.getLocationJobProfileId());
	
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
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
			int rowCount = mgrService.updateLocationJobProfileRate(locationJobProfile.getLocationJobProfileId(), 
					                                               locationJobProfile.getRate(), 
					                                               locationJobProfile.getNoOfChanges(), 
					                                               getManagerLoggedIn().getManagerId());
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

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		List<LocationJobProfileUser> list = mgrService.getLocationJobProfileUsersForLocation(location.getLocationId());

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

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            Integer locationJobProfileId = new Integer(st.nextToken().trim());
            Integer noOfChanges = new Integer(st.nextToken().trim());
    		int rowCount = mgrService.deleteLocationJobProfile(locationJobProfileId, noOfChanges, getManagerLoggedIn().getManagerId());
    	}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
}
