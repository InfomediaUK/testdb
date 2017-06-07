package com.helmet.application.agy;

import java.sql.Time;
import java.util.Calendar;
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
import org.apache.struts.actions.ActionDispatcher;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Booking;
import com.helmet.bean.Location;
import com.helmet.bean.LocationEntity;
import com.helmet.bean.LocationUser;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.Site;

public class LocationActionDispatcher extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  protected ActionDispatcher dispatcher = new ActionDispatcher(this, ActionDispatcher.MAPPING_FLAVOR);

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    try
    {
      return dispatcher.execute(mapping, form, request, response);
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }

  }

  public ActionForward newX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    logger.exit("Out going !!!");

    return mapping.findForward("success");

  }

  public ActionForward newProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Location location = (Location) dynaForm.get("location");

    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");

      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?site.siteId=" + location.getSiteId(), actionForward.getRedirect());

    }

    String nhsDayStartHour = (String) dynaForm.get("nhsDayStartHour");
    String nhsDayStartMinute = (String) dynaForm.get("nhsDayStartMinute");
    String nhsDayEndHour = (String) dynaForm.get("nhsDayEndHour");
    String nhsDayEndMinute = (String) dynaForm.get("nhsDayEndMinute");

    if ((nhsDayStartHour.equals("--") || nhsDayStartMinute.equals("--")))
    {
      // Do nothing.
    }
    else
    {
      location.setNhsDayStartTime(Time.valueOf(nhsDayStartHour + ":" + nhsDayStartMinute + ":00"));
    }
    if ((nhsDayEndHour.equals("--") || nhsDayEndMinute.equals("--")))
    {
      // Do nothing.
    }
    else
    {
      location.setNhsDayEndTime(Time.valueOf(nhsDayEndHour + ":" + nhsDayEndMinute + ":00"));
    }

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    try
    {
      int rowCount = agyService.insertLocation(location, getConsultantLoggedIn().getConsultantId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("location", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?location.locationId=" + location.getLocationId(), actionForward.getRedirect());

  }

  public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    LocationEntity location = (LocationEntity) dynaForm.get("location");

    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");

      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?site.siteId=" + location.getSiteId(), actionForward.getRedirect());
    }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    location = agyService.getLocationEntity(location.getLocationId());

    dynaForm.set("location", location);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

  public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    LocationUser location = (LocationUser) dynaForm.get("location");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    location = agyService.getLocationUser(location.getLocationId());

    dynaForm.set("location", location);
    Calendar cal = Calendar.getInstance();
    if (location.getNhsDayStartTime() == null)
    {
      dynaForm.set("nhsDayStartHour", "--");
      dynaForm.set("nhsDayStartMinute", "--");
    }
    else
    {
      cal.setTimeInMillis(location.getNhsDayStartTime().getTime());
      dynaForm.set("nhsDayStartHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
      dynaForm.set("nhsDayStartMinute", String.valueOf(cal.get(Calendar.MINUTE)));
    }
    if (location.getNhsDayEndTime() == null)
    {
      dynaForm.set("nhsDayEndHour", "--");
      dynaForm.set("nhsDayEndMinute", "--");
    }
    else
    {
      cal.setTimeInMillis(location.getNhsDayEndTime().getTime());
      dynaForm.set("nhsDayEndHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
      dynaForm.set("nhsDayEndMinute", String.valueOf(cal.get(Calendar.MINUTE)));
    }
    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

  public ActionForward editProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Location location = (Location) dynaForm.get("location");

    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?location.locationId=" + location.getLocationId(), actionForward.getRedirect());
    }

    String nhsDayStartHour = (String) dynaForm.get("nhsDayStartHour");
    String nhsDayStartMinute = (String) dynaForm.get("nhsDayStartMinute");
    String nhsDayEndHour = (String) dynaForm.get("nhsDayEndHour");
    String nhsDayEndMinute = (String) dynaForm.get("nhsDayEndMinute");

    if ((nhsDayStartHour.equals("--") || nhsDayStartMinute.equals("--")))
    {
      // Do nothing.
    }
    else
    {
      location.setNhsDayStartTime(Time.valueOf(nhsDayStartHour + ":" + nhsDayStartMinute + ":00"));
    }
    if ((nhsDayEndHour.equals("--") || nhsDayEndMinute.equals("--")))
    {
      // Do nothing.
    }
    else
    {
      location.setNhsDayEndTime(Time.valueOf(nhsDayEndHour + ":" + nhsDayEndMinute + ":00"));
    }
    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    try
    {
      int rowCount = agyService.updateLocation(location, getConsultantLoggedIn().getConsultantId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("location", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?location.locationId=" + location.getLocationId(), actionForward.getRedirect());

  }

  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    LocationEntity location = (LocationEntity) dynaForm.get("location");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    List<Booking> bookings = agyService.getBookingsForLocation(location.getLocationId());
    ActionMessages errors = new ActionMessages();
    if (bookings.size() == 0)
    {
      // No active Bookings found for this Location...
      dynaForm.set("canDelete", true);
    }
    else
    {
      for (Booking booking : bookings)
      {
        if (booking.getStatus() != booking.BOOKING_STATUS_CANCELLED)
        {
          // A non-cancelled booking exists...
          MessageResources messageResources = getResources(request);
          errors.add("location", new ActionMessage("error.location.cannotBeDeleted.hasBookings", bookings.size()));
          break;
        }
      }
    }

    List<NhsBooking> nhsBookings = agyService.getNhsBookingsForLocation(getConsultantLoggedIn().getAgencyId(), location.getLocationId());
    if (nhsBookings.size() == 0)
    {
      // No active NHS Bookings found for this Location...
      dynaForm.set("canDelete", true);
    }
    else
    {
      MessageResources messageResources = getResources(request);
      errors.add("location", new ActionMessage("error.location.cannotBeDeleted.hasNhsBookings", nhsBookings.size()));
    }
    if (!errors.isEmpty())
    {
      saveErrors(request, errors);
    }

    location = agyService.getLocationEntity(location.getLocationId());

    dynaForm.set("location", location);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

  public ActionForward deleteProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Location location = (Location) dynaForm.get("location");

    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?location.locationId=" + location.getLocationId(), actionForward.getRedirect());
    }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    int rowCount = agyService.deleteLocation(location.getLocationId(), location.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());

    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?site.siteId=" + location.getSiteId(), actionForward.getRedirect());

  }

  public ActionForward order(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Site site = (Site) dynaForm.get("site");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    List<Location> list = agyService.getLocationsForSite(site.getSiteId());

    dynaForm.set("list", list);

    logger.exit("Out going !!!");

    return mapping.findForward("success");

  }

  public ActionForward orderProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Site site = (Site) dynaForm.get("site");

    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");

      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?site.siteId=" + site.getSiteId(), actionForward.getRedirect());

    }

    String order = (String) dynaForm.get("order");
    Boolean zeroiseDisplayOrder = (Boolean) dynaForm.get("zeroiseDisplayOrder");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    int rowcount = agyService.updateLocationDisplayOrder(order, zeroiseDisplayOrder, getConsultantLoggedIn().getConsultantId());

    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?site.siteId=" + site.getSiteId(), actionForward.getRedirect());

  }

}
