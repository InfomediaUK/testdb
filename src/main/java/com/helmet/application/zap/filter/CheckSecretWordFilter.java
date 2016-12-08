package com.helmet.application.zap.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.app.AppUtilities;

public class CheckSecretWordFilter extends com.helmet.application.filter.LoginFilter 
{
	protected boolean isLoggedIn(HttpServletRequest request)
  {
    String login = AppUtilities.getCurrentApplicant(request).getUser().getLogin();
    String secretWord = AppUtilities.getCurrentApplicant(request).getUser().getSecretWord();
    // return true of NOT equal
    return !secretWord.equals(login);
  }
}
