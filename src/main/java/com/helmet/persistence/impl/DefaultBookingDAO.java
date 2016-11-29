package com.helmet.persistence.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.BookingUser;
import com.helmet.bean.BookingUserEntityMgr;
import com.helmet.bean.IntValue;
import com.helmet.bean.StatusCount;
import com.helmet.persistence.BookingDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBookingDAO extends JdbcDaoSupport implements BookingDAO {

	private static StringBuffer insertBookingSQL;

	private static StringBuffer updateBookingSQL;

	private static StringBuffer updateBookingInfoSQL;

	private static StringBuffer updateBookingExpensesTextSQL;

	private static StringBuffer updateBookingExtendSQL;

	private static StringBuffer updateBookingStatusSQL;

	private static StringBuffer updateBookingOpenSQL;

	private static StringBuffer updateBookingCancelSQL;

	private static StringBuffer updateBookingClosedSQL;

	private static StringBuffer updateBookingFilledValueSQL;

	private static StringBuffer updateBookingWorkedValueSQL;

	private static StringBuffer deleteBookingSQL;

	private static StringBuffer selectBookingSQL;

	private static StringBuffer selectBookingCountNotFilledSQL;

	private static StringBuffer selectBookingCountNotAuthorizedSQL;

	private static StringBuffer selectBookingsSQL;

  private static StringBuffer selectBookingUsersSQL;

  private static StringBuffer selectBookingsForBookingReferenceSQL;

  private static StringBuffer selectBookingsForLocationSQL;

	private static StringBuffer selectBookingUsersForManagerSQL;

	private static StringBuffer selectBookingUserAgysForAgencySQL;

	private static StringBuffer selectActiveBookingUsersForManagerSQL;

	private static StringBuffer selectActiveBookingUsersForManagerAndStatusSQL;

	private static StringBuffer selectActiveBookingUsersForManagerAndStatusAndDateRangeSQL;

	private static StringBuffer selectActiveBookingUsersForManagerAndDateRangeSQL;

	private static StringBuffer selectActiveBookingUsersForManagerAndWorkedStatusSQL;

	private static StringBuffer selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL;

	private static StringBuffer selectActiveBookingUserAgysForAgencySQL;

	private static StringBuffer selectBookingUserSQL;
	
	private static StringBuffer selectBookingUserForManagerSQL;
	
	private static StringBuffer selectBookingStatusCountsForManagerSQL;

	private static StringBuffer selectShiftStatusCountsForManagerSQL;

	private static StringBuffer selectBookingWorkedStatusCountsForManagerSQL;

	private static StringBuffer selectShiftWorkedStatusCountsForManagerSQL;

	private static StringBuffer selectBookingGradeApplicantDateStatusCountsForManagerSQL;
	
	private static StringBuffer selectShiftsToActivateCountForManagerSQL;

	private static StringBuffer selectShiftsToActivateCountForAgencySQL;
	
	private static StringBuffer selectShiftsOutstandingCountForManagerSQL;
	
	private static StringBuffer selectShiftsOutstandingCountForAgencySQL;
	
	private static StringBuffer selectAgencyInvoiceStatusCountsForManagerSQL;
	
	private static StringBuffer selectAgencyInvoiceStatusCountsForAgencySQL;
	
	public static void init() {
		// Get insert Booking SQL.
		insertBookingSQL = new StringBuffer();
		insertBookingSQL.append("INSERT INTO BOOKING ");
		insertBookingSQL.append("(  ");
		insertBookingSQL.append("  BOOKINGID, ");
		insertBookingSQL.append("  REASONFORREQUESTID, ");
		insertBookingSQL.append("  LOCATIONID, ");
		insertBookingSQL.append("  JOBPROFILEID, ");
		insertBookingSQL.append("  SHIFTID, ");
		insertBookingSQL.append("  BOOKEDBYID, ");
		insertBookingSQL.append("  DRESSCODEID, ");
		insertBookingSQL.append("  SINGLECANDIDATE, ");
		insertBookingSQL.append("  CVREQUIRED, ");
		insertBookingSQL.append("  INTERVIEWREQUIRED, ");
		insertBookingSQL.append("  CANPROVIDEACCOMMODATION, ");
		insertBookingSQL.append("  CARREQUIRED, ");
		insertBookingSQL.append("  COMMENTS, ");
		insertBookingSQL.append("  BOOKINGREFERENCE, ");
		insertBookingSQL.append("  AUTOFILL, ");
		insertBookingSQL.append("  AUTOACTIVATE, ");
		insertBookingSQL.append("  REASONFORREQUESTTEXT, ");
		insertBookingSQL.append("  EXPENSESTEXT, ");
		insertBookingSQL.append("  RATE, ");
		insertBookingSQL.append("  VALUE, ");
		insertBookingSQL.append("  NOOFHOURS, ");
		insertBookingSQL.append("  MINBOOKINGDATE, ");
		insertBookingSQL.append("  MAXBOOKINGDATE, ");
		insertBookingSQL.append("  NOOFBOOKINGDATES, ");
		insertBookingSQL.append("  DURATION, ");
		insertBookingSQL.append("  ACCOUNTCONTACTNAME, ");
		insertBookingSQL.append("  ACCOUNTCONTACTADDRESS1, ");
		insertBookingSQL.append("  ACCOUNTCONTACTADDRESS2, ");
		insertBookingSQL.append("  ACCOUNTCONTACTADDRESS3, ");
		insertBookingSQL.append("  ACCOUNTCONTACTADDRESS4, ");
		insertBookingSQL.append("  ACCOUNTCONTACTPOSTALCODE, ");
		insertBookingSQL.append("  ACCOUNTCONTACTCOUNTRYID, ");
		insertBookingSQL.append("  ACCOUNTCONTACTEMAILADDRESS, ");
		insertBookingSQL.append("  CONTACTNAME, ");
		insertBookingSQL.append("  CONTACTJOBTITLE, ");
		insertBookingSQL.append("  CONTACTEMAILADDRESS, ");
		insertBookingSQL.append("  CONTACTTELEPHONENUMBER, ");
		insertBookingSQL.append("  CREATIONTIMESTAMP, ");
		insertBookingSQL.append("  AUDITORID, ");
		insertBookingSQL.append("  AUDITTIMESTAMP ");
		insertBookingSQL.append(")  ");
		insertBookingSQL.append("VALUES  ");
		insertBookingSQL.append("(  ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^, ");
		insertBookingSQL.append("  ^ ");
		insertBookingSQL.append(")  ");
		// Get update Booking SQL.
		updateBookingSQL = new StringBuffer();
		updateBookingSQL.append("UPDATE BOOKING ");
		updateBookingSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateBookingSQL.append("     REASONFORREQUESTID = ^, ");
		updateBookingSQL.append("     LOCATIONID = ^, ");
		updateBookingSQL.append("     JOBPROFILEID = ^, ");
		updateBookingSQL.append("     SHIFTID = ^, ");
		updateBookingSQL.append("     DRESSCODEID = ^, ");
		updateBookingSQL.append("     STATUS = ^, ");
		updateBookingSQL.append("     SINGLECANDIDATE = ^, ");
		updateBookingSQL.append("     CVREQUIRED = ^, ");
		updateBookingSQL.append("     INTERVIEWREQUIRED = ^, ");
		updateBookingSQL.append("     CANPROVIDEACCOMMODATION = ^, ");
		updateBookingSQL.append("     CARREQUIRED = ^, ");
		updateBookingSQL.append("     COMMENTS = ^, ");
		updateBookingSQL.append("     BOOKINGREFERENCE = ^, ");
		updateBookingSQL.append("     AUTOFILL = ^, ");
		updateBookingSQL.append("     AUTOACTIVATE = ^, ");
		updateBookingSQL.append("     REASONFORREQUESTTEXT = ^, ");
		updateBookingSQL.append("     EXPENSESTEXT = ^, ");
		updateBookingSQL.append("     RATE = ^, ");
		updateBookingSQL.append("     VALUE = ^, ");
		updateBookingSQL.append("     NOOFHOURS = ^, ");
		updateBookingSQL.append("     FILLEDVALUE = ^, ");
		updateBookingSQL.append("     WORKEDVALUE = ^, ");
		updateBookingSQL.append("     WORKEDNOOFHOURS = ^, ");
		updateBookingSQL.append("     MINBOOKINGDATE = ^, ");
		updateBookingSQL.append("     MAXBOOKINGDATE = ^, ");
		updateBookingSQL.append("     NOOFBOOKINGDATES = ^, ");
		updateBookingSQL.append("     DURATION = ^, ");
		updateBookingSQL.append("     ACCOUNTCONTACTNAME = ^, ");
		updateBookingSQL.append("     ACCOUNTCONTACTADDRESS1 = ^, ");
		updateBookingSQL.append("     ACCOUNTCONTACTADDRESS2 = ^, ");
		updateBookingSQL.append("     ACCOUNTCONTACTADDRESS3 = ^, ");
		updateBookingSQL.append("     ACCOUNTCONTACTADDRESS4 = ^, ");
		updateBookingSQL.append("     ACCOUNTCONTACTPOSTALCODE = ^, ");
		updateBookingSQL.append("     ACCOUNTCONTACTCOUNTRYID = ^, ");
		updateBookingSQL.append("     ACCOUNTCONTACTEMAILADDRESS = ^, ");
		updateBookingSQL.append("     CONTACTNAME = ^, ");
		updateBookingSQL.append("     CONTACTJOBTITLE = ^, ");
		updateBookingSQL.append("     CONTACTEMAILADDRESS = ^, ");
		updateBookingSQL.append("     CONTACTTELEPHONENUMBER = ^, ");
		updateBookingSQL.append("     AUDITORID = ^, ");
		updateBookingSQL.append("     AUDITTIMESTAMP = ^ ");
		updateBookingSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingInfo SQL.
		updateBookingInfoSQL = new StringBuffer();
		updateBookingInfoSQL.append("UPDATE BOOKING ");
		updateBookingInfoSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateBookingInfoSQL.append("     DURATION = ^, ");
		updateBookingInfoSQL.append("     BOOKINGREFERENCE = ^, ");
		updateBookingInfoSQL.append("     ACCOUNTCONTACTNAME = ^, ");
		updateBookingInfoSQL.append("     ACCOUNTCONTACTADDRESS1 = ^, ");
		updateBookingInfoSQL.append("     ACCOUNTCONTACTADDRESS2 = ^, ");
		updateBookingInfoSQL.append("     ACCOUNTCONTACTADDRESS3 = ^, ");
		updateBookingInfoSQL.append("     ACCOUNTCONTACTADDRESS4 = ^, ");
		updateBookingInfoSQL.append("     ACCOUNTCONTACTPOSTALCODE = ^, ");
		updateBookingInfoSQL.append("     ACCOUNTCONTACTCOUNTRYID = ^, ");
		updateBookingInfoSQL.append("     ACCOUNTCONTACTEMAILADDRESS = ^, ");
		updateBookingInfoSQL.append("     CONTACTNAME = ^, ");
		updateBookingInfoSQL.append("     CONTACTJOBTITLE = ^, ");
		updateBookingInfoSQL.append("     CONTACTEMAILADDRESS = ^, ");
		updateBookingInfoSQL.append("     CONTACTTELEPHONENUMBER = ^, ");
		updateBookingInfoSQL.append("     AUDITORID = ^, ");
		updateBookingInfoSQL.append("     AUDITTIMESTAMP = ^ ");
		updateBookingInfoSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingInfoSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingExpensesText SQL.
		updateBookingExpensesTextSQL = new StringBuffer();
		updateBookingExpensesTextSQL.append("UPDATE BOOKING ");
		updateBookingExpensesTextSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateBookingExpensesTextSQL.append("     EXPENSESTEXT = ^, ");
		updateBookingExpensesTextSQL.append("     AUDITORID = ^, ");
		updateBookingExpensesTextSQL.append("     AUDITTIMESTAMP = ^ ");
		updateBookingExpensesTextSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingExpensesTextSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update Booking when Extended SQL.
		updateBookingExtendSQL = new StringBuffer();
		updateBookingExtendSQL.append("UPDATE BOOKING ");
		updateBookingExtendSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateBookingExtendSQL.append("     SHIFTID = ^, ");
		updateBookingExtendSQL.append("     VALUE = ^, ");
		updateBookingExtendSQL.append("     NOOFHOURS = ^, ");
		updateBookingExtendSQL.append("     MINBOOKINGDATE = ^, ");
		updateBookingExtendSQL.append("     MAXBOOKINGDATE = ^, ");
		updateBookingExtendSQL.append("     NOOFBOOKINGDATES = ^, ");
		updateBookingExtendSQL.append("     AUDITORID = ^, ");
		updateBookingExtendSQL.append("     AUDITTIMESTAMP = ^ ");
		updateBookingExtendSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingExtendSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Booking SQL.
		deleteBookingSQL = new StringBuffer();
		deleteBookingSQL.append("UPDATE BOOKING ");
		deleteBookingSQL.append("SET ACTIVE = FALSE, ");
		deleteBookingSQL.append("    AUDITORID = ^, ");
		deleteBookingSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteBookingSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteBookingSQL.append("WHERE BOOKINGID = ^ ");
		deleteBookingSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update Booking status SQL.
		updateBookingStatusSQL = new StringBuffer();
		updateBookingStatusSQL.append("UPDATE BOOKING ");
		updateBookingStatusSQL.append("SET STATUS = ^, ");
		updateBookingStatusSQL.append("    AUDITORID = ^, ");
		updateBookingStatusSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingStatusSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingStatusSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingStatusSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update Booking Open SQL.
		updateBookingOpenSQL = new StringBuffer();
		updateBookingOpenSQL.append("UPDATE BOOKING ");
		updateBookingOpenSQL.append("SET STATUS = " + Booking.BOOKING_STATUS_OPEN + ", ");
		updateBookingOpenSQL.append("    SUBMITTEDBYID = ^, ");
		updateBookingOpenSQL.append("    SUBMITTEDTIMESTAMP = ^, ");
		updateBookingOpenSQL.append("    AUDITORID = ^, ");
		updateBookingOpenSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingOpenSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingOpenSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingOpenSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update Booking closed SQL.
		updateBookingClosedSQL = new StringBuffer();
		updateBookingClosedSQL.append("UPDATE BOOKING ");
		updateBookingClosedSQL.append("SET STATUS = " + Booking.BOOKING_STATUS_CLOSED + ", ");
		updateBookingClosedSQL.append("    FILLEDVALUE = FILLEDVALUE + ^, ");
		updateBookingClosedSQL.append("    AUDITORID = ^, ");
		updateBookingClosedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingClosedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingClosedSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingClosedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update Booking cancel SQL.
		updateBookingCancelSQL = new StringBuffer();
		updateBookingCancelSQL.append("UPDATE BOOKING ");
		updateBookingCancelSQL.append("SET STATUS = " + Booking.BOOKING_STATUS_CANCELLED + ", ");
		updateBookingCancelSQL.append("    NOOFBOOKINGDATES = 0, ");
		updateBookingCancelSQL.append("    NOOFHOURS = 0, ");
		updateBookingCancelSQL.append("    VALUE = 0, ");
		updateBookingCancelSQL.append("    CANCELTEXT = ^, ");
		updateBookingCancelSQL.append("    CANCELLEDBYID = ^, ");
		updateBookingCancelSQL.append("    CANCELLEDTIMESTAMP = ^, ");
		updateBookingCancelSQL.append("    AUDITORID = ^, ");
		updateBookingCancelSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingCancelSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingCancelSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingCancelSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update Booking filledValue SQL.
		updateBookingFilledValueSQL = new StringBuffer();
		updateBookingFilledValueSQL.append("UPDATE BOOKING ");
		updateBookingFilledValueSQL.append("SET FILLEDVALUE = FILLEDVALUE + ^, ");
		updateBookingFilledValueSQL.append("    AUDITORID = ^, ");
		updateBookingFilledValueSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingFilledValueSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingFilledValueSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingFilledValueSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update Booking workedValue SQL.
		updateBookingWorkedValueSQL = new StringBuffer();
		updateBookingWorkedValueSQL.append("UPDATE BOOKING ");
		updateBookingWorkedValueSQL.append("SET WORKEDVALUE = WORKEDVALUE + ^, ");
		updateBookingWorkedValueSQL.append("    WORKEDNOOFHOURS = WORKEDNOOFHOURS + ^, ");
		updateBookingWorkedValueSQL.append("    STATUS = ^, ");
		updateBookingWorkedValueSQL.append("    AUDITORID = ^, ");
		updateBookingWorkedValueSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingWorkedValueSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingWorkedValueSQL.append("WHERE BOOKINGID = ^ ");
		updateBookingWorkedValueSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Bookings SQL.
		selectBookingsSQL = new StringBuffer();
		selectBookingsSQL.append("SELECT B.BOOKINGID, ");
		selectBookingsSQL.append("  B.REASONFORREQUESTID, ");
		selectBookingsSQL.append("  B.LOCATIONID, ");
		selectBookingsSQL.append("  B.JOBPROFILEID, ");
		selectBookingsSQL.append("  B.SHIFTID, ");
		selectBookingsSQL.append("  B.BOOKEDBYID, ");
		selectBookingsSQL.append("  B.DRESSCODEID, ");
		selectBookingsSQL.append("  B.STATUS, ");
		selectBookingsSQL.append("  B.SINGLECANDIDATE, ");
		selectBookingsSQL.append("  B.CVREQUIRED, ");
		selectBookingsSQL.append("  B.INTERVIEWREQUIRED, ");
		selectBookingsSQL.append("  B.CANPROVIDEACCOMMODATION, ");
		selectBookingsSQL.append("  B.CARREQUIRED, ");
		selectBookingsSQL.append("  B.COMMENTS, ");
		selectBookingsSQL.append("  B.BOOKINGREFERENCE, ");
		selectBookingsSQL.append("  B.AUTOFILL, ");
		selectBookingsSQL.append("  B.AUTOACTIVATE, ");
		selectBookingsSQL.append("  B.REASONFORREQUESTTEXT, ");
		selectBookingsSQL.append("  B.EXPENSESTEXT, ");
		selectBookingsSQL.append("  B.RATE, ");
		selectBookingsSQL.append("  B.VALUE, ");
		selectBookingsSQL.append("  B.NOOFHOURS, ");
		selectBookingsSQL.append("  B.FILLEDVALUE, ");
		selectBookingsSQL.append("  B.WORKEDVALUE, ");
		selectBookingsSQL.append("  B.WORKEDNOOFHOURS, ");
		selectBookingsSQL.append("  B.MINBOOKINGDATE, ");
		selectBookingsSQL.append("  B.MAXBOOKINGDATE, ");
		selectBookingsSQL.append("  B.NOOFBOOKINGDATES, ");
		selectBookingsSQL.append("  B.DURATION, ");
		selectBookingsSQL.append("  B.ACCOUNTCONTACTNAME, ");
		selectBookingsSQL.append("  B.ACCOUNTCONTACTADDRESS1, ");
		selectBookingsSQL.append("  B.ACCOUNTCONTACTADDRESS2, ");
		selectBookingsSQL.append("  B.ACCOUNTCONTACTADDRESS3, ");
		selectBookingsSQL.append("  B.ACCOUNTCONTACTADDRESS4, ");
		selectBookingsSQL.append("  B.ACCOUNTCONTACTPOSTALCODE, ");
		selectBookingsSQL.append("  B.ACCOUNTCONTACTCOUNTRYID, ");
		selectBookingsSQL.append("  B.ACCOUNTCONTACTEMAILADDRESS, ");
		selectBookingsSQL.append("  B.CONTACTNAME, ");
		selectBookingsSQL.append("  B.CONTACTJOBTITLE, ");
		selectBookingsSQL.append("  B.CONTACTEMAILADDRESS, ");
		selectBookingsSQL.append("  B.CONTACTTELEPHONENUMBER, ");
		selectBookingsSQL.append("  B.CANCELTEXT, ");
		selectBookingsSQL.append("  B.CANCELLEDBYID, ");
		selectBookingsSQL.append("  B.CANCELLEDTIMESTAMP, ");
		selectBookingsSQL.append("  B.SUBMITTEDBYID, ");
		selectBookingsSQL.append("  B.SUBMITTEDTIMESTAMP, ");
		selectBookingsSQL.append("  B.CREATIONTIMESTAMP, ");
		selectBookingsSQL.append("  B.AUDITORID, ");
		selectBookingsSQL.append("  B.AUDITTIMESTAMP, ");
		selectBookingsSQL.append("  B.ACTIVE, ");
		selectBookingsSQL.append("  B.NOOFCHANGES ");
		// Get select Booking Users SQL.
		selectBookingUsersSQL = new StringBuffer(selectBookingsSQL);
        //		
		selectBookingsSQL.append("FROM BOOKING B ");
		// Get select Booking SQL.
		selectBookingSQL = new StringBuffer(selectBookingsSQL);
		selectBookingSQL.append("WHERE B.BOOKINGID = ^ ");
        //		
		selectBookingUsersSQL.append("  , ");
		selectBookingUsersSQL.append("  R.NAME AS REASONFORREQUESTNAME, ");
		selectBookingUsersSQL.append("  R.CODE AS REASONFORREQUESTCODE, ");
		selectBookingUsersSQL.append("  LV.CLIENTID, ");
		selectBookingUsersSQL.append("  LV.CLIENTNAME, ");
		selectBookingUsersSQL.append("  LV.CLIENTCODE, ");
		selectBookingUsersSQL.append("  LV.SITEID, ");
		selectBookingUsersSQL.append("  LV.SITENAME, ");
		selectBookingUsersSQL.append("  LV.SITECODE, ");
		selectBookingUsersSQL.append("  LV.LOCATIONNAME, ");
		selectBookingUsersSQL.append("  LV.LOCATIONCODE, ");
		selectBookingUsersSQL.append("  LV.LOCATIONDESCRIPTION, ");
		selectBookingUsersSQL.append("  JPV.JOBFAMILYID, ");
		selectBookingUsersSQL.append("  JPV.JOBFAMILYNAME, ");
		selectBookingUsersSQL.append("  JPV.JOBFAMILYCODE, ");
		selectBookingUsersSQL.append("  JPV.JOBSUBFAMILYID, ");
		selectBookingUsersSQL.append("  JPV.JOBSUBFAMILYNAME, ");
		selectBookingUsersSQL.append("  JPV.JOBSUBFAMILYCODE, ");
		selectBookingUsersSQL.append("  JPV.JOBPROFILENAME, ");
		selectBookingUsersSQL.append("  JPV.JOBPROFILECODE, ");
		selectBookingUsersSQL.append("  JPV.JOBPROFILERATE, ");
//		selectBookingUsersSQL.append("  C.CLIENTID, ");
//		selectBookingUsersSQL.append("  C.NAME AS CLIENTNAME, ");
//		selectBookingUsersSQL.append("  C.CODE AS CLIENTCODE, ");
//		selectBookingUsersSQL.append("  S.SITEID, ");
//		selectBookingUsersSQL.append("  S.NAME AS SITENAME, ");
//		selectBookingUsersSQL.append("  S.CODE AS SITECODE, ");
//		selectBookingUsersSQL.append("  L.NAME AS LOCATIONNAME, ");
//		selectBookingUsersSQL.append("  L.CODE AS LOCATIONCODE, ");
//		selectBookingUsersSQL.append("  L.DESCRIPTION AS LOCATIONDESCRIPTION, ");
//		selectBookingUsersSQL.append("  JF.JOBFAMILYID, ");
//		selectBookingUsersSQL.append("  JF.NAME AS JOBFAMILYNAME, ");
//		selectBookingUsersSQL.append("  JF.CODE AS JOBFAMILYCODE, ");
//		selectBookingUsersSQL.append("  JSF.JOBSUBFAMILYID, ");
//		selectBookingUsersSQL.append("  JSF.NAME AS JOBSUBFAMILYNAME, ");
//		selectBookingUsersSQL.append("  JSF.CODE AS JOBSUBFAMILYCODE, ");
//		selectBookingUsersSQL.append("  JP.NAME AS JOBPROFILENAME, ");
//		selectBookingUsersSQL.append("  JP.CODE AS JOBPROFILECODE, ");
//		selectBookingUsersSQL.append("  JP.RATE AS JOBPROFILERATE, ");
		selectBookingUsersSQL.append("  LJP.RATE AS LOCATIONJOBPROFILERATE, ");
		selectBookingUsersSQL.append("  SH.CODE AS SHIFTCODE, ");
		selectBookingUsersSQL.append("  SH.NAME AS SHIFTNAME, ");
		selectBookingUsersSQL.append("  SH.DESCRIPTION AS SHIFTDESCRIPTION, ");
		selectBookingUsersSQL.append("  SH.STARTTIME AS SHIFTSTARTTIME, ");
		selectBookingUsersSQL.append("  SH.ENDTIME AS SHIFTENDTIME, ");
		selectBookingUsersSQL.append("  SH.BREAKSTARTTIME AS SHIFTBREAKSTARTTIME, ");
		selectBookingUsersSQL.append("  SH.BREAKENDTIME AS SHIFTBREAKENDTIME, ");
		selectBookingUsersSQL.append("  SH.NOOFHOURS AS SHIFTNOOFHOURS, ");
		selectBookingUsersSQL.append("  AV.FIRSTNAME AS BOOKEDBYFIRSTNAME, ");
		selectBookingUsersSQL.append("  AV.LASTNAME AS BOOKEDBYLASTNAME, ");
		selectBookingUsersSQL.append("  AV.EMAILADDRESS AS BOOKEDBYEMAILADDRESS, ");
		selectBookingUsersSQL.append("  D.NAME AS DRESSCODENAME, ");
		selectBookingUsersSQL.append("	CO.NAME AS ACCOUNTCONTACTCOUNTRYNAME, ");

		selectBookingUsersSQL.append("  X1.LOGIN AS CANCELLEDBYLOGIN, X1.FIRSTNAME AS CANCELLEDBYFIRSTNAME, X1.LASTNAME AS CANCELLEDBYLASTNAME, ");   
		selectBookingUsersSQL.append("  X2.LOGIN AS SUBMITTEDBYLOGIN, X2.FIRSTNAME AS SUBMITTEDBYFIRSTNAME, X2.LASTNAME AS SUBMITTEDBYLASTNAME ");  

		selectBookingUsersSQL.append("  /* extracolumns */ ");
        selectBookingUsersSQL.append("FROM BOOKING B ");
 		selectBookingUsersSQL.append("  LEFT OUTER JOIN DRESSCODE D ON D.DRESSCODEID = B.DRESSCODEID AND D.ACTIVE = TRUE ");
 		selectBookingUsersSQL.append("  LEFT OUTER JOIN SHIFT SH ON SH.SHIFTID = B.SHIFTID AND SH.ACTIVE = TRUE ");

 		selectBookingUsersSQL.append("  LEFT OUTER JOIN AUDITORVIEW X1 ON X1.AUDITORID = B.CANCELLEDBYID ");  
 		selectBookingUsersSQL.append("  LEFT OUTER JOIN AUDITORVIEW X2 ON X2.AUDITORID = B.SUBMITTEDBYID ");  

 		selectBookingUsersSQL.append("  LEFT OUTER JOIN COUNTRY CO ON CO.COUNTRYID = B.ACCOUNTCONTACTCOUNTRYID AND CO.ACTIVE = TRUE "); 		
		
 		selectBookingUsersSQL.append(", ");
 		
 		selectBookingUsersSQL.append("  REASONFORREQUEST R, ");
 		selectBookingUsersSQL.append("  LOCATIONVIEW LV, ");
 		selectBookingUsersSQL.append("  JOBPROFILEVIEW JPV, ");
// 		selectBookingUsersSQL.append("  CLIENT C, ");
// 		selectBookingUsersSQL.append("  SITE S, ");
// 		selectBookingUsersSQL.append("  LOCATION L, ");
// 		selectBookingUsersSQL.append("  JOBFAMILY JF, ");
// 		selectBookingUsersSQL.append("  JOBSUBFAMILY JSF, ");
// 		selectBookingUsersSQL.append("  JOBPROFILE JP, ");
 		selectBookingUsersSQL.append("  LOCATIONJOBPROFILE LJP, ");
 		selectBookingUsersSQL.append("  AUDITORVIEW AV ");
        selectBookingUsersSQL.append("  /* extratables */ ");
        selectBookingUsersSQL.append("WHERE R.REASONFORREQUESTID = B.REASONFORREQUESTID ");
 		selectBookingUsersSQL.append("AND R.ACTIVE = TRUE ");
 		selectBookingUsersSQL.append("AND LV.LOCATIONID = B.LOCATIONID ");
// 		selectBookingUsersSQL.append("AND L.LOCATIONID = B.LOCATIONID ");
// 		selectBookingUsersSQL.append("AND L.ACTIVE = TRUE ");
// 		selectBookingUsersSQL.append("AND S.SITEID = L.SITEID ");
// 		selectBookingUsersSQL.append("AND S.ACTIVE = TRUE ");
// 		selectBookingUsersSQL.append("AND C.CLIENTID = S.CLIENTID ");
// 		selectBookingUsersSQL.append("AND C.ACTIVE = TRUE ");
 		selectBookingUsersSQL.append("AND JPV.JOBPROFILEID = B.JOBPROFILEID ");
// 		selectBookingUsersSQL.append("AND JP.JOBPROFILEID = B.JOBPROFILEID ");
// 		selectBookingUsersSQL.append("AND JP.ACTIVE = TRUE ");
// 		selectBookingUsersSQL.append("AND JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
// 		selectBookingUsersSQL.append("AND JSF.ACTIVE = TRUE ");
// 		selectBookingUsersSQL.append("AND JF.JOBFAMILYID = JSF.JOBFAMILYID ");
// 		selectBookingUsersSQL.append("AND JF.ACTIVE = TRUE ");
 		selectBookingUsersSQL.append("AND LJP.LOCATIONID = B.LOCATIONID ");
 		selectBookingUsersSQL.append("AND LJP.JOBPROFILEID = B.JOBPROFILEID ");
 		selectBookingUsersSQL.append("AND LJP.ACTIVE = TRUE ");
 		selectBookingUsersSQL.append("AND AV.AUDITORID = B.BOOKEDBYID ");
    // Get select Booking Users for BookingReference
    selectBookingsForBookingReferenceSQL = new StringBuffer(selectBookingsSQL);
    selectBookingsForBookingReferenceSQL.append("WHERE B.BOOKINGREFERENCE = ^ ");
    // Get select Booking Users for Location
    selectBookingsForLocationSQL = new StringBuffer(selectBookingsSQL);
    selectBookingsForLocationSQL.append("WHERE B.LOCATIONID = ^ ");
    selectBookingsForLocationSQL.append("AND B.ACTIVE = TRUE ");
    // Get select Booking User
		selectBookingUserSQL = new StringBuffer(selectBookingUsersSQL);
		selectBookingUserSQL.append("AND B.BOOKINGID = ^ ");
		// Get select BookingUsers for Manager SQL.
		selectBookingUsersForManagerSQL = new StringBuffer(selectBookingUsersSQL);
		selectBookingUsersForManagerSQL.append("AND (0 = ^ OR EXISTS ");
		selectBookingUsersForManagerSQL.append("( "); 
		selectBookingUsersForManagerSQL.append("  SELECT NULL "); 
		selectBookingUsersForManagerSQL.append("  FROM LOCATIONMANAGERJOBPROFILE LMJP ");
		selectBookingUsersForManagerSQL.append("  WHERE LMJP.MANAGERID = ^ ");
		selectBookingUsersForManagerSQL.append("  AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectBookingUsersForManagerSQL.append("  AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectBookingUsersForManagerSQL.append("  AND LMJP.ACTIVE = TRUE ");
		selectBookingUsersForManagerSQL.append(")) ");  		
		selectBookingUsersForManagerSQL.append("AND ( ");
		selectBookingUsersForManagerSQL.append(" ^ IS NULL "); //
		selectBookingUsersForManagerSQL.append("OR ");
		selectBookingUsersForManagerSQL.append(" B.SINGLECANDIDATE = ^ "); //
		selectBookingUsersForManagerSQL.append(") ");

		selectBookingUsersForManagerSQL.append("AND ( ");
		selectBookingUsersForManagerSQL.append(" ^ IS NULL "); //
		selectBookingUsersForManagerSQL.append("OR ");
		selectBookingUsersForManagerSQL.append(" LV.SITEID = ^ "); //
		selectBookingUsersForManagerSQL.append(") ");

		selectBookingUsersForManagerSQL.append("AND ( ");
		selectBookingUsersForManagerSQL.append(" ^ IS NULL "); //
		selectBookingUsersForManagerSQL.append("OR ");
		selectBookingUsersForManagerSQL.append(" B.LOCATIONID = ^ "); //
		selectBookingUsersForManagerSQL.append(") ");

		selectBookingUsersForManagerSQL.append("AND ( ");
		selectBookingUsersForManagerSQL.append(" ^ IS NULL "); //
		selectBookingUsersForManagerSQL.append("OR ");
		selectBookingUsersForManagerSQL.append(" B.JOBPROFILEID = ^ "); //
		selectBookingUsersForManagerSQL.append(") ");
		
		// Get select Active BookingUsers for Manager SQL.
		selectActiveBookingUsersForManagerSQL = new StringBuffer(selectBookingUsersForManagerSQL);
		selectActiveBookingUsersForManagerSQL.append("AND B.ACTIVE = TRUE ");


		// Get select Active BookingUser for Manager SQL.
		selectBookingUserForManagerSQL = new StringBuffer(selectActiveBookingUsersForManagerSQL);
		selectBookingUserForManagerSQL.append("AND B.BOOKINGID = ^ ");

		// Get select Active BookingUsers for Manager and status SQL.
		selectActiveBookingUsersForManagerAndStatusSQL = new StringBuffer(selectActiveBookingUsersForManagerSQL);
		selectActiveBookingUsersForManagerAndStatusSQL.append("AND B.STATUS = ^ ");

		
		selectActiveBookingUsersForManagerAndStatusAndDateRangeSQL = new StringBuffer(selectActiveBookingUsersForManagerAndStatusSQL);
		selectActiveBookingUsersForManagerAndStatusAndDateRangeSQL.append("AND B.MAXBOOKINGDATE >= ^ "); //
		selectActiveBookingUsersForManagerAndStatusAndDateRangeSQL.append("AND B.MINBOOKINGDATE <= ^ "); //
		
		selectActiveBookingUsersForManagerAndDateRangeSQL = new StringBuffer(selectActiveBookingUsersForManagerSQL);
		selectActiveBookingUsersForManagerAndDateRangeSQL.append("AND B.MAXBOOKINGDATE >= ^ "); //
		selectActiveBookingUsersForManagerAndDateRangeSQL.append("AND B.MINBOOKINGDATE <= ^ "); //
		
		// Get select Active BookingUsers for Manager and workedStatus SQL.
		selectActiveBookingUsersForManagerAndWorkedStatusSQL = new StringBuffer(selectActiveBookingUsersForManagerSQL);
		selectActiveBookingUsersForManagerAndWorkedStatusSQL.append("AND EXISTS ");
		selectActiveBookingUsersForManagerAndWorkedStatusSQL.append("( "); 
		selectActiveBookingUsersForManagerAndWorkedStatusSQL.append("  SELECT NULL ");
		selectActiveBookingUsersForManagerAndWorkedStatusSQL.append("  FROM BOOKINGDATE BD ");
		selectActiveBookingUsersForManagerAndWorkedStatusSQL.append("  WHERE BD.WORKEDSTATUS = ^ ");
		selectActiveBookingUsersForManagerAndWorkedStatusSQL.append("  AND BD.ACTIVE = TRUE ");
		selectActiveBookingUsersForManagerAndWorkedStatusSQL.append("  AND B.BOOKINGID = BD.BOOKINGID ");
		selectActiveBookingUsersForManagerAndWorkedStatusSQL.append(") ");  		

		// Get select Active BookingUsers for Manager and bookingGradeApplicantDateWorkedStatus SQL.
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL = new StringBuffer(selectActiveBookingUsersForManagerSQL);
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("AND EXISTS ");
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("( "); 
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  SELECT NULL ");
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  FROM BOOKINGGRADEAPPLICANTDATE BGAD, ");
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  BOOKINGDATE BD ");
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  WHERE BGAD.STATUS = ^ ");
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  AND BGAD.ACTIVE = TRUE ");

		// KRD - to correct bug where Open BGADs show even if date is Cancelled
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  AND BD.STATUS != " + BookingDate.BOOKINGDATE_STATUS_CANCELLED + " "); 

		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  AND BD.BOOKINGDATEID = BGAD.BOOKINGDATEID ");
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  AND BD.ACTIVE = TRUE ");
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append("  AND B.BOOKINGID = BD.BOOKINGID ");
		selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.append(") ");  		

		selectBookingUserAgysForAgencySQL = new StringBuffer(selectBookingUsersSQL.toString().
				replaceFirst("/\\* extracolumns \\*/",
  							 ", " +
  							 "BG.BOOKINGGRADEID, " +
							 "BG.CLIENTAGENCYJOBPROFILEGRADEID, " +
							 "BG.STATUS AS BOOKINGGRADESTATUS, " +
							 "BG.VALUE AS BOOKINGGRADEVALUE, " +
							 "BG.RATE AS BOOKINGGRADERATE, " +
							 "BG.VIEWED AS BOOKINGGRADEVIEWED, " +
							 "BG.CANNOTFILL AS BOOKINGGRADECANNOTFILL, " +
							 "BG.NOOFCHANGES AS BOOKINGGRADENOOFCHANGES"));
		
		System.out.println(selectBookingUserAgysForAgencySQL.toString());
		
		selectBookingUserAgysForAgencySQL = new StringBuffer(selectBookingUserAgysForAgencySQL.toString().
				replaceFirst("/\\* extratables \\*/",
						     ", " +
						     "BOOKINGGRADE BG, " +
						     "CLIENTAGENCYJOBPROFILEGRADE CAJPG, " +
						     "CLIENTAGENCYJOBPROFILE CAJP, " +
						     "CLIENTAGENCY CA "));

		System.out.println(selectBookingUserAgysForAgencySQL.toString());

		selectBookingUserAgysForAgencySQL.append("AND CA.AGENCYID = ^ ");
		selectBookingUserAgysForAgencySQL.append("AND CA.ACTIVE = TRUE ");
		selectBookingUserAgysForAgencySQL.append("AND BG.BOOKINGID = B.BOOKINGID ");
		selectBookingUserAgysForAgencySQL.append("AND BG.ACTIVE = TRUE ");
		selectBookingUserAgysForAgencySQL.append("AND BG.STATUS = " + BookingGrade.BOOKINGGRADE_STATUS_OPEN + " ");
		selectBookingUserAgysForAgencySQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BG.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectBookingUserAgysForAgencySQL.append("AND CAJPG.ACTIVE = TRUE ");
		selectBookingUserAgysForAgencySQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
		selectBookingUserAgysForAgencySQL.append("AND CAJP.ACTIVE = TRUE "); 
		selectBookingUserAgysForAgencySQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
		selectBookingUserAgysForAgencySQL.append("AND CA.ACTIVE = TRUE  ");
		
		selectActiveBookingUserAgysForAgencySQL = new StringBuffer(selectBookingUserAgysForAgencySQL);
		selectActiveBookingUserAgysForAgencySQL.append("AND B.ACTIVE = TRUE ");

		// Get select BookingCountNotFilled SQL.
		selectBookingCountNotFilledSQL = new StringBuffer();
		selectBookingCountNotFilledSQL.append("SELECT COUNT(BD.BOOKINGDATEID) ");
		selectBookingCountNotFilledSQL.append("FROM BOOKINGDATE BD ");
		selectBookingCountNotFilledSQL.append("WHERE BD.BOOKINGID = ^ ");
		selectBookingCountNotFilledSQL.append("AND BD.ACTIVE = TRUE ");
		selectBookingCountNotFilledSQL.append("AND BD.STATUS NOT IN (" + BookingDate.BOOKINGDATE_STATUS_FILLED + ", " +
				                                                         BookingDate.BOOKINGDATE_STATUS_COMPLETED + ", " + 
				                                                         BookingDate.BOOKINGDATE_STATUS_CANCELLED + ")");
		
		// Get select BookingCountNotAuthorized SQL.
		selectBookingCountNotAuthorizedSQL = new StringBuffer();
		selectBookingCountNotAuthorizedSQL.append("SELECT COUNT(BD.BOOKINGDATEID) ");
		selectBookingCountNotAuthorizedSQL.append("FROM BOOKINGDATE BD ");
		selectBookingCountNotAuthorizedSQL.append("WHERE BD.BOOKINGID = ^ ");
		selectBookingCountNotAuthorizedSQL.append("AND BD.ACTIVE = TRUE ");
		selectBookingCountNotAuthorizedSQL.append("AND BD.STATUS = " + BookingDate.BOOKINGDATE_STATUS_FILLED + " ");
		selectBookingCountNotAuthorizedSQL.append("AND ( ");
		selectBookingCountNotAuthorizedSQL.append("     BD.WORKEDSTATUS IS NULL "); 
		selectBookingCountNotAuthorizedSQL.append("  OR "); 
    	selectBookingCountNotAuthorizedSQL.append("     BD.WORKEDSTATUS NOT IN (" + 
				BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED + ", " + 
				BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED + 
				                                                                 ") ");
		selectBookingCountNotAuthorizedSQL.append("    ) ");
		
		//
		selectBookingStatusCountsForManagerSQL = new StringBuffer();
		selectBookingStatusCountsForManagerSQL.append("SELECT B.STATUS, ");
		selectBookingStatusCountsForManagerSQL.append("       COUNT(B.BOOKINGID) AS STATUSCOUNT ");
		selectBookingStatusCountsForManagerSQL.append("FROM BOOKING B ");
		selectBookingStatusCountsForManagerSQL.append("WHERE B.ACTIVE = TRUE ");

		selectBookingStatusCountsForManagerSQL.append("AND ( ");
		selectBookingStatusCountsForManagerSQL.append(" (B.STATUS = " + Booking.BOOKING_STATUS_DRAFT + ") ");
		selectBookingStatusCountsForManagerSQL.append(" OR ");
		selectBookingStatusCountsForManagerSQL.append(" (B.STATUS IN (" + Booking.BOOKING_STATUS_OPEN + ", " + 
                                                                          Booking.BOOKING_STATUS_OFFERED + ") ");
		selectBookingStatusCountsForManagerSQL.append("  AND B.SINGLECANDIDATE = TRUE) "); // booking counts ONLY apply to single candidate bookings
		selectBookingStatusCountsForManagerSQL.append(") ");
		selectBookingStatusCountsForManagerSQL.append("AND EXISTS ");
		selectBookingStatusCountsForManagerSQL.append("( ");
		selectBookingStatusCountsForManagerSQL.append("SELECT NULL ");
		selectBookingStatusCountsForManagerSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectBookingStatusCountsForManagerSQL.append("     LOCATIONJOBPROFILE LJP ");
		selectBookingStatusCountsForManagerSQL.append("WHERE LMJP.MANAGERID = ^ ");
		selectBookingStatusCountsForManagerSQL.append("AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectBookingStatusCountsForManagerSQL.append("AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectBookingStatusCountsForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectBookingStatusCountsForManagerSQL.append("AND LJP.LOCATIONID = LMJP.LOCATIONID ");
		selectBookingStatusCountsForManagerSQL.append("AND LJP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectBookingStatusCountsForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectBookingStatusCountsForManagerSQL.append(") ");
		selectBookingStatusCountsForManagerSQL.append("GROUP BY 1 ");
		
		//
		selectShiftStatusCountsForManagerSQL = new StringBuffer();
		selectShiftStatusCountsForManagerSQL.append("SELECT BD.STATUS, ");
		selectShiftStatusCountsForManagerSQL.append("       COUNT(BD.BOOKINGDATEID) AS STATUSCOUNT ");
		selectShiftStatusCountsForManagerSQL.append("FROM BOOKING B, ");
		selectShiftStatusCountsForManagerSQL.append("     BOOKINGDATE BD ");
		selectShiftStatusCountsForManagerSQL.append("WHERE B.ACTIVE = TRUE ");
		selectShiftStatusCountsForManagerSQL.append("AND B.SINGLECANDIDATE = FALSE "); // shift counts ONLY apply to NON single candidate bookings
		selectShiftStatusCountsForManagerSQL.append("AND BD.BOOKINGID = B.BOOKINGID ");
		selectShiftStatusCountsForManagerSQL.append("AND BD.ACTIVE = TRUE ");
		selectShiftStatusCountsForManagerSQL.append("AND BD.STATUS IN (" + BookingDate.BOOKINGDATE_STATUS_OPEN + ", " + 
				                                                           BookingDate.BOOKINGDATE_STATUS_OFFERED + ") ");
		selectShiftStatusCountsForManagerSQL.append("AND EXISTS ");
		selectShiftStatusCountsForManagerSQL.append("( ");
		selectShiftStatusCountsForManagerSQL.append("SELECT NULL ");
		selectShiftStatusCountsForManagerSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectShiftStatusCountsForManagerSQL.append("     LOCATIONJOBPROFILE LJP ");
		selectShiftStatusCountsForManagerSQL.append("WHERE LMJP.MANAGERID = ^ ");
		selectShiftStatusCountsForManagerSQL.append("AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectShiftStatusCountsForManagerSQL.append("AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectShiftStatusCountsForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectShiftStatusCountsForManagerSQL.append("AND LJP.LOCATIONID = LMJP.LOCATIONID ");
		selectShiftStatusCountsForManagerSQL.append("AND LJP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectShiftStatusCountsForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectShiftStatusCountsForManagerSQL.append(") ");
		selectShiftStatusCountsForManagerSQL.append("GROUP BY 1 ");

		//
		selectBookingWorkedStatusCountsForManagerSQL = new StringBuffer();
		selectBookingWorkedStatusCountsForManagerSQL.append("SELECT BD.WORKEDSTATUS, ");
		selectBookingWorkedStatusCountsForManagerSQL.append("       COUNT(DISTINCT BD.BOOKINGID) AS STATUSCOUNT ");
		selectBookingWorkedStatusCountsForManagerSQL.append("FROM BOOKINGDATE BD, ");
		selectBookingWorkedStatusCountsForManagerSQL.append("     BOOKING B ");
		selectBookingWorkedStatusCountsForManagerSQL.append("WHERE BD.WORKEDSTATUS IS NOT NULL ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND BD.ACTIVE = TRUE ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND B.ACTIVE = TRUE ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND EXISTS ");
		selectBookingWorkedStatusCountsForManagerSQL.append("( ");
		selectBookingWorkedStatusCountsForManagerSQL.append("SELECT NULL ");
		selectBookingWorkedStatusCountsForManagerSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectBookingWorkedStatusCountsForManagerSQL.append("     LOCATIONJOBPROFILE LJP ");
		selectBookingWorkedStatusCountsForManagerSQL.append("WHERE LMJP.MANAGERID = ^ ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND LJP.LOCATIONID = LMJP.LOCATIONID ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND LJP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectBookingWorkedStatusCountsForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectBookingWorkedStatusCountsForManagerSQL.append(") ");		
		selectBookingWorkedStatusCountsForManagerSQL.append("GROUP BY 1 ");

		//
		selectShiftWorkedStatusCountsForManagerSQL = new StringBuffer();
		selectShiftWorkedStatusCountsForManagerSQL.append("SELECT BD.WORKEDSTATUS, ");
		selectShiftWorkedStatusCountsForManagerSQL.append("       COUNT(BD.BOOKINGGRADEAPPLICANTDATEID) AS STATUSCOUNT ");
		selectShiftWorkedStatusCountsForManagerSQL.append("FROM BOOKINGDATE BD, ");
		selectShiftWorkedStatusCountsForManagerSQL.append("     BOOKING B ");
		selectShiftWorkedStatusCountsForManagerSQL.append("WHERE BD.WORKEDSTATUS IS NOT NULL ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND BD.ACTIVE = TRUE ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND B.ACTIVE = TRUE ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND EXISTS ");
		selectShiftWorkedStatusCountsForManagerSQL.append("( ");
		selectShiftWorkedStatusCountsForManagerSQL.append("SELECT NULL ");
		selectShiftWorkedStatusCountsForManagerSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectShiftWorkedStatusCountsForManagerSQL.append("     LOCATIONJOBPROFILE LJP ");
		selectShiftWorkedStatusCountsForManagerSQL.append("WHERE LMJP.MANAGERID = ^ ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND LJP.LOCATIONID = LMJP.LOCATIONID ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND LJP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectShiftWorkedStatusCountsForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectShiftWorkedStatusCountsForManagerSQL.append(") ");		
		selectShiftWorkedStatusCountsForManagerSQL.append("GROUP BY 1 ");

		//
		selectBookingGradeApplicantDateStatusCountsForManagerSQL = new StringBuffer();
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("SELECT BGAD.STATUS, ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("       COUNT(DISTINCT BD.BOOKINGID) AS STATUSCOUNT ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("FROM BOOKINGGRADEAPPLICANTDATE BGAD, ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("     BOOKINGDATE BD, ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("     BOOKING B ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("WHERE BGAD.STATUS IS NOT NULL ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND BGAD.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND BGAD.STATUS = " + BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OPEN + " "); 

		// KRD - to correct bug where Open BGADs show even if date is Cancelled
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND BD.STATUS != " + BookingDate.BOOKINGDATE_STATUS_CANCELLED + " "); 

		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND BD.BOOKINGDATEID = BGAD.BOOKINGDATEID ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND BD.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND B.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND EXISTS ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("( ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("SELECT NULL ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("     LOCATIONJOBPROFILE LJP ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("WHERE LMJP.MANAGERID = ^ ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND LJP.LOCATIONID = LMJP.LOCATIONID ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND LJP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append(") ");		
		selectBookingGradeApplicantDateStatusCountsForManagerSQL.append("GROUP BY 1 ");

		selectShiftsToActivateCountForManagerSQL = new StringBuffer();
		selectShiftsToActivateCountForManagerSQL.append("SELECT COUNT(BD.BOOKINGDATEID) "); 
		selectShiftsToActivateCountForManagerSQL.append("FROM BOOKINGDATE BD, ");
		selectShiftsToActivateCountForManagerSQL.append("     BOOKING B ");
		selectShiftsToActivateCountForManagerSQL.append("WHERE BD.ACTIVE = TRUE ");
		selectShiftsToActivateCountForManagerSQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectShiftsToActivateCountForManagerSQL.append("AND B.ACTIVE = TRUE ");
		selectShiftsToActivateCountForManagerSQL.append("AND BD.BOOKINGDATE <= CURRENT_DATE ");
		selectShiftsToActivateCountForManagerSQL.append("AND BD.ACTIVATED = FALSE ");
		selectShiftsToActivateCountForManagerSQL.append("AND BD.STATUS = " + BookingDate.BOOKINGDATE_STATUS_FILLED + " " );
		selectShiftsToActivateCountForManagerSQL.append("AND EXISTS ");
		selectShiftsToActivateCountForManagerSQL.append("( ");
		selectShiftsToActivateCountForManagerSQL.append(" SELECT NULL ");
		selectShiftsToActivateCountForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LMJP "); 
		selectShiftsToActivateCountForManagerSQL.append(" WHERE LMJP.MANAGERID = ^ ");
		selectShiftsToActivateCountForManagerSQL.append(" AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectShiftsToActivateCountForManagerSQL.append(" AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectShiftsToActivateCountForManagerSQL.append(" AND LMJP.ACTIVE = TRUE ");
		selectShiftsToActivateCountForManagerSQL.append(") ");

		selectShiftsToActivateCountForAgencySQL = new StringBuffer();
		selectShiftsToActivateCountForAgencySQL.append("SELECT COUNT(BD.BOOKINGDATEID) "); 
		selectShiftsToActivateCountForAgencySQL.append("FROM BOOKINGDATE BD, ");
		selectShiftsToActivateCountForAgencySQL.append("     BOOKING B, ");
		selectShiftsToActivateCountForAgencySQL.append("	 BOOKINGGRADEAPPLICANTDATE BGAD, ");
		selectShiftsToActivateCountForAgencySQL.append("	 BOOKINGGRADEAPPLICANT BGA ");
//		selectShiftsToActivateCountForAgencySQL.append("	 APPLICANT A ");
		selectShiftsToActivateCountForAgencySQL.append("WHERE BD.ACTIVE = TRUE ");
		selectShiftsToActivateCountForAgencySQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectShiftsToActivateCountForAgencySQL.append("AND B.ACTIVE = TRUE ");
		selectShiftsToActivateCountForAgencySQL.append("AND BD.BOOKINGDATE <= CURRENT_DATE ");
		selectShiftsToActivateCountForAgencySQL.append("AND BD.ACTIVATED = FALSE ");
		selectShiftsToActivateCountForAgencySQL.append("AND BD.STATUS = " + BookingDate.BOOKINGDATE_STATUS_FILLED + " ");
		selectShiftsToActivateCountForAgencySQL.append("AND BGAD.BOOKINGGRADEAPPLICANTDATEID = BD.BOOKINGGRADEAPPLICANTDATEID ");
		selectShiftsToActivateCountForAgencySQL.append("AND BGAD.ACTIVE = TRUE ");
		selectShiftsToActivateCountForAgencySQL.append("AND BGAD.STATUS = " + BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED + " ");
		selectShiftsToActivateCountForAgencySQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = BGAD.BOOKINGGRADEAPPLICANTID "); 
		selectShiftsToActivateCountForAgencySQL.append("AND BGA.ACTIVE = TRUE ");
//		selectShiftsToActivateCountForAgencySQL.append("AND A.APPLICANTID = BGA.APPLICANTID "); 
//		selectShiftsToActivateCountForAgencySQL.append("AND A.ACTIVE = TRUE "); 
//		selectShiftsToActivateCountForAgencySQL.append("AND A.AGENCYID = ^ ");		
		selectShiftsToActivateCountForAgencySQL.append("AND EXISTS ");
		selectShiftsToActivateCountForAgencySQL.append("(SELECT NULL ");
		selectShiftsToActivateCountForAgencySQL.append(" FROM APPLICANT A ");
		selectShiftsToActivateCountForAgencySQL.append(" WHERE A.ACTIVE = TRUE ");
		selectShiftsToActivateCountForAgencySQL.append(" AND A.AGENCYID = ^ ");
		selectShiftsToActivateCountForAgencySQL.append(" AND A.APPLICANTID = BGA.APPLICANTID)");		
		
		selectShiftsOutstandingCountForManagerSQL = new StringBuffer();
		selectShiftsOutstandingCountForManagerSQL.append("SELECT COUNT(BD.BOOKINGDATEID) "); 
		selectShiftsOutstandingCountForManagerSQL.append("FROM BOOKINGDATE BD, ");
		selectShiftsOutstandingCountForManagerSQL.append("     BOOKING B ");
		selectShiftsOutstandingCountForManagerSQL.append("WHERE BD.ACTIVE = TRUE ");
		selectShiftsOutstandingCountForManagerSQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectShiftsOutstandingCountForManagerSQL.append("AND B.ACTIVE = TRUE ");
		selectShiftsOutstandingCountForManagerSQL.append("AND BD.BOOKINGDATE <= CURRENT_DATE ");
		selectShiftsOutstandingCountForManagerSQL.append("AND BD.ACTIVATED = TRUE "); // Activated
		selectShiftsOutstandingCountForManagerSQL.append("AND BD.WORKEDSTATUS IS NULL "); // Not even draft though
		selectShiftsOutstandingCountForManagerSQL.append("AND BD.STATUS = " + BookingDate.BOOKINGDATE_STATUS_FILLED + " " );
		selectShiftsOutstandingCountForManagerSQL.append("AND EXISTS ");
		selectShiftsOutstandingCountForManagerSQL.append("( ");
		selectShiftsOutstandingCountForManagerSQL.append(" SELECT NULL ");
		selectShiftsOutstandingCountForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LMJP "); 
		selectShiftsOutstandingCountForManagerSQL.append(" WHERE LMJP.MANAGERID = ^ ");
		selectShiftsOutstandingCountForManagerSQL.append(" AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectShiftsOutstandingCountForManagerSQL.append(" AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectShiftsOutstandingCountForManagerSQL.append(" AND LMJP.ACTIVE = TRUE ");
		selectShiftsOutstandingCountForManagerSQL.append(") ");

		selectShiftsOutstandingCountForAgencySQL = new StringBuffer();
		selectShiftsOutstandingCountForAgencySQL.append("SELECT COUNT(BD.BOOKINGDATEID) "); 
		selectShiftsOutstandingCountForAgencySQL.append("FROM BOOKINGDATE BD, ");
		selectShiftsOutstandingCountForAgencySQL.append("     BOOKING B, ");
		selectShiftsOutstandingCountForAgencySQL.append("	 BOOKINGGRADEAPPLICANTDATE BGAD, ");
		selectShiftsOutstandingCountForAgencySQL.append("	 BOOKINGGRADEAPPLICANT BGA ");
//		selectShiftsOutstandingCountForAgencySQL.append("	 APPLICANT A ");
		selectShiftsOutstandingCountForAgencySQL.append("WHERE BD.ACTIVE = TRUE ");
		selectShiftsOutstandingCountForAgencySQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectShiftsOutstandingCountForAgencySQL.append("AND B.ACTIVE = TRUE ");
		selectShiftsOutstandingCountForAgencySQL.append("AND BD.BOOKINGDATE <= CURRENT_DATE ");
		selectShiftsOutstandingCountForAgencySQL.append("AND BD.ACTIVATED = TRUE "); // Activated
		selectShiftsOutstandingCountForAgencySQL.append("AND BD.WORKEDSTATUS IS NULL "); // Not even draft though
		selectShiftsOutstandingCountForAgencySQL.append("AND BD.STATUS = " + BookingDate.BOOKINGDATE_STATUS_FILLED + " ");
		selectShiftsOutstandingCountForAgencySQL.append("AND BGAD.BOOKINGGRADEAPPLICANTDATEID = BD.BOOKINGGRADEAPPLICANTDATEID ");
		selectShiftsOutstandingCountForAgencySQL.append("AND BGAD.ACTIVE = TRUE ");
		selectShiftsOutstandingCountForAgencySQL.append("AND BGAD.STATUS = " + BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED + " ");
		selectShiftsOutstandingCountForAgencySQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = BGAD.BOOKINGGRADEAPPLICANTID "); 
		selectShiftsOutstandingCountForAgencySQL.append("AND BGA.ACTIVE = TRUE ");
//		selectShiftsOutstandingCountForAgencySQL.append("AND A.APPLICANTID = BGA.APPLICANTID "); 
//		selectShiftsOutstandingCountForAgencySQL.append("AND A.ACTIVE = TRUE "); 
//		selectShiftsOutstandingCountForAgencySQL.append("AND A.AGENCYID = ^ ");		
		selectShiftsOutstandingCountForAgencySQL.append("AND EXISTS ");
		selectShiftsOutstandingCountForAgencySQL.append("(SELECT NULL ");
		selectShiftsOutstandingCountForAgencySQL.append(" FROM APPLICANT A ");
		selectShiftsOutstandingCountForAgencySQL.append(" WHERE A.ACTIVE = TRUE ");
		selectShiftsOutstandingCountForAgencySQL.append(" AND A.AGENCYID = ^ ");
		selectShiftsOutstandingCountForAgencySQL.append(" AND A.APPLICANTID = BGA.APPLICANTID)");		
		
		//
		selectAgencyInvoiceStatusCountsForManagerSQL = new StringBuffer();
		selectAgencyInvoiceStatusCountsForManagerSQL.append("SELECT AI.STATUS, ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("   COUNT(DISTINCT AI.AGENCYINVOICEID) AS STATUSCOUNT ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("FROM AGENCYINVOICE AI, ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("     BOOKINGDATE BD, ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("     BOOKING B ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("WHERE AI.ACTIVE = TRUE ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND AI.CLIENTID = ^ ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND BD.AGENCYINVOICEID = AI.AGENCYINVOICEID ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND BD.ACTIVE = TRUE ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND B.ACTIVE = TRUE ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND EXISTS ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("( ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("SELECT NULL ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("LOCATIONJOBPROFILE LJP ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("WHERE LMJP.MANAGERID = ^ ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND LJP.LOCATIONID = LMJP.LOCATIONID ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND LJP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append(") ");
		selectAgencyInvoiceStatusCountsForManagerSQL.append("GROUP BY 1 ");		

		//
		selectAgencyInvoiceStatusCountsForAgencySQL = new StringBuffer();
		selectAgencyInvoiceStatusCountsForAgencySQL.append("SELECT AI.STATUS, ");
		selectAgencyInvoiceStatusCountsForAgencySQL.append("   COUNT(DISTINCT AI.AGENCYINVOICEID) AS STATUSCOUNT ");
		selectAgencyInvoiceStatusCountsForAgencySQL.append("FROM AGENCYINVOICE AI ");
		selectAgencyInvoiceStatusCountsForAgencySQL.append("WHERE AI.ACTIVE = TRUE ");
		selectAgencyInvoiceStatusCountsForAgencySQL.append("AND AI.AGENCYID = ^ ");
		selectAgencyInvoiceStatusCountsForAgencySQL.append("GROUP BY 1 ");		
		
	}

	public int insertBooking(Booking booking, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBookingSQL.toString());
		// Replace the parameters with supplied values.
		booking.setBookingId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "booking"));
		Utilities.replace(sql, booking.getBookingId());
		Utilities.replace(sql, booking.getReasonForRequestId());
		Utilities.replace(sql, booking.getLocationId());
		Utilities.replace(sql, booking.getJobProfileId());
        // temporary frig
		if (booking.getShiftId() != null && booking.getShiftId().equals(new Integer(0))) {
			booking.setShiftId(null);
		}
		Utilities.replace(sql, booking.getShiftId());
		Utilities.replace(sql, auditorId);
		Utilities.replace(sql, booking.getDressCodeId());
		Utilities.replace(sql, booking.getSingleCandidate());
		Utilities.replace(sql, booking.getCvRequired());
		Utilities.replace(sql, booking.getInterviewRequired());
		Utilities.replace(sql, booking.getCanProvideAccommodation());
		Utilities.replace(sql, booking.getCarRequired());
		Utilities.replaceAndQuoteNullable(sql, booking.getComments());
		Utilities.replaceAndQuoteNullable(sql, booking.getBookingReference());
		Utilities.replace(sql, booking.getAutoFill());
		Utilities.replace(sql, booking.getAutoActivate());
		Utilities.replaceAndQuoteNullable(sql, booking.getReasonForRequestText());
		Utilities.replaceAndQuoteNullable(sql, booking.getExpensesText());
		Utilities.replace(sql, booking.getRate());
		Utilities.replace(sql, booking.getValue());
		Utilities.replace(sql, booking.getNoOfHours());
		Utilities.replaceAndQuote(sql, booking.getMinBookingDate());
		Utilities.replaceAndQuote(sql, booking.getMaxBookingDate());
		Utilities.replace(sql, booking.getNoOfBookingDates());
		Utilities.replaceAndQuoteNullable(sql, booking.getDuration());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactName());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress4());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getPostalCode());
		Utilities.replaceZeroWithNull(sql, booking.getAccountContactAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactName());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactJobTitle());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactTelephoneNumber());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBooking(Booking booking, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, booking.getReasonForRequestId());
		Utilities.replace(sql, booking.getLocationId());
		Utilities.replace(sql, booking.getJobProfileId());
        // temporary frig
		if (booking.getShiftId() != null && booking.getShiftId().equals(new Integer(0))) {
			booking.setShiftId(null);
		}
		Utilities.replace(sql, booking.getShiftId());
        // temporary frig
		if (booking.getDressCodeId() != null && booking.getDressCodeId().equals(new Integer(0))) {
			booking.setDressCodeId(null);
		}
		Utilities.replace(sql, booking.getDressCodeId());
		Utilities.replace(sql, booking.getStatus());
		Utilities.replace(sql, booking.getSingleCandidate());
		Utilities.replace(sql, booking.getCvRequired());
		Utilities.replace(sql, booking.getInterviewRequired());
		Utilities.replace(sql, booking.getCanProvideAccommodation());
		Utilities.replace(sql, booking.getCarRequired());
		Utilities.replaceAndQuoteNullable(sql, booking.getComments());
		Utilities.replaceAndQuoteNullable(sql, booking.getBookingReference());
		Utilities.replace(sql, booking.getAutoFill());
		Utilities.replace(sql, booking.getAutoActivate());
		Utilities.replaceAndQuoteNullable(sql, booking.getReasonForRequestText());
		Utilities.replaceAndQuoteNullable(sql, booking.getExpensesText());
		Utilities.replace(sql, booking.getRate());
		Utilities.replace(sql, booking.getValue());
		Utilities.replace(sql, booking.getNoOfHours());
		Utilities.replace(sql, booking.getFilledValue());
		Utilities.replace(sql, booking.getWorkedValue());
		Utilities.replace(sql, booking.getWorkedNoOfHours());
		Utilities.replaceAndQuote(sql, booking.getMinBookingDate());
		Utilities.replaceAndQuote(sql, booking.getMaxBookingDate());
		Utilities.replace(sql, booking.getNoOfBookingDates());
		Utilities.replaceAndQuoteNullable(sql, booking.getDuration());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactName());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress4());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getPostalCode());
		Utilities.replaceZeroWithNull(sql, booking.getAccountContactAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactName());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactJobTitle());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactTelephoneNumber());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, booking.getBookingId());
		Utilities.replace(sql, booking.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}
	
	public int updateBookingExtend(Booking booking, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingExtendSQL.toString());
        // temporary frig - also above
		if (booking.getShiftId() != null && booking.getShiftId().equals(new Integer(0))) {
			booking.setShiftId(null);
		}
		Utilities.replace(sql, booking.getShiftId());
		Utilities.replace(sql, booking.getValue());
		Utilities.replace(sql, booking.getNoOfHours());
		Utilities.replaceAndQuote(sql, booking.getMinBookingDate());
		Utilities.replaceAndQuote(sql, booking.getMaxBookingDate());
		Utilities.replace(sql, booking.getNoOfBookingDates());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, booking.getBookingId());
		Utilities.replace(sql, booking.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}
	

	public int updateBookingStatus(Integer bookingId, Integer noOfChanges,
			Integer auditorId, int status) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, status);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingOpen(Integer bookingId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingOpenSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteBooking(Integer bookingId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteBookingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public Booking getBooking(Integer bookingId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return (Booking) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Booking.class.getName());
	}

  public List<Booking> getBookingsForBookingReference(String bookingReference) 
  {
    StringBuffer sql = new StringBuffer(selectBookingsForBookingReferenceSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, bookingReference);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Booking.class.getName());
  }

  public List<Booking> getBookingsForLocation(Integer locationId) 
  {
    StringBuffer sql = new StringBuffer(selectBookingsForLocationSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, locationId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Booking.class.getName());
  }

	public List<BookingUser> getBookingUsersForManager(Integer managerId, Boolean singleCandidate, boolean showOnlyActive, Integer siteId, Integer locationId, Integer jobProfileId) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingUsersForManagerSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingUsersForManagerSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUser.class.getName());

	}

	public List<BookingUser> getBookingUsersForManagerAndBooking(Integer managerId, Integer bookingId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingUserForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);

		Boolean singleCandidate = null;
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

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
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUser.class.getName());

	}

	public List<BookingUser> getBookingUsersForManagerAndStatus(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {

		StringBuffer sql = new StringBuffer(selectActiveBookingUsersForManagerAndStatusSQL.toString());
		
		sql = addOrderBy(sql, bookingStatus);

		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, bookingStatus);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUser.class.getName());

	}

	public List<BookingUser> getBookingUsersForManagerAndStatusAndDateRange(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {

		StringBuffer sql = new StringBuffer(selectActiveBookingUsersForManagerAndStatusAndDateRangeSQL.toString());
		
		sql = addOrderBy(sql, bookingStatus);

		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, bookingStatus);
		Utilities.replaceAndQuote(sql, fromDate);
		Utilities.replaceAndQuote(sql, toDate);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUser.class.getName());

	}
    
	public List<BookingUser> getBookingUsersForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {
		
		StringBuffer sql = new StringBuffer(selectActiveBookingUsersForManagerAndDateRangeSQL.toString());
		
		sql = addOrderBy(sql, null);

		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);

		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replaceAndQuote(sql, fromDate);
		Utilities.replaceAndQuote(sql, toDate);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUser.class.getName());

	}
	
	private StringBuffer addOrderBy(StringBuffer sql, Integer bookingStatus) {

		// based on the status - change the ordering ???

		String statusLine = 
			 "WHERE BD.STATUS IN (" + BookingDate.BOOKINGDATE_STATUS_FILLED + ", " + 
			                          BookingDate.BOOKINGDATE_STATUS_COMPLETED + ") "; 

		if (bookingStatus == null) { // frigged for now !!!
			statusLine = "WHERE BD.STATUS IN (" + BookingDate.BOOKINGDATE_STATUS_OPEN + ", " + BookingDate.BOOKINGDATE_STATUS_OFFERED + 
																					    ", " + BookingDate.BOOKINGDATE_STATUS_FILLED + 
																					    ", " + BookingDate.BOOKINGDATE_STATUS_COMPLETED + 
																					    ", " + BookingDate.BOOKINGDATE_STATUS_CANCELLED + ") "; 
		}
		else if (bookingStatus == Booking.BOOKING_STATUS_DRAFT) {
			statusLine = "WHERE BD.STATUS = " + BookingDate.BOOKINGDATE_STATUS_DRAFT + " "; 
		}
		else if (bookingStatus == Booking.BOOKING_STATUS_OPEN) {
			statusLine = "WHERE BD.STATUS IN (" + BookingDate.BOOKINGDATE_STATUS_OPEN + ", " + BookingDate.BOOKINGDATE_STATUS_OFFERED + ") "; 
		}
		else if (bookingStatus == Booking.BOOKING_STATUS_OFFERED) {
			statusLine = "WHERE BD.STATUS = " + BookingDate.BOOKINGDATE_STATUS_OFFERED + " "; 
		}
		else if (bookingStatus == Booking.BOOKING_STATUS_CANCELLED) {
			statusLine = "WHERE BD.STATUS IN (" + BookingDate.BOOKINGDATE_STATUS_DRAFT + ", " + 
			                                      BookingDate.BOOKINGDATE_STATUS_OPEN + ", " + 
			                                      BookingDate.BOOKINGDATE_STATUS_OFFERED + ", " + 
			                                      BookingDate.BOOKINGDATE_STATUS_FILLED + ", " + 
			                                      BookingDate.BOOKINGDATE_STATUS_CANCELLED + ") "; 
		}
			
		sql = new StringBuffer(sql.toString().
				replaceFirst("/\\* extracolumns \\*/",
  							 ", " +
  							 "X.THEDATE"));

		sql = new StringBuffer(sql.toString().
				replaceFirst("/\\* extratables \\*/",
						     ", " +
							 "( " + 
							 "SELECT BD.BOOKINGID, MIN(BD.BOOKINGDATE + SX.STARTTIME) AS THEDATE " + 
							 "FROM BOOKINGDATE BD, " + 
							 "SHIFT SX " + 
							 statusLine + 
							 "AND BD.ACTIVE = TRUE " +
							 "AND SX.SHIFTID = BD.SHIFTID " +
							 "AND SX.ACTIVE = TRUE " +
							 "GROUP BY 1 " + 
							 ") X "));
		
		sql.append("AND X.BOOKINGID = B.BOOKINGID ");
		sql.append("ORDER BY X.THEDATE ");
		
		return sql;
		
	}
	
	public List<BookingUser> getBookingUsersForManagerAndWorkedStatus(Integer managerId, Integer workedStatus) {

		StringBuffer sql = new StringBuffer(selectActiveBookingUsersForManagerAndWorkedStatusSQL.toString());

		sql = addOrderBy(sql, BookingDate.BOOKINGDATE_STATUS_FILLED);
		
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);

		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, workedStatus);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUser.class.getName());

	}
		
	public List<BookingUser> getBookingUsersForManagerAndBookingGradeApplicantDateStatus(Integer managerId, Integer bookingGradeApplicantDateStatus) {

		StringBuffer sql = new StringBuffer(selectActiveBookingUsersForManagerAndBookingGradeApplicantDateStatusSQL.toString());

		// TODO - not quite right - not always FILLED !!!
		sql = addOrderBy(sql, null);
		
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);

		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
		Integer siteId = null;
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Integer locationId = null;
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		Integer jobProfileId = null;
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, jobProfileId);

		Utilities.replace(sql, bookingGradeApplicantDateStatus);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUser.class.getName());

	}
		

	public BookingUser getBookingUser(Integer bookingId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return (BookingUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUser.class.getName());
	}

	public BookingUserEntityMgr getBookingUserEntityMgr(Integer bookingId, Integer managerId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingUserForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);

		Boolean singleCandidate = null;
		
		Utilities.replace(sql, singleCandidate);
		Utilities.replace(sql, singleCandidate);
		
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
		return (BookingUserEntityMgr) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingUserEntityMgr.class.getName());
	}

	public IntValue getBookingCountNotFilled(Integer bookingId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingCountNotFilledSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IntValue.class.getName());
	}

	public IntValue getBookingCountNotAuthorized(Integer bookingId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingCountNotAuthorizedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IntValue.class.getName());
	}

	public int updateBookingCancel(Integer bookingId, String cancelText, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingCancelSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, cancelText);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
		.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingClosed(Integer bookingId, Integer noOfChanges, Integer auditorId, BigDecimal filledValue) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingClosedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, filledValue);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingFilledValue(Integer bookingId, Integer noOfChanges, Integer auditorId, BigDecimal filledValue) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingFilledValueSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, filledValue);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingWorkedValue(Integer bookingId, Integer noOfChanges, Integer auditorId, BigDecimal workedValue, BigDecimal workedNoOfHours, Integer status) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingWorkedValueSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, workedValue);
		Utilities.replace(sql, workedNoOfHours);
		Utilities.replace(sql, status);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<StatusCount> getBookingStatusCountsForManager(Integer managerId) {

		StringBuffer sql = new StringBuffer(selectBookingStatusCountsForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public List<StatusCount> getShiftStatusCountsForManager(Integer managerId) {

		StringBuffer sql = new StringBuffer(selectShiftStatusCountsForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public List<StatusCount> getBookingWorkedStatusCountsForManager(Integer managerId) {

		StringBuffer sql = new StringBuffer(selectBookingWorkedStatusCountsForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public List<StatusCount> getShiftWorkedStatusCountsForManager(Integer managerId) {

		StringBuffer sql = new StringBuffer(selectShiftWorkedStatusCountsForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public List<StatusCount> getBookingGradeApplicantDateStatusCountsForManager(Integer managerId) {

		StringBuffer sql = new StringBuffer(selectBookingGradeApplicantDateStatusCountsForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}

	public List<StatusCount> getAgencyInvoiceStatusCountsForManager(Integer clientId, Integer managerId) {

		StringBuffer sql = new StringBuffer(selectAgencyInvoiceStatusCountsForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public List<StatusCount> getAgencyInvoiceStatusCountsForAgency(Integer agencyId) {

		StringBuffer sql = new StringBuffer(selectAgencyInvoiceStatusCountsForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), StatusCount.class.getName());

	}
	
	public int updateBookingInfo(Booking booking, Integer auditorId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingInfoSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuoteNullable(sql, booking.getDuration());
		Utilities.replaceAndQuoteNullable(sql, booking.getBookingReference());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactName());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getAddress4());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactAddress().getPostalCode());
		Utilities.replaceZeroWithNull(sql, booking.getAccountContactAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, booking.getAccountContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactName());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactJobTitle());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, booking.getContactTelephoneNumber());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, booking.getBookingId());
		Utilities.replace(sql, booking.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}
	
	public int updateBookingExpensesText(Integer bookingId, String expensesText, Integer noOfChanges, Integer auditorId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingExpensesTextSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuoteNullable(sql, expensesText);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());

	}

	public IntValue getShiftsToActivateCountForManager(Integer managerId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectShiftsToActivateCountForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IntValue.class.getName());
	}
	
	public IntValue getShiftsToActivateCountForAgency(Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectShiftsToActivateCountForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IntValue.class.getName());
	}
	
	public IntValue getShiftsOutstandingCountForManager(Integer managerId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectShiftsOutstandingCountForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IntValue.class.getName());
	}
	
	public IntValue getShiftsOutstandingCountForAgency(Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectShiftsOutstandingCountForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IntValue.class.getName());
	}
	
}
