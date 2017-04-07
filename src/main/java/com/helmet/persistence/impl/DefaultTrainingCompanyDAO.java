package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.TrainingCompany;
import com.helmet.bean.TrainingCompanyUser;
import com.helmet.bean.TrainingCompanyUserEntity;
import com.helmet.persistence.TrainingCompanyDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultTrainingCompanyDAO extends JdbcDaoSupport implements TrainingCompanyDAO 
{

	private static StringBuffer insertTrainingCompanySQL;

	private static StringBuffer updateTrainingCompanySQL;

	private static StringBuffer updateTrainingCompanyDisplayOrderSQL;

	private static StringBuffer deleteTrainingCompanySQL;

	private static StringBuffer selectTrainingCompanySQL;

	private static StringBuffer selectTrainingCompanyForNameSQL;

	private static StringBuffer selectTrainingCompanyForCodeSQL;

  private static StringBuffer selectTrainingCompaniesSQL;

  private static StringBuffer selectActiveTrainingCompaniesSQL;

	private static StringBuffer selectTrainingCompanyUserSQL;

	private static StringBuffer selectTrainingCompanyUsersSQL;

	private static StringBuffer selectActiveTrainingCompanyUsersSQL;

	public static void init() {
		// Get insert TrainingCompany SQL.
		insertTrainingCompanySQL = new StringBuffer();
		insertTrainingCompanySQL.append("INSERT INTO TRAININGCOMPANY ");
		insertTrainingCompanySQL.append("(  ");
		insertTrainingCompanySQL.append("  TRAININGCOMPANYID, ");
		insertTrainingCompanySQL.append("  NAME, ");
		insertTrainingCompanySQL.append("  ADDRESS1, ");
		insertTrainingCompanySQL.append("  ADDRESS2, ");
		insertTrainingCompanySQL.append("  ADDRESS3, ");
		insertTrainingCompanySQL.append("  ADDRESS4, ");
		insertTrainingCompanySQL.append("  POSTALCODE, ");
		insertTrainingCompanySQL.append("  COUNTRYID, ");
		insertTrainingCompanySQL.append("  CODE, ");
		insertTrainingCompanySQL.append("  TELEPHONENUMBER, ");
    insertTrainingCompanySQL.append("  FAXNUMBER, ");
    insertTrainingCompanySQL.append("  EMAILADDRESS, ");
    insertTrainingCompanySQL.append("  WEBSITEADDRESS, ");
		insertTrainingCompanySQL.append("  LOGOFILENAME, ");
		insertTrainingCompanySQL.append("  LOGOWIDTH, ");
		insertTrainingCompanySQL.append("  LOGOHEIGHT, ");
		insertTrainingCompanySQL.append("  VATNUMBER, ");
		insertTrainingCompanySQL.append("  REFERENCE, ");
		insertTrainingCompanySQL.append("  FREETEXT, ");
    insertTrainingCompanySQL.append("  CREATIONTIMESTAMP, ");
		insertTrainingCompanySQL.append("  AUDITORID, ");
		insertTrainingCompanySQL.append("  AUDITTIMESTAMP ");
		insertTrainingCompanySQL.append(")  ");
		insertTrainingCompanySQL.append("VALUES  ");
		insertTrainingCompanySQL.append("(  ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
    insertTrainingCompanySQL.append("  ^, ");
    insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^, ");
    insertTrainingCompanySQL.append("  ^, ");
    insertTrainingCompanySQL.append("  ^, ");
		insertTrainingCompanySQL.append("  ^ ");
		insertTrainingCompanySQL.append(")  ");
		// Get update TrainingCompany SQL.
		updateTrainingCompanySQL = new StringBuffer();
		updateTrainingCompanySQL.append("UPDATE TRAININGCOMPANY ");
		updateTrainingCompanySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateTrainingCompanySQL.append("     NAME = ^, ");
		updateTrainingCompanySQL.append("     ADDRESS1 = ^, ");
		updateTrainingCompanySQL.append("     ADDRESS2 = ^, ");
		updateTrainingCompanySQL.append("     ADDRESS3 = ^, ");
		updateTrainingCompanySQL.append("     ADDRESS4 = ^, ");
		updateTrainingCompanySQL.append("     POSTALCODE = ^, ");
		updateTrainingCompanySQL.append("     COUNTRYID = ^, ");
		updateTrainingCompanySQL.append("     CODE = ^, ");
		updateTrainingCompanySQL.append("     TELEPHONENUMBER = ^, ");
    updateTrainingCompanySQL.append("     FAXNUMBER = ^, ");
    updateTrainingCompanySQL.append("     EMAILADDRESS = ^, ");
    updateTrainingCompanySQL.append("     WEBSITEADDRESS = ^, ");
		updateTrainingCompanySQL.append("     LOGOFILENAME = ^, ");
		updateTrainingCompanySQL.append("     LOGOWIDTH = ^, ");
		updateTrainingCompanySQL.append("     LOGOHEIGHT = ^, ");
		updateTrainingCompanySQL.append("     VATNUMBER = ^, ");
		updateTrainingCompanySQL.append("     REFERENCE = ^, ");
		updateTrainingCompanySQL.append("     FREETEXT = ^, ");
    updateTrainingCompanySQL.append("     DISPLAYORDER = ^, ");
    updateTrainingCompanySQL.append("     AUDITORID = ^, ");
		updateTrainingCompanySQL.append("     AUDITTIMESTAMP = ^ ");
		updateTrainingCompanySQL.append("WHERE TRAININGCOMPANYID = ^ ");
		updateTrainingCompanySQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateTrainingCompanyDisplayOrder SQL.
		updateTrainingCompanyDisplayOrderSQL = new StringBuffer();
		updateTrainingCompanyDisplayOrderSQL.append("UPDATE TRAININGCOMPANY ");
		updateTrainingCompanyDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateTrainingCompanyDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateTrainingCompanyDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateTrainingCompanyDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateTrainingCompanyDisplayOrderSQL.append("WHERE TRAININGCOMPANYID = ^ ");
		updateTrainingCompanyDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete TrainingCompany SQL.
		deleteTrainingCompanySQL = new StringBuffer();
		deleteTrainingCompanySQL.append("UPDATE TRAININGCOMPANY ");
		deleteTrainingCompanySQL.append("SET ACTIVE = FALSE, ");
		deleteTrainingCompanySQL.append("    AUDITORID = ^, ");
		deleteTrainingCompanySQL.append("    AUDITTIMESTAMP = ^, ");
		deleteTrainingCompanySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteTrainingCompanySQL.append("WHERE TRAININGCOMPANYID = ^ ");
		deleteTrainingCompanySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select TrainingCompanies SQL.
		selectTrainingCompaniesSQL = new StringBuffer();
		selectTrainingCompaniesSQL.append("SELECT TRAININGCOMPANYID, ");
		selectTrainingCompaniesSQL.append("       NAME, ");
		selectTrainingCompaniesSQL.append("       ADDRESS1, ");
		selectTrainingCompaniesSQL.append("       ADDRESS2, ");
		selectTrainingCompaniesSQL.append("       ADDRESS3, ");
		selectTrainingCompaniesSQL.append("       ADDRESS4, ");
		selectTrainingCompaniesSQL.append("       POSTALCODE, ");
		selectTrainingCompaniesSQL.append("       COUNTRYID, ");
		selectTrainingCompaniesSQL.append("       CODE, ");
		selectTrainingCompaniesSQL.append("       TELEPHONENUMBER, ");
    selectTrainingCompaniesSQL.append("       FAXNUMBER, ");
    selectTrainingCompaniesSQL.append("       EMAILADDRESS, ");
    selectTrainingCompaniesSQL.append("       WEBSITEADDRESS, ");
		selectTrainingCompaniesSQL.append("       LOGOFILENAME, ");
		selectTrainingCompaniesSQL.append("       LOGOWIDTH, ");
		selectTrainingCompaniesSQL.append("       LOGOHEIGHT, ");
		selectTrainingCompaniesSQL.append("       VATNUMBER, ");
		selectTrainingCompaniesSQL.append("       REFERENCE, ");
		selectTrainingCompaniesSQL.append("       FREETEXT, ");
    selectTrainingCompaniesSQL.append("       DISPLAYORDER, ");
		selectTrainingCompaniesSQL.append("       CREATIONTIMESTAMP, ");
		selectTrainingCompaniesSQL.append("       AUDITORID, ");
		selectTrainingCompaniesSQL.append("       AUDITTIMESTAMP, ");
		selectTrainingCompaniesSQL.append("       ACTIVE, ");
		selectTrainingCompaniesSQL.append("       NOOFCHANGES ");
		selectTrainingCompaniesSQL.append("FROM TRAININGCOMPANY ");
		// Get select TrainingCompany SQL.
		selectTrainingCompanySQL = new StringBuffer(selectTrainingCompaniesSQL);
		selectTrainingCompanySQL.append("WHERE TRAININGCOMPANYID = ^ ");
		// Get select TrainingCompany for Name SQL.
		selectTrainingCompanyForNameSQL = new StringBuffer(selectTrainingCompaniesSQL);
		selectTrainingCompanyForNameSQL.append("WHERE NAME = ^ ");
		selectTrainingCompanyForNameSQL.append("AND ACTIVE = TRUE ");
		// Get select TrainingCompany for Code SQL.
		selectTrainingCompanyForCodeSQL = new StringBuffer(selectTrainingCompaniesSQL);
		selectTrainingCompanyForCodeSQL.append("WHERE CODE = ^ ");
		selectTrainingCompanyForCodeSQL.append("AND ACTIVE = TRUE ");
		// Get select TrainingCompanyUsers SQL.
		selectTrainingCompanyUsersSQL = new StringBuffer();
		selectTrainingCompanyUsersSQL.append("SELECT TC.TRAININGCOMPANYID, ");
		selectTrainingCompanyUsersSQL.append("	   TC.NAME, ");
		selectTrainingCompanyUsersSQL.append("	   TC.ADDRESS1, ");
		selectTrainingCompanyUsersSQL.append("	   TC.ADDRESS2, ");
		selectTrainingCompanyUsersSQL.append("	   TC.ADDRESS3, ");
		selectTrainingCompanyUsersSQL.append("	   TC.ADDRESS4, ");
		selectTrainingCompanyUsersSQL.append("	   TC.POSTALCODE, ");
		selectTrainingCompanyUsersSQL.append("	   TC.COUNTRYID, ");
		selectTrainingCompanyUsersSQL.append("	   TC.CODE, ");
		selectTrainingCompanyUsersSQL.append("     TC.TELEPHONENUMBER, ");
    selectTrainingCompanyUsersSQL.append("     TC.FAXNUMBER, ");
    selectTrainingCompanyUsersSQL.append("     TC.EMAILADDRESS, ");
    selectTrainingCompanyUsersSQL.append("     TC.WEBSITEADDRESS, ");
		selectTrainingCompanyUsersSQL.append("     TC.LOGOFILENAME, ");
		selectTrainingCompanyUsersSQL.append("     TC.LOGOWIDTH, ");
		selectTrainingCompanyUsersSQL.append("     TC.LOGOHEIGHT, ");
		selectTrainingCompanyUsersSQL.append("     TC.VATNUMBER, ");
		selectTrainingCompanyUsersSQL.append("     TC.REFERENCE, ");
		selectTrainingCompanyUsersSQL.append("     TC.FREETEXT, ");
    selectTrainingCompanyUsersSQL.append("     TC.DISPLAYORDER, ");
    selectTrainingCompanyUsersSQL.append("	   TC.CREATIONTIMESTAMP, ");
		selectTrainingCompanyUsersSQL.append("	   TC.AUDITORID, ");
		selectTrainingCompanyUsersSQL.append("	   TC.AUDITTIMESTAMP, ");
		selectTrainingCompanyUsersSQL.append("	   TC.ACTIVE, ");
		selectTrainingCompanyUsersSQL.append("	   TC.NOOFCHANGES, ");
		selectTrainingCompanyUsersSQL.append("	   C.NAME AS COUNTRYNAME ");
		selectTrainingCompanyUsersSQL.append("FROM TRAININGCOMPANY TC, COUNTRY C ");
		selectTrainingCompanyUsersSQL.append("WHERE C.COUNTRYID  = TC.COUNTRYID ");
		// Get select Active TrainingCompanyUsers SQL.
		selectActiveTrainingCompanyUsersSQL = new StringBuffer(selectTrainingCompanyUsersSQL);
		selectActiveTrainingCompanyUsersSQL.append("AND TC.ACTIVE = TRUE ");
    // Get select Active TrainingCompanies SQL.
    selectActiveTrainingCompaniesSQL = new StringBuffer(selectTrainingCompaniesSQL);
    selectActiveTrainingCompaniesSQL.append("WHERE ACTIVE = TRUE ");
		// Get select TrainingCompanyUser SQL.
		selectTrainingCompanyUserSQL = new StringBuffer(selectTrainingCompanyUsersSQL);
		selectTrainingCompanyUserSQL.append("AND TC.TRAININGCOMPANYID = ^ ");
		selectTrainingCompanyUsersSQL.append("ORDER BY TC.DISPLAYORDER, TC.NAME ");
    //
    selectActiveTrainingCompanyUsersSQL.append("ORDER BY TC.DISPLAYORDER, TC.NAME ");
    //
    selectActiveTrainingCompaniesSQL.append("ORDER BY DISPLAYORDER, NAME ");
	}

	public int insertTrainingCompany(TrainingCompany trainingCompany,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertTrainingCompanySQL.toString());
		// Replace the parameters with supplied values.
		trainingCompany.setTrainingCompanyId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "trainingCompany"));
		Utilities.replace(sql, trainingCompany.getTrainingCompanyId());
		Utilities.replaceAndQuote(sql, trainingCompany.getName().trim());
		Utilities.replaceAndQuote(sql, trainingCompany.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getAddress().getAddress4());
		Utilities.replaceAndQuote(sql, trainingCompany.getAddress().getPostalCode());
		Utilities.replace(sql, trainingCompany.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getCode());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getTelephoneNumber());
    Utilities.replaceAndQuoteNullable(sql, trainingCompany.getFaxNumber());
    Utilities.replaceAndQuoteNullable(sql, trainingCompany.getEmailAddress());
    Utilities.replaceAndQuoteNullable(sql, trainingCompany.getWebsiteAddress());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getLogoFilename());
		Utilities.replaceZeroWithNull(sql, trainingCompany.getLogoWidth());
		Utilities.replaceZeroWithNull(sql, trainingCompany.getLogoHeight());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getVatNumber());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getReference());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getFreeText());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateTrainingCompany(TrainingCompany trainingCompany, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateTrainingCompanySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, trainingCompany.getName().trim());
		Utilities.replaceAndQuote(sql, trainingCompany.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getAddress().getAddress4());
		Utilities.replaceAndQuote(sql, trainingCompany.getAddress().getPostalCode());
		Utilities.replace(sql, trainingCompany.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getCode());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getFaxNumber());
    Utilities.replaceAndQuoteNullable(sql, trainingCompany.getEmailAddress());
    Utilities.replaceAndQuoteNullable(sql, trainingCompany.getWebsiteAddress());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getLogoFilename());
		Utilities.replaceZeroWithNull(sql, trainingCompany.getLogoWidth());
		Utilities.replaceZeroWithNull(sql, trainingCompany.getLogoHeight());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getVatNumber());
		Utilities.replaceAndQuoteNullable(sql, trainingCompany.getReference());
    Utilities.replaceAndQuoteNullable(sql, trainingCompany.getFreeText());
    Utilities.replace(sql, trainingCompany.getDisplayOrder());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, trainingCompany.getTrainingCompanyId());
		Utilities.replace(sql, trainingCompany.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteTrainingCompany(Integer trainingCompanyId, Integer noOfChanges, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteTrainingCompanySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, trainingCompanyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public TrainingCompany getTrainingCompany(Integer trainingCompanyId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCompanySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingCompanyId);
		return (TrainingCompany) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), TrainingCompany.class.getName());
	}

	public TrainingCompany getTrainingCompanyForName(String name) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCompanyForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (TrainingCompany) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), TrainingCompany.class.getName());
	}

	public TrainingCompany getTrainingCompanyForCode(String code) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCompanyForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (TrainingCompany) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), TrainingCompany.class.getName());
	}

	public TrainingCompanyUser getTrainingCompanyUser(Integer trainingCompanyId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectTrainingCompanyUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, trainingCompanyId);
		return (TrainingCompanyUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), TrainingCompanyUser.class.getName());
	}

  public TrainingCompanyUserEntity getTrainingCompanyUserEntity(Integer trainingCompanyId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectTrainingCompanyUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, trainingCompanyId);
    return (TrainingCompanyUserEntity)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCompanyUserEntity.class.getName());
  }

  public List<TrainingCompany> getTrainingCompanies(boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveTrainingCompaniesSQL.toString());  
    }
    else 
    {
      sql = new StringBuffer(selectTrainingCompaniesSQL.toString());  
    }
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCompany.class.getName());
  }

	public List<TrainingCompanyUser> getTrainingCompanyUsers(boolean showOnlyActive) 
	{
		StringBuffer sql = null;
		if (showOnlyActive) 
		{
			sql = new StringBuffer(selectActiveTrainingCompanyUsersSQL.toString());	
		}
		else 
		{
			sql = new StringBuffer(selectTrainingCompanyUsersSQL.toString());	
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				TrainingCompanyUser.class.getName());
	}

	public int updateTrainingCompanyDisplayOrder(Integer trainingCompanyId, Integer displayOrder, Integer noOfChanges, Integer auditorId) 
	{
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateTrainingCompanyDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, trainingCompanyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
  public List<TrainingCompanyUserEntity> getTrainingCompanyUserEntities(boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveTrainingCompanyUsersSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectTrainingCompanyUsersSQL.toString()); 
    }
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), TrainingCompanyUserEntity.class.getName());
  }

}
