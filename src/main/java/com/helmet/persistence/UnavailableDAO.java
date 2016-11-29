package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Unavailable;

public interface UnavailableDAO 
{
  public List<Unavailable> getUnavailables();
  public List<Unavailable> getUnavailablesForApplicant(Integer agencyId, Integer applicantId, boolean showOnlyActive);
  public List<Unavailable> getUnavailablesForApplicantInDateRange(Integer agencyId, Integer applicantId, boolean showOnlyActive, java.util.Date fromDate, java.util.Date toDate);
  public List<Unavailable> getActiveUnavailablesInDateRange(Integer agencyId, java.util.Date fromDate, java.util.Date toDate);
	public Unavailable getUnavailable(Integer notAvailableId);
  public int insertUnavailable(Unavailable notAvailable, Integer auditorId);
  public int updateUnavailable(Unavailable notAvailable, Integer auditorId);
}
