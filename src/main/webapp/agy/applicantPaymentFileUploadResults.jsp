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
  var searchResultsForm = document.getElementById(frmname);
  if (checked == false)
  {
    checked = true;
  }
  else
  {
    checked = false;
  }
  for (var i =0; i < searchResultsForm.elements.length; i++)
  {
    searchResultsForm.elements[i].checked=checked;
  }
}
</script>
<% String nhsBookingCheckBox = "nhsBookingId"; %>
<html:form action="/nhsBookingApplicantPaidDateUpdate.do" styleId="ApplicantPaymentResults" onsubmit="return singleSubmit();">
  <html:hidden name="ApplicantPaymentFileUploadFormAgy" property="paymentDateStr" />
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <td align="left" valign="middle" class="title">
      <bean:message key="title.applicantPaymentFileUploadResults"/>
    </td>
  <mmj-agy:hasAccess forward="nhsBackingReportFileAccept">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit>
    </td>
  </mmj-agy:hasAccess>
  </tr>
</table>
<logic:notPresent name="ApplicantPaymentFileUploadFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ApplicantPaymentFileUploadFormAgy" property="list">
	<logic:empty name="ApplicantPaymentFileUploadFormAgy" property="list">
	  <bean:message key="text.noDetails"/>
	</logic:empty>
<logic:notEmpty name="ApplicantPaymentFileUploadFormAgy" property="list">
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
      <td align="left">
        <b>Payment Date:</b> <bean:write name="ApplicantPaymentFileUploadFormAgy" property="paymentDateStr"/>
      </td>
      <td align="left">
        <b>Invalid:</b> <bean:write name="ApplicantPaymentFileUploadFormAgy" property="countInvalidNhsBooking"/>
      </td>
      <td align="left">
        <b>Valid:</b> <bean:write name="ApplicantPaymentFileUploadFormAgy" property="countValidNhsBooking"/>
      </td>
	  </tr>
	</table>
  <table class="simple" width="100%" border="1">
    <thead>
    <tr>
      <th align="left" width="2%">
        <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantPaymentResults');"/>
      </th>
      <th align="left">BankReqNum</th>
      <th align="left">Booking</th>
      <th align="left">Staff Name</th>
      <th align="left">Date</th>
      <th align="left">Start</th>
      <th align="left">End</th>
      <th align="left">Location</th>
      <th align="left">Ward</th>
      <th align="left">Assignment</th>
      <th align="left">Paid</th>
    </tr>
    </thead>
<logic:iterate id="nhsBooking" name="ApplicantPaymentFileUploadFormAgy" property="list" indexId="nhsBookingIndex" type="com.helmet.bean.ApplicantPaymentUpload">
<% 
    String nhsBookingEditAction = request.getContextPath() + "/agy/nhsBookingEdit.do?nhsBookingUser.nhsBookingId=" + nhsBooking.getNhsBookingId();
