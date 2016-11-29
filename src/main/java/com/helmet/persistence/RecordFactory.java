package com.helmet.persistence;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.bean.Base;

public class RecordFactory {

	protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	/** @label singleton */
	private static RecordFactory recordFactory = null;

	/**
	 * Returns the RecordFactory object.
	 */
	public static RecordFactory getInstance() {
		if (recordFactory == null) {
			// NOT instantiated yet so maybe it should be created.
			synchronized (RecordFactory.class) {
				// Now that only one thread can be here check if it exists
				// again!
				if (recordFactory == null) {
					// NOT instantiated yet so create it.
					recordFactory = new RecordFactory();
				}
			}
		}
		return recordFactory;
	}

	public RecordFactory() {
	}

	
	public Base get(JdbcTemplate jt, String sql, String className) {
		Base baseDO = null;

		logger.debug(sql.toString());

		java.util.Date before = null;
		java.util.Date after = null;
		
		before = new java.util.Date();
		SqlRowSet rs = jt.queryForRowSet(sql);
		after = new java.util.Date();
		long sqlTime = after.getTime() -  before.getTime();
		
		before = new java.util.Date();
        if (rs.next()) {
			// One row found.
			baseDO = loadRecord(rs, className);
		}
		after = new java.util.Date();
		long loadTime = after.getTime() -  before.getTime();

		logger.debug(getClass().getName() + " -- sql -> " + sqlTime + " " + "-- load -> " + loadTime + " " + className);

		return baseDO;
	}
	
	
	public Base loadRecord(SqlRowSet rs, String className) {

		Base baseDO = null;

		try {
			baseDO = (Base) Class.forName(className).newInstance();
		} catch (IllegalAccessException e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>");
			logger.error(e.getMessage());
			logger.error("<<<<<<<<<<<<<<<<<<<<");
		} catch (InstantiationException e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>");
			logger.error(e.getMessage());
			logger.error("<<<<<<<<<<<<<<<<<<<<");
		} catch (ClassNotFoundException e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>");
			logger.error(e.getMessage());
			logger.error("<<<<<<<<<<<<<<<<<<<<");
		}

		baseDO.load(rs);
		return baseDO;
	}

	
	
}