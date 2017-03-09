package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class IdDocument extends Base
{
  private Integer idDocumentId;
  private String code;
  private String name;  
  private Integer idDocumentType;
  private Boolean requiresVisa = true;
  private Integer displayOrder;

  public Integer getIdDocumentId()
  {
    return idDocumentId;
  }

  public void setIdDocumentId(Integer idDocumentId)
  {
    this.idDocumentId = idDocumentId;
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

  public String getNameWithCodeAndRequiresVisa()
  {
    if (requiresVisa)
    {
      return name + " (" + code + ") - Requires Visa";
    }
    return getNameWithCode();
  }

  public String getCodeWithName()
  {
    return code + " - " + name;
  }

  public Integer getIdDocumentType()
  {
    return idDocumentType;
  }

  public void setIdDocumentType(Integer idDocumentType)
  {
    this.idDocumentType = idDocumentType;
  }

  public Boolean getRequiresVisa()
  {
    return requiresVisa;
  }

  public void setRequiresVisa(Boolean requiresVisa)
  {
    this.requiresVisa = requiresVisa;
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
    setIdDocumentId(rs.getInt("IDDOCUMENTID"));
    setCode(rs.getString("CODE"));
    setName(rs.getString("NAME"));
    setIdDocumentType(rs.getInt("IDDOCUMENTTYPE"));
    setRequiresVisa(rs.getBoolean("REQUIRESVISA"));
    setDisplayOrder(rs.getInt("DISPLAYORDER"));
  }

  @Override
  public String toString()
  {
    return "IdDocument [idDocumentId=" + idDocumentId + ", code=" + code + ", name=" + name + ", idDocumentType=" + idDocumentType + ", requiresVisa=" + requiresVisa + ", displayOrder=" + displayOrder
        + "]";
  }

}
