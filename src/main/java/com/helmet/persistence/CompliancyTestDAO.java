package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.CompliancyTest;

public interface CompliancyTestDAO 
{
	public List<CompliancyTest> getCompliancyTests(boolean showOnlyActive);
	public CompliancyTest getCompliancyTest(Integer CompliancyTestId);
	public CompliancyTest getCompliancyTestForProperty(String name);
	public int insertCompliancyTest(CompliancyTest CompliancyTest, Integer auditorId);
  public int updateCompliancyTest(CompliancyTest CompliancyTest, Integer auditorId);
  public int updateCompliancyTestDisplayOrder(CompliancyTest CompliancyTest, Integer auditorId);
	public int deleteCompliancyTest(Integer CompliancyTestId, Integer noOfChanges, Integer auditorId);
}
