<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.budgetTransactionList"/>
		</td>
  </tr>
</table>

<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.location"/>
    </th>
    <td align="left" width="75%">
      <bean:write name="BudgetTransactionListFormMgr" property="locationJobProfile.locationName"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
      <bean:write name="BudgetTransactionListFormMgr" property="locationJobProfile.jobProfileName"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.budget"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BudgetTransactionListFormMgr" property="locationJobProfile.budget" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.vatBudget"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BudgetTransactionListFormMgr" property="locationJobProfile.vatBudget" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.expenseBudget"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BudgetTransactionListFormMgr" property="locationJobProfile.expenseBudget" format="#,##0.00"/>
    </td>
  </tr>
<%--
  <tr>
    <th align="left">
      <bean:message key="label.nonPayBudget"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BudgetTransactionListFormMgr" property="locationJobProfile.nonPayBudget" format="#,##0.00"/>
    </td>
  </tr>
--%>
</table>

<br/>

<bean:parameter id="tab" name="tab" value="pay"/>

<logic:notPresent name="BudgetTransactionListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="BudgetTransactionListFormMgr" property="list">
<logic:empty name="BudgetTransactionListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="BudgetTransactionListFormMgr" property="list">
<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="pay"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
		  <html:link forward="budgetTransactionListPay" paramId="locationJobProfile.locationJobProfileId" paramName="BudgetTransactionListFormMgr" paramProperty="locationJobProfile.locationJobProfileId">
		  <bean:message key="label.budget"/>
		  </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="vat"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
		  <html:link forward="budgetTransactionListVat" paramId="locationJobProfile.locationJobProfileId" paramName="BudgetTransactionListFormMgr" paramProperty="locationJobProfile.locationJobProfileId">
		  <bean:message key="label.vat"/>
		  </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="expense"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
		  <html:link forward="budgetTransactionListExpense" paramId="locationJobProfile.locationJobProfileId" paramName="BudgetTransactionListFormMgr" paramProperty="locationJobProfile.locationJobProfileId">
		  <bean:message key="label.expense"/>
		  </html:link>
    </td>
    <td align="center" width="25%">
      &nbsp;
    </td>
  </tr>
</table>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.created"/>
    </th>
    <th align="right" width="10%">
      <bean:message key="label.in"/>
    </th>
    <th align="right" width="10%">
      <bean:message key="label.out"/>
    </th>
    <th align="right" width="15%">
			<bean:message key="label.balance"/>
    </th>
    <th align="left" width="40%">
      <bean:message key="label.comment"/>
    </th>
  </tr>
  <logic:iterate id="budgetTransaction" name="BudgetTransactionListFormMgr" property="list" indexId="budgetTransactionIndex" type="com.helmet.bean.BudgetTransaction">
  <logic:equal name="tab" value="pay">
    <logic:notEqual name="budgetTransaction" property="value" value="0">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
      <bean:write name="budgetTransaction" property="creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
    <td align="right">
    <logic:lessThan name="budgetTransaction" property="value" value="0">
      &nbsp;
    </logic:lessThan>
    <logic:greaterThan name="budgetTransaction" property="value" value="0">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="value" format="#,##0.00"/>
    </logic:greaterThan>        
    </td>
    <td align="right">
    <logic:lessThan name="budgetTransaction" property="value" value="0">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="value" format="#,##0.00"/>
    </logic:lessThan>
    <logic:greaterThan name="budgetTransaction" property="value" value="0">
      &nbsp;
    </logic:greaterThan>        
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="balance" format="#,##0.00"/>
    </td>
    <td align="left">
      <bean:write name="budgetTransaction" property="comment"/>
    </td>
  </tr> 
    </logic:notEqual>
  </logic:equal>

  <logic:equal name="tab" value="vat">
    <logic:notEqual name="budgetTransaction" property="vatValue" value="0">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
      <bean:write name="budgetTransaction" property="creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
    <td align="right">
    <logic:lessThan name="budgetTransaction" property="vatValue" value="0">
      &nbsp;
    </logic:lessThan>
    <logic:greaterThan name="budgetTransaction" property="vatValue" value="0">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="vatValue" format="#,##0.00"/>
    </logic:greaterThan>        
    </td>
    <td align="right">
    <logic:lessThan name="budgetTransaction" property="vatValue" value="0">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="vatValue" format="#,##0.00"/>
    </logic:lessThan>
    <logic:greaterThan name="budgetTransaction" property="vatValue" value="0">
      &nbsp;
    </logic:greaterThan>        
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="vatBalance" format="#,##0.00"/>
    </td>
    <td align="left">
      <bean:write name="budgetTransaction" property="comment"/>
    </td>
  </tr> 
    </logic:notEqual>
  </logic:equal>

  <logic:equal name="tab" value="expense">
    <logic:notEqual name="budgetTransaction" property="expenseValue" value="0">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
      <bean:write name="budgetTransaction" property="creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
    <td align="right">
    <logic:lessThan name="budgetTransaction" property="expenseValue" value="0">
      &nbsp;
    </logic:lessThan>
    <logic:greaterThan name="budgetTransaction" property="expenseValue" value="0">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="expenseValue" format="#,##0.00"/>
    </logic:greaterThan>        
    </td>
    <td align="right">
    <logic:lessThan name="budgetTransaction" property="expenseValue" value="0">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="expenseValue" format="#,##0.00"/>
    </logic:lessThan>
    <logic:greaterThan name="budgetTransaction" property="expenseValue" value="0">
      &nbsp;
    </logic:greaterThan>        
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="expenseBalance" format="#,##0.00"/>
    </td>
    <td align="left">
      <bean:write name="budgetTransaction" property="comment"/>
    </td>
  </tr> 
    </logic:notEqual>
  </logic:equal>

  <logic:equal name="tab" value="nonPay">
    <logic:notEqual name="budgetTransaction" property="nonPayValue" value="0">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
      <bean:write name="budgetTransaction" property="creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
    <td align="right">
    <logic:lessThan name="budgetTransaction" property="nonPayValue" value="0">
      &nbsp;
    </logic:lessThan>
    <logic:greaterThan name="budgetTransaction" property="nonPayValue" value="0">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="nonPayValue" format="#,##0.00"/>
    </logic:greaterThan>        
    </td>
    <td align="right">
    <logic:lessThan name="budgetTransaction" property="nonPayValue" value="0">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="nonPayValue" format="#,##0.00"/>
    </logic:lessThan>
    <logic:greaterThan name="budgetTransaction" property="nonPayValue" value="0">
      &nbsp;
    </logic:greaterThan>        
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="budgetTransaction" property="nonPayBalance" format="#,##0.00"/>
    </td>
    <td align="left">
      <bean:write name="budgetTransaction" property="comment"/>
    </td>
  </tr> 
    </logic:notEqual>
  </logic:equal>

  </logic:iterate>
</table> 
</logic:notEmpty> 
</logic:present>

<mmj-mgr:hasAccess forward="budgetTransactionNew">
  <html:form action="budgetTransactionNew.do" method="get">
    <html:hidden name="BudgetTransactionListFormMgr" property="tab"/>
    <html:hidden name="BudgetTransactionListFormMgr" property="locationJobProfile.locationJobProfileId"/>
    <html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit>
  </html:form>
</mmj-mgr:hasAccess>



