<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-app" prefix="mmj-app" %>
<bean:define id="registrationAlertNotification" name="applicant" property="registrationAlertNotification"/>
<bean:define id="fitToWorkIssuedBy" name="applicant" property="fitToWorkIssuedBy"/>
<%
String fileUrl = null;
String fitToWorkIssuedByMessageKey = "label.fitToWorkIssuedBy" + fitToWorkIssuedBy;
String registrationAlertNotificationMessageKey = "label.registrationAlertNotification" + registrationAlertNotification;
%>
<mmj-app:applicant var="applicant"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.applicantView"/>&nbsp;<html:link forward="changeSecretWord" titleKey="link.title.changeSecretWord"><bean:message key="link.changeSecretWord"/></html:link>
      &nbsp;<html:link forward="changePassword" titleKey="link.title.changePassword"><bean:message key="link.changePassword"/></html:link>
		</td>
<logic:equal name="applicant" property="active" value="true">
	  <td align="right" valign="middle" width="75">
	    <html:form action="/applicantEdit.do" onsubmit="return singleSubmit();">
	      <html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit>
	    </html:form>
	  </td>
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
              <td align="left"><bean:write name="applicant" property="user.firstName"/></td>
            </tr>
            <tr>
              <th align="left" class="label"><bean:message key="label.lastName"/></th>
              <td align="left"><bean:write name="applicant" property="user.lastName"/></td>
            </tr>
            <tr>
              <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
              <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>
            </tr>
            <tr>
              <th align="left"><bean:message key="label.telephoneNumber"/></th>
              <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
            </tr>
            <tr>
              <th align="left" class="label"><bean:message key="label.mobileNumber"/></th>
              <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
            </tr>
            <tr>
              <th align="left" class="label"><bean:message key="label.dateOfBirth"/></th>
              <td align="left"><bean:write name="applicant" property="dateOfBirth" formatKey="format.mediumDateFormat" /></td>
            </tr>
            <tr>
              <th align="left" class="label"><bean:message key="label.gender"/></th>
              <td align="left">
                <logic:equal name="applicant" property="gender" value="<%= com.helmet.persistence.Constants.sqlMale %>">
                  <bean:message key="label.male"/>
                </logic:equal>
                <logic:equal name="applicant" property="gender" value="<%= com.helmet.persistence.Constants.sqlFemale %>">
                  <bean:message key="label.female"/>
                </logic:equal>
              </td>
            </tr>
            <tr>
              <th align="left" class="label">&nbsp;</th>
              <td align="left">
                &nbsp;
              </td>
            </tr>
            <tr>
              <th align="left" width="25%"><bean:message key="label.address"/></th>
              <td align="left"><bean:write name="applicant" property="address.address1"/></td>
            </tr>
            <tr>
              <th align="left">&nbsp;</th>
              <td align="left"><bean:write name="applicant" property="address.address2"/></td>
            </tr>
            <tr>
              <th align="left">&nbsp;</th>
              <td align="left"><bean:write name="applicant" property="address.address3"/></td>
            </tr>
            <tr>
              <th align="left">&nbsp;</th>
              <td align="left"><bean:write name="applicant" property="address.address4"/></td>
            </tr>
            <tr>
              <th align="left"><bean:message key="label.postalCode"/></th>
              <td align="left"><bean:write name="applicant" property="address.postalCode"/></td>
            </tr>
            <tr>
              <th align="left"><bean:message key="label.country"/></th>
              <td align="left"><bean:write name="applicant"  property="countryName"/></td>
            </tr>
          </table>
        </div>
			  <div class="tabbertab">
				  <h2>Current Documents</h2>
          <table class="simple" width="100%">
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.cvFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="cvFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.crbFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="crbFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.crbExpiryDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="crbExpiryDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.crbIssueDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="crbIssueDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.crbDisclosureNumber"/></th>
              <td align="left">
                <bean:write name="applicant" property="crbDisclosureNumber" />
              </td>
            </tr>
			  <tr>
			    <th width="25%" align="left"class="label"><bean:message key="label.registeredWithDbsDate"/></th>
			    <td align="left">
				    <bean:write name="applicant" property="registeredWithDbsDate" formatKey="format.mediumDateFormat" />
			    </td>
			  </tr>
			  <tr>
			    <th width="25%" align="left"class="label"><bean:message key="label.dbsRenewalDate"/></th>
			    <td align="left">
				    <bean:write name="applicant" property="dbsRenewalDate" formatKey="format.mediumDateFormat" />
			    </td>
			  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.dbsFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="dbsFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference1Filename"/></th>
              <td align="left">
                <bean:write name="applicant" property="reference1Filename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference1Date"/></th>
              <td align="left">
              <bean:write name="applicant" property="reference1Date" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference1Status"/></th>
              <td align="left">
