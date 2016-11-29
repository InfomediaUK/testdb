package com.helmet.application.agy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.application.BookingDateUserApplicantReportGroup;
import com.helmet.application.FileHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.ClientAgencyUser;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.NhsBackingReportUser;

public abstract class NhsBackingReportCommon extends AgyAction
{
  protected void validateName(ActionMessages errors, NhsBackingReport nhsBackingReport)
  {
    if (StringUtils.isEmpty(nhsBackingReport.getName()))
    {
      errors.add("nhsBackingReport", new ActionMessage("errors.required", "Name"));
    }
    if (nhsBackingReport.getName().length() < AgyConstants.BACKING_REPORT_NAME_MINIMUM_LENGTH)
    {
      errors.add("nhsBackingReport", new ActionMessage("errors.minlength", "Name", AgyConstants.BACKING_REPORT_NAME_MINIMUM_LENGTH));
    }
    if (nhsBackingReport.getName().length() > AgyConstants.BACKING_REPORT_NAME_MAXIMUM_LENGTH)
    {
      errors.add("nhsBackingReport", new ActionMessage("errors.maxlength", "Name", AgyConstants.BACKING_REPORT_NAME_MAXIMUM_LENGTH));
    }
  }
  
  protected boolean validateDates(ActionMessages errors, NhsBackingReport nhsBackingReport)
  {
    Boolean result = true;
    if (nhsBackingReport.getStartDate() == null)
    {
      errors.add("nhsBackingReport", new ActionMessage("errors.required", "Start Date"));
      result = false;
    }
    if (nhsBackingReport.getEndDate() == null)
    {
      errors.add("nhsBackingReport", new ActionMessage("errors.required", "End Date"));
      result = false;
    }
    if (nhsBackingReport.getStartDate() != null && nhsBackingReport.getEndDate() != null)
    {
      if (nhsBackingReport.getStartDate().after(nhsBackingReport.getEndDate()))
      {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        errors.add("nhsBackingReport", new ActionMessage("errors.dateRangeInvalid", df.format(nhsBackingReport.getStartDate()), df.format(nhsBackingReport.getEndDate())));
        result = false;
      }
    }
    return result;
  }

//  protected Date convertDate(String dateStr, SimpleDateFormat simpleDateFormat, ActionMessages errors, MessageResources messageResources, String messageKey)
//  {
//    Date date = null;
//    if (StringUtils.isNotEmpty(dateStr))
//    {
//      try
//      {
//        date = new Date(simpleDateFormat.parse(dateStr).getTime());
//      }
//      catch (ParseException e)
//      {
//        errors.add("nhsBackingReport", new ActionMessage("errors.invalidDateFormat", messageResources.getMessage(messageKey)));
//      }
//    }
//    return date;
//  }
//
  protected void repopulateClientAgencyUserList(AgyService agyService, DynaValidatorForm dynaForm)
  {
    List<ClientAgencyUser> clientAgencyUserList = agyService.getClientAgencyUsersForAgency(getConsultantLoggedIn().getAgencyId(), true);
    dynaForm.set("clientAgencyUserList", clientAgencyUserList);
  }

  protected List<BookingDateUserApplicant> getBookingDateUserApplicantListForBackingReportName(AgyService agyService, Date startDateToSearch, Date endDateToSearch, String nhsBackingReportName)
  {
    Integer bookingGradeStatus = null;
    Integer bookingDateStatus = null;
    Integer workedStatus = BookingGrade.BOOKINGGRADE_STATUS_CLOSED;
    List<BookingDateUserApplicant> listBookingDateUserApplicant = agyService.getBookingDateUserApplicantsForAgencyBackingReport(getConsultantLoggedIn().getAgencyId(), null, null, null, null, bookingGradeStatus,
        bookingDateStatus, workedStatus, null, null, null, null, null, null, null, null, startDateToSearch, endDateToSearch, nhsBackingReportName);
    return listBookingDateUserApplicant;
  }
  // Special version for 4SW.
  protected List<BookingDateUserApplicant> getBookingDateUserApplicantListForBackingReportName4SW(AgyService agyService, Date startDateToSearch, Date endDateToSearch, String nhsBackingReportName)
  {
    Integer bookingGradeStatus = null;
    Integer bookingDateStatus = null;
    Integer workedStatus = BookingGrade.BOOKINGGRADE_STATUS_OPEN;
    List<BookingDateUserApplicant> listBookingDateUserApplicant = agyService.getBookingDateUserApplicantsForAgencyBackingReport(getConsultantLoggedIn().getAgencyId(), null, null, null, null, bookingGradeStatus,
        bookingDateStatus, workedStatus, null, null, null, null, null, null, null, null, startDateToSearch, endDateToSearch, nhsBackingReportName);
    return listBookingDateUserApplicant;
  }
  
