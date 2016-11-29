<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="theFormAgy" name="theFormAgy" value="ListFormAgy"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<bean:parameter id="showCheckbox" name="showCheckbox" value="false"/>

<bean:parameter id="showHrs" name="showHrs" value="true"/> <%/* this means show hrs column */%>
<bean:parameter id="showAgreed" name="showAgreed" value="true"/> <%/* this means show agreed column */%>
<bean:parameter id="showActuals" name="showActuals" value="true"/> <%/* this means show actual value and hrs columns */%>
<bean:parameter id="showVat" name="showVat" value="false"/> <%/* this means show vat and total columns */%>

<bean:parameter id="bookingDateStatus" name="bookingDateStatus" value="-1"/>
<bean:parameter id="workedStatus" name="workedStatus" value="-1"/>
<mmj-agy:consultant var="consultant"/>

<%
if (bookingDateStatus.equals("")) {
	bookingDateStatus = "-1";
}
switch (Integer.parseInt(bookingDateStatus)) {
	case (com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_DRAFT): 
	case (com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_OPEN): {
		pageContext.setAttribute("showAgreed", "false");
		pageContext.setAttribute("showActuals", "false");
		pageContext.setAttribute("showVat", "false");
		break;
	}
	case (com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_OFFERED): {
		pageContext.setAttribute("showActuals", "false");
		pageContext.setAttribute("showVat", "false");
		break;
	}
	case (-1): { // shift status NOT selected so check for the timesheet status
		if (!workedStatus.equals("")) {
			switch (Integer.parseInt(workedStatus)) {
				case (com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_SUBMITTED): {
					pageContext.setAttribute("showVat", "false");
					break;
				}
				case (com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED): 
				case (com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED): {
					pageContext.setAttribute("showHrs", "false");
					pageContext.setAttribute("showAgreed", "false");
					pageContext.setAttribute("showActuals", "true");
					pageContext.setAttribute("showVat", "true");
					break;
				}
				case (com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_REJECTED): {
					pageContext.setAttribute("showVat", "false");
           break;
				}
			}
		}
	}
}
%>

<bean:define id="totalColumns" value="13"/>
<logic:notEqual name="showHrs" value="true">
  <bean:define id="totalColumns" value="<%= new Integer(Integer.parseInt(totalColumns) - 1).toString() %>"/>
</logic:notEqual>
<logic:notEqual name="showAgreed" value="true">
  <bean:define id="totalColumns" value="<%= new Integer(Integer.parseInt(totalColumns) - 1).toString() %>"/>
</logic:notEqual>
<logic:notEqual name="showActuals" value="true">
  <bean:define id="totalColumns" value="<%= new Integer(Integer.parseInt(totalColumns) - 2).toString() %>"/>
</logic:notEqual>
<logic:notEqual name="showVat" value="true">
  <bean:define id="totalColumns" value="<%= new Integer(Integer.parseInt(totalColumns) - 2).toString() %>"/>
</logic:notEqual>

<bean:define id="totalColumnsLess3" value="<%= new Integer(Integer.parseInt(totalColumns) - 3).toString() %>"/>

<%-- shifts --%>
<logic:notPresent name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormAgy %>" property="<%= theList %>">
<logic:empty name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="<%= theFormAgy %>" property="<%= theList %>">
<logic:equal name="showCheckbox" value="true">
<bean:define id="bdList" name="<%= theFormAgy %>" property="<%= theList %>" type="java.util.List" />
<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers
function toggle(checked) {
  for (i = 0; i < <%= bdList.size() %>; i++) {
    document.forms["BookingDatesFormAgy"].elements["bd" + i].checked = checked;
  }
}
// end hiding contents from old browsers  -->
</script>
</logic:equal>
<table class="simple" width="100%">
<thead>
  <tr>
    <th align="left">
    <logic:equal name="showCheckbox" value="true">
    <input type="checkbox" border="0" title="<bean:message key="text.toggle"/>" onclick="javascript:toggle(this.checked)"/>
    </logic:equal>
      <bean:message key="label.noDot"/>
    </th>
    <th align="left">
			<bean:message key="label.date"/>
    </th>
    <th align="left">
			<bean:message key="label.jobProfile"/>
    </th>
    <th align="left">
			<bean:message key="label.location"/>
    </th>
    <th align="left">
			<bean:message key="label.shift"/>
    </th>
