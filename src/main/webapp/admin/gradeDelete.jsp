<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.gradeDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="gradeDeleteProcess.do">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="grade.clientId" />

<html:hidden property="grade.gradeId"/>
<html:hidden property="grade.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="GradeFormAdmin" property="client.name"/> (<bean:write name="GradeFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="GradeFormAdmin" property="grade.name" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="GradeFormAdmin" property="grade.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="GradeFormAdmin" property="grade.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
