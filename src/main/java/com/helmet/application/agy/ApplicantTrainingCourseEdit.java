package com.helmet.application.agy;

import java.text.SimpleDateFormat;
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
import com.helmet.bean.Applicant;
import com.helmet.bean.ApplicantTrainingCourse;
import com.helmet.bean.TrainingCompanyCourse;
import com.helmet.bean.TrainingCompanyCourseUser;

public class ApplicantTrainingCourseEdit extends AgyAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    ApplicantTrainingCourse applicantTrainingCourse = (ApplicantTrainingCourse)dynaForm.get("applicantTrainingCourse");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    applicantTrainingCourse = agyService.getApplicantTrainingCourse(applicantTrainingCourse.getApplicantTrainingCourseId());
    Applicant applicant = agyService.getApplicant(applicantTrainingCourse.getApplicantId());
    if (applicant == null) 
    { 
      return mapping.findForward("illegalaccess"); 
    }
    TrainingCompanyCourse trainingCompanyCourse = agyService.getTrainingCompanyCourse(applicantTrainingCourse.getTrainingCompanyCourseId());
    List<TrainingCompanyCourseUser> trainingCompanyCourseList = agyService.getTrainingCompanyCourseUsersForTrainingCourse(trainingCompanyCourse.getTrainingCourseId(), true);
    dynaForm.set("applicant", applicant);
    dynaForm.set("applicantTrainingCourse", applicantTrainingCourse);
    dynaForm.set("trainingCompanyCourseList", trainingCompanyCourseList);
    dynaForm.set("startDateStr", sdf.format(applicantTrainingCourse.getStartDate()));
    dynaForm.set("endDateStr", sdf.format(applicantTrainingCourse.getEndDate()));
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
