package com.helmet.application.zap.filter;

import static com.helmet.application.zap.ZapConstants.RESETPWDHINT;
import javax.servlet.http.HttpServletRequest;
import com.helmet.application.app.AppUtilities;

public class CheckPwdFilter extends com.helmet.application.filter.LoginFilter
{
  protected boolean isLoggedIn(HttpServletRequest request)
  {
    String pwdHint = AppUtilities.getCurrentApplicant(request).getUser().getPwdHint();
    // Return true if NOT equal.
    return !RESETPWDHINT.equals(pwdHint);
  }
}
