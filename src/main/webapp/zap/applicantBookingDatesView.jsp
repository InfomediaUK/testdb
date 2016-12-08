<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<bean:parameter id="showButtons" name="showButtons" value="false"/>
<bean:define id="totalColumns" value="6"/>
<bean:define id="totalColumnsLess2" value="<%= new Integer(Integer.parseInt(totalColumns) - 2).toString() %>"/>
<bean:define id="totalColumnsLess3" value="<%= new Integer(Integer.parseInt(totalColumns) - 3).toString() %>"/>

<bean:define id="tab" value="weekly"/>
<%
String theAction = "/applicantBookingDatesView.do";
%>
<bean:define id="weekToShow" name="ApplicantBookingDatesViewFormZap" property="weekToShow" />
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.applicantBookingDatesView"/>
		</td>
  </tr>
</table>
<%/* tabs */%>
<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="weekly"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
			<c:set var="fourWeeksBack" value="${weekToShow - 4}" scope="page"/>
			<c:set var="twoWeeksBack" value="${weekToShow - 2}" scope="page"/>
			<c:set var="previousWeek" value="${weekToShow - 1}" scope="page"/>
			<c:set var="currentWeek" value="0" scope="page"/>
			<c:set var="nextWeek" value="${weekToShow + 1}" scope="page"/>
			<c:set var="twoWeeksForward" value="${weekToShow + 2}" scope="page"/>
			<c:set var="fourWeeksForward" value="${weekToShow + 4}" scope="page"/>
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="fourWeeksBack" paramScope="page" titleKey="title.showFourWeeksBack">&lt;&lt;&lt;&lt;</html:link>
      &nbsp;
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="twoWeeksBack" paramScope="page" titleKey="title.showTwoWeeksBack">&lt;&lt;</html:link>
      &nbsp;
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="previousWeek" paramScope="page" titleKey="title.showPreviousWeek">&lt;</html:link>
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="currentWeek"  paramScope="page" titleKey="title.showCurrentWeek"><bean:message key="link.currentWeek"/></html:link>
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="nextWeek"     paramScope="page" titleKey="title.showNextWeek">&gt;</html:link>
      &nbsp;
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="twoWeeksForward" paramScope="page" titleKey="title.showTwoWeeksForward">&gt;&gt;</html:link>
      &nbsp;
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="fourWeeksForward" paramScope="page" titleKey="title.showFourWeeksForward">&gt;&gt;&gt;&gt;</html:link>
    </td>
    <td align="center" class="tabInvisibleClass " width="50%">
			<bean:write name="ApplicantBookingDatesViewFormZap" property="startDate" formatKey="format.longDateFormat" />
			-
			<bean:write name="ApplicantBookingDatesViewFormZap" property="endDate" formatKey="format.longDateFormat" />
    </td>
    <td align="center" class="tabInvisibleClass" width="25%">
			&nbsp;
    </td>
  </tr>
</table>
<%-- shifts --%>
<logic:notPresent name="ApplicantBookingDatesViewFormZap" property="list">
  <br/>
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ApplicantBookingDatesViewFormZap" property="list">
	<logic:empty name="ApplicantBookingDatesViewFormZap" property="list">
	  <br/>
	  <bean:message key="text.noDetails"/>
	</logic:empty>
  <logic:notEmpty name="ApplicantBookingDatesViewFormZap" property="list">
    <table class="simple" width="100%">
    <logic:iterate id="bookingBookingDate" name="ApplicantBookingDatesViewFormZap" property="list" type="com.helmet.application.agy.BookingBookingDateUserApplicant" indexId="bookingBookingDateIndex">
      <bean:define id="firstBookingDate" name="bookingBookingDate" property="firstBookingDate" type="com.helmet.bean.BookingDateUserApplicant" />
      <bean:define id="bookingGradeApplicantId" name="firstBookingDate" property="bookingGradeApplicantId" type="java.lang.Integer" />
      <bean:define id="applicantId" name="firstBookingDate" property="applicantId" type="java.lang.Integer" />
      <%
        String appLink = request.getContextPath() + "/app/home.do?login=" + bookingGradeApplicantId + "-" + applicantId; 
      %>
      <thead>
      <tr><th align="left" colspan="<bean:write name="totalColumns"/>" bgcolor="#000000" height="3"></th></tr>
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th colspan="<%= totalColumnsLess2 %>" align="left" >
          <bean:write name="firstBookingDate" property="clientName"/>,
          <bean:write name="firstBookingDate" property="siteName"/>,
          <bean:write name="firstBookingDate" property="locationName"/>
<logic:notEmpty name="firstBookingDate" property="bookingReference" >
          &nbsp;&nbsp;(<bean:message key="label.reference" />&nbsp;<bean:write name="firstBookingDate" property="bookingReference"/>)
