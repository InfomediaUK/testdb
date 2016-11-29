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
import com.helmet.bean.NhsBookingUser;

public class NhsBookingsReady extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<NhsBookingUser> listNhsBookingsReadyToBook = agyService.getNhsBookingUsersReadyToBook(getConsultantLoggedIn().getAgencyId());
    dynaForm.set("list", listNhsBookingsReadyToBook);
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
