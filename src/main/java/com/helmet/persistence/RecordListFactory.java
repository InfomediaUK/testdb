package com.helmet.persistence;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class RecordListFactory {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	/** @label singleton */
	private static RecordListFactory recordListFactory = null;

	/**
	 * Returns the Record List object.
	 */
	public static RecordListFactory getInstance() {
		if (recordListFactory == null) {
			// NOT instantiated yet so maybe it should be created.
			synchronized (RecordListFactory.class) {
				// Now that only one thread can be here check if it exists
				// again!
				if (recordListFactory == null) {
					// NOT instantiated yet so create it.
					recordListFactory = new RecordListFactory();
				}
			}
		}
		return recordListFactory;
	}

	public RecordListFactory() {
	}

	public List get(JdbcTemplate jt, String sql, String className) {
		List list = new ArrayList();

        logger.debug(sql.toString());

		java.util.Date before = null;
		java.util.Date after = null;
		
		before = new java.util.Date();
		SqlRowSet rs = jt.queryForRowSet(sql);
		after = new java.util.Date();
		long sqlTime = after.getTime() -  before.getTime();
		
		before = new java.util.Date();
        while (rs.next()) {
			
			list.add(RecordFactory.getInstance().loadRecord(rs,
					className));

		}
		after = new java.util.Date();
		long loadTime = after.getTime() -  before.getTime();

		logger.debug(getClass().getName() + " -- sql -> " + sqlTime + " " + "-- load -> " + loadTime + " " + className);

		return list;
	}
	
}