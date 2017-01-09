package com.helmet.application.mgr;

import java.util.List;

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
import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.LocationJobProfileUser;


public class BudgetTransactionList extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	LocationJobProfileUser locationJobProfile = (LocationJobProfileUser)dynaForm.get("locationJobProfile");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
        locationJobProfile = mgrService.getLocationJobProfileUserForManager(getManagerLoggedIn().getManagerId(), locationJobProfile.getLocationJobProfileId());
        
        List<BudgetTransaction> list = mgrService.getBudgetTransactionsForLocationJobProfile(locationJobProfile.getLocationJobProfileId());
     	
     	dynaForm.set("locationJobProfile", locationJobProfile);
     	dynaForm.set("list", list);

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
