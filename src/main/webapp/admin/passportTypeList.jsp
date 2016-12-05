<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.passportTypeList"/>

<br/>
<br/>
<%-- --%>
<mmj-admin:hasAccess forward="passportTypeNew" >
<html:link forward="passportTypeNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>
<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.active" /></th>
    <th align="left"><bean:message key="label.order" /></th>
  </tr>
  </thead>
  <logic:iterate id="passportType" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="passportType"/>
	<logic:notEqual name="passportType" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="passportTypeView" >
      <html:link forward="passportTypeView" paramId="passportType.passportTypeId" paramName="passportType" paramProperty="passportTypeId"><bean:write name="passportType" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="passportTypeView" >
      <bean:write name="passportType" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="passportType" property="code"/>
    </td>
    <td align="left">
      <bean:write name="passportType" property="active"/>
    </td>    
    <td align="left">
      <bean:write name="passportType" property="displayOrder"/>
    </td>    
  </tr>
  </logic:iterate>
</table>
</logic:present>

