package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ClientAgencyJobProfile;
import com.helmet.bean.ClientAgencyJobProfileUser;
import com.helmet.bean.ClientAgencyJobProfileUserEntity;
import com.helmet.persistence.ClientAgencyJobProfileDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultClientAgencyJobProfileDAO extends JdbcDaoSupport implements ClientAgencyJobProfileDAO {

	private static StringBuffer insertClientAgencyJobProfileSQL;

	private static StringBuffer updateClientAgencyJobProfileSQL;

	private static StringBuffer deleteClientAgencyJobProfileSQL;

	private static StringBuffer selectClientAgencyJobProfileSQL;

	private static StringBuffer selectClientAgencyJobProfileUsersSQL;

	private static StringBuffer selectClientAgencyJobProfileUserSQL;

	private static StringBuffer selectClientAgencyJobProfileUsersForJobProfileSQL;

	private static StringBuffer selectClientAgencyJobProfileUsersForJobProfileAndAgencySQL;

	public static void init() {
		// Get insert ClientAgencyJobProfile SQL.
		insertClientAgencyJobProfileSQL = new StringBuffer();
		insertClientAgencyJobProfileSQL.append("INSERT INTO CLIENTAGENCYJOBPROFILE ");
		insertClientAgencyJobProfileSQL.append("(  ");
		insertClientAgencyJobProfileSQL.append("  CLIENTAGENCYJOBPROFILEID, ");
		insertClientAgencyJobProfileSQL.append("  CLIENTAGENCYID, ");
		insertClientAgencyJobProfileSQL.append("  JOBPROFILEID, ");
		insertClientAgencyJobProfileSQL.append("  CREATIONTIMESTAMP, ");
		insertClientAgencyJobProfileSQL.append("  AUDITORID, ");
		insertClientAgencyJobProfileSQL.append("  AUDITTIMESTAMP ");
		insertClientAgencyJobProfileSQL.append(")  ");
		insertClientAgencyJobProfileSQL.append("VALUES  ");
		insertClientAgencyJobProfileSQL.append("(  ");
		insertClientAgencyJobProfileSQL.append("  ^, ");
		insertClientAgencyJobProfileSQL.append("  ^, ");
		insertClientAgencyJobProfileSQL.append("  ^, ");
		insertClientAgencyJobProfileSQL.append("  ^, ");
		insertClientAgencyJobProfileSQL.append("  ^, ");
		insertClientAgencyJobProfileSQL.append("  ^ ");
		insertClientAgencyJobProfileSQL.append(")  ");
		// Get update ClientAgencyJobProfile SQL.
		updateClientAgencyJobProfileSQL = new StringBuffer();
		updateClientAgencyJobProfileSQL.append("UPDATE CLIENTAGENCYJOBPROFILE ");
		updateClientAgencyJobProfileSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateClientAgencyJobProfileSQL.append("     PERCENTAGE = ^, ");
		updateClientAgencyJobProfileSQL.append("     SENDEMAILADDRESS = ^, ");
		updateClientAgencyJobProfileSQL.append("     MASTERVENDORNAME = ^, ");
		updateClientAgencyJobProfileSQL.append("     AUDITORID = ^, ");
		updateClientAgencyJobProfileSQL.append("     AUDITTIMESTAMP = ^ ");
		updateClientAgencyJobProfileSQL.append("WHERE CLIENTAGENCYJOBPROFILEID = ^ ");
		updateClientAgencyJobProfileSQL.append("AND   NOOFCHANGES = ^ ");
     	// Get delete ClientAgencyJobProfile SQL.
		deleteClientAgencyJobProfileSQL = new StringBuffer();
		deleteClientAgencyJobProfileSQL.append("UPDATE CLIENTAGENCYJOBPROFILE ");
		deleteClientAgencyJobProfileSQL.append("SET ACTIVE = FALSE, ");
		deleteClientAgencyJobProfileSQL.append("    AUDITORID = ^, ");
		deleteClientAgencyJobProfileSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteClientAgencyJobProfileSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteClientAgencyJobProfileSQL.append("WHERE CLIENTAGENCYJOBPROFILEID = ^ ");
		deleteClientAgencyJobProfileSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ClientAgencyJobProfile SQL.
		selectClientAgencyJobProfileSQL = new StringBuffer();
		selectClientAgencyJobProfileSQL.append("SELECT CLIENTAGENCYJOBPROFILEID, ");
		selectClientAgencyJobProfileSQL.append("       CLIENTAGENCYID, ");
		selectClientAgencyJobProfileSQL.append("       JOBPROFILEID, ");
		selectClientAgencyJobProfileSQL.append("       PERCENTAGE, ");
		selectClientAgencyJobProfileSQL.append("       SENDEMAILADDRESS, ");
		selectClientAgencyJobProfileSQL.append("       MASTERVENDORNAME, ");
		selectClientAgencyJobProfileSQL.append("       CREATIONTIMESTAMP, ");
		selectClientAgencyJobProfileSQL.append("       AUDITORID, ");
		selectClientAgencyJobProfileSQL.append("       AUDITTIMESTAMP, ");
		selectClientAgencyJobProfileSQL.append("       ACTIVE, ");
		selectClientAgencyJobProfileSQL.append("       NOOFCHANGES ");
		selectClientAgencyJobProfileSQL.append("FROM CLIENTAGENCYJOBPROFILE ");
		selectClientAgencyJobProfileSQL.append("WHERE CLIENTAGENCYJOBPROFILEID = ^ ");
		// Get select ClientAgencyJobProfileUsers SQL.
		selectClientAgencyJobProfileUsersSQL = new StringBuffer();
		selectClientAgencyJobProfileUsersSQL.append("SELECT CAJP.CLIENTAGENCYJOBPROFILEID, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.CLIENTAGENCYID, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.JOBPROFILEID, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.SENDEMAILADDRESS, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.MASTERVENDORNAME, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.PERCENTAGE, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.CREATIONTIMESTAMP, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.AUDITORID, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.AUDITTIMESTAMP, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.ACTIVE, ");
		selectClientAgencyJobProfileUsersSQL.append("       CAJP.NOOFCHANGES, ");
		selectClientAgencyJobProfileUsersSQL.append("       A.AGENCYID, ");
		selectClientAgencyJobProfileUsersSQL.append("       A.NAME AS AGENCYNAME ");
		selectClientAgencyJobProfileUsersSQL.append("FROM CLIENTAGENCYJOBPROFILE CAJP, ");
		selectClientAgencyJobProfileUsersSQL.append("     CLIENTAGENCY CA, ");
		selectClientAgencyJobProfileUsersSQL.append("     AGENCY A ");
		selectClientAgencyJobProfileUsersSQL.append("WHERE CA.CLIENTAGENCYID = CAJP.CLIENTAGENCYID ");
		selectClientAgencyJobProfileUsersSQL.append("AND CA.ACTIVE = TRUE ");
		selectClientAgencyJobProfileUsersSQL.append("AND A.AGENCYID = CA.AGENCYID ");
		selectClientAgencyJobProfileUsersSQL.append("AND A.ACTIVE = TRUE ");
		// Get select ClientAgencyJobProfileUser SQL.
		selectClientAgencyJobProfileUserSQL = new StringBuffer(selectClientAgencyJobProfileUsersSQL);
		selectClientAgencyJobProfileUserSQL.append("AND CAJP.CLIENTAGENCYJOBPROFILEID = ^ ");
		// Get select ClientAgencyJobProfileUsers for JobProfile SQL.
		selectClientAgencyJobProfileUsersForJobProfileSQL = new StringBuffer(selectClientAgencyJobProfileUsersSQL);
		selectClientAgencyJobProfileUsersForJobProfileSQL.append("AND CAJP.JOBPROFILEID = ^ ");
		selectClientAgencyJobProfileUsersForJobProfileSQL.append("AND CAJP.ACTIVE = TRUE ");

		// Get select ClientAgencyJobProfileUsers for JobProfile and Agency SQL.
		selectClientAgencyJobProfileUsersForJobProfileAndAgencySQL = new StringBuffer(selectClientAgencyJobProfileUsersForJobProfileSQL);
		selectClientAgencyJobProfileUsersForJobProfileAndAgencySQL.append("AND A.AGENCYID = ^ ");
		
		// order by clauses
		selectClientAgencyJobProfileUsersForJobProfileSQL.append("ORDER BY CAJP.PERCENTAGE DESC ");
	}

	public int insertClientAgencyJobProfile(ClientAgencyJobProfile clientAgencyJobProfile, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertClientAgencyJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		clientAgencyJobProfile.setClientAgencyJobProfileId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "clientAgencyJobProfile"));
		Utilities.replace(sql, clientAgencyJobProfile.getClientAgencyJobProfileId());
		Utilities.replace(sql, clientAgencyJobProfile.getClientAgencyId());
		Utilities.replace(sql, clientAgencyJobProfile.getJobProfileId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteClientAgencyJobProfile(Integer clientAgencyJobProfileId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteClientAgencyJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientAgencyJobProfileId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsersForJobProfile(Integer jobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileUsersForJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfileUser.class.getName());

	}

	public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsersForJobProfileAndAgency(Integer jobProfileId, Integer agencyId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileUsersForJobProfileAndAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfileUser.class.getName());

	}

	public ClientAgencyJobProfile getClientAgencyJobProfile(Integer clientAgencyJobProfileId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyJobProfileId);
		return (ClientAgencyJobProfile) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfile.class.getName());
	}

	public ClientAgencyJobProfileUserEntity getClientAgencyJobProfileUserEntity(Integer clientAgencyJobProfileId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyJobProfileUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyJobProfileId);
		return (ClientAgencyJobProfileUserEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyJobProfileUserEntity.class.getName());
	}

	public int updateClientAgencyJobProfile(ClientAgencyJobProfile clientAgencyJobProfile, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateClientAgencyJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyJobProfile.getPercentage());
		Utilities.replaceAndQuoteNullable(sql, clientAgencyJobProfile.getSendEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, clientAgencyJobProfile.getMasterVendorName());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientAgencyJobProfile.getClientAgencyJobProfileId());
		Utilities.replace(sql, clientAgencyJobProfile.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

}
