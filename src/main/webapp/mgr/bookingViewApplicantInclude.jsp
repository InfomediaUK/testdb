<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<%-- start of applicant details --%>

<bean:parameter id="bdBookingDateId" name="bdBookingDateId" value="0"/>
<bean:parameter id="bookingDateCancelled" name="bookingDateCancelled" value="true"/>

<logic:notEmpty name="BookingViewFormMgr" property="booking.bookingGradeApplicantDateUsers" >
  <logic:iterate name="BookingViewFormMgr" property="booking.bookingGradeApplicantDateUsers" id="bookingGradeApplicantDate" >
  <%/* check it is for the correct bookingDate */%>
  <bean:define id="bgadBookingDateId" name="bookingGradeApplicantDate" property="bookingDateId" /> 
  <%/* bean:define id="bdBookingDateId" name="bookingDate" property="bookingDateId" type="java.lang.Integer" / */%> 
  <logic:equal name="bookingGradeApplicantDate" property="bookingDateId" value="<%= bdBookingDateId.toString() %>"> 
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td colspan="3">
			<mmj-mgr:hasAccess forward="bookingGradeApplicantView">
			<html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="bookingGradeApplicantDate" paramProperty="bookingGradeApplicantId">
	    <bean:write name="bookingGradeApplicantDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingGradeApplicantDate" property="applicantLastName"/>
			</html:link>
			</mmj-mgr:hasAccess>
			<mmj-mgr:hasNoAccess forward="bookingGradeApplicantView">
	    <bean:write name="bookingGradeApplicantDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingGradeApplicantDate" property="applicantLastName"/>
			</mmj-mgr:hasNoAccess>
	    (<bean:write name="bookingGradeApplicantDate" property="agencyName"/>)
	    <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="bookingGradeRate" format="#0.00"/>
    </td>   
	<logic:empty name="bookingGradeApplicantDate" property="startTime">
	  <td align="left" colspan="4">
	    &nbsp;
	  </td>
	  <td align="right">
      <logic:equal name="bookingDateCancelled" value="true">
      <s><bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="value" format="#0.00"/></s>
      </logic:equal>
      <logic:notEqual name="bookingDateCancelled" value="true">
      <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="value" format="#0.00"/>
      </logic:notEqual>
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
  </logic:empty>
	<logic:notEmpty name="bookingGradeApplicantDate" property="startTime">

  	<%/* start time has been entered by applicant */%>
  	<logic:equal name="bookingGradeApplicantDate" property="workedStatusIsDraft" value="true">
  	<%/* BUT is still DRAFT so cannot be shown to Manager */%>
	  <td align="left" colspan="4">
	    &nbsp;
	  </td>
	  <td align="right">
      <logic:equal name="bookingDateCancelled" value="true">
      <s><bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="value" format="#0.00"/></s>
      </logic:equal>
      <logic:notEqual name="bookingDateCancelled" value="true">
      <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="value" format="#0.00"/>
      </logic:notEqual>
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
	  <td align="left">
	    &nbsp;
	  </td>
  	</logic:equal>
  	<logic:notEqual name="bookingGradeApplicantDate" property="workedStatusIsDraft" value="true">
  	<%/* NOT DRAFT so can be shown to manager */%>
	  <td align="left">
	    <bean:write name="bookingGradeApplicantDate" property="startTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="endTime" format="HH:mm"/>
	  </td>
	  <td align="left">
	    <bean:write name="bookingGradeApplicantDate" property="breakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="breakEndTime" format="HH:mm"/>
	  </td>
       <td align="left">
         &nbsp;
       </td>
	  <td align="left">
	    &nbsp;
	  </td>
    <logic:equal name="BookingViewFormMgr" property="booking.draft" value="false">
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="value" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:message key="label.currencySymbol" /><bean:write name="bookingGradeApplicantDate" property="workedChargeRateValue" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:write name="bookingGradeApplicantDate" property="noOfHours" format="#0.00"/>
	  </td>
    </logic:equal>
    <td align="left">
  	<logic:equal name="bookingGradeApplicantDate" property="canBeAuthorized" value="true">
  	<%/* if it can be authorised it can be rejected - manager will probably have access to both authorise and reject */%>
    <mmj-mgr:hasAccess forward="bookingGradeApplicantDateAuthorize">
      <html:link forward="bookingGradeApplicantDateAuthorize" paramId="bookingGradeApplicantDate.bookingGradeApplicantDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingGradeApplicantDateId"><bean:message key="link.authorize"/></html:link>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantDateAuthorize">
	    <bean:message name="bookingGradeApplicantDate" property="workedStatusDescriptionKey"/>
    </mmj-mgr:hasNoAccess>
    <mmj-mgr:hasAccess forward="bookingGradeApplicantDateReject">
      <html:link forward="bookingGradeApplicantDateReject" paramId="bookingGradeApplicantDate.bookingGradeApplicantDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingGradeApplicantDateId"><bean:message key="link.reject"/></html:link>
    </mmj-mgr:hasAccess>
    </logic:equal>
  	<logic:notEqual name="bookingGradeApplicantDate" property="canBeAuthorized" value="true">
	    <bean:message name="bookingGradeApplicantDate" property="workedStatusDescriptionKey"/>
    </logic:notEqual>
	  </td>
    </logic:notEqual>
	</logic:notEmpty>
	</tr>
	<logic:equal name="bookingGradeApplicantDate" property="workedStatusIsRejected" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left" colspan="3">
	    &nbsp;
	  </td>
	  <td align="left" colspan="8">
      <bean:write name="bookingGradeApplicantDate" property="rejectText"/>
    </td>
  </tr>
  </logic:equal>
	
	
	</logic:equal>
	</logic:iterate>
</logic:notEmpty>

<%-- end of applicant details --%>


   