<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isOffer" name="isOffer" value="false"/>
<bean:parameter id="isReject" name="isReject" value="false"/>
<bean:parameter id="isActivate" name="isActivate" value="false"/>
<bean:parameter id="isRenegotiate" name="isRenegotiate" value="false"/>

<%-- title and buttons --%>
<jsp:include page="bookingGradeApplicantViewHeader.jsp" flush="true">
  <jsp:param name="isView" value="<%= isView %>"/>
  <jsp:param name="isOffer" value="<%= isOffer %>"/>
  <jsp:param name="isReject" value="<%= isReject %>"/>
  <jsp:param name="isActivate" value="<%= isActivate %>"/>
  <jsp:param name="isRenegotiate" value="<%= isRenegotiate %>"/>
</jsp:include>

<logic:equal name="isReject" value="true">
  <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeRejected" value="true">
    <mmj-mgr:hasAccess forward="bookingGradeApplicantReject">
<html:errors/>
<table class="simple" width="100%">
<form name="RejectFormMgr">
  <tr>
    <th align="left" width="25%"><bean:message key="label.rejectText"/></th>
    <td align="left" width="75%"><input type="text" name="rejectText" size="99"/></td>
  </tr>
</form>
<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["RejectFormMgr"].elements["rejectText"];

  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
  // -->
</script>
</table>
<br/>
    </mmj-mgr:hasAccess>
  </logic:equal>
</logic:equal>

<logic:equal name="isRenegotiate" value="true">
  <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeMarkedRenegotiate" value="true">
    <mmj-mgr:hasAccess forward="bookingGradeApplicantRenegotiate">
<html:errors/>
<table class="simple" width="100%">
<form name="RenegotiateFormMgr">
  <tr>
    <th align="left" width="25%"><bean:message key="label.renegotiateText"/></th>
    <td align="left" width="75%"><input type="text" name="renegotiateText" size="99"/></td>
  </tr>
</form>
<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["RenegotiateFormMgr"].elements["renegotiateText"];

  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
  // -->
</script>
</table>
<br/>
    </mmj-mgr:hasAccess>
  </logic:equal>
</logic:equal>




<bean:define id="leftColumnWidth" value="75%"/>
<logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.showPhotoToManager" value="true">
  <bean:define id="leftColumnWidth" value="100%"/>
</logic:notEqual>

<table cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td align="left" valign="top" width="<bean:write name="leftColumnWidth"/>">
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="33%"><bean:message key="label.bookingNo"/></th>
    <td align="left" width="67%">
    <mmj-mgr:hasAccess forward="bookingViewApplicants">
    <html:link forward="bookingViewApplicants" paramId="booking.bookingId" paramName="BookingGradeApplicantViewFormMgr" paramProperty="bookingGradeApplicant.bookingId" ><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingId" format="#000"/></html:link>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingViewApplicants">
    <bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingId" format="#000"/>
    </mmj-mgr:hasNoAccess>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.location"/></th>
    <td align="left">
      <bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.locationName" />,
      <bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.siteName" />
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.jobProfile"/></th>
    <td align="left">
      <bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.jobProfileName" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.name"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantFullNameLastFirst"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.agency"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.agencyName"/></td>
  </tr>
<%--
  <tr>
    <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantEmailAddress"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.niNumber"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantNiNumber"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.reference"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantReference"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.professionalReference"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantProfessionalReference"/></td>
  </tr>
--%>
  <tr>
    <th align="left" class="label"><bean:message key="label.gender"/></th>
    <td align="left"><bean:message name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantGenderDescriptionKey"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.dateOfBirth"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantDateOfBirth" formatKey="format.mediumDateFormat" /></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.grade"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.gradeName"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.rate"/></th>
    <td align="left"><bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.rate" format="#,##0.00" /></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.status"/></th>
    <td align="left"><bean:message name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.statusDescriptionKey"/></td>
  </tr>
  <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.hasBeenRejected" value="true">
  <tr>
    <th align="left" class="label"><bean:message key="label.rejectText"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.rejectText"/></td>
  </tr>
  </logic:equal>
  <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeRenegotiated" value="true">
  <tr>
    <th align="left" class="label"><bean:message key="label.renegotiateText"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.renegotiateText"/></td>
  </tr>
  </logic:equal>
  <logic:notEmpty name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.filename">
  <tr>
    <th align="left" class="label"><bean:message key="label.document"/></th>
    <td align="left">
      <bean:define id="documentUrl" name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.documentUrl" type="java.lang.String" />
      <html:link href="<%= request.getContextPath() + documentUrl %>" target="_blank"><bean:message key="link.viewFile"/></html:link>
    </td>
  </tr>
  </logic:notEmpty>
  <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.hasBeenFilled" value="true">
<%--
  <tr>
    <th align="left" class="label"><bean:message key="label.login"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.login"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.pwd"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.pwd"/></td>
  </tr>
