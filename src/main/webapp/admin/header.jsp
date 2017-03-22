<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><%@ taglib uri="/mmj-admin" prefix="mmj-admin" 
%>

<style>

#navLeft {
  float:left;
}

#navLeft ul {
	list-style:none;
	margin: 0
}

#navLeft li {
	display:inline;
}

#navRight {
  float:right;
}

#navRight ul {
	list-style:none;
	margin: 0
}

#navRight li {
	display:inline;
}

#navMain {
  clear: both;
}

#navMain ul {
	list-style:none;
	margin: 0
}

#navMain li {
	display:inline;
}

</style>

<div id="navLeft" class="navLeft">

<ul>
<li class="navLeft">
<html:link forward="home"><bean:message key="link.home"/></html:link>
</li>
<li class="navLeft">
<html:link forward="changePassword"><bean:message key="link.changePassword"/></html:link>
</li>
<li class="navLeft">
<html:link forward="changeSecretWord"><bean:message key="link.changeSecretWord"/></html:link>
</li>
<li class="navLeft">
<html:link forward="logout"><bean:message key="link.logout"/></html:link>
</li>
<li class="navLeft">
<mmj-admin:administrator var="administrator"/>
(<bean:write name="administrator" property="user.fullName"/>)
</li>
</ul>

</div>

<div id="navRight" class="navRight">

<ul>
<mmj-admin:hasAccess forward="connectionPool">
<li class="navRight">
  <html:link forward="connectionPool"><bean:message key="link.connectionPool"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="performanceMonitor">
<li class="navRight">
  <html:link forward="performanceMonitor"><bean:message key="link.performanceMonitor"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="clientUpload">
<li class="navRight">
  <html:link forward="clientUpload"><bean:message key="link.clientUpload"/></html:link>
</li>
</mmj-admin:hasAccess>
</ul>

</div>

<br/>
<br/>

<div id="navMain" class="navMain" align="center">

<ul>
<mmj-admin:hasAccess forward="administratorList">
<li class="navMain">
  <html:link forward="administratorList"><bean:message key="link.administratorList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="adminAccessList">
<li class="navMain">
  <html:link forward="adminAccessList"><bean:message key="link.adminAccessList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="adminAccessGroupList">
<li class="navMain">
  <html:link forward="adminAccessGroupList"><bean:message key="link.adminAccessGroupList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="mgrAccessList">
<li class="navMain">
  <html:link forward="mgrAccessList"><bean:message key="link.mgrAccessList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="reEnterPwdList">
<li class="navMain">
  <html:link forward="reEnterPwdList"><bean:message key="link.reEnterPwdList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="agyAccessList">
<li class="navMain">
  <html:link forward="agyAccessList"><bean:message key="link.agyAccessList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="countryList">
<li class="navMain">
  <html:link forward="countryList"><bean:message key="link.countryList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="clientList">
<li class="navMain">
  <html:link forward="clientList"><bean:message key="link.clientList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="agencyList">
<li class="navMain">
  <html:link forward="agencyList"><bean:message key="link.agencyList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="shiftSearch">
<li class="navMain">
  <html:link forward="shiftSearch"><bean:message key="link.shiftSearch"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="queries">
<li class="navMain">
  <html:link forward="queries"><bean:message key="link.queries"/></html:link>
</li>
</mmj-admin:hasAccess>
</ul>
<ul>
<mmj-admin:hasAccess forward="emailActionList">
<li class="navMain">
  <html:link forward="emailActionList"><bean:message key="link.emailActionList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="disciplineCategoryList">
<li class="navMain">
  <html:link forward="disciplineCategoryList"><bean:message key="link.disciplineCategoryList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="idDocumentList">
<li class="navMain">
  <html:link forward="idDocumentList"><bean:message key="link.idDocumentList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="visaTypeList">
<li class="navMain">
  <html:link forward="visaTypeList"><bean:message key="link.visaTypeList"/></html:link>
</li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="regulatorList">
<li class="navMain">
  <html:link forward="regulatorList"><bean:message key="link.regulatorList"/></html:link>
</li>
</mmj-admin:hasAccess>

<mmj-admin:hasAccess forward="compliancyTestList">
<li class="navMain">
  <html:link forward="compliancyTestList"><bean:message key="link.compliancyTestList"/></html:link>
</li>
</mmj-admin:hasAccess>

<mmj-admin:hasAccess forward="siteUnavailableView">
<li class="navMain">
  <html:link forward="siteUnavailableView"><bean:message key="link.siteUnavailableView"/></html:link>
</li>
</mmj-admin:hasAccess>

</ul>
</div>

<%--

<style>

#nav {
  float:left;
}

#nav ul {
	list-style:none;
	text-align:center;
  margin: 0em 0em 0em 2.0em;
	line-height:3.0em;
	width:100%;
	border-bottom: 0.1em solid #a8a7a5;
}


#nav li {
	display:inline;
	text-align:center;
	font-weight: bold;
	width:7.5em;
    margin: 0em 0.5em 0em 0.5em;

}

#nav li a {
    width:100%;
    color: #000000;
}

.nav li a:link,
.nav li a:visited {
	text-decoration:none;
	border-top: 0.2em solid #e0e0e0;
	border-left: 0.1em solid #e0e0e0;
  border-right: 0.1em solid #e0e0e0;
}

.nav li a:hover,
.nav li a:active {
	text-decoration:underline;
	border-top: 0.2em solid #000000;
	border-left: 0.1em solid #000000;
  border-right: 0.1em solid #000000;
}

#nav2 {
  clear: both;
}

</style>

<div id="nav" class="nav">
<ul>
<li class="nav"><html:link forward="home"><bean:message key="link.home"/></html:link></li>
<li class="nav"><html:link forward="changePassword"><bean:message key="link.changePassword"/></html:link></li>
<li class="nav"><html:link forward="changeSecretWord"><bean:message key="link.changeSecretWord"/></html:link></li>
<li class="nav"><html:link forward="logout"><bean:message key="link.logout"/></html:link></li>
<mmj-admin:hasAccess forward="connectionPool">
  <li class="nav"><html:link forward="connectionPool"><bean:message key="link.connectionPool"/></html:link></li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="performanceMonitor">
  <li class="nav"><html:link forward="performanceMonitor"><bean:message key="link.performanceMonitor"/></html:link></li>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="clientUpload">
  <li class="nav"><html:link forward="clientUpload"><bean:message key="link.clientUpload"/></html:link></li>
</mmj-admin:hasAccess>
</ul>
</div>

<div id="nav2">

<br/>

</div>

--%>
