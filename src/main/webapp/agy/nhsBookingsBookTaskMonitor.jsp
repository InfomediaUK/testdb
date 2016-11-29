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
      <bean:message key="title.nhsBookingsBookTaskMonitor"/>
		</td>
  </tr>
</table>
<logic:notPresent name="NhsBookingsBookTaskFormAgy" property="nhsBookingsBookTaskResult">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="NhsBookingsBookTaskFormAgy" property="nhsBookingsBookTaskResult">
<bean:define id="nhsBookingsBookTaskResult" name="NhsBookingsBookTaskFormAgy" property="nhsBookingsBookTaskResult"/>
	  <table class="simple" width="100%" border="1">
	    <thead>
	    <tr>
	      <th align="left">Client</th>
	      <th align="left">Site</th>
	      <th align="left">Location</th>
	      <th align="left">Job Profile</th>
	      <th align="left">Bookings</th>
	      <th align="left">OK</th>
	      <th align="left">Done</th>
	    </tr>
	    </thead>
	    <tr>
	      <td align="left"><%-- Client Name --%>
	        <bean:write name="nhsBookingsBookTaskResult" property="clientName"/>
	      </td>
	      <td align="left"><%-- Site Name (Location) --%>
	        <bean:write name="nhsBookingsBookTaskResult" property="siteName"/>
	      </td>
	      <td align="left"><%-- Location Name (Ward) --%>
	        <bean:write name="nhsBookingsBookTaskResult" property="locationName"/>
	      </td>
	      <td align="left"><%-- Job Profile Name --%>
	        <bean:write name="nhsBookingsBookTaskResult" property="jobProfileName"/>
	      </td>
	      <td align="left"><%-- Bookings to Process --%>
	        <bean:write name="nhsBookingsBookTaskResult" property="bookingsToProcess"/>
	      </td>
	      <td align="left"><%-- Bookings to Process --%>
	        <bean:write name="nhsBookingsBookTaskResult" property="bookingsProcessedOk"/>
	      </td>
	      <td align="left"><%-- Bookings to Process --%>
	        <bean:write name="nhsBookingsBookTaskResult" property="done"/>
	      </td>
	    </tr>
	  </table>
</logic:present>