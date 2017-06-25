package com.helmet.application.agy;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.NhsBooking;

public class NhsBookingApplicantPaidDateUpdate extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String paymentDateStr = (String)dynaForm.get("paymentDateStr");
    String applicantPaymentFilename = (String)dynaForm.get("applicantPaymentFilename");
    Date paymentDate = convertDate(paymentDateStr, sdf, errors, messageResources, "label.applicantPaidDate");
    int rowCount = 0;
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    Integer nhsBookingId = null;
    NhsBooking nhsBooking = null;
    Enumeration<String> paramNames = request.getParameterNames();
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.equals("nhsBookingId"))
      {
        String[] paramValues = request.getParameterValues(paramName);
        for(int i = 0; i < paramValues.length; i++) 
        {
          // For each Applicant to be emailed...
          nhsBookingId = Integer.parseInt(paramValues[i]);
          nhsBooking = agyService.getNhsBooking(nhsBookingId);
          if (nhsBooking == null)
          {
            // Applicant NOT found is a disaster...
          }
          else
          {
            // Applicant found, so now validate them.
            nhsBooking.setApplicantPaidDate(paymentDate);
            if (StringUtils.isEmpty(nhsBooking.getComment()))
            {
              nhsBooking.setComment("Paid Date from: " + applicantPaymentFilename);
            }
            else
            {
              nhsBooking.setComment(nhsBooking.getComment() + "\n\n Paid Date from: " + applicantPaymentFilename);
            }
            rowCount += agyService.updateNhsBookingCommentValueApplicantPaidDate(nhsBooking, getConsultantLoggedIn().getConsultantId());
          }
        }
      }
    }
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    logger.exit("Out going !!!");
    return mapping.findForward("success");  
  }

}
