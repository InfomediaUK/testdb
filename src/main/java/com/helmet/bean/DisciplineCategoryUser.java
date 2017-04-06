package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DisciplineCategoryUser extends DisciplineCategory
{
  private String regulatorName;
  private String regulatorCode;

  public String getRegulatorName()
  {
    return regulatorName;
  }

  public void setRegulatorName(String regulatorName)
  {
    this.regulatorName = regulatorName;
  }

  public String getRegulatorCode()
  {
    return regulatorCode;
  }

  public void setRegulatorCode(String regulatorCode)
  {
    this.regulatorCode = regulatorCode;
  }

  public String getNameWithCodeAndRegulator()
  {
    if (getRegulatorId() == null)
    {
      // This DisciplineCategory does NOT have a Regulator.
      return getName() + " (" + getCode() + ")";      
    }
    else
    {
      // This DisciplineCategory does have a Regulator.
      return getName() + " (" + getCode() + ") - " + getRegulatorCode();
    }
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setRegulatorName(rs.getString("REGULATORNAME"));
    setRegulatorCode(rs.getString("REGULATORCODE"));
  }

}
