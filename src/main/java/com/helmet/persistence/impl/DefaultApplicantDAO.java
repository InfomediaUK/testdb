package com.helmet.persistence.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.application.Constants;
import com.helmet.application.agy.ApplicantSearchParameters;
import com.helmet.bean.Applicant;
import com.helmet.bean.ApplicantClientBooking;
import com.helmet.persistence.ApplicantDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultApplicantDAO extends JdbcDaoSupport implements ApplicantDAO {

	private static StringBuffer insertAuditorSQL;

	private static StringBuffer insertApplicantSQL;

  private static StringBuffer updateApplicantSQL;

  private static StringBuffer updateApplicantByApplicantSQL;

  private static StringBuffer updateApplicantChecklistCreatedTimeSQL;
  
  private static StringBuffer updateApplicantPwdSQL;

	private static StringBuffer updateApplicantSecretWordSQL;

  private static StringBuffer archiveApplicantSQL;

  private static StringBuffer unarchiveApplicantSQL;

  private static StringBuffer compliantApplicantSQL;

  private static StringBuffer deleteApplicantSQL;

	private static StringBuffer selectApplicantsSQL;

  private static StringBuffer selectApplicantsForAgencySQL;

  private static StringBuffer selectApplicantsToCopySQL;

	private static StringBuffer selectActiveApplicantsForAgencySQL;

  private static StringBuffer selectApplicantsForNhsStaffNameSQL;

  private static StringBuffer selectApplicantsForAgencyInLastNameGroupSQL;

  private static StringBuffer selectActiveApplicantsForAgencyInLastNameGroupSQL;

  private static StringBuffer selectActiveApplicantsForAgencyNewSQL;

  private static StringBuffer selectActiveApplicantsForAgencyCrbAboutToExpireSQL;

  private static StringBuffer selectActiveApplicantsForAgencyDbsAboutToExpireSQL;

  private static StringBuffer selectActiveApplicantsForAgencyFitToWorkAboutToExpireSQL;

  private static StringBuffer selectActiveApplicantsForAgencyHpcAboutToExpireSQL;

  private static StringBuffer selectActiveApplicantsForAgencyIdDocumentAboutToExpireSQL;

  private static StringBuffer selectActiveApplicantsForAgencyTrainingAboutToExpireSQL;

  private static StringBuffer selectActiveApplicantsForAgencyVisaAboutToExpireSQL;

  private static StringBuffer selectActiveApplicantsForAgencyReference1NotSatisfiedSQL;

  private static StringBuffer selectActiveApplicantsForAgencyReference2NotSatisfiedSQL;

  private static StringBuffer selectActiveApplicantsForAgencyDrivingLicenseAboutToExpireSQL;

  private static StringBuffer selectActiveApplicantsForAgencyUnarchivedSQL;

  private static StringBuffer selectActiveApplicantsForAgencyRecentlyCompliantSQL;
  
  private static StringBuffer selectActiveApplicantsForAgencyRecentProspectSQL;
  
  private static StringBuffer selectActiveApplicantsForAgencyAndNotForBookingGradeSQL;

  private static StringBuffer selectActiveApplicantsForAgencyAndNotForBookingGradeInLastNameGroupSQL;

	private static StringBuffer selectApplicantSQL;

  private static StringBuffer selectApplicantForLoginSQL;

  private static StringBuffer selectApplicantClientBookingsSQL;

  public static void init() {
		// Get insert Auditor SQL.
		insertAuditorSQL = new StringBuffer();
		insertAuditorSQL.append("INSERT INTO AUDITOR ");
		insertAuditorSQL.append("(  ");
		insertAuditorSQL.append("  AUDITORID, ");
		insertAuditorSQL.append("  TYPE ");
		insertAuditorSQL.append(")  ");
		insertAuditorSQL.append("VALUES  ");
		insertAuditorSQL.append("(  ");
		insertAuditorSQL.append("  ^, ");
		insertAuditorSQL.append("  'P' ");
		insertAuditorSQL.append(")  ");
		// Get insert Applicant SQL.
		insertApplicantSQL = new StringBuffer();
		insertApplicantSQL.append("INSERT INTO APPLICANT ");
		insertApplicantSQL.append("(  ");
		insertApplicantSQL.append("  APPLICANTID, ");
		insertApplicantSQL.append("  AGENCYID, ");
		insertApplicantSQL.append("  FIRSTNAME, ");
		insertApplicantSQL.append("  LASTNAME, ");
		insertApplicantSQL.append("  EMAILADDRESS, ");
		insertApplicantSQL.append("  LOGIN, ");
		insertApplicantSQL.append("  PWD, ");
		insertApplicantSQL.append("  PWDHINT, ");
		insertApplicantSQL.append("  SECRETWORD, ");
		insertApplicantSQL.append("  REFERENCE, ");
		insertApplicantSQL.append("  PROFESSIONALREFERENCE, ");
		insertApplicantSQL.append("  DATEOFBIRTH, ");
		insertApplicantSQL.append("  NINUMBER, ");
		insertApplicantSQL.append("  PHOTOFILENAME, ");
		insertApplicantSQL.append("  GENDER, ");
		insertApplicantSQL.append("  LIMITEDCOMPANYNAME, ");
		insertApplicantSQL.append("  HIDEMONEY, ");
		insertApplicantSQL.append("  CANTOGGLEHIDEMONEY, ");
		insertApplicantSQL.append("  ADDRESS1, ");
		insertApplicantSQL.append("  ADDRESS2, ");
		insertApplicantSQL.append("  ADDRESS3, ");
		insertApplicantSQL.append("  ADDRESS4, ");
		insertApplicantSQL.append("  POSTALCODE, ");
		insertApplicantSQL.append("  COUNTRYID, ");
		insertApplicantSQL.append("  TELEPHONENUMBER, ");
		insertApplicantSQL.append("  MOBILENUMBER, ");
		insertApplicantSQL.append("  VARICELLAFILENAME, ");
		insertApplicantSQL.append("  HEPBFILENAME, ");
		insertApplicantSQL.append("  TBFILENAME, ");
		insertApplicantSQL.append("  MMRX2FILENAME, ");
		insertApplicantSQL.append("  MMRFILENAME, ");
		insertApplicantSQL.append("  PERFORMANCEEVALUATIONDATE, ");
		insertApplicantSQL.append("  PERFORMANCEEVALUATION, ");
		insertApplicantSQL.append("  COMPLIANT, ");
		insertApplicantSQL.append("  REFERENCE2FILENAME, ");
		insertApplicantSQL.append("  REFERENCE2DATE, ");
		insertApplicantSQL.append("  REFERENCE2STATUS, ");
		insertApplicantSQL.append("  REFERENCE2, ");
		insertApplicantSQL.append("  REFERENCE1FILENAME, ");
		insertApplicantSQL.append("  REFERENCE1DATE, ");
		insertApplicantSQL.append("  REFERENCE1STATUS, ");
		insertApplicantSQL.append("  REFERENCE1, ");
		insertApplicantSQL.append("  CVFILENAME, ");
		insertApplicantSQL.append("  OVERSEASPOLICECLEARANCE, ");
		insertApplicantSQL.append("  DEGREEDETAIL, ");
		insertApplicantSQL.append("  DEGREE, ");
		insertApplicantSQL.append("  BIRTHCERTIFICATEFILENAME, ");
		insertApplicantSQL.append("  BIRTHCERTIFICATE, ");
    insertApplicantSQL.append("  PROOFOFADDRESS1FILENAME, ");
    insertApplicantSQL.append("  PROOFOFADDRESS1, ");
    insertApplicantSQL.append("  PROOFOFADDRESS2FILENAME, ");
    insertApplicantSQL.append("  PROOFOFADDRESS2, ");
		insertApplicantSQL.append("  NINUMBERSTATUS, ");
		insertApplicantSQL.append("  FITTOWORKFILENAME, ");
		insertApplicantSQL.append("  FITTOWORKEXPIRYDATE, ");
		insertApplicantSQL.append("  IDDOCUMENTFILENAME, ");
		insertApplicantSQL.append("  IDDOCUMENTEXPIRYDATE, ");
		insertApplicantSQL.append("  IDDOCUMENTNUMBER, ");
		insertApplicantSQL.append("  TRAININGFILENAME, ");
		insertApplicantSQL.append("  TRAININGEXPIRYDATE, ");
		insertApplicantSQL.append("  CRBFILENAME, ");
		insertApplicantSQL.append("  CRBEXPIRYDATE, ");
		insertApplicantSQL.append("  CRBISSUEDATE, ");
		insertApplicantSQL.append("  HPCFILENAME, ");
		insertApplicantSQL.append("  HPCEXPIRYDATE, ");
		insertApplicantSQL.append("  HPCNUMBER, ");
		insertApplicantSQL.append("  INTERVIEWDATE, ");
    insertApplicantSQL.append("  ASSESSMENT12WEEK, ");
    insertApplicantSQL.append("  ASSESSMENT12WEEKDATE, ");
    insertApplicantSQL.append("  AVAILABILITYDATE, ");
    insertApplicantSQL.append("  ARRIVALINCOUNTRYDATE, ");
    insertApplicantSQL.append("  VISAEXPIRYDATE, ");
    insertApplicantSQL.append("  AREAOFSPECIALITYID, ");
    insertApplicantSQL.append("  GEOGRAPHICALREGIONID, ");
    insertApplicantSQL.append("  DISCIPLINECATEGORYID, ");
    insertApplicantSQL.append("  CLIENTGROUP, ");
    insertApplicantSQL.append("  DRIVINGLICENSE, ");
    insertApplicantSQL.append("  DRIVINGLICENSEEXPIRYDATE, ");
    insertApplicantSQL.append("  FITTOWORKSTATUS, ");
    insertApplicantSQL.append("  AREAOFSPECIALITYID2, ");
    insertApplicantSQL.append("  CURRENTLYWORKING, ");
    insertApplicantSQL.append("  VATCHARGEABLE, ");
    insertApplicantSQL.append("  BANKNAME, ");
    insertApplicantSQL.append("  BANKSORTCODE, ");
    insertApplicantSQL.append("  BANKACCOUNTNAME, ");
    insertApplicantSQL.append("  BANKACCOUNTNUMBER, ");
    insertApplicantSQL.append("  IDDOCUMENT, ");
    insertApplicantSQL.append("  LANGUAGECOMPETENCY, ");
    insertApplicantSQL.append("  FITTOWORKISSUEDBY, ");
    insertApplicantSQL.append("  IVSEPPFILENAME, ");
    insertApplicantSQL.append("  MANUALHANDLINGTRAINING, ");
    insertApplicantSQL.append("  BASICLIFESUPPORTTRAINING, ");
    insertApplicantSQL.append("  ELEARNINGTRAINING, ");
    insertApplicantSQL.append("  POVATRAINING, ");
    insertApplicantSQL.append("  NEONATALLIFESUPPORTTRAINING, ");
    insertApplicantSQL.append("  AHPREGISTRATIONTYPE, ");
    insertApplicantSQL.append("  HPCLASTCHECKEDDATE, ");
    insertApplicantSQL.append("  HPCALERTNOTIFICATION, ");
    insertApplicantSQL.append("  PAEDIATRICLIFESUPPORTFILENAME, ");
    insertApplicantSQL.append("  PAEDIATRICLIFESUPPORTISSUEDDATE, ");
    insertApplicantSQL.append("  VISATYPE, ");
    insertApplicantSQL.append("  CRBDISCLOSURENUMBER, ");
    insertApplicantSQL.append("  ENGLISHTESTCERTIFICATEFILENAME, ");
    insertApplicantSQL.append("  NHSSTAFFNAME, ");
    insertApplicantSQL.append("  ORIGINALAGENCYID, ");
    insertApplicantSQL.append("  REGISTEREDWITHDBSDATE, ");
    // NEW -->
    insertApplicantSQL.append("  DBSRENEWALDATE, ");
    insertApplicantSQL.append("  DBSFILENAME, ");
    // <-- NEW
		insertApplicantSQL.append("  CREATIONTIMESTAMP, ");
		insertApplicantSQL.append("  AUDITORID, ");
		insertApplicantSQL.append("  AUDITTIMESTAMP ");
		insertApplicantSQL.append(")  ");
		insertApplicantSQL.append("VALUES  ");
		insertApplicantSQL.append("(  ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    insertApplicantSQL.append("  ^, ");
    // NEW -->
    insertApplicantSQL.append("  ^, ");
    // <-- NEW
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^, ");
		insertApplicantSQL.append("  ^ ");
		insertApplicantSQL.append(")  ");
		// Get update Applicant SQL.
		updateApplicantSQL = new StringBuffer();
		updateApplicantSQL.append("UPDATE APPLICANT ");
		updateApplicantSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateApplicantSQL.append("     AGENCYID = ^, ");
		updateApplicantSQL.append("     FIRSTNAME = ^, ");
		updateApplicantSQL.append("     LASTNAME = ^, ");
		updateApplicantSQL.append("     EMAILADDRESS = ^, ");
		updateApplicantSQL.append("     LOGIN = ^, ");
		updateApplicantSQL.append("     PWD = ^, ");
		updateApplicantSQL.append("     PWDHINT = ^, ");
		updateApplicantSQL.append("     SHOWPAGEHELP = ^, ");
		updateApplicantSQL.append("     REFERENCE = ^, ");
		updateApplicantSQL.append("     PROFESSIONALREFERENCE = ^, ");
		updateApplicantSQL.append("     DATEOFBIRTH = ^, ");
		updateApplicantSQL.append("     NINUMBER = ^, ");
		updateApplicantSQL.append("     PHOTOFILENAME = ^, ");
		updateApplicantSQL.append("     GENDER = ^, ");
		updateApplicantSQL.append("     LIMITEDCOMPANYNAME = ^, ");
		updateApplicantSQL.append("     HIDEMONEY = ^, ");
		updateApplicantSQL.append("     CANTOGGLEHIDEMONEY = ^, ");
		updateApplicantSQL.append("     ADDRESS1 = ^, ");
		updateApplicantSQL.append("     ADDRESS2 = ^, ");
		updateApplicantSQL.append("     ADDRESS3 = ^, ");
		updateApplicantSQL.append("     ADDRESS4 = ^, ");
		updateApplicantSQL.append("     POSTALCODE = ^, ");
		updateApplicantSQL.append("     COUNTRYID = ^, ");
		updateApplicantSQL.append("     TELEPHONENUMBER = ^, ");
		updateApplicantSQL.append("     MOBILENUMBER = ^, ");
		updateApplicantSQL.append("     VARICELLAFILENAME = ^, ");
		updateApplicantSQL.append("     HEPBFILENAME = ^, ");
		updateApplicantSQL.append("     TBFILENAME = ^, ");
		updateApplicantSQL.append("     MMRX2FILENAME = ^, ");
		updateApplicantSQL.append("     MMRFILENAME = ^, ");
		updateApplicantSQL.append("     PERFORMANCEEVALUATIONDATE = ^, ");
		updateApplicantSQL.append("     PERFORMANCEEVALUATION = ^, ");
    updateApplicantSQL.append("     COMPLIANT = ^, ");
    updateApplicantSQL.append("     HASBEENUNARCHIVED = ^, ");
		updateApplicantSQL.append("     REFERENCE2FILENAME = ^, ");
		updateApplicantSQL.append("     REFERENCE2DATE = ^, ");
		updateApplicantSQL.append("     REFERENCE2STATUS = ^, ");
		updateApplicantSQL.append("     REFERENCE2 = ^, ");
		updateApplicantSQL.append("     REFERENCE1FILENAME = ^, ");
		updateApplicantSQL.append("     REFERENCE1DATE = ^, ");
		updateApplicantSQL.append("     REFERENCE1STATUS = ^, ");
		updateApplicantSQL.append("     REFERENCE1 = ^, ");
		updateApplicantSQL.append("     CVFILENAME = ^, ");
		updateApplicantSQL.append("     OVERSEASPOLICECLEARANCE = ^, ");
		updateApplicantSQL.append("     DEGREEDETAIL = ^, ");
		updateApplicantSQL.append("     DEGREE = ^, ");
		updateApplicantSQL.append("     BIRTHCERTIFICATEFILENAME = ^, ");
		updateApplicantSQL.append("     BIRTHCERTIFICATE = ^, ");
    updateApplicantSQL.append("     PROOFOFADDRESS1FILENAME = ^, ");
    updateApplicantSQL.append("     PROOFOFADDRESS1 = ^, ");
    updateApplicantSQL.append("     PROOFOFADDRESS2FILENAME = ^, ");
    updateApplicantSQL.append("     PROOFOFADDRESS2 = ^, ");
		updateApplicantSQL.append("     NINUMBERSTATUS = ^, ");
		updateApplicantSQL.append("     FITTOWORKFILENAME = ^, ");
		updateApplicantSQL.append("     FITTOWORKEXPIRYDATE = ^, ");
		updateApplicantSQL.append("     IDDOCUMENTFILENAME = ^, ");
		updateApplicantSQL.append("     IDDOCUMENTEXPIRYDATE = ^, ");
		updateApplicantSQL.append("     IDDOCUMENTNUMBER = ^, ");
		updateApplicantSQL.append("     TRAININGFILENAME = ^, ");
		updateApplicantSQL.append("     TRAININGEXPIRYDATE = ^, ");
		updateApplicantSQL.append("     CRBFILENAME = ^, ");
		updateApplicantSQL.append("     CRBEXPIRYDATE = ^, ");
		updateApplicantSQL.append("     CRBISSUEDATE = ^, ");
		updateApplicantSQL.append("     HPCFILENAME = ^, ");
		updateApplicantSQL.append("     HPCEXPIRYDATE = ^, ");
		updateApplicantSQL.append("     HPCNUMBER = ^, ");
		updateApplicantSQL.append("     INTERVIEWDATE = ^, ");
    updateApplicantSQL.append("     ASSESSMENT12WEEK = ^, ");
    updateApplicantSQL.append("     ASSESSMENT12WEEKDATE = ^, ");
    updateApplicantSQL.append("     AVAILABILITYDATE = ^, ");
    updateApplicantSQL.append("     ARRIVALINCOUNTRYDATE = ^, ");
    updateApplicantSQL.append("     VISAEXPIRYDATE = ^, ");
    updateApplicantSQL.append("     AREAOFSPECIALITYID = ^, ");
    updateApplicantSQL.append("     GEOGRAPHICALREGIONID = ^, ");
    updateApplicantSQL.append("     DISCIPLINECATEGORYID = ^, ");
    updateApplicantSQL.append("     CLIENTGROUP = ^, ");
    updateApplicantSQL.append("     DRIVINGLICENSE = ^, ");
    updateApplicantSQL.append("     DRIVINGLICENSEEXPIRYDATE = ^, ");
    updateApplicantSQL.append("     FITTOWORKSTATUS = ^, ");
    updateApplicantSQL.append("     AREAOFSPECIALITYID2 = ^, ");
    updateApplicantSQL.append("     RECENTLYCOMPLIANT = ^, ");
    updateApplicantSQL.append("     CURRENTLYWORKING = ^, ");
    updateApplicantSQL.append("     VATCHARGEABLE = ^, ");
    updateApplicantSQL.append("     BANKNAME = ^, ");
    updateApplicantSQL.append("     BANKSORTCODE = ^, ");
    updateApplicantSQL.append("     BANKACCOUNTNAME = ^, ");
    updateApplicantSQL.append("     BANKACCOUNTNUMBER = ^, ");
    updateApplicantSQL.append("     IDDOCUMENT = ^, ");
    updateApplicantSQL.append("     LANGUAGECOMPETENCY = ^, ");
    updateApplicantSQL.append("     FITTOWORKISSUEDBY = ^, ");
    updateApplicantSQL.append("     IVSEPPFILENAME = ^, ");
    updateApplicantSQL.append("     MANUALHANDLINGTRAINING = ^, ");
    updateApplicantSQL.append("     BASICLIFESUPPORTTRAINING = ^, ");
    updateApplicantSQL.append("     ELEARNINGTRAINING = ^, ");
    updateApplicantSQL.append("     POVATRAINING = ^, ");
    updateApplicantSQL.append("     NEONATALLIFESUPPORTTRAINING = ^, ");
    updateApplicantSQL.append("     AHPREGISTRATIONTYPE = ^, ");
    updateApplicantSQL.append("     HPCLASTCHECKEDDATE = ^, ");
    updateApplicantSQL.append("     HPCALERTNOTIFICATION = ^, ");
    updateApplicantSQL.append("     PAEDIATRICLIFESUPPORTFILENAME = ^, ");
    updateApplicantSQL.append("     PAEDIATRICLIFESUPPORTISSUEDDATE = ^, ");
    updateApplicantSQL.append("     VISATYPE = ^, ");
    updateApplicantSQL.append("     CRBDISCLOSURENUMBER = ^, ");
    updateApplicantSQL.append("     ENGLISHTESTCERTIFICATEFILENAME = ^, ");
    updateApplicantSQL.append("     NHSSTAFFNAME = ^, ");
    updateApplicantSQL.append("     REGISTEREDWITHDBSDATE = ^, ");
    // NEW -->
    updateApplicantSQL.append("     DBSRENEWALDATE = ^, ");
    updateApplicantSQL.append("     DBSFILENAME = ^, ");
    // <-- NEW
		updateApplicantSQL.append("     AUDITORID = ^, ");
		updateApplicantSQL.append("     AUDITTIMESTAMP = ^ ");
		updateApplicantSQL.append("WHERE APPLICANTID = ^ ");
		updateApplicantSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update Applicant by Applicant SQL.
    updateApplicantByApplicantSQL = new StringBuffer();
    updateApplicantByApplicantSQL.append("UPDATE APPLICANT ");
    updateApplicantByApplicantSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateApplicantByApplicantSQL.append("     FIRSTNAME = ^, ");
    updateApplicantByApplicantSQL.append("     LASTNAME = ^, ");
    updateApplicantByApplicantSQL.append("     EMAILADDRESS = ^, ");
    updateApplicantByApplicantSQL.append("     ADDRESS1 = ^, ");
    updateApplicantByApplicantSQL.append("     ADDRESS2 = ^, ");
    updateApplicantByApplicantSQL.append("     ADDRESS3 = ^, ");
    updateApplicantByApplicantSQL.append("     ADDRESS4 = ^, ");
    updateApplicantByApplicantSQL.append("     POSTALCODE = ^, ");
    updateApplicantByApplicantSQL.append("     COUNTRYID = ^, ");
    updateApplicantByApplicantSQL.append("     TELEPHONENUMBER = ^, ");
    updateApplicantByApplicantSQL.append("     MOBILENUMBER = ^, ");
    updateApplicantByApplicantSQL.append("     AUDITORID = ^, ");
    updateApplicantByApplicantSQL.append("     AUDITTIMESTAMP = ^ ");
    updateApplicantByApplicantSQL.append("WHERE APPLICANTID = ^ ");
    updateApplicantByApplicantSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update Applicant Checklist Created Date SQL.
    updateApplicantChecklistCreatedTimeSQL = new StringBuffer();
    updateApplicantChecklistCreatedTimeSQL.append("UPDATE APPLICANT ");
    updateApplicantChecklistCreatedTimeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateApplicantChecklistCreatedTimeSQL.append("     CHECKLISTCREATEDTIME = ^, ");
    updateApplicantChecklistCreatedTimeSQL.append("     AUDITORID = ^, ");
    updateApplicantChecklistCreatedTimeSQL.append("     AUDITTIMESTAMP = ^ ");
    updateApplicantChecklistCreatedTimeSQL.append("WHERE APPLICANTID = ^ ");
    updateApplicantChecklistCreatedTimeSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update ApplicantPwd SQL.
    updateApplicantPwdSQL = new StringBuffer();
    updateApplicantPwdSQL.append("UPDATE APPLICANT ");
    updateApplicantPwdSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateApplicantPwdSQL.append("     PWD = ^, ");
    updateApplicantPwdSQL.append("     PWDHINT = ^, ");
    updateApplicantPwdSQL.append("     AUDITORID = ^, ");
    updateApplicantPwdSQL.append("     AUDITTIMESTAMP = ^ ");
    updateApplicantPwdSQL.append("WHERE APPLICANTID = ^ ");
    updateApplicantPwdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ApplicantSecretWord SQL.
		updateApplicantSecretWordSQL = new StringBuffer();
		updateApplicantSecretWordSQL.append("UPDATE APPLICANT ");
		updateApplicantSecretWordSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateApplicantSecretWordSQL.append("     SECRETWORD = ^, ");
		updateApplicantSecretWordSQL.append("     AUDITORID = ^, ");
		updateApplicantSecretWordSQL.append("     AUDITTIMESTAMP = ^ ");
		updateApplicantSecretWordSQL.append("WHERE APPLICANTID = ^ ");
		updateApplicantSecretWordSQL.append("AND   NOOFCHANGES = ^ ");
    // Get archive Applicant SQL.
    archiveApplicantSQL = new StringBuffer();
    archiveApplicantSQL.append("UPDATE APPLICANT ");
    archiveApplicantSQL.append("SET ARCHIVED = TRUE, ");
    archiveApplicantSQL.append("    AUDITORID = ^, ");
    archiveApplicantSQL.append("    AUDITTIMESTAMP = ^, ");
    archiveApplicantSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    archiveApplicantSQL.append("WHERE APPLICANTID = ^ ");
    archiveApplicantSQL.append("AND   NOOFCHANGES = ^ ");
    // Get unarchive Applicant SQL.
    unarchiveApplicantSQL = new StringBuffer();
    unarchiveApplicantSQL.append("UPDATE APPLICANT ");
    unarchiveApplicantSQL.append("SET ARCHIVED = FALSE, ");
    unarchiveApplicantSQL.append("    HASBEENUNARCHIVED = TRUE, ");
    unarchiveApplicantSQL.append("    COMPLIANT = FALSE, ");
    unarchiveApplicantSQL.append("    RECENTLYCOMPLIANT = FALSE, ");
    unarchiveApplicantSQL.append("    AUDITORID = ^, ");
    unarchiveApplicantSQL.append("    AUDITTIMESTAMP = ^, ");
    unarchiveApplicantSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    unarchiveApplicantSQL.append("WHERE APPLICANTID = ^ ");
    unarchiveApplicantSQL.append("AND   NOOFCHANGES = ^ ");
    // Get compliant Applicant SQL.
    compliantApplicantSQL = new StringBuffer();
    compliantApplicantSQL.append("UPDATE APPLICANT ");
    compliantApplicantSQL.append("SET COMPLIANT = ^, ");
    compliantApplicantSQL.append("    RECENTLYCOMPLIANT = ^, ");
    compliantApplicantSQL.append("    ARCHIVED = FALSE, ");
    compliantApplicantSQL.append("    HASBEENUNARCHIVED = FALSE, ");
    compliantApplicantSQL.append("    AUDITORID = ^, ");
    compliantApplicantSQL.append("    AUDITTIMESTAMP = ^, ");
    compliantApplicantSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    compliantApplicantSQL.append("WHERE APPLICANTID = ^ ");
    compliantApplicantSQL.append("AND   NOOFCHANGES = ^ ");
    // Get delete Applicant SQL.
    deleteApplicantSQL = new StringBuffer();
    deleteApplicantSQL.append("UPDATE APPLICANT ");
    deleteApplicantSQL.append("SET ACTIVE = FALSE, ");
    deleteApplicantSQL.append("    AUDITORID = ^, ");
    deleteApplicantSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteApplicantSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    deleteApplicantSQL.append("WHERE APPLICANTID = ^ ");
    deleteApplicantSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Applicants SQL.
		selectApplicantsSQL = new StringBuffer();
		selectApplicantsSQL.append("SELECT A.APPLICANTID, ");
		selectApplicantsSQL.append("       A.AGENCYID, ");
		selectApplicantsSQL.append("       A.FIRSTNAME, ");
		selectApplicantsSQL.append("       A.LASTNAME, ");
		selectApplicantsSQL.append("       A.EMAILADDRESS, ");
		selectApplicantsSQL.append("       A.LOGIN, ");
		selectApplicantsSQL.append("       A.PWD, ");
		selectApplicantsSQL.append("       A.PWDHINT, ");
		selectApplicantsSQL.append("       A.SECRETWORD, ");
		selectApplicantsSQL.append("       A.SHOWPAGEHELP, ");
		selectApplicantsSQL.append("       A.REFERENCE, ");
		selectApplicantsSQL.append("       A.PROFESSIONALREFERENCE, ");
		selectApplicantsSQL.append("       A.DATEOFBIRTH, ");
		selectApplicantsSQL.append("       A.NINUMBER, ");
		selectApplicantsSQL.append("       A.PHOTOFILENAME, ");
		selectApplicantsSQL.append("       A.GENDER, ");
		selectApplicantsSQL.append("       A.LIMITEDCOMPANYNAME, ");
		selectApplicantsSQL.append("       A.HIDEMONEY, ");
		selectApplicantsSQL.append("       A.CANTOGGLEHIDEMONEY, ");
		selectApplicantsSQL.append("       A.ADDRESS1, ");
		selectApplicantsSQL.append("       A.ADDRESS2, ");
		selectApplicantsSQL.append("       A.ADDRESS3, ");
		selectApplicantsSQL.append("       A.ADDRESS4, ");
		selectApplicantsSQL.append("       A.POSTALCODE, ");
		selectApplicantsSQL.append("       A.COUNTRYID, ");
		selectApplicantsSQL.append("       A.TELEPHONENUMBER, ");
		selectApplicantsSQL.append("       A.MOBILENUMBER, ");
		selectApplicantsSQL.append("       A.CREATIONTIMESTAMP, ");
		selectApplicantsSQL.append("       A.AUDITORID, ");
		selectApplicantsSQL.append("       A.AUDITTIMESTAMP, ");
		selectApplicantsSQL.append("       A.ACTIVE, ");
		selectApplicantsSQL.append("       A.NOOFCHANGES, ");
		selectApplicantsSQL.append("       A.VARICELLAFILENAME, ");
		selectApplicantsSQL.append("       A.HEPBFILENAME, ");
		selectApplicantsSQL.append("       A.TBFILENAME, ");
		selectApplicantsSQL.append("       A.MMRX2FILENAME, ");
		selectApplicantsSQL.append("       A.MMRFILENAME, ");
		selectApplicantsSQL.append("       A.PERFORMANCEEVALUATIONDATE, ");
		selectApplicantsSQL.append("       A.PERFORMANCEEVALUATION, ");
		selectApplicantsSQL.append("       A.ARCHIVED, ");
		selectApplicantsSQL.append("       A.COMPLIANT, ");
		selectApplicantsSQL.append("       A.REFERENCE2FILENAME, ");
		selectApplicantsSQL.append("       A.REFERENCE2DATE, ");
		selectApplicantsSQL.append("       A.REFERENCE2STATUS, ");
		selectApplicantsSQL.append("       A.REFERENCE2, ");
		selectApplicantsSQL.append("       A.REFERENCE1FILENAME, ");
		selectApplicantsSQL.append("       A.REFERENCE1DATE, ");
		selectApplicantsSQL.append("       A.REFERENCE1STATUS, ");
		selectApplicantsSQL.append("       A.REFERENCE1, ");
		selectApplicantsSQL.append("       A.CVFILENAME, ");
		selectApplicantsSQL.append("       A.OVERSEASPOLICECLEARANCE, ");
		selectApplicantsSQL.append("       A.DEGREEDETAIL, ");
		selectApplicantsSQL.append("       A.DEGREE, ");
		selectApplicantsSQL.append("       A.BIRTHCERTIFICATEFILENAME, ");
		selectApplicantsSQL.append("       A.BIRTHCERTIFICATE, ");
    selectApplicantsSQL.append("       A.PROOFOFADDRESS1FILENAME, ");
    selectApplicantsSQL.append("       A.PROOFOFADDRESS1, ");
    selectApplicantsSQL.append("       A.PROOFOFADDRESS2FILENAME, ");
    selectApplicantsSQL.append("       A.PROOFOFADDRESS2, ");
		selectApplicantsSQL.append("       A.NINUMBERSTATUS, ");
		selectApplicantsSQL.append("       A.FITTOWORKFILENAME, ");
		selectApplicantsSQL.append("       A.FITTOWORKEXPIRYDATE, ");
		selectApplicantsSQL.append("       A.IDDOCUMENTFILENAME, ");
		selectApplicantsSQL.append("       A.IDDOCUMENTEXPIRYDATE, ");
		selectApplicantsSQL.append("       A.IDDOCUMENTNUMBER, ");
		selectApplicantsSQL.append("       A.TRAININGFILENAME, ");
		selectApplicantsSQL.append("       A.TRAININGEXPIRYDATE, ");
		selectApplicantsSQL.append("       A.CRBFILENAME, ");
		selectApplicantsSQL.append("       A.CRBEXPIRYDATE, ");
		selectApplicantsSQL.append("       A.CRBISSUEDATE, ");
		selectApplicantsSQL.append("       A.HPCFILENAME, ");
		selectApplicantsSQL.append("       A.HPCEXPIRYDATE, ");
		selectApplicantsSQL.append("       A.HPCNUMBER, ");
		selectApplicantsSQL.append("       A.INTERVIEWDATE, ");
    selectApplicantsSQL.append("       A.ASSESSMENT12WEEK, ");
    selectApplicantsSQL.append("       A.ASSESSMENT12WEEKDATE, ");
    selectApplicantsSQL.append("       A.AVAILABILITYDATE, ");
    selectApplicantsSQL.append("       A.ARRIVALINCOUNTRYDATE, ");
    selectApplicantsSQL.append("       A.VISAEXPIRYDATE, ");
    selectApplicantsSQL.append("       A.AREAOFSPECIALITYID, ");
    selectApplicantsSQL.append("       A.AREAOFSPECIALITYID2, ");
    selectApplicantsSQL.append("       A.GEOGRAPHICALREGIONID, ");
    selectApplicantsSQL.append("       A.DISCIPLINECATEGORYID, ");
    selectApplicantsSQL.append("       A.CLIENTGROUP, ");
    selectApplicantsSQL.append("       A.DRIVINGLICENSE, ");
    selectApplicantsSQL.append("       A.DRIVINGLICENSEEXPIRYDATE, ");
    selectApplicantsSQL.append("       A.FITTOWORKSTATUS, ");
    selectApplicantsSQL.append("       A.HASBEENUNARCHIVED, ");
    selectApplicantsSQL.append("       A.RECENTLYCOMPLIANT, ");
    selectApplicantsSQL.append("       A.CURRENTLYWORKING, ");
    selectApplicantsSQL.append("       A.VATCHARGEABLE, ");
    selectApplicantsSQL.append("       A.BANKNAME, ");
    selectApplicantsSQL.append("       A.BANKSORTCODE, ");
    selectApplicantsSQL.append("       A.BANKACCOUNTNAME, ");
    selectApplicantsSQL.append("       A.BANKACCOUNTNUMBER, ");
    selectApplicantsSQL.append("       A.IDDOCUMENT, ");
    selectApplicantsSQL.append("       A.LANGUAGECOMPETENCY, ");
    selectApplicantsSQL.append("       A.FITTOWORKISSUEDBY, ");
    selectApplicantsSQL.append("       A.IVSEPPFILENAME, ");
    selectApplicantsSQL.append("       A.MANUALHANDLINGTRAINING, ");
    selectApplicantsSQL.append("       A.BASICLIFESUPPORTTRAINING, ");
    selectApplicantsSQL.append("       A.ELEARNINGTRAINING, ");
    selectApplicantsSQL.append("       A.POVATRAINING, ");
    selectApplicantsSQL.append("       A.NEONATALLIFESUPPORTTRAINING, ");
    selectApplicantsSQL.append("       A.AHPREGISTRATIONTYPE, ");
    selectApplicantsSQL.append("       A.HPCLASTCHECKEDDATE, ");
    selectApplicantsSQL.append("       A.HPCALERTNOTIFICATION, ");
    selectApplicantsSQL.append("       A.PAEDIATRICLIFESUPPORTFILENAME, ");
    selectApplicantsSQL.append("       A.PAEDIATRICLIFESUPPORTISSUEDDATE, ");
    selectApplicantsSQL.append("       A.VISATYPE, ");
    selectApplicantsSQL.append("       A.CRBDISCLOSURENUMBER, ");
    selectApplicantsSQL.append("       A.CHECKLISTCREATEDTIME, ");
    selectApplicantsSQL.append("       A.ENGLISHTESTCERTIFICATEFILENAME, ");
    selectApplicantsSQL.append("       A.NHSSTAFFNAME, ");
    selectApplicantsSQL.append("       A.ORIGINALAGENCYID, ");
    selectApplicantsSQL.append("       A.REGISTEREDWITHDBSDATE, ");
    // NEW -->
    selectApplicantsSQL.append("       A.DBSRENEWALDATE, ");
    selectApplicantsSQL.append("       A.DBSFILENAME, ");
    // <-- NEW
    selectApplicantsSQL.append("       AOS.NAME AS AREAOFSPECIALITYNAME, ");
    selectApplicantsSQL.append("       AOS2.NAME AS AREAOFSPECIALITYNAME2, ");
    selectApplicantsSQL.append("       GR.NAME AS GEOGRAPHICALREGIONNAME, ");
    selectApplicantsSQL.append("       DC.NAME AS DISCIPLINECATEGORYNAME, ");
    selectApplicantsSQL.append("       R.NAME AS REGULATORNAME, ");
    selectApplicantsSQL.append("       R.CODE AS REGULATORCODE, ");
    selectApplicantsSQL.append("       ID.NAME AS IDDOCUMENTNAME, ");
    selectApplicantsSQL.append("       ID.REQUIRESVISA, ");
    selectApplicantsSQL.append("       VT.NAME AS VISATYPENAME, ");
		selectApplicantsSQL.append("       C.NAME AS COUNTRYNAME ");
		selectApplicantsSQL.append("FROM APPLICANT A ");
    selectApplicantsSQL.append("LEFT OUTER JOIN COUNTRY C ON (C.COUNTRYID = A.COUNTRYID) ");
    selectApplicantsSQL.append("LEFT OUTER JOIN AREAOFSPECIALITY AOS ON (AOS.AREAOFSPECIALITYID = A.AREAOFSPECIALITYID) ");
    selectApplicantsSQL.append("LEFT OUTER JOIN AREAOFSPECIALITY AOS2 ON (AOS2.AREAOFSPECIALITYID = A.AREAOFSPECIALITYID2) ");
    selectApplicantsSQL.append("LEFT OUTER JOIN GEOGRAPHICALREGION GR ON (GR.GEOGRAPHICALREGIONID = A.GEOGRAPHICALREGIONID) ");
    selectApplicantsSQL.append("LEFT OUTER JOIN DISCIPLINECATEGORY DC ON (DC.DISCIPLINECATEGORYID = A.DISCIPLINECATEGORYID) ");
    selectApplicantsSQL.append("LEFT OUTER JOIN REGULATOR R ON (DC.REGULATORID = R.REGULATORID) ");
    // NEW -->     ************** Colums do NOT have ID suffix ********************
    selectApplicantsSQL.append("LEFT OUTER JOIN IDDOCUMENT ID ON (ID.IDDOCUMENTID = A.IDDOCUMENT) ");
    selectApplicantsSQL.append("LEFT OUTER JOIN VISATYPE VT ON (VT.VISATYPEID = A.VISATYPE) ");
    // <-- NEW
		// Get select Applicant SQL.
		selectApplicantSQL = new StringBuffer(selectApplicantsSQL);
		selectApplicantSQL.append("WHERE A.APPLICANTID = ^ ");
		// Get select Applicants for Agency SQL. Never select any ARCHIVED data.
		selectApplicantsForAgencySQL = new StringBuffer(selectApplicantsSQL);
    selectApplicantsForAgencySQL.append("WHERE A.AGENCYID = ^ ");
    selectApplicantsForAgencySQL.append("AND A.ARCHIVED = FALSE ");
		// Get select Active Applicants for Agency SQL.
		selectActiveApplicantsForAgencySQL = new StringBuffer(selectApplicantsForAgencySQL);
		selectActiveApplicantsForAgencySQL.append("AND A.ACTIVE = TRUE ");
    // Get select Applicants (for Agency) To Copy SQL.
    selectApplicantsToCopySQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectApplicantsToCopySQL.append("AND NOT EXISTS ");
    selectApplicantsToCopySQL.append("( ");
    selectApplicantsToCopySQL.append("   SELECT NULL");
    selectApplicantsToCopySQL.append("   FROM APPLICANT B");
    selectApplicantsToCopySQL.append("   WHERE B.FIRSTNAME = A.FIRSTNAME ");
    selectApplicantsToCopySQL.append("   AND   B.LASTNAME = A.LASTNAME ");
    selectApplicantsToCopySQL.append("   AND   B.DATEOFBIRTH = A.DATEOFBIRTH ");
    selectApplicantsToCopySQL.append("   AND   B.NINUMBER = A.NINUMBER ");
    selectApplicantsToCopySQL.append("   AND   B.AGENCYID = ^ ");
    selectApplicantsToCopySQL.append(") ");
    selectApplicantsToCopySQL.append("ORDER BY A.LASTNAME, A.FIRSTNAME ");
    // Get select Applicants for NHS Staff Name SQL.
    selectApplicantsForNhsStaffNameSQL = new StringBuffer(selectApplicantsForAgencySQL);
    selectApplicantsForNhsStaffNameSQL.append("AND NHSSTAFFNAME = ^ ");
    // Get select Applicants for Agency for LastName starting with letters in supplied list SQL.
    selectApplicantsForAgencyInLastNameGroupSQL = addInLastNameGroupSQL(selectApplicantsForAgencySQL);
    // Get select Active Applicants for Agency for LastName starting with letters in supplied list SQL.
    selectActiveApplicantsForAgencyInLastNameGroupSQL = new StringBuffer(selectApplicantsForAgencyInLastNameGroupSQL);
    selectActiveApplicantsForAgencyInLastNameGroupSQL.append("AND A.ACTIVE = TRUE ");
    // Get select NEW Active Applicants for Agency.
    selectActiveApplicantsForAgencyNewSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyNewSQL.append("AND (( ");
    selectActiveApplicantsForAgencyNewSQL.append("    A.INTERVIEWDATE IS NULL ");
//    selectActiveApplicantsForAgencyNewSQL.append("AND A.CRBEXPIRYDATE IS NULL ");
//    selectActiveApplicantsForAgencyNewSQL.append("AND A.CRBISSUEDATE  IS NULL ");
    selectActiveApplicantsForAgencyNewSQL.append(") ");
    selectActiveApplicantsForAgencyNewSQL.append("OR ( ");
    selectActiveApplicantsForAgencyNewSQL.append("    A.INTERVIEWDATE >= ^ ");
    selectActiveApplicantsForAgencyNewSQL.append(")) ");
    selectActiveApplicantsForAgencyNewSQL.append("ORDER BY A.LASTNAME, A.FIRSTNAME ");
    // Get select Active Applicants for Agency with CRB about to expire.
    selectActiveApplicantsForAgencyCrbAboutToExpireSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyCrbAboutToExpireSQL.append("AND A.CRBEXPIRYDATE IS NOT NULL ");
    selectActiveApplicantsForAgencyCrbAboutToExpireSQL.append("AND A.CRBEXPIRYDATE < ^ ");
    selectActiveApplicantsForAgencyCrbAboutToExpireSQL.append("ORDER BY A.CRBEXPIRYDATE DESC ");
    // Get select Active Applicants for Agency with DBS about to expire.
    selectActiveApplicantsForAgencyDbsAboutToExpireSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyDbsAboutToExpireSQL.append("AND A.DBSRENEWALDATE IS NOT NULL ");
    selectActiveApplicantsForAgencyDbsAboutToExpireSQL.append("AND A.DBSRENEWALDATE < ^ ");
    selectActiveApplicantsForAgencyDbsAboutToExpireSQL.append("ORDER BY A.DBSRENEWALDATE DESC ");
    // Get select Active Applicants for Agency with FitToWork about to expire.
    selectActiveApplicantsForAgencyFitToWorkAboutToExpireSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyFitToWorkAboutToExpireSQL.append("AND A.FITTOWORKEXPIRYDATE IS NOT NULL ");
    selectActiveApplicantsForAgencyFitToWorkAboutToExpireSQL.append("AND A.FITTOWORKEXPIRYDATE < ^ ");
    selectActiveApplicantsForAgencyFitToWorkAboutToExpireSQL.append("ORDER BY A.FITTOWORKEXPIRYDATE DESC ");
    // Get select Active Applicants for Agency with Hpc about to expire.
    selectActiveApplicantsForAgencyHpcAboutToExpireSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyHpcAboutToExpireSQL.append("AND A.HPCEXPIRYDATE IS NOT NULL ");
    selectActiveApplicantsForAgencyHpcAboutToExpireSQL.append("AND A.HPCEXPIRYDATE < ^ ");
    selectActiveApplicantsForAgencyHpcAboutToExpireSQL.append("ORDER BY A.HPCEXPIRYDATE DESC ");
    // Get select Active Applicants for Agency with IdDocument about to expire.
    selectActiveApplicantsForAgencyIdDocumentAboutToExpireSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyIdDocumentAboutToExpireSQL.append("AND A.IDDOCUMENTEXPIRYDATE IS NOT NULL ");
    selectActiveApplicantsForAgencyIdDocumentAboutToExpireSQL.append("AND A.IDDOCUMENTEXPIRYDATE < ^ ");
    selectActiveApplicantsForAgencyIdDocumentAboutToExpireSQL.append("ORDER BY A.IDDOCUMENTEXPIRYDATE DESC ");
    // Get select Active Applicants for Agency with Training about to expire.
    selectActiveApplicantsForAgencyTrainingAboutToExpireSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyTrainingAboutToExpireSQL.append("AND A.TRAININGEXPIRYDATE IS NOT NULL ");
    selectActiveApplicantsForAgencyTrainingAboutToExpireSQL.append("AND A.TRAININGEXPIRYDATE < ^ ");
    selectActiveApplicantsForAgencyTrainingAboutToExpireSQL.append("ORDER BY A.TRAININGEXPIRYDATE DESC ");
    // Get select Active Applicants for Agency with Visa about to expire.
    selectActiveApplicantsForAgencyVisaAboutToExpireSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyVisaAboutToExpireSQL.append("AND A.VISAEXPIRYDATE IS NOT NULL ");
    selectActiveApplicantsForAgencyVisaAboutToExpireSQL.append("AND A.VISAEXPIRYDATE < ^ ");
    selectActiveApplicantsForAgencyVisaAboutToExpireSQL.append("ORDER BY A.VISAEXPIRYDATE DESC ");
    // Get select Active Applicants for Agency with Reference 1 Date set but NO Reference 1 File.
    selectActiveApplicantsForAgencyReference1NotSatisfiedSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyReference1NotSatisfiedSQL.append("AND A.REFERENCE1DATE IS NOT NULL ");
    selectActiveApplicantsForAgencyReference1NotSatisfiedSQL.append("AND A.REFERENCE1DATE < ^ ");
    selectActiveApplicantsForAgencyReference1NotSatisfiedSQL.append("AND A.REFERENCE1FILENAME IS NULL ");
    selectActiveApplicantsForAgencyReference1NotSatisfiedSQL.append("ORDER BY A.REFERENCE1DATE DESC ");
    // Get select Active Applicants for Agency with Reference 2 Date set but NO Reference 2 File.
    selectActiveApplicantsForAgencyReference2NotSatisfiedSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyReference2NotSatisfiedSQL.append("AND A.REFERENCE2DATE IS NOT NULL ");
    selectActiveApplicantsForAgencyReference2NotSatisfiedSQL.append("AND A.REFERENCE2DATE < ^ ");
    selectActiveApplicantsForAgencyReference2NotSatisfiedSQL.append("AND A.REFERENCE2FILENAME IS NULL ");
    selectActiveApplicantsForAgencyReference2NotSatisfiedSQL.append("ORDER BY A.REFERENCE2DATE DESC ");
    // Get select Active Applicants for Agency with Driving License about to expire.
    selectActiveApplicantsForAgencyDrivingLicenseAboutToExpireSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyDrivingLicenseAboutToExpireSQL.append("AND A.DRIVINGLICENSEEXPIRYDATE IS NOT NULL ");
    selectActiveApplicantsForAgencyDrivingLicenseAboutToExpireSQL.append("AND A.DRIVINGLICENSEEXPIRYDATE < ^ ");
    selectActiveApplicantsForAgencyDrivingLicenseAboutToExpireSQL.append("ORDER BY A.DRIVINGLICENSEEXPIRYDATE DESC ");
    // Get select Active Applicants for Agency that have been Unarchived.
    selectActiveApplicantsForAgencyUnarchivedSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyUnarchivedSQL.append("AND A.HASBEENUNARCHIVED = TRUE ");
    selectActiveApplicantsForAgencyUnarchivedSQL.append("ORDER BY A.LASTNAME ");
    // Get select Active Applicants for Agency that are Compliant but NOT Acknowledged.
    selectActiveApplicantsForAgencyRecentlyCompliantSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyRecentlyCompliantSQL.append("AND A.RECENTLYCOMPLIANT = TRUE ");
    selectActiveApplicantsForAgencyRecentlyCompliantSQL.append("ORDER BY A.LASTNAME ");
    // Get select Active Applicants for Agency that are Recent Prospects.
    selectActiveApplicantsForAgencyRecentProspectSQL = new StringBuffer(selectApplicantsSQL);
    selectActiveApplicantsForAgencyRecentProspectSQL.append("JOIN CONSULTANT CON ");
    selectActiveApplicantsForAgencyRecentProspectSQL.append("  ON  CON.CONSULTANTID = A.AUDITORID ");
    selectActiveApplicantsForAgencyRecentProspectSQL.append("  AND CON.AGENCYID = A.AGENCYID ");
    selectActiveApplicantsForAgencyRecentProspectSQL.append("WHERE A.AGENCYID = ^ ");
    selectActiveApplicantsForAgencyRecentProspectSQL.append("AND A.ARCHIVED = FALSE ");
    selectActiveApplicantsForAgencyRecentProspectSQL.append("AND A.ACTIVE = TRUE ");
    selectActiveApplicantsForAgencyRecentProspectSQL.append("AND CON.LOGIN = ^ ");
    selectActiveApplicantsForAgencyRecentProspectSQL.append("ORDER BY A.LASTNAME ");
    // Get select Active Applicants for Agency and not for Booking SQL.
		selectActiveApplicantsForAgencyAndNotForBookingGradeSQL = addAndNotForBookingGradeSQL(selectActiveApplicantsForAgencySQL);
    selectActiveApplicantsForAgencyAndNotForBookingGradeInLastNameGroupSQL = addInLastNameGroupSQL(selectActiveApplicantsForAgencyAndNotForBookingGradeSQL);
		// Get select Applicant for Login SQL.
		selectApplicantForLoginSQL = new StringBuffer(selectActiveApplicantsForAgencySQL);
		selectApplicantForLoginSQL.append("AND A.LOGIN = ^ ");
    // Add Order By clauses.
		selectActiveApplicantsForAgencySQL.append("ORDER BY A.LASTNAME, A.FIRSTNAME ");
    selectActiveApplicantsForAgencyAndNotForBookingGradeSQL.append("ORDER BY A.LASTNAME, A.FIRSTNAME ");
    selectActiveApplicantsForAgencyAndNotForBookingGradeInLastNameGroupSQL.append("ORDER BY A.LASTNAME, A.FIRSTNAME ");
    selectApplicantsForAgencyInLastNameGroupSQL.append("ORDER BY A.LASTNAME, A.FIRSTNAME ");
    selectActiveApplicantsForAgencyInLastNameGroupSQL.append("ORDER BY A.LASTNAME, A.FIRSTNAME ");
    // Applicant Client Bookings: 
    // A list of Booking date ranges for the Applicant/Client with  the most recent date range first.
    // To get a list of date ranges when the Applicant had worked at the Client before today.
    selectApplicantClientBookingsSQL = new StringBuffer();
    selectApplicantClientBookingsSQL.append("SELECT B.BOOKINGID, ");
    selectApplicantClientBookingsSQL.append("       B.MINBOOKINGDATE, ");
    selectApplicantClientBookingsSQL.append("       B.MAXBOOKINGDATE ");
    selectApplicantClientBookingsSQL.append("FROM BOOKINGGRADEAPPLICANT BGA ");
    selectApplicantClientBookingsSQL.append("     JOIN BOOKINGGRADE BG ");
    selectApplicantClientBookingsSQL.append("     ON BG.BOOKINGGRADEID = BGA.BOOKINGGRADEID ");
    selectApplicantClientBookingsSQL.append("     JOIN BOOKING B ");
    selectApplicantClientBookingsSQL.append("     ON B.BOOKINGID = BG.BOOKINGID ");
    selectApplicantClientBookingsSQL.append("     JOIN LOCATION L ");
    selectApplicantClientBookingsSQL.append("     ON L.LOCATIONID = B.LOCATIONID ");
    selectApplicantClientBookingsSQL.append("     JOIN SITE S ");
    selectApplicantClientBookingsSQL.append("     ON S.SITEID = L.SITEID ");
    selectApplicantClientBookingsSQL.append("     JOIN CLIENT C ");
    selectApplicantClientBookingsSQL.append("     ON C.CLIENTID = S.CLIENTID ");
    selectApplicantClientBookingsSQL.append("WHERE BGA.APPLICANTID = ^ ");
    selectApplicantClientBookingsSQL.append("AND C.CLIENTID = ^ ");
    selectApplicantClientBookingsSQL.append("AND BG.AGENCYID = ^ ");
    selectApplicantClientBookingsSQL.append("AND B.MINBOOKINGDATE < ^ ");
    selectApplicantClientBookingsSQL.append("ORDER BY B.MINBOOKINGDATE DESC ");
}
  /**
   * Adds the SQL for the InLastNameGroup part of the full statement.
   * Eg. Adds to selectApplicantsForAgencySQL to become selectApplicantsForAgencyInLastNameGroupSQL.
   *  
   * @param sourceSQL The original SQL to be added to.
   */
  private static StringBuffer addInLastNameGroupSQL(StringBuffer sourceSQL)
  {
    StringBuffer targetSQL = new StringBuffer(sourceSQL);
    targetSQL.append("AND SUBSTRING(UPPER(A.LASTNAME), 1, 1) IN (^) ");
    return targetSQL;
  }
  
  /**
   * Adds the SQL for the AndNotForBookingGrade part of the full statement.
   * Eg. Adds to selectActiveApplicantsForAgencySQL to become selectActiveApplicantsForAgencyAndNotForBookingGradeSQL.
   *  
   * @param sourceSQL The original SQL to be added to.
   */
  private static StringBuffer addAndNotForBookingGradeSQL(StringBuffer sourceSQL)
  {
    StringBuffer targetSQL = new StringBuffer(sourceSQL);
    targetSQL.append("AND NOT EXISTS ");
    targetSQL.append("( ");
    targetSQL.append(" SELECT NULL ");
    targetSQL.append(" FROM BOOKINGGRADEAPPLICANT BGA ");
    targetSQL.append(" WHERE BGA.BOOKINGGRADEID = ^ ");
    targetSQL.append(" AND BGA.ACTIVE = TRUE ");
    targetSQL.append(" AND A.APPLICANTID = BGA.APPLICANTID ");
    targetSQL.append(") ");
    return targetSQL;
  }
  
	public List<Applicant> getApplicantsForAgency(Integer clientId, boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveApplicantsForAgencySQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectApplicantsForAgencySQL.toString());
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Applicant.class.getName());
		
	}

  public List<Applicant> getApplicantsForNhsStaffName(Integer agencyId, String nhsStaffName) 
  {
    StringBuffer sql = new StringBuffer(selectApplicantsForNhsStaffNameSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, nhsStaffName);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  // NEW -->
  public List<Applicant> getApplicantsForAgencySearch(Integer clientId, ApplicantSearchParameters applicantSearchParameters, boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    StringBuffer searchCriteriaOR = new StringBuffer();
    StringBuffer searchCriteriaAND = new StringBuffer();
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectApplicantsSQL.toString());
      sql.append("WHERE A.AGENCYID = ^ ");
      sql.append("AND A.ACTIVE = TRUE ");
    }
    else 
    {
      sql = new StringBuffer(selectApplicantsSQL.toString());
      sql.append("WHERE A.AGENCYID = ^ ");
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, clientId);
    if (applicantSearchParameters.getLastName() != null)
    {
      if (applicantSearchParameters.getLastName().contains("%"))
      {
        addSearchCriteria(applicantSearchParameters.getLastNameOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
            applicantSearchParameters.getLastNameOperator(), 
            "LOWER(LASTNAME) LIKE LOWER('" + applicantSearchParameters.getLastName() + "') ");
      }
      else
      {
        if (applicantSearchParameters.getLastName().contains("~"))
        {
          addSearchCriteria(applicantSearchParameters.getLastNameOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
              applicantSearchParameters.getLastNameOperator(), 
              "SOUNDEX(LASTNAME) = SOUNDEX('" + applicantSearchParameters.getLastName().replaceAll("~", "") + "') ");
        }
        else
        {
          addSearchCriteria(applicantSearchParameters.getLastNameOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
              applicantSearchParameters.getLastNameOperator(), 
              "LOWER(LASTNAME) = LOWER('" + applicantSearchParameters.getLastName() + "') ");
        }
      }
    }
    if (applicantSearchParameters.getPerformanceEvaluation() != null)
    {
      nullableColumn(applicantSearchParameters.getPerformanceEvaluationOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getPerformanceEvaluationOperator(), 
          applicantSearchParameters.getPerformanceEvaluation(), 
          "A.PERFORMANCEEVALUATION");
    }
    if (applicantSearchParameters.getAssessment12Week() != null)
    {
      nullableColumn(applicantSearchParameters.getAssessment12WeekOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getAssessment12WeekOperator(), 
          applicantSearchParameters.getAssessment12Week(), 
          "A.ASSESSMENT12WEEK");
    }
    if (applicantSearchParameters.getInterviewed() != null)
    {
      nullableColumn(applicantSearchParameters.getInterviewedOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getInterviewedOperator(), 
          applicantSearchParameters.getInterviewed(), 
          "A.INTERVIEWDATE");
    }
    if (applicantSearchParameters.getHasVaricellaDocument() != null)
    {
      nullableColumn(applicantSearchParameters.getHasVaricellaDocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasVaricellaDocumentOperator(), 
          applicantSearchParameters.getHasVaricellaDocument(), 
          "A.VARICELLAFILENAME");
    }
    if (applicantSearchParameters.getHasHepbDocument() != null)
    {
      nullableColumn(applicantSearchParameters.getHasHepbDocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasHepbDocumentOperator(), 
          applicantSearchParameters.getHasHepbDocument(), 
          "A.HEPBFILENAME");
    }
    if (applicantSearchParameters.getHasTuberculosisDocument() != null)
    {
      nullableColumn(applicantSearchParameters.getHasTuberculosisDocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasTuberculosisDocumentOperator(), 
          applicantSearchParameters.getHasTuberculosisDocument(), 
          "A.TBFILENAME");
    }
    if (applicantSearchParameters.getHasMMRX2Document() != null)
    {
      nullableColumn(applicantSearchParameters.getHasMMRX2DocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasMMRX2DocumentOperator(), 
          applicantSearchParameters.getHasMMRX2Document(), 
          "A.MMRX2FILENAME");
    }
    if (applicantSearchParameters.getHasMMRDocument() != null)
    {
      nullableColumn(applicantSearchParameters.getHasMMRDocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasMMRDocumentOperator(), 
          applicantSearchParameters.getHasMMRDocument(), 
          "A.MMRFILENAME");
    }
    if (applicantSearchParameters.getHasFitToWorkDocument() != null)
    {
      nullableColumn(applicantSearchParameters.getHasFitToWorkDocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasFitToWorkDocumentOperator(), 
          applicantSearchParameters.getHasFitToWorkDocument(), 
          "A.FITTOWORKFILENAME");
    }
    if (applicantSearchParameters.getHasCRBDocument() != null)
    {
      nullableColumn(applicantSearchParameters.getHasCRBDocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasCRBDocumentOperator(), 
          applicantSearchParameters.getHasCRBDocument(), 
          "A.CRBFILENAME");
    }
    if (applicantSearchParameters.getHasHPCDocument() != null)
    {
      nullableColumn(applicantSearchParameters.getHasHPCDocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasHPCDocumentOperator(), 
          applicantSearchParameters.getHasHPCDocument(), 
          "A.HPCFILENAME");
    }
    if (applicantSearchParameters.getHasTrainingCertificate() != null)
    {
      nullableColumn(applicantSearchParameters.getHasTrainingCertificateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasTrainingCertificateOperator(), 
          applicantSearchParameters.getHasTrainingCertificate(), 
          "A.TRAININGFILENAME");
    }
    if (applicantSearchParameters.getHasIdDocument() != null)
    {
      nullableColumn(applicantSearchParameters.getHasIdDocumentOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasIdDocumentOperator(), 
          applicantSearchParameters.getHasIdDocument(), 
          "A.IDDOCUMENTFILENAME");
    }
    if (applicantSearchParameters.getHasProofOfAddress1() != null)
    {
      booleanColumn(applicantSearchParameters.getHasProofOfAddress1Operator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasProofOfAddress1Operator(), 
          applicantSearchParameters.getHasProofOfAddress1(), 
          "A.PROOFOFADDRESS1");
    }
    if (applicantSearchParameters.getHasProofOfAddress2() != null)
    {
      booleanColumn(applicantSearchParameters.getHasProofOfAddress2Operator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasProofOfAddress2Operator(), 
          applicantSearchParameters.getHasProofOfAddress2(), 
          "A.PROOFOFADDRESS2");
    }
    if (applicantSearchParameters.getHasBirthCertificate() != null)
    {
      booleanColumn(applicantSearchParameters.getHasBirthCertificateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasBirthCertificateOperator(), 
          applicantSearchParameters.getHasBirthCertificate(), 
          "A.BIRTHCERTIFICATE");
    }
    if (applicantSearchParameters.getHasOverseasPoliceClearance() != null)
    {
      booleanColumn(applicantSearchParameters.getHasOverseasPoliceClearanceOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasOverseasPoliceClearanceOperator(), 
          applicantSearchParameters.getHasOverseasPoliceClearance(), 
          "A.OVERSEASPOLICECLEARANCE");
    }
    if (applicantSearchParameters.getHasDegree() != null)
    {
      booleanColumn(applicantSearchParameters.getHasDegreeOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHasDegreeOperator(), 
          applicantSearchParameters.getHasDegree(), 
          "A.DEGREE");
    }
    if (applicantSearchParameters.getIsArchived() != null)
    {
      booleanColumn(applicantSearchParameters.getIsArchivedOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getIsArchivedOperator(), 
          applicantSearchParameters.getIsArchived(), 
          "A.ARCHIVED");
    }
    if (applicantSearchParameters.getIsCompliant() != null)
    {
      booleanColumn(applicantSearchParameters.getIsCompliantOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getIsCompliantOperator(), 
          applicantSearchParameters.getIsCompliant(), 
          "A.COMPLIANT");
    }
    if (applicantSearchParameters.getAreaOfSpecialityId() == 0 && applicantSearchParameters.getAreaOfSpecialityId2() == 0)
    {
      // Neither Area of Speciality entered.
    }
    else
    {
      // One or both Area of Speciality has been eneterd.
      areaOfSpecialityColumn(applicantSearchParameters.getAreaOfSpecialityIdOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getAreaOfSpecialityId2Operator(), 
          applicantSearchParameters.getAreaOfSpecialityId(), 
          applicantSearchParameters.getAreaOfSpecialityId2());
    }
