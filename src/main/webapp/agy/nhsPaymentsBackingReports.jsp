<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsPaymentsBackingReports"/>:&nbsp;<bean:write name="NhsPaymentsBackingReportsFormAgy" property="nhsPaymentsFilename"/>
		</td>
  </tr>
</table>
<logic:present name="NhsPaymentsBackingReportsFormAgy" property="list">
  <logic:empty name="NhsPaymentsBackingReportsFormAgy" property="list">
    <bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="NhsPaymentsBackingReportsFormAgy" property="list">
    <table class="simple" width="100%" border="1">
      <thead>
      <tr>
        <th align="left"><bean:message key="label.name" /></th>
        <th align="left"><bean:message key="label.client" /></th>
        <th align="left"><bean:message key="label.startDate" /></th>
        <th align="left"><bean:message key="label.endDate" /></th>
        <th align="right"><bean:message key="label.agreedValue" /></th>
        <th align="right"><bean:message key="label.paidValue" /></th>
        <th align="left"><bean:message key="label.completeDate" /></th>
        <th align="left"><bean:message key="label.comment" /></th>
      </tr>
      </thead>
<logic:iterate id="nhsBackingReportUser" name="NhsPaymentsBackingReportsFormAgy" property="list" type="com.helmet.bean.NhsBackingReportUser">
      <tr>
        <td align="left">
        <mmj-agy:hasAccess forward="nhsBackingReportView" >
          <html:link forward="nhsBackingReportView" paramId="nhsBackingReportUser.nhsBackingReportId" paramName="nhsBackingReportUser" paramProperty="nhsBackingReportId" titleKey="title.nhsBackingReportView"><bean:write name="nhsBackingReportUser" property="name"/></html:link>
        </mmj-agy:hasAccess>
        <mmj-agy:hasNoAccess forward="nhsBackingReportView" >
          <bean:write name="nhsBackingReportUser" property="name"/>
        </mmj-agy:hasNoAccess>
        </td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="clientName"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="startDate" formatKey="format.longDateFormat"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="endDate" formatKey="format.longDateFormat"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="agreedValue" format="#,##0.00"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="paidValue" format="#,##0.00"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="completeDate" formatKey="format.longDateFormat"/></td>
        <td align="left"><bean:write name="nhsBackingReportUser" property="comment"/></td>
      </tr>
</logic:iterate>
    </table>
  </logic:notEmpty>
</logic:present>