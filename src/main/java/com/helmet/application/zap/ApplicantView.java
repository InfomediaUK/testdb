package com.helmet.application.zap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.ZapService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.app.AppUtilities;
import com.helmet.application.zap.abztract.ZapAction;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;


public class ApplicantView extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");
    ZapService zapService = ServiceFactory.getInstance().getZapService();
    Applicant applicant = zapService.getApplicant(getApplicantLoggedIn().getApplicantId());
    if (applicant == null) { return mapping.findForward("illegalAccess"); }
    // Get the Applicant & Agency records every time they're viewed so that we have the latest versions.
    AppUtilities.setCurrentApplicant(request, applicant);
    Agency agency = zapService.getAgency(applicant.getAgencyId());
    AppUtilities.setCurrentAgency(request, agency);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
