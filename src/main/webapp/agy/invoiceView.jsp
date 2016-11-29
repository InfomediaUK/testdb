<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:parameter id="tab" name="tab" value="summary"/>
<mmj-agy:consultant var="consultant"/>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.invoiceView"/>
		</td>
<mmj-agy:hasAccess forward="invoiceEdit">
    <html:form action="/invoiceEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="invoiceAgency.invoiceId" value="<bean:write name="InvoiceFormAgy" property="invoiceAgency.invoiceId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
  </tr>
</table>

<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="summary"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="50%">
    <html:link forward="invoiceViewSummary" paramId="invoiceAgency.invoiceId" paramName="InvoiceFormAgy" paramProperty="invoiceAgency.invoiceId" titleKey="title.invoiceViewSummary" >
    <bean:message key="link.tabSummary"/>
    </html:link>
    </td>
<%--
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="detail"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="40%">
    <html:link forward="invoiceViewDetail" paramId="invoiceAgency.invoiceId" paramName="InvoiceFormAgy" paramProperty="invoiceAgency.invoiceId" titleKey="title.invoiceViewDetail" >
    <bean:message key="link.tabDetail"/>
    </html:link>
    </td>
--%>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="pdf"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="50%">
    <html:link forward="invoiceViewPDF" paramId="invoiceAgency.invoiceId" paramName="InvoiceFormAgy" paramProperty="invoiceAgency.invoiceId" titleKey="title.invoiceViewPDF" >
    <bean:message key="link.tabDetail"/>
<%--
    <bean:message key="link.tabPDF"/>
--%>
    </html:link>
    </td>
  </tr>
</table>

<logic:equal name="tab" value="summary">

<logic:notEmpty name="InvoiceFormAgy" property="invoiceAgency">
  <logic:empty name="InvoiceFormAgy" property="invoiceAgency.bookingDateUserApplicants">
    <bean:message key="text.accessDenied" />
  </logic:empty>
  <logic:notEmpty name="InvoiceFormAgy" property="invoiceAgency.bookingDateUserApplicants">

<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.invoiceNo"/></th>
    <td align="left" width="75%">
      <bean:write name="InvoiceFormAgy" property="invoiceAgency.invoiceId"/>
    </td>
  </tr>
  <tr>
    <th align="left" width="25%"><bean:message key="label.reference"/></th>
    <td align="left" width="75%">
      <bean:write name="InvoiceFormAgy" property="invoiceAgency.reference"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.created"/></th>
    <td align="left">
      <bean:write name="InvoiceFormAgy" property="invoiceAgency.creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoicePayRateValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.payRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceWtdValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.wtdValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceNiValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.niValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceWageRateValue"/></th>
    <td align="left">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.wageRateValue" format="#,##0.00"/>
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceCommissionValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.commissionValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceChargeRateValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.chargeRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceExpenseValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.expenseValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceVatValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.vatValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceTotalValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.totalValue" format="#,##0.00"/>
    </td>
  </tr>

  <tr>
    <th align="left"><bean:message key="label.noOfHours"/></th>
    <td align="left">
      <bean:write name="InvoiceFormAgy" property="invoiceAgency.noOfHours" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.feeValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.feeValue" format="#,##0.00"/>
    </td>
  </tr>
<%/*
  <tr>
    <th align="left"><bean:message key="label.paymentTerms"/></th>
    <td align="left">
      <bean:write name="InvoiceFormAgy" property="invoiceAgency.paymentTerms" format="#,##0.00"/>
    </td>
  </tr>
*/%>
</table>
<br/>
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.shiftNo"/></th>
    <th align="left"><bean:message key="label.date"/></th>
    <th align="left"><bean:message key="label.jobProfile"/></th>
    <th align="left"><bean:message key="label.location"/></th>
    <th align="left"><bean:message key="label.shift"/></th>
    <th align="left"><bean:message key="label.invoicePayRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceWtdValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceNiValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceWageRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceCommissionValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceChargeRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceExpenseValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceVatValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceTotalValueShort"/></th>
  </tr>
<logic:iterate id="bookingDate" name="InvoiceFormAgy" property="invoiceAgency.bookingDateUserApplicants">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td>
 			<mmj-agy:hasAccess forward="bookingDateView">
      <html:link forward="bookingDateView" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId">    			
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
      </html:link>
      </mmj-agy:hasAccess>
 			<mmj-agy:hasNoAccess forward="bookingDateView">
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
      </mmj-agy:hasNoAccess>
    </td>
    <td>
			<bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </td>
    <td>
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			(<bean:write name="bookingDate" property="agencyCode"/>)
    </td>
    <td>
      <bean:write name="bookingDate" property="locationName"/>,
      <bean:write name="bookingDate" property="siteName"/>
    </td>
    <td>
			<bean:write name="bookingDate" property="shiftName"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedPayRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedWtdValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedNiValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedWageRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedCommissionValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedChargeRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="expenseValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="totalVatValue" format="#,##0.00"/>
    </td>
    <td align="right" title="<bean:write name="bookingDate" property="feePerShift" format="#0.00"/> <bean:write name="bookingDate" property="feePerHour" format="#0.00"/> <bean:write name="bookingDate" property="feePercentage" format="#0.00"/> <bean:write name="bookingDate" property="feeValue" format="#0.00"/>">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedTotalValue" format="#,##0.00"/>
    </td>
  </tr>
