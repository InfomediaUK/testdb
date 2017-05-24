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
<bean:message key="title.agencyView"/>
		</td>
<logic:equal name="AgencyFormAgy" property="agency.active" value="true">
<mmj-agy:hasAccess forward="agencyEdit">
    <html:form action="/agencyEdit.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
</logic:equal>  
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.name"/></th>
    <td align="left" width="75%"><bean:write name="AgencyFormAgy" property="agency.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.address"/></th>
    <td align="left">
	  <bean:write name="AgencyFormAgy" property="agency.address.address1"/>
	  <logic:notEmpty name="AgencyFormAgy" property="agency.address.address2">
		<br/><bean:write name="AgencyFormAgy" property="agency.address.address2"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="AgencyFormAgy" property="agency.address.address3">
		<br/><bean:write name="AgencyFormAgy" property="agency.address.address3"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="AgencyFormAgy" property="agency.address.address4">
		<br/><bean:write name="AgencyFormAgy" property="agency.address.address4"/>
	  </logic:notEmpty>		
	</td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.postalCode"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.address.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.country"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.countryName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.telephoneNumber"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.telephoneNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.faxNumber"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.faxNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.vatNumber"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.vatNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.reference"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.reference"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.websiteAddress"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.websiteAddress"/></td>
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
  <logic:notEmpty name="AgencyFormAgy" property="agency.invoiceLogoFilename">
  <bean:define id="agencyInvoiceLogo" name="agency" property="invoiceLogoUrl" type="java.lang.String"/>
  <bean:define id="agencyInvoiceLogoWidth" name="agency" property="invoiceLogoWidth" type="java.lang.Integer"/>
  <bean:define id="agencyInvoiceLogoHeight" name="agency" property="invoiceLogoHeight" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.invoiceLogo"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + agencyInvoiceLogo %>" width="<%= agencyInvoiceLogoWidth.toString() %>" height="<%= agencyInvoiceLogoHeight.toString() %>" />
    </td>
  </tr>
  </logic:notEmpty>

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
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.payrollContactName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.payrollContactEmailAddress"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.payrollContactEmailAddress"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.payrollContactTelephoneNumber"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.payrollContactTelephoneNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.payrollContactFaxNumber"/></th>
    <td align="left"><bean:write name="AgencyFormAgy" property="agency.payrollContactFaxNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.freeText"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.freeText"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.freeText2"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.freeText2"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceCreditFreeText"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.invoiceCreditFreeText"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceCreditFooterFreeText"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.invoiceCreditFooterFreeText"/>
    </td>
  </tr>
  
  <tr>
    <th align="left"><bean:message key="label.paymentTermsText"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.paymentTermsText"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.bankDetailsText"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.bankDetailsText"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.clientConfirmationEmailFreeText"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.clientConfirmationEmailFreeText"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.applicantConfirmationEmailFreeText"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.applicantConfirmationEmailFreeText"/>
    </td>
  </tr>
  <tr>
    <th align="left">
    	<bean:message key="label.tradeshift" />
    </th>
    <th align="right" class="label">
<mmj-agy:hasAccess forward="agencyTradeshiftValidate">
      <html:form action="/agencyTradeshiftValidate.do" onsubmit="return singleSubmit();">
        <html:submit styleClass="titleButton"><bean:message key="button.validate"/></html:submit>
      </html:form>
</mmj-agy:hasAccess>            
    </th>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftConsumerKey"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.tradeshiftConsumerKey"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftConsumerSecret"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.tradeshiftConsumerSecret"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftTokenKey"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.tradeshiftTokenKey"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftTokenSecret"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.tradeshiftTokenSecret"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftTenantId"/></th>
    <td align="left">
    <bean:write name="AgencyFormAgy" property="agency.tradeshiftTenantId"/>
    </td>
  </tr>
</table>
