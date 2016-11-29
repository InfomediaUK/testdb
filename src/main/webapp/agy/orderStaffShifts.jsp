<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:parameter id="formName" name="formName" value="Form Name Not Set" />
<bean:define id="form" name="<%= formName %>" type="org.apache.struts.validator.DynaValidatorForm"/>
<bean:define id="bookingDates" name="form" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
<%
com.helmet.bean.BookingDate minDate = bookingDates[0];
com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
pageContext.setAttribute("minDate", minDate);
pageContext.setAttribute("maxDate", maxDate);
%>
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
  </tr>
  <logic:iterate id="bookingDate" name="form" property="bookingDates" type="com.helmet.bean.BookingDate">
  <tr>
    <td align="left">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </td>
    <td align="left">
			<bean:write name="bookingDate" property="shiftName"/>
    </td>
    <td align="left">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        Undefined
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
      </logic:notEqual>
    </td>
    <td align="left">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <bean:write name="bookingDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftBreakEndTime" format="HH:mm"/>
        (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
      </logic:notEqual>
    </td>
    <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
      </logic:notEqual>
    </td>
    <td align="right">
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="value" format="#,##0.00"/>
      </logic:notEqual>
    </td>
  </tr>
  </logic:iterate>
  <tr>
    <th align="left" colspan="4">
			<bean:message key="label.total"/>
    </th>
	  <td align="right">
	    <bean:write name="form" property="totalHours" format="#0.00"/>
	  </td>
    <td align="right">
			<bean:message key="label.currencySymbol"/><bean:write name="form" property="rrp" format="#,##0.00" />
    </td>
  </tr>
</table>