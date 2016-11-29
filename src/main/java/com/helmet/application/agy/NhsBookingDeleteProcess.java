package com.helmet.application.agy;

import java.text.SimpleDateFormat;

import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.MailHandler;
import com.helmet.application.Utilities;
import com.helmet.bean.Applicant;
import com.helmet.bean.EmailAction;
import com.helmet.bean.NhsBookingUser;

public class NhsBookingDeleteProcess extends SendApplicantEmailProcess
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    NhsBookingUser nhsBookingUser = (NhsBookingUser)dynaForm.get("nhsBookingUser");
    Integer weekToShow = (Integer)dynaForm.get("weekToShow");
    String applicantComment = (String)dynaForm.get("applicantComment");
    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?weekToShow=" + weekToShow, actionForward.getRedirect());
    }   
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    int rowCount = agyService.deleteNhsBooking(nhsBookingUser, getConsultantLoggedIn().getConsultantId());
    if (rowCount == 1 && StringUtils.isNotEmpty(applicantComment))
    {
      // Email Applicant with message.
      StringBuffer textTemplate   = new StringBuffer();
      StringBuffer htmlTemplate   = new StringBuffer();
      nhsBookingUser = agyService.getNhsBookingUser(nhsBookingUser.getNhsBookingId());
      Applicant applicant = agyService.getApplicant(nhsBookingUser.getApplicantId());
      EmailAction emailAction = agyService.getEmailAction(AgyConstants.EMAIL_ACTION_ID_NHS_BOOKING_CLOSED_NOTIFICATION);
      String subject = emailAction.getSubject();
      validateEmailAction(request, emailAction, textTemplate, htmlTemplate, errors);
      // Put bankReqNum into the templates.
      String bankReqNumPlaceholder = "%bankReqNum%";
      int index = textTemplate.indexOf(bankReqNumPlaceholder);
      if (index > -1)
      {
        // BankReqNum Placeholder found, replace it with new bankReqNum.
        textTemplate.replace(index, index + bankReqNumPlaceholder.length(), nhsBookingUser.getBankReqNum());
        index = htmlTemplate.indexOf(bankReqNumPlaceholder);
        htmlTemplate.replace(index, index + bankReqNumPlaceholder.length(), nhsBookingUser.getBankReqNum());
      }    

      String reasonPlaceholder = "%reason%";
      index = textTemplate.indexOf(reasonPlaceholder);
      if (index > -1)
      {
        // Reason Placeholder found, replace it with Applicant Comment.
        textTemplate.replace(index, index + reasonPlaceholder.length(), applicantComment);
        index = htmlTemplate.indexOf(reasonPlaceholder);
        htmlTemplate.replace(index, index + reasonPlaceholder.length(), applicantComment);
      }    

      String siteNamePlaceholder = "%siteName%";
      index = textTemplate.indexOf(siteNamePlaceholder);
      if (index > -1)
      {
        // Site Name Placeholder found, replace it with Site Name.
        textTemplate.replace(index, index + siteNamePlaceholder.length(), nhsBookingUser.getSiteName());
        index = htmlTemplate.indexOf(siteNamePlaceholder);
        htmlTemplate.replace(index, index + siteNamePlaceholder.length(), nhsBookingUser.getSiteName());
      }    

      String locationNamePlaceholder = "%locationName%";
      index = textTemplate.indexOf(locationNamePlaceholder);
      if (index > -1)
      {
        // Location Name Placeholder found, replace it with Location Name.
        textTemplate.replace(index, index + locationNamePlaceholder.length(), nhsBookingUser.getLocationName());
        index = htmlTemplate.indexOf(locationNamePlaceholder);
        htmlTemplate.replace(index, index + locationNamePlaceholder.length(), nhsBookingUser.getLocationName());
      }    

      String jobProfileAssignmentPlaceholder = "%jobProfileAssignment%";
      index = textTemplate.indexOf(jobProfileAssignmentPlaceholder);
      if (index > -1)
      {
        // Job Profile/Assignment Placeholder found, replace it with details.
        String jobProfileAssignment = nhsBookingUser.getJobProfileName() + " (" + nhsBookingUser.getJobFamilyCode() + "." + nhsBookingUser.getJobSubFamilyCode() + ") - " + nhsBookingUser.getAssignment();
        textTemplate.replace(index, index + jobProfileAssignmentPlaceholder.length(), jobProfileAssignment);
        index = htmlTemplate.indexOf(jobProfileAssignmentPlaceholder);
        htmlTemplate.replace(index, index + jobProfileAssignmentPlaceholder.length(), jobProfileAssignment);
      }    

      String datePlaceholder = "%date%";
      index = textTemplate.indexOf(datePlaceholder);
      if (index > -1)
      {
        // Date Placeholder found, replace it with formatted date.
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd-MMM-yyyy");
        String date = sdf.format(nhsBookingUser.getDate());
        textTemplate.replace(index, index + datePlaceholder.length(), date);
        index = htmlTemplate.indexOf(datePlaceholder);
        htmlTemplate.replace(index, index + datePlaceholder.length(), date);
      }    

      String startTimePlaceholder = "%startTime%";
      index = textTemplate.indexOf(startTimePlaceholder);
      if (index > -1)
      {
        // Start Time Placeholder found, replace it with formatted time.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time = sdf.format(nhsBookingUser.getStartTime());
        textTemplate.replace(index, index + startTimePlaceholder.length(), time);
        index = htmlTemplate.indexOf(startTimePlaceholder);
        htmlTemplate.replace(index, index + startTimePlaceholder.length(), time);
      }    

      String endTimePlaceholder = "%endTime%";
      index = textTemplate.indexOf(endTimePlaceholder);
      if (index > -1)
      {
        // End Time Placeholder found, replace it with formatted time.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time = sdf.format(nhsBookingUser.getEndTime());
        textTemplate.replace(index, index + endTimePlaceholder.length(), time);
        index = htmlTemplate.indexOf(endTimePlaceholder);
        htmlTemplate.replace(index, index + endTimePlaceholder.length(), time);
      }    

      // Now send the email notification...
      String fromEmailAddress = getConsultantLoggedIn().getUser().getEmailAddress();
      String fromFullName = getConsultantLoggedIn().getUser().getFullName();
      String fromNiceEmailAddress = Utilities.makeNiceEmailAddress(fromFullName, fromEmailAddress);
      String toEmailAddress = null;
      String toNiceEmailAddress = null;
      if (StringUtils.isEmpty(applicant.getEmailAddress()))
      {
        // Applicant does NOT have an email address. Send email to consultant.
        toNiceEmailAddress = fromNiceEmailAddress;
        String consultantNamePlaceholder = "%consultant%";
        index = textTemplate.indexOf(consultantNamePlaceholder);
        if (index > -1)
        {
          // Consultant Placeholder found, replace it with consultant's name.
          textTemplate.replace(index, index + consultantNamePlaceholder.length(), fromFullName);
          index = htmlTemplate.indexOf(consultantNamePlaceholder);
          htmlTemplate.replace(index, index + consultantNamePlaceholder.length(), fromFullName);
        }      
        subject += " - " + applicant.getUser().getFullName();
      }
      else
      {
        // Applicant does have an email address. Send email to applicant.
        toEmailAddress = applicant.getEmailAddress();
        toNiceEmailAddress = Utilities.makeNiceEmailAddress(applicant.getUser().getFullName(), toEmailAddress);
      }
      // Now do actual sending of email.
      MimeMultipart multipartRoot = buildMimeMultipart(request, applicant, textTemplate, htmlTemplate, "", messageResources);
      String contentType = "text/plain";
      int status = MailHandler.getInstance().sendSingleMail(fromNiceEmailAddress, toNiceEmailAddress, "", "", subject, multipartRoot, contentType);
      if (status != 0)
      {
        // an error has occurred ... inform the user ...
        String errorKey = "error.email." + status;
        errors.add("sendApplicantEmail", new ActionMessage(errorKey));
        saveErrors(request, errors);
        logger.debug("Out going with email error: " + errorKey);
        return mapping.getInputForward();
      }
      logger.debug("NHS Booking Closed Notification email sent to: " + toNiceEmailAddress);
    }
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    if (weekToShow == 0)
    {
      return actionForward;
    }
    else
    {
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?weekToShow=" + weekToShow, actionForward.getRedirect());
    }
  }

}
