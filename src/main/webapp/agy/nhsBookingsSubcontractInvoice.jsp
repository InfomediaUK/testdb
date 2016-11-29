<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<script type="text/javascript">
var checked=false;
var frmname='';
function checkedAll(frmname)
{
  var theForm = document.getElementById(frmname);
  var theButton = document.getElementById('invoiceButton');
  if (checked == false)
  {
    checked = true;
    theButton.disabled = false;
  }
  else
  {
    checked = false;
    theButton.disabled = true;
  }
  for (var i =0; i < theForm.elements.length; i++)
  {
    if (theForm.elements[i].type && theForm.elements[i].type === 'checkbox') 
    {
      theForm.elements[i].checked=checked;
    }
  }
}
function checkAny(frmname)
{
  var theForm = document.getElementById(frmname);
  var theButton = document.getElementById('invoiceButton');
  theButton.disabled = true;
  for (var i =0; i < theForm.elements.length; i++)
  {
    if (theForm.elements[i].type && theForm.elements[i].type === 'checkbox') 
    {
      if (theForm.elements[i].checked == true)
      {
    	theButton.disabled = false;
    	break;
      }
    }
  }
}
</script>

<bean:define id="weekToShow" name="NhsBookingsSubcontractInvoiceFormAgy" property="weekToShow" />
<bean:define id="nhsBookingReportGroup" name="NhsBookingsSubcontractInvoiceFormAgy" property="nhsBookingReportGroup" type="com.helmet.application.NhsBookingReportGroup"/>
<%
String nhsBookingCheckBox = "nhsBookingId";
%>


<table cellpadding="0" cellspacing="0" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBookingsSubcontractInvoice"/>
		</td>
		<td align="left" valign="middle">
      &nbsp;
      <bean:write name="NhsBookingsSubcontractInvoiceFormAgy" property="startDate" formatKey="format.longDateFormat" />
      -
      <bean:write name="NhsBookingsSubcontractInvoiceFormAgy" property="endDate" formatKey="format.longDateFormat" />
		</td>
  </tr>
</table>
<html:errors/>
<html:form action="/nhsBookingsSubcontractInvoiceConfirm.do" styleId="NhsBooking" onsubmit="return singleSubmit();">
  <html:hidden name="NhsBookingsSubcontractInvoiceFormAgy" property="fromAgency.agencyId" />
  <html:hidden name="NhsBookingsSubcontractInvoiceFormAgy" property="weekToShow" />
  <html:hidden name="nhsBookingReportGroup" property="clientId" />
  <html:hidden name="nhsBookingReportGroup" property="siteId" />
  <html:hidden name="nhsBookingReportGroup" property="locationId" />
  <html:hidden name="nhsBookingReportGroup" property="jobProfileId" />
	<table class="simple" width="100%">
	  <tr>
	    <th width="25%" align="left" class="label"><bean:message key="label.invoiceDate"/></th>
	    <td align="left">
        <html:text name="NhsBookingsSubcontractInvoiceFormAgy" property="invoiceDateStr" styleId="invoiceDate" title="dd/MM/yyyy"/>
			  <input type="reset" value=" ... " id="invoiceDateButton">
	    </td>
	    <td align="right" width="10%">
        <html:submit styleClass="titleButton" styleId="invoiceButton" disabled="true"><bean:message key="button.invoice"/></html:submit>
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
	    <th align="left" width="100%">
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
	          <th align="center" width="2%">
	            <input type="checkbox" name="checkall" onclick="checkedAll('NhsBooking');"/>
	          </th>
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
	        <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	          <td align="center" valign="top">
	            <input type="checkbox" name="<%= nhsBookingCheckBox %>" value="<%= nhsBooking.getNhsBookingId() %>"  onclick="checkAny('NhsBooking');">    
	          </td>
	          <td align="left" ><%-- Bank Request Number --%>
	            <bean:write name="nhsBooking" property="bankReqNum"/>
	          </td>
	          <td align="left" ><%-- Date --%>
	            <bean:write name="nhsBooking" property="date" format="EEE dd-MMM-yyyy"/>
	          </td>
	          <td align="left" ><%-- Time --%>
	            <bean:write name="nhsBooking" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="nhsBooking" property="workedEndTime" format="HH:mm"/>
  <logic:equal name="nhsBooking" property="workedDiffersFromBooked" value="true">
	            (Booked:&nbsp;<bean:write name="nhsBooking" property="startTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="nhsBooking" property="endTime" format="HH:mm"/>)
	</logic:equal>
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
	          <td colspan="8">
	            &nbsp;
	          </td>
	          <td align="right">
	            <bean:message key="label.currencySymbol"/><bean:write name="nhsBookingReportGroup" property="nhsBookingsToInvoiceValue" format="#,##0.00"/>
	          </td>
	        </tr>
	      </table>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" colspan="2">
	      Notes:-<br />
	      <html:textarea name="NhsBookingsSubcontractInvoiceFormAgy" property="subcontractInvoiceNotes" style="width:100%" styleId="message" cols="100" rows="12" disabled="false" />      
	    </td>
	  </tr>
	</table>
<script type="text/javascript">//<![CDATA[
  var cal1 = Zapatec.Calendar.setup({
		    firstDay          : 1,
		    showOthers        : true,
		    step              : 1,
		    electric          : false,
		    inputField        : "invoiceDate",
		    button            : "invoiceDateButton",
		    ifFormat          : "%d/%m/%Y",
		    daFormat          : "%d/%m/%Y",
        showsTime         : false
		  });
//]]></script>
</html:form>
