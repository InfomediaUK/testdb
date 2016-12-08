<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="bookingDateSubmitProcess.do" onsubmit="return singleSubmit();">
<html:hidden property="bookingDate.bookingDateId" />
<html:hidden property="bookingDate.noOfChanges" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.bookingDateSubmit"/>
		</td>
    <td align="right" valign="middle" width="75">
	    <html:submit styleClass="confirmButton" tabindex="1">
	      <bean:message key="button.confirm"/>
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
      <bean:write name="BookingDateViewFormApp" property="bookingDate.bookingId" format="#000" />.<bean:write name="BookingDateViewFormApp" property="bookingDate.bookingDateId" format="#000" />
    </html:link>    
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateViewFormApp" property="bookingDate.jobProfileName" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateViewFormApp" property="bookingDate.locationName" />,
      <bean:write name="BookingDateViewFormApp" property="bookingDate.siteName" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.date"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateViewFormApp" property="bookingDate.bookingDate" formatKey="format.longDateFormat" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shift"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateViewFormApp" property="bookingDate.shiftName" />
	  </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.time"/>
    </th>
    <td align="left">
	    <bean:write name="BookingDateViewFormApp" property="bookingDate.shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateViewFormApp" property="bookingDate.shiftEndTime" format="HH:mm"/>
	  </td>
	</tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.break"/>
    </th>
	  <td align="left">
	    <bean:write name="BookingDateViewFormApp" property="bookingDate.shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateViewFormApp" property="bookingDate.shiftBreakEndTime" format="HH:mm"/>
	    (<bean:write name="BookingDateViewFormApp" property="bookingDate.shiftBreakNoOfHours" format="#0.00"/>)
	  </td>
	</tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.hours"/>
    </th>
	  <td align="left">
	    <bean:write name="BookingDateViewFormApp" property="bookingDate.shiftNoOfHours" format="#0.00"/>
	  </td>
  </tr>
<%--
  <tr>
    <th align="left" class="label">
      <bean:message key="label.agreedValue"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateViewFormApp" property="bookingDate.payRateValue" format="#,##0.00" />
    </td>
  </tr>
--%>
</table>
<br/>
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="30%">
      <bean:message key="label.actualTime"/>
    </th>
    <td align="left" width="70%">
      <bean:write name="BookingDateViewFormApp" property="bookingDate.workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateViewFormApp" property="bookingDate.workedEndTime" format="HH:mm"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.actualBreak"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateViewFormApp" property="bookingDate.workedBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateViewFormApp" property="bookingDate.workedBreakEndTime" format="HH:mm"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.actualHours"/>
    </th>
	  <td align="left">
	    <bean:write name="BookingDateViewFormApp" property="bookingDate.workedNoOfHours" format="#0.00"/>
	  </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.actualValue"/>
    </th>
    <td align="left">
<%--
      <bean:message key="label.currencySymbol" /><bean:write name="BookingDateViewFormApp" property="bookingDate.workedPayRateValue" format="#0.00"/>
--%>
      <bean:message key="label.currencySymbol" /><bean:write name="BookingDateViewFormApp" property="bookingDate.workedWageRateValue" format="#0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.expenseValue"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol" /><bean:write name="BookingDateViewFormApp" property="bookingDate.expenseValue" format="#0.00"/>
    </td>
  </tr>
<%--
  <tr>
    <th align="left" class="label">
      <bean:message key="label.expenseVatValue"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol" /><bean:write name="BookingDateViewFormApp" property="bookingDate.expenseVatValue" format="#0.00"/>
    </td>
  </tr>
--%>
  <tr>
    <th align="left">
      <bean:message key="label.comment"/>
    </th>
    <td align="left">
  	  <bean:write name="BookingDateViewFormApp" property="bookingDate.comment"/>
    </td>
  </tr>

</html:form>
</table>