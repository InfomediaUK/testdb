package com.helmet.application.agy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

import com.helmet.application.NhsBookingsBookTaskHandle;
import com.helmet.application.NhsBookingsBookTaskResult;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.application.agy.AgyConstants;


public class NhsBookingsBookTaskMonitor extends AgyAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    // Get the NhsBookingsBookTaskHandle from the Session if it exists.
    NhsBookingsBookTaskHandle nhsBookingsBookTaskHandle = (NhsBookingsBookTaskHandle)request.getSession().getAttribute(AgyConstants.NHS_BOOKINGS_BOOK_TASK_HANDLE);
    NhsBookingsBookTaskResult result = null;
    if (nhsBookingsBookTaskHandle != null)
    {
      Future<NhsBookingsBookTaskResult> future = nhsBookingsBookTaskHandle.getFuture();
      if (future == null)
      {
        result = new NhsBookingsBookTaskResult(nhsBookingsBookTaskHandle);
      }
      else
      {
        if (future.isDone())
        {
          try
          {
            result = future.get();
            if (result != null)
            {
              result.setDone(true);
            }
          }
          catch (InterruptedException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          catch (ExecutionException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        else
        {
          result = new NhsBookingsBookTaskResult(nhsBookingsBookTaskHandle);
        }
      }
    }
    dynaForm.set("nhsBookingsBookTaskResult", result);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }
 

}
