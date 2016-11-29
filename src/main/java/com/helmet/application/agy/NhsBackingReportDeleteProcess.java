package com.helmet.application.agy;

import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.PdfHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingDate;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportDeleteProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    NhsBackingReportUser nhsBackingReportUser = (NhsBackingReportUser) dynaForm.get("nhsBackingReportUser");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    nhsBackingReportUser = agyService.getNhsBackingReportUser(nhsBackingReportUser.getNhsBackingReportId());
    File fileNhsInvoice = PdfHandler.getInstance().getNhsInvoiceFile(agency, nhsBackingReportUser);
    File fileNhsBackingReportDocumentation = null;
    if (StringUtils.isNotEmpty(nhsBackingReportUser.getDocumentationFileName()))
    {
      String nhsBackingReportDocumentationFile = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getDocumentationFileName();
      String nhsBackingReportDocumentationFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + nhsBackingReportDocumentationFile;
      fileNhsBackingReportDocumentation = new File(nhsBackingReportDocumentationFilePath);
    }    
    File fileNhsBackingReportRejectedDocumentation = null;
    if (StringUtils.isNotEmpty(nhsBackingReportUser.getRejectedDocumentationFileName()))
    {
      String nhsBackingReportRejectedDocumentationFile = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getRejectedDocumentationFileName();
      String nhsBackingReportRejectedDocumentationFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + nhsBackingReportRejectedDocumentationFile;
      fileNhsBackingReportRejectedDocumentation = new File(nhsBackingReportRejectedDocumentationFilePath);
    }    
    // Remove this NHS Backing Report from any BookingDate records it exists on.
    List<BookingDate> bookingDates = agyService.getBookingDatesForNhsBackingReport(nhsBackingReportUser.getName());
    if (bookingDates.size() > 0)
    {
      // BookingDates have been found that are ON the NHS Backing Report.
      StringBuffer bookingDateIds = new StringBuffer();
      for (BookingDate bookingDate : bookingDates)
      {
        if (bookingDateIds.length() > 0)
        {
          bookingDateIds.append(",");
        }
        bookingDateIds.append(bookingDate.getBookingDateId().toString());
      }
      // Remove NHS Backing Report from ALL BookingDates.
      agyService.updateBookingDatesBackingReport(bookingDateIds.toString(), "-" + nhsBackingReportUser.getName(), getConsultantLoggedIn().getAgencyId());
    }    
    // Now delete the Nhs Backing Report record. 
    int rowCount = agyService.deleteNhsBackingReport(nhsBackingReportUser.getNhsBackingReportId(), nhsBackingReportUser.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());
    if (rowCount == 1)
    {
      // Deleted NHS Backing Report
      if (fileNhsInvoice.exists())
      {
        // NHS Invoice exists. Delete it.
        fileNhsInvoice.delete();
      }
      if (fileNhsBackingReportDocumentation.exists())
      {
        // Documentation exists. Delete it.
        fileNhsBackingReportDocumentation.delete();
      }
      if (fileNhsBackingReportRejectedDocumentation != null && fileNhsBackingReportRejectedDocumentation.exists())
      {
        // Rejected Documentation exists. Delete it.
        fileNhsBackingReportRejectedDocumentation.delete();
      }
    }
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath(), actionForward.getRedirect());
  }

}
