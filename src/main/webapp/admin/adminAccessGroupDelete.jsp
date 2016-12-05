<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.adminAccessGroupDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="adminAccessGroupDeleteProcess.do">

<html:hidden property="adminAccessGroup.adminAccessGroupId"/>
<html:hidden property="adminAccessGroup.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.name" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
