<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<mmj-agy:consultant var="consultant"/>

<script type="text/javascript">
var checked=false;
var frmname='';
function checkedAll(frmname)
{
  var searchResultsForm = document.getElementById(frmname);
  if (checked == false)
  {
    checked = true;
  }
  else
  {
    checked = false;
  }
  for (var i =0; i < searchResultsForm.elements.length; i++)
  {
    if (searchResultsForm.elements[i].type && searchResultsForm.elements[i].type === 'checkbox') {
      searchResultsForm.elements[i].checked=checked;
    }
  }
}
</script>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBookingsAdditional"/>
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
  <html:form action="/nhsBookingsBookTaskProcess.do" styleId="NhsBookings" onsubmit="return singleSubmit();">
  <html:hidden property="clientUser.clientId"/>
  <html:hidden property="siteUser.siteId"/>
  <html:hidden property="locationUser.locationId"/>
  <html:hidden property="jobProfileUser.jobProfileId"/>
	<mmj-agy:hasAccess forward="nhsBookingsBookTask">
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
	    <td align="right" valign="middle" width="100">
	      <html:submit styleClass="titleButton"><bean:message key="button.book"/></html:submit>
	    </td>
	  </tr>
	</table>
	</mmj-agy:hasAccess>
	<table class="simple" width="100%">
	  <tr>
	    <th align="left" width="25%"><bean:message key="label.client"/></th>
	    <td align="left" width="75%">
	      <bean:write name="NhsBookingsBookFormAgy" property="clientUser.name"/></td>
	  </tr>
	  <tr>
	    <th align="left" width="25%"><bean:message key="label.site"/></th>
	    <td align="left" width="75%">
	      <bean:write name="NhsBookingsBookFormAgy" property="siteUser.name"/></td>
	  </tr>
	  <tr>
	    <th align="left" width="25%"><bean:message key="label.location"/></th>
	    <td align="left" width="75%"><bean:write name="NhsBookingsBookFormAgy" property="locationUser.name"/></td>
	  </tr>
	  <tr>
	    <th align="left" width="25%"><bean:message key="label.jobProfile"/></th>
	    <td align="left" width="75%">
        <bean:write name="NhsBookingsBookFormAgy" property="jobProfileUser.jobFamilyCode"/>.
        <bean:write name="NhsBookingsBookFormAgy" property="jobProfileUser.jobSubFamilyCode"/>.
	      <bean:write name="NhsBookingsBookFormAgy" property="jobProfileUser.name"/>
	      (<bean:write name="NhsBookingsBookFormAgy" property="jobProfileUser.nhsAssignment"/>)
	    </td>
	  </tr>
	  <tr>
	    <th align="left" width="25%">Booking Group</th>
	    <td align="left" width="75%">
	      <bean:write name="NhsBookingsBookFormAgy" property="bookingGroupName"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left" width="25%"><bean:message key="label.rrpRate" /></th>
	    <td align="left" width="75%">
		    <mmj-agy:hasAccess forward="canChangeRates">
		      <html:text property="hourlyRate"/>
		    </mmj-agy:hasAccess>
		    <mmj-agy:hasNoAccess forward="canChangeRates">
		      <bean:message key="label.currencySymbol"/><bean:write name="NhsBookingsBookFormAgy" property="hourlyRate" format="#0.00"/>
		      <html:hidden property="hourlyRate"/>
		    </mmj-agy:hasNoAccess>
	    </td>
	  </tr>
	  <tr>
	    <th align="left" width="25%"><bean:message key="label.wageRate" /></th>
	    <td align="left" width="75%">
		    <mmj-agy:hasAccess forward="canChangeRates">
		      <html:text property="wageRate"/>
		    </mmj-agy:hasAccess>
		    <mmj-agy:hasNoAccess forward="canChangeRates">
		      <bean:message key="label.currencySymbol"/><bean:write name="NhsBookingsBookFormAgy" property="wageRate" format="#0.00"/>
		      <html:hidden property="wageRate"/>
		    </mmj-agy:hasNoAccess>
	    </td>
	  </tr>
	  <tr>
	    <th align="left" width="25%"><bean:message key="label.value" /></th>
	    <td align="left" width="75%">
		    <html:text property="value"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" width="25%" valign="top">
         <table class="simple" width="100%" border="0">
           <thead>
           <tr>
             <th align="left" width="2%">&nbsp;</th>
             <th align="left" width="30%">Grade</th>
             <th align="left" width="30%">Rate</th>
             <th align="left" width="30%">Pay Rate</th>
           </tr>
           </thead>
       <logic:iterate id="clientAgencyJobProfileGradeUser" name="NhsBookingsBookFormAgy" property="listClientAgencyJobProfileGradeUser" indexId="clientAgencyJobProfileGradeUserIndex" type="com.helmet.bean.ClientAgencyJobProfileGradeUser">
           <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
             <td align="left" valign="top"><%-- Grade Name --%>
               <input type="radio" name="clientAgencyJobProfileGradeId" value="<%= clientAgencyJobProfileGradeUser.getClientAgencyJobProfileGradeId() %>" id="<%= "clientAgencyJobProfileGradeUser" + clientAgencyJobProfileGradeUserIndex %>">
             </td>
             <td align="left" valign="top"><%-- Grade Name --%>
               <bean:write name="clientAgencyJobProfileGradeUser" property="gradeName"/>
             </td>
             <td align="left" valign="top"><%-- Rate --%>
               &nbsp;<bean:message key="label.currencySymbol"/><bean:write name="clientAgencyJobProfileGradeUser" property="rate" format="#,##0.00" />
             </td>
             <td align="left" valign="top"><%-- Rate --%>
               &nbsp;<bean:message key="label.currencySymbol"/><bean:write name="clientAgencyJobProfileGradeUser" property="payRate" format="#,##0.00" />
             </td>
           </tr>
       </logic:iterate>
         </table>
	    </td>
	    <td align="left" width="75%">
	      <table>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.contactName" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="contactName" size="30" maxlength="50" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.contactJobTitle" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="contactJobTitle" size="30" maxlength="50" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.contactEmailAddress" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="contactEmailAddress" size="30" maxlength="320" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.contactTelephoneNumber" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="contactTelephoneNumber" size="30" maxlength="20" tabindex="1"/>
				    </td>
				  </tr>
	      </table>
	    </td>
	  </tr>
	</table>
  <table class="simple" width="100%" border="1">
    <thead>
    <tr>
      <th align="center" width="2%">
        <input type="checkbox" name="checkall" onclick="checkedAll('NhsBookings');"/>
      </th>
      <th align="left" width="20%">Date</th>
      <th align="left" width="10%">Start</th>
      <th align="left" width="10%">End</th>
      <th align="left" width="40%">Applicant</th>
      <th align="left" width="40%">Bank Request Number</th>
    </tr>
    </thead>
