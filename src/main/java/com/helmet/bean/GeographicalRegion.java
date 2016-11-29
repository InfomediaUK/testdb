package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class GeographicalRegion extends Base
{

  private Integer geographicalRegionId;

  private String code;

  private String name;

  public Integer getGeographicalRegionId()
  {
    return geographicalRegionId;
  }

  public void setGeographicalRegionId(Integer geographicalRegionId)
  {
    this.geographicalRegionId = geographicalRegionId;
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

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setGeographicalRegionId(rs.getInt("GEOGRAPHICALREGIONID"));
    setCode(rs.getString("CODE"));
    setName(rs.getString("NAME"));
  }

}