//    if (applicantSearchParameters.getAreaOfSpecialityId2() != 0)
//    {
//      integerColumn(applicantSearchParameters.getAreaOfSpecialityId2Operator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
//          applicantSearchParameters.getAreaOfSpecialityId2Operator(), 
//          applicantSearchParameters.getAreaOfSpecialityId2(), 
//          "A.AREAOFSPECIALITYID2");
//    }
    if (applicantSearchParameters.getGeographicalRegionId() != 0)
    {
      integerColumn(applicantSearchParameters.getGeographicalRegionIdOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getGeographicalRegionIdOperator(), 
          applicantSearchParameters.getGeographicalRegionId(), 
          "A.GEOGRAPHICALREGIONID");
    }
    if (applicantSearchParameters.getDisciplineCategoryId() != 0)
    {
      integerColumn(applicantSearchParameters.getDisciplineCategoryIdOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getDisciplineCategoryIdOperator(), 
          applicantSearchParameters.getDisciplineCategoryId(), 
          "A.DISCIPLINECATEGORYID");
    }
    if (applicantSearchParameters.getClientGroup() != 0)
    {
      integerColumn(applicantSearchParameters.getClientGroupOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getClientGroupOperator(), 
          applicantSearchParameters.getClientGroup(), 
          "A.CLIENTGROUP");
    }
    if (applicantSearchParameters.getNiNumberStatus() != 0)
    {
      integerColumn(applicantSearchParameters.getNiNumberStatusOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getNiNumberStatusOperator(), 
          applicantSearchParameters.getNiNumberStatus(), 
          "A.NINUMBERSTATUS");
    }
    if (applicantSearchParameters.getAvailabilityDate() != null)
    {
      dateColumn(applicantSearchParameters.getAvailabilityDateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getAvailabilityDateOperator(), 
          applicantSearchParameters.getAvailabilityDate(), 
          "A.AVAILABILITYDATE");
    }
    if (applicantSearchParameters.getVisaExpiryDate() != null)
    {
      dateColumn(applicantSearchParameters.getVisaExpiryDateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getVisaExpiryDateOperator(), 
          applicantSearchParameters.getVisaExpiryDate(), 
          "A.VISAEXPIRYDATE");
    }
    if (applicantSearchParameters.getIdDocumentExpiryDate() != null)
    {
      dateColumn(applicantSearchParameters.getIdDocumentExpiryDateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getIdDocumentExpiryDateOperator(), 
          applicantSearchParameters.getIdDocumentExpiryDate(), 
          "A.IDDOCUMENTEXPIRYDATE");
    }
    if (applicantSearchParameters.getCrbExpiryDate() != null)
    {
      dateColumn(applicantSearchParameters.getCrbExpiryDateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getCrbExpiryDateOperator(), 
          applicantSearchParameters.getCrbExpiryDate(), 
          "A.CRBEXPIRYDATE");
    }
    if (applicantSearchParameters.getHpcExpiryDate() != null)
    {
      dateColumn(applicantSearchParameters.getHpcExpiryDateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getHpcExpiryDateOperator(), 
          applicantSearchParameters.getHpcExpiryDate(), 
          "A.HPCEXPIRYDATE");
    }
    if (applicantSearchParameters.getTrainingExpiryDate() != null)
    {
      dateColumn(applicantSearchParameters.getTrainingExpiryDateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getTrainingExpiryDateOperator(), 
          applicantSearchParameters.getTrainingExpiryDate(), 
          "A.TRAININGEXPIRYDATE");
    }
    if (applicantSearchParameters.getFitToWorkExpiryDate() != null)
    {
      dateColumn(applicantSearchParameters.getFitToWorkExpiryDateOperator().equals(Constants.AND) ? searchCriteriaAND : searchCriteriaOR, 
          applicantSearchParameters.getFitToWorkExpiryDateOperator(), 
          applicantSearchParameters.getFitToWorkExpiryDate(), 
          "A.FITTOWORKEXPIRYDATE");
    }
    if (searchCriteriaAND.length() > 0)
    {
      // AND part must be added after 'AND '.
      sql.append(Constants.AND);
      sql.append(" ");
      sql.append(searchCriteriaAND.toString());
    }
    if (searchCriteriaOR.length() > 0)
    {
      // OR part must be added between 'AND (' and ')'.
      sql.append(Constants.AND);
      sql.append(" ( ");
      sql.append(searchCriteriaOR.toString());
      sql.append(" ) ");
    }
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
    
  }
  private void nullableColumn(StringBuffer searchCriteria, String operator, Boolean propertyValue, String columnName)
  {
    if (propertyValue)
    {
      addSearchCriteria(searchCriteria, operator, columnName + " IS NOT NULL ");
    }
    else
    {
      addSearchCriteria(searchCriteria, operator, columnName + " IS NULL ");
    }
  }
  private void booleanColumn(StringBuffer searchCriteria, String operator, Boolean propertyValue, String columnName)
  {
    if (propertyValue)
    {
      addSearchCriteria(searchCriteria, operator, columnName + " = true ");
    }
    else
    {
      addSearchCriteria(searchCriteria, operator, columnName + " = false ");
    }
  }
  private void integerColumn(StringBuffer searchCriteria, String operator, Integer propertyValue, String columnName)
  {
    addSearchCriteria(searchCriteria, operator, columnName + " = " + propertyValue + " ");
  }
  // SPECIAL CASE FOR THE TWO AREA OF SPECIALITY COLUMNS...
  private void areaOfSpecialityColumn(StringBuffer searchCriteria, String operator, Integer areaOfSpecialityId, Integer areaOfSpecialityId2)
  {
    if (searchCriteria.length() > 0)
    {
      searchCriteria.append(" ");
      searchCriteria.append("AND");
    }
    searchCriteria.append(" (");
    if (areaOfSpecialityId != 0)
    {
      if (areaOfSpecialityId2 == 0)
      {
        searchCriteria.append("(A.AREAOFSPECIALITYID = " + areaOfSpecialityId + ") ");
      }
      else
      {
        searchCriteria.append("(A.AREAOFSPECIALITYID = " + areaOfSpecialityId + " OR A.AREAOFSPECIALITYID = " + areaOfSpecialityId2 + ") ");
      }
    }
    if (areaOfSpecialityId2 != 0)
    {
      searchCriteria.append(" ");
      searchCriteria.append(operator);
      searchCriteria.append(" ");
      if (areaOfSpecialityId == 0)
      {
        searchCriteria.append("(A.AREAOFSPECIALITYID2 = " + areaOfSpecialityId2 + ") ");
      }
      else
      {
        searchCriteria.append("(A.AREAOFSPECIALITYID2 = " + areaOfSpecialityId2 + " OR A.AREAOFSPECIALITYID2 = " + areaOfSpecialityId + ") ");
      }
    }
    searchCriteria.append(")");
  }
  private void dateColumn(StringBuffer searchCriteria, String operator, Date propertyValue, String columnName)
  {
    addSearchCriteria(searchCriteria, operator, columnName + " <= ^ ");
    Utilities.replaceAndQuote(searchCriteria, propertyValue);
  }
  private void addSearchCriteria(StringBuffer searchCriteria, String operator, String newSearchCriteria)
  {
    if (searchCriteria.length() > 0)
    {
      searchCriteria.append(" ");
      searchCriteria.append(operator);
      searchCriteria.append(" ");
    }
    searchCriteria.append(newSearchCriteria);
  }
