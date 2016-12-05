<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.upliftDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="upliftDeleteProcess.do">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="uplift.clientId" />

<html:hidden property="uplift.upliftId"/>
<html:hidden property="uplift.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="client.name"/> (<bean:write name="UpliftFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftDay"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftDay" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftHour"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftHour" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftFactor"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftFactor" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftValue"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftValue" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
