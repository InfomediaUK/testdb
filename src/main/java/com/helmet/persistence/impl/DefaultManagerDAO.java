package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Manager;
import com.helmet.bean.ManagerEntity;
import com.helmet.persistence.ManagerDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultManagerDAO extends JdbcDaoSupport implements ManagerDAO {

	private static StringBuffer insertAuditorSQL;

	private static StringBuffer insertManagerSQL;

	private static StringBuffer updateManagerSQL;

	private static StringBuffer updateManagerPwdSQL;

	private static StringBuffer updateManagerSecretWordSQL;

	private static StringBuffer updateManagerShowPageHelpSQL;

	private static StringBuffer deleteManagerSQL;

	private static StringBuffer selectManagersSQL;

	private static StringBuffer selectManagersForClientSQL;

	private static StringBuffer selectActiveManagersForClientSQL;

	private static StringBuffer selectManagerSQL;

	private static StringBuffer selectManagerForLoginSQL;

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
		insertAuditorSQL.append("  'M' ");
		insertAuditorSQL.append(")  ");
		// Get insert Manager SQL.
		insertManagerSQL = new StringBuffer();
		insertManagerSQL.append("INSERT INTO MANAGER ");
		insertManagerSQL.append("(  ");
		insertManagerSQL.append("  MANAGERID, ");
		insertManagerSQL.append("  CLIENTID, ");
		insertManagerSQL.append("  FIRSTNAME, ");
		insertManagerSQL.append("  LASTNAME, ");
		insertManagerSQL.append("  EMAILADDRESS, ");
		insertManagerSQL.append("  LOGIN, ");
		insertManagerSQL.append("  PWD, ");
		insertManagerSQL.append("  PWDHINT, ");
		insertManagerSQL.append("  SECRETWORD, ");
		insertManagerSQL.append("  CREATIONTIMESTAMP, ");
		insertManagerSQL.append("  AUDITORID, ");
		insertManagerSQL.append("  AUDITTIMESTAMP ");
		insertManagerSQL.append(")  ");
		insertManagerSQL.append("VALUES  ");
		insertManagerSQL.append("(  ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^, ");
		insertManagerSQL.append("  ^ ");
		insertManagerSQL.append(")  ");
		// Get update Manager SQL.
		updateManagerSQL = new StringBuffer();
		updateManagerSQL.append("UPDATE MANAGER ");
		updateManagerSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateManagerSQL.append("     CLIENTID = ^, ");
		updateManagerSQL.append("     FIRSTNAME = ^, ");
		updateManagerSQL.append("     LASTNAME = ^, ");
		updateManagerSQL.append("     EMAILADDRESS = ^, ");
		updateManagerSQL.append("     LOGIN = ^, ");
//		updateManagerSQL.append("     PWD = ^, ");
//		updateManagerSQL.append("     PWDHINT = ^, ");
//		updateManagerSQL.append("     SECRETWORD = ^, ");
		updateManagerSQL.append("     SHOWPAGEHELP = ^, ");
		updateManagerSQL.append("     SUPERUSER = ^, ");
		updateManagerSQL.append("     AUDITORID = ^, ");
		updateManagerSQL.append("     AUDITTIMESTAMP = ^ ");
		updateManagerSQL.append("WHERE MANAGERID = ^ ");
		updateManagerSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ManagerPwd SQL.
		updateManagerPwdSQL = new StringBuffer();
		updateManagerPwdSQL.append("UPDATE MANAGER ");
		updateManagerPwdSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateManagerPwdSQL.append("     PWD = ^, ");
		updateManagerPwdSQL.append("     PWDHINT = ^, ");
		updateManagerPwdSQL.append("     AUDITORID = ^, ");
		updateManagerPwdSQL.append("     AUDITTIMESTAMP = ^ ");
		updateManagerPwdSQL.append("WHERE MANAGERID = ^ ");
		updateManagerPwdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ManagerSecretWord SQL.
		updateManagerSecretWordSQL = new StringBuffer();
		updateManagerSecretWordSQL.append("UPDATE MANAGER ");
		updateManagerSecretWordSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateManagerSecretWordSQL.append("     SECRETWORD = ^, ");
		updateManagerSecretWordSQL.append("     AUDITORID = ^, ");
		updateManagerSecretWordSQL.append("     AUDITTIMESTAMP = ^ ");
		updateManagerSecretWordSQL.append("WHERE MANAGERID = ^ ");
		updateManagerSecretWordSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ManagerShowPageHelp SQL.
		updateManagerShowPageHelpSQL = new StringBuffer();
		updateManagerShowPageHelpSQL.append("UPDATE MANAGER ");
		updateManagerShowPageHelpSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateManagerShowPageHelpSQL.append("     SHOWPAGEHELP = ^, ");
		updateManagerShowPageHelpSQL.append("     AUDITORID = ^, ");
		updateManagerShowPageHelpSQL.append("     AUDITTIMESTAMP = ^ ");
		updateManagerShowPageHelpSQL.append("WHERE MANAGERID = ^ ");
		updateManagerShowPageHelpSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Manager SQL.
		deleteManagerSQL = new StringBuffer();
//		deleteManagerSQL.append("DELETE FROM MANAGER ");
//		deleteManagerSQL.append("WHERE MANAGERID = ^ ");
//		deleteManagerSQL.append("AND   NOOFCHANGES = ^ ");
		deleteManagerSQL.append("UPDATE MANAGER ");
		deleteManagerSQL.append("SET ACTIVE = FALSE, ");
		deleteManagerSQL.append("    AUDITORID = ^, ");
		deleteManagerSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteManagerSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteManagerSQL.append("WHERE MANAGERID = ^ ");
		deleteManagerSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Managers SQL.
		selectManagersSQL = new StringBuffer();
		selectManagersSQL.append("SELECT MANAGERID, ");
		selectManagersSQL.append("       CLIENTID, ");
		selectManagersSQL.append("       FIRSTNAME, ");
		selectManagersSQL.append("       LASTNAME, ");
		selectManagersSQL.append("       EMAILADDRESS, ");
		selectManagersSQL.append("       LOGIN, ");
		selectManagersSQL.append("       PWD, ");
		selectManagersSQL.append("       PWDHINT, ");
		selectManagersSQL.append("       SECRETWORD, ");
		selectManagersSQL.append("       SHOWPAGEHELP, ");
		selectManagersSQL.append("       SUPERUSER, ");
		selectManagersSQL.append("       CREATIONTIMESTAMP, ");
		selectManagersSQL.append("       AUDITORID, ");
		selectManagersSQL.append("       AUDITTIMESTAMP, ");
		selectManagersSQL.append("       ACTIVE, ");
		selectManagersSQL.append("       NOOFCHANGES ");
		selectManagersSQL.append("FROM MANAGER ");
		// Get select Manager SQL.
		selectManagerSQL = new StringBuffer(selectManagersSQL);
		selectManagerSQL.append("WHERE MANAGERID = ^ ");
		// Get select Managers for Client SQL.
		selectManagersForClientSQL = new StringBuffer(selectManagersSQL);
		selectManagersForClientSQL.append("WHERE CLIENTID = ^ ");
		// Get select Active Managers for Client SQL.
		selectActiveManagersForClientSQL = new StringBuffer(selectManagersForClientSQL);
		selectActiveManagersForClientSQL.append("AND ACTIVE = TRUE ");
		// Get select Manager for Login SQL.
		selectManagerForLoginSQL = new StringBuffer(selectActiveManagersForClientSQL);
		selectManagerForLoginSQL.append("AND LOGIN = ^ ");
		// ORDER BY clauses
		selectManagersForClientSQL.append("ORDER BY FIRSTNAME, LASTNAME ");
		//
		selectActiveManagersForClientSQL.append("ORDER BY FIRSTNAME, LASTNAME ");
	}

	public List<Manager> getManagersForClient(Integer clientId, boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveManagersForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectManagersForClientSQL.toString());
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Manager.class.getName());
		
	}
	public Manager getManager(Integer managerId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return (Manager) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Manager.class.getName());
	}
	public Manager getManagerForLogin(Integer clientId, String login) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectManagerForLoginSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, login);
		return (Manager) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Manager.class.getName());
	}
	public int insertManager(Manager manager, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAuditorSQL.toString());
		Integer newAuditorId = UpdateHandler.getInstance().getId(getJdbcTemplate(), "auditor");
		Utilities.replace(sql, newAuditorId);
		// swallow rowcount
		UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
        // 
		manager.setManagerId(newAuditorId);
		// Create a new local StringBuffer containing the parameterised SQL.
		sql = new StringBuffer(insertManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, manager.getManagerId());
		Utilities.replace(sql, manager.getClientId());
		Utilities.replaceAndQuote(sql, manager.getUser().getFirstName());
		Utilities.replaceAndQuote(sql, manager.getUser().getLastName());
		Utilities.replaceAndQuote(sql, manager.getUser().getEmailAddress());
		Utilities.replaceAndQuote(sql, manager.getUser().getLogin());
