package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.SubcontractInvoice;
import com.helmet.bean.SubcontractInvoiceUser;

public interface SubcontractInvoiceDAO 
{
  public List<SubcontractInvoice> getSubcontractInvoices(Integer agencyId, boolean showOnlyActive);
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsers(Integer agencyId, boolean showOnlyActive);
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersForAgency(Integer fromAgencyId, Integer subcontractInvoiceId, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer status, Date fromDate, Date toDate);
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersInList(Integer agencyId, String subcontractInvoiceIdList);
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersForRemittanceAdvice(Integer agencyId, String remittanceAdvice);

  public SubcontractInvoice getSubcontractInvoice(Integer subcontractInvoiceId);
  public SubcontractInvoiceUser getSubcontractInvoiceUser(Integer subcontractInvoiceId);
  public int insertSubcontractCreditNote(SubcontractInvoice subcontractInvoice, Integer auditorId);
	public int insertSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int updateSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int updateSubcontractInvoiceValue(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int updateSubcontractInvoiceSentDate(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int updateSubcontractInvoicePaidDate(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int updateSubcontractInvoiceRelatedSubcontractInvoiceId(SubcontractInvoice subcontractInvoice, Integer auditorId); 
	public int deleteSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId);
}