%>
    <logic:notPresent name="nhsBooking" property="nhsBookingId"><%-- NHS Booking NOT found --%>
      <td align="left" valign="top" class="unmatched"><%-- Blank --%>
        &nbsp;
      </td>
      <td align="left" valign="top" class="unmatched"><%-- BankReqNum --%>
        <bean:write name="nhsBooking" property="bankReqNum"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Booking --%>
        <bean:write name="nhsBooking" property="bookingId"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Staff Name --%>
        <bean:write name="nhsBooking" property="uploadStaffName"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Date --%>
        <bean:write name="nhsBooking" property="uploadDate"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Start Time --%>
        <bean:write name="nhsBooking" property="uploadStart"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- End Time --%>
        <bean:write name="nhsBooking" property="uploadEnd"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Location --%>
        <bean:write name="nhsBooking" property="uploadLocation"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Ward --%>
        <bean:write name="nhsBooking" property="uploadWard"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Assignment --%>
        <bean:write name="nhsBooking" property="uploadAssignment"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Applicant Paid Date --%>
        &nbsp;
      </td>
    </tr>
    </logic:notPresent>
    <logic:present name="nhsBooking" property="nhsBookingId"><%-- NHS Booking found --%>
    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
      <td align="left" valign="top" class="matched"><%-- CheckBox --%>
      <logic:equal name="nhsBooking" property="valid" value="true">
        <input type="checkbox" name="<%= nhsBookingCheckBox %>" value="<%= nhsBooking.getNhsBookingId() %>">    
      </logic:equal>
      <logic:notEqual name="nhsBooking" property="valid" value="true">
        &nbsp;
      </logic:notEqual>
      </td>
      <td align="left" valign="top" class="matched"><%-- BankReqNum --%>
        <mmj-agy:hasAccess forward="nhsBookingEdit">
          <html:link href="<%= nhsBookingEditAction %>" titleKey="title.nhsBookingEdit"><bean:write name="nhsBooking" property="bankReqNum"/></html:link>
        </mmj-agy:hasAccess>
        <mmj-agy:hasNoAccess forward="nhsBookingEdit">
          <bean:write name="nhsBooking" property="bankReqNum"/>
        </mmj-agy:hasNoAccess>
      </td>
      <td align="left" valign="top" class="matched"><%-- Booking --%>
      <logic:greaterThan name="nhsBooking" property="bookingId" value="0">
	      <mmj-agy:hasAccess forward="bookingGradeViewSummary">
	        <html:link forward="bookingGradeViewSummary" paramId="bookingGrade.bookingGradeId" paramName="nhsBooking" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewSummary"><bean:write name="nhsBooking" property="bookingId" format="#000"/></html:link>
	      </mmj-agy:hasAccess>
	      <mmj-agy:hasNoAccess forward="bookingGradeViewSummary">
	        <bean:write name="nhsBooking" property="bookingId" format="#000"/>
	      </mmj-agy:hasNoAccess>
      </logic:greaterThan>
      <logic:equal name="nhsBooking" property="bookingId" value="0">
        &nbsp;
      </logic:equal>
      </td>
      <td align="left" valign="top" class="matched"><%-- Staff Name --%>
        <bean:write name="nhsBooking" property="uploadStaffName"/>
      </td>
      <td align="left" valign="top" class="matched"><%-- Date --%>
        <bean:write name="nhsBooking" property="uploadDate"/>
      </td>
      <td align="left" valign="top" class="matched"><%-- Start Time --%>
        <bean:write name="nhsBooking" property="uploadStart"/>
      </td>
      <td align="left" valign="top" class="matched"><%-- End Time --%>
        <bean:write name="nhsBooking" property="uploadEnd"/>
      </td>
      <td align="left" valign="top" class="matched"><%-- Location --%>
        <bean:write name="nhsBooking" property="uploadLocation"/>
      </td>
      <td align="left" valign="top" class="matched"><%-- Ward --%>
        <bean:write name="nhsBooking" property="uploadWard"/>
      </td>
      <td align="left" valign="top" class="matched"><%-- Assignment --%>
        <bean:write name="nhsBooking" property="uploadAssignment"/>
      </td>
      <td align="left" valign="top" class="matched"><%-- Applicant Paid Date --%>
        <bean:write name="nhsBooking" property="applicantPaidDate" format="dd-MMM-yyyy"/>
      </td>
    </tr>
      <logic:equal name="nhsBooking" property="valid" value="true">
    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
      <td align="left" valign="top" class="matched"><%-- CheckBox --%>
        &nbsp;
      </td>
      <td colspan="10" align="left" valign="top" class="matched"><%-- Comment/BackingReport --%>
    <logic:present name="nhsBooking" property="comment"><%-- Comment --%>
        Comment
        &nbsp;<bean:write name="nhsBooking" property="comment"/>
    </logic:present>
    <logic:present name="nhsBooking" property="backingReport"><%-- BackingReport --%>
        Backing Report
        &nbsp;<bean:write name="nhsBooking" property="backingReport"/>
    </logic:present>
      </td>
    </tr>
      </logic:equal>
      <logic:notEqual name="nhsBooking" property="valid" value="true">
    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
      <td align="left" valign="top" class="unmatched"><%-- CheckBox --%>
        &nbsp;
      </td>
      <td align="left" valign="top" class="unmatched"><%-- BankReqNum --%>
        &nbsp;
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Booking --%>
        &nbsp;
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Staff Name --%>
        <bean:write name="nhsBooking" property="staffName"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Date --%>
        <bean:write name="nhsBooking" property="date" format="dd-MMM-yyyy"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Start Time --%>
        <bean:write name="nhsBooking" property="startTime" format="HH:mm"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- End Time --%>
        <bean:write name="nhsBooking" property="endTime" format="HH:mm"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Location --%>
        <bean:write name="nhsBooking" property="location"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Ward --%>
        <bean:write name="nhsBooking" property="ward"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Assignment --%>
        <bean:write name="nhsBooking" property="assignment"/>
      </td>
      <td align="left" valign="top" class="unmatched"><%-- Assignment --%>
        <bean:write name="nhsBooking" property="applicantPaidDate" format="dd-MMM-yyyy"/>
      </td>
    </tr>
      </logic:notEqual>
    </logic:present>
</logic:iterate>
  </table>
</logic:notEmpty>
</logic:present>
</html:form>