//  private void replaceCheckForColumnHasData(StringBuffer sql, Boolean hasData, String columnName)
//  {
//    if (hasData == null)
//    {
//      Utilities.replace(sql, "", false);
//    }
//    else
//    {
//      Utilities.replace(sql, (hasData == true) ? "OR " + columnName + " IS NOT NULL" : "OR " + columnName + " IS NULL");
//    }
//  }
// <-- NEW  
  
  public List<Applicant> getApplicantsForAgencyInLastNameGroup(Integer agencyId, String lastNameGroup, boolean showOnlyActive) {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveApplicantsForAgencyInLastNameGroupSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectApplicantsForAgencyInLastNameGroupSQL.toString());
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, lastNameGroup);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
    
  }

  public List<Applicant> getWorkingApplicantsForAgency(Integer agencyId) 
  {
    StringBuffer sql = null;
    sql = new StringBuffer(selectActiveApplicantsForAgencySQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
    
  }

  public List<Applicant> getApplicantsForAgencyNew(Integer agencyId, Date dateToday)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyNewSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToday);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyCrbAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyCrbAboutToExpireSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToCheck);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyDbsAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyDbsAboutToExpireSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToCheck);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyFitToWorkAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyFitToWorkAboutToExpireSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToCheck);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyIdDocumentAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyIdDocumentAboutToExpireSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToCheck);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyHpcAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyHpcAboutToExpireSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToCheck);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyTrainingAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyTrainingAboutToExpireSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToCheck);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyVisaAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyVisaAboutToExpireSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToCheck);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyReference1NotSatisfied(Integer agencyId, Date dateToday)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyReference1NotSatisfiedSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToday);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyReference2NotSatisfied(Integer agencyId, Date dateToday)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyReference2NotSatisfiedSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToday);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyDrivingLicenseAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyDrivingLicenseAboutToExpireSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, dateToCheck);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyUnarchived(Integer agencyId)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyUnarchivedSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyRecentlyCompliant(Integer agencyId)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyRecentlyCompliantSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsForAgencyRecentProspect(Integer agencyId)
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyRecentProspectSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, com.helmet.persistence.Constants.prospectLogin);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public List<Applicant> getApplicantsToCopy(Integer sourceAgencyId, Integer targetAgencyId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectApplicantsToCopySQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, sourceAgencyId);
    Utilities.replace(sql, targetAgencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
  }

  public Applicant getApplicant(Integer applicantId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, applicantId);
		return (Applicant) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Applicant.class.getName());
	}
	public Applicant getApplicantForLogin(Integer clientId, String login) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectApplicantForLoginSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, login);
		return (Applicant) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Applicant.class.getName());
	}
	public int insertApplicant(Applicant applicant, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAuditorSQL.toString());
		Integer newAuditorId = UpdateHandler.getInstance().getId(getJdbcTemplate(), "auditor");
		Utilities.replace(sql, newAuditorId);
		// swallow rowcount
		UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
        // 
		applicant.setApplicantId(newAuditorId);
		// Create a new local StringBuffer containing the parameterised SQL.
		sql = new StringBuffer(insertApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, applicant.getApplicantId());
		Utilities.replace(sql, applicant.getAgencyId());
		Utilities.replaceAndQuote(sql, StringUtils.trim(applicant.getUser().getFirstName()));
		Utilities.replaceAndQuote(sql, StringUtils.trim(applicant.getUser().getLastName()));
		Utilities.replaceAndQuoteNullable(sql, StringUtils.trim(applicant.getUser().getEmailAddress()));
		Utilities.replaceAndQuote(sql, applicant.getUser().getLogin());
		Utilities.replaceAndQuote(sql, applicant.getUser().getPwd());
		Utilities.replaceAndQuote(sql, applicant.getUser().getPwdHint());
		// on insert set secretword as login - then force to change on first login
		Utilities.replaceAndQuote(sql, applicant.getUser().getLogin());
		Utilities.replaceAndQuoteNullable(sql, applicant.getReference());
		Utilities.replaceAndQuoteNullable(sql, applicant.getProfessionalReference());
		Utilities.replaceAndQuoteNullable(sql, applicant.getDateOfBirth());
		Utilities.replaceAndQuote(sql, applicant.getNiNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getPhotoFilename());
    Utilities.replaceAndQuote(sql, String.valueOf(applicant.getGender()));
		Utilities.replaceAndQuoteNullable(sql, applicant.getLimitedCompanyName());
		Utilities.replace(sql, applicant.getHideMoney());
		Utilities.replace(sql, applicant.getCanToggleHideMoney());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress4());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getPostalCode());
		Utilities.replaceZeroWithNull(sql, applicant.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, applicant.getTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, applicant.getMobileNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getVaricellaFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getHepbFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getTbFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getMmrx2Filename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getMmrFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getPerformanceEvaluationDate());
    Utilities.replace(sql, applicant.getPerformanceEvaluation());
    // Archived is DEFAULT.
    Utilities.replace(sql, applicant.getCompliant());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference2Filename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference2Date());
    Utilities.replace(sql, applicant.getReference2Status());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference2());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference1Filename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference1Date());
    Utilities.replace(sql, applicant.getReference1Status());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference1());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCvFilename());
    Utilities.replace(sql, applicant.getOverseasPoliceClearance());
    Utilities.replaceAndQuoteNullable(sql, applicant.getDegreeDetail());
    Utilities.replace(sql, applicant.getDegree());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBirthCertificateFilename());
    Utilities.replace(sql, applicant.getBirthCertificate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getProofOfAddress1Filename());
    Utilities.replace(sql, applicant.getProofOfAddress1());
    Utilities.replaceAndQuoteNullable(sql, applicant.getProofOfAddress2Filename());
    Utilities.replace(sql, applicant.getProofOfAddress2());
    Utilities.replace(sql, applicant.getNiNumberStatus());
    Utilities.replaceAndQuoteNullable(sql, applicant.getFitToWorkFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getFitToWorkExpiryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getIdDocumentFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getIdDocumentExpiryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getIdDocumentNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getTrainingFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getTrainingExpiryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCrbFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCrbExpiryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCrbIssueDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getHpcFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getHpcExpiryDate());
    Utilities.replaceAndQuote(sql, applicant.getHpcNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getInterviewDate());
    Utilities.replace(sql, applicant.getAssessment12Week());
    Utilities.replaceAndQuoteNullable(sql, applicant.getAssessment12WeekDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getAvailabilityDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getArrivalInCountryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getVisaExpiryDate());
    Utilities.replaceZeroWithNull(sql, applicant.getAreaOfSpecialityId());
    Utilities.replaceZeroWithNull(sql, applicant.getGeographicalRegionId());
    Utilities.replaceZeroWithNull(sql, applicant.getDisciplineCategoryId());
    Utilities.replace(sql, applicant.getClientGroup());
    Utilities.replace(sql, applicant.getDrivingLicense());
    Utilities.replaceAndQuoteNullable(sql, applicant.getDrivingLicenseExpiryDate());
    Utilities.replace(sql, applicant.getFitToWorkStatus());
    Utilities.replace(sql, applicant.getAreaOfSpecialityId2());
