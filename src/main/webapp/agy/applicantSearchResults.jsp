<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<script type="text/javascript">
var checked=false;
var frmname='';
function checkedAll(frmname)
{
  var searchResultsForm = document.getElementById(frmname);
  if (checked == false)
  {
    checked = true;
  }
  else
  {
    checked = false;
  }
  for (var i =0; i < searchResultsForm.elements.length; i++)
  {
    searchResultsForm.elements[i].checked=checked;
  }
}
</script>

<div class="tabber">
  <div class="tabbertab">
	  <h2>Search Criteria</h2>
    <jsp:include page="applicantSearch.jsp" flush="true">
      <jsp:param name="embedded" value="true"/>
    </jsp:include>
  </div>
  <div class="tabbertab tabbertabdefault">
	  <h2>Search Results</h2>
    <html:form action="/applicantEmailProcess.do" styleId="ApplicantSearchResults" onsubmit="return singleSubmit();">
		<table cellpadding="0" cellspacing="0" width="50%" height="30">
		  <tr>
			  <td align="left" valign="middle" class="title">
		      <bean:message key="title.applicantSearchResults"/>&nbsp;
		    </td>
        <td>
          <mmj:emailActionList var="emailActionList" />
          <html:select property="emailActionId">
            <%--html:option value="0"><bean:message key="label.pleaseSelect"/></html:option--%>
            <html:options collection="emailActionList" labelProperty="name" property="emailActionId" />
          </html:select>
        </td>
<mmj-agy:hasAccess forward="applicantEdit">
        <td align="right" valign="middle" width="75">
          <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
        </td>
</mmj-agy:hasAccess>
		  </tr>
		</table>
		<logic:notPresent name="ApplicantSearchFormAgy" property="list">
		  <bean:message key="text.noDetails"/>
		</logic:notPresent>
		
		<logic:present name="ApplicantSearchFormAgy" property="list">
			<logic:empty name="ApplicantSearchFormAgy" property="list">
			  <bean:message key="text.noDetails"/>
			</logic:empty>
			<logic:notEmpty name="ApplicantSearchFormAgy" property="list">
				<table class="simple" width="100%" border="1">
				  <thead>
				  <tr>
				    <th align="center" width="2%">
				      <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantSearchResults');"/>
            </th>
				    <th align="left" width="18%">Applicant</th>
				    <th align="left" width="15%">Exipres On</th>
				    <th align="left" width="20%">ID Documents</th>
				    <th align="left" width="17%">Health Documents</th>
				    <th align="left" width="28%">Certificates</th>
				  </tr>
				  </thead>
				  <logic:iterate id="applicant" name="ApplicantSearchFormAgy" property="list" indexId="applicantIndex" type="com.helmet.bean.Applicant">
				  <% String applicantCheckBox = "applicantId"; %>
				  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
				    <td align="left" valign="top"><%-- Checkbox --%>
				      <input type="checkbox" name="<%= applicantCheckBox %>" value="<%= applicant.getApplicantId() %>">    
				    </td>
				    <td align="left" valign="top"><%-- Applicant --%> 
				      <table cellpadding="0" cellspacing="0" width="100%">
				        <tr>
				          <td colspan="2">   
				    <mmj-agy:hasAccess forward="applicantView">
				      <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
				        <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
				      </html:link>
				    </mmj-agy:hasAccess>
				    <mmj-agy:hasNoAccess forward="applicantView">
				        <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
				    </mmj-agy:hasNoAccess>
				          </td>
				        </tr>
				        <tr>
				          <td colspan="2">   
      				      <bean:write name="applicant" property="user.emailAddress"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th width="30%">   
      				      Contact
				          </th>
				          <td width="70%">
      				      <bean:write name="applicant" property="telephoneNumber"/><br />
				          </td>
				        </tr>
				        <tr>
				          <th>   
      				      Mobile
				          </th>
				          <td>
				            <bean:write name="applicant" property="mobileNumber"/><br />
				          </td>
				        </tr>
				        <tr>
				          <th>   
      				      Interview
				          </th>
				          <td>
                    <bean:write name="applicant" property="interviewDate" formatKey="format.mediumDateFormat"/><br />
				          </td>
				        </tr>
				        <tr>
				          <th>   
      				      Available
				          </th>
				          <td>
                    <bean:write name="applicant" property="availabilityDate" formatKey="format.mediumDateFormat"/><br />
				          </td>
				        </tr>
				      </table>
				    </td>
				    <td align="left" valign="top"><%-- Expires On --%>
				      <table cellpadding="0" cellspacing="0" width="100%">
				        <tr>
				          <th  width="50%">
				            Driving License
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="drivingLicenseExpiryDate" formatKey="format.mediumDateFormat"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            Visa
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="visaExpiryDate" formatKey="format.mediumDateFormat"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th>
				            Passport
				          </th>
				          <td>
      				      <bean:write name="applicant" property="passportExpiryDate" formatKey="format.mediumDateFormat"/>&nbsp; 
				          </td>
				        </tr>
				        <tr>
				          <th>
				            CRB
				          </th>
				          <td>
				            <bean:write name="applicant" property="crbExpiryDate" formatKey="format.mediumDateFormat"/>&nbsp; 
				          </td>
				        </tr>
				        <tr>
				          <th>
				            HPC
				          </th>
				          <td>
  				          <bean:write name="applicant" property="hpcExpiryDate" formatKey="format.mediumDateFormat"/>&nbsp; 
				          </td>
				        </tr>
				        <tr>
				          <th>
				            Fit to Work
				          </th>
				          <td>
      				      <bean:write name="applicant" property="fitToWorkExpiryDate" formatKey="format.mediumDateFormat"/>&nbsp; 
				          </td>
				        </tr>
				        <tr>
				          <th>
				            Training
				          </th>
				          <td>
				            <bean:write name="applicant" property="trainingExpiryDate" formatKey="format.mediumDateFormat"/>&nbsp; 
				          </td>
				        </tr>
				      </table>
				    </td>   
				    <td align="left" valign="top"><%-- ID Documents --%>
				      <table cellpadding="0" cellspacing="0" width="100%">
				        <tr>
				          <th  width="50%">
				            Photograph
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="photoFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            Birth Certificate
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="birthCertificateFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            Proof of Address
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="proofOfAddressFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            Passport
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="passportFilename"/>&nbsp;
				          </td>
				        </tr>
				      </table>
				    </td>
				    <td align="left" valign="top"><%-- Health Documents --%>
				      <table cellpadding="0" cellspacing="0" width="100%">
				        <tr>
				          <th  width="50%">
				            Varicella
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="varicellaFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            Hep B
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="hepbFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            TB
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="tbFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            MMRX2
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="mmrx2Filename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            MMR
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="mmrFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="50%">
				            Fit to Work
				          </th>
				          <td  width="50%">
      				      <bean:write name="applicant" property="fitToWorkFilename"/>&nbsp;
				          </td>
				        </tr>
				      </table>
				    </td>   
				    <td align="left" valign="top"><%-- Certificates --%>
				      <table cellpadding="0" cellspacing="0" width="100%">
				        <tr>
				          <th  width="30%">
				            <bean:message key="label.drivingLicense"/>
				          </th>
				          <td  width="70%">
