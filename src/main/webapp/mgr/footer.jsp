<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" 
%><br/>
<bean:message key="text.copyright" />
<br/>
<br/>
<html:link page="/.."><bean:message key="link.mmjHome"/></html:link>
<jsp:include flush="true" page="/sessionStuff.jsp"/>
