package com.helmet.application.admin;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.BookingDateUserApplicant;


public class ShiftSearchProcess extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		String fromDateStr = (String)dynaForm.get("fromDate");
		String toDateStr = (String)dynaForm.get("toDate");

		Date fromDate = null;
		Date toDate = null;
		// sort out the dates
		if (fromDateStr != null && !"".equals(fromDateStr) && toDateStr != null && !"".equals(toDateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			
			try {
				fromDate = new Date(sdf.parse(fromDateStr).getTime());
			}
			catch (ParseException e) {
				ActionMessages errors = new ActionMessages();
				MessageResources messageResources = getResources(request);
	            errors.add("shiftSearch", new ActionMessage("errors.invalid", messageResources.getMessage("label.fromDate")));
	            saveErrors(request, errors);
	    		return mapping.getInputForward();
			}
			try {
				toDate = new Date(sdf.parse(toDateStr).getTime());
			}
			catch (ParseException e) {
				ActionMessages errors = new ActionMessages();
				MessageResources messageResources = getResources(request);
	            errors.add("shiftSearch", new ActionMessage("errors.invalid", messageResources.getMessage("label.toDate")));
	            saveErrors(request, errors);
	    		return mapping.getInputForward();
			}
			System.out.println(">>>>> " + fromDate + " ----- " + toDate + " <<<<<");
		}		

		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		List<BookingDateUserApplicant> list = adminService.getBookingDateUserApplicantsForAdmin(fromDate, toDate);
		
		dynaForm.set("list", list);
    	
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");

    }

}
