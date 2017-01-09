<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:define id="focusControl" value="valueStr" type="java.lang.String"/>
<logic:equal name="BudgetTransactionNewFormMgr" property="tab" value="pay">
  <bean:define id="focusControl" value="valueStr" type="java.lang.String"/>
</logic:equal>
<logic:equal name="BudgetTransactionNewFormMgr" property="tab" value="vat">
  <bean:define id="focusControl" value="vatValueStr" type="java.lang.String"/>
</logic:equal>
<logic:equal name="BudgetTransactionNewFormMgr" property="tab" value="expense">
  <bean:define id="focusControl" value="expenseValueStr" type="java.lang.String"/>
</logic:equal>
<logic:equal name="BudgetTransactionNewFormMgr" property="tab" value="nonPay">
  <bean:define id="focusControl" value="nonPayValueStr" type="java.lang.String"/>
</logic:equal>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/budgetTransactionNewProcess.do" focus="<%= focusControl %>" onsubmit="return singleSubmit();">
  <html:hidden property="locationJobProfile.locationJobProfileId"/>
  <html:hidden property="locationJobProfile.locationName"/>
  <html:hidden property="locationJobProfile.jobProfileName"/>
  <html:hidden property="locationJobProfile.budget"/>
  <html:hidden property="locationJobProfile.vatBudget"/>
  <html:hidden property="locationJobProfile.expenseBudget"/>
  <html:hidden property="locationJobProfile.nonPayBudget"/>
  <html:hidden property="tab"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.budgetTransactionNew"/>
		</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left">
      <bean:message key="label.location"/>
    </th>
    <td>
      <bean:write name="BudgetTransactionNewFormMgr" property="locationJobProfile.locationName"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.jobProfile"/>
    </th>
    <td>
      <bean:write name="BudgetTransactionNewFormMgr" property="locationJobProfile.jobProfileName"/>
    </td>
  </tr>
  <logic:equal name="BudgetTransactionNewFormMgr" property="tab" value="pay">
  <tr>
    <th align="left">
      <bean:message key="label.budget"/>
    </th>
    <td>
      <bean:message key="label.currencySymbol"/><bean:write name="BudgetTransactionNewFormMgr" property="locationJobProfile.budget" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.value"/>
    </th>
    <td>
      <html:text property="valueStr"/>
    </td>
  </tr>
  </logic:equal>
  <logic:equal name="BudgetTransactionNewFormMgr" property="tab" value="vat">
  <tr>
    <th align="left">
      <bean:message key="label.vatBudget"/>
    </th>
    <td>
      <bean:message key="label.currencySymbol"/><bean:write name="BudgetTransactionNewFormMgr" property="locationJobProfile.vatBudget" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.vatValue"/>
    </th>
    <td>
      <html:text property="vatValueStr"/>
    </td>
  </tr>
  </logic:equal>
  <logic:equal name="BudgetTransactionNewFormMgr" property="tab" value="expense">
  <tr>
    <th align="left">
      <bean:message key="label.expenseBudget"/>
    </th>
    <td>
      <bean:message key="label.currencySymbol"/><bean:write name="BudgetTransactionNewFormMgr" property="locationJobProfile.expenseBudget" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.expenseValue"/>
    </th>
    <td>
      <html:text property="expenseValueStr"/>
    </td>
  </tr>
  </logic:equal>
  <logic:equal name="BudgetTransactionNewFormMgr" property="tab" value="nonPay">
  <tr>
    <th align="left">
      <bean:message key="label.nonPayBudget"/>
    </th>
    <td>
      <bean:message key="label.currencySymbol"/><bean:write name="BudgetTransactionNewFormMgr" property="locationJobProfile.nonPayBudget" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.nonPayValue"/>
    </th>
    <td>
      <html:text property="nonPayValueStr"/>
    </td>
  </tr>
  </logic:equal>
  <tr>
    <th align="left">
      <bean:message key="label.comment"/>
    </th>
    <td>
      <html:text property="budgetTransaction.comment" size="50" maxlength="100"/>
    </td>
  </tr>
</html:form>
</table>
