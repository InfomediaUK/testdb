package com.helmet.persistence.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceItemHistory;
import com.helmet.bean.SubcontractInvoiceItemUser;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.SubcontractInvoiceItemDAO;
import com.helmet.persistence.Utilities;

public class DefaultSubcontractInvoiceItemDAO extends JdbcDaoSupport implements SubcontractInvoiceItemDAO 
{

  private static StringBuffer insertSubcontractInvoiceItemSQL;

  private static StringBuffer deleteSubcontractInvoiceItemSQL;

  private static StringBuffer updateSubcontractInvoiceItemSQL;

  private static StringBuffer selectSubcontractInvoiceItemsSQL;

  private static StringBuffer selectSubcontractInvoiceItemUserSQL;

  private static StringBuffer selectSubcontractInvoiceItemHistoryForBankReqNumSQL;

  private static StringBuffer selectSubcontractInvoiceItemsForSubcontractInvoiceSQL;

  private static StringBuffer selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL;

  public static void init() 
  {
    // Get insert SubcontractInvoiceItem SQL.
    insertSubcontractInvoiceItemSQL = new StringBuffer();
    insertSubcontractInvoiceItemSQL.append("INSERT INTO SUBCONTRACTINVOICEITEM ");
    insertSubcontractInvoiceItemSQL.append("(  ");
    insertSubcontractInvoiceItemSQL.append("  SUBCONTRACTINVOICEITEMID, ");
    insertSubcontractInvoiceItemSQL.append("  SUBCONTRACTINVOICEID, ");
    insertSubcontractInvoiceItemSQL.append("  BANKREQNUM, ");
    insertSubcontractInvoiceItemSQL.append("  STAFFNAME, ");
    insertSubcontractInvoiceItemSQL.append("  DATE, ");
    insertSubcontractInvoiceItemSQL.append("  STARTTIME, ");
    insertSubcontractInvoiceItemSQL.append("  ENDTIME, ");
    insertSubcontractInvoiceItemSQL.append("  RATE, ");
    insertSubcontractInvoiceItemSQL.append("  VALUE, ");
    insertSubcontractInvoiceItemSQL.append("  NOOFHOURS, ");
    insertSubcontractInvoiceItemSQL.append("  NHSBOOKINGID, ");
    insertSubcontractInvoiceItemSQL.append("  APPLICANTID, ");
    insertSubcontractInvoiceItemSQL.append("  SHIFTID, ");
    insertSubcontractInvoiceItemSQL.append("  BOOKINGID, ");
    insertSubcontractInvoiceItemSQL.append("  BOOKINGGRADEID, ");
    insertSubcontractInvoiceItemSQL.append("  BOOKINGDATEID, ");
    insertSubcontractInvoiceItemSQL.append("  COMMENT, ");
    insertSubcontractInvoiceItemSQL.append("  CREATIONTIMESTAMP, ");
    insertSubcontractInvoiceItemSQL.append("  AUDITORID, ");
    insertSubcontractInvoiceItemSQL.append("  AUDITTIMESTAMP ");
    insertSubcontractInvoiceItemSQL.append(")  ");
    insertSubcontractInvoiceItemSQL.append("VALUES  ");
    insertSubcontractInvoiceItemSQL.append("(  ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^, ");
    insertSubcontractInvoiceItemSQL.append("  ^ ");
    insertSubcontractInvoiceItemSQL.append(")  ");
    // Get delete SubcontractInvoiceItem SQL.
    deleteSubcontractInvoiceItemSQL = new StringBuffer();
    deleteSubcontractInvoiceItemSQL.append("UPDATE SUBCONTRACTINVOICEITEM ");
    deleteSubcontractInvoiceItemSQL.append("SET ACTIVE = FALSE, ");
    deleteSubcontractInvoiceItemSQL.append("    COMMENT = ^, ");
    deleteSubcontractInvoiceItemSQL.append("    AUDITORID = ^, ");
    deleteSubcontractInvoiceItemSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteSubcontractInvoiceItemSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    deleteSubcontractInvoiceItemSQL.append("WHERE SUBCONTRACTINVOICEITEMID = ^ ");
    deleteSubcontractInvoiceItemSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update SubcontractInvoiceItem SQL.
    updateSubcontractInvoiceItemSQL = new StringBuffer();
    updateSubcontractInvoiceItemSQL.append("UPDATE SUBCONTRACTINVOICEITEM ");
    updateSubcontractInvoiceItemSQL.append("SET RATE = ^, ");
    updateSubcontractInvoiceItemSQL.append("    VALUE = ^, ");
    updateSubcontractInvoiceItemSQL.append("    COMMENT = ^, ");
    updateSubcontractInvoiceItemSQL.append("    AUDITORID = ^, ");
    updateSubcontractInvoiceItemSQL.append("    AUDITTIMESTAMP = ^, ");
    updateSubcontractInvoiceItemSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateSubcontractInvoiceItemSQL.append("WHERE SUBCONTRACTINVOICEITEMID = ^ ");
    updateSubcontractInvoiceItemSQL.append("AND   NOOFCHANGES = ^ ");
    //
    selectSubcontractInvoiceItemsSQL = new StringBuffer();
    selectSubcontractInvoiceItemsSQL.append("SELECT SII.SUBCONTRACTINVOICEITEMID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.SUBCONTRACTINVOICEID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.BANKREQNUM, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.STAFFNAME, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.DATE, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.STARTTIME, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.ENDTIME, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.NOOFHOURS, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.RATE, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.VALUE, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.NHSBOOKINGID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.APPLICANTID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.SHIFTID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.BOOKINGID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.BOOKINGGRADEID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.BOOKINGDATEID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.COMMENT, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.CREATIONTIMESTAMP, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.AUDITORID, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.AUDITTIMESTAMP, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.ACTIVE, ");
    selectSubcontractInvoiceItemsSQL.append("       SII.NOOFCHANGES ");
    // Get select SubcontractInvoiceItemUsers for SubcontractInvoice SQL.
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL = new StringBuffer(selectSubcontractInvoiceItemsSQL);
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append(", ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("       BD.WORKEDSTARTTIME, ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("       BD.WORKEDENDTIME, ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("       BD.WORKEDBREAKSTARTTIME, ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("       BD.WORKEDBREAKENDTIME, ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("       BD.WORKEDNOOFHOURS, ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("       BD.WORKEDBREAKNOOFHOURS ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("FROM SUBCONTRACTINVOICEITEM SII ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("JOIN BOOKINGDATE BD ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("ON  BD.BOOKINGDATEID = SII.BOOKINGDATEID ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("WHERE SII.SUBCONTRACTINVOICEID = ^ ");
    // Get select SubcontractInvoiceItemUser SQL.
    selectSubcontractInvoiceItemUserSQL = new StringBuffer(selectSubcontractInvoiceItemsSQL);
    selectSubcontractInvoiceItemUserSQL.append(", ");
    selectSubcontractInvoiceItemUserSQL.append("       BD.WORKEDSTARTTIME, ");
    selectSubcontractInvoiceItemUserSQL.append("       BD.WORKEDENDTIME, ");
    selectSubcontractInvoiceItemUserSQL.append("       BD.WORKEDBREAKSTARTTIME, ");
    selectSubcontractInvoiceItemUserSQL.append("       BD.WORKEDBREAKENDTIME, ");
    selectSubcontractInvoiceItemUserSQL.append("       BD.WORKEDNOOFHOURS, ");
    selectSubcontractInvoiceItemUserSQL.append("       BD.WORKEDBREAKNOOFHOURS ");
    selectSubcontractInvoiceItemUserSQL.append("FROM SUBCONTRACTINVOICEITEM SII ");
    selectSubcontractInvoiceItemUserSQL.append("JOIN BOOKINGDATE BD ");
    selectSubcontractInvoiceItemUserSQL.append("ON  BD.BOOKINGDATEID = SII.BOOKINGDATEID ");
    selectSubcontractInvoiceItemUserSQL.append("WHERE SII.SUBCONTRACTINVOICEITEMID = ^ ");
    // Get select SubcontractInvoiceItemHistory for BankReqNum SQL.
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL = new StringBuffer();    
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("SELECT SI.SUBCONTRACTINVOICEID, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SI.RELATEDSUBCONTRACTINVOICEID, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SI.VALUE AS INVOICEVALUE, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SI.SENTDATE, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SI.PAIDDATE, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.DATE, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.STARTTIME, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.ENDTIME, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.NOOFHOURS, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.RATE, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.VALUE, "); 
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.CREATIONTIMESTAMP, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.AUDITORID, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.AUDITTIMESTAMP, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.ACTIVE, ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("       SII.NOOFCHANGES ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("FROM SUBCONTRACTINVOICE SI ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("    JOIN SUBCONTRACTINVOICEITEM SII "); 
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("    ON SII.SUBCONTRACTINVOICEID = SI.SUBCONTRACTINVOICEID ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("WHERE SII.BANKREQNUM = ^ ");
    selectSubcontractInvoiceItemHistoryForBankReqNumSQL.append("ORDER BY SI.CREATIONTIMESTAMP ");
    
    // Add FROM to Get SubcontractInvoiceItems SQL.
    selectSubcontractInvoiceItemsSQL.append("FROM SUBCONTRACTINVOICEITEM SII ");
    //
    selectSubcontractInvoiceItemsForSubcontractInvoiceSQL = new StringBuffer(selectSubcontractInvoiceItemsSQL);
    selectSubcontractInvoiceItemsForSubcontractInvoiceSQL.append("WHERE SII.SUBCONTRACTINVOICEID = ^ ");
   
    // ORDER BY 
    selectSubcontractInvoiceItemsForSubcontractInvoiceSQL.append("ORDER BY SII.DATE, SII.STARTTIME ");
    selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.append("ORDER BY SII.DATE, SII.STARTTIME ");

  }

  public int insertSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(insertSubcontractInvoiceItemSQL.toString());
    // Replace the parameters with supplied values.
    Integer sunContractInvoiceId = null;
    if (subcontractInvoiceItem.getValue().compareTo(new BigDecimal(0)) > 0)
    {
      // Invoice.
      sunContractInvoiceId = UpdateHandler.getInstance().getId(getJdbcTemplate(), "subcontractInvoiceItem");
    }
    else
    {
      // Credit Note
      sunContractInvoiceId = UpdateHandler.getInstance().getId(getJdbcTemplate(), "subcontractInvoiceItem") * -1;
    }
    subcontractInvoiceItem.setSubcontractInvoiceItemId(sunContractInvoiceId);
    Utilities.replace(sql, subcontractInvoiceItem.getSubcontractInvoiceItemId());
    Utilities.replace(sql, subcontractInvoiceItem.getSubcontractInvoiceId());
    Utilities.replaceAndQuote(sql, subcontractInvoiceItem.getBankReqNum());
    Utilities.replaceAndQuote(sql, subcontractInvoiceItem.getStaffName());
    Utilities.replaceAndQuote(sql, subcontractInvoiceItem.getDate());
    Utilities.replaceAndQuote(sql, subcontractInvoiceItem.getStartTime());
    Utilities.replaceAndQuote(sql, subcontractInvoiceItem.getEndTime());
    Utilities.replace(sql, subcontractInvoiceItem.getRate());
    Utilities.replace(sql, subcontractInvoiceItem.getValue());
    Utilities.replace(sql, subcontractInvoiceItem.getNoOfHours());
    Utilities.replace(sql, subcontractInvoiceItem.getNhsBookingId());
    Utilities.replace(sql, subcontractInvoiceItem.getApplicantId());
    Utilities.replace(sql, subcontractInvoiceItem.getShiftId());
    Utilities.replace(sql, subcontractInvoiceItem.getBookingId());
    Utilities.replace(sql, subcontractInvoiceItem.getBookingGradeId());
    Utilities.replace(sql, subcontractInvoiceItem.getBookingDateId());
    Utilities.replaceAndQuoteNullable(sql, subcontractInvoiceItem.getComment());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int deleteSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(deleteSubcontractInvoiceItemSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuoteNullable(sql, subcontractInvoiceItem.getComment());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, subcontractInvoiceItem.getSubcontractInvoiceItemId());
    Utilities.replace(sql, subcontractInvoiceItem.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int updateSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateSubcontractInvoiceItemSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoiceItem.getRate());
    Utilities.replace(sql, subcontractInvoiceItem.getValue());
    Utilities.replaceAndQuoteNullable(sql, subcontractInvoiceItem.getComment());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, subcontractInvoiceItem.getSubcontractInvoiceItemId());
    Utilities.replace(sql, subcontractInvoiceItem.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public SubcontractInvoiceItemUser getSubcontractInvoiceItemUser(Integer subcontractInvoiceItemId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectSubcontractInvoiceItemUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoiceItemId);
    return (SubcontractInvoiceItemUser) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceItemUser.class.getName());
  }

  public List<SubcontractInvoiceItemHistory> getSubcontractInvoiceItemHistoryForBankReqNum(String bankReqNum) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectSubcontractInvoiceItemHistoryForBankReqNumSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, bankReqNum);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceItemHistory.class.getName());
  }

  public List<SubcontractInvoiceItem> getSubcontractInvoiceItemsForSubcontractInvoice(Integer subcontractInvoiceId) 
  {
    StringBuffer sql = null;
    sql = new StringBuffer(selectSubcontractInvoiceItemsForSubcontractInvoiceSQL.toString()); 
    // Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoiceId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceItem.class.getName());
  }

  public List<SubcontractInvoiceItemUser> getSubcontractInvoiceItemUsersForSubcontractInvoice(Integer subcontractInvoiceId) 
  {
    StringBuffer sql = null;
    sql = new StringBuffer(selectSubcontractInvoiceItemUsersForSubcontractInvoiceSQL.toString()); 
    // Replace the parameters with supplied values.
    Utilities.replace(sql, subcontractInvoiceId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), SubcontractInvoiceItemUser.class.getName());
  }

}
