<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.passportTypeView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="PassportTypeFormAdmin" property="passportType.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="PassportTypeFormAdmin" property="passportType.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="PassportTypeFormAdmin" property="passportType.displayOrder"/></td>
  </tr>
</table>

<mmj-admin:hasAccess forward="passportTypeEdit" >
  <html:link forward="passportTypeEdit" paramId="passportType.passportTypeId" paramName="PassportTypeFormAdmin" paramProperty="passportType.passportTypeId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="passportTypeDelete" >
  <html:link forward="passportTypeDelete" paramId="passportType.passportTypeId" paramName="PassportTypeFormAdmin" paramProperty="passportType.passportTypeId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
