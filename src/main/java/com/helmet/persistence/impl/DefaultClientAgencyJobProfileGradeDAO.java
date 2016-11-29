package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.persistence.ClientAgencyJobProfileGradeDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultClientAgencyJobProfileGradeDAO extends JdbcDaoSupport implements ClientAgencyJobProfileGradeDAO {

	private static StringBuffer insertClientAgencyJobProfileGradeSQL;

	private static StringBuffer updateClientAgencyJobProfileGradeSQL;

	private static StringBuffer deleteClientAgencyJobProfileGradeSQL;

	private static StringBuffer selectClientAgencyJobProfileGradesSQL;

	private static StringBuffer selectClientAgencyJobProfileGradesForJobProfileSQL;

  private static StringBuffer selectClientAgencyJobProfileGradesForJobProfileAndAgencySQL;

  private static StringBuffer selectClientAgencyJobProfileGradeUsersForClientAndAgencyAndJobProfileSQL;

	private static StringBuffer selectClientAgencyJobProfileGradeSQL;

	private static StringBuffer selectClientAgencyJobProfileGradeUsersSQL;

	private static StringBuffer selectClientAgencyJobProfileGradeUserSQL;

	private static StringBuffer selectClientAgencyJobProfileGradeUsersForClientAgencyJobProfileSQL;

	public static void init() {
		// Get insert ClientAgencyJobProfileGrade SQL.
		insertClientAgencyJobProfileGradeSQL = new StringBuffer();
		insertClientAgencyJobProfileGradeSQL.append("INSERT INTO CLIENTAGENCYJOBPROFILEGRADE ");
		insertClientAgencyJobProfileGradeSQL.append("(  ");
		insertClientAgencyJobProfileGradeSQL.append("  CLIENTAGENCYJOBPROFILEGRADEID, ");
		insertClientAgencyJobProfileGradeSQL.append("  CLIENTAGENCYJOBPROFILEID, ");
		insertClientAgencyJobProfileGradeSQL.append("  GRADEID, ");
		insertClientAgencyJobProfileGradeSQL.append("  CREATIONTIMESTAMP, ");
		insertClientAgencyJobProfileGradeSQL.append("  AUDITORID, ");
		insertClientAgencyJobProfileGradeSQL.append("  AUDITTIMESTAMP ");
		insertClientAgencyJobProfileGradeSQL.append(")  ");
		insertClientAgencyJobProfileGradeSQL.append("VALUES  ");
		insertClientAgencyJobProfileGradeSQL.append("(  ");
		insertClientAgencyJobProfileGradeSQL.append("  ^, ");
		insertClientAgencyJobProfileGradeSQL.append("  ^, ");
		insertClientAgencyJobProfileGradeSQL.append("  ^, ");
		insertClientAgencyJobProfileGradeSQL.append("  ^, ");
		insertClientAgencyJobProfileGradeSQL.append("  ^, ");
		insertClientAgencyJobProfileGradeSQL.append("  ^ ");
		insertClientAgencyJobProfileGradeSQL.append(")  ");
		// Get update ClientAgencyJobProfileGrade SQL.
		updateClientAgencyJobProfileGradeSQL = new StringBuffer();
		updateClientAgencyJobProfileGradeSQL.append("UPDATE CLIENTAGENCYJOBPROFILEGRADE ");
		updateClientAgencyJobProfileGradeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateClientAgencyJobProfileGradeSQL.append("     RATE = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     PAYRATE = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     WTDPERCENTAGE = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     NIPERCENTAGE = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     CHARGERATEVATRATE = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     COMMISSIONVATRATE = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     PAYRATEVATRATE = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     WTDVATRATE = ^, ");
    updateClientAgencyJobProfileGradeSQL.append("     NIVATRATE = ^, ");
    updateClientAgencyJobProfileGradeSQL.append("     AVAILABLE = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     AUDITORID = ^, ");
		updateClientAgencyJobProfileGradeSQL.append("     AUDITTIMESTAMP = ^ ");
		updateClientAgencyJobProfileGradeSQL.append("WHERE CLIENTAGENCYJOBPROFILEGRADEID = ^ ");
		updateClientAgencyJobProfileGradeSQL.append("AND   NOOFCHANGES = ^ ");
     	// Get delete ClientAgencyJobProfileGrade SQL.
		deleteClientAgencyJobProfileGradeSQL = new StringBuffer();
		deleteClientAgencyJobProfileGradeSQL.append("UPDATE CLIENTAGENCYJOBPROFILEGRADE ");
		deleteClientAgencyJobProfileGradeSQL.append("SET ACTIVE = FALSE, ");
		deleteClientAgencyJobProfileGradeSQL.append("    AUDITORID = ^, ");
		deleteClientAgencyJobProfileGradeSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteClientAgencyJobProfileGradeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteClientAgencyJobProfileGradeSQL.append("WHERE CLIENTAGENCYJOBPROFILEGRADEID = ^ ");
		deleteClientAgencyJobProfileGradeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ClientAgencyJobProfileGrades SQL.
		selectClientAgencyJobProfileGradesSQL = new StringBuffer();
		selectClientAgencyJobProfileGradesSQL.append("SELECT CAJPG.CLIENTAGENCYJOBPROFILEGRADEID, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.CLIENTAGENCYJOBPROFILEID, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.GRADEID, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.RATE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.PAYRATE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.WTDPERCENTAGE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.NIPERCENTAGE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.CHARGERATEVATRATE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.COMMISSIONVATRATE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.PAYRATEVATRATE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.WTDVATRATE, ");
    selectClientAgencyJobProfileGradesSQL.append("       CAJPG.NIVATRATE, ");
    selectClientAgencyJobProfileGradesSQL.append("       CAJPG.AVAILABLE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.CREATIONTIMESTAMP, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.AUDITORID, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.AUDITTIMESTAMP, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.ACTIVE, ");
		selectClientAgencyJobProfileGradesSQL.append("       CAJPG.NOOFCHANGES ");
		//
		selectClientAgencyJobProfileGradeUsersSQL = new StringBuffer(selectClientAgencyJobProfileGradesSQL);
        //
		selectClientAgencyJobProfileGradesSQL.append("FROM CLIENTAGENCYJOBPROFILEGRADE CAJPG ");
		// Get select ClientAgencyJobProfileGrade SQL.
		selectClientAgencyJobProfileGradeSQL = new StringBuffer(selectClientAgencyJobProfileGradesSQL);
		selectClientAgencyJobProfileGradeSQL.append("WHERE CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = ^ ");
        //
		selectClientAgencyJobProfileGradeUsersSQL.append(", ");
		selectClientAgencyJobProfileGradeUsersSQL.append("A.NAME AS AGENCYNAME, ");
		selectClientAgencyJobProfileGradeUsersSQL.append("CAJP.PERCENTAGE, ");
		selectClientAgencyJobProfileGradeUsersSQL.append("CAJP.MASTERVENDORNAME, ");
		selectClientAgencyJobProfileGradeUsersSQL.append("G.NAME AS GRADENAME ");
		selectClientAgencyJobProfileGradeUsersSQL.append("FROM CLIENTAGENCYJOBPROFILEGRADE CAJPG, ");
		selectClientAgencyJobProfileGradeUsersSQL.append("     CLIENTAGENCYJOBPROFILE CAJP, ");
		selectClientAgencyJobProfileGradeUsersSQL.append("     CLIENTAGENCY CA, ");
		selectClientAgencyJobProfileGradeUsersSQL.append("     AGENCY A, ");
		selectClientAgencyJobProfileGradeUsersSQL.append("     GRADE G ");
		selectClientAgencyJobProfileGradeUsersSQL.append("WHERE G.GRADEID = CAJPG.GRADEID ");
		selectClientAgencyJobProfileGradeUsersSQL.append("AND G.ACTIVE = TRUE ");
		selectClientAgencyJobProfileGradeUsersSQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = CAJPG.CLIENTAGENCYJOBPROFILEID ");
		selectClientAgencyJobProfileGradeUsersSQL.append("AND CAJP.ACTIVE = TRUE ");
		selectClientAgencyJobProfileGradeUsersSQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
		selectClientAgencyJobProfileGradeUsersSQL.append("AND CA.ACTIVE = TRUE ");
		selectClientAgencyJobProfileGradeUsersSQL.append("AND A.AGENCYID = CA.AGENCYID ");
		selectClientAgencyJobProfileGradeUsersSQL.append("AND A.ACTIVE = TRUE ");
		// Get select ClientAgencyJobProfileGradeUser 
		selectClientAgencyJobProfileGradeUserSQL = new StringBuffer(selectClientAgencyJobProfileGradeUsersSQL);
		selectClientAgencyJobProfileGradeUserSQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEGRADEID = ^ "); 
		// Get select ClientAgencyJobProfileGradeUsers for ClientAgencyJobProfile SQL.
		selectClientAgencyJobProfileGradeUsersForClientAgencyJobProfileSQL = new StringBuffer(selectClientAgencyJobProfileGradeUsersSQL);
		selectClientAgencyJobProfileGradeUsersForClientAgencyJobProfileSQL.append("AND CAJPG.ACTIVE = TRUE ");
		selectClientAgencyJobProfileGradeUsersForClientAgencyJobProfileSQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEID = ^ "); 
		// Get select ClientAgencyJobProfileGrades for JobProfile SQL.
		selectClientAgencyJobProfileGradesForJobProfileSQL = new StringBuffer(selectClientAgencyJobProfileGradesSQL);
		selectClientAgencyJobProfileGradesForJobProfileSQL.append(", ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("CLIENTAGENCYJOBPROFILE CAJP, ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("CLIENTAGENCY CA ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("WHERE CAJPG.ACTIVE = TRUE ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("AND CAJPG.CLIENTAGENCYJOBPROFILEID = CAJP.CLIENTAGENCYJOBPROFILEID ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("AND CAJP.ACTIVE = TRUE ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("AND CAJP.JOBPROFILEID = ^ ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("AND CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("AND CA.ACTIVE = TRUE ");

		// Get select ClientAgencyJobProfileGrades for JobProfile and Agency SQL.
		selectClientAgencyJobProfileGradesForJobProfileAndAgencySQL = new StringBuffer(selectClientAgencyJobProfileGradesForJobProfileSQL);
		selectClientAgencyJobProfileGradesForJobProfileAndAgencySQL.append("AND CA.AGENCYID = ^ "); 
    // Get select ClientAgencyJobProfileGradeUsers for Client, Agency & JobProfile SQL.
    selectClientAgencyJobProfileGradeUsersForClientAndAgencyAndJobProfileSQL = new StringBuffer(selectClientAgencyJobProfileGradeUsersSQL);
    selectClientAgencyJobProfileGradeUsersForClientAndAgencyAndJobProfileSQL.append("AND CAJPG.ACTIVE = TRUE ");
    selectClientAgencyJobProfileGradeUsersForClientAndAgencyAndJobProfileSQL.append("AND CA.CLIENTID = ^ "); 
    selectClientAgencyJobProfileGradeUsersForClientAndAgencyAndJobProfileSQL.append("AND CA.AGENCYID = ^ "); 
    selectClientAgencyJobProfileGradeUsersForClientAndAgencyAndJobProfileSQL.append("AND CAJP.JOBPROFILEID = ^ ");
    
		// order by clauses
  selectClientAgencyJobProfileGradeUsersForClientAndAgencyAndJobProfileSQL.append("ORDER BY G.NAME ");
    selectClientAgencyJobProfileGradeUsersForClientAgencyJobProfileSQL.append("ORDER BY G.NAME ");
		selectClientAgencyJobProfileGradesForJobProfileSQL.append("ORDER BY CAJPG.CLIENTAGENCYJOBPROFILEID, CAJPG.RATE ");
	}

	public int insertClientAgencyJobProfileGrade(ClientAgencyJobProfileGrade clientAgencyJobProfileGrade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertClientAgencyJobProfileGradeSQL.toString());
		// Replace the parameters with supplied values.
		clientAgencyJobProfileGrade.setClientAgencyJobProfileGradeId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "clientAgencyJobProfileGrade"));
		Utilities.replace(sql, clientAgencyJobProfileGrade.getClientAgencyJobProfileGradeId());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getClientAgencyJobProfileId());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getGradeId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteClientAgencyJobProfileGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientAgencyJobProfileGradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public ClientAgencyJobProfileGrade getClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyJobProfileGradeId);
		return (ClientAgencyJobProfileGrade) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfileGrade.class.getName());
	}

	public ClientAgencyJobProfileGradeUser getClientAgencyJobProfileGradeUser(Integer clientAgencyJobProfileGradeId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileGradeUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyJobProfileGradeId);
		return (ClientAgencyJobProfileGradeUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfileGradeUser.class.getName());
	}

	public int updateClientAgencyJobProfileGrade(ClientAgencyJobProfileGrade clientAgencyJobProfileGrade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateClientAgencyJobProfileGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyJobProfileGrade.getRate());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getPayRate());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getWtdPercentage());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getNiPercentage());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getChargeRateVatRate());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getCommissionVatRate());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getPayRateVatRate());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getWtdVatRate());
    Utilities.replace(sql, clientAgencyJobProfileGrade.getNiVatRate());
    Utilities.replace(sql, clientAgencyJobProfileGrade.getAvailable());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getClientAgencyJobProfileGradeId());
		Utilities.replace(sql, clientAgencyJobProfileGrade.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(Integer clientAgencyJobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileGradeUsersForClientAgencyJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyJobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfileGradeUser.class.getName());

	}

	public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(Integer clientId, Integer agencyId, Integer jobProfileId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileGradeUsersForClientAndAgencyAndJobProfileSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, clientId);
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, jobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ClientAgencyJobProfileGradeUser.class.getName());
	}

	public List<ClientAgencyJobProfileGrade> getClientAgencyJobProfileGradesForJobProfile(Integer jobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileGradesForJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfileGrade.class.getName());

	}

	public List<ClientAgencyJobProfileGrade> getClientAgencyJobProfileGradesForJobProfileAndAgency(Integer jobProfileId, Integer agencyId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileGradesForJobProfileAndAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfileGrade.class.getName());

	}

}
