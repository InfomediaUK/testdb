<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.agyAccessNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="agyAccessNewProcess.do" focus="agyAccess.name">

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="agyAccess.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><html:text property="agyAccess.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startsWith"/></td>
    <td align="left"><html:checkbox property="agyAccess.startsWith"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.global"/></td>
    <td align="left"><html:checkbox property="agyAccess.global"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
