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
import com.helmet.bean.TrainingCourseUser;
import com.helmet.bean.TrainingCompany;

public class ApplicantTrainingCourseXML extends Action
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
    String disciplineCategoryIdStr = request.getParameter("disciplineCategoryId");
    Integer disciplineCategoryId = disciplineCategoryIdStr == null || "".equals(disciplineCategoryIdStr) ? null : Integer.parseInt(disciplineCategoryIdStr);
    if ("trainingCourse".equals(type))
    {
      List<TrainingCourseUser> list = agyService.getTrainingCoursesForApplicantSelect(disciplineCategoryId);
      if (list != null)
      {
        for (TrainingCourseUser trainingCourse : list)
        {
          sb.append("<item id=\"");
          sb.append(trainingCourse.getTrainingCourseId());
          sb.append("\" name=\"");
          sb.append(trainingCourse.getNameWithMandatory().replaceAll("&", "&amp;"));
          sb.append("\"/>");
        }
      }
    }
    if ("trainingCompany".equals(type))
    {
      String trainingCourseIdStr = request.getParameter("trainingCourseId");
      Integer trainingCourseId = trainingCourseIdStr == null || "".equals(trainingCourseIdStr) ? null : Integer.parseInt(trainingCourseIdStr);
      List<TrainingCompany> list = agyService.getTrainingCompaniesForTrainingCourse(trainingCourseId);
      if (list != null)
      {
        for (TrainingCompany trainingCompany : list)
        {
          sb.append("<item id=\"");
          sb.append(trainingCompany.getTrainingCompanyId());
          sb.append("\" name=\"");
          sb.append(trainingCompany.getName().replaceAll("&", "&amp;"));
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