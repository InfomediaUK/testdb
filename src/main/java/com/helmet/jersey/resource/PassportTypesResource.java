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

import com.helmet.xml.jaxb.model.PassportType;

/**
 * @author infomedia
 *
 */
//Will map the resource to the URL passports
@Path("/passports")
public class PassportTypesResource
{
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of passports to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<PassportType> getPassportTypesBrowser()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyPassportTypes(true);
  }

  // Return the list of passports for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<PassportType> getPassportTypes()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyPassportTypes(true);
  }

  // retuns the number of passports
  // Use http://localhost:8080/jersey/rest/passports/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    List<PassportType> passportTypes = adminService.getJerseyPassportTypes(true);
    int count = passportTypes.size();
    return String.valueOf(count);
  }

  // Defines that the next path parameter after passports is
  // treated as a parameter and passed to the PassportTypeResources
  // Allows to type http://localhost:8080/jersey/rest/passports/1
  // 1 will be treated as parameter passporttype and passed to PassportTypeResource
  @Path("{passporttype}")
  public PassportTypeResource getPassportType(@PathParam("passporttype") String id)
  {
    return new PassportTypeResource(uriInfo, request, id);
  }
}
