<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.jobProfileNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="jobProfileNewProcess.do" onsubmit="javascript:sortCheckBoxes();" focus="jobProfile.name">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="jobFamily.clientId" />

<html:hidden property="jobFamily.name" /> <%-- to preserve value --%>
<html:hidden property="jobFamily.code" /> <%-- to preserve value --%>
<html:hidden property="jobSubFamily.name" /> <%-- to preserve value --%>
<html:hidden property="jobSubFamily.code" /> <%-- to preserve value --%>
<html:hidden property="jobProfile.jobSubFamilyId" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="JobProfileFormAdmin" property="client.name"/> (<bean:write name="JobProfileFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobFamily"/></td>
    <td align="left"><bean:write name="JobProfileFormAdmin" property="jobFamily.name"/> (<bean:write name="JobProfileFormAdmin" property="jobFamily.code"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobSubFamily"/></td>
    <td align="left"><bean:write name="JobProfileFormAdmin" property="jobSubFamily.name"/> (<bean:write name="JobProfileFormAdmin" property="jobSubFamily.code"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="jobProfile.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="jobProfile.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.nhsAssignment"/></td>
    <td align="left"><html:text property="jobProfile.nhsAssignment" size="50"/></td>
  </tr>
<%--
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><html:text property="jobProfile.rate"/></td>
  </tr>
--%>
  <html:hidden property="jobProfile.rate"/>
  <tr>
    <td align="left">
		  <label for="autoFillStrId"><bean:message key="label.autoFillDefault"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="autoFillStr" id="autoFillStrId">
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.documentURL"/></td>
    <td align="left"><html:text property="jobProfile.documentURL"/></td>
  </tr>
  <html:hidden property="jobProfile.autoFill"/>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>

<script type="text/javascript">
<!--

theFormAdmin = document.JobProfileFormAdmin;

function sortCheckBoxes() {
	theFormAdmin.elements["jobProfile.autoFill"].value = theFormAdmin.autoFillStr.checked;
}

theFormAdmin.autoFillStr.checked = theFormAdmin.elements["jobProfile.autoFill"].value == "true";

//-->
</script>
