<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.emailActionList"/>

<br/>
<b><bean:message key="warning.emailNotifications.changes"/></b>
<br/>

<mmj-admin:hasAccess forward="emailActionNew" >
<html:link forward="emailActionNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>

<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.subject" /></th>
    <th align="left"><bean:message key="label.textTemplate" /></th>
    <th align="left"><bean:message key="label.htmlTemplate" /></th>
    <th align="left"><bean:message key="label.dependsOn" /></th>
    <th align="left"><bean:message key="label.order" /></th>
  </tr>
  </thead>
  <logic:iterate id="emailAction" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="emailAction"/>
	<logic:notEqual name="emailAction" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="emailActionView" >
      <html:link forward="emailActionView" paramId="emailAction.emailActionId" paramName="emailAction" paramProperty="emailActionId"><bean:write name="emailAction" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="emailActionView" >
      <bean:write name="emailAction" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="emailAction" property="subject"/>
    </td>    
    <td align="left">
      <bean:write name="emailAction" property="textTemplate"/>
    </td>    
    <td align="left">
      <bean:write name="emailAction" property="htmlTemplate"/>
    </td>    
    <td align="left">
      <bean:write name="emailAction" property="dependsOn"/>
    </td>    
    <td align="left">
      <bean:write name="emailAction" property="displayOrder"/>
    </td>    
  </tr>
  </logic:iterate>
</table>
</logic:present>

