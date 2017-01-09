<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.clientView"/>
		</td>
<logic:equal name="ClientFormMgr" property="client.active" value="true">
<mmj-mgr:hasAccess forward="clientEdit">
    <html:form action="/clientEdit.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
</logic:equal>  
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.name"/></th>
    <td align="left" width="75%"><bean:write name="ClientFormMgr" property="client.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.address"/></th>
    <td align="left">
	  <bean:write name="ClientFormMgr" property="client.address.address1"/>
	  <logic:notEmpty name="ClientFormMgr" property="client.address.address2">
		<br/><bean:write name="ClientFormMgr" property="client.address.address2"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="ClientFormMgr" property="client.address.address3">
		<br/><bean:write name="ClientFormMgr" property="client.address.address3"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="ClientFormMgr" property="client.address.address4">
		<br/><bean:write name="ClientFormMgr" property="client.address.address4"/>
	  </logic:notEmpty>		
	</td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.postalCode"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.address.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.country"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.countryName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.secretWordAtLogin"/></th>
    <td align="left">
      <logic:equal name="ClientFormMgr" property="client.secretWordAtLogin" value="true">
		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ClientFormMgr" property="client.secretWordAtLogin" value="true">
		<bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.autoActivateDefault"/></th>
    <td align="left">
      <logic:equal name="ClientFormMgr" property="client.autoActivateDefault" value="true">
		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ClientFormMgr" property="client.autoActivateDefault" value="true">
		<bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.telephoneNumber"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.telephoneNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.faxNumber"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.faxNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.vatNumber"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.vatNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.reference"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.reference"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.freeText"/></th>
    <td align="left">
    <bean:write name="ClientFormMgr" property="client.freeText"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label">
    	<bean:message key="label.accountContact" />
    </th>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactName"/></th>
    <td align="left"><bean:write name="ClientFormMgr"  property="client.accountContactName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactAddress"/></th>
    <td align="left">
    
	  <bean:write name="ClientFormMgr" property="client.accountContactAddress.address1"/>
	  <logic:notEmpty name="ClientFormMgr" property="client.accountContactAddress.address2">
		<br/><bean:write name="ClientFormMgr" property="client.accountContactAddress.address2"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="ClientFormMgr" property="client.accountContactAddress.address3">
		<br/><bean:write name="ClientFormMgr" property="client.accountContactAddress.address3"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="ClientFormMgr" property="client.accountContactAddress.address4">
		<br/><bean:write name="ClientFormMgr" property="client.accountContactAddress.address4"/>
	  </logic:notEmpty>		
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactPostalCode"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.accountContactAddress.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactCountry"/></th>
    <td align="left"><bean:write name="ClientFormMgr"  property="client.accountContactCountryName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactEmailAddress"/></th>
    <td align="left"><bean:write name="ClientFormMgr"  property="client.accountContactEmailAddress"/></td>
  </tr>
  <mmj-mgr:client var="client"/>
  <logic:notEmpty name="ClientFormMgr" property="client.logoFilename">
  <bean:define id="clientLogo" name="client" property="logoUrl" type="java.lang.String"/>
  <bean:define id="clientLogoWidth" name="client" property="logoWidth" type="java.lang.Integer"/>
  <bean:define id="clientLogoHeight" name="client" property="logoHeight" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.logo"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + clientLogo %>" width="<%= clientLogoWidth.toString() %>" height="<%= clientLogoHeight.toString() %>" />
    </td>
  </tr>
<!-- 
  <tr>
    <th align="left"><bean:message key="label.logoFilename"/></th>
    <td align="left">
      <bean:write name="ClientFormMgr" property="client.logoFilename"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logoWidth"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.logoWidth"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logoHeight"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.logoHeight"/></td>
  </tr>
-->
   </logic:notEmpty>
  <logic:notEmpty name="ClientFormMgr" property="client.logo2Filename">
  <bean:define id="clientLogo2" name="client" property="logo2Url" type="java.lang.String"/>
  <bean:define id="clientLogo2Width" name="client" property="logo2Width" type="java.lang.Integer"/>
  <bean:define id="clientLogo2Height" name="client" property="logo2Height" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.logo2"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + clientLogo2 %>" width="<%= clientLogo2Width.toString() %>" height="<%= clientLogo2Height.toString() %>" />
    </td>
  </tr>
<!-- 
  <tr>
    <th align="left"><bean:message key="label.logo2Filename"/></th>
    <td align="left">
      <bean:write name="ClientFormMgr" property="client.logo2Filename"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logo2Width"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.logo2Width"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logo2Height"/></th>
    <td align="left"><bean:write name="ClientFormMgr" property="client.logo2Height"/></td>
  </tr>
-->
  </logic:notEmpty>
</table>
