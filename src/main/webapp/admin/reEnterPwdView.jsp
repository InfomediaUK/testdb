<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.reEnterPwdView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="ReEnterPwdFormAdmin" property="reEnterPwd.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ReEnterPwdFormAdmin" property="reEnterPwd.active"/></td>
  </tr>
</table>

<logic:equal name="ReEnterPwdFormAdmin" property="reEnterPwd.active" value="true">
<mmj-admin:hasAccess forward="reEnterPwdEdit" >
  <html:link forward="reEnterPwdEdit" paramId="reEnterPwd.reEnterPwdId" paramName="ReEnterPwdFormAdmin" paramProperty="reEnterPwd.reEnterPwdId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="reEnterPwdDelete" >
  <html:link forward="reEnterPwdDelete" paramId="reEnterPwd.reEnterPwdId" paramName="ReEnterPwdFormAdmin" paramProperty="reEnterPwd.reEnterPwdId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
