package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Client;
import com.helmet.bean.ClientUser;
import com.helmet.bean.ClientUserEntity;
import com.helmet.persistence.ClientDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultClientDAO extends JdbcDaoSupport implements ClientDAO {

	private static StringBuffer insertClientSQL;

	private static StringBuffer updateClientSQL;
  
  private static StringBuffer updateClientTradeshiftDetailsSQL;

	private static StringBuffer updateClientDisplayOrderSQL;

	private static StringBuffer deleteClientSQL;

	private static StringBuffer selectClientSQL;

	private static StringBuffer selectClientForNameSQL;

  private static StringBuffer selectClientForCodeSQL;

  private static StringBuffer selectClientForReferenceSQL;

  private static StringBuffer selectClientsForTradeshiftSbsPayablesCodeSQL;

	private static StringBuffer selectClientForBookingGradeApplicantSQL;

	private static StringBuffer selectClientsSQL;

	private static StringBuffer selectClientUserSQL;

	private static StringBuffer selectClientUsersSQL;

	private static StringBuffer selectActiveClientUsersSQL;

  private static StringBuffer selectClientUsersForAgencySQL;

  private static StringBuffer selectNhsClientUsersForAgencySQL;

	public static void init() {
		// Get insert Client SQL.
		insertClientSQL = new StringBuffer();
		insertClientSQL.append("INSERT INTO CLIENT ");
		insertClientSQL.append("(  ");
		insertClientSQL.append("  CLIENTID, ");
		insertClientSQL.append("  NAME, ");
		insertClientSQL.append("  ADDRESS1, ");
		insertClientSQL.append("  ADDRESS2, ");
		insertClientSQL.append("  ADDRESS3, ");
		insertClientSQL.append("  ADDRESS4, ");
		insertClientSQL.append("  POSTALCODE, ");
		insertClientSQL.append("  COUNTRYID, ");
		insertClientSQL.append("  CODE, ");
		insertClientSQL.append("  TELEPHONENUMBER, ");
    insertClientSQL.append("  FAXNUMBER, ");
    insertClientSQL.append("  EMAILADDRESS, ");
    insertClientSQL.append("  WEBSITEADDRESS, ");
		insertClientSQL.append("  LOGOFILENAME, ");
		insertClientSQL.append("  LOGOWIDTH, ");
		insertClientSQL.append("  LOGOHEIGHT, ");
		insertClientSQL.append("  VATNUMBER, ");
		insertClientSQL.append("  REFERENCE, ");
		insertClientSQL.append("  FREETEXT, ");
		insertClientSQL.append("  LOGO2FILENAME, ");
		insertClientSQL.append("  LOGO2WIDTH, ");
		insertClientSQL.append("  LOGO2HEIGHT, ");
		insertClientSQL.append("  SECRETWORDATLOGIN, ");
		insertClientSQL.append("  AUTOACTIVATEDEFAULT, ");
		insertClientSQL.append("  ACCOUNTCONTACTNAME, ");
    insertClientSQL.append("  ACCOUNTCONTACTEMAILADDRESS, ");
		insertClientSQL.append("  ACCOUNTCONTACTADDRESS1, ");
		insertClientSQL.append("  ACCOUNTCONTACTADDRESS2, ");
		insertClientSQL.append("  ACCOUNTCONTACTADDRESS3, ");
		insertClientSQL.append("  ACCOUNTCONTACTADDRESS4, ");
		insertClientSQL.append("  ACCOUNTCONTACTPOSTALCODE, ");
		insertClientSQL.append("  ACCOUNTCONTACTCOUNTRYID, ");
    insertClientSQL.append("  AGENCYWORKERCHECKLISTOTHER, ");
    insertClientSQL.append("  TRADESHIFTSBSPAYABLESCODE, ");
    insertClientSQL.append("  TRADESHIFTCOMPANYACCOUNTID, ");
    insertClientSQL.append("  TRADESHIFTSTATE, ");
    insertClientSQL.append("  NHSNAME, ");
    insertClientSQL.append("  UPLIFTCOMMISSION, ");
    insertClientSQL.append("  CREATIONTIMESTAMP, ");
		insertClientSQL.append("  AUDITORID, ");
		insertClientSQL.append("  AUDITTIMESTAMP ");
		insertClientSQL.append(")  ");
		insertClientSQL.append("VALUES  ");
		insertClientSQL.append("(  ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
    insertClientSQL.append("  ^, ");
		insertClientSQL.append("  ^ ");
		insertClientSQL.append(")  ");
		// Get update Client SQL.
		updateClientSQL = new StringBuffer();
		updateClientSQL.append("UPDATE CLIENT ");
		updateClientSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateClientSQL.append("     NAME = ^, ");
		updateClientSQL.append("     ADDRESS1 = ^, ");
		updateClientSQL.append("     ADDRESS2 = ^, ");
		updateClientSQL.append("     ADDRESS3 = ^, ");
		updateClientSQL.append("     ADDRESS4 = ^, ");
		updateClientSQL.append("     POSTALCODE = ^, ");
		updateClientSQL.append("     COUNTRYID = ^, ");
		updateClientSQL.append("     CODE = ^, ");
		updateClientSQL.append("     TELEPHONENUMBER = ^, ");
    updateClientSQL.append("     FAXNUMBER = ^, ");
    updateClientSQL.append("     EMAILADDRESS = ^, ");
    updateClientSQL.append("     WEBSITEADDRESS = ^, ");
		updateClientSQL.append("     LOGOFILENAME = ^, ");
		updateClientSQL.append("     LOGOWIDTH = ^, ");
		updateClientSQL.append("     LOGOHEIGHT = ^, ");
		updateClientSQL.append("     VATNUMBER = ^, ");
		updateClientSQL.append("     REFERENCE = ^, ");
		updateClientSQL.append("     FREETEXT = ^, ");
		updateClientSQL.append("     LOGO2FILENAME = ^, ");
		updateClientSQL.append("     LOGO2WIDTH = ^, ");
		updateClientSQL.append("     LOGO2HEIGHT = ^, ");
		updateClientSQL.append("     SECRETWORDATLOGIN = ^, ");
		updateClientSQL.append("     AUTOACTIVATEDEFAULT = ^, ");
		updateClientSQL.append("     ACCOUNTCONTACTNAME = ^, ");
		updateClientSQL.append("     ACCOUNTCONTACTEMAILADDRESS = ^, ");
		updateClientSQL.append("     ACCOUNTCONTACTADDRESS1 = ^, ");
		updateClientSQL.append("     ACCOUNTCONTACTADDRESS2 = ^, ");
		updateClientSQL.append("     ACCOUNTCONTACTADDRESS3 = ^, ");
		updateClientSQL.append("     ACCOUNTCONTACTADDRESS4 = ^, ");
		updateClientSQL.append("     ACCOUNTCONTACTPOSTALCODE = ^, ");
		updateClientSQL.append("     ACCOUNTCONTACTCOUNTRYID = ^, ");
    updateClientSQL.append("     AGENCYWORKERCHECKLISTOTHER = ^, ");
    updateClientSQL.append("     TRADESHIFTSBSPAYABLESCODE = ^, ");
    updateClientSQL.append("     TRADESHIFTCOMPANYACCOUNTID = ^, ");
    updateClientSQL.append("     TRADESHIFTSTATE = ^, ");
    updateClientSQL.append("     NHSNAME = ^, ");
    updateClientSQL.append("     UPLIFTCOMMISSION = ^, ");
    updateClientSQL.append("     AUDITORID = ^, ");
		updateClientSQL.append("     AUDITTIMESTAMP = ^ ");
		updateClientSQL.append("WHERE CLIENTID = ^ ");
		updateClientSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update Client Tradeshift Details SQL.
    updateClientTradeshiftDetailsSQL = new StringBuffer();
    updateClientTradeshiftDetailsSQL.append("UPDATE CLIENT ");
    updateClientTradeshiftDetailsSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateClientTradeshiftDetailsSQL.append("     TRADESHIFTCOMPANYACCOUNTID = ^, ");
    updateClientTradeshiftDetailsSQL.append("     TRADESHIFTSTATE = ^, ");
    updateClientTradeshiftDetailsSQL.append("     AUDITORID = ^, ");
    updateClientTradeshiftDetailsSQL.append("     AUDITTIMESTAMP = ^ ");
    updateClientTradeshiftDetailsSQL.append("WHERE CLIENTID = ^ ");
    updateClientTradeshiftDetailsSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Client SQL.
		deleteClientSQL = new StringBuffer();
		deleteClientSQL.append("UPDATE CLIENT ");
		deleteClientSQL.append("SET ACTIVE = FALSE, ");
		deleteClientSQL.append("    AUDITORID = ^, ");
		deleteClientSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteClientSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteClientSQL.append("WHERE CLIENTID = ^ ");
		deleteClientSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateClientDisplayOrder SQL.
		updateClientDisplayOrderSQL = new StringBuffer();
		updateClientDisplayOrderSQL.append("UPDATE CLIENT ");
		updateClientDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateClientDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateClientDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateClientDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateClientDisplayOrderSQL.append("WHERE CLIENTID = ^ ");
		updateClientDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Clients SQL.
		selectClientsSQL = new StringBuffer();
		selectClientsSQL.append("SELECT CLIENTID, ");
		selectClientsSQL.append("       NAME, ");
		selectClientsSQL.append("       ADDRESS1, ");
		selectClientsSQL.append("       ADDRESS2, ");
		selectClientsSQL.append("       ADDRESS3, ");
		selectClientsSQL.append("       ADDRESS4, ");
		selectClientsSQL.append("       POSTALCODE, ");
		selectClientsSQL.append("       COUNTRYID, ");
		selectClientsSQL.append("       CODE, ");
		selectClientsSQL.append("       DISPLAYORDER, ");
		selectClientsSQL.append("       TELEPHONENUMBER, ");
    selectClientsSQL.append("       FAXNUMBER, ");
    selectClientsSQL.append("       EMAILADDRESS, ");
    selectClientsSQL.append("       WEBSITEADDRESS, ");
		selectClientsSQL.append("       LOGOFILENAME, ");
		selectClientsSQL.append("       LOGOWIDTH, ");
		selectClientsSQL.append("       LOGOHEIGHT, ");
		selectClientsSQL.append("       VATNUMBER, ");
		selectClientsSQL.append("       REFERENCE, ");
		selectClientsSQL.append("       FREETEXT, ");
		selectClientsSQL.append("       LOGO2FILENAME, ");
		selectClientsSQL.append("       LOGO2WIDTH, ");
		selectClientsSQL.append("       LOGO2HEIGHT, ");
		selectClientsSQL.append("       SECRETWORDATLOGIN, ");
		selectClientsSQL.append("       AUTOACTIVATEDEFAULT, ");
		selectClientsSQL.append("       ACCOUNTCONTACTNAME, ");
		selectClientsSQL.append("       ACCOUNTCONTACTEMAILADDRESS, ");
		selectClientsSQL.append("       ACCOUNTCONTACTADDRESS1, ");
		selectClientsSQL.append("       ACCOUNTCONTACTADDRESS2, ");
		selectClientsSQL.append("       ACCOUNTCONTACTADDRESS3, ");
		selectClientsSQL.append("       ACCOUNTCONTACTADDRESS4, ");
		selectClientsSQL.append("       ACCOUNTCONTACTPOSTALCODE, ");
		selectClientsSQL.append("       ACCOUNTCONTACTCOUNTRYID, ");
    selectClientsSQL.append("       AGENCYWORKERCHECKLISTOTHER, ");
    selectClientsSQL.append("       TRADESHIFTSBSPAYABLESCODE, ");
    selectClientsSQL.append("       TRADESHIFTCOMPANYACCOUNTID, ");
    selectClientsSQL.append("       TRADESHIFTSTATE, ");
    selectClientsSQL.append("       NHSNAME, ");
    selectClientsSQL.append("       UPLIFTCOMMISSION, ");
    selectClientsSQL.append("       CREATIONTIMESTAMP, ");
		selectClientsSQL.append("       AUDITORID, ");
		selectClientsSQL.append("       AUDITTIMESTAMP, ");
		selectClientsSQL.append("       ACTIVE, ");
		selectClientsSQL.append("       NOOFCHANGES ");
		selectClientsSQL.append("FROM CLIENT ");
		// Get select Client SQL.
		selectClientSQL = new StringBuffer(selectClientsSQL);
		selectClientSQL.append("WHERE CLIENTID = ^ ");
		// Get select Client for Name SQL.
		selectClientForNameSQL = new StringBuffer(selectClientsSQL);
		selectClientForNameSQL.append("WHERE NAME = ^ ");
		selectClientForNameSQL.append("AND ACTIVE = TRUE ");
    // Get select Client for Code SQL.
    selectClientForCodeSQL = new StringBuffer(selectClientsSQL);
    selectClientForCodeSQL.append("WHERE CODE = ^ ");
    selectClientForCodeSQL.append("AND ACTIVE = TRUE ");
    // Get select Client for Code SQL.
    selectClientForReferenceSQL = new StringBuffer(selectClientsSQL);
    selectClientForReferenceSQL.append("WHERE REFERENCE = ^ ");
    // Get select Client for Code SQL.
    selectClientsForTradeshiftSbsPayablesCodeSQL = new StringBuffer(selectClientsSQL);
    selectClientsForTradeshiftSbsPayablesCodeSQL.append("WHERE TRADESHIFTSBSPAYABLESCODE = ^ ");
    selectClientsForTradeshiftSbsPayablesCodeSQL.append("AND ACTIVE = TRUE ");

		// Get select Client for BookingGradeApplicant SQL.
		selectClientForBookingGradeApplicantSQL = new StringBuffer(selectClientsSQL);
		selectClientForBookingGradeApplicantSQL.append("WHERE ACTIVE = TRUE ");
		selectClientForBookingGradeApplicantSQL.append("AND CLIENTID = ( ");
		selectClientForBookingGradeApplicantSQL.append("		SELECT S.CLIENTID ");
		selectClientForBookingGradeApplicantSQL.append("		FROM SITE S, ");
		selectClientForBookingGradeApplicantSQL.append("		LOCATION L, ");
		selectClientForBookingGradeApplicantSQL.append("		BOOKING B, ");
		selectClientForBookingGradeApplicantSQL.append("		BOOKINGGRADE BG, ");
		selectClientForBookingGradeApplicantSQL.append("		BOOKINGGRADEAPPLICANT BGA ");
		selectClientForBookingGradeApplicantSQL.append("		WHERE BGA.BOOKINGGRADEAPPLICANTID = ^ ");
		selectClientForBookingGradeApplicantSQL.append("		AND BGA.ACTIVE = TRUE ");
		selectClientForBookingGradeApplicantSQL.append("		AND BG.BOOKINGGRADEID = BGA.BOOKINGGRADEID ");
		selectClientForBookingGradeApplicantSQL.append("		AND BG.ACTIVE = TRUE ");
		selectClientForBookingGradeApplicantSQL.append("		AND B.BOOKINGID = BG.BOOKINGID ");
		selectClientForBookingGradeApplicantSQL.append("		AND B.ACTIVE = TRUE ");
		selectClientForBookingGradeApplicantSQL.append("		AND L.LOCATIONID = B.LOCATIONID ");
		selectClientForBookingGradeApplicantSQL.append("		AND L.ACTIVE = TRUE ");
		selectClientForBookingGradeApplicantSQL.append("		AND S.SITEID = L.SITEID ");
		selectClientForBookingGradeApplicantSQL.append("		AND S.ACTIVE = TRUE ");
		selectClientForBookingGradeApplicantSQL.append(") ");
		// Get select ClientUsers SQL.
		selectClientUsersSQL = new StringBuffer();
		selectClientUsersSQL.append("SELECT C.CLIENTID, ");
		selectClientUsersSQL.append("	   C.NAME, ");
		selectClientUsersSQL.append("	   C.ADDRESS1, ");
		selectClientUsersSQL.append("	   C.ADDRESS2, ");
		selectClientUsersSQL.append("	   C.ADDRESS3, ");
		selectClientUsersSQL.append("	   C.ADDRESS4, ");
		selectClientUsersSQL.append("	   C.POSTALCODE, ");
		selectClientUsersSQL.append("	   C.COUNTRYID, ");
		selectClientUsersSQL.append("	   C.CODE, ");
		selectClientUsersSQL.append("	   C.DISPLAYORDER, ");
		selectClientUsersSQL.append("    C.TELEPHONENUMBER, ");
    selectClientUsersSQL.append("    C.FAXNUMBER, ");
    selectClientUsersSQL.append("    C.EMAILADDRESS, ");
    selectClientUsersSQL.append("    C.WEBSITEADDRESS, ");
		selectClientUsersSQL.append("    C.LOGOFILENAME, ");
		selectClientUsersSQL.append("    C.LOGOWIDTH, ");
		selectClientUsersSQL.append("    C.LOGOHEIGHT, ");
		selectClientUsersSQL.append("    C.VATNUMBER, ");
		selectClientUsersSQL.append("    C.REFERENCE, ");
		selectClientUsersSQL.append("    C.FREETEXT, ");
		selectClientUsersSQL.append("    C.LOGO2FILENAME, ");
		selectClientUsersSQL.append("    C.LOGO2WIDTH, ");
		selectClientUsersSQL.append("    C.LOGO2HEIGHT, ");
		selectClientUsersSQL.append("    C.SECRETWORDATLOGIN, ");
		selectClientUsersSQL.append("    C.AUTOACTIVATEDEFAULT, ");
		selectClientUsersSQL.append("    C.ACCOUNTCONTACTNAME, ");
		selectClientUsersSQL.append("    C.ACCOUNTCONTACTEMAILADDRESS, ");
		selectClientUsersSQL.append("    C.ACCOUNTCONTACTADDRESS1, ");
		selectClientUsersSQL.append("    C.ACCOUNTCONTACTADDRESS2, ");
		selectClientUsersSQL.append("    C.ACCOUNTCONTACTADDRESS3, ");
		selectClientUsersSQL.append("    C.ACCOUNTCONTACTADDRESS4, ");
		selectClientUsersSQL.append("    C.ACCOUNTCONTACTPOSTALCODE, ");
		selectClientUsersSQL.append("    C.ACCOUNTCONTACTCOUNTRYID, ");
    selectClientUsersSQL.append("    C.AGENCYWORKERCHECKLISTOTHER, ");
    selectClientUsersSQL.append("    C.TRADESHIFTSBSPAYABLESCODE, ");
    selectClientUsersSQL.append("    C.TRADESHIFTCOMPANYACCOUNTID, ");
    selectClientUsersSQL.append("    C.TRADESHIFTSTATE, ");
    selectClientUsersSQL.append("    C.NHSNAME, ");
    selectClientUsersSQL.append("    C.UPLIFTCOMMISSION, ");
		selectClientUsersSQL.append("	   C.CREATIONTIMESTAMP, ");
		selectClientUsersSQL.append("	   C.AUDITORID, ");
		selectClientUsersSQL.append("	   C.AUDITTIMESTAMP, ");
		selectClientUsersSQL.append("	   C.ACTIVE, ");
		selectClientUsersSQL.append("	   C.NOOFCHANGES, ");
		selectClientUsersSQL.append("	   CO.NAME AS COUNTRYNAME, ");
		selectClientUsersSQL.append("	   CO2.NAME AS ACCOUNTCONTACTCOUNTRYNAME ");
		selectClientUsersSQL.append("FROM CLIENT C ");

		selectClientUsersSQL.append(" LEFT OUTER JOIN COUNTRY CO2 ON "); 
		selectClientUsersSQL.append("   CO2.COUNTRYID = C.ACCOUNTCONTACTCOUNTRYID  ");
		selectClientUsersSQL.append(" AND CO2.ACTIVE = TRUE "); 		
		
		selectClientUsersSQL.append(", ");
		selectClientUsersSQL.append("     COUNTRY CO ");
		selectClientUsersSQL.append("WHERE CO.COUNTRYID  = C.COUNTRYID ");
		// Get select Active ClientUsers SQL.
		selectActiveClientUsersSQL = new StringBuffer(selectClientUsersSQL);
		selectActiveClientUsersSQL.append("AND C.ACTIVE = TRUE ");
		// Get select ClientUsers for Agency SQL.
		selectClientUsersForAgencySQL = new StringBuffer(selectActiveClientUsersSQL);
		selectClientUsersForAgencySQL.append("AND EXISTS ");
		selectClientUsersForAgencySQL.append("( ");
		selectClientUsersForAgencySQL.append(" SELECT NULL ");
		selectClientUsersForAgencySQL.append(" FROM CLIENTAGENCY CA ");
		selectClientUsersForAgencySQL.append(" WHERE CA.CLIENTID = C.CLIENTID ");
		selectClientUsersForAgencySQL.append(" AND CA.AGENCYID = ^ ");
		selectClientUsersForAgencySQL.append(" AND CA.ACTIVE = TRUE ");
		selectClientUsersForAgencySQL.append(") ");
    // Get select NHS ClientUsers for Agency SQL.
    selectNhsClientUsersForAgencySQL = new StringBuffer(selectClientUsersForAgencySQL);
    selectNhsClientUsersForAgencySQL.append("AND C.NHSNAME IS NOT NULL ");
		// Get select ClientUser SQL.
		selectClientUserSQL = new StringBuffer(selectClientUsersSQL);
		selectClientUserSQL.append("AND C.CLIENTID = ^ ");
        //
		selectClientUsersSQL.append("ORDER BY C.DISPLAYORDER, C.NAME ");
		selectActiveClientUsersSQL.append("ORDER BY C.DISPLAYORDER, C.NAME ");
    selectClientUsersForAgencySQL.append("ORDER BY C.DISPLAYORDER, C.NAME ");
    selectNhsClientUsersForAgencySQL.append("ORDER BY C.DISPLAYORDER, C.NAME ");
	}

	public int insertClient(Client client,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertClientSQL.toString());
		// Replace the parameters with supplied values.
		client.setClientId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "client"));
		Utilities.replace(sql, client.getClientId());
		Utilities.replaceAndQuote(sql, client.getName().trim());
		Utilities.replaceAndQuote(sql, client.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, client.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, client.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, client.getAddress().getAddress4());
		Utilities.replaceAndQuote(sql, client.getAddress().getPostalCode());
		Utilities.replace(sql, client.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, client.getCode());
		Utilities.replaceAndQuoteNullable(sql, client.getTelephoneNumber());
    Utilities.replaceAndQuoteNullable(sql, client.getFaxNumber());
    Utilities.replaceAndQuoteNullable(sql, client.getEmailAddress());
    Utilities.replaceAndQuoteNullable(sql, client.getWebsiteAddress());
		Utilities.replaceAndQuoteNullable(sql, client.getLogoFilename());
		Utilities.replaceZeroWithNull(sql, client.getLogoWidth());
		Utilities.replaceZeroWithNull(sql, client.getLogoHeight());
		Utilities.replaceAndQuoteNullable(sql, client.getVatNumber());
		Utilities.replaceAndQuoteNullable(sql, client.getReference());
		Utilities.replaceAndQuoteNullable(sql, client.getFreeText());
		Utilities.replaceAndQuoteNullable(sql, client.getLogo2Filename());
		Utilities.replaceZeroWithNull(sql, client.getLogo2Width());
		Utilities.replaceZeroWithNull(sql, client.getLogo2Height());
		Utilities.replace(sql, client.getSecretWordAtLogin());
		Utilities.replace(sql, client.getAutoActivateDefault());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactName());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getAddress4());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getPostalCode());
		Utilities.replaceZeroWithNull(sql, client.getAccountContactAddress().getCountryId());
    Utilities.replaceAndQuoteNullable(sql, client.getAgencyWorkerChecklistOther());
    Utilities.replaceAndQuoteNullable(sql, client.getTradeshiftSbsPayablesCode());
    Utilities.replaceAndQuoteNullable(sql, client.getTradeshiftCompanyAccountId());
    Utilities.replaceAndQuoteNullable(sql, client.getTradeshiftState());
    Utilities.replaceAndQuoteNullable(sql, client.getNhsName());
    Utilities.replace(sql, client.getUpliftCommission());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateClient(Client client,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateClientSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, client.getName().trim());
		Utilities.replaceAndQuote(sql, client.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, client.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, client.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, client.getAddress().getAddress4());
		Utilities.replaceAndQuote(sql, client.getAddress().getPostalCode());
		Utilities.replace(sql, client.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, client.getCode());
		Utilities.replaceAndQuoteNullable(sql, client.getTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, client.getFaxNumber());
    Utilities.replaceAndQuoteNullable(sql, client.getEmailAddress());
    Utilities.replaceAndQuoteNullable(sql, client.getWebsiteAddress());
		Utilities.replaceAndQuoteNullable(sql, client.getLogoFilename());
		Utilities.replaceZeroWithNull(sql, client.getLogoWidth());
		Utilities.replaceZeroWithNull(sql, client.getLogoHeight());
		Utilities.replaceAndQuoteNullable(sql, client.getVatNumber());
		Utilities.replaceAndQuoteNullable(sql, client.getReference());
		Utilities.replaceAndQuoteNullable(sql, client.getFreeText());
		Utilities.replaceAndQuoteNullable(sql, client.getLogo2Filename());
		Utilities.replaceZeroWithNull(sql, client.getLogo2Width());
		Utilities.replaceZeroWithNull(sql, client.getLogo2Height());
		Utilities.replace(sql, client.getSecretWordAtLogin());
		Utilities.replace(sql, client.getAutoActivateDefault());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactName());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getAddress4());
		Utilities.replaceAndQuoteNullable(sql, client.getAccountContactAddress().getPostalCode());
		Utilities.replaceZeroWithNull(sql, client.getAccountContactAddress().getCountryId());
    Utilities.replaceAndQuoteNullable(sql, client.getAgencyWorkerChecklistOther());
    Utilities.replaceAndQuoteNullable(sql, client.getTradeshiftSbsPayablesCode());
    Utilities.replaceAndQuoteNullable(sql, client.getTradeshiftCompanyAccountId());
    Utilities.replaceAndQuoteNullable(sql, client.getTradeshiftState());
    Utilities.replaceAndQuoteNullable(sql, client.getNhsName());    
    Utilities.replace(sql, client.getUpliftCommission());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, client.getClientId());
		Utilities.replace(sql, client.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateClientTradeshiftDetails(Client client, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateClientTradeshiftDetailsSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuoteNullable(sql, client.getTradeshiftCompanyAccountId());
    Utilities.replaceAndQuoteNullable(sql, client.getTradeshiftState());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, client.getClientId());
    Utilities.replace(sql, client.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int deleteClient(Integer clientId,
			Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteClientSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public Client getClient(Integer clientId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return (Client) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Client.class.getName());
	}

	public Client getClientForName(String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (Client) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Client.class.getName());
	}

  public Client getClientForCode(String code) {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectClientForCodeSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, code);
    return (Client) RecordFactory.getInstance().get(getJdbcTemplate(),
        sql.toString(), Client.class.getName());
  }

  public Client getClientForReference(String reference) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectClientForReferenceSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, reference);
    return (Client) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Client.class.getName());
  }

  public List<Client> getClientsForTradeshiftSbsPayablesCode(String tradeshiftSbsPayablesCode) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectClientsForTradeshiftSbsPayablesCodeSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, tradeshiftSbsPayablesCode);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Client.class.getName());
  }

	public Client getClientForBookingGradeApplicant(Integer bookingGradeApplicantId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientForBookingGradeApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		return (Client) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Client.class.getName());
	}
	
	public ClientUser getClientUser(Integer clientId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return (ClientUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientUser.class.getName());
	}

	public List<ClientUser> getClientUsers(boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveClientUsersSQL.toString());	
		}
		else {
			sql = new StringBuffer(selectClientUsersSQL.toString());	
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				ClientUser.class.getName());
	}

	public List<ClientUser> getClientUsersForAgency(Integer agencyId) {

		StringBuffer sql = new StringBuffer(selectClientUsersForAgencySQL.toString());	
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ClientUser.class.getName());
		
	}

  public List<ClientUser> getNhsClientUsersForAgency(Integer agencyId) 
  {
    StringBuffer sql = new StringBuffer(selectNhsClientUsersForAgencySQL.toString());  
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ClientUser.class.getName());
  }

	public List<ClientUserEntity> getClientUserEntities(boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveClientUsersSQL.toString());	
		}
		else {
			sql = new StringBuffer(selectClientUsersSQL.toString());	
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				ClientUserEntity.class.getName());
	}

	public ClientUserEntity getClientUserEntity(Integer clientId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		ClientUserEntity clientUserEntity = (ClientUserEntity) RecordFactory
				.getInstance().get(getJdbcTemplate(), sql.toString(),
						ClientUserEntity.class.getName());
		return clientUserEntity;
	}

	public int updateClientDisplayOrder(Integer clientId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateClientDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
}
