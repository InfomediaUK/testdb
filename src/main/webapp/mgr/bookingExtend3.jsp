<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ 
taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ 
taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:parameter id="formAction" name="formAction" value="/bookingExtendFinish.do"/>

<html:form action="<%= formAction %>" onsubmit="return singleSubmit();">
<html:hidden property="page" value="3"/>

<html:hidden property="booking.bookingId"/>

<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.extendBooking"/>
      -&nbsp;<bean:message key="title.bookingExtend3"/>
    </td>
    <td align="left" valign="top">
		  <jsp:include page="orderStaffButtons.jsp" flush="true" >
		    <jsp:param name="nextButtonKey" value="button.finish" />
		  </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<table class="simple" width="100%">
  <tr>
    <th align="left">
			<bean:message key="label.date"/>
    </th>
    <th align="left">
			<bean:message key="label.shift"/>
    </th>
    <th align="left">
			<bean:message key="label.time"/>
    </th>
    <th align="left">
			<bean:message key="label.break"/>
    </th>
    <th align="left">
			<bean:message key="label.hrs"/>
    </th>
    <th align="right">
			<bean:message key="label.rrpValue"/>
    </th>
    <th align="right">
			<bean:message key="label.charge"/>
    </th>
    <th align="right">
			<bean:message key="label.wage"/>
    </th>
  </tr>
  <logic:iterate id="bookingDate" name="BookingExtendFormMgr" property="bookingDates" type="com.helmet.bean.BookingDate">
  <tr>
    <td align="left">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </td>
    <td align="left">
			<bean:write name="bookingDate" property="shiftName"/>
    </td>
    <td align="left">
      <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
    </td>
    <td align="left">
      <bean:write name="bookingDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftBreakEndTime" format="HH:mm"/>
      (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
    </td>
    <td align="right">
      <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="value" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/>
    </td>
  </tr>
  </logic:iterate>
  <tr>
    <th align="left" colspan="4">
			<bean:message key="label.total"/>
    </th>
	  <td align="right">
	    <bean:write name="BookingExtendFormMgr" property="totalHours" format="#0.00"/>
	  </td>
    <td align="right">
			<bean:message key="label.currencySymbol"/><bean:write name="BookingExtendFormMgr" property="rrp" format="#,##0.00" />
    </td>
    <td align="right">
			<bean:message key="label.currencySymbol"/><bean:write name="BookingExtendFormMgr" property="chargeRateValueTotal" format="#,##0.00" />
    </td>
    <td align="right">
			<bean:message key="label.currencySymbol"/><bean:write name="BookingExtendFormMgr" property="wageRateValueTotal" format="#,##0.00" />
    </td>
  </tr>
</table>

</html:form>
    