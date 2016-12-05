<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.agencyList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="agencyNew">
<html:link forward="agencyNew"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="agencyOrder">
&nbsp;
<html:link forward="agencyOrder"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>

<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.address" /></th>
    <th align="left"><bean:message key="label.country" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
  <logic:iterate id="agency" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="agency"/>
	<logic:notEqual name="agency" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="agencyView">
      <html:link forward="agencyView" paramId="agency.agencyId" paramName="agency" paramProperty="agencyId"><bean:write name="agency" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="agencyView">
      <bean:write name="agency" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="agency" property="address.fullAddress"/>
    </td>
    <td align="left">
      <bean:write name="agency" property="countryName"/>
    </td>
    <td align="left">
      <bean:write name="agency" property="code"/>
    </td>
  </tr>
  </logic:iterate>
</table>
</logic:present>
