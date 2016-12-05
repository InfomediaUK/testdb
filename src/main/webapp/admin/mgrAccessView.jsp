<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.mgrAccessView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startsWith"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.startsWith"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.global"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.global"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.active"/></td>
  </tr>
</table>

<logic:equal name="MgrAccessFormAdmin" property="mgrAccess.active" value="true">
<mmj-admin:hasAccess forward="mgrAccessEdit">
  <html:link forward="mgrAccessEdit" paramId="mgrAccess.mgrAccessId" paramName="MgrAccessFormAdmin" paramProperty="mgrAccess.mgrAccessId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="mgrAccessDelete">
  <html:link forward="mgrAccessDelete" paramId="mgrAccess.mgrAccessId" paramName="MgrAccessFormAdmin" paramProperty="mgrAccess.mgrAccessId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>
