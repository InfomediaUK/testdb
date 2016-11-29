package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Address;
import com.helmet.bean.Client;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Expense;
import com.helmet.bean.LocationUser;


public class OrderStaffCopy9 extends OrderStaffCopyBase
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    Integer page = (Integer) dynaForm.get("page");
    Boolean complete = (Boolean)dynaForm.get("complete");
    if (isCancelled(request))
    {
      dynaForm.set("page", page - 1);
      return mapping.findForward("back");
    }

    // checks !!!
    ClientUser client = (ClientUser) dynaForm.get("client");
    if (client.getClientId() == null || client.getClientId() == 0) { return mapping.findForward("orderStaff"); }

    String[] selectedExpenses = (String[]) dynaForm.get("selectedExpenses");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    Expense[] expenseArray = buildExpenseArray(selectedExpenses, agyService);
    dynaForm.set("expenseArray", expenseArray);

    // determine whether the user is going forwards (next) or backwards (back)
    boolean userPressedNext = page == 8;

    if (userPressedNext)
    {

      String contactName = (String) dynaForm.get("contactName");
      String contactJobTitle = (String) dynaForm.get("contactJobTitle");
      String contactEmailAddress = (String) dynaForm.get("contactEmailAddress");
      String contactTelephoneNumber = (String) dynaForm.get("contactTelephoneNumber");

      LocationUser location = (LocationUser) dynaForm.get("location");

      if (contactName == null || "".equals(contactName))
      {
        dynaForm.set("contactName", location.getContactName());
      }
      if (contactJobTitle == null || "".equals(contactJobTitle))
      {
        dynaForm.set("contactJobTitle", location.getContactJobTitle());
      }
      if (contactEmailAddress == null || "".equals(contactEmailAddress))
      {
        dynaForm.set("contactEmailAddress", location.getContactEmailAddress());
      }
      if (contactTelephoneNumber == null || "".equals(contactTelephoneNumber))
      {
        dynaForm.set("contactTelephoneNumber", location.getContactTelephoneNumber());
      }

      String accountContactName = (String) dynaForm.get("accountContactName");
      Address accountContactAddress = (Address) dynaForm.get("accountContactAddress");
      String accountContactEmailAddress = (String) dynaForm.get("accountContactEmailAddress");

      Client currentClient = client;

      // default the accountContact only if blank ...
      if (accountContactName == null || "".equals(accountContactName))
      {
        dynaForm.set("accountContactName", currentClient.getAccountContactName() == null || "".equals(currentClient.getAccountContactName()) ? currentClient.getName() : currentClient
            .getAccountContactName());
      }
      if (accountContactAddress.getAddress1() == null || "".equals(accountContactAddress.getAddress1()))
      {
        dynaForm.set("accountContactAddress", currentClient.getAccountContactAddress().getAddress1() == null || "".equals(currentClient.getAccountContactAddress().getAddress1()) ? currentClient
            .getAddress() : currentClient.getAccountContactAddress());
      }
      if (accountContactEmailAddress == null || "".equals(accountContactEmailAddress))
      {
        dynaForm.set("accountContactEmailAddress", currentClient.getAccountContactEmailAddress());
      }

    }
    if (complete)
    {
      return mapping.findForward("complete");
    }
    else
    {
      return mapping.findForward("success");
    }
  }

}
