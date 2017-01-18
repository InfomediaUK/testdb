package com.helmet.application.agy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Constants;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;


public class ApplicantSearch extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) 
    {
      HttpSession session = request.getSession();
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    	logger.entry("In coming !!!");
      session.setAttribute("lastNameOperator", session.getAttribute("lastNameOperator") == null ? Constants.AND : (String)session.getAttribute("lastNameOperator"));    	
      dynaForm.set("lastNameOperator", (String)session.getAttribute("lastNameOperator"));
      session.setAttribute("performanceEvaluationOperator", session.getAttribute("performanceEvaluationOperator") == null ? Constants.AND : (String)session.getAttribute("performanceEvaluationOperator"));      
      dynaForm.set("performanceEvaluationOperator", (String)session.getAttribute("performanceEvaluationOperator"));
      session.setAttribute("assessment12WeekOperator", session.getAttribute("assessment12WeekOperator") == null ? Constants.AND : (String)session.getAttribute("assessment12WeekOperator"));      
      dynaForm.set("assessment12WeekOperator", (String)session.getAttribute("assessment12WeekOperator"));
      session.setAttribute("interviewedOperator", session.getAttribute("interviewedOperator") == null ? Constants.AND : (String)session.getAttribute("interviewedOperator"));      
      dynaForm.set("interviewedOperator", (String)session.getAttribute("interviewedOperator"));
      session.setAttribute("areaOfSpecialityIdOperator", session.getAttribute("areaOfSpecialityIdOperator") == null ? Constants.AND : (String)session.getAttribute("areaOfSpecialityIdOperator"));      
      dynaForm.set("areaOfSpecialityIdOperator", (String)session.getAttribute("areaOfSpecialityIdOperator"));
      session.setAttribute("areaOfSpecialityId2Operator", session.getAttribute("areaOfSpecialityId2Operator") == null ? Constants.AND : (String)session.getAttribute("areaOfSpecialityId2Operator"));      
      dynaForm.set("areaOfSpecialityId2Operator", (String)session.getAttribute("areaOfSpecialityId2Operator"));
      session.setAttribute("geographicalRegionIdOperator", session.getAttribute("geographicalRegionIdOperator") == null ? Constants.AND : (String)session.getAttribute("geographicalRegionIdOperator"));      
      dynaForm.set("geographicalRegionIdOperator", (String)session.getAttribute("geographicalRegionIdOperator"));
      session.setAttribute("disciplineCategoryIdOperator", session.getAttribute("disciplineCategoryIdOperator") == null ? Constants.AND : (String)session.getAttribute("disciplineCategoryIdOperator"));      
      dynaForm.set("disciplineCategoryIdOperator", (String)session.getAttribute("disciplineCategoryIdOperator"));
      session.setAttribute("clientGroupOperator", session.getAttribute("clientGroupOperator") == null ? Constants.AND : (String)session.getAttribute("clientGroupOperator"));      
      dynaForm.set("clientGroupOperator", (String)session.getAttribute("clientGroupOperator"));
      session.setAttribute("niNumberStatusOperator", session.getAttribute("niNumberStatusOperator") == null ? Constants.AND : (String)session.getAttribute("niNumberStatusOperator"));      
      dynaForm.set("niNumberStatusOperator", (String)session.getAttribute("niNumberStatusOperator"));
      session.setAttribute("availabilityDateOperator", session.getAttribute("availabilityDateOperator") == null ? Constants.AND : (String)session.getAttribute("availabilityDateOperator"));      
      dynaForm.set("availabilityDateOperator", (String)session.getAttribute("availabilityDateOperator"));
      session.setAttribute("visaExpiryDateOperator", session.getAttribute("visaExpiryDateOperator") == null ? Constants.OR : (String)session.getAttribute("visaExpiryDateOperator"));      
      dynaForm.set("visaExpiryDateOperator", (String)session.getAttribute("visaExpiryDateOperator"));
      session.setAttribute("idDocumentExpiryDateOperator", session.getAttribute("idDocumentExpiryDateOperator") == null ? Constants.OR : (String)session.getAttribute("idDocumentExpiryDateOperator"));      
      dynaForm.set("idDocumentExpiryDateOperator", (String)session.getAttribute("idDocumentExpiryDateOperator"));
      session.setAttribute("crbExpiryDateOperator", session.getAttribute("crbExpiryDateOperator") == null ? Constants.OR : (String)session.getAttribute("crbExpiryDateOperator"));      
      dynaForm.set("crbExpiryDateOperator", (String)session.getAttribute("crbExpiryDateOperator"));
      session.setAttribute("hpcExpiryDateOperator", session.getAttribute("hpcExpiryDateOperator") == null ? Constants.OR : (String)session.getAttribute("hpcExpiryDateOperator"));      
      dynaForm.set("hpcExpiryDateOperator", (String)session.getAttribute("hpcExpiryDateOperator"));
      session.setAttribute("trainingExpiryDateOperator", session.getAttribute("trainingExpiryDateOperator") == null ? Constants.OR : (String)session.getAttribute("trainingExpiryDateOperator"));      
      dynaForm.set("trainingExpiryDateOperator", (String)session.getAttribute("trainingExpiryDateOperator"));
      session.setAttribute("fitToWorkExpiryDateOperator", session.getAttribute("fitToWorkExpiryDateOperator") == null ? Constants.OR : (String)session.getAttribute("fitToWorkExpiryDateOperator"));      
      dynaForm.set("fitToWorkExpiryDateOperator", (String)session.getAttribute("fitToWorkExpiryDateOperator"));
      session.setAttribute("hasVaricellaDocumentOperator", session.getAttribute("hasVaricellaDocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasVaricellaDocumentOperator"));      
      dynaForm.set("hasVaricellaDocumentOperator", (String)session.getAttribute("hasVaricellaDocumentOperator"));
      session.setAttribute("hasHepbDocumentOperator", session.getAttribute("hasHepbDocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasHepbDocumentOperator"));      
      dynaForm.set("hasHepbDocumentOperator", (String)session.getAttribute("hasHepbDocumentOperator"));
      session.setAttribute("hasTuberculosisDocumentOperator", session.getAttribute("hasTuberculosisDocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasTuberculosisDocumentOperator"));      
      dynaForm.set("hasTuberculosisDocumentOperator", (String)session.getAttribute("hasTuberculosisDocumentOperator"));
      session.setAttribute("hasMMRX2DocumentOperator", session.getAttribute("hasMMRX2DocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasMMRX2DocumentOperator"));      
      dynaForm.set("hasMMRX2DocumentOperator", (String)session.getAttribute("hasMMRX2DocumentOperator"));
      session.setAttribute("hasMMRDocumentOperator", session.getAttribute("hasMMRDocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasMMRDocumentOperator"));      
      dynaForm.set("hasMMRDocumentOperator", (String)session.getAttribute("hasMMRDocumentOperator"));
      session.setAttribute("hasFitToWorkDocumentOperator", session.getAttribute("hasFitToWorkDocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasFitToWorkDocumentOperator"));      
      dynaForm.set("hasFitToWorkDocumentOperator", (String)session.getAttribute("hasFitToWorkDocumentOperator"));
      session.setAttribute("hasCRBDocumentOperator", session.getAttribute("hasCRBDocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasCRBDocumentOperator"));      
      dynaForm.set("hasCRBDocumentOperator", (String)session.getAttribute("hasCRBDocumentOperator"));
      session.setAttribute("hasHPCDocumentOperator", session.getAttribute("hasHPCDocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasHPCDocumentOperator"));      
      dynaForm.set("hasHPCDocumentOperator", (String)session.getAttribute("hasHPCDocumentOperator"));
      session.setAttribute("hasTrainingCertificateOperator", session.getAttribute("hasTrainingCertificateOperator") == null ? Constants.AND : (String)session.getAttribute("hasTrainingCertificateOperator"));      
      dynaForm.set("hasTrainingCertificateOperator", (String)session.getAttribute("hasTrainingCertificateOperator"));
      session.setAttribute("hasIdDocumentOperator", session.getAttribute("hasIdDocumentOperator") == null ? Constants.AND : (String)session.getAttribute("hasIdDocumentOperator"));      
      dynaForm.set("hasIdDocumentOperator", (String)session.getAttribute("hasIdDocumentOperator"));
      session.setAttribute("hasProofOfAddress1Operator", session.getAttribute("hasProofOfAddress1Operator") == null ? Constants.AND : (String)session.getAttribute("hasProofOfAddress1Operator"));      
      dynaForm.set("hasProofOfAddress1Operator", (String)session.getAttribute("hasProofOfAddress1Operator"));
      session.setAttribute("hasProofOfAddress2Operator", session.getAttribute("hasProofOfAddress2Operator") == null ? Constants.AND : (String)session.getAttribute("hasProofOfAddress2Operator"));      
      dynaForm.set("hasProofOfAddress2Operator", (String)session.getAttribute("hasProofOfAddress2Operator"));
      session.setAttribute("hasBirthCertificateOperator", session.getAttribute("hasBirthCertificateOperator") == null ? Constants.AND : (String)session.getAttribute("hasBirthCertificateOperator"));      
      dynaForm.set("hasBirthCertificateOperator", (String)session.getAttribute("hasBirthCertificateOperator"));
      session.setAttribute("hasOverseasPoliceClearanceOperator", session.getAttribute("hasOverseasPoliceClearanceOperator") == null ? Constants.AND : (String)session.getAttribute("hasOverseasPoliceClearanceOperator"));      
      dynaForm.set("hasOverseasPoliceClearanceOperator", (String)session.getAttribute("hasOverseasPoliceClearanceOperator"));
      session.setAttribute("hasDegreeOperator", session.getAttribute("hasDegreeOperator") == null ? Constants.AND : (String)session.getAttribute("hasDegreeOperator"));      
      dynaForm.set("hasDegreeOperator", (String)session.getAttribute("hasDegreeOperator"));
      session.setAttribute("isArchivedOperator", session.getAttribute("isArchivedOperator") == null ? Constants.AND : (String)session.getAttribute("isArchivedOperator"));      
      dynaForm.set("isArchivedOperator", (String)session.getAttribute("isArchivedOperator"));
      session.setAttribute("isCompliantOperator", session.getAttribute("isCompliantOperator") == null ? Constants.AND : (String)session.getAttribute("isCompliantOperator"));      
      dynaForm.set("isCompliantOperator", (String)session.getAttribute("isCompliantOperator"));

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
