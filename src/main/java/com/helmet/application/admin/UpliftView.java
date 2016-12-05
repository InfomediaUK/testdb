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
import com.helmet.bean.ClientUser;
import com.helmet.bean.Uplift;
import com.helmet.bean.UpliftMinuteUser;


public class UpliftView extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Uplift uplift = (Uplift) dynaForm.get("uplift");

    AdminService adminService = ServiceFactory.getInstance().getAdminService();

    uplift = adminService.getUplift(uplift.getUpliftId());
    ClientUser client = adminService.getClientUser(uplift.getClientId());
    List<UpliftMinuteUser> listUpliftMinuteUser = adminService.getUpliftMinuteUsersForUplift(uplift.getUpliftId());
    dynaForm.set("client", client);
    dynaForm.set("uplift", uplift);
    if (listUpliftMinuteUser.size() > 0)
    {
      dynaForm.set("list", listUpliftMinuteUser);
    }

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
