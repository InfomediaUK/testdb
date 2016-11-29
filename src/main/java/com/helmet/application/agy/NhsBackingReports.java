package com.helmet.application.agy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.NhsBackingReportUser;
import com.helmet.bean.RecordCount;

public class NhsBackingReports extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    String filter = (String)dynaForm.get("nhsBackingReportsFilter");
    if (StringUtils.isEmpty(filter))
    {
      // Filter NOT in Request. Check Session.
      filter = (String)request.getSession().getAttribute("nhsBackingReportsFilter");
      if (filter == null)
      {
        // Filter NOT in Request or Session. Set to NO FILTER.
        filter = AgyConstants.NO_FILTER;
        request.getSession().setAttribute("nhsBackingReportsFilter", filter);
      }
    }
    else
    {
      // Filter in Request. Put in Session.
      request.getSession().setAttribute("nhsBackingReportsFilter", filter);
    }
    filter = (String)request.getSession().getAttribute("nhsBackingReportsFilter");
    Integer page = getPageNumber("NhsBackingReportsPageNumber", request, dynaForm);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Integer limit = agyService.getNhsBackingReportPagingLimit();
    Integer pagingGroupSize = agyService.getNhsBackingReportPagingGroupSize();
    Integer offset = ((page - 1) * limit);
    // Fill list with NHSBackingReports.
    List<NhsBackingReportUser> list = agyService.getNhsBackingReportUsersOffset(getConsultantLoggedIn().getAgencyId(), true, offset, filter);
    RecordCount rc = agyService.getNhsBackingReportsCount(getConsultantLoggedIn().getAgencyId(), true, filter);
    Integer pageCount = (rc.getCount() + (limit - 1)) / limit;
    // A list of NhsBookingReportGroups.
    dynaForm.set("list", list);
    dynaForm.set("page", page);
    dynaForm.set("previousPage", page.equals(1) ? -1 : page - 1);
    dynaForm.set("nextPage", page.equals(pageCount) ? -1 : page + 1);
    dynaForm.set("pageCount", pageCount);
    dynaForm.set("pagingGroupSize", pagingGroupSize);
    dynaForm.set("recordCount", rc.getCount());
    dynaForm.set("nhsBackingReportsFilter", filter);
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
