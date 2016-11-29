package com.helmet.bean;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;

public class AgencyInvoice extends Base {

	public final static int AGENCYINVOICE_STATUS_SUBMITTED = 0;
	public final static int AGENCYINVOICE_STATUS_AUTHORIZED = 1;
	public final static int AGENCYINVOICE_STATUS_PAID = 2;
	public final static int AGENCYINVOICE_STATUS_CREDITED = 3;

	public final static String[] AGENCYINVOICE_STATUS_DESCRIPTION_KEYS = {"agencyInvoice.status.submitted",
																		  "agencyInvoice.status.authorized",
																		  "agencyInvoice.status.paid",
																		  "agencyInvoice.status.credited"};
	
	private Integer agencyInvoiceId;

	private Integer agencyId;

	private Integer clientId;
	
	private BigDecimal chargeRateValue = new BigDecimal(0);

	private BigDecimal payRateValue = new BigDecimal(0);

	private BigDecimal wageRateValue = new BigDecimal(0);

	private BigDecimal wtdValue = new BigDecimal(0);

	private BigDecimal niValue = new BigDecimal(0);

	private BigDecimal commissionValue = new BigDecimal(0);

	private BigDecimal expenseValue = new BigDecimal(0);

	private BigDecimal vatValue = new BigDecimal(0);

	private BigDecimal noOfHours = new BigDecimal(0);

	private BigDecimal feeValue = new BigDecimal(0);

	private Integer paymentTerms;
	
	private String reference;

	private Integer status;

	private Integer submittedByConsultantId;
	
	private Timestamp submittedTimestamp;
	
	private Integer authorizedByManagerId;
	
	private Timestamp authorizedTimestamp;
	
	private Integer paidByManagerId;
	
	private Timestamp paidTimestamp;
	
	private Integer creditedById;
	
	private Timestamp creditedTimestamp;
	
	private String timesheetFilename;
	
	private String discountText;
	
	private BigDecimal discountValue = new BigDecimal(0);

	private BigDecimal discountVatRate = new BigDecimal(0);

	private BigDecimal discountVatValue = new BigDecimal(0);

	private Integer agencyInvoiceCreditId;

	public Integer getAgencyInvoiceId() {
		return agencyInvoiceId;
	}

