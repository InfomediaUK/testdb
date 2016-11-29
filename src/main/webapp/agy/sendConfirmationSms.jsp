<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/sendConfirmationSmsProcess.do" focus="mobileNumber" onsubmit="return singleSubmit();">
<html:hidden property="referer"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.sendConfirmationSms"/>
		</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.mobileNumber"/>
    </th>
    <td width="75%">
      <html:text property="mobileNumber"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.message"/>
    </th>
    <td>
      <html:textarea property="message" styleId="message" cols="50" rows="10" 
      onkeydown="textCounter('message', 'remLen', 160)"
      onkeyup="textCounter('message', 'remLen', 160)" />
      <br/>
      <label id="remLen">160</label>&nbsp;characters left
    </td>
  </tr>
</html:form>
</table>

<script language="javascript">
<!-- begin
textCounter('message', 'remLen', 160)
// end -->
</script>
