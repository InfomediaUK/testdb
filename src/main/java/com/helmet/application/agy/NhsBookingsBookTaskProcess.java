package com.helmet.application.agy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.NhsBookingsBookTask;
import com.helmet.application.NhsBookingsBookTaskHandle;
import com.helmet.application.NhsBookingsBookTaskResult;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.application.agy.AgyConstants;
import com.helmet.bean.Agency;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.SiteUser;


public class NhsBookingsBookTaskProcess extends AgyAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ClientUser clientUser                 = (ClientUser)dynaForm.get("clientUser");
    SiteUser siteUser                     = (SiteUser)dynaForm.get("siteUser"); 
    LocationUser locationUser             = (LocationUser)dynaForm.get("locationUser"); 
    JobProfileUser jobProfileUser         = (JobProfileUser)dynaForm.get("jobProfileUser"); 
    Integer clientAgencyJobProfileGradeId = (Integer)dynaForm.get("clientAgencyJobProfileGradeId");
    BigDecimal hourlyRate = (BigDecimal)dynaForm.get("hourlyRate");
    BigDecimal wageRate = (BigDecimal)dynaForm.get("wageRate");
    BigDecimal value = (BigDecimal)dynaForm.get("value");
    MessageResources messageResources = getResources(request);
    String cssFileName = request.getSession().getServletContext().getRealPath("/agy/site.css");
    String serverName = request.getServerName();
    // Get the main records from the database.
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Agency agency = AgyUtilities.getCurrentAgency(request);
    clientUser = agyService.getClientUser(clientUser.getClientId());;
    siteUser = agyService.getSiteUser(siteUser.getSiteId());
    locationUser = agyService.getLocationUser(locationUser.getLocationId());
    jobProfileUser = agyService.getJobProfileUser(jobProfileUser.getJobProfileId());
    ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser = agyService.getClientAgencyJobProfileGradeUser(clientAgencyJobProfileGradeId);
    // Get the NhsBookingsBookTaskHandle from the Session if it exists.
    NhsBookingsBookTaskHandle nhsBookingsBookTaskHandle = (NhsBookingsBookTaskHandle)request.getSession().getAttribute(AgyConstants.NHS_BOOKINGS_BOOK_TASK_HANDLE);
    //
    if (nhsBookingsBookTaskHandle != null)
    {
      // Already running.
//      return mapping.findForward("error");
    }
    // Get the NHSBookingIds from the request and make a list.
    Enumeration paramNames = request.getParameterNames();
    List<Integer> listNhsBookingId = new ArrayList<Integer>();
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.equals("nhsBookingId"))
      {
        String[] paramValues = request.getParameterValues(paramName);
        for(String param : paramValues) 
        {
          // For each NHS Booking to be booked...
          listNhsBookingId.add(Integer.parseInt(param));
        }
      }
    }
    // Create an NHSBookingsBookTask that will do the actual booking.
    NhsBookingsBookTask nhsBookingsBookTask = new NhsBookingsBookTask(agyService, agency, clientUser, siteUser, locationUser, jobProfileUser, clientAgencyJobProfileGradeUser, getConsultantLoggedIn(), hourlyRate, wageRate, value, messageResources, cssFileName, serverName, listNhsBookingId);
    ExecutorService executorService = (ExecutorService)request.getSession().getServletContext().getAttribute(AgyConstants.NHS_EXECUTOR);
    // Submit the NHSBookingsBookTask to the ExecutorService to run it in a separate thread.
    Future<NhsBookingsBookTaskResult> future = executorService.submit(nhsBookingsBookTask);
    nhsBookingsBookTaskHandle = new NhsBookingsBookTaskHandle(clientUser.getName(), siteUser.getName(), locationUser.getName(), jobProfileUser.getName(), listNhsBookingId.size(), future);
    request.getSession().setAttribute(AgyConstants.NHS_BOOKINGS_BOOK_TASK_HANDLE, nhsBookingsBookTaskHandle);
    return mapping.findForward("success");
  }
 

}
