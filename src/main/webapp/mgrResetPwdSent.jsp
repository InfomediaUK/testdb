<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" 
%><%@ taglib uri="/mmj" prefix="mmj" 
%><br/>
<h2><bean:message key="title.mgrResetPwdSent" /></h2>
<br/>
<br/>
<bean:message key="text.pwdResetAndSent"/>
<br/>
<br/>
<bean:write name="MgrForgottenPwdForm" property="manager.user.emailAddress"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<html:link forward="mgrLogin"><bean:message key="link.login"/></html:link>
