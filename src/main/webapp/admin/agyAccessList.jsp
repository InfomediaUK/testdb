<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.agyAccessList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="agyAccessNew">
<html:link forward="agyAccessNew"><bean:message key="link.new"/></html:link>
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
  <logic:iterate id="agyAccess" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="agyAccess"/>
	<logic:notEqual name="agyAccess" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="agyAccessView">
      <html:link forward="agyAccessView" paramId="agyAccess.agyAccessId" paramName="agyAccess" paramProperty="agyAccessId"><bean:write name="agyAccess" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="agyAccessView">
      <bean:write name="agyAccess" property="name"/>
    </mmj-admin:hasNoAccess>   
    </td>
    <td align="left">
      <bean:write name="agyAccess" property="description"/>
    </td>
    <td align="left">
      <bean:write name="agyAccess" property="startsWith"/>
    </td>
    <td align="left">
      <bean:write name="agyAccess" property="global"/>
    </td>
  </tr>
</logic:iterate>
</table>
</logic:present>
