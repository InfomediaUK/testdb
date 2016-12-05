<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.jobFamilyView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="JobFamilyViewFormAdmin" paramProperty="client.clientId"><bean:write name="JobFamilyViewFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="JobFamilyViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="JobFamilyViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="JobFamilyViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobFamilyViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobFamilyViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="JobFamilyViewFormAdmin" property="jobFamily.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobFamilyViewFormAdmin" property="jobFamily.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="JobFamilyViewFormAdmin" property="jobFamily.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobFamilyViewFormAdmin" property="jobFamily.active"/></td>
  </tr>
</table>

<logic:equal name="JobFamilyViewFormAdmin" property="jobFamily.active" value="true">
<mmj-admin:hasAccess forward="jobFamilyEdit">
  <html:link forward="jobFamilyEdit" paramId="jobFamily.jobFamilyId" paramName="JobFamilyViewFormAdmin" paramProperty="jobFamily.jobFamilyId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobFamilyDelete">
  <html:link forward="jobFamilyDelete" paramId="jobFamily.jobFamilyId" paramName="JobFamilyViewFormAdmin" paramProperty="jobFamily.jobFamilyId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>

<br/>
<br/>

<bean:message key="title.jobSubFamilyList"/>&nbsp;
<mmj-admin:hasAccess forward="jobSubFamilyNew">
<html:link forward="jobSubFamilyNew" paramId="jobSubFamily.jobFamilyId" paramName="JobFamilyViewFormAdmin" paramProperty="jobFamily.jobFamilyId"><bean:message key="link.new"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobSubFamilyOrder">
<html:link forward="jobSubFamilyOrder" paramId="jobFamily.jobFamilyId" paramName="JobFamilyViewFormAdmin" paramProperty="jobFamily.jobFamilyId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
  <logic:iterate id="jobSubFamily" name="JobFamilyViewFormAdmin" property="jobFamily.jobSubFamilies">
	<bean:define id="trClass" value="jobSubFamily"/>
	<logic:notEqual name="jobSubFamily" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="jobSubFamilyView">
      <html:link forward="jobSubFamilyView" paramId="jobSubFamily.jobSubFamilyId" paramName="jobSubFamily" paramProperty="jobSubFamilyId"><bean:write name="jobSubFamily" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobSubFamilyView">
      <bean:write name="jobSubFamily" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="jobSubFamily" property="code"/>
    </td>
  </tr>
  </logic:iterate>
</table>
