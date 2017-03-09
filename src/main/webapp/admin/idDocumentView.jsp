<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.idDocumentView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="IdDocumentFormAdmin" property="idDocument.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="IdDocumentFormAdmin" property="idDocument.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.idDocumentType"/></td>
    <td align="left">
<logic:equal name="IdDocumentFormAdmin" property="idDocument.idDocumentType" value="0">
       <bean:message key="label.passport"/>
</logic:equal>
<logic:equal name="IdDocumentFormAdmin" property="idDocument.idDocumentType" value="1">
       <bean:message key="label.idCard"/>
</logic:equal>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.requiresVisa"/></td>
    <td align="left">
<logic:equal name="IdDocumentFormAdmin" property="idDocument.requiresVisa" value="true">
      <bean:message key="label.yes"/>
</logic:equal>
<logic:notEqual name="IdDocumentFormAdmin" property="idDocument.requiresVisa" value="true">
      <bean:message key="label.no"/>
</logic:notEqual>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="IdDocumentFormAdmin" property="idDocument.displayOrder"/></td>
  </tr>
</table>

<mmj-admin:hasAccess forward="idDocumentEdit" >
  <html:link forward="idDocumentEdit" paramId="idDocument.idDocumentId" paramName="IdDocumentFormAdmin" paramProperty="idDocument.idDocumentId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="idDocumentDelete" >
  <html:link forward="idDocumentDelete" paramId="idDocument.idDocumentId" paramName="IdDocumentFormAdmin" paramProperty="idDocument.idDocumentId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
