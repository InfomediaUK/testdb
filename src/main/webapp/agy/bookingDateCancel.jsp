<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="bookingDateCancelProcess.do" onsubmit="return singleSubmit();">
	<html:hidden property="bookingDate.bookingDateId" />
	<html:hidden property="bookingDate.bookingGradeId" />
	<html:hidden property="bookingDate.noOfChanges" />
	<%-- used to lock the booking --%>
	<input type="hidden" name="bookingId" value="<bean:write name="BookingDateFormAgy" property="bookingDate.bookingId"/>"/>
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDateCancel"/>
		</td>
    <td align="right" valign="middle" width="75">
	<logic:equal name="BookingDateFormAgy" property="bookingDate.canBeCancelled" value="true">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </logic:equal>
	<logic:equal name="BookingDateFormAgy" property="bookingDate.canBeCancelledCompleted" value="true">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </logic:equal>
	<logic:notEqual name="BookingDateFormAgy" property="bookingDate.canBeCancelled" value="true">
	<logic:notEqual name="BookingDateFormAgy" property="bookingDate.canBeCancelledCompleted" value="true">
      &nbsp;
    </logic:notEqual> 
    </logic:notEqual> 
    </td>
  </tr>
</table>

<html:errors/>

<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.cancelText"/></th>
    <td align="left" width="75%">
  		<html:text property="bookingDate.cancelText" size="99" />
    </td>
  </tr>
</table>
<br/>

<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.shiftNo"/></th>
    <td align="left" width="75%">
      <bean:write name="BookingDateFormAgy" property="bookingDate.bookingId" format="#000"/>.<bean:write name="BookingDateFormAgy" property="bookingDate.bookingDateId" format="#000"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.location"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.locationName" />,
      <bean:write name="BookingDateFormAgy" property="bookingDate.siteName" />
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.jobProfile"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.jobProfileName" />
    </td>
  </tr>
<logic:notEqual name="BookingDateFormAgy" property="bookingDate.applicantId" value="0">
  <tr>
    <th align="left"><bean:message key="label.grade"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.gradeName" />
    </td>
  </tr>
</logic:notEqual>  
  <tr>
    <th align="left"><bean:message key="label.date"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.bookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.shift"/></th>
    <td align="left">
			<bean:write name="BookingDateFormAgy" property="bookingDate.shiftName"/>
    </td>
  </tr>
<logic:equal name="BookingDateFormAgy" property="bookingDate.applicantId" value="0">
  <tr>
    <th align="left"><bean:message key="label.time"/></th>
    <td align="left">
<logic:equal name="BookingDateFormAgy" property="bookingDate.undefinedShift" value="true">
      Undefined
</logic:equal>
<logic:notEqual name="BookingDateFormAgy" property="bookingDate.undefinedShift" value="true">
      <bean:write name="BookingDateFormAgy" property="bookingDate.shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateFormAgy" property="bookingDate.shiftEndTime" format="HH:mm"/>
</logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.break"/></th>
    <td align="left">
<logic:equal name="BookingDateFormAgy" property="bookingDate.undefinedShift" value="true">
      &nbsp;
</logic:equal>
<logic:notEqual name="BookingDateFormAgy" property="bookingDate.undefinedShift" value="true">
		  <bean:write name="BookingDateFormAgy" property="bookingDate.shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateFormAgy" property="bookingDate.shiftBreakEndTime" format="HH:mm"/>
      (<bean:write name="BookingDateFormAgy" property="bookingDate.shiftBreakNoOfHours" format="#0.00"/>)
</logic:notEqual>
		</td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.noOfHours"/></th>
    <td align="left">
<logic:equal name="BookingDateFormAgy" property="bookingDate.undefinedShift" value="true">
      &nbsp;
</logic:equal>
<logic:notEqual name="BookingDateFormAgy" property="bookingDate.undefinedShift" value="true">
      <bean:write name="BookingDateFormAgy" property="bookingDate.shiftNoOfHours" format="#0.00"/>
</logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.rrpValue"/></th>
    <td align="left">
<logic:equal name="BookingDateFormAgy" property="bookingDate.undefinedShift" value="true">
      &nbsp;
</logic:equal>
<logic:notEqual name="BookingDateFormAgy" property="bookingDate.undefinedShift" value="true">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateFormAgy" property="bookingDate.value" format="#,##0.00"/>
</logic:notEqual>
    </td>
  </tr>
</logic:equal>
  <tr>
    <th align="left"><bean:message key="label.status"/></th>
    <td align="left">
	    <bean:message name="BookingDateFormAgy" property="bookingDate.statusDescriptionKey"/>
    </td>
  </tr>

<logic:notEmpty name="BookingDateFormAgy" property="bookingDate.cancelledTimestamp">
  <tr>
    <th align="left"><bean:message key="label.cancelledBy"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.cancelledByFirstName"/>
      <bean:write name="BookingDateFormAgy" property="bookingDate.cancelledByLastName"/>
	    (<bean:write name="BookingDateFormAgy" property="bookingDate.cancelledTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="BookingDateFormAgy" property="bookingDate.activatedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.activatedBy"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.activatedByFirstName"/>
      <bean:write name="BookingDateFormAgy" property="bookingDate.activatedByLastName"/>
	    (<bean:write name="BookingDateFormAgy" property="bookingDate.activatedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="BookingDateFormAgy" property="bookingDate.authorizedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.authorizedBy"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.authorizedByFirstName"/>
      <bean:write name="BookingDateFormAgy" property="bookingDate.authorizedByLastName"/>
	    (<bean:write name="BookingDateFormAgy" property="bookingDate.authorizedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:equal name="BookingDateFormAgy" property="bookingDate.workedStatusIsRejected" value="true">
<logic:notEmpty name="BookingDateFormAgy" property="bookingDate.rejectedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.rejectedBy"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.rejectedByFirstName"/>
      <bean:write name="BookingDateFormAgy" property="bookingDate.rejectedByLastName"/>
	    (<bean:write name="BookingDateFormAgy" property="bookingDate.rejectedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
</logic:equal>
<logic:notEmpty name="BookingDateFormAgy" property="bookingDate.invoicedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.invoicedBy"/></th>
    <td align="left">
      <bean:write name="BookingDateFormAgy" property="bookingDate.invoicedByFirstName"/>
      <bean:write name="BookingDateFormAgy" property="bookingDate.invoicedByLastName"/>
	    (<bean:write name="BookingDateFormAgy" property="bookingDate.invoicedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>

</html:form>
</table>

<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["BookingDateFormAgy"].elements["bookingDate.cancelText"];

  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
  // -->
</script>
