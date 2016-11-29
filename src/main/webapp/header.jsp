<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<br/>
<br/>
<br/>
<br/>
<%
String serverNamePrefix = null;
String mmjLogo = null;
if (request.getServerName().startsWith("www.befriended.co.uk"))
{
  serverNamePrefix = request.getServerName();
  mmjLogo = request.getContextPath() + "/localmaster-logo.jpg";
}
else
{
  serverNamePrefix = request.getServerName().substring(0, request.getServerName().indexOf("."));
  serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
  mmjLogo = request.getContextPath() + "/images/" + serverNamePrefix + "master-logo.jpg";
}
%>
<html:link forward="home"><img src="<%= mmjLogo %>" width="150" height="58" /></html:link>
<br/>
<br/>
<h1><bean:message key="text.strapLine" /></h1>
