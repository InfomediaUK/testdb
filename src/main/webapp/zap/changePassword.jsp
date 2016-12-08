<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/changePasswordProcess.do" focus="oldPwd" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.changePassword"/>
		</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.oldPwd"/>
    </th>
    <td width="75%">
      <html:password property="oldPwd"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.newPwd"/>
    </th>
    <td>
      <html:password property="newPwd"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.confirmPwd"/>
    </th>
    <td>
      <html:password property="confirmPwd"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.pwdHint"/>
    </th>
    <td>
      <html:text property="pwdHint"/>
    </td>
  </tr>
</html:form>
</table>