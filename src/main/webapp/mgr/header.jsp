<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" 
%><html:link forward="home"><bean:message key="link.home"/></html:link>
<mmj-mgr:hasAccess forward="orderStaff1"><html:link accesskey="o" forward="orderStaff"><bean:message key="link.orderStaff"/></html:link></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="bookingList"><html:link accesskey="b" forward="bookingList"><bean:message key="link.bookingList"/></html:link></mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="locationList"><html:link forward="locationList"><bean:message key="link.locationList"/></html:link></mmj-mgr:hasAccess>
<html:link forward="logout"><bean:message key="link.logout"/></html:link>
<mmj-mgr:manager var="manager"/>(<bean:write name="manager" property="user.fullName"/>)