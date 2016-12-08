package com.helmet.application.zap.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.app.AppUtilities;
import com.helmet.bean.Applicant;

public class LoginFilter extends com.helmet.application.filter.LoginFilter
{

  protected boolean isLoggedIn(HttpServletRequest request)
  {
    Applicant applicant = AppUtilities.getCurrentApplicant(request);
    return applicant != null;
  }

}