//    Utilities.replace(sql, applicant.getRequiresVisa());
    Utilities.replace(sql, applicant.getCurrentlyWorking());
    Utilities.replace(sql, applicant.getVatChargeable());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBankName());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBankSortCode());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBankAccountName());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBankAccountNumber());
    Utilities.replace(sql, applicant.getIdDocument());
    Utilities.replace(sql, applicant.getLanguageCompetency());
    Utilities.replace(sql, applicant.getFitToWorkIssuedBy());
    Utilities.replaceAndQuoteNullable(sql, applicant.getIvsEppFilename());
    Utilities.replace(sql, applicant.getManualHandlingTraining());
    Utilities.replace(sql, applicant.getBasicLifeSupportTraining());
    Utilities.replace(sql, applicant.getElearningTraining());
    Utilities.replace(sql, applicant.getPovaTraining());
    Utilities.replace(sql, applicant.getNeonatalLifeSupportTraining());
    Utilities.replaceZeroWithNull(sql, applicant.getAhpRegistrationType());
    Utilities.replaceAndQuoteNullable(sql, applicant.getHpcLastCheckedDate());
    Utilities.replace(sql, applicant.getHpcAlertNotification());
    Utilities.replaceAndQuoteNullable(sql, applicant.getPaediatricLifeSupportFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getPaediatricLifeSupportIssuedDate());
    Utilities.replace(sql, applicant.getVisaType());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCrbDisclosureNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getEnglishTestCertificateFilename());
    Utilities.replaceAndQuoteNullable(sql, StringUtils.trim(applicant.getUser().getNhsStaffName()));
    Utilities.replace(sql, applicant.getOriginalAgencyId());
    Utilities.replaceAndQuoteNullable(sql, applicant.getRegisteredWithDbsDate());
    // NEW -->
    Utilities.replaceAndQuoteNullable(sql, applicant.getDbsRenewalDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getDbsFilename());
	  // <-- NEW
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateApplicant(Applicant applicant, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, applicant.getAgencyId());
		Utilities.replaceAndQuote(sql, StringUtils.trim(applicant.getUser().getFirstName()));
		Utilities.replaceAndQuote(sql, StringUtils.trim(applicant.getUser().getLastName()));
		Utilities.replaceAndQuoteNullable(sql, StringUtils.trim(applicant.getUser().getEmailAddress()));
		Utilities.replaceAndQuote(sql, applicant.getUser().getLogin());
		Utilities.replaceAndQuote(sql, applicant.getUser().getPwd());
		Utilities.replaceAndQuote(sql, applicant.getUser().getPwdHint());
		Utilities.replace(sql, applicant.getUser().getShowPageHelp());
		Utilities.replaceAndQuoteNullable(sql, applicant.getReference());
		Utilities.replaceAndQuoteNullable(sql, applicant.getProfessionalReference());
		Utilities.replaceAndQuoteNullable(sql, applicant.getDateOfBirth());
		Utilities.replaceAndQuote(sql, applicant.getNiNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getPhotoFilename());
    Utilities.replaceAndQuote(sql, String.valueOf(applicant.getGender()));
		Utilities.replaceAndQuoteNullable(sql, applicant.getLimitedCompanyName());
		Utilities.replace(sql, applicant.getHideMoney());
		Utilities.replace(sql, applicant.getCanToggleHideMoney());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress4());
		Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getPostalCode());
		Utilities.replaceZeroWithNull(sql, applicant.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, applicant.getTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, applicant.getMobileNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getVaricellaFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getHepbFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getTbFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getMmrx2Filename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getMmrFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getPerformanceEvaluationDate());
    Utilities.replace(sql, applicant.getPerformanceEvaluation());
    Utilities.replace(sql, applicant.getCompliant());
    // The HasBeenUnarchived flag is set to False when Applicant is Compliant.
    Utilities.replace(sql, applicant.getCompliant() ? false : applicant.getHasBeenUnarchived());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference2Filename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference2Date());
    Utilities.replace(sql, applicant.getReference2Status());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference2());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference1Filename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference1Date());
    Utilities.replace(sql, applicant.getReference1Status());
    Utilities.replaceAndQuoteNullable(sql, applicant.getReference1());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCvFilename());
    Utilities.replace(sql, applicant.getOverseasPoliceClearance());
    Utilities.replaceAndQuoteNullable(sql, applicant.getDegreeDetail());
    Utilities.replace(sql, applicant.getDegree());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBirthCertificateFilename());
    Utilities.replace(sql, applicant.getBirthCertificate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getProofOfAddress1Filename());
    Utilities.replace(sql, applicant.getProofOfAddress1());
    Utilities.replaceAndQuoteNullable(sql, applicant.getProofOfAddress2Filename());
    Utilities.replace(sql, applicant.getProofOfAddress2());
    Utilities.replace(sql, applicant.getNiNumberStatus());
    Utilities.replaceAndQuoteNullable(sql, applicant.getFitToWorkFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getFitToWorkExpiryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getIdDocumentFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getIdDocumentExpiryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getIdDocumentNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getTrainingFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getTrainingExpiryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCrbFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCrbExpiryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCrbIssueDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getHpcFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getHpcExpiryDate());
    Utilities.replaceAndQuote(sql, applicant.getHpcNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getInterviewDate());
    Utilities.replace(sql, applicant.getAssessment12Week());
    Utilities.replaceAndQuoteNullable(sql, applicant.getAssessment12WeekDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getAvailabilityDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getArrivalInCountryDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getVisaExpiryDate());
    Utilities.replaceZeroWithNull(sql, applicant.getAreaOfSpecialityId());
    Utilities.replaceZeroWithNull(sql, applicant.getGeographicalRegionId());
    Utilities.replaceZeroWithNull(sql, applicant.getDisciplineCategoryId());
    Utilities.replace(sql, applicant.getClientGroup());
    Utilities.replace(sql, applicant.getDrivingLicense());
    Utilities.replaceAndQuoteNullable(sql, applicant.getDrivingLicenseExpiryDate());
    Utilities.replace(sql, applicant.getFitToWorkStatus());
    Utilities.replace(sql, applicant.getAreaOfSpecialityId2());
