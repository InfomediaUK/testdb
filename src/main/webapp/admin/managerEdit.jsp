<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.managerEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="managerEditProcess.do" focus="manager.user.firstName">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="manager.clientId" />

<html:hidden property="manager.managerId" />
<html:hidden property="manager.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="ManagerFormAdmin" property="client.name"/> (<bean:write name="ManagerFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.firstName"/></td>
    <td align="left"><html:text property="manager.user.firstName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.lastName"/></td>
    <td align="left"><html:text property="manager.user.lastName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><html:text property="manager.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><html:text property="manager.user.login"/></td>
  </tr>
<%--
  <tr>
    <td align="left"><bean:message key="label.pwd"/></td>
    <td align="left"><html:password property="manager.user.pwd"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.confirmPwd"/></td>
    <td align="left"><html:password property="confirmPwd"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.pwdHint"/></td>
    <td align="left"><html:text property="manager.user.pwdHint"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.secretWord"/></td>
    <td align="left"><html:text property="manager.secretWord"/></td>
  </tr>
--%>
  <tr>
    <td align="left"><bean:message key="label.showPageHelp"/></td>
    <td align="left"><html:checkbox property="manager.user.showPageHelp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.superUser"/></td>
    <td align="left"><html:checkbox property="manager.user.superUser"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
  