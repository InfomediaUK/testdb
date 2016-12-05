<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.adminAccessView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startsWith"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.startsWith"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.global"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.global"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.active"/></td>
  </tr>
</table>

<logic:equal name="AdminAccessFormAdmin" property="adminAccess.active" value="true">
<mmj-admin:hasAccess forward="adminAccessEdit">
  <html:link forward="adminAccessEdit" paramId="adminAccess.adminAccessId" paramName="AdminAccessFormAdmin" paramProperty="adminAccess.adminAccessId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="adminAccessDelete">
  <html:link forward="adminAccessDelete" paramId="adminAccess.adminAccessId" paramName="AdminAccessFormAdmin" paramProperty="adminAccess.adminAccessId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>
