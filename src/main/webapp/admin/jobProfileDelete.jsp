<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.jobProfileDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="jobProfileDeleteProcess.do">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="jobFamily.clientId" />

<html:hidden property="jobFamily.name" /> <%-- to preserve value --%>
<html:hidden property="jobFamily.code" /> <%-- to preserve value --%>
<html:hidden property="jobSubFamily.name" /> <%-- to preserve value --%>
<html:hidden property="jobSubFamily.code" /> <%-- to preserve value --%>
<html:hidden property="jobProfile.jobSubFamilyId" />
<html:hidden property="jobProfile.jobProfileId" />
<html:hidden property="jobProfile.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="client.name"/> (<bean:write name="JobProfileViewFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobFamily"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobFamily.name"/> (<bean:write name="JobProfileViewFormAdmin" property="jobFamily.code"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobSubFamily"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobSubFamily.name"/> (<bean:write name="JobProfileViewFormAdmin" property="jobSubFamily.code"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.code"/></td>
  </tr>
<%--
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.rate"/></td>
  </tr>
--%>
  <tr>
    <td align="left"><bean:message key="label.autoFillDefault"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.autoFill"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.documentURL"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.documentURL"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
