<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<bean:parameter id="theFormMgr" name="theFormMgr" value="ListFormMgr"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<bean:parameter id="showCheckbox" name="showCheckbox" value="false"/>

<bean:parameter id="showHrs" name="showHrs" value="true"/> <%/* this means show hrs column */%>
<bean:parameter id="showRRP" name="showRRP" value="true"/> <%/* this means show rrp column */%>
<bean:parameter id="showAgreed" name="showAgreed" value="true"/> <%/* this means show agreed column */%>
<bean:parameter id="showActuals" name="showActuals" value="true"/> <%/* this means show actual value and hrs columns */%>
<bean:parameter id="showVat" name="showVat" value="false"/> <%/* this means show vat and total columns */%>

<bean:parameter id="bookingDateStatus" name="bookingDateStatus" value="-1"/>
<bean:parameter id="workedStatus" name="workedStatus" value="-1"/>

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
					pageContext.setAttribute("showRRP", "false");
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

<bean:define id="totalColumns" value="14"/>
<logic:notEqual name="showHrs" value="true">
  <bean:define id="totalColumns" value="<%= new Integer(Integer.parseInt(totalColumns) - 1).toString() %>"/>
</logic:notEqual>
<logic:notEqual name="showRRP" value="true">
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
<logic:notPresent name="<%= theFormMgr %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormMgr %>" property="<%= theList %>">
<logic:empty name="<%= theFormMgr %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="<%= theFormMgr %>" property="<%= theList %>">
<logic:equal name="showCheckbox" value="true">
<bean:define id="bdList" name="<%= theFormMgr %>" property="<%= theList %>" type="java.util.List" />
<script type="text/javascript">
function toggle(checked) {
  for (i = 0; i < <%= bdList.size() %>; i++) {
    document.forms["BookingDatesFormMgr"].elements["bd" + i].checked = checked;
  }
}
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
<logic:equal name="showRRP" value="true">
    <th align="right">
			<bean:message key="label.rrp"/>
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
<logic:iterate id="bookingDate" name="<%= theFormMgr %>" property="<%= theList %>" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
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
			<html:multibox name="<%= theFormMgr %>" property="selectedBookingDates" styleId="<%= \"bd\" + bookingDateIndex %>">
			  <bean:write name="bookingDate" property="bookingDateId"/>
			</html:multibox>
	  </logic:equal>
  </logic:equal>
    <!-- index <bean:write name="bookingDateIndex"/> -->
	<mmj-mgr:hasAccess forward="bookingViewSummary"><html:link forward="bookingViewSummary" paramId="booking.bookingId" paramName="bookingDate" paramProperty="bookingId" titleKey="title.bookingViewSummary">
	  <bean:write name="bookingDate" property="bookingId" format="#000"/></html:link>.<html:link forward="bookingDateViewSummary" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId" titleKey="title.bookingDateViewSummary"><bean:write name="bookingDate" property="bookingDateId" format="#000"/>
	  </html:link>  	  
    </mmj-mgr:hasAccess>	  
 	<mmj-mgr:hasNoAccess forward="bookingViewSummary">
 	  <bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
 	</mmj-mgr:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormatyy"/>&nbsp;
    </td>
    <td align="left">
      <logic:equal name="bookingDate" property="isDraft" value="true">
 			<bean:write name="bookingDate" property="jobProfileName"/>
      </logic:equal>
      <logic:notEqual name="bookingDate" property="isDraft" value="true">
 			<mmj-mgr:hasAccess forward="bookingViewApplicants">
      <html:link forward="bookingViewApplicants" paramId="booking.bookingId" paramName="bookingDate" paramProperty="bookingId" titleKey="title.bookingViewApplicants">    			
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			</html:link>
 			</mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingViewApplicants">
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			</mmj-mgr:hasNoAccess>
 			</logic:notEqual>
    </td>
    <td align="left">
      <bean:write name="bookingDate" property="locationName"/>,
      <bean:write name="bookingDate" property="siteName"/>
    </td>
    <td align="left">
    <logic:equal name="bookingDate" property="isDraft" value="true">
 			<mmj-mgr:hasAccess forward="bookingViewShifts">
      <html:link forward="bookingViewShifts" paramId="booking.bookingId" paramName="bookingDate" paramProperty="bookingId" titleKey="title.bookingViewShifts">    			
			<bean:write name="bookingDate" property="shiftName"/>
 			</html:link>
 			</mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingViewShifts">
			<bean:write name="bookingDate" property="shiftName"/>
 			</mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="bookingDate" property="isDraft" value="true">
 			<mmj-mgr:hasAccess forward="bookingViewTimesheets">
      <html:link forward="bookingViewTimesheets" paramId="booking.bookingId" paramName="bookingDate" paramProperty="bookingId" titleKey="title.bookingViewTimesheets">    			
			<bean:write name="bookingDate" property="shiftName"/>
 			</html:link>
 			</mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingViewTimesheets">
			<bean:write name="bookingDate" property="shiftName"/>
 			</mmj-mgr:hasNoAccess>
    </logic:notEqual>
    </td>
