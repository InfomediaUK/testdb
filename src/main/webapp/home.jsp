<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" errorPage="/errorPages/error500.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<br/>
<br/>
<html:link accesskey="m" forward="mgr"><bean:message key="link.mgr" /></html:link>
<br/>
<br/>
<html:link accesskey="c" forward="agy"><bean:message key="link.agy" /></html:link>
<br/>
<br/>
<html:link accesskey="p" forward="app"><bean:message key="link.app" /></html:link>

<%--
 %><br/>
<br/>
<html:link accesskey="a" forward="admin"><bean:message key="link.admin" /></html:link>
<br/>
<br/>
<bean:define id="clientCode" value="SPAH"/>
<html:link forward="mgr" paramId="clientCode" paramName="clientCode"><bean:message key="link.mgr" />&nbsp;<bean:write name="clientCode"/></html:link>
<br/>
<br/>
<bean:define id="agencyCode" value="PJ"/>
<html:link forward="agy" paramId="agencyCode" paramName="agencyCode"><bean:message key="link.agy" />&nbsp;<bean:write name="agencyCode"/></html:link>
--%>