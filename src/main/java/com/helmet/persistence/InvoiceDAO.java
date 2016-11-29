package com.helmet.persistence;

import com.helmet.bean.Invoice;
import com.helmet.bean.InvoiceEntity;

public interface InvoiceDAO {

	public Invoice getInvoice(Integer clientId, Integer invoiceId);
	public InvoiceEntity getInvoiceEntity(Integer clientId, Integer invoiceId);
	
	public int insertInvoice(Invoice invoice, Integer auditorId);
	public int updateInvoiceValues(Invoice invoice, Integer auditorId);

}
