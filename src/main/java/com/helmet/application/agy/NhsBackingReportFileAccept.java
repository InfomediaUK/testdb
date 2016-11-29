package com.helmet.application.agy;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.PdfHandler;
import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingDate;
import com.helmet.bean.Client;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.nhs.BackingReport;
import com.helmet.bean.nhs.BackingReportLine;

public class NhsBackingReportFileAccept extends NhsFileUploadCommon
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    ActionForward actionForward = null;
    NhsBackingReport nhsBackingReport = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Client client = (Client)dynaForm.get("client");
    String accept = (String)dynaForm.get("accept");
    client = agyService.getClientUser(client.getClientId());
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    BackingReport backingReport = (BackingReport)request.getSession().getAttribute("backingReport");
    if (backingReport == null)
    {
      // Backing Report vanished from Session!
      errors.add("nhsBackingReportAccept", new ActionMessage("error.nhsBackingReportFile.vanishedFromSession"));
    }
    else
    {
      try
      {
        if (backingReport.getName().equals(accept))
        {
          // Backing Report found in Session. Process it...
          String serverName = request.getServerName();
          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
          nhsBackingReport = agyService.getNhsBackingReportForName(backingReport.getName(), false);
          backingReport.setNhsBackingReport(nhsBackingReport);
          if (nhsBackingReport == null || nhsBackingReport.getActive().equals(false))
          {
            // Nhs Backing Report NOT found or Inactive.
            if (nhsBackingReport == null)
            {
              // NHS Backing Report NOT found, create a new record.
              nhsBackingReport = new NhsBackingReport();
              nhsBackingReport.setActive(true);
            }
            if (nhsBackingReport.getActive().equals(false))
            {
              nhsBackingReport.setSubcontractValue(null);
              nhsBackingReport.setPaidValue(null);
              nhsBackingReport.setSubcontractPaidDate(null);
              nhsBackingReport.setCompleteDate(null);
            }
            nhsBackingReport.setComment(messageResources.getMessage("text.nhsBackingReport.createdFrom", backingReport.getFileName(), sdf.format(new java.util.Date())));
            newNhsBackingReport(agyService, nhsBackingReport, backingReport);
            // Write NHSInvoice...
            java.util.Date invoiceDate = new java.util.Date();
            PdfHandler.getInstance().writeNhsInvoice(messageResources, client, agency, nhsBackingReport, backingReport.getHospital(), backingReport.getWard(), invoiceDate, serverName);
            String documetationFileName = PdfHandler.getInstance().writeNhsBackingReportPdf(agency, backingReport);
            String rejectedDocumetationFileName = null;
            if (agency.getHasSubcontractors())
            {
              if (backingReport.getHasRejectedLines())
              {
                rejectedDocumetationFileName = PdfHandler.getInstance().writeNhsBackingReportRejectedPdf(agency, backingReport);
              }
            }
            // Get newly inserted/reactivated NhsBackingReport record and update its Documentation File Name. 
            nhsBackingReport = agyService.getNhsBackingReport(nhsBackingReport.getNhsBackingReportId());
            nhsBackingReport.setDocumentationFileName(documetationFileName);
            nhsBackingReport.setRejectedDocumentationFileName(rejectedDocumetationFileName);
            agyService.updateNhsBackingReport(nhsBackingReport, getConsultantLoggedIn().getConsultantId());
            updateBookingDatesForBackingReport(agyService, backingReport);
          }
          else
          {
            // Nhs Backing Report found.
            int rowCount = 0;
            nhsBackingReport.setSubcontractAgencyId(backingReport.getSubcontractAgencyId());
            nhsBackingReport.setStartDate(backingReport.getStartDate());
            nhsBackingReport.setEndDate(backingReport.getEndDate());
            nhsBackingReport.setAgreedValue(backingReport.getGrandTotalCost());
            if (backingReport.getSubcontractAgencyId() != null)
            {
              if (allWorkersAreSubcontracted(backingReport))
              {
                // All workers have an Original Agency. Set Subcontracted value to Agreed value.
                nhsBackingReport.setSubcontractValue(nhsBackingReport.getAgreedValue());
              }
            }
            if (StringUtils.isEmpty(nhsBackingReport.getComment()))
            {
              nhsBackingReport.setComment(messageResources.getMessage("text.nhsBackingReport.updatedFrom", backingReport.getFileName(), sdf.format(new java.util.Date())));
            }
            else
            {
              nhsBackingReport.setComment(nhsBackingReport.getComment() + "\n" + messageResources.getMessage("text.nhsBackingReport.updatedFrom", backingReport.getFileName(), sdf.format(new java.util.Date())));
            }
            rowCount = agyService.updateNhsBackingReport(nhsBackingReport, getConsultantLoggedIn().getConsultantId());
            File fileNhsInvoice = PdfHandler.getInstance().getNhsInvoiceFile(agency, nhsBackingReport);
//            if (!fileNhsInvoice.exists())
//            {
//              // Backing Report has changed from Invoice perspective or no Invoice exists. Write an Invoice.
            java.util.Date invoiceDate = new java.util.Date();
            PdfHandler.getInstance().writeNhsInvoice(messageResources, client, agency, nhsBackingReport, backingReport.getHospital(), backingReport.getWard(), invoiceDate, serverName);
//            } 
//            if (StringUtils.isEmpty(nhsBackingReport.getDocumentationFileName()))
//            {
//              // Backing Report has changed from Documentation perspective or no Documentation exists. Write Documentation file.
            String documetationFileName = PdfHandler.getInstance().writeNhsBackingReportPdf(agency, backingReport);
            String rejectedDocumetationFileName = null;
            if (agency.getHasSubcontractors())
            {
              if (StringUtils.isNotEmpty(nhsBackingReport.getRejectedDocumentationFileName()))
              {
                // Has Rejected Documentation.
                String nhsBackingReportRejectedFilePath = PdfHandler.getInstance().getNhsBackingReportFullFolderPath(agency) + nhsBackingReport.getRejectedDocumentationFileName();
                File nhsBackingReportRejectedFile = new File(nhsBackingReportRejectedFilePath);
                if (nhsBackingReportRejectedFile.exists())
                {
                  // Old version of file exists. Delete it.
                  nhsBackingReportRejectedFile.delete();
                } 
              }
              if (backingReport.getHasRejectedLines())
              {
                rejectedDocumetationFileName = PdfHandler.getInstance().writeNhsBackingReportRejectedPdf(agency, backingReport);
              }
            }
            // Get newly inserted/reactivated NhsBackingReport record and update its Documentation File Names. 
            nhsBackingReport = agyService.getNhsBackingReport(nhsBackingReport.getNhsBackingReportId());
            nhsBackingReport.setDocumentationFileName(documetationFileName);
            nhsBackingReport.setRejectedDocumentationFileName(rejectedDocumetationFileName);
            rowCount = agyService.updateNhsBackingReport(nhsBackingReport, getConsultantLoggedIn().getConsultantId());
//            }
            // It is possible to re-upload a backing report with a different number of shifts on it Beware...
            // Remove this NHS Backing Report from any BookingDate records it exists on.
            List<BookingDate> bookingDates = agyService.getBookingDatesForNhsBackingReport(nhsBackingReport.getName());
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
              agyService.updateBookingDatesBackingReport(bookingDateIds.toString(), "-" + nhsBackingReport.getName(), getConsultantLoggedIn().getAgencyId());
            }
            // Now add the NHS Backing Report to the Booking Dates.
            updateBookingDatesForBackingReport(agyService, backingReport);
          }
        }
        else
        {
          request.getSession().removeAttribute("backingReport");
          errors.add("nhsBackingReportAccept", new ActionMessage("error.nhsBackingReportFile.notAsAccepted", accept));
        }
      }
      catch (Exception e)
      {
        errors.add("nhsBackingReportAccept", new ActionMessage("error.nhsBackingReportFile.exception", e.getMessage()));
      }
    }
    logger.exit("Out going !!!");
    if (errors.isEmpty())
    {
      actionForward = mapping.findForward("success");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?nhsBackingReportUser.nhsBackingReportId=" + nhsBackingReport.getNhsBackingReportId(), actionForward.getRedirect());
    }
    else
    {
      saveErrors(request, errors);
      actionForward = mapping.findForward("error");
    }
    return actionForward;
  }

  /*
   * Note. The Backing Report may have Agency Workers on it that are NOT subcontracted to this Agency. In this case they are marked invalid.
   * 
   * It's all to do with 4SW...
   */
  private Boolean allWorkersAreSubcontracted(BackingReport backingReport)
  {
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      if (backingReportLine.getAgencyWorkerNameValid() && backingReportLine.getApplicantOriginalAgencyId().equals(0))
      {
        // Valid Agency Worker found that does not have an Original Agency.
        return false;
      }
    }
    // All workers have an Original Agency.
    return true;
  }
  

}
