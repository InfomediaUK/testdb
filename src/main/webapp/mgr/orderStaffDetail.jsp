<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:parameter id="tab" name="tab" value="summary"/>

<%-- summary --%>
<%-- summary --%>
<%-- summary --%>
<logic:equal name="tab" value="summary">
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="20%">
      <bean:message key="label.reason"/>
    </th>
    <td align="left" width="80%">
			<bean:write name="OrderStaffFormMgr" property="reasonForRequest.name"/>
			<logic:notEmpty name="OrderStaffFormMgr" property="reasonForRequestText">
			  (<bean:write name="OrderStaffFormMgr" property="reasonForRequestText"/>)
			</logic:notEmpty>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
      <bean:write name="OrderStaffFormMgr" property="jobProfile.name"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.singleCandidate"/>
    </th>
    <td align="left" valign="middle">
    	<logic:equal name="OrderStaffFormMgr" property="singleCandidate" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
    	<logic:notEqual name="OrderStaffFormMgr" property="singleCandidate" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
	  <bean:write name="OrderStaffFormMgr" property="location.name"/>,
	  <bean:write name="OrderStaffFormMgr" property="location.siteName"/>
      <logic:notEmpty name="OrderStaffFormMgr" property="location.description">
  	    (<bean:write name="OrderStaffFormMgr" property="location.description"/>)
      </logic:notEmpty>
    </td>
  </tr>
  <bean:define id="bookingDates" name="OrderStaffFormMgr" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
  <%
  com.helmet.bean.BookingDate minDate = bookingDates[0];
  com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
  pageContext.setAttribute("minDate", minDate);
  pageContext.setAttribute("maxDate", maxDate);
  String minShiftName = bookingDates[0].getShiftName();
  java.sql.Time minShiftStartTime = bookingDates[0].getShiftStartTime();
  java.sql.Time minShiftEndTime = bookingDates[0].getShiftEndTime();
  java.math.BigDecimal minShiftNoOfHours = bookingDates[0].getShiftNoOfHours();
  Boolean variedShifts = new Boolean(false);
  for (int i = 0; i < bookingDates.length; i++) {
          com.helmet.bean.BookingDate bookingDate = bookingDates[i];
          if (!bookingDate.getShiftName().equals(minShiftName)) {
                   variedShifts = new Boolean(true);
                   break;
          }
  }
  pageContext.setAttribute("variedShifts", variedShifts);
  pageContext.setAttribute("minShiftName", minShiftName);
  pageContext.setAttribute("minShiftStartTime", minShiftStartTime);
  pageContext.setAttribute("minShiftEndTime", minShiftEndTime);
  pageContext.setAttribute("minShiftNoOfHours", minShiftNoOfHours);
  %>
  <logic:equal name="OrderStaffFormMgr" property="noOfDates" value="1">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.date"/>
    </th>
    <td align="left">
			<bean:write name="minDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  </logic:equal>
  <logic:notEqual name="OrderStaffFormMgr" property="noOfDates" value="1">
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
			<bean:write name="OrderStaffFormMgr" property="noOfDates"/>
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
			<bean:write name="minShiftStartTime" format="HH:mm"/>
      -
      <bean:write name="minShiftEndTime" format="HH:mm"/>
			(<bean:write name="minShiftNoOfHours" format="#0.00"/><bean:message key="label.hrsLower"/>)
    </td>
  </tr>
  </logic:notEqual>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.totalHours"/>
    </th>
    <td align="left">
			<bean:write name="OrderStaffFormMgr" property="totalHours" format="0.00" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.rrpRate"/>
    </th>
    <td align="left">
			<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormMgr" property="hourlyRate" format="#,##0.00" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.rrpValue"/>
    </th>
    <td align="left">
			<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormMgr" property="rrp" format="#,##0.00" />
    </td>
  </tr>

<%/* START OF SPECIFICS */%>

<bean:define id="firstSpecific" value="true"/>

<logic:equal name="OrderStaffFormMgr" property="cvRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.cvRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.cvRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormMgr" property="interviewRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.interviewRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.interviewRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormMgr" property="canProvideAccommodation" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.canProvideAccommodation" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.canProvideAccommodation" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormMgr" property="carRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.carRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.carRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal>
<logic:equal name="firstSpecific" value="false"></td></tr></logic:equal>

