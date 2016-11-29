package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.AgencyUserEntity;
import com.helmet.persistence.AgencyDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAgencyDAO extends JdbcDaoSupport implements AgencyDAO {

	private static StringBuffer insertAgencySQL;

	private static StringBuffer updateAgencySQL;

	private static StringBuffer updateAgencyDisplayOrderSQL;

	private static StringBuffer deleteAgencySQL;

	private static StringBuffer selectAgencySQL;

	private static StringBuffer selectAgencyForNameSQL;

	private static StringBuffer selectAgencyForCodeSQL;

  private static StringBuffer selectAgenciesSQL;

  private static StringBuffer selectActiveAgenciesSQL;

	private static StringBuffer selectAgencyUserSQL;

	private static StringBuffer selectAgencyUsersSQL;

	private static StringBuffer selectActiveAgencyUsersSQL;

	private static StringBuffer selectAgencyUsersNotForClientSQL;

	public static void init() {
		// Get insert Agency SQL.
		insertAgencySQL = new StringBuffer();
		insertAgencySQL.append("INSERT INTO AGENCY ");
		insertAgencySQL.append("(  ");
		insertAgencySQL.append("  AGENCYID, ");
		insertAgencySQL.append("  NAME, ");
		insertAgencySQL.append("  ADDRESS1, ");
		insertAgencySQL.append("  ADDRESS2, ");
		insertAgencySQL.append("  ADDRESS3, ");
		insertAgencySQL.append("  ADDRESS4, ");
		insertAgencySQL.append("  POSTALCODE, ");
		insertAgencySQL.append("  COUNTRYID, ");
		insertAgencySQL.append("  CODE, ");
		insertAgencySQL.append("  TELEPHONENUMBER, ");
		insertAgencySQL.append("  FAXNUMBER, ");
		insertAgencySQL.append("  LOGOFILENAME, ");
		insertAgencySQL.append("  LOGOWIDTH, ");
		insertAgencySQL.append("  LOGOHEIGHT, ");
		insertAgencySQL.append("  VATNUMBER, ");
		insertAgencySQL.append("  REFERENCE, ");
		insertAgencySQL.append("  FREETEXT, ");
		insertAgencySQL.append("  FREETEXT2, ");
		insertAgencySQL.append("  INVOICECREDITFREETEXT, ");
		insertAgencySQL.append("  INVOICECREDITFOOTERFREETEXT, ");
		insertAgencySQL.append("  INVOICELOGOFILENAME, ");
		insertAgencySQL.append("  INVOICELOGOWIDTH, ");
		insertAgencySQL.append("  INVOICELOGOHEIGHT, ");
		insertAgencySQL.append("  PAYROLLCONTACTNAME, ");
		insertAgencySQL.append("  PAYROLLCONTACTEMAILADDRESS, ");
		insertAgencySQL.append("  PAYROLLCONTACTTELEPHONENUMBER, ");
		insertAgencySQL.append("  PAYROLLCONTACTFAXNUMBER, ");
		insertAgencySQL.append("  WEBSITEADDRESS, ");
		insertAgencySQL.append("  CLIENTCONFIRMATIONEMAILFREETEXT, ");
    insertAgencySQL.append("  APPLICANTCONFIRMATIONEMAILFREETEXT, ");
    insertAgencySQL.append("  AGENCYADMINEMAILADDRESS, ");
    insertAgencySQL.append("  TRADESHIFTCONSUMERKEY, ");
    insertAgencySQL.append("  TRADESHIFTCONSUMERSECRET, ");
    insertAgencySQL.append("  TRADESHIFTTOKENKEY, ");
    insertAgencySQL.append("  TRADESHIFTTOKENSECRET, ");
    insertAgencySQL.append("  TRADESHIFTTENANTID, ");
    insertAgencySQL.append("  CREATIONTIMESTAMP, ");
		insertAgencySQL.append("  AUDITORID, ");
		insertAgencySQL.append("  AUDITTIMESTAMP ");
		insertAgencySQL.append(")  ");
		insertAgencySQL.append("VALUES  ");
		insertAgencySQL.append("(  ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
    insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");    
    insertAgencySQL.append("  ^, ");
    insertAgencySQL.append("  ^, ");
    insertAgencySQL.append("  ^, ");
    insertAgencySQL.append("  ^, ");
    insertAgencySQL.append("  ^, ");    
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^, ");
		insertAgencySQL.append("  ^ ");
		insertAgencySQL.append(")  ");
		// Get update Agency SQL.
		updateAgencySQL = new StringBuffer();
		updateAgencySQL.append("UPDATE AGENCY ");
		updateAgencySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAgencySQL.append("     NAME = ^, ");
		updateAgencySQL.append("     ADDRESS1 = ^, ");
		updateAgencySQL.append("     ADDRESS2 = ^, ");
		updateAgencySQL.append("     ADDRESS3 = ^, ");
		updateAgencySQL.append("     ADDRESS4 = ^, ");
		updateAgencySQL.append("     POSTALCODE = ^, ");
		updateAgencySQL.append("     COUNTRYID = ^, ");
		updateAgencySQL.append("     CODE = ^, ");
		updateAgencySQL.append("     TELEPHONENUMBER = ^, ");
		updateAgencySQL.append("     FAXNUMBER = ^, ");
		updateAgencySQL.append("     LOGOFILENAME = ^, ");
		updateAgencySQL.append("     LOGOWIDTH = ^, ");
		updateAgencySQL.append("     LOGOHEIGHT = ^, ");
		updateAgencySQL.append("     VATNUMBER = ^, ");
		updateAgencySQL.append("     REFERENCE = ^, ");
		updateAgencySQL.append("     FREETEXT = ^, ");
		updateAgencySQL.append("     FREETEXT2 = ^, ");
		updateAgencySQL.append("     INVOICECREDITFREETEXT = ^, ");
		updateAgencySQL.append("     INVOICECREDITFOOTERFREETEXT = ^, ");
		updateAgencySQL.append("     INVOICELOGOFILENAME = ^, ");
		updateAgencySQL.append("     INVOICELOGOWIDTH = ^, ");
		updateAgencySQL.append("     INVOICELOGOHEIGHT = ^, ");
		updateAgencySQL.append("     PAYROLLCONTACTNAME = ^, ");
		updateAgencySQL.append("     PAYROLLCONTACTEMAILADDRESS = ^, ");
		updateAgencySQL.append("     PAYROLLCONTACTTELEPHONENUMBER = ^, ");
		updateAgencySQL.append("     PAYROLLCONTACTFAXNUMBER = ^, ");
		updateAgencySQL.append("     WEBSITEADDRESS = ^, ");
		updateAgencySQL.append("     CLIENTCONFIRMATIONEMAILFREETEXT = ^, ");
    updateAgencySQL.append("     APPLICANTCONFIRMATIONEMAILFREETEXT = ^, ");
    updateAgencySQL.append("     AGENCYADMINEMAILADDRESS = ^, ");
    updateAgencySQL.append("     TRADESHIFTCONSUMERKEY = ^, ");
    updateAgencySQL.append("     TRADESHIFTCONSUMERSECRET = ^, ");
    updateAgencySQL.append("     TRADESHIFTTOKENKEY = ^, ");
    updateAgencySQL.append("     TRADESHIFTTOKENSECRET = ^, ");
    updateAgencySQL.append("     TRADESHIFTTENANTID = ^, ");
    updateAgencySQL.append("     AUDITORID = ^, ");
		updateAgencySQL.append("     AUDITTIMESTAMP = ^ ");
		updateAgencySQL.append("WHERE AGENCYID = ^ ");
		updateAgencySQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateAgencyDisplayOrder SQL.
		updateAgencyDisplayOrderSQL = new StringBuffer();
		updateAgencyDisplayOrderSQL.append("UPDATE AGENCY ");
		updateAgencyDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateAgencyDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateAgencyDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateAgencyDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateAgencyDisplayOrderSQL.append("WHERE AGENCYID = ^ ");
		updateAgencyDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Agency SQL.
		deleteAgencySQL = new StringBuffer();
		deleteAgencySQL.append("UPDATE AGENCY ");
		deleteAgencySQL.append("SET ACTIVE = FALSE, ");
		deleteAgencySQL.append("    AUDITORID = ^, ");
		deleteAgencySQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAgencySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAgencySQL.append("WHERE AGENCYID = ^ ");
		deleteAgencySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Agencies SQL.
		selectAgenciesSQL = new StringBuffer();
		selectAgenciesSQL.append("SELECT AGENCYID, ");
		selectAgenciesSQL.append("       NAME, ");
		selectAgenciesSQL.append("       ADDRESS1, ");
		selectAgenciesSQL.append("       ADDRESS2, ");
		selectAgenciesSQL.append("       ADDRESS3, ");
		selectAgenciesSQL.append("       ADDRESS4, ");
		selectAgenciesSQL.append("       POSTALCODE, ");
		selectAgenciesSQL.append("       COUNTRYID, ");
		selectAgenciesSQL.append("       CODE, ");
		selectAgenciesSQL.append("       DISPLAYORDER, ");
		selectAgenciesSQL.append("       TELEPHONENUMBER, ");
		selectAgenciesSQL.append("       FAXNUMBER, ");
		selectAgenciesSQL.append("       LOGOFILENAME, ");
		selectAgenciesSQL.append("       LOGOWIDTH, ");
		selectAgenciesSQL.append("       LOGOHEIGHT, ");
		selectAgenciesSQL.append("       VATNUMBER, ");
		selectAgenciesSQL.append("       REFERENCE, ");
		selectAgenciesSQL.append("       FREETEXT, ");
		selectAgenciesSQL.append("       FREETEXT2, ");
		selectAgenciesSQL.append("       INVOICECREDITFREETEXT, ");
		selectAgenciesSQL.append("       INVOICECREDITFOOTERFREETEXT, ");
		selectAgenciesSQL.append("       INVOICELOGOFILENAME, ");
		selectAgenciesSQL.append("       INVOICELOGOWIDTH, ");
		selectAgenciesSQL.append("       INVOICELOGOHEIGHT, ");
		selectAgenciesSQL.append("       PAYROLLCONTACTNAME, ");
		selectAgenciesSQL.append("       PAYROLLCONTACTEMAILADDRESS, ");
		selectAgenciesSQL.append("       PAYROLLCONTACTTELEPHONENUMBER, ");
		selectAgenciesSQL.append("       PAYROLLCONTACTFAXNUMBER, ");
		selectAgenciesSQL.append("       WEBSITEADDRESS, ");
		selectAgenciesSQL.append("       CLIENTCONFIRMATIONEMAILFREETEXT, ");
    selectAgenciesSQL.append("       APPLICANTCONFIRMATIONEMAILFREETEXT, ");
    selectAgenciesSQL.append("       AGENCYADMINEMAILADDRESS, ");
    selectAgenciesSQL.append("       TRADESHIFTCONSUMERKEY, ");
    selectAgenciesSQL.append("       TRADESHIFTCONSUMERSECRET, ");
    selectAgenciesSQL.append("       TRADESHIFTTOKENKEY, ");
    selectAgenciesSQL.append("       TRADESHIFTTOKENSECRET, ");
    selectAgenciesSQL.append("       TRADESHIFTTENANTID, ");
    selectAgenciesSQL.append("       HASSUBCONTRACTORS, ");
		selectAgenciesSQL.append("       CREATIONTIMESTAMP, ");
		selectAgenciesSQL.append("       AUDITORID, ");
		selectAgenciesSQL.append("       AUDITTIMESTAMP, ");
		selectAgenciesSQL.append("       ACTIVE, ");
		selectAgenciesSQL.append("       NOOFCHANGES ");
		selectAgenciesSQL.append("FROM AGENCY ");
		// Get select Agency SQL.
		selectAgencySQL = new StringBuffer(selectAgenciesSQL);
		selectAgencySQL.append("WHERE AGENCYID = ^ ");
		// Get select Agency for Name SQL.
		selectAgencyForNameSQL = new StringBuffer(selectAgenciesSQL);
		selectAgencyForNameSQL.append("WHERE NAME = ^ ");
		selectAgencyForNameSQL.append("AND ACTIVE = TRUE ");
		// Get select Agency for Code SQL.
		selectAgencyForCodeSQL = new StringBuffer(selectAgenciesSQL);
		selectAgencyForCodeSQL.append("WHERE CODE = ^ ");
		selectAgencyForCodeSQL.append("AND ACTIVE = TRUE ");
		// Get select AgencyUsers SQL.
		selectAgencyUsersSQL = new StringBuffer();
		selectAgencyUsersSQL.append("SELECT A.AGENCYID, ");
		selectAgencyUsersSQL.append("	   A.NAME, ");
		selectAgencyUsersSQL.append("	   A.ADDRESS1, ");
		selectAgencyUsersSQL.append("	   A.ADDRESS2, ");
		selectAgencyUsersSQL.append("	   A.ADDRESS3, ");
		selectAgencyUsersSQL.append("	   A.ADDRESS4, ");
		selectAgencyUsersSQL.append("	   A.POSTALCODE, ");
		selectAgencyUsersSQL.append("	   A.COUNTRYID, ");
		selectAgencyUsersSQL.append("	   A.CODE, ");
		selectAgencyUsersSQL.append("	   A.DISPLAYORDER, ");
		selectAgencyUsersSQL.append("    A.TELEPHONENUMBER, ");
		selectAgencyUsersSQL.append("    A.FAXNUMBER, ");
		selectAgencyUsersSQL.append("    A.LOGOFILENAME, ");
		selectAgencyUsersSQL.append("    A.LOGOWIDTH, ");
		selectAgencyUsersSQL.append("    A.LOGOHEIGHT, ");
		selectAgencyUsersSQL.append("    A.VATNUMBER, ");
		selectAgencyUsersSQL.append("    A.REFERENCE, ");
		selectAgencyUsersSQL.append("    A.FREETEXT, ");
		selectAgencyUsersSQL.append("    A.FREETEXT2, ");
		selectAgencyUsersSQL.append("    A.INVOICECREDITFREETEXT, ");
		selectAgencyUsersSQL.append("    A.INVOICECREDITFOOTERFREETEXT, ");
		selectAgencyUsersSQL.append("    A.INVOICELOGOFILENAME, ");
		selectAgencyUsersSQL.append("    A.INVOICELOGOWIDTH, ");
		selectAgencyUsersSQL.append("    A.INVOICELOGOHEIGHT, ");
		selectAgencyUsersSQL.append("    A.PAYROLLCONTACTNAME, ");
		selectAgencyUsersSQL.append("    A.PAYROLLCONTACTEMAILADDRESS, ");
		selectAgencyUsersSQL.append("    A.PAYROLLCONTACTTELEPHONENUMBER, ");
		selectAgencyUsersSQL.append("    A.PAYROLLCONTACTFAXNUMBER, ");
		selectAgencyUsersSQL.append("    A.WEBSITEADDRESS, ");
		selectAgencyUsersSQL.append("    A.CLIENTCONFIRMATIONEMAILFREETEXT, ");
    selectAgencyUsersSQL.append("    A.APPLICANTCONFIRMATIONEMAILFREETEXT, ");
    selectAgencyUsersSQL.append("    A.AGENCYADMINEMAILADDRESS, ");
    selectAgencyUsersSQL.append("    A.TRADESHIFTCONSUMERKEY, ");
    selectAgencyUsersSQL.append("    A.TRADESHIFTCONSUMERSECRET, ");
    selectAgencyUsersSQL.append("    A.TRADESHIFTTOKENKEY, ");
    selectAgencyUsersSQL.append("    A.TRADESHIFTTOKENSECRET, ");
    selectAgencyUsersSQL.append("    A.TRADESHIFTTENANTID, ");
    selectAgencyUsersSQL.append("    A.HASSUBCONTRACTORS, ");
    selectAgencyUsersSQL.append("	   A.CREATIONTIMESTAMP, ");
		selectAgencyUsersSQL.append("	   A.AUDITORID, ");
		selectAgencyUsersSQL.append("	   A.AUDITTIMESTAMP, ");
		selectAgencyUsersSQL.append("	   A.ACTIVE, ");
		selectAgencyUsersSQL.append("	   A.NOOFCHANGES, ");
		selectAgencyUsersSQL.append("	   C.NAME AS COUNTRYNAME ");
		selectAgencyUsersSQL.append("FROM AGENCY A, COUNTRY C ");
		selectAgencyUsersSQL.append("WHERE C.COUNTRYID  = A.COUNTRYID ");
		// Get select Active AgencyUsers SQL.
		selectActiveAgencyUsersSQL = new StringBuffer(selectAgencyUsersSQL);
		selectActiveAgencyUsersSQL.append("AND A.ACTIVE = TRUE ");
    // Get select Active Agencies SQL.
    selectActiveAgenciesSQL = new StringBuffer(selectAgenciesSQL);
    selectActiveAgenciesSQL.append("WHERE ACTIVE = TRUE ");
		// Get select AgencyUser SQL.
		selectAgencyUserSQL = new StringBuffer(selectAgencyUsersSQL);
		selectAgencyUserSQL.append("AND A.AGENCYID = ^ ");
		// Get select Agencies not for Client SQL.
		selectAgencyUsersNotForClientSQL = new StringBuffer(selectAgencyUsersSQL);
		selectAgencyUsersNotForClientSQL.append("AND A.ACTIVE = TRUE ");
		selectAgencyUsersNotForClientSQL.append("AND NOT EXISTS ");
		selectAgencyUsersNotForClientSQL.append("( ");
		selectAgencyUsersNotForClientSQL.append(" SELECT NULL ");
		selectAgencyUsersNotForClientSQL.append(" FROM CLIENTAGENCY CA ");
		selectAgencyUsersNotForClientSQL.append(" WHERE CA.CLIENTID = ^ ");
		selectAgencyUsersNotForClientSQL.append(" AND CA.ACTIVE = TRUE ");
		selectAgencyUsersNotForClientSQL.append(" AND CA.AGENCYID = A.AGENCYID ");
		selectAgencyUsersNotForClientSQL.append(") ");
        //
		selectAgencyUsersNotForClientSQL.append("ORDER BY A.DISPLAYORDER, A.NAME ");
        //
		selectAgencyUsersSQL.append("ORDER BY A.DISPLAYORDER, A.NAME ");
    //
    selectActiveAgencyUsersSQL.append("ORDER BY A.DISPLAYORDER, A.NAME ");
    //
    selectActiveAgenciesSQL.append("ORDER BY DISPLAYORDER, NAME ");
	}

	public int insertAgency(Agency agency,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAgencySQL.toString());
		// Replace the parameters with supplied values.
		agency.setAgencyId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "agency"));
		Utilities.replace(sql, agency.getAgencyId());
		Utilities.replaceAndQuote(sql, agency.getName().trim());
		Utilities.replaceAndQuote(sql, agency.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, agency.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, agency.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, agency.getAddress().getAddress4());
		Utilities.replaceAndQuote(sql, agency.getAddress().getPostalCode());
		Utilities.replace(sql, agency.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, agency.getCode());
		Utilities.replaceAndQuoteNullable(sql, agency.getTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getFaxNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getLogoFilename());
		Utilities.replaceZeroWithNull(sql, agency.getLogoWidth());
		Utilities.replaceZeroWithNull(sql, agency.getLogoHeight());
		Utilities.replaceAndQuoteNullable(sql, agency.getVatNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getReference());
		Utilities.replaceAndQuoteNullable(sql, agency.getFreeText());
		Utilities.replaceAndQuoteNullable(sql, agency.getFreeText2());
		Utilities.replaceAndQuoteNullable(sql, agency.getInvoiceCreditFreeText());
		Utilities.replaceAndQuoteNullable(sql, agency.getInvoiceCreditFooterFreeText());
		Utilities.replaceAndQuoteNullable(sql, agency.getInvoiceLogoFilename());
		Utilities.replaceZeroWithNull(sql, agency.getInvoiceLogoWidth());
		Utilities.replaceZeroWithNull(sql, agency.getInvoiceLogoHeight());
		Utilities.replaceAndQuoteNullable(sql, agency.getPayrollContactName());
		Utilities.replaceAndQuoteNullable(sql, agency.getPayrollContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, agency.getPayrollContactTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getPayrollContactFaxNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getWebsiteAddress());
		Utilities.replaceAndQuoteNullable(sql, agency.getClientConfirmationEmailFreeText());
    Utilities.replaceAndQuoteNullable(sql, agency.getApplicantConfirmationEmailFreeText());
    Utilities.replaceAndQuoteNullable(sql, agency.getAgencyAdminEmailAddress());

    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftConsumerKey());
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftConsumerSecret());
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftTokenKey());
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftTokenSecret());
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftTenantId());

		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateAgency(Agency agency,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, agency.getName().trim());
		Utilities.replaceAndQuote(sql, agency.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, agency.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, agency.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, agency.getAddress().getAddress4());
		Utilities.replaceAndQuote(sql, agency.getAddress().getPostalCode());
		Utilities.replace(sql, agency.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, agency.getCode());
		Utilities.replaceAndQuoteNullable(sql, agency.getTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getFaxNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getLogoFilename());
		Utilities.replaceZeroWithNull(sql, agency.getLogoWidth());
		Utilities.replaceZeroWithNull(sql, agency.getLogoHeight());
		Utilities.replaceAndQuoteNullable(sql, agency.getVatNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getReference());
		Utilities.replaceAndQuoteNullable(sql, agency.getFreeText());
		Utilities.replaceAndQuoteNullable(sql, agency.getFreeText2());
		Utilities.replaceAndQuoteNullable(sql, agency.getInvoiceCreditFreeText());
		Utilities.replaceAndQuoteNullable(sql, agency.getInvoiceCreditFooterFreeText());
		Utilities.replaceAndQuoteNullable(sql, agency.getInvoiceLogoFilename());
		Utilities.replaceZeroWithNull(sql, agency.getInvoiceLogoWidth());
		Utilities.replaceZeroWithNull(sql, agency.getInvoiceLogoHeight());
		Utilities.replaceAndQuoteNullable(sql, agency.getPayrollContactName());
		Utilities.replaceAndQuoteNullable(sql, agency.getPayrollContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, agency.getPayrollContactTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getPayrollContactFaxNumber());
		Utilities.replaceAndQuoteNullable(sql, agency.getWebsiteAddress());
		Utilities.replaceAndQuoteNullable(sql, agency.getClientConfirmationEmailFreeText());
		Utilities.replaceAndQuoteNullable(sql, agency.getApplicantConfirmationEmailFreeText());
    Utilities.replaceAndQuoteNullable(sql, agency.getAgencyAdminEmailAddress());
    
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftConsumerKey());
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftConsumerSecret());
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftTokenKey());
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftTokenSecret());
    Utilities.replaceAndQuoteNullable(sql, agency.getTradeshiftTenantId());

		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agency.getAgencyId());
		Utilities.replace(sql, agency.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteAgency(Integer agencyId,
			Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public Agency getAgency(Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return (Agency) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Agency.class.getName());
	}

	public Agency getAgencyForName(String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (Agency) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Agency.class.getName());
	}

	public Agency getAgencyForCode(String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (Agency) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Agency.class.getName());
	}

	public AgencyUser getAgencyUser(Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return (AgencyUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyUser.class.getName());
	}

	public AgencyUserEntity getAgencyUserEntity(Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return (AgencyUserEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyUserEntity.class.getName());
	}

  public List<Agency> getAgencies(boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveAgenciesSQL.toString());  
    }
    else 
    {
      sql = new StringBuffer(selectAgenciesSQL.toString());  
    }
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Agency.class.getName());
  }

	public List<AgencyUser> getAgencyUsers(boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveAgencyUsersSQL.toString());	
		}
		else {
			sql = new StringBuffer(selectAgencyUsersSQL.toString());	
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				AgencyUser.class.getName());
	}

	public List<AgencyUser> getAgencyUsersNotForClient(Integer clientId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyUsersNotForClientSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyUser.class.getName());

	}

	public int updateAgencyDisplayOrder(Integer agencyId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgencyDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
	
}
