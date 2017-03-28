package com.helmet.application.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Agency;


public class AgencyEditProcess extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    Agency agency = (Agency)dynaForm.get("agency");
    FormFile logoFile = (FormFile)dynaForm.get("logoFile");
    String logoContentType = logoFile.getContentType();
    String logoFilename = logoFile.getFileName();
    int logoFileSize = logoFile.getFileSize();

    if (logoFilename != null && !"".equals(logoFilename))
    {
      agency.setLogoFilename(logoFilename);
    }

    FormFile invoiceLogoFile = (FormFile)dynaForm.get("invoiceLogoFile");
    String invoiceLogoContentType = invoiceLogoFile.getContentType();
    String invoiceLogoFilename = invoiceLogoFile.getFileName();
    int invoiceLogoFileSize = invoiceLogoFile.getFileSize();

    if (invoiceLogoFilename != null && !"".equals(invoiceLogoFilename))
    {
      agency.setInvoiceLogoFilename(invoiceLogoFilename);
    }

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    AdminService adminService = ServiceFactory.getInstance().getAdminService();

    try
    {
      int rowCount = adminService.updateAgency(agency, getAdministratorLoggedIn().getAdministratorId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("agency", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    if (logoFilename != null && !"".equals(logoFilename))
    {
      // now upload the file

      int indexOfLastDot = logoFile.getFileName().lastIndexOf(".");

      String fileExtension = "";

      if (indexOfLastDot > -1)
      {
        fileExtension = logoFile.getFileName().substring(indexOfLastDot);
      }

      String filePath = FileHandler.getInstance().getLogoFileLocation() + agency.getLogoUrl();

      // Read the InputStream and store it in a 'byteArrayOutputStream'.
      try
      {
        byte[] fileData = logoFile.getFileData();

        File folder = new File(filePath).getParentFile();
        if (!folder.exists())
        {
          // Create any required directories.
          folder.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(fileData);
        fileOutputStream.close();

      }
      catch (IOException e)
      {
        // TODO
        System.out.println("IOException - uploading " + logoFilename);
      }
    }

    if (invoiceLogoFilename != null && !"".equals(invoiceLogoFilename))
    {
      // now upload the file

      int indexOfLastDot = invoiceLogoFile.getFileName().lastIndexOf(".");

      String fileExtension = "";

      if (indexOfLastDot > -1)
      {
        fileExtension = invoiceLogoFile.getFileName().substring(indexOfLastDot);
      }

      String filePath = FileHandler.getInstance().getLogoFileLocation() + agency.getInvoiceLogoUrl();

      // Read the InputStream and store it in a 'byteArrayOutputStream'.
      try
      {
        byte[] fileData = invoiceLogoFile.getFileData();

        File folder = new File(filePath).getParentFile();
        if (!folder.exists())
        {
          // Create any required directories.
          folder.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(fileData);
        fileOutputStream.close();

      }
      catch (IOException e)
      {
        // TODO
        System.out.println("IOException - uploading " + invoiceLogoFilename);
      }
    }
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?agency.agencyId=" + agency.getAgencyId(), actionForward.getRedirect());

  }

}
