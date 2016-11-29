package com.helmet.persistence;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UpdateHandler {

	private static UpdateHandler updateHandler;

	protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	public static UpdateHandler getInstance() {
		if (updateHandler == null) {
			// NOT instantiated yet so create it.
			synchronized (UpdateHandler.class) {
				// Only ONE thread at a time here!
				if (updateHandler == null) {
					updateHandler = new UpdateHandler();
				}
			}
		}
		return updateHandler;
	}

	private String getAuditTableName(String tableName) {
		String auditTableName = tableName + "AUDIT";
		if (auditTableName.length() > 32) {
			auditTableName = auditTableName.substring(0, 32);
		}
		return auditTableName; 
	}
	
	public int update(JdbcTemplate jt, String sql) {
		return update(jt, sql, true);
	}

	public int update(JdbcTemplate jt, String sql, boolean singleRowUpdate) {
		return update(jt, sql, singleRowUpdate, true);
	}

	public int update(JdbcTemplate jt, String sql, boolean singleRowUpdate, boolean doAudit) {

		int rc = 0;

		logger.debug(sql.toString());

		rc = jt.update(sql);
		
		if (singleRowUpdate && rc == 0) {
			throw new OptimisticLockingFailureException(rc + " rows affected - expecting single row. " + sql);			
		}
		
		// Check if History is required and Insert a row if necessary.
		if (doAudit) {
		    insertAuditRow(jt, sql);
		}
        return rc;
	}

	private void insertAuditRow(JdbcTemplate jt, String sql) {

		logger.debug("AAAUUUDDDIIITTT");
		
		// check if insert or update
		// add auditId
		
		String auditSQL = null;
		
		
	    // Find the index of the first space in the SQL.
	    int indexOf1stSpace = sql.indexOf(" ");
	    // The action is the portion of the SQL before the first space.
	    String action = sql.substring(0, indexOf1stSpace);

		if (action.equals("INSERT") || action.equals("UPDATE")) {
			
		    // Action is insert or update. Now find the index of the second
			// space in the SQL.
		    int indexOf2ndSpace = sql.indexOf(" ", indexOf1stSpace + 1);
			
		    // Determine the tablename i.e. the 2nd word in the sql.
		    String tableName = null;
		    tableName    = sql.substring(indexOf1stSpace + 1, indexOf2ndSpace);
	
		    if (action.equals("INSERT") && tableName.equals("INTO"))
		    {
		        // The action is 'insert' and the 2nd word is 'into', so the table name will be between the 2nd and 3rd space.
		        int indexOf3rdSpace = sql.indexOf(" ", indexOf2ndSpace + 1);
		        tableName    = sql.substring(indexOf2ndSpace + 1, indexOf3rdSpace);
		    }

		    if (tableName.equalsIgnoreCase("AUDITOR")) {

		    	// TODO shouldn't be doing this - should really be auditing only certain tables
		    	
		    }
		    else {
		    	
			    if (action.equalsIgnoreCase("INSERT")) {
					
				    int indexOf1stLeftBracket = sql.indexOf("(");
				    int indexOf2ndLeftBracket = sql.indexOf("(", indexOf1stLeftBracket + 1);
			    	auditSQL = "INSERT INTO " + getAuditTableName(tableName) + " " + 
	 	                       "(AUDITID, " +
			    	           sql.substring(indexOf1stLeftBracket + 1, indexOf2ndLeftBracket - 1) +
			    	           "(" + getId(jt, "AUDIT") + ", " +
			    	           sql.substring(indexOf2ndLeftBracket + 1);
			        
			    }
			    else if (action.equalsIgnoreCase("UPDATE")) {
			    	
			    	auditSQL = "INSERT INTO " + getAuditTableName(tableName) + " " + 
			    	           "SELECT " + getId(jt, "AUDIT") + ", * " +
			    	           "FROM " + tableName + " " +
			    	           sql.substring(sql.indexOf("WHERE")) + " + 1"; // noOfChanges
			    }

		    }
		
		}
		
		if (auditSQL != null) {
			
			// We need to write an audit record but don't audit the audit record!

			update(jt, auditSQL, true, false);

			
		}
			
    }	
	
	public Integer getId(JdbcTemplate jt, String tableName) {
		
		Integer id = 0;
		String columnName = tableName.toUpperCase() + "ID";

		// need to work out which type of database we are accessing

		// firebird/interbase
		// String sql = "SELECT " + columnName + " FROM GET" + columnName;

		// postgres sequence
		String sql = "SELECT nextval('GET" + columnName + "') AS " + columnName;

		SqlRowSet rs = jt.queryForRowSet(sql);

        if (rs.next()) {
			// One row found.
			id = rs.getInt(columnName);
		}

		return id;
	}

}
