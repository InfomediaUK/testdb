<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.shiftView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="ShiftFormAdmin" paramProperty="client.clientId"><bean:write name="ShiftFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="ShiftFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.site"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="siteView">
      <html:link forward="siteView" paramId="site.siteId" paramName="ShiftFormAdmin" paramProperty="site.siteId"><bean:write name="ShiftFormAdmin" property="site.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="siteView">
      <bean:write name="ShiftFormAdmin" property="site.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="site.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="site.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="site.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="site.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.location"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="locationView">
      <html:link forward="locationView" paramId="location.locationId" paramName="ShiftFormAdmin" paramProperty="location.locationId"><bean:write name="ShiftFormAdmin" property="location.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="locationView">
      <bean:write name="ShiftFormAdmin" property="location.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="location.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="location.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="location.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.shift"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startTime"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.startTime"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.endTime"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.endTime"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.breakStartTime"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.breakStartTime"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.breakEndTime"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.breakEndTime"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.noOfHours"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.noOfHours"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.breakNoOfHours"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.breakNoOfHours"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftFactor"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.upliftFactor"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftValue"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.upliftValue"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.useShiftUplift"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.useShiftUplift"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.overtimeNoOfHours"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.overtimeNoOfHours"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.overtimeUpliftFactor"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.overtimeUpliftFactor"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="shift.active"/></td>
  </tr>
</table>

<logic:equal name="ShiftFormAdmin" property="shift.active" value="true">
<mmj-admin:hasAccess forward="shiftEdit">
  <html:link forward="shiftEdit" paramId="shift.shiftId" paramName="ShiftFormAdmin" paramProperty="shift.shiftId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="shiftDelete">
  <html:link forward="shiftDelete" paramId="shift.shiftId" paramName="ShiftFormAdmin" paramProperty="shift.shiftId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
