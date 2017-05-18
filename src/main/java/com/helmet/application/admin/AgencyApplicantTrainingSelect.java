package com.helmet.application.admin;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

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
import com.helmet.bean.Consultant;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class AgencyApplicantTrainingSelect extends AdminAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    AgencyUserEntity agency = (AgencyUserEntity)dynaForm.get("agency");
    Integer consultantId = (Integer)dynaForm.get("consultantId");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    agency = adminService.getAgencyUserEntity(agency.getAgencyId(), getShowOnlyActive());
    Consultant consultant = adminService.getConsultant(consultantId);
    List<ApplicantEntity> list = adminService.getApplicantEntitiesForAgencyWithBLSAndMHTraining(agency.getAgencyId());
    removeApplicantWithTrainingCoursesFromList(list);
    String trainingFilePath = null;
    for (ApplicantEntity applicant : list)
    {
      trainingFilePath = FileHandler.getInstance().getApplicantFileLocation() + applicant.getTrainingFileUrl();
      applicant.setTrainingDocumentInfo(readDataFromPDF(trainingFilePath));
    }
    dynaForm.set("agency", agency);
    dynaForm.set("consultantId", consultant.getConsultantId());
    dynaForm.set("list", list);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }
  
  private String readDataFromPDF(String filePath)
  {
    StringBuffer text = new StringBuffer();
    PdfReader reader;
    try
    {
      reader = new PdfReader(filePath);
      String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
      if (textFromPage.contains("OSMOSIS"))
      {
        text.append("OSMOSIS > ");
        text.append(osmosis(textFromPage));
      }
      else if (textFromPage.contains("London Cactus LTD"))
      {
        text.append("CACTUS");
      }
      else if (textFromPage.contains("Health & Safety Group Ltd"))
      {
        text.append("HSG > ");
        text.append(hsg(textFromPage));
      }
      else
      {
        text.append("UNKNOWN");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    return text.toString();
  }

  private String osmosis(String pdfText)
  {
    StringBuffer text = new StringBuffer();
    String lines[] = pdfText.split("\\r?\\n");
    for (int i = 5; i < (lines.length - 1); i++)
    {
      appendLine(text, lines[i]);
    }
    return text.toString();
  }

  private String hsg(String pdfText)
  {
    StringBuffer text = new StringBuffer();
    String lines[] = pdfText.split("\\r?\\n");
    String line = null;
    for (int i = 0; i < lines.length; i++)
    {
      line = lines[i];
      if (line.contains("Date (Valid for one year):"))
      {
        appendLine(text, lines[i+1].replaceAll("(?<=[0-9])(?:st|nd|rd|th)", ""));
      }
      else if (line.contains("Date:"))
      {
        appendLine(text, lines[i - 1].replaceAll("(?<=[0-9])(?:st|nd|rd|th)", ""));
      }
      else if (line.contains("Basic Life Support"))
      {
        appendLine(text, "Basic Life Support");
      }
      else if (line.contains("Moving & Handling"))
      {
        appendLine(text, "Moving & Handling");
      }
    }
    return text.toString();
  }

  private void appendLine(StringBuffer text, String line)
  {
    if (text.length() > 0)
    {
      text.append(" | ");
    }
    text.append(line);
  }

  private void removeApplicantWithTrainingCoursesFromList(List<ApplicantEntity> list)
  {
    ListIterator<ApplicantEntity> listIterator = list.listIterator(list.size());
    ApplicantEntity applicant = null;
    while (listIterator.hasPrevious())
    {
      // For each Applicant (in reverse order)...
      applicant = (ApplicantEntity)listIterator.previous();
      if (applicant.getHasApplicantTrainingCourses())
      {
         // Applicant has TrainingCourses. Now remove it from the list.
         listIterator.remove();
      }
    }
  }
}
