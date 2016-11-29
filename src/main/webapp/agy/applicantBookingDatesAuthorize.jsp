<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="applicant" name="ApplicantBookingDatesFormAgy" property="applicant" type="com.helmet.bean.Applicant"/>
<bean:define id="applicantId" name="applicant" property="applicantId"/>
<bean:define id="weekToShow" name="ApplicantBookingDatesFormAgy" property="weekToShow" />
<%
String theAction = "/applicantView.do?applicant.applicantId=" + applicantId;
%>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.applicantBookingDatesAuthorize"/>&nbsp;
      <mmj-agy:hasAccess forward="applicantView">
        <html:link page="<%= theAction %>" paramId="weekToShow" paramName="weekToShow" paramScope="page">
          <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
        </html:link>
      </mmj-agy:hasAccess>
      <mmj-agy:hasNoAccess forward="applicantView">
          <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
      </mmj-agy:hasNoAccess>
			&nbsp;<bean:write name="ApplicantBookingDatesFormAgy" property="startDate" formatKey="format.longDateFormat" />
			-
			<bean:write name="ApplicantBookingDatesFormAgy" property="endDate" formatKey="format.longDateFormat" />
		</td>
    <logic:notEmpty name="ApplicantBookingDatesFormAgy" property="list">
    <td align="right" valign="middle" width="75">
			<html:form action="/applicantBookingDatesAuthorizeProcess.do" onsubmit="return singleSubmit();">
			  <html:hidden name="ApplicantBookingDatesFormAgy" property="bookingDateIdStrs" />
			  <html:hidden name="ApplicantBookingDatesFormAgy" property="weekToShow" />
	      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
      </html:form>
    </td>
    </logic:notEmpty>
  </tr>
</table>
<html:errors/>
<logic:notEmpty name="ApplicantBookingDatesFormAgy" property="list">
<jsp:include page="shiftsIncludeApplicant.jsp" flush="true">
  <jsp:param name="theFormAgy" value="ApplicantBookingDatesFormAgy"/>
  <jsp:param name="theList" value="list"/>
</jsp:include>
</logic:notEmpty>
