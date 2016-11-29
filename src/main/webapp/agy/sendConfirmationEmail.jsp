<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/sendConfirmationEmailProcess.do" focus="toEmailAddress" onsubmit="return singleSubmit();">
<html:hidden property="referer"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.sendConfirmationEmail"/>
		</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.fromEmailAddress"/>
    </th>
    <td width="75%">
      <bean:write name="SendConfirmationEmailFormAgy" property="fromEmailAddress"/>
      <html:hidden property="fromEmailAddress"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.toEmailAddress"/>
    </th>
    <td align="left">
      <html:text property="toEmailAddress" size="75"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.ccEmailAddress"/>
    </th>
    <td align="left">
      <html:text property="ccEmailAddress" size="75"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.bccEmailAddress"/>
    </th>
    <td align="left">
      <html:text property="bccEmailAddress" size="75"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.subject"/>
    </th>
    <td align="left">
      <html:text property="subject" size="75"/>
    </td>
  </tr>
  <logic:notEmpty name="SendConfirmationEmailFormAgy" property="attachment">
  <tr>
    <th align="left">
      <bean:message key="label.attachment"/>
    </th>
    <td align="left">
      <bean:write name="SendConfirmationEmailFormAgy" property="attachmentFileName"/>
      <html:hidden property="attachmentFileName"/>
      <html:hidden property="attachment"/>
    </td>
  </tr>
  </logic:notEmpty>
  <tr>
    <th align="left">
      <bean:message key="label.message"/>
    </th>
    <td align="left">
      <html:textarea property="message" styleId="message" cols="75" rows="30" />
    </td>
  </tr>
</html:form>
  <tr>
    <td colspan="2">
      <bean:write  name="SendConfirmationEmailFormAgy" property="hostName"/>&nbsp;=&nbsp;<bean:write  name="SendConfirmationEmailFormAgy" property="hostValue"/>&nbsp;
      <bean:write  name="SendConfirmationEmailFormAgy" property="portName"/>&nbsp;=&nbsp;<bean:write  name="SendConfirmationEmailFormAgy" property="portValue"/>&nbsp;
      (<bean:write  name="SendConfirmationEmailFormAgy" property="userName"/>)
    </td>
  </tr>
</table>
