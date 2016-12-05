<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.administratorDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="administratorDeleteProcess.do">

<html:hidden property="administrator.administratorId"/>
<html:hidden property="administrator.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.firstName"/></td>
    <td align="left"><bean:write name="AdministratorFormAdmin" property="administrator.user.firstName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.lastName"/></td>
    <td align="left"><bean:write name="AdministratorFormAdmin" property="administrator.user.lastName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><bean:write name="AdministratorFormAdmin" property="administrator.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><bean:write name="AdministratorFormAdmin" property="administrator.user.login"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.showPageHelp"/></td>
    <td align="left"><bean:write name="AdministratorFormAdmin" property="administrator.user.showPageHelp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.superUser"/></td>
    <td align="left"><bean:write name="AdministratorFormAdmin" property="administrator.user.superUser"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
