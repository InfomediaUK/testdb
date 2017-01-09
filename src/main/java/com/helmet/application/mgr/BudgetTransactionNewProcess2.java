package com.helmet.application.mgr;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.BudgetTransaction;


public class BudgetTransactionNewProcess2 extends BudgetTransactionNewProcess {

    protected int doAfterValidation(BudgetTransaction budgetTransaction, DynaValidatorForm dynaForm, ActionMapping mapping) {
        
    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		//
        int rc =  mgrService.insertBudgetTransaction(budgetTransaction, getManagerLoggedIn().getManagerId());
        //
        dynaForm.initialize(mapping);
        //
        return rc;
    	
    }

}
