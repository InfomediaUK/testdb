package com.helmet.application.mgr;

import java.math.BigDecimal;
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
import com.helmet.bean.LocationJobProfileUser;


public class LocationList extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
//		List<LocationManagerJobProfileUser> list = mgrService.getLocationManagerJobProfileUsersForManager(getManagerLoggedIn().getManagerId());
		List<LocationJobProfileUser> list = mgrService.getLocationJobProfileUsersForManager(getManagerLoggedIn().getManagerId());
     	
		BigDecimal totalBudget = new BigDecimal(0);
		BigDecimal totalVatBudget = new BigDecimal(0);
		BigDecimal totalExpenseBudget = new BigDecimal(0);
		BigDecimal totalNonPayBudget = new BigDecimal(0);
     	for (LocationJobProfileUser locationJobProfile: list) {
     		totalBudget = totalBudget.add(locationJobProfile.getBudget());
     		totalVatBudget = totalVatBudget.add(locationJobProfile.getVatBudget());
     		totalExpenseBudget = totalExpenseBudget.add(locationJobProfile.getExpenseBudget());
     		totalNonPayBudget = totalNonPayBudget.add(locationJobProfile.getNonPayBudget());
     	}
		
		dynaForm.set("totalBudget", totalBudget);
		dynaForm.set("totalVatBudget", totalVatBudget);
		dynaForm.set("totalExpenseBudget", totalExpenseBudget);
		dynaForm.set("totalNonPayBudget", totalNonPayBudget);
		dynaForm.set("list", list);

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
