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
<title>HTTP 404 page not found - <bean:message key="title.mmj" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="site.css" media="screen" />
</head>
<body>

<div align="center">
<img src="/master-logo.jpg" title="Match My Job - Providing management solutions for workforce procurement" alt="Match My Job" border="0"/>
</div>

<br/>
<br/>


<div align="center">

HTTP 404 page not found

<br/>
<br/>

<bean:message key="title.mmj" />

<br/>
<br/>

<bean:message key="text.blurb" />

<br/>
<br/>

<html:link forward="admin"><bean:message key="link.admin" /></html:link>

<br/>
<br/>

<html:link forward="mgr"><bean:message key="link.mgr" /></html:link>


<br/>
<br/>

<br/>
<br/>

<html:link forward="home"><bean:message key="link.home" /></html:link>

</div>

</body>
</html>