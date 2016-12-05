<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.adminAccessDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="adminAccessDeleteProcess.do">

<html:hidden property="adminAccess.adminAccessId"/>
<html:hidden property="adminAccess.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.name" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.description" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startsWith"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.startsWith"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.global"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.global"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AdminAccessFormAdmin" property="adminAccess.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
