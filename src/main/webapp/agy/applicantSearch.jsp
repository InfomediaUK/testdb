<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.helmet.application.Constants" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="embedded" name="embedded" value="false"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
	<td align="left" valign="middle" class="title">
      <bean:message key="title.applicantSearch"/>
	</td>
  </tr>
</table>
<html:errors/>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<html:form action="/applicantSearchProcess.do" focus="lastName">
  <tr>
    <td width="80%">

		<table class="simple" width="100%" border="1">
		  <tr>
		    <th align="left" class="label" width="30%">&nbsp</th>
		    <th align="left" class="label" width="2%"><bean:message key="label.and"/></th>
		    <th align="left" class="label" width="2%"><bean:message key="label.or"/></th>
		    <th align="left" class="label" width="66%">&nbsp</th>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.lastName"/></th>
		    <td>
		      <html:radio property="lastNameOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="lastNameOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:text property="lastName"/>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.performanceEvaluation"/></th>
		    <td>
		      <html:radio property="performanceEvaluationOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="performanceEvaluationOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:select property="performanceEvaluation" tabindex="1">
						<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
						<html:option value="true"><bean:message key="label.yes"/></html:option>
						<html:option value="false"><bean:message key="label.no"/></html:option>
		      </html:select>
				</td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.assessment12Week"/></th>
		    <td>
		      <html:radio property="assessment12WeekOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="assessment12WeekOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:select property="assessment12Week" tabindex="1">
						<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
						<html:option value="true"><bean:message key="label.yes"/></html:option>
						<html:option value="false"><bean:message key="label.no"/></html:option>
		      </html:select>
				</td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.interviewed"/></th>
		    <td>
		      <html:radio property="interviewedOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="interviewedOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:select property="interviewed" tabindex="1">
						<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
						<html:option value="true"><bean:message key="label.yes"/></html:option>
						<html:option value="false"><bean:message key="label.no"/></html:option>
		      </html:select>
				</td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.preferredLocation"/></th>
		    <td>
		      <html:radio property="geographicalRegionIdOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="geographicalRegionIdOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <mmj:geographicalRegionList var="geographicalRegionList" />
		      <html:select property="geographicalRegionId">
			    <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
		        <html:options collection="geographicalRegionList" labelProperty="nameWithCode" property="geographicalRegionId" />
		      </html:select>
		    </td>
		  </tr>

		  <tr>
		    <th align="left" class="label"><bean:message key="label.idDocument"/></th>
		    <td>
		      <html:radio property="idDocumentIdOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="idDocumentIdOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <mmj:idDocumentList var="idDocumentList" />
		      <html:select property="idDocumentId">
			    <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
		        <html:options collection="idDocumentList" labelProperty="nameWithCode" property="idDocumentId" />
		      </html:select>
		    </td>
		  </tr>

		  <tr>
		    <th align="left" class="label"><bean:message key="label.disciplineCategory"/></th>
		    <td>
		      <html:radio property="disciplineCategoryIdOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="disciplineCategoryIdOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <mmj:disciplineCategoryList var="disciplineCategoryList" />
		      <html:select property="disciplineCategoryId">
			    <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
		        <html:options collection="disciplineCategoryList" labelProperty="nameWithCode" property="disciplineCategoryId" />
		      </html:select>
		    </td>
		  </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.clientGroup"/></th>
        <td>
          <html:radio property="clientGroupOperator" value="<%= Constants.AND %>"/>
        </td>
        <td>
          <html:radio property="clientGroupOperator" value="<%= Constants.OR %>"/>
        </td>
        <td align="left">
          <html:select property="clientGroup" tabindex="33">
            <html:option value="0">-----</html:option>
            <html:option value="1"><bean:message key="label.adults"/></html:option>
            <html:option value="2"><bean:message key="label.paeds"/></html:option>
          </html:select>
        </td>
      </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.areaOfSpeciality"/></th>
		    <td>
		      <html:radio property="areaOfSpecialityIdOperator" value="<%= Constants.AND %>" />
		    </td>
		    <td>
		      <html:radio property="areaOfSpecialityIdOperator" value="<%= Constants.OR %>" disabled="true"/>
		    </td>
		    <td align="left">
		      <mmj:areaOfSpecialityList var="areaOfSpecialityList" />
		      <html:select property="areaOfSpecialityId">
			    <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
		        <html:options collection="areaOfSpecialityList" labelProperty="name" property="areaOfSpecialityId" />
		      </html:select>
		      &nbsp;
          <html:radio property="areaOfSpecialityId2Operator" value="<%= Constants.AND %>"/><bean:message key="label.and"/>&nbsp;
          <html:radio property="areaOfSpecialityId2Operator" value="<%= Constants.OR %>"/><bean:message key="label.or"/>&nbsp;
          <mmj:areaOfSpecialityList var="areaOfSpecialityList" />
		      &nbsp;
          <html:select property="areaOfSpecialityId2">
          <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
            <html:options collection="areaOfSpecialityList" labelProperty="name" property="areaOfSpecialityId" />
          </html:select>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.niNumberStatus"/></th>
		    <td>
		      <html:radio property="niNumberStatusOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="niNumberStatusOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:select property="niNumberStatus">
		        <html:option value="0">-----</html:option>
		        <html:option value="1"><bean:message key="label.appliedFor"/></html:option>
		        <html:option value="2"><bean:message key="label.pending"/></html:option>
		        <html:option value="3"><bean:message key="label.received"/></html:option>
		      </html:select>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.availableOnOrBefore"/></th>
		    <td>
		      <html:radio property="availabilityDateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="availabilityDateOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:text property="availabilityDate"/>&nbsp;<bean:message key="text.dateFormat"/>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.visaExpiresOnOrBefore"/></th>
		    <td>
		      <html:radio property="visaExpiryDateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="visaExpiryDateOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:text property="visaExpiryDate"/>&nbsp;<bean:message key="text.dateFormat"/>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.idDocumentExpiresOnOrBefore"/></th>
		    <td>
		      <html:radio property="idDocumentExpiryDateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="idDocumentExpiryDateOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:text property="idDocumentExpiryDate"/>&nbsp;<bean:message key="text.dateFormat"/>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.crbExpiresOnOrBefore"/></th>
		    <td>
		      <html:radio property="crbExpiryDateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="crbExpiryDateOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:text property="crbExpiryDate"/>&nbsp;<bean:message key="text.dateFormat"/>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.hpcExpiresOnOrBefore"/></th>
		    <td>
		      <html:radio property="hpcExpiryDateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hpcExpiryDateOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:text property="hpcExpiryDate"/>&nbsp;<bean:message key="text.dateFormat"/>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.trainingExpiresOnOrBefore"/></th>
		    <td>
		      <html:radio property="trainingExpiryDateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="trainingExpiryDateOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:text property="trainingExpiryDate"/>&nbsp;<bean:message key="text.dateFormat"/>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.fitToWorkExpiresOnOrBefore"/></th>
		    <td>
		      <html:radio property="fitToWorkExpiryDateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="fitToWorkExpiryDateOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:text property="fitToWorkExpiryDate"/>&nbsp;<bean:message key="text.dateFormat"/>
		    </td>
		  </tr>
		  <tr>
		    <th align="left" class="label"><bean:message key="label.hasVaricellaDocument"/></th>
		    <td>
		      <html:radio property="hasVaricellaDocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasVaricellaDocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
		    <td align="left">
		      <html:select property="hasVaricellaDocument" tabindex="1">
						<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
						<html:option value="true"><bean:message key="label.yes"/></html:option>
						<html:option value="false"><bean:message key="label.no"/></html:option>
		      </html:select>
				</td>
		  </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasHepbDocument"/></th>
		    <td>
		      <html:radio property="hasHepbDocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasHepbDocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasHepbDocument" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasTuberculosisDocument"/></th>
		    <td>
		      <html:radio property="hasTuberculosisDocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasTuberculosisDocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasTuberculosisDocument" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasMMRX2Document"/></th>
		    <td>
		      <html:radio property="hasMMRX2DocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasMMRX2DocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasMMRX2Document" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasMMRDocument"/></th>
		    <td>
		      <html:radio property="hasMMRDocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasMMRDocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasMMRDocument" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasFitToWorkDocument"/></th>
		    <td>
		      <html:radio property="hasFitToWorkDocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasFitToWorkDocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasFitToWorkDocument" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasCRBDocument"/></th>
		    <td>
		      <html:radio property="hasCRBDocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasCRBDocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasCRBDocument" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasHPCDocument"/></th>
		    <td>
		      <html:radio property="hasHPCDocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasHPCDocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasHPCDocument" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasTrainingCertificate"/></th>
		    <td>
		      <html:radio property="hasTrainingCertificateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasTrainingCertificateOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasTrainingCertificate" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasIdDocument"/></th>
		    <td>
		      <html:radio property="hasIdDocumentOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasIdDocumentOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasIdDocument" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasProofOfAddress1"/></th>
		    <td>
		      <html:radio property="hasProofOfAddress1Operator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasProofOfAddress1Operator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasProofOfAddress1" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasProofOfAddress2"/></th>
		    <td>
		      <html:radio property="hasProofOfAddress2Operator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasProofOfAddress2Operator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasProofOfAddress2" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasBirthCertificate"/></th>
		    <td>
		      <html:radio property="hasBirthCertificateOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasBirthCertificateOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasBirthCertificate" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasOverseasPoliceClearance"/></th>
		    <td>
		      <html:radio property="hasOverseasPoliceClearanceOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasOverseasPoliceClearanceOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasOverseasPoliceClearance" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.hasDegree"/></th>
		    <td>
		      <html:radio property="hasDegreeOperator" value="<%= Constants.AND %>"/>
		    </td>
		    <td>
		      <html:radio property="hasDegreeOperator" value="<%= Constants.OR %>"/>
		    </td>
        <td align="left">
          <html:select property="hasDegree" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.isArchived"/></th>
        <td>
          <html:radio property="isArchivedOperator" value="<%= Constants.AND %>"/>
        </td>
        <td>
          <html:radio property="isArchivedOperator" value="<%= Constants.OR %>"/>
        </td>
        <td align="left">
          <html:select property="isArchived" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th align="left" class="label"><bean:message key="label.isCompliant"/></th>
        <td>
          <html:radio property="isCompliantOperator" value="<%= Constants.AND %>"/>
        </td>
        <td>
          <html:radio property="isCompliantOperator" value="<%= Constants.OR %>"/>
        </td>
        <td align="left">
          <html:select property="isCompliant" tabindex="1">
            <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
            <html:option value="true"><bean:message key="label.yes"/></html:option>
            <html:option value="false"><bean:message key="label.no"/></html:option>
          </html:select>
        </td>
      </tr>
		</table>

    </td>
    <td width="20%" valign="center" align="center">
      <html:submit styleClass="titleButton" tabindex="2"><bean:message key="button.search"/></html:submit>
      <br/>
      <br/>
      <html:reset styleClass="titleButton" tabindex="2"><bean:message key="button.reset"/></html:reset>
    </td>
  </tr>
  <logic:equal name="embedded" value="false">
  <html:hidden property="emailActionId" value="999999"/>
  </logic:equal>
</html:form>
</table>
