<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/clientEditProcess.do" focus="client.name" onsubmit="return singleSubmit();" enctype="multipart/form-data">
<html:hidden property="client.clientId"/>
<html:hidden property="client.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.clientEdit"/>
		</td>
    <mmj-agy:hasAccess forward="clientEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.name"/></th>
    <td align="left" width="75%"><html:text property="client.name" size="50" /></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.nhsName"/></th>
    <td align="left"><html:text property="client.nhsName" size="50" /></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.address"/></th>
    <td align="left"><html:text property="client.address.address1"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="client.address.address2"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="client.address.address3"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="client.address.address4"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.postalCode"/></th>
    <td align="left"><html:text property="client.address.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.country"/></th>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="client.address.countryId" >
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <%/* probably shouldn't be allowed to change this - here for now for the spare ones */%>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><html:text property="client.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.secretWordAtLogin"/></th>
    <td align="left"><html:checkbox property="client.secretWordAtLogin"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.autoActivateDefault"/></th>
    <td align="left"><html:checkbox property="client.autoActivateDefault"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.upliftCommission"/></th>
    <td align="left"><html:checkbox property="client.upliftCommission"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.telephoneNumber"/></th>
    <td align="left"><html:text property="client.telephoneNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.faxNumber"/></th>
    <td align="left"><html:text property="client.faxNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.vatNumber"/></th>
    <td align="left"><html:text property="client.vatNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.reference"/></th>
    <td align="left"><html:text property="client.reference"/></td>
  </tr>
  <tr>
    <th align="left" valign="top"><bean:message key="label.clientFreeText"/></th>
    <td align="left"><html:textarea property="client.freeText" cols="75" rows="5"/></td>
  </tr>
  <tr>
    <th align="left" valign="top"><bean:message key="label.agencyWorkerChecklistOther"/></th>
    <td align="left"><html:textarea property="client.agencyWorkerChecklistOther" cols="75" rows="2"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <th align="left"><bean:message key="label.accountContact"/></th>
  <tr>
    <th align="left"><bean:message key="label.accountContactName"/></th>
    <td align="left"><html:text property="client.accountContactName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactAddress"/></th>
    <td align="left"><html:text property="client.accountContactAddress.address1"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="client.accountContactAddress.address2"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="client.accountContactAddress.address3"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="client.accountContactAddress.address4"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactPostalCode"/></th>
    <td align="left"><html:text property="client.accountContactAddress.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactCountry"/></th>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="client.accountContactAddress.countryId" >
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactEmailAddress"/></th>
    <td align="left"><html:text property="client.accountContactEmailAddress"/></td>
  </tr>
  <logic:notEmpty name="ClientFormAgy" property="client.logoFilename">
  <bean:define id="clientLogo" name="ClientFormAgy" property="client.logoUrl" type="java.lang.String"/>
  <bean:define id="clientLogoWidth" name="ClientFormAgy" property="client.logoWidth" type="java.lang.Integer"/>
  <bean:define id="clientLogoHeight" name="ClientFormAgy" property="client.logoHeight" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.logo"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + clientLogo %>" width="<%= clientLogoWidth.toString() %>" height="<%= clientLogoHeight.toString() %>" />
    </td>
  </tr>
  </logic:notEmpty>
  <tr>
    <th align="left"><bean:message key="label.logoFilename"/></th>
    <td align="left">
      <html:file property="logoFile" size="30"/>
      <html:hidden property="client.logoFilename" />
      <bean:write name="ClientFormAgy" property="client.logoFilename"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logoWidth"/></th>
    <td align="left"><html:text property="client.logoWidth"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logoHeight"/></th>
    <td align="left"><html:text property="client.logoHeight"/></td>
  </tr>
  <logic:notEmpty name="ClientFormAgy" property="client.logo2Filename">
  <bean:define id="clientLogo2" name="ClientFormAgy" property="client.logo2Url" type="java.lang.String"/>
  <bean:define id="clientLogo2Width" name="ClientFormAgy" property="client.logo2Width" type="java.lang.Integer"/>
  <bean:define id="clientLogo2Height" name="ClientFormAgy" property="client.logo2Height" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.logo2"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + clientLogo2 %>" width="<%= clientLogo2Width.toString() %>" height="<%= clientLogo2Height.toString() %>" />
    </td>
  </tr></logic:notEmpty>
  <tr>
    <th align="left"><bean:message key="label.logo2Filename"/></th>
    <td align="left">
      <html:file property="logo2File" size="30" />
      <html:hidden property="client.logo2Filename" />
      <bean:write name="ClientFormAgy" property="client.logo2Filename"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logo2Width"/></th>
    <td align="left"><html:text property="client.logo2Width"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logo2Height"/></th>
    <td align="left"><html:text property="client.logo2Height"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftSbsPayablesCode"/></th>
    <td align="left"><html:text property="client.tradeshiftSbsPayablesCode" size="10"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftCompanyAccountId"/></th>
    <td align="left">
      <html:hidden property="client.tradeshiftCompanyAccountId"/>
      <bean:write name="ClientFormAgy" property="client.tradeshiftCompanyAccountId"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftState"/></th>
    <td align="left">
      <html:hidden property="client.tradeshiftState"/>
      <bean:write name="ClientFormAgy" property="client.tradeshiftState"/>
    </td>
  </tr>
</html:form>
</table>
  