package com.helmet.bean;

import java.sql.Timestamp;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Base
{
  private Timestamp creationTimestamp;

  private Integer auditorId;

  private Timestamp auditTimestamp;

  private Boolean active;

  private Integer noOfChanges;

  public Timestamp getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(Timestamp creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  public Integer getAuditorId() {
    return auditorId;
  }

  public void setAuditorId(Integer auditorId) {
    this.auditorId = auditorId;
  }

  public Timestamp getAuditTimestamp() {
    return auditTimestamp;
  }

  public void setAuditTimestamp(Timestamp auditTimestamp) {
    this.auditTimestamp = auditTimestamp;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Integer getNoOfChanges() {
    return noOfChanges;
  }

  public void setNoOfChanges(Integer noOfChanges) {
    this.noOfChanges = noOfChanges;
  }

  public void load(SqlRowSet rs) {

    setCreationTimestamp(rs.getTimestamp("CREATIONTIMESTAMP"));
    setAuditorId(rs.getInt("AUDITORID"));
    setAuditTimestamp(rs.getTimestamp("AUDITTIMESTAMP"));
    setActive(rs.getBoolean("ACTIVE"));
    setNoOfChanges(rs.getInt("NOOFCHANGES"));

  }


}
