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
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.DressCode;
import com.helmet.bean.Expense;
import com.helmet.bean.LocationUser;


public class OrderStaff8 extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	Integer page = (Integer)dynaForm.get("page");
    	if (isCancelled(request)) {
    		dynaForm.set("page", page - 1);
         	return mapping.findForward("back");
    	}

    	// checks !!!

		ActionMessages errors = new ActionMessages();
		MessageResources messageResources = getResources(request);

		Boolean cvRequired = (Boolean)dynaForm.get("cvRequired");
    	Boolean interviewRequired = (Boolean)dynaForm.get("interviewRequired");
    	Boolean autoFill = (Boolean)dynaForm.get("autoFill");
    	
    	if ((cvRequired || interviewRequired) && autoFill) {
            errors.add("orderStaff", new ActionMessage("errors.invalid.autoFill"));
    	}
    	
    	DressCode dressCode = (DressCode)dynaForm.get("dressCode");
    	Integer noOfDressCodes = (Integer)dynaForm.get("noOfDressCodes");

    	if (noOfDressCodes > 0 && dressCode.getDressCodeId() == null) {
            errors.add("orderStaff", new ActionMessage("errors.required", messageResources.getMessage("label.dressCode")));
    	}

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
       		return mapping.getInputForward();
        }

    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();

    	if (dressCode.getDressCodeId() != null) {
        	dressCode = mgrService.getDressCode(dressCode.getDressCodeId());
    	}

     	dynaForm.set("dressCode", dressCode);
    	
    	LocationUser locationUser = (LocationUser)dynaForm.get("location");

    	List<Expense> list = mgrService.getExpensesForLocation(locationUser.getLocationId());
     	
		if (list.size() == 0) {
	    	// no expenses can be claimed so skip this step
			//
			// determine whether the user is going forwards (next) or backwards (back)
	    	boolean userPressedNext = page == 7;
            // miss out this step and based on the mapping config go to the next
	     	if (userPressedNext) {
	    		dynaForm.set("page", page + 1);
	     		return mapping.findForward("noExpenseSuccess");
		    }
	     	else {
	    		dynaForm.set("page", page - 1);
	         	return mapping.findForward("noExpenseBack");
	     	}
		}
				
		dynaForm.set("list", list);

     	return mapping.findForward("success");
    }

}
