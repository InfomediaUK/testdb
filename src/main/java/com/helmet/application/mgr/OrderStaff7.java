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
import com.helmet.application.Utilities;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.DressCode;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Uplift;


public class OrderStaff7 extends MgrAction {

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
    	
    	ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
    	if (reasonForRequest.getReasonForRequestId() == null || reasonForRequest.getReasonForRequestId() == 0) {
         	return mapping.findForward("orderStaff");
    	}
    	LocationUser locationUser = (LocationUser)dynaForm.get("location");
    	if (locationUser.getLocationId() == null || locationUser.getLocationId() == 0) {
         	return mapping.findForward("orderStaff");
    	}
    	JobProfileUser jobProfileUser = (JobProfileUser)dynaForm.get("jobProfile");
    	if (jobProfileUser.getJobProfileId() == null || jobProfileUser.getJobProfileId() == 0) {
         	return mapping.findForward("orderStaff");
    	}
    	String dates = (String)dynaForm.get("dates");
    	if (dates == null || "".equals(dates)) {
         	return mapping.findForward("orderStaff");
    	}
    	BookingDate[] bookingDates = (BookingDate[])dynaForm.get("bookingDates");
    	if (bookingDates == null || bookingDates.length == 0) {
         	return mapping.findForward("orderStaff");
    	}
    	ClientAgencyJobProfileGradeUser[] clientAgencyJobProfileGradeUserArray = (ClientAgencyJobProfileGradeUser[])dynaForm.get("clientAgencyJobProfileGradeUserArray");
    	if (clientAgencyJobProfileGradeUserArray.length == 0) {
         	return mapping.findForward("orderStaff");
        }
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
        List<PublicHoliday> publicHolidays = mgrService.getPublicHolidaysForClient(getManagerLoggedIn().getClientId());
        List<Uplift> uplifts = mgrService.getUpliftsForClient(getManagerLoggedIn().getClientId());

	    // CALCULATE 'UPLIFTED' RATE

        BigDecimal hourlyRate = (BigDecimal)dynaForm.get("hourlyRate");
 
        if (hourlyRate == null) {
         	return mapping.findForward("orderStaff");
        }

        BigDecimal rrp = Utilities.calculateIt(bookingDates, hourlyRate, publicHolidays, uplifts, true);
     	dynaForm.set("rrp", rrp);

    	for (int i = 0; i < clientAgencyJobProfileGradeUserArray.length; i++) {
    		ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser = clientAgencyJobProfileGradeUserArray[i];
    		clientAgencyJobProfileGradeUser = mgrService.getClientAgencyJobProfileGradeUser(clientAgencyJobProfileGradeUser.getClientAgencyJobProfileGradeId());

    		BigDecimal value = Utilities.calculateIt(bookingDates, clientAgencyJobProfileGradeUser.getRate(), publicHolidays, uplifts);
        	
    		clientAgencyJobProfileGradeUser.setValue(value);
        	clientAgencyJobProfileGradeUserArray[i] = clientAgencyJobProfileGradeUser;
    	}

		List<DressCode> list = mgrService.getDressCodesForLocation(locationUser.getLocationId());
     	dynaForm.set("list", list);
     	Integer noOfDressCodes = list.size();
     	if (noOfDressCodes == 1) {
     		dynaForm.set("dressCode", list.get(0)); 
     	}
     	dynaForm.set("noOfDressCodes", noOfDressCodes);

     	if (noOfDressCodes < 2
     	 && !locationUser.getSingleCandidateShow()
     	 && !locationUser.getCvRequiredShow()
     	 && !locationUser.getInterviewRequiredShow()
     	 && !locationUser.getCanProvideAccommodationShow()
     	 && !locationUser.getCarRequiredShow()){
     		// nothing to show so skip the step
			//
			// determine whether the user is going forwards (next) or backwards (back)
	    	boolean userPressedNext = page == 6;
            // miss out this step and based on the mapping config go to the next
	     	if (userPressedNext) {
	    		dynaForm.set("page", page + 1);
	     		return mapping.findForward("noSpecificsSuccess");
		    }
	     	else {
	    		dynaForm.set("page", page - 1);
	         	return mapping.findForward("noSpecificsBack");
	     	}
     	}
     	return mapping.findForward("success");
    }

}