--%>
<%--
  <tr>
    <th align="left" class="label"><bean:message key="label.activated"/></th>
    <td align="left">
		  <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.activated" value="true">
		    <bean:message key="label.yes"/>
		  </logic:equal>
		  <logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.activated" value="true">
		    <bean:message key="label.no"/>
		  </logic:notEqual>
    </td>
  </tr>
--%>
  </logic:equal>  
</table>
    </td>
<logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.showPhotoToManager" value="true">
    <td align="center" valign="top" width="25%">
<logic:empty name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantPhotoFilename" >
<bean:message key="text.noPhotoAvailable"/>
</logic:empty>
<logic:notEmpty name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantPhotoFilename" >
<bean:define id="photoFileUrl" name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.applicantPhotoFileUrl" type="java.lang.String" />
<html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
</logic:notEmpty>
    </td>
</logic:equal>
  </tr>
</table>
<br/>

<logic:notEmpty name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" >
<table class="simple" width="100%">
  <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.hasBeenFilled" value="true">
  <tr>
    <td align="left" colspan="6">&nbsp;</td>
    <th align="center" colspan="3">
      <bean:message key="label.agreed"/>
    </th>
		<bean:define id="colspan" value="3"/>
		<logic:greaterThan name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
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
			<bean:message key="label.rrp"/>
    </th>
    <th align="center">
			<bean:message key="label.hrs"/>
    </th>
    <th align="center">
			<bean:message key="label.charge"/>
    </th>
    <th align="center">
			<bean:message key="label.wage"/>
			(<bean:message key="label.pay"/>)
    </th>
    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.hasBeenFilled" value="true">
    <th align="center">
			<bean:message key="label.hrs"/>
    </th>
    <th align="center">
			<bean:message key="label.charge"/>
    </th>
    <th align="center">
			<bean:message key="label.wage"/>
			(<bean:message key="label.pay"/>)
    </th>
    </logic:equal>
    <logic:greaterThan name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
    <th align="center">
			<bean:message key="label.expenses"/>
    </th>
    </logic:greaterThan>
    <th align="center">
			<bean:message key="label.status"/>
    </th>
  </tr>
<logic:iterate id="bookingGradeApplicantDate" name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" >
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
		  <bean:write name="bookingGradeApplicantDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="shiftEndTime" format="HH:mm"/>
		</td>
		<td align="left">
		  <bean:write name="bookingGradeApplicantDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="shiftBreakEndTime" format="HH:mm"/>
     (<bean:write name="bookingGradeApplicantDate" property="shiftBreakNoOfHours" format="#0.00"/>)
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="bookingDateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:write name="bookingGradeApplicantDate" property="shiftNoOfHours" format="#0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="value" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="wageRateValue" format="#,##0.00"/>
      (<bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="payRateValue" format="#,##0.00"/>)
    </td>
    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.hasBeenFilled" value="true">
    <td align="left">
      &nbsp;
    </td>
    <td align="left">
      &nbsp;
    </td>
    <td align="left">
      &nbsp;
    </td>
  	<logic:greaterThan name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
    <td align="left">
      &nbsp;
    </td>
    </logic:greaterThan>
    </logic:equal>
    <td align="left">
 			<logic:equal name="bookingGradeApplicantDate" property="isFilled" value="true">
 			<logic:equal name="bookingGradeApplicantDate" property="isActivated" value="true">
        <bean:message key="text.activated"/>
			</logic:equal>
 			<logic:equal name="bookingGradeApplicantDate" property="canBeActivated" value="true">
				<mmj-mgr:hasAccess forward="bookingDateActivate">
				<html:link forward="bookingDateActivate" paramId="bookingDate.bookingDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingDateId">
          <bean:message key="link.activate"/>
				</html:link>
				</mmj-mgr:hasAccess>
			</logic:equal>
			</logic:equal>
 			<logic:notEqual name="bookingGradeApplicantDate" property="isFilled" value="true">
      <bean:message name="bookingGradeApplicantDate" property="statusDescriptionKey"/>
 			</logic:notEqual>
<%--
	    (<bean:message name="bookingGradeApplicantDate" property="bookingDateStatusDescriptionKey"/>)
