package com.helmet.application.agy.abztract;

import java.util.StringTokenizer;

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

import com.helmet.application.AlreadyLockedRuntimeException;
import com.helmet.application.BookingLockHandler;
import com.helmet.application.NotLockedRuntimeException;

public abstract class AgyBookingMultiLockAction extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  protected ActionForward beforeDoExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    String bookingIds = (String)dynaForm.get("bookingIds");
    StringTokenizer st = new StringTokenizer(bookingIds, ",");

    while (st.hasMoreTokens())
    {
      String bookingId = st.nextToken();
      try
      {
        logger.debug("About to lock - " + bookingId + " from " + getClass().getName());
        BookingLockHandler.getInstance().addLock(Integer.valueOf(bookingId));
      }
      catch (AlreadyLockedRuntimeException e)
      {

        logger.warn("***** Already locked - " + bookingId);

        ActionMessages errors = new ActionMessages();
        MessageResources messageResources = getResources(request);
        errors.add("bookingLocked", new ActionMessage("error.bookingLocked"));
        saveErrors(request, errors);
        return mapping.findForward("bookingLocked");
      }
    }    
    return null;

  }

  protected void afterDoExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    String bookingIds = (String)dynaForm.get("bookingIds");
    StringTokenizer st = new StringTokenizer(bookingIds, ",");

    while (st.hasMoreTokens())
    {
      String bookingId = st.nextToken();
      try
      {
        logger.debug("About to unlock - " + bookingId);
        BookingLockHandler.getInstance().removeLock(Integer.valueOf(bookingId));
      }
      catch (NotLockedRuntimeException e)
      {
        logger.error("Error trying to remove lock on bookingId - " + bookingId);
      }
    }    

  }

}
