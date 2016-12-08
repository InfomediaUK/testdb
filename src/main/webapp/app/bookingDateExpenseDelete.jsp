<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="bookingDateExpenseDeleteProcess.do" onsubmit="return singleSubmit();">
<html:hidden property="bookingDateExpense.bookingDateExpenseId" />
<html:hidden property="bookingDateExpense.noOfChanges" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.bookingDateExpenseDelete"/>
		</td>
    <td align="right" valign="middle" width="75">
	    <html:submit styleClass="confirmButton" tabindex="1" titleKey="link.title.confirmDeleteExpense">
	      <bean:message key="button.confirm"/>
	    </html:submit>
    </td>
  </tr>
</html:form>
</table>
<html:errors/>

<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%">
	  <bean:message key="label.date"/>
    </th>
    <td align="left" width="65%">
      <bean:write name="BookingDateExpenseViewFormApp" property="bookingDate.bookingDate" formatKey="format.longDateFormat" />
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.expense"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpenseViewFormApp" property="bookingExpense.expenseName"/>
      -
      <bean:write name="BookingDateExpenseViewFormApp" property="bookingExpense.expenseCode"/>
      <logic:notEmpty name="BookingDateExpenseViewFormApp" property="bookingExpense.expenseDescription">
      -
      <bean:write name="BookingDateExpenseViewFormApp" property="bookingExpense.expenseDescription"/>
      </logic:notEmpty>
    </td>
  </tr>
  <logic:equal name="BookingDateExpenseViewFormApp" property="bookingExpense.isMultiplier" value="true">
  <tr>
    <th align="left"">
      <bean:message key="label.qty"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpenseViewFormApp" property="bookingDateExpense.qty" format="#0.00"/>
      @
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateExpenseViewFormApp" property="bookingExpense.expenseMultiplier" format="#0.00"/>
    </td>
  </tr>
  </logic:equal>
  <tr>
    <th align="left"">
      <bean:message key="label.value"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateExpenseViewFormApp" property="bookingDateExpense.value" format="#0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"">
      <bean:message key="label.vatValue"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateExpenseViewFormApp" property="bookingDateExpense.vatValue" format="#0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label" nowrap="true">
      <bean:message key="label.comment"/>&nbsp;
    </th>
    <td align="left">
      <pre><bean:write name="BookingDateExpenseViewFormApp" property="bookingDateExpense.text"/></pre>
    </td>
  </tr>
  <logic:notEmpty name="BookingDateExpenseViewFormApp" property="bookingDateExpense.filename" >
  <tr>
	<th>
	  <bean:message key="label.uploadedReceipt"/>
	</th>
    <td>
      <logic:empty name="BookingDateExpenseViewFormApp" property="bookingDateExpense.filename">
        <bean:message key="text.noReceipt"/>
  	  </logic:empty>
	  <logic:notEmpty name="BookingDateExpenseViewFormApp" property="bookingDateExpense.filename">
        <bean:define id="documentUrl" name="BookingDateExpenseViewFormApp" property="bookingDateExpense.documentUrl" type="java.lang.String" />
        <html:link href="<%= request.getContextPath() + documentUrl %>" target="_blank" titleKey="link.title.viewReceipt"><bean:message key="link.viewReceipt"/></html:link>
	  </logic:notEmpty>
    </td>
  </tr>
  </logic:notEmpty>
</table>
