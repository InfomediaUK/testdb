package com.helmet.application.agy;

import java.sql.Date;
import java.text.SimpleDateFormat;

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
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.NhsBackingReportUser;

public class NhsBackingReportSubcontractProcess extends NhsBackingReportCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    NhsBackingReportUser nhsBackingReportUser = (NhsBackingReportUser)dynaForm.get("nhsBackingReportUser");
    String subcontractPaidDateStr = (String)dynaForm.get("subcontractPaidDateStr");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);

    if (StringUtils.isEmpty(subcontractPaidDateStr))
    {
      nhsBackingReportUser.setSubcontractPaidDate(null);
    }
    else
    {
      Date subcontractPaidDate =  convertDate(subcontractPaidDateStr, sdf, errors, messageResources, "label.subcontractPaidDate");
      nhsBackingReportUser.setSubcontractPaidDate(subcontractPaidDate);
    }
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    int rowCount = agyService.updateNhsBackingReportSubcontract(nhsBackingReportUser, getConsultantLoggedIn().getConsultantId());
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?nhsBackingReportUser.nhsBackingReportId=" + nhsBackingReportUser.getNhsBackingReportId(), actionForward.getRedirect());
  }

}
