<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.reasonForRequestView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="ReasonForRequestFormAdmin" paramProperty="client.clientId"><bean:write name="ReasonForRequestFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="JobFamilyViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ReasonForRequestFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ReasonForRequestFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ReasonForRequestFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ReasonForRequestFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.reasonForRequest"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="ReasonForRequestFormAdmin" property="reasonForRequest.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ReasonForRequestFormAdmin" property="reasonForRequest.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ReasonForRequestFormAdmin" property="reasonForRequest.active"/></td>
  </tr>
</table>

<logic:equal name="ReasonForRequestFormAdmin" property="reasonForRequest.active" value="true">
<mmj-admin:hasAccess forward="reasonForRequestEdit">
  <html:link forward="reasonForRequestEdit" paramId="reasonForRequest.reasonForRequestId" paramName="ReasonForRequestFormAdmin" paramProperty="reasonForRequest.reasonForRequestId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="reasonForRequestDelete">
  <html:link forward="reasonForRequestDelete" paramId="reasonForRequest.reasonForRequestId" paramName="ReasonForRequestFormAdmin" paramProperty="reasonForRequest.reasonForRequestId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>
