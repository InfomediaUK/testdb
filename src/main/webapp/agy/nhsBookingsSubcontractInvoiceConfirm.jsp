<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="weekToShow" name="NhsBookingsSubcontractInvoiceFormAgy" property="weekToShow" />
<bean:define id="nhsBookingReportGroup" name="NhsBookingsSubcontractInvoiceFormAgy" property="nhsBookingReportGroup" type="com.helmet.application.NhsBookingReportGroup"/>

<table cellpadding="0" cellspacing="0" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBookingsSubcontractInvoiceConfirm"/>
		</td>
		<td align="left" valign="middle">
      &nbsp;
      <bean:write name="NhsBookingsSubcontractInvoiceFormAgy" property="startDate" formatKey="format.longDateFormat" />
      -
      <bean:write name="NhsBookingsSubcontractInvoiceFormAgy" property="endDate" formatKey="format.longDateFormat" />
		</td>
  </tr>
</table>
<html:form action="/nhsBookingsSubcontractInvoiceProcess.do" onsubmit="return singleSubmit();">
  <html:hidden name="NhsBookingsSubcontractInvoiceFormAgy" property="fromAgency.agencyId" />
  <html:hidden name="NhsBookingsSubcontractInvoiceFormAgy" property="weekToShow" />
  <html:hidden name="NhsBookingsSubcontractInvoiceFormAgy" property="invoiceDateStr" />
  <html:hidden name="NhsBookingsSubcontractInvoiceFormAgy" property="subcontractInvoiceNotes" />
  <html:hidden name="nhsBookingReportGroup" property="clientId" />
  <html:hidden name="nhsBookingReportGroup" property="siteId" />
  <html:hidden name="nhsBookingReportGroup" property="locationId" />
  <html:hidden name="nhsBookingReportGroup" property="jobProfileId" />
	<table class="simple" width="100%">
	  <tr>
	    <th width="25%" align="left" class="label"><bean:message key="label.invoiceDate"/></th>
	    <td align="left">
          <bean:write name="NhsBookingsSubcontractInvoiceFormAgy" property="invoiceDateStr"/>
	    </td>
	    <td align="right" width="10%">
<logic:greaterThan name="nhsBookingReportGroup" property="noOfNhsBookings" value="0">
          <html:submit  styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
</logic:greaterThan>
<logic:equal name="nhsBookingReportGroup" property="noOfNhsBookings" value="0">
          No Bookings
</logic:equal>
	    </td>
	  </tr>
	</table>	
	<table class="simple" width="100%" border="1">
	  <thead>
	  <tr>
	    <th align="left">
	      <bean:write name="NhsBookingsSubcontractInvoiceFormAgy" property="fromAgency.name"/> 
	    </th>
	  </tr>
	  <tr>
	    <th align="left">
	      <bean:write name="nhsBookingReportGroup" property="clientName"/>, 
	      <bean:write name="nhsBookingReportGroup" property="siteName"/>,
	      <bean:write name="nhsBookingReportGroup" property="locationName"/>,
	      <bean:write name="nhsBookingReportGroup" property="jobProfileName"/>&nbsp;(<bean:write name="nhsBookingReportGroup" property="jobFamilyCode"/>.<bean:write name="nhsBookingReportGroup" property="jobSubFamilyCode"/>)&nbsp;-&nbsp;<bean:write name="nhsBookingReportGroup" property="assignment"/>&nbsp;
				(<bean:write name="nhsBookingReportGroup" property="noOfNhsBookings"/>&nbsp;<logic:equal name="nhsBookingReportGroup" property="noOfNhsBookings" value="1">Booking</logic:equal><logic:greaterThan name="nhsBookingReportGroup" property="noOfNhsBookings" value="1">Bookings</logic:greaterThan>)
	    </th>
	  </tr>
	  </thead>
	  <tr>
	    <td>
	      <table class="simple" width="100%" border="1">
	        <thead>
	        <tr>
	          <th align="left" width="9%">Bank Request</th>
	          <th align="left" width="11%">Date</th>
	          <th align="left">Worked</th>
	          <th align="left" width="8%">Hours</th>
	          <th align="left">Applicant</th>
	          <th align="left">Comment</th>
	          <th align="right" width="10%"><bean:message key="label.rate"/></th>
	          <th align="right" width="10%"><bean:message key="label.value"/></th>
	        </tr>
	        </thead>
	      <logic:iterate id="nhsBooking" name="nhsBookingReportGroup" property="listNhsBookingUser" indexId="nhsBookingIndex" type="com.helmet.bean.NhsBookingUser">
          <html:hidden name="nhsBooking" property="nhsBookingId" />
	        <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	          <td align="left" ><%-- Bank Request Number --%>
	            <bean:write name="nhsBooking" property="bankReqNum"/>
	          </td>
	          <td align="left" ><%-- Date --%>
	            <bean:write name="nhsBooking" property="date" format="EEE dd-MMM-yyyy"/>
	          </td>
	          <td align="left" ><%-- Worked Times --%>
	            <bean:write name="nhsBooking" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="nhsBooking" property="workedEndTime" format="HH:mm"/>
	          </td>
	          <td align="left" ><%-- Worked Number of Hours --%>
	            <bean:write name="nhsBooking" property="workedNoOfHours" format="##0.00"/>
	          </td>
	          <td align="left" ><%-- Applicant Full Name --%>
	            <bean:write name="nhsBooking" property="applicantFullName"/>
	<logic:greaterThan name="nhsBooking" property="applicantOriginalAgencyId" value="0"><%-- Applicant IS subcontracted. Flag it up... --%>
	            *
	</logic:greaterThan>
	          </td>
	          <td align="left" ><%-- Comment --%>
	            <bean:write name="nhsBooking" property="comment" />
	          </td>
	          <td align="right" ><%-- Value --%>
	            <bean:message key="label.currencySymbol"/><bean:write name="nhsBooking" property="rate" format="#0.00"/>
	          </td>
	          <td align="right" ><%-- Value --%>
	            <bean:message key="label.currencySymbol"/><bean:write name="nhsBooking" property="value" format="#,##0.00"/>
	          </td>
	        </tr>
	      </logic:iterate>
	        <tr>
	          <td align="right" colspan="7">
	            Invoice Total
	          </td>
	          <td align="right">
	            <bean:message key="label.currencySymbol"/><bean:write name="nhsBookingReportGroup" property="nhsBookingsToInvoiceValue" format="#,##0.00"/>
	          </td>
	        </tr>
	      </table>
	    </td>
	  </tr>
<logic:notEmpty name="NhsBookingsSubcontractInvoiceFormAgy" property="subcontractInvoiceNotes">
	  <tr>
	    <td align="left" colspan="2">
	      Notes:-<br />
	      <html:textarea name="NhsBookingsSubcontractInvoiceFormAgy" property="subcontractInvoiceNotes" style="width:100%" styleId="message" cols="100" rows="12" disabled="true" />      
	    </td>
	  </tr>
</logic:notEmpty>
	</table>
</html:form>
