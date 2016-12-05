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
<link type="text/css" rel="stylesheet" href="site.css" media="screen" />
</head>
<body>
<div align="left">
<table>
  <tr>
    <td align="left" valign="top">
<tiles:insert attribute="header"/>
    </td>
    <td align="left" valign="top">
<tiles:insert attribute="body"/>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="left" valign="top">
<tiles:insert attribute="footer"/>
    </td>
  </tr>
</table>
</div>
</body>
</html>
