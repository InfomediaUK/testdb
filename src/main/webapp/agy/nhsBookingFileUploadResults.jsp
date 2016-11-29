<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBookingFileUploadResults"/>
		</td>
  </tr>
</table>
<logic:notPresent name="NhsBookingFileUploadFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="NhsBookingFileUploadFormAgy" property="list">
	<logic:empty name="NhsBookingFileUploadFormAgy" property="list">
	  <bean:message key="text.noDetails"/>
	</logic:empty>
<logic:notEmpty name="NhsBookingFileUploadFormAgy" property="list">
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
			<td align="left">
        <b>New:</b> <bean:write name="NhsBookingFileUploadFormAgy" property="countNewNhsBooking"/>
			</td>
			<td align="left">
        <b>Invalid:</b> <bean:write name="NhsBookingFileUploadFormAgy" property="countInvalidNhsBooking"/>
			</td>
			<td align="left">
        <b>Existing:</b> <bean:write name="NhsBookingFileUploadFormAgy" property="countExistingNhsBooking"/>
			</td>
	  </tr>
	</table>
  <table class="simple" width="100%" border="1">
    <thead>
    <tr>
      <th align="left" width="10%">BankReqNum</th>
      <th align="left" width="20%">Staff Name</th>
      <th align="left" width="5%">Date</th>
      <th align="left" width="5%">Start</th>
      <th align="left" width="5%">End</th>
      <th align="left" width="25%">Location</th>
      <th align="left" width="20%">Ward</th>
      <th align="left" width="20%">Assignment</th>
    </tr>
    </thead>
<logic:iterate id="nhsBooking" name="NhsBookingFileUploadFormAgy" property="list" indexId="nhsBookingIndex" type="com.helmet.bean.NhsBookingUpload">
    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <logic:notPresent name="nhsBooking" property="nhsBookingId">
      <td align="left" valign="top"><%-- BankReqNum --%>
        <bean:write name="nhsBooking" property="bankReqNum"/>
      </td>
      <td align="left" valign="top"><%-- Staff Name --%>
        <bean:write name="nhsBooking" property="staffName"/>
      </td>
      <td align="left" valign="top"><%-- Date --%>
        <bean:write name="nhsBooking" property="nhsDate"/>
      </td>
      <td align="left" valign="top"><%-- Start Time --%>
        <bean:write name="nhsBooking" property="nhsStart"/>
      </td>
      <td align="left" valign="top"><%-- End Time --%>
        <bean:write name="nhsBooking" property="nhsEnd"/>
      </td>
      <td align="left" valign="top"><%-- Location --%>
        <bean:write name="nhsBooking" property="location"/>
      </td>
      <td align="left" valign="top"><%-- Ward --%>
        <bean:write name="nhsBooking" property="ward"/>
      </td>
      <td align="left" valign="top"><%-- Assignment --%>
        <bean:write name="nhsBooking" property="assignment"/>
      </td>
    </logic:notPresent>
    <logic:present name="nhsBooking" property="nhsBookingId">
      <td align="left" valign="top" class="nhsMatched"><%-- BankReqNum --%>
        <bean:write name="nhsBooking" property="bankReqNum"/>
      </td>
      <td align="left" valign="top" class="nhsMatched"><%-- Staff Name --%>
        <bean:write name="nhsBooking" property="staffName"/>
      </td>
      <td align="left" valign="top" class="nhsMatched"><%-- Date --%>
        <bean:write name="nhsBooking" property="date" format="dd-MMM-yyyy"/>
      </td>
      <td align="left" valign="top" class="nhsMatched"><%-- Start Time --%>
        <bean:write name="nhsBooking" property="startTime" format="HH:mm"/>
      </td>
      <td align="left" valign="top" class="nhsMatched"><%-- End Time --%>
        <bean:write name="nhsBooking" property="endTime" format="HH:mm"/>
      </td>
      <td align="left" valign="top" class="nhsMatched"><%-- Location --%>
        <bean:write name="nhsBooking" property="location"/>
      </td>
      <td align="left" valign="top" class="nhsMatched"><%-- Ward --%>
        <bean:write name="nhsBooking" property="ward"/>
      </td>
      <td align="left" valign="top" class="nhsMatched"><%-- Assignment --%>
        <bean:write name="nhsBooking" property="assignment"/>
      </td>
    </logic:present>
    </tr>
