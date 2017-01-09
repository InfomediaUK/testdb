<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>
<bean:parameter id="isActivate" name="isActivate" value="false"/>
<bean:parameter id="isAuthorize" name="isAuthorize" value="false"/>
<bean:parameter id="isReject" name="isReject" value="false"/>
<bean:parameter id="isInvoice" name="isInvoice" value="false"/>

<bean:parameter id="tab" name="tab" value="summary"/>

<bean:define id="formAction" value="home.do"/>

<%/* VIEW */%> 
<logic:equal name="isView" value="true">		
<bean:define id="formAction" value="bookingDateCancelProcess.do"/>
</logic:equal>

<%/* CANCEL */%> 
<logic:equal name="isCancel" value="true">		
<bean:define id="formAction" value="bookingDateCancelProcess.do"/>
</logic:equal>

<%/* ACTIVATE */%> 
<logic:equal name="isActivate" value="true">		
<bean:define id="formAction" value="bookingDateActivateProcess.do"/>
</logic:equal>

<%/* AUTHORIZE */%> 
<logic:equal name="isAuthorize" value="true">		
<bean:define id="formAction" value="bookingDateAuthorizeProcess.do"/>
</logic:equal>

<%/* REJECT */%> 
<logic:equal name="isReject" value="true">		
<bean:define id="formAction" value="bookingDateRejectProcess.do"/>
</logic:equal>

<%/* INVOICE */%> 
<logic:equal name="isInvoice" value="true">		
<bean:define id="formAction" value="bookingDateInvoiceProcess.do"/>
</logic:equal>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="<%= formAction %>" onsubmit="return singleSubmit();">
	<html:hidden property="bookingDate.bookingId" />
	<html:hidden property="bookingDate.bookingDateId" />
	<html:hidden property="bookingDate.noOfChanges" />
	<%-- used to lock the booking --%>
	<html:hidden property="bookingId"/>
  <tr>

<%/* VIEW */%> 
<logic:equal name="isView" value="true">		
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDateView"/>
<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.activated" value="true">
			<mmj-mgr:hasAccess forward="xbookingDateViewPDF">
			<html:link forward="bookingDateViewPDF" target="_blank" paramId="bookingDate.bookingDateId" paramName="BookingDateUserApplicantFormMgr" paramProperty="bookingDate.bookingDateId">    			
			PDF
			</html:link>
			</mmj-mgr:hasAccess>
</logic:equal>      
		</td>
</logic:equal>

<%/* ACTIVATE */%> 
<logic:equal name="isActivate" value="true">		
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDateActivate"/>
		</td>
    <td align="right" valign="middle" width="75">
		<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeActivated" value="true">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </logic:equal>
		<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeActivated" value="true">
      &nbsp;
    </logic:notEqual> 
    </td>
</logic:equal>

<%/* CANCEL */%> 
<logic:equal name="isCancel" value="true">		
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDateCancel"/>
		</td>
    <td align="right" valign="middle" width="75">
	<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeCancelled" value="true">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </logic:equal>
	<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeCancelledCompleted" value="true">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </logic:equal>
	<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeCancelled" value="true">
	<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeCancelledCompleted" value="true">
      &nbsp;
    </logic:notEqual> 
    </logic:notEqual> 
    </td>
</logic:equal>

<%/* AUTHORIZE */%> 
<logic:equal name="isAuthorize" value="true">		
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDateAuthorize"/>
		</td>
    <td align="right" valign="middle" width="75">
		<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeAuthorized" value="true">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </logic:equal>
		<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeAuthorized" value="true">
      &nbsp;
    </logic:notEqual> 
    </td>
</logic:equal>

<%/* REJECT */%> 
<logic:equal name="isReject" value="true">		
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDateReject"/>
		</td>
    <td align="right" valign="middle" width="75">
		<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeAuthorized" value="true">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </logic:equal>
		<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeAuthorized" value="true">
      &nbsp;
    </logic:notEqual> 
    </td>
</logic:equal>

