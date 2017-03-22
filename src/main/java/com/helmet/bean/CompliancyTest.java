package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class CompliancyTest extends Base
{
  private Integer compliancyTestId;
  private String property;
  private String value;  
  private String requiredDocumentText;  
  private Integer displayOrder;

  public Integer getCompliancyTestId()
  {
    return compliancyTestId;
  }

  public void setCompliancyTestId(Integer compliancyTestId)
  {
    this.compliancyTestId = compliancyTestId;
  }

  public String getProperty()
  {
    return property;
  }

  public void setProperty(String property)
  {
    this.property = property;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public String getRequiredDocumentText()
  {
    return requiredDocumentText;
  }

  public void setRequiredDocumentText(String requiredDocumentText)
  {
    this.requiredDocumentText = requiredDocumentText;
  }

  public Integer getDisplayOrder()
  {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder)
  {
    this.displayOrder = displayOrder;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setCompliancyTestId(rs.getInt("COMPLIANCYTESTID"));
    setProperty(rs.getString("PROPERTY"));
    setValue(rs.getString("VALUE"));
    setRequiredDocumentText(rs.getString("REQUIREDDOCUMENTTEXT"));
    setDisplayOrder(rs.getInt("DISPLAYORDER"));
  }

}
