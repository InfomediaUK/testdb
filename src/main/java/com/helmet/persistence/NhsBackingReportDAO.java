package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.NhsBackingReportUser;
import com.helmet.bean.RecordCount;

public interface NhsBackingReportDAO 
{
  public RecordCount getNhsBackingReportsCount(Integer agencyId, boolean showOnlyActive, String filter);
  public List<NhsBackingReport> getNhsBackingReports(Integer agencyId, boolean showOnlyActive);
  public List<NhsBackingReportUser> getNhsBackingReportUsers(Integer agencyId, boolean showOnlyActive);
  public List<NhsBackingReportUser> getNhsBackingReportUsersInList(Integer agencyId, String nhsBackingReportIdList);
  public List<NhsBackingReportUser> getNhsBackingReportUsersOffset(Integer agencyId, boolean showOnlyActive, Integer offset, String filter);
  public List<NhsBackingReport> getNhsBackingReportsForAgencyDateRange(Integer agencyId, Date startOfWeekDate, Date endOfWeekDate); 
  public List<NhsBackingReportUser> getNhsBackingReportUsersForAgencyDateRange(Integer agencyId, Date startOfWeekDate, Date endOfWeekDate); 
  public NhsBackingReport getNhsBackingReport(Integer nhsBackingReportId);
  public NhsBackingReportUser getNhsBackingReportUser(Integer nhsBackingReportId);
	public NhsBackingReport getNhsBackingReportForName(String name, boolean showOnlyActive);
  public NhsBackingReport getInactiveNhsBackingReportForName(String name); 
	public int insertNhsBackingReport(NhsBackingReport hsBackingReport, Integer auditorId);
	public int updateNhsBackingReport(NhsBackingReport hsBackingReport, Integer auditorId);
  public int updateNhsBackingReportTradeshiftDocumentId(NhsBackingReport nhsBackingReport, Integer auditorId); 
  public int updateNhsBackingReportSubcontract(NhsBackingReport nhsBackingReport, Integer auditorId);
  public int updateNhsBackingReportSubcontractDocumentationSentDate(NhsBackingReport nhsBackingReport, Integer auditorId);
  public int reactivateNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId); 
	public int deleteNhsBackingReport(Integer nhsBackingReportId, Integer noOfChanges, Integer auditorId);
  public Integer getPagingLimit();
  public void setPagingLimit(Integer pagingLimit);
  public Integer getPagingGroupSize();
  public void setPagingGroupSize(Integer pagingGroupSize);
}
