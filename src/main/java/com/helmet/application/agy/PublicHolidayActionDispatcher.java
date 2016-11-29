package com.helmet.application.agy;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import com.helmet.bean.PublicHoliday;

public class PublicHolidayActionDispatcher extends AgyAction {

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

		PublicHoliday publicHoliday = (PublicHoliday)dynaForm.get("publicHoliday");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?client.clientId=" + publicHoliday.getClientId(),
	    	                         actionForward.getRedirect());
			
		}		

		String phDate = (String)dynaForm.get("phDate");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);

		try {
			publicHoliday.setPhDate(new Date(sdf.parse(phDate).getTime()));
		} catch (ParseException e) {
		    errors.add("publicHoliday", new ActionMessage("errors.invalid", messageResources.getMessage("label.date")));
		    saveErrors(request, errors);
			return mapping.getInputForward();
		}

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		try {
			int rowCount = agyService.insertPublicHoliday(publicHoliday, getConsultantLoggedIn().getConsultantId());
		}  
		catch (DuplicateDataException e) {
		    errors.add("publicHoliday", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		    saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?publicHoliday.publicHolidayId=" + publicHoliday.getPublicHolidayId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		PublicHoliday publicHoliday = (PublicHoliday)dynaForm.get("publicHoliday");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?client.clientId=" + publicHoliday.getClientId(),
	    	                         actionForward.getRedirect());
		}		
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		publicHoliday = agyService.getPublicHoliday(publicHoliday.getPublicHolidayId());
		
		dynaForm.set("publicHoliday", publicHoliday); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		
		PublicHoliday publicHoliday = (PublicHoliday)dynaForm.get("publicHoliday");
	
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
	
		publicHoliday = agyService.getPublicHoliday(publicHoliday.getPublicHolidayId());
	
		String phDate = new SimpleDateFormat("dd/MM/yyyy").format(publicHoliday.getPhDate());

		dynaForm.set("publicHoliday", publicHoliday); 
		dynaForm.set("phDate", phDate); 
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		PublicHoliday publicHoliday = (PublicHoliday)dynaForm.get("publicHoliday");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?publicHoliday.publicHolidayId=" + publicHoliday.getPublicHolidayId(),
	    	                         actionForward.getRedirect());
		}		

		String phDate = (String)dynaForm.get("phDate");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);

		try {
			publicHoliday.setPhDate(new Date(sdf.parse(phDate).getTime()));
		} catch (ParseException e) {
		    errors.add("publicHoliday", new ActionMessage("errors.invalid", messageResources.getMessage("label.date")));
		    saveErrors(request, errors);
			return mapping.getInputForward();
		}

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		try {
			int rowCount = agyService.updatePublicHoliday(publicHoliday, getConsultantLoggedIn().getConsultantId());
		}  
		catch (DuplicateDataException e) {
		    errors.add("publicHoliday", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		    saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?publicHoliday.publicHolidayId=" + publicHoliday.getPublicHolidayId(),
                                 actionForward.getRedirect());
		
	}
    
    public ActionForward deleteProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		PublicHoliday publicHoliday = (PublicHoliday)dynaForm.get("publicHoliday");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?publicHoliday.publicHolidayId=" + publicHoliday.getPublicHolidayId(),
	    	                         actionForward.getRedirect());
		}		

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		int rowCount = agyService.deletePublicHoliday(publicHoliday.getPublicHolidayId(), publicHoliday.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?client.clientId=" + publicHoliday.getClientId(),
                                 actionForward.getRedirect());
		
	}
    
}
