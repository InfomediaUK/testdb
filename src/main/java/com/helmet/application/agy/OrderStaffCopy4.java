package com.helmet.application.agy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.PublicHoliday;


public class OrderStaffCopy4 extends AgyAction 
{

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) 
    {
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	Integer page = (Integer)dynaForm.get("page");
      Boolean complete = (Boolean)dynaForm.get("complete");
    	if (isCancelled(request)) 
      {
    		dynaForm.set("page", page - 1);
        return mapping.findForward("back");
    	}

    	ClientUser client = (ClientUser)dynaForm.get("client");
    	if (client.getClientId() == null || client.getClientId() == 0) 
      {
     		return mapping.findForward("orderStaffCopy");
    	}
    	
    	String reasonForRequestText = (String)dynaForm.get("reasonForRequestText");
//    	if (reasonForRequest.getReasonForRequestId() == null || reasonForRequest.getReasonForRequestId() == 0) 
//      {
//         	return mapping.findForward("orderStaff");
//    	}
    	LocationUser locationUser = (LocationUser)dynaForm.get("location");
    	if (locationUser.getLocationId() == null || locationUser.getLocationId() == 0) 
      {
         	return mapping.findForward("orderStaffCopy");
    	}
    	JobProfileUser jobProfileUser = (JobProfileUser)dynaForm.get("jobProfile");
    	if (jobProfileUser.getJobProfileId() == null || jobProfileUser.getJobProfileId() == 0) 
      {
         	return mapping.findForward("orderStaffCopy");
    	}

		  AgyService agyService = ServiceFactory.getInstance().getAgyService();

		  // determine whether the user is going forwards (next) or backwards (back)
    	boolean userPressedNext = page == 3;

     	if (userPressedNext) 
      {
     		// only check if jobProfile has been changed if going forward
	    	Integer origJobProfileId = (Integer)dynaForm.get("origJobProfileId");
	    	if (origJobProfileId == null || origJobProfileId == 0) 
        {
	    		// first time thru
	    		dynaForm.set("autoFill", jobProfileUser.getAutoFill());
	    	}
     	}
    	
//     	dynaForm.set("jobProfile", jobProfileUser);
		  List<PublicHoliday> list = agyService.getPublicHolidaysForClient(client.getClientId());
     	dynaForm.set("list", list);
      if (complete)
      {
        return mapping.findForward("complete");
      }
      else
      {
        return mapping.findForward("success");
      }
    }

}
