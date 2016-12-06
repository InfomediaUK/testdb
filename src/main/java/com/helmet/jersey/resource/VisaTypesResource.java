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

import com.helmet.xml.jaxb.model.VisaType;

/**
 * @author infomedia
 *
 */
//Will map the resource to the URL visas
@Path("/visas")
public class VisaTypesResource
{
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of visas to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<VisaType> getVisaTypesBrowser()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyVisaTypes(true);
  }

  // Return the list of visas for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<VisaType> getVisaTypes()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyVisaTypes(true);
  }

  // retuns the number of visas
  // Use http://localhost:8080/jersey/rest/visas/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    List<VisaType> visaTypes = adminService.getJerseyVisaTypes(true);
    int count = visaTypes.size();
    return String.valueOf(count);
  }

  // Defines that the next path parameter after visas is
  // treated as a parameter and passed to the VisaTypeResources
  // Allows to type http://localhost:8080/jersey/rest/visas/1
  // 1 will be treated as parameter visatype and passed to VisaTypeResource
  @Path("{visatype}")
  public VisaTypeResource getVisaType(@PathParam("visatype") String id)
  {
    return new VisaTypeResource(uriInfo, request, id);
  }
}
