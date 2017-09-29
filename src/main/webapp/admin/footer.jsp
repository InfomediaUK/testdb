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
String imagePrefix = System.getenv("IMAGE_PREFIX");
String mmjLogo = request.getContextPath() + "/images/" + imagePrefix + "master-logo.jpg";
DateTime applicationNow = DateTime.now();
%>
<%= applicationNow.toDate().toString() %>
<br/>
<br/>
<html:link page="/home.do" titleKey="title.mmjLogo"><html:img src="<%= mmjLogo %>" width="150" height="58" /></html:link>
<jsp:include flush="true" page="/sessionStuff.jsp"/>
