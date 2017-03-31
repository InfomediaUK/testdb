package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AgencyUserEntity;
import com.helmet.bean.DisciplineCategory;
import com.helmet.bean.DisciplineCategoryUser;
import com.helmet.bean.DisciplineCategoryUserEntity;
import com.helmet.bean.DisciplineCategoryUserEntityAdmin;
import com.helmet.persistence.DisciplineCategoryDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultDisciplineCategoryDAO extends JdbcDaoSupport implements DisciplineCategoryDAO 
{

	private static StringBuffer insertDisciplineCategorySQL;

	private static StringBuffer updateDisciplineCategorySQL;

  private static StringBuffer updateDisciplineCategoryDisplayOrderSQL;

	private static StringBuffer deleteDisciplineCategorySQL;

  private static StringBuffer selectDisciplineCategorySQL;

	private static StringBuffer selectDisciplineCategoryForNameSQL;

	private static StringBuffer selectDisciplineCategoryForCodeSQL;

  private static StringBuffer selectDisciplineCategoryUserSQL;

  private static StringBuffer selectDisciplineCategoriesSQL;

  private static StringBuffer selectDisciplineCategoryUsersSQL;

  private static StringBuffer selectActiveDisciplineCategoryUsersSQL;

	private static StringBuffer selectActiveDisciplineCategoriesSQL;

	public static void init() 
  {
		// Get insert DisciplineCategory SQL.
		insertDisciplineCategorySQL = new StringBuffer();
		insertDisciplineCategorySQL.append("INSERT INTO DISCIPLINECATEGORY ");
		insertDisciplineCategorySQL.append("(  ");
		insertDisciplineCategorySQL.append("  DISCIPLINECATEGORYID, ");
		insertDisciplineCategorySQL.append("  CODE, ");
    insertDisciplineCategorySQL.append("  NAME, ");
    insertDisciplineCategorySQL.append("  REGULATORID, ");
    insertDisciplineCategorySQL.append("  UNDERTAKESEPP, ");
    insertDisciplineCategorySQL.append("  DISPLAYORDER, ");
    insertDisciplineCategorySQL.append("  CREATIONTIMESTAMP, ");
    insertDisciplineCategorySQL.append("  AUDITORID, ");
    insertDisciplineCategorySQL.append("  AUDITTIMESTAMP ");
		insertDisciplineCategorySQL.append(")  ");
		insertDisciplineCategorySQL.append("VALUES  ");
		insertDisciplineCategorySQL.append("(  ");
		insertDisciplineCategorySQL.append("  ^, ");
    insertDisciplineCategorySQL.append("  ^, ");
    insertDisciplineCategorySQL.append("  ^, ");
    insertDisciplineCategorySQL.append("  ^, ");
    insertDisciplineCategorySQL.append("  ^, ");
    insertDisciplineCategorySQL.append("  ^, ");
    insertDisciplineCategorySQL.append("  ^, ");
    insertDisciplineCategorySQL.append("  ^, ");
		insertDisciplineCategorySQL.append("  ^ ");
		insertDisciplineCategorySQL.append(")  ");
		// Get update DisciplineCategory SQL.
    // NOTE. Updates DisplayOrder too...
		updateDisciplineCategorySQL = new StringBuffer();
		updateDisciplineCategorySQL.append("UPDATE DISCIPLINECATEGORY ");
		updateDisciplineCategorySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateDisciplineCategorySQL.append("     CODE = ^, ");
    updateDisciplineCategorySQL.append("     NAME = ^, ");
    updateDisciplineCategorySQL.append("     REGULATORID = ^, ");
    updateDisciplineCategorySQL.append("     UNDERTAKESEPP = ^, ");
    updateDisciplineCategorySQL.append("     DISPLAYORDER = ^, ");
    updateDisciplineCategorySQL.append("     AUDITORID = ^, ");
    updateDisciplineCategorySQL.append("     AUDITTIMESTAMP = ^ ");
		updateDisciplineCategorySQL.append("WHERE DISCIPLINECATEGORYID = ^ ");
		updateDisciplineCategorySQL.append("AND   NOOFCHANGES = ^ ");
    // Get updateDisciplineCategoryDisplayOrder SQL.
    updateDisciplineCategoryDisplayOrderSQL = new StringBuffer();
    updateDisciplineCategoryDisplayOrderSQL.append("UPDATE DISCIPLINECATEGORY ");
    updateDisciplineCategoryDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
    updateDisciplineCategoryDisplayOrderSQL.append("    AUDITORID = ^, ");
    updateDisciplineCategoryDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
    updateDisciplineCategoryDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateDisciplineCategoryDisplayOrderSQL.append("WHERE DISCIPLINECATEGORYID = ^ ");
    updateDisciplineCategoryDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete DisciplineCategory SQL.
		deleteDisciplineCategorySQL = new StringBuffer();
		deleteDisciplineCategorySQL.append("UPDATE DISCIPLINECATEGORY ");
		deleteDisciplineCategorySQL.append("SET ACTIVE = FALSE, ");
    deleteDisciplineCategorySQL.append("    AUDITORID = ^, ");
    deleteDisciplineCategorySQL.append("    AUDITTIMESTAMP = ^, ");
    deleteDisciplineCategorySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteDisciplineCategorySQL.append("WHERE DISCIPLINECATEGORYID = ^ ");
		deleteDisciplineCategorySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select DisciplineCategories SQL.
		selectDisciplineCategoriesSQL = new StringBuffer();
		selectDisciplineCategoriesSQL.append("SELECT DISCIPLINECATEGORYID, ");
		selectDisciplineCategoriesSQL.append("       CODE, ");
    selectDisciplineCategoriesSQL.append("       NAME, ");
    selectDisciplineCategoriesSQL.append("       REGULATORID, ");
    selectDisciplineCategoriesSQL.append("       UNDERTAKESEPP, ");
    selectDisciplineCategoriesSQL.append("       DISPLAYORDER, ");
    selectDisciplineCategoriesSQL.append("       CREATIONTIMESTAMP, ");
    selectDisciplineCategoriesSQL.append("       AUDITORID, ");
    selectDisciplineCategoriesSQL.append("       AUDITTIMESTAMP, ");
    selectDisciplineCategoriesSQL.append("       ACTIVE, ");
		selectDisciplineCategoriesSQL.append("       NOOFCHANGES ");
		selectDisciplineCategoriesSQL.append("FROM DISCIPLINECATEGORY ");

    // Get select DisciplineCategoryUsers SQL.
    selectDisciplineCategoryUsersSQL = new StringBuffer();
    selectDisciplineCategoryUsersSQL.append("SELECT DC.DISCIPLINECATEGORYID, ");
    selectDisciplineCategoryUsersSQL.append("       DC.CODE, ");
    selectDisciplineCategoryUsersSQL.append("       DC.NAME, ");
    selectDisciplineCategoryUsersSQL.append("       DC.REGULATORID, ");
    selectDisciplineCategoryUsersSQL.append("       DC.UNDERTAKESEPP, ");
    selectDisciplineCategoryUsersSQL.append("       DC.DISPLAYORDER, ");
    selectDisciplineCategoryUsersSQL.append("       DC.CREATIONTIMESTAMP, ");
    selectDisciplineCategoryUsersSQL.append("       DC.AUDITORID, ");
    selectDisciplineCategoryUsersSQL.append("       DC.AUDITTIMESTAMP, ");
    selectDisciplineCategoryUsersSQL.append("       DC.ACTIVE, ");
    selectDisciplineCategoryUsersSQL.append("       DC.NOOFCHANGES, ");
    selectDisciplineCategoryUsersSQL.append("       R.NAME AS REGULATORNAME, ");
    selectDisciplineCategoryUsersSQL.append("       R.CODE AS REGULATORCODE ");
    selectDisciplineCategoryUsersSQL.append("FROM DISCIPLINECATEGORY DC ");
    selectDisciplineCategoryUsersSQL.append("     LEFT OUTER JOIN REGULATOR R ");
    selectDisciplineCategoryUsersSQL.append("     ON  DC.REGULATORID = R.REGULATORID ");
    // Get select ActiveDisciplineCategoryUsers SQL.
    selectActiveDisciplineCategoryUsersSQL = new StringBuffer(selectDisciplineCategoryUsersSQL);
    selectActiveDisciplineCategoryUsersSQL.append("WHERE DC.ACTIVE = TRUE ");
    selectActiveDisciplineCategoryUsersSQL.append("ORDER BY DC.DISPLAYORDER, DC.NAME ");
    // Get select DisciplineCategory SQL.
    selectDisciplineCategorySQL = new StringBuffer(selectDisciplineCategoriesSQL);
    selectDisciplineCategorySQL.append("WHERE DISCIPLINECATEGORYID = ^ ");
		// Get select DisciplineCategoryUser SQL.
		selectDisciplineCategoryUserSQL = new StringBuffer(selectDisciplineCategoryUsersSQL);
		selectDisciplineCategoryUserSQL.append("WHERE DC.DISCIPLINECATEGORYID = ^ ");
    // Get select DisciplineCategory for Name SQL.
    selectDisciplineCategoryForNameSQL = new StringBuffer(selectDisciplineCategoriesSQL);
    selectDisciplineCategoryForNameSQL.append("WHERE NAME = ^ ");
    // Get select DisciplineCategory for Iso Code SQL.
    selectDisciplineCategoryForCodeSQL = new StringBuffer(selectDisciplineCategoriesSQL);
    selectDisciplineCategoryForCodeSQL.append("WHERE CODE = ^ ");
		// Get select Active DisciplineCategories SQL.
		selectActiveDisciplineCategoriesSQL = new StringBuffer(selectDisciplineCategoriesSQL);
    selectActiveDisciplineCategoriesSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveDisciplineCategoriesSQL.append("ORDER BY DISPLAYORDER, NAME ");
    // Put order by on now...
    selectDisciplineCategoriesSQL.append("ORDER BY DISPLAYORDER, NAME ");
    selectDisciplineCategoryUsersSQL.append("ORDER BY DC.DISPLAYORDER, DC.NAME ");

	}

	public int insertDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertDisciplineCategorySQL.toString());
		// Replace the parameters with supplied values.
		disciplineCategory.setDisciplineCategoryId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "disciplineCategory"));
		Utilities.replace(sql, disciplineCategory.getDisciplineCategoryId());
		Utilities.replaceAndQuote(sql, disciplineCategory.getCode());
    Utilities.replaceAndQuote(sql, disciplineCategory.getName());
    Utilities.replaceZeroWithNull(sql, disciplineCategory.getRegulatorId());
    Utilities.replace(sql, disciplineCategory.getUndertakesEPP());
    Utilities.replace(sql, disciplineCategory.getDisplayOrder());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateDisciplineCategorySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, disciplineCategory.getCode());
    Utilities.replaceAndQuote(sql, disciplineCategory.getName());
    Utilities.replaceZeroWithNull(sql, disciplineCategory.getRegulatorId());
    Utilities.replace(sql, disciplineCategory.getUndertakesEPP());
    Utilities.replace(sql, disciplineCategory.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, disciplineCategory.getDisciplineCategoryId());
		Utilities.replace(sql, disciplineCategory.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateDisciplineCategoryDisplayOrder(DisciplineCategory disciplineCategory, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateDisciplineCategoryDisplayOrderSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, disciplineCategory.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, disciplineCategory.getDisciplineCategoryId());
    Utilities.replace(sql, disciplineCategory.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteDisciplineCategory(Integer disciplineCategoryId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteDisciplineCategorySQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, disciplineCategoryId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public DisciplineCategory getDisciplineCategory(Integer disciplineCategoryId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectDisciplineCategorySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, disciplineCategoryId);
		return (DisciplineCategory) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategory.class.getName());
	}

	public DisciplineCategory getDisciplineCategoryForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectDisciplineCategoryForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (DisciplineCategory) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), DisciplineCategory.class.getName());
	}

	public DisciplineCategory getDisciplineCategoryForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectDisciplineCategoryForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (DisciplineCategory) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategory.class.getName());
	}

  public DisciplineCategoryUser getDisciplineCategoryUser(Integer disciplineCategoryId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectDisciplineCategoryUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, disciplineCategoryId);
    return (DisciplineCategoryUser) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryUser.class.getName());
  }

  public DisciplineCategoryUserEntity getDisciplineCategoryUserEntity(Integer disciplineCategoryId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectDisciplineCategoryUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, disciplineCategoryId);
    return (DisciplineCategoryUserEntity)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryUserEntity.class.getName());
  }

  public DisciplineCategoryUserEntityAdmin getDisciplineCategoryUserEntityAdmin(Integer disciplineCategoryId)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectDisciplineCategoryUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, disciplineCategoryId);
    return (DisciplineCategoryUserEntityAdmin)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryUserEntityAdmin.class.getName());
  }

	public List<DisciplineCategory> getDisciplineCategories() 
  {
		return getDisciplineCategories(false);
	}

	public List<DisciplineCategory> getDisciplineCategories(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveDisciplineCategoriesSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectDisciplineCategoriesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), DisciplineCategory.class.getName());
	}

	public List<DisciplineCategory> getActiveDisciplineCategories() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveDisciplineCategoriesSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), DisciplineCategory.class.getName());

	}

  public List<DisciplineCategoryUser> getDisciplineCategoryUsers(boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveDisciplineCategoryUsersSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectDisciplineCategoryUsersSQL.toString()); 
    }
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryUser.class.getName());
  }

  public List<DisciplineCategoryUserEntity> getDisciplineCategoryUserEntities(boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveDisciplineCategoryUsersSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectDisciplineCategoryUsersSQL.toString()); 
    }
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), DisciplineCategoryUserEntity.class.getName());
  }

}
