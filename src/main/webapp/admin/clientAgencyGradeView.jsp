<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.clientAgencyGradeView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><bean:write name="ClientAgencyGradeViewFormAdmin" property="clientAgencyGrade.rate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyGradeViewFormAdmin" property="clientAgencyGrade.active"/></td>
  </tr>
</table>

<logic:equal name="ClientAgencyGradeViewFormAdmin" property="clientAgencyGrade.active" value="true">
<mmj-admin:hasAccess forward="clientAgencyGradeEdit" >
  <html:link forward="clientAgencyGradeEdit" paramId="clientAgencyGrade.clientAgencyGradeId" paramName="ClientAgencyGradeViewFormAdmin" paramProperty="clientAgencyGrade.clientAgencyGradeId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>
