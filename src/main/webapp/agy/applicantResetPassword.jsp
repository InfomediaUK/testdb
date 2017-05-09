<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.applicantResetPassword"/>
		</td>
<logic:equal name="ApplicantResetPasswordFormAgy" property="applicant.active" value="true">
<mmj-agy:hasAccess forward="applicantResetPassword">
		<html:form action="applicantResetPasswordProcess.do" onsubmit="return singleSubmit();">
		<html:hidden property="applicant.applicantId" />
		<html:hidden property="applicant.user.login" />
		<html:hidden property="applicant.noOfChanges" />
  <logic:equal name="ApplicantResetPasswordFormAgy" property="applicant.user.emailAddress" value="">
		<html:hidden property="emailActionId" value="13"/>
  </logic:equal>
  <logic:notEqual name="ApplicantResetPasswordFormAgy" property="applicant.user.emailAddress" value="">
		<html:hidden property="emailActionId" value="12"/>
  </logic:notEqual>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</html:form>
</mmj-agy:hasAccess>
</logic:equal>
  </tr>
</table>
<html:errors/>
<table cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td align="left" valign="top" width="75%">
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.firstName"/></th>
    <td align="left" width="65%"><bean:write name="ApplicantResetPasswordFormAgy" property="applicant.user.firstName"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.lastName"/></th>
    <td align="left"><bean:write name="ApplicantResetPasswordFormAgy" property="applicant.user.lastName"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
    <td align="left"><bean:write name="ApplicantResetPasswordFormAgy" property="applicant.user.emailAddress"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.mobileNumber"/></th>
    <td align="left"><bean:write name="ApplicantResetPasswordFormAgy" property="applicant.mobileNumber"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.dateOfBirth"/></th>
    <td align="left"><bean:write name="ApplicantResetPasswordFormAgy" property="applicant.dateOfBirth" formatKey="format.mediumDateFormat" /></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.niNumber"/></th>
    <td align="left"><bean:write name="ApplicantResetPasswordFormAgy" property="applicant.niNumber"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.gender"/></th>
    <td align="left">
		  <logic:equal name="ApplicantResetPasswordFormAgy" property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlMale %>">
		    <bean:message key="label.male"/>
		  </logic:equal>
		  <logic:equal name="ApplicantResetPasswordFormAgy" property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlFemale %>">
		    <bean:message key="label.female"/>
		  </logic:equal>
    </td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.agencyPaperwork"/></th>
    <td align="left"><bean:write name="ApplicantResetPasswordFormAgy" property="applicant.reference"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.registrationNumber"/></th>
    <td align="left"><bean:write name="ApplicantResetPasswordFormAgy" property="applicant.registrationNumber"/></td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.limitedCompany"/></th>
    <td align="left">
    	<logic:empty name="ApplicantResetPasswordFormAgy" property="applicant.limitedCompanyName">
    	  &nbsp;
    	</logic:empty>
    	<logic:notEmpty name="ApplicantResetPasswordFormAgy" property="applicant.limitedCompanyName">
	    	<bean:write name="ApplicantResetPasswordFormAgy" property="applicant.limitedCompanyName"/>
	    </logic:notEmpty>
    </td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.hideMoney"/></th>
    <td align="left">
      <logic:equal name="ApplicantResetPasswordFormAgy" property="applicant.hideMoney" value="true">
				<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ApplicantResetPasswordFormAgy" property="applicant.hideMoney" value="true">
				<bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left" class="label"><bean:message key="label.canToggleHideMoney"/></th>
    <td align="left">
      <logic:equal name="ApplicantResetPasswordFormAgy" property="applicant.canToggleHideMoney" value="true">
				<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ApplicantResetPasswordFormAgy" property="applicant.canToggleHideMoney" value="true">
				<bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
</table>
    </td>
    <td align="center" valign="top" width="25%">
<logic:empty name="ApplicantResetPasswordFormAgy" property="applicant.photoFilename" >
<bean:message key="text.noPhotoAvailable"/>
</logic:empty>
<logic:notEmpty name="ApplicantResetPasswordFormAgy" property="applicant.photoFilename" >
<bean:define id="photoFileUrl" name="ApplicantResetPasswordFormAgy" property="applicant.photoFileUrl" type="java.lang.String" />
<html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
</logic:notEmpty>
    </td>
  </tr>
</table>