</logic:notEmpty>
          <br/>
          <a href="<%= appLink %>"><bean:write name="firstBookingDate" property="bookingId" format="#000"/></a>
          <bean:write name="firstBookingDate" property="jobProfileName"/> - 
          <bean:write name="firstBookingDate" property="gradeName"/>
        </th>
        <th colspan="2" align="right">
        </th>
      </tr>  
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <td colspan="3">
          &nbsp;
        </td>
        <th align="center">
          <bean:message key="label.agreed"/>
        </th>
        <th align="center">
          <bean:message key="label.actual"/>
        </th>
        <td>
          &nbsp;
        </td>
      </tr> 
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th align="left">
          <bean:message key="label.noDot"/>
        </th>
        <th align="left">
          <bean:message key="label.date"/>
        </th>
        <th align="left">
          <bean:message key="label.shift"/>
        </th>
        <th align="center">
          <bean:message key="label.times"/>
          (<bean:message key="label.hrs"/>)
        </th>
        <th align="center">
          <bean:message key="label.times"/>
          (<bean:message key="label.hrs"/>)
        </th>
        <th align="left">
          <bean:message key="label.status"/>
        </th>
      </tr>
      </thead>
      <logic:iterate id="bookingDate" name="bookingBookingDate" property="list" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <td align="left">
          <bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
        </td>
        <td align="left" nowrap="nowrap">
          <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormatyy"/>&nbsp;
        </td>
        <td align="left">
          <bean:write name="bookingDate" property="shiftName"/>
        </td>
        <td align="right">
         <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
         (<bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>)
        </td>
        <logic:equal name="bookingDate" property="hasBeenEntered" value="true">
        <%/* timesheet has been submitted */%>
        <td align="right">
          <logic:equal name="bookingDate" property="hasUplift" value="true">
          *
          </logic:equal>
         <bean:write name="bookingDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="workedEndTime" format="HH:mm"/>
          (<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>)
        </td>
        </logic:equal> 
        <logic:notEqual name="bookingDate" property="hasBeenEntered" value="true">
        <td align="left">&nbsp;</td>
        </logic:notEqual>
        <td align="left">
          <logic:equal name="bookingDate" property="isCancelled" value="true">
            <bean:message name="bookingDate" property="statusDescriptionKey"/>
          </logic:equal>
          <logic:notEqual name="bookingDate" property="isCancelled" value="true">
            <logic:equal name="bookingDate" property="canBeActivated" value="true">
              <bean:message key="text.notActivated"/>
            </logic:equal>
            <logic:notEqual name="bookingDate" property="canBeActivated" value="true">
              <logic:equal name="bookingDate" property="hasBeenEntered" value="true">
                <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
                <logic:equal name="bookingDate" property="workedStatusIsInvoiced" value="true">
                  <logic:greaterThan name="bookingDate" property="agencyInvoiceId" value="0">
                    <bean:write name="bookingDate" property="agencyInvoiceId"/>
                  </logic:greaterThan>
                </logic:equal>
              </logic:equal>
              <logic:notEqual name="bookingDate" property="hasBeenEntered" value="true">
                <bean:message key="text.activated"/>
              </logic:notEqual>
            </logic:notEqual>
          </logic:notEqual>
        </td>
      </tr>
    <logic:notEmpty name="bookingDate" property="comment">
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th align="left" class="label" colspan="3">
          <bean:message key="label.comment"/>
        </th>
        <td align="left" colspan="<bean:write name="totalColumnsLess3"/>">
          <bean:write name="bookingDate" property="comment"/>
        </td>
      </tr>
    </logic:notEmpty> 
    <logic:equal name="bookingDate" property="workedStatusIsRejected" value="true">
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th align="left" class="label" colspan="3">
          <bean:message key="label.rejectText"/>
        </th>
        <td align="left" colspan="<bean:write name="totalColumnsLess3"/>">
          <bean:write name="bookingDate" property="rejectText"/>
        </td>
      </tr>
    </logic:equal>
    
      <tr><th align="left" colspan="<bean:write name="totalColumns"/>" bgcolor="#000000" height="3"></th></tr>
      </logic:iterate>
    </logic:iterate>
    <logic:equal name="showTotals" value="true">
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th align="left" colspan="3"><bean:message key="label.total"/></th>
        <td align="right"><bean:write name="ApplicantBookingDatesViewFormZap" property="totalHours" format="#0.00"/></td>
        <td align="right"><bean:write name="ApplicantBookingDatesViewFormZap" property="totalActualHours" format="#0.00"/></td>
         <th align="right">&nbsp;</th>
      </tr>
    </logic:equal>
    </table>
  </logic:notEmpty>
</logic:present>
