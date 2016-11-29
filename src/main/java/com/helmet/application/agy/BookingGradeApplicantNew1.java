package com.helmet.application.agy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.BookingGradeApplicantUser;


public class BookingGradeApplicantNew1 extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) 
    {
    	
      HttpSession session = request.getSession();
      String applicantIndexLetter = (String)session.getAttribute("applicantIndexLetter");
      applicantIndexLetter = applicantIndexLetter == null ? "A" : applicantIndexLetter;
      String indexLetter = request.getParameter("indexLetter") == null ? applicantIndexLetter : request.getParameter("indexLetter");
      session.setAttribute("applicantIndexLetter", indexLetter);
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	if (isCancelled(request)) {
    		dynaForm.set("page", (Integer)dynaForm.get("page") - 1);
         	return mapping.findForward("back");
    	}

    	BookingGradeAgy bookingGradeAgy = (BookingGradeAgy)dynaForm.get("bookingGrade");
    	
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	bookingGradeAgy = agyService.getBookingGradeAgy(bookingGradeAgy.getBookingGradeId(), getConsultantLoggedIn().getAgencyId());
    	
    	BookingGradeApplicantUser bookingGradeApplicant = (BookingGradeApplicantUser)dynaForm.get("bookingGradeApplicant");

		List<Applicant> list = null;
		
		if (bookingGradeApplicant == null || bookingGradeApplicant.getBookingGradeApplicantId() == null) 
    {
			
			list = agyService.getApplicantsForAgencyAndNotForBookingGradeInLastNameGroup(getConsultantLoggedIn().getAgencyId(), bookingGradeAgy.getBookingGradeId(), indexLetter);
		
		}
		else 
    {
			
			list = agyService.getApplicantsForAgencyInLastNameGroup(getConsultantLoggedIn().getAgencyId());

		}
     	
        dynaForm.set("list", list);

    	logger.exit("Out going !!!");

     	return mapping.findForward("success");
    }

}
