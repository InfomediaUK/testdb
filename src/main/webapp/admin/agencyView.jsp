<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.agencyView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.agency"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AgencyViewFormAdmin" property="agency.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="AgencyViewFormAdmin" property="agency.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="AgencyViewFormAdmin" property="agency.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="AgencyViewFormAdmin" property="agency.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="AgencyViewFormAdmin" property="agency.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AgencyViewFormAdmin" property="agency.active"/></td>
  </tr>
</table>

<logic:equal name="AgencyViewFormAdmin" property="agency.active" value="true">
<mmj-admin:hasAccess forward="agencyEdit">
  <html:link forward="agencyEdit" paramId="agency.agencyId" paramName="AgencyViewFormAdmin" paramProperty="agency.agencyId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="agencyDelete">
  <html:link forward="agencyDelete" paramId="agency.agencyId" paramName="AgencyViewFormAdmin" paramProperty="agency.agencyId"><bean:message key="link.delete"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="agencyApplicantCopy">
  <html:link forward="agencyApplicantCopy" paramId="targetAgencyId" paramName="AgencyViewFormAdmin" paramProperty="agency.agencyId"><bean:message key="link.agencyApplicantCopy"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>

<br/>
<br/>

<bean:message key="title.consultantList"/>&nbsp;
<mmj-admin:hasAccess forward="consultantNew">
<html:link forward="consultantNew" paramId="consultant.agencyId" paramName="AgencyViewFormAdmin" paramProperty="agency.agencyId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.emailAddress" /></th>
    <th align="left"><bean:message key="label.login" /></th>
    <th align="left"><bean:message key="label.jobTitle" /></th>
    <th align="left"><bean:message key="label.canViewDocuments"/></th>
    <th align="left"><bean:message key="label.canViewWages"/></th>
  </tr>
  </thead>
	<logic:iterate id="consultant" name="AgencyViewFormAdmin" property="agency.consultants">
	<bean:define id="trClass" value="consultant"/>
	<logic:notEqual name="consultant" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
	  <td align="left">
    <mmj-admin:hasAccess forward="consultantView">
      <html:link forward="consultantView" paramId="consultant.consultantId" paramName="consultant" paramProperty="consultantId"><bean:write name="consultant" property="user.fullName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="consultantView">
      <bean:write name="consultant" property="user.fullName"/>
    </mmj-admin:hasNoAccess>    
	  </td>
	  <td align="left">
	    <bean:write name="consultant" property="user.emailAddress"/>
	  </td>
	  <td align="left">
	    <bean:write name="consultant" property="user.login"/>
	  </td>
	  <td align="left">
	    <bean:write name="consultant" property="jobTitle"/>
	  </td>
	  <td align="left">
	    <bean:write name="consultant" property="canViewDocuments"/>
	  </td>
	  <td align="left">
	    <bean:write name="consultant" property="canViewWages"/>
	  </td>
	</tr>
	</logic:iterate>  
</table>

<br/>

<bean:message key="title.agyAccessGroupList"/>&nbsp;
<mmj-admin:hasAccess forward="agyAccessGroupNew">
<html:link forward="agyAccessGroupNew" paramId="agyAccessGroup.agencyId" paramName="AgencyViewFormAdmin" paramProperty="agency.agencyId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
  </tr>
  </thead>
	<logic:iterate id="agyAccessGroup" name="AgencyViewFormAdmin" property="agency.agyAccessGroups">
	<bean:define id="trClass" value="agyAccessGroup"/>
	<logic:notEqual name="agyAccessGroup" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="agyAccessGroupView">
      <html:link forward="agyAccessGroupView" paramId="agyAccessGroup.agyAccessGroupId" paramName="agyAccessGroup" paramProperty="agyAccessGroupId"><bean:write name="agyAccessGroup" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="agyAccessGroupView">
      <bean:write name="agyAccessGroup" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  </logic:iterate>
</table>


<br/>

<bean:message key="title.clientList"/>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
	<logic:iterate id="clientAgencyUser" name="AgencyViewFormAdmin" property="agency.clientAgencyUsers">
	<bean:define id="trClass" value="agyAccessGroup"/>
	<logic:notEqual name="clientAgencyUser" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="clientAgencyUser" paramProperty="clientId"><bean:write name="clientAgencyUser" property="clientName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="clientAgencyUser" property="clientName"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="clientAgencyUser" property="clientCode"/>
    </td>
  </tr>
  </logic:iterate>
</table>

