package com.helmet.api;

public class ServiceFactory
{

  private static ServiceFactory instance;

  AdminService adminService;
  AgyService agyService;

  public final static ServiceFactory getInstance()
  {
    if (instance == null)
    {
      instance = new ServiceFactory();
    }
    return instance;
  }

  public AdminService getAdminService()
  {
    return adminService;
  }

  public void setAdminService(AdminService adminService)
  {
    this.adminService = adminService;
  }

  public AgyService getAgyService()
  {
    return agyService;
  }

  public void setAgyService(AgyService agyService)
  {
    this.agyService = agyService;
  }

}
