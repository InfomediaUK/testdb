package com.helmet.application.app.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.helmet.application.app.AppConstants;
import com.helmet.application.app.AppUtilities;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.Client;
import org.apache.commons.lang3.StringUtils;

public class LoginFilter extends com.helmet.application.filter.LoginFilter 
{
  private Pattern loginQueryStringPattern;
  private Integer bookingGradeApplicantIdGroup;

  public void init(FilterConfig filterConfig) throws ServletException 
  {
    super.init(filterConfig);
    String pattern = filterConfig.getInitParameter(AppConstants.LOGINQUERYSTRINGPATTERN);   
    loginQueryStringPattern = Pattern.compile(pattern);
    bookingGradeApplicantIdGroup = Integer.parseInt(filterConfig.getInitParameter(AppConstants.BOOKINGGRADEAPPLICANTIDGROUP));   
  }
  
	protected boolean isLoggedIn(HttpServletRequest request) {
		Applicant applicant = AppUtilities.getCurrentApplicant(request);
    Client client = AppUtilities.getCurrentClient(request);
    Agency agency = AppUtilities.getCurrentAgency(request);
    Integer bookingGradeApplicantId = AppUtilities.getCurrentBookingGradeApplicantId(request);
    if (request.getQueryString() != null)
    {
      Matcher matcher = loginQueryStringPattern.matcher(request.getQueryString());
      if (matcher.find())
      {
        String bookingGradeApplicantIdParameter = matcher.group(bookingGradeApplicantIdGroup);
        return applicant != null && client != null && agency != null && StringUtils.equals(bookingGradeApplicantId.toString(), bookingGradeApplicantIdParameter);
      }
    }
    return applicant != null && client != null && agency != null && bookingGradeApplicantId != null;
	}

  protected String addLoginParameters(String loginPage, HttpServletRequest hreq) 
  {
    if (hreq.getQueryString() != null)
    {
      loginPage = loginPage + "?" + hreq.getQueryString();
    }
    return loginPage;
  }
}