//    Utilities.replace(sql, applicant.getRequiresVisa());
    Utilities.replace(sql, applicant.getRecentlyCompliant());
    Utilities.replace(sql, applicant.getCurrentlyWorking());
    Utilities.replace(sql, applicant.getVatChargeable());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBankName());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBankSortCode());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBankAccountName());
    Utilities.replaceAndQuoteNullable(sql, applicant.getBankAccountNumber());
    Utilities.replace(sql, applicant.getIdDocument());
    Utilities.replace(sql, applicant.getLanguageCompetency());
    Utilities.replace(sql, applicant.getFitToWorkIssuedBy());
    Utilities.replaceAndQuoteNullable(sql, applicant.getIvsEppFilename());
    Utilities.replace(sql, applicant.getManualHandlingTraining());
    Utilities.replace(sql, applicant.getBasicLifeSupportTraining());
    Utilities.replace(sql, applicant.getElearningTraining());
    Utilities.replace(sql, applicant.getPovaTraining());
    Utilities.replace(sql, applicant.getNeonatalLifeSupportTraining());
    Utilities.replaceZeroWithNull(sql, applicant.getAhpRegistrationType());
    Utilities.replaceAndQuoteNullable(sql, applicant.getHpcLastCheckedDate());
    Utilities.replace(sql, applicant.getHpcAlertNotification());
    Utilities.replaceAndQuoteNullable(sql, applicant.getPaediatricLifeSupportFilename());
    Utilities.replaceAndQuoteNullable(sql, applicant.getPaediatricLifeSupportIssuedDate());
    Utilities.replace(sql, applicant.getVisaType());
    Utilities.replaceAndQuoteNullable(sql, applicant.getCrbDisclosureNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getEnglishTestCertificateFilename());
    Utilities.replaceAndQuoteNullable(sql, StringUtils.trim(applicant.getUser().getNhsStaffName()));
    Utilities.replaceAndQuoteNullable(sql, applicant.getRegisteredWithDbsDate());
    // NEW -->
    Utilities.replaceAndQuoteNullable(sql, applicant.getDbsRenewalDate());
    Utilities.replaceAndQuoteNullable(sql, applicant.getDbsFilename());
    // <-- NEW
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, applicant.getApplicantId());
		Utilities.replace(sql, applicant.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int archiveApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(archiveApplicantSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, noOfChanges);
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int unarchiveApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(unarchiveApplicantSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, noOfChanges);
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
  public int compliantApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId, Boolean compliant) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(compliantApplicantSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, compliant);
    Utilities.replace(sql, compliant);
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, noOfChanges);
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int deleteApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, applicantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public List<Applicant> getApplicantsForAgencyAndNotForBookingGrade(Integer agencyId, Integer bookingGradeId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyAndNotForBookingGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, bookingGradeId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
		
	}

  public List<Applicant> getApplicantsForAgencyAndNotForBookingGradeInLastNameGroup(Integer agencyId, Integer bookingGradeId, String lastNameGroup) {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectActiveApplicantsForAgencyAndNotForBookingGradeInLastNameGroupSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, bookingGradeId);
    Utilities.replaceAndQuote(sql, lastNameGroup);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Applicant.class.getName());
    
  }

  public int updateApplicantChecklistCreatedTime(Integer applicantId, Integer noOfChanges, Integer auditorId, Date checklistCreatedTime) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateApplicantChecklistCreatedTimeSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, new Timestamp(checklistCreatedTime.getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, noOfChanges);
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
  public int updateApplicantByApplicant(Applicant applicant) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateApplicantByApplicantSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, StringUtils.trim(applicant.getUser().getFirstName()));
    Utilities.replaceAndQuote(sql, StringUtils.trim(applicant.getUser().getLastName()));
    Utilities.replaceAndQuoteNullable(sql, StringUtils.trim(applicant.getUser().getEmailAddress()));
    Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress1());
    Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress2());
    Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress3());
    Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getAddress4());
    Utilities.replaceAndQuoteNullable(sql, applicant.getAddress().getPostalCode());
    Utilities.replaceZeroWithNull(sql, applicant.getAddress().getCountryId());
    Utilities.replaceAndQuoteNullable(sql, applicant.getTelephoneNumber());
    Utilities.replaceAndQuoteNullable(sql, applicant.getMobileNumber());
    // Set AuditorId to ApplicantId.
    Utilities.replace(sql, applicant.getApplicantId());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, applicant.getApplicantId());
    Utilities.replace(sql, applicant.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int updateApplicantPwd(Integer applicantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateApplicantPwdSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, Utilities.encryptPassword(newPwd));
    Utilities.replaceAndQuote(sql, pwdHint);
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, noOfChanges);
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
	public int updateApplicantSecretWord(Integer applicantId, String newSecretWord, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateApplicantSecretWordSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, newSecretWord);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, applicantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
  public List<ApplicantClientBooking> getApplicantClientBookings(Integer applicantId, Integer clientId, Integer agencyId, Date searchDate)
  {
    StringBuffer sql = new StringBuffer(selectApplicantClientBookingsSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, searchDate);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ApplicantClientBooking.class.getName());
  }

}