<logic:equal name="showHrs" value="true">
    <th align="left">
			<bean:message key="label.hrs"/>
    </th>
</logic:equal>
<logic:equal name="showAgreed" value="true">
    <th align="right">
			<bean:message key="label.agreed"/>
    </th>
</logic:equal>
<logic:equal name="showActuals" value="true">
    <th align="left">
			<bean:message key="label.hrs"/>
    </th>
    <th align="right">
			<bean:message key="label.actual"/>
    </th>
    <th align="left">
			<bean:message key="label.expenses"/>
    </th>
</logic:equal>
<logic:equal name="showVat" value="true">
    <th align="left">
			<bean:message key="label.vat"/>
    </th>
    <th align="left">
			<bean:message key="label.total"/>
    </th>
</logic:equal>
    <th align="left">
			<bean:message key="label.status"/>
    </th>
  </tr>
</thead>
<logic:iterate id="bookingDate" name="<%= theFormAgy %>" property="<%= theList %>" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
    
  <logic:equal name="showCheckbox" value="true">
    <bean:define id="renderCheckbox" value="false"/>

    <logic:equal name="bookingDate" property="canBeActivated" value="true">
      <bean:define id="renderCheckbox" value="true"/>
    </logic:equal>
    <logic:equal name="bookingDate" property="canBeAuthorized" value="true">
      <bean:define id="renderCheckbox" value="true"/>
    </logic:equal>
    <logic:equal name="bookingDate" property="canBeInvoiced" value="true">
      <bean:define id="renderCheckbox" value="true"/>
    </logic:equal>
    <logic:equal name="renderCheckbox" value="true">
			<html:multibox name="<%= theFormAgy %>" property="selectedBookingDates" styleId="<%= \"bd\" + bookingDateIndex %>">
			  <bean:write name="bookingDate" property="bookingDateId"/>
			</html:multibox>
	  </logic:equal>
  </logic:equal>
    
    <!-- index <bean:write name="bookingDateIndex"/> -->
		<mmj-agy:hasAccess forward="bookingGradeViewSummary"><html:link forward="bookingGradeViewSummary" paramId="bookingGrade.bookingGradeId" paramName="bookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewSummary">
		  <bean:write name="bookingDate" property="bookingId" format="#000"/></html:link>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
<%--
		  <html:link forward="bookingDateViewSummary" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId" titleKey="title.bookingDateViewSummary"><bean:write name="bookingDate" property="bookingDateId" format="#000"/>
		  </html:link>  	  
--%>		  
	  </mmj-agy:hasAccess>	  
	 	<mmj-agy:hasNoAccess forward="bookingGradeViewSummary">
	 	  <bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
	 	</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormatyy"/>&nbsp;
    </td>
    <td align="left">
 			<mmj-agy:hasAccess forward="bookingGradeViewApplicants">
      <html:link forward="bookingGradeViewApplicants" paramId="bookingGrade.bookingGradeId" paramName="bookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewApplicants">    			
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			</html:link>
 			</mmj-agy:hasAccess>
 			<mmj-agy:hasNoAccess forward="bookingGradeViewApplicants">
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="bookingDate" property="locationName"/>,
      <bean:write name="bookingDate" property="siteName"/>
    </td>
    <td align="left">
    <logic:equal name="bookingDate" property="canBeAppliedFor" value="true">
 			<mmj-agy:hasAccess forward="bookingGradeViewShifts">
      <html:link forward="bookingGradeViewShifts" paramId="bookingGrade.bookingGradeId" paramName="bookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewShifts">    			
			<bean:write name="bookingDate" property="shiftName"/>
 			</html:link>
 			</mmj-agy:hasAccess>
 			<mmj-agy:hasNoAccess forward="bookingGradeViewShifts">
			<bean:write name="bookingDate" property="shiftName"/>
 			</mmj-agy:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="bookingDate" property="canBeAppliedFor" value="true">
 			<mmj-agy:hasAccess forward="bookingGradeViewTimesheets">
      <html:link forward="bookingGradeViewTimesheets" paramId="bookingGrade.bookingGradeId" paramName="bookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewTimesheets">    			
			<bean:write name="bookingDate" property="shiftName"/>
 			</html:link>
 			</mmj-agy:hasAccess>
 			<mmj-agy:hasNoAccess forward="bookingGradeViewTimesheets">
			<bean:write name="bookingDate" property="shiftName"/>
 			</mmj-agy:hasNoAccess>
    </logic:notEqual>
    </td>
