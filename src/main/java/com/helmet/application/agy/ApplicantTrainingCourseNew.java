package com.helmet.application.agy;

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
import com.helmet.bean.Applicant;
import com.helmet.bean.ApplicantTrainingCourse;

public class ApplicantTrainingCourseNew extends AgyAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    Applicant applicant = (Applicant) dynaForm.get("applicant");
    ApplicantTrainingCourse applicantTrainingCourse = new ApplicantTrainingCourse();
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    applicant = agyService.getApplicant(applicant.getApplicantId());
    if (applicant == null) 
    { 
      return mapping.findForward("illegalaccess"); 
    }
    applicantTrainingCourse.setApplicantId(applicant.getApplicantId());
    dynaForm.set("applicant", applicant);
    dynaForm.set("applicantTrainingCourse", applicantTrainingCourse);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
