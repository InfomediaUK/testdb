package com.helmet.application.agy;

import java.sql.Date;

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

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Agency;
import com.helmet.bean.ApplicantEntity;

public class ApplicantChecklist extends AgencyWorkerChecklist
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.debug("In coming !!! - ApplicantChecklist");

    boolean checklistCreated = false;
    ActionMessages errors = new ActionMessages();
    ApplicantEntity applicant = (ApplicantEntity) dynaForm.get("applicant");
//    Integer applicantId  = (Integer)dynaForm.get("applicantId");
    Integer weekToShow  = (Integer)dynaForm.get("weekToShow");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    applicant = agyService.getApplicantEntity(applicant.getApplicantId());
    Agency agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());

    if (applicant == null) { return mapping.findForward("illegalaccess"); }

    Date nowDate = new Date(new java.util.Date().getTime());
    try
    {
      String fileName = applicant.getChecklistFilePath();
      MessageResources messageResources = getResources(request);
      logger.debug("About to create ApplicantChecklist: " + fileName);
      checklistCreated = doIt(messageResources, agency, applicant, null, null, null, null, null, nowDate, fileName);
      if (checklistCreated)
      {
        // Checklist created. Set the Checklist Created Time to NOW.
        logger.debug("ApplicantChecklist created");
        agyService.updateApplicantChecklistCreatedTime(applicant.getApplicantId(), applicant.getNoOfChanges(), getConsultantLoggedIn().getConsultantId(), nowDate);
      }
      else
      {
        // Error.
        logger.debug("ERROR creating ApplicantChecklist");
        errors.add("checklist", new ActionMessage("error.checklist.notCreated"));
        saveErrors(request, errors);
      }
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    dynaForm.set("applicant", applicant);

    logger.debug("Out going !!! - ApplicantChecklist");

    if (errors.isEmpty())
    {
      ActionForward actionForward = mapping.findForward("success");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicant.getApplicantId() + "&weekToShow=" + weekToShow, actionForward.getRedirect());
    }
    else
    {
      return mapping.findForward("error");
    }
  }
  
}
