<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<bean:parameter id="theFormMgr" name="theFormMgr" value="BookingListFormMgr"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>

<bean:parameter id="showRRP" name="showRRP" value="true"/>
<bean:parameter id="showAgreed" name="showAgreed" value="true"/>
<bean:parameter id="showActuals" name="showActuals" value="true"/>

<bean:parameter id="bookingStatus" name="bookingStatus" value="-1"/>
<%
if (bookingStatus.equals("")) {
	bookingStatus = "-1";
}
switch (Integer.parseInt(bookingStatus)) {
	case (com.helmet.bean.Booking.BOOKING_STATUS_DRAFT): 
	case (com.helmet.bean.Booking.BOOKING_STATUS_OPEN): {
		pageContext.setAttribute("showAgreed", "false");
		pageContext.setAttribute("showActuals", "false");
		break;
	}
	case (com.helmet.bean.Booking.BOOKING_STATUS_OFFERED): {
		pageContext.setAttribute("showAgreed", "false"); // probably should be showing - but cuurently zero until accepted
		pageContext.setAttribute("showActuals", "false");
		break;
	}
}
%>

<bean:define id="totalColumns" value="15"/>
<logic:notEqual name="showRRP" value="true">
  <bean:define id="totalColumns" value="<%= new Integer(Integer.parseInt(totalColumns) - 1).toString() %>"/>
</logic:notEqual>
<logic:notEqual name="showAgreed" value="true">
  <bean:define id="totalColumns" value="<%= new Integer(Integer.parseInt(totalColumns) - 1).toString() %>"/>
</logic:notEqual>
<logic:notEqual name="showActual" value="true">
  <bean:define id="totalColumns" value="<%= new Integer(Integer.parseInt(totalColumns) - 2).toString() %>"/>
</logic:notEqual>

<!-- included -->
<logic:notPresent name="<%= theFormMgr %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormMgr %>" property="<%= theList %>">
<logic:empty name="<%= theFormMgr %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="<%= theFormMgr %>" property="<%= theList %>">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.noDot"/></th>
    <th align="left"><bean:message key="label.start"/></th>
    <th align="left"><bean:message key="label.end"/></th>
    <th align="center"><bean:message key="label.dys"/></th>
    <th align="left"><bean:message key="label.jobProfile"/></th>
    <th align="left"><bean:message key="label.location"/></th>
    <th align="left"><bean:message key="label.shift"/></th>
    <th align="right"><bean:message key="label.hrs"/></th>
<logic:equal name="showRRP" value="true">
		<th align="right"><bean:message key="label.rrp"/></th>
</logic:equal>
<logic:equal name="showAgreed" value="true">
		<th align="right"><bean:message key="label.agreed"/></th>
</logic:equal>
<logic:equal name="showActuals" value="true">
		<th align="right"><bean:message key="label.actual"/></th>
		<th align="right"><bean:message key="label.hrs"/></th>
</logic:equal>
    <th align="center"><bean:message key="label.sa"/></th>
    <th align="center"><bean:message key="label.af"/></th>
    <th align="left"><bean:message key="label.status"/></th>
  </tr>
  </thead>
  <logic:iterate id="booking" name="<%= theFormMgr %>" property="<%= theList %>" indexId="bookingIndex">
  <bean:define id="jobProfileName" name="booking" property="jobProfileName" type="java.lang.String" />
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" valign="top">
    <!-- index <bean:write name="bookingIndex"/> -->
	  <mmj-mgr:hasAccess forward="bookingViewSummary">
	    <html:link forward="bookingViewSummary" paramId="booking.bookingId" paramName="booking" paramProperty="bookingId" titleKey="title.bookingViewSummary"><bean:write name="booking" property="bookingId" format="#000"/></html:link>
	  </mmj-mgr:hasAccess>
	  <mmj-mgr:hasNoAccess forward="bookingViewSummary">
    <bean:write name="booking" property="bookingId"/>
	  </mmj-mgr:hasNoAccess>
    </td>
    <td align="left" valign="top"><bean:write name="booking" property="minBookingDate" formatKey="format.longDateFormatyy"/></td>
    <td align="left" valign="top"><bean:write name="booking" property="maxBookingDate" formatKey="format.longDateFormatyy"/></td>
    <td align="right" valign="top"><bean:write name="booking" property="noOfBookingDates"/></td>
    <td align="left" valign="top">
      <logic:equal name="booking" property="draft" value="true">
  	    <%= org.apache.commons.lang3.StringUtils.abbreviate(jobProfileName, 25) %>
      </logic:equal>
      <logic:notEqual name="booking" property="draft" value="true">
			  <mmj-mgr:hasAccess forward="bookingViewApplicants">
			    <html:link forward="bookingViewApplicants" paramId="booking.bookingId" paramName="booking" paramProperty="bookingId" titleKey="title.bookingViewApplicants"><%= org.apache.commons.lang3.StringUtils.abbreviate(jobProfileName, 25) %></html:link>
			  </mmj-mgr:hasAccess>
			  <mmj-mgr:hasNoAccess forward="bookingViewApplicants">
  	    <%= org.apache.commons.lang3.StringUtils.abbreviate(jobProfileName, 25) %>
			  </mmj-mgr:hasNoAccess>
      </logic:notEqual>
    </td>
    <td align="left" valign="top" title="<bean:write name="booking" property="locationName"/>, <bean:write name="booking" property="siteName"/>"><bean:write name="booking" property="locationName"/></td>
    <td align="left" valign="top">
    <logic:equal name="booking" property="draft" value="true">
		  <mmj-mgr:hasAccess forward="bookingViewShifts">
		    <html:link forward="bookingViewShifts" paramId="booking.bookingId" paramName="booking" paramProperty="bookingId" titleKey="title.bookingViewShifts">
	    <logic:empty name="booking" property="shiftName">
	      <bean:message key="label.varied"/>
	    </logic:empty>
	    <logic:notEmpty name="booking" property="shiftName">
	      <bean:write name="booking" property="shiftName"/>
	    </logic:notEmpty>
	      </html:link>
		  </mmj-mgr:hasAccess>
		  <mmj-mgr:hasNoAccess forward="bookingViewShifts">
	    <logic:empty name="booking" property="shiftName">
	      <bean:message key="label.varied"/>
	    </logic:empty>
	    <logic:notEmpty name="booking" property="shiftName">
	      <bean:write name="booking" property="shiftName"/>
	    </logic:notEmpty>
	    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="booking" property="draft" value="true">
		  <mmj-mgr:hasAccess forward="bookingViewTimesheets">
		    <html:link forward="bookingViewTimesheets" paramId="booking.bookingId" paramName="booking" paramProperty="bookingId" titleKey="title.bookingViewTimesheets">
	    <logic:empty name="booking" property="shiftName">
	      <bean:message key="label.varied"/>
	    </logic:empty>
	    <logic:notEmpty name="booking" property="shiftName">
	      <bean:write name="booking" property="shiftName"/>
	    </logic:notEmpty>
	      </html:link>
		  </mmj-mgr:hasAccess>
		  <mmj-mgr:hasNoAccess forward="bookingViewTimesheets">
	    <logic:empty name="booking" property="shiftName">
	      <bean:message key="label.varied"/>
	    </logic:empty>
	    <logic:notEmpty name="booking" property="shiftName">
	      <bean:write name="booking" property="shiftName"/>
	    </logic:notEmpty>
	    </mmj-mgr:hasNoAccess>
    </logic:notEqual>
    </td>
    <td align="right" valign="top"><bean:write name="booking" property="noOfHours" format="0.00" /></td>
