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
import com.helmet.bean.AgencyInvoiceUser;

public class AgencyInvoicesView extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	String[] selectedAgencyInvoices = (String[])dynaForm.get("selectedAgencyInvoices");

    	String agencyInvoiceIdStrs = "";
    	
    	for (String agencyInvoiceIdStr: selectedAgencyInvoices) {
    		if (!"".equals(agencyInvoiceIdStrs)) {
    			agencyInvoiceIdStrs = agencyInvoiceIdStrs + ",";
    		}
    		agencyInvoiceIdStrs = agencyInvoiceIdStrs + agencyInvoiceIdStr;
    	}
    	
     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
     	
     	List<AgencyInvoiceUser> agencyInvoiceUsers = mgrService.getAgencyInvoiceUsersForManagerAndAgencyInvoices(getManagerLoggedIn().getClientId(), getManagerLoggedIn().getManagerId(), agencyInvoiceIdStrs);
		
		// in ancestor class
		sortAgencyInvoiceTotals(agencyInvoiceUsers, dynaForm);

		dynaForm.set("agencyInvoiceIdStrs", agencyInvoiceIdStrs);
		dynaForm.set("list", agencyInvoiceUsers);
    	
    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");

    }

}