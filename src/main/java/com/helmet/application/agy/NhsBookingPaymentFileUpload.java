package com.helmet.application.agy;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class NhsBookingPaymentFileUpload extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    ActionMessages errors = new ActionMessages();
    Calendar calendar = Calendar.getInstance();
    Date dateToday = new Date(calendar.getTimeInMillis());
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<ClientUser> list = agyService.getNhsClientUsersForAgency(getConsultantLoggedIn().getAgencyId());
    StringBuffer nhsBookingsPaymentExampleText = new StringBuffer();
    String nhsBookingPaymentExampleFileName = FileHandler.getInstance().getNhsBookingFileLocation() + FileHandler.getInstance().getNhsBookingFileFolder() + "/nhsbookingpaymentexample.txt";
    File nhsBookingExampleFile = new File(nhsBookingPaymentExampleFileName);
    if (nhsBookingExampleFile.exists())
    {
      Utilities.suckInFile(nhsBookingPaymentExampleFileName, nhsBookingsPaymentExampleText);
      dynaForm.set("nhsBookingPaymentExampleText", nhsBookingsPaymentExampleText.toString());
    }
    else
    {
      errors.add("nhsBookingsPaymentFileUpload", new ActionMessage("error.nhsBookingPaymentFile.exampleNotFound", nhsBookingPaymentExampleFileName));
      saveErrors(request, errors);
    }
    dynaForm.set("list", list);
    dynaForm.set("paymentDateStr", sdf.format(dateToday));
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
