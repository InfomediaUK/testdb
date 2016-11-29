<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="weekToShow" name="NhsBookingsFormAgy" property="weekToShow" />
<bean:define id="nhsBookingsFilter"      name="NhsBookingsFormAgy" property="nhsBookingsFilter" type="java.lang.String" />
<%
String actionPath    = "/nhsBookings.do";
String pageParameter = (actionPath.indexOf('?') == -1) ? "?weekToShow=" : "&weekToShow=";
String allActionPath = actionPath + pageParameter + weekToShow + "&nhsBookingsFilter=ALL";
String requiringValueActionPath   = actionPath + pageParameter + weekToShow + "&nhsBookingsFilter=REQUIRINGVALUE";
%>


<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBookings"/>
<logic:greaterThan name="NhsBookingsFormAgy" property="countNhsBooking" value="0">
      (Bookings: <bean:write name="NhsBookingsFormAgy" property="countNhsBooking"/>, Booked: <bean:write name="NhsBookingsFormAgy" property="countNhsBookingBooked"/>, Deleted: <bean:write name="NhsBookingsFormAgy" property="countNhsBookingDeleted"/>)
</logic:greaterThan>
<logic:equal name="NhsBookingsFormAgy" property="nhsBookingsFilter" value="ALL" >
      &nbsp;
		  <html:link page="<%= requiringValueActionPath %>" title="Booking Requiring Value Only">
		    Requiring Value
		  </html:link>
      &nbsp;
</logic:equal>
<logic:equal name="NhsBookingsFormAgy" property="nhsBookingsFilter" value="REQUIRINGVALUE" >
      &nbsp;
		  <html:link page="<%= allActionPath %>" title="All Bookings">
		    All
		  </html:link>
      &nbsp;
</logic:equal>
		</td>
  </tr>
</table>
<logic:notPresent name="NhsBookingsFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="NhsBookingsFormAgy" property="list">
	<html:errors/>
</logic:present>

<jsp:include page="weekToShowNavigationTab.jsp" flush="true">
  <jsp:param name="theForm" value="NhsBookingsFormAgy"/>
  <jsp:param name="weekToShow" value="<%= weekToShow %>"/>
</jsp:include>

<logic:present name="NhsBookingsFormAgy" property="list">
  <logic:empty name="NhsBookingsFormAgy" property="list">
    <bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="NhsBookingsFormAgy" property="list">
    <table class="simple" width="100%" border="1">
<logic:iterate id="nhsBookingReportGroup" name="NhsBookingsFormAgy" property="list" indexId="nhsBookingReportGroupIndex" type="com.helmet.application.NhsBookingReportGroup">
		  <thead>
      <tr>
        <th align="left" width="90%">
          <bean:write name="nhsBookingReportGroup" property="clientName"/>, 
          <bean:write name="nhsBookingReportGroup" property="siteName"/>,
          <bean:write name="nhsBookingReportGroup" property="locationName"/>,
				  <bean:write name="nhsBookingReportGroup" property="jobProfileName"/>&nbsp;(<bean:write name="nhsBookingReportGroup" property="jobFamilyCode"/>.<bean:write name="nhsBookingReportGroup" property="jobSubFamilyCode"/>)&nbsp;-&nbsp;<bean:write name="nhsBookingReportGroup" property="assignment"/>
				  (<bean:write name="nhsBookingReportGroup" property="noOfNhsBookings"/>&nbsp;<logic:equal name="nhsBookingReportGroup" property="noOfNhsBookings" value="1">Booking</logic:equal><logic:greaterThan name="nhsBookingReportGroup" property="noOfNhsBookings" value="1">Bookings</logic:greaterThan>)
	<logic:greaterThan name="nhsBookingReportGroup" property="nhsBookingsToInvoiceValue" value="0">
				  &nbsp;
				  <bean:message key="label.currencySymbol"/><bean:write name="nhsBookingReportGroup" property="nhsBookingsToInvoiceValue" format="#,##0.00"/>
	</logic:greaterThan>
        </th>
        <th align="right" width="10%">
	<logic:greaterThan name="nhsBookingReportGroup" property="nhsBookingsToInvoiceValue" value="0">
					<html:form action="/nhsBookingsSubcontractInvoice.do" onsubmit="return singleSubmit();">
            <html:hidden name="NhsBookingsFormAgy" property="weekToShow" />
            <html:hidden name="nhsBookingReportGroup" property="clientId" />
            <html:hidden name="nhsBookingReportGroup" property="siteId" />
            <html:hidden name="nhsBookingReportGroup" property="locationId" />
            <html:hidden name="nhsBookingReportGroup" property="jobProfileId" />
					  <html:submit styleClass="titleButton"><bean:message key="button.invoice"/></html:submit>
					</html:form>
	</logic:greaterThan>
        </th>
      </tr>
      </thead>
      <tr>
        <td colspan="2">
				  <table class="simple" width="100%" border="1">
				    <thead>
				    <tr>
				      <th align="left" width="6%">Booking</th>
				      <th align="left" width="9%">Bank Request</th>
				      <th align="left" width="3%">Day</th>
				      <th align="left" width="8%">Date</th>
				      <th align="left" width="5%">Start</th>
				      <th align="left" width="5%">End</th>
				      <th align="left" width="20%">Applicant</th>
				      <th align="left" width="20%">Notified</th>
				      <th align="left" width="20%"><bean:message key="label.comment"/></th>
				      <th align="left" width="20%"><bean:message key="label.invoice"/></th>
				      <th align="left" width="3">?</th>
				      <th align="left" width="20%"><bean:message key="label.value"/></th>
				      <th align="left">*</th>
				    </tr>
				    </thead>
				  <logic:iterate id="nhsBooking" name="nhsBookingReportGroup" property="listNhsBookingUser" indexId="nhsBookingIndex" type="com.helmet.bean.NhsBookingUser">
