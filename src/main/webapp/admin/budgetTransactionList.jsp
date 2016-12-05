<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.budgetTransactionList"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="ClientListFormAdmin" paramProperty="client.clientId"><bean:write name="ClientListFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="ClientListFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ClientListFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ClientListFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientListFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientListFormAdmin" property="client.active"/></td>
  </tr>
</table>

<br/>

<bean:message key="title.budgetTransactionList"/>&nbsp;
<mmj-admin:hasAccess forward="budgetTransactionNew">
<html:link forward="budgetTransactionNew" paramId="client.clientId" paramName="ClientListFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>

<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.creationTimestamp" /></th>
    <th align="left"><bean:message key="label.location" /></th>
    <th align="left"><bean:message key="label.jobProfile" /></th>
    <th align="left"><bean:message key="label.value" /></th>
    <th align="left"><bean:message key="label.comment" /></th>
  </tr>
  </thead>
	<logic:iterate id="budgetTransaction" name="ClientListFormAdmin" property="list">
	<bean:define id="trClass" value="budgetTransaction"/>
	<logic:notEqual name="budgetTransaction" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
      <bean:write name="budgetTransaction" property="creationTimestamp"/>
    </td>
    <td align="left">
    <mmj-admin:hasAccess forward="locationView">
      <html:link forward="locationView" paramId="location.locationId" paramName="budgetTransaction" paramProperty="locationId"><bean:write name="budgetTransaction" property="locationName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="locationView">
      <bean:write name="budgetTransaction" property="locationName"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobProfileView">
      <html:link forward="jobProfileView" paramId="jobProfile.jobProfileId" paramName="budgetTransaction" paramProperty="jobProfileId"><bean:write name="budgetTransaction" property="jobProfileName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobProfileView">
      <bean:write name="budgetTransaction" property="jobProfileName"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="right">
    <mmj-admin:hasAccess forward="budgetTransactionView">
      <html:link forward="budgetTransactionView" paramId="budgetTransaction.budgetTransactionId" paramName="budgetTransaction" paramProperty="budgetTransactionId"><bean:write name="budgetTransaction" property="value" format="#,##0.00"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="budgetTransactionView">
      <bean:write name="budgetTransaction" property="value" format="#,##0.00"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="budgetTransaction" property="comment"/>
    </td>
  </tr>
  </logic:iterate>
</table>
