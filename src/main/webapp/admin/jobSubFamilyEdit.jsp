<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.jobSubFamilyEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="jobSubFamilyEditProcess.do" focus="jobSubFamily.name">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="jobFamily.clientId" />

<html:hidden property="jobFamily.name" /> <%-- to preserve value --%>
<html:hidden property="jobFamily.code" /> <%-- to preserve value --%>
<html:hidden property="jobSubFamily.jobFamilyId" />
<html:hidden property="jobSubFamily.jobSubFamilyId" />
<html:hidden property="jobSubFamily.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="JobSubFamilyFormAdmin" property="client.name"/> (<bean:write name="JobSubFamilyFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobFamily"/></td>
    <td align="left"><bean:write name="JobSubFamilyFormAdmin" property="jobFamily.name"/> (<bean:write name="JobSubFamilyFormAdmin" property="jobFamily.code"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="jobSubFamily.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="jobSubFamily.code"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
