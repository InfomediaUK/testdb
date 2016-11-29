package com.helmet.application.agy;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Utilities;
import com.helmet.bean.Agency;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.ClientUser;


public class SendConfirmationEmail extends SendEmail
{

  protected ActionForward setFormProperties(ActionMapping mapping, DynaValidatorForm dynaForm, HttpServletRequest request)
  {

    BookingGradeApplicantUserEntity bookingGradeApplicant = (BookingGradeApplicantUserEntity) dynaForm.get("bookingGradeApplicant");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    bookingGradeApplicant = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicant.getBookingGradeApplicantId());

    if (bookingGradeApplicant == null) { return mapping.findForward("illegalaccess"); }

    BookingGradeAgyEntity bookingGrade = agyService.getBookingGradeAgyEntity(bookingGradeApplicant.getBookingGradeId(), bookingGradeApplicant.getAgencyId());

    Agency agency = AgyUtilities.getCurrentAgency(request);

    ClientUser client = agyService.getClientUser(bookingGradeApplicant.getClientId());

    MessageResources messageResources = getResources(request);

    ApplicantBookingConfirmationMessage applicantBookingConfirmationMessage = 
      new ApplicantBookingConfirmationMessage(bookingGradeApplicant, bookingGrade, agency, client, getConsultantLoggedIn(), messageResources, request.getServerName());
    
//    SimpleDateFormat ldf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
//    SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
//    DecimalFormat df = new DecimalFormat("#,##0.00");
//    DecimalFormat dfBookingId = new DecimalFormat("#000");
//
//    String toNiceEmailAddress = Utilities.makeNiceEmailAddress(bookingGradeApplicant.getApplicantFullName(), bookingGradeApplicant.getApplicantEmailAddress());
    String toNiceEmailAddress = applicantBookingConfirmationMessage.getToNiceEmailAddress();

    dynaForm.set("toEmailAddress", toNiceEmailAddress);

//    String subject = messageResources.getMessage("emailSubject.applicantBookingConfirmation", agency.getName());
    String subject = applicantBookingConfirmationMessage.getSubject();

