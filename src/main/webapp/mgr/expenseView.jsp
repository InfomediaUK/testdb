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
<bean:message key="title.expenseView"/>
		</td>
<mmj-mgr:hasAccess forward="expenseEdit">
    <html:form action="/expenseEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="expense.expenseId" value="<bean:write name="ExpenseFormMgr" property="expense.expenseId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="expenseDelete">
    <html:form action="/expenseDelete.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="expense.expenseId" value="<bean:write name="ExpenseFormMgr" property="expense.expenseId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
    <html:form action="/expenseView.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="expense.locationId" value="<bean:write name="ExpenseFormMgr" property="expense.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><bean:write name="ExpenseFormMgr" property="expense.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.description"/></th>
    <td align="left"><bean:write name="ExpenseFormMgr" property="expense.description"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><bean:write name="ExpenseFormMgr" property="expense.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.multiplier"/></th>
    <td align="left"><bean:write name="ExpenseFormMgr" property="multiplierStr"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.vatRate"/></th>
    <td align="left"><bean:write name="ExpenseFormMgr" property="vatRateStr"/></td>
  </tr>
</table>
