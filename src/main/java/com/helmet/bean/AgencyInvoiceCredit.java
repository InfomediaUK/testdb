package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AgencyInvoiceCredit extends Base {

	public final static int AGENCYINVOICECREDIT_STATUS_SUBMITTED = 0;

	public final static String[] AGENCYINVOICECREDIT_STATUS_DESCRIPTION_KEYS = {"agencyInvoiceCredit.status.submitted"};
	
	private Integer agencyInvoiceCreditId;

	private Integer agencyInvoiceId;
	
	private String reasonText;

	private Integer status;

	public Integer getAgencyInvoiceCreditId() {
		return agencyInvoiceCreditId;
	}

	public void setAgencyInvoiceCreditId(Integer agencyInvoiceCreditId) {
		this.agencyInvoiceCreditId = agencyInvoiceCreditId;
	}

	public Integer getAgencyInvoiceId() {
		return agencyInvoiceId;
	}

	public void setAgencyInvoiceId(Integer agencyInvoiceId) {
		this.agencyInvoiceId = agencyInvoiceId;
	}

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAgencyInvoiceCreditId(rs.getInt("AGENCYINVOICECREDITID"));
		setAgencyInvoiceId(rs.getInt("AGENCYINVOICEID"));
		setReasonText(rs.getString("REASONTEXT"));
		setStatus(rs.getInt("STATUS"));
	}

}
