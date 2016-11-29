package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.application.agy.AgyConstants;
import com.helmet.bean.Consultant;
import com.helmet.bean.ConsultantEntity;
import com.helmet.persistence.Constants;
import com.helmet.persistence.ConsultantDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultConsultantDAO extends JdbcDaoSupport implements ConsultantDAO {

	private static StringBuffer insertAuditorSQL;

	private static StringBuffer insertConsultantSQL;

	private static StringBuffer updateConsultantSQL;

	private static StringBuffer updateConsultantPwdSQL;

	private static StringBuffer updateConsultantSecretWordSQL;

	private static StringBuffer updateConsultantShowPageHelpSQL;

	private static StringBuffer deleteConsultantSQL;

	private static StringBuffer selectConsultantsSQL;

	private static StringBuffer selectConsultantsForAgencySQL;

	private static StringBuffer selectActiveConsultantsForAgencySQL;

	private static StringBuffer selectConsultantSQL;

	private static StringBuffer selectConsultantForLoginSQL;

	public static void init() {
		// Get insert Auditor SQL.
		insertAuditorSQL = new StringBuffer();
		insertAuditorSQL.append("INSERT INTO AUDITOR ");
		insertAuditorSQL.append("(  ");
		insertAuditorSQL.append("  AUDITORID, ");
		insertAuditorSQL.append("  TYPE ");
		insertAuditorSQL.append(")  ");
		insertAuditorSQL.append("VALUES  ");
		insertAuditorSQL.append("(  ");
		insertAuditorSQL.append("  ^, ");
		insertAuditorSQL.append("  'C' ");
		insertAuditorSQL.append(")  ");
		// Get insert Consultant SQL.
		insertConsultantSQL = new StringBuffer();
		insertConsultantSQL.append("INSERT INTO CONSULTANT ");
		insertConsultantSQL.append("(  ");
		insertConsultantSQL.append("  CONSULTANTID, ");
		insertConsultantSQL.append("  AGENCYID, ");
		insertConsultantSQL.append("  FIRSTNAME, ");
		insertConsultantSQL.append("  LASTNAME, ");
		insertConsultantSQL.append("  EMAILADDRESS, ");
		insertConsultantSQL.append("  LOGIN, ");
		insertConsultantSQL.append("  PWD, ");
		insertConsultantSQL.append("  PWDHINT, ");
		insertConsultantSQL.append("  SECRETWORD, ");
    insertConsultantSQL.append("  JOBTITLE, ");
    // NEW -->
    insertConsultantSQL.append("  SIGNATUREFILENAME, ");
    // <-- NEW
		insertConsultantSQL.append("  CREATIONTIMESTAMP, ");
		insertConsultantSQL.append("  AUDITORID, ");
		insertConsultantSQL.append("  AUDITTIMESTAMP ");
		insertConsultantSQL.append(")  ");
		insertConsultantSQL.append("VALUES  ");
		insertConsultantSQL.append("(  ");
		insertConsultantSQL.append("  ^, ");
		insertConsultantSQL.append("  ^, ");
		insertConsultantSQL.append("  ^, ");
    insertConsultantSQL.append("  ^, ");
    insertConsultantSQL.append("  ^, ");
		insertConsultantSQL.append("  ^, ");
		insertConsultantSQL.append("  ^, ");
		insertConsultantSQL.append("  ^, ");
		insertConsultantSQL.append("  ^, ");
    insertConsultantSQL.append("  ^, ");
    // NEW -->
    insertConsultantSQL.append("  ^, ");
    // <-- NEW
		insertConsultantSQL.append("  ^, ");
		insertConsultantSQL.append("  ^, ");
		insertConsultantSQL.append("  ^ ");
		insertConsultantSQL.append(")  ");
		// Get update Consultant SQL.
		updateConsultantSQL = new StringBuffer();
		updateConsultantSQL.append("UPDATE CONSULTANT ");
		updateConsultantSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateConsultantSQL.append("     AGENCYID = ^, ");
		updateConsultantSQL.append("     FIRSTNAME = ^, ");
		updateConsultantSQL.append("     LASTNAME = ^, ");
		updateConsultantSQL.append("     EMAILADDRESS = ^, ");
		updateConsultantSQL.append("     LOGIN = ^, ");
//		updateConsultantSQL.append("     PWD = ^, ");
//		updateConsultantSQL.append("     PWDHINT = ^, ");
//		updateConsultantSQL.append("     SECRETWORD = ^, ");
		updateConsultantSQL.append("     SHOWPAGEHELP = ^, ");
		updateConsultantSQL.append("     SUPERUSER = ^, ");
    updateConsultantSQL.append("     JOBTITLE = ^, ");
    updateConsultantSQL.append("     CANVIEWDOCUMENTS = ^, ");
    updateConsultantSQL.append("     CANVIEWWAGES = ^, ");
    // NEW -->
    updateConsultantSQL.append("     SIGNATUREFILENAME = ^, ");
    // <-- NEW
		updateConsultantSQL.append("     AUDITORID = ^, ");
		updateConsultantSQL.append("     AUDITTIMESTAMP = ^ ");
		updateConsultantSQL.append("WHERE CONSULTANTID = ^ ");
		updateConsultantSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ConsultantPwd SQL.
		updateConsultantPwdSQL = new StringBuffer();
		updateConsultantPwdSQL.append("UPDATE CONSULTANT ");
		updateConsultantPwdSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateConsultantPwdSQL.append("     PWD = ^, ");
		updateConsultantPwdSQL.append("     PWDHINT = ^, ");
		updateConsultantPwdSQL.append("     AUDITORID = ^, ");
		updateConsultantPwdSQL.append("     AUDITTIMESTAMP = ^ ");
		updateConsultantPwdSQL.append("WHERE CONSULTANTID = ^ ");
		updateConsultantPwdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ConsultantSecretWord SQL.
		updateConsultantSecretWordSQL = new StringBuffer();
		updateConsultantSecretWordSQL.append("UPDATE CONSULTANT ");
		updateConsultantSecretWordSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateConsultantSecretWordSQL.append("     SECRETWORD = ^, ");
		updateConsultantSecretWordSQL.append("     AUDITORID = ^, ");
		updateConsultantSecretWordSQL.append("     AUDITTIMESTAMP = ^ ");
		updateConsultantSecretWordSQL.append("WHERE CONSULTANTID = ^ ");
		updateConsultantSecretWordSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ConsultantShowPageHelp SQL.
		updateConsultantShowPageHelpSQL = new StringBuffer();
		updateConsultantShowPageHelpSQL.append("UPDATE CONSULTANT ");
		updateConsultantShowPageHelpSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateConsultantShowPageHelpSQL.append("     SHOWPAGEHELP = ^, ");
		updateConsultantShowPageHelpSQL.append("     AUDITORID = ^, ");
		updateConsultantShowPageHelpSQL.append("     AUDITTIMESTAMP = ^ ");
		updateConsultantShowPageHelpSQL.append("WHERE CONSULTANTID = ^ ");
		updateConsultantShowPageHelpSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Consultant SQL.
		deleteConsultantSQL = new StringBuffer();
		deleteConsultantSQL.append("UPDATE CONSULTANT ");
		deleteConsultantSQL.append("SET ACTIVE = FALSE, ");
		deleteConsultantSQL.append("    AUDITORID = ^, ");
		deleteConsultantSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteConsultantSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteConsultantSQL.append("WHERE CONSULTANTID = ^ ");
		deleteConsultantSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Consultants SQL.
		selectConsultantsSQL = new StringBuffer();
		selectConsultantsSQL.append("SELECT CONSULTANTID, ");
		selectConsultantsSQL.append("       AGENCYID, ");
		selectConsultantsSQL.append("       FIRSTNAME, ");
		selectConsultantsSQL.append("       LASTNAME, ");
		selectConsultantsSQL.append("       EMAILADDRESS, ");
		selectConsultantsSQL.append("       LOGIN, ");
		selectConsultantsSQL.append("       PWD, ");
		selectConsultantsSQL.append("       PWDHINT, ");
		selectConsultantsSQL.append("       SECRETWORD, ");
    selectConsultantsSQL.append("       SHOWPAGEHELP, ");
		selectConsultantsSQL.append("       SUPERUSER, ");
		selectConsultantsSQL.append("       JOBTITLE, ");
    selectConsultantsSQL.append("       CANVIEWDOCUMENTS, ");
    selectConsultantsSQL.append("       CANVIEWWAGES, ");
    // NEW -->
    selectConsultantsSQL.append("       SIGNATUREFILENAME, ");
    // <-- NEW
		selectConsultantsSQL.append("       CREATIONTIMESTAMP, ");
		selectConsultantsSQL.append("       AUDITORID, ");
		selectConsultantsSQL.append("       AUDITTIMESTAMP, ");
		selectConsultantsSQL.append("       ACTIVE, ");
		selectConsultantsSQL.append("       NOOFCHANGES ");
		selectConsultantsSQL.append("FROM CONSULTANT ");
		// Get select Consultant SQL.
		selectConsultantSQL = new StringBuffer(selectConsultantsSQL);
		selectConsultantSQL.append("WHERE CONSULTANTID = ^ ");
		// Get select Consultants for Agency SQL.
		selectConsultantsForAgencySQL = new StringBuffer(selectConsultantsSQL);
		selectConsultantsForAgencySQL.append("WHERE AGENCYID = ^ ");
		// Get select Active Consultants for Agency SQL.
		selectActiveConsultantsForAgencySQL = new StringBuffer(selectConsultantsForAgencySQL);
		selectActiveConsultantsForAgencySQL.append("AND ACTIVE = TRUE ");
		// Get select Consultant for Login SQL.
		selectConsultantForLoginSQL = new StringBuffer(selectActiveConsultantsForAgencySQL);
		selectConsultantForLoginSQL.append("AND LOGIN = ^ ");
	}

	public List<Consultant> getConsultantsForAgency(Integer clientId, boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveConsultantsForAgencySQL.toString());
		}
		else {
			sql = new StringBuffer(selectConsultantsForAgencySQL.toString());
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Consultant.class.getName());
		
	}
	public Consultant getConsultant(Integer consultantId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultantId);
		return (Consultant) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Consultant.class.getName());
	}
  // Gets the active Prospect Consultant for the Agency if one exists.
  public Consultant getProspectConsultant(Integer agencyId)
  {
    return getConsultantForLogin(agencyId, Constants.prospectLogin);
  }
	public Consultant getConsultantForLogin(Integer clientId, String login) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectConsultantForLoginSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, login);
		return (Consultant) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Consultant.class.getName());
	}
	public int insertConsultant(Consultant consultant, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAuditorSQL.toString());
		Integer newAuditorId = UpdateHandler.getInstance().getId(getJdbcTemplate(), "auditor");
		Utilities.replace(sql, newAuditorId);
		// swallow rowcount
		UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
        // 
		consultant.setConsultantId(newAuditorId);
		// Create a new local StringBuffer containing the parameterised SQL.
		sql = new StringBuffer(insertConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultant.getConsultantId());
		Utilities.replace(sql, consultant.getAgencyId());
		Utilities.replaceAndQuote(sql, consultant.getUser().getFirstName());
		Utilities.replaceAndQuote(sql, consultant.getUser().getLastName());
		Utilities.replaceAndQuote(sql, consultant.getUser().getEmailAddress());
		Utilities.replaceAndQuote(sql, consultant.getUser().getLogin());
		// on insert set password & passwordHint so they are forced to change on first login
		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(AgyConstants.RESETPWDHINT));
		Utilities.replaceAndQuote(sql, AgyConstants.RESETPWDHINT);
		// on insert set secretword as login - then force to change on first login
		Utilities.replaceAndQuote(sql, consultant.getUser().getLogin());
    Utilities.replaceAndQuoteNullable(sql, consultant.getJobTitle());
