package com.helmet.reporting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SQLServlet extends HttpServlet
{
  /**
   * 
   */
  private static final long serialVersionUID = -1586679719013887278L;
  private String names = "show";

  @Override
  public void init(ServletConfig servletConfig) throws ServletException
  {
    super.init(servletConfig);
    System.out.print("*********************** SQLServlet STARTING UP ***********************\n");
    if (servletConfig.getInitParameter("names") != null)
    {
      this.names = servletConfig.getInitParameter("names");
    }
    System.out.print("names = " + names + "\n");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    response.setContentType("text/xml");
    response.setCharacterEncoding("UTF-8");

    PrintWriter out = response.getWriter();

    String id = request.getParameter("id");

    XMLGenerator xmlGenerator = XMLGenerator.getInstance();

    String paramNames[] = xmlGenerator.getParamNames(id);
    int paramCount = paramNames.length;
    String paramValues[] = new String[paramCount];

    for (int i = 0; i < paramCount; i++)
    {
      paramValues[i] = request.getParameter(paramNames[i]);
    }

    String xml = xmlGenerator.getDataAsString(id, paramValues, names=="show");

    out.println(xml);
  }

}
