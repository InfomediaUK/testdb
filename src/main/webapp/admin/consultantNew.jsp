<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.consultantNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="consultantNewProcess.do" focus="consultant.user.firstName">

<html:hidden property="agency.name" /> <%-- to preserve value --%>
<html:hidden property="agency.countryName" /> <%-- to preserve value --%>
<html:hidden property="consultant.agencyId" />

<table>
  <tr>
    <td align="left"><bean:message key="label.agency"/></td>
    <td align="left"><bean:write name="ConsultantFormAdmin" property="agency.name"/> (<bean:write name="ConsultantFormAdmin" property="agency.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.firstName"/></td>
    <td align="left"><html:text property="consultant.user.firstName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.lastName"/></td>
    <td align="left"><html:text property="consultant.user.lastName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><html:text property="consultant.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><html:text property="consultant.user.login"/></td>
  </tr>
<%--
  <tr>
    <td align="left"><bean:message key="label.pwd"/></td>
    <td align="left"><html:password property="consultant.user.pwd"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.confirmPwd"/></td>
    <td align="left"><html:password property="confirmPwd"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.pwdHint"/></td>
    <td align="left"><html:text property="consultant.user.pwdHint"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.secretWord"/></td>
    <td align="left"><html:text property="consultant.secretWord"/></td>
  </tr>
--%>
  <tr>
    <td align="left"><bean:message key="label.jobTitle"/></td>
    <td align="left"><html:text property="consultant.jobTitle"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
  