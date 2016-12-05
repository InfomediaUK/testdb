package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Uplift;


public class UpliftDeleteProcess extends UpliftCommonProcess
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Uplift uplift = (Uplift) dynaForm.get("uplift");

    AdminService adminService = ServiceFactory.getInstance().getAdminService();

    int rowCount = adminService.deleteUplift(uplift.getUpliftId(), uplift.getNoOfChanges(), getAdministratorLoggedIn().getAdministratorId());
    if (uplift.getUpliftMinutePeriod() > 0)
    {
      // Uplift HAS UpliftMinutePeriod specified. Delete UpliftMinutes.
      deleteUpliftMinutes(adminService, uplift);
    }

    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?client.clientId=" + uplift.getClientId(), actionForward.getRedirect());
  }

}