//		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(MgrConstants.RESETPWDHINT));
//		Utilities.replaceAndQuote(sql, MgrConstants.RESETPWDHINT);
		Utilities.replaceAndQuote(sql, manager.getUser().getLogin());
// following now set so user has to set them on first login		
//		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(manager.getUser().getPwd()));
//		Utilities.replaceAndQuote(sql, manager.getUser().getPwdHint());
//		Utilities.replaceAndQuote(sql, manager.getSecretWord());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateManager(Manager manager, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, manager.getClientId());
		Utilities.replaceAndQuote(sql, manager.getUser().getFirstName());
		Utilities.replaceAndQuote(sql, manager.getUser().getLastName());
		Utilities.replaceAndQuote(sql, manager.getUser().getEmailAddress());
		Utilities.replaceAndQuote(sql, manager.getUser().getLogin());
// following now set ONLY but user in separate functions		
//		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(manager.getUser().getPwd()));
//		Utilities.replaceAndQuote(sql, manager.getUser().getPwdHint());
//		Utilities.replaceAndQuote(sql, manager.getSecretWord());
		Utilities.replace(sql, manager.getUser().getShowPageHelp());
		Utilities.replace(sql, manager.getUser().getSuperUser());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, manager.getManagerId());
		Utilities.replace(sql, manager.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int deleteManager(Integer managerId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateManagerPwd(Integer managerId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateManagerPwdSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, Utilities.encryptPassword(newPwd));
		Utilities.replaceAndQuote(sql, pwdHint);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
	public int updateManagerSecretWord(Integer managerId, String newSecretWord, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateManagerSecretWordSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, newSecretWord);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
	public int updateManagerShowPageHelp(Manager manager, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateManagerShowPageHelpSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, manager.getUser().getShowPageHelp());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, manager.getManagerId());
		Utilities.replace(sql, manager.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
	public ManagerEntity getManagerEntity(Integer managerId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return (ManagerEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ManagerEntity.class.getName());
	}

}
