package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.ApplicantEntity;


public class ApplicantNew extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    HttpSession session = request.getSession();
    Integer applicantTab = (Integer)session.getAttribute("applicantTab");
    applicantTab = applicantTab == null ? 0 : applicantTab;
    Integer weekToShow = (Integer)session.getAttribute("weekToShow");
    weekToShow = weekToShow == null ? new Integer(0) : weekToShow;
    ApplicantEntity applicant = (ApplicantEntity)dynaForm.get("applicant");
    applicant.setHideMoney(false);
    applicant.setCanToggleHideMoney(true);
    applicant.setApplicantId(0);
    dynaForm.set("applicant", applicant);
    dynaForm.set("dateOfBirthStr", "");
    dynaForm.set("performanceEvaluationDateStr", "");
    dynaForm.set("reference2DateStr", "");
    dynaForm.set("reference1DateStr", "");
    dynaForm.set("fitToWorkExpiryDateStr", "");
    dynaForm.set("idDocumentExpiryDateStr", "");
    dynaForm.set("trainingExpiryDateStr", "");
    dynaForm.set("crbExpiryDateStr", "");
    dynaForm.set("crbIssueDateStr", "");
    dynaForm.set("registrationExpiryDateStr", "");
    dynaForm.set("registrationLastCheckedDateStr", "");
    dynaForm.set("interviewDateStr", "");
    // NEW -->
    dynaForm.set("paediatricLifeSupportIssuedDateStr", "");
    dynaForm.set("assessment12WeekDateStr", "");
    dynaForm.set("availabilityDateStr", "");
    dynaForm.set("arrivalInCountryDateStr", "");
    dynaForm.set("visaExpiryDateStr", "");
    dynaForm.set("drivingLicenseExpiryDateStr", "");
    dynaForm.set("weekToShow", weekToShow);
    dynaForm.set("applicantTab", applicantTab);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
