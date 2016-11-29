package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AreaOfSpeciality extends Base
{

  private Integer areaOfSpecialityId;

  private String name;

  public Integer getAreaOfSpecialityId()
  {
    return areaOfSpecialityId;
  }

  public void setAreaOfSpecialityId(Integer areaOfSpecialityId)
  {
    this.areaOfSpecialityId = areaOfSpecialityId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setAreaOfSpecialityId(rs.getInt("AREAOFSPECIALITYID"));
    setName(rs.getString("NAME"));
  }

}