<logic:iterate id="nhsBooking" name="NhsBookingsBookFormAgy" property="list" indexId="nhsBookingIndex" type="com.helmet.bean.NhsBookingUser">
    <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	    <td align="center" valign="top"><%-- Checkbox --%>
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultants that can view wages can book any Applicant --%>
	      <input type="checkbox" name="nhsBookingId" value="<%= nhsBooking.getNhsBookingId() %>">
	</logic:equal>    
  <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultants that can NOT view wages... --%>
    <logic:equal name="nhsBooking" property="applicantOriginalAgencyId" value="0"><%-- Applicant is NOT subcontracted. OK to Book... --%>
	      <input type="checkbox" name="nhsBookingId" value="<%= nhsBooking.getNhsBookingId() %>">
    </logic:equal>
    <logic:greaterThan name="nhsBooking" property="applicantOriginalAgencyId" value="0"><%-- Applicant IS subcontracted. Can NOT Book... --%>
	      &nbsp;
    </logic:greaterThan>
  </logic:equal>
	    </td>
      <td align="left" valign="top"><%-- Date --%>
        <bean:write name="nhsBooking" property="date" format="EEE dd-MMM-yyyy"/>
      </td>
      <td align="left" valign="top"><%-- Start Time --%>
        <bean:write name="nhsBooking" property="startTime" format="HH:mm"/>
      </td>
      <td align="left" valign="top"><%-- End Time --%>
        <bean:write name="nhsBooking" property="endTime" format="HH:mm"/>
      </td>
      <td align="left" valign="top"><%-- Applicant Full Name --%>
        <bean:write name="nhsBooking" property="applicantFullName"/>
  <logic:greaterThan name="nhsBooking" property="applicantOriginalAgencyId" value="0"><%-- Applicant IS subcontracted. Flag it up... --%>
        *
  </logic:greaterThan>
        &nbsp;(<bean:write name="nhsBooking" property="applicantEmailAddress"/>)
      </td>
      <td align="left" valign="top"><%-- Bank Request Number --%>
        <bean:write name="nhsBooking" property="bankReqNum"/>
      </td>
    </tr>
</logic:iterate>
  </table>
	</html:form>
  </logic:notEmpty>
</logic:present>