package com.helmet.persistence.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.SubcontractInvoice;
import com.helmet.bean.SubcontractInvoiceUser;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.SubcontractInvoiceDAO;
import com.helmet.persistence.Utilities;

public class DefaultSubcontractInvoiceDAO extends JdbcDaoSupport implements SubcontractInvoiceDAO 
{

  private static StringBuffer insertSubcontractCreditNoteSQL;

  private static StringBuffer insertSubcontractInvoiceSQL;

  private static StringBuffer updateSubcontractInvoiceSQL;

  private static StringBuffer updateSubcontractInvoiceValueSQL;

  private static StringBuffer updateSubcontractInvoiceSentDateSQL;

  private static StringBuffer updateSubcontractInvoicePaidDateSQL;

  private static StringBuffer updateSubcontractInvoicePaidDateAndNotesSQL;

  private static StringBuffer updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL;

  private static StringBuffer deleteSubcontractInvoiceSQL;

  private static StringBuffer selectSubcontractInvoiceSQL;

  private static StringBuffer selectSubcontractInvoiceUserSQL;

  private static StringBuffer selectSubcontractInvoicesSQL;

  private static StringBuffer selectSubcontractInvoiceUsersSQL;

  private static StringBuffer selectSubcontractInvoiceUsersInListSQL;

  private static StringBuffer selectSubcontractInvoiceUsersForRemittanceAdviceSQL;

  private static StringBuffer selectActiveSubcontractInvoicesSQL;

  private static StringBuffer selectActiveSubcontractInvoiceUsersSQL;
  
  private static StringBuffer selectSubcontractInvoiceUsersForAgencySearchSQL;
  
  private static StringBuffer selectSubcontractInvoiceUsersForAgencySearchWithDateRangeSQL;

