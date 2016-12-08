package com.helmet.application.zap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.ZapService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.zap.abztract.ZapAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.Unavailable;

public class ApplicantUnavailable extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    ZapService zapService = ServiceFactory.getInstance().getZapService();
    Applicant applicant = zapService.getApplicant(getApplicantLoggedIn().getApplicantId());
    // Get ONLY the ACTIVE Unavailables.
    List<Unavailable> listUnavailableDates = getApplicantUnavailables(zapService, applicant, true);
    StringBuffer unavailableDates = new StringBuffer();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (Unavailable unavailable : listUnavailableDates)
    {
      if (unavailableDates.length() > 0)
      {
        unavailableDates.append(',');
      }
      unavailableDates.append(sdf.format(unavailable.getUnavailableDate()));
    }
    int noOfDates = new java.util.StringTokenizer(unavailableDates.toString(), ",").countTokens();
    dynaForm.set("noOfDates", new Integer(noOfDates));
    dynaForm.set("unavailableDates", unavailableDates.toString());
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

  private List<Unavailable> getApplicantUnavailables(ZapService zapService, Applicant applicant, boolean showOnlyActive)
  {
    // Set Calendar to Start of Today one year ago.
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new java.util.Date());
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.YEAR, -1);
    java.util.Date fromDate = calendar.getTime();
    // Set Calendar to End of Today 1 year ahead.
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    calendar.add(Calendar.YEAR, 2);
    java.util.Date toDate = calendar.getTime();
    // Get ONLY the Unavailables for the date range.
    return zapService.getUnavailablesForApplicantInDateRange(applicant.getAgencyId(), applicant.getApplicantId(), showOnlyActive, fromDate, toDate);
  }
}
