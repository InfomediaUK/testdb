<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.adminAccessList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="adminAccessNew">
<html:link forward="adminAccessNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>

<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.description" /></th>
    <th align="left"><bean:message key="label.startsWith" /></th>
    <th align="left"><bean:message key="label.global" /></th>
  </tr>
  </thead>
  <logic:iterate id="adminAccess" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="adminAccess"/>
	<logic:notEqual name="adminAccess" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="adminAccessView">
      <html:link forward="adminAccessView" paramId="adminAccess.adminAccessId" paramName="adminAccess" paramProperty="adminAccessId"><bean:write name="adminAccess" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="adminAccessView">
      <bean:write name="adminAccess" property="name"/>
    </mmj-admin:hasNoAccess>   
    </td>
    <td align="left">
      <bean:write name="adminAccess" property="description"/>
    </td>
    <td align="left">
      <bean:write name="adminAccess" property="startsWith"/>
    </td>
    <td align="left">
      <bean:write name="adminAccess" property="global"/>
    </td>
  </tr>
</logic:iterate>
</table>
</logic:present>
