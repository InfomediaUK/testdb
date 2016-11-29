package com.helmet.bean;

import java.util.List;

public class NhsBookingUpload extends NhsBookingUser
{
  private String nhsDate;
  private boolean validDate;
  private String nhsStart;
  private boolean validStart;
  private String nhsEnd;
  private boolean validEnd;
  private List<Applicant> listApplicant;
  private List<Site> listSite;
  private List<Location> listLocation;
  private List<LocationJobProfileUser> listLocationJobProfileUser;

  public boolean getBothTimesValid()
  {
    return (validStart && validEnd);
  }

  public String getNhsEnd()
  {
    return nhsEnd;
  }

  public void setNhsEnd(String nhsEnd)
  {
    this.nhsEnd = nhsEnd;
  }

  public boolean isValidEnd()
  {
    return validEnd;
  }

  public void setValidEnd(boolean validEnd)
  {
    this.validEnd = validEnd;
  }

  public String getNhsDate()
  {
    return nhsDate;
  }

  public void setNhsDate(String nhsDate)
  {
    this.nhsDate = nhsDate;
  }

  public boolean isValidDate()
  {
    return validDate;
  }

  public void setValidDate(boolean validDate)
  {
    this.validDate = validDate;
  }

  public String getNhsStart()
  {
    return nhsStart;
  }

  public void setNhsStart(String nhsStart)
  {
    this.nhsStart = nhsStart;
  }

  public boolean isValidStart()
  {
    return validStart;
  }

  public void setValidStart(boolean validStart)
  {
    this.validStart = validStart;
  }

  public List<Applicant> getListApplicant()
  {
    return listApplicant;
  }

  public void setListApplicant(List<Applicant> listApplicant)
  {
    this.listApplicant = listApplicant;
  }

  public List<Site> getListSite()
  {
    return listSite;
  }

  public void setListSite(List<Site> listSite)
  {
    this.listSite = listSite;
  }

  public List<Location> getListLocation()
  {
    return listLocation;
  }

  public void setListLocation(List<Location> listLocation)
  {
    this.listLocation = listLocation;
  }

  public List<LocationJobProfileUser> getListLocationJobProfileUser()
  {
    return listLocationJobProfileUser;
  }

  public void setListJobProfileUser(List<LocationJobProfileUser> listLocationJobProfileUser)
  {
    this.listLocationJobProfileUser = listLocationJobProfileUser;
  }
  
}
