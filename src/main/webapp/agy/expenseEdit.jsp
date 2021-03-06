<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/expenseEditProcess.do" focus="expense.name" onsubmit="return singleSubmit();">
<html:hidden property="expense.locationId"/>
<html:hidden property="expense.expenseId"/>
<html:hidden property="expense.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.expenseEdit"/>
		</td>
    <mmj-agy:hasAccess forward="expenseEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><html:text property="expense.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.description"/></th>
    <td align="left"><html:text property="expense.description"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><html:text property="expense.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.multiplier"/></th>
    <td align="left"><html:text property="multiplierStr"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.vatRate"/></th>
    <td align="left"><html:text property="vatRateStr"/></td>
  </tr>
</html:form>
</table>
