<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<logic:empty name="BookingEditExpensesFormMgr" property="list">
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <td align="left" valign="middle" class="title">
<bean:message key="title.bookingEditExpenses"/>
	</td>
  </tr>
</table>
<bean:message key="error.noExpenses"/>
</logic:empty>
<logic:notEmpty name="BookingEditExpensesFormMgr" property="list">
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/bookingEditExpensesProcess.do" focus="expense0" onsubmit="return singleSubmit();">
<html:hidden property="booking.bookingId" />
<html:hidden property="booking.noOfChanges" />
<html:hidden property="selectedExpenses" value="0"/>
  <tr>
    <td align="left" valign="middle" class="title">
<bean:message key="title.bookingEditExpenses"/>
	</td>
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit>
    </td>
  </tr>
</table>
<html:errors />
<table class="simple" width="100%">
  <tr>
    <td colspan="2">
<logic:iterate id="expense" name="BookingEditExpensesFormMgr" property="list" indexId="expenseIndex">
<html:multibox tabindex="1" property="selectedExpenses" styleId='<%= "expense" + expenseIndex %>'>
  <bean:write name="expense" property="expenseId"/>
</html:multibox>
<label for="<%= "expense" + expenseIndex %>">
<bean:write name="expense" property="name"/>
<logic:notEmpty name="expense" property="description">(<bean:write name="expense" property="description"/>)</logic:notEmpty>
</label>
<br/>
</logic:iterate>
    </td>
  </tr>
  <tr>
    <th align="left" class="label" nowrap="true">
      <bean:message key="label.comment"/>&nbsp;
    </th>
    <td align="left" width="100%">
      <html:textarea tabindex="1" property="booking.expensesText" cols="65" rows="3"/>
    </td>
  </tr>
</table>
</html:form>
</logic:notEmpty>
