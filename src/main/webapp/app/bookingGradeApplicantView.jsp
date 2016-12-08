<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mmj-app" prefix="mmj-app" %>

<bean:define id="tab" value="outstanding"/>
<bean:parameter id="showOnlyOutstanding" name="showOnlyOutstanding" value="true"/>
<%--bean:parameter id="showWeekly" name="showWeekly" value="false"/--%>
<bean:define id="show" name="BookingGradeApplicantViewFormApp" property="show" />
<logic:equal name="show" value="all">
  <bean:define id="tab" value="all"/>
</logic:equal>
<logic:equal name="show" value="weekly">
  <bean:define id="tab" value="weekly"/>
</logic:equal>
<bean:define id="clientName" name="client" property="name" type="java.lang.String"/>

<%
  String tempFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getTempFileFolder() + "/" + request.getSession().getId() + ".pdf";
  String tempFileUrl = request.getScheme() + "://" + request.getServerName() + tempFilePath;
%>
<script language='Javascript'>

var desktopWindow = null;

function showPDF()
{
  var now;
  now = new Date();
  url = "<%= tempFilePath %>?" + now.getTime();
  
  options = 'width=800,height=600,menubar=0,status=1,toolbar=0,resizable=1';

  desktopWindow = window.open(url, "pdf", options);
  
}

</script>

<bean:define id="showCheckbox" value="false"/>
<logic:equal name="BookingGradeApplicantViewFormApp" property="atLeastOneToSubmit" value="true">
  <bean:define id="showCheckbox" value="true"/>
</logic:equal>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/bookingDatesSubmit.do" onsubmit="return singleSubmit();">
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.bookingGradeApplicantView"/>
	</td>
  <td align="right" valign="middle" width="75">
<logic:equal name="show" value="weekly">
	<logic:empty name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" >
    &nbsp;
	</logic:empty>
	<logic:notEmpty name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" >
    <logic:equal name="BookingGradeApplicantViewFormApp" property="allowPrint" value="true">
      <html:button property="pdfButton" styleClass="titleButton" onclick="javascript:showPDF()" titleKey="link.title.pdf2Print"><bean:message key="button.pdf2Print"/></html:button>
    </logic:equal>
<%--
    <html:link href="<%= tempFilePath %>" target="pdf" titleKey="title.pdf">
      <bean:message key="link.pdf"/>
    </html:link>


<input type=button name=button1 value='Click Here' onClick='popup();'>



--%>
	</logic:notEmpty>
</logic:equal>
<logic:notEqual name="show" value="weekly">
    &nbsp;
</logic:notEqual>
    </td>
  <td align="right" valign="middle" width="75">
<logic:equal name="showCheckbox" value="true">
  <logic:equal name="BookingGradeApplicantViewFormApp" property="allowChange" value="true">
    <html:submit styleId="submitButton" styleClass="titleButton" titleKey="link.title.submit"><bean:message key="button.submit"/></html:submit>
  </logic:equal>
</logic:equal>
<logic:notEqual name="showCheckbox" value="true">
    &nbsp;
</logic:notEqual>
    </td>
  </tr>
</table>

<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="25%">
      <bean:message key="label.bookingNo"/>
    </th>
    <td align="left" width="75%">
      <bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingId" format="#000" />
    </td>

    <td rowspan="2" align="right" width="120">

<logic:equal name="show" value="weekly">
	<logic:empty name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" >
      <img src="<%= request.getContextPath() %>/images/trans.gif" width="112" height="33" align="middle"/>
	</logic:empty>
	<logic:notEmpty name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" >
	  <html:link href="http://www.adobe.com/products/acrobat/readstep2.html" target="_blank">
        <img src="<%= request.getContextPath() %>/images/getAdobeReader.gif" width="112" height="33" align="middle"/>
    </html:link>
	</logic:notEmpty>
</logic:equal>
<logic:notEqual name="show" value="weekly">
    <img src="<%= request.getContextPath() %>/images/trans.gif" width="112" height="33" align="middle"/>
</logic:notEqual>

    </td>

  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.location"/>
    </th>
    <td align="left" colspan="2">
      <%= clientName %>,
      <bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.locationName" />,
      <bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.siteName" />
<logic:notEmpty name="BookingGradeApplicantViewFormApp" property="bookingReference" >
      &nbsp;&nbsp;(<bean:message key="label.reference" />&nbsp;<bean:write name="BookingGradeApplicantViewFormApp" property="bookingReference" />)
</logic:notEmpty>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left" colspan="2">
      <bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.jobProfileName" />
      -
      <bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.gradeName" />
    </td>
  </tr>
<mmj-app:applicant var="applicant"/>
<bean:define id="hideMoney" name="applicant" property="hideMoney"/>
<logic:equal name="hideMoney" value="false">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.wageRate"/>
    </th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.wageRate" format="#,##0.00" />
    </td>
  </tr>
</logic:equal>
<logic:present name="BookingGradeApplicantViewFormApp" property="nhsBooking" >
   <logic:notEmpty name="BookingGradeApplicantViewFormApp" property="nhsBooking.comment" >
	 <tr>
	   <th align="left" class="label">
	     <bean:message key="label.comment"/>
	   </th>
	   <td align="left" colspan="2">
	     <bean:write name="BookingGradeApplicantViewFormApp" property="nhsBooking.comment" />
	   </td>
	 </tr>
   </logic:notEmpty>
	 <logic:equal name="BookingGradeApplicantViewFormApp" property="nhsBooking.active" value="false">
	 <tr>
	   <th align="left" class="label">
	     <bean:message key="label.bankRequest"/>&nbsp;<bean:write name="BookingGradeApplicantViewFormApp" property="nhsBooking.bankReqNum" />
	   </th>
	   <td align="left" colspan="2">
	     <bean:message key="label.closedWarning"/>
	   </td>
	 </tr>
   </logic:equal>