<%/* INVOICE */%> 
<logic:equal name="isInvoice" value="true">		
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDateInvoice"/>
		</td>
    <td align="right" valign="middle" width="75">
		<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeInvoiced" value="true">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </logic:equal>
		<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.canBeInvoiced" value="true">
      &nbsp;
    </logic:notEqual> 
    </td>
</logic:equal>

  </tr>
</table>

<html:errors/>

<%/* CANCEL */%> 
<logic:equal name="isCancel" value="true">		
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.cancelText"/></th>
    <td align="left" width="75%">
  		<html:text property="bookingDate.cancelText" size="99" />
    </td>
  </tr>
</table>
<br/>
</logic:equal>

<%/* REJECT */%> 
<logic:equal name="isReject" value="true">		
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.rejectText"/></th>
    <td align="left" width="75%">
  		<html:text property="bookingDate.rejectText" size="99" />
    </td>
  </tr>
</table>
<br/>
</logic:equal>

<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.shiftNo"/></th>
    <td align="left" width="75%">
			<mmj-mgr:hasAccess forward="bookingViewShifts">
			  <html:link forward="bookingViewShifts" paramId="booking.bookingId" paramName="BookingDateUserApplicantFormMgr" paramProperty="bookingDate.bookingId">
          <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingId" format="#000"/>.<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateId" format="#000"/>
        </html:link>
			</mmj-mgr:hasAccess>
			<mmj-mgr:hasNoAccess forward="bookingViewShifts">
        <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingId" format="#000"/>.<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateId" format="#000"/>
      </mmj-mgr:hasNoAccess>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.location"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.locationName" />,
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.siteName" />
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.jobProfile"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.jobProfileName" />
    </td>
  </tr>
<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantId" value="0">
  <tr>
    <th align="left"><bean:message key="label.grade"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.gradeName" />
    </td>
  </tr>
</logic:notEqual>  
  <tr>
    <th align="left"><bean:message key="label.date"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDate" formatKey="format.longDateFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.shift"/></th>
    <td align="left">
			<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftName"/>
    </td>
  </tr>
