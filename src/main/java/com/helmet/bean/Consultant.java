//
//
// CHANGED
//
//
package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;

public class Consultant extends Base
{
  private Integer consultantId;

  private Integer agencyId;

  private User user = new User();
  
  private String jobTitle;

  private Boolean canViewDocuments = false;
  
  private Boolean canViewWages = false;
  
  private String signatureFilename;

  public Integer getConsultantId() {
    return consultantId;
  }

  public void setConsultantId(Integer consultantId) {
    this.consultantId = consultantId;
  }

  public Integer getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId) {
    this.agencyId = agencyId;
  }

    public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public Boolean getCanViewDocuments()
  {
    return canViewDocuments;
  }

  public void setCanViewDocuments(Boolean canViewDocuments)
  {
    this.canViewDocuments = canViewDocuments;
  }

  public Boolean getCanViewWages()
  {
    return canViewWages;
  }

  public void setCanViewWages(Boolean canViewWages)
  {
    this.canViewWages = canViewWages;
  }

  public String getSignatureFilename()
  {
    return signatureFilename;
  }

  public void setSignatureFilename(String signatureFilename)
  {
    this.signatureFilename = signatureFilename;
  }

  public String getSignatureFileUrl()
  {
    return FileHandler.getInstance().getConsultantFileFolder() + "/" + consultantId + "/" + signatureFilename;
  }

  public void load(SqlRowSet rs)
  {
      super.load(rs);
      setConsultantId(rs.getInt("CONSULTANTID"));   
      setAgencyId(rs.getInt("AGENCYID"));   
      getUser().setFirstName(rs.getString("FIRSTNAME"));    
      getUser().setLastName(rs.getString("LASTNAME"));    
      getUser().setEmailAddress(rs.getString("EMAILADDRESS"));    
      getUser().setLogin(rs.getString("LOGIN"));    
      getUser().setPwd(rs.getString("PWD"));    
      getUser().setPwdHint(rs.getString("PWDHINT"));    
      getUser().setSecretWord(rs.getString("SECRETWORD"));    
      getUser().setShowPageHelp(rs.getBoolean("SHOWPAGEHELP"));
      getUser().setSuperUser(rs.getBoolean("SUPERUSER"));
      setJobTitle(rs.getString("JOBTITLE"));    
      setCanViewDocuments(rs.getBoolean("CANVIEWDOCUMENTS"));
      setCanViewWages(rs.getBoolean("CANVIEWWAGES"));
      setSignatureFilename(rs.getString("SIGNATUREFILENAME"));
  }

}
