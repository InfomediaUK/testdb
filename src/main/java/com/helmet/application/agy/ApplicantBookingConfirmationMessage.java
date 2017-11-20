package com.helmet.application.agy;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.util.MessageResources;

import com.helmet.application.Utilities;
import com.helmet.bean.Agency;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.Client;
import com.helmet.bean.Consultant;

public class ApplicantBookingConfirmationMessage
{
  private BookingGradeApplicantUserEntity bookingGradeApplicant;
  private BookingGradeAgyEntity bookingGrade;
  private Agency agency;
  private Client client;
  private Consultant consultantLoggedIn;
  private MessageResources messageResources;
  private String serverName;
  
  public ApplicantBookingConfirmationMessage(BookingGradeApplicantUserEntity bookingGradeApplicant, BookingGradeAgyEntity bookingGrade, Agency agency, Client client, Consultant consultantLoggedIn, MessageResources messageResources, String serverName)
  {
    super();
    this.bookingGradeApplicant = bookingGradeApplicant;
    this.bookingGrade = bookingGrade;
    this.agency = agency;
    this.client = client;
    this.consultantLoggedIn = consultantLoggedIn;
    this.messageResources = messageResources;
    this.serverName = serverName;
  }
  
  public String getMessage()
  {
    SimpleDateFormat ldf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
    SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
    DecimalFormat df = new DecimalFormat("#,##0.00");
    DecimalFormat dfBookingId = new DecimalFormat("#000");
    StringBuffer messageBuffer = new StringBuffer();
    messageBuffer.append("Hi " + bookingGradeApplicant.getApplicantFirstName());
    messageBuffer.append("\n");
    messageBuffer.append("\n");
    messageBuffer.append("This is your booking confirmation from " + agency.getName() + ". ");
    messageBuffer.append("\n");
    messageBuffer.append("\n");

    String clientName = bookingGradeApplicant.getClientName();
    String siteName = bookingGradeApplicant.getSiteName();
    String locationName = bookingGradeApplicant.getLocationName();
    String jobProfileName = bookingGradeApplicant.getJobProfileName();
    String gradeName = bookingGradeApplicant.getGradeName();

    messageBuffer.append(messageResources.getMessage("label.bookingNo") + " : " + dfBookingId.format(bookingGradeApplicant.getBookingId()));
    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.client") + " : " + clientName);
    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.site") + " : " + siteName);
    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.location") + " : " + locationName);
    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.jobProfile") + " : " + jobProfileName);

    if (StringUtils.isNotEmpty(bookingGrade.getJobFamilyCode()) && StringUtils.isNotEmpty(bookingGrade.getJobSubFamilyCode()) && StringUtils.isNotEmpty(bookingGrade.getJobProfileCode()))
    {
      messageBuffer.append(" (" + bookingGrade.getJobFamilyCode() + "." + bookingGrade.getJobSubFamilyCode() + "." + bookingGrade.getJobProfileCode() + ")");
    }

    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.grade") + " : " + gradeName);
    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.startDate") + " : " + ldf.format(bookingGradeApplicant.getMinBookingDate()));
    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.startTime") + " : " + tdf.format(bookingGradeApplicant.getMinBookingDateShiftStartTime()));
    messageBuffer.append("\n");

    if (bookingGrade.getDuration() != null && !"".equals(bookingGrade.getDuration()))
    {
      messageBuffer.append(messageResources.getMessage("label.duration") + " : " + bookingGrade.getDuration());
      messageBuffer.append("\n");
    }
    else
    {
      messageBuffer.append(messageResources.getMessage("label.endDate") + " : " + ldf.format(bookingGradeApplicant.getMaxBookingDate()));
      messageBuffer.append("\n");
      messageBuffer.append(messageResources.getMessage("label.days") + " : " + bookingGrade.getNoOfBookingDates());
      messageBuffer.append("\n");
      messageBuffer.append(messageResources.getMessage("label.noOfHours") + " : " + df.format(bookingGradeApplicant.getNoOfHours()));
      messageBuffer.append("\n");
    }

    String shiftName = bookingGradeApplicant.getShiftName();
    messageBuffer.append(messageResources.getMessage("label.shift") + " : " + (shiftName == null ? messageResources.getMessage("label.varied") : shiftName));
    messageBuffer.append("\n");

    String dressCodeName = bookingGrade.getDressCodeName();
    if (dressCodeName != null && !"".equals(dressCodeName))
    {
      messageBuffer.append(messageResources.getMessage("label.dressCode") + " : " + dressCodeName);
      messageBuffer.append("\n");
    }

    messageBuffer.append(messageResources.getMessage("label.specifics") + " : ");
    String specifics = "";
    if (bookingGrade.getCvRequired())
    {
      if (!"".equals(specifics))
      {
        specifics += ", ";
      }
      specifics += messageResources.getMessage("label.cvRequired");
    }
    if (bookingGrade.getInterviewRequired())
    {
      if (!"".equals(specifics))
      {
        specifics += ", ";
      }
      specifics += messageResources.getMessage("label.interviewRequired");
    }
    if (bookingGrade.getCanProvideAccommodation())
    {
      if (!"".equals(specifics))
      {
        specifics += ", ";
      }
      specifics += messageResources.getMessage("label.canProvideAccommodation");
    }
    if (bookingGrade.getCarRequired())
    {
      if (!"".equals(specifics))
      {
        specifics += ", ";
      }
      specifics += messageResources.getMessage("label.carRequired");
    }
    if ("".equals(specifics))
    {
      specifics = messageResources.getMessage("label.none");
    }
    messageBuffer.append(specifics);
    messageBuffer.append("\n");

    messageBuffer.append(messageResources.getMessage("label.expenses") + " : ");
    String expenses = "";
    if (bookingGradeApplicant.getBookingExpensesCount().getValue() == 0)
    {
      expenses = messageResources.getMessage("label.none");
    }
    else
    {
      for (BookingExpense bookingExpense : bookingGrade.getBookingExpenses())
      {
        if (!"".equals(expenses))
        {
          expenses += ", ";
        }
        expenses += bookingExpense.getExpenseName();
      }
    }
    messageBuffer.append(expenses);
    messageBuffer.append("\n");

    String expensesText = bookingGrade.getExpensesText();
    if (expensesText != null && !"".equals(expensesText))
    {
      messageBuffer.append(expensesText);
      messageBuffer.append("\n");
    }

    messageBuffer.append(messageResources.getMessage("label.wageRate") + " : " + messageResources.getMessage("label.currencySymbolActual") + df.format(bookingGradeApplicant.getWageRate()));
    messageBuffer.append("\n");
    messageBuffer.append("\n");
    messageBuffer.append("Match My Job (MMJ) provide us with an online timesheet service. Please use the following link and login details to enter your times");
    if (bookingGradeApplicant.getBookingExpensesCount().getValue() > 0)
    {
      messageBuffer.append(" and expenses");
    }
    messageBuffer.append(".");
    messageBuffer.append("\n");

    messageBuffer.append("\n");

    messageBuffer.append("<LINK>http://" + serverName + "/appLogin.do</LINK>");
    messageBuffer.append("\n");
    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.login") + " : " + bookingGradeApplicant.getLogin());
    messageBuffer.append("\n");
    messageBuffer.append(messageResources.getMessage("label.pwd") + " : " + bookingGradeApplicant.getPwd());
    messageBuffer.append("\n");

    messageBuffer.append("\n");
    messageBuffer.append("On initial entry to MMJ you will be asked to enter a secret word. ");
    messageBuffer.append("Your secret word must be between 8-20 characters and contain at least one number (0-9) and at least one letter (a-z, A-Z). ");
    messageBuffer.append("On subsequent entry to MMJ you will be asked to enter 3 random letters from your secret word so try to make it memorable. ");
    messageBuffer.append("If you forget your secret word please contact us and we can reset it for you. ");
    messageBuffer.append("\n");
    messageBuffer.append("\n");
    messageBuffer.append("Any problems or feedback please don't hesitate to contact us.");
    messageBuffer.append("\n");
    messageBuffer.append("\n");

//    ClientUser client = agyService.getClientUser(bookingGradeApplicant.getClientId());

    String clientApplicantConfirmationEmailFreeText = client.getFreeText();

    if (clientApplicantConfirmationEmailFreeText != null && !"".equals(clientApplicantConfirmationEmailFreeText))
    {

      messageBuffer.append(clientApplicantConfirmationEmailFreeText);
      messageBuffer.append("\n");
      messageBuffer.append("\n");

    }

    String applicantConfirmationEmailFreeText = agency.getApplicantConfirmationEmailFreeText();

    if (applicantConfirmationEmailFreeText != null && !"".equals(applicantConfirmationEmailFreeText))
    {

      messageBuffer.append(applicantConfirmationEmailFreeText);
      messageBuffer.append("\n");
      messageBuffer.append("\n");

    }

    messageBuffer.append("Kind regards");

    messageBuffer.append("\n");
    messageBuffer.append("\n");
    messageBuffer.append(consultantLoggedIn.getUser().getFullName());
    if (consultantLoggedIn.getJobTitle() != null && !"".equals(consultantLoggedIn.getJobTitle()))
    {
      messageBuffer.append("\n");
      messageBuffer.append(consultantLoggedIn.getJobTitle());
    }
    messageBuffer.append("\n");
    messageBuffer.append(agency.getName());
    messageBuffer.append("\n");
    messageBuffer.append(agency.getAddress().getAddress1());
    messageBuffer.append("\n");
    if (agency.getAddress().getAddress2() != null && !"".equals(agency.getAddress().getAddress2()))
    {
      messageBuffer.append(agency.getAddress().getAddress2());
      messageBuffer.append("\n");
    }
    if (agency.getAddress().getAddress3() != null && !"".equals(agency.getAddress().getAddress3()))
    {
      messageBuffer.append(agency.getAddress().getAddress3());
      messageBuffer.append("\n");
    }
    if (agency.getAddress().getAddress4() != null && !"".equals(agency.getAddress().getAddress4()))
    {
      messageBuffer.append(agency.getAddress().getAddress4());
      messageBuffer.append("\n");
    }
    messageBuffer.append(agency.getAddress().getPostalCode());
    messageBuffer.append("\n");
    messageBuffer.append("\n");
    messageBuffer.append("Tel: ");
    if (StringUtils.isNotEmpty(agency.getTelephoneNumber()))
    {
      messageBuffer.append(agency.getTelephoneNumber());
    }    
    messageBuffer.append("\n");
    messageBuffer.append("Fax: ");
    if (StringUtils.isNotEmpty(agency.getFaxNumber()))
    {
      messageBuffer.append(agency.getFaxNumber());
    }    
    messageBuffer.append("\n");
    messageBuffer.append("Email: " + consultantLoggedIn.getUser().getEmailAddress());
    messageBuffer.append("\n");
    messageBuffer.append("Website: ");
    if (StringUtils.isNotEmpty(agency.getWebsiteAddress()))
    {
      messageBuffer.append(agency.getWebsiteAddress());
    }    
    
    return messageBuffer.toString();
  }
  
  public String getToNiceEmailAddress()
  {
    String toNiceEmailAddress = Utilities.makeNiceEmailAddress(bookingGradeApplicant.getApplicantFullName(), bookingGradeApplicant.getApplicantEmailAddress());
    return toNiceEmailAddress;
  }
  
  public String getFromNiceEmailAddress()
  {
    String fromEmailAddress = consultantLoggedIn.getUser().getEmailAddress();
    String fromFullName = consultantLoggedIn.getUser().getFullName();
    String fromNiceEmailAddress = Utilities.makeNiceEmailAddress(fromFullName, fromEmailAddress);
    return fromNiceEmailAddress;
  }
  
  public String getSubject()
  {
    String subject = messageResources.getMessage("emailSubject.applicantBookingConfirmation", agency.getName());
    return subject;
  }

}