<logic:equal name="showHrs" value="true">
    <td align="right">
    <logic:equal name="bookingDate" property="undefinedShift" value="true">
      &nbsp;
    </logic:equal>
    <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
      <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
    </logic:notEqual>
    </td>
</logic:equal>
<logic:equal name="showAgreed" value="true">
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
<logic:equal name="showActuals" value="true">
	  <td>
	    &nbsp;
	  </td>
	  <td>
	    &nbsp;
	  </td>
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
<logic:equal name="showVat" value="true">
	  <td>
	    &nbsp;
	  </td>
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
    <td align="left">
    <bean:message name="bookingDate" property="statusDescriptionKey"/>
    </td>
  </tr>
<logic:greaterThan name="bookingDate" property="applicantId" value="0">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td colspan="3">
		<mmj-agy:hasAccess forward="bookingGradeApplicantView">
		<html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="bookingDate" paramProperty="bookingGradeApplicantId">
 	    <bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/>
		</html:link>
		</mmj-agy:hasAccess>
		<mmj-agy:hasNoAccess forward="bookingGradeApplicantView">
	    <bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/>
		</mmj-agy:hasNoAccess>
	  (<bean:write name="bookingDate" property="agencyName"/>)
	    <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRate" format="#0.00"/>
	  </td>   
    <td align="left">
    <logic:equal name="bookingDate" property="undefinedShift" value="true">
      Undefined
    </logic:equal>
    <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
      <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
      (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
    </logic:notEqual>
    </td>
	  <td>
	    &nbsp;
	  </td>
<logic:equal name="showHrs" value="true">
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
<logic:equal name="showAgreed" value="true">
	  <td align="right">
    <logic:equal name="bookingDate" property="undefinedShift" value="true">
      &nbsp;
    </logic:equal>
    <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
	    &nbsp;<bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="wageRateValue" format="#0.00"/>
	    </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Check if Applicant subcontracted... --%>
        <logic:equal name="bookingDate" property="applicantOriginalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
	    &nbsp;<bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="wageRateValue" format="#0.00"/>
	      </logic:equal>
	    </logic:equal>
	  </logic:notEqual>
	  </td>
</logic:equal>
<logic:equal name="showActuals" value="true">
	  <td>
	    &nbsp;
	  </td>
	  <td>
	    &nbsp;
	  </td>
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
<logic:equal name="showVat" value="true">
	  <td>
	    &nbsp;
	  </td>
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
	  <td>
   		<logic:equal name="bookingDate" property="isFilled" value="true">
      	<logic:equal name="bookingDate" property="activated" value="true">
          <bean:message key="text.activated"/>
      	</logic:equal>
      	<logic:equal name="bookingDate" property="canBeActivated" value="true">
            <bean:message key="text.notActivated"/>
      	</logic:equal>
      </logic:equal>
	  </td>
  </tr>
</logic:greaterThan>
<logic:equal name="bookingDate" property="hasBeenEntered" value="true">
  <%/* timesheet has been submitted */%>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left" colspan="3">
      <bean:write name="bookingDate" property="backingReport"/>
    </td>
    <td align="left">
     <bean:write name="bookingDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="workedEndTime" format="HH:mm"/>
     (<bean:write name="bookingDate" property="workedBreakNoOfHours" format="#0.00"/>)
    </td>
	  <td>
	    &nbsp;
	  </td>
<logic:equal name="showHrs" value="true">
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
<logic:equal name="showAgreed" value="true">
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
<logic:equal name="showActuals" value="true">
	  <td align="right" nowrap="nowrap">
      <logic:equal name="bookingDate" property="hasUplift" value="true">
      *
      </logic:equal>
	    <bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedChargeRateValue" format="#0.00"/>
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
	    &nbsp;<bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedWageRateValue" format="#0.00"/>
	</logic:equal>
  <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Check if Applicant subcontracted... --%>
    <logic:equal name="bookingDate" property="applicantOriginalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
	    &nbsp;<bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedWageRateValue" format="#0.00"/>
	  </logic:equal>
	</logic:equal>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="expenseValue" format="#0.00"/>
	  </td>
</logic:equal>
<logic:equal name="showVat" value="true">
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="totalVatValue" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedTotalValue" format="#0.00"/>
	  </td>
</logic:equal>
	  <td>
	  
     	<logic:equal name="bookingDate" property="canBeActivated" value="true">
        <bean:message key="text.notActivated"/>
		</logic:equal>
     	<logic:notEqual name="bookingDate" property="canBeActivated" value="true">
     	  <logic:equal name="bookingDate" property="hasBeenEntered" value="true">
		    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
	    	<logic:equal name="bookingDate" property="workedStatusIsInvoiced" value="true">
	          <logic:greaterThan name="bookingDate" property="agencyInvoiceId" value="0">
			    <mmj-agy:hasAccess forward="agencyInvoiceView">
			      <html:link forward="agencyInvoiceView" paramId="agencyInvoice.agencyInvoiceId" paramName="bookingDate" paramProperty="agencyInvoiceId"><bean:write name="bookingDate" property="agencyInvoiceId"/></html:link>
			    </mmj-agy:hasAccess>
			    <mmj-agy:hasNoAccess forward="agencyInvoiceView">
		          <bean:write name="bookingDate" property="agencyInvoiceId"/>
			    </mmj-agy:hasNoAccess>
	  		  </logic:greaterThan>
            </logic:equal>
	    	<logic:equal name="bookingDate" property="workedStatusIsCredited" value="true">
  		      <mmj-agy:hasAccess forward="agencyInvoiceCreditView">
		        <html:link forward="agencyInvoiceCreditView" paramId="agencyInvoiceCredit.agencyInvoiceCreditId" paramName="bookingDate" paramProperty="agencyInvoiceCreditId">
		          <bean:write name="bookingDate" property="agencyInvoiceCreditId"/>
	            </html:link>
		      </mmj-agy:hasAccess>
		      <mmj-agy:hasNoAccess forward="agencyInvoiceCreditView">
	            <bean:write name="bookingDate" property="agencyInvoiceCreditId"/>
		      </mmj-agy:hasNoAccess>
            </logic:equal>
		  </logic:equal>
   		  <logic:notEqual name="bookingDate" property="hasBeenEntered" value="true">
	        <bean:message key="text.activated"/>
		  </logic:notEqual>
    	</logic:notEqual>
	  
<%--

  	<logic:equal name="bookingDate" property="canBeAuthorized" value="true">
  	<%/* if it can be authorised it can be rejected - manager will probably have access to both authorise and reject */%>
    <mmj-agy:hasAccess forward="bookingDateAuthorize">
      <html:link forward="bookingDateAuthorize" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.authorize"/></html:link>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="bookingDateAuthorize">
	    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
    </mmj-agy:hasNoAccess>
    <mmj-agy:hasAccess forward="bookingDateReject">
      <html:link forward="bookingDateReject" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.reject"/></html:link>
    </mmj-agy:hasAccess>
    </logic:equal>
  	<logic:notEqual name="bookingDate" property="canBeAuthorized" value="true">
    	<logic:equal name="bookingDate" property="workedStatusIsInvoiced" value="true">
		    <mmj-agy:hasAccess forward="bookingDateView">
		      <html:link forward="bookingDateView" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message name="bookingDate" property="workedStatusDescriptionKey"/></html:link>
		    </mmj-agy:hasAccess>
		    <mmj-agy:hasNoAccess forward="bookingDateView">
    	    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
		    </mmj-agy:hasNoAccess>
        <logic:greaterThan name="bookingDate" property="invoiceId" value="0">
			    <mmj-agy:hasAccess forward="invoiceView">
			      <html:link forward="invoiceView" paramId="invoiceAgency.invoiceId" paramName="bookingDate" paramProperty="invoiceId"><bean:write name="bookingDate" property="invoiceId"/></html:link>
			    </mmj-agy:hasAccess>
			    <mmj-agy:hasNoAccess forward="invoiceView">
	    	    <bean:write name="bookingDate" property="invoiceId"/>
			    </mmj-agy:hasNoAccess>
  			</logic:greaterThan>

        <logic:greaterThan name="bookingDate" property="agencyInvoiceId" value="0">
			    <mmj-agy:hasAccess forward="agencyInvoiceView">
			      <html:link forward="agencyInvoiceView" paramId="agencyInvoice.agencyInvoiceId" paramName="bookingDate" paramProperty="agencyInvoiceId"><bean:write name="bookingDate" property="agencyInvoiceId"/></html:link>
			    </mmj-agy:hasAccess>
        </logic:greaterThan>

      </logic:equal>
      <logic:notEqual name="bookingDate" property="workedStatusIsInvoiced" value="true">
  	    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
      </logic:notEqual>
    </logic:notEqual>

--%>

	  </td>
  </tr>
</logic:equal>
<logic:notEmpty name="bookingDate" property="comment">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <th align="left" class="label" colspan="3">
	    <bean:message key="label.comment"/>
	  </th>
	  <td align="left" colspan="<bean:write name="totalColumnsLess3"/>">
      <bean:write name="bookingDate" property="comment"/>
    </td>
  </tr>
</logic:notEmpty> 
<logic:equal name="bookingDate" property="workedStatusIsRejected" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <th align="left" class="label" colspan="3">
	    <bean:message key="label.rejectText"/>
	  </th>
	  <td align="left" colspan="<bean:write name="totalColumnsLess3"/>">
      <bean:write name="bookingDate" property="rejectText"/>
    </td>
  </tr>
</logic:equal>

  <tr><th align="left" colspan="<bean:write name="totalColumns"/>" bgcolor="#000000" height="3"></th></tr>
</logic:iterate>
  <logic:equal name="showTotals" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="5"><bean:message key="label.total"/></th>
<logic:equal name="showHrs" value="true">
		<td align="right"><bean:write name="<%= theFormAgy %>" property="totalHours" format="#0.00"/></td>
</logic:equal>
<logic:equal name="showAgreed" value="true">
		<td align="right">
		  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalAgreedValue" format="#,##0.00"/>
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
		  &nbsp;<bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalAgreedWageRateValue" format="#,##0.00"/>
  </logic:equal>
		</td>
</logic:equal>
<logic:equal name="showActuals" value="true">
		<td align="right"><bean:write name="<%= theFormAgy %>" property="totalActualHours" format="#0.00"/></td>
		<td align="right">
		  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalActualValue" format="#,##0.00"/>
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
		  &nbsp;<bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalActualWageRateValue" format="#,##0.00"/>
  </logic:equal>
		</td>
		<td align="right">
		  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalExpenseValue" format="#,##0.00"/>
		</td>
</logic:equal>
<logic:equal name="showVat" value="true">
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="<%= theFormAgy %>" property="totalTotalVatValue" format="#,##0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="<%= theFormAgy %>" property="totalActualTotalValue" format="#,##0.00"/>
	  </td>
</logic:equal>
		<th align="right">&nbsp;</th>
  </tr>
  </logic:equal>
</table>

</logic:notEmpty>
</logic:present>
    