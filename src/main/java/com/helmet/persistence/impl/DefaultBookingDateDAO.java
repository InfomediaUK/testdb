package com.helmet.persistence.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingDateUserApplicantEntity;
import com.helmet.bean.BookingDateUserGrade;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.RecordCount;
import com.helmet.persistence.BookingDateDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBookingDateDAO extends DefaultPagingDaoSupport implements BookingDateDAO {

	private static StringBuffer insertBookingDateSQL;

	private static StringBuffer updateBookingDateSQL;

	private static StringBuffer updateBookingDateStatusSQL;

	private static StringBuffer updateBookingDateCancelSQL;

	private static StringBuffer updateBookingDateOfferedSQL;
	
	private static StringBuffer updateBookingDateFilledSQL;
	
	private static StringBuffer updateBookingDateRefusedSQL;
	
	private static StringBuffer updateBookingDateWorkedSQL;
	
	private static StringBuffer updateBookingDateSubmittedSQL;
	
	private static StringBuffer updateBookingDateWithdrawnSQL;
	
	private static StringBuffer updateBookingDateActivatedSQL;

	private static StringBuffer updateBookingDateRejectedSQL;
	
	private static StringBuffer updateBookingDateAuthorizedSQL;

	private static StringBuffer updateBookingDateInvoicedSQL;

	private static StringBuffer updateBookingDateExpensesSQL;

	private static StringBuffer updateBookingDateOvertimeSQL;

	private static StringBuffer updateBookingDateCreditedSQL;

  private static StringBuffer updateBookingDateBackingReportSQL;

	private static StringBuffer deleteBookingDateSQL;

	private static StringBuffer selectBookingDateSQL;

  private static StringBuffer selectBookingDatesSQL;

  private static StringBuffer selectBookingDatesForNhsBackingReportSQL;

	private static StringBuffer selectBookingDateUsersSQL;

  private static StringBuffer selectBookingDateUserSQL;

//  private static StringBuffer selectBookingDateUserForBookingBookingDateSQL;
//
	private static StringBuffer selectBookingDatesForBookingSQL;

	private static StringBuffer selectBookingDatesForAgencyInvoiceSQL;

	private static StringBuffer selectBookingDatesForBookingDatesSQL;

	private static StringBuffer selectActiveBookingDatesForBookingSQL;

	private static StringBuffer selectBookingDatesForBookingAndAgencySQL;

	private static StringBuffer selectActiveBookingDatesForBookingAndAgencySQL;

	private static StringBuffer selectBookingDateUsersForBookingSQL;

	private static StringBuffer selectActiveBookingDateUsersForBookingSQL;

	private static StringBuffer selectBookingDateUsersForBookingAndAgencySQL;

	private static StringBuffer selectActiveBookingDateUsersForBookingAndAgencySQL;
	
	private static StringBuffer selectBookingDateUserApplicantsSQL;
	// NEW ->
  private static StringBuffer selectBookingDateUserApplicantsForAgencyAndDateRangeSQL;
  // NEW <-
	private static StringBuffer selectBookingDateUserApplicantsForApplicantForAgencySQL;

	private static StringBuffer selectBookingDateUserApplicantsForApplicantForAgencyAndDateRangeSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndBookingSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndBookingAndDateRangeSQL;
	
	private static StringBuffer selectBookingDateUserApplicantsForManagerAndBookingGradeApplicantSQL;
	
	private static StringBuffer selectBookingDateUserApplicantsForManagerSQL;

	private static StringBuffer selectBookingDateUserApplicantsForAdminSQL;
	
	private static StringBuffer selectBookingDateUserApplicantsForManagerSearchSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerSearchWithDateRangeSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndStatusSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndStatusAndDateRangeSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndDateRangeSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndWorkedStatusSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRangeSQL;

	private static StringBuffer selectBookingDateUserApplicantForManagerAndBookingDateSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndBookingDatesSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndInvoiceSQL;

	private static StringBuffer selectBookingDateUserApplicantsForManagerAndAgencyInvoiceSQL;

	private static StringBuffer selectBookingDateUserApplicantsForAgencySQL;
	
  private static StringBuffer selectBookingDateUserApplicantsForAgencySearchSQL;
  
  private static StringBuffer selectBookingDateUserApplicantsForAgencySearchByClientSiteLocationSQL;
  
  private static StringBuffer selectBookingDateUserApplicantsForAgencySearchWithDateRangeSQL;
  
  private static StringBuffer selectBookingDateUserApplicantsForAgencyBackingReportSQL;
  
  private static StringBuffer selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL;
  
  private static StringBuffer selectBookingDateUserApplicantsForAgencyNotOnBackingReportCountSQL;

  private static StringBuffer selectBookingDateUserApplicantsForAgencyOnBackingReportSQL;
  
  private static StringBuffer selectBookingDateUserApplicantsForAgencyOnBackingReportCountSQL;

  private static StringBuffer selectBookingDateUserApplicantsForAgencySearchWithDateRangeByClientSiteLocationSQL;

  private static StringBuffer selectBookingDateUserApplicantsForAgencyAndStatusSQL;

	private static StringBuffer selectBookingDateUserApplicantsForAgencyAndInvoiceSQL;

	private static StringBuffer selectBookingDateUserApplicantsForAgencyAndAgencyInvoiceSQL;

	private static StringBuffer selectBookingDateUserApplicantsForAgencyAndBookingDatesSQL;

	private static StringBuffer selectBookingDateUserApplicantForAgencyAndBookingDateSQL;

	private static StringBuffer selectBookingDateUserApplicantsForAgencyAndBookingSQL;

	private static StringBuffer selectBookingDateUserApplicantsForAgencyAndBookingGradeApplicantSQL;

	private static StringBuffer selectBookingDateUserApplicantsForApplicantSQL;
	
	private static StringBuffer selectBookingDateUserApplicantsForApplicantForBookingGradeApplicantSQL;

	private static StringBuffer selectBookingDateUserApplicantForApplicantForBookingDateSQL;

	private static StringBuffer selectBookingDateUserGradesSQL;

	private static StringBuffer selectBookingDateUserGradesForAgencySQL;

	private static StringBuffer selectBookingDateUserGradesForAgencyAndStatusSQL;

	public static void init() {
		// Get insert BookingDate SQL.
		insertBookingDateSQL = new StringBuffer();
		insertBookingDateSQL.append("INSERT INTO BOOKINGDATE ");
		insertBookingDateSQL.append("(  ");
		insertBookingDateSQL.append("  BOOKINGDATEID, ");
		insertBookingDateSQL.append("  BOOKINGID, ");
		insertBookingDateSQL.append("  BOOKINGDATE, ");
		insertBookingDateSQL.append("  SHIFTID, ");
		insertBookingDateSQL.append("  SHIFTNAME, ");
		insertBookingDateSQL.append("  SHIFTCODE, ");
		insertBookingDateSQL.append("  SHIFTUSEUPLIFT, ");
		insertBookingDateSQL.append("  SHIFTUPLIFTFACTOR, ");
		insertBookingDateSQL.append("  SHIFTUPLIFTVALUE, ");
		insertBookingDateSQL.append("  SHIFTSTARTTIME, ");
		insertBookingDateSQL.append("  SHIFTENDTIME, ");
		insertBookingDateSQL.append("  SHIFTBREAKSTARTTIME, ");
		insertBookingDateSQL.append("  SHIFTBREAKENDTIME, ");
		insertBookingDateSQL.append("  SHIFTNOOFHOURS, ");
		insertBookingDateSQL.append("  SHIFTBREAKNOOFHOURS, ");
		insertBookingDateSQL.append("  SHIFTOVERTIMENOOFHOURS, ");
		insertBookingDateSQL.append("  SHIFTOVERTIMEUPLIFTFACTOR, ");
		insertBookingDateSQL.append("  VALUE, ");
		insertBookingDateSQL.append("  CREATIONTIMESTAMP, ");
		insertBookingDateSQL.append("  AUDITORID, ");
		insertBookingDateSQL.append("  AUDITTIMESTAMP ");
		insertBookingDateSQL.append(")  ");
		insertBookingDateSQL.append("VALUES  ");
		insertBookingDateSQL.append("(  ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^, ");
		insertBookingDateSQL.append("  ^ ");
		insertBookingDateSQL.append(")  ");
		// Get update BookingDate SQL.
		updateBookingDateSQL = new StringBuffer();
		updateBookingDateSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateBookingDateSQL.append("     BOOKINGDATE = ^, ");
		updateBookingDateSQL.append("     SHIFTID = ^, ");
		updateBookingDateSQL.append("     SHIFTNAME = ^, ");
		updateBookingDateSQL.append("     SHIFTCODE = ^, ");
		updateBookingDateSQL.append("     SHIFTUSEUPLIFT = ^, ");
		updateBookingDateSQL.append("     SHIFTUPLIFTFACTOR = ^, ");
		updateBookingDateSQL.append("     SHIFTUPLIFTVALUE = ^, ");
		updateBookingDateSQL.append("     SHIFTSTARTTIME = ^, ");
		updateBookingDateSQL.append("     SHIFTENDTIME = ^, ");
		updateBookingDateSQL.append("     SHIFTBREAKSTARTTIME = ^, ");
		updateBookingDateSQL.append("     SHIFTBREAKENDTIME = ^, ");
		updateBookingDateSQL.append("     SHIFTNOOFHOURS = ^, ");
		updateBookingDateSQL.append("     SHIFTBREAKNOOFHOURS = ^, ");
		updateBookingDateSQL.append("     SHIFTOVERTIMENOOFHOURS = ^, ");
		updateBookingDateSQL.append("     SHIFTOVERTIMEUPLIFTFACTOR = ^, ");
		updateBookingDateSQL.append("     VALUE = ^, ");
		updateBookingDateSQL.append("     AUDITORID = ^, ");
		updateBookingDateSQL.append("     AUDITTIMESTAMP = ^ ");
		updateBookingDateSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate cancel SQL.
		updateBookingDateCancelSQL = new StringBuffer();
		updateBookingDateCancelSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateCancelSQL.append("SET STATUS = " + BookingDate.BOOKINGDATE_STATUS_CANCELLED + ", ");
		updateBookingDateCancelSQL.append("    WORKEDSTATUS = ");

		// ONLY update the workedStatus as cancelled if the shift has been worked i.e. has had its workedStatus set
		updateBookingDateCancelSQL.append("  CASE WHEN WORKEDSTATUS IS NULL THEN NULL ELSE " + BookingDate.BOOKINGDATE_WORKEDSTATUS_CANCELLED + " END, ");
		
		updateBookingDateCancelSQL.append("    CANCELTEXT = ^, ");
		updateBookingDateCancelSQL.append("    CANCELLEDBYID = ^, ");
		updateBookingDateCancelSQL.append("    CANCELLEDTIMESTAMP = ^, ");
		updateBookingDateCancelSQL.append("    INVOICEID = NULL, "); //
		updateBookingDateCancelSQL.append("    AGENCYINVOICEID = NULL, "); //
		updateBookingDateCancelSQL.append("    AUDITORID = ^, ");
		updateBookingDateCancelSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateCancelSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateCancelSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateCancelSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate filled SQL.
		updateBookingDateFilledSQL = new StringBuffer();
		updateBookingDateFilledSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateFilledSQL.append("SET STATUS = " + BookingDate.BOOKINGDATE_STATUS_FILLED + ", ");
		updateBookingDateFilledSQL.append("    BOOKINGGRADEAPPLICANTDATEID = ^, ");
		updateBookingDateFilledSQL.append("    AUDITORID = ^, ");
		updateBookingDateFilledSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateFilledSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateFilledSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateFilledSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate offered SQL.
		updateBookingDateOfferedSQL = new StringBuffer();
		updateBookingDateOfferedSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateOfferedSQL.append("SET STATUS = " + BookingDate.BOOKINGDATE_STATUS_OFFERED + ", ");
		updateBookingDateOfferedSQL.append("    BOOKINGGRADEAPPLICANTDATEID = ^, ");
		updateBookingDateOfferedSQL.append("    AUDITORID = ^, ");
		updateBookingDateOfferedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateOfferedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateOfferedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateOfferedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate refused SQL.
		updateBookingDateRefusedSQL = new StringBuffer();
		updateBookingDateRefusedSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateRefusedSQL.append("SET STATUS = " + BookingDate.BOOKINGDATE_STATUS_OPEN + ", ");
		updateBookingDateRefusedSQL.append("    BOOKINGGRADEAPPLICANTDATEID = NULL, ");
		updateBookingDateRefusedSQL.append("    AUDITORID = ^, ");
		updateBookingDateRefusedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateRefusedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateRefusedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateRefusedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate worked SQL.
		updateBookingDateWorkedSQL = new StringBuffer();
		updateBookingDateWorkedSQL.append("UPDATE BOOKINGDATE ");
		// should only be set to draft if start time entered OR comment entered 
		updateBookingDateWorkedSQL.append("SET WORKEDSTATUS = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDSTARTTIME = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDENDTIME = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDBREAKSTARTTIME = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDBREAKENDTIME = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDNOOFHOURS = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDBREAKNOOFHOURS = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDCHARGERATEVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDPAYRATEVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDWTDVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDNIVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDCOMMISSIONVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDCHARGERATEVATVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDPAYRATEVATVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDWTDVATVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDNIVATVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDCOMMISSIONVATVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDVATVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    WORKEDWAGERATEVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    HASUPLIFT = ^, ");
		updateBookingDateWorkedSQL.append("    COMMENT = ^, ");
		updateBookingDateWorkedSQL.append("    FEEPERSHIFT = ^, ");
		updateBookingDateWorkedSQL.append("    FEEPERHOUR = ^, ");
		updateBookingDateWorkedSQL.append("    FEEPERCENTAGE = ^, ");
		updateBookingDateWorkedSQL.append("    FEEVALUE = ^, ");
		updateBookingDateWorkedSQL.append("    AUDITORID = ^, ");
		updateBookingDateWorkedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateWorkedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateWorkedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateWorkedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate submitted SQL.
		updateBookingDateSubmittedSQL = new StringBuffer();
		updateBookingDateSubmittedSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateSubmittedSQL.append("SET WORKEDSTATUS = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_SUBMITTED + ", ");
		updateBookingDateSubmittedSQL.append("    AUDITORID = ^, ");
		updateBookingDateSubmittedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateSubmittedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateSubmittedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateSubmittedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate withdrawn SQL.
		updateBookingDateWithdrawnSQL = new StringBuffer();
		updateBookingDateWithdrawnSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateWithdrawnSQL.append("SET WORKEDSTATUS = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_DRAFT + ", ");
		updateBookingDateWithdrawnSQL.append("    AUDITORID = ^, ");
		updateBookingDateWithdrawnSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateWithdrawnSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateWithdrawnSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateWithdrawnSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate credited SQL.
		updateBookingDateCreditedSQL = new StringBuffer();
		updateBookingDateCreditedSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateCreditedSQL.append("SET STATUS = " + BookingDate.BOOKINGDATE_STATUS_CREDITED + ", ");
		updateBookingDateCreditedSQL.append("    WORKEDSTATUS = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_CREDITED + ", ");
		updateBookingDateCreditedSQL.append("    AGENCYINVOICECREDITID = ^, ");
		updateBookingDateCreditedSQL.append("    AUDITORID = ^, ");
		updateBookingDateCreditedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateCreditedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateCreditedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateCreditedSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update BookingDate BackingReport SQL.
    updateBookingDateBackingReportSQL = new StringBuffer();
    updateBookingDateBackingReportSQL.append("UPDATE BOOKINGDATE ");
    updateBookingDateBackingReportSQL.append("SET BACKINGREPORT = ^, ");
    updateBookingDateBackingReportSQL.append("    AUDITORID = ^, ");
    updateBookingDateBackingReportSQL.append("    AUDITTIMESTAMP = ^, ");
    updateBookingDateBackingReportSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateBookingDateBackingReportSQL.append("WHERE BOOKINGDATEID = ^ ");
    updateBookingDateBackingReportSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate status SQL.
		updateBookingDateStatusSQL = new StringBuffer();
		updateBookingDateStatusSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateStatusSQL.append("SET STATUS = ^, ");
		updateBookingDateStatusSQL.append("    AUDITORID = ^, ");
		updateBookingDateStatusSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateStatusSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateStatusSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateStatusSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate activated SQL.
		updateBookingDateActivatedSQL = new StringBuffer();
		updateBookingDateActivatedSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateActivatedSQL.append("SET ACTIVATED = TRUE, ");
		updateBookingDateActivatedSQL.append("    ACTIVATEDBYID = ^, ");
		updateBookingDateActivatedSQL.append("    ACTIVATEDTIMESTAMP = ^, ");
		updateBookingDateActivatedSQL.append("    AUDITORID = ^, ");
		updateBookingDateActivatedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateActivatedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateActivatedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateActivatedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate authorized SQL.
		updateBookingDateAuthorizedSQL = new StringBuffer();
		updateBookingDateAuthorizedSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateAuthorizedSQL.append("SET WORKEDSTATUS = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED + ", ");
		updateBookingDateAuthorizedSQL.append("    STATUS = " + BookingDate.BOOKINGDATE_STATUS_COMPLETED + ", ");
		updateBookingDateAuthorizedSQL.append("    AUTHORIZEDBYID = ^, ");
		updateBookingDateAuthorizedSQL.append("    AUTHORIZEDTIMESTAMP = ^, ");
		updateBookingDateAuthorizedSQL.append("    AUDITORID = ^, ");
		updateBookingDateAuthorizedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateAuthorizedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateAuthorizedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateAuthorizedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate rejected SQL.
		updateBookingDateRejectedSQL = new StringBuffer();
		updateBookingDateRejectedSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateRejectedSQL.append("SET WORKEDSTATUS = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_REJECTED + ", ");
		updateBookingDateRejectedSQL.append("    REJECTTEXT = ^, ");
		updateBookingDateRejectedSQL.append("    REJECTEDBYID = ^, ");
		updateBookingDateRejectedSQL.append("    REJECTEDTIMESTAMP = ^, ");
		updateBookingDateRejectedSQL.append("    AUDITORID = ^, ");
		updateBookingDateRejectedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateRejectedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateRejectedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateRejectedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate invoiced SQL.
		updateBookingDateInvoicedSQL = new StringBuffer();
		updateBookingDateInvoicedSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateInvoicedSQL.append("SET WORKEDSTATUS = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED + ", ");
		updateBookingDateInvoicedSQL.append("    AGENCYINVOICEID = ^, ");
		updateBookingDateInvoicedSQL.append("    INVOICEDBYID = ^, ");

//
// if managers are invoicing ...
//
//		updateBookingDateInvoicedSQL.append("    INVOICEID = ^, ");
//		updateBookingDateInvoicedSQL.append("    INVOICEDBYID = ^, ");

		updateBookingDateInvoicedSQL.append("    INVOICEDTIMESTAMP = ^, ");
		updateBookingDateInvoicedSQL.append("    AUDITORID = ^, ");
		updateBookingDateInvoicedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateInvoicedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateInvoicedSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateInvoicedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingDate expenses SQL.
		updateBookingDateExpensesSQL = new StringBuffer();
		updateBookingDateExpensesSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateExpensesSQL.append("SET EXPENSEVALUE = EXPENSEVALUE + ^, ");
		updateBookingDateExpensesSQL.append("    EXPENSEVATVALUE = EXPENSEVATVALUE + ^, ");
		updateBookingDateExpensesSQL.append("    AUDITORID = ^, ");
		updateBookingDateExpensesSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateExpensesSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateExpensesSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateExpensesSQL.append("AND   NOOFCHANGES = ^ ");

		// Get update BookingDate overtime SQL.
		updateBookingDateOvertimeSQL = new StringBuffer();
		updateBookingDateOvertimeSQL.append("UPDATE BOOKINGDATE ");
		updateBookingDateOvertimeSQL.append("SET SHIFTOVERTIMENOOFHOURS = ^, ");
		updateBookingDateOvertimeSQL.append("    SHIFTOVERTIMEUPLIFTFACTOR = ^, ");
		updateBookingDateOvertimeSQL.append("    AUDITORID = ^, ");
		updateBookingDateOvertimeSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateOvertimeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateOvertimeSQL.append("WHERE BOOKINGDATEID = ^ ");
		updateBookingDateOvertimeSQL.append("AND   NOOFCHANGES = ^ ");
		
		// Get delete BookingDate SQL.
		deleteBookingDateSQL = new StringBuffer();
		deleteBookingDateSQL.append("UPDATE BOOKINGDATE ");
		deleteBookingDateSQL.append("SET ACTIVE = FALSE, ");
		deleteBookingDateSQL.append("    AUDITORID = ^, ");
		deleteBookingDateSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteBookingDateSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteBookingDateSQL.append("WHERE BOOKINGDATEID = ^ ");
		deleteBookingDateSQL.append("AND   NOOFCHANGES = ^ ");

		// Get select BookingDates SQL.
		//
		// SELECT ...
		//
		selectBookingDatesSQL = new StringBuffer();
		selectBookingDatesSQL.append("SELECT BD.BOOKINGDATEID, ");
		selectBookingDatesSQL.append("  BD.BOOKINGID, ");
		selectBookingDatesSQL.append("  BD.BOOKINGDATE, ");
		selectBookingDatesSQL.append("  BD.SHIFTID, ");
		selectBookingDatesSQL.append("  BD.SHIFTNAME, ");
		selectBookingDatesSQL.append("  BD.SHIFTCODE, ");
		selectBookingDatesSQL.append("  BD.SHIFTUSEUPLIFT, ");
		selectBookingDatesSQL.append("  BD.SHIFTUPLIFTFACTOR, ");
		selectBookingDatesSQL.append("  BD.SHIFTUPLIFTVALUE, ");
		selectBookingDatesSQL.append("  BD.SHIFTSTARTTIME, ");
		selectBookingDatesSQL.append("  BD.SHIFTENDTIME, ");
		selectBookingDatesSQL.append("  BD.SHIFTBREAKSTARTTIME, ");
		selectBookingDatesSQL.append("  BD.SHIFTBREAKENDTIME, ");
		selectBookingDatesSQL.append("  BD.SHIFTNOOFHOURS, ");
		selectBookingDatesSQL.append("  BD.SHIFTBREAKNOOFHOURS, ");
		selectBookingDatesSQL.append("  BD.SHIFTOVERTIMENOOFHOURS, ");
		selectBookingDatesSQL.append("  BD.SHIFTOVERTIMEUPLIFTFACTOR, ");
		selectBookingDatesSQL.append("  BD.BOOKINGGRADEAPPLICANTDATEID, ");	
		selectBookingDatesSQL.append("  BD.WORKEDSTATUS, ");	
		selectBookingDatesSQL.append("  BD.WORKEDSTARTTIME, ");
		selectBookingDatesSQL.append("  BD.WORKEDENDTIME, ");
		selectBookingDatesSQL.append("  BD.WORKEDBREAKSTARTTIME, ");
		selectBookingDatesSQL.append("  BD.WORKEDBREAKENDTIME, ");
		selectBookingDatesSQL.append("  BD.WORKEDNOOFHOURS, ");
		selectBookingDatesSQL.append("  BD.WORKEDBREAKNOOFHOURS, ");
		selectBookingDatesSQL.append("  BD.WORKEDCHARGERATEVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDPAYRATEVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDWTDVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDNIVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDCOMMISSIONVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDCHARGERATEVATVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDPAYRATEVATVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDWTDVATVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDNIVATVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDCOMMISSIONVATVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDVATVALUE, ");	
		selectBookingDatesSQL.append("  BD.WORKEDWAGERATEVALUE, ");	
		selectBookingDatesSQL.append("  BD.EXPENSEVALUE, ");	
		selectBookingDatesSQL.append("  BD.EXPENSEVATVALUE, ");	
		selectBookingDatesSQL.append("  BD.HASUPLIFT, ");	
		selectBookingDatesSQL.append("  BD.COMMENT, ");	
		selectBookingDatesSQL.append("  BD.FEEPERSHIFT, ");	
		selectBookingDatesSQL.append("  BD.FEEPERHOUR, ");	
		selectBookingDatesSQL.append("  BD.FEEPERCENTAGE, ");	
		selectBookingDatesSQL.append("  BD.FEEVALUE, ");	
		selectBookingDatesSQL.append("  BD.INVOICEID, ");	
		selectBookingDatesSQL.append("  BD.AGENCYINVOICEID, ");	
		selectBookingDatesSQL.append("  BD.AGENCYINVOICECREDITID, ");	
		selectBookingDatesSQL.append("  BD.REJECTTEXT, ");
		selectBookingDatesSQL.append("  BD.VALUE, ");
		selectBookingDatesSQL.append("  BD.STATUS, ");
		selectBookingDatesSQL.append("  BD.ACTIVATED, ");
		selectBookingDatesSQL.append("  BD.CANCELTEXT, ");
		selectBookingDatesSQL.append("  BD.CANCELLEDBYID, ");
		selectBookingDatesSQL.append("  BD.CANCELLEDTIMESTAMP, ");
		selectBookingDatesSQL.append("  BD.INVOICEDBYID, ");
		selectBookingDatesSQL.append("  BD.INVOICEDTIMESTAMP, ");
		selectBookingDatesSQL.append("  BD.REJECTEDBYID, ");
		selectBookingDatesSQL.append("  BD.REJECTEDTIMESTAMP, ");
		selectBookingDatesSQL.append("  BD.AUTHORIZEDBYID, ");
		selectBookingDatesSQL.append("  BD.AUTHORIZEDTIMESTAMP, ");
		selectBookingDatesSQL.append("  BD.ACTIVATEDBYID, ");
		selectBookingDatesSQL.append("  BD.ACTIVATEDTIMESTAMP, ");
		selectBookingDatesSQL.append("  BD.CREATIONTIMESTAMP, ");
		selectBookingDatesSQL.append("  BD.AUDITORID, ");
		selectBookingDatesSQL.append("  BD.AUDITTIMESTAMP, ");
		selectBookingDatesSQL.append("  BD.ACTIVE, ");
    selectBookingDatesSQL.append("  BD.BACKINGREPORT, ");
    selectBookingDatesSQL.append("  BD.NOOFCHANGES ");

		//
		selectBookingDateUsersSQL = new StringBuffer(selectBookingDatesSQL);
		selectBookingDateUsersSQL.append("  , ");
		selectBookingDateUsersSQL.append("  JPV.JOBPROFILEID, ");
		selectBookingDateUsersSQL.append("  JPV.JOBPROFILECODE, ");
		selectBookingDateUsersSQL.append("  JPV.JOBPROFILENAME, ");
		selectBookingDateUsersSQL.append("  JPV.JOBSUBFAMILYID, ");
		selectBookingDateUsersSQL.append("  JPV.JOBSUBFAMILYCODE, ");
		selectBookingDateUsersSQL.append("  JPV.JOBSUBFAMILYNAME, ");
		selectBookingDateUsersSQL.append("  JPV.JOBFAMILYID, ");
		selectBookingDateUsersSQL.append("  JPV.JOBFAMILYCODE, ");
		selectBookingDateUsersSQL.append("  JPV.JOBFAMILYNAME, ");
		selectBookingDateUsersSQL.append("  LV.LOCATIONID, ");
		selectBookingDateUsersSQL.append("  LV.LOCATIONCODE, ");
		selectBookingDateUsersSQL.append("  LV.LOCATIONNAME, ");
		selectBookingDateUsersSQL.append("  LV.SITEID, ");
		selectBookingDateUsersSQL.append("  LV.SITECODE, ");
		selectBookingDateUsersSQL.append("  LV.SITENAME, ");
		selectBookingDateUsersSQL.append("  LV.CLIENTID, ");
		selectBookingDateUsersSQL.append("  LV.CLIENTCODE, ");
    selectBookingDateUsersSQL.append("  LV.CLIENTNAME, ");
    selectBookingDateUsersSQL.append("  LV.CLIENTUPLIFTCOMMISSION, ");
//		selectBookingDateUsersSQL.append("  JP.JOBPROFILEID, ");
//		selectBookingDateUsersSQL.append("  JP.CODE AS JOBPROFILECODE, ");
//		selectBookingDateUsersSQL.append("  JP.NAME AS JOBPROFILENAME, ");
//		selectBookingDateUsersSQL.append("  JSF.JOBSUBFAMILYID, ");
//		selectBookingDateUsersSQL.append("  JSF.CODE AS JOBSUBFAMILYCODE, ");
//		selectBookingDateUsersSQL.append("  JSF.NAME AS JOBSUBFAMILYNAME, ");
//		selectBookingDateUsersSQL.append("  JF.JOBFAMILYID, ");
//		selectBookingDateUsersSQL.append("  JF.CODE AS JOBFAMILYCODE, ");
//		selectBookingDateUsersSQL.append("  JF.NAME AS JOBFAMILYNAME, ");
//		selectBookingDateUsersSQL.append("  L.LOCATIONID, ");
//		selectBookingDateUsersSQL.append("  L.CODE AS LOCATIONCODE, ");
//		selectBookingDateUsersSQL.append("  L.NAME AS LOCATIONNAME, ");
//		selectBookingDateUsersSQL.append("  SI.SITEID, ");
//		selectBookingDateUsersSQL.append("  SI.CODE AS SITECODE, ");
//		selectBookingDateUsersSQL.append("  SI.NAME AS SITENAME, ");
//		selectBookingDateUsersSQL.append("  C.CLIENTID, ");
//		selectBookingDateUsersSQL.append("  C.CODE AS CLIENTCODE, ");
//		selectBookingDateUsersSQL.append("  C.NAME AS CLIENTNAME, ");
		selectBookingDateUsersSQL.append("  B.RATE AS RRPRATE, ");
		selectBookingDateUsersSQL.append("  B.BOOKINGREFERENCE ");

        //
		selectBookingDateUserGradesSQL = new StringBuffer(selectBookingDateUsersSQL);
		selectBookingDateUserGradesSQL.append("  , ");
		selectBookingDateUserGradesSQL.append("  BG.BOOKINGGRADEID, ");
		selectBookingDateUserGradesSQL.append("  BG.RATE AS BOOKINGGRADERATE, ");
		selectBookingDateUserGradesSQL.append("  BG.PAYRATE AS BOOKINGGRADEPAYRATE, ");
		selectBookingDateUserGradesSQL.append("  BG.WTDPERCENTAGE AS BOOKINGGRADEWTDPERCENTAGE, ");
		selectBookingDateUserGradesSQL.append("  BG.NIPERCENTAGE AS BOOKINGGRADENIPERCENTAGE, ");
		selectBookingDateUserGradesSQL.append("  B.RATE AS BOOKINGRATE, ");
		selectBookingDateUserGradesSQL.append("  BG.GRADEID, ");
		selectBookingDateUserGradesSQL.append("  G.NAME AS GRADENAME ");
		//
		selectBookingDateUserApplicantsSQL = new StringBuffer(selectBookingDateUsersSQL);
		selectBookingDateUserApplicantsSQL.append("  , ");
		selectBookingDateUserApplicantsSQL.append("  BGAD.STATUS AS BOOKINGGRADEAPPLICANTDATESTATUS, ");
		selectBookingDateUserApplicantsSQL.append("  BGAD.VALUE AS CHARGERATEVALUE, ");
		selectBookingDateUserApplicantsSQL.append("  BGAD.PAYRATEVALUE AS PAYRATEVALUE, ");
		selectBookingDateUserApplicantsSQL.append("  BGAD.WAGERATEVALUE AS WAGERATEVALUE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.BOOKINGGRADEAPPLICANTID, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.RATE AS CHARGERATE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.PAYRATE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.WTDPERCENTAGE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.NIPERCENTAGE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.WAGERATE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.CHARGERATEVATRATE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.PAYRATEVATRATE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.WTDVATRATE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.NIVATRATE, ");
		selectBookingDateUserApplicantsSQL.append("  BGA.COMMISSIONVATRATE, ");
		
		selectBookingDateUserApplicantsSQL.append("  BG.BOOKINGGRADEID, ");
		selectBookingDateUserApplicantsSQL.append("  BG.RATE AS REQUESTEDCHARGERATE, "); // not used

		selectBookingDateUserApplicantsSQL.append("  A.APPLICANTID, ");
		selectBookingDateUserApplicantsSQL.append("  A.FIRSTNAME AS APPLICANTFIRSTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  A.LASTNAME AS APPLICANTLASTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  A.DATEOFBIRTH AS APPLICANTDATEOFBIRTH, ");	
		selectBookingDateUserApplicantsSQL.append("  A.PHOTOFILENAME AS APPLICANTPHOTOFILENAME, ");	
    selectBookingDateUserApplicantsSQL.append("  A.GENDER AS APPLICANTGENDER, "); 
    selectBookingDateUserApplicantsSQL.append("  A.ORIGINALAGENCYID AS APPLICANTORIGINALAGENCYID, "); 
		selectBookingDateUserApplicantsSQL.append("  AG.AGENCYID, ");
		selectBookingDateUserApplicantsSQL.append("  AG.CODE AS AGENCYCODE, ");
		selectBookingDateUserApplicantsSQL.append("  AG.NAME AS AGENCYNAME, ");
		selectBookingDateUserApplicantsSQL.append("  G.GRADEID, ");
		selectBookingDateUserApplicantsSQL.append("  G.NAME AS GRADENAME, ");

		selectBookingDateUserApplicantsSQL.append("  X1.LOGIN AS CANCELLEDBYLOGIN, ");
		selectBookingDateUserApplicantsSQL.append("  X1.FIRSTNAME AS CANCELLEDBYFIRSTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X1.LASTNAME AS CANCELLEDBYLASTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X2.LOGIN AS INVOICEDBYLOGIN, ");
		selectBookingDateUserApplicantsSQL.append("  X2.FIRSTNAME AS INVOICEDBYFIRSTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X2.LASTNAME AS INVOICEDBYLASTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X3.LOGIN AS REJECTEDBYLOGIN, ");
		selectBookingDateUserApplicantsSQL.append("  X3.FIRSTNAME AS REJECTEDBYFIRSTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X3.LASTNAME AS REJECTEDBYLASTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X4.LOGIN AS AUTHORIZEDBYLOGIN, ");
		selectBookingDateUserApplicantsSQL.append("  X4.FIRSTNAME AS AUTHORIZEDBYFIRSTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X4.LASTNAME AS AUTHORIZEDBYLASTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X5.LOGIN AS ACTIVATEDBYLOGIN, ");
		selectBookingDateUserApplicantsSQL.append("  X5.FIRSTNAME AS ACTIVATEDBYFIRSTNAME, ");
		selectBookingDateUserApplicantsSQL.append("  X5.LASTNAME AS ACTIVATEDBYLASTNAME ");

		//
		// FROM ...
		//
		//
		selectBookingDateUsersSQL.append("FROM BOOKINGDATE BD, ");
		selectBookingDateUsersSQL.append("     BOOKING B, ");
		selectBookingDateUsersSQL.append("     LOCATIONVIEWALL LV, "); // Was LOCATIONVIEW until 12/09/2016
		selectBookingDateUsersSQL.append("     JOBPROFILEVIEWALL JPV "); // Was JOBPROFILEVIEW until 13/09/2016

    //
//    selectBookingDateUserForBookingBookingDateSQL = new StringBuffer(selectBookingDateUsersSQL);
//    selectBookingDateUserForBookingBookingDateSQL.append("WHERE BD.BOOKINGID = ^ ");
//    selectBookingDateUserForBookingBookingDateSQL.append("AND BD.BOOKINGDATE = ^ ");
//    

//		selectBookingDateUsersSQL.append("     LOCATION L, ");
//		selectBookingDateUsersSQL.append("     SITE SI, ");
//		selectBookingDateUsersSQL.append("     CLIENT C, ");
//		selectBookingDateUsersSQL.append("     JOBPROFILE JP, ");
//		selectBookingDateUsersSQL.append("     JOBSUBFAMILY JSF, ");
//		selectBookingDateUsersSQL.append("     JOBFAMILY JF ");
		selectBookingDateUsersSQL.append("WHERE B.BOOKINGID = BD.BOOKINGID ");
		selectBookingDateUsersSQL.append("AND B.ACTIVE = TRUE ");

		selectBookingDateUsersSQL.append("AND JPV.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingDateUsersSQL.append("AND JP.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingDateUsersSQL.append("AND JP.ACTIVE = TRUE ");
//		selectBookingDateUsersSQL.append("AND JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
//		selectBookingDateUsersSQL.append("AND JSF.ACTIVE = TRUE ");
//		selectBookingDateUsersSQL.append("AND JF.JOBFAMILYID = JSF.JOBFAMILYID ");
//		selectBookingDateUsersSQL.append("AND JF.ACTIVE = TRUE ");
		selectBookingDateUsersSQL.append("AND LV.LOCATIONID = B.LOCATIONID ");
//		selectBookingDateUsersSQL.append("AND L.LOCATIONID = B.LOCATIONID ");
//		selectBookingDateUsersSQL.append("AND L.ACTIVE = TRUE ");
//		selectBookingDateUsersSQL.append("AND SI.SITEID = L.SITEID ");
//		selectBookingDateUsersSQL.append("AND SI.ACTIVE = TRUE ");
//		selectBookingDateUsersSQL.append("AND C.CLIENTID = SI.CLIENTID ");
//		selectBookingDateUsersSQL.append("AND C.ACTIVE = TRUE ");

    //
    selectBookingDateUserSQL = new StringBuffer(selectBookingDateUsersSQL);
    selectBookingDateUserSQL.append("AND BD.BOOKINGDATEID = ^ ");
    
        //
		selectBookingDateUserGradesSQL.append("FROM BOOKINGDATE BD, ");
		selectBookingDateUserGradesSQL.append("     BOOKING B, ");
		selectBookingDateUserGradesSQL.append("     CLIENTAGENCY CA, ");
		selectBookingDateUserGradesSQL.append("     CLIENTAGENCYJOBPROFILE CAJP, ");
		selectBookingDateUserGradesSQL.append("     CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
		selectBookingDateUserGradesSQL.append("     BOOKINGGRADE BG, ");
		selectBookingDateUserGradesSQL.append("     GRADE G, ");
		// TODO below should be common rather than here and in applicants select !!
		selectBookingDateUserGradesSQL.append("     LOCATIONVIEWALL LV, "); // Was LOCATIONVIEW until 12/09/2016
		selectBookingDateUserGradesSQL.append("     JOBPROFILEVIEWALL JPV "); // Was JOBPROFILEVIEW until 13/09/2016
//		selectBookingDateUserGradesSQL.append("     LOCATION L, ");
//		selectBookingDateUserGradesSQL.append("     SITE SI, ");
//		selectBookingDateUserGradesSQL.append("     CLIENT C, ");
//		selectBookingDateUserGradesSQL.append("     JOBPROFILE JP, ");
//		selectBookingDateUserGradesSQL.append("     JOBSUBFAMILY JSF, ");
//		selectBookingDateUserGradesSQL.append("     JOBFAMILY JF ");
		
		selectBookingDateUserGradesSQL.append("WHERE B.BOOKINGID = BD.BOOKINGID ");
		selectBookingDateUserGradesSQL.append("AND B.ACTIVE = TRUE ");
		selectBookingDateUserGradesSQL.append("AND JPV.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingDateUserGradesSQL.append("AND JP.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingDateUserGradesSQL.append("AND JP.ACTIVE = TRUE ");
//		selectBookingDateUserGradesSQL.append("AND JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
//		selectBookingDateUserGradesSQL.append("AND JSF.ACTIVE = TRUE ");
//		selectBookingDateUserGradesSQL.append("AND JF.JOBFAMILYID = JSF.JOBFAMILYID ");
//		selectBookingDateUserGradesSQL.append("AND JF.ACTIVE = TRUE ");
		selectBookingDateUserGradesSQL.append("AND LV.LOCATIONID = B.LOCATIONID ");
//		selectBookingDateUserGradesSQL.append("AND L.LOCATIONID = B.LOCATIONID ");
//		selectBookingDateUserGradesSQL.append("AND L.ACTIVE = TRUE ");
//		selectBookingDateUserGradesSQL.append("AND SI.SITEID = L.SITEID ");
//		selectBookingDateUserGradesSQL.append("AND SI.ACTIVE = TRUE ");
//		selectBookingDateUserGradesSQL.append("AND C.CLIENTID = SI.CLIENTID ");
//		selectBookingDateUserGradesSQL.append("AND C.ACTIVE = TRUE ");

		selectBookingDateUserGradesSQL.append("AND ( ");
		selectBookingDateUserGradesSQL.append(" ^ IS NULL ");
		selectBookingDateUserGradesSQL.append("OR ");
		selectBookingDateUserGradesSQL.append(" B.SINGLECANDIDATE = ^ ");
		selectBookingDateUserGradesSQL.append(") ");
		selectBookingDateUserGradesSQL.append("AND CA.ACTIVE = TRUE ");
		selectBookingDateUserGradesSQL.append("AND CAJP.CLIENTAGENCYID = CA.CLIENTAGENCYID ");
		selectBookingDateUserGradesSQL.append("AND CAJP.ACTIVE = TRUE ");
		selectBookingDateUserGradesSQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEID = CAJP.CLIENTAGENCYJOBPROFILEID ");
		selectBookingDateUserGradesSQL.append("AND CAJPG.ACTIVE = TRUE ");
		selectBookingDateUserGradesSQL.append("AND BG.CLIENTAGENCYJOBPROFILEGRADEID = CAJPG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectBookingDateUserGradesSQL.append("AND BG.ACTIVE = TRUE ");
		selectBookingDateUserGradesSQL.append("AND BG.STATUS IN (" + BookingGrade.BOOKINGGRADE_AGENCY_STATUSES + ") ");
		selectBookingDateUserGradesSQL.append("AND BG.BOOKINGID = B.BOOKINGID ");
		selectBookingDateUserGradesSQL.append("AND ( ");
		selectBookingDateUserGradesSQL.append(" ^ IS NULL ");
		selectBookingDateUserGradesSQL.append("OR ");
		selectBookingDateUserGradesSQL.append(" BG.VIEWED = ^ ");
		selectBookingDateUserGradesSQL.append(") ");
		selectBookingDateUserGradesSQL.append("AND G.GRADEID = CAJPG.GRADEID ");
		selectBookingDateUserGradesSQL.append("AND G.ACTIVE = TRUE ");
		selectBookingDateUserGradesSQL.append("AND BD.BOOKINGID = B.BOOKINGID ");
		selectBookingDateUserGradesSQL.append("AND BD.ACTIVE = TRUE ");
		
		selectBookingDateUserGradesForAgencySQL = new StringBuffer(selectBookingDateUserGradesSQL);
		selectBookingDateUserGradesForAgencySQL.append("AND CA.AGENCYID = ^ ");

		selectBookingDateUserGradesForAgencyAndStatusSQL = new StringBuffer(selectBookingDateUserGradesForAgencySQL);
		selectBookingDateUserGradesForAgencyAndStatusSQL.append("AND BD.STATUS = ^ ");


		//
		//
		//
		// HERE because Agency select uses a different FROM clause ...
		selectBookingDateUserApplicantsForAgencySQL = new StringBuffer(selectBookingDateUserApplicantsSQL);
		//
		//
		//

		
		//
		selectBookingDateUserApplicantsSQL.append("FROM BOOKINGDATE BD ");
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN BOOKINGGRADEAPPLICANTDATE BGAD ON "); 
		selectBookingDateUserApplicantsSQL.append("                 BGAD.BOOKINGGRADEAPPLICANTDATEID = BD.BOOKINGGRADEAPPLICANTDATEID  ");
		selectBookingDateUserApplicantsSQL.append("             AND BGAD.ACTIVE = TRUE "); 
		// offered and filled ONLY - NOT open as there could be several, causing a cartesian product
		selectBookingDateUserApplicantsSQL.append("             AND BGAD.STATUS in (" + 
				                                                              BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OFFERED + ", " +
				                                                              BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED + ") ");
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN BOOKINGGRADEAPPLICANT BGA ON ");
		selectBookingDateUserApplicantsSQL.append("                 BGA.BOOKINGGRADEAPPLICANTID = BGAD.BOOKINGGRADEAPPLICANTID "); 
		selectBookingDateUserApplicantsSQL.append("             AND BGA.ACTIVE = TRUE "); 
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN BOOKINGGRADE BG ON ");
		selectBookingDateUserApplicantsSQL.append("                 BG.BOOKINGGRADEID = BGA.BOOKINGGRADEID "); 
		selectBookingDateUserApplicantsSQL.append("             AND BG.ACTIVE = TRUE "); 
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN CLIENTAGENCYJOBPROFILEGRADE CAJPG ON ");                  
		selectBookingDateUserApplicantsSQL.append("                 CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BGA.CLIENTAGENCYJOBPROFILEGRADEID ");              
		selectBookingDateUserApplicantsSQL.append("             AND CAJPG.ACTIVE = TRUE ");  
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN GRADE G ON ");                  
		selectBookingDateUserApplicantsSQL.append("                 G.GRADEID = CAJPG.GRADEID ");              
		selectBookingDateUserApplicantsSQL.append("             AND G.ACTIVE = TRUE ");  
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN APPLICANT A ON "); 
		selectBookingDateUserApplicantsSQL.append("                 A.APPLICANTID = BGA.APPLICANTID "); 
		selectBookingDateUserApplicantsSQL.append("             AND A.ACTIVE = TRUE "); 
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN AGENCY AG ON "); 
		selectBookingDateUserApplicantsSQL.append("                 AG.AGENCYID = A.AGENCYID "); 
		selectBookingDateUserApplicantsSQL.append("             AND AG.ACTIVE = TRUE "); 

		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN AUDITORVIEW X1 ON X1.AUDITORID = BD.CANCELLEDBYID ");
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN AUDITORVIEW X2 ON X2.AUDITORID = BD.INVOICEDBYID ");
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN AUDITORVIEW X3 ON X3.AUDITORID = BD.REJECTEDBYID ");
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN AUDITORVIEW X4 ON X4.AUDITORID = BD.AUTHORIZEDBYID ");
		selectBookingDateUserApplicantsSQL.append(" LEFT OUTER JOIN AUDITORVIEW X5 ON X5.AUDITORID = BD.ACTIVATEDBYID ");
		
		selectBookingDateUserApplicantsSQL.append(", ");
		selectBookingDateUserApplicantsSQL.append("BOOKING B, ");
		
		selectBookingDateUserApplicantsSQL.append("LOCATIONVIEWALL LV, "); // Was LOCATIONVIEW until 12/09/2016
		selectBookingDateUserApplicantsSQL.append("JOBPROFILEVIEWALL JPV "); // Was JOBPROFILEVIEW until 13/09/2016
//		selectBookingDateUserApplicantsSQL.append("LOCATION L, ");
//		selectBookingDateUserApplicantsSQL.append("SITE SI, ");
//		selectBookingDateUserApplicantsSQL.append("CLIENT C, ");
//		selectBookingDateUserApplicantsSQL.append("JOBPROFILE JP, ");
//		selectBookingDateUserApplicantsSQL.append("JOBSUBFAMILY JSF, ");
//		selectBookingDateUserApplicantsSQL.append("JOBFAMILY JF ");
		
		selectBookingDateUserApplicantsSQL.append("WHERE BD.ACTIVE = TRUE ");
		selectBookingDateUserApplicantsSQL.append("AND B.BOOKINGID = BD.BOOKINGID "); 
		selectBookingDateUserApplicantsSQL.append("AND B.ACTIVE = TRUE ");
		selectBookingDateUserApplicantsSQL.append("AND JPV.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingDateUserApplicantsSQL.append("AND JP.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingDateUserApplicantsSQL.append("AND JP.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsSQL.append("AND JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
//		selectBookingDateUserApplicantsSQL.append("AND JSF.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsSQL.append("AND JF.JOBFAMILYID = JSF.JOBFAMILYID ");
//		selectBookingDateUserApplicantsSQL.append("AND JF.ACTIVE = TRUE ");
		selectBookingDateUserApplicantsSQL.append("AND LV.LOCATIONID = B.LOCATIONID ");
//		selectBookingDateUserApplicantsSQL.append("AND L.LOCATIONID = B.LOCATIONID ");
//		selectBookingDateUserApplicantsSQL.append("AND L.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsSQL.append("AND SI.SITEID = L.SITEID ");
//		selectBookingDateUserApplicantsSQL.append("AND SI.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsSQL.append("AND C.CLIENTID = SI.CLIENTID ");
//		selectBookingDateUserApplicantsSQL.append("AND C.ACTIVE = TRUE ");

		
		selectBookingDateUserApplicantsSQL.append("AND ( ");
		selectBookingDateUserApplicantsSQL.append(" ^ IS NULL "); //
		selectBookingDateUserApplicantsSQL.append("OR ");
		selectBookingDateUserApplicantsSQL.append(" B.SINGLECANDIDATE = ^ "); //
		selectBookingDateUserApplicantsSQL.append(") ");

        //
		selectBookingDateUserApplicantsForAdminSQL = new StringBuffer(selectBookingDateUserApplicantsSQL);
		selectBookingDateUserApplicantsForAdminSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");
		
        //
		selectBookingDateUserApplicantsForApplicantForAgencySQL = new StringBuffer(selectBookingDateUserApplicantsSQL);
		selectBookingDateUserApplicantsForApplicantForAgencySQL.append("AND A.APPLICANTID = ^ ");
		selectBookingDateUserApplicantsForApplicantForAgencySQL.append("AND A.AGENCYID = ^ ");

		//
		selectBookingDateUserApplicantsForApplicantForAgencyAndDateRangeSQL = new StringBuffer(selectBookingDateUserApplicantsForApplicantForAgencySQL);
		selectBookingDateUserApplicantsForApplicantForAgencyAndDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");
		
    // NEW ->
    selectBookingDateUserApplicantsForAgencyAndDateRangeSQL = new StringBuffer(selectBookingDateUserApplicantsSQL);
    selectBookingDateUserApplicantsForAgencyAndDateRangeSQL.append("AND A.AGENCYID = ^ ");
    selectBookingDateUserApplicantsForAgencyAndDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");
    // NEW <-
		//
		selectBookingDateUserApplicantsForManagerSQL = new StringBuffer(selectBookingDateUserApplicantsSQL);
		selectBookingDateUserApplicantsForManagerSQL.append("AND EXISTS ");
		selectBookingDateUserApplicantsForManagerSQL.append("( "); 
		selectBookingDateUserApplicantsForManagerSQL.append("SELECT NULL "); 
		selectBookingDateUserApplicantsForManagerSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP ");
		selectBookingDateUserApplicantsForManagerSQL.append("WHERE LMJP.MANAGERID = ^ ");
		selectBookingDateUserApplicantsForManagerSQL.append("AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectBookingDateUserApplicantsForManagerSQL.append("AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectBookingDateUserApplicantsForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectBookingDateUserApplicantsForManagerSQL.append(") ");  	

		selectBookingDateUserApplicantsForManagerSQL.append("AND ( ");
		selectBookingDateUserApplicantsForManagerSQL.append(" ^ IS NULL "); //
		selectBookingDateUserApplicantsForManagerSQL.append("OR ");
		selectBookingDateUserApplicantsForManagerSQL.append(" LV.SITEID = ^ "); //
		selectBookingDateUserApplicantsForManagerSQL.append(") ");

		selectBookingDateUserApplicantsForManagerSQL.append("AND ( ");
		selectBookingDateUserApplicantsForManagerSQL.append(" ^ IS NULL "); //
		selectBookingDateUserApplicantsForManagerSQL.append("OR ");
		selectBookingDateUserApplicantsForManagerSQL.append(" B.LOCATIONID = ^ "); //
		selectBookingDateUserApplicantsForManagerSQL.append(") ");

		selectBookingDateUserApplicantsForManagerSQL.append("AND ( ");
		selectBookingDateUserApplicantsForManagerSQL.append(" ^ IS NULL "); //
		selectBookingDateUserApplicantsForManagerSQL.append("OR ");
		selectBookingDateUserApplicantsForManagerSQL.append(" B.JOBPROFILEID = ^ "); //
		selectBookingDateUserApplicantsForManagerSQL.append(") ");
		
  
		//
		selectBookingDateUserApplicantsForManagerSearchSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerSearchSQL.append("AND ( ^ IS NULL OR BD.ACTIVATED = ^ ) ");
		selectBookingDateUserApplicantsForManagerSearchSQL.append("AND ( ^ IS NULL OR B.BOOKINGID = ^ ) ");
		selectBookingDateUserApplicantsForManagerSearchSQL.append("AND ( ^ IS NULL OR BD.BOOKINGDATEID = ^ ) ");
		selectBookingDateUserApplicantsForManagerSearchSQL.append("AND ( ^ IS NULL OR BGA.BOOKINGGRADEAPPLICANTID = ^ ) ");

//
//		 if managers are invoicing ...
//
//		selectBookingDateUserApplicantsForManagerSearchSQL.append("AND ( ^ IS NULL OR BD.INVOICEID = ^ ) ");

		selectBookingDateUserApplicantsForManagerSearchSQL.append("AND ( ^ IS NULL OR BD.AGENCYINVOICEID = ^ ) ");
		selectBookingDateUserApplicantsForManagerSearchSQL.append("AND ( ^ IS NULL OR BD.STATUS = ^ ) "); 

		selectBookingDateUserApplicantsForManagerSearchSQL.append("AND ( ^ IS NULL OR BD.WORKEDSTATUS = ^ " +
                " OR ( ^ = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_NULL +
                "     AND BD.WORKEDSTATUS IS NULL)" +
                "  ) ");

		//
		selectBookingDateUserApplicantsForManagerSearchWithDateRangeSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSearchSQL);
		selectBookingDateUserApplicantsForManagerSearchWithDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");
		
		//
		selectBookingDateUserApplicantsForApplicantSQL = new StringBuffer(selectBookingDateUserApplicantsSQL);
		selectBookingDateUserApplicantsForApplicantSQL.append("AND BGAD.STATUS = " + BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED + " ");
		
		//

		//
		//
		//
		selectBookingDateUserApplicantsForAgencySQL.append("FROM BOOKINGDATE BD ");
		selectBookingDateUserApplicantsForAgencySQL.append(" LEFT OUTER JOIN BOOKINGGRADEAPPLICANTDATE BGAD ON "); 
		selectBookingDateUserApplicantsForAgencySQL.append("                 BGAD.BOOKINGGRADEAPPLICANTDATEID = BD.BOOKINGGRADEAPPLICANTDATEID  ");
		selectBookingDateUserApplicantsForAgencySQL.append("             AND BGAD.ACTIVE = TRUE "); 
		// offered and filled ONLY - NOT open as there could be several, causing a cartesian product
		selectBookingDateUserApplicantsForAgencySQL.append("             AND BGAD.STATUS in (" + 
				                                                              BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OFFERED + ", " +
				                                                              BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED + ") ");
		selectBookingDateUserApplicantsForAgencySQL.append(" LEFT OUTER JOIN BOOKINGGRADEAPPLICANT BGA ON ");
		selectBookingDateUserApplicantsForAgencySQL.append("                 BGA.BOOKINGGRADEAPPLICANTID = BGAD.BOOKINGGRADEAPPLICANTID "); 
		selectBookingDateUserApplicantsForAgencySQL.append("             AND BGA.ACTIVE = TRUE "); 
		selectBookingDateUserApplicantsForAgencySQL.append(" LEFT OUTER JOIN APPLICANT A ON "); 
		selectBookingDateUserApplicantsForAgencySQL.append("                 A.APPLICANTID = BGA.APPLICANTID "); 
		selectBookingDateUserApplicantsForAgencySQL.append("             AND A.ACTIVE = TRUE "); 

		// TODO - currently assumes all aredone by MANAGER
		selectBookingDateUserApplicantsForAgencySQL.append(" LEFT OUTER JOIN AUDITORVIEW X1 ON X1.AUDITORID= BD.CANCELLEDBYID ");
		selectBookingDateUserApplicantsForAgencySQL.append(" LEFT OUTER JOIN AUDITORVIEW X2 ON X2.AUDITORID = BD.INVOICEDBYID ");
		selectBookingDateUserApplicantsForAgencySQL.append(" LEFT OUTER JOIN AUDITORVIEW X3 ON X3.AUDITORID = BD.REJECTEDBYID ");
		selectBookingDateUserApplicantsForAgencySQL.append(" LEFT OUTER JOIN AUDITORVIEW X4 ON X4.AUDITORID = BD.AUTHORIZEDBYID ");
		selectBookingDateUserApplicantsForAgencySQL.append(" LEFT OUTER JOIN AUDITORVIEW X5 ON X5.AUDITORID = BD.ACTIVATEDBYID ");

		selectBookingDateUserApplicantsForAgencySQL.append(", ");
		selectBookingDateUserApplicantsForAgencySQL.append("BOOKINGGRADE BG, ");
		selectBookingDateUserApplicantsForAgencySQL.append("CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("CLIENTAGENCYJOBPROFILE CAJP, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("CLIENTAGENCY CA, ");
		selectBookingDateUserApplicantsForAgencySQL.append("GRADE G, ");
		selectBookingDateUserApplicantsForAgencySQL.append("AGENCY AG, ");
		selectBookingDateUserApplicantsForAgencySQL.append("BOOKING B, ");
		selectBookingDateUserApplicantsForAgencySQL.append("LOCATIONVIEWALL LV, "); // Was LOCATIONVIEW until 12/09/2016
		selectBookingDateUserApplicantsForAgencySQL.append("JOBPROFILEVIEWALL JPV "); // Was JOBPROFILEVIEW until 13/09/2016
//		selectBookingDateUserApplicantsForAgencySQL.append("LOCATION L, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("SITE SI, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("CLIENT C, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("JOBPROFILE JP, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("JOBSUBFAMILY JSF, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("JOBFAMILY JF ");
		selectBookingDateUserApplicantsForAgencySQL.append("WHERE BD.ACTIVE = TRUE ");
		selectBookingDateUserApplicantsForAgencySQL.append("AND B.BOOKINGID = BD.BOOKINGID "); 
		selectBookingDateUserApplicantsForAgencySQL.append("AND B.ACTIVE = TRUE ");
		selectBookingDateUserApplicantsForAgencySQL.append("AND JPV.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND JP.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND JP.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND JSF.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND JF.JOBFAMILYID = JSF.JOBFAMILYID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND JF.ACTIVE = TRUE ");
		selectBookingDateUserApplicantsForAgencySQL.append("AND LV.LOCATIONID = B.LOCATIONID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND L.LOCATIONID = B.LOCATIONID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND L.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND SI.SITEID = L.SITEID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND SI.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND C.CLIENTID = SI.CLIENTID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND C.ACTIVE = TRUE ");

		selectBookingDateUserApplicantsForAgencySQL.append("AND ( ^ IS NULL OR B.SINGLECANDIDATE = ^ ) ");

//		selectBookingDateUserApplicantsForAgencySQL.append("AND CA.AGENCYID = ^ ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CA.ACTIVE = TRUE "); 
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CA.CLIENTID = C.CLIENTID ");

//		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJP.CLIENTAGENCYID = CA.CLIENTAGENCYID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJP.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEID = CAJP.CLIENTAGENCYJOBPROFILEID ");

		selectBookingDateUserApplicantsForAgencySQL.append("AND BG.BOOKINGID = B.BOOKINGID ");
		selectBookingDateUserApplicantsForAgencySQL.append("AND BG.ACTIVE = TRUE "); 
		selectBookingDateUserApplicantsForAgencySQL.append("AND BG.AGENCYID = ^ ");
		selectBookingDateUserApplicantsForAgencySQL.append("AND BG.STATUS IN (" + BookingGrade.BOOKINGGRADE_AGENCY_STATUSES + ") "); 
		selectBookingDateUserApplicantsForAgencySQL.append("AND ( ^ IS NULL OR BG.VIEWED = ^ ) "); 

		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJPG.ACTIVE = TRUE "); 
		selectBookingDateUserApplicantsForAgencySQL.append("AND G.GRADEID = CAJPG.GRADEID ");              
		selectBookingDateUserApplicantsForAgencySQL.append("AND G.ACTIVE = TRUE ");

		selectBookingDateUserApplicantsForAgencySQL.append("AND AG.AGENCYID = BG.AGENCYID ");              
		
//		selectBookingDateUserApplicantsForAgencySQL.append("AND AG.AGENCYID = CA.AGENCYID ");              
		selectBookingDateUserApplicantsForAgencySQL.append("AND AG.ACTIVE = TRUE ");
		
		//
		selectBookingDateUserApplicantsForAgencyAndInvoiceSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL);
		selectBookingDateUserApplicantsForAgencyAndInvoiceSQL.append("AND BD.INVOICEID = ^ ");
		
		//
		selectBookingDateUserApplicantsForAgencyAndAgencyInvoiceSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL);
		selectBookingDateUserApplicantsForAgencyAndAgencyInvoiceSQL.append("AND BD.AGENCYINVOICEID = ^ ");
		
		//
		selectBookingDateUserApplicantsForAgencyAndBookingDatesSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL);
		selectBookingDateUserApplicantsForAgencyAndBookingDatesSQL.append("AND BD.BOOKINGDATEID IN (^) ");

		//
		selectBookingDateUserApplicantForAgencyAndBookingDateSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL);
		selectBookingDateUserApplicantForAgencyAndBookingDateSQL.append("AND BD.BOOKINGDATEID = ^ ");

		//
		selectBookingDateUserApplicantsForAgencyAndBookingSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL);
		selectBookingDateUserApplicantsForAgencyAndBookingSQL.append("AND BD.BOOKINGID = ^ ");

		
//		selectBookingDateUserApplicantsForAgencySQL.append("AND EXISTS ");
//		selectBookingDateUserApplicantsForAgencySQL.append("( "); 
//		selectBookingDateUserApplicantsForAgencySQL.append("SELECT NULL ");
//		selectBookingDateUserApplicantsForAgencySQL.append("FROM BOOKINGGRADE BG, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("     CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("     CLIENTAGENCYJOBPROFILE CAJP, ");
//		selectBookingDateUserApplicantsForAgencySQL.append("     CLIENTAGENCY CA ");
//		selectBookingDateUserApplicantsForAgencySQL.append("WHERE CA.AGENCYID = ^ ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CA.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJP.CLIENTAGENCYID = CA.CLIENTAGENCYID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJP.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEID = CAJP.CLIENTAGENCYJOBPROFILEID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND CAJPG.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND BG.CLIENTAGENCYJOBPROFILEGRADEID = CAJPG.CLIENTAGENCYJOBPROFILEGRADEID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND BG.ACTIVE = TRUE ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND BG.STATUS IN (" + BookingGrade.BOOKINGGRADE_AGENCY_STATUSES + ") ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND BG.BOOKINGID = B.BOOKINGID ");
//		selectBookingDateUserApplicantsForAgencySQL.append("AND ( ");
//		selectBookingDateUserApplicantsForAgencySQL.append(" ^ IS NULL "); //
//		selectBookingDateUserApplicantsForAgencySQL.append("OR ");
//		selectBookingDateUserApplicantsForAgencySQL.append(" BG.VIEWED = ^ "); //
//		selectBookingDateUserApplicantsForAgencySQL.append(") ");
//		selectBookingDateUserApplicantsForAgencySQL.append(") ");  	
		
		selectBookingDateUserApplicantsForAgencySQL.append("AND ( ");
		selectBookingDateUserApplicantsForAgencySQL.append(" ^ IS NULL "); //
		selectBookingDateUserApplicantsForAgencySQL.append("OR ");
		selectBookingDateUserApplicantsForAgencySQL.append(" LV.CLIENTID = ^ "); //
		selectBookingDateUserApplicantsForAgencySQL.append(") ");

		selectBookingDateUserApplicantsForAgencySQL.append("AND ( ");
		selectBookingDateUserApplicantsForAgencySQL.append(" ^ IS NULL "); //
		selectBookingDateUserApplicantsForAgencySQL.append("OR ");
		selectBookingDateUserApplicantsForAgencySQL.append(" LV.SITEID = ^ "); //
		selectBookingDateUserApplicantsForAgencySQL.append(") ");

		selectBookingDateUserApplicantsForAgencySQL.append("AND ( ");
		selectBookingDateUserApplicantsForAgencySQL.append(" ^ IS NULL "); //
		selectBookingDateUserApplicantsForAgencySQL.append("OR ");
		selectBookingDateUserApplicantsForAgencySQL.append(" B.LOCATIONID = ^ "); //
		selectBookingDateUserApplicantsForAgencySQL.append(") ");

		selectBookingDateUserApplicantsForAgencySQL.append("AND ( ");
		selectBookingDateUserApplicantsForAgencySQL.append(" ^ IS NULL "); //
		selectBookingDateUserApplicantsForAgencySQL.append("OR ");
		selectBookingDateUserApplicantsForAgencySQL.append(" B.JOBPROFILEID = ^ "); //
		selectBookingDateUserApplicantsForAgencySQL.append(") ");
		
        //
		selectBookingDateUserApplicantsForAgencySearchSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL);
		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BD.ACTIVATED = ^ ) ");
		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR B.BOOKINGID = ^ ) ");
		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BD.BOOKINGDATEID = ^ ) ");
		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BGA.BOOKINGGRADEAPPLICANTID = ^ ) ");
		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BGA.APPLICANTID = ^ ) ");

//
//		 if managers are invoicing ...
//
//		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BD.INVOICEID = ^ ) ");

		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BD.AGENCYINVOICEID = ^ ) ");
		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BD.STATUS = ^ ) "); 

		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BD.WORKEDSTATUS = ^ " +
				                                                   " OR ( ^ = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_NULL +
				                                                   "     AND BD.WORKEDSTATUS IS NULL)" +
				                                                   "  ) ");
		
		selectBookingDateUserApplicantsForAgencySearchSQL.append("AND ( ^ IS NULL OR BG.STATUS = ^ ) ");

    //
    selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchSQL);
    selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL.append("AND BD.BACKINGREPORT IS NULL ");

    //
    selectBookingDateUserApplicantsForAgencyOnBackingReportSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchSQL);
    selectBookingDateUserApplicantsForAgencyOnBackingReportSQL.append("AND BD.BACKINGREPORT IS NOT NULL ");

    //
    selectBookingDateUserApplicantsForAgencyNotOnBackingReportCountSQL = new StringBuffer("SELECT COUNT(*) ");
    selectBookingDateUserApplicantsForAgencyNotOnBackingReportCountSQL.append(selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL.substring(selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL.indexOf("FROM")));

    //
    selectBookingDateUserApplicantsForAgencyOnBackingReportCountSQL = new StringBuffer("SELECT COUNT(*) ");
    selectBookingDateUserApplicantsForAgencyOnBackingReportCountSQL.append(selectBookingDateUserApplicantsForAgencyOnBackingReportSQL.substring(selectBookingDateUserApplicantsForAgencyOnBackingReportSQL.indexOf("FROM")));

    //
		selectBookingDateUserApplicantsForAgencySearchWithDateRangeSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchSQL);
		selectBookingDateUserApplicantsForAgencySearchWithDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");

		//
		selectBookingDateUserApplicantsForAgencyAndStatusSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL);
		selectBookingDateUserApplicantsForAgencyAndStatusSQL.append("AND BD.STATUS = ^ ");
		
		//
		selectBookingDateUserApplicantsForAgencyAndBookingGradeApplicantSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL);
		selectBookingDateUserApplicantsForAgencyAndBookingGradeApplicantSQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = ^ ");

		//
		selectBookingDateUserApplicantsForManagerAndBookingSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerAndBookingSQL.append("AND B.BOOKINGID = ^ ");

		selectBookingDateUserApplicantsForManagerAndBookingSQL.append("AND (  ^ IS NULL OR  BD.STATUS = ^ ) "); 

		selectBookingDateUserApplicantsForManagerAndBookingSQL.append("AND ( ^ IS NULL OR BD.WORKEDSTATUS = ^ " +
                " OR ( ^ = " + BookingDate.BOOKINGDATE_WORKEDSTATUS_NULL +
                "     AND BD.WORKEDSTATUS IS NULL)" +
                "  ) ");
		
		
		//
		selectBookingDateUserApplicantsForManagerAndBookingAndDateRangeSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerAndBookingSQL);
		selectBookingDateUserApplicantsForManagerAndBookingAndDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");
		
		//
		selectBookingDateUserApplicantsForManagerAndBookingGradeApplicantSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerAndBookingGradeApplicantSQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = ^ ");

		//
		selectBookingDateUserApplicantsForManagerAndStatusSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerAndStatusSQL.append("AND BD.STATUS = ^ ");
		
		//
		selectBookingDateUserApplicantsForManagerAndStatusAndDateRangeSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerAndStatusSQL);
		selectBookingDateUserApplicantsForManagerAndStatusAndDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");
		
		//
		selectBookingDateUserApplicantsForManagerAndDateRangeSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerAndDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");

        //		
		selectBookingDateUserApplicantsForManagerAndWorkedStatusSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerAndWorkedStatusSQL.append("AND BD.WORKEDSTATUS = ^ ");
		
		//
		selectBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRangeSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerAndWorkedStatusSQL);
		selectBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");
		
		//
		selectBookingDateUserApplicantForManagerAndBookingDateSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantForManagerAndBookingDateSQL.append("AND BD.BOOKINGDATEID = ^ ");

		//
		selectBookingDateUserApplicantsForManagerAndBookingDatesSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerAndBookingDatesSQL.append("AND BD.BOOKINGDATEID IN (^) ");

		//
		selectBookingDateUserApplicantsForManagerAndInvoiceSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerAndInvoiceSQL.append("AND BD.INVOICEID = ^ ");

		//
		selectBookingDateUserApplicantsForManagerAndAgencyInvoiceSQL = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL);
		selectBookingDateUserApplicantsForManagerAndAgencyInvoiceSQL.append("AND BD.AGENCYINVOICEID = ^ ");

		//
		selectBookingDateUserApplicantsForApplicantForBookingGradeApplicantSQL = new StringBuffer(selectBookingDateUserApplicantsForApplicantSQL);
		selectBookingDateUserApplicantsForApplicantForBookingGradeApplicantSQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = ^ ");

		//
		selectBookingDateUserApplicantForApplicantForBookingDateSQL = new StringBuffer(selectBookingDateUserApplicantsForApplicantSQL);
		selectBookingDateUserApplicantForApplicantForBookingDateSQL.append("AND BD.BOOKINGDATEID = ^ ");

    selectBookingDateUserApplicantsForAgencySearchByClientSiteLocationSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchSQL);
    selectBookingDateUserApplicantsForAgencySearchWithDateRangeByClientSiteLocationSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchWithDateRangeSQL);
		//
    selectBookingDateUserApplicantsForAgencyBackingReportSQL = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchWithDateRangeByClientSiteLocationSQL);
    selectBookingDateUserApplicantsForAgencyBackingReportSQL.append("AND BD.BACKINGREPORT LIKE '%^%'  ");
    //
		selectBookingDateUserApplicantsForManagerSearchSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerSearchWithDateRangeSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
    selectBookingDateUserApplicantsForAgencySearchSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
    selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME DESC, BD.BOOKINGDATEID DESC");
    selectBookingDateUserApplicantsForAgencyOnBackingReportSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME DESC, BD.BOOKINGDATEID DESC");
    selectBookingDateUserApplicantsForAgencySearchByClientSiteLocationSQL.append("ORDER BY LV.CLIENTNAME, LV.SITENAME, LV.LOCATIONNAME, BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
    selectBookingDateUserApplicantsForAgencySearchWithDateRangeSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
    selectBookingDateUserApplicantsForAgencySearchWithDateRangeByClientSiteLocationSQL.append("ORDER BY LV.CLIENTNAME, LV.SITENAME, LV.LOCATIONNAME, BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForAgencyAndInvoiceSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForAgencyAndAgencyInvoiceSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForAgencyAndBookingDatesSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndBookingSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndBookingAndDateRangeSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndBookingDatesSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndBookingGradeApplicantSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndInvoiceSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndAgencyInvoiceSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForAdminSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForApplicantForAgencySQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
    // NEW ->
    selectBookingDateUserApplicantsForAgencyAndDateRangeSQL.append("ORDER BY A.APPLICANTID, BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
    // NEW <-
    selectBookingDateUserApplicantsForApplicantForAgencyAndDateRangeSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndStatusSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndStatusAndDateRangeSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndDateRangeSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndWorkedStatusSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRangeSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserApplicantsForApplicantForBookingGradeApplicantSQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		selectBookingDateUserGradesForAgencySQL.append("ORDER BY BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		
		selectBookingDateUserApplicantsForAgencyAndBookingSQL.append("ORDER BY BD.BOOKINGDATE ");
    selectBookingDateUserApplicantsForAgencyBackingReportSQL.append("ORDER BY LV.CLIENTNAME, LV.SITENAME, LV.LOCATIONNAME, BD.BOOKINGDATE + BD.SHIFTSTARTTIME, BD.BOOKINGDATEID ");
		
		// Get select BookingDateUsers for Booking SQL.
		selectBookingDateUsersForBookingSQL = new StringBuffer(selectBookingDateUsersSQL);
		selectBookingDateUsersForBookingSQL.append("AND BD.BOOKINGID = ^ ");
		// Get select Active BookingDateUsers for BookingSQL.
		selectActiveBookingDateUsersForBookingSQL = new StringBuffer(selectBookingDateUsersForBookingSQL);
		selectActiveBookingDateUsersForBookingSQL.append("AND BD.ACTIVE = TRUE ");

		// Get select BookingDateUsers for Booking for Agency SQL.
		selectBookingDateUsersForBookingAndAgencySQL = new StringBuffer(selectBookingDateUsersForBookingSQL);
		// not really for client just restrict the visible statuses
		selectBookingDateUsersForBookingAndAgencySQL.append("AND BD.STATUS IN (" + BookingDate.BOOKINGDATE_AGENCY_STATUSES + ")");
		// Get select Active BookingDateUsers for BookingSQL.
		selectActiveBookingDateUsersForBookingAndAgencySQL = new StringBuffer(selectBookingDateUsersForBookingAndAgencySQL);
		selectActiveBookingDateUsersForBookingAndAgencySQL.append("AND BD.ACTIVE = TRUE ");

		//
		selectBookingDatesSQL.append("FROM BOOKINGDATE BD ");

		// Get select BookingDates for Booking SQL.
		selectBookingDatesForBookingSQL = new StringBuffer(selectBookingDatesSQL);
		selectBookingDatesForBookingSQL.append("WHERE BD.BOOKINGID = ^ ");
		
    // Get select BookingDates for Nhs Backing Report SQL.
    selectBookingDatesForNhsBackingReportSQL = new StringBuffer(selectBookingDatesSQL);
    selectBookingDatesForNhsBackingReportSQL.append("WHERE BD.BACKINGREPORT LIKE '%^%' ");

    // Get select BookingDates for AgencyInvoice SQL.
		selectBookingDatesForAgencyInvoiceSQL = new StringBuffer(selectBookingDatesSQL);
		selectBookingDatesForAgencyInvoiceSQL.append("WHERE BD.AGENCYINVOICEID = ^ ");
		selectBookingDatesForAgencyInvoiceSQL.append("AND BD.ACTIVE = TRUE ");
		selectBookingDatesForAgencyInvoiceSQL.append("ORDER BY BD.BOOKINGDATE ");
		
		// Get select BookingDates for BookingDates SQL.
		selectBookingDatesForBookingDatesSQL = new StringBuffer(selectBookingDatesSQL);
		selectBookingDatesForBookingDatesSQL.append("WHERE BD.BOOKINGDATEID IN (^) ");

		
		// Get select Active BookingDates for BookingSQL.
		selectActiveBookingDatesForBookingSQL = new StringBuffer(selectBookingDatesForBookingSQL);
		selectActiveBookingDatesForBookingSQL.append("AND BD.ACTIVE = TRUE ");

		// Get select BookingDates for Booking for Agency SQL.
		selectBookingDatesForBookingAndAgencySQL = new StringBuffer(selectBookingDatesForBookingSQL);
		// not really for client just restrict the visible statuses
		selectBookingDatesForBookingAndAgencySQL.append("AND BD.STATUS IN (" + BookingDate.BOOKINGDATE_AGENCY_STATUSES + ")");
		// Get select Active BookingDates for BookingSQL.
		selectActiveBookingDatesForBookingAndAgencySQL = new StringBuffer(selectBookingDatesForBookingAndAgencySQL);
		selectActiveBookingDatesForBookingAndAgencySQL.append("AND BD.ACTIVE = TRUE ");
        
		//
    selectBookingDatesForNhsBackingReportSQL.append("ORDER BY BD.BOOKINGDATE ");
    selectBookingDatesForBookingSQL.append("ORDER BY BD.BOOKINGDATE ");
        //
		selectActiveBookingDatesForBookingSQL.append("ORDER BY BD.BOOKINGDATE ");
		// Get select BookingDate SQL.
		selectBookingDateSQL = new StringBuffer(selectBookingDatesSQL);
		selectBookingDateSQL.append("WHERE BD.BOOKINGDATEID = ^ ");

        //
		selectBookingDatesForBookingAndAgencySQL.append("ORDER BY BD.BOOKINGDATEID ");
        //
		selectActiveBookingDatesForBookingAndAgencySQL.append("ORDER BY BD.BOOKINGDATEID");
	

		//
		selectBookingDateUsersForBookingSQL.append("ORDER BY BD.BOOKINGDATE ");
        //
		selectActiveBookingDateUsersForBookingSQL.append("ORDER BY BD.BOOKINGDATE ");
        //
		selectBookingDateUsersForBookingAndAgencySQL.append("ORDER BY BD.BOOKINGDATEID ");
        //
		selectActiveBookingDateUsersForBookingAndAgencySQL.append("ORDER BY BD.BOOKINGDATEID");

    selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL.append(" OFFSET ^ ");
    selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL.append(" LIMIT ^ ");

    selectBookingDateUserApplicantsForAgencyOnBackingReportSQL.append(" OFFSET ^ ");
    selectBookingDateUserApplicantsForAgencyOnBackingReportSQL.append(" LIMIT ^ ");
	}

	public int insertBookingDate(BookingDate bookingDate, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		bookingDate.setBookingDateId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "bookingDate"));
		Utilities.replace(sql, bookingDate.getBookingDateId());
		Utilities.replace(sql, bookingDate.getBookingId());
		Utilities.replaceAndQuote(sql, bookingDate.getBookingDate());
		Utilities.replace(sql, bookingDate.getShiftId());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftName());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftCode());
		Utilities.replace(sql, bookingDate.getShiftUseUplift());
		Utilities.replace(sql, bookingDate.getShiftUpliftFactor());
		Utilities.replace(sql, bookingDate.getShiftUpliftValue());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftStartTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftEndTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftBreakStartTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftBreakEndTime());
		Utilities.replaceZeroWithNull(sql, bookingDate.getShiftNoOfHours());
		Utilities.replaceZeroWithNull(sql, bookingDate.getShiftBreakNoOfHours());
		Utilities.replace(sql, bookingDate.getShiftOvertimeNoOfHours());
		Utilities.replace(sql, bookingDate.getShiftOvertimeUpliftFactor());
		Utilities.replace(sql, bookingDate.getValue());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingDate(BookingDate bookingDate, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, bookingDate.getBookingDate());
		Utilities.replace(sql, bookingDate.getShiftId());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftName());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftCode());
		Utilities.replace(sql, bookingDate.getShiftUseUplift());
		Utilities.replace(sql, bookingDate.getShiftUpliftFactor());
		Utilities.replace(sql, bookingDate.getShiftUpliftValue());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftStartTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftEndTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftBreakStartTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getShiftBreakEndTime());
		Utilities.replaceZeroWithNull(sql, bookingDate.getShiftNoOfHours());
		Utilities.replaceZeroWithNull(sql, bookingDate.getShiftBreakNoOfHours());
		Utilities.replace(sql, bookingDate.getShiftOvertimeNoOfHours());
		Utilities.replace(sql, bookingDate.getShiftOvertimeUpliftFactor());
		Utilities.replace(sql, bookingDate.getValue());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDate.getBookingDateId());
		Utilities.replace(sql, bookingDate.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}
	
	public int updateBookingDateCancel(Integer bookingDateId, String cancelText, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateCancelSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, cancelText);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingDateFilled(Integer bookingDateId, Integer noOfChanges, Integer auditorId, Integer bookingGradeApplicantDateId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateFilledSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantDateId);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
	public int updateBookingDateOffered(Integer bookingDateId, Integer noOfChanges, Integer auditorId, Integer bookingGradeApplicantDateId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateOfferedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantDateId);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
	public int updateBookingDateRefused(Integer bookingDateId, Integer noOfChanges, Integer auditorId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateRefusedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
	public int updateBookingDateStatus(Integer bookingDateId, Integer noOfChanges,
			Integer auditorId, int status) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, status);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingDateActivated(Integer bookingDateId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateActivatedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingDateWorked(BookingDate bookingDate, Integer auditorId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateWorkedSQL.toString());
		// Replace the parameters with supplied values.

		// should only be set to draft if start time entered OR comment entered 
		if (bookingDate.getWorkedStartTime() != null ||
			(bookingDate.getComment() != null && !"".equals(bookingDate.getComment()))
			) {
			Utilities.replace(sql, BookingDate.BOOKINGDATE_WORKEDSTATUS_DRAFT);
		}
		else {
			Utilities.replaceZeroWithNull(sql, 0);
		}
		
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getWorkedStartTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getWorkedEndTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getWorkedBreakStartTime());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getWorkedBreakEndTime());
		Utilities.replace(sql, bookingDate.getWorkedNoOfHours());
		Utilities.replace(sql, bookingDate.getWorkedBreakNoOfHours());
		Utilities.replace(sql, bookingDate.getWorkedChargeRateValue());
		Utilities.replace(sql, bookingDate.getWorkedPayRateValue());
		Utilities.replace(sql, bookingDate.getWorkedWtdValue());
		Utilities.replace(sql, bookingDate.getWorkedNiValue());
		Utilities.replace(sql, bookingDate.getWorkedCommissionValue());
		Utilities.replace(sql, bookingDate.getWorkedChargeRateVatValue());
		Utilities.replace(sql, bookingDate.getWorkedPayRateVatValue());
		Utilities.replace(sql, bookingDate.getWorkedWtdVatValue());
		Utilities.replace(sql, bookingDate.getWorkedNiVatValue());
		Utilities.replace(sql, bookingDate.getWorkedCommissionVatValue());
		Utilities.replace(sql, bookingDate.getWorkedVatValue());
		Utilities.replace(sql, bookingDate.getWorkedWageRateValue());
		Utilities.replace(sql, bookingDate.getHasUplift());
		Utilities.replaceAndQuoteNullable(sql, bookingDate.getComment());
		Utilities.replace(sql, bookingDate.getFeePerShift());
		Utilities.replace(sql, bookingDate.getFeePerHour());
		Utilities.replace(sql, bookingDate.getFeePercentage());
		Utilities.replace(sql, bookingDate.getFeeValue());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDate.getBookingDateId());
		Utilities.replace(sql, bookingDate.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
	public int updateBookingDateSubmitted(BookingDate bookingDate, Integer auditorId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateSubmittedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDate.getBookingDateId());
		Utilities.replace(sql, bookingDate.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
	public int updateBookingDateWithdrawn(BookingDate bookingDate, Integer auditorId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateWithdrawnSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDate.getBookingDateId());
		Utilities.replace(sql, bookingDate.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
    public int updateBookingDateRejected(Integer bookingDateId, String rejectText, Integer noOfChanges, Integer auditorId) {
    	
    	// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateRejectedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, rejectText);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

    }

    public int updateBookingDateAuthorized(Integer bookingDateId, Integer noOfChanges, Integer auditorId) {

    	// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateAuthorizedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	
    }
	
    public int updateBookingDateInvoiced(Integer bookingDateId, Integer invoiceId, Integer noOfChanges, Integer auditorId) {

    	// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateInvoicedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, invoiceId);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	
    }
	
	public int updateBookingDateExpenses(Integer bookingDateId, Integer noOfChanges, BigDecimal value, BigDecimal vatValue, Integer auditorId) {

    	// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateExpensesSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, value);
		Utilities.replace(sql, vatValue);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}

	public int updateBookingDateOvertime(BookingDateUserApplicant bookingDate, Integer auditorId) {
		
    	// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateOvertimeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDate.getShiftOvertimeNoOfHours());
		Utilities.replace(sql, bookingDate.getShiftOvertimeUpliftFactor());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDate.getBookingDateId());
		Utilities.replace(sql, bookingDate.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
	private int updateBookingDateCredited(Integer bookingDateId, Integer noOfChanges, Integer auditorId, Integer agencyInvoiceCreditId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateCreditedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyInvoiceCreditId);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
	public int updateBookingDatesCreditedForAgencyInvoice(Integer agencyInvoiceId, Integer auditorId, Integer agencyInvoiceCreditId){
		
		int rc = 0;
		
		List<BookingDate> bookingDatesToBeCredited = getBookingDatesForAgencyInvoice(agencyInvoiceId);
		for (BookingDate bookingDate: bookingDatesToBeCredited) {
			rc += updateBookingDateCredited(bookingDate.getBookingDateId(), bookingDate.getNoOfChanges(), auditorId, agencyInvoiceCreditId);
		}
		return rc;
	
	}
	
  public int updateBookingDateBackingReport(BookingDate bookingDate, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateBookingDateBackingReportSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuoteNullable(sql, bookingDate.getBackingReport());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, bookingDate.getBookingDateId());
    Utilities.replace(sql, bookingDate.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
	public int deleteBookingDate(Integer bookingDateId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public BookingDate getBookingDate(Integer bookingDateId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDateId);
		return (BookingDate) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDate.class.getName());
	}

	public BookingDateUser getBookingDateUser(Integer bookingDateId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingDateUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDateId);
		return (BookingDateUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUser.class.getName());
	}

	public int deleteBookingDatesForBooking(Integer bookingId, Integer auditorId) {

		int i = 0;
		List<BookingDate> bookingDates = getBookingDatesForBooking(bookingId, true);
		for (BookingDate bookingDate: bookingDates) {
            i += deleteBookingDate(bookingDate.getBookingDateId(), bookingDate.getNoOfChanges(), auditorId);
		}
		return i;

	}

	public int insertBookingDates(Integer bookingId, BookingDate[] bookingDates, Integer auditorId) {

		int i = 0;
		for (BookingDate bookingDate: bookingDates) {
        	bookingDate.setBookingId(bookingId);
        	i += insertBookingDate(bookingDate, auditorId);
        }
		return i;
		
	}

  public List<BookingDate> getBookingDatesForNhsBackingReport(String backingReport) 
  {

    StringBuffer sql = new StringBuffer(selectBookingDatesForNhsBackingReportSQL.toString()); 
    // Replace the parameters with supplied values.
    Utilities.replace(sql, backingReport);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingDate.class.getName());
  }

	public List<BookingDate> getBookingDatesForBooking(Integer bookingId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingDatesForBookingSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingDatesForBookingSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDate.class.getName());

	}
	
	public List<BookingDate> getBookingDatesForAgencyInvoice(Integer agencyInvoiceId) {

		StringBuffer sql = new StringBuffer(selectBookingDatesForAgencyInvoiceSQL.toString()); 
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyInvoiceId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDate.class.getName());

	}
	
	public List<BookingDate> getBookingDatesForBookingDates(String bookingDateIdStrs) {
		
		StringBuffer sql = new StringBuffer(selectBookingDatesForBookingDatesSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDateIdStrs);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDate.class.getName());

	}
	

	public List<BookingDate> getBookingDatesForBookingAndAgency(Integer bookingId, Integer agencyId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingDatesForBookingAndAgencySQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingDatesForBookingAndAgencySQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
//		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDate.class.getName());

	}

	public List<BookingDateUser> getBookingDateUsersForBooking(Integer bookingId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingDateUsersForBookingSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingDateUsersForBookingSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUser.class.getName());

	}

	public List<BookingDateUser> getBookingDateUsersForBookingAndAgency(Integer bookingId, Integer agencyId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingDateUsersForBookingAndAgencySQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingDateUsersForBookingAndAgencySQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
//		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUser.class.getName());

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingDateStatus, Integer workedStatus,                                                  
			Boolean singleCandidate, Boolean activated, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {
		
		StringBuffer sql = null;
		if (fromDate == null) {
			sql = new StringBuffer(selectBookingDateUserApplicantsForManagerSearchSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingDateUserApplicantsForManagerSearchWithDateRangeSQL.toString());
		}

		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);
		
		Utilities.replace(sql, activated);
		Utilities.replace(sql, activated);
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, invoiceId);
		Utilities.replace(sql, invoiceId);
		Utilities.replace(sql, bookingDateStatus);
		Utilities.replace(sql, bookingDateStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, workedStatus);
		
		if (fromDate != null) {
		    if (toDate == null) {
                // FORCE for last year
		    	Utilities.replace(sql, "CURRENT_DATE - INTERVAL '1 YEAR'");
				Utilities.replace(sql, "CURRENT_DATE");
		    }
		    else {
				Utilities.replaceAndQuote(sql, fromDate);
				Utilities.replaceAndQuote(sql, toDate);
		    }
		}
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsToActivateForManager(Integer managerId) { 

		return getBookingDateUserApplicantsForManager(managerId, null, null, null, null, BookingDate.BOOKINGDATE_STATUS_FILLED, 
				                                                 null, null, false, null, null, null, new Date(new java.util.Date().getTime()), null);
		
	}
	
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgency(Integer agencyId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
        	Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
        	Date fromDate, Date toDate) 
  {
		
		StringBuffer sql = null;
    if (fromDate == null)
    {
      sql = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchSQL.toString());
    }
    else
    {
      sql = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchWithDateRangeSQL.toString());
    }

    // Replace the parameters with supplied values.
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);

    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, viewed);
    Utilities.replace(sql, viewed);

    Utilities.replace(sql, clientId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, jobProfileId);

    Utilities.replace(sql, activated);
    Utilities.replace(sql, activated);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, bookingGradeStatus);

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

		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	
	}

  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyByClientSiteLocation(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
          Date fromDate, Date toDate) 
  {
    
    StringBuffer sql = null;
    if (fromDate == null)
    {
      sql = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchByClientSiteLocationSQL.toString());
    }
    else
    {
      sql = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchWithDateRangeByClientSiteLocationSQL.toString());
    }

    // Replace the parameters with supplied values.
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);

    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, viewed);
    Utilities.replace(sql, viewed);

    Utilities.replace(sql, clientId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, jobProfileId);

    Utilities.replace(sql, activated);
    Utilities.replace(sql, activated);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, bookingGradeStatus);

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

    return RecordListFactory.getInstance().get(getJdbcTemplate(),
        sql.toString(), BookingDateUserApplicant.class.getName());
    
  
  }

  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
          Date fromDate, Date toDate, String backingReportName) 
  {
    
    StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyBackingReportSQL.toString());

    // Replace the parameters with supplied values.
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);

    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, viewed);
    Utilities.replace(sql, viewed);

    Utilities.replace(sql, clientId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, jobProfileId);

    Utilities.replace(sql, activated);
    Utilities.replace(sql, activated);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replaceAndQuote(sql, fromDate);
    Utilities.replaceAndQuote(sql, toDate);
    Utilities.replace(sql, backingReportName);

    return RecordListFactory.getInstance().get(getJdbcTemplate(),
        sql.toString(), BookingDateUserApplicant.class.getName());
    
  
  }

  // Only for Authorised Timesheets.
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyNotOnBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, Integer offset) 
  {
    
    StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyNotOnBackingReportSQL.toString());

    // Replace the parameters with supplied values.
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);

    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, viewed);
    Utilities.replace(sql, viewed);

    Utilities.replace(sql, clientId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, jobProfileId);

    Utilities.replace(sql, activated);
    Utilities.replace(sql, activated);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, offset);
    Utilities.replace(sql, getPagingLimit() == null ? 100 : getPagingLimit());

    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingDateUserApplicant.class.getName());
  
  }

  // Only for Authorised Timesheets.
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyOnBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, Integer offset) 
  {
    
    StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyOnBackingReportSQL.toString());

    // Replace the parameters with supplied values.
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);

    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, viewed);
    Utilities.replace(sql, viewed);

    Utilities.replace(sql, clientId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, jobProfileId);

    Utilities.replace(sql, activated);
    Utilities.replace(sql, activated);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, offset);
    Utilities.replace(sql, getPagingLimit() == null ? 100 : getPagingLimit());

    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingDateUserApplicant.class.getName());
  
  }

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsToActivateForAgency(Integer agencyId) { 

    	return getBookingDateUserApplicantsForAgency(agencyId, 
				null, null, null, null, null, BookingDate.BOOKINGDATE_STATUS_FILLED, null,                                                  
				null, false, null, null, null, null, null, null, new Date(new java.util.Date().getTime()), null);
		
	}
	
    // COPIED FROM METHOD ABOVE !!!
	
	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForAgency(Integer agencyId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
        	Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
        	Date fromDate, Date toDate) {
		
		StringBuffer sql = null;
		if (fromDate == null) {
			sql = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingDateUserApplicantsForAgencySearchWithDateRangeSQL.toString());
		}

		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);

		Utilities.replace(sql, clientId);
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);
		
		Utilities.replace(sql, activated);
		Utilities.replace(sql, activated);
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, applicantId);
		Utilities.replace(sql, applicantId);
		Utilities.replace(sql, invoiceId);
		Utilities.replace(sql, invoiceId);
		Utilities.replace(sql, bookingDateStatus);
		Utilities.replace(sql, bookingDateStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, bookingGradeStatus);
		Utilities.replace(sql, bookingGradeStatus);
		
		if (fromDate != null) {
			Utilities.replaceAndQuote(sql, fromDate);
			Utilities.replaceAndQuote(sql, toDate);
		}

		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicantEntity.class.getName());
		
	
	}
	
	
	
	
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBooking(Integer managerId, Integer bookingId) {

		return getBookingDateUserApplicantsForManagerAndBooking(managerId, bookingId, null, null);
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBooking(Integer managerId, Integer bookingId, Integer bookingDateStatus, Integer workedStatus) {
		
		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndBookingSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);
		
		Utilities.replace(sql, bookingId);
		
		Utilities.replace(sql, bookingDateStatus);
		Utilities.replace(sql, bookingDateStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, workedStatus);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingAndDateRange(Integer managerId, Integer bookingId, Integer bookingDateStatus, Integer workedStatus, Date fromDate, Date toDate) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndBookingAndDateRangeSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);
		
		Utilities.replace(sql, bookingId);

		Utilities.replace(sql, bookingDateStatus);
		Utilities.replace(sql, bookingDateStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, workedStatus);
		Utilities.replace(sql, workedStatus);

		Utilities.replaceAndQuote(sql, fromDate);
		Utilities.replaceAndQuote(sql, toDate);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	
    }
	
    public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingDate(Integer managerId, Integer bookingDateId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantForManagerAndBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);
		
		Utilities.replace(sql, bookingDateId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingDates(Integer managerId, String bookingDateIdStrs) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndBookingDatesSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);
		
        Utilities.replace(sql, bookingDateIdStrs);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public BookingDateUserApplicant getBookingDateUserApplicantForManagerAndBookingDate(Integer managerId, Integer bookingDateId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantForManagerAndBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);
		
		Utilities.replace(sql, bookingDateId);
		return (BookingDateUserApplicant) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingGradeApplicant(Integer managerId, Integer bookingGradeApplicantId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndBookingGradeApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);
		
        Utilities.replace(sql, bookingGradeApplicantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());

	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatus(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, bookingDateStatus);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());

	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatusAndDateRange(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndStatusAndDateRangeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, bookingDateStatus);
		Utilities.replaceAndQuote(sql, fromDate);
		Utilities.replaceAndQuote(sql, toDate);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {
		
		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndDateRangeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replaceAndQuote(sql, fromDate);
		Utilities.replaceAndQuote(sql, toDate);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRange(Integer managerId, Integer workedStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRangeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, workedStatus);
		Utilities.replaceAndQuote(sql, fromDate);
		Utilities.replaceAndQuote(sql, toDate);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatus(Integer managerId, Integer workedStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndWorkedStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, managerId);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, workedStatus);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndInvoice(Integer managerId, Integer invoiceId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, invoiceId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForInvoiceForAgency(Integer invoiceId, Integer agencyId) {
		return null;
	}
	

//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgency(Integer agencyId, Boolean singleCandidate, Boolean viewed) {
//
//		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencySQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, singleCandidate);
//		Utilities.replace(sql, singleCandidate);
//		Utilities.replace(sql, agencyId);
//		Utilities.replace(sql, viewed);
//		Utilities.replace(sql, viewed);
//		
//		return RecordListFactory.getInstance().get(getJdbcTemplate(),
//				sql.toString(), BookingDateUserApplicant.class.getName());
//		
//	}
//
//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndStatus(Integer agencyId, Boolean singleCandidate, Boolean viewed, Integer bookingDateStatus) {
//
//		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyAndStatusSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, singleCandidate);
//		Utilities.replace(sql, singleCandidate);
//		Utilities.replace(sql, agencyId);
//		Utilities.replace(sql, viewed);
//		Utilities.replace(sql, viewed);
//		Utilities.replace(sql, bookingDateStatus);
//		
//		return RecordListFactory.getInstance().get(getJdbcTemplate(),
//				sql.toString(), BookingDateUserApplicant.class.getName());
//		
//	}
//
//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndBookingGradeApplicant(Integer agencyId, Boolean singleCandidate, Boolean viewed, Integer bookingGradeApplicantId) {
//		
//		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyAndBookingGradeApplicantSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, singleCandidate);
//		Utilities.replace(sql, singleCandidate);
//		Utilities.replace(sql, agencyId);
//		Utilities.replace(sql, viewed);
//		Utilities.replace(sql, viewed);
//		Utilities.replace(sql, bookingGradeApplicantId);
//		
//		return RecordListFactory.getInstance().get(getJdbcTemplate(),
//				sql.toString(), BookingDateUserApplicant.class.getName());
//
//	
//	}
	
	

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForBookingGradeApplicant(Integer bookingGradeApplicantId) {
		
		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForApplicantForBookingGradeApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		Utilities.replace(sql, bookingGradeApplicantId);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());

	}

	public BookingDateUserApplicant getBookingDateUserApplicantForApplicantAndBookingDate(Integer bookingDateId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantForApplicantForBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		Utilities.replace(sql, bookingDateId);
		return (BookingDateUserApplicant) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public BookingDateUserApplicantEntity getBookingDateUserApplicantEntityForManagerAndBookingDate(Integer managerId, Integer bookingDateId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantForManagerAndBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, bookingDateId);
		return (BookingDateUserApplicantEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicantEntity.class.getName());
		
	}
	
	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForManagerAndInvoice(Integer managerId, Integer invoiceId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndInvoiceSQL.toString());

		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, invoiceId);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicantEntity.class.getName());
		
	}
	
	public List<BookingDateUserGrade> getBookingDateUserGradesForAgency(Integer agencyId, Boolean singleCandidate, Boolean viewed) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserGradesForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, agencyId);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserGrade.class.getName());
		
	}

	public List<BookingDateUserGrade> getBookingDateUserGradesForAgencyAndStatus(Integer agencyId, Boolean singleCandidate, Boolean viewed, Integer bookingGradeStatus) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserGradesForAgencyAndStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, bookingGradeStatus);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserGrade.class.getName());
		
	}
	
	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForAgencyAndInvoice(Integer agencyId, Integer invoiceId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyAndInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		Boolean viewed = null;

		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, invoiceId);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicantEntity.class.getName());
		
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAdmin(Date fromDate, Date toDate) {
    		

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAdminSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;

		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replaceAndQuote(sql, fromDate);
		Utilities.replaceAndQuote(sql, toDate);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());

	}

	
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgency(Integer applicantId, Integer agencyId) {

		return getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(applicantId, agencyId, null, null);
	
	}

  // NEW ->
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndDateRange(Integer agencyId, Date fromDate, Date toDate) 
  {
    StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyAndDateRangeSQL.toString()); 
    // Replace the parameters with supplied values.
    Boolean singleCandidate = null;
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, fromDate);
    Utilities.replaceAndQuote(sql, toDate);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingDateUserApplicant.class.getName());
  }
  // NEW <-
  
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(Integer applicantId, Integer agencyId, Date fromDate, Date toDate) {
		
		StringBuffer sql = null;
		if (fromDate != null) {
			sql = new StringBuffer(selectBookingDateUserApplicantsForApplicantForAgencyAndDateRangeSQL.toString()); 
		}
		else {
			sql = new StringBuffer(selectBookingDateUserApplicantsForApplicantForAgencySQL.toString());
		}
		
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;

		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, applicantId);
		Utilities.replace(sql, agencyId);

		if (fromDate != null) {
			Utilities.replaceAndQuote(sql, fromDate);
			Utilities.replaceAndQuote(sql, toDate);
		}
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());

	}
	
	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForAgencyAndAgencyInvoice(Integer agencyId, Integer agencyInvoiceId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyAndAgencyInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		Boolean viewed = null;

		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, agencyInvoiceId);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicantEntity.class.getName());
		
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndBookingDates(Integer agencyId, String bookingDateIdStrs) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyAndBookingDatesSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		Boolean viewed = null;

		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);
		
		Utilities.replace(sql, bookingDateIdStrs);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public BookingDateUserApplicant getBookingDateUserApplicantForAgencyAndBookingDate(Integer agencyId, Integer bookingDateId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantForAgencyAndBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		Boolean viewed = null;

		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);
		
		Utilities.replace(sql, bookingDateId);
		
		return (BookingDateUserApplicant) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndBooking(Integer agencyId, Integer bookingId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyAndBookingSQL.toString());
		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		Boolean viewed = null;

		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);
		
		Utilities.replace(sql, bookingId);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicant.class.getName());
		
	}

	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForManagerAndAgencyInvoice(Integer managerId, Integer agencyInvoiceId) {

		StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForManagerAndAgencyInvoiceSQL.toString());

		// Replace the parameters with supplied values.
		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		Utilities.replace(sql, managerId);

		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, agencyInvoiceId);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateUserApplicantEntity.class.getName());
		
	}

  public RecordCount getBookingDatesUserApplicantsForAgencyNotOnBackingReportCount(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
      Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId) 
  {
    StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyNotOnBackingReportCountSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);

    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, viewed);
    Utilities.replace(sql, viewed);

    Utilities.replace(sql, clientId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, jobProfileId);

    Utilities.replace(sql, activated);
    Utilities.replace(sql, activated);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, bookingGradeStatus);
    return (RecordCount)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), RecordCount.class.getName());
  }

  public RecordCount getBookingDatesUserApplicantsForAgencyOnBackingReportCount(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
      Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId) 
  {
    StringBuffer sql = new StringBuffer(selectBookingDateUserApplicantsForAgencyOnBackingReportCountSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);

    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, viewed);
    Utilities.replace(sql, viewed);

    Utilities.replace(sql, clientId);
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, jobProfileId);

    Utilities.replace(sql, activated);
    Utilities.replace(sql, activated);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingDateId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, applicantId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, invoiceId);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, bookingDateStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, workedStatus);
    Utilities.replace(sql, bookingGradeStatus);
    Utilities.replace(sql, bookingGradeStatus);
    return (RecordCount)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), RecordCount.class.getName());
  }

//  public BookingDateUser getBookingDateUserForBookingBookingDate(Integer bookingId, Date bookingDate)
//  {
//    StringBuffer sql = new StringBuffer(selectBookingDateUserForBookingBookingDateSQL.toString());
//    // Replace the parameters with supplied values.
//    Utilities.replace(sql, bookingId);
//    Utilities.replaceAndQuote(sql, bookingDate);
//    return (BookingDateUser) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingDateUser.class.getName());
//  }
//
}
