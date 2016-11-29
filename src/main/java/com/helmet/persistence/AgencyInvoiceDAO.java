package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.AgencyInvoice;
import com.helmet.bean.AgencyInvoiceUser;
import com.helmet.bean.AgencyInvoiceUserEntity;

public interface AgencyInvoiceDAO {

	public AgencyInvoiceUser getAgencyInvoiceUser(Integer agencyInvoiceId);
	public AgencyInvoiceUserEntity getAgencyInvoiceUserEntity(Integer agencyInvoiceId);

	public int insertAgencyInvoice(AgencyInvoice agencyInvoice, Integer auditorId);
	public int updateAgencyInvoice(AgencyInvoice agencyInvoice, Integer auditorId);
	public int updateAgencyInvoiceValues(AgencyInvoice agencyInvoice, Integer auditorId);
    public int updateAgencyInvoiceAuthorized(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId);
    public int updateAgencyInvoicePaid(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId);
	public int updateAgencyInvoiceTimesheet(AgencyInvoice agencyInvoice, Integer auditorId);
    public int updateAgencyInvoiceCredited(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId, Integer agencyInvoiceCreditId);
    
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForManagerAndStatus(Integer clientId, Integer managerId, Integer status);
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForManagerAndAgencyInvoices(Integer clientId, Integer managerId, String agencyInvoiceIdStrs);

	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForAgencyAndStatus(Integer agencyId, Integer status);
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForAgency(Integer agencyId, Integer agencyInvoiceId, 
			Integer clientId, Integer status, Date fromDate, Date toDate);
	
}
