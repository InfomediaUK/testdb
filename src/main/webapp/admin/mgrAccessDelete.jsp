<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.mgrAccessDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="mgrAccessDeleteProcess.do">

<html:hidden property="mgrAccess.mgrAccessId"/>
<html:hidden property="mgrAccess.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.name" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.description" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startsWith"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.startsWith"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.global"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.global"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="MgrAccessFormAdmin" property="mgrAccess.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
