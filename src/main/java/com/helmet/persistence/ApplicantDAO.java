package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.application.agy.ApplicantSearchParameters;
import com.helmet.bean.Applicant;
import com.helmet.bean.ApplicantClientBooking;

public interface ApplicantDAO 
{
  public List<Applicant> getApplicantsForAgency(Integer agencyId, boolean showOnlyActive);
  public List<Applicant> getApplicantsForAgencySearch(Integer agencyId, ApplicantSearchParameters applicantSearchParameters, boolean showOnlyActive);
  public List<Applicant> getApplicantsForAgencyInLastNameGroup(Integer agencyId, String lastNameGroup, boolean showOnlyActive);
  public List<Applicant> getApplicantsForNhsStaffName(Integer agencyId, String nhsStaffName);
	public List<Applicant> getApplicantsForAgencyAndNotForBookingGrade(Integer agencyId, Integer bookingGradeId);
  public List<Applicant> getApplicantsForAgencyAndNotForBookingGradeInLastNameGroup(Integer agencyId, Integer bookingGradeId, String lastNameGroup);
  public List<Applicant> getApplicantsForAgencyNew(Integer agencyId, Date dateToday);
  public List<Applicant> getApplicantsForAgencyCrbAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyDbsAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyFitToWorkAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyRegistrationAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyIdDocumentAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyTrainingAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyVisaAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsToCopy(Integer sourceAgencyId, Integer targetAgencyId);
  public List<Applicant> getApplicantsForIdDocument(Integer idDocumentId);
  
  public List<Applicant> getApplicantsForAgencyReference1NotSatisfied(Integer agencyId, Date dateToday);
  public List<Applicant> getApplicantsForAgencyReference2NotSatisfied(Integer agencyId, Date dateToday);
  public List<Applicant> getApplicantsForAgencyDrivingLicenseAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyUnarchived(Integer agencyId);
  public List<Applicant> getApplicantsForAgencyRecentlyCompliant(Integer agencyId);
  public List<Applicant> getApplicantsForAgencyRecentProspect(Integer agencyId);

  public Applicant getApplicantForLogin(Integer agencyId, String login);
	public Applicant getApplicant(Integer applicantId);
	public int insertApplicant(Applicant applicant, Integer auditorId);
	public int updateApplicant(Applicant applicant, Integer auditorId);
  public int archiveApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId);
  public int unarchiveApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId);
  public int compliantApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId, Boolean compliant);
  public int deleteApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId);
  public int updateApplicantChecklistCreatedTime(Integer applicantId, Integer noOfChanges, Integer auditorId, Date checklistCreatedTime);
  public int updateApplicantByApplicant(Applicant applicant);
  public int updateApplicantPwd(Integer applicantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
  public int updateApplicantSecretWord(Integer applicantId, String newSecretWord, Integer noOfChanges, Integer auditorId);

  public List<ApplicantClientBooking> getApplicantClientBookings(Integer applicantId, Integer clientId, Integer agencyId, Date searchDate);
  
}
