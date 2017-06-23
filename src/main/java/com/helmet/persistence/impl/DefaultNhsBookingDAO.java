package com.helmet.persistence.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.application.NhsBookingReportGroup;
import com.helmet.application.agy.AgyConstants;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.NhsBookingGroup;
import com.helmet.bean.ApplicantPaymentUpload;
import com.helmet.bean.NhsBookingUser;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.NhsBookingDAO;
import com.helmet.persistence.Utilities;

public class DefaultNhsBookingDAO extends JdbcDaoSupport implements NhsBookingDAO 
{

	private static StringBuffer insertNhsBookingSQL;

  private static StringBuffer updateNhsBookingSQL;

  private static StringBuffer updateNhsBookingCommentValueApplicantPaidDateSQL;

  private static StringBuffer updateNhsBookingApplicantNotificationSentSQL;
  
  private static StringBuffer updateNhsBookingSubcontractInvoiceIdSQL;

  private static StringBuffer deleteNhsBookingSQL;

  private static StringBuffer selectNhsBookingSQL;

  private static StringBuffer selectNhsBookingUserSQL;

  private static StringBuffer selectNhsBookingsSQL;

  private static StringBuffer selectActiveNhsBookingForBankReqNumSQL;

  private static StringBuffer selectNhsBookingForBankReqNumSQL;

  private static StringBuffer selectApplicantPaymentUploadForBankReqNumSQL;

  private static StringBuffer selectNhsBookingUsersReadyToBookSQL;

  private static StringBuffer selectNhsBookingUsersReadyToBookForBookingGroupSQL;

  private static StringBuffer selectActiveNhsBookingUsersForDateRangeSQL;

  private static StringBuffer selectNhsBookingUsersForAgencyDateRangeSQL;

  private static StringBuffer selectNhsBookingUsersRequiringValueSQL;

  private static StringBuffer selectNhsBookingUsersForAgencyDateRangeNhsBookingReportGroupSQL;
  
  private static StringBuffer selectNhsBookingUsersForSubcontractInvoiceSQL;

  private static StringBuffer selectNhsBookingGroupsReadyToBookSQL;

  private static StringBuffer selectPickedNhsBookingUsersReadyToBookSQL;

  private static StringBuffer selectNhsBookingUsersSQL;

  private static StringBuffer selectActiveNhsBookingsSQL;

  private static StringBuffer selectNhsBookingsForLocationSQL;
  
  private static StringBuffer selectActiveNhsBookingUsersSQL;

