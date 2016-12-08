<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/sendEmailProcess.do" focus="subject" onsubmit="return singleSubmit();">
<html:hidden property="referer"/>
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.sendEmail"/>
		</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left">
      <bean:message key="label.subject"/>
    </th>
    <td align="left">
      <html:text property="subject" size="75"/>
    </td>
  </tr>
  <tr>
    <th align="left" valign="top">
      <bean:message key="label.message"/>
    </th>
    <td align="left">
      <html:textarea property="message" styleId="message" cols="75" rows="30" />
    </td>
  </tr>
</html:form>
</table>
