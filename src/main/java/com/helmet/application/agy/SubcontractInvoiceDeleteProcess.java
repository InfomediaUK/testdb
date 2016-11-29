package com.helmet.application.agy;

import java.io.File;
import java.io.FileOutputStream;
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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceUser;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class SubcontractInvoiceDeleteProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    SubcontractInvoiceUser subcontractInvoiceUser = (SubcontractInvoiceUser)dynaForm.get("subcontractInvoiceUser");
    AgencyUser toAgency = null;
    AgencyUser fromAgency = null;
    ActionForward actionForward = null;
    ActionMessages errors = new ActionMessages();
    String notes = subcontractInvoiceUser.getNotes();
    MessageResources messageResources = getResources(request);
    if (isCancelled(request))
    {
      actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
    }   
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    if (StringUtils.isEmpty(notes))
    {
      // Backing Report vanished from Session!
      errors.add("subcontractInvoiceDelete", new ActionMessage("error.subcontractInvoiceDelete.mustHaveNotes"));
    }
    else
    {
      subcontractInvoiceUser = agyService.getSubcontractInvoiceUser(subcontractInvoiceUser.getSubcontractInvoiceId());
      if (subcontractInvoiceUser != null)
      {
        toAgency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
        fromAgency = agyService.getAgencyUser(subcontractInvoiceUser.getFromAgencyId());
        // Fill list with SubcontractInvoiceItems for the SubcontractInvoice and delete them all.
        int rowCount = 0;
        subcontractInvoiceUser.setNotes(notes);
        List<SubcontractInvoiceItem> listSubcontractInvoiceItemUser = agyService.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
        ///////////////// DELETE INVOICE ///////////////////////////////////////////
        rowCount += agyService.deleteSubcontractInvoice(subcontractInvoiceUser, getConsultantLoggedIn().getConsultantId());
        ///////////////// DELETE INVOICE ITEMS ///////////////////////////////////////////
        for (SubcontractInvoiceItem subcontractInvoiceItem : listSubcontractInvoiceItemUser)
        {
          // Delete each Invoice Item...
          rowCount += agyService.deleteSubcontractInvoiceItem(subcontractInvoiceItem, getConsultantLoggedIn().getConsultantId());
        }
        ///////////////// UPDATE RELATED NHSBOOKINGS SubcontractInvoiceId /////////////////////
        List<NhsBookingUser> listNhsBookingUser = agyService.getNhsBookingUsersForSubcontractInvoice(getConsultantLoggedIn().getAgencyId(), subcontractInvoiceUser.getSubcontractInvoiceId());
        for (NhsBookingUser nhsBookingUser : listNhsBookingUser)
        {
          // Set the SubcontractInvoiceId to NULL...
          nhsBookingUser.setSubcontractInvoiceId(0);
          rowCount += agyService.updateNhsBookingSubcontractInvoiceId(nhsBookingUser, getConsultantLoggedIn().getConsultantId());
        }
        try
        {
          String serverName = request.getServerName();
          PdfHandler.getInstance().writeSubcontractInvoice(messageResources, toAgency, fromAgency, subcontractInvoiceUser, serverName);
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        String fileName = subcontractInvoiceUser.getSubcontractInvoiceNumber() + ".pdf";
        String waterMarkedFileName = subcontractInvoiceUser.getSubcontractInvoiceNumber() + " DELETED.pdf";
        String subcontractInvoiceFolderPath = FileHandler.getInstance().getNhsInvoiceFileLocation() + FileHandler.getInstance().getNhsInvoiceFileFolder();
        String waterMarkFilePath = subcontractInvoiceFolderPath + "/deleted_watermark.jpg";
        String subcontractInvoiceFilePath = subcontractInvoiceFolderPath + "/a" + subcontractInvoiceUser.getFromAgencyId() + "/a" + subcontractInvoiceUser.getToAgencyId() + "/" + fileName;
        String waterMarkedSubcontractInvoiceFilePath = subcontractInvoiceFolderPath + "/a" + subcontractInvoiceUser.getFromAgencyId() + "/a" + subcontractInvoiceUser.getToAgencyId() + "/"
            + waterMarkedFileName;
        //      try 
        //      {
        //          PdfReader invoiceToWaterMark = new PdfReader(subcontractInvoiceFilePath);
        //          int number_of_pages = invoiceToWaterMark.getNumberOfPages();
        //          PdfStamper stamp = new PdfStamper(invoiceToWaterMark, new FileOutputStream(waterMarkedSubcontractInvoiceFilePath));
        //          int i = 0;
        //          Image watermark_image = Image.getInstance(waterMarkFilePath);
        //          watermark_image.setAbsolutePosition(200, 400);
        //          PdfContentByte add_watermark;            
        //          while (i < number_of_pages) 
        //          {
        //            i++;
        //            add_watermark = stamp.getUnderContent(i);
        //            add_watermark.addImage(watermark_image);
        //          }
        //          invoiceToWaterMark.close();
        //          stamp.close();
        //      }
        //      catch (Exception i1) 
        //      {
        //          i1.printStackTrace();
        //      }
        File oldFile = new File(fileName);
        oldFile.delete();
      }
    }
    logger.exit("Out going !!!");
    if (errors.isEmpty())
    {
      actionForward = mapping.findForward("success");
    }
    else
    {
      saveErrors(request, errors);
      List<SubcontractInvoiceItem> listSubcontractInvoiceItems = agyService.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceUser.getSubcontractInvoiceId());
      dynaForm.set("subcontractInvoiceUser", subcontractInvoiceUser);
      dynaForm.set("list", listSubcontractInvoiceItems);
      return mapping.getInputForward();
    }
    return new ActionForward(actionForward.getName(), actionForward.getPath(), actionForward.getRedirect());
  }

}
