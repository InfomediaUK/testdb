<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"
%><%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" 
%>

<%-- determine whether to show the bookings title --%>
<bean:define id="showBooking" value="false"/>
<mmj-mgr:hasAccess forward="orderStaff"><bean:define id="showBooking" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingListDraft"><bean:define id="showBooking" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingListUnfilled"><bean:define id="showBooking" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingListOffered"><bean:define id="showBooking" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingSearch"><bean:define id="showBooking" value="true"/></mmj-mgr:hasAccess>
<%-- bookings --%>
<logic:equal name="showBooking" value="true">
<bean:message key="title.bookings"/>
</logic:equal>
<mmj-mgr:hasAccess forward="orderStaff">
<br/><html:link accesskey="o" forward="orderStaff" styleClass="leftColumn"><bean:message key="link.new"/></html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingListDraft">
<br/><html:link forward="bookingListDraft" styleClass="leftColumn"><bean:message key="link.bookingListDraft"/> (<mmj-mgr:bookingStatusCount status="0"/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingListUnfilled">
<br/><html:link forward="bookingListUnfilled" styleClass="leftColumn"><bean:message key="link.bookingListUnfilled"/> (<mmj-mgr:bookingStatusCount status="1"/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingListOffered">
<br/><html:link forward="bookingListOffered" styleClass="leftColumn"><bean:message key="link.bookingListOffered"/> (<mmj-mgr:bookingStatusCount status="2"/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingSearch">
<br/><html:link forward="bookingSearch" styleClass="leftColumn"><bean:message key="link.bookingSearch"/></html:link>
</mmj-mgr:hasAccess>
<%-- determine whether to show the shifts title --%>
<bean:define id="showShifts" value="false"/>
<mmj-mgr:hasAccess forward="shiftListUnfilled"><bean:define id="showShifts" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftListOffered"><bean:define id="showShifts" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftsToActivate"><bean:define id="showShifts" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftSearch"><bean:define id="showShifts" value="true"/></mmj-mgr:hasAccess>
<%-- shifts --%>
<logic:equal name="showShifts" value="true">
<br/><bean:message key="title.shifts"/>
</logic:equal>
<mmj-mgr:hasAccess forward="shiftListUnfilled">
<br/><html:link forward="shiftListUnfilled" styleClass="leftColumn"><bean:message key="link.shiftListUnfilled"/> (<mmj-mgr:shiftStatusCount status="1"/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftListOffered">
<br/><html:link forward="shiftListOffered" styleClass="leftColumn"><bean:message key="link.shiftListOffered"/> (<mmj-mgr:shiftStatusCount status="2"/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftsToActivate">
<br/><html:link forward="shiftsToActivate" styleClass="leftColumn"><bean:message key="link.shiftsToActivate"/> (<mmj-mgr:shiftsToActivateCount/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftSearch">
<br/><html:link forward="shiftSearch" styleClass="leftColumn"><bean:message key="link.shiftSearch"/></html:link>
</mmj-mgr:hasAccess>
<%-- determine whether to show the timesheets title --%>
<bean:define id="showTimesheets" value="false"/>
<mmj-mgr:hasAccess forward="shiftsOutstanding"><bean:define id="showTimesheets" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftListTSSubmitted"><bean:define id="showTimesheets" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftListTSAuthorized"><bean:define id="showTimesheets" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftListTSRejected"><bean:define id="showTimesheets" value="true"/></mmj-mgr:hasAccess>
<%-- timesheets --%>
<logic:equal name="showTimesheets" value="true">
<br/><bean:message key="title.timesheets"/>
</logic:equal>
<mmj-mgr:hasAccess forward="shiftsOutstanding">
<br/><html:link forward="shiftsOutstanding" styleClass="leftColumn"><bean:message key="link.shiftsOutstanding"/>&nbsp;(<mmj-mgr:shiftsOutstandingCount/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftListTSSubmitted">
<br/><html:link forward="shiftListTSSubmitted" styleClass="leftColumn"><bean:message key="link.shiftListTSSubmitted"/>&nbsp;(<mmj-mgr:shiftWorkedStatusCount status="1"/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftListTSAuthorized">
<br/><html:link forward="shiftListTSAuthorized" styleClass="leftColumn"><bean:message key="link.shiftListTSAuthorized"/>&nbsp;(<mmj-mgr:shiftWorkedStatusCount status="2"/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftListTSRejected">
<br/><html:link forward="shiftListTSRejected" styleClass="leftColumn"><bean:message key="link.shiftListTSRejected"/>&nbsp;(<mmj-mgr:shiftWorkedStatusCount status="3"/>)</html:link>
</mmj-mgr:hasAccess>
<%-- determine whether to show the applicants title --%>
<bean:define id="showApplicants" value="false"/>
<mmj-mgr:hasAccess forward="bookingListBGADOpen"><bean:define id="showApplicants" value="true"/></mmj-mgr:hasAccess>
<%-- applicants --%>
<logic:equal name="showApplicants" value="true">
<br/><bean:message key="title.applicants"/>
</logic:equal>
<mmj-mgr:hasAccess forward="bookingListBGADOpen">
<br/><html:link forward="bookingListBGADOpen" styleClass="leftColumn"><bean:message key="link.bookingListBGADOpen"/>&nbsp;(<mmj-mgr:bookingGradeApplicantDateStatusCount status="1"/>)</html:link>
</mmj-mgr:hasAccess>
<%-- determine whether to show the invoices title --%>
<bean:define id="showInvoices" value="false"/>
<mmj-mgr:hasAccess forward="agencyInvoiceListSubmitted"><bean:define id="showInvoices" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="agencyInvoiceListAuthorized"><bean:define id="showInvoices" value="true"/></mmj-mgr:hasAccess>
<%-- invoices --%>
<logic:equal name="showInvoices" value="true">
<br/><bean:message key="title.invoices"/>
</logic:equal>
<mmj-mgr:hasAccess forward="agencyInvoiceListSubmitted">
<br/><html:link forward="agencyInvoiceListSubmitted" styleClass="leftColumn"><bean:message key="link.agencyInvoiceListSubmitted"/>&nbsp;(<mmj-mgr:agencyInvoiceStatusCount status="0"/>)</html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="agencyInvoiceListAuthorized">
<br/><html:link forward="agencyInvoiceListAuthorized" styleClass="leftColumn"><bean:message key="link.agencyInvoiceListAuthorized"/>&nbsp;(<mmj-mgr:agencyInvoiceStatusCount status="1"/>)</html:link>
</mmj-mgr:hasAccess>
<%-- determine whether to show the accountable title --%>
<bean:define id="showAccountable" value="false"/>
<mmj-mgr:hasAccess forward="locationList"><bean:define id="showAccountable" value="true"/></mmj-mgr:hasAccess>
<%-- accountable --%>
<logic:equal name="showAccountable" value="true">
<br/><bean:message key="title.accountable"/>
</logic:equal>
<mmj-mgr:hasAccess forward="locationList">
<br/><html:link forward="locationList" styleClass="leftColumn"><bean:message key="link.locationList"/></html:link>
</mmj-mgr:hasAccess>
<%-- determine whether to show the reports title --%>
<bean:define id="showReports" value="false"/>
<%-- reports --%>
<logic:equal name="showReports" value="true">
<br/><bean:message key="title.reports"/>
</logic:equal>
<%-- determine whether to show the profile title --%>
<bean:define id="showProfile" value="false"/>
<mmj-mgr:hasAccess forward="changePassword"><bean:define id="showProfile" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="changeSecretWord"><bean:define id="showProfile" value="true"/></mmj-mgr:hasAccess>
<%-- profile --%>
<logic:equal name="showProfile" value="true">
<br/><bean:message key="title.profile"/>
</logic:equal>
<mmj-mgr:hasAccess forward="changePassword">
<br/><html:link forward="changePassword" styleClass="leftColumn"><bean:message key="link.changePassword"/></html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="changeSecretWord">
<br/><html:link forward="changeSecretWord" styleClass="leftColumn"><bean:message key="link.changeSecretWord"/></html:link>
</mmj-mgr:hasAccess>


<%-- determine whether to show the data title --%>
<bean:define id="showData" value="false"/>
<mmj-mgr:hasAccess forward="clientView"><bean:define id="showData" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="siteList"><bean:define id="showData" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="managerList"><bean:define id="showData" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="upliftList"><bean:define id="showData" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="publicHolidayList"><bean:define id="showData" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="reasonForRequestList"><bean:define id="showData" value="true"/></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="gradeList"><bean:define id="showData" value="true"/></mmj-mgr:hasAccess>
<%-- data --%>
<logic:equal name="showData" value="true">
<br/><bean:message key="title.data"/>
</logic:equal>
<mmj-mgr:hasAccess forward="clientView">
<br/><html:link forward="clientView" styleClass="leftColumn"><bean:message key="link.clientView"/></html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="siteList">
<br/><html:link forward="siteList" styleClass="leftColumn"><bean:message key="link.siteList"/></html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="managerList">
<br/><html:link forward="managerList" styleClass="leftColumn"><bean:message key="link.managerList"/></html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="upliftList">
<br/><html:link forward="upliftList" styleClass="leftColumn"><bean:message key="link.upliftList"/></html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="publicHolidayList">
<br/><html:link forward="publicHolidayList" styleClass="leftColumn"><bean:message key="link.publicHolidayList"/></html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="reasonForRequestList">
<br/><html:link forward="reasonForRequestList" styleClass="leftColumn"><bean:message key="link.reasonForRequestList"/></html:link>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="gradeList">
<br/><html:link forward="gradeList" styleClass="leftColumn"><bean:message key="link.gradeList"/></html:link>
</mmj-mgr:hasAccess>

