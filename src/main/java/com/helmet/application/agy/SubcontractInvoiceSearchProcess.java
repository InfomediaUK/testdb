package com.helmet.application.agy;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.application.comparator.SubcontractInvoiceUserComparator;
import com.helmet.bean.SubcontractInvoiceUser;


public class SubcontractInvoiceSearchProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    String subcontractInvoiceIdStr = (String) dynaForm.get("subcontractInvoiceId");
    String clientIdStr = (String) dynaForm.get("clientId");
    String siteIdStr = (String) dynaForm.get("siteId");
    String locationIdStr = (String) dynaForm.get("locationId");
    String jobProfileIdStr = (String) dynaForm.get("jobProfileId");
    String statusStr = (String) dynaForm.get("status");
    String fromDateStr = (String) dynaForm.get("fromDate");
    String toDateStr = (String) dynaForm.get("toDate");

    Date fromDate = null;
    Date toDate = null;
    // sort out the dates
    if (StringUtils.isNotEmpty(fromDateStr) && StringUtils.isNotEmpty(toDateStr))
    {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      sdf.setLenient(false);

      try
      {
        fromDate = new Date(sdf.parse(fromDateStr).getTime());
      }
      catch (ParseException e)
      {
        ActionMessages errors = new ActionMessages();
        MessageResources messageResources = getResources(request);
        errors.add("subcontractInvoiceSearch", new ActionMessage("errors.invalid", messageResources.getMessage("label.fromDate")));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
      try
      {
        toDate = new Date(sdf.parse(toDateStr).getTime());
      }
      catch (ParseException e)
      {
        ActionMessages errors = new ActionMessages();
        MessageResources messageResources = getResources(request);
        errors.add("subcontractInvoiceSearch", new ActionMessage("errors.invalid", messageResources.getMessage("label.toDate")));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
      System.out.println(">>>>> " + fromDate + " ----- " + toDate + " <<<<<");
    }

    // sort out single candidate
    Integer subcontractInvoiceId = null; //"".equals(subcontractInvoiceIdStr) ? null : Integer.parseInt(subcontractInvoiceIdStr);
    if (StringUtils.isNotEmpty(subcontractInvoiceIdStr))
    {
      if(subcontractInvoiceIdStr.startsWith("S"))
      {
        subcontractInvoiceId = Integer.parseInt(subcontractInvoiceIdStr.substring(1));
      }
      else
      {
        subcontractInvoiceId = Integer.parseInt(subcontractInvoiceIdStr);
      }
    }
    Integer clientId = StringUtils.isEmpty(clientIdStr) ? null : Integer.parseInt(clientIdStr);
    Integer siteId = StringUtils.isEmpty(siteIdStr) ? null : Integer.parseInt(siteIdStr);
    Integer locationId = StringUtils.isEmpty(locationIdStr) ? null : Integer.parseInt(locationIdStr);
    Integer jobProfileId = StringUtils.isEmpty(jobProfileIdStr) ? null : Integer.parseInt(jobProfileIdStr);
    Integer status = StringUtils.isEmpty(statusStr) ? null : Integer.parseInt(statusStr);

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    List<SubcontractInvoiceUser> list = agyService.getSubcontractInvoiceUsersForAgency(5, subcontractInvoiceId, clientId, siteId, locationId, jobProfileId, status, fromDate, toDate);
    sortSubcontractInvoiceTotals(list, dynaForm);

    dynaForm.set("list", list);

    logger.exit("Out going !!!");

    return mapping.findForward("success");

  }

  private void sortSubcontractInvoiceTotals(List<SubcontractInvoiceUser> list, DynaValidatorForm dynaForm)
  {
    Collections.sort(list, new SubcontractInvoiceUserComparator());
    BigDecimal totalValue = new BigDecimal(0);
    for (SubcontractInvoiceUser subcontractInvoice : list)
    {
      totalValue = totalValue.add(subcontractInvoice.getValue());
    }
    dynaForm.set("totalValue", totalValue);
  }
    

}
