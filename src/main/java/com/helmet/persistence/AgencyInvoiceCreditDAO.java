package com.helmet.persistence;

import com.helmet.bean.AgencyInvoiceCredit;

public interface AgencyInvoiceCreditDAO {

	public AgencyInvoiceCredit getAgencyInvoiceCredit(Integer agencyInvoiceCreditId);
	public int insertAgencyInvoiceCredit(AgencyInvoiceCredit agencyInvoiceCredit, Integer auditorId);

}
