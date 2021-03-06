<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" 
%><%@ taglib uri="/mmj" prefix="mmj" 
%><br/>
<h2><bean:message key="title.mgrLogin" /></h2>
<bean:define id="focusField" value="clientCode"/>
<bean:parameter id="clientCodeParam" name="clientCode" value=""/>
<logic:notEmpty name="clientCodeParam">
  <bean:define id="focusField" value="manager.user.login"/>
</logic:notEmpty>
<html:errors />
<table>
<html:form action="mgrLoginProcess.do" focus="<%= focusField %>">
  <tr>
    <td align="left" class="label"><bean:message key="label.clientCode"/></td>
    <td align="left"><html:text property="clientCode" maxlength="30"/></td>
  </tr>
  <tr>
    <td align="left" class="label"><bean:message key="label.login"/></td>
    <td align="left"><html:text property="manager.user.login" maxlength="20"/></td>
  </tr>
  <tr>
    <td align="left" class="label"><bean:message key="label.pwd"/></td>
    <td align="left"><html:password property="manager.user.pwd" maxlength="20"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <br/>
      <html:submit styleClass="button"><bean:message key="button.login"/></html:submit>
      <html:reset styleClass="button"><bean:message key="button.reset"/></html:reset>
      <html:cancel styleClass="button"><bean:message key="button.cancel"/></html:cancel>
    </td>
  </tr>
</html:form>
</table>
<br/>
<html:link forward="mgrForgottenPwd"><bean:message key="link.forgottenPwd"/></html:link>
