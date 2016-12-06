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

import com.helmet.xml.jaxb.model.Agency;

/**
 * @author infomedia
 *
 */
//Will map the resource to the URL agencies
@Path("/agencies")
public class AgenciesResource
{
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of agencies to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Agency> getAgencysBrowser()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyAgencies(true);
  }

  // Return the list of agencies for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<Agency> getAgencys()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyAgencies(true);
  }

  // retuns the number of agencies
  // Use http://localhost:8080/jersey/rest/agencies/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    List<Agency> agencies = adminService.getJerseyAgencies(true);
    int count = agencies.size();
    return String.valueOf(count);
  }

  // Defines that the next path parameter after agencies is
  // treated as a parameter and passed to the AgencyResources
  // Allows to type http://localhost:8080/jersey/rest/agencies/1
  // 1 will be treated as parameter agency and passed to AgencyResource
  @Path("{agency}")
  public AgencyResource getAgency(@PathParam("agency") String id)
  {
    return new AgencyResource(uriInfo, request, id);
  }
}
