<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.agencyEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="agencyEditProcess.do" focus="agency.name" enctype="multipart/form-data">

<html:hidden property="agency.agencyId"/>
<html:hidden property="agency.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="agency.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><html:text property="agency.address.address1"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="agency.address.address2"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="agency.address.address3"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="agency.address.address4"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.postalCode"/></td>
    <td align="left"><html:text property="agency.address.postalCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="agency.address.countryId" >
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="agency.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.telephoneNumber"/></td>
    <td align="left"><html:text property="agency.telephoneNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.faxNumber"/></td>
    <td align="left"><html:text property="agency.faxNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logoFilename"/></td>
    <td align="left">
      <html:file property="logoFile" size="30" />
      <html:hidden property="agency.logoFilename" />
      <bean:write name="AgencyFormAdmin" property="agency.logoFilename"/>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logoWidth"/></td>
    <td align="left"><html:text property="agency.logoWidth"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.logoHeight"/></td>
    <td align="left"><html:text property="agency.logoHeight"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.vatNumber"/></td>
    <td align="left"><html:text property="agency.vatNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.reference"/></td>
    <td align="left"><html:text property="agency.reference"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.agencyFreeText"/></td>
    <td align="left"><html:textarea property="agency.freeText" cols="75" rows="10"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.agencyFreeText2"/></td>
    <td align="left"><html:textarea property="agency.freeText2" cols="75" rows="10"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.invoiceCreditFreeText"/></td>
    <td align="left"><html:textarea property="agency.invoiceCreditFreeText" cols="75" rows="10"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.invoiceCreditFooterFreeText"/></td>
    <td align="left"><html:textarea property="agency.invoiceCreditFooterFreeText" cols="75" rows="10"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.paymentTermsText"/></td>
    <td align="left"><html:text property="agency.paymentTermsText" size="75"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.bankDetailsText"/></td>
    <td align="left"><html:textarea property="agency.bankDetailsText" cols="75" rows="5"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.invoiceLogoFilename"/></td>
    <td align="left">
      <html:file property="invoiceLogoFile" size="30" />
      <html:hidden property="agency.invoiceLogoFilename" />
      <bean:write name="AgencyFormAdmin" property="agency.invoiceLogoFilename"/>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.invoiceLogoWidth"/></td>
    <td align="left"><html:text property="agency.invoiceLogoWidth"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.invoiceLogoHeight"/></td>
    <td align="left"><html:text property="agency.invoiceLogoHeight"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.payrollContactName"/></td>
    <td align="left"><html:text property="agency.payrollContactName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.payrollContactEmailAddress"/></td>
    <td align="left"><html:text property="agency.payrollContactEmailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.payrollContactTelephoneNumber"/></td>
    <td align="left"><html:text property="agency.payrollContactTelephoneNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.payrollContactFaxNumber"/></td>
    <td align="left"><html:text property="agency.payrollContactFaxNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.websiteAddress"/></td>
    <td align="left"><html:text property="agency.websiteAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.clientConfirmationEmailFreeText"/></td>
    <td align="left"><html:textarea property="agency.clientConfirmationEmailFreeText" cols="75" rows="10"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.applicantConfirmationEmailFreeText"/></td>
    <td align="left"><html:textarea property="agency.applicantConfirmationEmailFreeText" cols="75" rows="10"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.agencyAdminEmailAddress"/></td>
    <td align="left"><html:text property="agency.agencyAdminEmailAddress" size="40" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.tradeshiftConsumerKey"/></th>
    <td align="left"><html:text property="agency.tradeshiftConsumerKey" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.tradeshiftConsumerSecret"/></th>
    <td align="left"><html:text property="agency.tradeshiftConsumerSecret" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.tradeshiftTokenKey"/></th>
    <td align="left"><html:text property="agency.tradeshiftTokenKey" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.tradeshiftTokenSecret"/></th>
    <td align="left"><html:text property="agency.tradeshiftTokenSecret" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.tradeshiftTenantId"/></th>
    <td align="left"><html:text property="agency.tradeshiftTenantId" size="50"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
