<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.idDocumentList"/>

<br/>
<br/>
<%-- --%>
<mmj-admin:hasAccess forward="idDocumentNew" >
<html:link forward="idDocumentNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>
<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.idDocumentType" /></th>
    <th align="left"><bean:message key="label.requiresVisa" /></th>
    <th align="left"><bean:message key="label.active" /></th>
    <th align="left"><bean:message key="label.order" /></th>
  </tr>
  </thead>
  <logic:iterate id="idDocument" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="idDocument"/>
	<logic:notEqual name="idDocument" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="idDocumentView" >
      <html:link forward="idDocumentView" paramId="idDocument.idDocumentId" paramName="idDocument" paramProperty="idDocumentId"><bean:write name="idDocument" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="idDocumentView" >
      <bean:write name="idDocument" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="idDocument" property="code"/>
    </td>
    <td align="left">
<logic:equal name="idDocument" property="idDocumentType" value="0">
       <bean:message key="label.passport"/>
</logic:equal>
<logic:equal name="idDocument" property="idDocumentType" value="1">
       <bean:message key="label.idCard"/>
</logic:equal>
    </td>
    <td align="left">
<logic:equal name="idDocument" property="requiresVisa" value="true">
       <bean:message key="label.yes"/>
</logic:equal>
<logic:equal name="idDocument" property="requiresVisa" value="false">
       <bean:message key="label.no"/>
</logic:equal>
    </td>
    <td align="left">
      <bean:write name="idDocument" property="active"/>
    </td>    
    <td align="left">
      <bean:write name="idDocument" property="displayOrder"/>
    </td>    
  </tr>
  </logic:iterate>
</table>
</logic:present>

