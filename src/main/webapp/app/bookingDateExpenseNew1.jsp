<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ 
taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ 
taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="BookingDateExpenseFormApp" property="list">
<br/>
<br/>
<bean:message key="error.noExpenses"/>
</logic:empty>
<logic:notEmpty name="BookingDateExpenseFormApp" property="list" >
<html:form action="/bookingDateExpenseNew2.do" focus="exp0" onsubmit="return singleSubmit();">
<html:hidden property="page" value="1"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingDateExpenseNewStep1"/>&nbsp;-&nbsp;<bean:message key="title.bookingDateExpenseNew1"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="bookingDateExpenseNewButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<table class="radio">
<logic:iterate name="BookingDateExpenseFormApp" property="list" id="bookingExpense" indexId="bookingExpenseIndex">
  <tr>
    <td align="left">
      <html:radio tabindex="1" property="bookingExpense.bookingExpenseId" idName="bookingExpense" value="bookingExpenseId" styleId='<%= "exp" + bookingExpenseIndex %>' />
    </td>
    <label for="<%= "exp" + bookingExpenseIndex %>">
    <td align="left">
      <bean:write name="bookingExpense" property="expenseName"/>
      <logic:notEmpty name="bookingExpense" property="expenseDescription">
      (<bean:write name="bookingExpense" property="expenseDescription"/>)
      </logic:notEmpty>
    </td>
    </label>
  </tr>
</logic:iterate>
</table>
</html:form>
<pre>
<bean:write name="BookingDateExpenseFormApp" property="bookingExpensesText" />
</pre>
</logic:notEmpty>
