<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>

<bean:parameter id="tab" name="tab" value="applicants"/>

<bean:parameter id="showApplicants" name="showApplicants" value="false"/>
<mmj-agy:consultant var="consultant"/>

<%-- title and buttons --%>
<jsp:include page="bookingGradeViewHeader.jsp" flush="true">
  <jsp:param name="isView" value="<%= isView %>"/>
  <jsp:param name="isCancel" value="<%= isCancel %>"/>
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<%-- tabs --%>
<jsp:include page="bookingGradeViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<logic:notPresent name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers">
  <br/>
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers">
<logic:empty name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers">
  <br/>
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers" >
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.name"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.emailAddress"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.dob"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.ni"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.gender"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.agencyPaperwork"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.professionalReference"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.grade"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.charge"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.wage"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.status"/>
    </th>
  </tr>
  <logic:iterate name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeApplicantUsers" id="bookingGradeApplicant" type="com.helmet.bean.BookingGradeApplicantUser">
  <tr>
    <td align="left">
			<mmj-agy:hasAccess forward="bookingGradeApplicantView">
			<html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="bookingGradeApplicant" paramProperty="bookingGradeApplicantId"><bean:write name="bookingGradeApplicant" property="applicantFullNameLastFirst" /></html:link>
			</mmj-agy:hasAccess>
			<mmj-agy:hasNoAccess forward="bookingGradeApplicantView">
      <bean:write name="bookingGradeApplicant" property="applicantFullNameLastFirst" />
			</mmj-agy:hasNoAccess>
      <logic:greaterThan name="bookingGradeApplicant" property="applicantOriginalAgencyId" value="0"><%-- Subcontracted Applicant. Show wage... --%>
        *
      </logic:greaterThan>
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantEmailAddress" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantDateOfBirth" formatKey="format.mediumDateFormat" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantNiNumber" />
    </td>
    <td align="left">
      <bean:message name="bookingGradeApplicant" property="applicantGenderDescriptionKey" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantReference" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantProfessionalReference" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="gradeName" />
    </td>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicant" property="rate" format="#,##0.00" />
    </td>
<%--
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicant" property="payRate" format="#,##0.00" />
--%>
    <td align="left">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicant" property="wageRate" format="#,##0.00" />
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. But check Applicant... --%>
        <logic:equal name="bookingGradeApplicant" property="applicantOriginalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
          <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicant" property="wageRate" format="#,##0.00" />
        </logic:equal>
        <logic:greaterThan name="bookingGradeApplicant" property="applicantOriginalAgencyId" value="0"><%-- Applicant subcontracted. Don't show wage... --%>
          &nbsp;
        </logic:greaterThan>
      </logic:equal>
    </td>
    <td align="left" <logic:notEmpty name="bookingGradeApplicant" property="rejectText">title="<bean:write name="bookingGradeApplicant" property="rejectText" />"</logic:notEmpty>>
      <bean:message name="bookingGradeApplicant" property="statusDescriptionKey" />
    </td>
  </tr>
  </logic:iterate> 
</table>
<logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.hasSubcontractedApplicants" value="true"><%-- Booking Grade HAS subcontracted Applicants... --%>
  * Applicant is subcontracted
</logic:equal>   
</logic:notEmpty>
</logic:present>

