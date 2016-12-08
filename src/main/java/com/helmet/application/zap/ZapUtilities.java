package com.helmet.application.zap;

import static com.helmet.application.zap.ZapConstants.PWDENTERED;
import javax.servlet.http.HttpServletRequest;

public class ZapUtilities
{
  public static Boolean getPwdEntered(HttpServletRequest request)
  {
    return (Boolean) request.getSession().getAttribute(PWDENTERED);
  }

  public static void setPwdEntered(HttpServletRequest request)
  {
    request.getSession().setAttribute(PWDENTERED, true);
  }

  public static void removePwdEntered(HttpServletRequest request)
  {
    request.getSession().removeAttribute(PWDENTERED);
  }

  public static boolean needToReEnterPwd(HttpServletRequest request, String path)
  {
    return false;
  }
}
