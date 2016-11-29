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
      <bean:message key="title.nhsBookingGroupsReady"/>
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
<html:form action="/nhsBookingGroupAdditional.do">
  <mmj-agy:hasAccess forward="nhsBookingGroupsReady">
  <table cellpadding="0" cellspacing="0" width="100%" height="30">
    <tr>
      <td align="right" valign="middle" width="100">
        <html:submit styleClass="titleButton"><bean:message key="button.next"/></html:submit>
      </td>
    </tr>
  </table>
  </mmj-agy:hasAccess>
  <html:errors/>
  <table class="simple" width="100%" border="1">
    <thead>
    <tr>
      <th align="left" width="2%">&nbsp;</th>
      <th align="left">Client</th>
      <th align="left">Site</th>
      <th align="left">Location (NHS Ward)</th>
      <th align="left">Job Profile (NHS Assignment)</th>
      <th align="left">Booking Group</th>
      <th align="left">Date Range</th>
      <th align="left">Bookings</th>
    </tr>
    </thead>
<logic:iterate id="nhsBookingGroup" name="NhsBookingsBookFormAgy" property="list" indexId="nhsBookingGroupIndex" type="com.helmet.bean.NhsBookingGroup">
    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
      <td align="left"><%-- Radio Button --%>
        <input type="radio" name="nhsBookingGroupIds" value="<%= nhsBookingGroup.getNhsBookingGroupIds() %>" id="<%= "nhsBookingGroup" + nhsBookingGroupIndex %>">
      </td>
      <td align="left"><%-- Client Name --%>
        <bean:write name="nhsBookingGroup" property="clientName"/>
      </td>
      <td align="left"><%-- Site Name (Location) --%>
        <bean:write name="nhsBookingGroup" property="siteName"/>
      </td>
      <td align="left"><%-- Location Name (Ward) --%>
        <bean:write name="nhsBookingGroup" property="locationName"/>
      </td>
      <td align="left"><%-- Assignment --%>
        <bean:write name="nhsBookingGroup" property="jobFamilyCode"/>.
        <bean:write name="nhsBookingGroup" property="jobSubFamilyCode"/>.
        <bean:write name="nhsBookingGroup" property="jobProfileName"/>
        (<bean:write name="nhsBookingGroup" property="assignment"/>)
      </td>
      <td align="left"><%-- Booking Group --%>
        <bean:write name="nhsBookingGroup" property="bookingGroupName"/>
      </td>
      <td align="left"><%-- Date Range --%>
        <bean:write name="nhsBookingGroup" property="earliestDate" format="EEE dd-MMM-yyyy"/>
  <logic:notEqual name="nhsBookingGroup" property="singleDate" value="true">
        &nbsp;-&nbsp;<bean:write name="nhsBookingGroup" property="latestDate" format="EEE dd-MMM-yyyy"/>
  </logic:notEqual>
      </td>
      <td align="left"><%-- Bookings --%>
        <bean:write name="nhsBookingGroup" property="noOfBookings"/>
      </td>
    </tr>
</logic:iterate>
  </table>
</html:form>
  </logic:notEmpty>
</logic:present>