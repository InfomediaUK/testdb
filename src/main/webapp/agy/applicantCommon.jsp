<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="applicantAction" name="applicantAction" value="new"/>
<bean:define id="unavailableDates" name="ApplicantFormAgy" property="unavailableDates" type="java.lang.String" />
<bean:define id="checklistFileUrl" name="ApplicantFormAgy" property="applicant.checklistFileUrl"/>
<html:hidden property="unavailableDates" styleId="datesId" />
<table cellpadding="0" cellspacing="0" width="100%" border="0">
  <tr>
    <td align="left" valign="top" width="75%">
			<div class="tabber">
			  <div class="tabbertab">
				  <h2>Registration</h2>
					<table class="simple" width="100%">
						<tr>
					    <th align="left" width="25%"><bean:message key="label.firstName"/></th>
					    <td align="left" width="35%"><html:text property="applicant.user.firstName" size="40" tabindex="1" /></td>
					    <th align="left" width="15%"><bean:message key="label.address"/></th>
					    <td align="left" width="25%"><html:text property="applicant.address.address1" size="30" tabindex="20" /></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.lastName"/></th>
					    <td align="left"><html:text property="applicant.user.lastName" size="40" tabindex="2" /></td>
					    <th align="left">&nbsp;</th>
					    <td align="left"><html:text property="applicant.address.address2" size="30" tabindex="21" /></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.nhsStaffName"/></th>
					    <td align="left"><html:text property="applicant.user.nhsStaffName" size="40" tabindex="2" /></td>
					    <th align="left">&nbsp;</th>
					    <td align="left"><html:text property="applicant.address.address3" size="30" tabindex="22" /></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
					    <td align="left"><html:text property="applicant.user.emailAddress" size="40" tabindex="3" /></td>
					    <th align="left">&nbsp;</th>
					    <td align="left"><html:text property="applicant.address.address4" size="30" tabindex="23" /></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.mobileNumber"/></th>
					    <td align="left"><html:text property="applicant.mobileNumber" tabindex="4" /></td>
					    <th align="left"><bean:message key="label.postalCode"/></th>
					    <td align="left"><html:text property="applicant.address.postalCode" size="30" tabindex="24" /></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.dateOfBirth"/></th>
					    <td align="left"><html:text property="dateOfBirthStr" tabindex="5" />&nbsp;<bean:message key="text.dateFormat"/></td>
					    <th align="left"><bean:message key="label.country"/></th>
					    <td align="left">
					      <mmj:countryList var="countryList" />
					      <html:select property="applicant.address.countryId" tabindex="25" >
					      <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
					        <html:options collection="countryList" labelProperty="name" property="countryId" />
					      </html:select>
              </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.gender"/></th>
					    <td align="left">
					      <html:radio property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlMale %>" styleId="genderMale" tabindex="6" />
					      <label for="genderMale">
					        <bean:message key="label.male" />
					      </label>
					      <html:radio property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlFemale %>" styleId="genderFemale" tabindex="7" />
					      <label for="genderFemale">
					        <bean:message key="label.female" />
					      </label>
					    </td>
					    <th align="left"><bean:message key="label.telephoneNumber"/></th>
					    <td align="left"><html:text property="applicant.telephoneNumber" size="30" tabindex="26" /></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.currentlyWorking"/></th>
					    <td align="left" colspan="3"><html:checkbox property="applicant.currentlyWorking" tabindex="7"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.discipline"/></th>
					    <td align="left" colspan="3"><html:text property="applicant.reference" size="50" maxlength="50" tabindex="9"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.arrivalInUKDate"/></th>
					    <td align="left" colspan="3"><html:text property="arrivalInCountryDateStr" tabindex="10" />&nbsp;<bean:message key="text.dateFormat" /></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.availabilityDate"/></th>
					    <td align="left" colspan="3"><html:text property="availabilityDateStr" tabindex="11" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.preferredLocation"/></th>
					    <td align="left" colspan="3">
					      <mmj:geographicalRegionList var="geographicalRegionList" />
					      <html:select property="applicant.geographicalRegionId" tabindex="13">
						      <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
					        <html:options collection="geographicalRegionList" labelProperty="name" property="geographicalRegionId" />
					      </html:select>
              </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.disciplineCategory"/></th>
					    <td align="left" colspan="3">
					      <mmj:disciplineCategoryList var="disciplineCategoryList" />
					      <html:select property="applicant.disciplineCategoryId" tabindex="14">
						      <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
					        <html:options collection="disciplineCategoryList" labelProperty="nameWithCode" property="disciplineCategoryId" />
					      </html:select>
					    </td>
					  </tr>
            <tr>
              <th align="left" class="label"><bean:message key="label.clientGroup"/></th>
              <td align="left" colspan="3">
                <html:select name="ApplicantFormAgy" property="applicant.clientGroup" tabindex="33">
                  <html:option value="0">-----</html:option>
                  <html:option value="1"><bean:message key="label.adults"/></html:option>
                  <html:option value="2"><bean:message key="label.paeds"/></html:option>
                </html:select>
              </td>
            </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.areaOfSpeciality"/></th>
					    <td align="left">
					      <mmj:areaOfSpecialityList var="areaOfSpecialityList" />
					      <html:select property="applicant.areaOfSpecialityId" tabindex="12">
						      <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
					        <html:options collection="areaOfSpecialityList" labelProperty="name" property="areaOfSpecialityId" />
					      </html:select>
              </td>
              <th align="left" class="label"><bean:message key="label.limitedCompany"/></th>
              <td align="left"><html:text property="applicant.limitedCompanyName" size="30" maxlength="50" tabindex="15"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.areaOfSpeciality"/>&nbsp;2</th>
					    <td align="left">
					      <mmj:areaOfSpecialityList var="areaOfSpecialityList" />
					      <html:select property="applicant.areaOfSpecialityId2" tabindex="13">
						      <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
					        <html:options collection="areaOfSpecialityList" labelProperty="name" property="areaOfSpecialityId" />
					      </html:select>
              </td>
              <th align="left" class="label"><bean:message key="label.vatChargeable"/></th>
              <td align="left"><html:checkbox property="applicant.vatChargeable" tabindex="15"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.interviewDate"/></th>
					    <td align="left"><html:text property="interviewDateStr" tabindex="16"/>&nbsp;<bean:message key="text.dateFormat"/></td>
					    <th align="left" class="label"><bean:message key="label.bankName"/></th>
              <td align="left"><html:text property="applicant.bankName" size="30" maxlength="40" tabindex="16"/></td>
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
              <td align="left"><html:text property="applicant.bankSortCode" size="30" maxlength="10" tabindex="16"/></td>
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
              <td align="left"><html:text property="applicant.bankAccountName" size="30" maxlength="40" tabindex="17"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.hideMoney"/></th>
					    <td align="left"><html:checkbox property="applicant.hideMoney" tabindex="18"/></td>
					    <th align="left" class="label"><bean:message key="label.bankAccountNumber"/></th>
              <td align="left"><html:text property="applicant.bankAccountNumber" size="30" maxlength="20" tabindex="17"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.canToggleHideMoney"/></th>
					    <td align="left" colspan="3"><html:checkbox property="applicant.canToggleHideMoney" tabindex="19"/></td>
					  </tr>
					</table>
			  </div>
			  <div class="tabbertab">
				  <h2>Current Documents</h2>
					<table class="simple" width="100%">
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.cvFile"/></th>
					    <td align="left"><html:file property="cvFormFile" size="30" tabindex="27" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.cvFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.crbFile"/></th>
					    <td align="left"><html:file property="crbFormFile" size="30" tabindex="28" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.crbFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.crbExpiryDate"/></th>
					    <td align="left"><html:text property="crbExpiryDateStr" tabindex="29" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.crbIssueDate"/></th>
					    <td align="left"><html:text property="crbIssueDateStr" tabindex="30" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.crbDisclosureNumber"/></th>
              <td align="left"><html:text property="applicant.crbDisclosureNumber" maxlength="20" tabindex="30" /></td>
            </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.registeredWithDbsDate"/></th>
					    <td align="left"><html:text property="registeredWithDbsDateStr" tabindex="30" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.dbsRenewalDate"/></th>
					    <td align="left"><html:text property="dbsRenewalDateStr" tabindex="30" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.dbsFile"/></th>
              <td align="left"><html:file property="dbsFormFile" size="30" tabindex="67" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.dbsFilename"/></td>
            </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference1File"/></th>
					    <td align="left"><html:file property="reference1FormFile" size="30" tabindex="31" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.reference1Filename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference1Date"/></th>
					    <td align="left"><html:text property="reference1DateStr" tabindex="32" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference1Status"/></th>
					    <td align="left">
					      <html:select name="ApplicantFormAgy" property="applicant.reference1Status" tabindex="33">
					        <html:option value="0">-----</html:option>
					        <html:option value="1"><bean:message key="label.appliedFor"/></html:option>
					        <html:option value="2"><bean:message key="label.pending"/></html:option>
					        <html:option value="3"><bean:message key="label.received"/></html:option>
					      </html:select>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.reference1"/></th>
					    <td align="left"><html:text property="applicant.reference1" size="30" maxlength="100" tabindex="34" /></td>
					  </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference2File"/></th>
              <td align="left"><html:file property="reference2FormFile" size="30" tabindex="35" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.reference2Filename"/></td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference2Date"/></th>
              <td align="left"><html:text property="reference2DateStr" tabindex="36" />&nbsp;<bean:message key="text.dateFormat"/></td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference2Status"/></th>
              <td align="left">
                <html:select name="ApplicantFormAgy" property="applicant.reference2Status" tabindex="37">
                  <html:option value="0">-----</html:option>
                  <html:option value="1"><bean:message key="label.appliedFor"/></html:option>
                  <html:option value="2"><bean:message key="label.pending"/></html:option>
                  <html:option value="3"><bean:message key="label.received"/></html:option>
                </html:select>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.reference2"/></th>
              <td align="left"><html:text property="applicant.reference2" size="30" maxlength="100" tabindex="38" /></td>
            </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.performanceEvaluationDate"/></th>
					    <td align="left"><html:text property="performanceEvaluationDateStr" tabindex="39" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.performanceEvaluation"/></th>
					    <td align="left"><html:checkbox property="applicant.performanceEvaluation" tabindex="40" /></td>
					  </tr>
					  <%/* NEW --> */%>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.assessment12WeekDate"/></th>
					    <td align="left"><html:text property="assessment12WeekDateStr" tabindex="41" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.assessment12Week"/></th>
					    <td align="left"><html:checkbox property="applicant.assessment12Week" tabindex="42" /></td>
					  </tr>
					</table>
			  </div>
			  <div class="tabbertab">
				  <h2>ID Documents</h2>
					<table class="simple" width="100%">
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.photoFile"/></th>
					    <td align="left"><html:file property="document" size="30" tabindex="44" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.photoFilename"/></td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.requiresVisa"/></th>
					    <td align="left">
					      <html:checkbox property="applicant.requiresVisa" tabindex="44" />
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label"><bean:message key="label.visaType"/></th>
					    <td align="left">
                <mmj:visaTypeList var="visaTypeList" />
                <html:select property="applicant.visaType" tabindex="44">
                  <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
                  <html:options collection="visaTypeList" labelProperty="name" property="visaTypeId" />
                </html:select>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.visaExpiryDate"/></th>
					    <td align="left"><html:text property="visaExpiryDateStr" tabindex="43" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.birthCertificateFile"/></th>
					    <td align="left"><html:file property="birthCertificateFormFile" size="30" tabindex="44" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.birthCertificateFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.birthCertificate"/></th>
					    <td align="left"><html:checkbox property="applicant.birthCertificate" tabindex="45" /></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress1File"/></th>
					    <td align="left"><html:file property="proofOfAddress1FormFile" size="30" tabindex="46" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.proofOfAddress1Filename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress1"/></th>
					    <td align="left"><html:checkbox property="applicant.proofOfAddress1" tabindex="47" /></td>
					  </tr>

					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress2File"/></th>
					    <td align="left"><html:file property="proofOfAddress2FormFile" size="30" tabindex="46" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.proofOfAddress2Filename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.proofOfAddress2"/></th>
					    <td align="left"><html:checkbox property="applicant.proofOfAddress2" tabindex="47" /></td>
					  </tr>

					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.passportFile"/></th>
					    <td align="left"><html:file property="passportFormFile" size="30" tabindex="49" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.passportFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.passportExpiryDate"/></th>
					    <td align="left"><html:text property="passportExpiryDateStr" tabindex="50" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.passportNumber"/></th>
					    <td align="left"><html:text property="applicant.passportNumber" size="30" maxlength="50" tabindex="51" /></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.passportType"/></th>
					    <td align="left">
                <mmj:passportTypeList var="passportTypeList" />
                <html:select property="applicant.passportType" tabindex="51">
                  <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
                  <html:options collection="passportTypeList" labelProperty="name" property="passportTypeId" />
                </html:select>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.niNumberStatus"/></th>
					    <td align="left">
					      <html:select name="ApplicantFormAgy" property="applicant.niNumberStatus" tabindex="48">
					        <html:option value="0">-----</html:option>
					        <html:option value="1"><bean:message key="label.appliedFor"/></html:option>
					        <html:option value="2"><bean:message key="label.pending"/></html:option>
					        <html:option value="3"><bean:message key="label.received"/></html:option>
					      </html:select>
					    </td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.niNumber"/></th>
					    <td align="left"><html:text property="applicant.niNumber" tabindex="52" /></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.overseasPoliceClearance"/></th>
					    <td align="left"><html:checkbox property="applicant.overseasPoliceClearance" tabindex="53" /></td>
					  </tr>
					</table>
				</div>
				<div class="tabbertab">
					<h2>Health Documents</h2>
					<table class="simple" width="100%">
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.varicellaFile"/></th>
					    <td align="left"><html:file property="varicellaFormFile" size="30" tabindex="54" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.varicellaFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.hepbFile"/></th>
					    <td align="left"><html:file property="hepbFormFile" size="30" tabindex="55" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.hepbFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.tbFile"/></th>
					    <td align="left"><html:file property="tbFormFile" size="30" tabindex="56" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.tbFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.mmrx2File"/></th>
					    <td align="left"><html:file property="mmrx2FormFile" size="30" tabindex="57" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.mmrx2Filename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.mmrFile"/></th>
					    <td align="left"><html:file property="mmrFormFile" size="30" tabindex="58" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.mmrFilename"/></td>
					  </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.ivsEppFile"/></th>
              <td align="left"><html:file property="ivsEppFormFile" size="30" tabindex="58" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.ivsEppFilename"/></td>
            </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkFile"/></th>
					    <td align="left"><html:file property="fitToWorkFormFile" size="30" tabindex="59" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.fitToWorkFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkExpiryDate"/></th>
					    <td align="left"><html:text property="fitToWorkExpiryDateStr" tabindex="60" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkStatus"/></th>
              <td align="left">
                <html:select name="ApplicantFormAgy" property="applicant.fitToWorkStatus" tabindex="33">
                  <html:option value="0">-----</html:option>
                  <html:option value="1"><bean:message key="label.appliedFor"/></html:option>
                  <html:option value="2"><bean:message key="label.pending"/></html:option>
                  <html:option value="3"><bean:message key="label.received"/></html:option>
                  <html:option value="4"><bean:message key="label.temporary"/></html:option>
                </html:select>
              </td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.fitToWorkIssuedBy"/></th>
              <td align="left">
                <html:select name="ApplicantFormAgy" property="applicant.fitToWorkIssuedBy" tabindex="33">
                  <html:option value="0">-----</html:option>
                  <html:option value="1"><bean:message key="label.fitToWorkIssuedBy1"/></html:option>
                </html:select>
              </td>
            </tr>
					</table>
			  </div>
		    <div class="tabbertab">
				  <h2>Certificates</h2>
					<table class="simple" width="100%">
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.drivingLicense"/></th>
              <td align="left"><html:checkbox property="applicant.drivingLicense" tabindex="61" /></td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.drivingLicenseExpiryDate"/></th>
              <td align="left"><html:text property="drivingLicenseExpiryDateStr" tabindex="62" />&nbsp;<bean:message key="text.dateFormat"/></td>
            </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.degreeDetail"/></th>
					    <td align="left"><html:text property="applicant.degreeDetail" size="30" maxlength="100" tabindex="63"/></td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.degree"/></th>
					    <td align="left"><html:checkbox property="applicant.degree" tabindex="64" /></td>
					  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.englishTestCertificateFile"/></th>
              <td align="left"><html:file property="englishTestCertificateFormFile" size="30" tabindex="65" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.englishTestCertificateFilename"/></td>
            </tr>
					  <tr>
					    <th width="25%" align="left"class="label"><bean:message key="label.languageCompetency"/></th>
					    <td align="left">
					      <html:checkbox property="applicant.languageCompetency" tabindex="44" />
					    </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.trainingFile"/></th>
					    <td align="left"><html:file property="trainingFormFile" size="30" tabindex="65" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.trainingFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.trainingExpiryDate"/></th>
					    <td align="left"><html:text property="trainingExpiryDateStr" tabindex="66" />&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.manualHandlingTraining"/></th>
					    <td align="left"><html:checkbox property="applicant.manualHandlingTraining" tabindex="66" /></td>
					  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.basicLifeSupportTraining"/></th>
					    <td align="left"><html:checkbox property="applicant.basicLifeSupportTraining" tabindex="66" /></td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.elearningTraining"/></th>
					    <td align="left"><html:checkbox property="applicant.elearningTraining" tabindex="66" /></td>
            </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.povaTraining"/></th>
					    <td align="left"><html:checkbox property="applicant.povaTraining" tabindex="66" /></td>
            </tr>
            <tr>
              <th width="25%" align="left"class="label"><bean:message key="label.paediatricLifeSupportFile"/></th>
              <td align="left"><html:file property="paediatricLifeSupportFormFile" size="30" tabindex="66" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.paediatricLifeSupportFilename"/></td>
            </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.paediatricLifeSupportIssuedDate"/></th>
					    <td align="left"><html:text property="paediatricLifeSupportIssuedDateStr" tabindex="66"/>&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
      		  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.hpc"/></th>
					    <th  align="left" class="label">(HCPC/NMC/HCA)</th>
					  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.ahpRegistrationType"/></th>
              <td align="left">
                <html:select name="ApplicantFormAgy" property="applicant.ahpRegistrationType" tabindex="66">
                  <html:option value="0"><bean:message key="label.full"/></html:option>
                  <html:option value="1"><bean:message key="label.temp"/></html:option>
                  <html:option value="2"><bean:message key="label.hca"/></html:option>
                </html:select>
              </td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.hpcFile"/></th>
					    <td align="left"><html:file property="hpcFormFile" size="30" tabindex="67" />&nbsp;<bean:write name="ApplicantFormAgy" property="applicant.hpcFilename"/></td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.hpcExpiryDate"/></th>
					    <td align="left"><html:text property="hpcExpiryDateStr" tabindex="68"/>&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.professionalReference"/></th>
					    <td align="left"><html:text property="applicant.hpcNumber" size="30" maxlength="50" tabindex="69" /></td>
					  </tr>
					  <tr>
					    <th width="25%"  align="left" class="label"><bean:message key="label.hpcLastCheckedDate"/></th>
					    <td align="left"><html:text property="hpcLastCheckedDateStr" tabindex="68"/>&nbsp;<bean:message key="text.dateFormat"/></td>
					  </tr>
            <tr>
              <th width="25%"  align="left" class="label"><bean:message key="label.hpcAlertNotification"/></th>
              <td align="left">
                <html:select name="ApplicantFormAgy" property="applicant.hpcAlertNotification" tabindex="69">
                  <html:option value="0"><bean:message key="label.hpcAlertNotification0"/></html:option>
                  <html:option value="1"><bean:message key="label.hpcAlertNotification1"/></html:option>
                  <html:option value="2"><bean:message key="label.hpcAlertNotification2"/></html:option>
                  <html:option value="3"><bean:message key="label.hpcAlertNotification3"/></html:option>
                  <html:option value="4"><bean:message key="label.hpcAlertNotification4"/></html:option>
                  <html:option value="5"><bean:message key="label.hpcAlertNotification5"/></html:option>
                  <html:option value="6"><bean:message key="label.hpcAlertNotification6"/></html:option>
                  <html:option value="7"><bean:message key="label.hpcAlertNotification7"/></html:option>
                  <html:option value="8"><bean:message key="label.hpcAlertNotification8"/></html:option>
                </html:select>
              </td>
					  </tr>
			  	</table>
			  </div>
		    <div class="tabbertab">
				  <h2>Notes</h2>
					<table class="simple" width="100%">
					  <tr>
					    <td align="left">
					      <html:textarea name="ApplicantFormAgy" property="notes" cols="65" rows="15"/>
					    </td>
					  </tr>
					</table>
			  </div>		
		    <div class="tabbertab">
				  <h2>Checklist</h2>
					<table>
					  <tr>
					    <td align="left">
