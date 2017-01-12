<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" errorPage="/errorPages/error500.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%  org.apache.struts.util.ModuleUtils.getInstance().selectModule(request, pageContext.getServletContext()); %>
<!DOCTYPE html>
<html>
<head>
<tiles:useAttribute name="titleKey"/>
<title><bean:message name="titleKey" property="value" scope="page"/></title>
<meta name="description" content="<tiles:getAsString name="description"/>" />
<meta name="keywords" content="<tiles:getAsString name="keywords"/>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="<%= pageContext.getSession().getMaxInactiveInterval() %>; url=<%= request.getContextPath() %>/adminLoggedOut.do" />
<link rel="stylesheet" type="text/css" media="all" href="<%= request.getContextPath() %>/zapatec/themes/winxp.css" title="Calendar Theme - winxp.css" >
<link href="<%= request.getContextPath() %>/zapatec/doc/css/zpcal.css" rel="stylesheet" type="text/css">
<link href="<%= request.getContextPath() %>/zapatec/doc/css/template.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="site.css" media="screen" />
<!-- import the calendar script -->
<script type="text/javascript" src="<%= request.getContextPath() %>/zapatec/src/utils.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/zapatec/src/calendar.js"></script>
<!-- import the language module -->
<script type="text/javascript" src="<%= request.getContextPath() %>/zapatec/lang/calendar-en.js"></script>
<!-- import the calendar setup script -->
<script type="text/javascript" src="<%= request.getContextPath() %>/zapatec/src/calendar-setup.js"></script>
</head>
<body>
<div align="left">
<tiles:insert attribute="header"/>
<br/>
<br/>
<tiles:insert attribute="body"/>
<br/>
<br/>
<tiles:insert attribute="footer"/>
</div>
</body>
</html>
