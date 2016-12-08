<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.helmet.application.app.AppUtilities" %>
<%@ page import="com.helmet.application.zap.ZapConstants" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<br/>
<% 
String errorMessage = (String)request.getSession().getAttribute(ZapConstants.FATALERRORMESSAGE);
%>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.applicantFatalError"/>
		</td>
  </tr>
</table>
<br/>
<%= errorMessage %>
<br/>
<br/>
<bean:message key="text.applicantFatalErrorLoggedOut"/>
<br/>
<br/>
<bean:message key="text.applicantFatalErrorPleaseReport"/>
<br/>
<br/>
<html:link forward="applicantView"><bean:message key="text.logBackIn"/></html:link>
<% 
// Invalidate Session to force a full login as a security measure.
AppUtilities.invalidateSession(request);
%>
