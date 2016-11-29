<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>
<mmj-agy:consultant var="consultant"/>
<logic:empty name="BookingGradeApplicantFormAgy" property="list">
<br/>
<br/>
<bean:message key="error.noApplicant"/>
</logic:empty>
<logic:notEmpty name="BookingGradeApplicantFormAgy" property="list" >
<html:form action="/bookingGradeApplicantNewFinish.do" onsubmit="return singleSubmit();">
<html:hidden property="page" value="5"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingGradeApplicantNewStep5"/>&nbsp;-&nbsp;<bean:message key="title.bookingGradeApplicantNew5"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="bookingGradeApplicantNewButtons.jsp" flush="true" >
		    <jsp:param name="nextButtonKey" value="button.finish" />
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />

<table cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td align="left" valign="top" width="75%">
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%">
      <bean:message key="label.bookingNo"/>
    </th>
    <td align="left" width="65%">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.bookingId" format="#000"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.client"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.clientName"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.locationName"/>,
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.siteName"/>
      (<bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.locationDescription"/>)
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.jobProfileName"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.name"/>
    </th>
    <td align="left">
			<bean:write name="BookingGradeApplicantFormAgy" property="applicant.user.firstName" />&nbsp;<bean:write name="BookingGradeApplicantFormAgy" property="applicant.user.lastName" />
	  </td>
	</tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
    <td align="left"><bean:write name="BookingGradeApplicantFormAgy" property="applicant.user.emailAddress"/></td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.dateOfBirth"/>
    </th>
    <td align="left">
			<bean:write name="BookingGradeApplicantFormAgy" property="applicant.dateOfBirth" formatKey="format.mediumDateFormat" />
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.niNumber"/>
    </th>
    <td align="left">
			<bean:write name="BookingGradeApplicantFormAgy" property="applicant.niNumber" />
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.gender"/>
    </th>
    <td align="left">
		  <logic:equal name="BookingGradeApplicantFormAgy" property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlMale %>">
		    <bean:message key="label.male"/>
		  </logic:equal>
		  <logic:equal name="BookingGradeApplicantFormAgy" property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlFemale %>">
		    <bean:message key="label.female"/>
		  </logic:equal>
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.discipline"/>
    </th>
    <td align="left">
			<bean:write name="BookingGradeApplicantFormAgy" property="applicant.reference" />
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.professionalReference"/>
    </th>
    <td align="left">
			<bean:write name="BookingGradeApplicantFormAgy" property="applicant.professionalReference" />
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.totalHours" />
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.bookingNoOfHours" format="#0.00" />
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.grade"/>
    </th>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="gradeName" />
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.chargeRate"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="rate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantFormAgy" property="chargeRateVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.payRate"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="payRate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantFormAgy" property="payRateVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.wtd"/>&nbsp;@&nbsp;<bean:write name="BookingGradeApplicantFormAgy" property="wtdPercentage" format="#,##0.00" />%
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="wtdRate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantFormAgy" property="wtdVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.ni"/>&nbsp;@&nbsp;<bean:write name="BookingGradeApplicantFormAgy" property="niPercentage" format="#,##0.00" />%
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="niRate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantFormAgy" property="niVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.commission"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="commissionRate" format="#,##0.00" />
      (<bean:write name="BookingGradeApplicantFormAgy" property="commissionVatRate" format="#,##0.00" />%)
	  </td>
	</tr>
  <tr>
    <th align="left">
      <bean:message key="label.wageRate"/>
    </th>
    <td align="left">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="wageRate" format="#,##0.00" />
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
	  </td>
	</tr>
</table>    
    </td>
    <td align="center" valign="top" width="25%">
<logic:empty name="BookingGradeApplicantFormAgy" property="applicant.photoFilename" >
<bean:message key="text.noPhotoAvailable"/>
</logic:empty>
<logic:notEmpty name="BookingGradeApplicantFormAgy" property="applicant.photoFilename" >
<bean:define id="photoFileUrl" name="BookingGradeApplicantFormAgy" property="applicant.photoFileUrl" type="java.lang.String" />
<html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
</logic:notEmpty>
    </td>
  </tr>
</table>
<%-- miss out the first 'dummy' one 
<logic:iterate id="bookingDate" name="BookingGradeApplicantFormAgy" property="selectedBookingDates" offset="1">
  <bean:write name="bookingDate" />
</logic:iterate>
--%>
<br/>
<table class="simple" width="100%">
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
    <th align="left">
			<bean:message key="label.hrs"/>
    </th>
    <th align="left">
			<bean:message key="label.charge"/>
    </th>
    <th align="left">
			<bean:message key="label.wage"/>
    </th>
  </tr>
<logic:iterate id="bookingDate" name="BookingGradeApplicantFormAgy" property="list" type="com.helmet.bean.BookingDateUser">
  <bean:define id="showDate" value="false"/>
  <logic:iterate id="selectedBookingDate" name="BookingGradeApplicantFormAgy" property="selectedBookingDates" type="java.lang.String">
    <logic:equal name="bookingDate" property="bookingDateId" value="<%= selectedBookingDate %>">
      <bean:define id="showDate" value="true"/>
    </logic:equal>
  </logic:iterate>
  <logic:equal name="showDate" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left">
	    <bean:write name="bookingDate" property="bookingId" format="000" />.<bean:write name="bookingDate" property="bookingDateId" format="000" />
	  </td>
	  <td align="left">
	    <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormatyy" />
	  </td>
	  <td align="left">
  	  <bean:write name="bookingDate" property="shiftName"/>
	  </td>
	  <td align="left">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        Undefined
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
    	  <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
      </logic:notEqual>
	  </td>
	  <td align="left">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
    	  <bean:write name="bookingDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftBreakEndTime" format="HH:mm"/>
    	  (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
      </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
     	  <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
      </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
      	<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRateValue" format="#,##0.00"/>
      </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
    	    <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/>
    	  </logic:equal>
	      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
	        &nbsp;
	      </logic:equal>
    	</logic:notEqual>
	  </td>
  </tr>
  </logic:equal>
</logic:iterate>
<logic:equal name="BookingGradeApplicantFormAgy" property="bookingGrade.undefinedShift" value="true">
  <tr>
    <th align="left" colspan="5">
      <bean:message key="label.total"/>
    </th>
    <td align="right">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.bookingNoOfHours" format="#0.00" />
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.value" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.wageRateValue" format="#,##0.00" />
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
    </td>
  </tr>
</logic:equal>
</table>
<br/>
<br/>
<logic:notEmpty name="BookingGradeApplicantFormAgy" property="filename" >
<%--
<bean:write name="BookingGradeApplicantFormAgy" property="contentType" />
<bean:write name="BookingGradeApplicantFormAgy" property="filename" />
<bean:write name="BookingGradeApplicantFormAgy" property="fileSize" />
<br/>
<br/>
<bean:write name="BookingGradeApplicantFormAgy" property="tempFileName" />
<br/>
<bean:write name="BookingGradeApplicantFormAgy" property="tempFileUrl" />
<br/>
<bean:write name="BookingGradeApplicantFormAgy" property="tempFilePath" />
<br/>
<br/>
--%>
<bean:define id="tempFileUrl" name="BookingGradeApplicantFormAgy" property="tempFileUrl" type="java.lang.String" />
<html:link href="<%=tempFileUrl %>" target="_blank"><bean:message key="link.uploadedFile"/></html:link>
</logic:notEmpty>

</html:form>
</logic:notEmpty>
