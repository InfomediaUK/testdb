package com.helmet.application.agy;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.PdfHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.Site;
import com.helmet.bean.SubcontractInvoice;
import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceItemUser;
import com.helmet.bean.SubcontractInvoiceUser;

public class SubcontractInvoiceCreditProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    MessageResources messageResources = getResources(request);
    BigDecimal minusOne = new BigDecimal(-1);
    SubcontractInvoiceUser subcontractInvoiceUser = (SubcontractInvoiceUser)dynaForm.get("subcontractInvoiceUser");
    SubcontractInvoiceUser subcontractCreditNoteUser = null;
    String subcontractCreditNoteNotes = subcontractInvoiceUser.getNotes();
    Date creditNoteDate =  subcontractInvoiceUser.getDate();
    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
    }   
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    subcontractInvoiceUser = agyService.getSubcontractInvoiceUser(subcontractInvoiceUser.getSubcontractInvoiceId());
    if (subcontractInvoiceUser != null)
    {
      // Build the Credit Note from the Invoice...
      ClientUser client = agyService.getClientUser(subcontractInvoiceUser.getClientId());
      Site site = agyService.getSite(subcontractInvoiceUser.getSiteId());
      Location location = agyService.getLocation(subcontractInvoiceUser.getLocationId());
      JobProfileUser jobProfile = agyService.getJobProfileUser(subcontractInvoiceUser.getJobProfileId());
      subcontractCreditNoteUser = new SubcontractInvoiceUser();
      subcontractCreditNoteUser.setToAgencyId(subcontractInvoiceUser.getToAgencyId());
      subcontractCreditNoteUser.setFromAgencyId(subcontractInvoiceUser.getFromAgencyId());
      subcontractCreditNoteUser.setClientId(subcontractInvoiceUser.getClientId());
      subcontractCreditNoteUser.setClientNhsName(client.getNhsName());
      subcontractCreditNoteUser.setSiteId(subcontractInvoiceUser.getSiteId());
      subcontractCreditNoteUser.setSiteNhsLocation(site.getNhsLocation());
      subcontractCreditNoteUser.setLocationId(subcontractInvoiceUser.getLocationId());
      subcontractCreditNoteUser.setLocationNhsWard(location.getNhsWard());
      subcontractCreditNoteUser.setJobProfileId(subcontractInvoiceUser.getJobProfileId());
      subcontractCreditNoteUser.setJobProfileName(jobProfile.getName());
      subcontractCreditNoteUser.setJobFamilyCode(jobProfile.getJobFamilyCode());
      subcontractCreditNoteUser.setJobSubFamilyCode(jobProfile.getJobSubFamilyCode());
      subcontractCreditNoteUser.setJobProfileNhsAssignment(jobProfile.getNhsAssignment());
      subcontractCreditNoteUser.setStartDate(subcontractInvoiceUser.getStartDate());
      subcontractCreditNoteUser.setEndDate(subcontractInvoiceUser.getEndDate());
      // Note. NEGATIVE value.
      subcontractCreditNoteUser.setValue(subcontractInvoiceUser.getValue().multiply(minusOne));
      subcontractCreditNoteUser.setRelatedSubcontractInvoiceId(subcontractInvoiceUser.getSubcontractInvoiceId());
      // Set values from form.
      subcontractCreditNoteUser.setDate(creditNoteDate);
      subcontractCreditNoteUser.setNotes(subcontractCreditNoteNotes);
      ///////////////// INSERT CREDIT NOTE /////////////////////////////////////////////////
      int rowCount = agyService.insertSubcontractCreditNote(subcontractCreditNoteUser, getConsultantLoggedIn().getConsultantId());
      // Set the RelatedSubcontractInvoiceId on the Invoice to the Id of the Credit Note.
      subcontractInvoiceUser.setRelatedSubcontractInvoiceId(subcontractCreditNoteUser.getSubcontractInvoiceId());
      ///////////////// UPDATE RELATED INVOICE /////////////////////////////////////////////
      rowCount += agyService.updateSubcontractInvoiceRelatedSubcontractInvoiceId(subcontractInvoiceUser, getConsultantLoggedIn().getConsultantId());
      // Fill list with SubcontractInvoiceItems for the SubcontractInvoice.
      List<SubcontractInvoiceItemUser> listSubcontractInvoiceItemUser = agyService.getSubcontractInvoiceItemUsersForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
      ///////////////// INSERT CREDIT NOTE ITEMS ///////////////////////////////////////////
      for (SubcontractInvoiceItemUser subcontractInvoiceItem : listSubcontractInvoiceItemUser)
      {
        // Convert each Invoice Item to a Credit Note Item...
        subcontractInvoiceItem.setSubcontractInvoiceId(subcontractCreditNoteUser.getSubcontractInvoiceId());
        subcontractInvoiceItem.setValue(subcontractInvoiceItem.getValue().multiply(minusOne));
        rowCount += agyService.insertSubcontractInvoiceItem(subcontractInvoiceItem, getConsultantLoggedIn().getConsultantId());
      }
      subcontractCreditNoteUser.setListSubcontractInvoiceItemUser(listSubcontractInvoiceItemUser);
      ///////////////// UPDATE RELATED NHSBOOKINGS SubcontractInvoiceId /////////////////////
      List<NhsBookingUser> listNhsBookingUser = agyService.getNhsBookingUsersForSubcontractInvoice(getConsultantLoggedIn().getAgencyId(), subcontractInvoiceUser.getSubcontractInvoiceId());
      for (NhsBookingUser nhsBookingUser : listNhsBookingUser)
      {
        nhsBookingUser.setSubcontractInvoiceId(subcontractCreditNoteUser.getSubcontractInvoiceId());
        rowCount += agyService.updateNhsBookingSubcontractInvoiceId(nhsBookingUser, getConsultantLoggedIn().getConsultantId());
      }
      try
      {
        String serverName = request.getServerName();
        AgencyUser toAgency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
        AgencyUser fromAgency = agyService.getAgencyUser(subcontractCreditNoteUser.getFromAgencyId());
        PdfHandler.getInstance().writeSubcontractInvoice(messageResources, toAgency, fromAgency, subcontractCreditNoteUser, serverName);
      }
      catch (Exception e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }    
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    if (subcontractCreditNoteUser == null)
    {
      // There was some sort of problemm...
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
    }
    else
    {
      // Looks good...
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractCreditNoteUser.getSubcontractInvoiceId(), actionForward.getRedirect());
    }
  }

}
