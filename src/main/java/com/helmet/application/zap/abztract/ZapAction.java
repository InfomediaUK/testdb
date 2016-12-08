package com.helmet.application.zap.abztract;

import static com.helmet.application.zap.ZapConstants.FATALERRORMESSAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helmet.api.exceptions.IllegalAccessException;
import com.helmet.application.app.AppUtilities;
import com.helmet.bean.Applicant;

public abstract class ZapAction extends Action
{

  private Applicant applicantLoggedIn;

  public Applicant getApplicantLoggedIn()
  {
    return applicantLoggedIn;
  }

  public void setApplicantLoggedIn(Applicant applicantLoggedIn)
  {
    this.applicantLoggedIn = applicantLoggedIn;
  }

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    Applicant applicant = AppUtilities.getCurrentApplicant(request);

    if (applicant == null) 
    { 
      String message = "***** ILLEGAL ACCESS WARNING - Applicant record appears to have vanished from Session. *****";
      try
      {
        throw new IllegalAccessException();
      }
      catch (RuntimeException e)
      {
        e.printStackTrace();
        return fatalError(mapping, form, request, response, message);
      } 
    }

    setApplicantLoggedIn(applicant);

    // all is well, so call the actual execute method

    ActionForward af = beforeDoExecute(mapping, form, request, response);

    if (af != null) { return af; }
    try
    {
      return doExecute(mapping, form, request, response);
    }
    finally
    {
      afterDoExecute(mapping, form, request, response);
    }
  }

  protected ActionForward beforeDoExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    // override in subclasses if required
    return null;
  }

  protected void afterDoExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    // override in subclasses if required
  }

  protected ActionForward fatalError(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String message)
  {
    response.setHeader("Cache-Control", "no-cache, no-store");
    response.setHeader("Pragma", "no-cache");
    request.getSession().setAttribute(FATALERRORMESSAGE, message);
    return mapping.findForward("fatalError"); 
  }

  protected abstract ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

}
