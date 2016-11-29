package com.helmet.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SubcontractInvoiceUser extends SubcontractInvoice
{
  private String fromAgencyName;
  private String toAgencyName;
  private String clientName;
  private String clientNhsName;
  private String siteName;
  private String siteNhsLocation;
  private String locationName;
  private String locationNhsWard;
  private String jobFamilyName;
  private String jobFamilyCode;
  private String jobSubFamilyName;
  private String jobSubFamilyCode;
  private String jobProfileName;
  private String jobProfileCode;
  private String jobProfileNhsAssignment;
  private List<SubcontractInvoiceItemUser> listSubcontractInvoiceItemUser = new ArrayList<SubcontractInvoiceItemUser>();
  
  public String getFromAgencyName()
  {
    return fromAgencyName;
  }

  public void setFromAgencyName(String fromAgencyName)
  {
    this.fromAgencyName = fromAgencyName;
  }

  public String getToAgencyName()
  {
    return toAgencyName;
  }

  public void setToAgencyName(String toAgencyName)
  {
    this.toAgencyName = toAgencyName;
  }

  public String getClientName()
  {
    return clientName;
  }

  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }

  public String getClientNhsName()
  {
    return clientNhsName;
  }

  public void setClientNhsName(String clientNhsName)
  {
    this.clientNhsName = clientNhsName;
  }

  public String getSiteName()
  {
    return siteName;
  }

  public void setSiteName(String siteName)
  {
    this.siteName = siteName;
  }

  public String getSiteNhsLocation()
  {
    return siteNhsLocation;
  }

  public void setSiteNhsLocation(String siteNhsLocation)
  {
    this.siteNhsLocation = siteNhsLocation;
  }

  public String getLocationName()
  {
    return locationName;
  }

  public void setLocationName(String locationName)
  {
    this.locationName = locationName;
  }

  public String getLocationNhsWard()
  {
    return locationNhsWard;
  }

  public void setLocationNhsWard(String locationNhsWard)
  {
    this.locationNhsWard = locationNhsWard;
  }

  public String getJobFamilyName()
  {
    return jobFamilyName;
  }

  public void setJobFamilyName(String jobFamilyName)
  {
    this.jobFamilyName = jobFamilyName;
  }

  public String getJobFamilyCode()
  {
    return jobFamilyCode;
  }

  public void setJobFamilyCode(String jobFamilyCode)
  {
    this.jobFamilyCode = jobFamilyCode;
  }

  public String getJobSubFamilyName()
  {
    return jobSubFamilyName;
  }

  public void setJobSubFamilyName(String jobSubFamilyName)
  {
    this.jobSubFamilyName = jobSubFamilyName;
  }

  public String getJobSubFamilyCode()
  {
    return jobSubFamilyCode;
  }

  public void setJobSubFamilyCode(String jobSubFamilyCode)
  {
    this.jobSubFamilyCode = jobSubFamilyCode;
  }

  public String getJobProfileName()
  {
    return jobProfileName;
  }

  public void setJobProfileName(String jobProfileName)
  {
    this.jobProfileName = jobProfileName;
  }

  public String getJobProfileCode()
  {
    return jobProfileCode;
  }

  public void setJobProfileCode(String jobProfileCode)
  {
    this.jobProfileCode = jobProfileCode;
  }

  public String getJobProfileNhsAssignment()
  {
    return jobProfileNhsAssignment;
  }

  public void setJobProfileNhsAssignment(String jobProfileNhsAssignment)
  {
    this.jobProfileNhsAssignment = jobProfileNhsAssignment;
  }

  public List<SubcontractInvoiceItemUser> getListSubcontractInvoiceItemUser()
  {
    return listSubcontractInvoiceItemUser;
  }

  public void setListSubcontractInvoiceItemUser(List<SubcontractInvoiceItemUser> listSubcontractInvoiceItemUser)
  {
    this.listSubcontractInvoiceItemUser = listSubcontractInvoiceItemUser;
  }
      
  public int getNoOfNhsBookings()
  {
    return  listSubcontractInvoiceItemUser.size(); 
  }
  
  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setFromAgencyName(rs.getString("FROMAGENCYNAME"));
    setToAgencyName(rs.getString("ToAGENCYNAME"));
    setClientName(rs.getString("CLIENTNAME"));
    setClientNhsName(rs.getString("CLIENTNHSNAME"));
    setSiteName(rs.getString("SITENAME"));
    setSiteNhsLocation(rs.getString("SITENHSLOCATION"));
    setLocationName(rs.getString("LOCATIONNAME"));
    setLocationNhsWard(rs.getString("LOCATIONNHSWARD"));
    setJobFamilyName(rs.getString("JOBFAMILYNAME"));
    setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
    setJobSubFamilyName(rs.getString("JOBSUBFAMILYNAME"));
    setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
    setJobProfileName(rs.getString("JOBPROFILENAME"));
    setJobProfileCode(rs.getString("JOBPROFILECODE"));
    setJobProfileNhsAssignment(rs.getString("JOBPROFILENHSASSIGNMENT"));
  }

  public String toString()
  {
    StringBuffer text = new StringBuffer(super.toString());
    text.append("fromAgencyName=");
    text.append(fromAgencyName);
    text.append(",");
    text.append("toAgencyName=");
    text.append(toAgencyName);
    text.append(",");
    text.append("clientName=");
    text.append(clientName);
    text.append(",");
    text.append("clientNhsName=");
    text.append(clientNhsName);
    text.append(",");
    text.append("siteName=");
    text.append(siteName);
    text.append(",");
    text.append("siteNhsLocation=");
    text.append(siteNhsLocation);
    text.append(",");
    text.append("locationName=");
    text.append(locationName);
    text.append(",");
    text.append("locationNhsWard=");
    text.append(locationNhsWard);
    text.append(",");
    text.append("jobFamilyName=");
    text.append(jobFamilyName);
    text.append(",");
    text.append("jobFamilyCode=");
    text.append(jobFamilyCode);
    text.append(",");
    text.append("jobSubFamilyName=");
    text.append(jobSubFamilyName);
    text.append(",");
    text.append("jobSubFamilyCode=");
    text.append(jobSubFamilyCode);
    text.append(",");
    text.append("jobProfileName=");
    text.append(jobProfileName);
    text.append(",");
    text.append("jobProfileCode=");
    text.append(jobProfileCode);
    text.append(",");
    text.append("jobProfileNhsAssignment=");
    text.append(jobProfileNhsAssignment);
    return text.toString();
  }

}
