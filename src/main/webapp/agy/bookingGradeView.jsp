<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<mmj-agy:consultant var="consultant"/>

<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>

<bean:parameter id="tab" name="tab" value="summary"/>

<bean:parameter id="showApplicants" name="showApplicants" value="false"/>

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


<logic:equal name="tab" value="summary">

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
			<bean:write name="bookingGrade" property="shiftName"/>
			<bean:write name="bookingGrade" property="shiftStartTime" format="HH:mm"/>
      -
      <bean:write name="bookingGrade" property="shiftEndTime" format="HH:mm"/>
			(<bean:write name="bookingGrade" property="shiftNoOfHours" format="#0.00"/><bean:message key="label.hrsLower"/>)
    </td>
  </tr>
  </logic:notEmpty>
  <tr>
    <th align="left" class="label">
			<bean:message key="label.totalHours"/>
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
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Don't show wage... --%>
        &nbsp;
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

<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.bookingExpenses">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.expenses" />
    </th>
    <td>
			<logic:iterate id="bookingExpense" name="BookingGradeViewFormAgy" property="bookingGrade.bookingExpenses" indexId="expenseIndex"><logic:greaterThan name="expenseIndex" value="0">,&nbsp;</logic:greaterThan><bean:write name="bookingExpense" property="expenseName"/></logic:iterate>
		  <logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.expensesText">
		    (<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.expensesText"/>)
		  </logic:notEmpty>
      <mmj-agy:hasAccess forward="bookingEditExpenses">
	    <html:link forward="bookingEditExpenses" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeViewFormAgy" paramProperty="bookingGrade.bookingGradeId" titleKey="title.bookingEditExpenses" >
	    <bean:message key="link.edit"/>
	    </html:link>
      </mmj-agy:hasAccess>
    </td>
  </tr>
</logic:notEmpty>

<%/* END OF EXPENSES */%>

<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.comments">
  <tr>
    <th align="left" valign="middle" class="label">
    	<bean:message key="label.comments" />
    </th>
    <td>
      <pre><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.comments"/></pre>
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.bookingReference">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.reference" />
    </th>
    <td>
      <bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingReference"/>
    </td>
  </tr>
</logic:notEmpty>  
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
      <mmj-agy:hasAccess forward="bookingEditInfo">
	    <html:link forward="bookingEditInfo" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeViewFormAgy" paramProperty="bookingGrade.bookingGradeId" titleKey="title.bookingEditInfo" >
	    <bean:message key="link.edit"/>
	    </html:link>
      </mmj-agy:hasAccess>
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

</table>

</logic:equal>

<logic:equal name="tab" value="shifts">

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
      <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
    </td>
	  <td align="left">
		  <bean:write name="bookingDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftBreakEndTime" format="HH:mm"/>
      (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
		</td>
    <td align="right">
      <logic:equal name="bookingDate" property="isCancelled" value="true">
      <s><bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/></s>
      </logic:equal> 
      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
      <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
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
      <logic:equal name="bookingDate" property="isCancelled" value="true">
      <s><bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRateValue" format="#,##0.00"/></s>
      </logic:equal> 
      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRateValue" format="#,##0.00"/>
      </logic:notEqual>
    </td>
<%--
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="payRateValue" format="#,##0.00"/>
--%>
    <td align="right">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
	      <logic:equal name="bookingDate" property="isCancelled" value="true">
	        <s><bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/></s>
	      </logic:equal> 
	      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
	        <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/>
	      </logic:notEqual>
	    </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Don't show wage... --%>
        &nbsp;
      </logic:equal>
    </td>
    <td align="left">
			<bean:message name="bookingDate" property="statusDescriptionKey"/>
      <logic:equal name="bookingDate" property="canBeCancelled" value="true">
			<mmj-agy:hasAccess forward="bookingDateCancel">
			  <html:link forward="bookingDateCancel" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.cancel"/></html:link>
			</mmj-agy:hasAccess>
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
		  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.value" format="#,##0.00" />
	  </td>
<%--
		  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.payRateValue" format="#,##0.00" />
--%>
    <td align="right">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
		    <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeViewFormAgy" property="bookingGrade.wageRateValue" format="#,##0.00" />
		  </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Don't show wage... --%>
        &nbsp;
      </logic:equal>
    </td>
    <th align="left">
   		&nbsp;
    </th>
  </tr>



</table>

<%--

<table>
  <tr>
    <td align="left" class="label" colspan="2">
      <a href="#" onClick="toggle('shiftsX'); return false;"><bean:message key="label.shifts"/></a>
    </td>
  </tr>
  <tr id="shiftsX">
    <td align="left" colspan="2">
      <table>
      <logic:iterate id="bookingDate" name="BookingGradeViewFormAgy" property="bookingGrade.bookingDateUsers">
        <tr>
          <td align="left">
			<bean:write name="bookingDate" property="bookingId"/>.<bean:write name="bookingDate" property="bookingDateId"/>
          </td>
          <td align="left">
			<fmt:formatDate value="${bookingDate.bookingDate}" pattern="EEE, dd MMM yyyy" />
          </td>
          <td align="left">
			<bean:write name="bookingDate" property="shiftName"/>
          </td>
          <td align="left">
			<bean:write name="bookingDate" property="startTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="endTime" format="HH:mm"/>
			    </td>
			    <td align="left">
			<bean:write name="bookingDate" property="breakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="breakEndTime" format="HH:mm"/>
			    </td>
          <td align="right">
			<bean:write name="bookingDate" property="noOfHours" format="#0.00"/>
          </td>

				  <td align="right">
     	<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="agyValue" format="#,##0.00"/>
				  </td>
          <td align="left">
			<bean:message name="bookingDate" property="statusDescriptionKey"/>
          </td>
        </tr>
      </logic:iterate>
      </table>
    </td>
  </tr>
  <SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
	<!--
  toggle('shiftsX');
	// -->
	</SCRIPT>
</table>

--%>


</logic:equal>

<logic:equal name="tab" value="applicants">

<logic:notPresent name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers">
  <br/>
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers">
<logic:empty name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers">
  <br/>
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers" >
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.name"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.emailAddress"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.dob"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.ni"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.gender"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.reference"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.professionalReference"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.grade"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.charge"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.wage"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.status"/>
    </th>
  </tr>
  <logic:iterate name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers" id="bookingGradeApplicant" type="com.helmet.bean.BookingGradeApplicantUser">
  <tr>
    <td align="left">
			<mmj-agy:hasAccess forward="bookingGradeApplicantView">
			<html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="bookingGradeApplicant" paramProperty="bookingGradeApplicantId"><bean:write name="bookingGradeApplicant" property="applicantFullNameLastFirst" /></html:link>
			</mmj-agy:hasAccess>
			<mmj-agy:hasNoAccess forward="bookingGradeApplicantView">
      <bean:write name="bookingGradeApplicant" property="applicantFullNameLastFirst" />
			</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantEmailAddress" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantDateOfBirth" formatKey="format.mediumDateFormat" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantNiNumber" />
    </td>
    <td align="left">
      <bean:message name="bookingGradeApplicant" property="applicantGenderDescriptionKey" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantReference" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantProfessionalReference" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="gradeName" />
    </td>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicant" property="rate" format="#,##0.00" />
    </td>
<%--
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicant" property="payRate" format="#,##0.00" />
--%>
    <td align="left">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicant" property="wageRate" format="#,##0.00" />
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Don't show wage... --%>
        &nbsp;
      </logic:equal>
    </td>
    <td align="left" <logic:notEmpty name="bookingGradeApplicant" property="rejectText">title="<bean:write name="bookingGradeApplicant" property="rejectText" />"</logic:notEmpty>>
      <bean:message name="bookingGradeApplicant" property="statusDescriptionKey" />
    </td>
  </tr>
  </logic:iterate> 
</table>
</logic:notEmpty>
</logic:present>

</logic:equal>

<logic:equal name="tab" value="timesheets">
<br/>
TODO

</logic:equal>
