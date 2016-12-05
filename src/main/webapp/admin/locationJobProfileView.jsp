<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.locationJobProfileView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="LocationJobProfileViewFormAdmin" paramProperty="locationJobProfile.clientId"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.clientName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.clientName"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.clientCode"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.site"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="siteView">
      <html:link forward="siteView" paramId="site.siteId" paramName="LocationJobProfileViewFormAdmin" paramProperty="locationJobProfile.siteId"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.siteName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="siteView">
      <bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.siteName"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.siteCode"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.location"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="locationView">
      <html:link forward="locationView" paramId="location.locationId" paramName="LocationJobProfileViewFormAdmin" paramProperty="locationJobProfile.locationId"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.locationName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="locationView">
      <bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.locationName"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.locationCode"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobFamilyView">
      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="LocationJobProfileViewFormAdmin" paramProperty="locationJobProfile.jobFamilyId"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobFamilyName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobFamilyView">
      <bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobFamilyName"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobFamilyCode"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobSubFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobSubFamilyView">
      <html:link forward="jobSubFamilyView" paramId="jobSubFamily.jobSubFamilyId" paramName="LocationJobProfileViewFormAdmin" paramProperty="locationJobProfile.jobSubFamilyId"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobSubFamilyName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobSubFamilyView">
      <bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobSubFamilyName"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobSubFamilyCode"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobProfile"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobProfileView">
      <html:link forward="jobProfileView" paramId="jobProfile.jobProfileId" paramName="LocationJobProfileViewFormAdmin" paramProperty="locationJobProfile.jobProfileId"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobProfileName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobProfileView">
      <bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobProfileName"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.jobProfileCode"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.locationJobProfile"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.budget"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.budget" format="#,##0.00" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.rate" format="#,##0.00" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="LocationJobProfileViewFormAdmin" property="locationJobProfile.active"/></td>
  </tr>
</table>

<logic:equal name="LocationJobProfileViewFormAdmin" property="locationJobProfile.active" value="true">
<mmj-admin:hasAccess forward="locationJobProfileEdit" >
  <html:link forward="locationJobProfileEdit" paramId="locationJobProfile.locationJobProfileId" paramName="LocationJobProfileViewFormAdmin" paramProperty="locationJobProfile.locationJobProfileId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>
