package com.helmet.application.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Consultant;

public class ConsultantEditProcess extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Consultant consultant = (Consultant) dynaForm.get("consultant");

    FormFile signatureFile = (FormFile) dynaForm.get("signatureFile");
    String signatureContentType = signatureFile.getContentType();
    String signatureFilename = signatureFile.getFileName();
    int signatureFileSize = signatureFile.getFileSize();

    if (StringUtils.isNotEmpty(signatureFilename))
    {
      consultant.setSignatureFilename(signatureFilename);
    }

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    AdminService adminService = ServiceFactory.getInstance().getAdminService();

    try
    {
      int rowCount = adminService.updateConsultant(consultant, getAdministratorLoggedIn().getAdministratorId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("consultant", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    if (StringUtils.isNotEmpty(signatureFilename))
    {
      // now upload the file
      int indexOfLastDot = signatureFile.getFileName().lastIndexOf(".");
      String fileExtension = "";
      if (indexOfLastDot > -1)
      {
        fileExtension = signatureFile.getFileName().substring(indexOfLastDot);
      }
      String filePath = FileHandler.getInstance().getConsultantFileLocation() + consultant.getSignatureFileUrl();
      // Read the InputStream and store it in a 'byteArrayOutputStream'.
      try
      {
        byte[] fileData = signatureFile.getFileData();

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
        System.out.println("IOException - uploading " + signatureFilename);
      }
    }

    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?consultant.consultantId=" + consultant.getConsultantId(), actionForward.getRedirect());

  }

}
