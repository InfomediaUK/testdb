package com.helmet.application.agy;

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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceUser;

public class SubcontractInvoiceSend extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    MessageResources messageResources = getResources(request);
    SimpleDateFormat sdf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
    SubcontractInvoiceUser subcontractInvoiceUser = (SubcontractInvoiceUser)dynaForm.get("subcontractInvoiceUser");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    subcontractInvoiceUser = agyService.getSubcontractInvoiceUser(subcontractInvoiceUser.getSubcontractInvoiceId());
    AgencyUser fromAgency = agyService.getAgencyUser(subcontractInvoiceUser.getFromAgencyId());
    AgencyUser toAgency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    String documentName = subcontractInvoiceUser.getSubcontractInvoiceId() > 0 ? "Invoice" : "Credit Note";
    // Fill list with NHSBookings for the Subcontract Invoice.
    List<SubcontractInvoiceItem> listSubcontractInvoiceItems = agyService.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
    if (toAgency.getPayrollContactEmailAddress() != null)
    {
      String toEmailAddress = toAgency.getPayrollContactEmailAddress(); //Utilities.makeNiceEmailAddress(toAgency.getPayrollContactName(), toAgency.getPayrollContactEmailAddress());
      dynaForm.set("toEmailAddress", toEmailAddress);
    }
    if (fromAgency.getPayrollContactEmailAddress() != null)
    {
      String fromEmailAddress = fromAgency.getPayrollContactEmailAddress(); //Utilities.makeNiceEmailAddress(fromAgency.getPayrollContactName(), fromAgency.getPayrollContactEmailAddress());
      dynaForm.set("fromEmailAddress", fromEmailAddress);
    }
    String noOfBookings = listSubcontractInvoiceItems.size() == 1 ? "1 Booking" : listSubcontractInvoiceItems.size() + " Bookings";
    String ward = subcontractInvoiceUser.getClientNhsName() + ", " + subcontractInvoiceUser.getSiteNhsLocation() + ", " + subcontractInvoiceUser.getLocationNhsWard();
    String assignment = subcontractInvoiceUser.getJobProfileName() + " (" + subcontractInvoiceUser.getJobFamilyCode() +"." + 
                        subcontractInvoiceUser.getJobSubFamilyCode() +") " + subcontractInvoiceUser.getJobProfileNhsAssignment();
    String subject = "Subcontract " + documentName + " " + subcontractInvoiceUser.getSubcontractInvoiceNumber() + " - " + ward;
    StringBuffer messageBuffer = new StringBuffer();

    messageBuffer.append("Hi " + toAgency.getPayrollContactName());
    messageBuffer.append("\n\n");
    messageBuffer.append("Please find attached Subcontract " + documentName + " " + subcontractInvoiceUser.getSubcontractInvoiceNumber() + " dated " + sdf.format(subcontractInvoiceUser.getDate()) + ".");
    messageBuffer.append("\n\n");
    messageBuffer.append(noOfBookings + " for " + ward + " - " + assignment);
    messageBuffer.append("\n\n");
    messageBuffer.append("during the period " + sdf.format(subcontractInvoiceUser.getStartDate()) + " - " + sdf.format(subcontractInvoiceUser.getEndDate()) + ".");
    messageBuffer.append("\n\n");
    messageBuffer.append("The document is supplied in Portable Document Format (.pdf).");
    messageBuffer.append("\n\n");
    messageBuffer.append("Just open the attached file to retrieve your document and print it if a hardcopy is required.");
    messageBuffer.append("\n\n");
    messageBuffer.append("Kind Regards");
    messageBuffer.append("\n\n");
    messageBuffer.append(fromAgency.getName());
    dynaForm.set("subcontractInvoiceUser", subcontractInvoiceUser);
    dynaForm.set("list", listSubcontractInvoiceItems);
    dynaForm.set("subject", subject);
    dynaForm.set("message", messageBuffer.toString());
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