<%/* END OF SPECIFICS */%>

<logic:notEmpty name="OrderStaffFormMgr" property="dressCode.name">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.dressCode" />
    </th>
    <td>
      <bean:write name="OrderStaffFormMgr" property="dressCode.name"/>
    </td>
  </tr>
</logic:notEmpty>

<%/* START OF EXPENSES */%>

<logic:notEmpty name="OrderStaffFormMgr" property="expenseArray">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.expenses" />
    </th>
    <td>
			<logic:iterate id="expense" name="OrderStaffFormMgr" property="expenseArray" indexId="expenseIndex"><logic:greaterThan name="expenseIndex" value="0">,&nbsp;</logic:greaterThan><bean:write name="expense" property="name"/></logic:iterate>
			<logic:notEmpty name="OrderStaffFormMgr" property="expensesText">
			  (<bean:write name="OrderStaffFormMgr" property="expensesText"/>)
			</logic:notEmpty>
    </td>
  </tr>
</logic:notEmpty>


<%/* END OF EXPENSES */%>

<logic:notEmpty name="OrderStaffFormMgr" property="comments">
  <tr>
    <th align="left" valign="top" class="label">
    	<bean:message key="label.comments" />
    </th>
    <td>
      <pre><bean:write name="OrderStaffFormMgr" property="comments"/></pre>
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="OrderStaffFormMgr" property="duration">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.duration" />
    </th>
    <td>
      <bean:write name="OrderStaffFormMgr" property="duration"/>
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="OrderStaffFormMgr" property="bookingReference">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.reference" />
    </th>
    <td>
      <bean:write name="OrderStaffFormMgr" property="bookingReference"/>
    </td>
  </tr>
</logic:notEmpty>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.autoFill" />
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="OrderStaffFormMgr" property="autoFill" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="OrderStaffFormMgr" property="autoFill" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.autoActivate" />
    </th>
    <td align="left" valign="middle">
		  <logic:equal name="OrderStaffFormMgr" property="autoActivate" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="OrderStaffFormMgr" property="autoActivate" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
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
    <td>
      <bean:write name="OrderStaffFormMgr" property="contactName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.contactJobTitle" />
    </th>
    <td>
      <bean:write name="OrderStaffFormMgr" property="contactJobTitle"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.contactEmailAddress" />
    </th>
    <td>
      <bean:write name="OrderStaffFormMgr" property="contactEmailAddress"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.contactTelephoneNumber" />
    </th>
    <td>
      <bean:write name="OrderStaffFormMgr" property="contactTelephoneNumber"/>
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
      <bean:write name="OrderStaffFormMgr" property="accountContactName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.accountContactAddress" />
    </th>
    <td>
      <bean:write name="OrderStaffFormMgr" property="accountContactAddress.fullAddress"/>
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
      <bean:write name="OrderStaffFormMgr" property="accountContactEmailAddress"/>
    </td>
  </tr>
</table>
</logic:equal>

<%-- shifts --%>
<%-- shifts --%>
<%-- shifts --%>
<logic:equal name="tab" value="shifts">
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
  <logic:iterate id="bookingDate" name="OrderStaffFormMgr" property="bookingDates" type="com.helmet.bean.BookingDate">
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
  </tr>
  </logic:iterate>
  <tr>
    <th align="left" colspan="4">
			<bean:message key="label.total"/>
    </th>
	  <td align="right">
	    <bean:write name="OrderStaffFormMgr" property="totalHours" format="#0.00"/>
	  </td>
    <td align="right">
			<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormMgr" property="rrp" format="#,##0.00" />
    </td>
  </tr>
</table>
</logic:equal>

<%-- grades/agencies --%>
<%-- grades/agencies --%>
<%-- grades/agencies --%>
<logic:equal name="tab" value="grades">
<table class="simple" width="100%">
  <tr>
    <th align="left">
      <bean:message key="label.agency" />
    </tH>
    <th align="right">
      <bean:message key="label.percentage" />
    </th>
    <th align="left">
      <bean:message key="label.grade" />
    </th>
    <th align="right">
      <bean:message key="label.value" />
    </th>
    <th align="right">
      <bean:message key="label.rate" />
    </th>
  </tr>
