<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.expenseView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="ExpenseFormAdmin" paramProperty="client.clientId"><bean:write name="ExpenseFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="ExpenseFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.site"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="siteView">
      <html:link forward="siteView" paramId="site.siteId" paramName="ExpenseFormAdmin" paramProperty="site.siteId"><bean:write name="ExpenseFormAdmin" property="site.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="siteView">
      <bean:write name="ExpenseFormAdmin" property="site.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="site.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="site.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="site.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="site.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.location"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="locationView">
      <html:link forward="locationView" paramId="location.locationId" paramName="ExpenseFormAdmin" paramProperty="location.locationId"><bean:write name="ExpenseFormAdmin" property="location.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="locationView">
      <bean:write name="ExpenseFormAdmin" property="location.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="location.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="location.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="location.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.expense"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="expense.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="expense.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="expense.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.multiplier"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="expense.multiplier"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.vatRate"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="expense.vatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="expense.active"/></td>
  </tr>
</table>

<logic:equal name="ExpenseFormAdmin" property="expense.active" value="true">
<mmj-admin:hasAccess forward="expenseEdit">
  <html:link forward="expenseEdit" paramId="expense.expenseId" paramName="ExpenseFormAdmin" paramProperty="expense.expenseId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="expenseDelete">
  <html:link forward="expenseDelete" paramId="expense.expenseId" paramName="ExpenseFormAdmin" paramProperty="expense.expenseId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>
