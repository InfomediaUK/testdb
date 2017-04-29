package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ApplicantTrainingCourse;
import com.helmet.bean.ApplicantTrainingCourseUser;
import com.helmet.bean.RecordCount;
import com.helmet.persistence.ApplicantTrainingCourseDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultApplicantTrainingCourseDAO extends JdbcDaoSupport implements ApplicantTrainingCourseDAO 
{

	private static StringBuffer insertApplicantTrainingCourseSQL;

	private static StringBuffer deleteApplicantTrainingCourseSQL;

	private static StringBuffer updateApplicantTrainingCourseSQL;

	private static StringBuffer selectApplicantTrainingCoursesSQL;

	private static StringBuffer selectApplicantTrainingCourseSQL;

	private static StringBuffer selectApplicantTrainingCourseUsersSQL;

	private static StringBuffer selectApplicantTrainingCourseUserSQL;

	private static StringBuffer selectApplicantTrainingCourseUsersForApplicantSQL;

	private static StringBuffer selectActiveApplicantTrainingCourseUsersForApplicantSQL;

	private static StringBuffer selectActiveApplicantTrainingCourseCountForTrainingCourseSQL;
	
	public static void init() {
		// Get insert ApplicantTrainingCourse SQL.
		insertApplicantTrainingCourseSQL = new StringBuffer();
		insertApplicantTrainingCourseSQL.append("INSERT INTO APPLICANTTRAININGCOURSE ");
		insertApplicantTrainingCourseSQL.append("(  ");
		insertApplicantTrainingCourseSQL.append("  APPLICANTTRAININGCOURSEID, ");
		insertApplicantTrainingCourseSQL.append("  APPLICANTID, ");
		insertApplicantTrainingCourseSQL.append("  TRAININGCOMPANYCOURSEID, ");
    insertApplicantTrainingCourseSQL.append("  STARTDATE, ");
    insertApplicantTrainingCourseSQL.append("  ENDDATE, ");
    insertApplicantTrainingCourseSQL.append("  COMMENT, ");
    insertApplicantTrainingCourseSQL.append("  DOCUMENTATIONFILENAME, ");
    insertApplicantTrainingCourseSQL.append("  CREATIONTIMESTAMP, ");
		insertApplicantTrainingCourseSQL.append("  AUDITORID, ");
		insertApplicantTrainingCourseSQL.append("  AUDITTIMESTAMP ");
		insertApplicantTrainingCourseSQL.append(")  ");
		insertApplicantTrainingCourseSQL.append("VALUES  ");
		insertApplicantTrainingCourseSQL.append("(  ");
		insertApplicantTrainingCourseSQL.append("  ^, ");
		insertApplicantTrainingCourseSQL.append("  ^, ");
    insertApplicantTrainingCourseSQL.append("  ^, ");
    insertApplicantTrainingCourseSQL.append("  ^, ");
    insertApplicantTrainingCourseSQL.append("  ^, ");
    insertApplicantTrainingCourseSQL.append("  ^, ");
    insertApplicantTrainingCourseSQL.append("  ^, ");
		insertApplicantTrainingCourseSQL.append("  ^, ");
		insertApplicantTrainingCourseSQL.append("  ^, ");
		insertApplicantTrainingCourseSQL.append("  ^ ");
		insertApplicantTrainingCourseSQL.append(")  ");
		// Get delete ApplicantTrainingCourse SQL.
		deleteApplicantTrainingCourseSQL = new StringBuffer();
		deleteApplicantTrainingCourseSQL.append("UPDATE APPLICANTTRAININGCOURSE ");
		deleteApplicantTrainingCourseSQL.append("SET ACTIVE = FALSE, ");
		deleteApplicantTrainingCourseSQL.append("    AUDITORID = ^, ");
		deleteApplicantTrainingCourseSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteApplicantTrainingCourseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteApplicantTrainingCourseSQL.append("WHERE APPLICANTTRAININGCOURSEID = ^ ");
		deleteApplicantTrainingCourseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ApplicantTrainingCourse SQL.
		updateApplicantTrainingCourseSQL = new StringBuffer();
		updateApplicantTrainingCourseSQL.append("UPDATE APPLICANTTRAININGCOURSE ");
    updateApplicantTrainingCourseSQL.append("SET TRAININGCOMPANYCOURSEID = ^, ");
    updateApplicantTrainingCourseSQL.append("    STARTDATE = ^, ");
    updateApplicantTrainingCourseSQL.append("    ENDDATE = ^, ");
    updateApplicantTrainingCourseSQL.append("    COMMENT = ^, ");
    updateApplicantTrainingCourseSQL.append("    DOCUMENTATIONFILENAME = ^, ");
		updateApplicantTrainingCourseSQL.append("    AUDITORID = ^, ");
		updateApplicantTrainingCourseSQL.append("    AUDITTIMESTAMP = ^, ");
		updateApplicantTrainingCourseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateApplicantTrainingCourseSQL.append("WHERE APPLICANTTRAININGCOURSEID = ^ ");
		updateApplicantTrainingCourseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ApplicantTrainingCourses SQL.
		selectApplicantTrainingCoursesSQL = new StringBuffer();
		selectApplicantTrainingCoursesSQL.append("SELECT APPLICANTTRAININGCOURSEID, ");
		selectApplicantTrainingCoursesSQL.append("       APPLICANTID, ");
		selectApplicantTrainingCoursesSQL.append("       TRAININGCOMPANYCOURSEID, ");
    selectApplicantTrainingCoursesSQL.append("       STARTDATE, ");
    selectApplicantTrainingCoursesSQL.append("       ENDDATE, ");
    selectApplicantTrainingCoursesSQL.append("       COMMENT, ");
    selectApplicantTrainingCoursesSQL.append("       DOCUMENTATIONFILENAME, ");
		selectApplicantTrainingCoursesSQL.append("       CREATIONTIMESTAMP, ");
		selectApplicantTrainingCoursesSQL.append("       AUDITORID, ");
		selectApplicantTrainingCoursesSQL.append("       AUDITTIMESTAMP, ");
		selectApplicantTrainingCoursesSQL.append("       ACTIVE, ");
		selectApplicantTrainingCoursesSQL.append("       NOOFCHANGES ");
		selectApplicantTrainingCoursesSQL.append("FROM APPLICANTTRAININGCOURSE ");
		// Get select ApplicantTrainingCourse SQL.
		selectApplicantTrainingCourseSQL = new StringBuffer(selectApplicantTrainingCoursesSQL);
		selectApplicantTrainingCourseSQL.append("WHERE APPLICANTTRAININGCOURSEID = ^ ");
		// Get select ApplicantTrainingCourseUsers SQL.
		selectApplicantTrainingCourseUsersSQL = new StringBuffer();
		selectApplicantTrainingCourseUsersSQL.append("SELECT ATC.APPLICANTTRAININGCOURSEID, ");
		selectApplicantTrainingCourseUsersSQL.append("       ATC.APPLICANTID, ");
		selectApplicantTrainingCourseUsersSQL.append("       ATC.TRAININGCOMPANYCOURSEID, ");
    selectApplicantTrainingCourseUsersSQL.append("       ATC.STARTDATE, ");
    selectApplicantTrainingCourseUsersSQL.append("       ATC.ENDDATE, ");
    selectApplicantTrainingCourseUsersSQL.append("       ATC.COMMENT, ");
    selectApplicantTrainingCourseUsersSQL.append("       ATC.DOCUMENTATIONFILENAME, ");
		selectApplicantTrainingCourseUsersSQL.append("       ATC.CREATIONTIMESTAMP, ");
		selectApplicantTrainingCourseUsersSQL.append("       ATC.AUDITORID, ");
		selectApplicantTrainingCourseUsersSQL.append("       ATC.AUDITTIMESTAMP, ");
		selectApplicantTrainingCourseUsersSQL.append("       ATC.ACTIVE, ");
		selectApplicantTrainingCourseUsersSQL.append("       ATC.NOOFCHANGES, ");
		selectApplicantTrainingCourseUsersSQL.append("       A.FIRSTNAME AS APPLICANTFIRSTNAME, ");
    selectApplicantTrainingCourseUsersSQL.append("       A.LASTNAME AS APPLICANTLASTNAME, ");
    selectApplicantTrainingCourseUsersSQL.append("       A.NHSSTAFFNAME AS APPLICANTNHSSTAFFNAME, ");
    selectApplicantTrainingCourseUsersSQL.append("       A.EMAILADDRESS AS APPLICANTEMAILADDRESS, ");
    selectApplicantTrainingCourseUsersSQL.append("       DC.NAME AS DISCIPLINECATEGORYNAME, ");
		selectApplicantTrainingCourseUsersSQL.append("       TCC.NAME AS TRAININGCOMPANYCOURSENAME, ");
		selectApplicantTrainingCourseUsersSQL.append("       TCC.ONLINE, ");
    selectApplicantTrainingCourseUsersSQL.append("       TC.NAME AS TRAININGCOMPANYNAME ");
		selectApplicantTrainingCourseUsersSQL.append("FROM APPLICANTTRAININGCOURSE ATC ");
    selectApplicantTrainingCourseUsersSQL.append("    JOIN APPLICANT A ON ");
    selectApplicantTrainingCourseUsersSQL.append("        A.APPLICANTID = ATC.APPLICANTID ");
    selectApplicantTrainingCourseUsersSQL.append("    JOIN DISCIPLINECATEGORY DC ON ");
    selectApplicantTrainingCourseUsersSQL.append("        DC.DISCIPLINECATEGORYID = A.DISCIPLINECATEGORYID ");
    selectApplicantTrainingCourseUsersSQL.append("    JOIN TRAININGCOMPANYCOURSE TCC ON ");
    selectApplicantTrainingCourseUsersSQL.append("        TCC.TRAININGCOMPANYCOURSEID = ATC.TRAININGCOMPANYCOURSEID ");
    selectApplicantTrainingCourseUsersSQL.append("    JOIN TRAININGCOMPANY TC ON ");
    selectApplicantTrainingCourseUsersSQL.append("        TC.TRAININGCOMPANYID = TCC.TRAININGCOMPANYID ");
		selectApplicantTrainingCourseUsersSQL.append("WHERE ATC.ACTIVE = TRUE ");
		// Get select ApplicantTrainingCourseUser SQL.
		selectApplicantTrainingCourseUserSQL = new StringBuffer(selectApplicantTrainingCourseUsersSQL);
		selectApplicantTrainingCourseUserSQL.append("AND ATC.APPLICANTTRAININGCOURSEID = ^ ");
		// Get select ApplicantTrainingCourseUsers for Applicant SQL.
		selectApplicantTrainingCourseUsersForApplicantSQL = new StringBuffer(selectApplicantTrainingCourseUsersSQL);
		selectApplicantTrainingCourseUsersForApplicantSQL.append("AND ATC.APPLICANTID = ^ ");
		// Active ONLY
		selectActiveApplicantTrainingCourseUsersForApplicantSQL = new StringBuffer(selectApplicantTrainingCourseUsersForApplicantSQL);
		selectActiveApplicantTrainingCourseUsersForApplicantSQL.append("AND ATC.ACTIVE = TRUE ");
    // Get select Active ApplicantTrainingCourse Count for TrainingCourse SQL
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL = new StringBuffer();
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("SELECT COUNT(*) ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("FROM APPLICANTTRAININGCOURSE ATC ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("JOIN APPLICANT A ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("    ON A.APPLICANTID = ATC.APPLICANTID ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("JOIN TRAININGCOMPANYCOURSE TCC ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("    ON TCC.TRAININGCOMPANYCOURSEID = ATC.TRAININGCOMPANYCOURSEID ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("JOIN TRAININGCOURSE TC ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("    ON TC.TRAININGCOURSEID = TCC.TRAININGCOURSEID ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("WHERE TC.TRAININGCOURSEID = ^ ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("AND A.ACTIVE = TRUE ");
		selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.append("AND ATC.ACTIVE = TRUE ");
		// ORDER BY clauses
		selectApplicantTrainingCourseUsersForApplicantSQL.append("ORDER BY TCC.NAME, ATC.STARTDATE DESC ");
		selectActiveApplicantTrainingCourseUsersForApplicantSQL.append("ORDER BY TCC.NAME, ATC.STARTDATE DESC ");

	}

	public Integer getApplicantTrainingCourseId() 
	{
	  return UpdateHandler.getInstance().getId(getJdbcTemplate(), "applicantTrainingCourse");
	}
	
	public int insertApplicantTrainingCourse(ApplicantTrainingCourse applicantTrainingCourse, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertApplicantTrainingCourseSQL.toString());
		// Replace the parameters with supplied values.
		// NON-STANDARD INSERT METHOD. ApplicantTrainingCourseId supplied in object.
		// Was: applicantTrainingCourse.setApplicantTrainingCourseId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "applicantTrainingCourse"));
		Utilities.replace(sql, applicantTrainingCourse.getApplicantTrainingCourseId());
		Utilities.replace(sql, applicantTrainingCourse.getApplicantId());
		Utilities.replace(sql, applicantTrainingCourse.getTrainingCompanyCourseId());
    Utilities.replaceAndQuote(sql, applicantTrainingCourse.getStartDate());
    Utilities.replaceAndQuote(sql, applicantTrainingCourse.getEndDate());
    Utilities.replaceAndQuoteNullable(sql, applicantTrainingCourse.getComment());
    Utilities.replaceAndQuoteNullable(sql, applicantTrainingCourse.getDocumentationFileName());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteApplicantTrainingCourse(Integer applicantTrainingCourseId, Integer noOfChanges, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteApplicantTrainingCourseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, applicantTrainingCourseId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateApplicantTrainingCourse(ApplicantTrainingCourse applicantTrainingCourse, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateApplicantTrainingCourseSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, applicantTrainingCourse.getTrainingCompanyCourseId());
    Utilities.replaceAndQuote(sql, applicantTrainingCourse.getStartDate());
    Utilities.replaceAndQuote(sql, applicantTrainingCourse.getEndDate());
    Utilities.replaceAndQuoteNullable(sql, applicantTrainingCourse.getComment());
    Utilities.replaceAndQuoteNullable(sql, applicantTrainingCourse.getDocumentationFileName());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, applicantTrainingCourse.getApplicantTrainingCourseId());
		Utilities.replace(sql, applicantTrainingCourse.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public List<ApplicantTrainingCourseUser> getApplicantTrainingCourseUsersForApplicant(Integer applicantId) 
	{
		return getApplicantTrainingCourseUsersForApplicant(applicantId, true);
	}

	public List<ApplicantTrainingCourseUser> getApplicantTrainingCourseUsersForApplicant(Integer applicantId, boolean showOnlyActive) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = null;
		if (showOnlyActive) 
		{
			sql = new StringBuffer(selectActiveApplicantTrainingCourseUsersForApplicantSQL.toString());
		}
		else 
		{
			sql = new StringBuffer(selectApplicantTrainingCourseUsersForApplicantSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, applicantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ApplicantTrainingCourseUser.class.getName());
	}

	public ApplicantTrainingCourse getApplicantTrainingCourse(Integer applicantTrainingCourseId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectApplicantTrainingCourseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, applicantTrainingCourseId);
		ApplicantTrainingCourse applicantTrainingCourse = (ApplicantTrainingCourse)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ApplicantTrainingCourse.class.getName());
		return applicantTrainingCourse;
	}

  public ApplicantTrainingCourseUser getApplicantTrainingCourseUser(Integer applicantTrainingCourseId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectApplicantTrainingCourseUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, applicantTrainingCourseId);
    ApplicantTrainingCourseUser applicantTrainingCourseUser = (ApplicantTrainingCourseUser)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ApplicantTrainingCourseUser.class.getName());
    return applicantTrainingCourseUser;
  }
		
  public RecordCount getActiveApplicantTrainingCourseCountForTrainingCourse(Integer trainingCourseId) 
  {
    StringBuffer sql = new StringBuffer(selectActiveApplicantTrainingCourseCountForTrainingCourseSQL.toString());
    Utilities.replace(sql, trainingCourseId);
    return (RecordCount)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), RecordCount.class.getName());
  }

}
