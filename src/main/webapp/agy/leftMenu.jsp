<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<%-- determine whether to show the bookings title --%>
<bean:define id="showBooking" value="false"/>
<mmj-agy:hasAccess forward="orderStaff"><bean:define id="showBooking" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="bookingGradeListNew"><bean:define id="showBooking" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="bookingGradeListOpen"><bean:define id="showBooking" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="bookingGradeSearch"><bean:define id="showBooking" value="true"/></mmj-agy:hasAccess>
<%-- bookings --%>
<logic:equal name="showBooking" value="true">
<bean:message key="title.bookings"/>
</logic:equal>
<mmj-agy:hasAccess forward="orderStaff">
<br/><html:link forward="orderStaff" styleClass="leftColumn"><bean:message key="link.new"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="bookingGradeListNew">
<br/><html:link forward="bookingGradeListNew" styleClass="leftColumn"><bean:message key="link.new"/> (<mmj-agy:bookingStatusCount status="-1"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="bookingGradeListOpen">
<br/><html:link forward="bookingGradeListOpen" styleClass="leftColumn"><bean:message key="link.open"/> (<mmj-agy:bookingStatusCount status="1"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="bookingGradeSearch"> <%-- DOESN'T EXISTS --%>
<br/><html:link forward="bookingGradeList" styleClass="leftColumn"><bean:message key="link.search"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="bookings">
<br/><html:link forward="bookings" styleClass="leftColumn"><bean:message key="link.bookings"/></html:link>
</mmj-agy:hasAccess>

<%-- determine whether to show the shifts title --%>
<bean:define id="showShifts" value="false"/>
<mmj-agy:hasAccess forward="shiftListNew"><bean:define id="showShifts" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListOpen"><bean:define id="showShifts" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftsToActivate"><bean:define id="showShifts" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftSearch"><bean:define id="showShifts" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftSearchMiData"><bean:define id="showShifts" value="true"/></mmj-agy:hasAccess>
<%-- shifts --%>
<logic:equal name="showShifts" value="true">
<br/><bean:message key="title.shifts"/>
</logic:equal>
<mmj-agy:hasAccess forward="shiftListNew">
<br/><html:link forward="shiftListNew" styleClass="leftColumn"><bean:message key="link.new"/> (<mmj-agy:shiftStatusCount status="-1"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListOpen">
<br/><html:link forward="shiftListOpen" styleClass="leftColumn"><bean:message key="link.open"/> (<mmj-agy:shiftStatusCount status="1"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftsToActivate">
<br/><html:link forward="shiftsToActivate" styleClass="leftColumn"><bean:message key="link.shiftsToActivate"/> (<mmj-agy:shiftsToActivateCount/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftSearch">
<br/><html:link forward="shiftSearch" styleClass="leftColumn"><bean:message key="link.search"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftSearchMiData">
<br/><html:link forward="shiftSearchMiData" styleClass="leftColumn"><bean:message key="link.search"/> MI Data</html:link>
</mmj-agy:hasAccess>

<%-- determine whether to show the timesheets title --%>
<bean:define id="showTimesheets" value="false"/>
<mmj-agy:hasAccess forward="shiftsOutstanding"><bean:define id="showTimesheets" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListDraft"><bean:define id="showTimesheets" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListSubmitted"><bean:define id="showTimesheets" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListAuthorized"><bean:define id="showTimesheets" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListNotOnNhsBackingReport"><bean:define id="showTimesheets" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListOnNhsBackingReport"><bean:define id="showTimesheets" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListRejected"><bean:define id="showTimesheets" value="true"/></mmj-agy:hasAccess>
<%-- timesheets --%>
<logic:equal name="showTimesheets" value="true">
<br/><bean:message key="title.timesheets"/>
<mmj-agy:hasAccess forward="shiftsOutstanding">
<br/><html:link forward="shiftsOutstanding" styleClass="leftColumn"><bean:message key="link.shiftsOutstanding"/>&nbsp;(<mmj-agy:shiftsOutstandingCount/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListDraft">
<br/><html:link forward="shiftListDraft" styleClass="leftColumn"><bean:message key="link.draft"/>&nbsp;(<mmj-agy:shiftWorkedStatusCount status="0"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListSubmitted">
<br/><html:link forward="shiftListSubmitted" styleClass="leftColumn"><bean:message key="link.submitted"/>&nbsp;(<mmj-agy:shiftWorkedStatusCount status="1"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListAuthorized">
<br/><html:link forward="shiftListAuthorized" styleClass="leftColumn"><bean:message key="link.authorized"/>&nbsp;(<mmj-agy:shiftWorkedStatusCount status="2"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListNotOnNhsBackingReport">
<br/><html:link forward="shiftListNotOnNhsBackingReport" styleClass="leftColumn"><bean:message key="link.notOnNhsBackingReport"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListOnNhsBackingReport">
<br/><html:link forward="shiftListOnNhsBackingReport" styleClass="leftColumn"><bean:message key="link.onNhsBackingReport"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="shiftListRejected">
<br/><html:link forward="shiftListRejected" styleClass="leftColumn"><bean:message key="link.rejected"/>&nbsp;(<mmj-agy:shiftWorkedStatusCount status="3"/>)</html:link>
</mmj-agy:hasAccess>
</logic:equal>

<%-- determine whether to show the applicants title --%>
<bean:define id="showApplicants" value="false"/>
<mmj-agy:hasAccess forward="bookingGradeListBGADOffered"><bean:define id="showApplicants" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="applicantNew"><bean:define id="showApplicants" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="applicantList"><bean:define id="showApplicants" value="true"/></mmj-agy:hasAccess>
<%-- applicants --%>
<logic:equal name="showApplicants" value="true">
<br/><bean:message key="title.applicants"/>
</logic:equal>
<mmj-agy:hasAccess forward="bookingGradeListBGADOffered">
<br/><html:link forward="bookingGradeListBGADOffered" styleClass="leftColumn"><bean:message key="link.offered"/> (<mmj-agy:bookingGradeApplicantDateStatusCount status="2"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="applicantNotifications">
<br/><html:link forward="applicantNotifications" styleClass="leftColumn"><bean:message key="link.applicantNotifications"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="applicantNew">
<br/><html:link forward="applicantNew" styleClass="leftColumn"><bean:message key="link.new"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="applicantList">
<br/><html:link forward="applicantList" styleClass="leftColumn"><bean:message key="link.list"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="applicantSearch">
<br/><html:link forward="applicantSearch" styleClass="leftColumn"><bean:message key="link.search"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="applicantWorkingList">
<br/><html:link forward="applicantWorkingList" styleClass="leftColumn"><bean:message key="link.working"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="applicantPaymentFileUpload">
<br/><html:link forward="applicantPaymentFileUpload" styleClass="leftColumn"><bean:message key="link.applicantPaymentFileUpload"/></html:link>
</mmj-agy:hasAccess>

<bean:define id="showNhsp" value="false"/>
<mmj-agy:hasAccess forward="nhsBookingFileUpload"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBookingsReady"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBookingGroupsReady"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBookings"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBackingReports"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBackingReportsForDateRange"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBackingReportFileUpload"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsPaymentsFileUpload"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="subcontractInvoicePaymentsFileUpload"><bean:define id="showNhsp" value="true"/></mmj-agy:hasAccess>
<logic:equal name="showNhsp" value="true">
  <br/><bean:message key="title.nhsp"/>
</logic:equal>
<mmj-agy:hasAccess forward="nhsBookingFileUpload">
<br/><html:link forward="nhsBookingFileUpload" styleClass="leftColumn"><bean:message key="link.nhsBookingFileUpload"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBookingsReady">
<br/><html:link forward="nhsBookingsReady" styleClass="leftColumn"><bean:message key="link.nhsBookingsReady"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBookingGroupsReady">
<br/><html:link forward="nhsBookingGroupsReady" styleClass="leftColumn"><bean:message key="link.nhsBookingGroupsReady"/></html:link>
</mmj-agy:hasAccess>
<%-- 
<mmj-agy:hasAccess forward="bookingGradeListNew">
<br/><html:link forward="bookingGradeListNewNhs" styleClass="leftColumn"><bean:message key="link.new"/></html:link>
</mmj-agy:hasAccess>
--%>
<mmj-agy:hasAccess forward="nhsBookingsBookTaskMonitor">
<br/><html:link forward="nhsBookingsBookTaskMonitor" styleClass="leftColumn"><bean:message key="link.nhsBookingsBookTaskMonitor"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBookings">
<br/><html:link forward="nhsBookings" styleClass="leftColumn"><bean:message key="link.nhsBookings"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBackingReports">
<br/><html:link forward="nhsBackingReports" styleClass="leftColumn"><bean:message key="link.nhsBackingReports"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBackingReportsForDateRange">
<br/><html:link forward="nhsBackingReportsForDateRange" styleClass="leftColumn"><bean:message key="link.nhsBackingReportsForDateRange"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBackingReportFileUpload">
<br/><html:link forward="nhsBackingReportFileUpload" styleClass="leftColumn"><bean:message key="link.nhsBackingReportFileUpload"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsPaymentsFileUpload">
<br/><html:link forward="nhsPaymentsFileUpload" styleClass="leftColumn"><bean:message key="link.nhsPaymentsFileUpload"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="subcontractInvoicePaymentsFileUpload">
<br/><html:link forward="subcontractInvoicePaymentsFileUpload" styleClass="leftColumn"><bean:message key="link.subcontractInvoicePaymentsFileUpload"/></html:link>
</mmj-agy:hasAccess>


<%-- determine whether to show the invoices title --%>
<bean:define id="showInvoices" value="false"/>
<mmj-agy:hasAccess forward="agencyInvoiceListSubmitted"><bean:define id="showInvoices" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="agencyInvoiceListAuthorized"><bean:define id="showInvoices" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="agencyInvoiceSearch"><bean:define id="showInvoices" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="subcontractInvoiceSearch"><bean:define id="showInvoices" value="true"/></mmj-agy:hasAccess>
<%-- invoices --%>
<logic:equal name="showInvoices" value="true">
<br/><bean:message key="title.invoices"/>
</logic:equal>
<mmj-agy:hasAccess forward="agencyInvoiceListSubmitted">
<br/><html:link forward="agencyInvoiceListSubmitted" styleClass="leftColumn"><bean:message key="link.submitted"/>&nbsp;(<mmj-agy:agencyInvoiceStatusCount status="0"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="agencyInvoiceListAuthorized">
<br/><html:link forward="agencyInvoiceListAuthorized" styleClass="leftColumn"><bean:message key="link.authorized"/>&nbsp;(<mmj-agy:agencyInvoiceStatusCount status="1"/>)</html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="agencyInvoiceSearch">
<br/><html:link forward="agencyInvoiceSearch" styleClass="leftColumn"><bean:message key="link.search"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="subcontractInvoiceSearch">
<br/><html:link forward="subcontractInvoiceSearch" styleClass="leftColumn">Search 4SW</html:link>
</mmj-agy:hasAccess>

<%-- determine whether to show the profile title --%>
<bean:define id="showProfile" value="false"/>
<mmj-agy:hasAccess forward="changePassword"><bean:define id="showProfile" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="changeSecretWord"><bean:define id="showProfile" value="true"/></mmj-agy:hasAccess>
<%-- timesheets --%>
<logic:equal name="showProfile" value="true">
<br/><bean:message key="title.profile"/>
</logic:equal>
<mmj-agy:hasAccess forward="changePassword">
<br/><html:link forward="changePassword" styleClass="leftColumn"><bean:message key="link.changePassword"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="changeSecretWord">
<br/><html:link forward="changeSecretWord" styleClass="leftColumn"><bean:message key="link.changeSecretWord"/></html:link>
</mmj-agy:hasAccess>

<%-- determine whether to show the data title --%>
<bean:define id="showData" value="false"/>
<mmj-agy:hasAccess forward="agencyView"><bean:define id="showData" value="true"/></mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="clientList"><bean:define id="showData" value="true"/></mmj-agy:hasAccess>
<%-- data --%>
<logic:equal name="showData" value="true">
<br/><bean:message key="title.data"/>
</logic:equal>

<mmj-agy:hasAccess forward="agencyView">
<br/><html:link forward="agencyView" styleClass="leftColumn"><bean:message key="link.agencyView"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="clientList">
<br/><html:link forward="clientList" styleClass="leftColumn"><bean:message key="link.clientList"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="agencyTradeshiftRefresh">
<br/><html:link forward="agencyTradeshiftRefresh" styleClass="leftColumn">Tradeshift Refresh</html:link>
</mmj-agy:hasAccess>
<br/>
<mmj-agy:hasAccess forward="sendEmail">
<br/><html:link forward="sendEmail" styleClass="leftColumn"><bean:message key="link.sendEmail"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="sendSms">
<br/><html:link forward="sendSms" styleClass="leftColumn"><bean:message key="link.sendSms"/></html:link>
</mmj-agy:hasAccess>
