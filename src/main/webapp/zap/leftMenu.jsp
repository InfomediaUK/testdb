<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:link forward="applicantView" styleClass="leftColumn"><bean:message key="link.applicantView"/></html:link>
<br/><html:link forward="applicantBookingDatesView" styleClass="leftColumn"><bean:message key="link.applicantBookingDatesView"/></html:link>
<br/><html:link forward="applicantUnavailableView" styleClass="leftColumn"><bean:message key="link.applicantUnavailable"/></html:link>
<br/><html:link forward="sendEmail" styleClass="leftColumn"><bean:message key="link.sendEmail"/></html:link>
<br/><br/><html:link forward="logout" titleKey="link.title.logout"><bean:message key="link.logout"/></html:link>
