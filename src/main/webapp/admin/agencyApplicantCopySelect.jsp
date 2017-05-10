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
<% String applicantCheckBox = "applicantId"; %>

<bean:message key="title.agencyApplicantCopySelect"/>&nbsp;<bean:write name="AgencyApplicantCopyFormAdmin" property="sourceAgency.name" />&nbsp;<bean:message key="title.to"/>&nbsp;<bean:write name="AgencyApplicantCopyFormAdmin" property="targetAgency.name" />

<br/>
<br/>
<html:form action="/agencyApplicantCopyProcess.do" styleId="ApplicantsToCopy" onsubmit="return singleSubmit();">
<html:hidden property="sourceAgency.agencyId"/>
<html:hidden property="targetAgency.agencyId"/>
<html:hidden property="consultantId"/>
<table class="simple">
  <thead>
  <tr>
    <th align="center" width="2%">
      <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantsToCopy');"/>
    </th>
    <th align="left"><bean:message key="label.lastName" /></th>
    <th align="left"><bean:message key="label.firstName" /></th>
    <th align="left"><bean:message key="label.disciplineCategory" /></th>
    <th align="left"><bean:message key="label.clientGroup" /></th>
    <th align="left"><bean:message key="label.registrationNumber" /></th>
    <th align="left"><bean:message key="label.dateOfBirth" /></th>
    <th align="left"><bean:message key="label.niNumber" /></th>
    <th align="left"><bean:message key="label.gender" /></th>
    <th align="left"><bean:message key="label.compliant" /></th>
  </tr>
  </thead>
  <logic:iterate id="applicant" name="AgencyApplicantCopyFormAdmin" property="list" indexId="applicantIndex" type="com.helmet.bean.Applicant">
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
	  <logic:equal name="applicant" property="clientGroup" value="1">
      <bean:message key="label.adults"/>
    </logic:equal>
		<logic:equal name="applicant" property="clientGroup" value="2">
      <bean:message key="label.paeds"/>
    </logic:equal>
    </td>
    <td>
      <bean:write name="applicant" property="registrationNumber" />
    </td>
    <td>
      <bean:write name="applicant" property="niNumber" />
    </td>
    <td>
      <bean:write name="applicant" property="dateOfBirth" formatKey="format.mediumDateFormat" />
    </td>
    <td>
		  <logic:equal name="applicant" property="gender" value="<%= com.helmet.persistence.Constants.sqlMale %>">
		    <bean:message key="label.male"/>
		  </logic:equal>
		  <logic:equal name="applicant" property="gender" value="<%= com.helmet.persistence.Constants.sqlFemale %>">
		    <bean:message key="label.female"/>
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
  </logic:iterate>
  <tr>
    <td align="center" colspan="10">
      <html:submit styleClass="widebutton"><bean:message key="button.copy"/>&nbsp;<bean:message key="title.to"/>&nbsp;<bean:write name="AgencyApplicantCopyFormAdmin" property="targetAgency.name" /></html:submit>
    </td>
  </tr>
</table>
</html:form>