package com.helmet.api.impl;

import java.sql.Date;
import java.util.List;

import com.helmet.api.ZapService;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.Unavailable;
import com.helmet.persistence.AgencyDAO;
import com.helmet.persistence.ApplicantDAO;
import com.helmet.persistence.UnavailableDAO;
import com.helmet.persistence.Utilities;

public class DefaultZapService extends DefaultCommonService implements ZapService 
{
	private ApplicantDAO applicantDAO;
  private AgencyDAO agencyDAO;
  private UnavailableDAO unavailableDAO;

  public void setApplicantDAO(ApplicantDAO applicantDAO)
  {
    this.applicantDAO = applicantDAO;
  }

  public void setAgencyDAO(AgencyDAO agencyDAO)
  {
    this.agencyDAO = agencyDAO;
  }

  public void setUnavailableDAO(UnavailableDAO unavailableDAO) 
  {
    this.unavailableDAO = unavailableDAO;
  }

  public Applicant validateLogin(Applicant applicant)
  {
    Applicant applicantx = applicantDAO.getApplicant(applicant.getApplicantId());
    if (applicantx == null) 
    { 
      throw new DataNotFoundException(); 
    }
    String encryptedPwd = Utilities.encryptPassword(applicant.getUser().getPwd());
    if (!applicantx.getUser().getPwd().equals(encryptedPwd)) 
    { 
      throw new InvalidDetailException(); 
    }
    return applicantx;
  }

  public Applicant getApplicant(Integer applicantId)
  {
    Applicant applicant = null;
    applicant = applicantDAO.getApplicant(applicantId);
    return applicant;
  }

  public Agency getAgency(Integer agencyId)
  {
    Agency agency = null;
    agency = agencyDAO.getAgency(agencyId);
    return agency;
  }

  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(Integer applicantId, Integer agencyId, Date fromDate, Date toDate) 
  {
    List<BookingDateUserApplicant> bookingDateUserApplicants = null;
    bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(applicantId, agencyId, fromDate, toDate);
    return bookingDateUserApplicants;
  }

  public List<Unavailable> getUnavailablesForApplicant(Integer agencyId, Integer applicantId, boolean showOnlyActive)
  {
    List<Unavailable> unavailables = null;
    unavailables = unavailableDAO.getUnavailablesForApplicant(agencyId, applicantId, showOnlyActive);
    return unavailables;
  }

  public List<Unavailable> getUnavailablesForApplicantInDateRange(Integer agencyId, Integer applicantId, boolean showOnlyActive, java.util.Date fromDate, java.util.Date toDate)
  {
    List<Unavailable> unavailables = null;
    unavailables = unavailableDAO.getUnavailablesForApplicantInDateRange(agencyId, applicantId, showOnlyActive, fromDate, toDate);
    return unavailables;
  }

  public int insertUnavailable(Unavailable unavailable, Integer auditorId)
  {
    int rc = unavailableDAO.insertUnavailable(unavailable, auditorId);
    return rc;
  }

  public int updateUnavailable(Unavailable unavailable, Integer auditorId)
  {
    int rc = unavailableDAO.updateUnavailable(unavailable, auditorId);
    return rc;
  }

  public int updateApplicantByApplicant(Applicant applicant)
  {
    int rc = applicantDAO.updateApplicantByApplicant(applicant);
    return rc;
  }

  public int updateApplicantPwd(Integer applicantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId)
  {

    int rc = applicantDAO.updateApplicantPwd(applicantId, newPwd, pwdHint, noOfChanges, auditorId);
    return rc;

  }

}