	public static void init() 
  {
    // Get insert SubcontractCreditNote SQL.
    insertSubcontractCreditNoteSQL = new StringBuffer();
    insertSubcontractCreditNoteSQL.append("INSERT INTO SUBCONTRACTINVOICE ");
    insertSubcontractCreditNoteSQL.append("(  ");
    insertSubcontractCreditNoteSQL.append("  SUBCONTRACTINVOICEID, ");
    insertSubcontractCreditNoteSQL.append("  FROMAGENCYID, ");
    insertSubcontractCreditNoteSQL.append("  TOAGENCYID, ");
    insertSubcontractCreditNoteSQL.append("  DATE, ");
    insertSubcontractCreditNoteSQL.append("  CLIENTID, ");
    insertSubcontractCreditNoteSQL.append("  SITEID, ");
    insertSubcontractCreditNoteSQL.append("  LOCATIONID, ");
    insertSubcontractCreditNoteSQL.append("  JOBPROFILEID, ");
    insertSubcontractCreditNoteSQL.append("  STARTDATE, ");
    insertSubcontractCreditNoteSQL.append("  ENDDATE, ");
    insertSubcontractCreditNoteSQL.append("  VALUE, ");
    insertSubcontractCreditNoteSQL.append("  NOTES, ");
    insertSubcontractCreditNoteSQL.append("  RELATEDSUBCONTRACTINVOICEID, ");
    insertSubcontractCreditNoteSQL.append("  CREATIONTIMESTAMP, ");
    insertSubcontractCreditNoteSQL.append("  AUDITORID, ");
    insertSubcontractCreditNoteSQL.append("  AUDITTIMESTAMP ");
    insertSubcontractCreditNoteSQL.append(")  ");
    insertSubcontractCreditNoteSQL.append("VALUES  ");
    insertSubcontractCreditNoteSQL.append("(  ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^, ");
    insertSubcontractCreditNoteSQL.append("  ^ ");
    insertSubcontractCreditNoteSQL.append(")  ");
		// Get insert SubcontractInvoice SQL.
		insertSubcontractInvoiceSQL = new StringBuffer();
		insertSubcontractInvoiceSQL.append("INSERT INTO SUBCONTRACTINVOICE ");
		insertSubcontractInvoiceSQL.append("(  ");
    insertSubcontractInvoiceSQL.append("  SUBCONTRACTINVOICEID, ");
    insertSubcontractInvoiceSQL.append("  FROMAGENCYID, ");
    insertSubcontractInvoiceSQL.append("  TOAGENCYID, ");
		insertSubcontractInvoiceSQL.append("  DATE, ");
    insertSubcontractInvoiceSQL.append("  CLIENTID, ");
    insertSubcontractInvoiceSQL.append("  SITEID, ");
    insertSubcontractInvoiceSQL.append("  LOCATIONID, ");
    insertSubcontractInvoiceSQL.append("  JOBPROFILEID, ");
    insertSubcontractInvoiceSQL.append("  STARTDATE, ");
    insertSubcontractInvoiceSQL.append("  ENDDATE, ");
    insertSubcontractInvoiceSQL.append("  VALUE, ");
    insertSubcontractInvoiceSQL.append("  NOTES, ");
    insertSubcontractInvoiceSQL.append("  CREATIONTIMESTAMP, ");
		insertSubcontractInvoiceSQL.append("  AUDITORID, ");
		insertSubcontractInvoiceSQL.append("  AUDITTIMESTAMP ");
		insertSubcontractInvoiceSQL.append(")  ");
		insertSubcontractInvoiceSQL.append("VALUES  ");
		insertSubcontractInvoiceSQL.append("(  ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
    insertSubcontractInvoiceSQL.append("  ^, ");
		insertSubcontractInvoiceSQL.append("  ^ ");
		insertSubcontractInvoiceSQL.append(")  ");
		// Get update SubcontractInvoice SQL.
		updateSubcontractInvoiceSQL = new StringBuffer();
		updateSubcontractInvoiceSQL.append("UPDATE SUBCONTRACTINVOICE ");
		updateSubcontractInvoiceSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateSubcontractInvoiceSQL.append("     DATE = ^, ");
    updateSubcontractInvoiceSQL.append("     VALUE = ^, ");
    updateSubcontractInvoiceSQL.append("     NOTES = ^, ");
    updateSubcontractInvoiceSQL.append("     AUDITORID = ^, ");
		updateSubcontractInvoiceSQL.append("     AUDITTIMESTAMP = ^ ");
		updateSubcontractInvoiceSQL.append("WHERE SUBCONTRACTINVOICEID = ^ ");
		updateSubcontractInvoiceSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update SubcontractInvoice Value SQL.
    updateSubcontractInvoiceValueSQL = new StringBuffer();
    updateSubcontractInvoiceValueSQL.append("UPDATE SUBCONTRACTINVOICE ");
    updateSubcontractInvoiceValueSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateSubcontractInvoiceValueSQL.append("     VALUE = ^, ");
    updateSubcontractInvoiceValueSQL.append("     AUDITORID = ^, ");
    updateSubcontractInvoiceValueSQL.append("     AUDITTIMESTAMP = ^ ");
    updateSubcontractInvoiceValueSQL.append("WHERE SUBCONTRACTINVOICEID = ^ ");
    updateSubcontractInvoiceValueSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update SubcontractInvoice Sent Date SQL.
    updateSubcontractInvoiceSentDateSQL = new StringBuffer();
    updateSubcontractInvoiceSentDateSQL.append("UPDATE SUBCONTRACTINVOICE ");
    updateSubcontractInvoiceSentDateSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateSubcontractInvoiceSentDateSQL.append("     SENTDATE = ^, ");
    updateSubcontractInvoiceSentDateSQL.append("     AUDITORID = ^, ");
    updateSubcontractInvoiceSentDateSQL.append("     AUDITTIMESTAMP = ^ ");
    updateSubcontractInvoiceSentDateSQL.append("WHERE SUBCONTRACTINVOICEID = ^ ");
    updateSubcontractInvoiceSentDateSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update SubcontractInvoice Paid Date SQL.
    updateSubcontractInvoicePaidDateSQL = new StringBuffer();
    updateSubcontractInvoicePaidDateSQL.append("UPDATE SUBCONTRACTINVOICE ");
    updateSubcontractInvoicePaidDateSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateSubcontractInvoicePaidDateSQL.append("     PAIDDATE = ^, ");
    updateSubcontractInvoicePaidDateSQL.append("     REMITTANCEADVICE = ^, ");
    updateSubcontractInvoicePaidDateSQL.append("     AUDITORID = ^, ");
    updateSubcontractInvoicePaidDateSQL.append("     AUDITTIMESTAMP = ^ ");
    updateSubcontractInvoicePaidDateSQL.append("WHERE SUBCONTRACTINVOICEID = ^ ");
    updateSubcontractInvoicePaidDateSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update SubcontractInvoice Paid Date & Notes SQL.
    updateSubcontractInvoicePaidDateAndNotesSQL = new StringBuffer();
    updateSubcontractInvoicePaidDateAndNotesSQL.append("UPDATE SUBCONTRACTINVOICE ");
    updateSubcontractInvoicePaidDateAndNotesSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateSubcontractInvoicePaidDateAndNotesSQL.append("     PAIDDATE = ^, ");
    updateSubcontractInvoicePaidDateAndNotesSQL.append("     REMITTANCEADVICE = ^, ");
    updateSubcontractInvoicePaidDateAndNotesSQL.append("     NOTES = ^, ");
    updateSubcontractInvoicePaidDateAndNotesSQL.append("     AUDITORID = ^, ");
    updateSubcontractInvoicePaidDateAndNotesSQL.append("     AUDITTIMESTAMP = ^ ");
    updateSubcontractInvoicePaidDateAndNotesSQL.append("WHERE SUBCONTRACTINVOICEID = ^ ");
    updateSubcontractInvoicePaidDateAndNotesSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update SubcontractInvoice Related Subcontract Invoice Id SQL.
    updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL = new StringBuffer();
    updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL.append("UPDATE SUBCONTRACTINVOICE ");
    updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL.append("     RELATEDSUBCONTRACTINVOICEID = ^, ");
    updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL.append("     AUDITORID = ^, ");
    updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL.append("     AUDITTIMESTAMP = ^ ");
    updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL.append("WHERE SUBCONTRACTINVOICEID = ^ ");
    updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete SubcontractInvoice SQL.
		deleteSubcontractInvoiceSQL = new StringBuffer();
		deleteSubcontractInvoiceSQL.append("UPDATE SUBCONTRACTINVOICE ");
		deleteSubcontractInvoiceSQL.append("SET ACTIVE = FALSE, ");
    deleteSubcontractInvoiceSQL.append("    NOTES = ^, ");
    deleteSubcontractInvoiceSQL.append("    AUDITORID = ^, ");
		deleteSubcontractInvoiceSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteSubcontractInvoiceSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteSubcontractInvoiceSQL.append("WHERE SUBCONTRACTINVOICEID = ^ ");
		deleteSubcontractInvoiceSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select SubcontractInvoices SQL.
		selectSubcontractInvoicesSQL = new StringBuffer();
		selectSubcontractInvoicesSQL.append("SELECT SI.SUBCONTRACTINVOICEID, ");
		selectSubcontractInvoicesSQL.append("       SI.FROMAGENCYID, ");
		selectSubcontractInvoicesSQL.append("       SI.TOAGENCYID, ");
		selectSubcontractInvoicesSQL.append("       SI.DATE, ");
    selectSubcontractInvoicesSQL.append("       SI.CLIENTID, ");
    selectSubcontractInvoicesSQL.append("       SI.SITEID, ");
    selectSubcontractInvoicesSQL.append("       SI.LOCATIONID, ");
    selectSubcontractInvoicesSQL.append("       SI.JOBPROFILEID, ");
    selectSubcontractInvoicesSQL.append("       SI.STARTDATE, ");
    selectSubcontractInvoicesSQL.append("       SI.ENDDATE, ");
    selectSubcontractInvoicesSQL.append("       SI.VALUE, ");
    selectSubcontractInvoicesSQL.append("       SI.NOTES, ");
    selectSubcontractInvoicesSQL.append("       SI.SENTDATE, ");
    selectSubcontractInvoicesSQL.append("       SI.PAIDDATE, ");
    selectSubcontractInvoicesSQL.append("       SI.RELATEDSUBCONTRACTINVOICEID, ");
    selectSubcontractInvoicesSQL.append("       SI.REMITTANCEADVICE, ");
    selectSubcontractInvoicesSQL.append("       SI.CREATIONTIMESTAMP, ");
		selectSubcontractInvoicesSQL.append("       SI.AUDITORID, ");
		selectSubcontractInvoicesSQL.append("       SI.AUDITTIMESTAMP, ");
		selectSubcontractInvoicesSQL.append("       SI.ACTIVE, ");
		selectSubcontractInvoicesSQL.append("       SI.NOOFCHANGES ");
    // Get select SubcontractInvoiceUsers SQL.
    selectSubcontractInvoiceUsersSQL = new StringBuffer(selectSubcontractInvoicesSQL);
    selectSubcontractInvoiceUsersSQL.append(", ");
    selectSubcontractInvoiceUsersSQL.append("       AFROM.NAME AS FROMAGENCYNAME, ");
    selectSubcontractInvoiceUsersSQL.append("       ATO.NAME AS TOAGENCYNAME, ");
    selectSubcontractInvoiceUsersSQL.append("       C.NHSNAME AS CLIENTNHSNAME, ");
    selectSubcontractInvoiceUsersSQL.append("       C.NAME AS CLIENTNAME, ");
    selectSubcontractInvoiceUsersSQL.append("       C.NHSNAME AS CLIENTNHSNAME, ");
    selectSubcontractInvoiceUsersSQL.append("       S.NAME AS SITENAME, ");
    selectSubcontractInvoiceUsersSQL.append("       S.NHSLOCATION AS SITENHSLOCATION, ");
    selectSubcontractInvoiceUsersSQL.append("       L.NAME AS LOCATIONNAME, ");
    selectSubcontractInvoiceUsersSQL.append("       L.NHSWARD AS LOCATIONNHSWARD, ");
    selectSubcontractInvoiceUsersSQL.append("       JF.NAME AS JOBFAMILYNAME, ");
    selectSubcontractInvoiceUsersSQL.append("       JF.CODE AS JOBFAMILYCODE, ");
    selectSubcontractInvoiceUsersSQL.append("       JSF.NAME AS JOBSUBFAMILYNAME, ");
    selectSubcontractInvoiceUsersSQL.append("       JSF.CODE AS JOBSUBFAMILYCODE, ");
    selectSubcontractInvoiceUsersSQL.append("       JP.NAME AS JOBPROFILENAME, ");
    selectSubcontractInvoiceUsersSQL.append("       JP.CODE AS JOBPROFILECODE, ");
    selectSubcontractInvoiceUsersSQL.append("       JP.NHSASSIGNMENT AS JOBPROFILENHSASSIGNMENT ");
    selectSubcontractInvoiceUsersSQL.append("FROM SUBCONTRACTINVOICE SI ");
    selectSubcontractInvoiceUsersSQL.append("JOIN AGENCY AFROM ");
    selectSubcontractInvoiceUsersSQL.append("ON  AFROM.AGENCYID = SI.FROMAGENCYID ");
    selectSubcontractInvoiceUsersSQL.append("JOIN AGENCY ATO ");
    selectSubcontractInvoiceUsersSQL.append("ON  ATO.AGENCYID = SI.TOAGENCYID ");
    selectSubcontractInvoiceUsersSQL.append("JOIN CLIENT C ");
    selectSubcontractInvoiceUsersSQL.append("ON  C.CLIENTID = SI.CLIENTID ");
    selectSubcontractInvoiceUsersSQL.append("JOIN SITE S ");
    selectSubcontractInvoiceUsersSQL.append("ON  S.SITEID = SI.SITEID ");
    selectSubcontractInvoiceUsersSQL.append("JOIN JOBPROFILE JP ");
    selectSubcontractInvoiceUsersSQL.append("ON  JP.JOBPROFILEID = SI.JOBPROFILEID ");
    selectSubcontractInvoiceUsersSQL.append("JOIN JOBSUBFAMILY JSF ");
    selectSubcontractInvoiceUsersSQL.append("ON  JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
    selectSubcontractInvoiceUsersSQL.append("JOIN JOBFAMILY JF ");
    selectSubcontractInvoiceUsersSQL.append("ON  JF.JOBFAMILYID = JSF.JOBFAMILYID ");
    selectSubcontractInvoiceUsersSQL.append("JOIN LOCATION L ");
    selectSubcontractInvoiceUsersSQL.append("ON  L.LOCATIONID = SI.LOCATIONID ");
    // Add FROM to Get SubcontractInvoices SQL.
    selectSubcontractInvoicesSQL.append("FROM SUBCONTRACTINVOICE SI ");
		// Get select Active SubcontractInvoices SQL.
		selectActiveSubcontractInvoicesSQL = new StringBuffer(selectSubcontractInvoicesSQL);
    selectActiveSubcontractInvoicesSQL.append("WHERE SI.FROMAGENCYID = ^ ");
    selectActiveSubcontractInvoicesSQL.append("AND SI.ACTIVE = TRUE ");
    // Get select Active SubcontractInvoiceUsers for Uplift SQL.
    selectActiveSubcontractInvoiceUsersSQL = new StringBuffer(selectSubcontractInvoiceUsersSQL);
    selectActiveSubcontractInvoiceUsersSQL.append("WHERE SI.FROMAGENCYID = ^ ");
    selectActiveSubcontractInvoiceUsersSQL.append("AND SI.ACTIVE = TRUE ");
    // Get select SubcontractInvoiceUsers in List SQL. Comma delimited list of SubcontractInvoiceId.
    selectSubcontractInvoiceUsersInListSQL = new StringBuffer(selectSubcontractInvoiceUsersSQL);
    selectSubcontractInvoiceUsersInListSQL.append("WHERE SI.TOAGENCYID = ^ ");
    selectSubcontractInvoiceUsersInListSQL.append("AND SI.SUBCONTRACTINVOICEID IN ( ^ ) ");
    // Get select SubcontractInvoiceUsers for Remittance Advice SQL. Comma delimited list of SubcontractInvoiceId.
    selectSubcontractInvoiceUsersForRemittanceAdviceSQL = new StringBuffer(selectSubcontractInvoiceUsersSQL);
    selectSubcontractInvoiceUsersForRemittanceAdviceSQL.append("WHERE SI.TOAGENCYID = ^ ");
    selectSubcontractInvoiceUsersForRemittanceAdviceSQL.append("AND SI.REMITTANCEADVICE = ^ ");
    //
    selectSubcontractInvoiceUsersForAgencySearchSQL = new StringBuffer(selectActiveSubcontractInvoiceUsersSQL);
    selectSubcontractInvoiceUsersForAgencySearchSQL.append("AND ( ^ IS NULL OR SI.SUBCONTRACTINVOICEID = ^ ) ");
    selectSubcontractInvoiceUsersForAgencySearchSQL.append("AND ( ^ IS NULL OR SI.CLIENTID = ^ ) ");
    selectSubcontractInvoiceUsersForAgencySearchSQL.append("AND ( ^ IS NULL OR SI.SITEID = ^ ) ");
    selectSubcontractInvoiceUsersForAgencySearchSQL.append("AND ( ^ IS NULL OR SI.LOCATIONID = ^ ) ");
    selectSubcontractInvoiceUsersForAgencySearchSQL.append("AND ( ^ IS NULL OR SI.JOBPROFILEID = ^ ) ");
    //
    selectSubcontractInvoiceUsersForAgencySearchWithDateRangeSQL = new StringBuffer(selectSubcontractInvoiceUsersForAgencySearchSQL);
    selectSubcontractInvoiceUsersForAgencySearchWithDateRangeSQL.append("AND SI.DATE BETWEEN ^ AND ^ ");
    // Get select SubcontractInvoice SQL.
    selectSubcontractInvoiceSQL = new StringBuffer(selectSubcontractInvoicesSQL);
    selectSubcontractInvoiceSQL.append("WHERE SI.SUBCONTRACTINVOICEID = ^ ");
    // Get select SubcontractInvoiceUser SQL.
    selectSubcontractInvoiceUserSQL = new StringBuffer(selectSubcontractInvoiceUsersSQL);
    selectSubcontractInvoiceUserSQL.append("WHERE SI.SUBCONTRACTINVOICEID = ^ ");
		// ORDER BY 
    selectSubcontractInvoicesSQL.append("ORDER BY SI.DATE ");
    selectActiveSubcontractInvoicesSQL.append("ORDER BY SI.DATE ");
    selectSubcontractInvoiceUsersSQL.append("ORDER BY SI.DATE ");
    selectActiveSubcontractInvoiceUsersSQL.append("ORDER BY SI.DATE ");
    selectSubcontractInvoiceUsersInListSQL.append("ORDER BY CLIENTNAME, SI.DATE ");
    selectSubcontractInvoiceUsersForRemittanceAdviceSQL.append("ORDER BY CLIENTNAME, SI.DATE ");

	}

  public int insertSubcontractCreditNote(SubcontractInvoice subcontractInvoice, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(insertSubcontractCreditNoteSQL.toString());
    // Replace the parameters with supplied values.
    // Note. The SubcontractInvoiceId is NEGATIVE for a Credit Note.
    subcontractInvoice.setSubcontractInvoiceId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "subcontractInvoice") * -1);
    Utilities.replace(sql, subcontractInvoice.getSubcontractInvoiceId());
    Utilities.replace(sql, subcontractInvoice.getFromAgencyId());
    Utilities.replace(sql, subcontractInvoice.getToAgencyId());
    Utilities.replaceAndQuote(sql, subcontractInvoice.getDate());
    Utilities.replace(sql, subcontractInvoice.getClientId());
    Utilities.replace(sql, subcontractInvoice.getSiteId());
    Utilities.replace(sql, subcontractInvoice.getLocationId());
    Utilities.replace(sql, subcontractInvoice.getJobProfileId());
    Utilities.replaceAndQuote(sql, subcontractInvoice.getStartDate());
    Utilities.replaceAndQuote(sql, subcontractInvoice.getEndDate());
    Utilities.replace(sql, subcontractInvoice.getValue());
    Utilities.replaceAndQuote(sql, subcontractInvoice.getNotes());
    Utilities.replace(sql, subcontractInvoice.getRelatedSubcontractInvoiceId());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
	public int insertSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertSubcontractInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		subcontractInvoice.setSubcontractInvoiceId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "subcontractInvoice"));
		Utilities.replace(sql, subcontractInvoice.getSubcontractInvoiceId());
		Utilities.replace(sql, subcontractInvoice.getFromAgencyId());
		Utilities.replace(sql, subcontractInvoice.getToAgencyId());
		Utilities.replaceAndQuote(sql, subcontractInvoice.getDate());
    Utilities.replace(sql, subcontractInvoice.getClientId());
    Utilities.replace(sql, subcontractInvoice.getSiteId());
    Utilities.replace(sql, subcontractInvoice.getLocationId());
    Utilities.replace(sql, subcontractInvoice.getJobProfileId());
    Utilities.replaceAndQuote(sql, subcontractInvoice.getStartDate());
    Utilities.replaceAndQuote(sql, subcontractInvoice.getEndDate());
    Utilities.replace(sql, subcontractInvoice.getValue());
    Utilities.replaceAndQuote(sql, subcontractInvoice.getNotes());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateSubcontractInvoiceSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoice.getValue());
    Utilities.replaceAndQuote(sql, subcontractInvoice.getNotes());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, subcontractInvoice.getSubcontractInvoiceId());
		Utilities.replace(sql, subcontractInvoice.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateSubcontractInvoiceValue(SubcontractInvoice subcontractInvoice, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateSubcontractInvoiceValueSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoice.getValue());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, subcontractInvoice.getSubcontractInvoiceId());
    Utilities.replace(sql, subcontractInvoice.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int updateSubcontractInvoiceSentDate(SubcontractInvoice subcontractInvoice, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateSubcontractInvoiceSentDateSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, subcontractInvoice.getSentDate());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, subcontractInvoice.getSubcontractInvoiceId());
    Utilities.replace(sql, subcontractInvoice.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int updateSubcontractInvoicePaidDate(SubcontractInvoice subcontractInvoice, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = null;
    if (StringUtils.isEmpty(subcontractInvoice.getNotes()))
    {
      sql = new StringBuffer(updateSubcontractInvoicePaidDateSQL.toString());
      // Replace the parameters with supplied values.
      Utilities.replaceAndQuote(sql, subcontractInvoice.getPaidDate());
      Utilities.replaceAndQuote(sql, subcontractInvoice.getRemittanceAdvice());
    }
    else
    {
      sql = new StringBuffer(updateSubcontractInvoicePaidDateAndNotesSQL.toString());
      // Replace the parameters with supplied values.
      Utilities.replaceAndQuote(sql, subcontractInvoice.getPaidDate());
      Utilities.replaceAndQuote(sql, subcontractInvoice.getRemittanceAdvice());
      Utilities.replaceAndQuote(sql, subcontractInvoice.getNotes());
    }
    // Replace the rest of the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, subcontractInvoice.getSubcontractInvoiceId());
    Utilities.replace(sql, subcontractInvoice.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int updateSubcontractInvoiceRelatedSubcontractInvoiceId(SubcontractInvoice subcontractInvoice, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateSubcontractInvoiceRelatedSubcontractInvoiceIdSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoice.getRelatedSubcontractInvoiceId());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, subcontractInvoice.getSubcontractInvoiceId());
    Utilities.replace(sql, subcontractInvoice.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteSubcontractInvoiceSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replaceAndQuoteNullable(sql, subcontractInvoice.getNotes());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, subcontractInvoice.getSubcontractInvoiceId());
		Utilities.replace(sql, subcontractInvoice.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public SubcontractInvoice getSubcontractInvoice(Integer subcontractInvoiceId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectSubcontractInvoiceSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoiceId);
    return (SubcontractInvoice) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoice.class.getName());
  }

  public SubcontractInvoiceUser getSubcontractInvoiceUser(Integer subcontractInvoiceId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectSubcontractInvoiceUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoiceId);
    return (SubcontractInvoiceUser) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceUser.class.getName());
  }

	public List<SubcontractInvoice> getSubcontractInvoices(Integer agencyId, boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveSubcontractInvoicesSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectSubcontractInvoicesSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoice.class.getName());
	}

  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsers(Integer agencyId, boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveSubcontractInvoiceUsersSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectSubcontractInvoiceUsersSQL.toString()); 
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceUser.class.getName());
  }

  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersInList(Integer agencyId, String subcontractInvoiceIdList)
  {
    StringBuffer sql = new StringBuffer(selectSubcontractInvoiceUsersInListSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, subcontractInvoiceIdList);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceUser.class.getName());
  }
  
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersForRemittanceAdvice(Integer agencyId, String remittanceAdvice)
  {
    StringBuffer sql = new StringBuffer(selectSubcontractInvoiceUsersForRemittanceAdviceSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, remittanceAdvice);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceUser.class.getName());
  }
  
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersForAgency(Integer fromAgencyId, Integer subcontractInvoiceId, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer status, Date fromDate, Date toDate)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = null;
    if (fromDate == null)
    {
      sql = new StringBuffer(selectSubcontractInvoiceUsersForAgencySearchSQL.toString());
    }
    else
    {
      sql = new StringBuffer(selectSubcontractInvoiceUsersForAgencySearchWithDateRangeSQL.toString());
    }

    // Replace the parameters with supplied values.
    Utilities.replace(sql, fromAgencyId);

    Utilities.replace(sql, subcontractInvoiceId);
    Utilities.replace(sql, subcontractInvoiceId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, jobProfileId);

    if (fromDate != null)
    {
      if (toDate == null)
      {
        // FORCE for last year
        Utilities.replace(sql, "CURRENT_DATE - INTERVAL '1 YEAR'");
        Utilities.replace(sql, "CURRENT_DATE");
      }
      else
      {
        Utilities.replaceAndQuote(sql, fromDate);
        Utilities.replaceAndQuote(sql, toDate);
      }
    }
    if (status != null)
    {
      if (status.equals(SubcontractInvoice.SUBCONTRACTINVOICE_STATUS_SENT))
      {
        sql.append("  AND SI.SENTDATE IS NOT NULL ");
      }
      if (status.equals(SubcontractInvoice.SUBCONTRACTINVOICE_STATUS_UNPAID))
      {
        sql.append("  AND SI.PAIDDATE IS NULL ");
      }
      if (status.equals(SubcontractInvoice.SUBCONTRACTINVOICE_STATUS_PAID))
      {
        sql.append("  AND SI.PAIDDATE IS NOT NULL ");
      }
      if (status.equals(SubcontractInvoice.SUBCONTRACTINVOICE_STATUS_CREDITED))
      {
        sql.append("  AND ( SI.RELATEDSUBCONTRACTINVOICEID IS NOT NULL AND SI.RELATEDSUBCONTRACTINVOICEID < 0 )");
      }
      if (status.equals(SubcontractInvoice.SUBCONTRACTINVOICE_STATUS_PAYMENT_PENDING))
      {
        // Invoice, not credited, sent but NOT paid.
        sql.append("  AND SI.SUBCONTRACTINVOICEID > 0 ");
        sql.append("  AND SI.RELATEDSUBCONTRACTINVOICEID IS NULL");
        sql.append("  AND SI.SENTDATE IS NOT NULL ");
        sql.append("  AND SI.PAIDDATE IS NULL ");
      }
    }    
    sql.append("ORDER BY ABS(SI.SUBCONTRACTINVOICEID) ");
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceUser.class.getName());
  }

}
