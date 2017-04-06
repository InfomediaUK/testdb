package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.DisciplineCategoryTraining;
import com.helmet.bean.DisciplineCategoryTrainingUser;
import com.helmet.persistence.DisciplineCategoryTrainingDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultDisciplineCategoryTrainingDAO extends JdbcDaoSupport implements DisciplineCategoryTrainingDAO 
{

	private static StringBuffer insertDisciplineCategoryTrainingSQL;

	private static StringBuffer deleteDisciplineCategoryTrainingSQL;

	private static StringBuffer updateDisciplineCategoryTrainingSQL;

	private static StringBuffer selectDisciplineCategoryTrainingsSQL;

	private static StringBuffer selectDisciplineCategoryTrainingSQL;

	private static StringBuffer selectDisciplineCategoryTrainingForDisciplineCategoryAndTrainingSQL;

	private static StringBuffer selectDisciplineCategoryTrainingUsersSQL;

	private static StringBuffer selectDisciplineCategoryTrainingUserSQL;

	private static StringBuffer selectDisciplineCategoryTrainingUsersForDisciplineCategorySQL;

	private static StringBuffer selectActiveDisciplineCategoryTrainingUsersForDisciplineCategorySQL;

  private static StringBuffer selectDisciplineCategoryTrainingUsersForTrainingSQL;

  private static StringBuffer selectActiveDisciplineCategoryTrainingUsersForTrainingSQL;

//  private static StringBuffer selectDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL;
//
//  private static StringBuffer selectActiveDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL;

	public static void init() {
		// Get insert DisciplineCategoryTraining SQL.
		insertDisciplineCategoryTrainingSQL = new StringBuffer();
		insertDisciplineCategoryTrainingSQL.append("INSERT INTO DISCIPLINECATEGORYTRAINING ");
		insertDisciplineCategoryTrainingSQL.append("(  ");
		insertDisciplineCategoryTrainingSQL.append("  DISCIPLINECATEGORYTRAININGID, ");
		insertDisciplineCategoryTrainingSQL.append("  DISCIPLINECATEGORYID, ");
		insertDisciplineCategoryTrainingSQL.append("  TRAININGCOURSEID, ");
		insertDisciplineCategoryTrainingSQL.append("  CREATIONTIMESTAMP, ");
		insertDisciplineCategoryTrainingSQL.append("  AUDITORID, ");
		insertDisciplineCategoryTrainingSQL.append("  AUDITTIMESTAMP ");
		insertDisciplineCategoryTrainingSQL.append(")  ");
		insertDisciplineCategoryTrainingSQL.append("VALUES  ");
		insertDisciplineCategoryTrainingSQL.append("(  ");
		insertDisciplineCategoryTrainingSQL.append("  ^, ");
		insertDisciplineCategoryTrainingSQL.append("  ^, ");
		insertDisciplineCategoryTrainingSQL.append("  ^, ");
		insertDisciplineCategoryTrainingSQL.append("  ^, ");
		insertDisciplineCategoryTrainingSQL.append("  ^, ");
		insertDisciplineCategoryTrainingSQL.append("  ^ ");
		insertDisciplineCategoryTrainingSQL.append(")  ");
		// Get delete DisciplineCategoryTraining SQL.
		deleteDisciplineCategoryTrainingSQL = new StringBuffer();
		deleteDisciplineCategoryTrainingSQL.append("UPDATE DISCIPLINECATEGORYTRAINING ");
		deleteDisciplineCategoryTrainingSQL.append("SET ACTIVE = FALSE, ");
		deleteDisciplineCategoryTrainingSQL.append("    AUDITORID = ^, ");
		deleteDisciplineCategoryTrainingSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteDisciplineCategoryTrainingSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteDisciplineCategoryTrainingSQL.append("WHERE DISCIPLINECATEGORYTRAININGID = ^ ");
		deleteDisciplineCategoryTrainingSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update DisciplineCategoryTraining SQL.
		updateDisciplineCategoryTrainingSQL = new StringBuffer();
		updateDisciplineCategoryTrainingSQL.append("UPDATE DISCIPLINECATEGORYTRAINING ");
		updateDisciplineCategoryTrainingSQL.append("SET MANDATORY = ^, ");
		updateDisciplineCategoryTrainingSQL.append("    AUDITORID = ^, ");
		updateDisciplineCategoryTrainingSQL.append("    AUDITTIMESTAMP = ^, ");
		updateDisciplineCategoryTrainingSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateDisciplineCategoryTrainingSQL.append("WHERE DISCIPLINECATEGORYTRAININGID = ^ ");
		updateDisciplineCategoryTrainingSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select DisciplineCategoryTrainings SQL.
		selectDisciplineCategoryTrainingsSQL = new StringBuffer();
		selectDisciplineCategoryTrainingsSQL.append("SELECT DISCIPLINECATEGORYTRAININGID, ");
		selectDisciplineCategoryTrainingsSQL.append("       DISCIPLINECATEGORYID, ");
		selectDisciplineCategoryTrainingsSQL.append("       TRAININGCOURSEID, ");
		selectDisciplineCategoryTrainingsSQL.append("       MANDATORY, ");
		selectDisciplineCategoryTrainingsSQL.append("       CREATIONTIMESTAMP, ");
		selectDisciplineCategoryTrainingsSQL.append("       AUDITORID, ");
		selectDisciplineCategoryTrainingsSQL.append("       AUDITTIMESTAMP, ");
		selectDisciplineCategoryTrainingsSQL.append("       ACTIVE, ");
		selectDisciplineCategoryTrainingsSQL.append("       NOOFCHANGES ");
		selectDisciplineCategoryTrainingsSQL.append("FROM DISCIPLINECATEGORYTRAINING ");
		// Get select DisciplineCategoryTraining SQL.
		selectDisciplineCategoryTrainingSQL = new StringBuffer(selectDisciplineCategoryTrainingsSQL);
		selectDisciplineCategoryTrainingSQL.append("WHERE DISCIPLINECATEGORYTRAININGID = ^ ");
		// Get select DisciplineCategoryTraining for DisciplineCategory and TrainingCourse SQL.
		selectDisciplineCategoryTrainingForDisciplineCategoryAndTrainingSQL = new StringBuffer(selectDisciplineCategoryTrainingsSQL);
		selectDisciplineCategoryTrainingForDisciplineCategoryAndTrainingSQL.append("WHERE DISCIPLINECATEGORYID = ^ ");
		selectDisciplineCategoryTrainingForDisciplineCategoryAndTrainingSQL.append("AND   TRAININGCOURSEID = ^ ");
		selectDisciplineCategoryTrainingForDisciplineCategoryAndTrainingSQL.append("AND   ACTIVE = TRUE ");
		// Get select DisciplineCategoryTrainingUsers SQL.
		selectDisciplineCategoryTrainingUsersSQL = new StringBuffer();
		selectDisciplineCategoryTrainingUsersSQL.append("SELECT DCT.DISCIPLINECATEGORYTRAININGID, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DCT.DISCIPLINECATEGORYID, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DCT.TRAININGCOURSEID, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DCT.MANDATORY, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DCT.CREATIONTIMESTAMP, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DCT.AUDITORID, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DCT.AUDITTIMESTAMP, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DCT.ACTIVE, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DCT.NOOFCHANGES, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       T.NAME AS TRAININGNAME, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       T.CODE AS TRAININGCODE, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DC.NAME AS DISCIPLINECATEGORYNAME, ");
		selectDisciplineCategoryTrainingUsersSQL.append("       DC.CODE AS DISCIPLINECATEGORYCODE ");
		selectDisciplineCategoryTrainingUsersSQL.append("FROM DISCIPLINECATEGORYTRAINING DCT, ");
		selectDisciplineCategoryTrainingUsersSQL.append("     TRAININGCOURSE T, ");
		selectDisciplineCategoryTrainingUsersSQL.append("     DISCIPLINECATEGORY DC ");
		selectDisciplineCategoryTrainingUsersSQL.append("WHERE T.TRAININGCOURSEID = DCT.TRAININGCOURSEID ");
		selectDisciplineCategoryTrainingUsersSQL.append("AND T.ACTIVE = TRUE ");
		selectDisciplineCategoryTrainingUsersSQL.append("AND DC.DISCIPLINECATEGORYID = DCT.DISCIPLINECATEGORYID ");
		selectDisciplineCategoryTrainingUsersSQL.append("AND DC.ACTIVE = TRUE ");
		// Get select DisciplineCategoryTrainingUser SQL.
		selectDisciplineCategoryTrainingUserSQL = new StringBuffer(selectDisciplineCategoryTrainingUsersSQL);
		selectDisciplineCategoryTrainingUserSQL.append("AND DCT.DISCIPLINECATEGORYTRAININGID = ^ ");
		// Get select DisciplineCategoryTrainingUsers for DisciplineCategory SQL.
		selectDisciplineCategoryTrainingUsersForDisciplineCategorySQL = new StringBuffer(selectDisciplineCategoryTrainingUsersSQL);
		selectDisciplineCategoryTrainingUsersForDisciplineCategorySQL.append("AND DCT.DISCIPLINECATEGORYID = ^ ");
		// Active ONLY
		selectActiveDisciplineCategoryTrainingUsersForDisciplineCategorySQL = new StringBuffer(selectDisciplineCategoryTrainingUsersForDisciplineCategorySQL);
		selectActiveDisciplineCategoryTrainingUsersForDisciplineCategorySQL.append("AND DCT.ACTIVE = TRUE ");
		// Get select DisciplineCategoryTrainingUsers for TrainingCourse SQL.
		selectDisciplineCategoryTrainingUsersForTrainingSQL = new StringBuffer(selectDisciplineCategoryTrainingUsersSQL);
		selectDisciplineCategoryTrainingUsersForTrainingSQL.append("AND DCT.TRAININGCOURSEID = ^ ");
		// Active ONLY
		selectActiveDisciplineCategoryTrainingUsersForTrainingSQL = new StringBuffer(selectDisciplineCategoryTrainingUsersForTrainingSQL);
		selectActiveDisciplineCategoryTrainingUsersForTrainingSQL.append("AND DCT.ACTIVE = TRUE ");
    // Get select DisciplineCategoryTrainingUsers for TrainingCourse for Name starting with letters in supplied list SQL.
//    selectDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL = addInNameGroupSQL(selectDisciplineCategoryTrainingUsersForTrainingSQL);
//    // Get select Active DisciplineCategoryTrainingUsers for TrainingCourse for Name starting with letters in supplied list SQL.
//    selectActiveDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL = new StringBuffer(selectDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL);
//    selectActiveDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL.append("AND A.ACTIVE = TRUE ");

		// ORDER BY clauses
		selectDisciplineCategoryTrainingUsersForDisciplineCategorySQL.append("ORDER BY T.DISPLAYORDER, T.NAME ");
		selectActiveDisciplineCategoryTrainingUsersForDisciplineCategorySQL.append("ORDER BY T.DISPLAYORDER, T.NAME ");
    selectDisciplineCategoryTrainingUsersForTrainingSQL.append("ORDER BY DC.DISPLAYORDER, DC.NAME ");
    selectActiveDisciplineCategoryTrainingUsersForTrainingSQL.append("ORDER BY DC.DISPLAYORDER, DC.NAME ");
//    selectDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL.append("ORDER BY DC.DISPLAYORDER, DC.NAME ");
//    selectActiveDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL.append("ORDER BY DC.DISPLAYORDER, DC.NAME ");

	}

  /**
   * Adds the SQL for the InNameGroup part of the full statement.
   * Eg. Adds to selectDisciplineCategoryTrainingUsersForTrainingSQL to become selectDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL.
   *  
   * @param sourceSQL The original SQL to be added to.
   */
  private static StringBuffer addInNameGroupSQL(StringBuffer sourceSQL)
  {
    StringBuffer targetSQL = new StringBuffer(sourceSQL);
    targetSQL.append("AND SUBSTRING(UPPER(DC.NAME), 1, 1) IN (^) ");
    return targetSQL;
  }
  
	public int insertDisciplineCategoryTraining(DisciplineCategoryTraining disciplineCategoryTraining, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertDisciplineCategoryTrainingSQL.toString());
		// Replace the parameters with supplied values.
		disciplineCategoryTraining.setDisciplineCategoryTrainingId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "disciplineCategoryTraining"));
		Utilities.replace(sql, disciplineCategoryTraining.getDisciplineCategoryTrainingId());
		Utilities.replace(sql, disciplineCategoryTraining.getDisciplineCategoryId());
		Utilities.replace(sql, disciplineCategoryTraining.getTrainingCourseId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteDisciplineCategoryTraining(Integer disciplineCategoryTrainingId, Integer noOfChanges, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteDisciplineCategoryTrainingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, disciplineCategoryTrainingId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateDisciplineCategoryTraining(DisciplineCategoryTraining disciplineCategoryTraining, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateDisciplineCategoryTrainingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, disciplineCategoryTraining.getMandatory());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, disciplineCategoryTraining.getDisciplineCategoryTrainingId());
		Utilities.replace(sql, disciplineCategoryTraining.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public List<DisciplineCategoryTrainingUser> getDisciplineCategoryTrainingUsersForDisciplineCategory(Integer disciplineCategoryId) 
	{
		return getDisciplineCategoryTrainingUsersForDisciplineCategory(disciplineCategoryId, true);
	}

	public List<DisciplineCategoryTrainingUser> getDisciplineCategoryTrainingUsersForDisciplineCategory(Integer disciplineCategoryId, boolean showOnlyActive) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = null;
		if (showOnlyActive) 
		{
			sql = new StringBuffer(selectActiveDisciplineCategoryTrainingUsersForDisciplineCategorySQL.toString());
		}
		else 
		{
			sql = new StringBuffer(selectDisciplineCategoryTrainingUsersForDisciplineCategorySQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, disciplineCategoryId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryTrainingUser.class.getName());
	}

	public List<DisciplineCategoryTrainingUser> getDisciplineCategoryTrainingUsersForTraining(Integer trainingId) 
	{
		return getDisciplineCategoryTrainingUsersForTraining(trainingId, true);
	}

	public List<DisciplineCategoryTrainingUser> getDisciplineCategoryTrainingUsersForTraining(Integer trainingId, boolean showOnlyActive) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = null;
		if (showOnlyActive) 
		{
			sql = new StringBuffer(selectActiveDisciplineCategoryTrainingUsersForTrainingSQL.toString());
		}
		else 
		{
			sql = new StringBuffer(selectDisciplineCategoryTrainingUsersForTrainingSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryTrainingUser.class.getName());

	}

//  public List<DisciplineCategoryTrainingUser> getDisciplineCategoryTrainingUsersForTrainingInNameGroup(Integer trainingId, String indexLetter) 
//  {
//    return getDisciplineCategoryTrainingUsersForTraining(trainingId, true);
//  }
//
//  public List<DisciplineCategoryTrainingUser> getDisciplineCategoryTrainingUsersForTrainingInNameGroup(Integer trainingId, String indexLetter, boolean showOnlyActive) 
//  {
//    // Create a new local StringBuffer containing the parameterised SQL.
//    StringBuffer sql = null;
//    if (showOnlyActive) 
//    {
//      sql = new StringBuffer(selectActiveDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL.toString());
//    }
//    else 
//    {
//      sql = new StringBuffer(selectDisciplineCategoryTrainingUsersForTrainingInNameGroupSQL.toString()); 
//    }
//    // Replace the parameters with supplied values.
//    Utilities.replace(sql, trainingId);
//    Utilities.replaceAndQuote(sql, indexLetter);
//    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryTrainingUser.class.getName());
//  }
//
	public DisciplineCategoryTraining getDisciplineCategoryTraining(Integer disciplineCategoryTrainingId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectDisciplineCategoryTrainingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, disciplineCategoryTrainingId);
		DisciplineCategoryTraining disciplineCategoryTraining = (DisciplineCategoryTraining)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryTraining.class.getName());
		return disciplineCategoryTraining;
	}

	public DisciplineCategoryTraining getDisciplineCategoryTrainingForDisciplineCategoryAndTraining(Integer disciplineCategoryId, Integer trainingId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectDisciplineCategoryTrainingForDisciplineCategoryAndTrainingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, disciplineCategoryId);
		Utilities.replace(sql, trainingId);
		DisciplineCategoryTraining disciplineCategoryTraining = (DisciplineCategoryTraining) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryTraining.class.getName());
		return disciplineCategoryTraining;
	}
	
  public DisciplineCategoryTrainingUser getDisciplineCategoryTrainingUser(Integer disciplineCategoryTrainingId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectDisciplineCategoryTrainingUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, disciplineCategoryTrainingId);
    DisciplineCategoryTrainingUser disciplineCategoryTrainingUser = (DisciplineCategoryTrainingUser)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryTrainingUser.class.getName());
    return disciplineCategoryTrainingUser;
  }
		
}
