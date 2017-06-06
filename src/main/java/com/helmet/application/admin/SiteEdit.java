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
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Site;


public class SiteEdit extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    Site site = (Site)dynaForm.get("site");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();

    site = adminService.getSite(site.getSiteId());

    // TODO check not null, maybe service should throw a known exception

    ClientUser client = adminService.getClientUser(site.getClientId());

    dynaForm.set("client", client);
    dynaForm.set("site", site);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
