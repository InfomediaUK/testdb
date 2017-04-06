package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.TrainingCompanyCourse;
import com.helmet.bean.TrainingCompanyCourseUser;
import com.helmet.persistence.TrainingCompanyCourseDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultTrainingCompanyCourseDAO extends JdbcDaoSupport implements TrainingCompanyCourseDAO 
{

	private static StringBuffer insertTrainingCompanyCourseSQL;

	private static StringBuffer deleteTrainingCompanyCourseSQL;

	private static StringBuffer updateTrainingCompanyCourseSQL;

	private static StringBuffer selectTrainingCompanyCoursesSQL;

	private static StringBuffer selectTrainingCompanyCourseSQL;

	private static StringBuffer selectTrainingCompanyCourseForTrainingCompanyAndTrainingSQL;

	private static StringBuffer selectTrainingCompanyCourseUsersSQL;

	private static StringBuffer selectTrainingCompanyCourseUserSQL;

	private static StringBuffer selectTrainingCompanyCourseUsersForTrainingCompanySQL;

	private static StringBuffer selectActiveTrainingCompanyCourseUsersForTrainingCompanySQL;

  private static StringBuffer selectTrainingCompanyCourseUsersForTrainingSQL;

  private static StringBuffer selectActiveTrainingCompanyCourseUsersForTrainingSQL;

	public static void init() {
		// Get insert TrainingCompanyCourse SQL.
		insertTrainingCompanyCourseSQL = new StringBuffer();
		insertTrainingCompanyCourseSQL.append("INSERT INTO TRAININGCOMPANYCOURSE ");
		insertTrainingCompanyCourseSQL.append("(  ");
		insertTrainingCompanyCourseSQL.append("  TRAININGCOMPANYCOURSEID, ");
		insertTrainingCompanyCourseSQL.append("  TRAININGCOMPANYID, ");
		insertTrainingCompanyCourseSQL.append("  TRAININGCOURSEID, ");
    insertTrainingCompanyCourseSQL.append("  NAME, ");
    insertTrainingCompanyCourseSQL.append("  ONLINE, ");
    insertTrainingCompanyCourseSQL.append("  CREATIONTIMESTAMP, ");
		insertTrainingCompanyCourseSQL.append("  AUDITORID, ");
		insertTrainingCompanyCourseSQL.append("  AUDITTIMESTAMP ");
		insertTrainingCompanyCourseSQL.append(")  ");
		insertTrainingCompanyCourseSQL.append("VALUES  ");
		insertTrainingCompanyCourseSQL.append("(  ");
		insertTrainingCompanyCourseSQL.append("  ^, ");
		insertTrainingCompanyCourseSQL.append("  ^, ");
    insertTrainingCompanyCourseSQL.append("  ^, ");
    insertTrainingCompanyCourseSQL.append("  ^, ");
		insertTrainingCompanyCourseSQL.append("  ^, ");
		insertTrainingCompanyCourseSQL.append("  ^, ");
		insertTrainingCompanyCourseSQL.append("  ^, ");
		insertTrainingCompanyCourseSQL.append("  ^ ");
		insertTrainingCompanyCourseSQL.append(")  ");
		// Get delete TrainingCompanyCourse SQL.
		deleteTrainingCompanyCourseSQL = new StringBuffer();
		deleteTrainingCompanyCourseSQL.append("UPDATE TRAININGCOMPANYCOURSE ");
		deleteTrainingCompanyCourseSQL.append("SET ACTIVE = FALSE, ");
		deleteTrainingCompanyCourseSQL.append("    AUDITORID = ^, ");
		deleteTrainingCompanyCourseSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteTrainingCompanyCourseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteTrainingCompanyCourseSQL.append("WHERE TRAININGCOMPANYCOURSEID = ^ ");
		deleteTrainingCompanyCourseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update TrainingCompanyCourse SQL.
		updateTrainingCompanyCourseSQL = new StringBuffer();
		updateTrainingCompanyCourseSQL.append("UPDATE TRAININGCOMPANYCOURSE ");
    updateTrainingCompanyCourseSQL.append("SET NAME = ^, ");
    updateTrainingCompanyCourseSQL.append("    ONLINE = ^, ");
    updateTrainingCompanyCourseSQL.append("    DISPLAYORDER = ^, ");
		updateTrainingCompanyCourseSQL.append("    AUDITORID = ^, ");
		updateTrainingCompanyCourseSQL.append("    AUDITTIMESTAMP = ^, ");
		updateTrainingCompanyCourseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateTrainingCompanyCourseSQL.append("WHERE TRAININGCOMPANYCOURSEID = ^ ");
		updateTrainingCompanyCourseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select TrainingCompanyCourses SQL.
		selectTrainingCompanyCoursesSQL = new StringBuffer();
		selectTrainingCompanyCoursesSQL.append("SELECT TRAININGCOMPANYCOURSEID, ");
		selectTrainingCompanyCoursesSQL.append("       TRAININGCOMPANYID, ");
		selectTrainingCompanyCoursesSQL.append("       TRAININGCOURSEID, ");
    selectTrainingCompanyCoursesSQL.append("       NAME, ");
    selectTrainingCompanyCoursesSQL.append("       DISPLAYORDER, ");
    selectTrainingCompanyCoursesSQL.append("       ONLINE, ");
		selectTrainingCompanyCoursesSQL.append("       CREATIONTIMESTAMP, ");
		selectTrainingCompanyCoursesSQL.append("       AUDITORID, ");
		selectTrainingCompanyCoursesSQL.append("       AUDITTIMESTAMP, ");
		selectTrainingCompanyCoursesSQL.append("       ACTIVE, ");
		selectTrainingCompanyCoursesSQL.append("       NOOFCHANGES ");
		selectTrainingCompanyCoursesSQL.append("FROM TRAININGCOMPANYCOURSE ");
		// Get select TrainingCompanyCourse SQL.
		selectTrainingCompanyCourseSQL = new StringBuffer(selectTrainingCompanyCoursesSQL);
		selectTrainingCompanyCourseSQL.append("WHERE TRAININGCOMPANYCOURSEID = ^ ");
		// Get select TrainingCompanyCourse for TrainingCompany and TrainingCourse SQL.
		selectTrainingCompanyCourseForTrainingCompanyAndTrainingSQL = new StringBuffer(selectTrainingCompanyCoursesSQL);
		selectTrainingCompanyCourseForTrainingCompanyAndTrainingSQL.append("WHERE TRAININGCOMPANYID = ^ ");
		selectTrainingCompanyCourseForTrainingCompanyAndTrainingSQL.append("AND   TRAININGCOURSEID = ^ ");
		selectTrainingCompanyCourseForTrainingCompanyAndTrainingSQL.append("AND   ACTIVE = TRUE ");
		// Get select TrainingCompanyCourseUsers SQL.
		selectTrainingCompanyCourseUsersSQL = new StringBuffer();
		selectTrainingCompanyCourseUsersSQL.append("SELECT TCC.TRAININGCOMPANYCOURSEID, ");
		selectTrainingCompanyCourseUsersSQL.append("       TCC.TRAININGCOMPANYID, ");
		selectTrainingCompanyCourseUsersSQL.append("       TCC.TRAININGCOURSEID, ");
    selectTrainingCompanyCourseUsersSQL.append("       TCC.NAME, ");
    selectTrainingCompanyCourseUsersSQL.append("       TCC.DISPLAYORDER, ");
    selectTrainingCompanyCourseUsersSQL.append("       TCC.ONLINE, ");
		selectTrainingCompanyCourseUsersSQL.append("       TCC.CREATIONTIMESTAMP, ");
		selectTrainingCompanyCourseUsersSQL.append("       TCC.AUDITORID, ");
		selectTrainingCompanyCourseUsersSQL.append("       TCC.AUDITTIMESTAMP, ");
		selectTrainingCompanyCourseUsersSQL.append("       TCC.ACTIVE, ");
		selectTrainingCompanyCourseUsersSQL.append("       TCC.NOOFCHANGES, ");
		selectTrainingCompanyCourseUsersSQL.append("       C.NAME AS TRAININGCOURSENAME, ");
		selectTrainingCompanyCourseUsersSQL.append("       C.CODE AS TRAININGCOURSECODE, ");
		selectTrainingCompanyCourseUsersSQL.append("       TC.NAME AS TRAININGCOMPANYNAME, ");
		selectTrainingCompanyCourseUsersSQL.append("       TC.CODE AS TRAININGCOMPANYCODE ");
		selectTrainingCompanyCourseUsersSQL.append("FROM TRAININGCOMPANYCOURSE TCC, ");
		selectTrainingCompanyCourseUsersSQL.append("     TRAININGCOURSE C, ");
		selectTrainingCompanyCourseUsersSQL.append("     TRAININGCOMPANY TC ");
		selectTrainingCompanyCourseUsersSQL.append("WHERE C.TRAININGCOURSEID = TCC.TRAININGCOURSEID ");
		selectTrainingCompanyCourseUsersSQL.append("AND C.ACTIVE = TRUE ");
		selectTrainingCompanyCourseUsersSQL.append("AND TC.TRAININGCOMPANYID = TCC.TRAININGCOMPANYID ");
		selectTrainingCompanyCourseUsersSQL.append("AND TC.ACTIVE = TRUE ");
		// Get select TrainingCompanyCourseUser SQL.
		selectTrainingCompanyCourseUserSQL = new StringBuffer(selectTrainingCompanyCourseUsersSQL);
		selectTrainingCompanyCourseUserSQL.append("AND TCC.TRAININGCOMPANYCOURSEID = ^ ");
		// Get select TrainingCompanyCourseUsers for TrainingCompany SQL.
		selectTrainingCompanyCourseUsersForTrainingCompanySQL = new StringBuffer(selectTrainingCompanyCourseUsersSQL);
		selectTrainingCompanyCourseUsersForTrainingCompanySQL.append("AND TCC.TRAININGCOMPANYID = ^ ");
		// Active ONLY
		selectActiveTrainingCompanyCourseUsersForTrainingCompanySQL = new StringBuffer(selectTrainingCompanyCourseUsersForTrainingCompanySQL);
		selectActiveTrainingCompanyCourseUsersForTrainingCompanySQL.append("AND TCC.ACTIVE = TRUE ");
		// Get select TrainingCompanyCourseUsers for TrainingCourse SQL.
		selectTrainingCompanyCourseUsersForTrainingSQL = new StringBuffer(selectTrainingCompanyCourseUsersSQL);
		selectTrainingCompanyCourseUsersForTrainingSQL.append("AND TCC.TRAININGCOURSEID = ^ ");
		// Active ONLY
		selectActiveTrainingCompanyCourseUsersForTrainingSQL = new StringBuffer(selectTrainingCompanyCourseUsersForTrainingSQL);
		selectActiveTrainingCompanyCourseUsersForTrainingSQL.append("AND TCC.ACTIVE = TRUE ");

		// ORDER BY clauses
		selectTrainingCompanyCourseUsersForTrainingCompanySQL.append("ORDER BY TCC.DISPLAYORDER, TCC.NAME ");
		selectActiveTrainingCompanyCourseUsersForTrainingCompanySQL.append("ORDER BY TCC.DISPLAYORDER, TCC.NAME ");
    selectTrainingCompanyCourseUsersForTrainingSQL.append("ORDER BY TC.DISPLAYORDER, TC.NAME ");
    selectActiveTrainingCompanyCourseUsersForTrainingSQL.append("ORDER BY TC.DISPLAYORDER, TC.NAME ");

	}

  /**
   * Adds the SQL for the InNameGroup part of the full statement.
   * Eg. Adds to selectTrainingCompanyCourseUsersForTrainingSQL to become selectTrainingCompanyCourseUsersForTrainingInNameGroupSQL.
   *  
   * @param sourceSQL The original SQL to be added to.
   */
  private static StringBuffer addInNameGroupSQL(StringBuffer sourceSQL)
  {
    StringBuffer targetSQL = new StringBuffer(sourceSQL);
    targetSQL.append("AND SUBSTRING(UPPER(TC.NAME), 1, 1) IN (^) ");
    return targetSQL;
  }
  
	public int insertTrainingCompanyCourse(TrainingCompanyCourse trainingCompanyCourse, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertTrainingCompanyCourseSQL.toString());
		// Replace the parameters with supplied values.
		trainingCompanyCourse.setTrainingCompanyCourseId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "trainingCompanyCourse"));
		Utilities.replace(sql, trainingCompanyCourse.getTrainingCompanyCourseId());
		Utilities.replace(sql, trainingCompanyCourse.getTrainingCompanyId());
		Utilities.replace(sql, trainingCompanyCourse.getTrainingCourseId());
    Utilities.replaceAndQuote(sql, trainingCompanyCourse.getName());
    Utilities.replace(sql, trainingCompanyCourse.getOnline());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteTrainingCompanyCourse(Integer trainingCompanyCourseId, Integer noOfChanges, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteTrainingCompanyCourseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, trainingCompanyCourseId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateTrainingCompanyCourse(TrainingCompanyCourse trainingCompanyCourse, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateTrainingCompanyCourseSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, trainingCompanyCourse.getName());
    Utilities.replace(sql, trainingCompanyCourse.getOnline());
    Utilities.replace(sql, trainingCompanyCourse.getDisplayOrder());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, trainingCompanyCourse.getTrainingCompanyCourseId());
		Utilities.replace(sql, trainingCompanyCourse.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTrainingCompany(Integer trainingCompanyId) 
	{
		return getTrainingCompanyCourseUsersForTrainingCompany(trainingCompanyId, true);
	}

	public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTrainingCompany(Integer trainingCompanyId, boolean showOnlyActive) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = null;
		if (showOnlyActive) 
		{
			sql = new StringBuffer(selectActiveTrainingCompanyCourseUsersForTrainingCompanySQL.toString());
		}
		else 
		{
			sql = new StringBuffer(selectTrainingCompanyCourseUsersForTrainingCompanySQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingCompanyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCompanyCourseUser.class.getName());
	}

	public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTraining(Integer trainingId) 
	{
		return getTrainingCompanyCourseUsersForTraining(trainingId, true);
	}

	public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTraining(Integer trainingId, boolean showOnlyActive) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = null;
		if (showOnlyActive) 
		{
			sql = new StringBuffer(selectActiveTrainingCompanyCourseUsersForTrainingSQL.toString());
		}
		else 
		{
			sql = new StringBuffer(selectTrainingCompanyCourseUsersForTrainingSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCompanyCourseUser.class.getName());

	}

  public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTrainingInNameGroup(Integer trainingId, String indexLetter) 
  {
    return getTrainingCompanyCourseUsersForTraining(trainingId, true);
  }

	public TrainingCompanyCourse getTrainingCompanyCourse(Integer trainingCompanyCourseId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCompanyCourseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingCompanyCourseId);
		TrainingCompanyCourse trainingCompanyCourse = (TrainingCompanyCourse)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCompanyCourse.class.getName());
		return trainingCompanyCourse;
	}

	public TrainingCompanyCourse getTrainingCompanyCourseForTrainingCompanyAndTraining(Integer trainingCompanyId, Integer trainingId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCompanyCourseForTrainingCompanyAndTrainingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingCompanyId);
		Utilities.replace(sql, trainingId);
		TrainingCompanyCourse trainingCompanyCourse = (TrainingCompanyCourse) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCompanyCourse.class.getName());
		return trainingCompanyCourse;
	}
	
  public TrainingCompanyCourseUser getTrainingCompanyCourseUser(Integer trainingCompanyCourseId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectTrainingCompanyCourseUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, trainingCompanyCourseId);
    TrainingCompanyCourseUser trainingCompanyCourseUser = (TrainingCompanyCourseUser)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCompanyCourseUser.class.getName());
    return trainingCompanyCourseUser;
  }
		
}
