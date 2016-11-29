<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="weekToShow" name="NhsBackingReportsForDateRangeFormAgy" property="weekToShow" />
<%
String theAction = "/nhsBackingReportsForDateRange.do";
%>


<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBackingReportsForDateRange"/>
<logic:greaterThan name="NhsBackingReportsForDateRangeFormAgy" property="countNhsBackingReport" value="0">
      (<bean:write name="NhsBackingReportsForDateRangeFormAgy" property="countNhsBackingReport"/>)
</logic:greaterThan>
		</td>
  </tr>
</table>
<logic:notPresent name="NhsBackingReportsForDateRangeFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="NhsBackingReportsForDateRangeFormAgy" property="list">
	<html:errors/>
</logic:present>

<jsp:include page="weekToShowNavigationTab.jsp" flush="true">
  <jsp:param name="theForm" value="NhsBackingReportsForDateRangeFormAgy"/>
  <jsp:param name="weekToShow" value="<%= weekToShow %>"/>
</jsp:include>

<logic:present name="NhsBackingReportsForDateRangeFormAgy" property="list">
  <logic:empty name="NhsBackingReportsForDateRangeFormAgy" property="list">
    <bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="NhsBackingReportsForDateRangeFormAgy" property="list">
    <table class="simple" width="100%" border="1">
		  <thead>
      <tr>
		    <th align="left"><bean:message key="label.name" /></th>
		    <th align="left"><bean:message key="label.client" /></th>
		    <th align="left"><bean:message key="label.dates" /></th>
		    <th align="right"><bean:message key="label.agreed" /></th>
		    <th align="right"><bean:message key="label.paid" /></th>
		    <th align="left"><bean:message key="label.complete" /></th>
		    <th align="left"><bean:message key="label.sub" /></th>
		    <th align="left"><bean:message key="label.subPaid" /></th>
		    <th align="left" width="30%"><bean:message key="label.comment" /></th>
      </tr>
      </thead>
<logic:iterate id="nhsBackingReportUser" name="NhsBackingReportsForDateRangeFormAgy" property="list" type="com.helmet.bean.NhsBackingReportUser">
      <tr>
        <td align="left">
		    <mmj-agy:hasAccess forward="nhsBackingReportShiftList" >
          <html:link forward="nhsBackingReportShiftList" paramId="nhsBackingReportUser.nhsBackingReportId" paramName="nhsBackingReportUser" paramProperty="nhsBackingReportId" titleKey="title.nhsBackingReportShiftList"><bean:write name="nhsBackingReportUser" property="name"/></html:link>
        </mmj-agy:hasAccess>
		    <mmj-agy:hasNoAccess forward="nhsBackingReportShiftList" >
		      <bean:write name="nhsBackingReportUser" property="name"/>
		    </mmj-agy:hasNoAccess>
		    <logic:greaterThan name="nhsBackingReportUser" property="subcontractAgencyId" value="0">*</logic:greaterThan>
        </td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="clientName"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="startDate" formatKey="format.mediumDateFormat"/>&nbsp;-&nbsp;<bean:write name="nhsBackingReportUser" property="endDate" formatKey="format.mediumDateFormat"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="agreedValue" format="#,##0.00"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="paidValue" format="#,##0.00"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="completeDate" formatKey="format.longDateFormat"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="subcontractValue" format="#,##0.00"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="subcontractPaidDate" formatKey="format.mediumDateFormat"/></td>
        <td align="left" width="30%"><bean:write name="nhsBackingReportUser" property="comment"/></td>
      </tr>
</logic:iterate>
    </table>
    * Uses Subcontracted Staff
  </logic:notEmpty>
</logic:present>