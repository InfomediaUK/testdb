package com.helmet.application.agy;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.BookingDateUserApplicantReportGroup;
import com.helmet.application.FileHandler;
import com.helmet.application.PdfHandler;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportShiftList extends NhsBackingReportCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");
    ActionForward forward = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    NhsBackingReportUser nhsBackingReportUser = (NhsBackingReportUser)dynaForm.get("nhsBackingReportUser");
    nhsBackingReportUser= agyService.getNhsBackingReportUser(nhsBackingReportUser.getNhsBackingReportId());
    // Used to form NHS Invoice url.
    String nhsInvoiceFile = PdfHandler.getInstance().getNhsInvoiceFileFolder(agency) + "/" + PdfHandler.getInstance().getNhsInvoiceFileName(agency, nhsBackingReportUser); 
    // Used for link text in jsp.
    String nhsInvoiceFileName = PdfHandler.getInstance().getNhsInvoiceFileName(agency, nhsBackingReportUser); 
    File fileNhsInvoice = PdfHandler.getInstance().getNhsInvoiceFile(agency, nhsBackingReportUser);
//    System.out.println("nhsInvoiceFilePath: " + nhsInvoiceFilePath + "  exits:" + fileNhsInvoice.exists());
    Date startDate = nhsBackingReportUser.getStartDate();
    Date endDate   = nhsBackingReportUser.getEndDate();
    
//    String bookingGradeStatusStr = (String)dynaForm.get("bookingGradeStatus");
//    String bookingDateStatusStr = (String)dynaForm.get("bookingDateStatus");
//    String workedStatusStr = (String)dynaForm.get("workedStatus");
//    String viewedStr = (String)dynaForm.get("viewed");
//    String singleCandidateStr = (String)dynaForm.get("singleCandidate");

//    Boolean singleCandidate = "".equals(singleCandidateStr) ? null : "true".equals(singleCandidateStr);
//    Integer bookingGradeStatus = "".equals(bookingGradeStatusStr) ? null : Integer.parseInt(bookingGradeStatusStr);
//    Integer bookingDateStatus = "".equals(bookingDateStatusStr) ? null : Integer.parseInt(bookingDateStatusStr);
//    Integer workedStatus = BookingGrade.BOOKINGGRADE_STATUS_CLOSED;
//    Boolean viewed = "".equals(viewedStr) ? null : "true".equals(viewedStr);
//    List<BookingDateUserApplicantReportGroup> listBookingDateUserApplicantReportGroup = new ArrayList<BookingDateUserApplicantReportGroup>();
//    List<BookingDateUserApplicant> listBookingDateUserApplicant = null;
    List<BookingDateUserApplicant> listBookingDateUserApplicant = null;
    if (agency.getHasSubcontractors())
    {
      listBookingDateUserApplicant = getBookingDateUserApplicantListForBackingReportName4SW(agyService, startDate, endDate, nhsBackingReportUser.getName());
    }
    else
    {
      listBookingDateUserApplicant = getBookingDateUserApplicantListForBackingReportName(agyService, startDate, endDate, nhsBackingReportUser.getName());
    }
