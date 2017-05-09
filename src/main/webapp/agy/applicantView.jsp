<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.helmet.application.FileHandler" %>
<%@ page import="com.helmet.bean.Applicant" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="applicantId" name="ApplicantFormAgy" property="applicant.applicantId"/>
<bean:define id="weekToShow" name="ApplicantFormAgy" property="weekToShow"/>
<bean:define id="registrationAlertNotification" name="ApplicantFormAgy" property="applicant.registrationAlertNotification"/>
<bean:define id="fitToWorkIssuedBy" name="ApplicantFormAgy" property="applicant.fitToWorkIssuedBy"/>
<bean:define id="checklistFileUrl" name="ApplicantFormAgy" property="applicant.checklistFileUrl"/>
<bean:define id="dates" name="ApplicantFormAgy" property="unavailableDates" type="java.lang.String" />
<%
String fileUrl = null;
String deleteFileUrl = request.getContextPath() + "/agy/applicantDeleteFile.do?applicantId=" + applicantId;
String createAgencyWorkerChecklistFileUrl = request.getContextPath() + "/agy/applicantChecklist.do?applicant.applicantId=" + applicantId + "&weekToShow=" + weekToShow;
String deleteAgencyWorkerChecklistFileUrl = request.getContextPath() + "/agy/applicantDeleteAgencyWorkerChecklistFile.do?applicantId=" + applicantId;
String registrationAlertNotificationMessageKey = "label.registrationAlertNotification" + registrationAlertNotification;
String fitToWorkIssuedByMessageKey = "label.fitToWorkIssuedBy" + fitToWorkIssuedBy;
%>
<mmj-agy:consultant var="consultant"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30" border="0">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.applicantView"/>&nbsp;
			<mmj-agy:hasAccess forward="applicantResetSecretWord">
			<html:link forward="applicantResetSecretWord" paramId="applicant.applicantId" paramName="ApplicantFormAgy" paramProperty="applicant.applicantId"><bean:message key="link.resetSecretWord"/></html:link>
			</mmj-agy:hasAccess>
			&nbsp;
      <mmj-agy:hasAccess forward="applicantResetPassword">
      <html:link forward="applicantResetPassword" paramId="applicant.applicantId" paramName="ApplicantFormAgy" paramProperty="applicant.applicantId"><bean:message key="link.resetPassword"/></html:link>
      </mmj-agy:hasAccess>
		</td>
<logic:equal name="ApplicantFormAgy" property="applicant.active" value="true">
	<mmj-agy:hasAccess forward="applicantEdit">
	    <html:form action="/applicantEdit.do" onsubmit="return singleSubmit();">
	      <input type="hidden" name="applicant.applicantId" value="<bean:write name="ApplicantFormAgy" property="applicant.applicantId"/>"/>
	      <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
	    </html:form>
	</mmj-agy:hasAccess>
	<mmj-agy:hasAccess forward="applicantCompliancyTest">
	    <html:form action="/applicantCompliancyTest.do" onsubmit="return singleSubmit();">
	      <input type="hidden" name="applicant.applicantId" value="<bean:write name="ApplicantFormAgy" property="applicant.applicantId"/>"/>
	      <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton"><bean:message key="button.compliancy"/></html:submit></td>
	    </html:form>
	<logic:equal name="ApplicantFormAgy" property="applicant.compliant" value="false">
        <html:form action="/applicantEmailProcess.do" styleId="ApplicantNew" onsubmit="return singleSubmit();">
	      <input type="hidden" name="applicantId" value="<bean:write name="ApplicantFormAgy" property="applicant.applicantId"/>"/>
          <input type="hidden" name="emailActionId" value="8">
          <td align="right" valign="middle" width="80"><html:submit styleClass="wideButton"><bean:message key="button.requestDocuments"/></html:submit></td>
        </html:form>
    </logic:equal>
	</mmj-agy:hasAccess>
	<logic:notEqual name="ApplicantFormAgy" property="applicant.archived" value="true">
		<mmj-agy:hasAccess forward="applicantArchive">
		    <html:form action="/applicantArchive.do" onsubmit="return singleSubmit();">
		    <input type="hidden" name="applicant.applicantId" value="<bean:write name="ApplicantFormAgy" property="applicant.applicantId"/>"/>
		    <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton"><bean:message key="button.archive"/></html:submit></td>
		    </html:form>
		</mmj-agy:hasAccess>
	</logic:notEqual>
	<logic:equal name="ApplicantFormAgy" property="applicant.archived" value="true">
		<mmj-agy:hasAccess forward="applicantUnarchive">
		    <html:form action="/applicantUnarchive.do" onsubmit="return singleSubmit();">
		    <input type="hidden" name="applicant.applicantId" value="<bean:write name="ApplicantFormAgy" property="applicant.applicantId"/>"/>
		    <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton"><bean:message key="button.unarchive"/></html:submit></td>
		    </html:form>
		</mmj-agy:hasAccess>
	</logic:equal>
	<%-- Patrice wanted the Delete button removed 07/04/2011
	<mmj-agy:hasAccess forward="applicantDelete">
	    <html:form action="/applicantDelete.do" onsubmit="return singleSubmit();">
	    <input type="hidden" name="applicant.applicantId" value="<bean:write name="ApplicantFormAgy" property="applicant.applicantId"/>"/>
	    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
	    </html:form>
	</mmj-agy:hasAccess>
	--%>
</logic:equal>
  </tr>