<logic:equal name="showRRP" value="true">
		<td align="right" valign="top"><bean:message key="label.currencySymbol"/><bean:write name="booking" property="value" format="#,##0.00" /></td>
</logic:equal>
<logic:equal name="showAgreed" value="true">
		<td align="right" valign="top"><bean:message key="label.currencySymbol"/><bean:write name="booking" property="filledValue" format="#,##0.00" /></td>
</logic:equal>
<logic:equal name="showActuals" value="true">
		<td align="right" valign="top"><bean:message key="label.currencySymbol"/><bean:write name="booking" property="workedValue" format="#,##0.00" /></td>
		<td align="right" valign="top"><bean:write name="booking" property="workedNoOfHours" format="0.00" /></td>
</logic:equal>
    <td align="center" valign="middle">
    <bean:define id="singleCandidate" name="booking" property="singleCandidate" type="java.lang.Boolean" />
	  <logic:equal name="booking" property="singleCandidate" value="true">
	    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" alt="<%= singleCandidate.toString() %>"/>
	  </logic:equal>
	  <logic:notEqual name="booking" property="singleCandidate" value="true">
	    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" alt="<%= singleCandidate.toString() %>"/>
	  </logic:notEqual>
	  </td>
    <td align="center" valign="middle">
    <bean:define id="autoFill" name="booking" property="autoFill" type="java.lang.Boolean" />
	  <logic:equal name="booking" property="autoFill" value="true">
	    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" alt="<%= autoFill.toString() %>"/>
	  </logic:equal>
	  <logic:notEqual name="booking" property="autoFill" value="true">
	    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" alt="<%= autoFill.toString() %>"/>
	  </logic:notEqual>
	  </td>
	  <td align="left">
 	    <bean:message name="booking" property="statusDescriptionKey"/>
      <logic:equal name="booking" property="canBeCancelled" value="true">
	  	  <mmj-mgr:hasAccess forward="bookingCancel">
		    <html:link forward="bookingCancel" paramId="booking.bookingId" paramName="booking" paramProperty="bookingId">
        <bean:message key="link.cancel"/>
	      </html:link>
	      </mmj-mgr:hasAccess>
      </logic:equal>
	  </td>
  </tr>
  </logic:iterate>
  <logic:equal name="showTotals" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="7"><bean:message key="label.total"/></th>
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalHours" format="#0.00"/></td>
<logic:equal name="showRRP" value="true">
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalRRPValue" format="#,##0.00"/></td>
</logic:equal>
<logic:equal name="showAgreed" value="true">
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalAgreedValue" format="#,##0.00"/></td>
</logic:equal>
<logic:equal name="showActuals" value="true">
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalActualValue" format="#,##0.00"/></td>
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalActualHours" format="#0.00"/></td>
</logic:equal>
		<th align="right" colspan="3">&nbsp</th>
  </tr>
  </logic:equal>
</table>
</logic:notEmpty>
</logic:present>