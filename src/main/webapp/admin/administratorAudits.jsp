<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.administratorAudits"/>

<br/>
<br/>

<%-- requestURI is used for sorting it would be good if we didn't have to specifiy the .do --%>

<display:table name="ListFormAdmin.list" id="administrator" class="simple" requestURI="administratorAudits.do">
  <display:column property="administratorId" titleKey="label.id" sortable="true" />
  <display:column property="user.firstName" titleKey="label.firstName" sortable="true" />
  <display:column property="user.lastName" titleKey="label.lastName" sortable="true"/>
  <display:column property="user.emailAddress" titleKey="label.emailAddress" sortable="true" />
  <display:column property="user.login" titleKey="label.login" sortable="true" />
  <display:column property="user.pwd" titleKey="label.pwd" sortable="true" />
  <display:column property="user.pwdHint" titleKey="label.pwdHint" sortable="true" />
  <display:column property="creationTimestamp" titleKey="label.created" sortable="true" />
  <display:column property="auditorId" titleKey="label.auditor" sortable="true" />
  <display:column property="auditTimestamp" titleKey="label.timestamp" sortable="true" />
  <display:column property="active" titleKey="label.active" sortable="true" />
</display:table>

<br/>
