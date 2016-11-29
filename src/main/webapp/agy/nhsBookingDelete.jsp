<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<%
// The following code gets the URL (after ContextPath)...
String theAction = (String)request.getAttribute("javax.servlet.forward.request_uri");
theAction = theAction.substring(theAction.lastIndexOf("/")); // After last slash.
theAction = theAction.substring(0, theAction.indexOf(".do")); // Up to .do.
theAction = theAction + "Process.do"; // Comes from two different processes and must go back to correct one...
%>
<bean:define id="nhsBookingUser" name="NhsBookingFormAgy"  property="nhsBookingUser" type="com.helmet.bean.NhsBookingUser"/>

<html:form action="<%= theAction %>" onsubmit="return singleSubmit();">
  <html:hidden property="weekToShow" />
  <html:hidden name="NhsBookingFormAgy" property="nhsBookingUser.nhsBookingId" />
  <html:hidden name="NhsBookingFormAgy" property="nhsBookingUser.noOfChanges" />
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
			<td align="left" valign="middle" class="title"><bean:message key="title.nhsBookingDelete"/></td>
	    <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
	    <td align="right" valign="middle" width="80"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
	  </tr>
	</table>
	<html:errors/>
	<table class="simple" width="100%">
	  <tr>
	    <th align="left"><bean:message key="label.client"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.clientName"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.site"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.siteName"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.location"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.locationName"/></td>
	  </tr>
	  <tr>
	    <th align="left">Job Profile - Assignment</th>
	    <td align="left"><bean:write name="NhsBookingFormAgy" property="nhsBookingUser.jobProfileName"/>&nbsp;(<bean:write name="NhsBookingFormAgy" property="nhsBookingUser.jobFamilyCode"/>.<bean:write name="NhsBookingFormAgy" property="nhsBookingUser.jobSubFamilyCode"/>)&nbsp;-&nbsp;<bean:write name="NhsBookingFormAgy" property="nhsBookingUser.assignment"/></td>
	  </tr>
	  <tr>
	    <th align="left">Booking</th>
	    <td align="left"><bean:write name="NhsBookingFormAgy" property="nhsBookingUser.bookingId" format="#000"/></td>
	  </tr>
	  <tr>
	    <th align="left">Bank Request</th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.bankReqNum"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.applicant"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.applicantFullName"/> (<bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.applicantEmailAddress"/>)</td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.date"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.date" format="EEE dd-MMM-yyyy"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.time"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.startTime" format="HH:mm"/> - <bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.endTime" format="HH:mm"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.applicantNotificationSent"/></th>
	    <td align="left"><bean:write name="NhsBookingFormAgy" property="nhsBookingUser.applicantNotificationSent" format="EEE dd-MMM-yyyy HH:mm"/></td>
	  </tr>
	  <tr>
	    <th align="left" valign="top">Comment</th>
	    <td align="left"><html:textarea name="NhsBookingFormAgy" property="nhsBookingUser.comment" cols="50" rows="4"/></td>
	  </tr>
	  <tr>
	    <th align="left" valign="top">Applicant Comment (Email Notification)</th>
	    <td align="left"><html:textarea name="NhsBookingFormAgy" property="applicantComment" cols="50" rows="4"/></td>
	  </tr>
	</table>
</html:form>