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
import com.helmet.application.FileHandler;
import com.helmet.application.PdfHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportDocumentationSend extends NhsBackingReportDocumentationCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    MessageResources messageResources = getResources(request);
    SimpleDateFormat sdf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
    NhsBackingReportUser nhsBackingReportUser = (NhsBackingReportUser)dynaForm.get("nhsBackingReportUser");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    nhsBackingReportUser = agyService.getNhsBackingReportUser(nhsBackingReportUser.getNhsBackingReportId());
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    AgencyUser subcontractAgency = agyService.getAgencyUser(nhsBackingReportUser.getSubcontractAgencyId()); 
    String documentName = PdfHandler.getInstance().getNhsInvoiceNumber(agency, nhsBackingReportUser.getName());
    if (agency.getPayrollContactEmailAddress() != null)
    {
      String toEmailAddress = agency.getPayrollContactEmailAddress(); //Utilities.makeNiceEmailAddress(agency.getPayrollContactName(), agency.getPayrollContactEmailAddress());
      dynaForm.set("toEmailAddress", toEmailAddress);
    }
    if (subcontractAgency.getPayrollContactEmailAddress() != null)
    {
      String fromEmailAddress = subcontractAgency.getPayrollContactEmailAddress(); //Utilities.makeNiceEmailAddress(agency.getPayrollContactName(), agency.getPayrollContactEmailAddress());
      dynaForm.set("fromEmailAddress", fromEmailAddress);
    }
    String subject = "Backing Report " + nhsBackingReportUser.getName() + " Invoice and Documentation";
    StringBuffer messageBuffer = new StringBuffer();

    messageBuffer.append("Hi " + agency.getPayrollContactName());
    messageBuffer.append("\n\n");
    messageBuffer.append("Please find attached Documents.");
    messageBuffer.append("\n\n");
    messageBuffer.append("Backing Report " + nhsBackingReportUser.getName() + " for " + nhsBackingReportUser.getClientName() + " " + sdf.format(nhsBackingReportUser.getStartDate()) + " to " + sdf.format(nhsBackingReportUser.getEndDate()) + ".");
    messageBuffer.append("\n\n");
    messageBuffer.append("The documents are supplied in Portable Document Format (.pdf).");
    messageBuffer.append("\n\n");
    messageBuffer.append("Just open the attached files to retrieve your document and print it if a hardcopy is required.");
    messageBuffer.append("\n\n");
    messageBuffer.append("Kind Regards");
    messageBuffer.append("\n\n");

    setInvoiceFormFields(agency, dynaForm, nhsBackingReportUser, messageResources, sdf);
    setDocumentationFormFields(agency, dynaForm, nhsBackingReportUser, messageResources, sdf);
    setRejectedDocumentationFormFields(agency, dynaForm, nhsBackingReportUser, messageResources, sdf);
    dynaForm.set("nhsBackingReportUser", nhsBackingReportUser);
    dynaForm.set("subject", subject);
    dynaForm.set("message", messageBuffer.toString());
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
