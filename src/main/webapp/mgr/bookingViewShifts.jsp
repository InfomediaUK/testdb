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
<bean:parameter id="showApplicants" name="showApplicants" value="false"/>
<bean:parameter id="tab" name="tab" value="shifts"/>

<%-- title and buttons --%>
<jsp:include page="bookingViewHeader.jsp" flush="true">
  <jsp:param name="isView" value="<%= isView %>"/>
  <jsp:param name="isSubmit" value="<%= isSubmit %>"/>
  <jsp:param name="isCancel" value="<%= isCancel %>"/>
</jsp:include>
<%-- tabs --%>
<jsp:include page="bookingViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<logic:equal name="isCancel" value="true">
  <logic:equal name="BookingViewFormMgr" property="booking.canBeCancelled" value="true">
    <mmj-mgr:hasAccess forward="bookingCancel">
<html:errors/>
<form name="CancelFormMgr">
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.cancelText"/></th>
    <td align="left" width="75%"><input type="text" name="cancelText" size="99"/></td>
  </tr>
</form>
<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["CancelFormMgr"].elements["cancelText"];

  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
  // -->
</script>
</table>
<br/>
    </mmj-mgr:hasAccess>
  </logic:equal>
</logic:equal>

<%-- shifts --%>
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
			<bean:message key="label.rrpValue"/>
    </th>
<logic:equal name="showApplicants" value="true">
    <th align="right">
			<bean:message key="label.agreed"/>
    </th>
    <th align="right">
			<bean:message key="label.actual"/>
    </th>
    <th align="left">
			<bean:message key="label.hrs"/>
    </th>
</logic:equal>
    <th align="left">
			<bean:message key="label.status"/>
    </th>
  </tr>
<logic:iterate id="bookingDate" name="BookingViewFormMgr" property="booking.bookingDateUserApplicants" type="com.helmet.bean.BookingDateUserApplicant">
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
		<mmj-mgr:hasAccess forward="bookingDateEditOvertime">
		<html:link forward="bookingDateEditOvertime" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId">
      <logic:greaterThan name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
      &gt;<bean:write name="bookingDate" property="shiftOvertimeNoOfHours" format="#0.##"/>&nbsp;x<bean:write name="bookingDate" property="shiftOvertimeUpliftFactor" format="#0.##"/>
      </logic:greaterThan>
      <logic:lessEqual name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
        <bean:message key="label.none"/>
      </logic:lessEqual>
		</html:link>
		</mmj-mgr:hasAccess>
		<mmj-mgr:hasNoAccess forward="bookingDateEditOvertime">
      <logic:greaterThan name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
      &gt;<bean:write name="bookingDate" property="shiftOvertimeNoOfHours" format="#0.##"/>&nbsp;x<bean:write name="bookingDate" property="shiftOvertimeUpliftFactor" format="#0.##"/>
      </logic:greaterThan>
      <logic:lessEqual name="bookingDate" property="shiftOvertimeNoOfHours" value="0">
        <bean:message key="label.none"/>
      </logic:lessEqual>
		</mmj-mgr:hasNoAccess>
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
      <s><bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="value" format="#,##0.00"/></s>
      </logic:equal> 
      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="value" format="#,##0.00"/>
      </logic:notEqual>
    </td>
<logic:equal name="showApplicants" value="true">
    <td align="left">
      &nbsp;
    </td>
    <td align="left">
      &nbsp;
    </td>
    <td align="left">
      &nbsp;
    </td>
</logic:equal>
    <% 
    String titleText = ""; 
    %>
    <logic:equal name="bookingDate" property="isCancelled" value="true">
    <logic:notEmpty name="bookingDate" property="cancelText">
    <bean:define id="cancelText" name="bookingDate" property="cancelText"/>
    <% 
    titleText = "title=\""+ cancelText + "\""; 
    %>
    </logic:notEmpty>
    </logic:equal>
    <td align="left" <%= titleText %>>
      <bean:message name="bookingDate" property="statusDescriptionKey"/>
      <logic:notEqual name="BookingViewFormMgr" property="booking.cancelled" value="true">
      <logic:equal name="bookingDate" property="canBeCancelled" value="true">
			<mmj-mgr:hasAccess forward="bookingDateCancel">
			  <html:link forward="bookingDateCancel" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.cancel"/></html:link>
			</mmj-mgr:hasAccess>
      </logic:equal>
      <logic:equal name="bookingDate" property="canBeCancelledCompleted" value="true">
	        <mmj-mgr:hasAccess forward="bookingDateCancelCompleted">
		      <html:link forward="bookingDateCancelCompleted" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.cancelCompleted"/></html:link>
		    </mmj-mgr:hasAccess>
	  </logic:equal>
      </logic:notEqual>

   		<logic:equal name="bookingDate" property="isFilled" value="true">
      	<logic:equal name="bookingDate" property="activated" value="true">
          <bean:message key="text.activated"/>
      	</logic:equal>
      	<logic:equal name="bookingDate" property="canBeActivated" value="true">
					<mmj-mgr:hasAccess forward="bookingDateActivate">
  			  <html:link forward="bookingDateActivate" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId">
            <bean:message key="link.activate"/>
				  </html:link>
					</mmj-mgr:hasAccess>
      	</logic:equal>
      </logic:equal>
    </td>
  </tr>
