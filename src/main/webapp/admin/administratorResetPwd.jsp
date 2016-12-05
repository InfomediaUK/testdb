<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.administratorResetPwd"/>

<br/>
<br/>

<html:errors/>

<html:form action="administratorResetPwdProcess.do">

<html:hidden property="administrator.administratorId" />
<html:hidden property="administrator.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.fullName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.login"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.showPageHelp"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.showPageHelp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.reset"/></html:submit></td>
  </tr>
</table>

</html:form>
