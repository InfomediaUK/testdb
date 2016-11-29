package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientAgencyUser;
import com.helmet.bean.ClientAgencyUserEntity;
import com.helmet.persistence.ClientAgencyDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultClientAgencyDAO extends JdbcDaoSupport implements ClientAgencyDAO {

	private static StringBuffer insertClientAgencySQL;

	private static StringBuffer deleteClientAgencySQL;

	private static StringBuffer updateClientAgencySQL;

	private static StringBuffer selectClientAgenciesSQL;

	private static StringBuffer selectClientAgencySQL;

	private static StringBuffer selectClientAgencyForClientAndAgencySQL;

	private static StringBuffer selectClientAgencyUsersSQL;

	private static StringBuffer selectClientAgencyUserSQL;

	private static StringBuffer selectClientAgencyUsersForClientSQL;

	private static StringBuffer selectActiveClientAgencyUsersForClientSQL;

  private static StringBuffer selectClientAgencyUsersForAgencySQL;

  private static StringBuffer selectActiveClientAgencyUsersForAgencySQL;

  private static StringBuffer selectClientAgencyUsersForAgencyInNameGroupSQL;

  private static StringBuffer selectActiveClientAgencyUsersForAgencyInNameGroupSQL;

	private static StringBuffer selectClientAgencyUsersNotForJobProfileSQL;

	public static void init() {
		// Get insert ClientAgency SQL.
		insertClientAgencySQL = new StringBuffer();
		insertClientAgencySQL.append("INSERT INTO CLIENTAGENCY ");
		insertClientAgencySQL.append("(  ");
		insertClientAgencySQL.append("  CLIENTAGENCYID, ");
		insertClientAgencySQL.append("  CLIENTID, ");
		insertClientAgencySQL.append("  AGENCYID, ");
		insertClientAgencySQL.append("  CREATIONTIMESTAMP, ");
		insertClientAgencySQL.append("  AUDITORID, ");
		insertClientAgencySQL.append("  AUDITTIMESTAMP ");
		insertClientAgencySQL.append(")  ");
		insertClientAgencySQL.append("VALUES  ");
		insertClientAgencySQL.append("(  ");
		insertClientAgencySQL.append("  ^, ");
		insertClientAgencySQL.append("  ^, ");
		insertClientAgencySQL.append("  ^, ");
		insertClientAgencySQL.append("  ^, ");
		insertClientAgencySQL.append("  ^, ");
		insertClientAgencySQL.append("  ^ ");
		insertClientAgencySQL.append(")  ");
		// Get delete ClientAgency SQL.
		deleteClientAgencySQL = new StringBuffer();
		deleteClientAgencySQL.append("UPDATE CLIENTAGENCY ");
		deleteClientAgencySQL.append("SET ACTIVE = FALSE, ");
		deleteClientAgencySQL.append("    AUDITORID = ^, ");
		deleteClientAgencySQL.append("    AUDITTIMESTAMP = ^, ");
		deleteClientAgencySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteClientAgencySQL.append("WHERE CLIENTAGENCYID = ^ ");
		deleteClientAgencySQL.append("AND   NOOFCHANGES = ^ ");
		// Get update ClientAgency SQL.
		updateClientAgencySQL = new StringBuffer();
		updateClientAgencySQL.append("UPDATE CLIENTAGENCY ");
		updateClientAgencySQL.append("SET FEEPERSHIFT = ^, ");
		updateClientAgencySQL.append("    FEEPERHOUR = ^, ");
		updateClientAgencySQL.append("    FEEPERCENTAGE = ^, ");
		updateClientAgencySQL.append("    PAYMENTTERMS = ^, ");
		updateClientAgencySQL.append("    AUDITORID = ^, ");
		updateClientAgencySQL.append("    AUDITTIMESTAMP = ^, ");
		updateClientAgencySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateClientAgencySQL.append("WHERE CLIENTAGENCYID = ^ ");
		updateClientAgencySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ClientAgencies SQL.
		selectClientAgenciesSQL = new StringBuffer();
		selectClientAgenciesSQL.append("SELECT CLIENTAGENCYID, ");
		selectClientAgenciesSQL.append("       CLIENTID, ");
		selectClientAgenciesSQL.append("       AGENCYID, ");
		selectClientAgenciesSQL.append("       FEEPERSHIFT, ");
		selectClientAgenciesSQL.append("       FEEPERHOUR, ");
		selectClientAgenciesSQL.append("       FEEPERCENTAGE, ");
		selectClientAgenciesSQL.append("       PAYMENTTERMS, ");
		selectClientAgenciesSQL.append("       CREATIONTIMESTAMP, ");
		selectClientAgenciesSQL.append("       AUDITORID, ");
		selectClientAgenciesSQL.append("       AUDITTIMESTAMP, ");
		selectClientAgenciesSQL.append("       ACTIVE, ");
		selectClientAgenciesSQL.append("       NOOFCHANGES ");
		selectClientAgenciesSQL.append("FROM CLIENTAGENCY ");
		// Get select ClientAgency SQL.
		selectClientAgencySQL = new StringBuffer(selectClientAgenciesSQL);
		selectClientAgencySQL.append("WHERE CLIENTAGENCYID = ^ ");
		// Get select ClientAgency for Client and Agency SQL.
		selectClientAgencyForClientAndAgencySQL = new StringBuffer(selectClientAgenciesSQL);
		selectClientAgencyForClientAndAgencySQL.append("WHERE CLIENTID = ^ ");
		selectClientAgencyForClientAndAgencySQL.append("AND   AGENCYID = ^ ");
		selectClientAgencyForClientAndAgencySQL.append("AND   ACTIVE = TRUE ");
		// Get select ClientAgencyUsers SQL.
		selectClientAgencyUsersSQL = new StringBuffer();
		selectClientAgencyUsersSQL.append("SELECT CA.CLIENTAGENCYID, ");
		selectClientAgencyUsersSQL.append("       CA.CLIENTID, ");
		selectClientAgencyUsersSQL.append("       CA.AGENCYID, ");
		selectClientAgencyUsersSQL.append("       CA.FEEPERSHIFT, ");
		selectClientAgencyUsersSQL.append("       CA.FEEPERHOUR, ");
		selectClientAgencyUsersSQL.append("       CA.FEEPERCENTAGE, ");
		selectClientAgencyUsersSQL.append("       CA.PAYMENTTERMS, ");
		selectClientAgencyUsersSQL.append("       CA.CREATIONTIMESTAMP, ");
		selectClientAgencyUsersSQL.append("       CA.AUDITORID, ");
		selectClientAgencyUsersSQL.append("       CA.AUDITTIMESTAMP, ");
		selectClientAgencyUsersSQL.append("       CA.ACTIVE, ");
		selectClientAgencyUsersSQL.append("       CA.NOOFCHANGES, ");
		selectClientAgencyUsersSQL.append("       A.NAME AS AGENCYNAME, ");
		selectClientAgencyUsersSQL.append("       A.CODE AS AGENCYCODE, ");
		selectClientAgencyUsersSQL.append("       A.ADDRESS1 AS AGENCYADDRESS1, ");
		selectClientAgencyUsersSQL.append("       A.ADDRESS2 AS AGENCYADDRESS2, ");
		selectClientAgencyUsersSQL.append("       A.ADDRESS3 AS AGENCYADDRESS3, ");
		selectClientAgencyUsersSQL.append("       A.ADDRESS4 AS AGENCYADDRESS4, ");
		selectClientAgencyUsersSQL.append("       A.POSTALCODE AS AGENCYPOSTALCODE, ");
		selectClientAgencyUsersSQL.append("       A.COUNTRYID AS AGENCYCOUNTRYID, ");
		selectClientAgencyUsersSQL.append("       C.NAME AS CLIENTNAME, ");
		selectClientAgencyUsersSQL.append("       C.CODE AS CLIENTCODE, ");
		selectClientAgencyUsersSQL.append("       C.ADDRESS1 AS CLIENTADDRESS1, ");
		selectClientAgencyUsersSQL.append("       C.ADDRESS2 AS CLIENTADDRESS2, ");
		selectClientAgencyUsersSQL.append("       C.ADDRESS3 AS CLIENTADDRESS3, ");
		selectClientAgencyUsersSQL.append("       C.ADDRESS4 AS CLIENTADDRESS4, ");
		selectClientAgencyUsersSQL.append("       C.POSTALCODE AS CLIENTPOSTALCODE, ");
		selectClientAgencyUsersSQL.append("       C.COUNTRYID AS CLIENTCOUNTRYID ");
		selectClientAgencyUsersSQL.append("FROM CLIENTAGENCY CA, ");
		selectClientAgencyUsersSQL.append("     AGENCY A, ");
		selectClientAgencyUsersSQL.append("     CLIENT C ");
		selectClientAgencyUsersSQL.append("WHERE A.AGENCYID = CA.AGENCYID ");
		selectClientAgencyUsersSQL.append("AND A.ACTIVE = TRUE ");
		selectClientAgencyUsersSQL.append("AND C.CLIENTID = CA.CLIENTID ");
		selectClientAgencyUsersSQL.append("AND C.ACTIVE = TRUE ");
		// Get select ClientAgencyUser SQL.
		selectClientAgencyUserSQL = new StringBuffer(selectClientAgencyUsersSQL);
		selectClientAgencyUserSQL.append("AND CA.CLIENTAGENCYID = ^ ");
		// Get select ClientAgencyUsers for Client SQL.
		selectClientAgencyUsersForClientSQL = new StringBuffer(selectClientAgencyUsersSQL);
		selectClientAgencyUsersForClientSQL.append("AND CA.CLIENTID = ^ ");
		// Active ONLY
		selectActiveClientAgencyUsersForClientSQL = new StringBuffer(selectClientAgencyUsersForClientSQL);
		selectActiveClientAgencyUsersForClientSQL.append("AND CA.ACTIVE = TRUE ");
		// Get select ClientAgencyUsers for Agency SQL.
		selectClientAgencyUsersForAgencySQL = new StringBuffer(selectClientAgencyUsersSQL);
		selectClientAgencyUsersForAgencySQL.append("AND CA.AGENCYID = ^ ");
		// Active ONLY
		selectActiveClientAgencyUsersForAgencySQL = new StringBuffer(selectClientAgencyUsersForAgencySQL);
		selectActiveClientAgencyUsersForAgencySQL.append("AND CA.ACTIVE = TRUE ");
    // Get select ClientAgencyUsers for Agency for Name starting with letters in supplied list SQL.
    selectClientAgencyUsersForAgencyInNameGroupSQL = addInNameGroupSQL(selectClientAgencyUsersForAgencySQL);
    // Get select Active ClientAgencyUsers for Agency for Name starting with letters in supplied list SQL.
    selectActiveClientAgencyUsersForAgencyInNameGroupSQL = new StringBuffer(selectClientAgencyUsersForAgencyInNameGroupSQL);
    selectActiveClientAgencyUsersForAgencyInNameGroupSQL.append("AND A.ACTIVE = TRUE ");
    // Get select ClientAgencyUsers not for Job Profile SQL.
		selectClientAgencyUsersNotForJobProfileSQL = new StringBuffer(selectClientAgencyUsersSQL);
		selectClientAgencyUsersNotForJobProfileSQL.append("AND CA.CLIENTID = ^ ");
		selectClientAgencyUsersNotForJobProfileSQL.append("AND CA.ACTIVE = TRUE ");
		selectClientAgencyUsersNotForJobProfileSQL.append("AND NOT EXISTS ");
		selectClientAgencyUsersNotForJobProfileSQL.append("( ");
		selectClientAgencyUsersNotForJobProfileSQL.append(" SELECT NULL ");
		selectClientAgencyUsersNotForJobProfileSQL.append(" FROM CLIENTAGENCYJOBPROFILE CAJP ");
		selectClientAgencyUsersNotForJobProfileSQL.append(" WHERE CAJP.JOBPROFILEID = ^ ");
		selectClientAgencyUsersNotForJobProfileSQL.append(" AND CAJP.ACTIVE = TRUE ");
		selectClientAgencyUsersNotForJobProfileSQL.append(" AND CAJP.CLIENTAGENCYID = CA.CLIENTAGENCYID ");
		selectClientAgencyUsersNotForJobProfileSQL.append(") ");

		// ORDER BY clauses
		selectClientAgencyUsersNotForJobProfileSQL.append("ORDER BY A.DISPLAYORDER, A.NAME ");
		selectClientAgencyUsersForClientSQL.append("ORDER BY A.DISPLAYORDER, A.NAME ");
		selectActiveClientAgencyUsersForClientSQL.append("ORDER BY A.DISPLAYORDER, A.NAME ");
    selectClientAgencyUsersForAgencySQL.append("ORDER BY C.DISPLAYORDER, C.NAME ");
    selectActiveClientAgencyUsersForAgencySQL.append("ORDER BY C.DISPLAYORDER, C.NAME ");
    selectClientAgencyUsersForAgencyInNameGroupSQL.append("ORDER BY C.DISPLAYORDER, C.NAME ");
    selectActiveClientAgencyUsersForAgencyInNameGroupSQL.append("ORDER BY C.DISPLAYORDER, C.NAME ");

	}

  /**
   * Adds the SQL for the InNameGroup part of the full statement.
   * Eg. Adds to selectClientAgencyUsersForAgencySQL to become selectClientAgencyUsersForAgencyInNameGroupSQL.
   *  
   * @param sourceSQL The original SQL to be added to.
   */
  private static StringBuffer addInNameGroupSQL(StringBuffer sourceSQL)
  {
    StringBuffer targetSQL = new StringBuffer(sourceSQL);
    targetSQL.append("AND SUBSTRING(UPPER(C.NAME), 1, 1) IN (^) ");
    return targetSQL;
  }
  
	public int insertClientAgency(ClientAgency clientAgency, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertClientAgencySQL.toString());
		// Replace the parameters with supplied values.
		clientAgency.setClientAgencyId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "clientAgency"));
		Utilities.replace(sql, clientAgency.getClientAgencyId());
		Utilities.replace(sql, clientAgency.getClientId());
		Utilities.replace(sql, clientAgency.getAgencyId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteClientAgency(Integer clientAgencyId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteClientAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientAgencyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateClientAgency(ClientAgency clientAgency, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateClientAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgency.getFeePerShift());
		Utilities.replace(sql, clientAgency.getFeePerHour());
		Utilities.replace(sql, clientAgency.getFeePercentage());
		Utilities.replace(sql, clientAgency.getPaymentTerms());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientAgency.getClientAgencyId());
		Utilities.replace(sql, clientAgency.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<ClientAgencyUser> getClientAgencyUsersForClient(Integer clientId) {
		
		return getClientAgencyUsersForClient(clientId, true);
		
	}

	public List<ClientAgencyUser> getClientAgencyUsersForClient(Integer clientId, boolean showOnlyActive) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveClientAgencyUsersForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectClientAgencyUsersForClientSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyUser.class.getName());

	}

	public List<ClientAgencyUser> getClientAgencyUsersForAgency(Integer agencyId) {
		
		return getClientAgencyUsersForAgency(agencyId, true);
		
	}

	public List<ClientAgencyUser> getClientAgencyUsersForAgency(Integer agencyId, boolean showOnlyActive) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveClientAgencyUsersForAgencySQL.toString());
		}
		else {
			sql = new StringBuffer(selectClientAgencyUsersForAgencySQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyUser.class.getName());

	}

  public List<ClientAgencyUser> getClientAgencyUsersForAgencyInNameGroup(Integer agencyId, String indexLetter) 
  {
    return getClientAgencyUsersForAgency(agencyId, true);
  }

  public List<ClientAgencyUser> getClientAgencyUsersForAgencyInNameGroup(Integer agencyId, String indexLetter, boolean showOnlyActive) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveClientAgencyUsersForAgencyInNameGroupSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectClientAgencyUsersForAgencyInNameGroupSQL.toString()); 
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, indexLetter);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), ClientAgencyUser.class.getName());
  }

  public List<ClientAgencyUser> getClientAgencyUsersNotForJobProfile(Integer clientId, Integer jobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyUsersNotForJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, jobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyUser.class.getName());

	}

	public ClientAgency getClientAgency(Integer clientAgencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyId);
		ClientAgency clientAgency = (ClientAgency) RecordFactory
				.getInstance().get(getJdbcTemplate(), sql.toString(),
						ClientAgency.class.getName());
		return clientAgency;
	}

	public ClientAgency getClientAgencyForClientAndAgency(Integer clientId, Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyForClientAndAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, agencyId);
		ClientAgency clientAgency = (ClientAgency) RecordFactory
				.getInstance().get(getJdbcTemplate(), sql.toString(),
						ClientAgency.class.getName());
		return clientAgency;
	}
	
	public ClientAgencyUserEntity getClientAgencyUserEntity(Integer clientAgencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyId);
		ClientAgencyUserEntity clientAgencyUserEntity = (ClientAgencyUserEntity) RecordFactory
				.getInstance().get(getJdbcTemplate(), sql.toString(),
						ClientAgencyUserEntity.class.getName());
		return clientAgencyUserEntity;
	}

}
