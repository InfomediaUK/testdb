<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.mgrAccessEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="mgrAccessEditProcess.do" focus="mgrAccess.name">

<html:hidden property="mgrAccess.mgrAccessId"/>
<html:hidden property="mgrAccess.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="mgrAccess.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><html:text property="mgrAccess.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startsWith"/></td>
    <td align="left"><html:checkbox property="mgrAccess.startsWith"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.global"/></td>
    <td align="left"><html:checkbox property="mgrAccess.global"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
