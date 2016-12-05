<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.managerResetSecretWord"/>

<br/>
<br/>

<html:errors/>

<html:form action="managerResetSecretWordProcess.do">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="manager.clientId" />

<html:hidden property="manager.managerId" />
<html:hidden property="manager.user.login" />
<html:hidden property="manager.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="ManagerViewFormAdmin" property="client.name"/> (<bean:write name="ManagerViewFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="ManagerViewFormAdmin" property="manager.user.fullName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><bean:write name="ManagerViewFormAdmin" property="manager.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><bean:write name="ManagerViewFormAdmin" property="manager.user.login"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.showPageHelp"/></td>
    <td align="left"><bean:write name="ManagerViewFormAdmin" property="manager.user.showPageHelp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ManagerViewFormAdmin" property="manager.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.reset"/></html:submit></td>
  </tr>
</table>

</html:form>
