<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isSubmit" name="isSubmit" value="false"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>

<%-- title and buttons --%>
<jsp:include page="bookingViewHeader.jsp" flush="true">
  <jsp:param name="isView" value="<%= isView %>"/>
  <jsp:param name="isSubmit" value="<%= isSubmit %>"/>
  <jsp:param name="isCancel" value="<%= isCancel %>"/>
</jsp:include>
<%-- tabs --%>
<jsp:include page="bookingViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="summary"/>
</jsp:include>
<%-- summary --%>
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="20%">
      <bean:message key="label.reason"/>
    </th>
    <td align="left" width="80%">
      <bean:write name="BookingViewFormMgr" property="booking.reasonForRequestName"/>
      <logic:notEmpty name="BookingViewFormMgr" property="booking.reasonForRequestText">
        (<bean:write name="BookingViewFormMgr" property="booking.reasonForRequestText"/>)
      </logic:notEmpty>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
			<bean:write name="BookingViewFormMgr" property="booking.jobProfileName"/>
			(<bean:write name="BookingViewFormMgr" property="booking.jobFamilyCode"/>.<bean:write name="BookingViewFormMgr" property="booking.jobSubFamilyCode"/>.<bean:write name="BookingViewFormMgr" property="booking.jobProfileCode"/>)
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.singleCandidate"/>
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="BookingViewFormMgr" property="booking.singleCandidate" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="BookingViewFormMgr" property="booking.singleCandidate" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
			<bean:write name="BookingViewFormMgr" property="booking.locationName"/>,
			<bean:write name="BookingViewFormMgr" property="booking.siteName"/>
<%--
			(<bean:write name="BookingViewFormMgr" property="booking.locationDescription"/>)
--%>
    </td>
  </tr>
  <bean:define id="booking" name="BookingViewFormMgr" property="booking"/>
  <logic:equal name="BookingViewFormMgr" property="booking.noOfBookingDates" value="1">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.date"/>
    </th>
    <td align="left">
      <bean:write name="booking" property="minBookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  </logic:equal>
  <logic:notEqual name="BookingViewFormMgr" property="booking.noOfBookingDates" value="1">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.start"/>
    </th>
    <td align="left">
      <bean:write name="booking" property="minBookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.end"/>
    </th>
    <td align="left">
      <bean:write name="booking" property="maxBookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.days"/>
    </th>
    <td align="left">
			<bean:write name="BookingViewFormMgr" property="booking.noOfBookingDates"/>
    </td>
  </tr>
  </logic:notEqual>
  <logic:notEmpty name="BookingViewFormMgr" property="booking.shiftName">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shift"/>
    </th>
    <td align="left">
			<bean:write name="booking" property="shiftName"/>
			<bean:write name="booking" property="shiftStartTime" format="HH:mm"/>
      -
      <bean:write name="booking" property="shiftEndTime" format="HH:mm"/>
			(<bean:write name="booking" property="shiftNoOfHours" format="#0.00"/><bean:message key="label.hrsLower"/>)
<%-- not in object ...
      <logic:greaterThan name="booking" property="shiftOvertimeNoOfHours" value="0">
      &gt;<bean:write name="booking" property="shiftOvertimeNoOfHours" format="#0.##"/>&nbsp;x<bean:write name="booking" property="shiftOvertimeUpliftFactor" format="#0.##"/>
      </logic:greaterThan>
      <logic:lessEqual name="booking" property="shiftOvertimeNoOfHours" value="0">
        <bean:message key="label.none"/>
      </logic:lessEqual>
--%>			
    </td>
  </tr>
  </logic:notEmpty>
  <logic:empty name="BookingViewFormMgr" property="booking.shiftName">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shifts"/>
    </th>
    <td align="left">
      <bean:message key="label.varied"/>
    </td>
  </tr>
  </logic:empty>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.totalHours"/>
    </th>
    <td align="left">
			<bean:write name="BookingViewFormMgr" property="booking.noOfHours" format="0.00" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.rrpRate"/>
    </th>
    <td align="left">
			<bean:message key="label.currencySymbol"/><bean:write name="BookingViewFormMgr" property="booking.rate" format="#,##0.00" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.rrpValue"/>
    </th>
    <td align="left">
			<bean:message key="label.currencySymbol"/><bean:write name="BookingViewFormMgr" property="booking.value" format="#,##0.00" />
    </td>
  </tr>
  <logic:notEqual name="BookingViewFormMgr" property="booking.draft" value="true">
  <tr>
    <th align="left" class="label">
			<bean:message key="label.agreedValue"/>
    </th>
    <td align="left">
			<bean:message key="label.currencySymbol"/><bean:write name="BookingViewFormMgr" property="booking.filledValue" format="#,##0.00" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.actualHours"/>
    </th>
    <td align="left">
			<bean:write name="BookingViewFormMgr" property="booking.workedNoOfHours" format="0.00" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.actualValue"/>
    </th>
    <td align="left">
			<bean:message key="label.currencySymbol"/><bean:write name="BookingViewFormMgr" property="booking.workedValue" format="#,##0.00" />
    </td>
  </tr>
  </logic:notEqual>

<%/* START OF SPECIFICS */%>

<bean:define id="firstSpecific" value="true"/>

<logic:equal name="BookingViewFormMgr" property="booking.cvRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.cvRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.cvRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="BookingViewFormMgr" property="booking.interviewRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.interviewRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.interviewRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="BookingViewFormMgr" property="booking.canProvideAccommodation" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.canProvideAccommodation" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.canProvideAccommodation" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="BookingViewFormMgr" property="booking.carRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.carRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.carRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal>
<logic:equal name="firstSpecific" value="false"></td></tr></logic:equal>

<%/* END OF SPECIFICS */%>

