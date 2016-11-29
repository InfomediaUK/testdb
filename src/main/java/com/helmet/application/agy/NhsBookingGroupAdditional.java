package com.helmet.application.agy;

import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationUser;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.SiteUser;

public class NhsBookingGroupAdditional extends AgyAction
{
  private static final Integer WEEKDAY_EARLY            = new Integer(1);
  private static final Integer SATURDAY_OR_WEEKDAY_LATE = new Integer(2);
  private static final Integer SUNDAY_OR_PUBLICHOLIDAY  = new Integer(3);
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    String nhsBookingGroupIds = (String)dynaForm.get("nhsBookingGroupIds");
    Integer clientId = null;
    Integer siteId = null;
    Integer locationId = null;
    Integer jobProfileId = null;
    Integer bookingGroupId = null;
    ActionForward actionForward = null;
    ActionMessages errors = new ActionMessages();
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    String[] ids = nhsBookingGroupIds.split(",");
    clientId = Integer.valueOf(ids[0]);
    siteId = Integer.valueOf(ids[1]);
    locationId = Integer.valueOf(ids[2]);
    jobProfileId = Integer.valueOf(ids[3]);
    bookingGroupId = Integer.valueOf(ids[4]);
    SiteUser siteUser = agyService.getSiteUser(siteId);
    ClientUser clientUser = agyService.getClientUser(clientId);
    LocationUser locationUser = agyService.getLocationUser(locationId);
    JobProfileUser jobProfileUser = agyService.getJobProfileUser(jobProfileId);
    LocationJobProfile locationJobProfile = agyService.getLocationJobProfileForLocationAndJobProfile(locationId, jobProfileId);
    if (locationJobProfile == null)
    {
      errors.add("nhsBookingGroupAdditional", new ActionMessage("error.nhsBookingGroupAdditional.locationJobProfileNotFound", locationUser.getName(), jobProfileUser.getName()));
    }
    else
    {
      List<ClientAgencyJobProfileGradeUser> listClientAgencyJobProfileGradeUser = agyService.getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(siteUser.getClientId(), getConsultantLoggedIn().getAgencyId(), jobProfileId);
      removeUnavailableClientAgencyJobProfileGradeUsersFromList(listClientAgencyJobProfileGradeUser);
      List<NhsBookingUser> listNhsBookingUsersReadyToBook = agyService.getNhsBookingUsersReadyToBookForBookingGroup(getConsultantLoggedIn().getAgencyId(), siteId, locationId, jobProfileId, bookingGroupId);
      dynaForm.set("clientUser", clientUser);
      dynaForm.set("siteUser", siteUser);
      dynaForm.set("locationUser", locationUser);
      dynaForm.set("jobProfileUser", jobProfileUser);
      dynaForm.set("hourlyRate", locationJobProfile.getRate());
      dynaForm.set("contactName", locationUser.getContactName()); 
      dynaForm.set("contactJobTitle", locationUser.getContactJobTitle()); 
      dynaForm.set("contactEmailAddress", locationUser.getContactEmailAddress()); 
      dynaForm.set("contactTelephoneNumber", locationUser.getContactTelephoneNumber()); 
      dynaForm.set("accountContactName", StringUtils.isEmpty(clientUser.getAccountContactName()) ? clientUser.getName() :  clientUser.getAccountContactName());
      dynaForm.set("accountContactAddress", StringUtils.isEmpty(clientUser.getAccountContactAddress().getAddress1()) ? clientUser.getAddress() : clientUser.getAccountContactAddress());
      dynaForm.set("accountContactEmailAddress", clientUser.getAccountContactEmailAddress());
      if (bookingGroupId.equals(WEEKDAY_EARLY) )
      {
        dynaForm.set("bookingGroupName", "Weekday Early");
      }
      if (bookingGroupId.equals(SATURDAY_OR_WEEKDAY_LATE))
      {
        dynaForm.set("bookingGroupName", "Saturday or Weekday Late");
      }
      if (bookingGroupId.equals(SUNDAY_OR_PUBLICHOLIDAY))
      {
        dynaForm.set("bookingGroupName", "Sunday or Public Holiday");
      }
      dynaForm.set("listClientAgencyJobProfileGradeUser", listClientAgencyJobProfileGradeUser);
      dynaForm.set("list", listNhsBookingUsersReadyToBook);
    }
    logger.exit("Out going !!!");
    if (errors.isEmpty())
    {
      actionForward = mapping.findForward("success");
    }
    else
    {
      saveErrors(request, errors);
      actionForward = mapping.findForward("error");
    }
    return actionForward;
  }
  
  private void removeUnavailableClientAgencyJobProfileGradeUsersFromList(List<ClientAgencyJobProfileGradeUser> list)
  {
    ListIterator listIterator = list.listIterator(list.size());
    ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser = null;
    while (listIterator.hasPrevious())
    {
      // For each ClientAgencyJobProfileGradeUser (in reverse order)...
      clientAgencyJobProfileGradeUser = (ClientAgencyJobProfileGradeUser)listIterator.previous();
      if (clientAgencyJobProfileGradeUser.getAvailable().equals(false))
      {
         // Found unavailable ClientAgencyJobProfileGradeUser. Now remove from the list.
         listIterator.remove();
      }
    }
  }
}
