package com.helmet.application.agy;

import java.sql.Timestamp;

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
import com.helmet.application.FileHandler;
import com.helmet.application.MultipartEmailer;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Agency;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Consultant;
import com.helmet.bean.NhsBookingUser;

public class NhsBookingApplicantNotify extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    String cssFileName = FileHandler.getInstance().getEmailTemplateRealPath("/agy/site.css");
    String serverName = request.getServerName();
    NhsBookingUser nhsBookingUser = (NhsBookingUser)dynaForm.get("nhsBookingUser");
    Integer weekToShow = (Integer)dynaForm.get("weekToShow");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    nhsBookingUser = agyService.getNhsBookingUser(nhsBookingUser.getNhsBookingId());
    Agency agency = AgyUtilities.getCurrentAgency(request);
    ClientUser clientUser = agyService.getClientUser(nhsBookingUser.getClientId());
    Consultant consultantLoggedIn = getConsultantLoggedIn();
    BookingGradeAgyEntity bookingGradeAgyEntity = agyService.getBookingGradeAgyEntity(nhsBookingUser.getBookingGradeId(), agency.getAgencyId());
    if (bookingGradeAgyEntity == null)
    {
      errors.add("nhsBookingApplicantNotify", new ActionMessage("error.nhsBookingApplicantNotify.bookingGradeNotFound", nhsBookingUser.getBookingId().toString(), nhsBookingUser.getBookingGradeId().toString()));
      saveErrors(request, errors);
    }
    else
    {
      BookingGradeApplicantUser bookingGradeApplicant = agyService.getBookingGradeApplicantUser(nhsBookingUser.getBookingGradeId(), nhsBookingUser.getApplicantId());
      if (bookingGradeApplicant == null)
      {
        errors.add("nhsBookingApplicantNotify", new ActionMessage("error.nhsBookingApplicantNotify.bookingGradeApplicantNotFound", nhsBookingUser.getBookingGradeId().toString(), nhsBookingUser.getApplicantId().toString()));
        saveErrors(request, errors);
      }
      else
      {
        BookingGradeApplicantUserEntity bookingGradeApplicantUserEntity = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicant.getBookingGradeApplicantId());
        ApplicantBookingConfirmationMessage applicantBookingConfirmationMessage = new ApplicantBookingConfirmationMessage(bookingGradeApplicantUserEntity, bookingGradeAgyEntity, agency, clientUser,
            consultantLoggedIn, messageResources, serverName);
        String fromNiceEmailAddress = applicantBookingConfirmationMessage.getFromNiceEmailAddress();
        String toNiceEmailAddress = applicantBookingConfirmationMessage.getToNiceEmailAddress();
        String subject = applicantBookingConfirmationMessage.getSubject();
        String message = applicantBookingConfirmationMessage.getMessage();
        MultipartEmailer multipartEmailer = new MultipartEmailer(agency, messageResources, cssFileName, serverName, fromNiceEmailAddress, toNiceEmailAddress, null, null, subject, message, null);
        int status = multipartEmailer.sendEmail();
        if (status == 0)
        {
          nhsBookingUser.setApplicantNotificationSent(new Timestamp(new java.util.Date().getTime()));
          agyService.updateNhsBookingApplicantNotificationSent(nhsBookingUser, consultantLoggedIn.getConsultantId());
        }
        else
        {
          errors.add("nhsBookingApplicantNotify", new ActionMessage("error.nhsBookingApplicantNotify.emailNotSent", status));
          saveErrors(request, errors);
        }
      }      
    }    
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?weekToShow=" + weekToShow, false); // actionForward.getRedirect()
  }

}
