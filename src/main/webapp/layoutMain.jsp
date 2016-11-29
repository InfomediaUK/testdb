<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%  org.apache.struts.util.ModuleUtils.getInstance().selectModule(request, pageContext.getServletContext()); %>
<!DOCTYPE html>
<html>
	<head>
	<tiles:useAttribute name="titleKey"
	/><title><bean:message name="titleKey" property="value" scope="page"/></title>
	<meta name="description" content="<tiles:getAsString name="description"/>">
	<meta name="keywords" content="<tiles:getAsString name="keywords"/>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta name="robots" content="noindex,nofollow">
	<link rel="icon" href="<%= request.getContextPath() %>/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/favicon.ico" type="image/x-icon">
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/site.css" media="screen">
	</head>
	<body>
		<%-- jsp:include flush="true" page="/siteUnavailable.jsp"/--%>
		<div align="center">
			<table align="center">
			  <tr>
			    <td align="center">
			      <tiles:insert attribute="header"/>
			    </td>
			  </tr>
			  <tr height="250">
			    <td valign="top" align="center">
			      <tiles:insert attribute="body"/>
			    </td>
			  </tr>
			  <tr>
			    <td align="center">
			      <tiles:insert attribute="footer"/>
			    </td>
			  </tr>
			</table>
		</div>
	</body>
</html>
