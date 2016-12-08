package com.helmet.application.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AppService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.Applicant;


public class ToggleShowPageHelp extends AppAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
		Applicant applicant = getApplicantLoggedIn();

		AppService appService = ServiceFactory.getInstance().getAppService();
		
        // Get the lastest version of the consultant - noOfChanges the main reason behind this
		applicant = appService.getApplicant(applicant.getApplicantId());
		applicant.getUser().setShowPageHelp(!applicant.getUser().getShowPageHelp());
		
		int rc = appService.updateApplicant(applicant, applicant.getApplicantId());

		AppUtilities.setCurrentApplicant(request, applicant);
		
		logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
