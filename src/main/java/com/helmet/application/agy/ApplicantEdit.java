package com.helmet.application.agy;

import java.text.SimpleDateFormat;
import java.util.Collections;
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
import com.helmet.application.FileHandler;
import com.helmet.application.comparator.AgencyWorkerChecklistFileComparator;
import com.helmet.bean.ApplicantEntity;

public class ApplicantEdit extends ApplicantCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    ApplicantEntity applicant = (ApplicantEntity) dynaForm.get("applicant");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    applicant = agyService.getApplicantEntity(applicant.getApplicantId());

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String dateOfBirthStr = applicant.getDateOfBirth() == null ? "" : sdf.format(applicant.getDateOfBirth());
    String performanceEvaluationDateStr = applicant.getPerformanceEvaluationDate() == null ? "" : sdf.format(applicant.getPerformanceEvaluationDate());
    String reference2DateStr = applicant.getReference2Date() == null ? "" : sdf.format(applicant.getReference2Date());
    String reference1DateStr = applicant.getReference1Date() == null ? "" : sdf.format(applicant.getReference1Date());
    String fitToWorkExpiryDateStr = applicant.getFitToWorkExpiryDate() == null ? "" : sdf.format(applicant.getFitToWorkExpiryDate());
    String idDocumentExpiryDateStr = applicant.getIdDocumentExpiryDate() == null ? "" : sdf.format(applicant.getIdDocumentExpiryDate());
    String trainingExpiryDateStr = applicant.getTrainingExpiryDate() == null ? "" : sdf.format(applicant.getTrainingExpiryDate());
    String crbExpiryDateStr = applicant.getCrbExpiryDate() == null ? "" : sdf.format(applicant.getCrbExpiryDate());
    String crbIssueDateStr = applicant.getCrbIssueDate() == null ? "" : sdf.format(applicant.getCrbIssueDate());
    String registeredWithDbsDateStr = applicant.getRegisteredWithDbsDate() == null ? "" : sdf.format(applicant.getRegisteredWithDbsDate());
    String registrationExpiryDateStr = applicant.getRegistrationExpiryDate() == null ? "" : sdf.format(applicant.getRegistrationExpiryDate());
    String registrationLastCheckedDateStr = applicant.getRegistrationLastCheckedDate() == null ? "" : sdf.format(applicant.getRegistrationLastCheckedDate());
    String dbsRenewalDateStr = applicant.getDbsRenewalDate() == null ? "" : sdf.format(applicant.getDbsRenewalDate());
    String interviewDateStr = applicant.getInterviewDate() == null ? "" : sdf.format(applicant.getInterviewDate());
    String assessment12WeekDateStr = applicant.getAssessment12WeekDate() == null ? "" : sdf.format(applicant.getAssessment12WeekDate());
    String availabilityDateStr = applicant.getAvailabilityDate() == null ? "" : sdf.format(applicant.getAvailabilityDate());
    String arrivalInCountryDateStr = applicant.getArrivalInCountryDate() == null ? "" : sdf.format(applicant.getArrivalInCountryDate());
    String visaExpiryDateStr = applicant.getVisaExpiryDate() == null ? "" : sdf.format(applicant.getVisaExpiryDate());
    String drivingLicenseExpiryDateStr = applicant.getDrivingLicenseExpiryDate() == null ? "" : sdf.format(applicant.getDrivingLicenseExpiryDate());
    String paediatricLifeSupportIssuedDateStr = applicant.getPaediatricLifeSupportIssuedDate() == null ? "" : sdf.format(applicant.getPaediatricLifeSupportIssuedDate());
    List<AgencyWorkerChecklistFile> agencyWorkerChecklists = loadAgencyWorkerChecklists(FileHandler.getInstance().getApplicantFileLocation() + FileHandler.getInstance().getApplicantFileFolder() + "/" + applicant.getApplicantId(), applicant.getApplicantId());
    Collections.sort(agencyWorkerChecklists, new AgencyWorkerChecklistFileComparator());
    // Get ONLY the ACTIVE Unavailables from one year ago to one year in the future.
    String unavailableDates = getUnavailablesForApplicant(agyService, applicant);
    dynaForm.set("applicant", applicant);
    dynaForm.set("notes", getApplicantNotes(applicant).toString()); 
    dynaForm.set("dateOfBirthStr", dateOfBirthStr);
    dynaForm.set("performanceEvaluationDateStr", performanceEvaluationDateStr);
    dynaForm.set("reference2DateStr", reference2DateStr);
    dynaForm.set("reference1DateStr", reference1DateStr);
    dynaForm.set("fitToWorkExpiryDateStr", fitToWorkExpiryDateStr);
    dynaForm.set("idDocumentExpiryDateStr", idDocumentExpiryDateStr);
    dynaForm.set("trainingExpiryDateStr", trainingExpiryDateStr);
    dynaForm.set("crbExpiryDateStr", crbExpiryDateStr);
    dynaForm.set("crbIssueDateStr", crbIssueDateStr);
    dynaForm.set("registeredWithDbsDateStr", registeredWithDbsDateStr);
    dynaForm.set("registrationExpiryDateStr", registrationExpiryDateStr);
    dynaForm.set("registrationLastCheckedDateStr", registrationLastCheckedDateStr);
    dynaForm.set("dbsRenewalDateStr", dbsRenewalDateStr);
    dynaForm.set("interviewDateStr", interviewDateStr);
    dynaForm.set("assessment12WeekDateStr", assessment12WeekDateStr);
    dynaForm.set("availabilityDateStr", availabilityDateStr);
    dynaForm.set("arrivalInCountryDateStr", arrivalInCountryDateStr);
    dynaForm.set("visaExpiryDateStr", visaExpiryDateStr);
    dynaForm.set("drivingLicenseExpiryDateStr", drivingLicenseExpiryDateStr);
    dynaForm.set("paediatricLifeSupportIssuedDateStr", paediatricLifeSupportIssuedDateStr);
    dynaForm.set("agencyWorkerChecklists", agencyWorkerChecklists);
    dynaForm.set("unavailableDates", unavailableDates);
    dynaForm.set("confirmPwd", applicant.getUser().getPwd());

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
