package com.helmet.application.mgr;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.LocationJobProfileUser;


public class BudgetTransactionNewProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	LocationJobProfileUser locationJobProfile = (LocationJobProfileUser)dynaForm.get("locationJobProfile");
    	BudgetTransaction budgetTransaction = (BudgetTransaction)dynaForm.get("budgetTransaction");
    	String valueStr = (String)dynaForm.get("valueStr");
    	String vatValueStr = (String)dynaForm.get("vatValueStr");
    	String expenseValueStr = (String)dynaForm.get("expenseValueStr");
    	String nonPayValueStr = (String)dynaForm.get("nonPayValueStr");

    	String tab = (String)dynaForm.get("tab");
    	
        BigDecimal value = new BigDecimal(valueStr);
        BigDecimal vatValue = new BigDecimal(vatValueStr);
        BigDecimal expenseValue = new BigDecimal(expenseValueStr);
        BigDecimal nonPayValue = new BigDecimal(nonPayValueStr);
        
        BigDecimal bdZero = new BigDecimal(0);
        
        if (value.compareTo(bdZero) == 0 && vatValue.compareTo(bdZero) == 0 && expenseValue.compareTo(bdZero) == 0 && nonPayValue.compareTo(bdZero) == 0) {
    		ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("budgetTransactionNew", new ActionMessage("errors.nonZero", messageResources.getMessage("label.value")));
            saveErrors(request, errors);
       		return mapping.getInputForward();
        }
        
        budgetTransaction.setLocationJobProfileId(locationJobProfile.getLocationJobProfileId());
        budgetTransaction.setValue(value);
        budgetTransaction.setVatValue(vatValue);
        budgetTransaction.setExpenseValue(expenseValue);
        budgetTransaction.setNonPayValue(nonPayValue);

     	int rc = doAfterValidation(budgetTransaction, dynaForm, mapping);
      	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?tab=" + tab + "&locationJobProfile.locationJobProfileId=" + locationJobProfile.getLocationJobProfileId(),
    	                         actionForward.getRedirect());
     	
    }

    protected int doAfterValidation(BudgetTransaction budgetTransaction, DynaValidatorForm dynaForm, ActionMapping mapping) {
    	// overidden in descendant class
    	return 0;
    }

}
