<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<bean:message key="text.error500" />
<br/>
<br/>
<html:link accesskey="m" forward="mgr"><bean:message key="link.mgr" /></html:link>
<br/>
<br/>
<html:link accesskey="c" forward="agy"><bean:message key="link.agy" /></html:link>
<br/>
<br/>
<html:link accesskey="p" forward="app"><bean:message key="link.app" /></html:link>