</table>
<html:errors/>
<table cellpadding="0" cellspacing="0" width="100%" border="0">
  <tr>
    <td align="left" valign="top" width="75%">
			<div class="tabber">
			  <div class="tabbertab">
				  <h2>Registration</h2>
					<table class="simple" width="100%">
						<tr>
					    <th align="left" width="25%"><bean:message key="label.firstName"/></th>
					    <td align="left" width="35%"><bean:write name="ApplicantFormAgy" property="applicant.user.firstName"/></td>
					    <th align="left" width="15%"><bean:message key="label.address"/></th>
					    <td align="left" width="25%"><bean:write name="ApplicantFormAgy" property="applicant.address.address1"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.lastName"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.user.lastName"/></td>
					    <th align="left">&nbsp;</th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.address.address2"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.nhsStaffName"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.user.nhsStaffName"/></td>
					    <th align="left">&nbsp;</th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.address.address3"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
					    <td align="left">
					    <logic:empty name="ApplicantFormAgy" property="applicant.user.emailAddress">
					      &nbsp;
					    </logic:empty>
					    <logic:notEmpty name="ApplicantFormAgy" property="applicant.user.emailAddress">    
					  	  <html:link forward="sendEmail" paramId="toEmailAddress" paramName="ApplicantFormAgy" paramProperty="applicant.user.niceEmailAddress" titleKey="title.sendEmail">
					        <bean:write name="ApplicantFormAgy" property="applicant.user.emailAddress"/>
					      </html:link>
							</logic:notEmpty>
					    </td>
					
					    <th align="left">&nbsp;</th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.address.address4"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.mobileNumber"/></th>
					    <td align="left">
					    <logic:empty name="ApplicantFormAgy" property="applicant.mobileNumber">
					      &nbsp;
					    </logic:empty>
					    <logic:notEmpty name="ApplicantFormAgy" property="applicant.mobileNumber">    
					  	  <html:link forward="sendSms" paramId="mobileNumber" paramName="ApplicantFormAgy" paramProperty="applicant.mobileNumber" titleKey="title.sendSms">
					        <bean:write name="ApplicantFormAgy" property="applicant.mobileNumber"/>
					      </html:link>
							</logic:notEmpty>
					    </td>
					
					    <th align="left"><bean:message key="label.postalCode"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.address.postalCode"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.dateOfBirth"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.dateOfBirth" formatKey="format.mediumDateFormat" /></td>
					
					    <th align="left"><bean:message key="label.country"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy"  property="applicant.countryName"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.gender"/></th>
					    <td align="left">
							  <logic:equal name="ApplicantFormAgy" property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlMale %>">
							    <bean:message key="label.male"/>
							  </logic:equal>
							  <logic:equal name="ApplicantFormAgy" property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlFemale %>">
							    <bean:message key="label.female"/>
							  </logic:equal>
					    </td>
					    <th align="left"><bean:message key="label.telephoneNumber"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.telephoneNumber"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.currentlyWorking"/></th>
					    <td align="left" colspan="3">
					      <logic:equal name="ApplicantFormAgy" property="applicant.currentlyWorking" value="true">
							    <bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.currentlyWorking" value="true">
							    <bean:message key="label.no"/>
					      </logic:notEqual>
                        </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.agencyPaperwork"/></th>
					    <td align="left" colspan="3"><bean:write name="ApplicantFormAgy" property="applicant.reference"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.signedApplicationForm"/></th>
					    <td align="left" colspan="3">
					      <logic:equal name="ApplicantFormAgy" property="applicant.signedApplicationForm" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.signedApplicationForm" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.signedTermsAndConditions"/></th>
					    <td align="left" colspan="3">
					      <logic:equal name="ApplicantFormAgy" property="applicant.signedTermsAndConditions" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.signedTermsAndConditions" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.signedDataConsentForm"/></th>
					    <td align="left" colspan="3">
					      <logic:equal name="ApplicantFormAgy" property="applicant.signedDataConsentForm" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.signedDataConsentForm" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.completedCompetencyTest"/></th>
					    <td align="left" colspan="3">
					      <logic:equal name="ApplicantFormAgy" property="applicant.completedCompetencyTest" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.completedCompetencyTest" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.completedNurseInterview"/></th>
					    <td align="left" colspan="3">
					      <logic:equal name="ApplicantFormAgy" property="applicant.completedNurseInterview" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.completedNurseInterview" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.arrivalInUKDate"/></th>
					    <td align="left" colspan="3">
						  <bean:write name="ApplicantFormAgy" property="applicant.arrivalInCountryDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.availabilityDate"/></th>
					    <td align="left" colspan="3">
						  <bean:write name="ApplicantFormAgy" property="applicant.availabilityDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.preferredLocation"/></th>
					    <td align="left" colspan="3"><bean:write name="ApplicantFormAgy" property="applicant.geographicalRegionName"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.disciplineCategory"/></th>
					    <td align="left" colspan="3">
					      <bean:write name="ApplicantFormAgy" property="applicant.disciplineCategoryName"/>&nbsp;
