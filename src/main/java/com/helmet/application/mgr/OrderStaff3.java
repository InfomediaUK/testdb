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
import com.helmet.bean.BookingDate;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.DressCode;
import com.helmet.bean.Expense;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationJobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.ReasonForRequest;


public class OrderStaff3 extends MgrAction {

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

    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
    	
		locationUser = mgrService.getLocationUser(locationUser.getLocationId());
     	

    	// determine whether the user is going forwards (next) or backwards (back)
    	boolean userPressedNext = page == 2;

     	if (userPressedNext) {
     		// only check if location has been changed if going forward
	    	Integer origLocationId = (Integer)dynaForm.get("origLocationId");
	    	if (origLocationId == null || origLocationId == 0) {
	    		// first time thru
	            // specifics will need to change when on/off and defaulted for each location
	            dynaForm.set("singleCandidate", locationUser.getSingleCandidateDefault());
	            dynaForm.set("cvRequired", locationUser.getCvRequiredDefault());
	            dynaForm.set("interviewRequired", locationUser.getInterviewRequiredDefault());
	            dynaForm.set("canProvideAccommodation", locationUser.getCanProvideAccommodationDefault());
	            dynaForm.set("carRequired", locationUser.getCarRequiredDefault());
	            dynaForm.set("comments", locationUser.getCommentsDefault());
	            
            }
  	    	if (origLocationId != null 
  	    	 && origLocationId != 0
	    	 && !origLocationId.equals(locationUser.getLocationId())) {
	            logger.debug("Location has been changed so clear stuff ...");
	            // clear job profile
	            dynaForm.set("jobProfile", new JobProfileUser());
	            // clear grades
	            dynaForm.set("clientAgencyJobProfileGradeUserArray", new ClientAgencyJobProfileGradeUser[0]);
	            // clear shifts
	            BookingDate[] bookingDates = (BookingDate[])dynaForm.get("bookingDates");
	            for (BookingDate bookingDate: bookingDates) {
	            	bookingDate.setShiftId(null);
//	            	bookingDate.setShift(null);
	            }
	            // specifics will need to change when on/off and defaulted for each location
	            dynaForm.set("singleCandidate", locationUser.getSingleCandidateDefault());
	            dynaForm.set("cvRequired", locationUser.getCvRequiredDefault());
	            dynaForm.set("interviewRequired", locationUser.getInterviewRequiredDefault());
	            dynaForm.set("canProvideAccommodation", locationUser.getCanProvideAccommodationDefault());
	            dynaForm.set("carRequired", locationUser.getCarRequiredDefault());
	            dynaForm.set("comments", locationUser.getCommentsDefault());
	            dynaForm.set("expensesText", null);
	            //
	            // dress code
	            dynaForm.set("dressCode", new DressCode());
	            //
	            // expenses
	    		String[] selectedExpenses = new String[1];
	    		selectedExpenses[0] = "0"; // the DUMMY expense! 
	            dynaForm.set("selectedExpenses", selectedExpenses);
	            dynaForm.set("expenseArray", new Expense[0]);
	    	}
     	}
		
		dynaForm.set("location", locationUser);
		List<LocationJobProfileUser> list = mgrService.getLocationJobProfileUsersForManagerForLocation(getManagerLoggedIn().getManagerId(), locationUser.getLocationId());
     	dynaForm.set("list", list);

     	//
     	JobProfileUser jobProfileUser = (JobProfileUser)dynaForm.get("jobProfile");
       	if (jobProfileUser.getJobProfileId() != null && jobProfileUser.getJobProfileId() != 0) {
       		dynaForm.set("origJobProfileId", jobProfileUser.getJobProfileId());
       	}
     	
     	return mapping.findForward("success");
    }

}
