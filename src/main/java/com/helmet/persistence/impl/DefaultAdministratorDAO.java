package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.application.admin.AdminConstants;
import com.helmet.bean.Administrator;
import com.helmet.bean.AdministratorEntity;
import com.helmet.persistence.AdministratorDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAdministratorDAO extends JdbcDaoSupport implements AdministratorDAO {

	private static StringBuffer insertAuditorSQL;

	private static StringBuffer insertAdministratorSQL;

	private static StringBuffer updateAdministratorSQL;

	private static StringBuffer updateAdministratorPwdSQL;

	private static StringBuffer updateAdministratorSecretWordSQL;

	private static StringBuffer deleteAdministratorSQL;

	private static StringBuffer selectAdministratorsSQL;

	private static StringBuffer selectAdministratorAuditsSQL;

	private static StringBuffer selectActiveAdministratorsSQL;

	private static StringBuffer selectAdministratorSQL;

	private static StringBuffer selectAdministratorForLoginSQL;

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
		insertAuditorSQL.append("  'A' ");
		insertAuditorSQL.append(")  ");
		// Get insert Administrator SQL.
		insertAdministratorSQL = new StringBuffer();
		insertAdministratorSQL.append("INSERT INTO ADMINISTRATOR ");
		insertAdministratorSQL.append("(  ");
		insertAdministratorSQL.append("  ADMINISTRATORID, ");
		insertAdministratorSQL.append("  FIRSTNAME, ");
		insertAdministratorSQL.append("  LASTNAME, ");
		insertAdministratorSQL.append("  EMAILADDRESS, ");
		insertAdministratorSQL.append("  LOGIN, ");
		insertAdministratorSQL.append("  PWD, ");
		insertAdministratorSQL.append("  PWDHINT, ");
		insertAdministratorSQL.append("  SECRETWORD, ");
		insertAdministratorSQL.append("  CREATIONTIMESTAMP, ");
		insertAdministratorSQL.append("  AUDITORID, ");
		insertAdministratorSQL.append("  AUDITTIMESTAMP ");
		insertAdministratorSQL.append(")  ");
		insertAdministratorSQL.append("VALUES  ");
		insertAdministratorSQL.append("(  ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^, ");
		insertAdministratorSQL.append("  ^ ");
		insertAdministratorSQL.append(")  ");
		// Get update Administrator SQL.
		updateAdministratorSQL = new StringBuffer();
		updateAdministratorSQL.append("UPDATE ADMINISTRATOR ");
		updateAdministratorSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAdministratorSQL.append("     FIRSTNAME = ^, ");
		updateAdministratorSQL.append("     LASTNAME = ^, ");
		updateAdministratorSQL.append("     EMAILADDRESS = ^, ");
		updateAdministratorSQL.append("     LOGIN = ^, ");
//		updateAdministratorSQL.append("     PWD = ^, ");
//		updateAdministratorSQL.append("     PWDHINT = ^, ");
		updateAdministratorSQL.append("     SHOWPAGEHELP = ^, ");
		updateAdministratorSQL.append("     SUPERUSER = ^, ");
		updateAdministratorSQL.append("     AUDITORID = ^, ");
		updateAdministratorSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAdministratorSQL.append("WHERE ADMINISTRATORID = ^ ");
		updateAdministratorSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update AdministratorPwd SQL.
		updateAdministratorPwdSQL = new StringBuffer();
		updateAdministratorPwdSQL.append("UPDATE ADMINISTRATOR ");
		updateAdministratorPwdSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAdministratorPwdSQL.append("     PWD = ^, ");
		updateAdministratorPwdSQL.append("     PWDHINT = ^, ");
		updateAdministratorPwdSQL.append("     AUDITORID = ^, ");
		updateAdministratorPwdSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAdministratorPwdSQL.append("WHERE ADMINISTRATORID = ^ ");
		updateAdministratorPwdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update AdministratorSecretWord SQL.
		updateAdministratorSecretWordSQL = new StringBuffer();
		updateAdministratorSecretWordSQL.append("UPDATE ADMINISTRATOR ");
		updateAdministratorSecretWordSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAdministratorSecretWordSQL.append("     SECRETWORD = ^, ");
		updateAdministratorSecretWordSQL.append("     AUDITORID = ^, ");
		updateAdministratorSecretWordSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAdministratorSecretWordSQL.append("WHERE ADMINISTRATORID = ^ ");
		updateAdministratorSecretWordSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Administrator SQL.
		deleteAdministratorSQL = new StringBuffer();
//		deleteAdministratorSQL.append("DELETE FROM ADMINISTRATOR ");
//		deleteAdministratorSQL.append("WHERE ADMINISTRATORID = ^ ");
//		deleteAdministratorSQL.append("AND   NOOFCHANGES = ^ ");
		deleteAdministratorSQL.append("UPDATE ADMINISTRATOR ");
		deleteAdministratorSQL.append("SET ACTIVE = FALSE, ");
		deleteAdministratorSQL.append("    AUDITORID = ^, ");
		deleteAdministratorSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAdministratorSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAdministratorSQL.append("WHERE ADMINISTRATORID = ^ ");
		deleteAdministratorSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Administrators SQL.
		selectAdministratorsSQL = new StringBuffer();
		selectAdministratorsSQL.append("SELECT ADMINISTRATORID, ");
		selectAdministratorsSQL.append("       FIRSTNAME, ");
		selectAdministratorsSQL.append("       LASTNAME, ");
		selectAdministratorsSQL.append("       EMAILADDRESS, ");
		selectAdministratorsSQL.append("       LOGIN, ");
		selectAdministratorsSQL.append("       PWD, ");
		selectAdministratorsSQL.append("       PWDHINT, ");
		selectAdministratorsSQL.append("       SECRETWORD, ");
		selectAdministratorsSQL.append("       SHOWONLYACTIVE, ");
		selectAdministratorsSQL.append("       SHOWPAGEHELP, ");
		selectAdministratorsSQL.append("       SUPERUSER, ");
		selectAdministratorsSQL.append("       CREATIONTIMESTAMP, ");
		selectAdministratorsSQL.append("       AUDITORID, ");
		selectAdministratorsSQL.append("       AUDITTIMESTAMP, ");
		selectAdministratorsSQL.append("       ACTIVE, ");
		selectAdministratorsSQL.append("       NOOFCHANGES ");
		// Get select AdministratorAudits SQL.
		selectAdministratorAuditsSQL = new StringBuffer(selectAdministratorsSQL);
		selectAdministratorAuditsSQL.append("FROM ADMINISTRATORAUDIT ");
		selectAdministratorAuditsSQL.append("ORDER BY ADMINISTRATORID ");
		//
		selectAdministratorsSQL.append("FROM ADMINISTRATOR ");
		// Get select Active Administrators SQL.
		selectActiveAdministratorsSQL = new StringBuffer(selectAdministratorsSQL);
		selectActiveAdministratorsSQL.append("WHERE ACTIVE = TRUE ");
		// Get select Administrator SQL.
		selectAdministratorSQL = new StringBuffer(selectAdministratorsSQL);
		selectAdministratorSQL.append("WHERE ADMINISTRATORID = ^ ");
		// Get select Administrator for Login SQL.
		selectAdministratorForLoginSQL = new StringBuffer(selectActiveAdministratorsSQL);
		selectAdministratorForLoginSQL.append("AND LOGIN = ^ ");
	}

	public List<Administrator> getAdministrators(boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveAdministratorsSQL.toString());
		}
		else {
			sql = new StringBuffer(selectAdministratorsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Administrator.class.getName());
		
	}

	public List<Administrator> getActiveAdministrators() {
		
		StringBuffer sql = new StringBuffer(selectActiveAdministratorsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Administrator.class.getName());
		
	}
	public Administrator getAdministrator(Integer administratorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, administratorId);
		return (Administrator) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Administrator.class.getName());
	}
	public AdministratorEntity getAdministratorEntity(Integer administratorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, administratorId);
		return (AdministratorEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdministratorEntity.class.getName());
	}
	public Administrator getAdministratorForLogin(String login) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdministratorForLoginSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, login);
		return (Administrator) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Administrator.class.getName());
	}
	public int insertAdministrator(Administrator administrator, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAuditorSQL.toString());
		Integer newAuditorId = UpdateHandler.getInstance().getId(getJdbcTemplate(), "auditor");
		Utilities.replace(sql, newAuditorId);
		// swallow rowcount
		UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
        // 
		administrator.setAdministratorId(newAuditorId);
		// Create a new local StringBuffer containing the parameterised SQL.
		sql = new StringBuffer(insertAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, administrator.getAdministratorId());
		Utilities.replaceAndQuote(sql, administrator.getUser().getFirstName());
		Utilities.replaceAndQuote(sql, administrator.getUser().getLastName());
		Utilities.replaceAndQuote(sql, administrator.getUser().getEmailAddress());
		Utilities.replaceAndQuote(sql, administrator.getUser().getLogin());
		// on insert set password & passwordHint so they are forced to change on first login
		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(AdminConstants.RESETPWDHINT));
		Utilities.replaceAndQuote(sql, AdminConstants.RESETPWDHINT);
		// on insert set secretword as login - then force to change on first login
		Utilities.replaceAndQuote(sql, administrator.getUser().getLogin());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateAdministrator(Administrator administrator, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, administrator.getUser().getFirstName());
		Utilities.replaceAndQuote(sql, administrator.getUser().getLastName());
		Utilities.replaceAndQuote(sql, administrator.getUser().getEmailAddress());
		Utilities.replaceAndQuote(sql, administrator.getUser().getLogin());
//		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(administrator.getUser().getPwd()));
//		Utilities.replaceAndQuote(sql, administrator.getUser().getPwdHint());
		Utilities.replace(sql, administrator.getUser().getShowPageHelp());
		Utilities.replace(sql, administrator.getUser().getSuperUser());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, administrator.getAdministratorId());
		Utilities.replace(sql, administrator.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateAdministratorPwd(Integer administratorId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAdministratorPwdSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(newPwd));
		Utilities.replaceAndQuote(sql, pwdHint);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, administratorId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateAdministratorSecretWord(Integer administratorId, String newSecretWord, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAdministratorSecretWordSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, newSecretWord);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, administratorId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int deleteAdministrator(Integer administratorId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, administratorId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public List<Administrator> getAdministratorAudits() {
		
		StringBuffer sql = new StringBuffer(selectAdministratorAuditsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Administrator.class.getName());
		
	}

}
