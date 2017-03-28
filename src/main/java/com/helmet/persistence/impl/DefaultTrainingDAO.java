package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Training;
import com.helmet.persistence.TrainingDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultTrainingDAO extends JdbcDaoSupport implements TrainingDAO 
{

	private static StringBuffer insertTrainingSQL;

	private static StringBuffer updateTrainingSQL;

  private static StringBuffer updateTrainingDisplayOrderSQL;

	private static StringBuffer deleteTrainingSQL;

	private static StringBuffer selectTrainingSQL;

	private static StringBuffer selectTrainingForNameSQL;

	private static StringBuffer selectTrainingForCodeSQL;

	private static StringBuffer selectTrainingsSQL;

	private static StringBuffer selectActiveTrainingsSQL;

	public static void init() 
  {
		// Get insert Training SQL.
		insertTrainingSQL = new StringBuffer();
		insertTrainingSQL.append("INSERT INTO TRAINING ");
		insertTrainingSQL.append("(  ");
		insertTrainingSQL.append("  TRAININGID, ");
		insertTrainingSQL.append("  CODE, ");
    insertTrainingSQL.append("  NAME, ");
    insertTrainingSQL.append("  DISPLAYORDER, ");
    insertTrainingSQL.append("  CREATIONTIMESTAMP, ");
    insertTrainingSQL.append("  AUDITORID, ");
    insertTrainingSQL.append("  AUDITTIMESTAMP ");
		insertTrainingSQL.append(")  ");
		insertTrainingSQL.append("VALUES  ");
		insertTrainingSQL.append("(  ");
		insertTrainingSQL.append("  ^, ");
    insertTrainingSQL.append("  ^, ");
    insertTrainingSQL.append("  ^, ");
    insertTrainingSQL.append("  ^, ");
    insertTrainingSQL.append("  ^, ");
    insertTrainingSQL.append("  ^, ");
		insertTrainingSQL.append("  ^ ");
		insertTrainingSQL.append(")  ");
		// Get update Training SQL.
    // NOTE. Updates DisplayOrder too...
		updateTrainingSQL = new StringBuffer();
		updateTrainingSQL.append("UPDATE TRAINING ");
		updateTrainingSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateTrainingSQL.append("     CODE = ^, ");
    updateTrainingSQL.append("     NAME = ^, ");
    updateTrainingSQL.append("     DISPLAYORDER = ^, ");
    updateTrainingSQL.append("     AUDITORID = ^, ");
    updateTrainingSQL.append("     AUDITTIMESTAMP = ^ ");
		updateTrainingSQL.append("WHERE TRAININGID = ^ ");
		updateTrainingSQL.append("AND   NOOFCHANGES = ^ ");
    // Get updateTrainingDisplayOrder SQL.
    updateTrainingDisplayOrderSQL = new StringBuffer();
    updateTrainingDisplayOrderSQL.append("UPDATE TRAINING ");
    updateTrainingDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
    updateTrainingDisplayOrderSQL.append("    AUDITORID = ^, ");
    updateTrainingDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
    updateTrainingDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateTrainingDisplayOrderSQL.append("WHERE TRAININGID = ^ ");
    updateTrainingDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Training SQL.
		deleteTrainingSQL = new StringBuffer();
		deleteTrainingSQL.append("UPDATE TRAINING ");
		deleteTrainingSQL.append("SET ACTIVE = FALSE, ");
    deleteTrainingSQL.append("    AUDITORID = ^, ");
    deleteTrainingSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteTrainingSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteTrainingSQL.append("WHERE TRAININGID = ^ ");
		deleteTrainingSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Trainings SQL.
		selectTrainingsSQL = new StringBuffer();
		selectTrainingsSQL.append("SELECT TRAININGID, ");
		selectTrainingsSQL.append("       CODE, ");
    selectTrainingsSQL.append("       NAME, ");
    selectTrainingsSQL.append("       DISPLAYORDER, ");
    selectTrainingsSQL.append("       CREATIONTIMESTAMP, ");
    selectTrainingsSQL.append("       AUDITORID, ");
    selectTrainingsSQL.append("       AUDITTIMESTAMP, ");
    selectTrainingsSQL.append("       ACTIVE, ");
		selectTrainingsSQL.append("       NOOFCHANGES ");
		selectTrainingsSQL.append("FROM TRAINING ");
		// Get select Training SQL.
		selectTrainingSQL = new StringBuffer(selectTrainingsSQL);
		selectTrainingSQL.append("WHERE TRAININGID = ^ ");
    // Get select Training for Name SQL.
    selectTrainingForNameSQL = new StringBuffer(selectTrainingsSQL);
    selectTrainingForNameSQL.append("WHERE NAME = ^ ");
    // Get select Training for Iso Code SQL.
    selectTrainingForCodeSQL = new StringBuffer(selectTrainingsSQL);
    selectTrainingForCodeSQL.append("WHERE CODE = ^ ");
		// Get select Active Trainings SQL.
		selectActiveTrainingsSQL = new StringBuffer(selectTrainingsSQL);
    selectActiveTrainingsSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveTrainingsSQL.append("ORDER BY DISPLAYORDER, NAME ");
    // Put order by on now...
    selectTrainingsSQL.append("ORDER BY DISPLAYORDER, NAME ");

	}

	public int insertTraining(Training training, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertTrainingSQL.toString());
		// Replace the parameters with supplied values.
		training.setTrainingId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "training"));
		Utilities.replace(sql, training.getTrainingId());
		Utilities.replaceAndQuote(sql, training.getCode());
    Utilities.replaceAndQuote(sql, training.getName());
    Utilities.replace(sql, training.getDisplayOrder());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateTraining(Training training, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateTrainingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, training.getCode());
    Utilities.replaceAndQuote(sql, training.getName());
    Utilities.replace(sql, training.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, training.getTrainingId());
		Utilities.replace(sql, training.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateTrainingDisplayOrder(Training training, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateTrainingDisplayOrderSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, training.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, training.getTrainingId());
    Utilities.replace(sql, training.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteTraining(Integer trainingId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteTrainingSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, trainingId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public Training getTraining(Integer trainingId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingId);
		return (Training) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Training.class.getName());
	}

	public Training getTrainingForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (Training) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Training.class.getName());
	}

	public Training getTrainingForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (Training) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Training.class.getName());
	}

	public List<Training> getTrainings() 
  {
		return getTrainings(false);
	}

	public List<Training> getTrainings(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveTrainingsSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectTrainingsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), Training.class.getName());
	}

	public List<Training> getActiveTrainings() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveTrainingsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), Training.class.getName());

	}

}