<%
String nhsBookingEditAction = request.getContextPath() + "/agy/nhsBookingEdit.do?weekToShow=" + weekToShow + "&nhsBookingUser.nhsBookingId=" + nhsBooking.getNhsBookingId();
String nhsBookingDeleteAction = request.getContextPath() + "/agy/nhsBookingDelete.do?weekToShow=" + weekToShow + "&nhsBookingUser.nhsBookingId=" + nhsBooking.getNhsBookingId();
String applicantViewAction = request.getContextPath() + "/agy/applicantView.do?weekToShow=" + weekToShow + "&applicant.applicantId=" + nhsBooking.getApplicantId();
String nhsBookingApplicantNotifyAction = request.getContextPath() + "/agy/nhsBookingApplicantNotify.do?weekToShow=" + weekToShow + "&nhsBookingUser.nhsBookingId=" + nhsBooking.getNhsBookingId();
%>
				    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Booking --%>
				<logic:equal name="nhsBooking" property="bookingId" value="0">
				        &nbsp;
				</logic:equal>
				<logic:notEqual name="nhsBooking" property="bookingId" value="0">
          <mmj-agy:hasAccess forward="bookingGradeViewSummary">
					      <html:link forward="bookingGradeViewSummary" paramId="bookingGrade.bookingGradeId" paramName="nhsBooking" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewSummary"><bean:write name="nhsBooking" property="bookingId" format="#000"/></html:link>
          </mmj-agy:hasAccess>
          <mmj-agy:hasNoAccess forward="bookingGradeViewSummary">
                <bean:write name="nhsBooking" property="bookingId" format="#000"/>
          </mmj-agy:hasNoAccess>
				</logic:notEqual>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Bank Request Number --%>
          <mmj-agy:hasAccess forward="nhsBookingEdit">
					      <html:link href="<%= nhsBookingEditAction %>" titleKey="title.nhsBookingEdit"><bean:write name="nhsBooking" property="bankReqNum"/></html:link>
          </mmj-agy:hasAccess>
          <mmj-agy:hasNoAccess forward="nhsBookingEdit">
                <bean:write name="nhsBooking" property="bankReqNum"/>
          </mmj-agy:hasNoAccess>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Date --%>
				        <bean:write name="nhsBooking" property="date" format="EEE"/>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Date --%>
				        <bean:write name="nhsBooking" property="date" format="dd-MMM-yyyy"/>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Start Time --%>
				        <bean:write name="nhsBooking" property="startTime" format="HH:mm"/>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- End Time --%>
				        <bean:write name="nhsBooking" property="endTime" format="HH:mm"/>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Applicant Full Name --%>
					      <html:link href="<%= applicantViewAction %>" titleKey="title.applicantView"><bean:write name="nhsBooking" property="applicantFullName"/></html:link>
  <logic:greaterThan name="nhsBooking" property="applicantOriginalAgencyId" value="0"><%-- Applicant IS subcontracted. Flag it up... --%>
                *
  </logic:greaterThan>
				      </td>
				      <td align="left"><%-- Applicant Notification Sent --%>
				<logic:equal name="nhsBooking" property="bookingId" value="0">
				        &nbsp;
				</logic:equal>
				<logic:greaterThan name="nhsBooking" property="bookingId" value="0">
				  <logic:equal name="nhsBooking" property="active" value="true">
 	          <mmj-agy:hasAccess forward="nhsBookingApplicantNotify">
	                <html:link href="<%= nhsBookingApplicantNotifyAction %>" titleKey="title.nhsBookingResendApplicantNotify">
					          <logic:empty name="nhsBooking" property="applicantNotificationSent">Notify</logic:empty>
					          <logic:notEmpty name="nhsBooking" property="applicantNotificationSent"><bean:write name="nhsBooking" property="applicantNotificationSent" format="EEE dd-MMM-yyyy HH:mm"/></logic:notEmpty>
	                </html:link>
	          </mmj-agy:hasAccess>
          </logic:equal>
 				  <logic:equal name="nhsBooking" property="active" value="false">
                &nbsp;
          </logic:equal>
        </logic:greaterThan>
          <mmj-agy:hasNoAccess forward="nhsBookingApplicantNotify">
                <bean:write name="nhsBooking" property="applicantNotificationSent" format="EEE dd-MMM-yyyy HH:mm"/>
          </mmj-agy:hasNoAccess>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Comment --%>
				        <bean:write name="nhsBooking" property="comment"/>
				      </td>
				      <td align="right" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Invoice --%>
				      <logic:notEqual name="nhsBooking" property="subcontractInvoiceId" value="0">
				        <html:link forward="subcontractInvoiceView" paramId="subcontractInvoiceUser.subcontractInvoiceId" paramName="nhsBooking" paramProperty="subcontractInvoiceId" >
				          <bean:write name="nhsBooking" property="subcontractInvoiceNumber"/>
				        </html:link>
				        <logic:notEmpty name="nhsBooking" property="backingReport" >
                          <br /><bean:write name="nhsBooking" property="backingReport"/>
                        </logic:notEmpty>
				      </logic:notEqual>
				      <logic:equal name="nhsBooking" property="subcontractInvoiceId" value="0">
					      <logic:empty name="nhsBooking" property="workedNoOfHours">
					        <logic:greaterThan name="nhsBooking" property="value" value="0">
					        Worked?
					        </logic:greaterThan>
					      </logic:empty>
					      <logic:notEmpty name="nhsBooking" property="workedNoOfHours">
					        &nbsp;
					      </logic:notEmpty>
				        <logic:notEmpty name="nhsBooking" property="backingReport" >
                          <bean:write name="nhsBooking" property="backingReport"/>
                        </logic:notEmpty>
				      </logic:equal>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>>
				        <bean:write name="nhsBooking" property="status"/>
				      </td>
				      <td align="right" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Value --%>
				      <logic:greaterThan name="nhsBooking" property="value" value="0">
				        <bean:message key="label.currencySymbol"/><bean:write name="nhsBooking" property="value" format="#,##0.00"/>
				      </logic:greaterThan>
				      <logic:equal name="nhsBooking" property="value" value="0">
				        &nbsp;
				      </logic:equal>
				      </td>
				      <td align="left" <logic:equal name="nhsBooking" property="active" value="false">class="nhsBookingDeleted"</logic:equal>><%-- Delete Action --%>
          <mmj-agy:hasAccess forward="nhsBookingDelete">
				    <logic:equal name="nhsBooking" property="active" value="true">
                <html:link href="<%= nhsBookingDeleteAction %>" titleKey="title.nhsBookingDelete">x</html:link>
            </logic:equal>
				    <logic:equal name="nhsBooking" property="active" value="false">
                &nbsp;
            </logic:equal>
          </mmj-agy:hasAccess>
          <mmj-agy:hasNoAccess forward="nhsBookingDelete">
                &nbsp;
          </mmj-agy:hasNoAccess>
				      </td>
				    </tr>
				  </logic:iterate>
				  </table>
				</td>
		  </tr>
</logic:iterate>
    </table>
* Applicant is subcontracted    
  </logic:notEmpty>
</logic:present>