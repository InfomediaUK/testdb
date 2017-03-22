<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.compliancyTestView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.property"/></td>
    <td align="left"><bean:write name="CompliancyTestFormAdmin" property="compliancyTest.property"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.value"/></td>
    <td align="left"><bean:write name="CompliancyTestFormAdmin" property="compliancyTest.value"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.requiredDocumentText"/></td>
    <td align="left"><bean:write name="CompliancyTestFormAdmin" property="compliancyTest.requiredDocumentText"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="CompliancyTestFormAdmin" property="compliancyTest.displayOrder"/></td>
  </tr>
</table>

<mmj-admin:hasAccess forward="compliancyTestEdit" >
  <html:link forward="compliancyTestEdit" paramId="compliancyTest.compliancyTestId" paramName="CompliancyTestFormAdmin" paramProperty="compliancyTest.compliancyTestId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="compliancyTestDelete" >
  <html:link forward="compliancyTestDelete" paramId="compliancyTest.compliancyTestId" paramName="CompliancyTestFormAdmin" paramProperty="compliancyTest.compliancyTestId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
