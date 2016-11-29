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
      <bean:define id="list" name="NhsBookingsBookFormAgy" property="list" type="java.util.List"/>
      <bean:message key="title.nhsBookingsReady"/>&nbsp;(<%=list.size() %>)
		</td>
  </tr>
</table>
<logic:notPresent name="NhsBookingsBookFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="NhsBookingsBookFormAgy" property="list">
  <logic:empty name="NhsBookingsBookFormAgy" property="list">
    <bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="NhsBookingsBookFormAgy" property="list">
  <table class="simple" width="100%" border="1">
    <thead>
    <tr>
      <th align="left">Client</th>
      <th align="left">Site</th>
      <th align="left">Location (Ward)</th>
      <th align="left">Job Profile - Assignment</th>
      <th align="left">Group</th>
      <th align="left">Date</th>
      <th align="left">Start</th>
      <th align="left">End</th>
      <th align="left">Applicant</th>
			<th align="left">*</th>
    </tr>
    </thead>
<logic:iterate id="nhsBooking" name="NhsBookingsBookFormAgy" property="list" indexId="nhsBookingIndex" type="com.helmet.bean.NhsBookingUser">
    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
      <td align="left"><%-- Client Name --%>
        <bean:write name="nhsBooking" property="clientName"/>
      </td>
      <td align="left"><%-- Site Name (Location) --%>
        <bean:write name="nhsBooking" property="siteName"/>
      </td>
      <td align="left"><%-- Location Name (Ward) --%>
        <bean:write name="nhsBooking" property="locationName"/>
      </td>
      <td align="left"><%-- Assignment --%>
        <bean:write name="nhsBooking" property="jobProfileName"/>&nbsp;(<bean:write name="nhsBooking" property="jobFamilyCode"/>.<bean:write name="nhsBooking" property="jobSubFamilyCode"/>)&nbsp;-&nbsp;<bean:write name="nhsBooking" property="assignment"/>
      </td>
      <td align="left"><%-- Booking GRoup --%>
        <bean:write name="nhsBooking" property="bookingGroupName"/>
      </td>
      <td align="left"><%-- Date --%>
        <bean:write name="nhsBooking" property="date" format="EEE dd-MMM-yyyy"/>
      </td>
      <td align="left"><%-- Start Time --%>
        <bean:write name="nhsBooking" property="startTime" format="HH:mm"/>
      </td>
      <td align="left"><%-- End Time --%>
        <bean:write name="nhsBooking" property="endTime" format="HH:mm"/>
      </td>
      <td align="left"><%-- Applicant Full Name --%>
        <bean:write name="nhsBooking" property="applicantFullName"/>
      </td>
<%
String nhsBookingReadyDeleteAction = request.getContextPath() + "/agy/nhsBookingReadyDelete.do?nhsBookingUser.nhsBookingId=" + nhsBooking.getNhsBookingId();
%>
      <td align="left"><%-- Delete Link --%>
         <mmj-agy:hasAccess forward="nhsBookingReadyDelete">
               <html:link href="<%= nhsBookingReadyDeleteAction %>" titleKey="title.nhsBookingDelete">x</html:link>
         </mmj-agy:hasAccess>
         <mmj-agy:hasNoAccess forward="nhsBookingReadyDelete">
               &nbsp;
         </mmj-agy:hasNoAccess>
      </td>
    </tr>
</logic:iterate>
  </table>
  </logic:notEmpty>
</logic:present>