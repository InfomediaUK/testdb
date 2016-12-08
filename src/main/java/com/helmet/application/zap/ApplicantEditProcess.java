package com.helmet.application.zap;

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

import com.helmet.api.ZapService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.app.AppUtilities;
import com.helmet.application.zap.abztract.ZapAction;
import com.helmet.bean.Applicant;


public class ApplicantEditProcess extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    ZapService zapService = ServiceFactory.getInstance().getZapService();
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    // Get partially filled Applicant from form.
    Applicant applicant = (Applicant)dynaForm.get("applicant");    
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    Applicant currentApplicant = AppUtilities.getCurrentApplicant(request);
    applicant.setApplicantId(currentApplicant.getApplicantId());
    int rowCount = 0;
    try
    {
      rowCount = zapService.updateApplicantByApplicant(applicant);
    }
    catch (Exception e)
    {
      if (rowCount == 0)
      {
        errors.add("applicant", new ActionMessage("errors.dataUpdatedByAdmin"));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
      else
      {
        e.printStackTrace();
      }
    }
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
