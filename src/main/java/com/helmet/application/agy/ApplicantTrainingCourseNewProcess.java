package com.helmet.application.agy;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Applicant;
import com.helmet.bean.ApplicantTrainingCourse;
import com.helmet.bean.TrainingCompanyCourse;
import com.helmet.bean.TrainingCourse;

public class ApplicantTrainingCourseNewProcess extends ApplicantTrainingCourseCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    ApplicantTrainingCourse newApplicantTrainingCourse = (ApplicantTrainingCourse)dynaForm.get("applicantTrainingCourse");
    Applicant applicant = (Applicant)dynaForm.get("applicant");
    String trainingCompanyIdStr = (String)dynaForm.get("trainingCompanyId");
    FormFile documentationFormFile = (FormFile)dynaForm.get("documentationFormFile");
    Integer trainingCompanyId = StringUtils.isEmpty(trainingCompanyIdStr) ? null : Integer.parseInt(trainingCompanyIdStr);
    String trainingCourseIdStr = (String)dynaForm.get("trainingCourseId");
    Integer trainingCourseId = StringUtils.isEmpty(trainingCourseIdStr) ? null : Integer.parseInt(trainingCourseIdStr);
    String startDateStr = (String)dynaForm.get("startDateStr");
    String endDateStr = (String)dynaForm.get("endDateStr");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    if (StringUtils.isNotEmpty(startDateStr))
    {
      Date startDate =  convertDate(startDateStr, sdf, errors, messageResources, "label.startDate");
      newApplicantTrainingCourse.setStartDate(startDate);
    }
    if (StringUtils.isNotEmpty(endDateStr))
    {
      Date endDate =  convertDate(endDateStr, sdf, errors, messageResources, "label.endDate");
      newApplicantTrainingCourse.setEndDate(endDate);
    }
    validateDates(errors, newApplicantTrainingCourse);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    TrainingCourse trainingCourse = agyService.getTrainingCourse(trainingCourseId);
    TrainingCompanyCourse trainingCompanyCourse = agyService.getTrainingCompanyCourseForTrainingCompanyAndTrainingCourse(trainingCompanyId, trainingCourseId);
    newApplicantTrainingCourse.setTrainingCompanyCourseId(trainingCompanyCourse.getTrainingCompanyCourseId());
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    newApplicantTrainingCourse.setApplicantTrainingCourseId(agyService.getApplicantTrainingCourseId());
    if (StringUtils.isNotEmpty(documentationFormFile.getFileName()))
    {
      newApplicantTrainingCourse.setDocumentationFileName(getDocumentationFileName(newApplicantTrainingCourse, trainingCourse));
    }
    int rowCount = agyService.insertApplicantTrainingCourse(newApplicantTrainingCourse, getConsultantLoggedIn().getConsultantId());

    if (StringUtils.isNotEmpty(documentationFormFile.getFileName()))
    {
      // Now upload the ApplicantTrainingCourse documentation file.
      String filePath = null;
      try
      {
        filePath = uploadApplicantTrainingCourseFile(newApplicantTrainingCourse, documentationFormFile);
      }
      catch (Exception e)
      {
        errors.add("nhsBackingReport", new ActionMessage("error.nhsBackingReportFile.uploadFailed", filePath, e.getMessage()));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
    }
    
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + newApplicantTrainingCourse.getApplicantId(), true);
  }

}