</logic:present>
<logic:notEmpty name="BookingGradeApplicantViewFormApp" property="nhsBookingError" >
	 <tr>
	   <th align="left" class="label">
	     NHS Booking Error
	   </th>
	   <td align="left" colspan="2">
	     <bean:write name="BookingGradeApplicantViewFormApp" property="nhsBookingError" />
	   </td>
	 </tr>
</logic:notEmpty>

<%--
      <bean:message key="label.payRate"/>
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.payRate" format="#,##0.00" />

      <bean:message key="label.wtd"/>&nbsp;@&nbsp;<bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.wtdPercentage" format="#,##0.00" />%
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.wtdRate" format="#,##0.00" />

      <bean:message key="label.ni"/>&nbsp;@&nbsp;<bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.niPercentage" format="#,##0.00" />%
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.niRate" format="#,##0.00" />
--%>

  <logic:notEmpty name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingExpensesText" >
  <tr>
    <th align="left" class="label">
      <bean:message key="label.expensesComment"/>
    </th>
    <td align="left" colspan="2">
      <pre><bean:write name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingExpensesText" /></pre>
    </td>
  </tr>
  </logic:notEmpty>
</table>
<br/>

<bean:define id="weekToShow" name="BookingGradeApplicantViewFormApp" property="weekToShow" />

<%/* tabs */%>
<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="outstanding"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
      <html:link forward="bookingGradeApplicantViewOutstanding" titleKey="title.showOutstanding"><bean:message key="link.outstanding"/></html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="weekly"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
			<c:set var="fourWeeksBack" value="${weekToShow - 4}" scope="page"/>
			<c:set var="twoWeeksBack" value="${weekToShow - 2}" scope="page"/>
			<c:set var="previousWeek" value="${weekToShow - 1}" scope="page"/>
      <c:set var="currentWeek" value="0" scope="page"/>
			<c:set var="nextWeek" value="${weekToShow + 1}" scope="page"/>
			<c:set var="twoWeeksForward" value="${weekToShow + 2}" scope="page"/>
			<c:set var="fourWeeksForward" value="${weekToShow + 4}" scope="page"/>
      <html:link forward="bookingGradeApplicantViewWeekly" paramId="weekToShow" paramName="fourWeeksBack" paramScope="page" titleKey="title.showFourWeeksBack">&lt;&lt;&lt;&lt;</html:link>
      &nbsp;
      <html:link forward="bookingGradeApplicantViewWeekly" paramId="weekToShow" paramName="twoWeeksBack" paramScope="page" titleKey="title.showTwoWeeksBack">&lt;&lt;</html:link>
      &nbsp;
      <html:link forward="bookingGradeApplicantViewWeekly" paramId="weekToShow" paramName="previousWeek" paramScope="page" titleKey="title.showPreviousWeek">&lt;</html:link>
      <html:link forward="bookingGradeApplicantViewWeekly" paramId="weekToShow" paramName="currentWeek" paramScope="page"  titleKey="title.showCurrentWeek"><bean:message key="link.weekly"/></html:link>
      <html:link forward="bookingGradeApplicantViewWeekly" paramId="weekToShow" paramName="nextWeek" paramScope="page" titleKey="title.showNextWeek">&gt;</html:link>
      &nbsp;
      <html:link forward="bookingGradeApplicantViewWeekly" paramId="weekToShow" paramName="twoWeeksForward" paramScope="page" titleKey="title.showTwoWeeksForward">&gt;&gt;</html:link>
      &nbsp;
      <html:link forward="bookingGradeApplicantViewWeekly" paramId="weekToShow" paramName="fourWeeksForward" paramScope="page" titleKey="title.showFourWeeksForward">&gt;&gt;&gt;&gt;</html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="all"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
      <html:link forward="bookingGradeApplicantViewAll" titleKey="title.showAll"><bean:message key="link.all"/></html:link>
    </td>
    <td align="center" class="tabInvisibleClass" width="25%">
      &nbsp;
    </td>
  </tr>
</table>
<bean:define id="expenseCount" name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingExpensesCount.value"/>
<bean:define id="allowChange" name="BookingGradeApplicantViewFormApp" property="allowChange"/>
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormApp" value="BookingGradeApplicantViewFormApp"/>
  <jsp:param name="theList" value="bookingGradeApplicant.bookingGradeApplicantDateUserEntities"/>
  <jsp:param name="theUpliftFactors" value="bookingGradeApplicant.upliftFactors"/>
  <jsp:param name="expenseCount" value="<%= expenseCount %>"/>
  <jsp:param name="showTotals" value="true"/>
  <jsp:param name="showCheckbox" value="<%= showCheckbox %>"/>
  <jsp:param name="show" value="<%= show %>"/>
  <jsp:param name="hideMoney" value="<%= hideMoney %>"/>
  <jsp:param name="allowChange" value="<%= allowChange %>"/>
</jsp:include>

</html:form>

<logic:equal name="show" value="weekly">
	<logic:notEmpty name="BookingGradeApplicantViewFormApp" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" >
    <logic:equal name="BookingGradeApplicantViewFormApp" property="allowPrint" value="true">
			<html:link href="javascript:showPDF()" titleKey="link.title.pdf2Print">
			  <bean:message key="link.pdf2Print"/>
			</html:link>
	  </logic:equal>
	</logic:notEmpty>
</logic:equal>

