<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

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
<% 
String fileUrl = null;
String applicantCheckBox = "applicantId"; 
%>

<bean:message key="title.agencyApplicantTrainingSelect"/>&nbsp;<bean:write name="AgencyApplicantTrainingFormAdmin" property="agency.code" />

<br/>
<br/>
<html:form action="/agencyApplicantTrainingProcess.do" styleId="ApplicantsForTraining" onsubmit="return singleSubmit();">
<html:hidden property="agency.agencyId"/>
<html:hidden property="consultantId"/>
<table class="simple">
  <thead>
  <tr>
    <th align="center" width="2%">
      <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantsForTraining');"/>
    </th>
    <th align="left"><bean:message key="label.lastName" /></th>
    <th align="left"><bean:message key="label.firstName" /></th>
    <th align="left"><bean:message key="label.disciplineCategory" /></th>
    <th align="left"><bean:message key="label.trainingFile" /></th>
    <th align="left">Expiry</th>
    <th align="left">BLS</th>
    <th align="left">MH</th>
    <th align="left">EL</th>
    <th align="left">POVA</th>
    <th align="left">NLS</th>
    <th align="left"><bean:message key="label.compliant" /></th>
  </tr>
  </thead>
  <logic:iterate id="applicant" name="AgencyApplicantTrainingFormAdmin" property="list" indexId="applicantIndex" type="com.helmet.bean.ApplicantEntity">
  <tr>
    <td align="left" valign="top"><%-- Checkbox --%>
      <input type="checkbox" name="<%= applicantCheckBox %>" value="<%= applicant.getApplicantId() %>">    
    </td>
    <td>
      <bean:write name="applicant" property="user.lastName" />
    </td>
    <td>
      <bean:write name="applicant" property="user.firstName" />
    </td>
    <td>
      <bean:write name="applicant" property="disciplineCategoryName" />
    </td>
    <td>
       <bean:define id="trainingFileUrl" name="applicant" property="trainingFileUrl" type="java.lang.String" />
       <% 
         fileUrl = request.getContextPath() + trainingFileUrl;
       %>
       <html:link href="<%= fileUrl %>" target="_blank"><bean:write name="applicant" property="trainingFilename" /></html:link>      
    </td>
    <td>
      <bean:write name="applicant" property="trainingExpiryDate" formatKey="format.mediumDateFormat" />
    </td>
    <td>
      <logic:equal name="applicant" property="basicLifeSupportTraining" value="true">
        <bean:message key="label.yes"/>
      </logic:equal>
      <logic:equal name="applicant" property="basicLifeSupportTraining" value="false">
        <bean:message key="label.no"/>
      </logic:equal>
    </td>
    <td>
      <logic:equal name="applicant" property="manualHandlingTraining" value="true">
        <bean:message key="label.yes"/>
      </logic:equal>
      <logic:equal name="applicant" property="manualHandlingTraining" value="false">
        <bean:message key="label.no"/>
      </logic:equal>
    </td>
    <td>
      <logic:equal name="applicant" property="elearningTraining" value="true">
        <bean:message key="label.yes"/>
      </logic:equal>
      <logic:equal name="applicant" property="elearningTraining" value="false">
        <bean:message key="label.no"/>
      </logic:equal>
    </td>
    <td>
      <logic:equal name="applicant" property="povaTraining" value="true">
        <bean:message key="label.yes"/>
      </logic:equal>
      <logic:equal name="applicant" property="povaTraining" value="false">
        <bean:message key="label.no"/>
      </logic:equal>
    </td>
    <td>
      <logic:equal name="applicant" property="neonatalLifeSupportTraining" value="true">
        <bean:message key="label.yes"/>
      </logic:equal>
      <logic:equal name="applicant" property="neonatalLifeSupportTraining" value="false">
        <bean:message key="label.no"/>
      </logic:equal>
    </td>
    <td>
	    <logic:equal name="applicant" property="compliant" value="true">
		    <bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="applicant" property="compliant" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <td align="left" colspan="12">
      <bean:write name="applicant" property="trainingDocumentInfo" />   
    </td>
  </tr>
  </logic:iterate>
  <tr>
    <td align="center" colspan="12">
      <html:submit styleClass="button"><bean:message key="button.submit"/></html:submit>
    </td>
  </tr>
</table>
</html:form>