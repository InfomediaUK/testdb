package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Training extends Base
{
  private Integer trainingId;
  private String code;
  private String name;  
  private Integer displayOrder;

  public Integer getTrainingId()
  {
    return trainingId;
  }

  public void setTrainingId(Integer trainingId)
  {
    this.trainingId = trainingId;
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
    setTrainingId(rs.getInt("TRAININGID"));
    setCode(rs.getString("CODE"));
    setName(rs.getString("NAME"));
    setDisplayOrder(rs.getInt("DISPLAYORDER"));
  }

}
