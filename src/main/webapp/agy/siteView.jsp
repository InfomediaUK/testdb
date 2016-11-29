<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.siteView"/>
		</td>
<mmj-agy:hasAccess forward="siteEdit">
    <html:form action="/siteEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="site.siteId" value="<bean:write name="SiteViewFormAgy" property="site.siteId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="siteDelete">
    <html:form action="/siteDelete.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="site.siteId" value="<bean:write name="SiteViewFormAgy" property="site.siteId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="siteView">
    <html:form action="/siteView.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="site.clientId" value="<bean:write name="SiteViewFormAgy" property="site.clientId"/>"/>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
</mmj-agy:hasAccess>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.client"/></th>
    <td align="left" colspan="3">
  	  <html:link forward="clientView" paramId="client.clientId" paramName="SiteViewFormAgy" paramProperty="site.clientId"><bean:write name="SiteViewFormAgy" property="site.clientName"/></html:link>
    </td>
  </tr>
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><bean:write name="SiteViewFormAgy" property="site.name"/></td>
  </tr>
  <tr>
    <th align="left" width="35%"><bean:message key="label.nhsLocation"/></th>
	<logic:equal name="SiteViewFormAgy" property="site.nhsMatched" value="true">
    <td align="left" width="65%" class="nhsMatched">
  </logic:equal>
	<logic:notEqual name="SiteViewFormAgy" property="site.nhsMatched" value="true">
    <td align="left" width="65%">
  </logic:notEqual>
      <bean:write name="SiteViewFormAgy" property="site.nhsLocation"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.address"/></th>
    <td align="left">
	  <bean:write name="SiteViewFormAgy" property="site.address.address1"/>
	  <logic:notEmpty name="SiteViewFormAgy" property="site.address.address2">
		<br/><bean:write name="SiteViewFormAgy" property="site.address.address2"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="SiteViewFormAgy" property="site.address.address3">
		<br/><bean:write name="SiteViewFormAgy" property="site.address.address3"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="SiteViewFormAgy" property="site.address.address4">
		<br/><bean:write name="SiteViewFormAgy" property="site.address.address4"/>
	  </logic:notEmpty>		
	</td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.postalCode"/></th>
    <td align="left"><bean:write name="SiteViewFormAgy" property="site.address.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.country"/></th>
    <td align="left"><bean:write name="SiteViewFormAgy" property="site.countryName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><bean:write name="SiteViewFormAgy" property="site.code"/></td>
  </tr>
</table>




<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.siteLocationList"/>
		</td>
<mmj-agy:hasAccess forward="locationNew">
    <html:form action="/locationNew.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.siteId" value="<bean:write name="SiteViewFormAgy" property="site.siteId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="locationOrder">
    <html:form action="/locationOrder.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="site.siteId" value="<bean:write name="SiteViewFormAgy" property="site.siteId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.order"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
	</tr>
</table>

<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.nhsWard" /></th>
    <th align="left">NHS Day</th>
    <th align="left"><bean:message key="label.description" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
	<logic:iterate id="location" name="SiteViewFormAgy" property="site.locations">
	<bean:define id="trClass" value="location"/>
	<logic:notEqual name="location" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
	  <td align="left">
    <mmj-agy:hasAccess forward="locationView">
      <html:link forward="locationView" paramId="location.locationId" paramName="location" paramProperty="locationId"><bean:write name="location" property="name"/></html:link>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="locationView">
      <bean:write name="location" property="name"/>
    </mmj-agy:hasNoAccess>    
	  </td>
	<logic:equal name="location" property="nhsMatched" value="true">
    <td align="left" class="nhsMatched">
  </logic:equal>
	<logic:notEqual name="location" property="nhsMatched" value="true">
    <td align="left">
  </logic:notEqual>
	    <bean:write name="location" property="nhsWard"/>
	  </td>
	  <td align="left">
	<logic:equal name="location" property="nhsDayTimesEntered" value="true">
	    <bean:write name="location" property="nhsDayStartTime" format="HH:mm"/> - <bean:write name="location" property="nhsDayEndTime" format="HH:mm"/>
	</logic:equal>
	<logic:notEqual name="location" property="nhsDayTimesEntered" value="true">
	    &nbsp;
	</logic:notEqual>
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

