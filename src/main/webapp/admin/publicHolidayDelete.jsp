<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.publicHolidayDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="publicHolidayDeleteProcess.do">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="publicHoliday.clientId" />

<html:hidden property="publicHoliday.publicHolidayId"/>
<html:hidden property="publicHoliday.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="client.name"/> (<bean:write name="PublicHolidayFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="publicHoliday.name" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.date"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="publicHoliday.phDate" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="publicHoliday.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="publicHoliday.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
