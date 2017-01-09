package com.helmet.application.mgr;

import java.util.Calendar;
import java.util.List;

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
import com.helmet.application.admin.ShiftUtilities;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.Location;
import com.helmet.bean.Shift;

public class ShiftActionDispatcher extends MgrAction {

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
 
    public ActionForward newX(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

    	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    	
    	logger.entry("In coming !!!");
    	
    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");

    }
    
    public ActionForward newProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Shift shift = (Shift)dynaForm.get("shift");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + shift.getLocationId(),
	    	                         actionForward.getRedirect());
			
		}		
		
     	//
     	String shiftStartHour = (String)dynaForm.get("shiftStartHour");
     	String shiftStartMinute = (String)dynaForm.get("shiftStartMinute");
     	String shiftEndHour = (String)dynaForm.get("shiftEndHour");
     	String shiftEndMinute = (String)dynaForm.get("shiftEndMinute");
     	String shiftBreakStartHour = (String)dynaForm.get("shiftBreakStartHour");
     	String shiftBreakStartMinute = (String)dynaForm.get("shiftBreakStartMinute");
     	String shiftBreakEndHour = (String)dynaForm.get("shiftBreakEndHour");
     	String shiftBreakEndMinute = (String)dynaForm.get("shiftBreakEndMinute");

     	ShiftUtilities.sortTimes(shift,
     			                 shiftStartHour, shiftStartMinute, shiftEndHour, shiftEndMinute, 
     			                 shiftBreakStartHour, shiftBreakStartMinute, shiftBreakEndHour, shiftBreakEndMinute);

     	ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.insertShift(shift, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("shift", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + shift.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Shift shift = (Shift)dynaForm.get("shift");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + shift.getLocationId(),
	    	                         actionForward.getRedirect());
		}		
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		shift = mgrService.getShift(shift.getShiftId());
		
		dynaForm.set("shift", shift); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		Shift shift = (Shift)dynaForm.get("shift");
	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
	
		shift = mgrService.getShift(shift.getShiftId());
	
		dynaForm.set("shift", shift); 
		
		//
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(shift.getStartTime().getTime());
		dynaForm.set("shiftStartHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
		dynaForm.set("shiftStartMinute", String.valueOf(cal.get(Calendar.MINUTE)));
		cal.setTimeInMillis(shift.getEndTime().getTime());
		dynaForm.set("shiftEndHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
		dynaForm.set("shiftEndMinute", String.valueOf(cal.get(Calendar.MINUTE)));
		if (shift.getBreakStartTime() != null) {
			cal.setTimeInMillis(shift.getBreakStartTime().getTime());
			dynaForm.set("shiftBreakStartHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
			dynaForm.set("shiftBreakStartMinute", String.valueOf(cal.get(Calendar.MINUTE)));
		}
		if (shift.getBreakEndTime() != null) {
			cal.setTimeInMillis(shift.getBreakEndTime().getTime());
			dynaForm.set("shiftBreakEndHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
			dynaForm.set("shiftBreakEndMinute", String.valueOf(cal.get(Calendar.MINUTE)));
		}
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Shift shift = (Shift)dynaForm.get("shift");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?shift.shiftId=" + shift.getShiftId(),
	    	                         actionForward.getRedirect());
		}		
		
     	//
     	String shiftStartHour = (String)dynaForm.get("shiftStartHour");
     	String shiftStartMinute = (String)dynaForm.get("shiftStartMinute");
     	String shiftEndHour = (String)dynaForm.get("shiftEndHour");
     	String shiftEndMinute = (String)dynaForm.get("shiftEndMinute");
     	String shiftBreakStartHour = (String)dynaForm.get("shiftBreakStartHour");
     	String shiftBreakStartMinute = (String)dynaForm.get("shiftBreakStartMinute");
     	String shiftBreakEndHour = (String)dynaForm.get("shiftBreakEndHour");
     	String shiftBreakEndMinute = (String)dynaForm.get("shiftBreakEndMinute");

     	ShiftUtilities.sortTimes(shift,
     			                 shiftStartHour, shiftStartMinute, shiftEndHour, shiftEndMinute, 
     			                 shiftBreakStartHour, shiftBreakStartMinute, shiftBreakEndHour, shiftBreakEndMinute);

     	ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.updateShift(shift, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("shift", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + shift.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward deleteProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Shift shift = (Shift)dynaForm.get("shift");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?shift.shiftId=" + shift.getShiftId(),
	    	                         actionForward.getRedirect());
		}		

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowCount = mgrService.deleteShift(shift.getShiftId(), shift.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + shift.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward order(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Location location = (Location)dynaForm.get("location");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		List<Shift> list = mgrService.getShiftsForLocation(location.getLocationId());
		
		dynaForm.set("list", list);
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");

    }
    
    public ActionForward orderProcess(ActionMapping mapping,
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
		
		String order = (String)dynaForm.get("order");
		Boolean zeroiseDisplayOrder = (Boolean)dynaForm.get("zeroiseDisplayOrder");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowcount = mgrService.updateShiftDisplayOrder(order, zeroiseDisplayOrder, getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
}
