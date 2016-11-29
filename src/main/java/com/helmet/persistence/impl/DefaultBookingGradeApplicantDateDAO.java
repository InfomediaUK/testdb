package com.helmet.persistence.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.BookingGradeApplicantDateUser;
import com.helmet.bean.BookingGradeApplicantDateUserEntity;
import com.helmet.persistence.BookingGradeApplicantDateDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBookingGradeApplicantDateDAO extends JdbcDaoSupport implements BookingGradeApplicantDateDAO {

	private static StringBuffer insertBookingGradeApplicantDateSQL;
	
	private static StringBuffer updateBookingGradeApplicantDateStatusSQL;

//	private static StringBuffer updateBookingGradeApplicantDateWorkedStatusSQL;
	
//	private static StringBuffer updateBookingGradeApplicantDateRejectSQL;
	
//	private static StringBuffer updateBookingGradeApplicantDateWorkedSQL;
	
	private static StringBuffer deleteBookingGradeApplicantDateSQL;

	private static StringBuffer selectBookingGradeApplicantDatesSQL;

	private static StringBuffer selectBookingGradeApplicantDatesForBookingDateAndStatusSQL;

	private static StringBuffer selectBookingGradeApplicantDateUsersSQL;

	private static StringBuffer selectBookingGradeApplicantDateUserSQL;

	private static StringBuffer selectBookingGradeApplicantDateUsersForBookingGradeApplicantSQL;

	private static StringBuffer selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantSQL;

	private static StringBuffer selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL;

	private static StringBuffer selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL;

	private static StringBuffer selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantAndBookingDatesSQL;

	private static StringBuffer selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForDateRangeSQL;
	
	private static StringBuffer selectBookingGradeApplicantDateUsersForBookingFilledSQL;

	private static StringBuffer selectActiveBookingGradeApplicantDateUsersForBookingFilledSQL;

	public static void init() {
		// Get insert BookingGradeApplicantDate SQL.
		insertBookingGradeApplicantDateSQL = new StringBuffer();
		insertBookingGradeApplicantDateSQL.append("INSERT INTO BOOKINGGRADEAPPLICANTDATE ");
		insertBookingGradeApplicantDateSQL.append("(  ");
		insertBookingGradeApplicantDateSQL.append("  BOOKINGGRADEAPPLICANTDATEID, ");
		insertBookingGradeApplicantDateSQL.append("  BOOKINGGRADEAPPLICANTID, ");
		insertBookingGradeApplicantDateSQL.append("  BOOKINGDATEID, ");
		insertBookingGradeApplicantDateSQL.append("  VALUE, ");
		insertBookingGradeApplicantDateSQL.append("  PAYRATEVALUE, ");
		insertBookingGradeApplicantDateSQL.append("  WAGERATEVALUE, ");
		insertBookingGradeApplicantDateSQL.append("  CREATIONTIMESTAMP, ");
		insertBookingGradeApplicantDateSQL.append("  AUDITORID, ");
		insertBookingGradeApplicantDateSQL.append("  AUDITTIMESTAMP ");
		insertBookingGradeApplicantDateSQL.append(")  ");
		insertBookingGradeApplicantDateSQL.append("VALUES  ");
		insertBookingGradeApplicantDateSQL.append("(  ");
		insertBookingGradeApplicantDateSQL.append("  ^, ");
		insertBookingGradeApplicantDateSQL.append("  ^, ");
		insertBookingGradeApplicantDateSQL.append("  ^, ");
		insertBookingGradeApplicantDateSQL.append("  ^, ");
		insertBookingGradeApplicantDateSQL.append("  ^, ");
		insertBookingGradeApplicantDateSQL.append("  ^, ");
		insertBookingGradeApplicantDateSQL.append("  ^, ");
		insertBookingGradeApplicantDateSQL.append("  ^, ");
		insertBookingGradeApplicantDateSQL.append("  ^ ");
		insertBookingGradeApplicantDateSQL.append(")  ");
		// Get update BookingGradeApplicantDate status SQL.
		updateBookingGradeApplicantDateStatusSQL = new StringBuffer();
		updateBookingGradeApplicantDateStatusSQL.append("UPDATE BOOKINGGRADEAPPLICANTDATE ");
		updateBookingGradeApplicantDateStatusSQL.append("SET STATUS = ^, ");
		updateBookingGradeApplicantDateStatusSQL.append("    AUDITORID = ^, ");
		updateBookingGradeApplicantDateStatusSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeApplicantDateStatusSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeApplicantDateStatusSQL.append("WHERE BOOKINGGRADEAPPLICANTDATEID = ^ ");
		updateBookingGradeApplicantDateStatusSQL.append("AND   NOOFCHANGES = ^ ");
//		// Get update BookingGradeApplicantDate workedStatus SQL.
//		updateBookingGradeApplicantDateWorkedStatusSQL = new StringBuffer();
//		updateBookingGradeApplicantDateWorkedStatusSQL.append("UPDATE BOOKINGGRADEAPPLICANTDATE ");
//		updateBookingGradeApplicantDateWorkedStatusSQL.append("SET WORKEDSTATUS = ^, ");
//		updateBookingGradeApplicantDateWorkedStatusSQL.append("    AUDITORID = ^, ");
//		updateBookingGradeApplicantDateWorkedStatusSQL.append("    AUDITTIMESTAMP = ^, ");
//		updateBookingGradeApplicantDateWorkedStatusSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
//		updateBookingGradeApplicantDateWorkedStatusSQL.append("WHERE BOOKINGGRADEAPPLICANTDATEID = ^ ");
//		updateBookingGradeApplicantDateWorkedStatusSQL.append("AND   NOOFCHANGES = ^ ");
//		// Get update BookingGradeApplicantDate reject SQL.
//		updateBookingGradeApplicantDateRejectSQL = new StringBuffer();
//		updateBookingGradeApplicantDateRejectSQL.append("UPDATE BOOKINGGRADEAPPLICANTDATE ");
//		updateBookingGradeApplicantDateRejectSQL.append("SET WORKEDSTATUS = " + BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_WORKEDSTATUS_REJECTED + ", ");
//		updateBookingGradeApplicantDateRejectSQL.append("    REJECTTEXT = ^, ");
//		updateBookingGradeApplicantDateRejectSQL.append("    AUDITORID = ^, ");
//		updateBookingGradeApplicantDateRejectSQL.append("    AUDITTIMESTAMP = ^, ");
//		updateBookingGradeApplicantDateRejectSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
//		updateBookingGradeApplicantDateRejectSQL.append("WHERE BOOKINGGRADEAPPLICANTDATEID = ^ ");
//		updateBookingGradeApplicantDateRejectSQL.append("AND   NOOFCHANGES = ^ ");
//		// Get update BookingGradeApplicantDate worked SQL.
//		updateBookingGradeApplicantDateWorkedSQL = new StringBuffer();
//		updateBookingGradeApplicantDateWorkedSQL.append("UPDATE BOOKINGGRADEAPPLICANTDATE ");
//		updateBookingGradeApplicantDateWorkedSQL.append("SET STARTTIME = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    ENDTIME = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    BREAKSTARTTIME = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    BREAKENDTIME = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    NOOFHOURS = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    WORKEDVALUE = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    WORKEDPAYRATEVALUE = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    WORKEDSTATUS = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    AUDITORID = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    AUDITTIMESTAMP = ^, ");
//		updateBookingGradeApplicantDateWorkedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
//		updateBookingGradeApplicantDateWorkedSQL.append("WHERE BOOKINGGRADEAPPLICANTDATEID = ^ ");
//		updateBookingGradeApplicantDateWorkedSQL.append("AND   NOOFCHANGES = ^ ");
		
		// Get delete BookingGradeApplicantDate SQL.
		deleteBookingGradeApplicantDateSQL = new StringBuffer();
		deleteBookingGradeApplicantDateSQL.append("UPDATE BOOKINGGRADEAPPLICANTDATE ");
		deleteBookingGradeApplicantDateSQL.append("SET ACTIVE = FALSE, ");
		deleteBookingGradeApplicantDateSQL.append("    AUDITORID = ^, ");
		deleteBookingGradeApplicantDateSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteBookingGradeApplicantDateSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteBookingGradeApplicantDateSQL.append("WHERE BOOKINGGRADEAPPLICANTDATEID = ^ ");
		deleteBookingGradeApplicantDateSQL.append("AND   NOOFCHANGES = ^ ");
		
		// Get select BookingGradeApplicantDateUsers SQL.
		selectBookingGradeApplicantDatesSQL = new StringBuffer();
		selectBookingGradeApplicantDatesSQL.append("SELECT BGAD.BOOKINGGRADEAPPLICANTDATEID, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.BOOKINGGRADEAPPLICANTID, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.BOOKINGDATEID, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.VALUE, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.PAYRATEVALUE, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.WAGERATEVALUE, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.STATUS, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.CANDO, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.STARTTIME, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.WORKEDSTATUS, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.ENDTIME, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.BREAKSTARTTIME, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.BREAKENDTIME, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.NOOFHOURS, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.WORKEDVALUE, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.WORKEDPAYRATEVALUE, ");
//		selectBookingGradeApplicantDatesSQL.append("  BGAD.REJECTTEXT, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.CREATIONTIMESTAMP, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.AUDITORID, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.AUDITTIMESTAMP, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.ACTIVE, ");
		selectBookingGradeApplicantDatesSQL.append("  BGAD.NOOFCHANGES ");
		// BookingGradeApplicantDateUser stuff
		selectBookingGradeApplicantDateUsersSQL = new StringBuffer(selectBookingGradeApplicantDatesSQL);
		selectBookingGradeApplicantDateUsersSQL.append("  , ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.BOOKINGID, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.BOOKINGDATE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTID, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTNAME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTCODE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTSTARTTIME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTENDTIME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTBREAKSTARTTIME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTBREAKENDTIME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTNOOFHOURS, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTBREAKNOOFHOURS, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTUSEUPLIFT, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTUPLIFTFACTOR, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTUPLIFTVALUE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTOVERTIMENOOFHOURS, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.SHIFTOVERTIMEUPLIFTFACTOR, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.VALUE AS BOOKINGDATEVALUE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.STATUS AS BOOKINGDATESTATUS, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.ACTIVATED AS BOOKINGDATEACTIVATED, ");	
		
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDSTATUS, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDSTARTTIME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDENDTIME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDBREAKSTARTTIME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDBREAKENDTIME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDNOOFHOURS, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDBREAKNOOFHOURS, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDCHARGERATEVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDPAYRATEVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDWAGERATEVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDWTDVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDNIVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDCOMMISSIONVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.WORKEDVATVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.REJECTTEXT, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.COMMENT, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BD.EXPENSEVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.EXPENSEVATVALUE, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  BD.HASUPLIFT, ");	
		
		selectBookingGradeApplicantDateUsersSQL.append("  BD.NOOFCHANGES AS BOOKINGDATENOOFCHANGES, ");	

//		selectBookingGradeApplicantDateUsersSQL.append("  S.SHIFTID, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.NAME AS SHIFTNAME, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.CODE AS SHIFTCODE, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.STARTTIME AS SHIFTSTARTTIME, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.ENDTIME AS SHIFTENDTIME, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.BREAKSTARTTIME AS SHIFTBREAKSTARTTIME, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.BREAKENDTIME AS SHIFTBREAKENDTIME, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.NOOFHOURS AS SHIFTNOOFHOURS, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.USESHIFTUPLIFT AS SHIFTUSESHIFTUPLIFT, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.UPLIFTFACTOR AS SHIFTUPLIFTFACTOR, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  S.UPLIFTVALUE AS SHIFTUPLIFTVALUE, ");
		
		selectBookingGradeApplicantDateUsersSQL.append("  A.APPLICANTID, ");
		selectBookingGradeApplicantDateUsersSQL.append("  A.FIRSTNAME AS APPLICANTFIRSTNAME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  A.LASTNAME AS APPLICANTLASTNAME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  A.DATEOFBIRTH AS APPLICANTDATEOFBIRTH, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  A.PHOTOFILENAME AS APPLICANTPHOTOFILENAME, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  A.GENDER AS APPLICANTGENDER, ");	
		selectBookingGradeApplicantDateUsersSQL.append("  AG.AGENCYID, ");
		selectBookingGradeApplicantDateUsersSQL.append("  AG.NAME AS AGENCYNAME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  AG.CODE AS AGENCYCODE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BGA.RATE AS BOOKINGGRADERATE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BGA.PAYRATE AS BOOKINGGRADEPAYRATE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BGA.WTDPERCENTAGE AS BOOKINGGRADEWTDPERCENTAGE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BGA.NIPERCENTAGE AS BOOKINGGRADENIPERCENTAGE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BGA.WAGERATE AS BOOKINGGRADEWAGERATE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  JP.JOBPROFILEID, ");
		selectBookingGradeApplicantDateUsersSQL.append("  JP.CODE AS JOBPROFILECODE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  JP.NAME AS JOBPROFILENAME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  LV.LOCATIONID, ");
		selectBookingGradeApplicantDateUsersSQL.append("  LV.LOCATIONCODE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  LV.LOCATIONNAME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  LV.SITEID, ");
		selectBookingGradeApplicantDateUsersSQL.append("  LV.SITECODE, ");
		selectBookingGradeApplicantDateUsersSQL.append("  LV.SITENAME, ");
		selectBookingGradeApplicantDateUsersSQL.append("  LV.CLIENTID ");
//		selectBookingGradeApplicantDateUsersSQL.append("  L.LOCATIONID, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  L.CODE AS LOCATIONCODE, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  L.NAME AS LOCATIONNAME, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  SI.SITEID, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  SI.CODE AS SITECODE, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  SI.NAME AS SITENAME, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  SI.CLIENTID ");
		selectBookingGradeApplicantDateUsersSQL.append("FROM BOOKINGGRADEAPPLICANTDATE BGAD, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BOOKINGDATE BD, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  SHIFT S, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BOOKINGGRADEAPPLICANT BGA, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BOOKINGGRADE BG, ");
		selectBookingGradeApplicantDateUsersSQL.append("  APPLICANT A, ");
		selectBookingGradeApplicantDateUsersSQL.append("  AGENCY AG, ");
		selectBookingGradeApplicantDateUsersSQL.append("  BOOKING B, ");
		selectBookingGradeApplicantDateUsersSQL.append("  LOCATIONVIEWALL LV, "); // Was LOCATIONVIEW until 13/09/2016
//		selectBookingGradeApplicantDateUsersSQL.append("  LOCATION L, ");
//		selectBookingGradeApplicantDateUsersSQL.append("  SITE SI, ");
		selectBookingGradeApplicantDateUsersSQL.append("  JOBPROFILE JP ");
		selectBookingGradeApplicantDateUsersSQL.append("WHERE BD.BOOKINGDATEID = BGAD.BOOKINGDATEID ");
		selectBookingGradeApplicantDateUsersSQL.append("AND BD.ACTIVE = TRUE ");
//		selectBookingGradeApplicantDateUsersSQL.append("AND S.SHIFTID = BD.SHIFTID ");
//		selectBookingGradeApplicantDateUsersSQL.append("AND S.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateUsersSQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = BGAD.BOOKINGGRADEAPPLICANTID ");
		selectBookingGradeApplicantDateUsersSQL.append("AND BGA.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateUsersSQL.append("AND BG.BOOKINGGRADEID = BGA.BOOKINGGRADEID ");
		selectBookingGradeApplicantDateUsersSQL.append("AND BG.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateUsersSQL.append("AND A.APPLICANTID = BGA.APPLICANTID ");
		selectBookingGradeApplicantDateUsersSQL.append("AND A.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateUsersSQL.append("AND AG.AGENCYID = A.AGENCYID ");
		selectBookingGradeApplicantDateUsersSQL.append("AND AG.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateUsersSQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectBookingGradeApplicantDateUsersSQL.append("AND B.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateUsersSQL.append("AND JP.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingGradeApplicantDateUsersSQL.append("AND JP.ACTIVE = TRUE "); // Commented out 13/09/2016 for same reason as use of LOCATIONVIEWALL
		selectBookingGradeApplicantDateUsersSQL.append("AND LV.LOCATIONID = B.LOCATIONID ");
//		selectBookingGradeApplicantDateUsersSQL.append("AND L.LOCATIONID = B.LOCATIONID ");
//		selectBookingGradeApplicantDateUsersSQL.append("AND L.ACTIVE = TRUE ");
//		selectBookingGradeApplicantDateUsersSQL.append("AND SI.SITEID = L.SITEID ");
//		selectBookingGradeApplicantDateUsersSQL.append("AND SI.ACTIVE = TRUE ");
		
		// Get select BookingGradeApplicantDateUsers for BookingGradeApplicant SQL.
		selectBookingGradeApplicantDateUsersForBookingGradeApplicantSQL = new StringBuffer(selectBookingGradeApplicantDateUsersSQL);
		selectBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.append("AND BGAD.BOOKINGGRADEAPPLICANTID = ^ ");
		// Get select Active BookingGradeApplicantDateUsers for BookingGrade SQL.
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantSQL = new StringBuffer(selectBookingGradeApplicantDateUsersForBookingGradeApplicantSQL);
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.append("AND BGAD.ACTIVE = TRUE ");
		
		// Get select BookingGradeApplicantDateUser SQL.
		selectBookingGradeApplicantDateUserSQL = new StringBuffer(selectBookingGradeApplicantDateUsersSQL);
		selectBookingGradeApplicantDateUserSQL.append("AND BGAD.BOOKINGGRADEAPPLICANTDATEID = ^ ");
		selectBookingGradeApplicantDateUserSQL.append("AND BGAD.ACTIVE = TRUE ");
		// Get select Active BookingGradeApplicantDateUsers for BookingGrade for Applicant SQL.
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL = new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantSQL);
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append("AND BGAD.STATUS = " + BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED + " ");

		// Get select Outstanding Active BookingGradeApplicantDateUsers for BookingGrade SQL.
		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL = new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL);
//		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append("AND BD.BOOKINGDATE + BD.SHIFTSTARTTIME < CURRENT_TIMESTAMP "); // shift has started - is in the past
		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append("AND BD.BOOKINGDATE < CURRENT_TIMESTAMP "); // shift has started - is in the past
		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append("AND BD.STATUS != " + BookingDate.BOOKINGDATE_STATUS_CANCELLED + " "); // NOT cancelled
		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append("AND ( ");
		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append(" BD.WORKEDSTATUS IS NULL ");
		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append("OR BD.WORKEDSTATUS IN (" + BookingDate.BOOKINGDATE_WORKEDSTATUS_DRAFT + ", " + BookingDate.BOOKINGDATE_WORKEDSTATUS_REJECTED + ") ");
		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append(") ");
		
		// Get select Active BookingGradeApplicantDateUsers for bookingDates SQL.
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantAndBookingDatesSQL = new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL);
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantAndBookingDatesSQL.append("AND BD.BOOKINGDATEID IN (^) ");

		// Get select Active BookingGradeApplicantDateUsers for date range SQL.
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForDateRangeSQL = new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL);
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForDateRangeSQL.append("AND BD.BOOKINGDATE BETWEEN ^ AND ^ ");

		// Get select BookingGradeApplicantDateUsers for Booking FILLED SQL.
		selectBookingGradeApplicantDateUsersForBookingFilledSQL = new StringBuffer(selectBookingGradeApplicantDateUsersSQL);
		selectBookingGradeApplicantDateUsersForBookingFilledSQL.append("AND BD.BOOKINGID = ^ ");
		selectBookingGradeApplicantDateUsersForBookingFilledSQL.append("AND BGAD.STATUS = " + BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED + " ");
		// Get select Active BookingGradeApplicantDateUsers for Booking FILLED SQL.
		selectActiveBookingGradeApplicantDateUsersForBookingFilledSQL = new StringBuffer(selectBookingGradeApplicantDateUsersForBookingFilledSQL);
		selectActiveBookingGradeApplicantDateUsersForBookingFilledSQL.append("AND BGAD.ACTIVE = TRUE ");
		
		
		// BookingGradeApplicantDates for BookingDate and Status stuff
		selectBookingGradeApplicantDatesForBookingDateAndStatusSQL = new StringBuffer(selectBookingGradeApplicantDatesSQL);
		selectBookingGradeApplicantDatesForBookingDateAndStatusSQL.append("FROM BOOKINGGRADEAPPLICANTDATE BGAD ");
		selectBookingGradeApplicantDatesForBookingDateAndStatusSQL.append("WHERE BGAD.BOOKINGDATEID = ^ ");
		selectBookingGradeApplicantDatesForBookingDateAndStatusSQL.append("AND BGAD.STATUS = ^ ");
		selectBookingGradeApplicantDatesForBookingDateAndStatusSQL.append("AND BGAD.ACTIVE = TRUE ");
		
		//
		selectBookingGradeApplicantDateUsersSQL.append("ORDER BY BD.BOOKINGDATE ");
		selectBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.append("ORDER BY BD.BOOKINGDATE ");
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.append("ORDER BY BD.BOOKINGDATE ");
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append("ORDER BY BD.BOOKINGDATE ");
		selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.append("ORDER BY BD.BOOKINGDATE ");
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantAndBookingDatesSQL.append("ORDER BY BD.BOOKINGDATE ");
		selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForDateRangeSQL.append("ORDER BY BD.BOOKINGDATE ");
		selectBookingGradeApplicantDateUsersForBookingFilledSQL.append("ORDER BY BD.BOOKINGDATE ");
		selectActiveBookingGradeApplicantDateUsersForBookingFilledSQL.append("ORDER BY BD.BOOKINGDATE ");

	}

	public int insertBookingGradeApplicantDate(BookingGradeApplicantDate bookingGradeApplicantDate, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBookingGradeApplicantDateSQL.toString());
		// Replace the parameters with supplied values.
		bookingGradeApplicantDate.setBookingGradeApplicantDateId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "bookingGradeApplicantDate"));
		Utilities.replace(sql, bookingGradeApplicantDate.getBookingGradeApplicantDateId());
		Utilities.replace(sql, bookingGradeApplicantDate.getBookingGradeApplicantId());
		Utilities.replace(sql, bookingGradeApplicantDate.getBookingDateId());
		Utilities.replace(sql, bookingGradeApplicantDate.getValue());
		Utilities.replace(sql, bookingGradeApplicantDate.getPayRateValue());
		Utilities.replace(sql, bookingGradeApplicantDate.getWageRateValue());
        Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeApplicantDateStatus(Integer bookingGradeApplicantDateId, Integer noOfChanges,
			Integer auditorId, int status) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantDateStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, status);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeApplicantDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

