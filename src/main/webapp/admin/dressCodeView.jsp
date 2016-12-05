<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.dressCodeView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="DressCodeFormAdmin" paramProperty="client.clientId"><bean:write name="DressCodeFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="DressCodeFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.site"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="siteView">
      <html:link forward="siteView" paramId="site.siteId" paramName="DressCodeFormAdmin" paramProperty="site.siteId"><bean:write name="DressCodeFormAdmin" property="site.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="siteView">
      <bean:write name="DressCodeFormAdmin" property="site.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="site.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="site.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="site.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="site.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.location"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="locationView">
      <html:link forward="locationView" paramId="location.locationId" paramName="DressCodeFormAdmin" paramProperty="location.locationId"><bean:write name="DressCodeFormAdmin" property="location.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="locationView">
      <bean:write name="DressCodeFormAdmin" property="location.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="location.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="location.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="location.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.dressCode"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="dressCode.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="dressCode.active"/></td>
  </tr>
</table>

<logic:equal name="DressCodeFormAdmin" property="dressCode.active" value="true">
<mmj-admin:hasAccess forward="dressCodeEdit">
  <html:link forward="dressCodeEdit" paramId="dressCode.dressCodeId" paramName="DressCodeFormAdmin" paramProperty="dressCode.dressCodeId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="dressCodeDelete">
  <html:link forward="dressCodeDelete" paramId="dressCode.dressCodeId" paramName="DressCodeFormAdmin" paramProperty="dressCode.dressCodeId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
