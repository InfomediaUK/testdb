package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Course;
import com.helmet.persistence.CourseDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultCourseDAO extends JdbcDaoSupport implements CourseDAO 
{

	private static StringBuffer insertCourseSQL;

	private static StringBuffer updateCourseSQL;

	private static StringBuffer deleteCourseSQL;

	private static StringBuffer selectCourseSQL;

	private static StringBuffer selectCourseForNameSQL;

	private static StringBuffer selectCourseForCodeSQL;

	private static StringBuffer selectCoursesSQL;

	private static StringBuffer selectActiveCoursesSQL;

	public static void init() 
  {
		// Get insert Course SQL.
		insertCourseSQL = new StringBuffer();
		insertCourseSQL.append("INSERT INTO COURSE ");
		insertCourseSQL.append("(  ");
		insertCourseSQL.append("  COURSEID, ");
		insertCourseSQL.append("  CODE, ");
    insertCourseSQL.append("  NAME, ");
    insertCourseSQL.append("  DESCRIPTION, ");
    insertCourseSQL.append("  CREATIONTIMESTAMP, ");
    insertCourseSQL.append("  AUDITORID, ");
    insertCourseSQL.append("  AUDITTIMESTAMP ");
		insertCourseSQL.append(")  ");
		insertCourseSQL.append("VALUES  ");
		insertCourseSQL.append("(  ");
		insertCourseSQL.append("  ^, ");
    insertCourseSQL.append("  ^, ");
    insertCourseSQL.append("  ^, ");
    insertCourseSQL.append("  ^, ");
    insertCourseSQL.append("  ^, ");
    insertCourseSQL.append("  ^, ");
		insertCourseSQL.append("  ^ ");
		insertCourseSQL.append(")  ");
		// Get update Course SQL.
    // NOTE. Updates Description too...
		updateCourseSQL = new StringBuffer();
		updateCourseSQL.append("UPDATE COURSE ");
		updateCourseSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateCourseSQL.append("     CODE = ^, ");
    updateCourseSQL.append("     NAME = ^, ");
    updateCourseSQL.append("     DESCRIPTION = ^, ");
    updateCourseSQL.append("     AUDITORID = ^, ");
    updateCourseSQL.append("     AUDITTIMESTAMP = ^ ");
		updateCourseSQL.append("WHERE COURSEID = ^ ");
		updateCourseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Course SQL.
		deleteCourseSQL = new StringBuffer();
		deleteCourseSQL.append("UPDATE COURSE ");
		deleteCourseSQL.append("SET ACTIVE = FALSE, ");
    deleteCourseSQL.append("    AUDITORID = ^, ");
    deleteCourseSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteCourseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteCourseSQL.append("WHERE COURSEID = ^ ");
		deleteCourseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Courses SQL.
		selectCoursesSQL = new StringBuffer();
		selectCoursesSQL.append("SELECT COURSEID, ");
		selectCoursesSQL.append("       CODE, ");
    selectCoursesSQL.append("       NAME, ");
    selectCoursesSQL.append("       DESCRIPTION, ");
    selectCoursesSQL.append("       CREATIONTIMESTAMP, ");
    selectCoursesSQL.append("       AUDITORID, ");
    selectCoursesSQL.append("       AUDITTIMESTAMP, ");
    selectCoursesSQL.append("       ACTIVE, ");
		selectCoursesSQL.append("       NOOFCHANGES ");
		selectCoursesSQL.append("FROM COURSE ");
		// Get select Course SQL.
		selectCourseSQL = new StringBuffer(selectCoursesSQL);
		selectCourseSQL.append("WHERE COURSEID = ^ ");
    // Get select Course for Name SQL.
    selectCourseForNameSQL = new StringBuffer(selectCoursesSQL);
    selectCourseForNameSQL.append("WHERE NAME = ^ ");
    // Get select Course for Iso Code SQL.
    selectCourseForCodeSQL = new StringBuffer(selectCoursesSQL);
    selectCourseForCodeSQL.append("WHERE CODE = ^ ");
		// Get select Active Courses SQL.
		selectActiveCoursesSQL = new StringBuffer(selectCoursesSQL);
    selectActiveCoursesSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveCoursesSQL.append("ORDER BY DESCRIPTION, NAME ");
    // Put order by on now...
    selectCoursesSQL.append("ORDER BY DESCRIPTION, NAME ");

	}

	public int insertCourse(Course course, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertCourseSQL.toString());
		// Replace the parameters with supplied values.
		course.setCourseId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "course"));
		Utilities.replace(sql, course.getCourseId());
		Utilities.replaceAndQuote(sql, course.getCode());
    Utilities.replaceAndQuote(sql, course.getName());
    Utilities.replaceAndQuote(sql, course.getDescription());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateCourse(Course course, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateCourseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, course.getCode());
    Utilities.replaceAndQuote(sql, course.getName());
    Utilities.replaceAndQuote(sql, course.getDescription());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, course.getCourseId());
		Utilities.replace(sql, course.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteCourse(Integer courseId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteCourseSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, courseId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public Course getCourse(Integer courseId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectCourseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, courseId);
		return (Course) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Course.class.getName());
	}

	public Course getCourseForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectCourseForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (Course) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Course.class.getName());
	}

	public Course getCourseForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectCourseForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (Course) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Course.class.getName());
	}

	public List<Course> getCourses() 
  {
		return getCourses(false);
	}

	public List<Course> getCourses(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveCoursesSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectCoursesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), Course.class.getName());
	}

	public List<Course> getActiveCourses() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveCoursesSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), Course.class.getName());

	}

}