//	public int updateBookingGradeApplicantDateWorkedStatus(Integer bookingGradeApplicantDateId, Integer noOfChanges,
//			Integer auditorId, int workedStatus) {
//		// Create a new local StringBuffer containing the parameterised SQL.
//		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantDateWorkedStatusSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, workedStatus);
//		Utilities.replace(sql, auditorId);
//		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
//				.getTime()).toString());
//		Utilities.replace(sql, bookingGradeApplicantDateId);
//		Utilities.replace(sql, noOfChanges);
//		return UpdateHandler.getInstance().update(getJdbcTemplate(),
//				sql.toString());
//	}

//	public int updateBookingGradeApplicantDateReject(Integer bookingGradeApplicantDateId, String rejectText, Integer noOfChanges,
//			Integer auditorId) {
//		// Create a new local StringBuffer containing the parameterised SQL.
//		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantDateRejectSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replaceAndQuote(sql, rejectText);
//		Utilities.replace(sql, auditorId);
//		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
//				.getTime()).toString());
//		Utilities.replace(sql, bookingGradeApplicantDateId);
//		Utilities.replace(sql, noOfChanges);
//		return UpdateHandler.getInstance().update(getJdbcTemplate(),
//				sql.toString());
//	}

	public int deleteBookingGradeApplicantDate(Integer bookingGradeApplicantDateId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteBookingGradeApplicantDateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeApplicantDateId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<BookingGradeApplicantDateUser> getBookingGradeApplicantDateUsersForBookingGradeApplicant(Integer bookingGradeApplicantId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUser.class.getName());

	}

	public List<BookingGradeApplicantDateUserEntity> getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicant(Integer bookingGradeApplicantId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUserEntity.class.getName());

	}

	public List<BookingGradeApplicantDateUserEntity> getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicantForApplicant(Integer bookingGradeApplicantId) {

		StringBuffer sql =  new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUserEntity.class.getName());

	}

	public List<BookingGradeApplicantDateUserEntity> getOutstandingBookingGradeApplicantDateUserEntitiesForBookingGradeApplicantForApplicant(Integer bookingGradeApplicantId) {

		StringBuffer sql = new StringBuffer(selectOutstandingActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUserEntity.class.getName());

	}

	public List<BookingGradeApplicantDateUser> getBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicant(Integer bookingGradeApplicantId) {

		StringBuffer sql = new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUser.class.getName());

	}

	public List<BookingGradeApplicantDate> getBookingGradeApplicantDatesForBookingDateAndStatus(Integer bookingDateId, Integer status) {
		StringBuffer sql = new StringBuffer(selectBookingGradeApplicantDatesForBookingDateAndStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDateId);
		Utilities.replace(sql, status);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDate.class.getName());
	}

	public BookingGradeApplicantDateUser getBookingGradeApplicantDateUser(Integer bookingGradeApplicantDateId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingGradeApplicantDateUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantDateId);
		return (BookingGradeApplicantDateUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUser.class.getName());
	}
	
