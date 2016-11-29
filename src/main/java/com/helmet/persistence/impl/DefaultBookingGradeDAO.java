package com.helmet.persistence.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.application.agy.AgyConstants;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeUser;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.IntValue;
import com.helmet.bean.StatusCount;
import com.helmet.persistence.BookingGradeDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBookingGradeDAO extends JdbcDaoSupport implements BookingGradeDAO {

	private static StringBuffer insertBookingGradeSQL;

	private static StringBuffer updateBookingGradeSQL;

	private static StringBuffer updateBookingGradeStatusSQL;

	private static StringBuffer updateBookingGradeSentTimestampSQL;

	private static StringBuffer updateBookingGradeSentStatusSQL;

	private static StringBuffer updateBookingGradeViewedSQL;

	private static StringBuffer updateBookingGradeAgencyIdSQL;
	
	private static StringBuffer deleteBookingGradeSQL;

  private static StringBuffer selectBookingGradeSQL;

  private static StringBuffer selectBookingGradeForBookingClientAgencyJobProfileGradeSQL;

	private static StringBuffer selectBookingGradesSQL;

	private static StringBuffer selectBookingGradesToSendSQL;

	private static StringBuffer selectBookingGradeUsersSQL;

	private static StringBuffer selectBookingGradeUsersForBookingSQL;

	private static StringBuffer selectActiveBookingGradeUsersForBookingSQL;

	private static StringBuffer selectBookingGradeUserForBookingAndAgencySQL;

	private static StringBuffer selectActiveBookingGradeUserForBookingAndAgencySQL;

	private static StringBuffer selectBookingGradeAgysSQL;

	private static StringBuffer selectBookingGradeAgySQL;

  private static StringBuffer selectBookingGradeAgysForAgencySQL;

  private static StringBuffer selectBookingGradeAgysForAgencyAndDateRangeSQL;

  private static StringBuffer selectActiveBookingGradeAgysForAgencySQL;

  private static StringBuffer selectActiveBookingGradeAgysForAgencyAndDateRangeSQL;

	private static StringBuffer selectBookingGradeAgysForAgencyUnviewedSQL;

	private static StringBuffer selectBookingGradeAgysForAgencyAndStatusSQL;
	
	private static StringBuffer selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL;
	
	private static StringBuffer selectBookingStatusCountsForAgencySQL;

	private static StringBuffer selectShiftStatusCountsForAgencySQL;

	private static StringBuffer selectBookingGradeApplicantDateStatusCountsForAgencySQL;

	private static StringBuffer selectShiftWorkedStatusCountsForAgencySQL;

	private static StringBuffer selectBookingGradeIdForAgencyAndBookingSQL;
	
	public static void init() {
		// Get insert BookingGrade SQL.
		insertBookingGradeSQL = new StringBuffer();
		insertBookingGradeSQL.append("INSERT INTO BOOKINGGRADE ");
		insertBookingGradeSQL.append("(  ");
		insertBookingGradeSQL.append("  BOOKINGGRADEID, ");
		insertBookingGradeSQL.append("  BOOKINGID, ");
		insertBookingGradeSQL.append("  CLIENTAGENCYJOBPROFILEGRADEID, ");
		insertBookingGradeSQL.append("  RATE, ");
		insertBookingGradeSQL.append("  VALUE, ");
		insertBookingGradeSQL.append("  PAYRATE, ");
		insertBookingGradeSQL.append("  WTDPERCENTAGE, ");
		insertBookingGradeSQL.append("  NIPERCENTAGE, ");
		insertBookingGradeSQL.append("  WAGERATE, ");
		insertBookingGradeSQL.append("  CHARGERATEVATRATE, ");
		insertBookingGradeSQL.append("  COMMISSIONVATRATE, ");
		insertBookingGradeSQL.append("  PAYRATEVATRATE, ");
		insertBookingGradeSQL.append("  WTDVATRATE, ");
		insertBookingGradeSQL.append("  NIVATRATE, ");
		insertBookingGradeSQL.append("  CREATIONTIMESTAMP, ");
		insertBookingGradeSQL.append("  AUDITORID, ");
		insertBookingGradeSQL.append("  AUDITTIMESTAMP ");
		insertBookingGradeSQL.append(")  ");
		insertBookingGradeSQL.append("VALUES  ");
		insertBookingGradeSQL.append("(  ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^, ");
		insertBookingGradeSQL.append("  ^ ");
		insertBookingGradeSQL.append(")  ");
		// Get update BookingGrade SQL.
		updateBookingGradeSQL = new StringBuffer();
		updateBookingGradeSQL.append("UPDATE BOOKINGGRADE ");
		updateBookingGradeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateBookingGradeSQL.append("     CLIENTAGENCYJOBPROFILEGRADEID = ^, ");
		updateBookingGradeSQL.append("     STATUS = ^, ");
		updateBookingGradeSQL.append("     RATE = ^, ");
		updateBookingGradeSQL.append("     VALUE = ^, ");
		updateBookingGradeSQL.append("     PAYRATE = ^, ");
		updateBookingGradeSQL.append("     WTDPERCENTAGE = ^, ");
		updateBookingGradeSQL.append("     NIPERCENTAGE = ^, ");
		updateBookingGradeSQL.append("     WAGERATE = ^, ");
		updateBookingGradeSQL.append("     CHARGERATEVATRATE = ^, ");
		updateBookingGradeSQL.append("     COMMISSIONVATRATE = ^, ");
		updateBookingGradeSQL.append("     PAYRATEVATRATE = ^, ");
		updateBookingGradeSQL.append("     WTDVATRATE = ^, ");
		updateBookingGradeSQL.append("     NIVATRATE = ^, ");
		updateBookingGradeSQL.append("     SENDTIMESTAMP = ^, ");
		updateBookingGradeSQL.append("     SENTTIMESTAMP = ^, ");
		updateBookingGradeSQL.append("     SENTSTATUS = ^, ");
		updateBookingGradeSQL.append("     AUDITORID = ^, ");
		updateBookingGradeSQL.append("     AUDITTIMESTAMP = ^ ");
		updateBookingGradeSQL.append("WHERE BOOKINGGRADEID = ^ ");
		updateBookingGradeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingGrade status SQL.
		updateBookingGradeStatusSQL = new StringBuffer();
		updateBookingGradeStatusSQL.append("UPDATE BOOKINGGRADE ");
		updateBookingGradeStatusSQL.append("SET STATUS = ^, ");
		updateBookingGradeStatusSQL.append("    AUDITORID = ^, ");
		updateBookingGradeStatusSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeStatusSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeStatusSQL.append("WHERE BOOKINGGRADEID = ^ ");
		updateBookingGradeStatusSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingGrade sentTimestamp SQL.
		updateBookingGradeSentTimestampSQL = new StringBuffer();
		updateBookingGradeSentTimestampSQL.append("UPDATE BOOKINGGRADE ");
		updateBookingGradeSentTimestampSQL.append("SET SENTTIMESTAMP = ^, ");
		updateBookingGradeSentTimestampSQL.append("    SENTSTATUS = 0, ");
		updateBookingGradeSentTimestampSQL.append("    AUDITORID = ^, ");
		updateBookingGradeSentTimestampSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeSentTimestampSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeSentTimestampSQL.append("WHERE BOOKINGGRADEID = ^ ");
		updateBookingGradeSentTimestampSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingGrade sentStatus SQL.
		updateBookingGradeSentStatusSQL = new StringBuffer();
		updateBookingGradeSentStatusSQL.append("UPDATE BOOKINGGRADE ");
		updateBookingGradeSentStatusSQL.append("SET SENTSTATUS = ^, ");
		updateBookingGradeSentStatusSQL.append("    AUDITORID = ^, ");
		updateBookingGradeSentStatusSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeSentStatusSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeSentStatusSQL.append("WHERE BOOKINGGRADEID = ^ ");
		updateBookingGradeSentStatusSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingGrade as viewed SQL.
		updateBookingGradeViewedSQL = new StringBuffer();
		updateBookingGradeViewedSQL.append("UPDATE BOOKINGGRADE ");
		updateBookingGradeViewedSQL.append("SET VIEWED = ^, ");
		updateBookingGradeViewedSQL.append("    AUDITORID = ^, ");
		updateBookingGradeViewedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeViewedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeViewedSQL.append("WHERE BOOKINGGRADEID = ^ ");
		updateBookingGradeViewedSQL.append("AND   NOOFCHANGES = ^ ");
		
		// Get update BookingGrade agencyId SQL.
		updateBookingGradeAgencyIdSQL = new StringBuffer();
		updateBookingGradeAgencyIdSQL.append("UPDATE BOOKINGGRADE ");
		updateBookingGradeAgencyIdSQL.append("SET AGENCYID = CA.AGENCYID, ");
		updateBookingGradeAgencyIdSQL.append("    AUDITORID = ^, ");
		updateBookingGradeAgencyIdSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeAgencyIdSQL.append("    NOOFCHANGES = BOOKINGGRADE.NOOFCHANGES + 1 ");
		updateBookingGradeAgencyIdSQL.append("FROM CLIENTAGENCY CA, ");
		updateBookingGradeAgencyIdSQL.append("     CLIENTAGENCYJOBPROFILE CAJP, ");
		updateBookingGradeAgencyIdSQL.append("     CLIENTAGENCYJOBPROFILEGRADE CAJPG ");
		updateBookingGradeAgencyIdSQL.append("WHERE CAJP.CLIENTAGENCYID = CA.CLIENTAGENCYID ");
		updateBookingGradeAgencyIdSQL.append("AND   CAJPG.CLIENTAGENCYJOBPROFILEID = CAJP.CLIENTAGENCYJOBPROFILEID ");
		updateBookingGradeAgencyIdSQL.append("AND   BOOKINGGRADE.CLIENTAGENCYJOBPROFILEGRADEID = CAJPG.CLIENTAGENCYJOBPROFILEGRADEID ");
		updateBookingGradeAgencyIdSQL.append("AND   BOOKINGGRADE.BOOKINGGRADEID = ^ ");
		
		// Get delete BookingGrade SQL.
		deleteBookingGradeSQL = new StringBuffer();
		deleteBookingGradeSQL.append("UPDATE BOOKINGGRADE ");
		deleteBookingGradeSQL.append("SET ACTIVE = FALSE, ");
		deleteBookingGradeSQL.append("    AUDITORID = ^, ");
		deleteBookingGradeSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteBookingGradeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteBookingGradeSQL.append("WHERE BOOKINGGRADEID = ^ ");
		deleteBookingGradeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select BookingGrades SQL.
		selectBookingGradesSQL = new StringBuffer();
		selectBookingGradesSQL.append("SELECT BG.BOOKINGGRADEID, ");
		selectBookingGradesSQL.append("  BG.BOOKINGID, ");
		selectBookingGradesSQL.append("  BG.AGENCYID, ");
		selectBookingGradesSQL.append("  BG.CLIENTAGENCYJOBPROFILEGRADEID, ");
		selectBookingGradesSQL.append("  BG.STATUS, ");
		selectBookingGradesSQL.append("  BG.RATE, ");
		selectBookingGradesSQL.append("  BG.VALUE, ");
		selectBookingGradesSQL.append("  BG.PAYRATE, ");
		selectBookingGradesSQL.append("  BG.WTDPERCENTAGE, ");
		selectBookingGradesSQL.append("  BG.NIPERCENTAGE, ");
		selectBookingGradesSQL.append("  BG.WAGERATE, ");
		selectBookingGradesSQL.append("  BG.CHARGERATEVATRATE, ");
		selectBookingGradesSQL.append("  BG.COMMISSIONVATRATE, ");
		selectBookingGradesSQL.append("  BG.PAYRATEVATRATE, ");
		selectBookingGradesSQL.append("  BG.WTDVATRATE, ");
		selectBookingGradesSQL.append("  BG.NIVATRATE, ");
		selectBookingGradesSQL.append("  BG.SENDTIMESTAMP, ");
		selectBookingGradesSQL.append("  BG.SENTTIMESTAMP, ");
		selectBookingGradesSQL.append("  BG.SENTSTATUS, ");
		selectBookingGradesSQL.append("  BG.VIEWED, ");
		selectBookingGradesSQL.append("  BG.CANNOTFILL, ");
		selectBookingGradesSQL.append("  BG.CREATIONTIMESTAMP, ");
		selectBookingGradesSQL.append("  BG.AUDITORID, ");
		selectBookingGradesSQL.append("  BG.AUDITTIMESTAMP, ");
		selectBookingGradesSQL.append("  BG.ACTIVE, ");
		selectBookingGradesSQL.append("  BG.NOOFCHANGES ");
		//
		selectBookingGradeUsersSQL = new StringBuffer(selectBookingGradesSQL);
		//
		selectBookingGradeAgysSQL = new StringBuffer(selectBookingGradesSQL);
		//
		selectBookingGradesSQL.append("FROM BOOKINGGRADE BG ");
		
		// NOTE. We do NOT want to pick up NHS Bookings, hence check on BookingReference.
		selectBookingGradesToSendSQL = new StringBuffer(selectBookingGradesSQL);
		selectBookingGradesToSendSQL.append(", BOOKING B ");
		selectBookingGradesToSendSQL.append("WHERE B.ACTIVE = TRUE ");
    selectBookingGradesToSendSQL.append("AND B.STATUS = " + Booking.BOOKING_STATUS_OPEN + " ");
    selectBookingGradesToSendSQL.append("AND (B.BOOKINGREFERENCE NOT LIKE '" + AgyConstants.NHS_BOOKINGS_BANK_REQUEST_NUMBER_LABEL + "%' OR B.BOOKINGREFERENCE IS NULL ) ");
		selectBookingGradesToSendSQL.append("AND BG.BOOKINGID = B.BOOKINGID ");
		selectBookingGradesToSendSQL.append("AND BG.ACTIVE = TRUE ");
		selectBookingGradesToSendSQL.append("AND BG.STATUS = " + BookingGrade.BOOKINGGRADE_STATUS_DRAFT + " ");
		selectBookingGradesToSendSQL.append("AND BG.SENDTIMESTAMP <= ^ ");
		selectBookingGradesToSendSQL.append("AND BG.SENTTIMESTAMP IS NULL ");
		selectBookingGradesToSendSQL.append("ORDER BY BG.SENDTIMESTAMP ");
		
		// Get select BookingGradeUsers SQL.
		selectBookingGradeUsersSQL.append("  , ");
		selectBookingGradeUsersSQL.append("  A.NAME AS AGENCYNAME, ");
		selectBookingGradeUsersSQL.append("  A.CODE AS AGENCYCODE, ");
		selectBookingGradeUsersSQL.append("  CAJP.PERCENTAGE, ");
		selectBookingGradeUsersSQL.append("  G.NAME AS GRADENAME, ");
		selectBookingGradeUsersSQL.append("  CAJPG.RATE AS CURRENTRATE ");
		selectBookingGradeUsersSQL.append("FROM BOOKINGGRADE BG, ");
		selectBookingGradeUsersSQL.append("  CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
		selectBookingGradeUsersSQL.append("  GRADE G, ");
		selectBookingGradeUsersSQL.append("  CLIENTAGENCYJOBPROFILE CAJP, ");
		selectBookingGradeUsersSQL.append("  CLIENTAGENCY CA, ");
		selectBookingGradeUsersSQL.append("  AGENCY A ");
		selectBookingGradeUsersSQL.append("WHERE CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectBookingGradeUsersSQL.append("AND CAJPG.ACTIVE = TRUE ");
		selectBookingGradeUsersSQL.append("AND G.GRADEID = CAJPG.GRADEID ");
		selectBookingGradeUsersSQL.append("AND G.ACTIVE = TRUE ");
		selectBookingGradeUsersSQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
		selectBookingGradeUsersSQL.append("AND CAJP.ACTIVE = TRUE ");
		selectBookingGradeUsersSQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
		selectBookingGradeUsersSQL.append("AND CA.ACTIVE = TRUE ");
		selectBookingGradeUsersSQL.append("AND A.AGENCYID = CA.AGENCYID ");
		selectBookingGradeUsersSQL.append("AND A.ACTIVE = TRUE ");
		// Get select BookingGradeUsers for Booking SQL.
		selectBookingGradeUsersForBookingSQL = new StringBuffer(selectBookingGradeUsersSQL);
		selectBookingGradeUsersForBookingSQL.append("AND BG.BOOKINGID = ^ ");
		// Get select Active BookingGrades for BookingSQL.
		selectActiveBookingGradeUsersForBookingSQL = new StringBuffer(selectBookingGradeUsersForBookingSQL);
		selectActiveBookingGradeUsersForBookingSQL.append("AND BG.ACTIVE = TRUE ");
		// Get select BookingGradeUser for Booking and Agency SQL.
		selectBookingGradeUserForBookingAndAgencySQL = new StringBuffer(selectBookingGradeUsersForBookingSQL);
		selectBookingGradeUserForBookingAndAgencySQL.append("AND A.AGENCYID = ^ ");
    	// Get select Active BookingGradeUser for Booking and Agency SQL.
		selectActiveBookingGradeUserForBookingAndAgencySQL = new StringBuffer(selectActiveBookingGradeUsersForBookingSQL);
		selectActiveBookingGradeUserForBookingAndAgencySQL.append("AND A.AGENCYID = ^ ");
		//
		selectBookingGradeUsersForBookingSQL.append("ORDER BY CAJP.PERCENTAGE DESC, CAJPG.RATE DESC ");
		selectActiveBookingGradeUsersForBookingSQL.append("ORDER BY CAJP.PERCENTAGE DESC, CAJPG.RATE DESC ");
		// Get select BookingGrade SQL.
		selectBookingGradeSQL = new StringBuffer(selectBookingGradesSQL);
		selectBookingGradeSQL.append("WHERE BG.BOOKINGGRADEID = ^ ");
    // Get select BookingGrade for Booking SQL.
    selectBookingGradeForBookingClientAgencyJobProfileGradeSQL = new StringBuffer(selectBookingGradesSQL);
    selectBookingGradeForBookingClientAgencyJobProfileGradeSQL.append("WHERE BG.BOOKINGID = ^ ");
    selectBookingGradeForBookingClientAgencyJobProfileGradeSQL.append("AND BG.CLIENTAGENCYJOBPROFILEGRADEID = ^ ");
		// Get select BookingGradeAgys SQL.
		selectBookingGradeAgysSQL.append(", ");
		selectBookingGradeAgysSQL.append("  B.STATUS AS BOOKINGSTATUS, "); 
		selectBookingGradeAgysSQL.append("  B.RATE AS BOOKINGRATE, "); 
		selectBookingGradeAgysSQL.append("  B.VALUE AS BOOKINGVALUE, "); 
		selectBookingGradeAgysSQL.append("  B.NOOFHOURS AS BOOKINGNOOFHOURS, "); 
		selectBookingGradeAgysSQL.append("  B.SINGLECANDIDATE, ");
		selectBookingGradeAgysSQL.append("  B.CVREQUIRED, ");
		selectBookingGradeAgysSQL.append("  B.INTERVIEWREQUIRED, ");
		selectBookingGradeAgysSQL.append("  B.CANPROVIDEACCOMMODATION, ");
		selectBookingGradeAgysSQL.append("  B.CARREQUIRED, " );
		selectBookingGradeAgysSQL.append("  B.AUTOFILL, " );
		selectBookingGradeAgysSQL.append("  B.AUTOACTIVATE, " );
		selectBookingGradeAgysSQL.append("  B.COMMENTS, ");
		selectBookingGradeAgysSQL.append("  B.EXPENSESTEXT, ");
		selectBookingGradeAgysSQL.append("  B.MINBOOKINGDATE, ");   
		selectBookingGradeAgysSQL.append("  B.MAXBOOKINGDATE, ");   
		selectBookingGradeAgysSQL.append("  B.NOOFBOOKINGDATES, ");
		selectBookingGradeAgysSQL.append("  B.DRESSCODEID, ");
		selectBookingGradeAgysSQL.append("  B.DURATION, ");
		selectBookingGradeAgysSQL.append("  B.BOOKINGREFERENCE, ");
		selectBookingGradeAgysSQL.append("  B.ACCOUNTCONTACTNAME, ");
		selectBookingGradeAgysSQL.append("  B.ACCOUNTCONTACTADDRESS1, ");
		selectBookingGradeAgysSQL.append("  B.ACCOUNTCONTACTADDRESS2, ");
		selectBookingGradeAgysSQL.append("  B.ACCOUNTCONTACTADDRESS3, ");
		selectBookingGradeAgysSQL.append("  B.ACCOUNTCONTACTADDRESS4, ");
		selectBookingGradeAgysSQL.append("  B.ACCOUNTCONTACTPOSTALCODE, ");
		selectBookingGradeAgysSQL.append("  B.ACCOUNTCONTACTCOUNTRYID, ");
		selectBookingGradeAgysSQL.append("  B.ACCOUNTCONTACTEMAILADDRESS, ");
		selectBookingGradeAgysSQL.append("  B.CONTACTNAME, ");
		selectBookingGradeAgysSQL.append("  B.CONTACTJOBTITLE, ");
		selectBookingGradeAgysSQL.append("  B.CONTACTEMAILADDRESS, ");
		selectBookingGradeAgysSQL.append("  B.CONTACTTELEPHONENUMBER, ");
		selectBookingGradeAgysSQL.append("  D.NAME AS DRESSCODENAME, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBPROFILEID, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBPROFILECODE, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBPROFILENAME, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBSUBFAMILYID, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBSUBFAMILYCODE, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBSUBFAMILYNAME, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBFAMILYID, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBFAMILYCODE, ");
		selectBookingGradeAgysSQL.append("  JPV.JOBFAMILYNAME, ");
		selectBookingGradeAgysSQL.append("  LV.CLIENTID, ");
		selectBookingGradeAgysSQL.append("  LV.CLIENTCODE, ");
		selectBookingGradeAgysSQL.append("  LV.CLIENTNAME, ");
		selectBookingGradeAgysSQL.append("  LV.LOCATIONID, ");
		selectBookingGradeAgysSQL.append("  LV.LOCATIONCODE, ");
		selectBookingGradeAgysSQL.append("  LV.LOCATIONNAME, ");
		selectBookingGradeAgysSQL.append("  LV.LOCATIONDESCRIPTION, ");
		selectBookingGradeAgysSQL.append("  LV.SITEID, ");   
		selectBookingGradeAgysSQL.append("  LV.SITECODE, ");   
		selectBookingGradeAgysSQL.append("  LV.SITENAME, ");   
//		selectBookingGradeAgysSQL.append("  JP.JOBPROFILEID, ");
//		selectBookingGradeAgysSQL.append("  JP.CODE AS JOBPROFILECODE, ");
//		selectBookingGradeAgysSQL.append("  JP.NAME AS JOBPROFILENAME, ");
//		selectBookingGradeAgysSQL.append("  JSF.JOBSUBFAMILYID, ");
//		selectBookingGradeAgysSQL.append("  JSF.CODE AS JOBSUBFAMILYCODE, ");
//		selectBookingGradeAgysSQL.append("  JSF.NAME AS JOBSUBFAMILYNAME, ");
//		selectBookingGradeAgysSQL.append("  JF.JOBFAMILYID, ");
//		selectBookingGradeAgysSQL.append("  JF.CODE AS JOBFAMILYCODE, ");
//		selectBookingGradeAgysSQL.append("  JF.NAME AS JOBFAMILYNAME, ");
//		selectBookingGradeAgysSQL.append("  C.CLIENTID, ");
//		selectBookingGradeAgysSQL.append("  C.CODE AS CLIENTCODE, ");
//		selectBookingGradeAgysSQL.append("  C.NAME AS CLIENTNAME, ");
//		selectBookingGradeAgysSQL.append("  L.LOCATIONID, ");
//		selectBookingGradeAgysSQL.append("  L.CODE AS LOCATIONCODE, ");
//		selectBookingGradeAgysSQL.append("  L.NAME AS LOCATIONNAME, ");
//		selectBookingGradeAgysSQL.append("  L.DESCRIPTION AS LOCATIONDESCRIPTION, ");
//		selectBookingGradeAgysSQL.append("  S.SITEID, ");   
//		selectBookingGradeAgysSQL.append("  S.CODE AS SITECODE, ");   
//		selectBookingGradeAgysSQL.append("  S.NAME AS SITENAME, ");   
		selectBookingGradeAgysSQL.append("  SH.SHIFTID, ");   
		selectBookingGradeAgysSQL.append("  SH.CODE AS SHIFTCODE, ");   
		selectBookingGradeAgysSQL.append("  SH.NAME AS SHIFTNAME, ");   
		selectBookingGradeAgysSQL.append("  SH.STARTTIME AS SHIFTSTARTTIME, ");
		selectBookingGradeAgysSQL.append("  SH.ENDTIME AS SHIFTENDTIME, ");
		selectBookingGradeAgysSQL.append("  SH.BREAKSTARTTIME AS SHIFTBREAKSTARTTIME, ");
		selectBookingGradeAgysSQL.append("  SH.BREAKENDTIME AS SHIFTBREAKENDTIME, ");
		selectBookingGradeAgysSQL.append("  SH.NOOFHOURS AS SHIFTNOOFHOURS, ");
		selectBookingGradeAgysSQL.append("  G.GRADEID, ");   
		selectBookingGradeAgysSQL.append("  G.NAME AS GRADENAME ");   
		selectBookingGradeAgysSQL.append("FROM ");
		selectBookingGradeAgysSQL.append("  BOOKINGGRADE BG, ");
		selectBookingGradeAgysSQL.append("  CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
//		selectBookingGradeAgysSQL.append("  CLIENTAGENCYJOBPROFILE CAJP, ");
//		selectBookingGradeAgysSQL.append("  CLIENTAGENCY CA, ");
		selectBookingGradeAgysSQL.append("  BOOKING B ");
		selectBookingGradeAgysSQL.append("  LEFT OUTER JOIN DRESSCODE D ON D.DRESSCODEID = B.DRESSCODEID AND D.ACTIVE = TRUE ");   
		selectBookingGradeAgysSQL.append("  LEFT OUTER JOIN SHIFT SH ON SH.SHIFTID = B.SHIFTID AND SH.ACTIVE = TRUE, ");   
		selectBookingGradeAgysSQL.append("  JOBPROFILEVIEWALL JPV, "); // Was JOBPROFILEVIEW until 13/09/2016
		selectBookingGradeAgysSQL.append("  LOCATIONVIEWALL LV, "); // Was LOCATIONVIEW until 12/09/2016. Lyndon
//		selectBookingGradeAgysSQL.append("  JOBPROFILE JP, ");
//		selectBookingGradeAgysSQL.append("  JOBSUBFAMILY JSF, ");
//		selectBookingGradeAgysSQL.append("  JOBFAMILY JF, ");
//		selectBookingGradeAgysSQL.append("  CLIENT C, ");
//		selectBookingGradeAgysSQL.append("  SITE S, ");
//		selectBookingGradeAgysSQL.append("  LOCATION L, ");
		selectBookingGradeAgysSQL.append("  GRADE G ");
		selectBookingGradeAgysSQL.append("WHERE BG.ACTIVE = TRUE ");
		selectBookingGradeAgysSQL.append("AND BG.STATUS IN (" + BookingGrade.BOOKINGGRADE_AGENCY_STATUSES + ") ");
		selectBookingGradeAgysSQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectBookingGradeAgysSQL.append("AND CAJPG.ACTIVE = TRUE ");
//		selectBookingGradeAgysSQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
//		selectBookingGradeAgysSQL.append("AND CAJP.ACTIVE = TRUE ");
//		selectBookingGradeAgysSQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
//		selectBookingGradeAgysSQL.append("AND CA.ACTIVE = TRUE ");
//		selectBookingGradeAgysSQL.append("AND C.CLIENTID = CA.CLIENTID ");
		selectBookingGradeAgysSQL.append("AND B.BOOKINGID = BG.BOOKINGID ");
		selectBookingGradeAgysSQL.append("AND JPV.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingGradeAgysSQL.append("AND JP.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingGradeAgysSQL.append("AND JP.ACTIVE = TRUE ");
//		selectBookingGradeAgysSQL.append("AND JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
//		selectBookingGradeAgysSQL.append("AND JSF.ACTIVE = TRUE ");
//		selectBookingGradeAgysSQL.append("AND JF.JOBFAMILYID = JSF.JOBFAMILYID ");
//		selectBookingGradeAgysSQL.append("AND JF.ACTIVE = TRUE ");
		selectBookingGradeAgysSQL.append("AND LV.LOCATIONID = B.LOCATIONID ");
//		selectBookingGradeAgysSQL.append("AND L.LOCATIONID = B.LOCATIONID ");
//		selectBookingGradeAgysSQL.append("AND L.ACTIVE = TRUE ");
//		selectBookingGradeAgysSQL.append("AND S.SITEID = L.SITEID ");
//		selectBookingGradeAgysSQL.append("AND S.ACTIVE = TRUE ");
//		selectBookingGradeAgysSQL.append("AND C.CLIENTID = S.CLIENTID ");
//		selectBookingGradeAgysSQL.append("AND C.ACTIVE = TRUE ");
		selectBookingGradeAgysSQL.append("AND G.GRADEID = CAJPG.GRADEID ");
		selectBookingGradeAgysSQL.append("AND G.ACTIVE = TRUE ");
        //
		selectBookingGradeAgysForAgencySQL = new StringBuffer(selectBookingGradeAgysSQL);
//		selectBookingGradeAgysForAgencySQL.append("AND CA.AGENCYID = ^ ");
		selectBookingGradeAgysForAgencySQL.append("AND BG.AGENCYID = ^ ");

		selectBookingGradeAgysForAgencySQL.append("AND ( ");
		selectBookingGradeAgysForAgencySQL.append(" ^ IS NULL "); //
		selectBookingGradeAgysForAgencySQL.append("OR ");
		selectBookingGradeAgysForAgencySQL.append(" B.SINGLECANDIDATE = ^ "); //
		selectBookingGradeAgysForAgencySQL.append(") ");

    //
    selectBookingGradeAgysForAgencyAndDateRangeSQL = new StringBuffer(selectBookingGradeAgysForAgencySQL);
    selectBookingGradeAgysForAgencyAndDateRangeSQL.append("AND B.MAXBOOKINGDATE >= ^ ");
    selectBookingGradeAgysForAgencyAndDateRangeSQL.append("AND B.MINBOOKINGDATE <= ^ ");
    selectBookingGradeAgysForAgencyAndDateRangeSQL.append("ORDER BY CLIENTNAME, SITENAME, LOCATIONNAME, JOBPROFILENAME, JOBFAMILYCODE, JOBSUBFAMILYCODE, B.MINBOOKINGDATE, SHIFTSTARTTIME ");

    //
    selectActiveBookingGradeAgysForAgencySQL = new StringBuffer(selectBookingGradeAgysForAgencySQL);
    selectActiveBookingGradeAgysForAgencySQL.append("AND B.ACTIVE = TRUE ");

    //
    selectActiveBookingGradeAgysForAgencyAndDateRangeSQL = new StringBuffer(selectActiveBookingGradeAgysForAgencySQL);
    selectActiveBookingGradeAgysForAgencyAndDateRangeSQL.append("AND B.MAXBOOKINGDATE >= ^ ");
    selectActiveBookingGradeAgysForAgencyAndDateRangeSQL.append("AND B.MINBOOKINGDATE <= ^ ");
    selectActiveBookingGradeAgysForAgencyAndDateRangeSQL.append("ORDER BY CLIENTNAME, SITENAME, LOCATIONNAME, JOBPROFILENAME, JOBFAMILYCODE, JOBSUBFAMILYCODE, B.MINBOOKINGDATE, SHIFTSTARTTIME ");

		//
		selectBookingGradeAgysForAgencyUnviewedSQL = new StringBuffer(selectActiveBookingGradeAgysForAgencySQL);
		selectBookingGradeAgysForAgencyUnviewedSQL.append("AND BG.VIEWED = FALSE ");
		
		//
		selectBookingGradeAgysForAgencyAndStatusSQL = new StringBuffer(selectActiveBookingGradeAgysForAgencySQL);
		selectBookingGradeAgysForAgencyAndStatusSQL.append("AND BG.STATUS = ^ ");
		selectBookingGradeAgysForAgencyAndStatusSQL.append("AND ( ");
		selectBookingGradeAgysForAgencyAndStatusSQL.append(" ^ IS NULL "); //
		selectBookingGradeAgysForAgencyAndStatusSQL.append("OR ");
		selectBookingGradeAgysForAgencyAndStatusSQL.append(" BG.VIEWED = ^ "); //
		selectBookingGradeAgysForAgencyAndStatusSQL.append(") ");
		
		//
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL = new StringBuffer(selectActiveBookingGradeAgysForAgencySQL);
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("AND EXISTS ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("( "); 
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  SELECT NULL ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  FROM BOOKINGGRADEAPPLICANTDATE BGAD, ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  BOOKINGGRADEAPPLICANT BGA, ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  BOOKINGDATE BD ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  WHERE BGAD.STATUS = ^ ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  AND BGAD.ACTIVE = TRUE ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  AND BGA.BOOKINGGRADEAPPLICANTID = BGAD.BOOKINGGRADEAPPLICANTID ");   
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  AND BGA.ACTIVE = TRUE ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  AND BGA.BOOKINGGRADEID = BG.BOOKINGGRADEID ");  
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  AND BD.BOOKINGDATEID = BGAD.BOOKINGDATEID ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  AND BD.ACTIVE = TRUE ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append("  AND B.BOOKINGID = BD.BOOKINGID ");
		selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.append(") ");  		
		
		//
		selectBookingGradeAgySQL = new StringBuffer(selectBookingGradeAgysSQL);
		selectBookingGradeAgySQL.append("AND BG.BOOKINGGRADEID = ^ ");
		selectBookingGradeAgySQL.append("AND BG.AGENCYID = ^ ");
		
		//
		selectBookingStatusCountsForAgencySQL = new StringBuffer();
		selectBookingStatusCountsForAgencySQL.append("SELECT BG.STATUS, ");
		selectBookingStatusCountsForAgencySQL.append("	       COUNT(BG.BOOKINGID) AS STATUSCOUNT ");
		selectBookingStatusCountsForAgencySQL.append("FROM BOOKINGGRADE BG, ");
		selectBookingStatusCountsForAgencySQL.append("CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
//		selectBookingStatusCountsForAgencySQL.append("CLIENTAGENCYJOBPROFILE CAJP, ");
//		selectBookingStatusCountsForAgencySQL.append("CLIENTAGENCY CA, ");
		selectBookingStatusCountsForAgencySQL.append("BOOKING B ");
		selectBookingStatusCountsForAgencySQL.append("WHERE BG.ACTIVE = TRUE ");
		selectBookingStatusCountsForAgencySQL.append("AND BG.STATUS = " + BookingGrade.BOOKINGGRADE_STATUS_OPEN + " ");
		selectBookingStatusCountsForAgencySQL.append("AND BG.VIEWED = TRUE ");
		selectBookingStatusCountsForAgencySQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectBookingStatusCountsForAgencySQL.append("AND CAJPG.ACTIVE = TRUE ");
//		selectBookingStatusCountsForAgencySQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
//		selectBookingStatusCountsForAgencySQL.append("AND CAJP.ACTIVE = TRUE ");
//		selectBookingStatusCountsForAgencySQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
//		selectBookingStatusCountsForAgencySQL.append("AND CA.ACTIVE = TRUE ");
//		selectBookingStatusCountsForAgencySQL.append("AND CA.AGENCYID = ^ ");
		selectBookingStatusCountsForAgencySQL.append("AND BG.AGENCYID = ^ ");
		selectBookingStatusCountsForAgencySQL.append("AND B.BOOKINGID = BG.BOOKINGID ");
		selectBookingStatusCountsForAgencySQL.append("AND B.ACTIVE = TRUE ");
		selectBookingStatusCountsForAgencySQL.append("AND B.SINGLECANDIDATE = TRUE ");
		selectBookingStatusCountsForAgencySQL.append("GROUP BY BG.STATUS ");
		selectBookingStatusCountsForAgencySQL.append("UNION ");
		selectBookingStatusCountsForAgencySQL.append("SELECT -1, ");
		selectBookingStatusCountsForAgencySQL.append("COUNT(BG.BOOKINGID) AS STATUSCOUNT ");
		selectBookingStatusCountsForAgencySQL.append("FROM BOOKINGGRADE BG, ");
		selectBookingStatusCountsForAgencySQL.append("CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
//		selectBookingStatusCountsForAgencySQL.append("CLIENTAGENCYJOBPROFILE CAJP, ");
//		selectBookingStatusCountsForAgencySQL.append("CLIENTAGENCY CA, ");
		selectBookingStatusCountsForAgencySQL.append("BOOKING B ");
		selectBookingStatusCountsForAgencySQL.append("WHERE BG.ACTIVE = TRUE ");
		selectBookingStatusCountsForAgencySQL.append("AND BG.STATUS = " + BookingGrade.BOOKINGGRADE_STATUS_OPEN + " ");
		selectBookingStatusCountsForAgencySQL.append("AND BG.VIEWED = FALSE ");
		selectBookingStatusCountsForAgencySQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectBookingStatusCountsForAgencySQL.append("AND CAJPG.ACTIVE = TRUE ");
//		selectBookingStatusCountsForAgencySQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
//		selectBookingStatusCountsForAgencySQL.append("AND CAJP.ACTIVE = TRUE ");
//		selectBookingStatusCountsForAgencySQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
//		selectBookingStatusCountsForAgencySQL.append("AND CA.ACTIVE = TRUE ");
//		selectBookingStatusCountsForAgencySQL.append("AND CA.AGENCYID = ^ ");
		selectBookingStatusCountsForAgencySQL.append("AND BG.AGENCYID = ^ ");
		selectBookingStatusCountsForAgencySQL.append("AND B.BOOKINGID = BG.BOOKINGID ");
        selectBookingStatusCountsForAgencySQL.append("AND B.ACTIVE = TRUE ");
        selectBookingStatusCountsForAgencySQL.append("AND B.SINGLECANDIDATE = TRUE ");
		selectBookingStatusCountsForAgencySQL.append("GROUP BY BG.STATUS ");		

		//
		selectShiftStatusCountsForAgencySQL = new StringBuffer();
		selectShiftStatusCountsForAgencySQL.append("SELECT BG.STATUS, ");
		selectShiftStatusCountsForAgencySQL.append("       COUNT(BG.BOOKINGID) AS STATUSCOUNT ");
		selectShiftStatusCountsForAgencySQL.append("FROM BOOKINGGRADE BG, ");
		selectShiftStatusCountsForAgencySQL.append("CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
//		selectShiftStatusCountsForAgencySQL.append("CLIENTAGENCYJOBPROFILE CAJP, ");
//		selectShiftStatusCountsForAgencySQL.append("CLIENTAGENCY CA, ");
		selectShiftStatusCountsForAgencySQL.append("BOOKING B, ");
		selectShiftStatusCountsForAgencySQL.append("BOOKINGDATE BD ");
		selectShiftStatusCountsForAgencySQL.append("WHERE BG.ACTIVE = TRUE ");
		selectShiftStatusCountsForAgencySQL.append("AND BG.STATUS = " + BookingGrade.BOOKINGGRADE_STATUS_OPEN + " ");
		selectShiftStatusCountsForAgencySQL.append("AND BG.VIEWED = TRUE ");
		selectShiftStatusCountsForAgencySQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectShiftStatusCountsForAgencySQL.append("AND CAJPG.ACTIVE = TRUE ");
//		selectShiftStatusCountsForAgencySQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
//		selectShiftStatusCountsForAgencySQL.append("AND CAJP.ACTIVE = TRUE ");
//		selectShiftStatusCountsForAgencySQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
//		selectShiftStatusCountsForAgencySQL.append("AND CA.ACTIVE = TRUE ");
//		selectShiftStatusCountsForAgencySQL.append("AND CA.AGENCYID = ^ ");
		selectShiftStatusCountsForAgencySQL.append("AND BG.AGENCYID = ^ ");
		selectShiftStatusCountsForAgencySQL.append("AND B.BOOKINGID = BG.BOOKINGID ");
		selectShiftStatusCountsForAgencySQL.append("AND B.ACTIVE = TRUE ");
		selectShiftStatusCountsForAgencySQL.append("AND B.SINGLECANDIDATE = FALSE ");
		selectShiftStatusCountsForAgencySQL.append("AND BD.BOOKINGID = BG.BOOKINGID ");
		selectShiftStatusCountsForAgencySQL.append("AND BD.ACTIVE = TRUE ");
		selectShiftStatusCountsForAgencySQL.append("GROUP BY BG.STATUS ");
		selectShiftStatusCountsForAgencySQL.append("UNION ");
		selectShiftStatusCountsForAgencySQL.append("SELECT -1, ");
		selectShiftStatusCountsForAgencySQL.append("COUNT(BD.BOOKINGID) AS STATUSCOUNT ");
		selectShiftStatusCountsForAgencySQL.append("FROM BOOKINGGRADE BG, ");
		selectShiftStatusCountsForAgencySQL.append("CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
//		selectShiftStatusCountsForAgencySQL.append("CLIENTAGENCYJOBPROFILE CAJP, ");
//		selectShiftStatusCountsForAgencySQL.append("CLIENTAGENCY CA, ");
		selectShiftStatusCountsForAgencySQL.append("BOOKING B, ");
		selectShiftStatusCountsForAgencySQL.append("BOOKINGDATE BD ");
		selectShiftStatusCountsForAgencySQL.append("WHERE BG.ACTIVE = TRUE ");
		selectShiftStatusCountsForAgencySQL.append("AND BG.STATUS = " + BookingGrade.BOOKINGGRADE_STATUS_OPEN + " ");
		selectShiftStatusCountsForAgencySQL.append("AND BG.VIEWED = FALSE ");
		selectShiftStatusCountsForAgencySQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectShiftStatusCountsForAgencySQL.append("AND CAJPG.ACTIVE = TRUE ");
//		selectShiftStatusCountsForAgencySQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
//		selectShiftStatusCountsForAgencySQL.append("AND CAJP.ACTIVE = TRUE ");
//		selectShiftStatusCountsForAgencySQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
//		selectShiftStatusCountsForAgencySQL.append("AND CA.ACTIVE = TRUE ");
//		selectShiftStatusCountsForAgencySQL.append("AND CA.AGENCYID = ^ ");
		selectShiftStatusCountsForAgencySQL.append("AND BG.AGENCYID = ^ ");
		selectShiftStatusCountsForAgencySQL.append("AND B.BOOKINGID = BG.BOOKINGID ");
		selectShiftStatusCountsForAgencySQL.append("AND B.ACTIVE = TRUE ");
		selectShiftStatusCountsForAgencySQL.append("AND B.SINGLECANDIDATE = FALSE ");
		selectShiftStatusCountsForAgencySQL.append("AND BD.BOOKINGID = BG.BOOKINGID ");
		selectShiftStatusCountsForAgencySQL.append("AND BD.ACTIVE = TRUE ");
		selectShiftStatusCountsForAgencySQL.append("GROUP BY BD.STATUS ");		
		
		//	
		selectBookingGradeApplicantDateStatusCountsForAgencySQL = new StringBuffer();
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("SELECT BGAD.STATUS, ");
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("       COUNT(DISTINCT B.BOOKINGID) "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("FROM BOOKINGGRADEAPPLICANTDATE BGAD, "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("     BOOKINGGRADEAPPLICANT BGA, "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("     BOOKINGGRADE BG, ");
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("     BOOKING B, ");
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("     APPLICANT A ");
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("WHERE BGAD.ACTIVE = TRUE "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = BGAD.BOOKINGGRADEAPPLICANTID "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND BGA.ACTIVE = TRUE "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND BG.BOOKINGGRADEID = BGA.BOOKINGGRADEID "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND BG.ACTIVE = TRUE "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND B.BOOKINGID = BG.BOOKINGID "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND B.ACTIVE = TRUE "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND A.APPLICANTID = BGA.APPLICANTID "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND A.ACTIVE = TRUE "); 
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("AND A.AGENCYID = ^ ");
		selectBookingGradeApplicantDateStatusCountsForAgencySQL.append("GROUP BY BGAD.STATUS ");		

		//
		selectShiftWorkedStatusCountsForAgencySQL = new StringBuffer();
		selectShiftWorkedStatusCountsForAgencySQL.append("SELECT BD.WORKEDSTATUS, ");
		selectShiftWorkedStatusCountsForAgencySQL.append("       COUNT(BD.BOOKINGDATEID) AS STATUSCOUNT ");
		selectShiftWorkedStatusCountsForAgencySQL.append("FROM BOOKINGDATE BD, ");
		selectShiftWorkedStatusCountsForAgencySQL.append("     BOOKINGGRADEAPPLICANTDATE BGAD, "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("     BOOKINGGRADEAPPLICANT BGA, "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("     APPLICANT A ");
		selectShiftWorkedStatusCountsForAgencySQL.append("WHERE BGAD.ACTIVE = TRUE "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = BGAD.BOOKINGGRADEAPPLICANTID "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("AND BGA.ACTIVE = TRUE "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("AND A.APPLICANTID = BGA.APPLICANTID "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("AND A.ACTIVE = TRUE "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("AND A.AGENCYID = ^ ");
		selectShiftWorkedStatusCountsForAgencySQL.append("AND BD.ACTIVE = TRUE "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("AND BD.BOOKINGDATEID = BGAD.BOOKINGDATEID "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("AND (BD.WORKEDSTARTTIME IS NOT NULL OR BD.COMMENT IS NOT NULL) "); 
		selectShiftWorkedStatusCountsForAgencySQL.append("GROUP BY 1 ");	
		
		//
		// TODO  don't think this is needed anymore
		//
		
		selectBookingGradeIdForAgencyAndBookingSQL = new StringBuffer();
		selectBookingGradeIdForAgencyAndBookingSQL.append("SELECT BG.BOOKINGGRADEID ");
		selectBookingGradeIdForAgencyAndBookingSQL.append("FROM BOOKINGGRADE BG ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("     , ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("     CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("     CLIENTAGENCYJOBPROFILE CAJP, ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("     CLIENTAGENCY CA ");
		selectBookingGradeIdForAgencyAndBookingSQL.append("WHERE BG.BOOKINGID = ^ ");
		selectBookingGradeIdForAgencyAndBookingSQL.append("AND BG.ACTIVE = TRUE ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("AND CAJPG.ACTIVE = TRUE ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("AND CAJP.ACTIVE = TRUE ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("AND CA.ACTIVE = TRUE ");
//		selectBookingGradeIdForAgencyAndBookingSQL.append("AND CA.AGENCYID = ^ ");		
		selectBookingGradeIdForAgencyAndBookingSQL.append("AND BG.AGENCYID = ^ ");		
		
	}

	public int insertBookingGrade(BookingGrade bookingGrade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBookingGradeSQL.toString());
		// Replace the parameters with supplied values.
		bookingGrade.setBookingGradeId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "bookingGrade"));
		Utilities.replace(sql, bookingGrade.getBookingGradeId());
		Utilities.replace(sql, bookingGrade.getBookingId());
		Utilities.replace(sql, bookingGrade.getClientAgencyJobProfileGradeId());
		Utilities.replace(sql, bookingGrade.getRate());
		Utilities.replace(sql, bookingGrade.getValue());
		Utilities.replace(sql, bookingGrade.getPayRate());
		Utilities.replace(sql, bookingGrade.getWtdPercentage());
		Utilities.replace(sql, bookingGrade.getNiPercentage());
		Utilities.replace(sql, bookingGrade.getWageRate());
		Utilities.replace(sql, bookingGrade.getChargeRateVatRate());
		Utilities.replace(sql, bookingGrade.getCommissionVatRate());
		Utilities.replace(sql, bookingGrade.getPayRateVatRate());
		Utilities.replace(sql, bookingGrade.getWtdVatRate());
		Utilities.replace(sql, bookingGrade.getNiVatRate());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGrade(BookingGrade bookingGrade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGrade.getClientAgencyJobProfileGradeId());
		Utilities.replace(sql, bookingGrade.getStatus());
		Utilities.replace(sql, bookingGrade.getRate());
		Utilities.replace(sql, bookingGrade.getValue());
		Utilities.replace(sql, bookingGrade.getPayRate());
		Utilities.replace(sql, bookingGrade.getWtdPercentage());
		Utilities.replace(sql, bookingGrade.getNiPercentage());
		Utilities.replace(sql, bookingGrade.getWageRate());
		Utilities.replace(sql, bookingGrade.getChargeRateVatRate());
		Utilities.replace(sql, bookingGrade.getCommissionVatRate());
		Utilities.replace(sql, bookingGrade.getPayRateVatRate());
		Utilities.replace(sql, bookingGrade.getWtdVatRate());
		Utilities.replace(sql, bookingGrade.getNiVatRate());
		Utilities.replaceAndQuoteNullable(sql, bookingGrade.getSendTimestamp());
		Utilities.replaceAndQuoteNullable(sql, bookingGrade.getSentTimestamp());
		Utilities.replace(sql, bookingGrade.getSentStatus());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGrade.getBookingGradeId());
		Utilities.replace(sql, bookingGrade.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeStatus(Integer bookingGradeId, Integer noOfChanges,
			Integer auditorId, int status) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, status);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeSentTimestamp(Integer bookingGradeId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeSentTimestampSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeSentStatus(Integer bookingGradeId, Integer noOfChanges, Integer sentStatus, 
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeSentStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, sentStatus);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeViewed(Integer bookingGradeId, Boolean viewed, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeViewedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteBookingGrade(Integer bookingGradeId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteBookingGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

  public BookingGrade getBookingGrade(Integer bookingGradeId) {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectBookingGradeSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, bookingGradeId);
    return (BookingGrade) RecordFactory.getInstance().get(getJdbcTemplate(),
        sql.toString(), BookingGrade.class.getName());
  }

  // NOTE. There can be more that ONE BookingGrade for a Booking so ONLY use this when you know for a fact
  //       that there is only one. Eg. For an NHS Booking.
  public BookingGrade getBookingGradeForBookingClientAgencyJobProfileGrade(Integer bookingId, Integer clientAgencyJobProfileGradeId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectBookingGradeForBookingClientAgencyJobProfileGradeSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, bookingId);
    Utilities.replace(sql, clientAgencyJobProfileGradeId);
    return (BookingGrade) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingGrade.class.getName());
  }

	public List<BookingGradeUser> getBookingGradeUsersForBooking(Integer bookingId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingGradeUsersForBookingSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingGradeUsersForBookingSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeUser.class.getName());

	}

	public BookingGradeUser getBookingGradeUserForBookingAndAgency(Integer bookingId, Integer agencyId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingGradeUserForBookingAndAgencySQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingGradeUserForBookingAndAgencySQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, agencyId);
		return (BookingGradeUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeUser.class.getName());

	}

	public int deleteBookingGradesForBooking(Integer bookingId, Integer auditorId) {

		int i = 0;
		List<BookingGradeUser> bookingGrades = getBookingGradeUsersForBooking(bookingId, true);
		for (BookingGradeUser bookingGrade: bookingGrades) {
            i += deleteBookingGrade(bookingGrade.getBookingGradeId(), bookingGrade.getNoOfChanges(), auditorId);
		}
		return i;

	}

	public int insertBookingGrades(Integer bookingId, ClientAgencyJobProfileGrade[] bookingGrades, Integer auditorId) {

		int i = 0;
        for (ClientAgencyJobProfileGrade clientAgencyJobProfileGrade: bookingGrades) {
        	BookingGrade bookingGrade = new BookingGrade();
        	bookingGrade.setBookingId(bookingId);
        	bookingGrade.setClientAgencyJobProfileGradeId(clientAgencyJobProfileGrade.getClientAgencyJobProfileGradeId());
        	bookingGrade.setRate(clientAgencyJobProfileGrade.getRate());
        	bookingGrade.setValue(clientAgencyJobProfileGrade.getValue());
        	bookingGrade.setPayRate(clientAgencyJobProfileGrade.getPayRate());
        	bookingGrade.setWtdPercentage(clientAgencyJobProfileGrade.getWtdPercentage());
        	bookingGrade.setNiPercentage(clientAgencyJobProfileGrade.getNiPercentage());
        	
        	// default the agency wage rate to the payeRate - this may change in the future to be configurable
        	bookingGrade.setWageRate(clientAgencyJobProfileGrade.getPayeRate());
        	
        	bookingGrade.setChargeRateVatRate(clientAgencyJobProfileGrade.getChargeRateVatRate());
        	bookingGrade.setCommissionVatRate(clientAgencyJobProfileGrade.getCommissionVatRate());
        	bookingGrade.setPayRateVatRate(clientAgencyJobProfileGrade.getPayRateVatRate());
        	bookingGrade.setWtdVatRate(clientAgencyJobProfileGrade.getWtdVatRate());
        	bookingGrade.setNiVatRate(clientAgencyJobProfileGrade.getNiVatRate());
        	i += insertBookingGrade(bookingGrade, auditorId);
        	
        	// TODO rubbish but for now need to update the bookingGrade with the agencyId
        	
        	int a = updateBookingGradeAgencyId(bookingGrade.getBookingGradeId(), bookingGrade.getNoOfChanges(), auditorId);
        
        }
        return i;

	}

	// TODO rubbish but for now need to update the bookingGrade with the agencyId

	private int updateBookingGradeAgencyId(Integer bookingGradeId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeAgencyIdSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeId);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString(), true, false);
	}
	
	public List<BookingGrade> getBookingGradesToSend() {

		StringBuffer sql = new StringBuffer(selectBookingGradesToSendSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGrade.class.getName());
		
	}

	public List<BookingGradeAgy> getBookingGradeAgysForAgency(Integer agencyId, Boolean singleCandidate, boolean showOnlyActive)
  {

    StringBuffer sql = null;
    if (showOnlyActive)
    {
      sql = new StringBuffer(selectActiveBookingGradeAgysForAgencySQL.toString());
    }
    else
    {
      sql = new StringBuffer(selectBookingGradeAgysForAgencySQL.toString());
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);

    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingGradeAgy.class.getName());

  }

  public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndDateRange(Integer agencyId, Date startDate, Date endDate, Boolean singleCandidate, boolean showOnlyActive)
  {

    StringBuffer sql = null;
    if (showOnlyActive)
    {
      sql = new StringBuffer(selectActiveBookingGradeAgysForAgencyAndDateRangeSQL.toString());
    }
    else
    {
      sql = new StringBuffer(selectBookingGradeAgysForAgencyAndDateRangeSQL.toString());
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, singleCandidate);
    Utilities.replace(sql, singleCandidate);
    Utilities.replaceAndQuote(sql, startDate);
    Utilities.replaceAndQuote(sql, endDate);

    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingGradeAgy.class.getName());

  }

	public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndStatus(Integer agencyId, Integer bookingGradeStatus, Boolean singleCandidate, 
			Boolean viewed) {
		
		StringBuffer sql = new StringBuffer(selectBookingGradeAgysForAgencyAndStatusSQL.toString()); 
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, bookingGradeStatus);
		Utilities.replace(sql, viewed);
		Utilities.replace(sql, viewed);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeAgy.class.getName());

	}

	public List<BookingGradeAgy> getBookingGradeAgysForAgencyUnviewed(Integer agencyId, Boolean singleCandidate) {
		
		StringBuffer sql = new StringBuffer(selectBookingGradeAgysForAgencyUnviewedSQL.toString()); 
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeAgy.class.getName());

	}

	public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatus(Integer agencyId, Integer bookingGradeApplicantDateStatus, Boolean singleCandidate) {
		
		StringBuffer sql = new StringBuffer(selectBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatusSQL.toString()); 
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, bookingGradeApplicantDateStatus);
		
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeAgy.class.getName());

	}
	
	public BookingGradeAgy getBookingGradeAgy(Integer bookingGradeId, Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingGradeAgySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeId);
		Utilities.replace(sql, agencyId);
		return (BookingGradeAgy) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeAgy.class.getName());
	}

	public BookingGradeAgyEntity getBookingGradeAgyEntity(Integer bookingGradeId, Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingGradeAgySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeId);
		Utilities.replace(sql, agencyId);
		return (BookingGradeAgyEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeAgyEntity.class.getName());
	}

	public List<StatusCount> getBookingStatusCountsForAgency(Integer agencyId) {

		StringBuffer sql = new StringBuffer(selectBookingStatusCountsForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public List<StatusCount> getShiftStatusCountsForAgency(Integer agencyId) {

		StringBuffer sql = new StringBuffer(selectShiftStatusCountsForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public List<StatusCount> getBookingGradeApplicantDateStatusCountsForAgency(Integer agencyId) {

		StringBuffer sql = new StringBuffer(selectBookingGradeApplicantDateStatusCountsForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public List<StatusCount> getShiftWorkedStatusCountsForAgency(Integer agencyId) {

		StringBuffer sql = new StringBuffer(selectShiftWorkedStatusCountsForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public IntValue getBookingGradeIdForAgencyAndBookingDate(Integer agencyId, Integer bookingId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingGradeIdForAgencyAndBookingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, agencyId);
		return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IntValue.class.getName());
		
	}

	
}
