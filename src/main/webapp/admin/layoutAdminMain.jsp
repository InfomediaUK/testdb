<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" errorPage="/errorPages/error500.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%  org.apache.struts.util.ModuleUtils.getInstance().selectModule(request, pageContext.getServletContext()); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Pragma" content="no-cache"/><tiles:useAttribute name="includeZapatec"/><tiles:useAttribute name="titleKey"/>
<title>ADMIN - <bean:message name="titleKey" property="value" scope="page"/></title>
<meta name="description" content="<tiles:getAsString name="description"/>" />
<meta name="keywords" content="<tiles:getAsString name="keywords"/>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Refresh" content="<%= pageContext.getSession().getMaxInactiveInterval() %>; url=<%= request.getContextPath() %>/adminLoggedOut.do" />
<logic:equal name="includeZapatec" property="value" value="true"
><link rel="stylesheet" type="text/css" media="all" href="/mmj/zapatec/themes/winxp.css" title="Calendar Theme - winxp.css">
<link href="/mmj/zapatec/doc/css/zpcal.css" rel="stylesheet" type="text/css">
</logic:equal
><link type="text/css" rel="stylesheet" href="site.css" media="screen" />
<logic:equal name="includeZapatec" property="value" value="true"
><!-- import the calendar script -->
<script type="text/javascript" src="/mmj/zapatec/src/utils.js"></script>
<script type="text/javascript" src="/mmj/zapatec/src/calendar.js"></script>
<!-- import the language module -->
<script type="text/javascript" src="/mmj/zapatec/lang/calendar-en.js"></script>
<!-- import the calendar setup script -->
<script type="text/javascript" src="/mmj/zapatec/src/calendar-setup.js"></script>
</logic:equal
>
</head>
<body>
<jsp:include flush="true" page="/siteUnavailable.jsp"/>
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