	public void setAgencyInvoiceId(Integer agencyInvoiceId) {
		this.agencyInvoiceId = agencyInvoiceId;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public BigDecimal getChargeRateValue() {
		return chargeRateValue;
	}

	public void setChargeRateValue(BigDecimal chargeRateValue) {
		this.chargeRateValue = chargeRateValue;
	}

	public BigDecimal getPayRateValue() {
		return payRateValue;
	}

	public void setPayRateValue(BigDecimal payRateValue) {
		this.payRateValue = payRateValue;
	}

	public BigDecimal getWtdValue() {
		return wtdValue;
	}

	public void setWtdValue(BigDecimal wtdValue) {
		this.wtdValue = wtdValue;
	}

	public BigDecimal getNiValue() {
		return niValue;
	}

	public void setNiValue(BigDecimal niValue) {
		this.niValue = niValue;
	}

	public BigDecimal getCommissionValue() {
		return commissionValue;
	}

	public void setCommissionValue(BigDecimal commissionValue) {
		this.commissionValue = commissionValue;
	}

	public BigDecimal getExpenseValue() {
		return expenseValue;
	}

	public void setExpenseValue(BigDecimal expenseValue) {
		this.expenseValue = expenseValue;
	}

	public BigDecimal getVatValue() {
		return vatValue;
	}

	public void setVatValue(BigDecimal vatValue) {
		this.vatValue = vatValue;
	}

	public BigDecimal getFeeValue() {
		return feeValue;
	}

	public void setFeeValue(BigDecimal feeValue) {
		this.feeValue = feeValue;
	}

	public BigDecimal getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(BigDecimal noOfHours) {
		this.noOfHours = noOfHours;
	}

	public Integer getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(Integer paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public BigDecimal getTotalNetValue() {
		return getChargeRateValue().add(getExpenseValue()).subtract(getDiscountValue());
	}

	public BigDecimal getTotalValue() {
		return getTotalNetValue().add(getVatValue());
	}

	public BigDecimal getPayeRateValue() {
		return getPayRateValue() == null ? new BigDecimal(0) : getPayRateValue().add(getWtdValue()).add(getNiValue());
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAuthorizedByManagerId() {
		return authorizedByManagerId;
	}

	public void setAuthorizedByManagerId(Integer authorizedByManagerId) {
		this.authorizedByManagerId = authorizedByManagerId;
	}

	public Timestamp getAuthorizedTimestamp() {
		return authorizedTimestamp;
	}

	public void setAuthorizedTimestamp(Timestamp authorizedTimestamp) {
		this.authorizedTimestamp = authorizedTimestamp;
	}

	public Integer getPaidByManagerId() {
		return paidByManagerId;
	}

	public void setPaidByManagerId(Integer paidByManagerId) {
		this.paidByManagerId = paidByManagerId;
	}

	public Timestamp getPaidTimestamp() {
		return paidTimestamp;
	}

	public void setPaidTimestamp(Timestamp paidTimestamp) {
		this.paidTimestamp = paidTimestamp;
	}

	public Integer getSubmittedByConsultantId() {
		return submittedByConsultantId;
	}

	public void setSubmittedByConsultantId(Integer submittedByConsultantId) {
		this.submittedByConsultantId = submittedByConsultantId;
	}

	public Timestamp getSubmittedTimestamp() {
		return submittedTimestamp;
	}

	public void setSubmittedTimestamp(Timestamp submittedTimestamp) {
		this.submittedTimestamp = submittedTimestamp;
	}

	public String getTimesheetFilename() {
		return timesheetFilename;
	}

	public void setTimesheetFilename(String timesheetFilename) {
		this.timesheetFilename = timesheetFilename;
	}

	public String getStatusDescriptionKey() {
		return AGENCYINVOICE_STATUS_DESCRIPTION_KEYS[status];
	}

    public boolean getCanBeAuthorized() {
		return status == AGENCYINVOICE_STATUS_SUBMITTED;
    }
	
    public boolean getCanBePaid() {
		return status == AGENCYINVOICE_STATUS_AUTHORIZED;
    }
	
    public boolean getCanBeEdited() {
		return status != AGENCYINVOICE_STATUS_CREDITED;
    }
	
    public boolean getCanBeCredited() {
		return status != AGENCYINVOICE_STATUS_CREDITED;
    }

	public String getTimesheetPath() {
		return FileHandler.getInstance().getInvoiceFileFolder() + "/" + agencyId + "/" + agencyInvoiceId + "/" ;
	}

	public String getTimesheetUrl() {
		return getTimesheetPath() + timesheetFilename;
	}
    
    public BigDecimal getWageRateValue() {
		return wageRateValue;
	}

	public void setWageRateValue(BigDecimal wageRateValue) {
		this.wageRateValue = wageRateValue;
	}

	public Boolean getTimesheetExists() {
		return  new File(FileHandler.getInstance().getInvoiceFileLocation() + getTimesheetPath() + getTimesheetFilename()).exists();
	}
		
	public String getDiscountText() {
		return discountText;
	}

	public void setDiscountText(String discountText) {
		this.discountText = discountText;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public BigDecimal getDiscountVatRate() {
		return discountVatRate;
	}

	public void setDiscountVatRate(BigDecimal discountVatRate) {
		this.discountVatRate = discountVatRate;
	}

	public BigDecimal getDiscountVatValue() {
		return discountVatValue;
	}

	public void setDiscountVatValue(BigDecimal discountVatValue) {
		this.discountVatValue = discountVatValue;
	}

	public BigDecimal getDiscountTotalValue() {
		return discountValue.add(discountVatValue);
	}
	
	public Integer getAgencyInvoiceCreditId() {
		return agencyInvoiceCreditId;
	}

	public void setAgencyInvoiceCreditId(Integer agencyInvoiceCreditId) {
		this.agencyInvoiceCreditId = agencyInvoiceCreditId;
	}

	public boolean getIsCredited() {
		return getStatus() == AGENCYINVOICE_STATUS_CREDITED;
	}

	public Integer getCreditedById() {
		return creditedById;
	}

	public void setCreditedById(Integer creditedById) {
		this.creditedById = creditedById;
	}

	public Timestamp getCreditedTimestamp() {
		return creditedTimestamp;
	}

	public void setCreditedTimestamp(Timestamp creditedTimestamp) {
		this.creditedTimestamp = creditedTimestamp;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAgencyInvoiceId(rs.getInt("AGENCYINVOICEID"));
		setAgencyId(rs.getInt("AGENCYID"));
		setClientId(rs.getInt("CLIENTID"));
		setChargeRateValue(rs.getBigDecimal("CHARGERATEVALUE"));
		setPayRateValue(rs.getBigDecimal("PAYRATEVALUE"));
		setWageRateValue(rs.getBigDecimal("WAGERATEVALUE"));
		setWtdValue(rs.getBigDecimal("WTDVALUE"));
		setNiValue(rs.getBigDecimal("NIVALUE"));
		setCommissionValue(rs.getBigDecimal("COMMISSIONVALUE"));
		setExpenseValue(rs.getBigDecimal("EXPENSEVALUE"));
		setVatValue(rs.getBigDecimal("VATVALUE"));
		setNoOfHours(rs.getBigDecimal("NOOFHOURS"));
		setFeeValue(rs.getBigDecimal("FEEVALUE"));
		setPaymentTerms(rs.getInt("PAYMENTTERMS"));
		setReference(rs.getString("REFERENCE"));
		setStatus(rs.getInt("STATUS"));
        setSubmittedByConsultantId(rs.getInt("SUBMITTEDBYCONSULTANTID"));	
        setSubmittedTimestamp(rs.getTimestamp("SUBMITTEDTIMESTAMP"));	
        setAuthorizedByManagerId(rs.getInt("AUTHORIZEDBYMANAGERID"));	
        setAuthorizedTimestamp(rs.getTimestamp("AUTHORIZEDTIMESTAMP"));	
        setPaidByManagerId(rs.getInt("PAIDBYMANAGERID"));	
        setPaidTimestamp(rs.getTimestamp("PAIDTIMESTAMP"));	
        setCreditedById(rs.getInt("CREDITEDBYID"));	
        setCreditedTimestamp(rs.getTimestamp("CREDITEDTIMESTAMP"));	
        setTimesheetFilename(rs.getString("TIMESHEETFILENAME"));
		setDiscountText(rs.getString("DISCOUNTTEXT"));
		setDiscountValue(rs.getBigDecimal("DISCOUNTVALUE"));
		setDiscountVatRate(rs.getBigDecimal("DISCOUNTVATRATE"));
		setDiscountVatValue(rs.getBigDecimal("DISCOUNTVATVALUE"));
		setAgencyInvoiceCreditId(rs.getInt("AGENCYINVOICECREDITID"));
	}

}