<logic:equal name="applicant" property="reference1Status" value="1">
                <bean:message key="label.appliedFor"/>
</logic:equal>
<logic:equal name="applicant" property="reference1Status" value="2">
                <bean:message key="label.pending"/>
</logic:equal>
<logic:equal name="applicant" property="reference1Status" value="3">
                <bean:message key="label.received"/>
</logic:equal>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference1"/></th>
              <td align="left">
              <bean:write name="applicant" property="reference1"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference2Filename"/></th>
              <td align="left">
                <bean:write name="applicant" property="reference2Filename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference2Date"/></th>
              <td align="left">
              <bean:write name="applicant" property="reference2Date" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference2Status"/></th>
              <td align="left">
<logic:equal name="applicant" property="reference2Status" value="1">
                <bean:message key="label.appliedFor"/>
</logic:equal>
<logic:equal name="applicant" property="reference2Status" value="2">
                <bean:message key="label.pending"/>
</logic:equal>
<logic:equal name="applicant" property="reference2Status" value="3">
                <bean:message key="label.received"/>
</logic:equal>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference2"/></th>
              <td align="left">
              <bean:write name="applicant" property="reference2"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.performanceEvaluationDate"/></th>
              <td align="left">
              <bean:write name="applicant" property="performanceEvaluationDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.performanceEvaluation"/></th>
              <td align="left">
                <logic:equal name="applicant" property="performanceEvaluation" value="true">
              <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="performanceEvaluation" value="true">
              <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.assessment12WeekDate"/></th>
              <td align="left">
              <bean:write name="applicant" property="assessment12WeekDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.assessment12Week"/></th>
              <td align="left">
                <logic:equal name="applicant" property="assessment12Week" value="true">
              <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="assessment12Week" value="true">
              <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
          </table>
        </div>
			  <div class="tabbertab">
				  <h2>ID Documents</h2>
          <table class="simple" width="100%">
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.photoFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="photoFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.requiresVisa"/></th>
              <td align="left">
                <logic:equal name="applicant" property="requiresVisa" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="requiresVisa" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.visaType"/></th>
              <td align="left">
                <bean:write name="applicant" property="visaTypeName"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.visaExpiryDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="visaExpiryDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.birthCertificateFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="birthCertificateFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.birthCertificate"/></th>
              <td align="left">
                <logic:equal name="applicant" property="birthCertificate" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="birthCertificate" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress1Filename"/></th>
              <td align="left">
                <bean:write name="applicant" property="proofOfAddress1Filename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress1"/></th>
              <td align="left">
                <logic:equal name="applicant" property="proofOfAddress1" value="true">
              <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="proofOfAddress1" value="true">
              <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>

            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress2Filename"/></th>
              <td align="left">
                <bean:write name="applicant" property="proofOfAddress2Filename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress2"/></th>
              <td align="left">
                <logic:equal name="applicant" property="proofOfAddress2" value="true">
              <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="proofOfAddress2" value="true">
              <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>

            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.idDocumentFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="idDocumentFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.idDocumentExpiryDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="idDocumentExpiryDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.idDocumentNumber"/></th>
              <td align="left">
                <bean:write name="applicant" property="idDocumentNumber"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.idDocument"/></th>
              <td align="left">
                <bean:write name="applicant" property="idDocumentName"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.niNumberStatus"/></th>
              <td align="left">
<logic:equal name="applicant" property="niNumberStatus" value="1">
                <bean:message key="label.appliedFor"/>
</logic:equal>
<logic:equal name="applicant" property="niNumberStatus" value="2">
                <bean:message key="label.pending"/>
</logic:equal>
<logic:equal name="applicant" property="niNumberStatus" value="3">
                <bean:message key="label.received"/>
</logic:equal>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.niNumber"/></th>
              <td align="left"><bean:write name="applicant" property="niNumber"/></td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.overseasPoliceClearance"/></th>
              <td align="left">
<logic:equal name="applicant" property="overseasPoliceClearance" value="true">
                <bean:message key="label.yes"/>
</logic:equal>
<logic:notEqual name="applicant" property="overseasPoliceClearance" value="true">
                <bean:message key="label.no"/>
