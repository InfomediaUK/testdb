package com.helmet.application.agy;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.NhsBookingUser;

public class NhsBookingsBookAdditional extends AgyAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    List<NhsBookingUser> listNhsBookingUsersReadyToBook = (List)dynaForm.get("list");
    NhsBookingUser nhsBookingUser = null;
    ActionMessages errors = new ActionMessages();
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Enumeration paramNames = request.getParameterNames();
    StringBuffer nhsBookingIds = new StringBuffer();
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.equals("nhsBookingId"))
      {
        String[] paramValues = request.getParameterValues(paramName);
        for(String param : paramValues) 
        {
          // For each NHS Booking to be booked...
          if (nhsBookingIds.length() > 0)
          {
            nhsBookingIds.append(',');
          }
          nhsBookingIds.append(param);
        }
      }
    }
    listNhsBookingUsersReadyToBook = agyService.getPickedNhsBookingUsersReadyToBook(getConsultantLoggedIn().getAgencyId(), nhsBookingIds.toString());
    dynaForm.set("list", listNhsBookingUsersReadyToBook);
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }
 
  private void book(int nhsBookingId)
  {
  }
}
