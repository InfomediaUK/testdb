<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.consultantResetPwd"/>

<br/>
<br/>

<html:errors/>

<html:form action="consultantResetPwdProcess.do">

<html:hidden property="agency.name" /> <%-- to preserve value --%>
<html:hidden property="agency.countryName" /> <%-- to preserve value --%>
<html:hidden property="consultant.agencyId" />

<html:hidden property="consultant.consultantId" />
<html:hidden property="consultant.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.agency"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="agency.name"/> (<bean:write name="ConsultantViewFormAdmin" property="agency.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.fullName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.login"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobTitle"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.jobTitle"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.showPageHelp"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.showPageHelp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.reset"/></html:submit></td>
  </tr>
</table>

</html:form>
