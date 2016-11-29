package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Invoice;
import com.helmet.bean.InvoiceAgency;
import com.helmet.bean.InvoiceAgencyUser;
import com.helmet.bean.InvoiceAgencyUserEntity;

public interface InvoiceAgencyDAO {

	public List<InvoiceAgencyUser> getInvoiceAgencyUsersForInvoice(Integer invoiceId);
	public InvoiceAgencyUser getInvoiceAgencyUserForInvoiceForAgency(Integer invoiceId, Integer agencyId);
	public InvoiceAgencyUserEntity getInvoiceAgencyUserEntityForInvoiceForAgency(Integer invoiceId, Integer agencyId);
	public int insertInvoiceAgency(InvoiceAgency invoiceAgency, Integer auditorId);
	public int updateInvoiceAgency(InvoiceAgency invoiceAgency, Integer auditorId);
	public int updateInvoiceAgencyValues(InvoiceAgency invoiceAgency, Integer auditorId);

}