<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantId" value="0">
  <tr>
    <th align="left"><bean:message key="label.time"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftEndTime" format="HH:mm"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.break"/></th>
    <td align="left">
		  <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftBreakEndTime" format="HH:mm"/>
      (<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftBreakNoOfHours" format="#0.00"/>)
		</td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.noOfHours"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftNoOfHours" format="#0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.rrpValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.value" format="#,##0.00"/>
    </td>
  </tr>
</logic:equal>
  <tr>
    <th align="left"><bean:message key="label.status"/></th>
    <td align="left">
	    <bean:message name="BookingDateUserApplicantFormMgr" property="bookingDate.statusDescriptionKey"/>
    </td>
  </tr>

<logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.cancelledTimestamp">
  <tr>
    <th align="left"><bean:message key="label.cancelledBy"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.cancelledByFirstName"/>
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.cancelledByLastName"/>
	    (<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.cancelledTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.activatedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.activatedBy"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.activatedByFirstName"/>
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.activatedByLastName"/>
	    (<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.activatedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.authorizedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.authorizedBy"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.authorizedByFirstName"/>
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.authorizedByLastName"/>
	    (<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.authorizedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.workedStatusIsRejected" value="true">
<logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.rejectedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.rejectedBy"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.rejectedByFirstName"/>
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.rejectedByLastName"/>
	    (<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.rejectedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
</logic:equal>
<logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.invoicedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.invoicedBy"/></th>
    <td align="left">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.invoicedByFirstName"/>
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.invoicedByLastName"/>
	    (<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.invoicedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>

</html:form>
</table>

<%/* CANCEL */%> 
<logic:equal name="isCancel" value="true">		
<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["BookingDateUserApplicantFormMgr"].elements["bookingDate.cancelText"];

  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
  // -->
</script>
</logic:equal>

<%/* REJECT */%> 
<logic:equal name="isReject" value="true">		
<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["BookingDateUserApplicantFormMgr"].elements["bookingDate.rejectText"];

  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
  // -->
</script>
</logic:equal>

<logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantId" value="0">
<br/>

<%-- tabs --%>
<jsp:include page="bookingDateViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<logic:equal name="tab" value="summary">

<table cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td align="left" valign="top" width="75%">
<table class="simple" width="100%">
  <tr>
    <th align="left" width="33%"><bean:message key="label.name"/></th>
    <td align="left" width="67%">
 	    <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantFirstName"/>
	    <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantLastName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.agency"/></th>
    <td align="left"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.agencyName"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.gender"/></th>
    <td align="left"><bean:message name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantGenderDescriptionKey"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.dateOfBirth"/></th>
    <td align="left"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantDateOfBirth" formatKey="format.mediumDateFormat" /></td>
  </tr>
</table>
<br/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">&nbsp;</th>
    <th align="right" width="15%"><bean:message key="label.time"/></th>
    <th align="right" width="20%"><bean:message key="label.break"/></th>
    <th align="right" width="10%"><bean:message key="label.noOfHours"/></th>
    <th align="right" width="15%"><bean:message key="label.rate"/></th>
    <th align="right" width="15%"><bean:message key="label.value"/></th>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left"><bean:message key="label.rrp"/></th>
    <th align="left" colspan="3">&nbsp;</th>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.rrpRate" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.value" format="#,##0.00"/>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left"><bean:message key="label.agreed"/></th>
    <td align="right">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftEndTime" format="HH:mm"/>
    </td>
    <td align="right">
	  <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftBreakEndTime" format="HH:mm"/>
      (<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftBreakNoOfHours" format="#0.00"/>)
	</td>
    <td align="right">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.shiftNoOfHours" format="#0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.chargeRate" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.chargeRateValue" format="#,##0.00" />
    </td>
  </tr>
<logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.activated" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left"><bean:message key="label.actual"/></th>
    <td align="right">
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedEndTime" format="HH:mm"/>
    </td>
    <td align="right">
	  <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedBreakEndTime" format="HH:mm"/>
      (<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedBreakNoOfHours" format="#0.00"/>)
	</td>
	  <td align="right" nowrap="nowrap">
      <logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.hasUplift" value="true">
      *
      </logic:equal>
      <bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNoOfHours" format="#0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.chargeRate" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedChargeRateValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" class="label"><bean:message key="label.workedVatValue"/></th>
    <th align="left" colspan="4">&nbsp;</th>
    <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedVatValue" format="#,##0.00" /></td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" class="label"><bean:message key="label.expenseValue"/></th>
    <th align="left" colspan="4">&nbsp;</th>
    <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.expenseValue" format="#,##0.00" /></td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" class="label"><bean:message key="label.expenseVatValue"/></th>
    <th align="left" colspan="4">&nbsp;</th>
    <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.expenseVatValue" format="#,##0.00" /></td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" class="label"><bean:message key="label.workedTotalValue"/></th>
    <th align="left" colspan="4">&nbsp;</th>
    <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedTotalValue" format="#,##0.00" /></td>
  </tr>
</logic:equal>
</table>
<logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.comment">
<br/>
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="25%"><bean:message key="label.comment"/></th>
    <td align="left" width="75%"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.comment"/></td>
  </tr>
</table>
</logic:notEmpty>
    </td>
    <td align="center" valign="top" width="25%">
      <br/>
<logic:empty name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantPhotoFilename" >
<bean:message key="text.noPhotoAvailable"/>
</logic:empty>
<logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantPhotoFilename" >
<bean:define id="photoFileUrl" name="BookingDateUserApplicantFormMgr" property="bookingDate.applicantPhotoFileUrl" type="java.lang.String" />
<html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
</logic:notEmpty>
    </td>
  </tr>
</table>  

</logic:equal>

<logic:equal name="tab" value="details">

<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.quantity"/></th>
    <th align="left"><bean:message key="label.details"/></th>
    <th align="left"><bean:message key="label.unitPrice"/></th>
    <th align="left"><bean:message key="label.net"/></th>
    <th align="left"><bean:message key="label.vatRate"/></th>
    <th align="left"><bean:message key="label.vat"/></th>
  </tr>
  <logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.chargeRateVatRate" value="0">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" nowrap="nowrap">
      <logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.hasUplift" value="true">
      *
      </logic:equal>
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNoOfHours" format="#0.00"/>
    </td>
    <td align="left">
<bean:message key="label.commission"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.commissionRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedCommissionValue" format="#,##0.00" />
    </td>
    <td align="right">
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.commissionVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedCommissionVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" nowrap="nowrap">
      <logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.hasUplift" value="true">
      *
      </logic:equal>
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNoOfHours" format="#0.00"/>
    </td>
    <td align="left">
<bean:message key="label.payRate"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.payRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedPayRateValue" format="#,##0.00" />
    </td>
    <td align="right">
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.payRateVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedPayRateVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" nowrap="nowrap">
      <logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.hasUplift" value="true">
      *
      </logic:equal>
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNoOfHours" format="#0.00"/>
    </td>
    <td align="left">
<bean:message key="label.wtd"/>&nbsp;@&nbsp;<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.wtdPercentage" format="#,##0.00" />%
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.wtdRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedWtdValue" format="#,##0.00" />
    </td>
    <td align="right">
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.wtdVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedWtdVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" nowrap="nowrap">
      <logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.hasUplift" value="true">
      *
      </logic:equal>
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNoOfHours" format="#0.00"/>
    </td>
    <td align="left">
<bean:message key="label.ni"/>&nbsp;@&nbsp;<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.niPercentage" format="#,##0.00" />%
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.niRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNiValue" format="#,##0.00" />
    </td>
    <td align="right">
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.niVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNiVatValue" format="#,##0.00" />
    </td>
  </tr>
  </logic:equal>
  <logic:notEqual name="BookingDateUserApplicantFormMgr" property="bookingDate.chargeRateVatRate" value="0">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" nowrap="nowrap">
      <logic:equal name="BookingDateUserApplicantFormMgr" property="bookingDate.hasUplift" value="true">
      *
      </logic:equal>
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNoOfHours" format="#0.00"/>
    </td>
    <td align="left">
<bean:message key="label.chargeRate"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.chargeRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedChargeRateValue" format="#,##0.00" />
    </td>
    <td align="right">
<bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.chargeRateVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedChargeRateVatValue" format="#,##0.00" />
    </td>
  </tr>
  </logic:notEqual>
  <logic:iterate id="bookingDateExpense" name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateExpenses" type="com.helmet.bean.BookingDateExpenseUser">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<logic:equal name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:write name="bookingDateExpense" property="qty" format="#0.00"/>
</logic:equal>
<logic:notEqual name="bookingDateExpense" property="isMultiplier" value="true">
  1.00
</logic:notEqual>
    </td>
    <td align="left">
 <bean:write name="bookingDateExpense" property="expenseName"/>
 <logic:notEmpty name="bookingDateExpense" property="text">
   -
   <bean:write name="bookingDateExpense" property="text"/>
 </logic:notEmpty>
    </td>
    <td align="right">
<logic:equal name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:message key="label.currencySymbol"/><bean:write name="bookingDateExpense" property="expenseMultiplier" format="#0.00"/>
</logic:equal>
<logic:notEqual name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="qty" format="#0.00"/>
</logic:notEqual>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="value" format="#0.00"/>
    </td>
    <td align="right">
<bean:write name="bookingDateExpense" property="expenseVatRate" format="#0.00"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="vatValue" format="#0.00"/>
    </td>
  </tr>
  </logic:iterate>
  <tr><td align="left" colspan="6">&nbsp;</td></tr>
  <tr>
    <td align="left" colspan="2" rowspan="3">&nbsp;</td>
    <th align="left" colspan="3"><bean:message key="label.totalNetAmount" /></th>
    <td align="right" ><bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedTotalNetValue" format="#,##0.00" /></td>
  </tr>
  <tr>
    <th align="left" colspan="3"><bean:message key="label.totalVatAmount" /></th>
    <td align="right" ><bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.totalVatValue" format="#,##0.00" /></td>
  </tr>
  <tr>
    <th align="left" colspan="3"><bean:message key="label.totalUppercase" /></th>
    <td align="right" ><bean:message key="label.currencySymbol"/><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedTotalValue" format="#,##0.00" /></td>
  </tr>
</table>

</logic:equal>

<logic:equal name="tab" value="expenses">

<logic:present name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateExpenses">
  <logic:empty name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateExpenses">
	<br/><bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateExpenses">
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.expense"/></th>
    <th align="left"><bean:message key="label.value"/></th>
    <th align="left"><bean:message key="label.vatRate"/></th>
    <th align="left"><bean:message key="label.vat"/></th>
    <th align="left"><bean:message key="label.receipt"/></th>
  </tr>
  <logic:iterate id="bookingDateExpense" name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateExpenses" type="com.helmet.bean.BookingDateExpenseUser">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left">
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
	  <td align="right">
      <bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="expenseVatRate" format="#0.00"/>
    </td>
	  <td align="right">
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
</table>
  </logic:notEmpty>
</logic:present>

</logic:equal>

<logic:equal name="tab" value="hourly">

<logic:present name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateHours">
  <logic:notEmpty name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateHours">
<table class="simple" width="100%">
  <tr>
<th align="right">hr</th>
<th align="right">prtOfHr</th>
<th align="right">chRt</th>
<th align="right">pyRt</th>
<th align="right">wgRt</th>
<th align="right">upFc</th>
<th align="right">upVl</th>
<th align="right">upChRt</th>
<th align="right">upPyRt</th>
<th align="right">upWgRt</th>
<th align="right">chRtVl</th>
<th align="right">chRtVVl</th>
<th align="right">pyRtVl</th>
<th align="right">pyRtVVl</th>
<th align="right">wgRtVl</th>
<th align="right">wtRt</th>
<th align="right">wtVl</th>
<th align="right">wtVVl</th>
<th align="right">niRt</th>
<th align="right">niVl</th>
<th align="right">niVVl</th>
<th align="right">coRt</th>
<th align="right">coVl</th>
<th align="right">coVVl</th>
  </tr>
    <logic:iterate id="bookingDateHour" name="BookingDateUserApplicantFormMgr" property="bookingDate.bookingDateHours" type="com.helmet.bean.BookingDateHour">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
<td align="right"><bean:write name="bookingDateHour" property="hourOfDay"/></td>
<td align="right"><bean:write name="bookingDateHour" property="portionOfHour"/></td>
<td align="right"><bean:write name="bookingDateHour" property="chargeRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="payRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="wageRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="upliftFactor"/></td>
<td align="right"><bean:write name="bookingDateHour" property="upliftValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="upliftedChargeRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="upliftedPayRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="upliftedWageRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="chargeRateValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="chargeRateVatValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="payRateValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="payRateVatValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="wageRateValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="wtdRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="wtdValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="wtdVatValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="niRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="niValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="niVatValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="commissionRate"/></td>
<td align="right"><bean:write name="bookingDateHour" property="commissionValue"/></td>
<td align="right"><bean:write name="bookingDateHour" property="commissionVatValue"/></td>
  </tr>
    </logic:iterate>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
<th align="left" colspan="10"><bean:message key="label.total"/></th>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedChargeRateValue"/></td>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedChargeRateVatValue"/></td>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedPayRateValue"/></td>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedPayRateVatValue"/></td>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedWageRateValue"/></td>
<th align="left">&nbsp;</th>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedWtdValue"/></td>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedWtdVatValue"/></td>
<th align="left">&nbsp;</th>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNiValue"/></td>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedNiVatValue"/></td>
<th align="left">&nbsp;</th>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedCommissionValue"/></td>
<td align="right"><bean:write name="BookingDateUserApplicantFormMgr" property="bookingDate.workedCommissionVatValue"/></td>
  </tr>    
</table>
  </logic:notEmpty>
</logic:present>

</logic:equal>

</logic:notEqual>