//    Utilities.replace(sql, consultant.getCanViewDocuments());
//    Utilities.replace(sql, consultant.getCanViewWages());
    Utilities.replaceAndQuoteNullable(sql, consultant.getSignatureFilename());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateConsultant(Consultant consultant, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultant.getAgencyId());
		Utilities.replaceAndQuote(sql, consultant.getUser().getFirstName());
		Utilities.replaceAndQuote(sql, consultant.getUser().getLastName());
		Utilities.replaceAndQuote(sql, consultant.getUser().getEmailAddress());
		Utilities.replaceAndQuote(sql, consultant.getUser().getLogin());
//		 following now set ONLY but user in separate functions		
//		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(consultant.getUser().getPwd()));
//		Utilities.replaceAndQuote(sql, consultant.getUser().getPwdHint());
//		Utilities.replaceAndQuote(sql, consultant.getSecretWord());
		Utilities.replace(sql, consultant.getUser().getShowPageHelp());
		Utilities.replace(sql, consultant.getUser().getSuperUser());
		Utilities.replaceAndQuoteNullable(sql, consultant.getJobTitle());
    Utilities.replace(sql, consultant.getCanViewDocuments());
    Utilities.replace(sql, consultant.getCanViewWages());
    Utilities.replaceAndQuoteNullable(sql, consultant.getSignatureFilename());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, consultant.getConsultantId());
		Utilities.replace(sql, consultant.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateConsultantPwd(Integer consultantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateConsultantPwdSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(newPwd));
		Utilities.replaceAndQuote(sql, pwdHint);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, consultantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
	public int updateConsultantSecretWord(Integer consultantId, String newSecretWord, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateConsultantSecretWordSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, newSecretWord);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, consultantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateConsultantShowPageHelp(Consultant consultant, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateConsultantShowPageHelpSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultant.getUser().getShowPageHelp());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, consultant.getConsultantId());
		Utilities.replace(sql, consultant.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int deleteConsultant(Integer consultantId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, consultantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public ConsultantEntity getConsultantEntity(Integer consultantId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultantId);
		return (ConsultantEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ConsultantEntity.class.getName());
	}

}
