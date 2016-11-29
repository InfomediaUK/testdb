package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class EmailAction extends Base
{

  private Integer emailActionId;

  private String name;

  private String subject;

  private String textTemplate;

  private String htmlTemplate;

  private String dependsOn;

  private Integer displayOrder;

  public Integer getEmailActionId()
  {
    return emailActionId;
  }

  public void setEmailActionId(Integer emailActionId)
  {
    this.emailActionId = emailActionId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getSubject()
  {
    return subject;
  }

  public void setSubject(String subject)
  {
    this.subject = subject;
  }

  public String getHtmlTemplate()
  {
    return htmlTemplate;
  }

  public void setHtmlTemplate(String htmlTemplate)
  {
    this.htmlTemplate = htmlTemplate;
  }

  public String getTextTemplate()
  {
    return textTemplate;
  }

  public void setTextTemplate(String textTemplate)
  {
    this.textTemplate = textTemplate;
  }

  public String getDependsOn()
  {
    return dependsOn;
  }

  public void setDependsOn(String dependsOn)
  {
    this.dependsOn = dependsOn;
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
    setEmailActionId(rs.getInt("EMAILACTIONID"));
    setName(rs.getString("NAME"));
    setSubject(rs.getString("SUBJECT"));
    setHtmlTemplate(rs.getString("HTMLTEMPLATE"));
    setTextTemplate(rs.getString("TEXTTEMPLATE"));
    setDependsOn(rs.getString("DEPENDSON"));
    setDisplayOrder(rs.getInt("DISPLAYORDER"));
  }

}