<logic:notPresent name="nhsBooking" property="nhsBookingId">
  <logic:notEqual name="nhsBooking" property="valid" value="true">
    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
      <td align="left" valign="top"><%-- BankReqNum --%>
        &nbsp;
      </td>
    <logic:equal name="nhsBooking" property="nhsStaffNameMatchedApplicant" value="true">
      <td align="left" valign="top" class="nhsMatched"><%-- Applicant Full Name --%>
    </logic:equal>
    <logic:notEqual name="nhsBooking" property="nhsStaffNameMatchedApplicant" value="true">
      <td align="left" valign="top"><%-- Applicant Full Name --%>
    </logic:notEqual>
        <bean:write name="nhsBooking" property="applicantLastName"/>&nbsp;<bean:write name="nhsBooking" property="applicantFirstName"/>
      </td>
      <td align="left" valign="top"><%-- Date --%>
    <logic:notEqual name="nhsBooking" property="validDate" value="true">
        Invalid
    </logic:notEqual>
    <logic:equal name="nhsBooking" property="validDate" value="true">
        &nbsp;
    </logic:equal>
      </td>
    <logic:equal name="nhsBooking" property="nhsStartAndEndMatchedShift" value="true">
      <td align="left" valign="top" class="nhsMatched" colspan="2"><%-- Start & End --%>
        <bean:write name="nhsBooking" property="shiftName"/>
      </td>
    </logic:equal>
    <logic:notEqual name="nhsBooking" property="nhsStartAndEndMatchedShift" value="true">
    <logic:equal name="nhsBooking" property="bothTimesValid" value="true">
      <td align="left" valign="top" class="nhsMatched" colspan="2"><%-- Start & End --%>
        No Shift
      </td>
    </logic:equal>
    <logic:notEqual name="nhsBooking" property="bothTimesValid" value="true">
      <td align="left" valign="top"><%-- Start --%>
      <logic:notEqual name="nhsBooking" property="validStart" value="true">
        Invalid
      </logic:notEqual>
      <logic:equal name="nhsBooking" property="validStart" value="true">
        &nbsp;
      </logic:equal>
      </td>
      <td align="left" valign="top"><%-- End --%>
      <logic:notEqual name="nhsBooking" property="validEnd" value="true">
        Invalid
      </logic:notEqual>
      <logic:equal name="nhsBooking" property="validEnd" value="true">
        &nbsp;
      </logic:equal>
      </td>
    </logic:notEqual>
    </logic:notEqual>
    <logic:equal name="nhsBooking" property="nhsLocationMatchedSite" value="true">
      <td align="left" valign="top" class="nhsMatched"><%-- Site --%>
    </logic:equal>
    <logic:notEqual name="nhsBooking" property="nhsLocationMatchedSite" value="true">
      <td align="left" valign="top"><%-- Site --%>
    </logic:notEqual>
        <bean:write name="nhsBooking" property="siteName"/>
      </td>
    <logic:equal name="nhsBooking" property="nhsWardMatchedLocation" value="true">
      <td align="left" valign="top" class="nhsMatched"><%-- Location --%>
    </logic:equal>
    <logic:notEqual name="nhsBooking" property="nhsWardMatchedLocation" value="true">
      <td align="left" valign="top"><%-- Location --%>
    </logic:notEqual>
        <bean:write name="nhsBooking" property="locationName"/>
      </td>
      <td align="left" valign="top"><%-- Assignment --%>
        <bean:write name="nhsBooking" property="jobProfileName"/>
      </td>
    </tr>
  </logic:notEqual>
</logic:notPresent>
</logic:iterate>
  </table>
</logic:notEmpty>
</logic:present>