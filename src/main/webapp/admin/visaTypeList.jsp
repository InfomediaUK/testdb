<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.visaTypeList"/>

<br/>
<br/>
<%-- --%>
<mmj-admin:hasAccess forward="visaTypeNew" >
<html:link forward="visaTypeNew"><bean:message key="link.new"/></html:link>
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
  <logic:iterate id="visaType" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="visaType"/>
	<logic:notEqual name="visaType" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="visaTypeView" >
      <html:link forward="visaTypeView" paramId="visaType.visaTypeId" paramName="visaType" paramProperty="visaTypeId"><bean:write name="visaType" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="visaTypeView" >
      <bean:write name="visaType" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="visaType" property="code"/>
    </td>
    <td align="left">
      <bean:write name="visaType" property="active"/>
    </td>    
    <td align="left">
      <bean:write name="visaType" property="displayOrder"/>
    </td>    
  </tr>
  </logic:iterate>
</table>
</logic:present>

