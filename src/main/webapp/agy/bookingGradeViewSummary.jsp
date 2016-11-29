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

<bean:parameter id="tab" name="tab" value="summary"/>

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


<table class="simple" width="100%">

  <tr>
    <th align="left" class="label" width="25%">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left" width="75%">
	  <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.jobProfileName"/>
	  (<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.jobFamilyCode"/>.<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.jobSubFamilyCode"/>.<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.jobProfileCode"/>)
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.grade"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.gradeName" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.singleCandidate"/>
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.singleCandidate" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="BookingGradeViewFormAgy" property="bookingGrade.singleCandidate" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.client"/>
    </th>
    <td align="left">
			<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.clientName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.site"/>
    </th>
    <td align="left">
	  <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.siteName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
	  <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.locationName"/>
	  <logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.locationDescription">
	  (<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.locationDescription"/>)
	  </logic:notEmpty>
    </td>
  </tr>
  <bean:define id="bookingGrade" name="BookingGradeViewFormAgy" property="bookingGrade"/>
  <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.noOfBookingDates" value="1">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.date"/>
    </th>
    <td align="left">
      <bean:write name="bookingGrade" property="minBookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  </logic:equal>
  <logic:notEqual name="BookingGradeViewFormAgy" property="bookingGrade.noOfBookingDates" value="1">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.start"/>
    </th>
    <td align="left">
      <bean:write name="bookingGrade" property="minBookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.end"/>
    </th>
    <td align="left">
      <bean:write name="bookingGrade" property="maxBookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.days"/>
    </th>
    <td align="left">
			<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.noOfBookingDates"/>
    </td>
  </tr>
  </logic:notEqual>
  <logic:empty name="BookingGradeViewFormAgy" property="bookingGrade.shiftName">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shifts"/>
    </th>
    <td align="left">
      <bean:message key="label.varied"/>
    </td>
  </tr>
  </logic:empty>
  <logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.shiftName">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shift"/>
    </th>
    <td align="left">
      <logic:equal name="bookingGrade" property="undefinedShift" value="true">
        Undefined
      </logic:equal>
      <logic:notEqual name="bookingGrade" property="undefinedShift" value="true">
        <bean:write name="bookingGrade" property="shiftName"/>
        <bean:write name="bookingGrade" property="shiftStartTime" format="HH:mm"/>
        -
        <bean:write name="bookingGrade" property="shiftEndTime" format="HH:mm"/>
        (<bean:write name="bookingGrade" property="shiftNoOfHours" format="#0.00"/><bean:message key="label.hrsLower"/>)
      </logic:notEqual>
    </td>
  </tr>
  </logic:notEmpty>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.totalHours"/> (Booking)
    </th>
    <td align="left">
			<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingNoOfHours" format="0.00" />
    </td>
  </tr>


  <tr>
    <th align="left" class="label">
      <bean:message key="label.autoFill" />
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.autoFill" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="BookingGradeViewFormAgy" property="bookingGrade.autoFill" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.autoActivate" />
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.autoActivate" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="BookingGradeViewFormAgy" property="bookingGrade.autoActivate" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.chargeRate"/>
    </th>
    <td align="left">
			<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.rate" format="#,##0.00" />
			(<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.value" format="#,##0.00" />)
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.wageRate"/>
    </th>
    <td align="left">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
				<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.wageRate" format="#,##0.00" />
				&nbsp;(<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.wageRateValue" format="#,##0.00" />)
		  </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.hasSubcontractedApplicants" value="false"><%-- Booking Grade does NOT have subcontracted Applicants. Show wages. --%>
          <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.wageRate" format="#,##0.00" />
          &nbsp;(<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.wageRateValue" format="#,##0.00" />)
        </logic:equal>
        <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.hasSubcontractedApplicants" value="true"><%-- Booking Grade HAS subcontracted Applicants. Don't show wages.  --%>
        &nbsp;
        </logic:equal>
      </logic:equal>
    </td>
  </tr>

<%/* START OF SPECIFICS */%>

<bean:define id="firstSpecific" value="true"/>

<logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.cvRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.cvRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.cvRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.interviewRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.interviewRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.interviewRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.canProvideAccommodation" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.canProvideAccommodation" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.canProvideAccommodation" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.carRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.carRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.carRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal>
<logic:equal name="firstSpecific" value="false"></td></tr></logic:equal>

<%/* END OF SPECIFICS */%>

<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.dressCodeName">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.dressCode" />
    </th>
    <td>
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.dressCodeName"/>
    </td>
  </tr>
</logic:notEmpty>

<%/* START OF EXPENSES */%>

  <tr>
    <th align="left" class="label">
      <bean:message key="label.expenses" />
    </th>
    <td>
	  <logic:iterate id="bookingExpense" name="BookingGradeViewFormAgy" property="bookingGrade.bookingExpenses" indexId="expenseIndex">
	    <logic:greaterThan name="expenseIndex" value="0">,&nbsp;</logic:greaterThan><bean:write name="bookingExpense" property="expenseName"/>
	  </logic:iterate>
	  <logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.expensesText">
		(<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.expensesText"/>)
	  </logic:notEmpty>
      <mmj-agy:hasAccess forward="bookingEditExpenses">
	    <html:link forward="bookingEditExpenses" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeViewFormAgy" paramProperty="bookingGrade.bookingGradeId" titleKey="title.bookingEditExpenses" >
	    <bean:message key="link.edit"/>
	    </html:link>
      </mmj-agy:hasAccess>
      <mmj-agy:hasNoAccess forward="bookingEditExpenses">
        &nbsp;
      </mmj-agy:hasNoAccess>
    </td>
  </tr>

<%/* END OF EXPENSES */%>

<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.comments">
  <tr>
    <th align="left" valign="top" class="label">
    	<bean:message key="label.additionalInformation" />
    </th>
    <td align="left" valign="top">
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.comments"/>
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.duration">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.duration" />
    </th>
    <td align="left">
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.duration"/>
    </td>
  </tr>
</logic:notEmpty>  
<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.bookingReference">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.reference" />
    </th>
    <td align="left">
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingReference"/>
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
    <td align="left" width="100%">
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.contactName"/>
      <mmj-agy:hasAccess forward="bookingEditInfo">
        &nbsp;
	      <html:link forward="bookingEditInfo" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeViewFormAgy" paramProperty="bookingGrade.bookingGradeId" titleKey="title.bookingEditInfo" >
	        <bean:message key="link.edit"/>
	      </html:link>
      </mmj-agy:hasAccess>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactJobTitle" />
    </th>
    <td align="left" width="100%">
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.contactJobTitle"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactEmailAddress" />
    </th>
    <td align="left" width="100%">
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.contactEmailAddress"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactTelephoneNumber" />
    </th>
    <td align="left" width="100%">
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.contactTelephoneNumber"/>
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
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.accountContactName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.accountContactAddress" />
    </th>
    <td>
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.accountContactAddress.fullAddress"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.accountContactEmailAddress" />
    </th>
    <td>
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.accountContactEmailAddress"/>
    </td>
  </tr>

</table>

