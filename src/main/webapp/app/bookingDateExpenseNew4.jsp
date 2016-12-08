<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="BookingDateExpenseFormApp" property="list">
<br/>
<br/>
<bean:message key="error.noExpense"/>
</logic:empty>
<logic:notEmpty name="BookingDateExpenseFormApp" property="list" >
<html:form action="/bookingDateExpenseNewFinish.do" onsubmit="return singleSubmit();">
<html:hidden property="page" value="4"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingDateExpenseNewStep4"/>&nbsp;-&nbsp;<bean:message key="title.bookingDateExpenseNew4"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="bookingDateExpenseNewButtons.jsp" flush="true" >
		    <jsp:param name="nextButtonKey" value="button.finish" />
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />

<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%">
      <bean:message key="label.expense"/>
    </th>
    <td align="left" width="65%">
      <bean:write name="BookingDateExpenseFormApp" property="bookingExpense.expenseName"/>
      -
      <bean:write name="BookingDateExpenseFormApp" property="bookingExpense.expenseCode"/>
      <logic:notEmpty name="BookingDateExpenseFormApp" property="bookingExpense.expenseDescription">
      -
      <bean:write name="BookingDateExpenseFormApp" property="bookingExpense.expenseDescription"/>
      </logic:notEmpty>
    </td>
  </tr>
<%--
  <tr>
    <th align="left"">
      <bean:message key="label.multiplier"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpenseFormApp" property="bookingExpense.expenseMultiplier" format="#0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"">
      <bean:message key="label.vatRate"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpenseFormApp" property="bookingExpense.expenseVatRate" format="#0.00"/>%
    </td>
  </tr>
--%>
  <logic:equal name="BookingDateExpenseFormApp" property="bookingExpense.isMultiplier" value="true">
  <tr>
    <th align="left"">
      <bean:message key="label.qty"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpenseFormApp" property="qty" format="#0.00"/>
      @
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateExpenseFormApp" property="bookingExpense.expenseMultiplier" format="#0.00"/>
    </td>
  </tr>
  </logic:equal>
  <tr>
    <th align="left"">
      <bean:message key="label.value"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateExpenseFormApp" property="value" format="#0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"">
      <bean:message key="label.vatValue"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateExpenseFormApp" property="vatValue" format="#0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label" nowrap="true">
      <bean:message key="label.comment"/>&nbsp;
    </th>
    <td align="left">
      <pre><bean:write name="BookingDateExpenseFormApp" property="text"/></pre>
    </td>
  </tr>
  <logic:notEmpty name="BookingDateExpenseFormApp" property="filename" >
  <tr>
    <th align="left" class="label" nowrap="true">
      <bean:message key="label.uploadedReceipt"/>&nbsp;
    </th>
    <td align="left">
			<bean:define id="tempFileUrl" name="BookingDateExpenseFormApp" property="tempFileUrl" type="java.lang.String" />
			<html:link href="<%=tempFileUrl %>" target="_blank" titleKey="link.title.viewReceipt"><bean:message key="link.viewReceipt"/></html:link>
    </td>
  </tr>
  </logic:notEmpty>
</table>
</html:form>
</logic:notEmpty>
