package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AgyAccessGroupItem;
import com.helmet.bean.AgyAccessGroupItemUser;
import com.helmet.persistence.AgyAccessGroupItemDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAgyAccessGroupItemDAO extends JdbcDaoSupport implements AgyAccessGroupItemDAO {

	private static StringBuffer insertAgyAccessGroupItemSQL;

	private static StringBuffer deleteAgyAccessGroupItemSQL;

	private static StringBuffer selectAgyAccessGroupItemUsersSQL;

	private static StringBuffer selectAgyAccessGroupItemUsersForAgyAccessGroupSQL;

	public static void init() {
		// Get insert AgyAccessGroupItem SQL.
		insertAgyAccessGroupItemSQL = new StringBuffer();
		insertAgyAccessGroupItemSQL.append("INSERT INTO AGYACCESSGROUPITEM ");
		insertAgyAccessGroupItemSQL.append("(  ");
		insertAgyAccessGroupItemSQL.append("  AGYACCESSGROUPITEMID, ");
		insertAgyAccessGroupItemSQL.append("  AGYACCESSGROUPID, ");
		insertAgyAccessGroupItemSQL.append("  AGYACCESSID, ");
		insertAgyAccessGroupItemSQL.append("  CREATIONTIMESTAMP, ");
		insertAgyAccessGroupItemSQL.append("  AUDITORID, ");
		insertAgyAccessGroupItemSQL.append("  AUDITTIMESTAMP ");
		insertAgyAccessGroupItemSQL.append(")  ");
		insertAgyAccessGroupItemSQL.append("VALUES  ");
		insertAgyAccessGroupItemSQL.append("(  ");
		insertAgyAccessGroupItemSQL.append("  ^, ");
		insertAgyAccessGroupItemSQL.append("  ^, ");
		insertAgyAccessGroupItemSQL.append("  ^, ");
		insertAgyAccessGroupItemSQL.append("  ^, ");
		insertAgyAccessGroupItemSQL.append("  ^, ");
		insertAgyAccessGroupItemSQL.append("  ^ ");
		insertAgyAccessGroupItemSQL.append(")  ");
		// Get delete AgyAccessGroupItem SQL.
		deleteAgyAccessGroupItemSQL = new StringBuffer();
		deleteAgyAccessGroupItemSQL.append("UPDATE AGYACCESSGROUPITEM ");
		deleteAgyAccessGroupItemSQL.append("SET ACTIVE = FALSE, ");
		deleteAgyAccessGroupItemSQL.append("    AUDITORID = ^, ");
		deleteAgyAccessGroupItemSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAgyAccessGroupItemSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAgyAccessGroupItemSQL.append("WHERE AGYACCESSGROUPITEMID = ^ ");
		deleteAgyAccessGroupItemSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AgyAccessGroupItemUsers SQL.
		selectAgyAccessGroupItemUsersSQL = new StringBuffer();
		selectAgyAccessGroupItemUsersSQL.append("SELECT A.AGYACCESSGROUPITEMID, ");
		selectAgyAccessGroupItemUsersSQL.append("       A.AGYACCESSGROUPID, ");
		selectAgyAccessGroupItemUsersSQL.append("       A.AGYACCESSID, ");
		selectAgyAccessGroupItemUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectAgyAccessGroupItemUsersSQL.append("       A.AUDITORID, ");
		selectAgyAccessGroupItemUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectAgyAccessGroupItemUsersSQL.append("       A.ACTIVE, ");
		selectAgyAccessGroupItemUsersSQL.append("       A.NOOFCHANGES, ");
		selectAgyAccessGroupItemUsersSQL.append("       B.NAME AS AGYACCESSNAME ");
		selectAgyAccessGroupItemUsersSQL.append("FROM AGYACCESSGROUPITEM A, ");
		selectAgyAccessGroupItemUsersSQL.append("     AGYACCESS B ");
		selectAgyAccessGroupItemUsersSQL.append("WHERE B.AGYACCESSID = A.AGYACCESSID ");
		// Get select AgyAccessGroupItemUsers for Manager SQL.
		selectAgyAccessGroupItemUsersForAgyAccessGroupSQL = new StringBuffer(selectAgyAccessGroupItemUsersSQL);
		selectAgyAccessGroupItemUsersForAgyAccessGroupSQL.append("AND A.AGYACCESSGROUPID = ^ ");
		selectAgyAccessGroupItemUsersForAgyAccessGroupSQL.append("AND A.ACTIVE = TRUE ");
		selectAgyAccessGroupItemUsersForAgyAccessGroupSQL.append("ORDER BY B.NAME ");
	}

	public int insertAgyAccessGroupItem(AgyAccessGroupItem agyAccessGroupItem, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAgyAccessGroupItemSQL.toString());
		// Replace the parameters with supplied values.
		agyAccessGroupItem.setAgyAccessGroupItemId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "agyAccessGroupItem"));
		Utilities.replace(sql, agyAccessGroupItem.getAgyAccessGroupItemId());
		Utilities.replace(sql, agyAccessGroupItem.getAgyAccessGroupId());
		Utilities.replace(sql, agyAccessGroupItem.getAgyAccessId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAgyAccessGroupItem(Integer agyAccessGroupItemId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAgyAccessGroupItemSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agyAccessGroupItemId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<AgyAccessGroupItemUser> getAgyAccessGroupItemUsersForAgyAccessGroup(Integer agyAccessGroupId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessGroupItemUsersForAgyAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agyAccessGroupId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccessGroupItemUser.class.getName());

	}

}