  protected List<BookingDateUserApplicantReportGroup> loadBookingDateUserApplicantReportGroupList(List<BookingDateUserApplicant> listBookingDateUserApplicant)
  {
    List<BookingDateUserApplicantReportGroup> listBookingDateUserApplicantReportGroup = new ArrayList<BookingDateUserApplicantReportGroup>();
    BookingDateUserApplicantReportGroup bookingDateUserApplicantReportGroup = null;
    for (BookingDateUserApplicant bookingDateUserApplicant : listBookingDateUserApplicant)
    {
      // For each BookingDateUserApplicant... 
      if (bookingDateUserApplicantReportGroup != null && bookingDateUserApplicantReportGroup.getReportGroupKey().equals(bookingDateUserApplicant.getReportGroupKey()))
      {
        // BookingDateUserApplicant is for current Report Group, just add it to the group's list.
        bookingDateUserApplicantReportGroup.addBookingDateUserApplicantToList(bookingDateUserApplicant);
      }
      else
      {
        // BookingDateUserApplicant is NOT for current Report Group. Create a new group and add BookingDateUserApplicant to the group's list. 
        bookingDateUserApplicantReportGroup = new BookingDateUserApplicantReportGroup(bookingDateUserApplicant);
        bookingDateUserApplicantReportGroup.addBookingDateUserApplicantToList(bookingDateUserApplicant);
        // Add the BookingDateUserApplicantReportGroup to the list of BookingDateUserApplicantReportGroups.
        listBookingDateUserApplicantReportGroup.add(bookingDateUserApplicantReportGroup);
      }
    }
    return listBookingDateUserApplicantReportGroup;
  }
 
  
  // NOTE. An NHS Location is an MMJ Site.
  protected String getSingleNhsLocation(List<BookingDateUserApplicantReportGroup> listBookingDateUserApplicantReportGroup)
  {
    String nhsLocation = "";
    HashMap<Integer, String> mapLocation = new HashMap<Integer, String>();
    Integer siteId = null;
    // Build Map of LocationIds.
    for (BookingDateUserApplicantReportGroup bookingDateUserApplicantReportGroup : listBookingDateUserApplicantReportGroup)
    {
      siteId = bookingDateUserApplicantReportGroup.getSiteId();
      if (mapLocation.get(siteId) == null)
      {
        mapLocation.put(siteId, bookingDateUserApplicantReportGroup.getSiteName());
      }
    }
    if (mapLocation.size() == 1)
    {
      // Only one Location.
      nhsLocation = mapLocation.get(siteId);
    }
    return nhsLocation;
  }

  protected String getDocumentationFileName(NhsBackingReport nhsBackingReport)
  {
    return "NHS Backing Report " + nhsBackingReport.getName() + ".pdf";
  }
  
  protected String getRejectedDocumentationFileName(NhsBackingReport nhsBackingReport)
  {
    return "NHS Backing Report " + nhsBackingReport.getName() + " Rejected.pdf";
  }
  
  protected String uploadNhsBackingReportFile(String fileName, FormFile nhsBackingReportFormFile) 
    throws FileNotFoundException, IOException
  {
    // Backing Report file name should be in format "nhs" + Backing Report Name + .pdf.
    String fileUrl = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + fileName;
    String filePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + fileUrl;

    // Read the InputStream and store it in a 'byteArrayOutputStream'.
    byte[] fileData = nhsBackingReportFormFile.getFileData();

    File folder = new File(filePath).getParentFile();
    if (!folder.exists())
    {
      // Create any required directories.
      folder.mkdirs();
    }
    FileOutputStream fileOutputStream = null;
    try
    {
      fileOutputStream = new FileOutputStream(filePath);
      fileOutputStream.write(fileData);
    }
    finally
    {
      fileOutputStream.close();
    }
    return filePath;
  }
  
  protected void setUpFormForSubcontractInvoice(NhsBackingReportUser nhsBackingReportUser, DynaValidatorForm dynaForm)
  {
    if (nhsBackingReportUser.getSubcontractAgencyId() > 0)
    {
      String subcontractInvoiceFile = FileHandler.getInstance().getNhsInvoiceFileFolder() + 
      "/a" + getConsultantLoggedIn().getAgencyId() + 
      "/a" + nhsBackingReportUser.getSubcontractAgencyId() + "/" + nhsBackingReportUser.getName() + "SUB.pdf";
      String subcontractInvoiceFilePath = FileHandler.getInstance().getNhsInvoiceFileLocation() + subcontractInvoiceFile;
      File fileSubcontractInvoice = new File(subcontractInvoiceFilePath);
      dynaForm.set("subcontractInvoiceFile", fileSubcontractInvoice.exists() ? subcontractInvoiceFile : "");
      dynaForm.set("subcontractInvoiceFileDate", new Date(fileSubcontractInvoice.lastModified()));
    }
  }
}
