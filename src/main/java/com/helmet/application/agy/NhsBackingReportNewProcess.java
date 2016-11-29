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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportNewProcess extends NhsBackingReportCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    NhsBackingReportUser newNhsBackingReportUser = (NhsBackingReportUser)dynaForm.get("nhsBackingReportUser");
    String startDateStr = (String)dynaForm.get("startDateStr");
    String endDateStr = (String)dynaForm.get("endDateStr");
    String completeDateStr = (String)dynaForm.get("completeDateStr");
    FormFile nhsBackingReportDocumentationFormFile = (FormFile)dynaForm.get("nhsBackingReportDocumentationFormFile");
    if (StringUtils.isNotEmpty(nhsBackingReportDocumentationFormFile.getFileName()))
    {
      newNhsBackingReportUser.setDocumentationFileName(getDocumentationFileName(newNhsBackingReportUser));
    }
    FormFile nhsBackingReportRejectedDocumentationFormFile = (FormFile)dynaForm.get("nhsBackingReportRejectedDocumentationFormFile");
    if (StringUtils.isNotEmpty(nhsBackingReportRejectedDocumentationFormFile.getFileName()))
    {
      newNhsBackingReportUser.setRejectedDocumentationFileName(getRejectedDocumentationFileName(newNhsBackingReportUser));
    }
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    if (StringUtils.isEmpty(startDateStr))
    {
      newNhsBackingReportUser.setStartDate(null);
    }
    else
    {
      Date startDate =  convertDate(startDateStr, sdf, errors, messageResources, "label.startDate");
      newNhsBackingReportUser.setStartDate(startDate);
    }
    if (StringUtils.isEmpty(endDateStr))
    {
      newNhsBackingReportUser.setEndDate(null);
    }
    else
    {
      Date endDate =  convertDate(endDateStr, sdf, errors, messageResources, "label.endDate");
      newNhsBackingReportUser.setEndDate(endDate);
    }
    if (StringUtils.isEmpty(completeDateStr))
    {
      newNhsBackingReportUser.setCompleteDate(null);
    }
    else
    {
      Date completeDate =  convertDate(completeDateStr, sdf, errors, messageResources, "label.completeDate");
      newNhsBackingReportUser.setCompleteDate(completeDate);
    }
    validateName(errors, newNhsBackingReportUser);
    validateDates(errors, newNhsBackingReportUser);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    NhsBackingReport duplicateNhsBackingReport = agyService.getNhsBackingReportForName(newNhsBackingReportUser.getName(), true);
    if (duplicateNhsBackingReport != null)
    {
      errors.add("nhsBackingReport", new ActionMessage("errors.duplicate", newNhsBackingReportUser.getName()));
    }
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      repopulateClientAgencyUserList(agyService, dynaForm);
      return mapping.getInputForward();
    }

    newNhsBackingReportUser.setAgencyId(getConsultantLoggedIn().getAgencyId());
    NhsBackingReport inactiveNhsBackingReport = agyService.getInactiveNhsBackingReportForName(newNhsBackingReportUser.getName());
    int rowCount = 0;
    if (inactiveNhsBackingReport != null)
    {
      // An NHS Backing Report with the same Name has been deleted in the past.
      newNhsBackingReportUser.setNhsBackingReportId(inactiveNhsBackingReport.getNhsBackingReportId());
      newNhsBackingReportUser.setActive(true);
      newNhsBackingReportUser.setNoOfChanges(inactiveNhsBackingReport.getNoOfChanges());
      rowCount = agyService.reactivateNhsBackingReport(newNhsBackingReportUser, getConsultantLoggedIn().getConsultantId());
    }
    else
    {
      rowCount = agyService.insertNhsBackingReport(newNhsBackingReportUser, getConsultantLoggedIn().getConsultantId());
    }

    if (StringUtils.isNotEmpty(nhsBackingReportDocumentationFormFile.getFileName()))
    {
      // Now upload the NHS Backing Report documentation file.
      String filePath = null;
      try
      {
        filePath = uploadNhsBackingReportFile(newNhsBackingReportUser.getDocumentationFileName(), nhsBackingReportDocumentationFormFile);
      }
      catch (Exception e)
      {
        errors.add("nhsBackingReport", new ActionMessage("error.nhsBackingReportFile.uploadFailed", filePath, e.getMessage()));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
    }
    if (StringUtils.isNotEmpty(nhsBackingReportRejectedDocumentationFormFile.getFileName()))
    {
      String filePath = null;
      try
      {
        filePath = uploadNhsBackingReportFile(newNhsBackingReportUser.getRejectedDocumentationFileName(), nhsBackingReportRejectedDocumentationFormFile);
      }
      catch (Exception e)
      {
        errors.add("nhsBackingReport", new ActionMessage("error.nhsBackingReportFile.uploadFailed", filePath, e.getMessage()));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
    }    
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath(), actionForward.getRedirect());
  }

}
