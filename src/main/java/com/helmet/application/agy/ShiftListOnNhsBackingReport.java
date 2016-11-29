package com.helmet.application.agy;

import java.util.List;

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
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.RecordCount;

public class ShiftListOnNhsBackingReport extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    Integer page = getPageNumber("ShiftListOnNhsBackingReportPageNumber", request, dynaForm);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    Integer limit = agyService.getBookingDatePagingLimit();
    Integer pagingGroupSize = agyService.getBookingDatePagingGroupSize();
    Integer offset = ((page - 1) * limit);

    Integer bookingGradeStatus = null;
    Integer bookingDateStatus = null;
    Integer workedStatus = agency.getHasSubcontractors() ? BookingGrade.BOOKINGGRADE_STATUS_OPEN : BookingGrade.BOOKINGGRADE_STATUS_CLOSED;
    List<BookingDateUserApplicant> listBookingDateUserApplicant = null;
    listBookingDateUserApplicant = agyService.getBookingDateUserApplicantsForAgencyOnBackingReport(getConsultantLoggedIn().getAgencyId(), null, null, null, null, bookingGradeStatus,
        bookingDateStatus, workedStatus, null, null, null, null, null, null, null, null, offset);
    RecordCount rc = agyService.getBookingDatesUserApplicantsForAgencyOnBackingReportCount(getConsultantLoggedIn().getAgencyId(), null, null, null, null, bookingGradeStatus, 
        bookingDateStatus, workedStatus, null, null, null, null, null, null, null, null);
    Integer pageCount = (rc.getCount() + (limit - 1)) / limit;
    
    dynaForm.set("list", listBookingDateUserApplicant);
    dynaForm.set("page", page);
    dynaForm.set("previousPage", page.equals(1) ? -1 : page - 1);
    dynaForm.set("nextPage", page.equals(pageCount) ? -1 : page + 1);
    dynaForm.set("pageCount", pageCount);
    dynaForm.set("pagingGroupSize", pagingGroupSize);
    dynaForm.set("recordCount", rc.getCount());
    ActionForward forward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return forward;
  }

}
