package com.helmet.application.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.Consultant;

public class AgencyApplicantCopySelect extends AdminAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    String sourceAgencyIdStr = (String)dynaForm.get("sourceAgencyId");
    Integer sourceAgencyId = StringUtils.isEmpty(sourceAgencyIdStr) ? null : Integer.parseInt(sourceAgencyIdStr);
    Integer targetAgencyId = (Integer)dynaForm.get("targetAgencyId");
    String consultantIdStr = (String)dynaForm.get("consultantId");
    Integer consultantId = StringUtils.isEmpty(consultantIdStr) ? null : Integer.parseInt(consultantIdStr);
    Consultant consultant = adminService.getConsultant(consultantId);
    Agency sourceAgency = adminService.getAgency(sourceAgencyId);
    Agency targetAgency = adminService.getAgency(targetAgencyId);
    List<Applicant> list = adminService.getApplicantsToCopy(sourceAgency.getAgencyId(), targetAgency.getAgencyId());
    dynaForm.set("sourceAgency", sourceAgency);
    dynaForm.set("targetAgency", targetAgency);
    dynaForm.set("consultantId", consultantIdStr);
    dynaForm.set("list", list);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