<logic:iterate id="clientAgencyJobProfileGrade" name="OrderStaffFormMgr" property="clientAgencyJobProfileGradeUserArray" >
  <tr>
    <td align="left">
      <bean:write name="clientAgencyJobProfileGrade" property="agencyName"/>
    </td>
    <td align="right">
      <bean:write name="clientAgencyJobProfileGrade" property="percentage" format="#0" />%
    </td>
    <td align="left">
      <bean:write name="clientAgencyJobProfileGrade" property="gradeName"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="clientAgencyJobProfileGrade" property="value" format="#,##0.00" />
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="clientAgencyJobProfileGrade" property="rate" format="#,##0.00" />
    </td>
  </tr>
</logic:iterate>
</table>

</logic:equal>    
    
<%--
<br/>
<br/>
<bean:write name="OrderStaffFormMgr" property="location.clientName"/>
<bean:write name="OrderStaffFormMgr" property="location.clientCode"/>
<br/>
<br/>
<bean:write name="OrderStaffFormMgr" property="location.code"/>
<br/>
<br/>
<bean:write name="OrderStaffFormMgr" property="jobProfile.jobFamilyName"/>
<bean:write name="OrderStaffFormMgr" property="jobProfile.jobFamilyCode"/>
<br/>
<bean:write name="OrderStaffFormMgr" property="jobProfile.jobSubFamilyName"/>
<bean:write name="OrderStaffFormMgr" property="jobProfile.jobSubFamilyCode"/>
<br/>
<bean:write name="OrderStaffFormMgr" property="jobProfile.code"/>
<bean:write name="OrderStaffFormMgr" property="hourlyRate" format="#,##0.00"/>
<bean:write name="OrderStaffFormMgr" property="rrp" format="#,##0.00"/>
<br/>
<br/>
<%/*
<bean:write name="OrderStaffFormMgr" property="dates"/>&nbsp;-&nbsp;
*/%>
<bean:write name="OrderStaffFormMgr" property="noOfDates"/>
<br/>
<br/>
<logic:iterate id="bookingDate" name="OrderStaffFormMgr" property="bookingDates">
<bean:write name="bookingDate" property="bookingDate"/>
<bean:write name="bookingDate" property="shiftId"/>
<bean:write name="bookingDate" property="shift.name"/>
<bean:write name="bookingDate" property="shift.code"/>
<bean:write name="bookingDate" property="shift.description"/>
<bean:write name="bookingDate" property="shift.startTime"/>
<bean:write name="bookingDate" property="shift.endTime"/>
<bean:write name="bookingDate" property="shift.noOfHours"/>
<bean:write name="bookingDate" property="value"/>
<br/>
</logic:iterate>
<%/*
<bean:write name="OrderStaffFormMgr" property="shift.name"/>
<bean:write name="OrderStaffFormMgr" property="shift.code"/>
<bean:write name="OrderStaffFormMgr" property="shift.description"/>
<bean:write name="OrderStaffFormMgr" property="shift.startTime"/>
<bean:write name="OrderStaffFormMgr" property="shift.endTime"/>
<bean:write name="OrderStaffFormMgr" property="shift.noOfHours"/>
*/%>
<br/>
<bean:message key="label.totalHours" />&nbsp;<bean:write name="OrderStaffFormMgr" property="totalHours"/>&nbsp;-&nbsp;<bean:write name="OrderStaffFormMgr" property="rrp"/>
<br/>
<br/>
<table>
  <tr>
    <td align="left">
      <bean:message key="label.agency" />
    </td>
    <td align="left">
      <bean:message key="label.percentage" />
    </td>
    <td align="left">
      <bean:message key="label.grade" />
    </td>
    <td align="left">
      <bean:message key="label.value" />
    </td>
    <td align="left">
      <bean:message key="label.rate" />
    </td>
  </tr>
