package com.helmet.application.app;

import static com.helmet.application.app.AppConstants.APPLICANT;
import static com.helmet.application.app.AppConstants.BOOKINGGRADEAPPLICANTID;
import static com.helmet.application.app.AppConstants.AGENCY;
import static com.helmet.application.app.AppConstants.CLIENT;
import static com.helmet.application.app.AppConstants.LEVEL2LOGIN;
import static com.helmet.application.app.AppConstants.SHOW;
import static com.helmet.application.app.AppConstants.WEEKTOSHOW;

import javax.servlet.http.HttpServletRequest;

import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.Client;

public class AppUtilities {

	public static Applicant getCurrentApplicant(HttpServletRequest request) {
		return (Applicant) request.getSession().getAttribute(APPLICANT);
	}

	public static void setCurrentApplicant(HttpServletRequest request,
			Applicant applicant) {
		request.getSession().setAttribute(APPLICANT, applicant);
	}

	public static void removeCurrentApplicant(HttpServletRequest request) {
		request.getSession().removeAttribute(APPLICANT);
	}

	public static Integer getCurrentBookingGradeApplicantId(HttpServletRequest request) {
		return (Integer)request.getSession().getAttribute(BOOKINGGRADEAPPLICANTID);
	}

	public static void setCurrentBookingGradeApplicantId(HttpServletRequest request,
			Integer bookingGradeApplicantId) {
		request.getSession().setAttribute(BOOKINGGRADEAPPLICANTID, bookingGradeApplicantId);
	}

	public static void removeCurrentBookingGradeApplicantId(HttpServletRequest request) {
		request.getSession().removeAttribute(BOOKINGGRADEAPPLICANTID);
	}

	public static Client getCurrentClient(HttpServletRequest request) {
		return (Client) request.getSession().getAttribute(CLIENT);
	}

	public static void setCurrentClient(HttpServletRequest request,
			Client applicant) {
		request.getSession().setAttribute(CLIENT, applicant);
	}

	public static void removeCurrentClient(HttpServletRequest request) {
		request.getSession().removeAttribute(CLIENT);
	}

	public static Agency getCurrentAgency(HttpServletRequest request) {
		return (Agency) request.getSession().getAttribute(AGENCY);
	}

	public static void setCurrentAgency(HttpServletRequest request,
			Agency applicant) {
		request.getSession().setAttribute(AGENCY, applicant);
	}

	public static void removeCurrentAgency(HttpServletRequest request) {
		request.getSession().removeAttribute(AGENCY);
	}

	public static Boolean getLevel2Login(HttpServletRequest request) {
		return (Boolean) request.getSession().getAttribute(LEVEL2LOGIN);
	}

	public static void setLevel2Login(HttpServletRequest request) {
		request.getSession().setAttribute(LEVEL2LOGIN, true);
	}

	public static void removeLevel2Login(HttpServletRequest request) {
		request.getSession().removeAttribute(LEVEL2LOGIN);
	}

  public static Integer getWeekToShow(HttpServletRequest request)
  {
    return (Integer)request.getSession().getAttribute(WEEKTOSHOW);
  }

  public static void setWeekToShow(HttpServletRequest request, Integer weekToShow)
  {
    request.getSession().setAttribute(WEEKTOSHOW, weekToShow);
  }

  public static void removeWeekToShow(HttpServletRequest request)
  {
    request.getSession().removeAttribute(WEEKTOSHOW);
  }

  public static String getShow(HttpServletRequest request)
  {
    return (String)request.getSession().getAttribute(SHOW);
  }

  public static void setShow(HttpServletRequest request, String show)
  {
    request.getSession().setAttribute(SHOW, show);
  }

  public static void removeShow(HttpServletRequest request)
  {
    request.getSession().removeAttribute(SHOW);
  }

	public static boolean needToEnterSecretWord(HttpServletRequest request) {
		
		String login = AppUtilities.getCurrentApplicant(request).getUser().getLogin();		
		String secretWord = AppUtilities.getCurrentApplicant(request).getUser().getSecretWord();		
        // return true of equal - this is how it starts off - the applicant will be forced to enter a secret word
		return !secretWord.equals(login);

	}
	
  public static void invalidateSession(HttpServletRequest request) 
  {
    request.getSession().invalidate();
  }
  
  public static void remove(HttpServletRequest request) 
  {
  	removeCurrentApplicant(request);
  	removeCurrentBookingGradeApplicantId(request);
  	removeCurrentClient(request);
  	removeCurrentAgency(request);
  	removeLevel2Login(request);
	}


}
