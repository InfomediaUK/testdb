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
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.TrainingCompanyCourseUser;


public class TrainingCompanyCourseEditProcess extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    TrainingCompanyCourseUser trainingCompanyCourseUser = (TrainingCompanyCourseUser)dynaForm.get("trainingCompanyCourseUser");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    try
    {
      int rowCount = adminService.updateTrainingCompanyCourse(trainingCompanyCourseUser, getAdministratorLoggedIn().getAdministratorId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("trainingCompanyCourse", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?trainingCompanyCourseUser.trainingCompanyCourseId=" + trainingCompanyCourseUser.getTrainingCompanyCourseId(), actionForward.getRedirect());
  }

}
