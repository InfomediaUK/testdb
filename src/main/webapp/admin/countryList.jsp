<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.countryList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="countryNew" >
<html:link forward="countryNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>

<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.isoCode" /></th>
  </tr>
  </thead>
  <logic:iterate id="country" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="country"/>
	<logic:notEqual name="country" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="countryView" >
      <html:link forward="countryView" paramId="country.countryId" paramName="country" paramProperty="countryId"><bean:write name="country" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="countryView" >
      <bean:write name="country" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="country" property="isoCode"/>
    </td>    
  </tr>
  </logic:iterate>
</table>
</logic:present>

<%-- requestURI is used for sorting it would be good if we didn't have to specifiy the .do --%>

<%--
<display:table name="ListFormAdmin.list" id="country" class="list" pagesize="5" 
               defaultsort="2" defaultorder="ascending" sort="list" requestURI="countryList.do">
  <display:column titleKey="label.action">
    <logic:equal name="country" property="active" value="true">
	    <html:link forward="countryEdit" paramId="country.countryId" paramName="country" paramProperty="countryId"><bean:message key="link.edit"/></html:link>&nbsp;
	    |
	    <html:link forward="countryDelete" paramId="country.countryId" paramName="country" paramProperty="countryId"><bean:message key="link.delete"/></html:link>
    </logic:equal>
    <logic:notEqual name="country" property="active" value="true">
      &nbsp;
    </logic:notEqual>
  </display:column>
  <display:column titleKey="label.name" sortable="true" >
    <html:link forward="countryView" paramId="country.countryId" paramName="country" paramProperty="countryId"><bean:write name="country" property="name"/></html:link>
  </display:column>
  <display:column property="isoCode" titleKey="label.isoCode" sortable="true"/>
  <display:column property="creationTimestamp" titleKey="label.created" sortable="true" />
  <display:column property="active" titleKey="label.active" sortable="true" />
</display:table>
--%>
