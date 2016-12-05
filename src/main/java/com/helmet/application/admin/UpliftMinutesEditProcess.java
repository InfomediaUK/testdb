package com.helmet.application.admin;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
import com.helmet.bean.UpliftMinuteUser;


public class UpliftMinutesEditProcess extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Uplift uplift = (Uplift)dynaForm.get("uplift");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    uplift = adminService.getUplift(uplift.getUpliftId());
    List<UpliftMinuteUser> listUpliftMinuteUser = adminService.getUpliftMinuteUsersForUplift(uplift.getUpliftId());
    // Build a HashMap of all the existing UpliftMinuteUser records for the Uplift.
    Map<Integer, UpliftMinuteUser> mapUpliftMinuteUser = new HashMap<Integer, UpliftMinuteUser>();
    for (UpliftMinuteUser upliftMinuteUser : listUpliftMinuteUser)
    {
      mapUpliftMinuteUser.put(upliftMinuteUser.getUpliftMinuteId(), upliftMinuteUser);
    }
    UpliftMinuteUser upliftMinuteUser = null;
    Enumeration paramNames = request.getParameterNames();
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.startsWith("upliftFactor-"))
      {
        Integer upliftMinuteId = Integer.valueOf(paramName.substring("upliftFactor-".length()));
        upliftMinuteUser = mapUpliftMinuteUser.get(upliftMinuteId);
        String[] paramValues = request.getParameterValues(paramName);
        BigDecimal upliftFactor = new BigDecimal(paramValues[0]);
        if (upliftMinuteUser.getUpliftFactor().compareTo(upliftFactor) != 0)
        {
          // UpliftFactor HAS been changed.
          upliftMinuteUser.setUpliftFactor(upliftFactor);
          if (upliftMinuteUser.getUpliftMinutePeriod() > 0)
          {
            // UpliftMinutePeriod (from Uplift) is used as an indicator of the record having been modified.
            upliftMinuteUser.setUpliftMinutePeriod(upliftMinuteUser.getUpliftMinutePeriod() * -1);
          }
        }
      }
      if (paramName.startsWith("upliftValue-"))
      {
        Integer upliftMinuteId = Integer.valueOf(paramName.substring("upliftValue-".length()));
        upliftMinuteUser = mapUpliftMinuteUser.get(upliftMinuteId);
        String[] paramValues = request.getParameterValues(paramName);
        BigDecimal upliftValue = new BigDecimal(paramValues[0]);
        if (upliftMinuteUser.getUpliftValue().compareTo(upliftValue) != 0)
        {
          // UpliftValue HAS been changed.
          upliftMinuteUser.setUpliftValue(upliftValue);
          if (upliftMinuteUser.getUpliftMinutePeriod() > 0)
          {
            // UpliftMinutePeriod (from Uplift) is used as an indicator of the record having been modified.
            upliftMinuteUser.setUpliftMinutePeriod(upliftMinuteUser.getUpliftMinutePeriod() * -1);
          }
        }
      }
      if (paramName.startsWith("noOfChanges-"))
      {
        // Preserve the NoOfChanges value for each record.
        Integer upliftMinuteId = Integer.valueOf(paramName.substring("noOfChanges-".length()));
        upliftMinuteUser = mapUpliftMinuteUser.get(upliftMinuteId);
        String[] paramValues = request.getParameterValues(paramName);
        Integer noOfChanges = Integer.valueOf(paramValues[0]);
        upliftMinuteUser.setNoOfChanges(noOfChanges);
      }
    }
    int rowCount = 0;
    for (UpliftMinuteUser upliftMinute : mapUpliftMinuteUser.values())
    {
      // UpliftMinutePeriod (from Uplift) is used as an indicator of the record having been modified.
      if (upliftMinute.getUpliftMinutePeriod() < 0)
      {
        rowCount += adminService.updateUpliftMinute(upliftMinute, getAdministratorLoggedIn().getAdministratorId());
      }
    }
    logger.debug("UpliftMinute Row Count = " + rowCount);
    BigDecimal upliftFactor = new BigDecimal(0);
    BigDecimal upliftValue = new BigDecimal(0);
    for (UpliftMinuteUser upliftMinute : listUpliftMinuteUser)
    {
      upliftFactor = upliftFactor.add(upliftMinute.getUpliftFactor());
      upliftValue  = upliftValue.add(upliftMinute.getUpliftValue());
    }
    upliftFactor = upliftFactor.divide(new BigDecimal(60), 5, RoundingMode.HALF_UP);
    if (upliftFactor.compareTo(uplift.getUpliftFactor()) == 0 && upliftValue.compareTo(uplift.getUpliftValue()) == 0)
    {
      // The Uplift UpliftFactor is the average of the UpliftMinutes and UpliftValue is the total of the UpliftMinutes. All is well...
    }
    else
    {
      uplift.setUpliftFactor(upliftFactor);
      uplift.setUpliftValue(upliftValue);
      adminService.updateUplift(uplift, getAdministratorLoggedIn().getAdministratorId());
    }
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?uplift.upliftId=" + uplift.getUpliftId(), actionForward.getRedirect());
  }

}
