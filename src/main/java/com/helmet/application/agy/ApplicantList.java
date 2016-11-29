package com.helmet.application.agy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;

public class ApplicantList extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    HttpSession session = request.getSession();
    String applicantIndexLetter = (String)session.getAttribute("applicantIndexLetter");
    applicantIndexLetter = applicantIndexLetter == null ? "A" : applicantIndexLetter;
    String indexLetter = request.getParameter("indexLetter") == null ? applicantIndexLetter : request.getParameter("indexLetter");
    session.setAttribute("applicantIndexLetter", indexLetter);
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<Applicant> list = agyService.getApplicantsForAgencyInLastNameGroup(getConsultantLoggedIn().getAgencyId(), indexLetter);
    dynaForm.set("list", list);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