//    public int updateBookingGradeApplicantDateWorked(BookingGradeApplicantDate bookingGradeApplicantDate, Integer auditorId) {
//		// Create a new local StringBuffer containing the parameterised SQL.
//		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantDateWorkedSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replaceAndQuote(sql, bookingGradeApplicantDate.getStartTime());
//		Utilities.replaceAndQuote(sql, bookingGradeApplicantDate.getEndTime());
//		Utilities.replaceAndQuoteNullable(sql, bookingGradeApplicantDate.getBreakStartTime());
//		Utilities.replaceAndQuoteNullable(sql, bookingGradeApplicantDate.getBreakEndTime());
//		Utilities.replace(sql, bookingGradeApplicantDate.getNoOfHours());
//		Utilities.replace(sql, bookingGradeApplicantDate.getWorkedValue());
//		Utilities.replace(sql, bookingGradeApplicantDate.getWorkedPayRateValue());
//		Utilities.replace(sql, bookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_WORKEDSTATUS_DRAFT);
//		Utilities.replace(sql, auditorId);
//		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
//				.getTime()).toString());
//		Utilities.replace(sql, bookingGradeApplicantDate.getBookingGradeApplicantDateId());
//		Utilities.replace(sql, bookingGradeApplicantDate.getNoOfChanges());
//		return UpdateHandler.getInstance().update(getJdbcTemplate(),
//				sql.toString());
//    }
	
	public List<BookingGradeApplicantDateUser> getBookingGradeApplicantDateUsersForBookingFilled(Integer bookingId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingFilledSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingGradeApplicantDateUsersForBookingFilledSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUser.class.getName());

	}

	public List<BookingGradeApplicantDateUserEntity> getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicantForApplicantAndBookingDates(Integer bookingGradeApplicantId, String bookingDateIdStrs) {
		
		StringBuffer sql =  new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForApplicantAndBookingDatesSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, bookingDateIdStrs);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUserEntity.class.getName());
		
	}

	public List<BookingGradeApplicantDateUserEntity> getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicantForDateRange(Integer bookingGradeApplicantId, Date startDate, Date endDate) {
		
		StringBuffer sql =  new StringBuffer(selectActiveBookingGradeApplicantDateUsersForBookingGradeApplicantForDateRangeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replaceAndQuote(sql, startDate);
		Utilities.replaceAndQuote(sql, endDate);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantDateUserEntity.class.getName());

	}
	
}
