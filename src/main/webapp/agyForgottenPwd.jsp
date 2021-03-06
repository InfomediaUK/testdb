<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" 
%><%@ taglib uri="/mmj" prefix="mmj" 
%><br/>
<h2><bean:message key="title.agyForgottenPwd" /></h2>
<bean:define id="focusField" value="agencyCode"/>
<bean:parameter id="agencyCodeParam" name="agencyCode" value=""/>
<logic:notEmpty name="agencyCodeParam">
  <bean:define id="focusField" value="consultant.user.login"/>
</logic:notEmpty>
<html:errors />
<table>
<html:form action="agyForgottenPwdProcess.do" focus="<%= focusField %>">
  <tr>
    <td align="left" class="label"><bean:message key="label.agencyCode"/></td>
    <td align="left"><html:text property="agencyCode" maxlength="30"/></td>
  </tr>
  <tr>
    <td align="left" class="label"><bean:message key="label.login"/></td>
    <td align="left"><html:text property="consultant.user.login" maxlength="20"/></td>
  </tr>
  <tr>
    <td align="left" class="label"><bean:message key="label.secretWord"/></td>
    <td align="left"><html:password property="consultant.user.secretWord" maxlength="20"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <br/>
      <html:submit styleClass="button"><bean:message key="button.getHint"/></html:submit>
      <html:cancel styleClass="button"><bean:message key="button.cancel"/></html:cancel>
    </td>
  </tr>
</html:form>
</table>
<br/>
<html:link forward="agyLogin"><bean:message key="link.login"/></html:link>
