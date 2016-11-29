<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>

<bean:parameter id="tab" name="tab" value="shifts"/>

<bean:parameter id="showApplicants" name="showApplicants" value="false"/>
<mmj-agy:consultant var="consultant"/>

<%-- title and buttons --%>
<jsp:include page="bookingGradeViewHeader.jsp" flush="true">
  <jsp:param name="isView" value="<%= isView %>"/>
  <jsp:param name="isCancel" value="<%= isCancel %>"/>
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<%-- tabs --%>
<jsp:include page="bookingGradeViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<logic:equal name="isCancel" value="true">
  <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.canBeCancelled" value="true">
    <mmj-agy:hasAccess forward="bookingCancel">
<html:errors/>
<form name="CancelFormAgy">
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.cancelText"/></th>
    <td align="left" width="75%"><input type="text" name="cancelText" size="99"/></td>
  </tr>
</form>
<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["CancelFormAgy"].elements["cancelText"];

  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
  // -->
</script>
</table>
<br/>
    </mmj-agy:hasAccess>
  </logic:equal>
</logic:equal>

<table class="simple" width="100%">
  <tr>
    <th align="center">
			<bean:message key="label.shiftNo"/>
    </th>
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
    <th align="center">
			<bean:message key="label.overtimeShort"/>
    </th>
    <th align="right">
			<bean:message key="label.charge"/>
    </th>
    <th align="right">
			<bean:message key="label.wage"/>
    </th>
    <th align="left">
			<bean:message key="label.status"/>
    </th>
  </tr>
<logic:iterate id="bookingDate" name="BookingGradeViewFormAgy" property="bookingGrade.bookingDateUsers" type="com.helmet.bean.BookingDate">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
    </th>
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
	      <logic:equal name="bookingDate" property="isCancelled" value="true">
	        <s><bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/></s>
	      </logic:equal> 
	      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
	        <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
	      </logic:notEqual>
      </logic:notEqual>
    </td>
    <td align="center">
    <logic:equal name="bookingDate" property="canEditOvertime" value="true">
		<mmj-agy:hasAccess forward="bookingDateEditOvertime">
		<html:link forward="bookingDateEditOvertime" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId">
      <logic:greaterThan name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
      &gt;<bean:write name="bookingDate" property="shiftOvertimeNoOfHours" format="#0.##"/>&nbsp;x<bean:write name="bookingDate" property="shiftOvertimeUpliftFactor" format="#0.##"/>
      </logic:greaterThan>
      <logic:lessEqual name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
        <bean:message key="label.none"/>
      </logic:lessEqual>
		</html:link>
		</mmj-agy:hasAccess>
		<mmj-agy:hasNoAccess forward="bookingDateEditOvertime">
      <logic:greaterThan name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
      &gt;<bean:write name="bookingDate" property="shiftOvertimeNoOfHours" format="#0.##"/>&nbsp;x<bean:write name="bookingDate" property="shiftOvertimeUpliftFactor" format="#0.##"/>
      </logic:greaterThan>
      <logic:lessEqual name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
        <bean:message key="label.none"/>
      </logic:lessEqual>
		</mmj-agy:hasNoAccess>
    </logic:equal>    
    <logic:notEqual name="bookingDate" property="canEditOvertime" value="true">
      <logic:greaterThan name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
      &gt;<bean:write name="bookingDate" property="shiftOvertimeNoOfHours" format="#0.##"/>&nbsp;x<bean:write name="bookingDate" property="shiftOvertimeUpliftFactor" format="#0.##"/>
      </logic:greaterThan>
      <logic:lessEqual name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
        <bean:message key="label.none"/>
      </logic:lessEqual>
    </logic:notEqual>
		</td>
    <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
	      <logic:equal name="bookingDate" property="isCancelled" value="true">
	        <s><bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRateValue" format="#,##0.00"/></s>
	      </logic:equal> 
	      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
	        <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRateValue" format="#,##0.00"/>
	      </logic:notEqual>
	    </logic:notEqual>
    </td>
<%--
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="payRateValue" format="#,##0.00"/>
--%>
    <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <logic:equal name="consultant" property="canViewWages" value="true">
		      <logic:equal name="bookingDate" property="isCancelled" value="true">
		        <s><bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/></s>
		      </logic:equal> 
		      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
		        <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/>
		      </logic:notEqual>
		    </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.hasSubcontractedApplicants" value="false"><%-- Booking Grade does NOT have subcontracted Applicants. Show wages. --%>
		      <logic:equal name="bookingDate" property="isCancelled" value="true">
		        <s><bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/></s>
		      </logic:equal> 
		      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
		        <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/>
		      </logic:notEqual>
        </logic:equal>
        <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.hasSubcontractedApplicants" value="true"><%-- Booking Grade HAS subcontracted Applicants. Don't show wages.  --%>
          &nbsp;
        </logic:equal>
      </logic:equal>
	    </logic:notEqual>
    </td>
    <td align="left">
	  <bean:message name="bookingDate" property="statusDescriptionKey"/>
      <logic:equal name="bookingDate" property="canBeCancelled" value="true">
			<mmj-agy:hasAccess forward="bookingDateCancel">
			  &nbsp;<html:link forward="bookingDateCancel" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.cancel"/></html:link>
			</mmj-agy:hasAccess>
      </logic:equal>
      <logic:equal name="bookingDate" property="canBeCancelledCompleted" value="true">
	        <mmj-agy:hasAccess forward="bookingDateCancelCompleted">
		      &nbsp;<html:link forward="bookingDateCancelCompleted" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.cancelCompleted"/></html:link>
		    </mmj-agy:hasAccess>
	  </logic:equal>
	  <logic:equal name="bookingDate" property="isFilled" value="true">
      	<logic:equal name="bookingDate" property="activated" value="true">
          &nbsp;<bean:message key="text.activated"/>
      	</logic:equal>
      	<logic:equal name="bookingDate" property="canBeActivated" value="true">
          &nbsp;<bean:message key="text.notActivated"/>
      	</logic:equal>
      </logic:equal>
    </td>
  </tr>
</logic:iterate>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="5">
   		<bean:message key="label.total"/>
    </th>
	  <td align="right">
	    <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingNoOfHours" format="#0.00"/>
	  </td>
    <th align="left">
   		&nbsp;
    </th>
    <td align="right">
		  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="chargeRateValue" format="#,##0.00" />
	  </td>
<%--
		  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="payRateValue" format="#,##0.00" />
--%>
    <td align="right">
      <logic:equal name="consultant" property="canViewWages" value="true">
		    <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="wageRateValue" format="#,##0.00" />
		  </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.hasSubcontractedApplicants" value="false"><%-- Booking Grade does NOT have subcontracted Applicants. Show wages. --%>
		      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="wageRateValue" format="#,##0.00" />
        </logic:equal>
        <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.hasSubcontractedApplicants" value="true"><%-- Booking Grade HAS subcontracted Applicants. Don't show wages.  --%>
          &nbsp;
        </logic:equal>
      </logic:equal>
    </td>
    <th align="left">
   		&nbsp;
    </th>
  </tr>



</table>
