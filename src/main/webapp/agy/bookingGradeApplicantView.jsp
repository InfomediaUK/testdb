<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="applicantId" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantId"/>
<bean:define id="bookingId" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingId" type="java.lang.Integer"/>
<bean:define id="checklistFileUrl" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.checklistFileUrl"/>
<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isSubmit" name="isSubmit" value="false"/>
<bean:parameter id="isAccept" name="isAccept" value="false"/>
<bean:parameter id="isRefuse" name="isRefuse" value="false"/>
<bean:parameter id="isWithdraw" name="isWithdraw" value="false"/>
<mmj-agy:consultant var="consultant"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
    <logic:equal name="isView" value="true">
      <bean:message key="title.bookingGradeApplicantView"/>
    </logic:equal>
    <logic:equal name="isSubmit" value="true">
			<bean:message key="title.bookingGradeApplicantSubmit"/>
    </logic:equal>
    <logic:equal name="isAccept" value="true">
			<bean:message key="title.bookingGradeApplicantAccept"/>
    </logic:equal>
    <logic:equal name="isRefuse" value="true">
			<bean:message key="title.bookingGradeApplicantRefuse"/>
    </logic:equal>
    <logic:equal name="isWithdraw" value="true">
			<bean:message key="title.bookingGradeApplicantWithdraw"/>
    </logic:equal>
    </td>
<logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.active" value="true">
	<%/* VIEW */%>
	<logic:equal name="isView" value="true">
    <html:form action="/bookingGradeApplicantChecklist.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton">Checklist</html:submit></td>
    </html:form>
    <%/* EDIT */%>
    <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeEdited" value="true">
    <mmj-agy:hasAccess forward="bookingGradeApplicantEdit">
    <html:form action="/bookingGradeApplicantEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    </logic:equal>
    <%/* SUBMIT */%>
    <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeSubmitted" value="true">
    <mmj-agy:hasAccess forward="bookingGradeApplicantSubmit">
    <html:form action="/bookingGradeApplicantSubmit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.submit"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    </logic:equal>
    <%/* ACCEPT */%>
    <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeAccepted" value="true">
    <mmj-agy:hasAccess forward="bookingGradeApplicantAccept">
    <html:form action="/bookingGradeApplicantAccept.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.accept"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    </logic:equal>
    <%/* REFUSE */%>
    <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeRefused" value="true">
    <mmj-agy:hasAccess forward="bookingGradeApplicantRefuse">
    <html:form action="/bookingGradeApplicantRefuse.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.refuse"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    </logic:equal>
    <%/* WITHDRAW */%>
    <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeWithdrawn" value="true">
    <mmj-agy:hasAccess forward="bookingGradeApplicantWithdraw">
    <html:form action="/bookingGradeApplicantWithdraw.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.withdraw"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    </logic:equal>
  </logic:equal>
  <%/* SUBMIT */%>
  <logic:equal name="isSubmit" value="true">
    <%/* CONFIRM */%>
	  <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeSubmitted" value="true">
    <mmj-agy:hasAccess forward="bookingGradeApplicantSubmit">
   	<html:form action="bookingGradeApplicantSubmitProcess.do" onsubmit="return singleSubmit();">
		<html:hidden property="bookingGradeApplicant.bookingGradeApplicantId"/>
		<html:hidden property="bookingGradeApplicant.noOfChanges"/>
		<%-- used to lock the booking --%>
		<html:hidden property="bookingId"/>
		<td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</html:form>
    </mmj-agy:hasAccess>
    </logic:equal>
  </logic:equal>
  <%/* ACCEPT */%>
  <logic:equal name="isAccept" value="true">
    <%/* CONFIRM */%>
		<logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeAccepted" value="true">
		<mmj-agy:hasAccess forward="bookingGradeApplicantAccept">
		<html:errors/>
		<html:form action="bookingGradeApplicantAcceptProcess.do" onsubmit="return singleSubmit();">
		<html:hidden property="bookingGradeApplicant.bookingGradeApplicantId"/>
		<html:hidden property="bookingGradeApplicant.noOfChanges"/>
		<%-- used to lock the booking --%>
		<html:hidden property="bookingId"/>
		<td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
		</html:form>
		</mmj-agy:hasAccess>
		</logic:equal>
	</logic:equal>
  <%/* REFUSE */%>
	<logic:equal name="isRefuse" value="true">
    <%/* CONFIRM */%>
		<logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeRefused" value="true">
		<mmj-agy:hasAccess forward="bookingGradeApplicantRefuse">
		<html:errors/>
		<html:form action="bookingGradeApplicantRefuseProcess.do" onsubmit="return singleSubmit();">
		<html:hidden property="bookingGradeApplicant.bookingGradeApplicantId"/>
		<html:hidden property="bookingGradeApplicant.noOfChanges"/>
		<%-- used to lock the booking --%>
		<html:hidden property="bookingId"/>
				<td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</html:form>
		</mmj-agy:hasAccess>
		</logic:equal>
	</logic:equal>
  <%/* WITHDRAW */%>
  <logic:equal name="isWithdraw" value="true">
    <%/* CONFIRM */%>
	  <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeWithdrawn" value="true">
    <mmj-agy:hasAccess forward="bookingGradeApplicantWithdraw">
   	<html:form action="bookingGradeApplicantWithdrawProcess.do" onsubmit="return singleSubmit();">
		<html:hidden property="bookingGradeApplicant.bookingGradeApplicantId"/>
		<html:hidden property="bookingGradeApplicant.noOfChanges"/>
		<%-- used to lock the booking --%>
		<html:hidden property="bookingId"/>
		<td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</html:form>
    </mmj-agy:hasAccess>
    </logic:equal>
  </logic:equal>
