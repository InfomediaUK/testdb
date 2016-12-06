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

import com.helmet.xml.jaxb.model.DisciplineCategory;

/**
 * @author infomedia
 *
 */
//Will map the resource to the URL disciplines
@Path("/disciplines")
public class DisciplineCategoriesResource
{
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of disciplines to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<DisciplineCategory> getDisciplineCategoriesBrowser()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyDisciplineCategories(true);
  }

  // Return the list of disciplines for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<DisciplineCategory> getDisciplineCategories()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    return adminService.getJerseyDisciplineCategories(true);
  }

  // retuns the number of disciplines
  // Use http://localhost:8080/jersey/rest/disciplines/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount()
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    List<DisciplineCategory> disciplineCategories = adminService.getJerseyDisciplineCategories(true);
    int count = disciplineCategories.size();
    return String.valueOf(count);
  }

  // Defines that the next path parameter after disciplines is
  // treated as a parameter and passed to the DisciplineResources
  // Allows to type http://localhost:8080/jersey/rest/disciplines/1
  // 1 will be treated as parameter discipline and passed to DisciplineResource
  @Path("{discipline}")
  public DisciplineCategoryResource getDisciplineCategory(@PathParam("discipline") String id)
  {
    return new DisciplineCategoryResource(uriInfo, request, id);
  }
}
