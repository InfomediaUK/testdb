<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<bean:message key="title.administratorNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="administratorNewProcess.do" focus="administrator.user.firstName">

<table>
  <tr>
    <td align="left"><bean:message key="label.firstName"/></td>
    <td align="left"><html:text property="administrator.user.firstName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.lastName"/></td>
    <td align="left"><html:text property="administrator.user.lastName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><html:text property="administrator.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><html:text property="administrator.user.login"/></td>
  </tr>
<%--
  <tr>
    <td align="left"><bean:message key="label.pwd"/></td>
    <td align="left"><html:password property="administrator.user.pwd"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.confirmPwd"/></td>
    <td align="left"><html:password property="confirmPwd"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.pwdHint"/></td>
    <td align="left"><html:text property="administrator.user.pwdHint"/></td>
  </tr>
--%>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
  