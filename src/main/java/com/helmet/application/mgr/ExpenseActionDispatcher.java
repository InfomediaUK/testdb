package com.helmet.application.mgr;

import java.math.BigDecimal;
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
import com.helmet.bean.Expense;
import com.helmet.bean.Location;

public class ExpenseActionDispatcher extends MgrAction {

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
		
		Expense expense = (Expense)dynaForm.get("expense");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + expense.getLocationId(),
	    	                         actionForward.getRedirect());
			
		}		
		
     	// sort multiplier
     	String multiplierStr = (String)dynaForm.get("multiplierStr");
        expense.setMultiplier(new BigDecimal(multiplierStr));     	

        // sort vatRate
        String vatRateStr = (String)dynaForm.get("vatRateStr");
        expense.setVatRate(new BigDecimal(vatRateStr));     	

     	ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.insertExpense(expense, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("expense", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + expense.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Expense expense = (Expense)dynaForm.get("expense");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + expense.getLocationId(),
	    	                         actionForward.getRedirect());
		}		
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		expense = mgrService.getExpense(expense.getExpenseId());
		
		dynaForm.set("expense", expense); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		Expense expense = (Expense)dynaForm.get("expense");
	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
	
		expense = mgrService.getExpense(expense.getExpenseId());
	
		dynaForm.set("expense", expense); 
		
		dynaForm.set("multiplierStr", expense.getMultiplier().toString()); 
		dynaForm.set("vatRateStr", expense.getVatRate().toString()); 
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Expense expense = (Expense)dynaForm.get("expense");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?expense.expenseId=" + expense.getExpenseId(),
	    	                         actionForward.getRedirect());
		}		
		
     	// sort multiplier
     	String multiplierStr = (String)dynaForm.get("multiplierStr");
        expense.setMultiplier(new BigDecimal(multiplierStr));     	

        // sort vatRate
        String vatRateStr = (String)dynaForm.get("vatRateStr");
        expense.setVatRate(new BigDecimal(vatRateStr));     	

     	ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.updateExpense(expense, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("expense", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + expense.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward deleteProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Expense expense = (Expense)dynaForm.get("expense");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?expense.expenseId=" + expense.getExpenseId(),
	    	                         actionForward.getRedirect());
		}		

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowCount = mgrService.deleteExpense(expense.getExpenseId(), expense.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + expense.getLocationId(),
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
		
		List<Expense> list = mgrService.getExpensesForLocation(location.getLocationId());
		
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
		
		int rowcount = mgrService.updateExpenseDisplayOrder(order, zeroiseDisplayOrder, getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
}
