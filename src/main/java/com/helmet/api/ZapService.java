package com.helmet.api;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.Unavailable;

public interface ZapService 
{
  public Applicant validateLogin(Applicant applicant);
  public Applicant getApplicant(Integer applicantId);
  public Agency getAgency(Integer agencyId);
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(Integer applicantId, Integer agencyId, Date fromDate, Date toDate);
  public List<Unavailable> getUnavailablesForApplicant(Integer agencyId, Integer applicantId, boolean showOnlyActive);
  public List<Unavailable> getUnavailablesForApplicantInDateRange(Integer agencyId, Integer applicantId, boolean showOnlyActive, java.util.Date fromDate, java.util.Date toDate);
  public int insertUnavailable(Unavailable unavailable, Integer auditorId);
  public int updateUnavailable(Unavailable unavailable, Integer auditorId);
  public int updateApplicantByApplicant(Applicant applicant);
  public int updateApplicantPwd(Integer applicantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
}