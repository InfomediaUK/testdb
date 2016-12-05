<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.budgetTransactionNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="budgetTransactionNewProcess.do" focus="budgetTransaction.locationJobProfileId">

<html:hidden property="client.clientId" /> <%-- to preserve value --%>
<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="BudgetTransactionFormAdmin" property="client.name"/> (<bean:write name="BudgetTransactionFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.locationJobProfile"/></td>
    <td align="left">
      <html:select property="budgetTransaction.locationJobProfileId" >
        <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
        <bean:define id="locationJobProfileUsers" name="BudgetTransactionFormAdmin" property="locationJobProfileUsers"/>
        <html:options collection="locationJobProfileUsers" labelProperty="locationJobProfileName" property="locationJobProfileId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.value"/></td>
    <td align="left"><html:text property="budgetTransaction.value"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.vatValue"/></td>
    <td align="left"><html:text property="budgetTransaction.vatValue"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.expenseValue"/></td>
    <td align="left"><html:text property="budgetTransaction.expenseValue"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.nonPayValue"/></td>
    <td align="left"><html:text property="budgetTransaction.nonPayValue"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.comment"/></td>
    <td align="left"><html:text property="budgetTransaction.comment"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>

