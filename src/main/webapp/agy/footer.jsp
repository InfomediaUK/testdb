<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="org.joda.time.DateTime" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<br/>
<bean:message key="text.copyright" />&nbsp;<fmt:formatDate value="${date}" pattern="yyyy" />
<br/>
<br/>
<%
DateTime applicationNow = DateTime.now();
%>
<%= applicationNow.toDate().toString() %>
<br/>
<br/>
<html:link page="/home.do"><bean:message key="link.mmjHome"/></html:link>
<jsp:include flush="true" page="/sessionStuff.jsp"/>
