<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<c:url value="https://${pageContext.request.serverName}${pageContext.request.contextPath}/admin/queryProcess.do" var="submitURL"/>

<c:import url="queries.xml" var="xml"/>
<c:import url="queries.xsl" var="xslt"/>
<x:transform xml="${xml}" xslt="${xslt}">
  <x:param name="submitURL" value="${submitURL}"/>
</x:transform>

