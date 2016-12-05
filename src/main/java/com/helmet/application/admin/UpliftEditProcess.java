package com.helmet.application.admin;

import java.util.List;

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
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Uplift;
import com.helmet.bean.UpliftMinute;


public class UpliftEditProcess extends UpliftCommonProcess
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Uplift uplift = (Uplift) dynaForm.get("uplift");

    AdminService adminService = ServiceFactory.getInstance().getAdminService();

    Uplift oldUplift = adminService.getUplift(uplift.getUpliftId());
    
    int rowCount = adminService.updateUplift(uplift, getAdministratorLoggedIn().getAdministratorId());

    if (oldUplift.getUpliftMinutePeriod().equals(0))
    {
      // Uplift did NOT have UpliftMinutes.
      if (uplift.getUpliftMinutePeriod() > 0)
      {
        // Now Uplift HAS UpliftMinutes.
        insertOrReactivateUpliftMinutes(adminService, uplift);
      }
    }
    else
    {
      // Uplift did HAVE UpliftMinutes.
      if (uplift.getUpliftMinutePeriod().equals(0))
      {
        // Now Uplift does NOT have UpliftMinutes. Delete associated UpliftMInutes.
        deleteUpliftMinutes(adminService, uplift);
      }
      else
      {
        if (!oldUplift.getUpliftMinutePeriod().equals(uplift.getUpliftMinutePeriod()))
        {
          // UpliftMinutesPeriod HAS changed. Delete associated UpliftMInutes and insert new ones.
          // CANNOT HAPPEN AT PRESENT.
        }
      }
    }

    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?client.clientId=" + uplift.getClientId(), actionForward.getRedirect());
  }

}
