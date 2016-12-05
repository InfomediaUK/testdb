<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.publicHolidayView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="PublicHolidayFormAdmin" paramProperty="client.clientId"><bean:write name="PublicHolidayFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="PublicHolidayFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.publicHoliday"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="publicHoliday.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.date"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="publicHoliday.phDate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="publicHoliday.active"/></td>
  </tr>
</table>

<logic:equal name="PublicHolidayFormAdmin" property="publicHoliday.active" value="true">
<mmj-admin:hasAccess forward="publicHolidayEdit">
  <html:link forward="publicHolidayEdit" paramId="publicHoliday.publicHolidayId" paramName="PublicHolidayFormAdmin" paramProperty="publicHoliday.publicHolidayId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="publicHolidayDelete">
  <html:link forward="publicHolidayDelete" paramId="publicHoliday.publicHolidayId" paramName="PublicHolidayFormAdmin" paramProperty="publicHoliday.publicHolidayId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>
