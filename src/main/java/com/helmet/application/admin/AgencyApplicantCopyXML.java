package com.helmet.application.admin;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.Consultant;

import org.apache.commons.lang3.StringUtils;

public class AgencyApplicantCopyXML extends AdminAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    String type = request.getParameter("type");
    StringBuffer sb = new StringBuffer();
    sb.append("<?xml version=\"1.0\"?>");
    sb.append("<list>");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    String targetAgencyIdStr = request.getParameter("targetAgencyId");
    Integer targetAgencyId = StringUtils.isEmpty(targetAgencyIdStr) ? null : Integer.parseInt(targetAgencyIdStr);
    if ("agency".equals(type))
    {
      List<AgencyUser> list = adminService.getAgencyUsers(true);
      if (targetAgencyId != null)
      {
        removeAgencyFromList(targetAgencyId, list);
      }
      if (list != null)
      {
        for (AgencyUser agency : list)
        {
          sb.append("<item id=\"");
          sb.append(agency.getAgencyId());
          sb.append("\" name=\"");
          sb.append(agency.getName());
          sb.append("\"/>");
        }
      }
    }
    if ("consultant".equals(type))
    {
      String sourceAgencyIdStr = request.getParameter("sourceAgencyId");
      Integer sourceAgencyId = StringUtils.isEmpty(sourceAgencyIdStr) ? null : Integer.parseInt(sourceAgencyIdStr);
      // I realised that the auditor on the Applicant record should be a Consultant for the target Agency, not the source Agency...
      // Was: List<Consultant> list = adminService.getConsultantsForAgency(sourceAgencyId, true);
      List<Consultant> list = adminService.getConsultantsForAgency(targetAgencyId, true);
      if (list != null)
      {
        for (Consultant consultant : list)
        {
          sb.append("<item id=\"");
          sb.append(consultant.getConsultantId());
          sb.append("\" name=\"");
          sb.append(consultant.getUser().getFullName());
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
    return mapping.findForward("success");
  }
  
  private void removeAgencyFromList(Integer currentAgencyId, List<AgencyUser> list)
  {
    ListIterator listIterator = list.listIterator(list.size());
    Agency agency = null;
    while (listIterator.hasPrevious())
    {
      // For each Agencys (in reverse order)...
      agency = (Agency)listIterator.previous();
      if (agency.getAgencyId().equals(currentAgencyId))
      {
         // Found matching Agency. Now remove the Agency from its list.
         listIterator.remove();
         break;
      }
    }
  }
}
