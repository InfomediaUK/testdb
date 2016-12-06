package com.helmet.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.xml.jaxb.model.Agency;

/**
 * @author infomedia
 *
 */
public class AgencyResource
{
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  String id;

  public AgencyResource(UriInfo uriInfo, Request request, String id)
  {
    this.uriInfo = uriInfo;
    this.request = request;
    this.id = id;
  }

  // Application integration
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public Agency getAgency()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    Agency agency = adminService.getJerseyAgency(Integer.valueOf(id));
    return agency;
  }

  // For the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public Agency getAgencyHTML()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    Agency agency = adminService.getJerseyAgency(Integer.valueOf(id));
    return agency;
  }
}
