package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DisciplineCategory extends Base
{
  private Integer disciplineCategoryId;
  private String code;
  private String name;  
  private Integer regulatorId;
  private Boolean undertakesEPP;
  private Integer displayOrder;

  public Integer getDisciplineCategoryId()
  {
    return disciplineCategoryId;
  }

  public void setDisciplineCategoryId(Integer disciplineCategoryId)
  {
    this.disciplineCategoryId = disciplineCategoryId;
  }

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Integer getRegulatorId()
  {
    return regulatorId;
  }

  public void setRegulatorId(Integer regulatorId)
  {
    this.regulatorId = regulatorId;
  }

  public Boolean getUndertakesEPP()
  {
    return undertakesEPP;
  }

  public void setUndertakesEPP(Boolean undertakesEPP)
  {
    this.undertakesEPP = undertakesEPP;
  }

  public String getNameWithCode()
  {
    return name + " (" + code + ")";
  }

  public String getCodeWithName()
  {
    return code + " - " + name;
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
    setDisciplineCategoryId(rs.getInt("DISCIPLINECATEGORYID"));
    setCode(rs.getString("CODE"));
    setName(rs.getString("NAME"));
    int regulatorId = rs.getInt("REGULATORID");
    if (rs.wasNull())
    {
      setRegulatorId(null);
    }
    else
    {
      setRegulatorId(regulatorId);
    }
    setUndertakesEPP(rs.getBoolean("UNDERTAKESEPP"));
    setDisplayOrder(rs.getInt("DISPLAYORDER"));
  }

}
