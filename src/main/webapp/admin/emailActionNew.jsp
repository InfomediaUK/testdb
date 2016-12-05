<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.emailActionNew"/>

<br/>
<br/>
<html:errors/>

<html:form action="emailActionNewProcess.do" focus="emailAction.name">

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text name="EmailActionFormAdmin" property="emailAction.name" size="60" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.subject"/></td>
    <td align="left"><html:text name="EmailActionFormAdmin" property="emailAction.subject" size="60" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.textTemplate"/></td>
    <td align="left"><html:text name="EmailActionFormAdmin" property="emailAction.textTemplate" size="60" /></td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      <bean:message key="label.text"/><br />
      <html:textarea name="EmailActionFormAdmin" property="text" styleId="message" cols="75" rows="12" />
  </tr>
  <tr>
    <td align="left"><bean:message key="label.htmlTemplate"/></td>
    <td align="left"><html:text name="EmailActionFormAdmin" property="emailAction.htmlTemplate" size="60" /></td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      <bean:message key="label.html"/><br />
      <html:textarea name="EmailActionFormAdmin" property="html" styleId="message" cols="75" rows="12" />
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.dependsOn"/></td>
    <td align="left"><html:text name="EmailActionFormAdmin" property="emailAction.dependsOn" size="60" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
     <td align="left"><html:text name="EmailActionFormAdmin" property="emailAction.displayOrder" size="10" /></td>
   </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>

