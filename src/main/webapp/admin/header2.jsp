<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="/mmj-admin" prefix="mmj-admin" 
%><html:link forward="home"><bean:message key="link.home"/></html:link>
<mmj-admin:hasAccess forward="administratorList">
<br/>
  <html:link forward="administratorList"><bean:message key="link.administratorList"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="adminAccessList">
<br/>
  <html:link forward="adminAccessList"><bean:message key="link.adminAccessList"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="adminAccessGroupList">
<br/>
  <html:link forward="adminAccessGroupList"><bean:message key="link.adminAccessGroupList"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="mgrAccessList">
<br/>
  <html:link forward="mgrAccessList"><bean:message key="link.mgrAccessList"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="countryList">
<br/>
  <html:link forward="countryList"><bean:message key="link.countryList"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobFamilyList">
<br/>
  <html:link forward="jobFamilyList"><bean:message key="link.jobFamilyList"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobProfileGroupList">
<br/>
  <html:link forward="jobProfileGroupList"><bean:message key="link.jobProfileGroupList"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="clientList">
<br/>
  <html:link forward="clientList"><bean:message key="link.clientList"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="connectionPool">
<br/>
  <html:link forward="connectionPool"><bean:message key="link.connectionPool"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="performanceMonitor">
<br/>
  <html:link forward="performanceMonitor"><bean:message key="link.performanceMonitor"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<html:link forward="logout"><bean:message key="link.logout"/></html:link>
<mmj-admin:administrator var="administrator"/>
<br/>
(<bean:write name="administrator" property="user.fullName"/>)