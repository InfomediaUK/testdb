<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.countryView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="CountryFormAdmin" property="country.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.isoCode"/></td>
    <td align="left"><bean:write name="CountryFormAdmin" property="country.isoCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="CountryFormAdmin" property="country.active"/></td>
  </tr>
</table>

<logic:equal name="CountryFormAdmin" property="country.active" value="true">
<mmj-admin:hasAccess forward="countryEdit" >
  <html:link forward="countryEdit" paramId="country.countryId" paramName="CountryFormAdmin" paramProperty="country.countryId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="countryDelete" >
  <html:link forward="countryDelete" paramId="country.countryId" paramName="CountryFormAdmin" paramProperty="country.countryId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
