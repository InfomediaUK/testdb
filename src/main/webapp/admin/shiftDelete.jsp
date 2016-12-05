<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.shiftDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="shiftDeleteProcess.do">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="site.name" /> <%-- to preserve value --%>
<html:hidden property="site.countryName" /> <%-- to preserve value --%>
<html:hidden property="location.name" /> <%-- to preserve value --%>

<html:hidden property="shift.locationId" />

<html:hidden property="shift.shiftId" />
<html:hidden property="shift.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="client.name"/> (<bean:write name="ShiftFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.site"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="site.name"/> (<bean:write name="ShiftFormAdmin" property="site.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.location"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="location.name"/></td>
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
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
