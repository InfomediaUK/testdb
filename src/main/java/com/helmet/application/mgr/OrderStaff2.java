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
import com.helmet.bean.LocationUser;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Shift;


public class OrderStaff2 extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	if (isCancelled(request)) {
    		dynaForm.set("page", (Integer)dynaForm.get("page") - 1);
         	return mapping.findForward("back");
    	}

    	ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
    	if (reasonForRequest.getReasonForRequestId() == null || reasonForRequest.getReasonForRequestId() == 0) {
     		return mapping.findForward("orderStaff");
    	}

    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		reasonForRequest = mgrService.getReasonForRequest(reasonForRequest.getReasonForRequestId());
     	dynaForm.set("reasonForRequest", reasonForRequest);
		List<LocationUser> list = mgrService.getLocationUsersForManager(getManagerLoggedIn().getManagerId());
     	dynaForm.set("list", list);
     	//
     	LocationUser locationUser = (LocationUser)dynaForm.get("location");
       	if (locationUser.getLocationId() != null && locationUser.getLocationId() != 0) {
       		dynaForm.set("origLocationId", locationUser.getLocationId());
       	}
    	
     	return mapping.findForward("success");
    }

}
