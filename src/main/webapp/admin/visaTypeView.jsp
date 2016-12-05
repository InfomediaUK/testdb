<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.visaTypeView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="VisaTypeFormAdmin" property="visaType.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="VisaTypeFormAdmin" property="visaType.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="VisaTypeFormAdmin" property="visaType.displayOrder"/></td>
  </tr>
</table>

<mmj-admin:hasAccess forward="visaTypeEdit" >
  <html:link forward="visaTypeEdit" paramId="visaType.visaTypeId" paramName="VisaTypeFormAdmin" paramProperty="visaType.visaTypeId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="visaTypeDelete" >
  <html:link forward="visaTypeDelete" paramId="visaType.visaTypeId" paramName="VisaTypeFormAdmin" paramProperty="visaType.visaTypeId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