</logic:iterate>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th colspan="5" align="left">
      <bean:message key="label.total"/>
    </th>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.payRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.wtdValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.niValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.wageRateValue" format="#,##0.00"/>
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.commissionValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.chargeRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.expenseValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.vatValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.totalValue" format="#,##0.00"/>
    </td>
  </tr>    
</table>

  </logic:notEmpty>
</logic:notEmpty>

</logic:equal>

<logic:equal name="tab" value="detail">

<logic:equal name="InvoiceFormAgy" property="invoiceAgency.allTheSameBookingAndApplicant" value="true">

<bean:define id="agencyName" name="InvoiceFormAgy" property="agency.name" type="java.lang.String"/>
<bean:define id="agencyLogo" name="InvoiceFormAgy" property="agency.logoUrl" type="java.lang.String"/>
<bean:define id="agencyLogoWidth" name="InvoiceFormAgy" property="agency.logoWidth" type="java.lang.Integer"/>
<bean:define id="agencyLogoHeight" name="InvoiceFormAgy" property="agency.logoHeight" type="java.lang.Integer"/>

<table width="100%">
  <tr>
    <td align="left" valign="top">
<logic:equal name="agencyLogoWidth" value="0">
      <bean:write name="agency" property="name"/>
</logic:equal>
<logic:notEqual name="agencyLogoWidth" value="0">
      <html:img src="<%= request.getContextPath() + agencyLogo %>" width="<%= agencyLogoWidth.toString() %>" height="<%= agencyLogoHeight.toString() %>" title="<%= agencyName %>" alt="<%= agencyName %>"/>
</logic:notEqual>
    </td>
    <td align="right" valign="top">
      <bean:write name="InvoiceFormAgy" property="agency.address.fullAddress"/>
			<br/>
			<bean:write name="InvoiceFormAgy" property="agency.countryName"/>
			<br/>
			<bean:write name="InvoiceFormAgy" property="agency.telephoneNumber"/>
			<br/>
			<bean:write name="InvoiceFormAgy" property="agency.faxNumber"/>
			<br/>
			<bean:write name="InvoiceFormAgy" property="agency.vatNumber"/>
    </td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      <br/>
    </td>
  </tr>
  <tr>
    <td align="left">
			<bean:write name="InvoiceFormAgy" property="client.name"/>
			<br/>
			<bean:write name="InvoiceFormAgy" property="client.address.fullAddress"/>
			<br/>
			<bean:write name="InvoiceFormAgy" property="client.countryName"/>
    </td>
    <td align="right" valign="top">
      <bean:write name="InvoiceFormAgy" property="invoiceAgency.invoiceId"/>
      <br/>
      <bean:write name="InvoiceFormAgy" property="invoiceAgency.creationTimestamp" formatKey="format.mediumDateFormat"/>
			<br/>
			<bean:write name="InvoiceFormAgy" property="client.reference"/>
    </td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      <br/>
    </td>
  </tr>
  <tr>
    <td align="left" colspan="2">
<logic:iterate id="bookingDate" name="InvoiceFormAgy" property="invoiceAgency.bookingDateUserApplicants" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex" length="1">
			<bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/>
      <br/>  
			<bean:write name="bookingDate" property="jobProfileName"/>
			-
			<bean:write name="bookingDate" property="gradeName"/>
      <br/>  
			<bean:write name="bookingDate" property="locationName"/>,&nbsp;<bean:write name="bookingDate" property="siteName"/>
</logic:iterate>
    </td>
  </tr>
</table>
<br/>
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.shiftNo"/></th>
    <th align="left"><bean:message key="label.date"/></th>
    <th align="left"><bean:message key="label.times"/></th>
    <th align="left"><bean:message key="label.quantity"/></th>
    <th align="left"><bean:message key="label.details"/></th>
    <th align="left"><bean:message key="label.unitPrice"/></th>
    <th align="left"><bean:message key="label.net"/></th>
    <th align="left"><bean:message key="label.vatRate"/></th>
    <th align="left"><bean:message key="label.vat"/></th>
  </tr>
<logic:iterate id="bookingDate" name="InvoiceFormAgy" property="invoiceAgency.bookingDateUserApplicants" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
    </th>
    <th align="left">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </th>
    <td align="left">
      <bean:write name="bookingDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="workedEndTime" format="HH:mm"/>
      <logic:greaterThan name="bookingDate" property="workedBreakNoOfHours" value="0">
      (<bean:write name="bookingDate" property="workedBreakNoOfHours" format="#0.00"/>)
      </logic:greaterThan>
      <logic:equal name="bookingDate" property="hasUplift" value="true">
      *
      </logic:equal>
    </td>
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
    </td>
  <logic:equal name="bookingDate" property="chargeRateVatRate" value="0">
    <td align="left">
