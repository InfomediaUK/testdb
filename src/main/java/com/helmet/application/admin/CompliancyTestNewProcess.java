package com.helmet.application.admin;

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
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.bean.CompliancyTest;


public class CompliancyTestNewProcess extends CompliancyTestCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    CompliancyTest compliancyTest = (CompliancyTest) dynaForm.get("compliancyTest");
    if (!propertyIsValid(compliancyTest, errors))
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    try
    {
      int rowCount = adminService.insertCompliancyTest(compliancyTest, getAdministratorLoggedIn().getAdministratorId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("compliancyTest", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
