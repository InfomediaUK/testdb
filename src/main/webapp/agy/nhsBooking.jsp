<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="nhsBookingUser" name="NhsBookingFormAgy"  property="nhsBookingUser" type="com.helmet.bean.NhsBookingUser"/>

<html:form action="/nhsBookingEditProcess.do" focus="nhsBookingUser.comment" onsubmit="return singleSubmit();">
  <html:hidden property="weekToShow" />
  <html:hidden name="NhsBookingFormAgy" property="nhsBookingUser.nhsBookingId" />
  <html:hidden name="NhsBookingFormAgy" property="nhsBookingUser.noOfChanges" />
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
			<td align="left" valign="middle" class="title"><bean:message key="title.nhsBookingEdit"/></td>
	    <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
	    <td align="right" valign="middle" width="80"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
	  </tr>
	</table>
	<html:errors/>
	<table class="simple" width="100%">
	  <tr>
	    <th align="left"><bean:message key="label.client"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.clientName"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.site"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.siteName"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.location"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.locationName"/></td>
	  </tr>
	  <tr>
	    <th align="left">Job Profile - Assignment</th>
	    <td align="left"><bean:write name="NhsBookingFormAgy" property="nhsBookingUser.jobProfileName"/>&nbsp;(<bean:write name="NhsBookingFormAgy" property="nhsBookingUser.jobFamilyCode"/>.<bean:write name="NhsBookingFormAgy" property="nhsBookingUser.jobSubFamilyCode"/>)&nbsp;-&nbsp;<bean:write name="NhsBookingFormAgy" property="nhsBookingUser.assignment"/></td>
	  </tr>
	  <tr>
	    <th align="left">Booking</th>
	    <td align="left">
<logic:greaterThan name="NhsBookingFormAgy" property="nhsBookingUser.bookingId" value="0">
	      <bean:write name="NhsBookingFormAgy" property="nhsBookingUser.bookingId" format="#000"/>
	      (<bean:write name="NhsBookingFormAgy" property="bookingStatus"/>)
</logic:greaterThan>
<logic:equal name="NhsBookingFormAgy" property="nhsBookingUser.bookingId" value="0">
        &nbsp;
</logic:equal>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Booking Grade</th>
	    <td align="left">
<logic:greaterThan name="NhsBookingFormAgy" property="nhsBookingUser.bookingGradeId" value="0">
	      <bean:write name="NhsBookingFormAgy" property="nhsBookingUser.bookingGradeId" format="#000"/>
	      (<bean:write name="NhsBookingFormAgy" property="bookingGradeStatus"/>)
</logic:greaterThan>	     
<logic:equal name="NhsBookingFormAgy" property="nhsBookingUser.bookingGradeId" value="0">
        &nbsp;
</logic:equal>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Bank Request</th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.bankReqNum"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.applicant"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.applicantFullName"/> (<bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.applicantEmailAddress"/>)</td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.date"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.date" format="EEE dd-MMM-yyyy"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.time"/></th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.startTime" format="HH:mm"/> - <bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.endTime" format="HH:mm"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.applicantNotificationSent"/></th>
	    <td align="left"><bean:write name="NhsBookingFormAgy" property="nhsBookingUser.applicantNotificationSent" format="EEE dd-MMM-yyyy HH:mm"/></td>
	  </tr>
<logic:greaterThan name="NhsBookingFormAgy" property="nhsBookingUser.bookingDateId" value="0">
	  <tr>
	    <th align="left">Worked Time</th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.workedStartTime" format="HH:mm"/> - <bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.workedEndTime" format="HH:mm"/></td>
	  </tr>
	  <tr>
	    <th align="left">Worked Hours</th>
	    <td align="left"><bean:write  name="NhsBookingFormAgy" property="nhsBookingUser.workedNoOfHours" format="##0.00"/></td>
	  </tr>
</logic:greaterThan>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.comment"/></th>
	    <td align="left"><html:textarea name="NhsBookingFormAgy" property="nhsBookingUser.comment" cols="50" rows="4"/></td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.value"/></th>
	    <td align="left"><html:text name="NhsBookingFormAgy" property="nhsBookingUser.value" /></td>
	  </tr>
	</table>
<logic:present name="NhsBookingFormAgy" property="listSubcontractInvoiceItemHistory">
	<table class="simple" width="100%">
	  <tr>
		  <th align="left">
		    Document
		  </th>
		  <th align="left">
		    Related
		  </th>
		  <th align="right">
		    Invoice Value
		  </th>
		  <th align="left">
		    Sent
		  </th>
		  <th align="left">
		    Paid
		  </th>
		  <th align="left">
		    Date
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
		  <th align="right">
		    Rate
		  </th>
		  <th align="right">
		    Value
		  </th>
		  <th align="right">
		    Active
		  </th>
      </tr>
  <logic:iterate id="subcontractInvoiceItemHistory" name="NhsBookingFormAgy" property="listSubcontractInvoiceItemHistory" type="com.helmet.bean.SubcontractInvoiceItemHistory">
	  <tr>
	    <td>
          <bean:write name="subcontractInvoiceItemHistory" property="subcontractInvoiceNumber"/>
	    </td>
	    <td>
          <bean:write name="subcontractInvoiceItemHistory" property="relatedSubcontractInvoiceNumber"/> 
	    </td>
	    <td align="right">
          <bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItemHistory" property="invoiceValue" format="#,##0.00"/> 
	    </td>
	    <td>
          <bean:write name="subcontractInvoiceItemHistory" property="sentDate" format="EEE dd-MMM-yyyy"/> 
	    </td>
	    <td>
          <bean:write name="subcontractInvoiceItemHistory" property="paidDate" format="EEE dd-MMM-yyyy"/> 
	    </td>
	    <td>
          <bean:write name="subcontractInvoiceItemHistory" property="date" format="EEE dd-MMM-yyyy"/> 
	    </td>
	    <td>
          <bean:write name="subcontractInvoiceItemHistory" property="startTime" format="HH:mm"/> 
	    </td>
	    <td>
          <bean:write name="subcontractInvoiceItemHistory" property="endTime" format="HH:mm"/> 
	    </td>
	    <td align="right">
          <bean:write name="subcontractInvoiceItemHistory" property="noOfHours" format="#,##0.00"/> 
	    </td>
	    <td align="right">
          <bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItemHistory" property="rate" format="#,##0.00"/> 
	    </td>
	    <td align="right">
          <bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItemHistory" property="value" format="#,##0.00"/> 
	    </td>
	    <td align="right">
          <bean:write name="subcontractInvoiceItemHistory" property="active"/> 
	    </td>
	  </tr>
  </logic:iterate>
	</table>
</logic:present>	
</html:form>