<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>
<bean:parameter id="isActivate" name="isActivate" value="false"/>
<bean:parameter id="isAuthorize" name="isAuthorize" value="false"/>
<bean:parameter id="isReject" name="isReject" value="false"/>
<bean:parameter id="isInvoice" name="isInvoice" value="false"/>

<bean:parameter id="tab" name="tab" value="summary"/>

<bean:define id="actionPrefix" value="bookingDateView"/>
<logic:equal name="isCancel" value="true">
  <bean:define id="actionPrefix" value="bookingDateCancel"/>
</logic:equal>
<logic:equal name="isActivate" value="true">
  <bean:define id="actionPrefix" value="bookingDateActivate"/>
</logic:equal>
<logic:equal name="isAuthorize" value="true">
  <bean:define id="actionPrefix" value="bookingDateAuthorize"/>
</logic:equal>
<logic:equal name="isReject" value="true">
  <bean:define id="actionPrefix" value="bookingDateReject"/>
</logic:equal>
<logic:equal name="isInvoice" value="true">
  <bean:define id="actionPrefix" value="bookingDateInvoice"/>
</logic:equal>
<bean:define id="summaryAction" value="<%= actionPrefix + "Summary"%>"/>
<bean:define id="detailsAction" value="<%= actionPrefix + "Details"%>"/>
<bean:define id="expensesAction" value="<%= actionPrefix + "Expenses"%>"/>
<bean:define id="hourlyAction" value="<%= actionPrefix + "Hourly"%>"/>

<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="summary"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
    <html:link forward="<%= summaryAction %>" paramId="bookingDate.bookingDateId" paramName="BookingDateUserApplicantFormMgr" paramProperty="bookingDate.bookingDateId" titleKey="title.bookingDateViewSummary" >
    <bean:message key="link.tabSummary"/>
    </html:link>
    </td>
<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.activated" value="true">
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="details"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
    <html:link forward="<%= detailsAction %>" paramId="bookingDate.bookingDateId" paramName="BookingDateUserApplicantFormMgr" paramProperty="bookingDate.bookingDateId" titleKey="title.bookingDateViewDetails" >
    <bean:message key="link.tabDetails"/>
    </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="expenses"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
    <html:link forward="<%= expensesAction %>" paramId="bookingDate.bookingDateId" paramName="BookingDateUserApplicantFormMgr" paramProperty="bookingDate.bookingDateId" titleKey="title.bookingDateViewExpenses" >
    <bean:message key="link.tabExpenses"/>
    </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="hourly"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
    <html:link forward="<%= hourlyAction %>" paramId="bookingDate.bookingDateId" paramName="BookingDateUserApplicantFormMgr" paramProperty="bookingDate.bookingDateId" titleKey="title.bookingDateViewHourly" >
    <bean:message key="link.tabHourly"/>
    </html:link>
    </td>
</logic:equal>
<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.activated" value="true">
    <td align="center" class="tabUnavailableClass" width="25%">
    <bean:message key="link.tabDetails"/>
    </td>
    <td align="center" class="tabUnavailableClass" width="25%">
    <bean:message key="link.tabExpenses"/>
    </td>
    <td align="center" class="tabUnavailableClass" width="25%">
    <bean:message key="link.tabHourly"/>
    </td>
</logic:notEqual>
  </tr>
</table>