<logic:equal name="showApplicants" value="true">
<%-- start of applicant details --%>     


<logic:notEqual name="bookingDate" property="bookingGradeApplicantDateId" value="0">

  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td colspan="3">
		<mmj-mgr:hasAccess forward="bookingGradeApplicantView">
		<html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="bookingDate" paramProperty="bookingGradeApplicantId">
    <bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/>
		</html:link>
		</mmj-mgr:hasAccess>
		<mmj-mgr:hasNoAccess forward="bookingGradeApplicantView">
    <bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/>
		</mmj-mgr:hasNoAccess>
    (<bean:write name="bookingDate" property="agencyName"/>)
    <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRate" format="#0.00"/>
	  </td>
	<logic:empty name="bookingDate" property="workedStartTime">
	  <td align="left" colspan="5">
	    &nbsp;
	  </td>
	  <td align="right">
      <logic:equal name="bookingDate" property="isCancelled" value="true">
      <s><bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/></s>
      </logic:equal>
      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
      <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
      </logic:notEqual>
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
    </logic:empty>
	<logic:notEmpty name="bookingDate" property="workedStartTime">
  	<%/* start time has been entered by applicant */%>
  	<logic:equal name="bookingDate" property="workedStatusIsDraft" value="true">
  	<%/* BUT is still DRAFT so cannot be shown to Manager */%>
	  <td align="left" colspan="5">
	    &nbsp;
	  </td>
	  <td align="right">
      <logic:equal name="bookingDate" property="isCancelled" value="true">
      <s><bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/></s>
      </logic:equal>
      <logic:notEqual name="bookingDate" property="isCancelled" value="true">
      <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
      </logic:notEqual>
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
    </logic:equal>
  	<logic:notEqual name="bookingDate" property="workedStatusIsDraft" value="true">
  	<%/* NOT DRAFT so can be shown to manager */%>
	  <td align="left">
	    <bean:write name="bookingDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="workedEndTime" format="HH:mm"/>
	  </td>
	  <td align="left">
	    <bean:write name="bookingDate" property="workedBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="workedBreakEndTime" format="HH:mm"/>
	    (<bean:write name="bookingDate" property="workedBreakNoOfHours" format="#0.00"/>)
	  </td>
    <td align="left">
      &nbsp;
    </td>
    <td align="left">
      &nbsp;
    </td>
	  <td align="left">
	    &nbsp;
	  </td>
    <logic:equal name="BookingViewFormMgr" property="booking.draft" value="false">
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedChargeRateValue" format="#0.00"/>
	  </td>
	  <td align="right" nowrap="nowrap">
      <logic:equal name="bookingDate" property="hasUplift" value="true">
      *
      </logic:equal>
	    <bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
	  </td>
    </logic:equal>
    <td align="left">
  	<logic:equal name="bookingDate" property="canBeAuthorized" value="true">
  	<%/* if it can be authorised it can be rejected - manager will probably have access to both authorise and reject */%>
    <mmj-mgr:hasAccess forward="bookingDateAuthorize">
      <html:link forward="bookingDateAuthorize" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.authorize"/></html:link>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingDateAuthorize">
	    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
    </mmj-mgr:hasNoAccess>
    <mmj-mgr:hasAccess forward="bookingDateReject">
      <html:link forward="bookingDateReject" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.reject"/></html:link>
    </mmj-mgr:hasAccess>
    </logic:equal>
  	<logic:notEqual name="bookingDate" property="canBeAuthorized" value="true">
      <logic:equal name="bookingDate" property="workedStatusIsInvoiced" value="true">
		<mmj-mgr:hasAccess forward="bookingDateView">
		  <html:link forward="bookingDateView" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message name="bookingDate" property="workedStatusDescriptionKey"/></html:link>
		</mmj-mgr:hasAccess>
		<mmj-mgr:hasNoAccess forward="bookingDateView">
    	  <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
		</mmj-mgr:hasNoAccess>

        <logic:greaterThan name="bookingDate" property="invoiceId" value="0">
			    <mmj-mgr:hasAccess forward="invoiceView">
			      <html:link forward="invoiceView" paramId="invoice.invoiceId" paramName="bookingDate" paramProperty="invoiceId"><bean:write name="bookingDate" property="invoiceId"/></html:link>
			    </mmj-mgr:hasAccess>
