<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<logic:empty name="OrderStaffFormMgr" property="list">
<br/>
<br/>
<bean:message key="error.noExpenses"/>
</logic:empty>
<logic:notEmpty name="OrderStaffFormMgr" property="list">
<html:form action="/orderStaff9.do" focus="expense0" onsubmit="return singleSubmit();">
<html:hidden property="page" value="8"/>
<html:hidden property="selectedExpenses" value="0"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <logic:empty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.newBooking"/>
      </logic:empty>
      <logic:notEmpty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.editBooking"/>
      </logic:notEmpty>
      -&nbsp;<bean:message key="title.orderStaff8"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="orderStaffButtons.jsp" flush="true" />
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<logic:iterate id="expense" name="OrderStaffFormMgr" property="list" indexId="expenseIndex">
<html:multibox tabindex="1" property="selectedExpenses" styleId='<%= "expense" + expenseIndex %>'>
  <bean:write name="expense" property="expenseId"/>
</html:multibox>
<label for="<%= "expense" + expenseIndex %>">
<bean:write name="expense" property="name"/>
<logic:notEmpty name="expense" property="description">(<bean:write name="expense" property="description"/>)</logic:notEmpty>
</label>
<br/>
</logic:iterate>
<br/>
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" nowrap="true">
      <bean:message key="label.comment"/>&nbsp;
    </th>
    <td align="left" width="100%">
      <html:textarea tabindex="1" property="expensesText" cols="65" rows="3"/>
    </td>
  </tr>
</table>
</html:form>
</logic:notEmpty>