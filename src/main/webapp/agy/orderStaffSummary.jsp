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
String minShiftName = bookingDates[0].getShiftName();
Boolean undefinedShift = bookingDates[0].getUndefinedShift();
java.sql.Time minShiftStartTime = bookingDates[0].getShiftStartTime();
java.sql.Time minShiftEndTime = bookingDates[0].getShiftEndTime();
java.math.BigDecimal minShiftNoOfHours = bookingDates[0].getShiftNoOfHours();
Boolean variedShifts = new Boolean(false);
for (int i = 0; i < bookingDates.length; i++) 
{
  com.helmet.bean.BookingDate bookingDate = bookingDates[i];
  if (!bookingDate.getShiftName().equals(minShiftName)) 
  {
    variedShifts = new Boolean(true);
    break;
  }
}
pageContext.setAttribute("undefinedShift", undefinedShift);
pageContext.setAttribute("variedShifts", variedShifts);
pageContext.setAttribute("minShiftName", minShiftName);
pageContext.setAttribute("minShiftStartTime", minShiftStartTime);
pageContext.setAttribute("minShiftEndTime", minShiftEndTime);
pageContext.setAttribute("minShiftNoOfHours", minShiftNoOfHours);
%>
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="20%">
      <bean:message key="label.reason"/>
    </th>
    <td align="left" width="80%">
			<bean:write name="form" property="reasonForRequest.name"/>
			<logic:notEmpty name="form" property="reasonForRequestText">
			  (<bean:write name="form" property="reasonForRequestText"/>)
			</logic:notEmpty>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
      <bean:write name="form" property="jobProfile.name"/>
	    (<bean:write name="form" property="jobProfile.jobFamilyCode"/>.<bean:write name="form" property="jobProfile.jobSubFamilyCode"/>.<bean:write name="form" property="jobProfile.code"/>)
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.singleCandidate"/>
    </th>
    <td align="left" valign="middle">
    	<logic:equal name="form" property="singleCandidate" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
    	<logic:notEqual name="form" property="singleCandidate" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.client"/>
    </th>
    <td>
   	  <bean:write name="form" property="client.name"/>&nbsp;(<bean:write name="form" property="client.code"/>)
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
	    <bean:write name="form" property="location.name"/>,
	    <bean:write name="form" property="location.siteName"/>
      <logic:notEmpty name="form" property="location.description">
  	    (<bean:write name="form" property="location.description"/>)
      </logic:notEmpty>
    </td>
  </tr>
  <logic:equal name="form" property="noOfDates" value="1">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.date"/>
    </th>
    <td align="left">
			<bean:write name="minDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  </logic:equal>
  <logic:notEqual name="form" property="noOfDates" value="1">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.start"/>
    </th>
    <td align="left">
			<bean:write name="minDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.end"/>
    </th>
    <td align="left">
			<bean:write name="maxDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.days"/>
    </th>
    <td align="left">
			<bean:write name="form" property="noOfDates"/>
    </td>
  </tr>
  </logic:notEqual>
  <logic:equal name="variedShifts" value="true">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shifts"/>
    </th>
    <td align="left">
      <bean:message key="label.varied"/>
    </td>
  </tr>
  </logic:equal>
  <logic:notEqual name="variedShifts" value="true">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shift"/>
    </th>
    <td align="left">
			<bean:write name="minShiftName"/>
      <logic:notEqual name="form" property="undefinedShift" value="true">
				<bean:write name="minShiftStartTime" format="HH:mm"/>
	      -
	      <bean:write name="minShiftEndTime" format="HH:mm"/>
				(<bean:write name="minShiftNoOfHours" format="#0.00"/><bean:message key="label.hrsLower"/>)
		  </logic:notEqual>
    </td>
  </tr>
  </logic:notEqual>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.totalHours"/> (Booking)
    </th>
    <td align="left">
			<bean:write name="form" property="totalHours" format="0.00" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.autoFill" />
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="form" property="autoFill" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="form" property="autoFill" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.autoActivate" />
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="form" property="autoActivate" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="form" property="autoActivate" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.chargeRate"/>
    </th>
    <td align="left">
			<bean:message key="label.currencySymbol"/><bean:write name="form" property="hourlyRate" format="#,##0.00" />
			(<bean:message key="label.currencySymbol"/><bean:write name="form" property="rrp" format="#,##0.00" />)
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.expenses" />
    </th>
    <td align="left">
			<logic:iterate id="expense" name="OrderStaffCopyFormAgy" property="expenseArray" indexId="expenseIndex"><logic:greaterThan name="expenseIndex" value="0">,&nbsp;</logic:greaterThan><bean:write name="expense" property="name"/></logic:iterate>
			<logic:notEmpty name="OrderStaffCopyFormAgy" property="expensesText">
			  (<bean:write name="OrderStaffCopyFormAgy" property="expensesText"/>)
			</logic:notEmpty>
    </td>
  </tr>
<logic:notEmpty name="form" property="comments">
  <tr>
    <th align="left" valign="top" class="label">
    	<bean:message key="label.additionalInformation" />
    </th>
    <td align="left" valign="top">
      <bean:write name="form" property="comments"/>
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="form" property="duration">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.duration" />
    </th>
    <td>
      <bean:write name="form" property="duration"/>
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="form" property="bookingReference">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.reference" />
    </th>
    <td>
      <bean:write name="form" property="bookingReference"/>
    </td>
  </tr>
</logic:notEmpty>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label">
    	<bean:message key="label.contact" />
    </th>
  </tr>  
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.contactName" />
    </th>
    <td>
      <bean:write name="form" property="contactName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.contactJobTitle" />
    </th>
    <td>
      <bean:write name="form" property="contactJobTitle"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.contactEmailAddress" />
    </th>
    <td>
      <bean:write name="form" property="contactEmailAddress"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.contactTelephoneNumber" />
    </th>
    <td>
      <bean:write name="form" property="contactTelephoneNumber"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label">
    	<bean:message key="label.accountContact" />
    </th>
  </tr>  
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.accountContactName" />
    </th>
    <td>
      <bean:write name="form" property="accountContactName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.accountContactAddress" />
    </th>
    <td>
      <bean:write name="form" property="accountContactAddress.fullAddress"/>
    </td>
  </tr>
<%--
      country
--%>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.accountContactEmailAddress" />
    </th>
    <td>
      <bean:write name="form" property="accountContactEmailAddress"/>
    </td>
  </tr>
</table>
  