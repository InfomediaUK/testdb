package com.helmet.application.agy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.NhsBookingReportGroup;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.NhsBookingUser;

public class NhsBookingsForDateRange extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    String filter = (String)dynaForm.get("nhsBookingsFilter");
    if (StringUtils.isEmpty(filter))
    {
      // Filter NOT in Request. Check Session.
      filter = (String)request.getSession().getAttribute("nhsBookingsFilter");
      if (filter == null)
      {
        // Filter NOT in Request or Session. Set to NO FILTER.
        filter = AgyConstants.NO_FILTER;
        request.getSession().setAttribute("nhsBookingsFilter", filter);
      }
    }
    else
    {
      // Filter in Request. Put in Session.
      request.getSession().setAttribute("nhsBookingsFilter", filter);
    }
    filter = (String)request.getSession().getAttribute("nhsBookingsFilter");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Date startOfWeekDate = null;
    Date endOfWeekDate = null;
    // showing weekly so determine which week to show 0=current, -1=last week, 1=next week
    Integer weekToShow = getWeekToShow(request, dynaForm);
    startOfWeekDate = Utilities.getStartOfWeek(weekToShow);
    endOfWeekDate = Utilities.getEndOfWeek(weekToShow);

    dynaForm.set("weekToShow", weekToShow);
    dynaForm.set("startDate", startOfWeekDate);
    dynaForm.set("endDate", endOfWeekDate);
    // Fill list with NHSBookings for the week.
    int countNhsBooking = 0;
    int countNhsBookingBooked = 0;
    int countNhsBookingDeleted = 0;
    List<NhsBookingUser> listNhsBookings = agyService.getNhsBookingUsersForAgencyDateRange(getConsultantLoggedIn().getAgencyId(), startOfWeekDate, endOfWeekDate, filter);
    // Create an empty list of NhsBookingReportGroups.
    List<NhsBookingReportGroup> listNhsBookingReportGroup = new ArrayList<NhsBookingReportGroup>();
    if (listNhsBookings.size() > 0) 
    {
      // Found NHSBookings for the week. 
      NhsBookingReportGroup nhsBookingReportGroup = null;
      for (NhsBookingUser nhsBookingUser : listNhsBookings)
      {
        // For each NhsBookingUser... 
        if (nhsBookingReportGroup != null && nhsBookingReportGroup.getReportGroupKey().equals(nhsBookingUser.getReportGroupKey()))
        {
          // NHS Booking is for current Report Group, just add it to the group's list.
          nhsBookingReportGroup.addNhsBookingUserToList(nhsBookingUser);
        }
        else
        {
          // NHS Booking is NOT for current Report Group. Create a new group and add NHS Booking to the group's list. 
          nhsBookingReportGroup = new NhsBookingReportGroup(nhsBookingUser);
          nhsBookingReportGroup.addNhsBookingUserToList(nhsBookingUser);
          // Add the NhsBookingReportGroup to the list of NhsBookingReportGroups.
          listNhsBookingReportGroup.add(nhsBookingReportGroup);
        }
        if (nhsBookingUser.getActive())
        {
          ++countNhsBooking;
          if (nhsBookingUser.getBookingId() > 0)
          {
            ++countNhsBookingBooked;
          }
        }
        else
        {
          ++countNhsBookingDeleted;
        }
      }
    }    
    dynaForm.set("list", listNhsBookingReportGroup);
    dynaForm.set("countNhsBooking", countNhsBooking);
    dynaForm.set("countNhsBookingBooked", countNhsBookingBooked);
    dynaForm.set("countNhsBookingDeleted", countNhsBookingDeleted);
    dynaForm.set("nhsBookingsFilter", filter);
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
