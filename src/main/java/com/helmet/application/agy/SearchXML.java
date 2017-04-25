package com.helmet.application.agy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Applicant;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.SiteUser;

public class SearchXML extends Action
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    String type = request.getParameter("type");
    StringBuffer sb = new StringBuffer();
    sb.append("<?xml version=\"1.0\"?>");
    sb.append("<list>");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    String agencyIdStr = request.getParameter("agencyId");
    Integer agencyId = agencyIdStr == null || "".equals(agencyIdStr) ? null : Integer.parseInt(agencyIdStr);
    // need to cater for ampersands in names !!!!
    if ("client".equals(type))
    {
      List<ClientUser> list = agyService.getClientUsersForAgency(agencyId);
      if (list != null)
      {
        for (ClientUser client : list)
        {
          sb.append("<item id=\"");
          sb.append(client.getClientId());
          sb.append("\" name=\"");
          sb.append(client.getName().replaceAll("&", "&amp;"));
          sb.append("\"/>");
        }
      }
    }

    if ("site".equals(type))
    {
      String clientIdStr = request.getParameter("clientId");
      Integer clientId = clientIdStr == null || "".equals(clientIdStr) ? null : Integer.parseInt(clientIdStr);
      List<SiteUser> list = agyService.getSiteUsersForAgency(agencyId, clientId);
      if (list != null)
      {
        for (SiteUser site : list)
        {
          sb.append("<item id=\"");
          sb.append(site.getSiteId());
          sb.append("\" name=\"");
          sb.append(site.getName().replaceAll("&", "&amp;"));
          sb.append("\"/>");
        }
      }
    }

    if ("location".equals(type))
    {
      String clientIdStr = request.getParameter("clientId");
      String siteIdStr = request.getParameter("siteId");
      Integer clientId = clientIdStr == null || "".equals(clientIdStr) ? null : Integer.parseInt(clientIdStr);
      Integer siteId = siteIdStr == null || "".equals(siteIdStr) ? null : Integer.parseInt(siteIdStr);
      List<LocationUser> list = agyService.getLocationUsersForAgency(agencyId, clientId, siteId);
      if (list != null)
      {
        for (LocationUser locationUser : list)
        {
          sb.append("<item id=\"");
          sb.append(locationUser.getLocationId());
          sb.append("\" name=\"");
          sb.append(locationUser.getName().replaceAll("&", "&amp;"));
          sb.append(", ");
          sb.append(locationUser.getSiteName().replaceAll("&", "&amp;"));
          sb.append("\"/>");
        }
      }
    }

    if ("jobProfile".equals(type))
    {
      String clientIdStr = request.getParameter("clientId");
      String siteIdStr = request.getParameter("siteId");
      String locationIdStr = request.getParameter("locationId");
      Integer clientId = clientIdStr == null || "".equals(clientIdStr) ? null : Integer.parseInt(clientIdStr);
      Integer siteId = siteIdStr == null || "".equals(siteIdStr) ? null : Integer.parseInt(siteIdStr);
      Integer locationId = locationIdStr == null || "".equals(locationIdStr) ? null : Integer.parseInt(locationIdStr);
      List<JobProfileUser> list = agyService.getJobProfileUsersForAgency(agencyId, clientId, siteId, locationId);
      if (list != null)
      {
        for (JobProfileUser jobProfileUser : list)
        {
          sb.append("<item id=\"");
          sb.append(jobProfileUser.getJobProfileId());
          sb.append("\" name=\"");
          sb.append(jobProfileUser.getName().replaceAll("&", "&amp;"));
          sb.append(" (" + jobProfileUser.getClientId() + ")");
          sb.append("\"/>");
        }
      }

    }

    if ("applicant".equals(type))
    {
      List<Applicant> list = agyService.getApplicantsForAgency(agencyId);
      if (list != null)
      {
        for (Applicant applicant : list)
        {
          sb.append("<item id=\"");
          sb.append(applicant.getApplicantId());
          sb.append("\" name=\"");
          sb.append(applicant.getUser().getFullName().replaceAll("&", "&amp;"));
          sb.append("\"/>");
        }
      }
    }

    sb.append("</list>");

    response.setHeader("Cache-Control", "max-age=30");
    response.setContentType("application/xml");
    response.setCharacterEncoding("UTF-8");
    response.setContentLength(sb.length());
    ServletOutputStream sos;
    try
    {
      sos = response.getOutputStream();
      sos.print(sb.toString());
      sos.flush();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logger.exit("Out going !!!");
    return null;
  }

}