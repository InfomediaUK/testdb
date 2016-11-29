package com.helmet.application.agy;

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

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;

public class ApplicantBookingDatesAuthorizeProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    String bookingDateIdStrs = (String)dynaForm.get("bookingDateIdStrs");
    Applicant applicant      = (Applicant)dynaForm.get("applicant");
    Integer weekToShow       = (Integer)dynaForm.get("weekToShow");

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    // need to make sure that no booking dates are ALREADY invoiced
    if (agyService.getBookingDatesAlreadyAuthorized(getConsultantLoggedIn().getAgencyId(), bookingDateIdStrs))
    {
      // 
      errors.add("authorize", new ActionMessage("error.alreadyProcessed"));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    int rowCount = agyService.updateBookingDatesAuthorized(bookingDateIdStrs, getConsultantLoggedIn().getConsultantId());

    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicant.getApplicantId() + "&weekToShow=" + weekToShow, actionForward.getRedirect());

  }

}