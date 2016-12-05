<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" 
%><%@ taglib uri="/mmj" prefix="mmj" 
%><br/>
<h2><bean:message key="title.adminPwdHint" /></h2>
<br/>
<br/>
<bean:write name="AdminForgottenPwdForm" property="administrator.user.pwdHint"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<html:link forward="adminLogin"><bean:message key="link.login"/></html:link>
<br/>
<br/>
<html:link forward="adminResetPwd"><bean:message key="link.resetPwd"/></html:link>