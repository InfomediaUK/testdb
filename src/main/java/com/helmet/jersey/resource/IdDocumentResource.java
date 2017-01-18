package com.helmet.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.xml.jaxb.model.IdDocument;

/**
 * @author infomedia
 *
 */
public class IdDocumentResource
{
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  String id;

  public IdDocumentResource(UriInfo uriInfo, Request request, String id)
  {
    this.uriInfo = uriInfo;
    this.request = request;
    this.id = id;
  }

  // Application integration
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public IdDocument getIdDocument()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    IdDocument idDocument = adminService.getJerseyIdDocument(Integer.valueOf(id));
    return idDocument;
  }

  // For the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public IdDocument getIdDocumentHTML()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    IdDocument idDocument = adminService.getJerseyIdDocument(Integer.valueOf(id));
    return idDocument;
  }
}
