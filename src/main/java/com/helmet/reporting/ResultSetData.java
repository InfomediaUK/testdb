package com.helmet.reporting;

import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.RowSetDynaClass;

public class ResultSetData {

	RowSetDynaClass dynaBeans;
	DynaProperty[] dynaProperties;
	
	public RowSetDynaClass getDynaBeans() {
		return dynaBeans;
	}
	public void setDynaBeans(RowSetDynaClass dynaBeans) {
		this.dynaBeans = dynaBeans;
	}
	public DynaProperty[] getDynaProperties() {
		return dynaProperties;
	}
	public void setDynaProperties(DynaProperty[] dynaProperties) {
		this.dynaProperties = dynaProperties;
	}
	
}
