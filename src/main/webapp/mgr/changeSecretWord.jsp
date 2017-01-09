<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/changeSecretWordProcess.do" focus="oldSecretWord" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.changeSecretWord"/>
		</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.oldSecretWord"/>
    </th>
    <td width="75%">
      <html:password property="oldSecretWord"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.newSecretWord"/>
    </th>
    <td>
      <html:password property="newSecretWord"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.confirmSecretWord"/>
    </th>
    <td>
      <html:password property="confirmSecretWord"/>
    </td>
  </tr>
</html:form>
</table>