<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:parameter id="tab" name="tab" value="summary"/>

<bean:parameter id="isInsertOrEdit" name="isInsertOrEdit" value="false"/>

<table class="tabs" width="100%">
  <tr>
<logic:equal name="isInsertOrEdit" value="true">
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="summary"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <html:link forward="orderStaff10Summary" titleKey="title.bookingViewSummary">
    <bean:message key="link.tabSummary"/>
    </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="shifts"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <html:link forward="orderStaff10Shifts" titleKey="title.bookingViewShifts">
    <bean:message key="link.tabShifts"/>
    </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="grades"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <html:link forward="orderStaff10Grades" paramId="tab" paramName="tab" titleKey="title.bookingViewGrades" >
    <bean:message key="link.tabGrades"/>
    </html:link>
    </td>
    <td align="center" class="tabUnavailableClass" width="20%">
    <bean:message key="link.tabApplicants"/>
    </td>
    <td align="center" class="tabUnavailableClass" width="20%">
    <bean:message key="link.tabTimesheets"/>
    </td>
</logic:equal>
<logic:notEqual name="isInsertOrEdit" value="true">
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="summary"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <html:link forward="bookingViewSummary" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId" titleKey="title.bookingViewSummary" >
    <bean:message key="link.tabSummary"/>
    </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="shifts"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <html:link forward="bookingViewShifts" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId" titleKey="title.bookingViewShifts" >
    <bean:message key="link.tabShifts"/>
    </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="grades"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <html:link forward="bookingViewGrades" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId" titleKey="title.bookingViewGrades" >
    <bean:message key="link.tabGrades"/>
    </html:link>
    </td>
    <logic:equal name="BookingViewFormMgr" property="booking.draft" value="true">
	    <td align="center" class="tabUnavailableClass" width="20%">
	    <bean:message key="link.tabApplicants"/>
	    </td>
	    <td align="center" class="tabUnavailableClass" width="20%">
	    <bean:message key="link.tabTimesheets"/>
	    </td>
    </logic:equal>
    <logic:notEqual name="BookingViewFormMgr" property="booking.draft" value="true">
	    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="applicants"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
	    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
	    <html:link forward="bookingViewApplicants" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId" titleKey="title.bookingViewApplicants" >
	    <bean:message key="link.tabApplicants"/>
	    </html:link>
	    </td>
	    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="timesheets"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
	    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
	    <html:link forward="bookingViewTimesheets" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId" titleKey="title.bookingViewTimesheets" >
	    <bean:message key="link.tabTimesheets"/>
      </html:link>
	    </td>
    </logic:notEqual>
</logic:notEqual>
  </tr>
</table>
