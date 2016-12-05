<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.publicHolidayNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="publicHolidayNewProcess.do" focus="publicHoliday.name">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="publicHoliday.clientId" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="PublicHolidayFormAdmin" property="client.name"/> (<bean:write name="PublicHolidayFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="publicHoliday.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.date"/></td>
    <td align="left"><html:text property="phDate" styleId="calendar"/>&nbsp;<button id="trigger">...</button></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

<script type="text/javascript">//<![CDATA[
  Zapatec.Calendar.setup({
    firstDay          : 1,
    showOthers        : true,
    step              : 1,
    electric          : false,
    inputField        : "calendar",
    button            : "trigger",
    ifFormat          : "%Y-%m-%d",
    daFormat          : "%Y/%m/%d"
  });
//]]></script>

</html:form>