<logic:equal name="applicantAction" value="edit">
	<bean:define id="applicantId" name="ApplicantFormAgy" property="applicant.applicantId"/>
	<bean:define id="weekToShow" name="ApplicantFormAgy" property="weekToShow"/>
	<%
	String createAgencyWorkerChecklistFileUrl = request.getContextPath() + "/agy/applicantChecklist.do?applicant.applicantId=" + applicantId + "&weekToShow=" + weekToShow;
	%>
	<logic:equal name="ApplicantFormAgy" property="applicant.compliant" value="true">
	              <a href="<%= createAgencyWorkerChecklistFileUrl %>">Create</a>
 	</logic:equal>
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
                &nbsp;
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
<logic:equal name="applicantAction" value="edit">
			<logic:empty name="ApplicantFormAgy" property="applicant.photoFilename" >
			<bean:message key="text.noPhotoAvailable"/>
			</logic:empty>
</logic:equal>
			<logic:notEmpty name="ApplicantFormAgy" property="applicant.photoFilename" >
			<bean:define id="photoFileUrl" name="ApplicantFormAgy" property="applicant.photoFileUrl" type="java.lang.String" />
			<html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
			</logic:notEmpty>
	  </td>
  </tr>
</table>
<html:hidden property="applicant.varicellaFilename" />
<html:hidden property="applicant.hepbFilename" />
<html:hidden property="applicant.tbFilename" />
<html:hidden property="applicant.mmrx2Filename" />
<html:hidden property="applicant.mmrFilename" />
<html:hidden property="applicant.reference2Filename" />
<html:hidden property="applicant.reference1Filename" />
<html:hidden property="applicant.photoFilename" />
<html:hidden property="applicant.cvFilename" />
<html:hidden property="applicant.birthCertificateFilename" />
<html:hidden property="applicant.proofOfAddress1Filename" />
<html:hidden property="applicant.proofOfAddress2Filename" />
<html:hidden property="applicant.fitToWorkFilename" />
<html:hidden property="applicant.passportFilename" />
<html:hidden property="applicant.trainingFilename" />
<html:hidden property="applicant.crbFilename" />
<html:hidden property="applicant.hpcFilename" />
<html:hidden property="applicant.dbsFilename" />
<html:hidden property="applicant.hasBeenUnarchived" />
<html:hidden property="applicant.compliant" />
<html:hidden property="applicant.recentlyCompliant" />
<html:hidden property="applicant.ivsEppFilename" />
<html:hidden property="applicant.paediatricLifeSupportFilename" />
<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

// load current selections ...

var MA = [
<%
  // get date s request parameter (comma separated dates in the form yyyy/mm/dd)
  java.util.StringTokenizer datesParser = new java.util.StringTokenizer(unavailableDates, ",");
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
.disabled { 
/*	text-decoration: line-through; */
	color:#000;
}
.ro { 
	color:#f00;
}
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

	dateStatusFunc : function(date, y, m, d) {
					           if (isDisabled(date)) {
					               return "disabled";
					           }
		               },

  disableFunc    : isDisabled

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










  