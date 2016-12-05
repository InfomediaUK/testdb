<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.clientList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="clientNew">
<html:link forward="clientNew"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="clientOrder">
<html:link forward="clientOrder"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>

<%-- requestURI is used for sorting it would be good if we didn't have to specifiy the .do --%>

<%--
<display:table name="ListFormAdmin.list" id="client" class="list" pagesize="3" 
               defaultsort="2" defaultorder="ascending" sort="list" requestURI="clientList.do">

--%>

<%--
  <display:column titleKey="label.action">
    <logic:equal name="client" property="active" value="true">
	    <html:link forward="clientEdit" paramId="client.clientId" paramName="client" paramProperty="clientId"><bean:message key="link.edit"/></html:link>&nbsp;
	    |
	    <html:link forward="clientDelete" paramId="client.clientId" paramName="client" paramProperty="clientId"><bean:message key="link.delete"/></html:link>
    </logic:equal>
    <logic:notEqual name="client" property="active" value="true">
      &nbsp;
    </logic:notEqual>
  </display:column>
--%>

<%--
  <display:column titleKey="label.name" sortable="true" >
    <html:link forward="clientView" paramId="client.clientId" paramName="client" paramProperty="clientId"><bean:write name="client" property="name"/></html:link>
  </display:column>
  <display:column titleKey="label.address">
    <bean:write name="client" property="address.fullAddress"/>
  </display:column>
  <display:column property="countryName" titleKey="label.country" sortable="true" />
  <display:column property="creationTimestamp" titleKey="label.created" sortable="true" />
  <display:column property="active" titleKey="label.active" sortable="true" />
</display:table>

<br/>
<br/>
<br/>

--%>

<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
<%--
    <th align="left"><bean:message key="label.action" /></th>
--%>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.address" /></th>
    <th align="left"><bean:message key="label.country" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
  <logic:iterate id="client" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="client"/>
	<logic:notEqual name="client" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
<%--
    <td align="left">
    <logic:equal name="client" property="active" value="true">
	    <html:link forward="clientEdit" paramId="client.clientId" paramName="client" paramProperty="clientId"><bean:message key="link.edit"/></html:link>
	    |
	    <html:link forward="clientDelete" paramId="client.clientId" paramName="client" paramProperty="clientId"><bean:message key="link.delete"/></html:link>
    </logic:equal>
    <logic:notEqual name="client" property="active" value="true">
      &nbsp;
    </logic:notEqual>
    </td>
--%>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="client" paramProperty="clientId"><bean:write name="client" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="client" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="client" property="address.fullAddress"/>
    </td>
    <td align="left">
      <bean:write name="client" property="countryName"/>
    </td>
    <td align="left">
      <bean:write name="client" property="code"/>
    </td>
  </tr>

<%--
  <logic:notEmpty name="client" property="sites">
  <logic:iterate id="site" name="client" property="sites">
  <tr class="site">
--%>

<%--
    <td align="left">
    <logic:equal name="site" property="active" value="true">
	    <html:link forward="siteEdit" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:message key="link.edit"/></html:link>
	    |
	    <html:link forward="siteDelete" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:message key="link.delete"/></html:link>
    </logic:equal>
    <logic:notEqual name="site" property="active" value="true">
      &nbsp;
    </logic:notEqual>
    </td>
--%>

<%--
    <td align="left">
      &nbsp;&nbsp;<html:link forward="siteView" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:write name="site" property="name"/></html:link>
    </td>
    <td align="left">
      <bean:write name="site" property="address.fullAddress"/>
    </td>
    <td align="left">
      <bean:write name="site" property="countryName"/>
    </td>
  </tr>
  </logic:iterate>  
  </logic:notEmpty>

  <logic:notEmpty name="client" property="managers">
  <logic:iterate id="manager" name="client" property="managers">
  <tr class="manager">
    <td align="left" colspan="3">
      <bean:write name="manager" property="user.firstName"/>
    </td>
  </tr>
  </logic:iterate>  
  </logic:notEmpty>
--%>

  </logic:iterate>
</table>
</logic:present>