    dynaForm.set("subject", subject);

//    StringBuffer messageBuffer = new StringBuffer();
//
//    messageBuffer.append("Hi " + bookingGradeApplicant.getApplicantFirstName());
//    messageBuffer.append("\n");
//    messageBuffer.append("\n");
//    messageBuffer.append("This is your booking confirmation from " + agency.getName() + ". ");
//    messageBuffer.append("\n");
//    messageBuffer.append("\n");
//
//    String clientName = bookingGradeApplicant.getClientName();
//    String siteName = bookingGradeApplicant.getSiteName();
//    String locationName = bookingGradeApplicant.getLocationName();
//    String jobProfileName = bookingGradeApplicant.getJobProfileName();
//    String gradeName = bookingGradeApplicant.getGradeName();
//
//    messageBuffer.append(messageResources.getMessage("label.bookingNo") + " : " + dfBookingId.format(bookingGradeApplicant.getBookingId()));
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.client") + " : " + clientName);
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.site") + " : " + siteName);
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.location") + " : " + locationName);
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.jobProfile") + " : " + jobProfileName);
//
//    if (bookingGrade.getJobFamilyCode() != null && !"".equals(bookingGrade.getJobFamilyCode()) && bookingGrade.getJobSubFamilyCode() != null && !"".equals(bookingGrade.getJobSubFamilyCode())
//        && bookingGrade.getJobProfileCode() != null && !"".equals(bookingGrade.getJobProfileCode()))
//    {
//      messageBuffer.append(" (" + bookingGrade.getJobFamilyCode() + "." + bookingGrade.getJobSubFamilyCode() + "." + bookingGrade.getJobProfileCode() + ")");
//    }
//
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.grade") + " : " + gradeName);
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.startDate") + " : " + ldf.format(bookingGradeApplicant.getMinBookingDate()));
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.startTime") + " : " + tdf.format(bookingGradeApplicant.getMinBookingDateShiftStartTime()));
//    messageBuffer.append("\n");
//
//    if (bookingGrade.getDuration() != null && !"".equals(bookingGrade.getDuration()))
//    {
//      messageBuffer.append(messageResources.getMessage("label.duration") + " : " + bookingGrade.getDuration());
//      messageBuffer.append("\n");
//    }
//    else
//    {
//      messageBuffer.append(messageResources.getMessage("label.endDate") + " : " + ldf.format(bookingGradeApplicant.getMaxBookingDate()));
//      messageBuffer.append("\n");
//      messageBuffer.append(messageResources.getMessage("label.days") + " : " + bookingGrade.getNoOfBookingDates());
//      messageBuffer.append("\n");
//      messageBuffer.append(messageResources.getMessage("label.noOfHours") + " : " + df.format(bookingGradeApplicant.getNoOfHours()));
//      messageBuffer.append("\n");
//    }
//
//    String shiftName = bookingGradeApplicant.getShiftName();
//    messageBuffer.append(messageResources.getMessage("label.shift") + " : " + (shiftName == null ? messageResources.getMessage("label.varied") : shiftName));
//    messageBuffer.append("\n");
//
//    String dressCodeName = bookingGrade.getDressCodeName();
//    if (dressCodeName != null && !"".equals(dressCodeName))
//    {
//      messageBuffer.append(messageResources.getMessage("label.dressCode") + " : " + dressCodeName);
//      messageBuffer.append("\n");
//    }
//
//    messageBuffer.append(messageResources.getMessage("label.specifics") + " : ");
//    String specifics = "";
//    if (bookingGrade.getCvRequired())
//    {
//      if (!"".equals(specifics))
//      {
//        specifics += ", ";
//      }
//      specifics += messageResources.getMessage("label.cvRequired");
//    }
//    if (bookingGrade.getInterviewRequired())
//    {
//      if (!"".equals(specifics))
//      {
//        specifics += ", ";
//      }
//      specifics += messageResources.getMessage("label.interviewRequired");
//    }
//    if (bookingGrade.getCanProvideAccommodation())
//    {
//      if (!"".equals(specifics))
//      {
//        specifics += ", ";
//      }
//      specifics += messageResources.getMessage("label.canProvideAccommodation");
//    }
//    if (bookingGrade.getCarRequired())
//    {
//      if (!"".equals(specifics))
//      {
//        specifics += ", ";
//      }
//      specifics += messageResources.getMessage("label.carRequired");
//    }
//    if ("".equals(specifics))
//    {
//      specifics = messageResources.getMessage("label.none");
//    }
//    messageBuffer.append(specifics);
//    messageBuffer.append("\n");
//
//    messageBuffer.append(messageResources.getMessage("label.expenses") + " : ");
//    String expenses = "";
//    if (bookingGradeApplicant.getBookingExpensesCount().getValue() == 0)
//    {
//      expenses = messageResources.getMessage("label.none");
//    }
//    else
//    {
//      for (BookingExpense bookingExpense : bookingGrade.getBookingExpenses())
//      {
//        if (!"".equals(expenses))
//        {
//          expenses += ", ";
//        }
//        expenses += bookingExpense.getExpenseName();
//      }
//    }
//    messageBuffer.append(expenses);
//    messageBuffer.append("\n");
//
//    String expensesText = bookingGrade.getExpensesText();
//    if (expensesText != null && !"".equals(expensesText))
//    {
//      messageBuffer.append(expensesText);
//      messageBuffer.append("\n");
//    }
//
//    messageBuffer.append(messageResources.getMessage("label.wageRate") + " : " + messageResources.getMessage("label.currencySymbolActual") + df.format(bookingGradeApplicant.getWageRate()));
//    messageBuffer.append("\n");
//    messageBuffer.append("\n");
//    messageBuffer.append("Match My Job (MMJ) provide us with an online timesheet service. Please use the following link and login details to enter your times");
//    if (bookingGradeApplicant.getBookingExpensesCount().getValue() > 0)
//    {
//      messageBuffer.append(" and expenses");
//    }
//    messageBuffer.append(".");
//    messageBuffer.append("\n");
//
//    messageBuffer.append("\n");
//
//    messageBuffer.append("<LINK>http://" + request.getServerName() + "/timesheet</LINK>");
//    messageBuffer.append("\n");
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.login") + " : " + bookingGradeApplicant.getLogin());
//    messageBuffer.append("\n");
//    messageBuffer.append(messageResources.getMessage("label.pwd") + " : " + bookingGradeApplicant.getPwd());
//    messageBuffer.append("\n");
//
//    messageBuffer.append("\n");
//    messageBuffer.append("On initial entry to MMJ you will be asked to enter a secret word. ");
//    messageBuffer.append("Your secret word must be between 8-20 characters and contain at least one number (0-9) and at least one letter (a-z, A-Z). ");
//    messageBuffer.append("On subsequent entry to MMJ you will be asked to enter 3 random letters from your secret word so try to make it memorable. ");
//    messageBuffer.append("If you forget your secret word please contact us and we can reset it for you. ");
//    messageBuffer.append("\n");
//    messageBuffer.append("\n");
//    messageBuffer.append("Any problems or feedback please don't hesitate to contact us.");
//    messageBuffer.append("\n");
//    messageBuffer.append("\n");
//
//    String clientApplicantConfirmationEmailFreeText = client.getFreeText();
//
//    if (clientApplicantConfirmationEmailFreeText != null && !"".equals(clientApplicantConfirmationEmailFreeText))
//    {
//
//      messageBuffer.append(clientApplicantConfirmationEmailFreeText);
//      messageBuffer.append("\n");
//      messageBuffer.append("\n");
//
//    }
//
//    String applicantConfirmationEmailFreeText = agency.getApplicantConfirmationEmailFreeText();
//
//    if (applicantConfirmationEmailFreeText != null && !"".equals(applicantConfirmationEmailFreeText))
//    {
//
//      messageBuffer.append(applicantConfirmationEmailFreeText);
//      messageBuffer.append("\n");
//      messageBuffer.append("\n");
//
//    }
//
//    messageBuffer.append("Kind regards");
//
//    messageBuffer.append("\n");
//    messageBuffer.append("\n");
//    messageBuffer.append(getConsultantLoggedIn().getUser().getFullName());
//    if (getConsultantLoggedIn().getJobTitle() != null && !"".equals(getConsultantLoggedIn().getJobTitle()))
//    {
//      messageBuffer.append("\n");
//      messageBuffer.append(getConsultantLoggedIn().getJobTitle());
//    }
//    messageBuffer.append("\n");
//    messageBuffer.append(agency.getName());
//    messageBuffer.append("\n");
//    messageBuffer.append(agency.getAddress().getAddress1());
//    messageBuffer.append("\n");
//    if (agency.getAddress().getAddress2() != null && !"".equals(agency.getAddress().getAddress2()))
//    {
//      messageBuffer.append(agency.getAddress().getAddress2());
//      messageBuffer.append("\n");
//    }
//    if (agency.getAddress().getAddress3() != null && !"".equals(agency.getAddress().getAddress3()))
//    {
//      messageBuffer.append(agency.getAddress().getAddress3());
//      messageBuffer.append("\n");
//    }
//    if (agency.getAddress().getAddress4() != null && !"".equals(agency.getAddress().getAddress4()))
//    {
//      messageBuffer.append(agency.getAddress().getAddress4());
//      messageBuffer.append("\n");
//    }
//    messageBuffer.append(agency.getAddress().getPostalCode());
//    messageBuffer.append("\n");
//    messageBuffer.append("\n");
//    messageBuffer.append("Tel: " + agency.getTelephoneNumber());
//    messageBuffer.append("\n");
//    messageBuffer.append("Fax: " + agency.getFaxNumber());
//    messageBuffer.append("\n");
//    messageBuffer.append("Email: " + getConsultantLoggedIn().getUser().getEmailAddress());
//    messageBuffer.append("\n");
//    messageBuffer.append("Website: " + agency.getWebsiteAddress());
//
//    dynaForm.set("message", messageBuffer.toString());
    dynaForm.set("message", applicantBookingConfirmationMessage.getMessage());

    return null;

  }

}
