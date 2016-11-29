package com.helmet.application.agy;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.BookingDateUserApplicantReportGroup;
import com.helmet.application.FileHandler;
import com.helmet.application.PdfHandler;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.ClientUser;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportEditProcess extends NhsBackingReportCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    NhsBackingReportUser nhsBackingReportUser = (NhsBackingReportUser)dynaForm.get("nhsBackingReportUser");
    FormFile nhsBackingReportDocumentationFormFile = (FormFile)dynaForm.get("nhsBackingReportDocumentationFormFile");
    if (StringUtils.isNotEmpty(nhsBackingReportDocumentationFormFile.getFileName()))
    {
      nhsBackingReportUser.setDocumentationFileName(getDocumentationFileName(nhsBackingReportUser));
    }
    FormFile nhsBackingReportRejectedDocumentationFormFile = (FormFile)dynaForm.get("nhsBackingReportRejectedDocumentationFormFile");
    if (StringUtils.isNotEmpty(nhsBackingReportRejectedDocumentationFormFile.getFileName()))
    {
      nhsBackingReportUser.setRejectedDocumentationFileName(getRejectedDocumentationFileName(nhsBackingReportUser));
    }
    NhsBackingReportUser oldNhsBackingReportUser = null;
    Boolean canChangeName = (Boolean)dynaForm.get("canChangeName");
    String startDateStr = (String)dynaForm.get("startDateStr");
    String endDateStr = (String)dynaForm.get("endDateStr");
    String completeDateStr = (String)dynaForm.get("completeDateStr");
    java.util.Date invoiceDate = new java.util.Date();
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);

    if (StringUtils.isEmpty(startDateStr))
    {
      nhsBackingReportUser.setStartDate(null);
    }
    else
    {
      Date startDate =  convertDate(startDateStr, sdf, errors, messageResources, "label.startDate");
      nhsBackingReportUser.setStartDate(startDate);
    }
    if (StringUtils.isEmpty(endDateStr))
    {
      nhsBackingReportUser.setEndDate(null);
    }
    else
    {
      Date endDate =  convertDate(endDateStr, sdf, errors, messageResources, "label.endDate");
      nhsBackingReportUser.setEndDate(endDate);
    }
    if (StringUtils.isEmpty(completeDateStr))
    {
      nhsBackingReportUser.setCompleteDate(null);
    }
    else
    {
      Date completeDate =  convertDate(completeDateStr, sdf, errors, messageResources, "label.completeDate");
      nhsBackingReportUser.setCompleteDate(completeDate);
    }
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    // Note. NHS Backing Report Name cannot be changed if it has ANY Timesheets on it.
    if (validateDates(errors, nhsBackingReportUser))
    {
      oldNhsBackingReportUser = agyService.getNhsBackingReportUser(nhsBackingReportUser.getNhsBackingReportId()); 
      if (nhsBackingReportUser.getStartDate().equals(oldNhsBackingReportUser.getStartDate()) && nhsBackingReportUser.getEndDate().equals(oldNhsBackingReportUser.getEndDate()))
      {
        // The Start and/or End Date has NOT changed.
      }
      else
      {
        checkForExistingTimesheetsOutsideDateRange(errors, nhsBackingReportUser, oldNhsBackingReportUser, agyService, sdf);
      }
    }

    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      String nhsInvoiceFile = FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getName() + ".pdf";
      String nhsInvoiceFilePath = FileHandler.getInstance().getNhsInvoiceFileLocation() + nhsInvoiceFile;
      File fileNhsInvoice = new File(nhsInvoiceFilePath);
      dynaForm.set("canChangeName", canChangeName);
      dynaForm.set("nhsInvoiceFile", fileNhsInvoice.exists() ? nhsInvoiceFile : "");
      dynaForm.set("nhsInvoiceFileDate", new Date(fileNhsInvoice.lastModified()));
      repopulateClientAgencyUserList(agyService, dynaForm);
      return mapping.getInputForward();
    }

    try
    {
      int rowCount = agyService.updateNhsBackingReport(nhsBackingReportUser, getConsultantLoggedIn().getConsultantId());
      if (!nhsBackingReportUser.getAgreedValue().equals(oldNhsBackingReportUser.getAgreedValue()))
      {
        String nhsInvoiceFile = FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getName() + ".pdf";
        String nhsInvoiceFilePath = FileHandler.getInstance().getNhsInvoiceFileLocation() + nhsInvoiceFile;
        File fileNhsInvoice = new File(nhsInvoiceFilePath);
        if (fileNhsInvoice.exists())
        {
          // Agreed Value has changed and Invoice exists. Recreate Invoice.
          ClientUser client = agyService.getClientUser(nhsBackingReportUser.getClientId());
          AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
          List<BookingDateUserApplicant> listBookingDateUserApplicant = getBookingDateUserApplicantListForBackingReportName(agyService, nhsBackingReportUser.getStartDate(), nhsBackingReportUser.getEndDate(), nhsBackingReportUser.getName());
          List<BookingDateUserApplicantReportGroup> listBookingDateUserApplicantReportGroup = loadBookingDateUserApplicantReportGroupList(listBookingDateUserApplicant);
          String nhsLocation = null;
          String nhsWard = null;
          if (listBookingDateUserApplicantReportGroup.size() == 1)
          {
            // Only ONE Ward.
            nhsLocation = listBookingDateUserApplicantReportGroup.get(0).getSiteName();
            nhsWard = listBookingDateUserApplicantReportGroup.get(0).getLocationName();
          }
          else
          {
            // More than ONE Ward but maybe only ONE Location...
            nhsLocation = getSingleNhsLocation(listBookingDateUserApplicantReportGroup);
          }
          try
          {
            String serverName = request.getServerName();
            PdfHandler.getInstance().writeNhsInvoice(messageResources, client, agency, nhsBackingReportUser, nhsLocation, nhsWard, invoiceDate, serverName);
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      }
    }
    catch (DuplicateDataException e)
    {
      errors.add("nhsBackingReport", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    if (StringUtils.isNotEmpty(nhsBackingReportDocumentationFormFile.getFileName()))
    {
      String filePath = null;
      try
      {
        filePath = uploadNhsBackingReportFile(nhsBackingReportUser.getDocumentationFileName(), nhsBackingReportDocumentationFormFile);
      }
      catch (Exception e)
      {
        errors.add("nhsBackingReport", new ActionMessage("error.nhsBackingReportFile.uploadFailed", filePath, e.getMessage()));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
    }    
    if (StringUtils.isNotEmpty(nhsBackingReportRejectedDocumentationFormFile.getFileName()))
    {
      String filePath = null;
      try
      {
        filePath = uploadNhsBackingReportFile(nhsBackingReportUser.getRejectedDocumentationFileName(), nhsBackingReportRejectedDocumentationFormFile);
      }
      catch (Exception e)
      {
        errors.add("nhsBackingReport", new ActionMessage("error.nhsBackingReportFile.uploadFailed", filePath, e.getMessage()));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
    }    
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?nhsBackingReportUser.nhsBackingReportId=" + nhsBackingReportUser.getNhsBackingReportId(), actionForward.getRedirect());
  }

  // The Start Date or the End Date has changed. This may invalidate the attached Timesheets...
  private void checkForExistingTimesheetsOutsideDateRange(ActionMessages errors, NhsBackingReport nhsBackingReport, NhsBackingReport oldNhsBackingReport, AgyService agyService, SimpleDateFormat sdf)
  {
    if (nhsBackingReport.getStartDate().before(oldNhsBackingReport.getStartDate()) && nhsBackingReport.getEndDate().after(oldNhsBackingReport.getEndDate()))
    {
      // The NEW Start Date is before the Old Start Date and the NEW End Date is after the Old End Date. No validation necessary.
    }
    else
    {
      // The Start and/or End Date has changed. Check if this invalidates any Timesheets.
      // The Name of the Backing Report can ONLY be changed if there are NO Timesheets on it.
      Date startDateToSearch = null;
      Date endDateToSearch = null;
      if (nhsBackingReport.getEndDate().before(oldNhsBackingReport.getEndDate()))
      {
        // New End Date before Old End Date.
        startDateToSearch = nhsBackingReport.getEndDate().after(oldNhsBackingReport.getStartDate()) ? nhsBackingReport.getEndDate() : oldNhsBackingReport.getStartDate();
        if (!nhsBackingReport.getEndDate().before(oldNhsBackingReport.getStartDate()))
        {
          // New and Old date ranges overlap.
          startDateToSearch = getNextDay(startDateToSearch);
        }
        List<BookingDateUserApplicant> listBookingDateUserApplicant = getBookingDateUserApplicantListForBackingReportName(agyService, startDateToSearch, oldNhsBackingReport.getEndDate(), nhsBackingReport.getName());
        if (listBookingDateUserApplicant.size() > 0)
        {
          if (oldNhsBackingReport.getEndDate().equals(startDateToSearch))
          {
            errors.add("nhsBackingReport", new ActionMessage("errors.timesheetsExistOn", sdf.format(oldNhsBackingReport.getEndDate())));
          } 
          else
          {
            errors.add("nhsBackingReport", new ActionMessage("errors.timesheetsExistBetween", sdf.format(startDateToSearch), sdf.format(oldNhsBackingReport.getEndDate())));
          }          
        }
      }
      if (nhsBackingReport.getStartDate().after(oldNhsBackingReport.getStartDate()))
      {
        // New Start Date after Old Start Date.
        endDateToSearch = nhsBackingReport.getStartDate().before(oldNhsBackingReport.getEndDate()) ? nhsBackingReport.getStartDate() : oldNhsBackingReport.getEndDate();
        if (!nhsBackingReport.getStartDate().after(oldNhsBackingReport.getEndDate()))
        {
          // New and Old date ranges overlap.
          endDateToSearch = getPreviousDay(endDateToSearch);
        }
        List<BookingDateUserApplicant> listBookingDateUserApplicant = getBookingDateUserApplicantListForBackingReportName(agyService, oldNhsBackingReport.getStartDate(), endDateToSearch, nhsBackingReport.getName());
        if (listBookingDateUserApplicant.size() > 0)
        {
          if (oldNhsBackingReport.getStartDate().equals(endDateToSearch))
          {
            errors.add("nhsBackingReport", new ActionMessage("errors.timesheetsExistOn", sdf.format(oldNhsBackingReport.getStartDate())));
          }
          else
          {
            errors.add("nhsBackingReport", new ActionMessage("errors.timesheetsExistBetween", sdf.format(oldNhsBackingReport.getStartDate()), sdf.format(endDateToSearch)));
          }          
        }
      }
    }        
    
  }

  private Date getNextDay(Date date)
  {
    Calendar calendar = Calendar.getInstance();  // TimeZone.getTimeZone("GMT")
    calendar.setTime(date);
    calendar.add(Calendar.DATE, 1);
    return new Date(calendar.getTime().getTime());
  }
  
  private Date getPreviousDay(Date date)
  {
    Calendar calendar = Calendar.getInstance();  // TimeZone.getTimeZone("GMT")
    calendar.setTime(date);
    calendar.add(Calendar.DATE, -1);
    return new Date(calendar.getTime().getTime());
  }

}
