package com.helmet.application.agy;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.ClientUser;

public class NhsBackingReportFileUpload extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    ActionMessages errors = new ActionMessages();
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<ClientUser> list = agyService.getNhsClientUsersForAgency(getConsultantLoggedIn().getAgencyId());
    StringBuffer nhsBackingReportExampleText = new StringBuffer();
    String nhsBackingReportExampleFileName = FileHandler.getInstance().getNhsBackingReportFileLocation() + FileHandler.getInstance().getNhsBackingReportFileFolder() + "/nhsbackingreportexample.txt";
    File nhsBackingReportExampleFile = new File(nhsBackingReportExampleFileName);
    if (nhsBackingReportExampleFile.exists())
    {
      Utilities.suckInFile(nhsBackingReportExampleFileName, nhsBackingReportExampleText);
      dynaForm.set("nhsBackingReportExampleText", nhsBackingReportExampleText.toString());
    }
    else
    {
      errors.add("nhsBackingReportFileUpload", new ActionMessage("error.nhsBackingReportFile.exampleNotFound", nhsBackingReportExampleFileName));
      saveErrors(request, errors);
    }
    dynaForm.set("list", list);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
