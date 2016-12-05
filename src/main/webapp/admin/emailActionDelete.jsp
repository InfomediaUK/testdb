<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.emailActionDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="emailActionDeleteProcess.do" focus="emailAction.name">

<html:hidden property="emailAction.emailActionId"/>
<html:hidden property="emailAction.noOfChanges"/>
<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="EmailActionFormAdmin" property="emailAction.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.subject"/></td>
    <td align="left"><bean:write name="EmailActionFormAdmin" property="emailAction.subject"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.textTemplate"/></td>
    <td align="left"><bean:write name="EmailActionFormAdmin" property="emailAction.textTemplate"/></td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      <bean:message key="label.text"/><br />
      <html:textarea name="EmailActionFormAdmin" property="text" styleId="message" cols="75" rows="12" disabled="true" />
  </tr>
  <tr>
    <td align="left"><bean:message key="label.htmlTemplate"/></td>
    <td align="left"><bean:write name="EmailActionFormAdmin" property="emailAction.htmlTemplate"/></td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      <bean:message key="label.html"/><br />
      <html:textarea name="EmailActionFormAdmin" property="html" styleId="message" cols="75" rows="12"  disabled="true" />
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.dependsOn"/></td>
    <td align="left"><bean:write name="EmailActionFormAdmin" property="emailAction.dependsOn"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="EmailActionFormAdmin" property="emailAction.displayOrder"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