<logic:notEmpty name="BookingViewFormMgr" property="booking.dressCodeName">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.dressCode" />
    </th>
    <td>
      <bean:write name="BookingViewFormMgr" property="booking.dressCodeName"/>
    </td>
  </tr>
</logic:notEmpty>

<%/* START OF EXPENSES */%>

  <tr>
    <th align="left" class="label">
      <bean:message key="label.expenses" />
    </th>
    <td>
<logic:notEmpty name="BookingViewFormMgr" property="booking.bookingExpenses">
	  <logic:iterate id="bookingExpense" name="BookingViewFormMgr" property="booking.bookingExpenses" indexId="expenseIndex"><logic:greaterThan name="expenseIndex" value="0">,&nbsp;</logic:greaterThan><bean:write name="bookingExpense" property="expenseName"/></logic:iterate>
  	  <logic:notEmpty name="BookingViewFormMgr" property="booking.expensesText">
		(<bean:write name="BookingViewFormMgr" property="booking.expensesText"/>)
	  </logic:notEmpty>
</logic:notEmpty>

    <logic:equal name="BookingViewFormMgr" property="booking.canBeEditedWhenSubmitted" value="true">
      <mmj-mgr:hasAccess forward="bookingEditExpenses">
	    <html:link forward="bookingEditExpenses" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId" titleKey="title.bookingEditExpenses" >
	    <bean:message key="link.edit"/>
	    </html:link>
      </mmj-mgr:hasAccess>
    </logic:equal>
		  
    </td>
  </tr>

<%/* END OF EXPENSES */%>

<logic:notEmpty name="BookingViewFormMgr" property="booking.comments">
  <tr>
    <th align="left" valign="middle" class="label">
    	<bean:message key="label.comments" />
    </th>
    <td>
      <pre><bean:write name="BookingViewFormMgr" property="booking.comments"/></pre>
    </td>
  </tr>
</logic:notEmpty>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.autoFill" />
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="BookingViewFormMgr" property="booking.autoFill" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="BookingViewFormMgr" property="booking.autoFill" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.autoActivate" />
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="BookingViewFormMgr" property="booking.autoActivate" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="BookingViewFormMgr" property="booking.autoActivate" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.bookedBy" />
    </th>
    <td>
			<bean:write name="BookingViewFormMgr" property="booking.bookedByFirstName"/>
			<bean:write name="BookingViewFormMgr" property="booking.bookedByLastName"/>
			(<bean:write name="BookingViewFormMgr" property="booking.creationTimestamp" formatKey="format.longDateTimeFormat"/>)
<%-- 
			<bean:write name="BookingViewFormMgr" property="booking.managerEmailAddress"/>
--%>
    </td>
  </tr>
  <logic:notEmpty name="BookingViewFormMgr" property="booking.submittedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.submittedBy"/></th>
    <td align="left">
      <bean:write name="BookingViewFormMgr" property="booking.submittedByFirstName"/>
      <bean:write name="BookingViewFormMgr" property="booking.submittedByLastName"/>
	    (<bean:write name="BookingViewFormMgr" property="booking.submittedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
  </logic:notEmpty>
  <logic:notEmpty name="BookingViewFormMgr" property="booking.cancelledTimestamp">
  <tr>
    <th align="left"><bean:message key="label.cancelledBy"/></th>
    <td align="left">
      <bean:write name="BookingViewFormMgr" property="booking.cancelledByFirstName"/>
      <bean:write name="BookingViewFormMgr" property="booking.cancelledByLastName"/>
	    (<bean:write name="BookingViewFormMgr" property="booking.cancelledTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
  </logic:notEmpty>
  <logic:notEmpty name="BookingViewFormMgr" property="booking.cancelText">
  <tr>
    <th align="left" class="label"><bean:message key="label.cancelText"/></th>
    <td align="left"><bean:write name="BookingViewFormMgr" property="booking.cancelText"/></td>
  </tr>
  </logic:notEmpty>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.duration" />
    </th>
    <td>
      <bean:write name="BookingViewFormMgr" property="booking.duration"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.reference" />
    </th>
    <td>
      <bean:write name="BookingViewFormMgr" property="booking.bookingReference"/>
    </td>
  </tr>
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
    <td align="left" width="100%">
      <bean:write name="BookingViewFormMgr" property="booking.contactName"/>

    <logic:equal name="BookingViewFormMgr" property="booking.canBeEditedWhenSubmitted" value="true">
      <mmj-mgr:hasAccess forward="bookingEditInfo">
	    <html:link forward="bookingEditInfo" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId" titleKey="title.bookingEditInfo" >
	    <bean:message key="link.edit"/>
	    </html:link>
      </mmj-mgr:hasAccess>
	</logic:equal>

    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactJobTitle" />
    </th>
    <td align="left" width="100%">
      <bean:write name="BookingViewFormMgr" property="booking.contactJobTitle"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactEmailAddress" />
    </th>
    <td align="left" width="100%">
      <bean:write name="BookingViewFormMgr" property="booking.contactEmailAddress"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactTelephoneNumber" />
    </th>
    <td align="left" width="100%">
      <bean:write name="BookingViewFormMgr" property="booking.contactTelephoneNumber"/>
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
      <bean:write name="BookingViewFormMgr" property="booking.accountContactName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.accountContactAddress" />
    </th>
    <td>
      <bean:write name="BookingViewFormMgr" property="booking.accountContactAddress.fullAddress"/>
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
      <bean:write name="BookingViewFormMgr" property="booking.accountContactEmailAddress"/>
    </td>
  </tr>

</table>

<jsp:include page="bookingViewFooter.jsp" flush="true">
  <jsp:param name="xxx" value="xxx"/>
</jsp:include>
			