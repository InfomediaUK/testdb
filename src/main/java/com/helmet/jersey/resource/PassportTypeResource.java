package com.helmet.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.xml.jaxb.model.PassportType;

/**
 * @author infomedia
 *
 */
public class PassportTypeResource
{
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  String id;

  public PassportTypeResource(UriInfo uriInfo, Request request, String id)
  {
    this.uriInfo = uriInfo;
    this.request = request;
    this.id = id;
  }

  // Application integration
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public PassportType getPassportType()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    PassportType passportType = adminService.getJerseyPassportType(Integer.valueOf(id));
    return passportType;
  }

  // For the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public PassportType getPassportTypeHTML()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    PassportType passportType = adminService.getJerseyPassportType(Integer.valueOf(id));
    return passportType;
  }
}