<%--
			    <mmj-mgr:hasNoAccess forward="invoiceView">
	    	    <bean:write name="bookingDate" property="invoiceId"/>
			    </mmj-mgr:hasNoAccess>
--%>
 				</logic:greaterThan>
      
        <logic:greaterThan name="bookingDate" property="agencyInvoiceId" value="0">
			    <mmj-mgr:hasAccess forward="agencyInvoiceView">
			      <html:link forward="agencyInvoiceView" paramId="agencyInvoice.agencyInvoiceId" paramName="bookingDate" paramProperty="agencyInvoiceId"><bean:write name="bookingDate" property="agencyInvoiceId"/></html:link>
			    </mmj-mgr:hasAccess>
			    <mmj-mgr:hasNoAccess forward="agencyInvoiceView">
	    	    <bean:write name="bookingDate" property="agencyInvoiceId"/>
					</mmj-mgr:hasNoAccess>
        </logic:greaterThan>
      
      </logic:equal>
      <logic:notEqual name="bookingDate" property="workedStatusIsInvoiced" value="true">
  	    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
      </logic:notEqual>
    </logic:notEqual>
	  </td>
    </logic:notEqual>
  </logic:notEmpty>
  </tr>
  <logic:notEmpty name="bookingDate" property="comment">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <th align="left" class="label" colspan="3">
	    <bean:message key="label.comment"/>
	  </th>
	  <td align="left" colspan="9">
      <bean:write name="bookingDate" property="comment"/>
    </td>
  </tr>
  </logic:notEmpty>
  <logic:equal name="bookingDate" property="workedStatusIsRejected" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <th align="left" class="label" colspan="3">
	    <bean:message key="label.rejectText"/>
	  </th>
	  <td align="left" colspan="9">
      <bean:write name="bookingDate" property="rejectText"/>
    </td>
  </tr>
  </logic:equal>
  
  
</logic:notEqual>


<%--
    <bean:define id="bdBookingDateId" name="bookingDate" property="bookingDateId" type="java.lang.Integer" /> 
    <bean:define id="bookingDateCancelled" name="bookingDate" property="isCancelled" type="java.lang.Boolean" />
  	<jsp:include page="bookingViewApplicantInclude.jsp" flush="true">
		  <jsp:param name="bdBookingDateId" value="<%= bdBookingDateId.toString() %>"/>
		  <jsp:param name="bookingDateCancelled" value="<%= bookingDateCancelled.toString() %>"/>
		</jsp:include>
--%>

<%-- end of applicant details --%>
</logic:equal>
<logic:equal name="showApplicants" value="true">
  <tr><th align="left" colspan="12" bgcolor="#000000" height="3"></th></tr>
</logic:equal>
<logic:notEqual name="showApplicants" value="true">
  <tr><th align="left" colspan="9" bgcolor="#000000" height="3"></th></tr>
</logic:notEqual>
</logic:iterate>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="5">
   		<bean:message key="label.total"/>
    </th>
	  <td align="right">
	    <bean:write name="BookingViewFormMgr" property="booking.noOfHours" format="#0.00"/>
	  </td>
    <th align="left">
      &nbsp;
    </th>
    <td align="right">
		  <bean:message key="label.currencySymbol"/><bean:write name="BookingViewFormMgr" property="booking.value" format="#,##0.00" />
    </td>
    <logic:equal name="showApplicants" value="true">
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingViewFormMgr" property="booking.filledValue" format="#,##0.00" />
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingViewFormMgr" property="booking.workedValue" format="#,##0.00" />
    </td>
    <td align="right">
      <bean:write name="BookingViewFormMgr" property="booking.workedNoOfHours" format="#0.00"/>
    </td>
    </logic:equal>
    <th align="left">
   		&nbsp;
    </th>
  </tr>
</table>

<jsp:include page="bookingViewFooter.jsp" flush="true">
  <jsp:param name="xxx" value="xxx"/>
</jsp:include>
   