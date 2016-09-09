<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<br/>
<br/>
<bean:message key="text.copyright" />&nbsp;<fmt:formatDate value="${date}" pattern="yyyy" />
<%-- <jsp:include flush="true" page="/sessionStuff.jsp"/>   --%>
