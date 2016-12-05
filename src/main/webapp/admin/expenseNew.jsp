<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.expenseNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="expenseNewProcess.do" focus="expense.name">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="site.name" /> <%-- to preserve value --%>
<html:hidden property="site.countryName" /> <%-- to preserve value --%>
<html:hidden property="location.name" /> <%-- to preserve value --%>

<html:hidden property="expense.locationId" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="client.name"/> (<bean:write name="ExpenseFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.site"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="site.name"/> (<bean:write name="ExpenseFormAdmin" property="site.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.location"/></td>
    <td align="left"><bean:write name="ExpenseFormAdmin" property="location.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="expense.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><html:text property="expense.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="expense.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.multiplier"/></td>
    <td align="left"><html:text property="multiplierStr"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.vatRate"/></td>
    <td align="left"><html:text property="vatRateStr"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>

