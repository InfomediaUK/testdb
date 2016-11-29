package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Client;
import com.helmet.bean.Site;
import com.helmet.bean.SiteUser;
import com.helmet.bean.SiteUserEntity;

public interface SiteDAO {

	public List<SiteUser> getSiteUsersForClient(Integer clientId, boolean showOnlyActive);
	public List<SiteUser> getSiteUsersForManager(Integer managerId);
	public List<SiteUser> getSiteUsersForAgency(Integer agencyId, Integer clientId);
  public List<Site> getSitesForNhsLocation(Integer clientId, String nhsLocation); 
	public Site getSite(Integer siteId);
	public Site getSiteForName(Integer clientId, String name);
	public Site getSiteForCode(Integer clientId, String code);
	public SiteUser getSiteUser(Integer siteId);
	public SiteUserEntity getSiteUserEntity(Integer siteId);
	public int insertSite(Site site, Integer auditorId);
	public int updateSite(Site site, Integer auditorId);
	public int deleteSite(Integer siteId, Integer noOfChanges, Integer auditorId);
	public int updateSiteDisplayOrder(Integer siteId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
