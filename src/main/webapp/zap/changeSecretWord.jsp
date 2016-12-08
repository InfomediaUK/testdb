<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mmj-app" prefix="mmj-app" %>

<mmj-app:applicant var="applicant"/>

<bean:define id="firstTimeThru" value="false"/>

<c:if test="${applicant.user.login == applicant.user.secretWord}">
  <%/* first time thru */%>
  <bean:define id="firstTimeThru" value="true"/>
</c:if>

<bean:define id="focusField" value="oldSecretWord"/>
<logic:equal name="firstTimeThru" value="true">
  <bean:define id="focusField" value="newSecretWord"/>
</logic:equal>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/changeSecretWordProcess.do" focus="<%= focusField %>" onsubmit="return singleSubmit();">
  <tr>
    <td align="left" valign="middle" class="title">
	<logic:equal name="firstTimeThru" value="true">
  	  <bean:message key="title.enterSecretWord"/>
	</logic:equal>
	<logic:notEqual name="firstTimeThru" value="true">
	  <bean:message key="title.changeSecretWord"/>
	</logic:notEqual>
	</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">

<logic:equal name="firstTimeThru" value="true">
  <tr>
    <td align="left" colspan="2">
    <html:hidden property="oldSecretWord"/>
    <br/>
    <ul>
      <li>For security reasons, please enter a secret word.</li>
      <li>On subsequent entry to MMJ you will be asked to enter 3 random letters from your secret word so try to make it memorable.</li>
      <li>Your secret word must be between 8-20 characters and contain at least one number (0-9) and at least one letter (a-z, A-Z).</li>
      <li>If you forget your secret word please contact us and we can reset it for you.</li>
	</ul>
	</td>
  </tr>
</logic:equal>
<logic:notEqual name="firstTimeThru" value="true">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.oldSecretWord"/>
    </th>
    <td width="75%">
      <html:password property="oldSecretWord"/>
    </td>
  </tr>
</logic:notEqual>

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