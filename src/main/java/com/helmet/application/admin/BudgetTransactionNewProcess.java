package com.helmet.application.admin;

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

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.ClientUser;


public class BudgetTransactionNewProcess extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BudgetTransaction budgetTransaction = (BudgetTransaction)dynaForm.get("budgetTransaction");
    	
    	if (budgetTransaction.getValue().compareTo(new BigDecimal(0)) == 0) {
    		ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("budgetTransaction", new ActionMessage("errors.required", messageResources.getMessage("label.value")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
    	}
    	
    	ClientUser client = (ClientUser)dynaForm.get("client");

     	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
    	int rowCount = adminService.insertBudgetTransaction(budgetTransaction, getAdministratorLoggedIn().getAdministratorId());
		  
    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?client.clientId=" + client.getClientId(),
    	                         actionForward.getRedirect());
    }

}
