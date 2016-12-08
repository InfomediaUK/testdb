package com.helmet.application.zap.filter;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import com.helmet.application.zap.ZapUtilities;

public class PwdFilter extends com.helmet.application.filter.LoginFilter
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  protected boolean isLoggedIn(HttpServletRequest request)
  {
    String path = request.getServletPath();
    path = path.replaceAll(".do", "");
    path = path.substring(path.lastIndexOf("/") + 1);
    logger.debug(getClass().getName() + " - checking - " + path);

    if (ZapUtilities.needToReEnterPwd(request, path))
    {
      Boolean pwdEntered = ZapUtilities.getPwdEntered(request);
      return pwdEntered != null;
    }
    else
    {
      return true;
    }
  }

  protected void postProcess(HttpServletRequest request)
  {
    ZapUtilities.removePwdEntered(request);
  }

}