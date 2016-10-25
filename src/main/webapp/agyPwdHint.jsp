<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<br/>
<h2><bean:message key="title.agyPwdHint" /></h2>
<br/>
<br/>
<bean:write name="AgyForgottenPwdForm" property="consultant.user.pwdHint"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<html:link forward="agyLogin"><bean:message key="link.login"/></html:link>
<br/>
<br/>
<html:link forward="agyResetPwd"><bean:message key="link.resetPwd"/></html:link>