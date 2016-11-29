<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="currentPage" name="NhsBackingReportsFormAgy" property="page" type="java.lang.Integer" />
<bean:define id="nhsBackingReportsFilter"      name="NhsBackingReportsFormAgy" property="nhsBackingReportsFilter" type="java.lang.String" />

<%
String actionPath       = "/nhsBackingReports.do";
String pageParameter    = (actionPath.indexOf('?') == -1) ? "?page=" : "&page=";
String allActionPath    = actionPath + pageParameter + currentPage + "&nhsBackingReportsFilter=ALL";
String paidActionPath   = actionPath + pageParameter + currentPage + "&nhsBackingReportsFilter=PAID";
String unpaidActionPath = actionPath + pageParameter + currentPage + "&nhsBackingReportsFilter=UNPAID";
%>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBackingReports"/>&nbsp;<bean:write name="NhsBackingReportsFormAgy" property="nhsBackingReportsFilter"/>&nbsp;(<bean:write name="NhsBackingReportsFormAgy" property="recordCount"/>)
<logic:equal name="NhsBackingReportsFormAgy" property="nhsBackingReportsFilter" value="PAID" >
      &nbsp;
		  <html:link page="<%= unpaidActionPath %>" title="Unpaid Backing Reports only">
		    Unpaid
		  </html:link>
      &nbsp;
		  <html:link page="<%= allActionPath %>" title="All Backing Reports">
		    All
		  </html:link>
</logic:equal>
<logic:equal name="NhsBackingReportsFormAgy" property="nhsBackingReportsFilter" value="UNPAID" >
      &nbsp;
		  <html:link page="<%= paidActionPath %>" title="Paid Backing Reports only">
		    Paid
		  </html:link>
      &nbsp;
		  <html:link page="<%= allActionPath %>" title="All Backing Reports">
		    All
		  </html:link>
</logic:equal>
<logic:equal name="NhsBackingReportsFormAgy" property="nhsBackingReportsFilter" value="ALL" >
      &nbsp;
		  <html:link page="<%= paidActionPath %>" title="Paid Backing Reports only">
		    Paid
		  </html:link>
      &nbsp;
		  <html:link page="<%= unpaidActionPath %>" title="Unpaid Backing Reports only">
		    Unpaid
		  </html:link>
</logic:equal>
		</td>
<mmj-agy:hasAccess forward="nhsBackingReportNew">
    <td align="right" valign="middle" width="75">
      <html:form action="/nhsBackingReportNew.do" onsubmit="return singleSubmit();">
		    <html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit>
		  </html:form>
    </td>
</mmj-agy:hasAccess>
  </tr>
</table>
<logic:notPresent name="NhsBackingReportsFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="NhsBackingReportsFormAgy" property="list">
	<html:errors/>
</logic:present>

<logic:present name="NhsBackingReportsFormAgy" property="list">
  <logic:empty name="NhsBackingReportsFormAgy" property="list">
    <bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="NhsBackingReportsFormAgy" property="list">
    <logic:greaterThan name="NhsBackingReportsFormAgy" property="pageCount" value="1">
		<jsp:include page="pagingInclude.jsp" flush="true">
		  <jsp:param name="actionPath" value="<%= actionPath %>"/>
		  <jsp:param name="formName" value="NhsBackingReportsFormAgy"/>
		</jsp:include>
    </logic:greaterThan>
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
<logic:iterate id="nhsBackingReportUser" name="NhsBackingReportsFormAgy" property="list" type="com.helmet.bean.NhsBackingReportUser">
      <tr>
        <td align="left">
		    <mmj-agy:hasAccess forward="nhsBackingReportView" >
		      <html:link forward="nhsBackingReportView" paramId="nhsBackingReportUser.nhsBackingReportId" paramName="nhsBackingReportUser" paramProperty="nhsBackingReportId" titleKey="title.nhsBackingReportView"><bean:write name="nhsBackingReportUser" property="name"/></html:link>
		    </mmj-agy:hasAccess>
		    <mmj-agy:hasNoAccess forward="nhsBackingReportView" >
		      <bean:write name="nhsBackingReportUser" property="name"/>
		    </mmj-agy:hasNoAccess>
		    <logic:greaterThan name="nhsBackingReportUser" property="subcontractAgencyId" value="0">*</logic:greaterThan>
        </td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="clientName"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="startDate" formatKey="format.mediumDateFormat"/>&nbsp;-&nbsp;<bean:write name="nhsBackingReportUser" property="endDate" formatKey="format.mediumDateFormat"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="agreedValue" format="#,##0.00"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="paidValue" format="#,##0.00"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="completeDate" formatKey="format.mediumDateFormat"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="subcontractValue" format="#,##0.00"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="subcontractPaidDate" formatKey="format.mediumDateFormat"/></td>
        <td align="left" width="30%"><bean:write name="nhsBackingReportUser" property="comment"/></td>
      </tr>
</logic:iterate>
    </table>
    * Uses Subcontracted Staff
  </logic:notEmpty>
</logic:present>