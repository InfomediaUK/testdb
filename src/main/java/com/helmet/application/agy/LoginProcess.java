package com.helmet.application.agy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.Constants;
import com.helmet.bean.Agency;
import com.helmet.bean.AgyAccess;
import com.helmet.bean.Consultant;


public class LoginProcess extends Action
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    logger.entry("In coming !!!");
    if (isCancelled(request))
    {
      logger.debug("Cancelled...");
      return mapping.findForward("cancel");
    }

    DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    Consultant consultant = (Consultant)dynaForm.get("consultant");
    String agencyCode = (String)dynaForm.get("agencyCode");

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    Agency agency = null;

    try
    {
      agency = agyService.getAgencyForCode(agencyCode);
    }
    catch (DataNotFoundException e)
    {
      errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.agencyCode")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    // Set the consultants agencyId
    consultant.setAgencyId(agency.getAgencyId());

    try
    {
      consultant = agyService.validateLogin(consultant);
    }
    catch (DataNotFoundException e)
    {
      errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.login")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    catch (InvalidDetailException e)
    {
      if (consultant.getUser().getPwdHint() != null && !"".equals(consultant.getUser().getPwdHint()))
      {
        // show pwdHint
        errors.add("login", new ActionMessage("errors.invalidPwd", consultant.getUser().getPwdHint()));
      }
      else
      {
        // no pwdHint
        errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.pwd")));
      }
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    List<AgyAccess> agyAccesses = agyService.getActiveAgyAccessesForConsultant(consultant.getConsultantId());

    AgyUtilities.setCurrentConsultant(request, consultant);
    AgyUtilities.setCurrentConsultantAgyAccesses(request, agyAccesses);

    AgyUtilities.setCurrentAgency(request, agency);

    // Go to where we were trying to go before we got sent to login page
    HttpSession session = request.getSession();
    String requestedURL = (String)session.getAttribute(Constants.REQUESTED_URL);

    if (requestedURL == null)
    {
      // They went straight to the login page
      ActionForward actionForward = mapping.findForward("success");
      logger.exit("Went straight to the login page...");
      return actionForward;
    }
    else
    {
      session.removeAttribute(Constants.REQUESTED_URL);

      // Get the servlet path which will be /webapp/rest of path
      URL url = null;
      try
      {
        url = new URL(requestedURL);
      }
      catch (MalformedURLException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      String redirectUrlServletPath = url.getPath();
      String query = url.getQuery();

      // Remove the /webapp/
      String regexp = request.getContextPath();
      redirectUrlServletPath = redirectUrlServletPath.replaceFirst(regexp, "");

      // Append the query
      if (query != null && query.length() > 0)
      {
        redirectUrlServletPath += "?";
        redirectUrlServletPath += query;
      }

      // ActionForward forward = new ActionForward("loginRedirect",
      // redirectUrlServletPath, true, true);
      ActionForward forward = new ActionForward("loginRedirect", redirectUrlServletPath, true);
      logger.exit("Out going !!!");
      return forward;
    }

  }

}
