<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<html:link forward="home"><bean:message key="link.home"/></html:link>
<mmj-agy:hasAccess forward="bookingList"><html:link accesskey="b" forward="bookingList"><bean:message key="link.bookingList"/></html:link></mmj-agy:hasAccess>
<html:link forward="logout"><bean:message key="link.logout"/></html:link>
<mmj-agy:consultant var="consultant"/>(<bean:write name="consultant" property="user.fullName"/>)