<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<bean:write name="QueryFormAdmin" property="xmlFileName"/><br />
<mmj-admin:hasAccess forward="queriesView" >
  <html:link forward="queriesView"><bean:message key="link.viewXml"/></html:link>
</mmj-admin:hasAccess>
<br /><br />
<bean:define id="xmlFileName" name="QueryFormAdmin" property="xmlFileName" />
<bean:define id="xsltFileName" name="QueryFormAdmin" property="xsltFileName" />
<%
String xmlFileUrl  = "file:" + xmlFileName;
String xsltFileUrl = "file:" + xsltFileName;
%>
<c:url value="http://${pageContext.request.serverName}${pageContext.request.contextPath}/admin/queryProcess.do" var="submitURL"/>

<c:import url="<%= xmlFileUrl %>" var="xml"/>
<c:import url="<%= xsltFileUrl %>" var="xslt"/>
<x:transform xml="${xml}" xslt="${xslt}">
  <x:param name="submitURL" value="${submitURL}"/>
</x:transform>

