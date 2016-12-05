package com.helmet.application.admin;

import java.text.DecimalFormat;
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
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.BookingGradeApplicant;
import com.helmet.bean.ClientAgencyJobProfile;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.Grade;
import com.helmet.bean.IntValue;


public class ClientAgencyJobProfileGradeDelete extends AdminAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    ActionMessages errors = new ActionMessages();
    ClientAgencyJobProfile clientAgencyJobProfile = (ClientAgencyJobProfile) dynaForm.get("clientAgencyJobProfile");
    String[] selectedItems = (String[]) dynaForm.get("selectedItems");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
//    BookingGradeApplicant bookingGradeApplicant = null;
    ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = null;
    DecimalFormat df = new DecimalFormat("#,##0.00");
    
    for (String str : selectedItems)
    {
      StringTokenizer st = new StringTokenizer(str, ",");
      Integer clientAgencyJobProfileGradeId = new Integer(st.nextToken().trim());
      Integer noOfChanges = new Integer(st.nextToken().trim());
      clientAgencyJobProfileGrade = adminService.getClientAgencyJobProfileGrade(clientAgencyJobProfileGradeId);
      Grade grade = null;
      if (clientAgencyJobProfileGrade.getNoOfChanges().equals(noOfChanges))
      {
        // ClientAgencyJobProfileGrade has NOT been updated by another user. OK to proceed...
        IntValue rowCount = adminService.getActiveBookingGradeApplicantsCountForClientAgencyJobProfileGrade(clientAgencyJobProfileGradeId);
        if (rowCount.getValue() > 0)
        {
          // Active BookingGradeApplicants found. Cannot delete this ClientAgencyJobProfileGrade...
          grade = adminService.getGrade(clientAgencyJobProfileGrade.getGradeId());
          errors.add("clientAgencyJobProfileGrade", new ActionMessage("errors.clientAgencyJobProfileGrade.inUse", grade.getName(), df.format(clientAgencyJobProfileGrade.getRate())));
        }
      }
      else
      {
        // ClientAgencyJobProfileGrade has been updated by another user. Cannot continue...
        grade = adminService.getGrade(clientAgencyJobProfileGrade.getGradeId());
        errors.add("clientAgencyJobProfileGrade", new ActionMessage("errors.clientAgencyJobProfileGrade.updatedByAnotherUser", grade.getName(), df.format(clientAgencyJobProfileGrade.getRate())));
        break;
      }
    }

    if (errors.isEmpty())
    {
      for (String str : selectedItems)
      {
        StringTokenizer st = new StringTokenizer(str, ",");
        Integer clientAgencyJobProfileGradeId = new Integer(st.nextToken().trim());
        Integer noOfChanges = new Integer(st.nextToken().trim());
        int rowCount = adminService.deleteClientAgencyJobProfileGrade(clientAgencyJobProfileGradeId, noOfChanges, getAdministratorLoggedIn().getAdministratorId());
      }
    }
    else
    {
      saveErrors(request, errors);
    }
    // Always use the 'success' forward...
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    // Do NOT redirect if there are ANY ERRORS, or the error messages won't get shown.
    return new ActionForward(actionForward.getName(), actionForward.getPath() + 
        "?clientAgencyJobProfile.clientAgencyJobProfileId=" + clientAgencyJobProfile.getClientAgencyJobProfileId(),
        errors.isEmpty() ? actionForward.getRedirect() : false);
  }

}
