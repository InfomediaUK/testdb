package com.helmet.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.xml.jaxb.model.VisaType;

/**
 * @author infomedia
 *
 */
public class VisaTypeResource
{
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  String id;

  public VisaTypeResource(UriInfo uriInfo, Request request, String id)
  {
    this.uriInfo = uriInfo;
    this.request = request;
    this.id = id;
  }

  // Application integration
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public VisaType getVisaType()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    VisaType visaType = adminService.getJerseyVisaType(Integer.valueOf(id));
    return visaType;
  }

  // For the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public VisaType getVisaTypeHTML()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    VisaType visaType = adminService.getJerseyVisaType(Integer.valueOf(id));
    return visaType;
  }
}