<logic:equal name="applicant" property="drivingLicense" value="true">
                    <bean:message key="label.yes"/>
</logic:equal>
<logic:notEqual name="applicant" property="drivingLicense" value="true">
                    <bean:message key="label.no"/>
</logic:notEqual>
				          </td>
				        </tr>
				        <tr>
				          <th  width="30%">
				            <bean:message key="label.degreeDetail"/>
				          </th>
				          <td  width="70%">
      				      <bean:write name="applicant" property="degreeDetail"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="30%">
				            <bean:message key="label.degree"/>
				          </th>
				          <td  width="70%">
<logic:equal name="applicant" property="degree" value="true">
							      <bean:message key="label.yes"/>
</logic:equal>
<logic:notEqual name="applicant" property="degree" value="true">
							      <bean:message key="label.no"/>
</logic:notEqual>
				          </td>
				        </tr>
				        <tr>
				          <th  width="30%">
				            Training
				          </th>
				          <td  width="70%">
      				      <bean:write name="applicant" property="trainingFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="30%">
				            HPC
				          </th>
				          <td  width="70%">
      				      <bean:write name="applicant" property="hpcFilename"/>&nbsp;
				          </td>
				        </tr>
				        <tr>
				          <th  width="30%">
				            <bean:message key="label.hpcNumber"/>
				          </th>
				          <td  width="70%">
      				      <bean:write name="applicant" property="hpcNumber"/>&nbsp;
				          </td>
				        </tr>
				      </table>
				    </td>   
				  </tr>
				  </logic:iterate>
				</table>
			</logic:notEmpty>
		</logic:present>
	  </html:form>
	</div>
</div>