<bean:message key="label.commission"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="commissionRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedCommissionValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="commissionVatRate" value="0">
<bean:write name="bookingDate" property="commissionVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedCommissionVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedCommissionVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" colspan="3">
      &nbsp;
    </td>
    <td align="right">
      &nbsp;
    </td>
    <td align="left">
<bean:message key="label.payRate"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="payRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedPayRateValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="payRateVatRate" value="0">
<bean:write name="bookingDate" property="payRateVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedPayRateVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedPayRateVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" colspan="3">
      &nbsp;
    </td>
    <td align="right">
      &nbsp;
    </td>
    <td align="left">
<bean:message key="label.wtd"/>&nbsp;@&nbsp;<bean:write name="bookingDate" property="wtdPercentage" format="#,##0.00" />%
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wtdRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedWtdValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="wtdVatRate" value="0">
<bean:write name="bookingDate" property="wtdVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedWtdVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedWtdVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" colspan="3">
      &nbsp;
    </td>
    <td align="right">
      &nbsp;
    </td>
    <td align="left">
<bean:message key="label.ni"/>&nbsp;@&nbsp;<bean:write name="bookingDate" property="niPercentage" format="#,##0.00" />%
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="niRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedNiValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="niVatRate" value="0">
<bean:write name="bookingDate" property="niVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedNiVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedNiVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </logic:equal>  
  <logic:notEqual name="bookingDate" property="chargeRateVatRate" value="0">
    <td align="left">
<bean:message key="label.chargeRate"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedChargeRateValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="chargeRateVatRate" value="0">
<bean:write name="bookingDate" property="chargeRateVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedChargeRateVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedChargeRateVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </logic:notEqual>
  </tr>
  
  <logic:iterate id="bookingDateExpense" name="bookingDate" property="bookingDateExpenses" type="com.helmet.bean.BookingDateExpenseUser">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" colspan="3">
      &nbsp;
    </td>
    <td align="right">
<logic:equal name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:write name="bookingDateExpense" property="qty" format="#0.00"/>
</logic:equal>
<logic:notEqual name="bookingDateExpense" property="isMultiplier" value="true">
  1.00
</logic:notEqual>
    </td>
    <td align="left">
 <bean:write name="bookingDateExpense" property="expenseName"/>
 <logic:notEmpty name="bookingDateExpense" property="text">
   -
   <bean:write name="bookingDateExpense" property="text"/>
 </logic:notEmpty>
    </td>
    <td align="right">
<logic:equal name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:message key="label.currencySymbol"/><bean:write name="bookingDateExpense" property="expenseMultiplier" format="#0.00"/>
</logic:equal>
<logic:notEqual name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="qty" format="#0.00"/>
</logic:notEqual>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="value" format="#0.00"/>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDateExpense" property="expenseVatRate" value="0">
<bean:write name="bookingDateExpense" property="expenseVatRate" format="#0.00"/>
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDateExpense" property="vatValue" value="0">
<bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="vatValue" format="#0.00"/>
      </logic:greaterThan>
    </td>
  </tr>
  </logic:iterate>

</logic:iterate>

<tr><td align="left" colspan="9">&nbsp;</td></tr>
<tr>
  <th align="right" colspan="6"><bean:message key="label.subTotal" /></th>
  <td align="right" >
    <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.totalNetValue" format="#,##0.00" />
  </td>
  <th align="right" >&nbsp;</th>
  <td align="right" >
    <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.vatValue" format="#,##0.00" />
  </td>
</tr>
<tr><td align="left" colspan="9">&nbsp;</td></tr>
<tr>
  <th align="right" colspan="6"><bean:message key="label.totalUppercase" /></th>
  <th align="right" colspan="2">&nbsp;</th>
  <td align="right" >
    <bean:message key="label.currencySymbol"/><bean:write name="InvoiceFormAgy" property="invoiceAgency.totalValue" format="#,##0.00" />
  </td>
</tr>
</table>

<table width="100%">
  <tr>
    <td align="left" colspan="2">
      <br/>
			<bean:write name="InvoiceFormAgy" property="agency.freeText"/>
      <br/>
    </td>
  </tr>
</table>


</logic:equal>

<logic:notEqual name="InvoiceFormAgy" property="invoiceAgency.allTheSameBookingAndApplicant" value="true">
  <br/>
	<bean:message key="error.noCanDo"/>
</logic:notEqual>

</logic:equal>

<logic:equal name="tab" value="pdf">

<bean:define id="invoiceAgency" name="InvoiceFormAgy" property="invoiceAgency" type="com.helmet.bean.InvoiceAgency"/>
<%
  String fileName = "ia" + invoiceAgency.getInvoiceId() + "." + invoiceAgency.getInvoiceAgencyId();
  String tempFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getTempFileFolder() + "/" + fileName + ".pdf";
%>
<br/>
<html:link href="<%= tempFilePath %>" target="pdf">
  <bean:message key="link.pdf"/>
</html:link>

</logic:equal>