<logic:iterate id="clientAgencyJobProfileGrade" name="OrderStaffFormMgr" property="clientAgencyJobProfileGradeUserArray" >
  <tr>
    <td align="left">
      <bean:write name="clientAgencyJobProfileGrade" property="agencyName"/>
    </td>
    <td align="right">
      <bean:write name="clientAgencyJobProfileGrade" property="percentage" format="#0" />
    </td>
    <td align="left">
      <bean:write name="clientAgencyJobProfileGrade" property="gradeName"/>
    </td>
    <td align="right">
      <bean:write name="clientAgencyJobProfileGrade" property="value" format="#,##0.00" />
    </td>
    <td align="right">
      <bean:write name="clientAgencyJobProfileGrade" property="rate" format="#,##0.00" />
    </td>
  </tr>
</logic:iterate>
</table>
<br/>
<%/* specifics stuff */%>
<bean:define id="specificsToShow" value="false"/>
<logic:equal name="specificsToShow" value="false">
  <logic:equal name="OrderStaffFormMgr" property="singleCandidate" value="true">
    <bean:define id="specificsToShow" value="true"/>
  </logic:equal>
</logic:equal>
<logic:equal name="specificsToShow" value="false">
  <logic:equal name="OrderStaffFormMgr" property="cvRequired" value="true">
    <bean:define id="specificsToShow" value="true"/>
  </logic:equal>
</logic:equal>
<logic:equal name="specificsToShow" value="false">
  <logic:equal name="OrderStaffFormMgr" property="interviewRequired" value="true">
    <bean:define id="specificsToShow" value="true"/>
  </logic:equal>
</logic:equal>
<logic:equal name="specificsToShow" value="false">
  <logic:equal name="OrderStaffFormMgr" property="canProvideAccommodation" value="true">
    <bean:define id="specificsToShow" value="true"/>
  </logic:equal>
</logic:equal>
<logic:equal name="specificsToShow" value="false">
  <logic:equal name="OrderStaffFormMgr" property="carRequired" value="true">
    <bean:define id="specificsToShow" value="true"/>
  </logic:equal>
</logic:equal>
<logic:equal name="specificsToShow" value="true">
	<bean:message key="label.specifics" />
	<br/>
	<logic:equal name="OrderStaffFormMgr" property="singleCandidate" value="true">
		<br/>
		<bean:message key="label.singleCandidate" />
	</logic:equal>
	<logic:equal name="OrderStaffFormMgr" property="cvRequired" value="true">
		<br/>
		<bean:message key="label.cvRequired" />
	</logic:equal>
	<logic:equal name="OrderStaffFormMgr" property="interviewRequired" value="true">
		<br/>
		<bean:message key="label.interviewRequired" />
	</logic:equal>
	<logic:equal name="OrderStaffFormMgr" property="canProvideAccommodation" value="true">
		<br/>
		<bean:message key="label.canProvideAccommodation" />
	</logic:equal>
	<logic:equal name="OrderStaffFormMgr" property="carRequired" value="true">
		<br/>
		<bean:message key="label.carRequired" />
	</logic:equal>
	<br/>
</logic:equal>
<logic:notEmpty name="OrderStaffFormMgr" property="dressCode">
<logic:notEmpty name="OrderStaffFormMgr" property="dressCode.name">
	<br/>
	<bean:message key="label.dressCode" />
  -
  <bean:write name="OrderStaffFormMgr" property="dressCode.name"/>
	<br/>
</logic:notEmpty>
</logic:notEmpty>
<logic:notEmpty name="OrderStaffFormMgr" property="expenseArray">
	<br/>
	<bean:message key="label.expenses" />
	-
	<logic:iterate id="expense" name="OrderStaffFormMgr" property="expenseArray" indexId="expenseIndex"><logic:greaterThan name="expenseIndex" value="0">,&nbsp;</logic:greaterThan><bean:write name="expense" property="name"/></logic:iterate>
	<logic:notEmpty name="OrderStaffFormMgr" property="expensesText">
	  (<bean:write name="OrderStaffFormMgr" property="expensesText"/>)
	</logic:notEmpty>
</logic:notEmpty>
<br/>
<br/>
<bean:message key="label.comments" />
<pre>
<bean:write name="OrderStaffFormMgr" property="comments"/>
</pre>
<br/>
<br/>
<bean:message key="label.autoFill" />
<bean:write name="OrderStaffFormMgr" property="autoFill"/>

--%>   
    

