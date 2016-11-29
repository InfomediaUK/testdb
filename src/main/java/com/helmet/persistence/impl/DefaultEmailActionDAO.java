package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.EmailAction;
import com.helmet.persistence.EmailActionDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultEmailActionDAO extends JdbcDaoSupport implements EmailActionDAO 
{

	private static StringBuffer insertEmailActionSQL;

	private static StringBuffer updateEmailActionSQL;

	private static StringBuffer deleteEmailActionSQL;

	private static StringBuffer selectEmailActionSQL;

	private static StringBuffer selectEmailActionForNameSQL;

	private static StringBuffer selectEmailActionsSQL;

	private static StringBuffer selectActiveEmailActionsSQL;

	public static void init() 
  {
		// Get insert EmailAction SQL.
		insertEmailActionSQL = new StringBuffer();
		insertEmailActionSQL.append("INSERT INTO EMAILACTION ");
		insertEmailActionSQL.append("(  ");
		insertEmailActionSQL.append("  EMAILACTIONID, ");
    insertEmailActionSQL.append("  NAME, ");
    insertEmailActionSQL.append("  SUBJECT, ");
    insertEmailActionSQL.append("  HTMLTEMPLATE, ");
    insertEmailActionSQL.append("  TEXTTEMPLATE, ");
    insertEmailActionSQL.append("  DEPENDSON, ");
    insertEmailActionSQL.append("  CREATIONTIMESTAMP, ");
    insertEmailActionSQL.append("  AUDITORID, ");
    insertEmailActionSQL.append("  AUDITTIMESTAMP ");
		insertEmailActionSQL.append(")  ");
		insertEmailActionSQL.append("VALUES  ");
		insertEmailActionSQL.append("(  ");
		insertEmailActionSQL.append("  ^, ");
    insertEmailActionSQL.append("  ^, ");
    insertEmailActionSQL.append("  ^, ");
    insertEmailActionSQL.append("  ^, ");
    insertEmailActionSQL.append("  ^, ");
    insertEmailActionSQL.append("  ^, ");
    insertEmailActionSQL.append("  ^, ");
    insertEmailActionSQL.append("  ^, ");
		insertEmailActionSQL.append("  ^ ");
		insertEmailActionSQL.append(")  ");
		// Get update EmailAction SQL.
		updateEmailActionSQL = new StringBuffer();
		updateEmailActionSQL.append("UPDATE EMAILACTION ");
		updateEmailActionSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateEmailActionSQL.append("     NAME = ^, ");
    updateEmailActionSQL.append("     SUBJECT = ^, ");
    updateEmailActionSQL.append("     HTMLTEMPLATE = ^, ");
    updateEmailActionSQL.append("     TEXTTEMPLATE = ^, ");
    updateEmailActionSQL.append("     DEPENDSON = ^, ");
    updateEmailActionSQL.append("     DISPLAYORDER = ^, ");
    updateEmailActionSQL.append("     AUDITORID = ^, ");
    updateEmailActionSQL.append("     AUDITTIMESTAMP = ^ ");
		updateEmailActionSQL.append("WHERE EMAILACTIONID = ^ ");
		updateEmailActionSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete EmailAction SQL.
		deleteEmailActionSQL = new StringBuffer();
		deleteEmailActionSQL.append("UPDATE EMAILACTION ");
		deleteEmailActionSQL.append("SET ACTIVE = FALSE, ");
    deleteEmailActionSQL.append("     AUDITORID = ^, ");
    deleteEmailActionSQL.append("     AUDITTIMESTAMP = ^, ");
    deleteEmailActionSQL.append("     NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteEmailActionSQL.append("WHERE EMAILACTIONID = ^ ");
		deleteEmailActionSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select EmailActions SQL.
		selectEmailActionsSQL = new StringBuffer();
		selectEmailActionsSQL.append("SELECT EMAILACTIONID, ");
    selectEmailActionsSQL.append("       NAME, ");
    selectEmailActionsSQL.append("       SUBJECT, ");
    selectEmailActionsSQL.append("       HTMLTEMPLATE, ");
    selectEmailActionsSQL.append("       TEXTTEMPLATE, ");
    selectEmailActionsSQL.append("       DEPENDSON, ");
    selectEmailActionsSQL.append("       DISPLAYORDER, ");
    selectEmailActionsSQL.append("       CREATIONTIMESTAMP, ");
    selectEmailActionsSQL.append("       AUDITORID, ");
    selectEmailActionsSQL.append("       AUDITTIMESTAMP, ");
    selectEmailActionsSQL.append("       ACTIVE, ");
		selectEmailActionsSQL.append("       NOOFCHANGES ");
		selectEmailActionsSQL.append("FROM EMAILACTION ");
		// Get select EmailAction SQL.
		selectEmailActionSQL = new StringBuffer(selectEmailActionsSQL);
		selectEmailActionSQL.append("WHERE EMAILACTIONID = ^ ");
		// Get select Active EmailActions SQL.
		selectActiveEmailActionsSQL = new StringBuffer(selectEmailActionsSQL);
		selectActiveEmailActionsSQL.append("WHERE ACTIVE = TRUE ");
		// Get select EmailAction for Name SQL.
		selectEmailActionForNameSQL = new StringBuffer(selectActiveEmailActionsSQL);
		selectEmailActionForNameSQL.append("AND NAME = ^ ");
    // Add Order By
    selectEmailActionsSQL.append("ORDER BY DISPLAYORDER, NAME");
    selectActiveEmailActionsSQL.append("ORDER BY DISPLAYORDER, NAME");
	}

	public int insertEmailAction(EmailAction emailAction, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertEmailActionSQL.toString());
		// Replace the parameters with supplied values.
		emailAction.setEmailActionId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "emailAction"));
		Utilities.replace(sql, emailAction.getEmailActionId());
    Utilities.replaceAndQuote(sql, emailAction.getName());
    Utilities.replaceAndQuote(sql, emailAction.getSubject());
    Utilities.replaceAndQuote(sql, emailAction.getHtmlTemplate());
    Utilities.replaceAndQuote(sql, emailAction.getTextTemplate());
    Utilities.replaceAndQuoteNullable(sql, emailAction.getDependsOn());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateEmailAction(EmailAction emailAction, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateEmailActionSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, emailAction.getName());
    Utilities.replaceAndQuote(sql, emailAction.getSubject());
    Utilities.replaceAndQuote(sql, emailAction.getHtmlTemplate());
    Utilities.replaceAndQuote(sql, emailAction.getTextTemplate());
    Utilities.replaceAndQuoteNullable(sql, emailAction.getDependsOn());
    Utilities.replace(sql, emailAction.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, emailAction.getEmailActionId());
		Utilities.replace(sql, emailAction.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteEmailAction(Integer emailActionId, Integer noOfChanges,
			Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteEmailActionSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, emailActionId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public EmailAction getEmailAction(Integer emailActionId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectEmailActionSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, emailActionId);
		return (EmailAction) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), EmailAction.class.getName());
	}

	public EmailAction getEmailActionForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectEmailActionForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (EmailAction) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), EmailAction.class.getName());
	}

	public List<EmailAction> getEmailActions() 
  {
		return getEmailActions(false);
	}

	public List<EmailAction> getEmailActions(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveEmailActionsSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectEmailActionsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), EmailAction.class.getName());
	}

	public List<EmailAction> getActiveEmailActions() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveEmailActionsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), EmailAction.class.getName());

	}

}
