<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.jobSubFamilyView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="JobSubFamilyViewFormAdmin" paramProperty="client.clientId"><bean:write name="JobSubFamilyViewFormAdmin" property="client.name"/></html:link>&nbsp;
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="JobSubFamilyViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobFamilyView">
      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="JobSubFamilyViewFormAdmin" paramProperty="jobFamily.jobFamilyId"><bean:write name="JobSubFamilyViewFormAdmin" property="jobFamily.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="JobSubFamilyViewFormAdmin" property="jobFamily.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="jobFamily.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="jobFamily.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="jobFamily.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobSubFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="jobSubFamily.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="jobSubFamily.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="jobSubFamily.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobSubFamilyViewFormAdmin" property="jobSubFamily.active"/></td>
  </tr>
</table>

<logic:equal name="JobSubFamilyViewFormAdmin" property="jobSubFamily.active" value="true">
<mmj-admin:hasAccess forward="jobSubFamilyEdit">
  <html:link forward="jobSubFamilyEdit" paramId="jobSubFamily.jobSubFamilyId" paramName="JobSubFamilyViewFormAdmin" paramProperty="jobSubFamily.jobSubFamilyId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobSubFamilyDelete">
  <html:link forward="jobSubFamilyDelete" paramId="jobSubFamily.jobSubFamilyId" paramName="JobSubFamilyViewFormAdmin" paramProperty="jobSubFamily.jobSubFamilyId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>

<br/>
<br/>

<bean:message key="title.jobProfileList"/>&nbsp;
<mmj-admin:hasAccess forward="jobProfileNew">
<html:link forward="jobProfileNew" paramId="jobProfile.jobSubFamilyId" paramName="JobSubFamilyViewFormAdmin" paramProperty="jobSubFamily.jobSubFamilyId"><bean:message key="link.new"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobProfileOrder">
<html:link forward="jobProfileOrder" paramId="jobSubFamily.jobSubFamilyId" paramName="JobSubFamilyViewFormAdmin" paramProperty="jobSubFamily.jobSubFamilyId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.nhsAssignment" /></th>
<%--
    <th align="left"><bean:message key="label.rate" /></th>
--%>
    <th align="left"><bean:message key="label.autoFillDefault" /></th>
  </tr>
  </thead>
  <logic:iterate id="jobProfile" name="JobSubFamilyViewFormAdmin" property="jobSubFamily.jobProfiles">
	<bean:define id="trClass" value="jobProfile"/>
	<logic:notEqual name="jobProfile" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="jobProfileView">
      <html:link forward="jobProfileView" paramId="jobProfile.jobProfileId" paramName="jobProfile" paramProperty="jobProfileId"><bean:write name="jobProfile" property="name"/></html:link>&nbsp;
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobProfileView">
      <bean:write name="jobProfile" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="jobProfile" property="code"/>
    </td>
    <td align="left">
      <bean:write name="jobProfile" property="nhsAssignment"/>
    </td>
<%--
    <td align="left">
      <bean:write name="jobProfile" property="rate"/>
    </td>
--%>
    <td align="left">
      <bean:write name="jobProfile" property="autoFill"/>
    </td>
  </tr>
  </logic:iterate>
</table>
