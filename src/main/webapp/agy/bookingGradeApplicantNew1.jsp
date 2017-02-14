<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ 
taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ 
taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="bookingGradeId" name="BookingGradeApplicantFormAgy" property="bookingGrade.bookingGradeId" />
<%
String applicantIndexLetter = (String)session.getAttribute("applicantIndexLetter");
applicantIndexLetter = applicantIndexLetter == null ? "A" : applicantIndexLetter;
String indexLetter = request.getParameter("indexLetter") == null ? applicantIndexLetter : request.getParameter("indexLetter");
session.setAttribute("applicantIndexLetter", indexLetter);
%>
<logic:empty name="BookingGradeApplicantFormAgy" property="list">
<br/> 
<br/>
<bean:message key="error.noApplicants"/>
</logic:empty>
<logic:notEmpty name="BookingGradeApplicantFormAgy" property="list" >
<html:form action="/bookingGradeApplicantNew2.do" focus="app0" onsubmit="return singleSubmit();">
<html:hidden property="page" value="1"/>
<html:hidden name="BookingGradeApplicantFormAgy" property="bookingGrade.bookingGradeId" />
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingGradeApplicantNewStep1"/>&nbsp;-&nbsp;<bean:message key="title.bookingGradeApplicantNew1"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="bookingGradeApplicantNewButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<jsp:include page="bookingGradeApplicantNewTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= indexLetter %>"/>
  <jsp:param name="bookingGradeId" value="<%= bookingGradeId %>"/>
</jsp:include>
<html:errors />

<table class="simple" width="100%">
<logic:empty name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.bookingGradeApplicantId">
  <thead>
  <tr>
    <th align="left" valign="middle">&nbsp;</th>
    <th align="left"><bean:message key="label.name"/></th>
    <th align="left"><bean:message key="label.emailAddress"/></th>
    <th align="left"><bean:message key="label.mobile"/></th>
    <th align="left"><bean:message key="label.availability"/></th>
    <th align="left"><bean:message key="label.discipline"/></th>
    <th align="left"><bean:message key="label.areaOfSpeciality"/></th>
    <th align="left"><bean:message key="label.professionalReference"/></th>
    <th align="center"><bean:message key="label.photo"/></th>
    <th align="center"><bean:message key="label.crb"/></th>
  </tr>
  </thead>
<logic:iterate name="BookingGradeApplicantFormAgy" property="list" id="applicant" indexId="applicantIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <label for="<%= "app" + applicantIndex %>">
    <td align="left">
      <html:radio tabindex="1" property="applicant.applicantId" idName="applicant" value="applicantId" styleId='<%= "app" + applicantIndex %>' />
    </td>
    <td align="left">
      <bean:write name="applicant" property="user.fullName" />
    </td>
    <td align="left">
      <bean:write name="applicant" property="user.emailAddress" />
    </td>
    <td align="left">
      <bean:write name="applicant" property="mobileNumber"/>
    </td>
    <td align="left">
      <bean:write name="applicant" property="availabilityDate" formatKey="format.mediumDateFormat"/>
    </td>   
    <td align="left">
      <bean:write name="applicant" property="reference"/>
    </td>
    <td align="left">
      <bean:write name="applicant" property="areaOfSpecialityName"/>
    </td>   
    <td align="left">
      <bean:write name="applicant" property="professionalReference" />
    </td>
    <td align="center">
			<logic:notEmpty name="applicant" property="photoFilename">
		    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
		  </logic:notEmpty>
		  <logic:empty name="applicant" property="photoFilename">
		    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
		  </logic:empty>
    </td>
    <td align="center">
			<logic:equal name="applicant" property="hasCurrentDisclosure" value="true">
		    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
		  </logic:equal>
			<logic:notEqual name="applicant" property="hasCurrentDisclosure" value="true">
		    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
		  </logic:notEqual>
    </td>
    </label>
  </tr>
</logic:iterate>
</logic:empty>
<logic:notEmpty name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.bookingGradeApplicantId">
  <tr>
    <label for="app0">
    <td align="left">
      <html:radio tabindex="1" property="applicant.applicantId" idName="BookingGradeApplicantFormAgy" value="bookingGradeApplicant.applicantId" styleId="app0" />
    </td>
    <td align="left">
			<bean:write name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.applicantFullName"/>
    </td>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.applicantEmailAddress" />
    </td>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.applicantMobileNumber"/>
    </td>
    <td align="left">
      n/a<!-- availabilityDate -->
    </td>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.applicantReference"/>
    </td>
    <td align="left">
      n/a<!-- areaOfSpecialityName -->
    </td>
    <td align="left">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.applicantProfessionalReference"/>
    </td>
    <td align="center">
			<logic:notEmpty name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.applicantPhotoFilename">
		    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
		  </logic:notEmpty>
		  <logic:empty name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.applicantPhotoFilename">
		    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
		  </logic:empty>
    </td>
    <td align="center">
      n/a<!-- hasCurrentDisclosure -->
    </td>
    </label>
  </tr>
</logic:notEmpty>
</table>
</html:form>
</logic:notEmpty>
    