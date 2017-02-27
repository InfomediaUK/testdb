<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="org.joda.time.DateTime" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<br/>
<br/>
<%
String serverNamePrefix = null;
String mmjLogo = null;
if (request.getServerName().startsWith("local"))
{
  serverNamePrefix = request.getServerName(); 
  mmjLogo = request.getContextPath() + "/images/localmaster-logo.jpg";
}
else
{
  serverNamePrefix = request.getServerName().substring(0, request.getServerName().indexOf("."));
  serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
  mmjLogo = request.getContextPath() + "/images/" + serverNamePrefix + "master-logo.jpg";
}
DateTime applicationNow = DateTime.now();
%>
<%= applicationNow.toDate().toString() %>
<br/>
<br/>
<html:link page="/home.do" titleKey="title.mmjLogo"><html:img src="<%= mmjLogo %>" width="150" height="58" /></html:link>
<jsp:include flush="true" page="/sessionStuff.jsp"/>
