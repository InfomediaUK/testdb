<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.agyAccessView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AgyAccessFormAdmin" property="agyAccess.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="AgyAccessFormAdmin" property="agyAccess.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startsWith"/></td>
    <td align="left"><bean:write name="AgyAccessFormAdmin" property="agyAccess.startsWith"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.global"/></td>
    <td align="left"><bean:write name="AgyAccessFormAdmin" property="agyAccess.global"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AgyAccessFormAdmin" property="agyAccess.active"/></td>
  </tr>
</table>

<logic:equal name="AgyAccessFormAdmin" property="agyAccess.active" value="true">
<mmj-admin:hasAccess forward="agyAccessEdit">
  <html:link forward="agyAccessEdit" paramId="agyAccess.agyAccessId" paramName="AgyAccessFormAdmin" paramProperty="agyAccess.agyAccessId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="agyAccessDelete">
  <html:link forward="agyAccessDelete" paramId="agyAccess.agyAccessId" paramName="AgyAccessFormAdmin" paramProperty="agyAccess.agyAccessId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>