<logic:equal name="showHrs" value="true">
    <td align="right">
      <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
    </td>
</logic:equal>
<logic:equal name="showRRP" value="true">
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="value" format="#,##0.00"/>
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
    </td>
  </tr>
<logic:greaterThan name="bookingDate" property="applicantId" value="0">
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
    <bean:write name="bookingDate" property="gradeName"/>
    <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRate" format="#0.00"/>
	  </td>   
    <td align="left">
     <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
     (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
    </td>
	  <td>
	    &nbsp;
	  </td>
<logic:equal name="showHrs" value="true">
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
<logic:equal name="showRRP" value="true">
	  <td>
	    &nbsp;
	  </td>
</logic:equal>
<logic:equal name="showAgreed" value="true">
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
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
					<mmj-mgr:hasAccess forward="bookingDateActivate">
  			  <html:link forward="bookingDateActivate" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId">
            <bean:message key="link.activate"/>
				  </html:link>
					</mmj-mgr:hasAccess>
					<mmj-mgr:hasNoAccess forward="bookingDateActivate">
            <bean:message key="text.notActivated"/>
					</mmj-mgr:hasNoAccess>
      	</logic:equal>
      </logic:equal>
	  </td>
  </tr>
</logic:greaterThan>
<logic:notEqual name="bookingDate" property="workedStatusIsDraft" value="true">
  <%/* timesheet has been submitted */%>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left" colspan="3">
      &nbsp;
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
<logic:equal name="showRRP" value="true">
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
  </tr>
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
</logic:notEqual>
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
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalHours" format="#0.00"/></td>
</logic:equal>
<logic:equal name="showRRP" value="true">
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalRRPValue" format="#,##0.00"/></td>
</logic:equal>
<logic:equal name="showAgreed" value="true">
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalAgreedValue" format="#,##0.00"/></td>
</logic:equal>
<logic:equal name="showActuals" value="true">
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalActualHours" format="#0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalActualValue" format="#,##0.00"/></td>
		<td align="right">
		  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalExpenseValue" format="#,##0.00"/>
		</td>
</logic:equal>
<logic:equal name="showVat" value="true">
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="<%= theFormMgr %>" property="totalTotalVatValue" format="#,##0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="<%= theFormMgr %>" property="totalActualTotalValue" format="#,##0.00"/>
	  </td>
</logic:equal>
		<th align="right">&nbsp;</th>
  </tr>
  </logic:equal>
</table>

<%--

<br/>
<br/>
<br/>

<table class="simple" width="100%">
<thead>
  <tr>
    <th align="left">
    <logic:equal name="showCheckbox" value="true">
    <input type="checkbox" border="0" title="<bean:message key="text.toggle"/>" onclick="javascript:toggle(this.checked)"/>
    </logic:equal>
    <bean:message key="label.shiftNo"/>
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
			<bean:message key="label.rrp"/>
    </th>
    <th align="right">
			<bean:message key="label.agreed"/>
    </th>
    <th align="right">
			<bean:message key="label.actual"/>
    </th>
    <logic:equal name="showVat" value="true">
    <th align="right">
			<bean:message key="label.vat"/>
    </th>
    </logic:equal>
    <th align="left">
			<bean:message key="label.hrs"/>
    </th>
    <th align="left">
			<bean:message key="label.status"/>
    </th>
  </tr>
</thead>
<logic:iterate id="bookingDate" name="<%= theFormMgr %>" property="<%= theList %>" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
  <logic:equal name="showCheckbox" value="true">
    <logic:equal name="bookingDate" property="canBeInvoiced" value="true">
			<html:multibox name="<%= theFormMgr %>" property="selectedBookingDates" styleId="<%= "bd" + bookingDateIndex %>">
			  <bean:write name="bookingDate" property="bookingDateId"/>
			</html:multibox>
	  </logic:equal>
  </logic:equal>
      <!-- index <bean:write name="bookingDateIndex"/> -->
 			<mmj-mgr:hasAccess forward="bookingViewSummary">
       <html:link forward="bookingViewSummary" paramId="booking.bookingId" paramName="bookingDate" paramProperty="bookingId" titleKey="title.bookingViewSummary">    			
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
 			</html:link>
 			</mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingViewSummary">
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
 			</mmj-mgr:hasNoAccess>
    </td>
    <bean:define id="colspan" value="9"/>
    <logic:equal name="showVat" value="true">
	    <bean:define id="colspan" value="10"/>
    </logic:equal>
    <td align="left" colspan="<bean:write name="colspan"/>">
      <logic:equal name="bookingDate" property="isDraft" value="true">
 			<bean:write name="bookingDate" property="jobProfileName"/>
      </logic:equal>
      <logic:notEqual name="bookingDate" property="isDraft" value="true">
 			<mmj-mgr:hasAccess forward="bookingViewApplicants">
      <html:link forward="bookingViewApplicants" paramId="booking.bookingId" paramName="bookingDate" paramProperty="bookingId" titleKey="title.bookingViewApplicants">    			
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			</html:link>
 			</mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingViewApplicants">
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			</mmj-mgr:hasNoAccess>
 			</logic:notEqual>
      (<bean:write name="bookingDate" property="locationName"/>,
      <bean:write name="bookingDate" property="siteName"/>)
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>&nbsp;
    </td>
    <td align="left">
    <logic:equal name="bookingDate" property="isDraft" value="true">
 			<mmj-mgr:hasAccess forward="bookingViewShifts">
      <html:link forward="bookingViewShifts" paramId="booking.bookingId" paramName="bookingDate" paramProperty="bookingId" titleKey="title.bookingViewShifts">    			
			<bean:write name="bookingDate" property="shiftName"/>
 			</html:link>
 			</mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingViewShifts">
			<bean:write name="bookingDate" property="shiftName"/>
 			</mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="bookingDate" property="isDraft" value="true">
 			<mmj-mgr:hasAccess forward="bookingViewTimesheets">
      <html:link forward="bookingViewTimesheets" paramId="booking.bookingId" paramName="bookingDate" paramProperty="bookingId" titleKey="title.bookingViewTimesheets">    			
			<bean:write name="bookingDate" property="shiftName"/>
 			</html:link>
 			</mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingViewTimesheets">
			<bean:write name="bookingDate" property="shiftName"/>
 			</mmj-mgr:hasNoAccess>
    </logic:notEqual>
    </td>
    <td align="left">
     <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
    </td>
    <td align="left">
      <bean:write name="bookingDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftBreakEndTime" format="HH:mm"/>
<%/*
      (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
*/%>
    </td>
    <td align="right">
      <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="value" format="#,##0.00"/>
    </td>
    <td align="left">
      &nbsp;
    </td>
    <td align="left">
      &nbsp;
    </td>
    <logic:equal name="showVat" value="true">
    <td align="left">
      &nbsp;
    </td>
    </logic:equal>    
    <td align="left">
      &nbsp;
    </td>
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
      <logic:equal name="bookingDate" property="canBeCancelled" value="true">
 	    <mmj-mgr:hasAccess forward="bookingDateCancel">
	    <html:link forward="bookingDateCancel" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.cancel"/></html:link>
	    </mmj-mgr:hasAccess>
      </logic:equal>
      
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
<logic:notEmpty name="bookingDate" property="applicantFirstName">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td colspan="2" nowrap="true">
		<mmj-mgr:hasAccess forward="bookingGradeApplicantView">
		<html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="bookingDate" paramProperty="bookingGradeApplicantId">
 	    <bean:write name="bookingDate" property="applicantFirstName"/>
	    <bean:write name="bookingDate" property="applicantLastName"/>
		</html:link>
		</mmj-mgr:hasAccess>
		<mmj-mgr:hasNoAccess forward="bookingGradeApplicantView">
	    <bean:write name="bookingDate" property="applicantFirstName"/>
	    <bean:write name="bookingDate" property="applicantLastName"/>
		</mmj-mgr:hasNoAccess>
	  (<bean:write name="bookingDate" property="agencyName"/>)
	    <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRate" format="#0.00"/>
	  </td>   
<logic:empty name="bookingDate" property="workedStartTime">
	  <td align="left" colspan="4">
	    &nbsp;
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
    <logic:equal name="showVat" value="true">
    <td align="left">
      &nbsp;
    </td>
    </logic:equal>    
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
	  <td align="left" colspan="4">
	    &nbsp;
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
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
	  </td>
    <td align="left">
      &nbsp;
    </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedChargeRateValue" format="#0.00"/>
	  </td>
    <logic:equal name="showVat" value="true">
    <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedVatValue" format="#0.00"/>
    </td>
    </logic:equal>    
	  <td align="right">
	    <bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
	  </td>
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
<%/*
    	<logic:equal name="bookingDate" property="canBeInvoiced" value="true">
		    <mmj-mgr:hasAccess forward="bookingDateInvoice">
		      <html:link forward="bookingDateInvoice" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message key="link.invoice"/></html:link>
		    </mmj-mgr:hasAccess>
      </logic:equal>
    	<logic:notEqual name="bookingDate" property="canBeInvoiced" value="true">
  	    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
      </logic:notEqual>
*/%>
    	<logic:equal name="bookingDate" property="workedStatusIsInvoiced" value="true">
		    <mmj-mgr:hasAccess forward="bookingDateView">
		      <html:link forward="bookingDateView" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId"><bean:message name="bookingDate" property="workedStatusDescriptionKey"/></html:link>
		    </mmj-mgr:hasAccess>
		    <mmj-mgr:hasNoAccess forward="bookingDateView">
    	    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
		    </mmj-mgr:hasNoAccess>
		    <mmj-mgr:hasAccess forward="invoiceView">
		      <html:link forward="invoiceView" paramId="invoice.invoiceId" paramName="bookingDate" paramProperty="invoiceId"><bean:write name="bookingDate" property="invoiceId"/></html:link>
		    </mmj-mgr:hasAccess>
		    <mmj-mgr:hasNoAccess forward="invoiceView">
    	    <bean:write name="bookingDate" property="invoiceId"/>
		    </mmj-mgr:hasNoAccess>
      </logic:equal>
      <logic:notEqual name="bookingDate" property="workedStatusIsInvoiced" value="true">
  	    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
      </logic:notEqual>
    </logic:notEqual>
	  </td>
	  </logic:notEqual>
</logic:notEmpty>
	</tr>
</logic:notEmpty>
<logic:equal name="bookingDate" property="workedStatusIsRejected" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left" colspan="2">
	    &nbsp;
	  </td>
    <bean:define id="colspan" value="8"/>
    <logic:equal name="showVat" value="true">
	    <bean:define id="colspan" value="9"/>
    </logic:equal>
	  <td align="left" colspan="<bean:write name="colspan"/>">
      <bean:write name="bookingDate" property="rejectText"/>
    </td>
  </tr>
</logic:equal>
  <bean:define id="colspan" value="10"/>
  <logic:equal name="showVat" value="true">
   <bean:define id="colspan" value="11"/>
  </logic:equal>
  <tr><th align="left" colspan="<bean:write name="colspan"/>" bgcolor="#000000" height="3"></th></tr>
</logic:iterate>
  <logic:equal name="showTotals" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="4"><bean:message key="label.total"/></th>
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalHours" format="#0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalRRPValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalAgreedValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalActualValue" format="#,##0.00"/></td>
  <logic:equal name="showVat" value="true">
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalActualVatValue" format="#,##0.00"/></td>
  </logic:equal>
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalActualHours" format="#0.00"/></td>
		<th align="right">&nbsp;</th>
  </tr>
  </logic:equal>
</table>
--%>



</logic:notEmpty>
</logic:present>
