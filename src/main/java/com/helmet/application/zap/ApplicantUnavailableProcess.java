package com.helmet.application.zap;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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

public class ApplicantUnavailableProcess extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ZapService zapService = ServiceFactory.getInstance().getZapService();
    Applicant applicant = zapService.getApplicant(getApplicantLoggedIn().getApplicantId());
    // Get the set of new Unavailable Dates from the form.
    String unavailableDates = (String)dynaForm.get("unavailableDates");
    int rowCount = updateUnavailablesForApplicant(zapService, applicant, unavailableDates);
    logger.debug("Unavailables rowCount:" + rowCount);
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
  
  // Very similar code to the following method exists in com.helmet.application.agy.ApplicantCommonProcess so if you change
  // it here you will have to change it there probably.
  private int updateUnavailablesForApplicant(ZapService zapService, Applicant applicant, String unavailableDates)
  {
    int rowCount = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // Load ALL Unavailable records (Active & Inactive) for the Applicant into a list.
    List<Unavailable> listUnavailableDates = getApplicantUnavailables(zapService, applicant, false);
    // Build a HashMap of all the existing Unavailable records keyed by the yyyy-mm-dd date.
    Map<String, Unavailable> mapUnavailableDates = new HashMap<String, Unavailable>();
    for (Unavailable unavailable : listUnavailableDates)
    {
      mapUnavailableDates.put(sdf.format(unavailable.getUnavailableDate()), unavailable);
    }
    StringTokenizer st = new StringTokenizer(unavailableDates, ",");
    Unavailable unavailable = null;
    String date = null;
    while (st.hasMoreTokens())
    {
      // For each new Unavailable date...
      date = st.nextToken();
      unavailable = mapUnavailableDates.get(date);
      if (unavailable == null)
      {
        // The date had NO Unavailable record before, so it is new. Insert an Unavailable record for the date.
        unavailable = new Unavailable();
        unavailable.setAgencyId(applicant.getAgencyId());
        unavailable.setApplicantId(applicant.getApplicantId());
        unavailable.setUnavailableDate(Date.valueOf(date));
        rowCount += zapService.insertUnavailable(unavailable, applicant.getApplicantId());
      }
      else
      {
        // An Unavailable record already exists for the date.
        if (unavailable.getActive() == false)
        {
          // The Unavailable record was NOT active. Make it active now.
          unavailable.setActive(true);
          rowCount += zapService.updateUnavailable(unavailable, applicant.getApplicantId());
        }
        // Now remove the Unavailable record from the List and Map.
        mapUnavailableDates.remove(date);
        listUnavailableDates.remove(unavailable);
      }
    }
    if (listUnavailableDates.size() > 0)
    {
      // The only ACTIVE Unavailable records in the List now are for dates that the Applicant is now available.
      for (Unavailable available : listUnavailableDates)
      {
        if (available.getActive())
        {
          available.setActive(false);
          rowCount += zapService.updateUnavailable(available, applicant.getApplicantId());
        }        
      }
    }    
    return rowCount;
  }
}
