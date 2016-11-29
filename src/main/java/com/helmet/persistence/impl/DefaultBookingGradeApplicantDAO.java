package com.helmet.persistence.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.BookingGradeApplicant;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.IntValue;
import com.helmet.persistence.BookingGradeApplicantDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBookingGradeApplicantDAO extends JdbcDaoSupport implements BookingGradeApplicantDAO {

	private static StringBuffer insertBookingGradeApplicantSQL;

	private static StringBuffer updateBookingGradeApplicantSQL;

	private static StringBuffer updateBookingGradeApplicantStatusSQL;

	private static StringBuffer updateBookingGradeApplicantRejectSQL;

	private static StringBuffer updateBookingGradeApplicantRenegotiateSQL;

	private static StringBuffer updateBookingGradeApplicantActivatedSQL;
  
  private static StringBuffer updateBookingGradeApplicantChecklistCreatedTimeSQL;

  private static StringBuffer selectBookingGradeApplicantUserSQL;
  
  private static StringBuffer selectBookingGradeApplicantUser2SQL;

	private static StringBuffer selectBookingGradeApplicantUsersSQL;

	private static StringBuffer selectBookingGradeApplicantUsersForBookingGradeSQL;

	private static StringBuffer selectBookingGradeApplicantUsersForBookingGradeAndAgencySQL;

	private static StringBuffer selectActiveBookingGradeApplicantUsersForBookingGradeAndAgencySQL;

	private static StringBuffer selectBookingGradeApplicantUsersForBookingSQL;
	
	private static StringBuffer selectBookingGradeApplicantUserForBookingFilledSingleCandidateSQL;

	private static StringBuffer selectBookingGradeApplicantUsersForBookingAndClientSQL;

	private static StringBuffer selectActiveBookingGradeApplicantUsersForBookingAndClientSQL;

  private static StringBuffer selectBookingExpensesCountSQL;

  private static StringBuffer selectActiveBookingGradeApplicantsCountForClientAgencyJobProfileGradeSQL;

	public static void init() {
		// Get insert BookingGradeApplicant SQL.
		insertBookingGradeApplicantSQL = new StringBuffer();
		insertBookingGradeApplicantSQL.append("INSERT INTO BOOKINGGRADEAPPLICANT ");
		insertBookingGradeApplicantSQL.append("(  ");
		insertBookingGradeApplicantSQL.append("  BOOKINGGRADEAPPLICANTID, ");
		insertBookingGradeApplicantSQL.append("  BOOKINGGRADEID, ");
		insertBookingGradeApplicantSQL.append("  APPLICANTID, ");
		insertBookingGradeApplicantSQL.append("  CLIENTAGENCYJOBPROFILEGRADEID, ");
		insertBookingGradeApplicantSQL.append("  FILENAME, ");
		insertBookingGradeApplicantSQL.append("  RATE, ");
		insertBookingGradeApplicantSQL.append("  PAYRATE, ");
		insertBookingGradeApplicantSQL.append("  WTDPERCENTAGE, ");
		insertBookingGradeApplicantSQL.append("  NIPERCENTAGE, ");
		insertBookingGradeApplicantSQL.append("  WAGERATE, ");
		insertBookingGradeApplicantSQL.append("  CHARGERATEVATRATE, ");
		insertBookingGradeApplicantSQL.append("  COMMISSIONVATRATE, ");
		insertBookingGradeApplicantSQL.append("  PAYRATEVATRATE, ");
		insertBookingGradeApplicantSQL.append("  WTDVATRATE, ");
		insertBookingGradeApplicantSQL.append("  NIVATRATE, ");
		insertBookingGradeApplicantSQL.append("  CREATIONTIMESTAMP, ");
		insertBookingGradeApplicantSQL.append("  AUDITORID, ");
		insertBookingGradeApplicantSQL.append("  AUDITTIMESTAMP ");
		insertBookingGradeApplicantSQL.append(")  ");
		insertBookingGradeApplicantSQL.append("VALUES  ");
		insertBookingGradeApplicantSQL.append("(  ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^, ");
		insertBookingGradeApplicantSQL.append("  ^ ");
		insertBookingGradeApplicantSQL.append(")  ");

		// Get update BookingGradeApplicant SQL.
		updateBookingGradeApplicantSQL = new StringBuffer();
		updateBookingGradeApplicantSQL.append("UPDATE BOOKINGGRADEAPPLICANT ");
		updateBookingGradeApplicantSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateBookingGradeApplicantSQL.append("     STATUS = " + BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_DRAFT + ", ");
		updateBookingGradeApplicantSQL.append("     CLIENTAGENCYJOBPROFILEGRADEID = ^, ");
		updateBookingGradeApplicantSQL.append("     FILENAME = ^, ");
		updateBookingGradeApplicantSQL.append("     RATE = ^, ");
		updateBookingGradeApplicantSQL.append("     PAYRATE = ^, ");
		updateBookingGradeApplicantSQL.append("     WTDPERCENTAGE = ^, ");
		updateBookingGradeApplicantSQL.append("     NIPERCENTAGE = ^, ");
		updateBookingGradeApplicantSQL.append("     WAGERATE = ^, ");
		updateBookingGradeApplicantSQL.append("     CHARGERATEVATRATE = ^, ");
		updateBookingGradeApplicantSQL.append("     COMMISSIONVATRATE = ^, ");
		updateBookingGradeApplicantSQL.append("     PAYRATEVATRATE = ^, ");
		updateBookingGradeApplicantSQL.append("     WTDVATRATE = ^, ");
		updateBookingGradeApplicantSQL.append("     NIVATRATE = ^, ");
		updateBookingGradeApplicantSQL.append("     AUDITORID = ^, ");
		updateBookingGradeApplicantSQL.append("     AUDITTIMESTAMP = ^ ");
		updateBookingGradeApplicantSQL.append("WHERE BOOKINGGRADEAPPLICANTID = ^ ");
		updateBookingGradeApplicantSQL.append("AND   NOOFCHANGES = ^ ");
		
		// Get update BookingGradeApplicant status SQL.
		updateBookingGradeApplicantStatusSQL = new StringBuffer();
		updateBookingGradeApplicantStatusSQL.append("UPDATE BOOKINGGRADEAPPLICANT ");
		updateBookingGradeApplicantStatusSQL.append("SET STATUS = ^, ");
		updateBookingGradeApplicantStatusSQL.append("    AUDITORID = ^, ");
		updateBookingGradeApplicantStatusSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeApplicantStatusSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeApplicantStatusSQL.append("WHERE BOOKINGGRADEAPPLICANTID = ^ ");
		updateBookingGradeApplicantStatusSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingGradeApplicant activated SQL.
		updateBookingGradeApplicantActivatedSQL = new StringBuffer();
		updateBookingGradeApplicantActivatedSQL.append("UPDATE BOOKINGGRADEAPPLICANT ");
		updateBookingGradeApplicantActivatedSQL.append("SET ACTIVATED = TRUE, ");
		updateBookingGradeApplicantActivatedSQL.append("    AUDITORID = ^, ");
		updateBookingGradeApplicantActivatedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeApplicantActivatedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeApplicantActivatedSQL.append("WHERE BOOKINGGRADEAPPLICANTID = ^ ");
		updateBookingGradeApplicantActivatedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingGradeApplicant reject SQL.
		updateBookingGradeApplicantRejectSQL = new StringBuffer();
		updateBookingGradeApplicantRejectSQL.append("UPDATE BOOKINGGRADEAPPLICANT ");
		updateBookingGradeApplicantRejectSQL.append("SET STATUS = " + BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_REJECTED + ", ");
		updateBookingGradeApplicantRejectSQL.append("    REJECTTEXT = ^, ");
		updateBookingGradeApplicantRejectSQL.append("    AUDITORID = ^, ");
		updateBookingGradeApplicantRejectSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeApplicantRejectSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeApplicantRejectSQL.append("WHERE BOOKINGGRADEAPPLICANTID = ^ ");
		updateBookingGradeApplicantRejectSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update BookingGradeApplicant renegotiate SQL.
		updateBookingGradeApplicantRenegotiateSQL = new StringBuffer();
		updateBookingGradeApplicantRenegotiateSQL.append("UPDATE BOOKINGGRADEAPPLICANT ");
		updateBookingGradeApplicantRenegotiateSQL.append("SET STATUS = " + BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_RENEGOTIATE + ", ");
		updateBookingGradeApplicantRenegotiateSQL.append("    RENEGOTIATETEXT = ^, ");
		updateBookingGradeApplicantRenegotiateSQL.append("    AUDITORID = ^, ");
		updateBookingGradeApplicantRenegotiateSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingGradeApplicantRenegotiateSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingGradeApplicantRenegotiateSQL.append("WHERE BOOKINGGRADEAPPLICANTID = ^ ");
		updateBookingGradeApplicantRenegotiateSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update Booking Grade Applicant Checklist Created Date SQL.
    updateBookingGradeApplicantChecklistCreatedTimeSQL = new StringBuffer();
    updateBookingGradeApplicantChecklistCreatedTimeSQL.append("UPDATE BOOKINGGRADEAPPLICANT ");
    updateBookingGradeApplicantChecklistCreatedTimeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateBookingGradeApplicantChecklistCreatedTimeSQL.append("     CHECKLISTCREATEDTIME = ^, ");
    updateBookingGradeApplicantChecklistCreatedTimeSQL.append("     AUDITORID = ^, ");
    updateBookingGradeApplicantChecklistCreatedTimeSQL.append("     AUDITTIMESTAMP = ^ ");
    updateBookingGradeApplicantChecklistCreatedTimeSQL.append("WHERE BOOKINGGRADEAPPLICANTID = ^ ");
    updateBookingGradeApplicantChecklistCreatedTimeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select BookingGradeApplicants SQL.
		selectBookingGradeApplicantUsersSQL = new StringBuffer();
		selectBookingGradeApplicantUsersSQL.append("SELECT BGA.BOOKINGGRADEAPPLICANTID, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.BOOKINGGRADEID, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.APPLICANTID, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.CLIENTAGENCYJOBPROFILEGRADEID, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.STATUS, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.FILENAME, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.RATE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.PAYRATE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.WTDPERCENTAGE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.NIPERCENTAGE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.WAGERATE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.CHARGERATEVATRATE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.COMMISSIONVATRATE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.PAYRATEVATRATE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.WTDVATRATE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.NIVATRATE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.REJECTTEXT, ");
    selectBookingGradeApplicantUsersSQL.append("  BGA.RENEGOTIATETEXT, ");
    selectBookingGradeApplicantUsersSQL.append("  BGA.CHECKLISTCREATEDTIME, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.ACTIVATED, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.CREATIONTIMESTAMP, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.AUDITORID, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.AUDITTIMESTAMP, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.ACTIVE, ");
		selectBookingGradeApplicantUsersSQL.append("  BGA.NOOFCHANGES, ");
		
		// should add more fields
		selectBookingGradeApplicantUsersSQL.append("  BG.BOOKINGID, ");
		selectBookingGradeApplicantUsersSQL.append("  BG.RATE AS CHARGERATE, "); // rate of requested grade
		
		selectBookingGradeApplicantUsersSQL.append("  A.FIRSTNAME AS APPLICANTFIRSTNAME, ");
		selectBookingGradeApplicantUsersSQL.append("  A.LASTNAME AS APPLICANTLASTNAME, ");
		selectBookingGradeApplicantUsersSQL.append("  A.EMAILADDRESS AS APPLICANTEMAILADDRESS, ");
		selectBookingGradeApplicantUsersSQL.append("  A.MOBILENUMBER AS APPLICANTMOBILENUMBER, ");
		selectBookingGradeApplicantUsersSQL.append("  A.REFERENCE AS APPLICANTREFERENCE, ");
		selectBookingGradeApplicantUsersSQL.append("  A.PROFESSIONALREFERENCE AS APPLICANTPROFESSIONALREFERENCE, ");
		selectBookingGradeApplicantUsersSQL.append("  A.DATEOFBIRTH AS APPLICANTDATEOFBIRTH, ");
		selectBookingGradeApplicantUsersSQL.append("  A.NINUMBER AS APPLICANTNINUMBER, ");
		selectBookingGradeApplicantUsersSQL.append("  A.PHOTOFILENAME AS APPLICANTPHOTOFILENAME, ");
    selectBookingGradeApplicantUsersSQL.append("  A.GENDER AS APPLICANTGENDER, ");
    selectBookingGradeApplicantUsersSQL.append("  A.ORIGINALAGENCYID AS APPLICANTORIGINALAGENCYID, ");
		selectBookingGradeApplicantUsersSQL.append("  AG.AGENCYID, ");
		selectBookingGradeApplicantUsersSQL.append("  AG.NAME AS AGENCYNAME, ");
		selectBookingGradeApplicantUsersSQL.append("  AG.CODE AS AGENCYCODE, ");
		selectBookingGradeApplicantUsersSQL.append("  JP.JOBPROFILEID, ");
		selectBookingGradeApplicantUsersSQL.append("  JP.CODE AS JOBPROFILECODE, ");
		selectBookingGradeApplicantUsersSQL.append("  JP.NAME AS JOBPROFILENAME, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.LOCATIONID, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.LOCATIONCODE, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.LOCATIONNAME, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.LOCATIONDESCRIPTION, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.SITEID, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.SITECODE, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.SITENAME, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.CLIENTID, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.CLIENTCODE, ");
		selectBookingGradeApplicantUsersSQL.append("  LV.CLIENTNAME, ");
//		selectBookingGradeApplicantUsersSQL.append("  L.LOCATIONID, ");
//		selectBookingGradeApplicantUsersSQL.append("  L.CODE AS LOCATIONCODE, ");
//		selectBookingGradeApplicantUsersSQL.append("  L.NAME AS LOCATIONNAME, ");
//		selectBookingGradeApplicantUsersSQL.append("  L.DESCRIPTION AS LOCATIONDESCRIPTION, ");
//		selectBookingGradeApplicantUsersSQL.append("  SI.SITEID, ");
//		selectBookingGradeApplicantUsersSQL.append("  SI.CODE AS SITECODE, ");
//		selectBookingGradeApplicantUsersSQL.append("  SI.NAME AS SITENAME, ");
//		selectBookingGradeApplicantUsersSQL.append("  C.CLIENTID, ");
//		selectBookingGradeApplicantUsersSQL.append("  C.CODE AS CLIENTCODE, ");
//		selectBookingGradeApplicantUsersSQL.append("  C.NAME AS CLIENTNAME, ");
    selectBookingGradeApplicantUsersSQL.append("  B.EXPENSESTEXT AS BOOKINGEXPENSESTEXT, ");
    selectBookingGradeApplicantUsersSQL.append("  B.NOOFHOURS AS BOOKINGNOOFHOURS, ");
		selectBookingGradeApplicantUsersSQL.append("  G.NAME AS GRADENAME ");
		selectBookingGradeApplicantUsersSQL.append("FROM BOOKINGGRADEAPPLICANT BGA, ");
		selectBookingGradeApplicantUsersSQL.append("     BOOKINGGRADE BG, ");
		selectBookingGradeApplicantUsersSQL.append("     CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
		selectBookingGradeApplicantUsersSQL.append("     GRADE G, ");
		selectBookingGradeApplicantUsersSQL.append("     APPLICANT A, ");
		selectBookingGradeApplicantUsersSQL.append("     AGENCY AG, ");
		selectBookingGradeApplicantUsersSQL.append("     BOOKING B, ");
		selectBookingGradeApplicantUsersSQL.append("     LOCATIONVIEWALL LV, "); // Was LOCATIONVIEW until 12/09/2016
//		selectBookingGradeApplicantUsersSQL.append("     LOCATION L, ");
//		selectBookingGradeApplicantUsersSQL.append("     SITE SI, ");
//		selectBookingGradeApplicantUsersSQL.append("     CLIENT C, ");
		selectBookingGradeApplicantUsersSQL.append("     JOBPROFILE JP ");
		selectBookingGradeApplicantUsersSQL.append("WHERE A.APPLICANTID = BGA.APPLICANTID ");
		selectBookingGradeApplicantUsersSQL.append("AND AG.AGENCYID = A.AGENCYID ");
		selectBookingGradeApplicantUsersSQL.append("AND A.ACTIVE = TRUE ");
		selectBookingGradeApplicantUsersSQL.append("AND BG.BOOKINGGRADEID = BGA.BOOKINGGRADEID ");
		selectBookingGradeApplicantUsersSQL.append("AND BG.ACTIVE = TRUE ");
		selectBookingGradeApplicantUsersSQL.append("AND B.BOOKINGID = BG.BOOKINGID ");
		selectBookingGradeApplicantUsersSQL.append("AND B.ACTIVE = TRUE ");
		selectBookingGradeApplicantUsersSQL.append("AND JP.JOBPROFILEID = B.JOBPROFILEID ");
//		selectBookingGradeApplicantUsersSQL.append("AND JP.ACTIVE = TRUE "); // Commented out 13/09/2016 for same reason as use of LOCATIONVIEWALL
		selectBookingGradeApplicantUsersSQL.append("AND LV.LOCATIONID = B.LOCATIONID ");
//		selectBookingGradeApplicantUsersSQL.append("AND L.LOCATIONID = B.LOCATIONID ");
//		selectBookingGradeApplicantUsersSQL.append("AND L.ACTIVE = TRUE ");
//		selectBookingGradeApplicantUsersSQL.append("AND SI.SITEID = L.SITEID ");
//		selectBookingGradeApplicantUsersSQL.append("AND SI.ACTIVE = TRUE ");
//		selectBookingGradeApplicantUsersSQL.append("AND C.CLIENTID = SI.CLIENTID ");
//		selectBookingGradeApplicantUsersSQL.append("AND C.ACTIVE = TRUE ");
		selectBookingGradeApplicantUsersSQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = BGA.CLIENTAGENCYJOBPROFILEGRADEID ");
		selectBookingGradeApplicantUsersSQL.append("AND CAJPG.ACTIVE = TRUE ");
		selectBookingGradeApplicantUsersSQL.append("AND G.GRADEID = CAJPG.GRADEID ");
		selectBookingGradeApplicantUsersSQL.append("AND G.ACTIVE = TRUE ");

		// Get select BookingGradeApplicantUsers for Booking SQL.
		selectBookingGradeApplicantUsersForBookingSQL = new StringBuffer(selectBookingGradeApplicantUsersSQL);
		selectBookingGradeApplicantUsersForBookingSQL.append("AND BG.BOOKINGID = ^ ");

		//
		selectBookingGradeApplicantUserForBookingFilledSingleCandidateSQL = new StringBuffer(selectBookingGradeApplicantUsersForBookingSQL);
		selectBookingGradeApplicantUserForBookingFilledSingleCandidateSQL.append("AND BGA.ACTIVE = TRUE ");
		selectBookingGradeApplicantUserForBookingFilledSingleCandidateSQL.append("AND B.SINGLECANDIDATE = TRUE ");
		selectBookingGradeApplicantUserForBookingFilledSingleCandidateSQL.append("AND BGA.STATUS = " + BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_FILLED + " ");		
		
		// Get select BookingGradeApplicantUsers for BookingGrade SQL.
		selectBookingGradeApplicantUsersForBookingGradeSQL = new StringBuffer(selectBookingGradeApplicantUsersSQL);
		selectBookingGradeApplicantUsersForBookingGradeSQL.append("AND BGA.BOOKINGGRADEID = ^ ");
		// Get select BookingGradeApplicantUsers for BookingGrade for Agency SQL.
		selectBookingGradeApplicantUsersForBookingGradeAndAgencySQL = new StringBuffer(selectBookingGradeApplicantUsersForBookingGradeSQL);
		selectBookingGradeApplicantUsersForBookingGradeAndAgencySQL.append("AND A.AGENCYID = ^ ");
		// Get select Active BookingGradeApplicantUsers for BookingSQL.
		selectActiveBookingGradeApplicantUsersForBookingGradeAndAgencySQL = new StringBuffer(selectBookingGradeApplicantUsersForBookingGradeAndAgencySQL);
		selectActiveBookingGradeApplicantUsersForBookingGradeAndAgencySQL.append("AND BGA.ACTIVE = TRUE ");
		selectActiveBookingGradeApplicantUsersForBookingGradeAndAgencySQL.append("AND A.ACTIVE = TRUE ");
		selectActiveBookingGradeApplicantUsersForBookingGradeAndAgencySQL.append("AND AG.ACTIVE = TRUE ");
        //
		selectBookingGradeApplicantUsersForBookingGradeAndAgencySQL.append("ORDER BY BGA.BOOKINGGRADEAPPLICANTID ");
        //
		selectActiveBookingGradeApplicantUsersForBookingGradeAndAgencySQL.append("ORDER BY BGA.BOOKINGGRADEAPPLICANTID");

		// Get select BookingGradeApplicantUsers for BookingGrade for Client SQL.
		selectBookingGradeApplicantUsersForBookingAndClientSQL = new StringBuffer(selectBookingGradeApplicantUsersForBookingSQL);
		// not really for client just restrict the visible statuses
		selectBookingGradeApplicantUsersForBookingAndClientSQL.append("AND BGA.STATUS IN (" + BookingGradeApplicant.BOOKINGGRADEAPPLICANT_CLIENT_STATUSES + ")");
		// Get select Active BookingGradeApplicantUsers for BookingSQL.
		selectActiveBookingGradeApplicantUsersForBookingAndClientSQL = new StringBuffer(selectBookingGradeApplicantUsersForBookingAndClientSQL);
		selectActiveBookingGradeApplicantUsersForBookingAndClientSQL.append("AND BGA.ACTIVE = TRUE ");
		selectActiveBookingGradeApplicantUsersForBookingAndClientSQL.append("AND A.ACTIVE = TRUE ");
        //
		selectBookingGradeApplicantUsersForBookingAndClientSQL.append("ORDER BY BGA.BOOKINGGRADEAPPLICANTID ");
        //
		selectActiveBookingGradeApplicantUsersForBookingAndClientSQL.append("ORDER BY BGA.BOOKINGGRADEAPPLICANTID");
		
		
    // Get select BookingGradeApplicantUser SQL.
    selectBookingGradeApplicantUserSQL = new StringBuffer(selectBookingGradeApplicantUsersSQL);
    selectBookingGradeApplicantUserSQL.append("AND BGA.BOOKINGGRADEAPPLICANTID = ^ ");

    // Get select BookingGradeApplicantUser 2 SQL.
    selectBookingGradeApplicantUser2SQL = new StringBuffer(selectBookingGradeApplicantUsersSQL);
    selectBookingGradeApplicantUser2SQL.append("AND BGA.BOOKINGGRADEID = ^ ");
    selectBookingGradeApplicantUser2SQL.append("AND BGA.APPLICANTID = ^ ");

		// Get select BookingExpensesCount SQL.
		selectBookingExpensesCountSQL = new StringBuffer();
		selectBookingExpensesCountSQL.append("SELECT COUNT(BE.BOOKINGEXPENSEID) ");
		selectBookingExpensesCountSQL.append("FROM BOOKINGEXPENSE BE ");
		selectBookingExpensesCountSQL.append("WHERE BE.BOOKINGID = ^ ");
		selectBookingExpensesCountSQL.append("AND BE.ACTIVE = TRUE ");

    // Get select Active BookingGradeApplicants Count for ClientAgencyJobProfileGrade SQL.
    selectActiveBookingGradeApplicantsCountForClientAgencyJobProfileGradeSQL = new StringBuffer();
    selectActiveBookingGradeApplicantsCountForClientAgencyJobProfileGradeSQL.append("SELECT COUNT(BGA.BOOKINGGRADEAPPLICANTID) ");
    selectActiveBookingGradeApplicantsCountForClientAgencyJobProfileGradeSQL.append("FROM BOOKINGGRADEAPPLICANT BGA ");
    selectActiveBookingGradeApplicantsCountForClientAgencyJobProfileGradeSQL.append("WHERE BGA.CLIENTAGENCYJOBPROFILEGRADEID = ^ ");
    selectActiveBookingGradeApplicantsCountForClientAgencyJobProfileGradeSQL.append("AND BGA.ACTIVE = TRUE ");

	}

	public int insertBookingGradeApplicant(BookingGradeApplicant bookingGradeApplicant, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBookingGradeApplicantSQL.toString());
		// Replace the parameters with supplied values.
		bookingGradeApplicant.setBookingGradeApplicantId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "bookingGradeApplicant"));
		Utilities.replace(sql, bookingGradeApplicant.getBookingGradeApplicantId());
		Utilities.replace(sql, bookingGradeApplicant.getBookingGradeId());
		Utilities.replace(sql, bookingGradeApplicant.getApplicantId());
		Utilities.replace(sql, bookingGradeApplicant.getClientAgencyJobProfileGradeId());
        Utilities.replaceAndQuoteNullable(sql, bookingGradeApplicant.getFilename());
        Utilities.replace(sql, bookingGradeApplicant.getRate());
        Utilities.replace(sql, bookingGradeApplicant.getPayRate());
        Utilities.replace(sql, bookingGradeApplicant.getWtdPercentage());
        Utilities.replace(sql, bookingGradeApplicant.getNiPercentage());
        Utilities.replace(sql, bookingGradeApplicant.getWageRate());
        Utilities.replace(sql, bookingGradeApplicant.getChargeRateVatRate());
        Utilities.replace(sql, bookingGradeApplicant.getCommissionVatRate());
        Utilities.replace(sql, bookingGradeApplicant.getPayRateVatRate());
        Utilities.replace(sql, bookingGradeApplicant.getWtdVatRate());
        Utilities.replace(sql, bookingGradeApplicant.getNiVatRate());
        Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeApplicant(BookingGradeApplicant bookingGradeApplicant, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicant.getClientAgencyJobProfileGradeId());
        Utilities.replaceAndQuoteNullable(sql, bookingGradeApplicant.getFilename());
        Utilities.replace(sql, bookingGradeApplicant.getRate());
        Utilities.replace(sql, bookingGradeApplicant.getPayRate());
        Utilities.replace(sql, bookingGradeApplicant.getWtdPercentage());
        Utilities.replace(sql, bookingGradeApplicant.getNiPercentage());
        Utilities.replace(sql, bookingGradeApplicant.getWageRate());
        Utilities.replace(sql, bookingGradeApplicant.getChargeRateVatRate());
        Utilities.replace(sql, bookingGradeApplicant.getCommissionVatRate());
        Utilities.replace(sql, bookingGradeApplicant.getPayRateVatRate());
        Utilities.replace(sql, bookingGradeApplicant.getWtdVatRate());
        Utilities.replace(sql, bookingGradeApplicant.getNiVatRate());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeApplicant.getBookingGradeApplicantId());
		Utilities.replace(sql, bookingGradeApplicant.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}
	
	
	public int updateBookingGradeApplicantStatus(Integer bookingGradeApplicantId, Integer noOfChanges,
			Integer auditorId, int status) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, status);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeApplicantReject(Integer bookingGradeApplicantId, String rejectText, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantRejectSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, rejectText);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeApplicantRenegotiate(Integer bookingGradeApplicantId, String renegotiateText, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantRenegotiateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, renegotiateText);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingGradeApplicantActivated(Integer bookingGradeApplicantId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingGradeApplicantActivatedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingGradeApplicantId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

  public int updateBookingGradeApplicantChecklistCreatedTime(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId, Date checklistCreatedTime) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateBookingGradeApplicantChecklistCreatedTimeSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, new Timestamp(checklistCreatedTime.getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, bookingGradeApplicantId);
    Utilities.replace(sql, noOfChanges);
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
  public BookingGradeApplicantUser getBookingGradeApplicantUser(Integer bookingGradeApplicantId) {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectBookingGradeApplicantUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, bookingGradeApplicantId);
    return (BookingGradeApplicantUser) RecordFactory.getInstance().get(getJdbcTemplate(),
        sql.toString(), BookingGradeApplicantUser.class.getName());
  }

  public BookingGradeApplicantUser getBookingGradeApplicantUser(Integer bookingGradeId, Integer applicantId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectBookingGradeApplicantUser2SQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, bookingGradeId);
    Utilities.replace(sql, applicantId);
    return (BookingGradeApplicantUser) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), BookingGradeApplicantUser.class.getName());
  }

	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntity(Integer bookingGradeApplicantId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingGradeApplicantUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeApplicantId);
		return (BookingGradeApplicantUserEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantUserEntity.class.getName());
	}

	public List<BookingGradeApplicantUser> getBookingGradeApplicantUsersForBookingGradeAndAgency(Integer bookingGradeId, Integer agencyId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingGradeApplicantUsersForBookingGradeAndAgencySQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingGradeApplicantUsersForBookingGradeAndAgencySQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingGradeId);
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantUser.class.getName());

	}

	public List<BookingGradeApplicantUser> getBookingGradeApplicantUsersForBookingAndClient(Integer bookingId, Integer clientId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingGradeApplicantUsersForBookingAndClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingGradeApplicantUsersForBookingAndClientSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
//		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantUser.class.getName());

	}

	public BookingGradeApplicantUser getBookingGradeApplicantUserForBookingFilledSingleCandidate(Integer bookingId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingGradeApplicantUserForBookingFilledSingleCandidateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return (BookingGradeApplicantUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingGradeApplicantUser.class.getName());
	}
	
	public IntValue getBookingExpensesCount(Integer bookingId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingExpensesCountSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), IntValue.class.getName());
	}

  public IntValue getActiveBookingGradeApplicantsCountForClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectActiveBookingGradeApplicantsCountForClientAgencyJobProfileGradeSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, clientAgencyJobProfileGradeId);
    return (IntValue) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), IntValue.class.getName());
  }
  

}
