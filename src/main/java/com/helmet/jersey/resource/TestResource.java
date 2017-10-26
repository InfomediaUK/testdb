package com.helmet.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * @author infomedia
 *
 */
//Will map the resource to the URL test
@Path("/test")
public class TestResource
{
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the string "Found"
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getTest()
  {
    return "Found";
  }

}
