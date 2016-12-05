<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.jobProfileGroupEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="jobProfileGroupEditProcess.do" focus="jobProfileGroup.name">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="jobProfileGroup.clientId" />

<html:hidden property="jobProfileGroup.jobProfileGroupId"/>
<html:hidden property="jobProfileGroup.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="JobProfileGroupFormAdmin" property="client.name"/> (<bean:write name="JobProfileGroupFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="jobProfileGroup.name"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
