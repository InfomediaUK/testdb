<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.clientEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="clientEditProcess.do" focus="client.name" enctype="multipart/form-data">

<html:hidden property="client.clientId"/>
<html:hidden property="client.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="client.name" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.nhsName"/></td>
    <td align="left"><html:text property="client.nhsName" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><html:text property="client.address.address1" size="50"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.address.address2" size="50"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.address.address3" size="50"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.address.address4" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.postalCode"/></td>
    <td align="left"><html:text property="client.address.postalCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="client.address.countryId" >
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.secretWordAtLogin"/></td>
    <td align="left"><html:checkbox property="client.secretWordAtLogin"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.autoActivateDefault"/></td>
    <td align="left"><html:checkbox property="client.autoActivateDefault"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftCommission"/></td>
    <td align="left"><html:checkbox property="client.upliftCommission"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.telephoneNumber"/></td>
    <td align="left"><html:text property="client.telephoneNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.faxNumber"/></td>
    <td align="left"><html:text property="client.faxNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logoFilename"/></td>
    <td align="left">
      <html:file property="logoFile" size="30" accept="image/jpg, image/jpeg"/>
      <html:hidden property="client.logoFilename" />
      <bean:write name="ClientFormAdmin" property="client.logoFilename"/>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logoWidth"/></td>
    <td align="left"><html:text property="client.logoWidth"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logoHeight"/></td>
    <td align="left"><html:text property="client.logoHeight"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logo2Filename"/></td>
    <td align="left">
      <html:file property="logo2File" size="30" />
      <html:hidden property="client.logo2Filename" />
      <bean:write name="ClientFormAdmin" property="client.logo2Filename"/>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logo2Width"/></td>
    <td align="left"><html:text property="client.logo2Width"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logo2Height"/></td>
    <td align="left"><html:text property="client.logo2Height"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.vatNumber"/></td>
    <td align="left"><html:text property="client.vatNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.reference"/></td>
    <td align="left"><html:text property="client.reference"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.clientFreeText"/></td>
    <td align="left"><html:textarea property="client.freeText" cols="50" rows="5"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.accountContactName"/></td>
    <td align="left"><html:text property="client.accountContactName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.accountContactAddress"/></td>
    <td align="left"><html:text property="client.accountContactAddress.address1" size="50"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.accountContactAddress.address2" size="50"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.accountContactAddress.address3" size="50"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.accountContactAddress.address4" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.accountContactPostalCode"/></td>
    <td align="left"><html:text property="client.accountContactAddress.postalCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.accountContactCountry"/></td>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="client.accountContactAddress.countryId" >
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.accountContactEmailAddress"/></td>
    <td align="left"><html:text property="client.accountContactEmailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.tradeshiftSbsPayablesCode"/></td>
    <td align="left"><html:text property="client.tradeshiftSbsPayablesCode" size="10"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.tradeshiftCompanyAccountId"/></td>
    <td align="left">
      <html:hidden property="client.tradeshiftCompanyAccountId" />
      <bean:write name="ClientFormAdmin" property="client.tradeshiftCompanyAccountId"/>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.tradeshiftState"/></td>
    <td align="left">
      <html:hidden property="client.tradeshiftState" />
      <bean:write name="ClientFormAdmin" property="client.tradeshiftState"/>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