</logic:notEqual>
              </td>
            </tr>
          </table>
        </div>
				<div class="tabbertab">
		      <h2>Health Documents</h2>
            <table class="simple" width="100%">
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.varicellaFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="varicellaFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.hepbFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="hepbFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.tbFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="tbFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.mmrx2Filename"/></th>
              <td align="left">
                <bean:write name="applicant" property="mmrx2Filename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.mmrFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="mmrFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.ivsEppFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="ivsEppFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="fitToWorkFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkExpiryDate"/></th>
              <td align="left">
              <bean:write name="applicant" property="fitToWorkExpiryDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkStatus"/></th>
              <td align="left">
<logic:equal name="applicant" property="fitToWorkStatus" value="1">
                <bean:message key="label.appliedFor"/>
</logic:equal>
<logic:equal name="applicant" property="fitToWorkStatus" value="2">
                <bean:message key="label.pending"/>
</logic:equal>
<logic:equal name="applicant" property="fitToWorkStatus" value="3">
                <bean:message key="label.received"/>
</logic:equal>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkIssuedBy"/></th>
              <td align="left">
<logic:equal name="applicant" property="fitToWorkIssuedBy" value="0">
                &nbsp;
</logic:equal>
<logic:greaterThan name="applicant" property="fitToWorkIssuedBy" value="0">
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
                <logic:equal name="applicant" property="drivingLicense" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="drivingLicense" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.drivingLicenseExpiryDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="drivingLicenseExpiryDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.degreeDetail"/></th>
              <td align="left">
              <bean:write name="applicant" property="degreeDetail"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.degree"/></th>
              <td align="left">
                <logic:equal name="applicant" property="degree" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="degree" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.englishTestCertificateFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="englishTestCertificateFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.languageCompetency"/></th>
              <td align="left">
<logic:equal name="applicant" property="languageCompetency" value="true">
                <bean:message key="label.yes"/>
</logic:equal>
<logic:notEqual name="applicant" property="languageCompetency" value="true">
                <bean:message key="label.no"/>
</logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.trainingFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="trainingFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.trainingExpiryDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="trainingExpiryDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.manualHandlingTraining"/></th>
              <td align="left">
                <logic:equal name="applicant" property="manualHandlingTraining" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="manualHandlingTraining" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.basicLifeSupportTraining"/></th>
              <td align="left">
                <logic:equal name="applicant" property="basicLifeSupportTraining" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="basicLifeSupportTraining" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.elearningTraining"/></th>
              <td align="left">
                <logic:equal name="applicant" property="elearningTraining" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="elearningTraining" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.povaTraining"/></th>
              <td align="left">
                <logic:equal name="applicant" property="povaTraining" value="true">
                  <bean:message key="label.yes"/>
                </logic:equal>
                <logic:notEqual name="applicant" property="povaTraining" value="true">
                  <bean:message key="label.no"/>
                </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.paediatricLifeSupportFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="paediatricLifeSupportFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.paediatricLifeSupportIssuedDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="paediatricLifeSupportIssuedDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.ahpRegistrationType"/></th>
              <td align="left">
                <logic:equal name="applicant" property="ahpRegistrationType" value="0">
                  <bean:message key="label.hcpc"/>
                </logic:equal>
                <logic:equal name="applicant" property="ahpRegistrationType" value="1">
                  <bean:message key="label.nmc"/>
                </logic:equal>
                <logic:equal name="applicant" property="ahpRegistrationType" value="2">
                  <bean:message key="label.hca"/>
                </logic:equal>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.registrationFilename"/></th>
              <td align="left">
                <bean:write name="applicant" property="registrationFilename"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.registrationExpiryDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="registrationExpiryDate" formatKey="format.mediumDateFormat"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.professionalReference"/></th>
              <td align="left">
                <bean:write name="applicant" property="registrationNumber"/>
              </td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.registrationLastCheckedDate"/></th>
              <td align="left">
                <bean:write name="applicant" property="registrationLastCheckedDate" formatKey="format.mediumDateFormat"/>
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
      </div>
    </td>
    <td align="center" valign="top" width="25%">
			<logic:empty name="applicant" property="photoFilename" >
			  <bean:message key="text.noPhotoAvailable"/>
			</logic:empty>
			<logic:notEmpty name="applicant" property="photoFilename" >
			  <bean:define id="photoFileUrl" name="applicant" property="photoFileUrl" type="java.lang.String" />
			  <html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
			</logic:notEmpty>
    </td>
  </tr>
</table>
          