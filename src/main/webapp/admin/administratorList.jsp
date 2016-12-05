<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.administratorList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="administratorNew">
<html:link forward="administratorNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>

<%-- requestURI is used for sorting it would be good if we didn't have to specifiy the .do --%>

<%--

<display:table name="ListFormAdmin.list" id="administrator" class="list" pagesize="10" 
               defaultsort="2" defaultorder="ascending" sort="list" requestURI="administratorList.do">
  <display:column titleKey="label.action">
    <logic:equal name="administrator" property="active" value="true">
	    <html:link forward="administratorEdit" paramId="administrator.administratorId" paramName="administrator" paramProperty="administratorId"><bean:message key="link.edit"/></html:link>&nbsp;
	    |
	    <html:link forward="administratorDelete" paramId="administrator.administratorId" paramName="administrator" paramProperty="administratorId"><bean:message key="link.delete"/></html:link>
    </logic:equal>
    <logic:notEqual name="administrator" property="active" value="true">
      &nbsp;
    </logic:notEqual>
  </display:column>
  <display:column titleKey="label.firstName" sortable="true" >
    <html:link forward="administratorView" paramId="administrator.administratorId" paramName="administrator" paramProperty="administratorId"><bean:write name="administrator" property="user.firstName"/></html:link>
  </display:column>
  <display:column property="user.lastName" titleKey="label.lastName" sortable="true"/>
  <display:column property="user.emailAddress" titleKey="label.emailAddress" sortable="true" />
  <display:column property="user.login" titleKey="label.login" sortable="true" />
  <display:column property="creationTimestamp" titleKey="label.created" sortable="true" />
  <display:column property="active" titleKey="label.active" sortable="true" />
</display:table>

--%>

<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.emailAddress" /></th>
    <th align="left"><bean:message key="label.login" /></th>
  </tr>
  </thead>
  <logic:iterate id="administrator" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="administrator"/>
	<logic:notEqual name="administrator" property="active" value="true">
  	<bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="administratorView">
      <html:link forward="administratorView" paramId="administrator.administratorId" paramName="administrator" paramProperty="administratorId"><bean:write name="administrator" property="user.fullName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="administratorView">
      <bean:write name="administrator" property="user.fullName"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="administrator" property="user.emailAddress"/>
    </td>
    <td align="left">
      <bean:write name="administrator" property="user.login"/>
    </td>
  </tr>

  </logic:iterate>
</table>
</logic:present>







<%--

<br/>
<br/>

<logic:present name="ListFormAdmin" property="list">
  <logic:iterate id="administrator" name="ListFormAdmin" property="list">

    <html:link forward="administratorEdit" paramId="administrator.administratorId" paramName="administrator" paramProperty="administratorId"><bean:message key="link.edit"/></html:link>
    <html:link forward="administratorDelete" paramId="administrator.administratorId" paramName="administrator" paramProperty="administratorId"><bean:message key="link.delete"/></html:link>
    <bean:write name="administrator" property="administratorId"/>
    <bean:write name="administrator" property="user.firstName"/>
    <bean:write name="administrator" property="user.lastName"/>
    <bean:write name="administrator" property="user.emailAddress"/>
    <bean:write name="administrator" property="user.login"/>
    <bean:write name="administrator" property="user.pwd"/>
    <bean:write name="administrator" property="user.pwdHint"/>
    <bean:write name="administrator" property="auditAdministratorId"/>
    <bean:write name="administrator" property="auditTimestamp"/>
    <bean:write name="administrator" property="active"/>
    <bean:write name="administrator" property="noOfChanges"/>

    <br/>
  </logic:iterate>
</logic:present>

--%>

<mmj-admin:hasAccess forward="administratorAudits">
<br/>
<html:link forward="administratorAudits"><bean:message key="link.audits"/></html:link>
</mmj-admin:hasAccess>