<logic:present name="ApplicantFormAgy" property="applicant.regulatorName">
                          (<bean:message key="label.mustRegisterWith"/>&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.regulatorName"/>)
</logic:present>
					    </td>
					  </tr>
            <tr>
              <th align="left" class="label"><bean:message key="label.clientGroup"/></th>
              <td align="left" colspan="3">
<logic:equal name="ApplicantFormAgy" property="applicant.clientGroup" value="1">
                <bean:message key="label.adults"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.clientGroup" value="2">
                <bean:message key="label.paeds"/>
</logic:equal>
              </td>
            </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.areaOfSpeciality"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.areaOfSpecialityName"/></td>
              <th align="left" class="label"><bean:message key="label.limitedCompany"/></th>
              <td align="left">
                <bean:write name="ApplicantFormAgy" property="applicant.limitedCompanyName"/>
              </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.areaOfSpeciality"/>&nbsp;2</th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.areaOfSpecialityName2"/></td>
              <th align="left" class="label"><bean:message key="label.vatChargeable"/></th>
              <td align="left">
                <logic:equal name="ApplicantFormAgy" property="applicant.vatChargeable" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="ApplicantFormAgy" property="applicant.vatChargeable" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.interviewDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.interviewDate" formatKey="format.mediumDateFormat"/>
					    </td>
					    <th align="left" class="label"><bean:message key="label.bankName"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.bankName"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.archived"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.archived" value="true">
							    <bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.archived" value="true">
							    <bean:message key="label.no"/>
					      </logic:notEqual>
                <logic:equal name="ApplicantFormAgy" property="applicant.hasBeenUnarchived" value="true">
                  &nbsp;<b>***&nbsp;<bean:message key="label.hasBeenUnarchived"/>&nbsp;***</b>
                </logic:equal>
					    </td>
					    <th align="left" class="label"><bean:message key="label.bankSortCode"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.bankSortCode"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.compliant"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.compliant" value="true">
							    <bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.compliant" value="true">
							    <bean:message key="label.no"/>
					      </logic:notEqual>
                <logic:equal name="ApplicantFormAgy" property="applicant.recentlyCompliant" value="true">
                  &nbsp;<b>***&nbsp;<bean:message key="label.recentlyCompliant"/>&nbsp;***</b>
                </logic:equal>
					    </td>
					    <th align="left" class="label"><bean:message key="label.bankAccountName"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.bankAccountName"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.hideMoney"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.hideMoney" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.hideMoney" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					    <th align="left" class="label"><bean:message key="label.bankAccountNumber"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.bankAccountNumber"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.canToggleHideMoney"/></th>
					    <td align="left" colspan="3">
					      <logic:equal name="ApplicantFormAgy" property="applicant.canToggleHideMoney" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.canToggleHideMoney" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					</table>  
			  </div>
			  <div class="tabbertab">
				  <h2>Current Docs</h2>
					<table class="simple" width="100%">
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.cvFilename"/></th>
					    <td align="left">
					      <bean:define id="cvFileUrl" name="ApplicantFormAgy" property="applicant.cvFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + cvFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
					      <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.cvFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
					      <bean:write name="ApplicantFormAgy" property="applicant.cvFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.cvFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.cvFilename"/>&fileProperty=cv"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.crbFilename"/></th>
					    <td align="left">
					      <bean:define id="crbFileUrl" name="ApplicantFormAgy" property="applicant.crbFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + crbFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.crbFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.crbFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.crbFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.crbFilename"/>&fileProperty=crb"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.crbExpiryDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.crbExpiryDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.crbIssueDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.crbIssueDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.crbDisclosureNumber"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.crbDisclosureNumber" />
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.registeredWithDbsDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.registeredWithDbsDate" formatKey="format.mediumDateFormat" />
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.dbsRenewalDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.dbsRenewalDate" formatKey="format.mediumDateFormat" />
					    </td>
					  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.dbsFilename"/></th>
              <td align="left">
                <bean:define id="dbsFileUrl" name="ApplicantFormAgy" property="applicant.dbsFileUrl" type="java.lang.String" />
                <% 
                fileUrl = request.getContextPath() + dbsFileUrl;
                %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.dbsFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.dbsFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.dbsFilename">
                &nbsp;
                <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.dbsFilename"/>&fileProperty=dbs"><bean:message key="link.delete"/></a>
  </logic:present>
</mmj-agy:hasAccess>
              </td>
            </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference1Filename"/></th>
					    <td align="left">
					      <bean:define id="reference1FileUrl" name="ApplicantFormAgy" property="applicant.reference1FileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + reference1FileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.reference1Filename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.reference1Filename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.reference1Filename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.reference1Filename"/>&fileProperty=reference1"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference1Date"/></th>
					    <td align="left">
						  <bean:write name="ApplicantFormAgy" property="applicant.reference1Date" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference1Status"/></th>
					    <td align="left">
<logic:equal name="ApplicantFormAgy" property="applicant.reference1Status" value="1">
						    <bean:message key="label.appliedFor"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.reference1Status" value="2">
						    <bean:message key="label.pending"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.reference1Status" value="3">
						    <bean:message key="label.received"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.reference1Status" value="4">
						    <bean:message key="label.temporary"/>
</logic:equal>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference1"/></th>
					    <td align="left">
						  <bean:write name="ApplicantFormAgy" property="applicant.reference1"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference2Filename"/></th>
					    <td align="left">
					      <bean:define id="reference2FileUrl" name="ApplicantFormAgy" property="applicant.reference2FileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + reference2FileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.reference2Filename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.reference2Filename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.reference2Filename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.reference2Filename"/>&fileProperty=reference2"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference2Date"/></th>
					    <td align="left">
						  <bean:write name="ApplicantFormAgy" property="applicant.reference2Date" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference2Status"/></th>
					    <td align="left">
<logic:equal name="ApplicantFormAgy" property="applicant.reference2Status" value="1">
						    <bean:message key="label.appliedFor"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.reference2Status" value="2">
						    <bean:message key="label.pending"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.reference2Status" value="3">
						    <bean:message key="label.received"/>
</logic:equal>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference2"/></th>
					    <td align="left">
						  <bean:write name="ApplicantFormAgy" property="applicant.reference2"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.performanceEvaluationDate"/></th>
					    <td align="left">
						  <bean:write name="ApplicantFormAgy" property="applicant.performanceEvaluationDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.performanceEvaluation"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.performanceEvaluation" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.performanceEvaluation" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <%/* NEW --> */%>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.assessment12WeekDate"/></th>
					    <td align="left">
						  <bean:write name="ApplicantFormAgy" property="applicant.assessment12WeekDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.assessment12Week"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.assessment12Week" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.assessment12Week" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <%/* <-- NEW */%>
					</table>
			  </div>
			  <div class="tabbertab">
				  <h2>ID Docs</h2>
					<table class="simple" width="100%">
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.photoFilename"/></th>
					    <td align="left">
					      <bean:define id="photoFileUrl" name="ApplicantFormAgy" property="applicant.photoFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + photoFileUrl;
					      %>
						    <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.photoFilename"/></html:link>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.photoFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.photoFilename"/>&fileProperty=photo"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.visaType"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.visaTypeName"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.visaExpiryDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.visaExpiryDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.birthCertificateFilename"/></th>
					    <td align="left">
					      <bean:define id="birthCertificateFileUrl" name="ApplicantFormAgy" property="applicant.birthCertificateFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + birthCertificateFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.birthCertificateFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.birthCertificateFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.birthCertificateFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.birthCertificateFilename"/>&fileProperty=birthCertificate"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.birthCertificate"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.birthCertificate" value="true">
							    <bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.birthCertificate" value="true">
							    <bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress1Filename"/></th>
					    <td align="left">
					      <bean:define id="proofOfAddress1FileUrl" name="ApplicantFormAgy" property="applicant.proofOfAddress1FileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + proofOfAddress1FileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.proofOfAddress1Filename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.proofOfAddress1Filename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.proofOfAddress1Filename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.proofOfAddress1Filename"/>&fileProperty=proofOfAddress1"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress1"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.proofOfAddress1" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.proofOfAddress1" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress2Filename"/></th>
					    <td align="left">
					      <bean:define id="proofOfAddress2FileUrl" name="ApplicantFormAgy" property="applicant.proofOfAddress2FileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + proofOfAddress2FileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.proofOfAddress2Filename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.proofOfAddress2Filename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.proofOfAddress2Filename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.proofOfAddress2Filename"/>&fileProperty=proofOfAddress2"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress2"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.proofOfAddress2" value="true">
							<bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.proofOfAddress2" value="true">
							<bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.idDocumentFilename"/></th>
					    <td align="left">
					      <bean:define id="idDocumentFileUrl" name="ApplicantFormAgy" property="applicant.idDocumentFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + idDocumentFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.idDocumentFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.idDocumentFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.idDocumentFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.idDocumentFilename"/>&fileProperty=idDocument"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.idDocumentExpiryDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.idDocumentExpiryDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.idDocumentNumber"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.idDocumentNumber"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.idDocumentType"/></th>
					    <td align="left">
					      <bean:write name="ApplicantFormAgy" property="applicant.idDocumentName"/>&nbsp;
					      <logic:equal name="ApplicantFormAgy" property="applicant.requiresVisa" value="true">
							**** Requires Visa ****
					      </logic:equal>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.niNumberStatus"/></th>
					    <td align="left">
<logic:equal name="ApplicantFormAgy" property="applicant.niNumberStatus" value="1">
						    <bean:message key="label.appliedFor"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.niNumberStatus" value="2">
						    <bean:message key="label.pending"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.niNumberStatus" value="3">
						    <bean:message key="label.received"/>
</logic:equal>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.niNumber"/></th>
					    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.niNumber"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.overseasPoliceClearance"/></th>
					    <td align="left">
<logic:equal name="ApplicantFormAgy" property="applicant.overseasPoliceClearance" value="true">
						    <bean:message key="label.yes"/>
</logic:equal>
<logic:notEqual name="ApplicantFormAgy" property="applicant.overseasPoliceClearance" value="true">
							  <bean:message key="label.no"/>
</logic:notEqual>
					    </td>
					  </tr>
					</table>
				  </div>
				  <div class="tabbertab">
					  <h2>Health Docs</h2>
						<table class="simple" width="100%">
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.varicellaFilename"/></th>
					    <td align="left">
					      <bean:define id="varicellaFileUrl" name="ApplicantFormAgy" property="applicant.varicellaFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + varicellaFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.varicellaFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.varicellaFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.varicellaFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.varicellaFilename"/>&fileProperty=varicella"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.hepbFilename"/></th>
					    <td align="left">
					      <bean:define id="hepbFileUrl" name="ApplicantFormAgy" property="applicant.hepbFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + hepbFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.hepbFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.hepbFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.hepbFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.hepbFilename"/>&fileProperty=hepb"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.tbFilename"/></th>
					    <td align="left">
					      <bean:define id="tbFileUrl" name="ApplicantFormAgy" property="applicant.tbFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + tbFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.tbFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.tbFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.tbFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.tbFilename"/>&fileProperty=tb"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.mmrx2Filename"/></th>
					    <td align="left">
					      <bean:define id="mmrx2FileUrl" name="ApplicantFormAgy" property="applicant.mmrx2FileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + mmrx2FileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.mmrx2Filename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.mmrx2Filename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.mmrx2Filename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.mmrx2Filename"/>&fileProperty=mmrx2"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.mmrFilename"/></th>
					    <td align="left">
					      <bean:define id="mmrFileUrl" name="ApplicantFormAgy" property="applicant.mmrFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + mmrFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.mmrFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.mmrFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.mmrFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.mmrFilename"/>&fileProperty=mmr"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.ivsEppFilename"/></th>
              <td align="left">
                <bean:define id="ivsEppFileUrl" name="ApplicantFormAgy" property="applicant.ivsEppFileUrl" type="java.lang.String" />
                <% 
                fileUrl = request.getContextPath() + ivsEppFileUrl;
                %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.ivsEppFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.ivsEppFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.ivsEppFilename">
                &nbsp;
                <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.ivsEppFilename"/>&fileProperty=ivsEpp"><bean:message key="link.delete"/></a>
  </logic:present>
</mmj-agy:hasAccess>
              </td>
            </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkFilename"/></th>
					    <td align="left">
					      <bean:define id="fitToWorkFileUrl" name="ApplicantFormAgy" property="applicant.fitToWorkFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + fitToWorkFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.fitToWorkFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.fitToWorkFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.fitToWorkFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.fitToWorkFilename"/>&fileProperty=fitToWork"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkExpiryDate"/></th>
					    <td align="left">
						  <bean:write name="ApplicantFormAgy" property="applicant.fitToWorkExpiryDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkStatus"/></th>
              <td align="left">
<logic:equal name="ApplicantFormAgy" property="applicant.fitToWorkStatus" value="1">
                <bean:message key="label.appliedFor"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.fitToWorkStatus" value="2">
                <bean:message key="label.pending"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.fitToWorkStatus" value="3">
                <bean:message key="label.received"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.fitToWorkStatus" value="4">
                <bean:message key="label.temporary"/>
</logic:equal>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkIssuedBy"/></th>
              <td align="left">
<logic:equal name="ApplicantFormAgy" property="applicant.fitToWorkIssuedBy" value="0">
                &nbsp;
</logic:equal>
<logic:greaterThan name="ApplicantFormAgy" property="applicant.fitToWorkIssuedBy" value="0">
                <bean:message key="<%= fitToWorkIssuedByMessageKey %>"/>
</logic:greaterThan>
              </td>
            </tr>
					</table>
			  </div>
		    <div class="tabbertab">
				  <h2>Certificates</h2>
					<table class="simple" width="100%">
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.drivingLicense"/></th>
              <td align="left">
                <logic:equal name="ApplicantFormAgy" property="applicant.drivingLicense" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="ApplicantFormAgy" property="applicant.drivingLicense" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.drivingLicenseExpiryDate"/></th>
              <td align="left">
                <bean:write name="ApplicantFormAgy" property="applicant.drivingLicenseExpiryDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.degreeDetail"/></th>
					    <td align="left">
						  <bean:write name="ApplicantFormAgy" property="applicant.degreeDetail"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.degree"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.degree" value="true">
							    <bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.degree" value="true">
							    <bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.englishTestCertificateFilename"/></th>
              <td align="left">
                <bean:define id="englishTestCertificateFileUrl" name="ApplicantFormAgy" property="applicant.englishTestCertificateFileUrl" type="java.lang.String" />
                <% 
                fileUrl = request.getContextPath() + englishTestCertificateFileUrl;
                %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.englishTestCertificateFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.englishTestCertificateFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.englishTestCertificateFilename">
                &nbsp;
                <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.englishTestCertificateFilename"/>&fileProperty=englishTestCertificate"><bean:message key="link.delete"/></a>
  </logic:present>
</mmj-agy:hasAccess>
              </td>
            </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.languageCompetency"/></th>
					    <td align="left">
<logic:equal name="ApplicantFormAgy" property="applicant.languageCompetency" value="true">
							  <bean:message key="label.yes"/>
</logic:equal>
<logic:notEqual name="ApplicantFormAgy" property="applicant.languageCompetency" value="true">
							  <bean:message key="label.no"/>
</logic:notEqual>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.trainingFilename"/></th>
					    <td align="left">
					      <bean:define id="trainingFileUrl" name="ApplicantFormAgy" property="applicant.trainingFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + trainingFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.trainingFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.trainingFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.trainingFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.trainingFilename"/>&fileProperty=training"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.trainingExpiryDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.trainingExpiryDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.manualHandlingTraining"/></th>
					    <td align="left">
					      <logic:equal name="ApplicantFormAgy" property="applicant.manualHandlingTraining" value="true">
							    <bean:message key="label.yes"/>
					      </logic:equal>
					      <logic:notEqual name="ApplicantFormAgy" property="applicant.manualHandlingTraining" value="true">
							    <bean:message key="label.no"/>
					      </logic:notEqual>
					    </td>
					  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.basicLifeSupportTraining"/></th>
              <td align="left">
                <logic:equal name="ApplicantFormAgy" property="applicant.basicLifeSupportTraining" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="ApplicantFormAgy" property="applicant.basicLifeSupportTraining" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.neonatalLifeSupportTraining"/></th>
              <td align="left">
                <logic:equal name="ApplicantFormAgy" property="applicant.neonatalLifeSupportTraining" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="ApplicantFormAgy" property="applicant.neonatalLifeSupportTraining" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.elearningTraining"/></th>
              <td align="left">
                <logic:equal name="ApplicantFormAgy" property="applicant.elearningTraining" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="ApplicantFormAgy" property="applicant.elearningTraining" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.povaTraining"/></th>
              <td align="left">
                <logic:equal name="ApplicantFormAgy" property="applicant.povaTraining" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="ApplicantFormAgy" property="applicant.povaTraining" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.paediatricLifeSupportFilename"/></th>
              <td align="left">
                <bean:define id="paediatricLifeSupportFileUrl" name="ApplicantFormAgy" property="applicant.paediatricLifeSupportFileUrl" type="java.lang.String" />
                <% 
                fileUrl = request.getContextPath() + paediatricLifeSupportFileUrl;
                %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.paediatricLifeSupportFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.paediatricLifeSupportFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.paediatricLifeSupportFilename">
                &nbsp;
                <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.paediatricLifeSupportFilename"/>&fileProperty=paediatricLifeSupport"><bean:message key="link.delete"/></a>
  </logic:present>
</mmj-agy:hasAccess>
              </td>
            </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.paediatricLifeSupportIssuedDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.paediatricLifeSupportIssuedDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.registration"/></th>
					    <th  align="left" class="label">&nbsp;</th>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.ahpRegistrationType"/></th>
					    <td align="left">
<logic:equal name="ApplicantFormAgy" property="applicant.ahpRegistrationType" value="1">
                          <bean:message key="label.hcpc"/>
</logic:equal>
<logic:equal name="ApplicantFormAgy" property="applicant.ahpRegistrationType" value="2">
                          <bean:message key="label.nmc"/>
</logic:equal>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.registrationFilename"/></th>
					    <td align="left">
					      <bean:define id="registrationFileUrl" name="ApplicantFormAgy" property="applicant.registrationFileUrl" type="java.lang.String" />
					      <% 
					      fileUrl = request.getContextPath() + registrationFileUrl;
					      %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="ApplicantFormAgy" property="applicant.registrationFilename"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="ApplicantFormAgy" property="applicant.registrationFilename"/>
</logic:notEqual>
<mmj-agy:hasAccess forward="applicantEdit">
  <logic:present name="ApplicantFormAgy" property="applicant.registrationFilename">
	              &nbsp;
	              <a href="<%= deleteFileUrl %>&filename=<bean:write name="ApplicantFormAgy" property="applicant.registrationFilename"/>&fileProperty=registration"><bean:message key="link.delete"/></a>
	</logic:present>
</mmj-agy:hasAccess>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.registrationExpiryDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.registrationExpiryDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.registrationNumber"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.registrationNumber"/>
				      </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.registrationLastCheckedDate"/></th>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.registrationLastCheckedDate" formatKey="format.mediumDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.registrationAlertNotification"/></th>
					    <td align="left">
                <bean:message key="<%= registrationAlertNotificationMessageKey %>"/>
					    </td>
					  </tr>
			  	</table>
			  </div>
		    <div class="tabbertab">
				  <h2>Notes</h2>
					<table width="100%">
					  <tr>
					    <td align="left">
					      <bean:write name="ApplicantFormAgy" property="notes" filter="false"/>
					    </td>
					  </tr>
					</table>
			  </div>		
		    <div class="tabbertab">
				  <h2>Checklist</h2>
					<table>
					  <tr>
					    <td align="left">
	<logic:equal name="ApplicantFormAgy" property="applicant.compliant" value="true">
    <mmj-agy:hasAccess forward="applicantEdit">
	              <a href="<%= createAgencyWorkerChecklistFileUrl %>">Create</a>
		</mmj-agy:hasAccess>
	</logic:equal>
<%
  String tempFilePath = request.getContextPath() + checklistFileUrl;
%>
					    </td>
<logic:present name="ApplicantFormAgy" property="applicant.checklistCreatedTime">
					    <td align="left">
								<html:link href="<%= tempFilePath %>"  target="pdf">
								  <bean:message key="link.view"/>
								</html:link>
					    </td>
					    <td align="left">
						    <bean:write name="ApplicantFormAgy" property="applicant.checklistCreatedTime" formatKey="format.longDateTimeFormat"/>
					    </td>
</logic:present>					    
					  </tr>
					</table>
<logic:present name="ApplicantFormAgy" property="agencyWorkerChecklists">
  <logic:notEmpty name="ApplicantFormAgy" property="agencyWorkerChecklists">
					<table>
	  <logic:iterate id="agencyWorkerChecklistFile" name="ApplicantFormAgy" property="agencyWorkerChecklists" type="com.helmet.application.agy.AgencyWorkerChecklistFile">
            <bean:define id="checklistFileUrl" name="agencyWorkerChecklistFile" property="checklistFileUrl"/>
<%
  String fullChecklistFileUrl = request.getContextPath() + checklistFileUrl;
%>
					  <tr>
			        <td align="left">
			<mmj-agy:hasAccess forward="bookingGradeViewSummary">
        <logic:equal name="agencyWorkerChecklistFile" property="current" value="true">
			          <html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="agencyWorkerChecklistFile" paramProperty="bookingGradeApplicantId" titleKey="title.bookingGradeApplicantView">
			            <bean:write name="agencyWorkerChecklistFile" property="bookingId" format="#000"/>
			          </html:link>
        </logic:equal>
        <logic:notEqual name="agencyWorkerChecklistFile" property="current" value="true">
			          <bean:write name="agencyWorkerChecklistFile" property="bookingId" format="#000"/>
       </logic:notEqual>
			</mmj-agy:hasAccess>  
			<mmj-agy:hasNoAccess forward="bookingGradeViewSummary">
			          <bean:write name="agencyWorkerChecklistFile" property="bookingId" format="#000"/>
			</mmj-agy:hasNoAccess>  
			        </td>
			        <td align="left">
			          <bean:write name="agencyWorkerChecklistFile" property="clientName" filter="false"/>
			        </td>
			        <td align="left">
			          <bean:write name="agencyWorkerChecklistFile" property="fileDate" filter="false"  formatKey="format.mediumDateTimeFormat"/>
			        </td>
			        <td align="left">
			          <html:link href="<%= fullChecklistFileUrl %>" target="pdf"><bean:message key="link.view"/></html:link>
			        </td>
			        <td align="left">
        <logic:equal name="agencyWorkerChecklistFile" property="current" value="true">
                &nbsp;
        </logic:equal>
        <logic:notEqual name="agencyWorkerChecklistFile" property="current" value="true">
	              <a href="<%= deleteAgencyWorkerChecklistFileUrl %>&filename=<bean:write name="agencyWorkerChecklistFile" property="filename"/>"><bean:message key="link.delete"/></a>
       </logic:notEqual>
			        </td>
					  </tr>
	  </logic:iterate>
					</table>
  </logic:notEmpty>
</logic:present>
			  </div>		
		      <div class="tabbertab">
				  <h2>Unavailable</h2>
					<table width="100%">
					  <tr>
					    <td align="left">
			          <div id="flatcalMultiDays" style="float: left"></div>
					    </td>
					  </tr>
					</table>
			  </div>		
			</div>
    </td>
    <td align="center" valign="top" width="25%">
			<logic:empty name="ApplicantFormAgy" property="applicant.photoFilename" >
			  <bean:message key="text.noPhotoAvailable"/>
			</logic:empty>
			<logic:notEmpty name="ApplicantFormAgy" property="applicant.photoFilename" >
			  <bean:define id="photoFileUrl" name="ApplicantFormAgy" property="applicant.photoFileUrl" type="java.lang.String" />
			  <html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
			</logic:notEmpty>
	  </td>
  </tr>
</table>
<table cellpadding="0" cellspacing="0" width="100%" border="0">
  <tr>
    <td>
	  <div class="tabber">
		<div class="tabbertab">
		  <h2>Training</h2>
		  <table class="simple" width="100%">
<mmj-agy:hasAccess forward="applicantTrainingCourseNew">
		    <tr>
		      <td colspan="7">
				<html:link forward="applicantTrainingCourseNew" paramId="applicant.applicantId" paramName="ApplicantFormAgy" paramProperty="applicant.applicantId"><bean:message key="link.new"/></html:link>
		      </td>
		    </tr>
</mmj-agy:hasAccess>
		    <tr>
		      <th>
		        Course
		      </th>
		      <th>
		        Company
		      </th>
		      <th>
		        Start
		      </th>
          <th>
            End
          </th>
          <th>
            Documentation
          </th>
          <th>
            Comment
          </th>
          <th>
            *
          </th>
		    </tr>
    <logic:iterate id="applicantTrainingCourse" name="ApplicantFormAgy" property="applicant.applicantTrainingCourseUsers" type="com.helmet.bean.ApplicantTrainingCourseUser">
		    <tr>
		      <td>
<mmj-agy:hasAccess forward="applicantTrainingCourseNew">
          <html:link forward="applicantTrainingCourseEdit" paramId="applicantTrainingCourse.applicantTrainingCourseId" paramName="applicantTrainingCourse" paramProperty="applicantTrainingCourseId"><bean:write name="applicantTrainingCourse" property="trainingCompanyCourseName" filter="false"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasNoAccess forward="applicantTrainingCourseNew">
		        <bean:write name="applicantTrainingCourse" property="trainingCompanyCourseName" filter="false"/>
</mmj-agy:hasNoAccess>
		      </td>
		      <td>
            <bean:write name="applicantTrainingCourse" property="trainingCompanyName" filter="false"/>
		      </td>
		      <td>
            <bean:write name="applicantTrainingCourse" property="startDate" formatKey="format.mediumDateFormat"/>
		      </td>
          <td>
            <bean:write name="applicantTrainingCourse" property="endDate" formatKey="format.mediumDateFormat"/>
          </td>
          <td>
            <bean:define id="documentationFileUrl" name="applicantTrainingCourse" property="documentationFileUrl" type="java.lang.String" />
                <% 
                fileUrl = request.getContextPath() + documentationFileUrl;
                %>
<logic:equal name="consultant" property="canViewDocuments" value="true">
                <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="applicantTrainingCourse" property="documentationFileName"/></html:link>
</logic:equal>
<logic:notEqual name="consultant" property="canViewDocuments" value="true">
                <bean:write name="applicantTrainingCourse" property="documentationFileName"/>
</logic:notEqual>
          </td>
          <td>
            <bean:write name="applicantTrainingCourse" property="comment" filter="false"/>
          </td>
          <td>
        <mmj-agy:hasAccess forward="applicantTrainingCourseDelete">
          <logic:equal name="applicantTrainingCourse" property="active" value="true">
              <html:link forward="applicantTrainingCourseDelete" paramId="applicantTrainingCourseUser.applicantTrainingCourseId" paramName="applicantTrainingCourse" paramProperty="applicantTrainingCourseId" titleKey="title.applicantTrainingCourseDelete">x</html:link>
          </logic:equal>
          <logic:equal name="applicantTrainingCourse" property="active" value="false">
              &nbsp;
          </logic:equal>
        </mmj-agy:hasAccess>
        <mmj-agy:hasNoAccess forward="applicantTrainingCourseDelete">
              &nbsp;
        </mmj-agy:hasNoAccess>
          </td>
		    </tr>
		 </logic:iterate>
		  </table>
		</div>
	  </div>
    </td>
  </tr>
</table>
<br/>
<bean:define id="tab" value="weekly"/>

<%
String theAction = "/applicantView.do?applicant.applicantId=" + applicantId;
%>

<bean:define id="weekToShow" name="ApplicantFormAgy" property="weekToShow" />

<%/* tabs */%>
<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="weekly"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="25%">
			<c:set var="fourWeeksBack" value="${weekToShow - 4}" scope="page"/>
			<c:set var="twoWeeksBack" value="${weekToShow - 2}" scope="page"/>
			<c:set var="previousWeek" value="${weekToShow - 1}" scope="page"/>
      <c:set var="currentWeek" value="${0}" scope="page"/>
			<c:set var="nextWeek" value="${weekToShow + 1}" scope="page"/>
			<c:set var="twoWeeksForward" value="${weekToShow + 2}" scope="page"/>
			<c:set var="fourWeeksForward" value="${weekToShow + 4}" scope="page"/>
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="fourWeeksBack" paramScope="page" titleKey="title.showFourWeeksBack">&lt;&lt;&lt;&lt;</html:link>
      &nbsp;
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="twoWeeksBack" paramScope="page" titleKey="title.showTwoWeeksBack">&lt;&lt;</html:link>
      &nbsp;
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="previousWeek" paramScope="page" titleKey="title.showPreviousWeek">&lt;</html:link>
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="currentWeek" paramScope="page"  titleKey="title.showCurrentWeek"><bean:message key="link.weekly"/></html:link>
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="nextWeek" paramScope="page" titleKey="title.showNextWeek">&gt;</html:link>
      &nbsp;
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="twoWeeksForward" paramScope="page" titleKey="title.showTwoWeeksForward">&gt;&gt;</html:link>
      &nbsp;
      <html:link page="<%= theAction %>" paramId="weekToShow" paramName="fourWeeksForward" paramScope="page" titleKey="title.showFourWeeksForward">&gt;&gt;&gt;&gt;</html:link>
    </td>
    <td align="center" class="tabInvisibleClass" width="50%">
			<bean:write name="ApplicantFormAgy" property="startDate" formatKey="format.longDateFormat" />
			-
			<bean:write name="ApplicantFormAgy" property="endDate" formatKey="format.longDateFormat" />
    </td>
    <td align="center" class="tabInvisibleClass" width="25%">
			&nbsp;
    </td>
  </tr>
</table>

<jsp:include page="shiftsIncludeApplicant.jsp" flush="true">
  <jsp:param name="theFormAgy" value="ApplicantFormAgy"/>
  <jsp:param name="showButtons" value="true"/>
</jsp:include>
<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

// load current selections ...

var MA = [
<%
  // get date s request parameter (comma separated dates in the form yyyy/mm/dd)
  java.util.StringTokenizer datesParser = new java.util.StringTokenizer(dates, ",");
  int i = 0; 
  // for each one ...
  while (datesParser.hasMoreTokens()) {
    // in the format yyyy/mm/dd
    String theDate = datesParser.nextToken();
    // need to separate into yyyy mm dd
    java.util.StringTokenizer dateParser = new java.util.StringTokenizer(theDate, "-");
    Integer theYear = new Integer(dateParser.nextToken());
    Integer theMonth = new Integer(dateParser.nextToken());
    Integer theDay = new Integer(dateParser.nextToken());
    if (i > 0) {
%>
,
<%
    }
%>
new Date(<%= theYear.intValue() %>, <%= theMonth.intValue() - 1 %>, <%= theDay.intValue() %>)    
<%    
    i++;
  }
%>
];

// end hiding contents from old browsers  -->
</script>

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers
var canEnterAnyDate = false; // No days in past or > 1 year in the future.
// end hiding contents from old browsers  -->
</script>

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

// Top optimize performance, Create the variables outside isDisabled.  
// isDisabled is called for EVERY day (e.g. 365 times for a year) 
var dateNextYear=new Date() 
// If you are concerned with leap years then code accordingly 
dateNextYear.setDate(dateNextYear.getDate() + 365) 
var today = new Date()
today.setHours(0);
today.setMinutes(0);
today.setSeconds(0); 

// Disable Function return true if disabled 
function isDisabled(date) {

  if (canEnterAnyDate) {
    return false; // allow all days to be selected - in the past and > 1 year in the future
  }
  else{
    if (date >= dateNextYear) {
      return true;
    }
    else {
      return (date.getTime() < today.getTime()); 
    }
  }  
} 

function timeOutOfRange(date) {
    return true;
  if (date.getDay() == 0) { //No Sunday
    return true;
  }
  return false;
}

function flatCallback(cal) {
	// here we'll write the output; this is only for example.  You
	// will normally fill an input field or something with the dates.
	var el = document.getElementById("datesId");

	// reset initial content.
	el.value = "";

	// Reset the "MA", in case one triggers the calendar again.
	// CAREFUL!  You don't want to do "MA = [];".  We need to modify
	// the value of the current array, instead of creating a new one.
	// Zapatec.Calendar.setup is called only once! :-)  So be careful.
	MA.length = 0;

	// walk the calendar's multiple dates selection hash
	for (var i in cal.multiple) {
    	  var currentDate = cal.multiple[i];
 	  // sometimes the date is not actually selected, that's why we need to check.
	  if (currentDate) {
      	    // OK, selected.  Fill an input field.  Or something.  Just for example,
	    // we will display all selected dates in the element having the id "output".
	    if (el.value != "") {
	      el.value += ",";
	    }
	    el.value += currentDate.print("%Y-%m-%d");

	    // and push it in the "MA", in case one triggers the calendar again.
	    MA[MA.length] = currentDate;
	  }
	}
//  alert(MA.length);
}

var SPECIAL_DAYS = [];

function dateIsSpecial(year, month, day) {
  for (ii = 0; ii < SPECIAL_DAYS.length; ii++) {
    theMonth = '0' + (month + 1);
    if (theMonth.length > 2) {
      theMonth = theMonth.substr(1);
    }
    theDay = '0' + day;
    if (theDay.length > 2) {
      theDay = theDay.substr(1);
    }
    theDate = year + '-' + theMonth + '-' + theDay;
    if (SPECIAL_DAYS[ii] == theDate) {
	return true;
    }
  }
  return false;
}

// end hiding contents from old browsers  -->
</script>

<!-- override special days -->
<style type="text/css">
.zpCalSpecialDay { 
	font-weight: bold;
	color: #cc0000;
	background-color: #efebde;
}
/*.disabled { 
	color:#000; 
}*/
.edges {
   border : 1px solid;
   border-color: #adaa9c #fff #fff #adaa9c;
   background-color: #fffbee;
}
.edgesSpecialDay {
   font-weight: bold;
   color: #cc0000;
   border : 1px solid;
   border-color: #adaa9c #fff #fff #adaa9c;
   background-color: #fffbee;
}
.between {
   background-color: #dccdb9;
}
.betweenSpecialDay {
   font-weight: bold;
   color: #cc0000;
   background-color: #dccdb9;
}
</style>

<script type="text/javascript">

// MUST BE THE SAME AS ABOVE !!!!
var cal = new Zapatec.Calendar.setup({

  firstDay       : 1,
  numberMonths   : 3,
  
  monthsInRow    : 3,
  yearStep       : 1,

	flat       	   : "flatcalMultiDays", // ID of the parent element
	flatCallback   : flatCallback,
	align      	   : "BR",
	showOthers 	   : true,
	//sortOrder	   : "desc", //default is "asc"ending; or remove comment to sort in "desc"ending order; or "none" to NOT sort the multiple dates.
	multiple   	   : MA, // pass the initial or computed array of multiple dates to be initially selected

	dateStatusFunc : function(date, y, m, d) { return true; }

	});

</script>
<noscript>
  	<br/>
  	This page uses a <a href='http://www.zapatec.com/website/main/products/prod1/'> Javascript Calendar </a>, but
  	your browser does not support Javascript.
  	<br/>
  	Either enable Javascript in your Browser or upgrade to a newer version.
</noscript>

<%-- HELPER FORM STUFF --%>

<script type="text/javascript">
<!--  to hide script contents from old browsers

function dateIsSpecial2(date) {

  return dateIsSpecial(date.getYear(), date.getMonth(), date.getDate());

}


var startDate;
var endDate;
var callbacks = 0;

			/*
			 * Given two dates (in seconds) find out if date1 is bigger, date2 is bigger or
			 * they're the same, taking only the dates, not the time into account.
			 * In other words, different times on the same date returns equal.
			 * returns -1 for date1 bigger, 1 for date2 is bigger 0 for equal
			 */

			function compareDatesOnly(date1, date2) {
				var year1 = date1.getYear();
				var year2 = date2.getYear();
				var month1 = date1.getMonth();
				var month2 = date2.getMonth();
				var day1 = date1.getDate();
				var day2 = date2.getDate();

				if (year1 > year2) {
					return -1;
				}
				if (year2 > year1) {
					return 1;
				}

				//years are equal
				if (month1 > month2) {
					return -1;
				}
				if (month2 > month1) {
					return 1;
				}

				//years and months are equal
				if (day1 > day2) {
					return -1;
				}
				if (day2 > day1) {
					return 1;
				}

				//days are equal
				return 0;


				/* Can't do this because of timezone issues
				var days1 = Math.floor(date1.getTime()/Date.DAY);
				var days2 = Math.floor(date2.getTime()/Date.DAY);
				return (days1 - days2);
				*/
			}

			function filterDates1(cal) {
				startDate = cal.date;
				/* If they haven't chosen an 
				end date before we'll set it to the same date as the start date This
				way if the user scrolls in the start date 5 months forward, they don't
				need to do it again for the end date.
				*/

				if (endDate == null) { 
					Zapatec.Calendar.setup({
						inputField     :    "toDate",
						button         :    "toDateButton",  // What will trigger the popup of the calendar
						ifFormat       :    "%d/%m/%Y",
						timeFormat     :    "24",
						date           :     startDate,
						electric       :     false,
						showsTime      :     false,          //no time
	dateStatusFunc : function(date, y, m, d) {
			               if (isDisabled(date)) {
			                 return "disabled";
			               }
		               },
						onUpdate       :    filterDates2
					});
				}
			}

			function filterDates2(cal) {
				endDate = cal.date;
			}

			/*
			* Both functions disable and hilight dates.
			*/
			
			/* 
			* Can't choose days after the
			* end date if it is choosen, hilights start and end dates with one style and dates between them with another
			*/
			function dateInRange1(date) {

				if (endDate != null) {

					// Disable dates after end date
					var compareEnd = compareDatesOnly(date, endDate);
					if  (compareEnd < 0) {
						return (true);
					}

					// Hilight end date with "edges" style
					if  (compareEnd == 0) {
					        if (dateIsSpecial2(date)) {
					          return "edgesSpecialDay";
					        }
						return "edges";
					}


					// Hilight inner dates with "between" style
					if (startDate != null){
						var compareStart = compareDatesOnly(date, startDate);
						if  (compareStart < 0) {
						        if (dateIsSpecial2(date)) {
						          return "betweenSpecialDay";
						        }
						        return "between";
						} 
					} 
				}

                                if (!canEnterAnyDate) {
					//disable days prior to today
					var today = new Date();
					var compareToday = compareDatesOnly(date, today);
					if (compareToday > 0) {
						return(true);
					}
				}

			        if (dateIsSpecial2(date)) {
					return "zpCalSpecialDay";
                                }

				//all other days are enabled
				return false;
			}

			/* 
			* Can't choose days before the
			* start date if it is choosen, hilights start and end dates with one style and dates between them with another
			*/

			function dateInRange2(date) {
				if (startDate != null) {
					// Disable dates before start date
					var compareDays = compareDatesOnly(startDate, date);
					if  (compareDays < 0) {
						return (true);
					}

					// Hilight end date with "edges" style
					if  (compareDays == 0) {
					        if (dateIsSpecial2(date)) {
					          return "edgesSpecialDay";
					        }
						return "edges";
					}

					// Hilight inner dates with "between" style
					if ((endDate != null) && (date > startDate) && (date < endDate)) {
						if (dateIsSpecial2(date)) {
						  return "betweenSpecialDay";
						}
						return "between";
					} 
				} 

                                if (!canEnterAnyDate) {
					//disable days prior to today
					var today = new Date();
					var compareToday = compareDatesOnly(date, today);
					if (compareToday > 0) {
						return(true);
					}
				}

			        if (dateIsSpecial2(date)) {
					return "zpCalSpecialDay";
                                }

				//all other days are enabled
				return false;
			}
			// end hiding contents from old browsers  -->
	</script>

<noscript>
<br/>
This page uses a <a href='http://www.zapatec.com/website/main/products/prod1/'> Javascript Calendar </a>, but
your browser does not support Javascript.
<br/>
Either enable Javascript in your Browser or upgrade to a newer version.
</noscript>










