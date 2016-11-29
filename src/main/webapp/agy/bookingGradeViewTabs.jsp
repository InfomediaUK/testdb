<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:parameter id="tab" name="tab" value="summary"/>

<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="summary"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
    <html:link forward="bookingGradeViewSummary" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeViewFormAgy" paramProperty="bookingGrade.bookingGradeId" titleKey="title.bookingGradeViewSummary" >
    <bean:message key="link.tabSummary"/>
    </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="shifts"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
    <html:link forward="bookingGradeViewShifts" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeViewFormAgy" paramProperty="bookingGrade.bookingGradeId" titleKey="title.bookingGradeViewShifts" >
    <bean:message key="link.tabShifts"/>
    </html:link>
    </td>
	  <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="applicants"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
	  <td align="center" class="<bean:write name="tabClass"/>" width="25%">
	  <html:link forward="bookingGradeViewApplicants" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeViewFormAgy" paramProperty="bookingGrade.bookingGradeId" titleKey="title.bookingGradeViewApplicants" >
	  <bean:message key="link.tabApplicants"/>
	  </html:link>
	  </td>
	  <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="timesheets"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
	  <td align="center" class="<bean:write name="tabClass"/>" width="25%">
	  <html:link forward="bookingGradeViewTimesheets" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeViewFormAgy" paramProperty="bookingGrade.bookingGradeId" titleKey="title.bookingGradeViewTimesheets" >
	  <bean:message key="link.tabTimesheets"/>
	  </html:link>
	  </td>
  </tr>
</table>
