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
<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers
function toggle(checked) {
  for (i = 0; i < <%= bdList.size() %>; i++) {
    document.forms["BookingDatesFormMgr"].elements["bd" + i].checked = checked;
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
    <bean:message key="label.shiftNo"/>
    </th>
    <th align="left">
			<bean:message key="label.date"/>
    </th>
    <th align="left">
			<bean:message key="label.shift"/>
    </th>
    <th align="left">
			<bean:message key="label.jobProfile"/>
    </th>
    <th align="left">
      <bean:message key="label.location"/>
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
    <th align="left">
			<bean:message key="label.hrs"/>
    </th>
    <th align="right">
			<bean:message key="label.expenses"/>
    </th>
    <th align="right">
			<bean:message key="label.vat"/>
    </th>
    <th align="right">
			<bean:message key="label.total"/>
    </th>
    <th align="left">
			<bean:message key="label.status"/>
    </th>
  </tr>
</thead>
<logic:iterate id="bookingDate" name="<%= theFormMgr %>" property="<%= theList %>" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
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
    <td align="right">
      <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="value" format="#,##0.00"/>
    </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
	  </td>
<logic:empty name="bookingDate" property="workedStartTime">
  	<%/* start time has NOT been entered by applicant */%>
  	<td align="left">
      &nbsp;
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
  	<td align="left">
      &nbsp;
	  </td>
	  <td align="left">
      <bean:message name="bookingDate" property="statusDescriptionKey"/>
	  </td>
</logic:empty>
<logic:notEmpty name="bookingDate" property="workedStartTime">
  	<%/* start time has been entered by applicant */%>
  	<logic:equal name="bookingDate" property="workedStatusIsDraft" value="true">
  	<%/* DRAFT so can NOT be shown to manager */%>
  	<td align="left">
      &nbsp;
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
	  <td align="left">
      <bean:message name="bookingDate" property="statusDescriptionKey"/>
	  </td>
    </logic:equal>  	
  	<logic:notEqual name="bookingDate" property="workedStatusIsDraft" value="true">
  	<%/* NOT DRAFT so can be shown to manager */%>

	  <td align="right">
 	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedChargeRateValue" format="#0.00"/>
	  </td>
	  <td align="right" nowrap="nowrap">
      <logic:equal name="bookingDate" property="hasUplift" value="true">
      *
      </logic:equal>
	    <bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="expenseValue" format="#0.00"/>
	  </td>
    <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="totalVatValue" format="#0.00"/>
    </td>
    <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedTotalValue" format="#0.00"/>
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
	</tr>
</logic:notEmpty>
<logic:notEmpty name="bookingDate" property="comment">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <th align="left" class="label" colspan="2">
	    <bean:message key="label.comment"/>
	  </th>
	  <td align="left" colspan="12">
      <bean:write name="bookingDate" property="comment"/>
    </td>
  </tr>
</logic:notEmpty> 
<logic:equal name="bookingDate" property="workedStatusIsRejected" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <th align="left" class="label" colspan="2">
	    <bean:message key="label.rejectText"/>
	  </th>
	  <td align="left" colspan="12">
      <bean:write name="bookingDate" property="rejectText"/>
    </td>
  </tr>
</logic:equal>
  <tr><th align="left" colspan="14" bgcolor="#000000" height="3"></th></tr>
</logic:iterate>
  <logic:equal name="showTotals" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="5"><bean:message key="label.total"/></th>
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalHours" format="#0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalRRPValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalAgreedValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalActualValue" format="#,##0.00"/></td>
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalActualHours" format="#0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalExpenseValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalTotalVatValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalActualTotalValue" format="#,##0.00"/></td>
		<th align="right">&nbsp;</th>
  </tr>
  </logic:equal>
</table>
</logic:notEmpty>
</logic:present>
