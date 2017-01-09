package com.helmet.application.mgr;

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
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.Grade;
import com.helmet.bean.ReasonForRequest;

public class GradeActionDispatcher extends MgrAction {

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
 
    public ActionForward list(ActionMapping mapping, 
    		                  ActionForm form,
    		                  HttpServletRequest request, 
    		                  HttpServletResponse response) {

		DynaValidatorForm dynaForm = (DynaValidatorForm) form;

		logger.entry("In coming !!!");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		List<Grade> list = mgrService.getGradesForClient(getManagerLoggedIn().getClientId());

		dynaForm.set("list", list);

		logger.exit("Out going !!!");

		return mapping.findForward("success");
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
		
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Grade grade = (Grade)dynaForm.get("grade");
		grade.setClientId(getManagerLoggedIn().getClientId());
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.insertGrade(grade, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("grade", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Grade grade = (Grade)dynaForm.get("grade");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		grade = mgrService.getGrade(grade.getGradeId());
		
		dynaForm.set("grade", grade); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		Grade grade = (Grade)dynaForm.get("grade");
	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
	
		grade = mgrService.getGrade(grade.getGradeId());
	
		dynaForm.set("grade", grade); 
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Grade grade = (Grade)dynaForm.get("grade");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?grade.gradeId=" + grade.getGradeId(),
	    	                         actionForward.getRedirect());
		}		

		grade.setClientId(getManagerLoggedIn().getClientId());
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.updateGrade(grade, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("grade", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
    public ActionForward deleteProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Grade grade = (Grade)dynaForm.get("grade");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?grade.gradeId=" + grade.getGradeId(),
	    	                         actionForward.getRedirect());
		}		

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowCount = mgrService.deleteGrade(grade.getGradeId(), grade.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
    public ActionForward order(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		List<Grade> list = mgrService.getGradesForClient(getManagerLoggedIn().getClientId());
		
		dynaForm.set("list", list);
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");

    }
    
    public ActionForward orderProcess(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		String order = (String)dynaForm.get("order");
		Boolean zeroiseDisplayOrder = (Boolean)dynaForm.get("zeroiseDisplayOrder");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowcount = mgrService.updateGradeDisplayOrder(order, zeroiseDisplayOrder, getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
}
