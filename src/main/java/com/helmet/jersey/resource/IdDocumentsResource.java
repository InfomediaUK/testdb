package com.helmet.jersey.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
//Will map the resource to the URL iddocuments
@Path("/iddocuments")
public class IdDocumentsResource
{
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of iddocuments to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<IdDocument> getIdDocumentsBrowser()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyIdDocuments(true);
  }

  // Return the list of iddocuments for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<IdDocument> getIdDocuments()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyIdDocuments(true);
  }

  // retuns the number of iddocuments
  // Use http://localhost:8080/jersey/rest/iddocuments/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    List<IdDocument> idDocuments = adminService.getJerseyIdDocuments(true);
    int count = idDocuments.size();
    return String.valueOf(count);
  }

  // Defines that the next path parameter after iddocuments is
  // treated as a parameter and passed to the IdDocumentResources
  // Allows to type http://localhost:8080/jersey/rest/iddocuments/1
  // 1 will be treated as parameter iddocument and passed to IdDocumentResource
  @Path("{iddocument}")
  public IdDocumentResource getIdDocument(@PathParam("iddocument") String id)
  {
    return new IdDocumentResource(uriInfo, request, id);
  }
}
