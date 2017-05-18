package com.helmet.application.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.AgencyUserEntity;
import com.helmet.bean.ApplicantEntity;
import com.helmet.bean.ApplicantTrainingCourse;
import com.helmet.bean.Consultant;
import com.helmet.bean.TrainingCompany;
import com.helmet.bean.TrainingCompanyCourse;
import com.helmet.bean.TrainingCourse;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class AgencyApplicantTrainingProcess extends AdminAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    AgencyUserEntity agency = (AgencyUserEntity)dynaForm.get("agency");
    Integer consultantId = (Integer)dynaForm.get("consultantId");
    Consultant consultant = adminService.getConsultant(consultantId);
    agency = adminService.getAgencyUserEntity(agency.getAgencyId(), getShowOnlyActive());
    List<ApplicantEntity> agencyApplicantTrainingList = new ArrayList<ApplicantEntity>();
    Enumeration<String> paramNames = request.getParameterNames();
    int rowCount = 0;
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.equals("applicantId"))
      {
        int applicantId = 0;
        String[] paramValues = request.getParameterValues(paramName);
        for(int i = 0; i < paramValues.length; i++) 
        {
          // For each Applicant to be copied...
          applicantId = Integer.parseInt(paramValues[i]);
          ApplicantEntity applicant = adminService.getApplicantEntity(applicantId);
          if (applicant == null)
          {
            // Applicant NOT found is a disaster...
          }
          else
          {
            // Applicant found...
            agencyApplicantTrainingList.add(applicant);
          }
        }
      }
    }
    // 
    String trainingFilePath = null;
    for (ApplicantEntity applicant : agencyApplicantTrainingList)
    {
      if (!applicant.getHasApplicantTrainingCourses())
      {
        trainingFilePath = FileHandler.getInstance().getApplicantFileLocation() + applicant.getTrainingFileUrl();
        File trainingFile = new File(trainingFilePath);
        if (trainingFile.exists())
        {
          // The Training File actually exists, so it is worth continuing...
          processPDF(applicant, consultant, trainingFilePath);
        }
      }
    }
    dynaForm.set("agency", agency);
    dynaForm.set("consultantId", consultantId);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

  private void processPDF(ApplicantEntity applicant, Consultant consultant, String filePath)
  {
    PdfReader reader;
    try
    {
      reader = new PdfReader(filePath);
      String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
      if (textFromPage.contains("OSMOSIS"))
      {
        osmosisTraining(applicant, consultant, textFromPage);
      }
      else if (textFromPage.contains("London Cactus LTD"))
      {
//        text.append("CACTUS");
      }
      else if (textFromPage.contains("Health & Safety Group Ltd"))
      {
//        text.append("HSG");
      }
      else
      {
//        text.append("UNKNOWN");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void osmosisTraining(ApplicantEntity applicant, Consultant consultant, String pdfText)
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    TrainingCompany trainingCompany = adminService.getTrainingCompanyForNameStartsWith("OSMOSIS");
    TrainingCourse trainingCourse = null;
    TrainingCompanyCourse trainingCompanyCourse = null;
    String lines[] = pdfText.split("\\r?\\n");
    Pattern pattern      = Pattern.compile("(.+)([0-9]{2}/[0-9]{2}/[0-9]{4}) ([0-9]{2}/[0-9]{2}/[0-9]{4}) ([a-zA-Z0-9 ]+)");
    Matcher matcher      = null;
    String trainingCourseName = null;
    String dateStr = null;
    String location = null;
    Date startDate = null;
    Date endDate = null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    for (int i = 5; i < (lines.length - 1); i++)
    {
      if (!lines[i].startsWith("Name of course Date Valid to Location"))
      {
        matcher = pattern.matcher(lines[i]);
        while (matcher.find())
        {
          System.out.println(matcher.group());
          trainingCourseName = matcher.group(1).trim();
          System.out.println(trainingCourseName);
          trainingCourse = adminService.getTrainingCourseForName(trainingCourseName);
          if (trainingCourse != null)
          {
            dateStr = matcher.group(2).trim();
            System.out.println(dateStr);
            try
            {
              startDate = new Date(sdf.parse(dateStr).getTime());
            }
            catch (ParseException e)
            {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            dateStr = matcher.group(3).trim();
            System.out.println(dateStr);
            try
            {
              endDate = new Date(sdf.parse(dateStr).getTime());
            }
            catch (ParseException e)
            {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            location = matcher.group(4);
            System.out.println(location);
            trainingCompanyCourse = adminService.getTrainingCompanyCourseForTrainingCompanyAndTrainingCourse(trainingCompany.getTrainingCompanyId(), trainingCourse.getTrainingCourseId());
            if (trainingCompanyCourse != null)
            {
              writeApplicantTrainingCourse(applicant, consultant, trainingCourse, trainingCompanyCourse.getTrainingCompanyCourseId(), startDate, endDate, location);
            }
          }
        }
      }
    }
  }

  private void writeApplicantTrainingCourse(ApplicantEntity applicant, Consultant consultant, TrainingCourse trainingCourse, Integer trainingCompanyCourseId, Date startDate, Date endDate, String comment)
  {
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    ApplicantTrainingCourse applicantTrainingCourse = new ApplicantTrainingCourse();
    applicantTrainingCourse.setApplicantId(applicant.getApplicantId());
    applicantTrainingCourse.setStartDate(startDate);
    applicantTrainingCourse.setEndDate(endDate);
    applicantTrainingCourse.setTrainingCompanyCourseId(trainingCompanyCourseId);
    applicantTrainingCourse.setApplicantTrainingCourseId(adminService.getApplicantTrainingCourseId());
    applicantTrainingCourse.setDocumentationFileName(getDocumentationFileName(applicantTrainingCourse, trainingCourse));
    applicantTrainingCourse.setComment(comment);
    System.out.println(applicantTrainingCourse.toString());
    String trainingFilePath = FileHandler.getInstance().getApplicantFileLocation() + applicant.getTrainingFileUrl();
    Path pathTrainingFile = Paths.get(trainingFilePath);
    System.out.println(trainingFilePath);
    String applicantTrainingCourseFolderPath = applicantTrainingCourse.getDocumentationFileFolderPath();
    System.out.println(applicantTrainingCourseFolderPath);
    String applicantTrainingCourseFilePath = applicantTrainingCourse.getDocumentationFilePath();
    File applicantTrainingCourseFile = new File(applicantTrainingCourseFilePath);
    applicantTrainingCourseFile.getParentFile().mkdirs();
    Path pathApplicantTrainingCourseFile = Paths.get(applicantTrainingCourseFilePath);
    System.out.println(applicantTrainingCourseFilePath);
    int rowCount = adminService.insertApplicantTrainingCourse(applicantTrainingCourse, consultant.getConsultantId());
    try
    {
      Files.copy(pathTrainingFile, pathApplicantTrainingCourseFile);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private String getDocumentationFileName(ApplicantTrainingCourse applicantTrainingCourse, TrainingCourse trainingCourse)
  {
    return trainingCourse.getName().replaceAll(" ", "") + applicantTrainingCourse.getApplicantTrainingCourseId() + ".pdf";
  }

}
