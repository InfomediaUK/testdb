<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%  org.apache.struts.util.ModuleUtils.getInstance().selectModule(request, pageContext.getServletContext()); %>

<!DOCTYPE html>
<html>
<head>
<title>HTTP 500 Internal server error - <bean:message key="title.mmj" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="site.css" media="screen" />
</head>
<body>

<br/>
<br/>

<div align="center">

HTTP 500 Internal server error - admin

<br/>
<br/>

Exception Class: <%= exception.getClass() %>

<br/>
<br/>

Message: <%= exception.getMessage() %>

<br/>
<br/>

<br/>
<br/>

<bean:message key="title.mmj" />

<br/>
<br/>

<html:link forward="admin"><bean:message key="link.admin" /></html:link>

</div>

</body>
</html>