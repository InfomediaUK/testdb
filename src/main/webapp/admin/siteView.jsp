<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.siteView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="SiteViewFormAdmin" paramProperty="client.clientId"><bean:write name="SiteViewFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="SiteViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.site"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="site.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="site.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="site.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="site.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="SiteViewFormAdmin" property="site.active"/></td>
  </tr>
</table>

<logic:equal name="SiteViewFormAdmin" property="site.active" value="true">
<mmj-admin:hasAccess forward="siteEdit">
  <html:link forward="siteEdit" paramId="site.siteId" paramName="SiteViewFormAdmin" paramProperty="site.siteId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="siteDelete">
  <html:link forward="siteDelete" paramId="site.siteId" paramName="SiteViewFormAdmin" paramProperty="site.siteId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
<br/>
<br/>
<bean:message key="title.locationList"/>&nbsp;
<mmj-admin:hasAccess forward="locationNew">
<html:link forward="locationNew" paramId="location.siteId" paramName="SiteViewFormAdmin" paramProperty="site.siteId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="locationOrder">
<html:link forward="locationOrder" paramId="site.siteId" paramName="SiteViewFormAdmin" paramProperty="site.siteId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.description" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
	<logic:iterate id="location" name="SiteViewFormAdmin" property="site.locations">
	<bean:define id="trClass" value="location"/>
	<logic:notEqual name="location" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
	  <td align="left">
    <mmj-admin:hasAccess forward="locationView">
      <html:link forward="locationView" paramId="location.locationId" paramName="location" paramProperty="locationId"><bean:write name="location" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="locationView">
      <bean:write name="location" property="name"/>
    </mmj-admin:hasNoAccess>    
	  </td>
	  <td align="left">
	    <bean:write name="location" property="description"/>
	  </td>
	  <td align="left">
	    <bean:write name="location" property="code"/>
	  </td>
	</tr>
	</logic:iterate>  
</table>
