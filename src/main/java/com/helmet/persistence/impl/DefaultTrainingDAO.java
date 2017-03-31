package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.TrainingCourse;
import com.helmet.persistence.TrainingDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultTrainingDAO extends JdbcDaoSupport implements TrainingDAO 
{

	private static StringBuffer insertTrainingCourseSQL;

	private static StringBuffer updateTrainingCourseSQL;

  private static StringBuffer updateTrainingCourseDisplayOrderSQL;

	private static StringBuffer deleteTrainingCourseSQL;

	private static StringBuffer selectTrainingCourseSQL;

	private static StringBuffer selectTrainingCourseForNameSQL;

	private static StringBuffer selectTrainingCourseForCodeSQL;

	private static StringBuffer selectTrainingCoursesSQL;

	private static StringBuffer selectActiveTrainingCoursesSQL;

  private static StringBuffer selectTrainingCoursesNotForDisciplineCategorySQL;

	public static void init() 
  {
		// Get insert TrainingCourse SQL.
		insertTrainingCourseSQL = new StringBuffer();
		insertTrainingCourseSQL.append("INSERT INTO TRAININGCOURSE ");
		insertTrainingCourseSQL.append("(  ");
		insertTrainingCourseSQL.append("  TRAININGCOURSEID, ");
		insertTrainingCourseSQL.append("  CODE, ");
    insertTrainingCourseSQL.append("  NAME, ");
    insertTrainingCourseSQL.append("  DISPLAYORDER, ");
    insertTrainingCourseSQL.append("  CREATIONTIMESTAMP, ");
    insertTrainingCourseSQL.append("  AUDITORID, ");
    insertTrainingCourseSQL.append("  AUDITTIMESTAMP ");
		insertTrainingCourseSQL.append(")  ");
		insertTrainingCourseSQL.append("VALUES  ");
		insertTrainingCourseSQL.append("(  ");
		insertTrainingCourseSQL.append("  ^, ");
    insertTrainingCourseSQL.append("  ^, ");
    insertTrainingCourseSQL.append("  ^, ");
    insertTrainingCourseSQL.append("  ^, ");
    insertTrainingCourseSQL.append("  ^, ");
    insertTrainingCourseSQL.append("  ^, ");
		insertTrainingCourseSQL.append("  ^ ");
		insertTrainingCourseSQL.append(")  ");
		// Get update TrainingCourseCourse SQL.
    // NOTE. Updates DisplayOrder too...
		updateTrainingCourseSQL = new StringBuffer();
		updateTrainingCourseSQL.append("UPDATE TRAININGCOURSE ");
		updateTrainingCourseSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateTrainingCourseSQL.append("     CODE = ^, ");
    updateTrainingCourseSQL.append("     NAME = ^, ");
    updateTrainingCourseSQL.append("     DISPLAYORDER = ^, ");
    updateTrainingCourseSQL.append("     AUDITORID = ^, ");
    updateTrainingCourseSQL.append("     AUDITTIMESTAMP = ^ ");
		updateTrainingCourseSQL.append("WHERE TRAININGCOURSEID = ^ ");
		updateTrainingCourseSQL.append("AND   NOOFCHANGES = ^ ");
    // Get updateTrainingCourseDisplayOrder SQL.
    updateTrainingCourseDisplayOrderSQL = new StringBuffer();
    updateTrainingCourseDisplayOrderSQL.append("UPDATE TRAININGCOURSE ");
    updateTrainingCourseDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
    updateTrainingCourseDisplayOrderSQL.append("    AUDITORID = ^, ");
    updateTrainingCourseDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
    updateTrainingCourseDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateTrainingCourseDisplayOrderSQL.append("WHERE TRAININGCOURSEID = ^ ");
    updateTrainingCourseDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete TrainingCourseCourse SQL.
		deleteTrainingCourseSQL = new StringBuffer();
		deleteTrainingCourseSQL.append("UPDATE TRAININGCOURSE ");
		deleteTrainingCourseSQL.append("SET ACTIVE = FALSE, ");
    deleteTrainingCourseSQL.append("    AUDITORID = ^, ");
    deleteTrainingCourseSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteTrainingCourseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteTrainingCourseSQL.append("WHERE TRAININGCOURSEID = ^ ");
		deleteTrainingCourseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select TrainingCourses SQL.
		selectTrainingCoursesSQL = new StringBuffer();
		selectTrainingCoursesSQL.append("SELECT T.TRAININGCOURSEID, ");
		selectTrainingCoursesSQL.append("       T.CODE, ");
    selectTrainingCoursesSQL.append("       T.NAME, ");
    selectTrainingCoursesSQL.append("       T.DISPLAYORDER, ");
    selectTrainingCoursesSQL.append("       T.CREATIONTIMESTAMP, ");
    selectTrainingCoursesSQL.append("       T.AUDITORID, ");
    selectTrainingCoursesSQL.append("       T.AUDITTIMESTAMP, ");
    selectTrainingCoursesSQL.append("       T.ACTIVE, ");
		selectTrainingCoursesSQL.append("       T.NOOFCHANGES ");
		selectTrainingCoursesSQL.append("FROM TRAININGCOURSE T ");
    // Get select TrainingCourses not for DisciplineCategory SQL.
    selectTrainingCoursesNotForDisciplineCategorySQL = new StringBuffer(selectTrainingCoursesSQL);
    selectTrainingCoursesNotForDisciplineCategorySQL.append("WHERE T.ACTIVE = TRUE ");
    selectTrainingCoursesNotForDisciplineCategorySQL.append("AND NOT EXISTS ");
    selectTrainingCoursesNotForDisciplineCategorySQL.append("( ");
    selectTrainingCoursesNotForDisciplineCategorySQL.append(" SELECT NULL ");
    selectTrainingCoursesNotForDisciplineCategorySQL.append(" FROM DISCIPLINECATEGORYTRAINING DCT ");
    selectTrainingCoursesNotForDisciplineCategorySQL.append(" WHERE DCT.DISCIPLINECATEGORYID = ^ ");
    selectTrainingCoursesNotForDisciplineCategorySQL.append(" AND DCT.ACTIVE = TRUE ");
    selectTrainingCoursesNotForDisciplineCategorySQL.append(" AND DCT.TRAININGCOURSEID = T.TRAININGCOURSEID ");
    selectTrainingCoursesNotForDisciplineCategorySQL.append(") ");
		// Get select TrainingCourse SQL.
		selectTrainingCourseSQL = new StringBuffer(selectTrainingCoursesSQL);
		selectTrainingCourseSQL.append("WHERE TRAININGCOURSEID = ^ ");
    // Get select TrainingCourse for Name SQL.
    selectTrainingCourseForNameSQL = new StringBuffer(selectTrainingCoursesSQL);
    selectTrainingCourseForNameSQL.append("WHERE NAME = ^ ");
    // Get select TrainingCourse for Iso Code SQL.
    selectTrainingCourseForCodeSQL = new StringBuffer(selectTrainingCoursesSQL);
    selectTrainingCourseForCodeSQL.append("WHERE CODE = ^ ");
		// Get select Active TrainingCourses SQL.
		selectActiveTrainingCoursesSQL = new StringBuffer(selectTrainingCoursesSQL);
    selectActiveTrainingCoursesSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveTrainingCoursesSQL.append("ORDER BY DISPLAYORDER, NAME ");
    // Put order by on now...
    selectTrainingCoursesSQL.append("ORDER BY DISPLAYORDER, NAME ");

	}

	public int insertTrainingCourse(TrainingCourse trainingCourse, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertTrainingCourseSQL.toString());
		// Replace the parameters with supplied values.
		trainingCourse.setTrainingCourseId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "trainingCourse"));
		Utilities.replace(sql, trainingCourse.getTrainingCourseId());
		Utilities.replaceAndQuote(sql, trainingCourse.getCode());
    Utilities.replaceAndQuote(sql, trainingCourse.getName());
    Utilities.replace(sql, trainingCourse.getDisplayOrder());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateTrainingCourse(TrainingCourse trainingCourse, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateTrainingCourseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, trainingCourse.getCode());
    Utilities.replaceAndQuote(sql, trainingCourse.getName());
    Utilities.replace(sql, trainingCourse.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, trainingCourse.getTrainingCourseId());
		Utilities.replace(sql, trainingCourse.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateTrainingCourseDisplayOrder(TrainingCourse trainingCourse, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateTrainingCourseDisplayOrderSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, trainingCourse.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, trainingCourse.getTrainingCourseId());
    Utilities.replace(sql, trainingCourse.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteTrainingCourse(Integer trainingCourseId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteTrainingCourseSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, trainingCourseId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public TrainingCourse getTrainingCourse(Integer trainingCourseId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCourseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingCourseId);
		return (TrainingCourse) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCourse.class.getName());
	}

	public TrainingCourse getTrainingCourseForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCourseForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (TrainingCourse) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), TrainingCourse.class.getName());
	}

	public TrainingCourse getTrainingCourseForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCourseForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (TrainingCourse) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCourse.class.getName());
	}

	public List<TrainingCourse> getTrainingCourses() 
  {
		return getTrainingCourses(false);
	}

	public List<TrainingCourse> getTrainingCourses(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveTrainingCoursesSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectTrainingCoursesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), TrainingCourse.class.getName());
	}

	public List<TrainingCourse> getActiveTrainingCourses() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveTrainingCoursesSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), TrainingCourse.class.getName());

	}

  public List<TrainingCourse> getTrainingCoursesNotForDisciplineCategory(Integer disciplineCategoryId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectTrainingCoursesNotForDisciplineCategorySQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, disciplineCategoryId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCourse.class.getName());
  }

}
