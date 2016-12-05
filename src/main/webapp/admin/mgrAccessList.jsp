<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.mgrAccessList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="mgrAccessNew">
<html:link forward="mgrAccessNew"><bean:message key="link.new"/></html:link>
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
  <logic:iterate id="mgrAccess" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="mgrAccess"/>
	<logic:notEqual name="mgrAccess" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="mgrAccessView">
      <html:link forward="mgrAccessView" paramId="mgrAccess.mgrAccessId" paramName="mgrAccess" paramProperty="mgrAccessId"><bean:write name="mgrAccess" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="mgrAccessView">
      <bean:write name="mgrAccess" property="name"/>
    </mmj-admin:hasNoAccess>   
    </td>
    <td align="left">
      <bean:write name="mgrAccess" property="description"/>
    </td>
    <td align="left">
      <bean:write name="mgrAccess" property="startsWith"/>
    </td>
    <td align="left">
      <bean:write name="mgrAccess" property="global"/>
    </td>
  </tr>
</logic:iterate>
</table>
</logic:present>