//    listBookingDateUserApplicant = agyService.getBookingDateUserApplicantsForAgencyBackingReport(getConsultantLoggedIn().getAgencyId(), null, null, null, null, bookingGradeStatus, bookingDateStatus,
//        workedStatus, singleCandidate, null, viewed, null, null, null, null, null, startDate, endDate, nhsBackingReportUser.getName());
    // in ancestor class
    sortShiftTotals(listBookingDateUserApplicant, dynaForm);

    String nhsBackingReportFile = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getDocumentationFileName();
    String nhsBackingReportFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + nhsBackingReportFile;
    File fileNhsBackingReport = new File(nhsBackingReportFilePath);
    dynaForm.set("nhsBackingReportDocumentationFile", fileNhsBackingReport.exists() ? nhsBackingReportFile : "");
    String nhsBackingReportDocumentationLinkTitle = messageResources.getMessage("title.viewNhsBackingReportDocumentation") + " - " + sdf.format(new Date(fileNhsBackingReport.lastModified()));;
    dynaForm.set("nhsBackingReportDocumentationLinkTitle", nhsBackingReportDocumentationLinkTitle);
    
    String nhsBackingReportRejectedFile = FileHandler.getInstance().getNhsBackingReportFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBackingReportUser.getRejectedDocumentationFileName();
    String nhsBackingReportRejectedFilePath = FileHandler.getInstance().getNhsBackingReportFileLocation() + nhsBackingReportRejectedFile;
    File fileNhsBackingReportRejected = new File(nhsBackingReportRejectedFilePath);
    dynaForm.set("nhsBackingReportRejectedDocumentationFile", fileNhsBackingReportRejected.exists() ? nhsBackingReportRejectedFile : "");
    String nhsBackingReportRejectedDocumentationLinkTitle = messageResources.getMessage("title.viewNhsBackingReportRejectedDocumentation") + " - " + sdf.format(new Date(fileNhsBackingReportRejected.lastModified()));;
    dynaForm.set("nhsBackingReportRejectedDocumentationLinkTitle", nhsBackingReportRejectedDocumentationLinkTitle);
    
    dynaForm.set("list", listBookingDateUserApplicant);
    List<BookingDateUserApplicantReportGroup> listBookingDateUserApplicantReportGroup = loadBookingDateUserApplicantReportGroupList(listBookingDateUserApplicant);
    dynaForm.set("nhsBackingReportUser", nhsBackingReportUser);
    dynaForm.set("nhsInvoiceFile", fileNhsInvoice.exists() ? nhsInvoiceFile : "");
    dynaForm.set("nhsInvoiceFileName", fileNhsInvoice.exists() ? nhsInvoiceFileName : "");
    String nhsInvoiceLinkTitle = messageResources.getMessage("title.viewNhsInvoice") + " - " + sdf.format(new Date(fileNhsInvoice.lastModified()));
    dynaForm.set("nhsInvoiceLinkTitle", nhsInvoiceLinkTitle);
    if (nhsBackingReportUser.getSubcontractAgencyId() > 0)
    {
      String subcontractInvoiceFile = FileHandler.getInstance().getNhsInvoiceFileFolder() + 
        "/a" + getConsultantLoggedIn().getAgencyId() + 
        "/a" + nhsBackingReportUser.getSubcontractAgencyId() + "/" + nhsBackingReportUser.getName() + "SUB.pdf";
      String subcontractInvoiceFilePath = FileHandler.getInstance().getNhsInvoiceFileLocation() + subcontractInvoiceFile;
      File fileSubcontractInvoice = new File(subcontractInvoiceFilePath);
      dynaForm.set("subcontractInvoiceFile", fileSubcontractInvoice.exists() ? subcontractInvoiceFile : "");
      String subcontractInvoiceLinkTitle = messageResources.getMessage("title.viewSubcontractInvoice") + " - " + sdf.format(new Date(fileSubcontractInvoice.lastModified()));
      dynaForm.set("subcontractInvoiceLinkTitle", subcontractInvoiceLinkTitle);
    }
    dynaForm.set("listBookingDateUserApplicantReportGroup", listBookingDateUserApplicantReportGroup);
    if (listBookingDateUserApplicantReportGroup.size() == 1)
    {
      // Only ONE Ward.
      dynaForm.set("nhsLocation", listBookingDateUserApplicantReportGroup.get(0).getSiteName());
      dynaForm.set("nhsWard", listBookingDateUserApplicantReportGroup.get(0).getLocationName());
    }
    else
    {
      // More than ONE Ward but maybe only ONE Location...
      dynaForm.set("nhsLocation", getSingleNhsLocation(listBookingDateUserApplicantReportGroup));
    }
    forward = mapping.findForward("success");
    logger.exit("Out going !!!");

    return forward;
  }

}