--%>
    </td>
  </tr>
      	<logic:notEmpty name="bookingGradeApplicantDate" property="workedStartTime">
       	<logic:notEqual name="bookingGradeApplicantDate" property="workedStatusIsDraft" value="true">
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
				  <td align="left">
				    &nbsp;
				  </td>
  			  <td align="right">
				    &nbsp;
				  </td>
  			  <td align="right">
				    &nbsp;
				  </td>
				  <td align="right" nowrap="nowrap">
				    <logic:equal name="bookingGradeApplicantDate" property="hasUplift" value="true">
				    *
				    </logic:equal>
				    <bean:write name="bookingGradeApplicantDate" property="workedNoOfHours" format="#0.00"/>
				  </td>
				  <td align="right">
				    <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="workedChargeRateValue" format="#,##0.00"/>
				  </td>
				  <td align="right">
			      <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="workedWageRateValue" format="#,##0.00"/>
			      (<bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="workedPayRateValue" format="#,##0.00"/>)
				  </td>
					<logic:greaterThan name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
  			  <td align="right">
				    &nbsp;
				  </td>
          </logic:greaterThan>
				  <td align="left">
			  	<logic:equal name="bookingGradeApplicantDate" property="canBeAuthorized" value="true">

			  	<%/* if it can be authorised it can be rejected - manager will probably have access to both authorise and reject */%>
			    <mmj-mgr:hasAccess forward="bookingDateAuthorize">
			      <html:link forward="bookingDateAuthorize" paramId="bookingDate.bookingDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingDateId"><bean:message key="link.authorize"/></html:link>
			    </mmj-mgr:hasAccess>
			    <mmj-mgr:hasNoAccess forward="bookingDateAuthorize">
				    <bean:message name="bookingGradeApplicantDate" property="workedStatusDescriptionKey"/>
			    </mmj-mgr:hasNoAccess>
			    <mmj-mgr:hasAccess forward="bookingDateReject">
			      <html:link forward="bookingDateReject" paramId="bookingDate.bookingDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingDateId"><bean:message key="link.reject"/></html:link>
			    </mmj-mgr:hasAccess>

<%-- 
			    <mmj-mgr:hasAccess forward="bookingGradeApplicantDateAuthorize">
			      <html:link forward="bookingGradeApplicantDateAuthorize" paramId="bookingGradeApplicantDate.bookingGradeApplicantDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingGradeApplicantDateId"><bean:message key="link.authorize"/></html:link>
			    </mmj-mgr:hasAccess>
			    <mmj-mgr:hasAccess forward="bookingGradeApplicantDateReject">
			      <html:link forward="bookingGradeApplicantDateReject" paramId="bookingGradeApplicantDate.bookingGradeApplicantDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingGradeApplicantDateId"><bean:message key="link.reject"/></html:link>
			    </mmj-mgr:hasAccess>
--%>
			    </logic:equal>
			    <logic:notEqual name="bookingGradeApplicantDate" property="canBeAuthorized" value="true">
 	    	    <bean:message name="bookingGradeApplicantDate" property="workedStatusDescriptionKey"/>
          </logic:notEqual>			    
				  </td>
				</tr>

       	<logic:notEqual name="bookingGradeApplicantDate" property="workedStatusIsDraft" value="true">
	
  <logic:notEmpty name="bookingGradeApplicantDate" property="bookingDateExpenseUsers" >
  <logic:iterate name="bookingGradeApplicantDate" property="bookingDateExpenseUsers" id="bookingDateExpense" >
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left" colspan="12">
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
	
        </logic:notEqual>




			  <logic:equal name="bookingGradeApplicantDate" property="workedStatusIsRejected" value="true">
			  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
				  <td align="left" colspan="3">
				    &nbsp;
				  </td>
					<bean:define id="colspan" value="10"/>
					<logic:greaterThan name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
					<bean:define id="colspan" value="11"/>
					</logic:greaterThan>
				  <td align="left" colspan="<bean:write name="colspan"/>">
			      <bean:write name="bookingGradeApplicantDate" property="rejectText"/>
			    </td>
			  </tr>
        </logic:equal>
        </logic:notEqual>
				</logic:notEmpty>
	<bean:define id="colspan" value="10"/>
  <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.hasBeenFilled" value="true">
    <logic:greaterThan name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
   	<bean:define id="colspan" value="14"/>
    </logic:greaterThan>
    <logic:lessEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
  	<bean:define id="colspan" value="13"/>
    </logic:lessEqual>
  </logic:equal>
  <tr><th align="left" colspan="<bean:write name="colspan"/>" height="3"></th></tr>
      </logic:iterate>
      <logic:greaterThan name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalHours" value="0">
        <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
          <th align="left" colspan="5"><bean:message key="label.total"/></th>
          <td align="right">
			     	<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalRRPValue" format="#,##0.00"/>
          </td>
          <td align="right">
            <bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalHours" format="#0.00"/>
          </td>
          <td align="right">
			     	<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalAgreedValue" format="#,##0.00"/>
          </td>
          <td align="right">
			     	<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalAgreedWageValue" format="#,##0.00"/>
			     	(<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalAgreedPayValue" format="#,##0.00"/>)
          </td>
          <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.hasBeenFilled" value="true">
          <td align="right">
			     	<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalActualHours" format="#0.00"/>
          </td>
          <td align="right">
			     	<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalActualValue" format="#,##0.00"/>
          </td>
          <td align="right">
			     	<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalActualWageValue" format="#,##0.00"/>
			     	(<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalActualPayValue" format="#,##0.00"/>)
          </td>
					<logic:greaterThan name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingExpensesCount.value" value="0">
          <td align="right">
			     	<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.totalExpenseValue" format="#,##0.00"/>
          </td>
          </logic:greaterThan>
          </logic:equal>
          <th align="left">
      			&nbsp;
          </th>
        </tr>
      </logic:greaterThan>
      </table>
      
</logic:notEmpty>
