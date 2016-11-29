package com.helmet.application.agy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.NhsBackingReportUser;

public class NhsPaymentsBackingReports extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    String nhsBackingReportIds = (String)dynaForm.get("nhsBackingReportIds");
    String nhsPaymentsFilename = (String)dynaForm.get("nhsPaymentsFilename");
    // Fill list with NHSBackingReports.
    List<NhsBackingReportUser> list = agyService.getNhsBackingReportUsersInList(getConsultantLoggedIn().getAgencyId(), nhsBackingReportIds);
    // A list of NhsBackingReportUsers.
    dynaForm.set("nhsPaymentsFilename", nhsPaymentsFilename);
    dynaForm.set("list", list);
    dynaForm.set("recordCount", list.size());
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
