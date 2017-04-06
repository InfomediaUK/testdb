package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.TrainingCompanyCourseUser;


public class TrainingCompanyCourseDeleteProcess extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    TrainingCompanyCourseUser trainingCompanyCourseUser = (TrainingCompanyCourseUser)dynaForm.get("trainingCompanyCourseUser");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    int rowCount = adminService.deleteTrainingCompanyCourse(trainingCompanyCourseUser.getTrainingCompanyCourseId(), trainingCompanyCourseUser.getNoOfChanges(), getAdministratorLoggedIn().getAdministratorId());
    // Now get the 'deleted' TrainingCompanyCourseUser so that we can forward to its owning TrainingCompany... 
    trainingCompanyCourseUser = adminService.getTrainingCompanyCourseUser(trainingCompanyCourseUser.getTrainingCompanyCourseId());
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?trainingCompany.trainingCompanyId=" + trainingCompanyCourseUser.getTrainingCompanyId(), actionForward.getRedirect());
  }

}
