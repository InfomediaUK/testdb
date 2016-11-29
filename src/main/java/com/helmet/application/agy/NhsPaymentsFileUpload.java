package com.helmet.application.agy;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;

public class NhsPaymentsFileUpload extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    ActionMessages errors = new ActionMessages();
    StringBuffer nhsPaymentsExampleText = new StringBuffer();
    String nhsPaymentsExampleFileName = FileHandler.getInstance().getNhsPaymentsFileLocation() + FileHandler.getInstance().getNhsPaymentsFileFolder() + "/nhspaymentsexample.txt";
    File nhsPaymentsExampleFile = new File(nhsPaymentsExampleFileName);
    if (nhsPaymentsExampleFile.exists())
    {
      Utilities.suckInFile(nhsPaymentsExampleFileName, nhsPaymentsExampleText);
      dynaForm.set("nhsPaymentsExampleText", nhsPaymentsExampleText.toString());
    }
    else
    {
      errors.add("nhsPaymentsFileUpload", new ActionMessage("error.paymentsFile.exampleNotFound", nhsPaymentsExampleFileName));
      saveErrors(request, errors);
    }
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
