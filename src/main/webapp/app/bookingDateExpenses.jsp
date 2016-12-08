<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="bookingDateExpenseNew.do" onsubmit="return singleSubmit();">
  <html:hidden name="BookingDateExpensesFormApp" property="bookingDate.bookingDateId" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.bookingDateExpenses"/>
		</td>
    <td align="right" valign="middle" width="75">
	    <html:submit styleClass="titleButton" tabindex="1">
	      <bean:message key="button.todo"/>
	    </html:submit>
    </td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="30%">
      <bean:message key="label.shiftNo"/>
    </th>
    <td align="left" width="70%">
    <html:link forward="home">
      <bean:write name="BookingDateExpensesFormApp" property="bookingDate.bookingId" format="#000" />.<bean:write name="BookingDateExpensesFormApp" property="bookingDate.bookingDateId" format="#000" />
    </html:link>    
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpensesFormApp" property="bookingDate.jobProfileName" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpensesFormApp" property="bookingDate.locationName" />,
      <bean:write name="BookingDateExpensesFormApp" property="bookingDate.siteName" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.date"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpensesFormApp" property="bookingDate.bookingDate" formatKey="format.longDateFormat" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shift"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateExpensesFormApp" property="bookingDate.shiftName" />
	  </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.time"/>
    </th>
    <td align="left">
	    <bean:write name="BookingDateExpensesFormApp" property="bookingDate.shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateExpensesFormApp" property="bookingDate.shiftEndTime" format="HH:mm"/>
	  </td>
	</tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.break"/>
    </th>
	  <td align="left">
	    <bean:write name="BookingDateExpensesFormApp" property="bookingDate.shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateExpensesFormApp" property="bookingDate.shiftBreakEndTime" format="HH:mm"/>
	    (<bean:write name="BookingDateExpensesFormApp" property="bookingDate.shiftBreakNoOfHours" format="#0.00"/>)
	  </td>
	</tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.hours"/>
    </th>
	  <td align="left">
	    <bean:write name="BookingDateExpensesFormApp" property="bookingDate.shiftNoOfHours" format="#0.00"/>
	  </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.agreedValue"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateExpensesFormApp" property="bookingDate.payRateValue" format="#,##0.00" />
    </td>
  </tr>
</table>
<br/>
<logic:notEmpty name="BookingDateExpensesFormApp" property="expenses">
<table class="simple" width="100%">
<logic:iterate id="bookingExpense" name="BookingDateExpensesFormApp" property="expenses">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
<bean:write name="bookingExpense" property="expenseName"/>
<bean:write name="bookingExpense" property="expenseCode"/>
<bean:write name="bookingExpense" property="expenseDescription"/>
<bean:write name="bookingExpense" property="expenseMultiplier"/>
<bean:write name="bookingExpense" property="expenseVatRate"/>
    </td>
  </tr>
</logic:iterate>
</table>
</logic:notEmpty>
</html:form>
<table class="simple" width="100%">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
Should be a wizard where ...
    </th>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
step 1 - user chooses expense 
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
step 2 - enters amount or qty (no of miles) 
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
step 3 - upload receipt
    </td>
  </tr>
</table>

