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
<html:form action="/agencyEditProcess.do" focus="agency.name" onsubmit="return singleSubmit();" enctype="multipart/form-data">
<html:hidden property="agency.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.agencyEdit"/>
		</td>
    <mmj-agy:hasAccess forward="agencyEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.name"/></th>
    <td align="left" width="75%"><html:text property="agency.name" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.address"/></th>
    <td align="left"><html:text property="agency.address.address1" size="30"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="agency.address.address2" size="30"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="agency.address.address3" size="30"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="agency.address.address4" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.postalCode"/></th>
    <td align="left"><html:text property="agency.address.postalCode" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.country"/></th>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="agency.address.countryId">
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <%/* probably shouldn't be allowed to change this - here for now for the spare ones */%>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><html:text property="agency.code" size="10"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.telephoneNumber"/></th>
    <td align="left"><html:text property="agency.telephoneNumber" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.faxNumber"/></th>
    <td align="left"><html:text property="agency.faxNumber" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.vatNumber"/></th>
    <td align="left"><html:text property="agency.vatNumber" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.reference"/></th>
    <td align="left"><html:text property="agency.reference" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.websiteAddress"/></th>
    <td align="left"><html:text property="agency.websiteAddress" size="30"/></td>
  </tr>
  <mmj-agy:agency var="agency"/>
  <logic:notEmpty name="AgencyFormAgy" property="agency.logoFilename">
  <bean:define id="agencyLogo" name="agency" property="logoUrl" type="java.lang.String"/>
  <bean:define id="agencyLogoWidth" name="agency" property="logoWidth" type="java.lang.Integer"/>
  <bean:define id="agencyLogoHeight" name="agency" property="logoHeight" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.logo"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + agencyLogo %>" width="<%= agencyLogoWidth.toString() %>" height="<%= agencyLogoHeight.toString() %>" />
    </td>
  </tr>
  </logic:notEmpty>
  <tr>
    <th align="left"><bean:message key="label.logoFilename"/></th>
    <td align="left">
      <html:file property="logoFile" size="30"/>
      <html:hidden property="agency.logoFilename" />
      <bean:write name="AgencyFormAgy" property="agency.logoFilename"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logoWidth"/></th>
    <td align="left"><html:text property="agency.logoWidth" size="10"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logoHeight"/></th>
    <td align="left"><html:text property="agency.logoHeight" size="10"/></td>
  </tr>
  <logic:notEmpty name="AgencyFormAgy" property="agency.invoiceLogoFilename">
  <bean:define id="agencyInvoiceLogo" name="agency" property="invoiceLogoUrl" type="java.lang.String"/>
  <bean:define id="agencyInvoiceLogoWidth" name="agency" property="invoiceLogoWidth" type="java.lang.Integer"/>
  <bean:define id="agencyInvoiceLogoHeight" name="agency" property="invoiceLogoHeight" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.invoiceLogo"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + agencyInvoiceLogo %>" width="<%= agencyInvoiceLogoWidth.toString() %>" height="<%= agencyInvoiceLogoHeight.toString() %>" />
    </td>
  </tr></logic:notEmpty>
  <tr>
    <th align="left"><bean:message key="label.invoiceLogoFilename"/></th>
    <td align="left">
      <html:file property="invoiceLogoFile" size="30" />
      <html:hidden property="agency.invoiceLogoFilename" />
      <bean:write name="AgencyFormAgy" property="agency.invoiceLogoFilename"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceLogoWidth"/></th>
    <td align="left"><html:text property="agency.invoiceLogoWidth" size="10"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceLogoHeight"/></th>
    <td align="left"><html:text property="agency.invoiceLogoHeight" size="10"/></td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label">
    	<bean:message key="label.payrollContact" />
    </th>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.payrollContactName"/></th>
    <td align="left"><html:text property="agency.payrollContactName" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.payrollContactEmailAddress"/></th>
    <td align="left"><html:text property="agency.payrollContactEmailAddress" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.payrollContactTelephoneNumber"/></th>
    <td align="left"><html:text property="agency.payrollContactTelephoneNumber" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.payrollContactFaxNumber"/></th>
    <td align="left"><html:text property="agency.payrollContactFaxNumber" size="30"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.freeText"/></th>
    <td align="left"><html:textarea property="agency.freeText" cols="75" rows="5"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.freeText2"/></th>
    <td align="left"><html:textarea property="agency.freeText2" cols="75" rows="5"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceCreditFreeText"/></th>
    <td align="left"><html:textarea property="agency.invoiceCreditFreeText" cols="75" rows="5"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceCreditFooterFreeText"/></th>
    <td align="left"><html:textarea property="agency.invoiceCreditFooterFreeText" cols="75" rows="5"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.clientConfirmationEmailFreeText"/></th>
    <td align="left"><html:textarea property="agency.clientConfirmationEmailFreeText" cols="75" rows="5"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.applicantConfirmationEmailFreeText"/></th>
    <td align="left"><html:textarea property="agency.applicantConfirmationEmailFreeText" cols="75" rows="5"/></td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label">
    	<bean:message key="label.tradeshift" />
    </th>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftConsumerKey"/></th>
    <td align="left"><html:text property="agency.tradeshiftConsumerKey" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftConsumerSecret"/></th>
    <td align="left"><html:text property="agency.tradeshiftConsumerSecret" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftTokenKey"/></th>
    <td align="left"><html:text property="agency.tradeshiftTokenKey" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftTokenSecret"/></th>
    <td align="left"><html:text property="agency.tradeshiftTokenSecret" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftTenantId"/></th>
    <td align="left"><html:text property="agency.tradeshiftTenantId" size="50"/></td>
  </tr>
</html:form>
</table>
  