	public static void init() 
  {
		// Get insert NhsBooking SQL.
		insertNhsBookingSQL = new StringBuffer();
		insertNhsBookingSQL.append("INSERT INTO NHSBOOKING ");
		insertNhsBookingSQL.append("(  ");
    insertNhsBookingSQL.append("  NHSBOOKINGID, ");
    insertNhsBookingSQL.append("  BANKREQNUM, ");
		insertNhsBookingSQL.append("  STAFFNAME, ");
		insertNhsBookingSQL.append("  DATE, ");
    insertNhsBookingSQL.append("  STARTTIME, ");
    insertNhsBookingSQL.append("  ENDTIME, ");
    insertNhsBookingSQL.append("  LOCATION, ");
    insertNhsBookingSQL.append("  WARD, ");
    insertNhsBookingSQL.append("  ASSIGNMENT, ");
    insertNhsBookingSQL.append("  APPLICANTID, ");
    insertNhsBookingSQL.append("  SHIFTID, ");
    insertNhsBookingSQL.append("  AGENCYID, ");
    insertNhsBookingSQL.append("  CLIENTID, ");
    insertNhsBookingSQL.append("  SITEID, ");
    insertNhsBookingSQL.append("  LOCATIONID, ");
    insertNhsBookingSQL.append("  JOBPROFILEID, ");
    insertNhsBookingSQL.append("  BOOKINGGROUPID, ");
    insertNhsBookingSQL.append("  CREATIONTIMESTAMP, ");
		insertNhsBookingSQL.append("  AUDITORID, ");
		insertNhsBookingSQL.append("  AUDITTIMESTAMP ");
		insertNhsBookingSQL.append(")  ");
		insertNhsBookingSQL.append("VALUES  ");
		insertNhsBookingSQL.append("(  ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
    insertNhsBookingSQL.append("  ^, ");
		insertNhsBookingSQL.append("  ^, ");
		insertNhsBookingSQL.append("  ^, ");
		insertNhsBookingSQL.append("  ^, ");
		insertNhsBookingSQL.append("  ^, ");
		insertNhsBookingSQL.append("  ^, ");
		insertNhsBookingSQL.append("  ^, ");
		insertNhsBookingSQL.append("  ^, ");
		insertNhsBookingSQL.append("  ^ ");
		insertNhsBookingSQL.append(")  ");
		// Get update NhsBooking SQL.
		updateNhsBookingSQL = new StringBuffer();
		updateNhsBookingSQL.append("UPDATE NHSBOOKING ");
		updateNhsBookingSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateNhsBookingSQL.append("     STAFFNAME = ^, ");
    updateNhsBookingSQL.append("     DATE = ^, ");
    updateNhsBookingSQL.append("     STARTTIME = ^, ");
    updateNhsBookingSQL.append("     ENDTIME = ^, ");
    updateNhsBookingSQL.append("     LOCATION = ^, ");
    updateNhsBookingSQL.append("     WARD = ^, ");
    updateNhsBookingSQL.append("     ASSIGNMENT = ^, ");
    updateNhsBookingSQL.append("     APPLICANTID = ^, ");
    updateNhsBookingSQL.append("     SHIFTID = ^, ");
    updateNhsBookingSQL.append("     CLIENTID = ^, ");
    updateNhsBookingSQL.append("     SITEID = ^, ");
    updateNhsBookingSQL.append("     LOCATIONID = ^, ");
    updateNhsBookingSQL.append("     JOBPROFILEID = ^, ");
    updateNhsBookingSQL.append("     BOOKINGGROUPID = ^, ");
    updateNhsBookingSQL.append("     BOOKINGID = ^, ");
    updateNhsBookingSQL.append("     BOOKINGTIME = ^, ");
    updateNhsBookingSQL.append("     BOOKINGDATEID = ^, ");
    updateNhsBookingSQL.append("     BOOKINGGRADEID = ^, ");
    updateNhsBookingSQL.append("     COMMENT = ^, ");
    updateNhsBookingSQL.append("     VALUE = ^, ");
//    updateNhsBookingSQL.append("     APPLICANTNOTIFICATIONSENT = ^, ");
    updateNhsBookingSQL.append("     AUDITORID = ^, ");
		updateNhsBookingSQL.append("     AUDITTIMESTAMP = ^ ");
		updateNhsBookingSQL.append("WHERE NHSBOOKINGID = ^ ");
		updateNhsBookingSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update NhsBooking Applicant Notification Sent SQL.
    updateNhsBookingApplicantNotificationSentSQL = new StringBuffer();
    updateNhsBookingApplicantNotificationSentSQL.append("UPDATE NHSBOOKING ");
    updateNhsBookingApplicantNotificationSentSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateNhsBookingApplicantNotificationSentSQL.append("     APPLICANTNOTIFICATIONSENT = ^, ");
    updateNhsBookingApplicantNotificationSentSQL.append("     AUDITORID = ^, ");
    updateNhsBookingApplicantNotificationSentSQL.append("     AUDITTIMESTAMP = ^ ");
    updateNhsBookingApplicantNotificationSentSQL.append("WHERE NHSBOOKINGID = ^ ");
    updateNhsBookingApplicantNotificationSentSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update NhsBooking Comment and Value Id SQL.
    updateNhsBookingCommentValueApplicantPaidDateSQL = new StringBuffer();
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("UPDATE NHSBOOKING ");
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("     COMMENT = ^, ");
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("     VALUE = ^, ");
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("     APPLICANTPAIDDATE = ^, ");
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("     AUDITORID = ^, ");
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("     AUDITTIMESTAMP = ^ ");
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("WHERE NHSBOOKINGID = ^ ");
    updateNhsBookingCommentValueApplicantPaidDateSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update NhsBooking Subcontract Invoice Id SQL.
    updateNhsBookingSubcontractInvoiceIdSQL = new StringBuffer();
    updateNhsBookingSubcontractInvoiceIdSQL.append("UPDATE NHSBOOKING ");
    updateNhsBookingSubcontractInvoiceIdSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateNhsBookingSubcontractInvoiceIdSQL.append("     SUBCONTRACTINVOICEID = ^, ");
    updateNhsBookingSubcontractInvoiceIdSQL.append("     AUDITORID = ^, ");
    updateNhsBookingSubcontractInvoiceIdSQL.append("     AUDITTIMESTAMP = ^ ");
    updateNhsBookingSubcontractInvoiceIdSQL.append("WHERE NHSBOOKINGID = ^ ");
    updateNhsBookingSubcontractInvoiceIdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete NhsBooking SQL.
		deleteNhsBookingSQL = new StringBuffer();
		deleteNhsBookingSQL.append("UPDATE NHSBOOKING ");
		deleteNhsBookingSQL.append("SET ACTIVE = FALSE, ");
    deleteNhsBookingSQL.append("    COMMENT = ^, ");
    deleteNhsBookingSQL.append("    AUDITORID = ^, ");
		deleteNhsBookingSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteNhsBookingSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteNhsBookingSQL.append("WHERE NHSBOOKINGID = ^ ");
		deleteNhsBookingSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select NhsBookings SQL.
		selectNhsBookingsSQL = new StringBuffer();
		selectNhsBookingsSQL.append("SELECT NB.NHSBOOKINGID, ");
		selectNhsBookingsSQL.append("       NB.BANKREQNUM, ");
		selectNhsBookingsSQL.append("       NB.STAFFNAME, ");
		selectNhsBookingsSQL.append("       NB.DATE, ");
    selectNhsBookingsSQL.append("       NB.STARTTIME, ");
    selectNhsBookingsSQL.append("       NB.ENDTIME, ");
    selectNhsBookingsSQL.append("       NB.LOCATION, ");
    selectNhsBookingsSQL.append("       NB.WARD, ");
    selectNhsBookingsSQL.append("       NB.ASSIGNMENT, ");
    selectNhsBookingsSQL.append("       NB.SUBCONTRACTINVOICEID, ");
    selectNhsBookingsSQL.append("       NB.VALUE, ");
    selectNhsBookingsSQL.append("       NB.APPLICANTID, ");
    selectNhsBookingsSQL.append("       NB.SHIFTID, ");
    selectNhsBookingsSQL.append("       NB.AGENCYID, ");
    selectNhsBookingsSQL.append("       NB.CLIENTID, ");
    selectNhsBookingsSQL.append("       NB.SITEID, ");
    selectNhsBookingsSQL.append("       NB.LOCATIONID, ");
    selectNhsBookingsSQL.append("       NB.JOBPROFILEID, ");
    selectNhsBookingsSQL.append("       NB.BOOKINGGROUPID, ");
    selectNhsBookingsSQL.append("       NB.BOOKINGID, ");
    selectNhsBookingsSQL.append("       NB.BOOKINGTIME, ");
    selectNhsBookingsSQL.append("       NB.BOOKINGDATEID, ");
    selectNhsBookingsSQL.append("       NB.BOOKINGGRADEID, ");
    selectNhsBookingsSQL.append("       NB.COMMENT, ");
    selectNhsBookingsSQL.append("       NB.APPLICANTPAIDDATE, ");
    selectNhsBookingsSQL.append("       NB.APPLICANTNOTIFICATIONSENT, ");
    selectNhsBookingsSQL.append("       NB.CREATIONTIMESTAMP, ");
		selectNhsBookingsSQL.append("       NB.AUDITORID, ");
		selectNhsBookingsSQL.append("       NB.AUDITTIMESTAMP, ");
		selectNhsBookingsSQL.append("       NB.ACTIVE, ");
		selectNhsBookingsSQL.append("       NB.NOOFCHANGES ");
    // Get select NhsBookingUsers SQL.
    selectNhsBookingUsersSQL = new StringBuffer(selectNhsBookingsSQL);
    selectNhsBookingUsersSQL.append(", ");
    selectNhsBookingUsersSQL.append("       A.FIRSTNAME AS APPLICANTFIRSTNAME, ");
    selectNhsBookingUsersSQL.append("       A.LASTNAME AS APPLICANTLASTNAME, ");
    selectNhsBookingUsersSQL.append("       A.EMAILADDRESS AS APPLICANTEMAILADDRESS, ");
    selectNhsBookingUsersSQL.append("       A.ORIGINALAGENCYID AS APPLICANTORIGINALAGENCYID, "); 
    selectNhsBookingUsersSQL.append("       C.NAME AS CLIENTNAME, ");
    selectNhsBookingUsersSQL.append("       S.NAME AS SITENAME, ");
    selectNhsBookingUsersSQL.append("       JP.NAME AS JOBPROFILENAME, ");
    selectNhsBookingUsersSQL.append("       JP.CODE AS JOBPROFILECODE, ");
    selectNhsBookingUsersSQL.append("       JSF.NAME AS JOBSUBFAMILYNAME, ");
    selectNhsBookingUsersSQL.append("       JSF.CODE AS JOBSUBFAMILYCODE, ");
    selectNhsBookingUsersSQL.append("       JF.NAME AS JOBFAMILYNAME, ");
    selectNhsBookingUsersSQL.append("       JF.CODE AS JOBFAMILYCODE, ");
    selectNhsBookingUsersSQL.append("       L.NAME AS LOCATIONNAME, ");
    selectNhsBookingUsersSQL.append("       SHIFT.NAME AS SHIFTNAME, ");
    selectNhsBookingUsersSQL.append("       BD.WORKEDSTARTTIME, ");
    selectNhsBookingUsersSQL.append("       BD.WORKEDENDTIME, ");
    selectNhsBookingUsersSQL.append("       BD.WORKEDBREAKSTARTTIME, ");
    selectNhsBookingUsersSQL.append("       BD.WORKEDBREAKENDTIME, ");
    selectNhsBookingUsersSQL.append("       BD.WORKEDNOOFHOURS, ");
    selectNhsBookingUsersSQL.append("       BD.WORKEDBREAKNOOFHOURS, ");
    selectNhsBookingUsersSQL.append("       BD.BACKINGREPORT, ");
    selectNhsBookingUsersSQL.append("       SI.SENTDATE AS SUBCONTRACTINVOICESENTDATE, ");
    selectNhsBookingUsersSQL.append("       SI.PAIDDATE AS SUBCONTRACTINVOICEPAIDDATE, ");
// WAS    selectNhsBookingUsersSQL.append("       BG.RATE ");
    selectNhsBookingUsersSQL.append("       B.RATE ");
    selectNhsBookingUsersSQL.append("FROM NHSBOOKING NB ");
    selectNhsBookingUsersSQL.append("JOIN APPLICANT A ");
    selectNhsBookingUsersSQL.append("ON  A.APPLICANTID = NB.APPLICANTID ");
    selectNhsBookingUsersSQL.append("JOIN CLIENT C ");
    selectNhsBookingUsersSQL.append("ON  C.CLIENTID = NB.CLIENTID ");
    selectNhsBookingUsersSQL.append("JOIN SITE S ");
    selectNhsBookingUsersSQL.append("ON  S.SITEID = NB.SITEID ");
    selectNhsBookingUsersSQL.append("JOIN JOBPROFILE JP ");
    selectNhsBookingUsersSQL.append("ON  JP.JOBPROFILEID = NB.JOBPROFILEID ");
    selectNhsBookingUsersSQL.append("JOIN JOBSUBFAMILY JSF ");
    selectNhsBookingUsersSQL.append("ON  JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
    selectNhsBookingUsersSQL.append("JOIN JOBFAMILY JF ");
    selectNhsBookingUsersSQL.append("ON  JF.JOBFAMILYID = JSF.JOBFAMILYID ");
    selectNhsBookingUsersSQL.append("JOIN LOCATION L ");
    selectNhsBookingUsersSQL.append("ON  L.LOCATIONID = NB.LOCATIONID ");
    selectNhsBookingUsersSQL.append("JOIN SHIFT ");
    selectNhsBookingUsersSQL.append("ON  SHIFT.SHIFTID = NB.SHIFTID ");
    selectNhsBookingUsersSQL.append("LEFT OUTER JOIN BOOKINGDATE BD ");
    selectNhsBookingUsersSQL.append("ON  BD.BOOKINGDATEID = NB.BOOKINGDATEID ");
// WAS    selectNhsBookingUsersSQL.append("LEFT OUTER JOIN BOOKINGGRADE BG ");
    selectNhsBookingUsersSQL.append("LEFT OUTER JOIN BOOKING B ");
// WAS    selectNhsBookingUsersSQL.append("ON  BG.BOOKINGGRADEID = NB.BOOKINGGRADEID ");
    selectNhsBookingUsersSQL.append("ON  B.BOOKINGID = NB.BOOKINGID ");
    selectNhsBookingUsersSQL.append("LEFT OUTER JOIN SUBCONTRACTINVOICE SI ");
    selectNhsBookingUsersSQL.append("ON  SI.SUBCONTRACTINVOICEID = NB.SUBCONTRACTINVOICEID ");

    // Get select ApplicantPaymentUpload for Bank Request Number SQL.
    selectApplicantPaymentUploadForBankReqNumSQL = new StringBuffer(selectNhsBookingsSQL);
    selectApplicantPaymentUploadForBankReqNumSQL.append(", ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("       BD.WORKEDSTARTTIME, ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("       BD.WORKEDENDTIME, ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("       BD.WORKEDBREAKSTARTTIME, ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("       BD.WORKEDBREAKENDTIME, ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("       BD.WORKEDNOOFHOURS, ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("       BD.WORKEDBREAKNOOFHOURS, ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("       BD.BACKINGREPORT ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("FROM NHSBOOKING NB ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("LEFT OUTER JOIN BOOKINGDATE BD ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("ON  BD.BOOKINGDATEID = NB.BOOKINGDATEID ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("WHERE NB.BANKREQNUM = ^ ");
    selectApplicantPaymentUploadForBankReqNumSQL.append("AND NB.AGENCYID = ^ ");

    // Add FROM to Get NhsBookings SQL.
    selectNhsBookingsSQL.append("FROM NHSBOOKING NB ");
    // Get select NhsBooking for Bank Request Number SQL.
    selectNhsBookingForBankReqNumSQL = new StringBuffer(selectNhsBookingsSQL);
    selectNhsBookingForBankReqNumSQL.append("WHERE NB.BANKREQNUM = ^ ");
    selectNhsBookingForBankReqNumSQL.append("AND NB.AGENCYID = ^ ");
    // Get select Active NhsBookings SQL.
		selectActiveNhsBookingsSQL = new StringBuffer(selectNhsBookingsSQL);
    selectActiveNhsBookingsSQL.append("WHERE NB.AGENCYID = ^ ");
    selectActiveNhsBookingsSQL.append("AND NB.ACTIVE = TRUE ");
    // Get select Active NhsBookings for Location SQL.
    selectNhsBookingsForLocationSQL = new StringBuffer(selectActiveNhsBookingsSQL);
    selectNhsBookingsForLocationSQL.append("AND NB.LOCATIONID = ^ ");
    // Get select Active NhsBooking for Bank Request Number SQL.
    selectActiveNhsBookingForBankReqNumSQL = new StringBuffer(selectNhsBookingsSQL);
    selectActiveNhsBookingForBankReqNumSQL.append("WHERE NB.BANKREQNUM = ^ ");
    selectActiveNhsBookingForBankReqNumSQL.append("AND NB.AGENCYID = ^ ");
    selectActiveNhsBookingForBankReqNumSQL.append("AND NB.ACTIVE = TRUE ");
    // Get select Active NhsBookingUsers for Uplift SQL.
    selectActiveNhsBookingUsersSQL = new StringBuffer(selectNhsBookingUsersSQL);
    selectActiveNhsBookingUsersSQL.append("WHERE NB.AGENCYID = ^ ");
    selectActiveNhsBookingUsersSQL.append("AND NB.ACTIVE = TRUE ");
    // Get select Active NhsBookings Ready to Book SQL.
    selectNhsBookingUsersReadyToBookSQL = new StringBuffer(selectActiveNhsBookingUsersSQL);
    selectNhsBookingUsersReadyToBookSQL.append("AND NB.APPLICANTID IS NOT NULL ");
    selectNhsBookingUsersReadyToBookSQL.append("AND NB.SITEID IS NOT NULL ");
    selectNhsBookingUsersReadyToBookSQL.append("AND NB.LOCATIONID IS NOT NULL ");
    selectNhsBookingUsersReadyToBookSQL.append("AND NB.JOBPROFILEID IS NOT NULL ");
    selectNhsBookingUsersReadyToBookSQL.append("AND NB.SHIFTID IS NOT NULL ");
    selectNhsBookingUsersReadyToBookSQL.append("AND NB.BOOKINGID IS NULL ");
    //
    // Get select Active NhsBookings for Date Range SQL.
    selectActiveNhsBookingUsersForDateRangeSQL = new StringBuffer(selectActiveNhsBookingUsersSQL);
    selectActiveNhsBookingUsersForDateRangeSQL.append("AND NB.DATE BETWEEN ^ AND ^ ");
    // Get select NhsBookings for Agency and Date Range SQL.
    selectNhsBookingUsersForAgencyDateRangeSQL = new StringBuffer(selectNhsBookingUsersSQL);
    selectNhsBookingUsersForAgencyDateRangeSQL.append("WHERE NB.AGENCYID = ^ ");
    selectNhsBookingUsersForAgencyDateRangeSQL.append("AND NB.DATE BETWEEN ^ AND ^ ");
    // Get select NhsBookingUsers Ready To Invoice (for Agency and Date Range) SQL.
    selectNhsBookingUsersRequiringValueSQL = new StringBuffer(selectNhsBookingUsersForAgencyDateRangeSQL);
    selectNhsBookingUsersRequiringValueSQL.append("AND NB.ACTIVE = TRUE ");
    selectNhsBookingUsersRequiringValueSQL.append("AND NB.VALUE = 0 ");
    selectNhsBookingUsersRequiringValueSQL.append("AND NB.SUBCONTRACTINVOICEID IS NULL ");
    selectNhsBookingUsersRequiringValueSQL.append("AND BD.WORKEDNOOFHOURS IS NOT NULL ");
    // Get select NhsBookings for Subcontract Invoice Range SQL.
    selectNhsBookingUsersForSubcontractInvoiceSQL = new StringBuffer(selectNhsBookingUsersSQL);
    selectNhsBookingUsersForSubcontractInvoiceSQL.append("WHERE NB.AGENCYID = ^ ");
    selectNhsBookingUsersForSubcontractInvoiceSQL.append("AND NB.SUBCONTRACTINVOICEID = ^ ");
    // Get select NhsBookings for Agency and Date Range and NHS Booking Report Group SQL.
    selectNhsBookingUsersForAgencyDateRangeNhsBookingReportGroupSQL = new StringBuffer(selectNhsBookingUsersForAgencyDateRangeSQL);
    selectNhsBookingUsersForAgencyDateRangeNhsBookingReportGroupSQL.append("AND NB.CLIENTID = ^ ");
    selectNhsBookingUsersForAgencyDateRangeNhsBookingReportGroupSQL.append("AND NB.SITEID = ^ ");
    selectNhsBookingUsersForAgencyDateRangeNhsBookingReportGroupSQL.append("AND NB.LOCATIONID = ^ ");
    selectNhsBookingUsersForAgencyDateRangeNhsBookingReportGroupSQL.append("AND NB.JOBPROFILEID = ^ ");
    // Get select Active NhsBookings Ready to Book for Booking Group (Site, Location, Job Profile and Booking Group) SQL.
    selectNhsBookingUsersReadyToBookForBookingGroupSQL = new StringBuffer(selectActiveNhsBookingUsersSQL);
    selectNhsBookingUsersReadyToBookForBookingGroupSQL.append("AND NB.APPLICANTID IS NOT NULL ");
    selectNhsBookingUsersReadyToBookForBookingGroupSQL.append("AND NB.SITEID = ^ ");
    selectNhsBookingUsersReadyToBookForBookingGroupSQL.append("AND NB.LOCATIONID = ^ ");
    selectNhsBookingUsersReadyToBookForBookingGroupSQL.append("AND NB.JOBPROFILEID = ^ ");
    selectNhsBookingUsersReadyToBookForBookingGroupSQL.append("AND NB.BOOKINGGROUPID = ^ ");
    selectNhsBookingUsersReadyToBookForBookingGroupSQL.append("AND NB.BOOKINGID IS NULL ");
    //
    selectPickedNhsBookingUsersReadyToBookSQL = new StringBuffer(selectNhsBookingUsersReadyToBookSQL);
    selectPickedNhsBookingUsersReadyToBookSQL.append("AND NB.NHSBOOKINGID IN (^) ");
    
    // Get select NhsBooking SQL.
    selectNhsBookingSQL = new StringBuffer(selectNhsBookingsSQL);
    selectNhsBookingSQL.append("WHERE NB.NHSBOOKINGID = ^ ");
    // Get select NhsBookingUser SQL.
    selectNhsBookingUserSQL = new StringBuffer(selectNhsBookingUsersSQL);
    selectNhsBookingUserSQL.append("WHERE NB.NHSBOOKINGID = ^ ");
		// ORDER BY 
    selectNhsBookingsSQL.append("ORDER BY NB.BANKREQNUM ");
    selectActiveNhsBookingsSQL.append("ORDER BY NB.BANKREQNUM ");
    selectNhsBookingUsersSQL.append("ORDER BY NB.BANKREQNUM ");
    selectActiveNhsBookingUsersForDateRangeSQL.append("ORDER BY CLIENTNAME, SITENAME, LOCATIONNAME, JOBPROFILENAME, JOBFAMILYCODE, JOBSUBFAMILYCODE, NB.DATE, NB.STARTTIME ");
    selectNhsBookingUsersForAgencyDateRangeSQL.append("ORDER BY CLIENTNAME, SITENAME, LOCATIONNAME, JOBPROFILENAME, JOBFAMILYCODE, JOBSUBFAMILYCODE, NB.DATE, NB.STARTTIME ");
    selectNhsBookingUsersRequiringValueSQL.append("ORDER BY CLIENTNAME, SITENAME, LOCATIONNAME, JOBPROFILENAME, JOBFAMILYCODE, JOBSUBFAMILYCODE, NB.DATE, NB.STARTTIME ");
    selectNhsBookingUsersForAgencyDateRangeNhsBookingReportGroupSQL.append("ORDER BY NB.DATE, NB.STARTTIME ");
    selectNhsBookingUsersForSubcontractInvoiceSQL.append("ORDER BY NB.DATE, NB.STARTTIME ");
    selectActiveNhsBookingUsersSQL.append("ORDER BY NB.BANKREQNUM ");
    selectNhsBookingUsersReadyToBookSQL.append("ORDER BY CLIENTNAME, SITENAME, LOCATIONNAME, JOBPROFILENAME, JOBFAMILYCODE, JOBSUBFAMILYCODE, NB.DATE, NB.STARTTIME ");
    selectNhsBookingUsersReadyToBookForBookingGroupSQL.append("ORDER BY CLIENTNAME, SITENAME, LOCATIONNAME, JOBPROFILENAME, JOBFAMILYCODE, JOBSUBFAMILYCODE, NB.DATE, NB.STARTTIME ");
    selectPickedNhsBookingUsersReadyToBookSQL.append("ORDER BY CLIENTNAME, SITENAME, LOCATIONNAME, JOBPROFILENAME, JOBFAMILYCODE, JOBSUBFAMILYCODE, NB.DATE, NB.STARTTIME ");
    // Get select NhsBookingGroup SQL.
    selectNhsBookingGroupsReadyToBookSQL = new StringBuffer();
    selectNhsBookingGroupsReadyToBookSQL.append("SELECT C.NAME AS CLIENTNAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       C.CLIENTID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       S.NAME AS SITENAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       S.SITEID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       L.NAME AS LOCATIONNAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       L.LOCATIONID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       NB.ASSIGNMENT,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       JF.CODE AS JOBFAMILYCODE,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       JSF.CODE AS JOBSUBFAMILYCODE,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       JP.NAME AS JOBPROFILENAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       JP.JOBPROFILEID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       NB.BOOKINGGROUPID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       COUNT(NB.NHSBOOKINGID) AS NOOFBOOKINGS,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       MIN(NB.DATE) AS EARLIESTDATE,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       MAX(NB.DATE) AS LATESTDATE,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       MIN(NB.STARTTIME) AS EARLIESTSTARTTIME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("       MAX(NB.ENDTIME) AS LATESTENDTIME ");
    selectNhsBookingGroupsReadyToBookSQL.append("FROM NHSBOOKING NB ");
    selectNhsBookingGroupsReadyToBookSQL.append("JOIN CLIENT C ON ");
    selectNhsBookingGroupsReadyToBookSQL.append("C.CLIENTID = NB.CLIENTID ");
    selectNhsBookingGroupsReadyToBookSQL.append("JOIN SITE S ON ");
    selectNhsBookingGroupsReadyToBookSQL.append("S.SITEID = NB.SITEID ");
    selectNhsBookingGroupsReadyToBookSQL.append("JOIN LOCATION L ON ");
    selectNhsBookingGroupsReadyToBookSQL.append("L.LOCATIONID = NB.LOCATIONID ");
    selectNhsBookingGroupsReadyToBookSQL.append("JOIN JOBPROFILE JP ");
    selectNhsBookingGroupsReadyToBookSQL.append("ON JP.JOBPROFILEID = NB.JOBPROFILEID ");
    selectNhsBookingGroupsReadyToBookSQL.append("JOIN JOBSUBFAMILY JSF ");
    selectNhsBookingGroupsReadyToBookSQL.append("ON JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
    selectNhsBookingGroupsReadyToBookSQL.append("JOIN JOBFAMILY JF  ");
    selectNhsBookingGroupsReadyToBookSQL.append("ON JF.JOBFAMILYID = JSF.JOBFAMILYID ");
    selectNhsBookingGroupsReadyToBookSQL.append("WHERE NB.AGENCYID = ^ ");
    selectNhsBookingGroupsReadyToBookSQL.append("AND NB.ACTIVE = TRUE ");
    selectNhsBookingGroupsReadyToBookSQL.append("AND NB.APPLICANTID IS NOT NULL ");
    selectNhsBookingGroupsReadyToBookSQL.append("AND NB.SITEID IS NOT NULL ");
    selectNhsBookingGroupsReadyToBookSQL.append("AND NB.LOCATIONID IS NOT NULL ");
    selectNhsBookingGroupsReadyToBookSQL.append("AND NB.JOBPROFILEID IS NOT NULL ");
    selectNhsBookingGroupsReadyToBookSQL.append("AND NB.SHIFTID IS NOT NULL ");
    selectNhsBookingGroupsReadyToBookSQL.append("AND NB.BOOKINGID IS NULL ");
    selectNhsBookingGroupsReadyToBookSQL.append("GROUP BY C.NAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         C.CLIENTID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         S.NAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         S.SITEID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         L.NAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         L.LOCATIONID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         NB.ASSIGNMENT,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         JF.CODE,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         JSF.CODE,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         JP.NAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         JP.JOBPROFILEID,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         NB.BOOKINGGROUPID ");
    selectNhsBookingGroupsReadyToBookSQL.append("ORDER BY C.NAME, ");
    selectNhsBookingGroupsReadyToBookSQL.append("         S.NAME, ");
    selectNhsBookingGroupsReadyToBookSQL.append("         L.NAME,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         JF.CODE,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         JSF.CODE,  ");
    selectNhsBookingGroupsReadyToBookSQL.append("         JP.NAME  ");

	}

	public int insertNhsBooking(NhsBooking nhsBooking, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertNhsBookingSQL.toString());
		// Replace the parameters with supplied values.
		nhsBooking.setNhsBookingId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "nhsBooking"));
		Utilities.replace(sql, nhsBooking.getNhsBookingId());
		Utilities.replaceAndQuote(sql, nhsBooking.getBankReqNum());
		Utilities.replaceAndQuote(sql, nhsBooking.getStaffName());
		Utilities.replaceAndQuote(sql, nhsBooking.getDate());
    Utilities.replaceAndQuote(sql, nhsBooking.getStartTime());
    Utilities.replaceAndQuote(sql, nhsBooking.getEndTime());
    Utilities.replaceAndQuote(sql, nhsBooking.getLocation());
    Utilities.replaceAndQuote(sql, nhsBooking.getWard());
    Utilities.replaceAndQuote(sql, nhsBooking.getAssignment());
    Utilities.replace(sql, nhsBooking.getApplicantId());
    Utilities.replace(sql, nhsBooking.getShiftId());
    Utilities.replace(sql, nhsBooking.getAgencyId());
    Utilities.replace(sql, nhsBooking.getClientId());
    Utilities.replace(sql, nhsBooking.getSiteId());
    Utilities.replace(sql, nhsBooking.getLocationId());
    Utilities.replace(sql, nhsBooking.getJobProfileId());
    Utilities.replace(sql, nhsBooking.getBookingGroupId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateNhsBooking(NhsBooking nhsBooking, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateNhsBookingSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, nhsBooking.getStaffName());
    Utilities.replaceAndQuote(sql, nhsBooking.getDate());
    Utilities.replaceAndQuote(sql, nhsBooking.getStartTime());
    Utilities.replaceAndQuote(sql, nhsBooking.getEndTime());
    Utilities.replaceAndQuote(sql, nhsBooking.getLocation());
    Utilities.replaceAndQuote(sql, nhsBooking.getWard());
    Utilities.replaceAndQuote(sql, nhsBooking.getAssignment());
    Utilities.replace(sql, nhsBooking.getApplicantId());
    Utilities.replace(sql, nhsBooking.getShiftId());
    Utilities.replace(sql, nhsBooking.getClientId());
    Utilities.replace(sql, nhsBooking.getSiteId());
    Utilities.replace(sql, nhsBooking.getLocationId());
    Utilities.replace(sql, nhsBooking.getJobProfileId());
    Utilities.replace(sql, nhsBooking.getBookingGroupId());
    Utilities.replace(sql, nhsBooking.getBookingId());
    Utilities.replaceAndQuoteNullable(sql, nhsBooking.getBookingTime());
    Utilities.replace(sql, nhsBooking.getBookingDateId());
    Utilities.replace(sql, nhsBooking.getBookingGradeId());
    Utilities.replaceAndQuoteNullable(sql, nhsBooking.getComment());
    Utilities.replace(sql, nhsBooking.getValue() == null ? new BigDecimal(0) : nhsBooking.getValue());
//    Utilities.replaceAndQuoteNullable(sql, nhsBooking.getApplicantNotificationSent());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, nhsBooking.getNhsBookingId());
		Utilities.replace(sql, nhsBooking.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateNhsBookingApplicantNotificationSent(NhsBooking nhsBooking, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateNhsBookingApplicantNotificationSentSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuoteNullable(sql, nhsBooking.getApplicantNotificationSent());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, nhsBooking.getNhsBookingId());
    Utilities.replace(sql, nhsBooking.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int updateNhsBookingCommentValueApplicantPaidDate(NhsBooking nhsBooking, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateNhsBookingCommentValueApplicantPaidDateSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuoteNullable(sql, nhsBooking.getComment());
    Utilities.replace(sql, nhsBooking.getValue() == null ? new BigDecimal(0) : nhsBooking.getValue());
    Utilities.replaceAndQuoteNullable(sql, nhsBooking.getApplicantPaidDate());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, nhsBooking.getNhsBookingId());
    Utilities.replace(sql, nhsBooking.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

  public int updateNhsBookingSubcontractInvoiceId(NhsBooking nhsBooking, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateNhsBookingSubcontractInvoiceIdSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceZeroWithNull(sql, nhsBooking.getSubcontractInvoiceId());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, nhsBooking.getNhsBookingId());
    Utilities.replace(sql, nhsBooking.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteNhsBooking(NhsBooking nhsBooking, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteNhsBookingSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replaceAndQuoteNullable(sql, nhsBooking.getComment());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, nhsBooking.getNhsBookingId());
		Utilities.replace(sql, nhsBooking.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public NhsBooking getNhsBooking(Integer nhsBookingId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBookingSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, nhsBookingId);
    return (NhsBooking) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBooking.class.getName());
  }

  public NhsBookingUser getNhsBookingUser(Integer nhsBookingId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBookingUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, nhsBookingId);
    return (NhsBookingUser) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }

  public NhsBooking getActiveNhsBookingForBankReqNum(Integer agencyId, String bankReqNum) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectActiveNhsBookingForBankReqNumSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, bankReqNum);
    Utilities.replace(sql, agencyId);
    return (NhsBooking)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBooking.class.getName());
  }

  public NhsBooking getNhsBookingForBankReqNum(Integer agencyId, String bankReqNum) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBookingForBankReqNumSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, bankReqNum);
    Utilities.replace(sql, agencyId);
    return (NhsBooking)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBooking.class.getName());
  }

  public ApplicantPaymentUpload getApplicantPaymentUploadForBankReqNum(Integer agencyId, String bankReqNum) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectApplicantPaymentUploadForBankReqNumSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, bankReqNum);
    Utilities.replace(sql, agencyId);
    return (ApplicantPaymentUpload)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ApplicantPaymentUpload.class.getName());
  }

  public List<NhsBookingUser> getNhsBookingUsersReadyToBook(Integer agencyId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBookingUsersReadyToBookSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }

  public List<NhsBookingUser> getActiveNhsBookingUsersForDateRange(Integer agencyId, Date startDate, Date endDate) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectActiveNhsBookingUsersForDateRangeSQL.toString());
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, startDate);
    Utilities.replaceAndQuote(sql, endDate);
    // Replace the parameters with supplied values.
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }

  public List<NhsBookingUser> getNhsBookingUsersForAgencyDateRange(Integer agencyId, Date startDate, Date endDate, String filter) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = null;
    if (filter.equals(AgyConstants.NO_FILTER))
    {
      sql = new StringBuffer(selectNhsBookingUsersForAgencyDateRangeSQL.toString());
    }
    else
    {
      sql = new StringBuffer(selectNhsBookingUsersRequiringValueSQL.toString());
    }
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, startDate);
    Utilities.replaceAndQuote(sql, endDate);
    // Replace the parameters with supplied values.
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }

  public List<NhsBookingUser> getNhsBookingUsersForAgencyDateRangeNhsBookingReportGroup(Integer agencyId, Date startDate, Date endDate, NhsBookingReportGroup nhsBookingReportGroup) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBookingUsersForAgencyDateRangeNhsBookingReportGroupSQL.toString());
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, startDate);
    Utilities.replaceAndQuote(sql, endDate);
    Utilities.replace(sql, nhsBookingReportGroup.getClientId());
    Utilities.replace(sql, nhsBookingReportGroup.getSiteId());
    Utilities.replace(sql, nhsBookingReportGroup.getLocationId());
    Utilities.replace(sql, nhsBookingReportGroup.getJobProfileId());
    // Replace the parameters with supplied values.
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }

  public List<NhsBookingUser> getNhsBookingUsersForSubcontractInvoice(Integer agencyId, Integer subcontractInvoiceId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBookingUsersForSubcontractInvoiceSQL.toString());
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, subcontractInvoiceId);
    // Replace the parameters with supplied values.
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }
  public List<NhsBookingUser> getNhsBookingUsersReadyToBookForBookingGroup(Integer agencyId, Integer siteId, Integer locationId, Integer jobProfileId, Integer bookingGroupId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBookingUsersReadyToBookForBookingGroupSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, siteId);
    Utilities.replace(sql, locationId);
    Utilities.replace(sql, jobProfileId);
    Utilities.replace(sql, bookingGroupId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }

  public List<NhsBookingGroup> getNhsBookingGroupsReadyToBook(Integer agencyId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBookingGroupsReadyToBookSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingGroup.class.getName());
  }

  public List<NhsBookingUser> getPickedNhsBookingUsersReadyToBook(Integer agencyId, String nhsBookingIds) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectPickedNhsBookingUsersReadyToBookSQL.toString());
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, nhsBookingIds);
    // Replace the parameters with supplied values.
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }

	public List<NhsBooking> getNhsBookings(Integer agencyId, boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveNhsBookingsSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectNhsBookingsSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBooking.class.getName());
	}

  public List<NhsBooking> getNhsBookingsForLocation(Integer agencyId, Integer locationId) 
  {
    StringBuffer sql = new StringBuffer(selectNhsBookingsForLocationSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, locationId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBooking.class.getName());
  }

  public List<NhsBookingUser> getNhsBookingUsers(Integer agencyId, boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveNhsBookingUsersSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectNhsBookingUsersSQL.toString()); 
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBookingUser.class.getName());
  }

}
