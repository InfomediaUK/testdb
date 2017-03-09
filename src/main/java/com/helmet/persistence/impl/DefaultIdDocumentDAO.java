package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.IdDocument;
import com.helmet.persistence.IdDocumentDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultIdDocumentDAO extends JdbcDaoSupport implements IdDocumentDAO 
{

	private static StringBuffer insertIdDocumentSQL;

	private static StringBuffer updateIdDocumentSQL;

  private static StringBuffer updateIdDocumentDisplayOrderSQL;

	private static StringBuffer deleteIdDocumentSQL;

	private static StringBuffer selectIdDocumentSQL;

	private static StringBuffer selectIdDocumentForNameSQL;

	private static StringBuffer selectIdDocumentForCodeSQL;

	private static StringBuffer selectIdDocumentsSQL;

	private static StringBuffer selectActiveIdDocumentsSQL;

	public static void init() 
  {
		// Get insert IdDocument SQL.
		insertIdDocumentSQL = new StringBuffer();
		insertIdDocumentSQL.append("INSERT INTO IDDOCUMENT ");
		insertIdDocumentSQL.append("(  ");
		insertIdDocumentSQL.append("  IDDOCUMENTID, ");
		insertIdDocumentSQL.append("  CODE, ");
    insertIdDocumentSQL.append("  NAME, ");
    insertIdDocumentSQL.append("  IDDOCUMENTTYPE, ");
    insertIdDocumentSQL.append("  REQUIRESVISA, ");
    insertIdDocumentSQL.append("  DISPLAYORDER, ");
    insertIdDocumentSQL.append("  CREATIONTIMESTAMP, ");
    insertIdDocumentSQL.append("  AUDITORID, ");
    insertIdDocumentSQL.append("  AUDITTIMESTAMP ");
		insertIdDocumentSQL.append(")  ");
		insertIdDocumentSQL.append("VALUES  ");
		insertIdDocumentSQL.append("(  ");
		insertIdDocumentSQL.append("  ^, ");
    insertIdDocumentSQL.append("  ^, ");
    insertIdDocumentSQL.append("  ^, ");
    insertIdDocumentSQL.append("  ^, ");
    insertIdDocumentSQL.append("  ^, ");
    insertIdDocumentSQL.append("  ^, ");
    insertIdDocumentSQL.append("  ^, ");
    insertIdDocumentSQL.append("  ^, ");
		insertIdDocumentSQL.append("  ^ ");
		insertIdDocumentSQL.append(")  ");
		// Get update IdDocument SQL.
    // NOTE. Updates DisplayOrder too...
		updateIdDocumentSQL = new StringBuffer();
		updateIdDocumentSQL.append("UPDATE IDDOCUMENT ");
		updateIdDocumentSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateIdDocumentSQL.append("     CODE = ^, ");
    updateIdDocumentSQL.append("     NAME = ^, ");
    updateIdDocumentSQL.append("     IDDOCUMENTTYPE = ^, ");
    updateIdDocumentSQL.append("     REQUIRESVISA = ^, ");
    updateIdDocumentSQL.append("     DISPLAYORDER = ^, ");
    updateIdDocumentSQL.append("     AUDITORID = ^, ");
    updateIdDocumentSQL.append("     AUDITTIMESTAMP = ^ ");
		updateIdDocumentSQL.append("WHERE IDDOCUMENTID = ^ ");
		updateIdDocumentSQL.append("AND   NOOFCHANGES = ^ ");
    // Get updateIdDocumentDisplayOrder SQL.
    updateIdDocumentDisplayOrderSQL = new StringBuffer();
    updateIdDocumentDisplayOrderSQL.append("UPDATE IDDOCUMENT ");
    updateIdDocumentDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
    updateIdDocumentDisplayOrderSQL.append("    AUDITORID = ^, ");
    updateIdDocumentDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
    updateIdDocumentDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateIdDocumentDisplayOrderSQL.append("WHERE IDDOCUMENTID = ^ ");
    updateIdDocumentDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete IdDocument SQL.
		deleteIdDocumentSQL = new StringBuffer();
		deleteIdDocumentSQL.append("UPDATE IDDOCUMENT ");
		deleteIdDocumentSQL.append("SET ACTIVE = FALSE, ");
    deleteIdDocumentSQL.append("    AUDITORID = ^, ");
    deleteIdDocumentSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteIdDocumentSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteIdDocumentSQL.append("WHERE IDDOCUMENTID = ^ ");
		deleteIdDocumentSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select IdDocuments SQL.
		selectIdDocumentsSQL = new StringBuffer();
		selectIdDocumentsSQL.append("SELECT IDDOCUMENTID, ");
		selectIdDocumentsSQL.append("       CODE, ");
    selectIdDocumentsSQL.append("       NAME, ");
    selectIdDocumentsSQL.append("       IDDOCUMENTTYPE, ");
    selectIdDocumentsSQL.append("       REQUIRESVISA, ");
    selectIdDocumentsSQL.append("       DISPLAYORDER, ");
    selectIdDocumentsSQL.append("       CREATIONTIMESTAMP, ");
    selectIdDocumentsSQL.append("       AUDITORID, ");
    selectIdDocumentsSQL.append("       AUDITTIMESTAMP, ");
    selectIdDocumentsSQL.append("       ACTIVE, ");
		selectIdDocumentsSQL.append("       NOOFCHANGES ");
		selectIdDocumentsSQL.append("FROM IDDOCUMENT ");
		// Get select IdDocument SQL.
		selectIdDocumentSQL = new StringBuffer(selectIdDocumentsSQL);
		selectIdDocumentSQL.append("WHERE IDDOCUMENTID = ^ ");
    // Get select IdDocument for Name SQL.
    selectIdDocumentForNameSQL = new StringBuffer(selectIdDocumentsSQL);
    selectIdDocumentForNameSQL.append("WHERE NAME = ^ ");
    // Get select IdDocument for Iso Code SQL.
    selectIdDocumentForCodeSQL = new StringBuffer(selectIdDocumentsSQL);
    selectIdDocumentForCodeSQL.append("WHERE CODE = ^ ");
		// Get select Active IdDocuments SQL.
		selectActiveIdDocumentsSQL = new StringBuffer(selectIdDocumentsSQL);
    selectActiveIdDocumentsSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveIdDocumentsSQL.append("ORDER BY DISPLAYORDER, NAME ");
    // Put order by on now...
    selectIdDocumentsSQL.append("ORDER BY DISPLAYORDER, NAME ");

	}

	public int insertIdDocument(IdDocument idDocument, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertIdDocumentSQL.toString());
		// Replace the parameters with supplied values.
		idDocument.setIdDocumentId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "idDocument"));
		Utilities.replace(sql, idDocument.getIdDocumentId());
		Utilities.replaceAndQuote(sql, idDocument.getCode());
    Utilities.replaceAndQuote(sql, idDocument.getName());
    Utilities.replace(sql, idDocument.getIdDocumentType());
    Utilities.replace(sql, idDocument.getRequiresVisa());
    Utilities.replace(sql, idDocument.getDisplayOrder());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateIdDocument(IdDocument idDocument, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateIdDocumentSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, idDocument.getCode());
    Utilities.replaceAndQuote(sql, idDocument.getName());
    Utilities.replace(sql, idDocument.getIdDocumentType());
    Utilities.replace(sql, idDocument.getRequiresVisa());
    Utilities.replace(sql, idDocument.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, idDocument.getIdDocumentId());
		Utilities.replace(sql, idDocument.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateIdDocumentDisplayOrder(IdDocument idDocument, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateIdDocumentDisplayOrderSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, idDocument.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, idDocument.getIdDocumentId());
    Utilities.replace(sql, idDocument.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteIdDocument(Integer idDocumentId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteIdDocumentSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, idDocumentId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public IdDocument getIdDocument(Integer idDocumentId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectIdDocumentSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, idDocumentId);
		return (IdDocument) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), IdDocument.class.getName());
	}

	public IdDocument getIdDocumentForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectIdDocumentForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (IdDocument) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IdDocument.class.getName());
	}

	public IdDocument getIdDocumentForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectIdDocumentForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (IdDocument) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), IdDocument.class.getName());
	}

	public List<IdDocument> getIdDocuments() 
  {
		return getIdDocuments(false);
	}

	public List<IdDocument> getIdDocuments(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveIdDocumentsSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectIdDocumentsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), IdDocument.class.getName());
	}

	public List<IdDocument> getActiveIdDocuments() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveIdDocumentsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), IdDocument.class.getName());

	}

}