</logic:equal>
  </tr>
</table>
<html:errors/>
<table cellpadding="0" cellspacing="0" width="100%" border="0">
  <tr>
    <td align="left" valign="top" width="75%">
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="35%"><bean:message key="label.bookingNo"/></th>
    <td align="left" width="65%">
	  <html:link forward="bookingGradeViewApplicants" paramId="bookingGrade.bookingGradeId" paramName="BookingGradeApplicantViewFormAgy" paramProperty="bookingGradeApplicant.bookingGradeId" titleKey="title.bookingGradeViewApplicants" >
      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingId" format="000"/>
    </html:link>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.client"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.clientName"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.locationName"/>,
      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.siteName"/>
      <logic:notEmpty name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.locationDescription">
      (<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.locationDescription"/>)
      </logic:notEmpty>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.jobProfileName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.name"/></th>
    <td align="left">
    <mmj-agy:hasAccess forward="applicantView">
      <html:link forward="applicantView" paramId="applicant.applicantId" paramName="BookingGradeApplicantViewFormAgy" paramProperty="bookingGradeApplicant.applicantId" >
	      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantFirstName"/>&nbsp;<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantLastName"/>
      </html:link>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="applicantView">
      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantFirstName"/>&nbsp;<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantLastName"/>
		</mmj-agy:hasNoAccess>
    </td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantEmailAddress"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.mobileNumber"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantMobileNumber"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.dateOfBirth"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantDateOfBirth" formatKey="format.mediumDateFormat" /></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.niNumber"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantNiNumber"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.gender"/></th>
    <td align="left"><bean:message name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantGenderDescriptionKey"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.discipline"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantReference"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.professionalReference"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantProfessionalReference"/></td>
  </tr>
<!-- LYNDON Should get value from Booking?  
-->
  <tr>
    <th align="left">
      <bean:message key="label.totalHours" /> (Booking)
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantViewFormAgy" property="booking.noOfHours" format="#0.00" />
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.grade"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.gradeName" />
	  </td>
	</tr>

  <tr>
    <th align="left">
      <bean:message key="label.chargeRate"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.rate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.chargeRateVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.wageRate"/>
    </th>
    <td align="left">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.wageRate" format="#,##0.00" />
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.payRate"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.payRate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.payRateVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.wtd"/>&nbsp;@&nbsp;<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.wtdPercentage" format="#0.00" />%
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.wtdRate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.wtdVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.ni"/>&nbsp;@&nbsp;<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.niPercentage" format="#0.00" />%
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.niRate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.niVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.commission"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.commissionRate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.commissionVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.status"/></th>
    <td align="left"><bean:message name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.statusDescriptionKey"/></td>
  </tr>
  <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.hasBeenRejected" value="true">
  <tr>
    <th align="left" class="label"><bean:message key="label.rejectText"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.rejectText"/></td>
  </tr>
  </logic:equal>
  <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.canBeRenegotiated" value="true">
  <tr>
    <th align="left" class="label"><bean:message key="label.renegotiateText"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.renegotiateText"/></td>
  </tr>
  </logic:equal>
  <logic:notEmpty name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.filename">
  <tr>
    <th align="left" class="label"><bean:message key="label.cv"/></th>
    <td align="left">
      <bean:define id="documentUrl" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.documentUrl" type="java.lang.String" />&nbsp;<html:link href="<%= request.getContextPath() + documentUrl %>" target="_blank"><bean:message key="link.view"/></html:link>
    </td>
  </tr>
  </logic:notEmpty>
  <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.hasBeenFilled" value="true">
  <tr>
    <th align="left" class="label"><bean:message key="label.login"/></th>
    <td align="left">
    <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.login"/>
    <logic:notEmpty name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantEmailAddress">
    
		<mmj-agy:hasAccess forward="sendConfirmationEmail">
		  &nbsp;
		  <html:link forward="sendConfirmationEmail" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="BookingGradeApplicantViewFormAgy" paramProperty="bookingGradeApplicant.bookingGradeApplicantId" titleKey="title.sendConfirmationEmail">
		    <bean:message key="link.sendConfirmationEmail"/>
		  </html:link>
		</mmj-agy:hasAccess>	  

		<mmj-agy:hasAccess forward="sendClientConfirmationEmail">
		  &nbsp;
		  <html:link forward="sendClientConfirmationEmail" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="BookingGradeApplicantViewFormAgy" paramProperty="bookingGradeApplicant.bookingGradeApplicantId" titleKey="title.sendClientConfirmationEmail">
		    <bean:message key="link.sendClientConfirmationEmail"/>
		  </html:link>
		</mmj-agy:hasAccess>	  

<%--
<mmj-agy:agency var="agency"/>
<bean:define id="agencyName" name="agency" property="name" type="java.lang.String"/>
<mmj-agy:consultant var="consultant"/>
<bean:define id="bookingId" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingId" type="java.lang.Integer"/>

<%/* manually replacing & with %26 */%>

<bean:define id="clientName" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.clientName" type="java.lang.String"/>
<bean:define id="locationName" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.locationName" type="java.lang.String"/>
<bean:define id="siteName" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.siteName" type="java.lang.String"/>
<bean:define id="jobProfileName" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.jobProfileName" type="java.lang.String"/>
<bean:define id="gradeName" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.gradeName" type="java.lang.String"/>

<% 
clientName = clientName.replaceAll("&", "%26");
locationName = locationName.replaceAll("&", "%26");
siteName = siteName.replaceAll("&", "%26");
jobProfileName = jobProfileName.replaceAll("&", "%26");
gradeName = gradeName.replaceAll("&", "%26");
%>

<a href="
mailto:<bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantEmailAddress"/>
?
subject=<bean:message key="emailSubject.applicantBookingConfirmation" arg0="<%= agencyName %>"/>
&
body=
Hi <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantFirstName"/>
%0A%0A
This is your booking confirmation from <%= agencyName %> - <bean:message key="label.bookingNo"/> <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingId" format="000"/>.
%0A%0A
<bean:message key="label.client"/>: <%= clientName %>
%0A
<bean:message key="label.location"/>: <%= locationName %>, <%= siteName %>
%0A
<bean:message key="label.jobProfile"/>: <%= jobProfileName %>
%0A
<bean:message key="label.grade"/>: <%= gradeName %>
%0A
<bean:message key="label.wageRate"/>: <bean:message key="label.currencySymbolActual"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.wageRate" format="#,##0.00" />
%0A
<bean:message key="label.startDate"/>: <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.minBookingDate" formatKey="format.longDateFormat"/>
%0A
<bean:message key="label.startTime"/>: <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.minBookingDateShiftStartTime" format="HH:mm"/>
%0A%0A
Match My Job (MMJ) provide us with an online timesheet service. Please use the following link and login details to enter your times
<logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
 and expenses
</logic:greaterThan>
.
%0A%0A
<%= "http://" + request.getServerName() + "/timesheet" %>
%0A%0A
<bean:message key="label.login"/>: <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.login"/>
%0A
<bean:message key="label.pwd"/>: <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.pwd"/>
%0A%0A
On initial entry to MMJ you will be asked to enter a secret word. 
Your secret word must be between 8-20 characters and contain at least one number (0-9) and at least one letter (a-z, A-Z). 
On subsequent entry to MMJ you will be asked to enter 3 random letters from your secret word so try to make it memorable. 
If you forget your secret word please contact us and we can reset it for you.
%0A%0A
Any problems or feedback please don't hesitate to contact us.
%0A%0A
Regards
%0A%0A
<bean:write name="agencyName"/>
">
email
</a>
--%>

    </logic:notEmpty>

    <logic:notEmpty name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantMobileNumber">
		<mmj-agy:hasAccess forward="sendConfirmationSms">
		  &nbsp;
		  <html:link forward="sendConfirmationSms" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="BookingGradeApplicantViewFormAgy" paramProperty="bookingGradeApplicant.bookingGradeApplicantId" titleKey="title.sendConfirmationSms">
		    <bean:message key="link.sendConfirmationSms"/>
		  </html:link>
		</mmj-agy:hasAccess>	  
    </logic:notEmpty>    

    </td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.pwd"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.pwd"/></td>
  </tr>
  </logic:equal>  
  <tr>
<%
  String tempFilePath = request.getContextPath() + checklistFileUrl;
%>
    <th align="left" class="label">Checklist</th>
    <td align="left">
<logic:present name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.checklistCreatedTime">
      <bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.checklistCreatedTime" formatKey="format.longDateTimeFormat"/>&nbsp;<html:link href="<%= tempFilePath %>" target="pdf"><bean:message key="link.view"/></html:link>
</logic:present>					    
<logic:notPresent name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.checklistCreatedTime">
      &nbsp;
</logic:notPresent>					    
    </td>
  </tr>
</table>
    </td>
    <td align="center" valign="top" width="25%">
<logic:empty name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantPhotoFilename" >
<bean:message key="text.noPhotoAvailable"/>
</logic:empty>
<logic:notEmpty name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantPhotoFilename" >
<bean:define id="photoFileUrl" name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.applicantPhotoFileUrl" type="java.lang.String" />
<html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
</logic:notEmpty>
    </td>
  </tr>
</table>
<br/>

<table class="simple" width="100%">
  <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.hasBeenFilled" value="true">
  <tr>
    <td align="left" colspan="5">&nbsp;</td>
    <th align="center" colspan="3">
      <bean:message key="label.agreed"/>
    </th>
		<bean:define id="colspan" value="3"/>
		<logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
		<bean:define id="colspan" value="4"/>
		</logic:greaterThan>
    <th align="center" colspan="<bean:write name="colspan"/>">
      <bean:message key="label.actual"/>
    </th>
    <td align="left" colspan="1">&nbsp;</td>
  </tr>
  </logic:equal>
  <tr>
    <th align="center">
			<bean:message key="label.shiftNo"/>
    </th>
    <th align="left">
			<bean:message key="label.date"/>
    </th>
    <th align="left">
			<bean:message key="label.shift"/>
    </th>
    <th align="left">
			<bean:message key="label.time"/>
    </th>
    <th align="left">
			<bean:message key="label.break"/>
    </th>
    <th align="center">
			<bean:message key="label.hrs"/>
    </th>
    <th align="right">
			<bean:message key="label.charge"/>
    </th>
    <th align="right">
			<bean:message key="label.pay"/>
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
			  &nbsp;(<bean:message key="label.wage"/>)
			</logic:equal>
    </th>
    <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.hasBeenFilled" value="true">
    <th align="center">
			<bean:message key="label.hrs"/>
    </th>
    <th align="right">
			<bean:message key="label.charge"/>
    </th>
    <th align="right">
			<bean:message key="label.pay"/>
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
			  &nbsp;(<bean:message key="label.wage"/>)
			</logic:equal>
    </th>
     <logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
    <th align="center">
			<bean:message key="label.expenses"/>
    </th>
    </logic:greaterThan>
    </logic:equal>
    <th align="center">
			<bean:message key="label.status"/>
    </th>
  </tr>
  <logic:iterate name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" id="bookingGradeApplicantDate" >
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left">
	    <bean:write name="bookingGradeApplicantDate" property="bookingId" format="#000"/>.<bean:write name="bookingGradeApplicantDate" property="bookingDateId" format="#000"/>
	  </td>
	  <td align="left">
	    <bean:write name="bookingGradeApplicantDate" property="bookingDate" formatKey="format.longDateFormat"/>
	  </td>
	  <td align="left">
	    <bean:write name="bookingGradeApplicantDate" property="shiftName"/>
	  </td>
	  <td align="left">
      <logic:equal name="bookingGradeApplicantDate" property="undefinedShift" value="true">
        Undefined
      </logic:equal>
      <logic:notEqual name="bookingGradeApplicantDate" property="undefinedShift" value="true">
  	    <bean:write name="bookingGradeApplicantDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="shiftEndTime" format="HH:mm"/>
      </logic:notEqual>
	  </td>
	  <td align="left">
      <logic:equal name="bookingGradeApplicantDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingGradeApplicantDate" property="undefinedShift" value="true">
	      <bean:write name="bookingGradeApplicantDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="shiftBreakEndTime" format="HH:mm"/>
        (<bean:write name="bookingGradeApplicantDate" property="shiftBreakNoOfHours" format="#0.00"/>)
      </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingGradeApplicantDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingGradeApplicantDate" property="undefinedShift" value="true">
	      <logic:equal name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
	        <s><bean:write name="bookingGradeApplicantDate" property="shiftNoOfHours" format="#0.00"/></s>
	      </logic:equal> 
	      <logic:notEqual name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
	        <bean:write name="bookingGradeApplicantDate" property="shiftNoOfHours" format="#0.00"/>
	      </logic:notEqual> 
      </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingGradeApplicantDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingGradeApplicantDate" property="undefinedShift" value="true">
	      <logic:equal name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
	        <s><bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="value" format="#,##0.00"/></s>
	      </logic:equal> 
	      <logic:notEqual name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
	        <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="value" format="#,##0.00"/>
	      </logic:notEqual>
	    </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingGradeApplicantDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingGradeApplicantDate" property="undefinedShift" value="true">
	      <logic:equal name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
	        <s><bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="payRateValue" format="#,##0.00"/></s>
	      </logic:equal> 
	      <logic:notEqual name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
	        <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="payRateValue" format="#,##0.00"/>
	      </logic:notEqual>
        <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
	        &nbsp;(<bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="wageRateValue" format="#,##0.00"/>)
	      </logic:equal>
	    </logic:notEqual>
<!-- 
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="bookingGradePayRate" format="#,##0.00"/>
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="bookingGradeWtdRate" format="#,##0.00"/>
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="bookingGradeNiRate" format="#,##0.00"/>
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="bookingGradeWageRate" format="#,##0.00"/>
--> 

	  </td>
    <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.hasBeenFilled" value="true">
	  <td align="right">
      &nbsp;
	  </td>
	  <td align="right">
      &nbsp;
	  </td>
	  <td align="right">
      &nbsp;
	  </td>
    <logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
	  <td align="right">
	    &nbsp;
	  </td>
    </logic:greaterThan>
	  </logic:equal>
	  <td align="left">
      <logic:equal name="bookingGradeApplicantDate" property="isFilled" value="true">
        <logic:equal name="bookingGradeApplicantDate" property="bookingDateActivated" value="true">
    	    <bean:message key="label.activated"/>
        </logic:equal>
        <logic:notEqual name="bookingGradeApplicantDate" property="bookingDateActivated" value="true">
    	    <bean:message key="label.notActivated"/>
        </logic:notEqual>
      </logic:equal>
      <logic:notEqual name="bookingGradeApplicantDate" property="isFilled" value="true">
  	    <bean:message name="bookingGradeApplicantDate" property="statusDescriptionKey"/>
  	    (<bean:message name="bookingGradeApplicantDate" property="bookingDateStatusDescriptionKey"/>)
      </logic:notEqual>
	  </td>
	</tr>
	<logic:equal name="bookingGradeApplicantDate" property="hasBeenEntered" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left" colspan="3">
	    &nbsp;
	  </td>
	  <td align="left">
	    <bean:write name="bookingGradeApplicantDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="workedEndTime" format="HH:mm"/>
	  </td>
	  <td align="left">
	    <bean:write name="bookingGradeApplicantDate" property="workedBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="workedBreakEndTime" format="HH:mm"/>
      (<bean:write name="bookingGradeApplicantDate" property="workedBreakNoOfHours" format="#0.00"/>)
	  </td>
	  <td align="right">
	    &nbsp;
	  </td>
	  <td align="right">
      &nbsp;
	  </td>
	  <td align="right">
      &nbsp;
	  </td>
	  <td align="right">
	    <bean:write name="bookingGradeApplicantDate" property="workedNoOfHours" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="workedChargeRateValue" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="workedPayRateValue" format="#0.00"/>
      (<bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="workedWageRateValue" format="#,##0.00"/>)
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="workedVatValue" format="#,##0.00"/>
	  </td>
    <logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
	  <td align="left">
	    &nbsp;
	  </td>
    </logic:greaterThan>
	  <td align="left">
	    <bean:message name="bookingGradeApplicantDate" property="workedStatusDescriptionKey"/>
	  </td>
	</tr>
	</logic:equal>
  
  <logic:notEmpty name="bookingGradeApplicantDate" property="bookingDateExpenseUsers" >
  <logic:iterate name="bookingGradeApplicantDate" property="bookingDateExpenseUsers" id="bookingDateExpense" >
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left" colspan="11">
      <bean:write name="bookingDateExpense" property="expenseName"/>
		  <logic:notEmpty name="bookingDateExpense" property="expenseDescription">
      (<bean:write name="bookingDateExpense" property="expenseDescription"/>)
      </logic:notEmpty>
		  <logic:equal name="bookingDateExpense" property="isMultiplier" value="true">
      <bean:write name="bookingDateExpense" property="qty" format="#0.00"/>
		  @
		  <bean:message key="label.currencySymbol"/><bean:write name="bookingDateExpense" property="expenseMultiplier" format="#0.00"/>
  		</logic:equal>
		  <logic:notEmpty name="bookingDateExpense" property="text">
      -
      <bean:write name="bookingDateExpense" property="text"/>
		  </logic:notEmpty>
    </td>
	  <td align="right">
      <bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="value" format="#0.00"/>
      <bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="vatValue" format="#0.00"/>
    </td>
	  <td align="left">
		  <logic:empty name="bookingDateExpense" property="filename">
      &nbsp;
		  </logic:empty>
		  <logic:notEmpty name="bookingDateExpense" property="filename">
      <bean:define id="documentUrl" name="bookingDateExpense" property="documentUrl" type="java.lang.String" />
      <html:link href="<%= request.getContextPath() + documentUrl %>" target="_blank"><bean:message key="link.receipt"/></html:link>
		  </logic:notEmpty>
    </td>
  </tr>
  </logic:iterate>
  </logic:notEmpty>
  
  <logic:notEmpty name="bookingGradeApplicantDate" property="comment">
  <tr>
	<th align="left" class="label" colspan="3"><bean:message key="label.comment" /></th>
	<bean:define id="colspan" value="9"/>
	<logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
	<bean:define id="colspan" value="10"/>
	</logic:greaterThan>
    <td align="left" colspan="<bean:write name="colspan"/>">
      <bean:write name="bookingGradeApplicantDate" property="comment"/>
    </td>
  </tr>
  </logic:notEmpty>
  
  <logic:equal name="bookingGradeApplicantDate" property="workedStatusIsRejected" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	<th align="left" class="label" colspan="3"><bean:message key="label.rejectText" /></th>
	<bean:define id="colspan" value="9"/>
	<logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
	<bean:define id="colspan" value="10"/>
	</logic:greaterThan>
    <td align="left" colspan="<bean:write name="colspan"/>">
      <bean:write name="bookingGradeApplicantDate" property="rejectText"/>
    </td>
  </tr>
  </logic:equal>
	
	<bean:define id="colspan" value="12"/>
	<logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
	<bean:define id="colspan" value="13"/>
	</logic:greaterThan>
  <tr><th align="left" colspan="<bean:write name="colspan"/>" height="3"></th></tr>
	</logic:iterate>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="5"><bean:message key="label.total"/></th>
		<td align="right"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalHours" format="#0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalAgreedValue" format="#,##0.00" /></td>
		<td align="right">
		  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalAgreedPayValue" format="#,##0.00" />
		  (<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalAgreedWageValue" format="#,##0.00" />)
		</td>
    <logic:equal name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.hasBeenFilled" value="true">
		<td align="right"><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalActualHours" format="#0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalActualValue" format="#,##0.00" /></td>
		<td align="right">
		  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalActualPayValue" format="#,##0.00" />
		  (<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalActualWageValue" format="#,##0.00" />)
		</td>
    <logic:greaterThan name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
	  <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormAgy" property="bookingGradeApplicant.totalExpenseValue" format="#,##0.00" /></td>
    </logic:greaterThan>
	  </logic:equal>
		<th align="right">&nbsp</th>
  </tr>
  
</table>
