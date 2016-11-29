package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;

public class Client extends BaseOwner {

	private Integer clientId;

	private String logo2Filename;

	private Integer logo2Width;
	
	private Integer logo2Height;
	
	private Boolean secretWordAtLogin = false;
	
	private Boolean autoActivateDefault = false;
	
	private String accountContactName;

	private String accountContactEmailAddress;

	private Address accountContactAddress = new Address();

  private String agencyWorkerChecklistOther;
  
  private String tradeshiftSbsPayablesCode;
  
  private String tradeshiftCompanyAccountId;
  
  private String tradeshiftState;
  
  private String nhsName;
	
  private Boolean upliftCommission = false;
  
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getLogo2Filename() {
		return logo2Filename;
	}

	public void setLogo2Filename(String logo2Filename) {
		this.logo2Filename = logo2Filename;
	}

	public Integer getLogo2Height() {
		return logo2Height;
	}

	public void setLogo2Height(Integer logo2Height) {
		this.logo2Height = logo2Height;
	}

	public Integer getLogo2Width() {
		return logo2Width;
	}

	public void setLogo2Width(Integer logo2Width) {
		this.logo2Width = logo2Width;
	}

	public Boolean getSecretWordAtLogin() {
		return secretWordAtLogin;
	}

	public void setSecretWordAtLogin(Boolean secretWordAtLogin) {
		this.secretWordAtLogin = secretWordAtLogin;
	}

	public String getLogoUrl() {
		return FileHandler.getInstance().getLogoFileFolder() + "/c" + getClientId() + "/" + getLogoFilename();
	}
	
	public String getLogo2Url() {
		return FileHandler.getInstance().getLogoFileFolder() + "/c" + getClientId() + "/" + getLogo2Filename();
	}
	
	public Address getAccountContactAddress() {
		return accountContactAddress;
	}

	public void setAccountContactAddress(Address accountContactAddress) {
		this.accountContactAddress = accountContactAddress;
	}

	public String getAccountContactName() {
		return accountContactName;
	}

	public void setAccountContactName(String accountContactName) {
		this.accountContactName = accountContactName;
	}

	public String getAccountContactEmailAddress() {
		return accountContactEmailAddress;
	}

	public void setAccountContactEmailAddress(String accountContactEmailAddress) {
		this.accountContactEmailAddress = accountContactEmailAddress;
	}

	public Boolean getAutoActivateDefault() {
		return autoActivateDefault;
	}

	public void setAutoActivateDefault(Boolean autoActivateDefault) {
		this.autoActivateDefault = autoActivateDefault;
	}

	public String getAgencyWorkerChecklistOther()
  {
    return agencyWorkerChecklistOther;
  }

  public void setAgencyWorkerChecklistOther(String agencyWorkerChecklistOther)
  {
    this.agencyWorkerChecklistOther = agencyWorkerChecklistOther;
  }

  public String getTradeshiftCompanyAccountId()
  {
    return tradeshiftCompanyAccountId;
  }

  public void setTradeshiftCompanyAccountId(String tradeshiftCompanyAccountId)
  {
    this.tradeshiftCompanyAccountId = tradeshiftCompanyAccountId;
  }

  public String getTradeshiftSbsPayablesCode()
  {
    return tradeshiftSbsPayablesCode;
  }

  public void setTradeshiftSbsPayablesCode(String tradeshiftSbsPayablesCode)
  {
    this.tradeshiftSbsPayablesCode = tradeshiftSbsPayablesCode;
  }

  public String getTradeshiftState()
  {
    return tradeshiftState;
  }

  public void setTradeshiftState(String tradeshiftState)
  {
    this.tradeshiftState = tradeshiftState;
  }

  public String getNhsName()
  {
    return nhsName;
  }

  public void setNhsName(String nhsName)
  {
    this.nhsName = nhsName;
  }

  public Boolean getUpliftCommission()
  {
    return upliftCommission;
  }

  public void setUpliftCommission(Boolean upliftCommission)
  {
    this.upliftCommission = upliftCommission;
  }

  public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setClientId(rs.getInt("CLIENTID"));		
        setLogo2Filename(rs.getString("LOGO2FILENAME"));		
        setLogo2Width(rs.getInt("LOGO2WIDTH"));		
        setLogo2Height(rs.getInt("LOGO2HEIGHT"));		
        setSecretWordAtLogin(rs.getBoolean("SECRETWORDATLOGIN"));		
        setAutoActivateDefault(rs.getBoolean("AUTOACTIVATEDEFAULT"));		
        setAccountContactName(rs.getString("ACCOUNTCONTACTNAME"));		
        setAccountContactEmailAddress(rs.getString("ACCOUNTCONTACTEMAILADDRESS"));		
        getAccountContactAddress().setAddress1(rs.getString("ACCOUNTCONTACTADDRESS1"));		
        getAccountContactAddress().setAddress2(rs.getString("ACCOUNTCONTACTADDRESS2"));		
        getAccountContactAddress().setAddress3(rs.getString("ACCOUNTCONTACTADDRESS3"));		
        getAccountContactAddress().setAddress4(rs.getString("ACCOUNTCONTACTADDRESS4"));		
        getAccountContactAddress().setPostalCode(rs.getString("ACCOUNTCONTACTPOSTALCODE"));		
        getAccountContactAddress().setCountryId(rs.getInt("ACCOUNTCONTACTCOUNTRYID"));    
        setAgencyWorkerChecklistOther(rs.getString("AGENCYWORKERCHECKLISTOTHER"));   
        setTradeshiftSbsPayablesCode(rs.getString("TRADESHIFTSBSPAYABLESCODE"));
        setTradeshiftCompanyAccountId(rs.getString("TRADESHIFTCOMPANYACCOUNTID"));
        setTradeshiftState(rs.getString("TRADESHIFTSTATE"));
        setNhsName(rs.getString("NHSNAME"));
        setUpliftCommission(rs.getBoolean("UPLIFTCOMMISSION"));   
    }
}
