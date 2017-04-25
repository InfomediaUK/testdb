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
import com.helmet.bean.ApplicantTrainingCourse;
import com.helmet.bean.TrainingCompanyCourse;
import com.helmet.bean.TrainingCourse;

public class ApplicantTrainingCourseEditProcess extends ApplicantTrainingCourseCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    ApplicantTrainingCourse applicantTrainingCourse = (ApplicantTrainingCourse)dynaForm.get("applicantTrainingCourse");
    FormFile documentationFormFile = (FormFile)dynaForm.get("documentationFormFile");
    String startDateStr = (String)dynaForm.get("startDateStr");
    String endDateStr = (String)dynaForm.get("endDateStr");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    if (StringUtils.isNotEmpty(startDateStr))
    {
      Date startDate =  convertDate(startDateStr, sdf, errors, messageResources, "label.startDate");
      applicantTrainingCourse.setStartDate(startDate);
    }
    if (StringUtils.isNotEmpty(endDateStr))
    {
      Date endDate =  convertDate(endDateStr, sdf, errors, messageResources, "label.endDate");
      applicantTrainingCourse.setEndDate(endDate);
    }
    validateDates(errors, applicantTrainingCourse);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    if (StringUtils.isNotEmpty(documentationFormFile.getFileName()))
    {
      TrainingCompanyCourse trainingCompanyCourse = agyService.getTrainingCompanyCourse(applicantTrainingCourse.getTrainingCompanyCourseId());
      TrainingCourse trainingCourse = agyService.getTrainingCourse(trainingCompanyCourse.getTrainingCourseId());
      applicantTrainingCourse.setDocumentationFileName(getDocumentationFileName(applicantTrainingCourse, trainingCourse));
    }
    int rowCount = agyService.updateApplicantTrainingCourse(applicantTrainingCourse, getConsultantLoggedIn().getConsultantId());

    if (StringUtils.isNotEmpty(documentationFormFile.getFileName()))
    {
      // Now upload the ApplicantTrainingCourse documentation file.
      String filePath = null;
      try
      {
        filePath = uploadApplicantTrainingCourseFile(applicantTrainingCourse, documentationFormFile);
      }
      catch (Exception e)
      {
        errors.add("applicantTrainingCourse", new ActionMessage("error.applicantTrainingCourseFile.uploadFailed", filePath, e.getMessage()));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
    }
    
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicantTrainingCourse.getApplicantId(), true);
  }

}
