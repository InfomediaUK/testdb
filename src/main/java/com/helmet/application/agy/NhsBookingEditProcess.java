package com.helmet.application.agy;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.NhsBookingUser;

public class NhsBookingEditProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    NhsBookingUser nhsBookingUser = (NhsBookingUser)dynaForm.get("nhsBookingUser");
    String applicantPaidDateStr = (String)dynaForm.get("applicantPaidDateStr");
    nhsBookingUser.setApplicantPaidDate(convertDate(applicantPaidDateStr, sdf, errors, messageResources, "label.applicantPaidDate"));
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    Integer weekToShow = (Integer)dynaForm.get("weekToShow");
    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?weekToShow=" + weekToShow, actionForward.getRedirect());
    }   
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    int rowCount = agyService.updateNhsBookingCommentValueApplicantPaidDate(nhsBookingUser, getConsultantLoggedIn().getConsultantId());
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?weekToShow=" + weekToShow, actionForward.getRedirect());
  }

}
