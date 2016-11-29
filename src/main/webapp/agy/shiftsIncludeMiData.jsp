<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="theFormAgy" name="theFormAgy" value="ListFormAgy"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<bean:parameter id="showCheckbox" name="showCheckbox" value="false"/>

<bean:parameter id="showHrs" name="showHrs" value="true"/> <%/* this means show hrs column */%>
<bean:parameter id="showAgreed" name="showAgreed" value="true"/> <%/* this means show agreed column */%>
<bean:parameter id="showActuals" name="showActuals" value="true"/> <%/* this means show actual value and hrs columns */%>
<bean:parameter id="showVat" name="showVat" value="false"/> <%/* this means show vat and total columns */%>

<bean:parameter id="bookingDateStatus" name="bookingDateStatus" value="-1"/>
<bean:parameter id="workedStatus" name="workedStatus" value="-1"/>
<mmj-agy:consultant var="consultant"/>

<%
if (bookingDateStatus.equals("")) {
	bookingDateStatus = "-1";
}
switch (Integer.parseInt(bookingDateStatus)) {
	case (com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_DRAFT): 
	case (com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_OPEN): {
		pageContext.setAttribute("showAgreed", "false");
		pageContext.setAttribute("showActuals", "false");
		pageContext.setAttribute("showVat", "false");
		break;
	}
	case (com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_OFFERED): {
		pageContext.setAttribute("showActuals", "false");
		pageContext.setAttribute("showVat", "false");
		break;
	}
	case (-1): { // shift status NOT selected so check for the timesheet status
		if (!workedStatus.equals("")) {
			switch (Integer.parseInt(workedStatus)) {
				case (com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_SUBMITTED): {
					pageContext.setAttribute("showVat", "false");
					break;
				}
				case (com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED): 
				case (com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED): {
					pageContext.setAttribute("showHrs", "false");
					pageContext.setAttribute("showAgreed", "false");
					pageContext.setAttribute("showActuals", "true");
					pageContext.setAttribute("showVat", "true");
					break;
				}
				case (com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_REJECTED): {
					pageContext.setAttribute("showVat", "false");
           break;
				}
			}
		}
	}
}
%>

<%-- shifts --%>
<logic:notPresent name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormAgy %>" property="<%= theList %>">
	<logic:empty name="<%= theFormAgy %>" property="<%= theList %>">
	  <bean:message key="text.noDetails"/>
	</logic:empty>
	<logic:notEmpty name="<%= theFormAgy %>" property="<%= theList %>">
		<table class="simple" width="100%">
		<thead>
		  <tr>
		    <th align="left">
					Applicant
		    </th>
		    <th align="left">
					<bean:message key="label.location"/>
		    </th>
		    <th align="left">
					Day
		    </th>
		    <th align="left">
					<bean:message key="label.date"/>
		    </th>
		    <th align="left">
					Client
		    </th>
		    <th align="left">
					<bean:message key="label.jobProfile"/>
		    </th>
		    <th align="left">
					Grade
		    </th>
		    <th align="left">
					Shift
		    </th>
		    <th align="left">
					Start
		    </th>
		    <th align="left">
					End
		    </th>
		    <th align="right">
					Hours
		    </th>
		  </tr>
		</thead>
		<logic:iterate id="bookingDate" name="<%= theFormAgy %>" property="<%= theList %>" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
      <logic:equal name="bookingDate" property="hasBeenEntered" value="true">
      <%/* timesheet has been submitted */%>
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <td align="left">
		      <bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="siteName"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="bookingDate" format="EEE"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="bookingDate" format="dd-MM-yyyy"/>
		    </td>
		    <td align="left">
		 			<bean:write name="bookingDate" property="clientName"/>
		    </td>
		    <td align="left">
		 			<bean:write name="bookingDate" property="jobProfileName"/>
		    </td>
		    <td align="left">
		 			<bean:write name="bookingDate" property="gradeName"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="shiftName"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="workedStartTime" format="HH:mm"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="workedEndTime" format="HH:mm"/>
		    </td>
		    <td align="right">
		      <bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
		    </td>
		  </tr>
		  </logic:equal>
		</logic:iterate>
		<logic:iterate id="bookingDate" name="<%= theFormAgy %>" property="<%= theList %>" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
      <logic:equal name="bookingDate" property="hasBeenEntered" value="false">
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <td align="left">
		      <bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="siteName"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="bookingDate" format="EEE"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="bookingDate" format="dd-MM-yyyy"/>
		    </td>
		    <td align="left">
		 			<bean:write name="bookingDate" property="clientName"/>
		    </td>
		    <td align="left">
		 			<bean:write name="bookingDate" property="jobProfileName"/>
		    </td>
		    <td align="left">
		 			<bean:write name="bookingDate" property="gradeName"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="shiftName"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="workedStartTime" format="HH:mm"/>
		    </td>
		    <td align="left">
		      <bean:write name="bookingDate" property="workedEndTime" format="HH:mm"/>
		    </td>
		    <td align="right">
		      <bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
		    </td>
		  </tr>
		  </logic:equal>
		</logic:iterate>
		</table>
	
	</logic:notEmpty>
</logic:present>
    