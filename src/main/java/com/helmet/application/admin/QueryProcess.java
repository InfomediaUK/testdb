package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.reporting.ResultSetData;
import com.helmet.reporting.XMLGenerator;


public class QueryProcess extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		String id = request.getParameter("id");

		XMLGenerator xmlGenerator = XMLGenerator.getInstance();

		String paramNames[] = xmlGenerator.getParamNames(id);
		int paramCount = paramNames.length;
		String paramValues[] = new String[paramCount];

		for (int i = 0; i < paramCount; i++) {
			paramValues[i] = request.getParameter(paramNames[i]);
		}

		ResultSetData resultSetData = xmlGenerator.getResultSetData(id, paramValues);

		dynaForm.set("resultSetData", resultSetData);
    	
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");

    }

}
