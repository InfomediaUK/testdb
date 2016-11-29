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
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.PublicHoliday;


public class BookingExtend1 extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	Integer page = (Integer)dynaForm.get("page");
    	if (isCancelled(request)) {
    		dynaForm.set("page", page - 1);
         	return mapping.findForward("back");
    	}

    	BookingGradeAgyEntity bookingGrade = (BookingGradeAgyEntity)dynaForm.get("bookingGrade");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
    	
		List<PublicHoliday> list = agyService.getPublicHolidaysForClient(bookingGrade.getClientId());
     	dynaForm.set("list", list);

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
