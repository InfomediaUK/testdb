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
      <bean:message key="title.invoiceView"/>
		</td>
  </tr>
</table>

<logic:empty name="InvoiceViewFormMgr" property="invoice">
<bean:message key="text.noDetails" />
</logic:empty>

<logic:notEmpty name="InvoiceViewFormMgr" property="invoice">
  <logic:notPresent name="InvoiceViewFormMgr" property="invoice.bookingDateUserApplicants">
    <bean:message key="text.accessDenied" />
  </logic:notPresent>
  <logic:present name="InvoiceViewFormMgr" property="invoice.bookingDateUserApplicants">

<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.invoiceNo"/></th>
    <td align="left" width="75%">
      <bean:write name="InvoiceViewFormMgr" property="invoice.invoiceId"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.created"/></th>
    <td align="left">
      <bean:write name="InvoiceViewFormMgr" property="invoice.creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoicePayRateValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.payRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceWtdValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.wtdValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceNiValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.niValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceWageRateValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.wageRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceCommissionValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.commissionValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceChargeRateValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.chargeRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceExpenseValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.expenseValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceVatValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.vatValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceTotalValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.totalValue" format="#,##0.00"/>
    </td>
  </tr>

  <tr>
    <th align="left"><bean:message key="label.noOfHours"/></th>
    <td align="left">
      <bean:write name="InvoiceViewFormMgr" property="invoice.noOfHours" format="#,##0.00"/>
    </td>
  </tr>
<%--
  <tr>
    <th align="left"><bean:message key="label.feeValue"/></th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.feeValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.paymentTerms"/></th>
    <td align="left">
      <bean:write name="InvoiceViewFormMgr" property="invoice.paymentTerms" format="#,##0.00"/>
    </td>
  </tr>
--%>

</table>
<br/>

<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.agency"/></th>
    <th align="left"><bean:message key="label.invoicePayRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceWtdValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceNiValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceWageRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceCommissionValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceChargeRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceExpenseValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceVatValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceTotalValueShort"/></th>
    <th align="left"><bean:message key="label.noOfHrs"/></th>
<%--
    <th align="left"><bean:message key="label.feeValue"/></th>
    <th align="left"><bean:message key="label.paymentTerms"/></th>
--%>
  </tr>
<logic:iterate id="invoiceAgency" name="InvoiceViewFormMgr" property="invoice.invoiceAgencies">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
      <bean:write name="invoiceAgency" property="agencyCode"/>
      <logic:notEmpty name="invoiceAgency" property="reference">
        (<bean:write name="invoiceAgency" property="reference"/>)
      </logic:notEmpty>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="payRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="wtdValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="niValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="wageRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="commissionValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="chargeRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="expenseValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="vatValue" format="#,##0.00"/>
    </td>
    <td align="right" title="<bean:write name="invoiceAgency" property="feeValue" format="#0.00"/>">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="totalValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:write name="invoiceAgency" property="noOfHours" format="#,##0.00"/>
    </td>
<%--
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="invoiceAgency" property="feeValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:write name="invoiceAgency" property="paymentTerms" format="#,##0"/>
    </td>
--%>
  </tr>
</logic:iterate>
  <tr>
    <th align="left">
      <bean:message key="label.total"/>
    </th>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.payRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.wtdValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.niValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.wageRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.commissionValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.chargeRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.expenseValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.vatValue" format="#,##0.00"/>
    </td>
    <td align="right" title="<bean:write name="InvoiceViewFormMgr" property="invoice.feeValue" format="#0.00"/>">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.totalValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:write name="InvoiceViewFormMgr" property="invoice.noOfHours" format="#,##0.00"/>
    </td>
<%--
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.feeValue" format="#,##0.00"/>
    </td>
    <th align="left">
      &nbsp;
    </th>
--%>
  </tr>
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
    <th align="left"><bean:message key="label.noOfHrs"/></th>
  </tr>
<logic:iterate id="bookingDate" name="InvoiceViewFormMgr" property="invoice.bookingDateUserApplicants">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td>
 			<mmj-mgr:hasAccess forward="bookingDateView">
      <html:link forward="bookingDateView" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId">    			
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
      </html:link>
      </mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingDateView">
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
      </mmj-mgr:hasNoAccess>
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
    <td align="right">
      <bean:write name="bookingDate" property="workedNoOfHours" format="#,##0.00"/>
    </td>
  </tr>
</logic:iterate>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th colspan="5" align="left">
      <bean:message key="label.total"/>
    </th>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.payRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.wtdValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.niValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.wageRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.commissionValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.chargeRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.expenseValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.vatValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.totalValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:write name="InvoiceViewFormMgr" property="invoice.noOfHours" format="#,##0.00"/>
    </td>
  </tr>    
</table>

  </logic:present>
</logic:notEmpty>

<%-- invoice details BROKEN DOWN !!!

<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.quantity"/></th>
    <th align="left"><bean:message key="label.details"/></th>
    <th align="left"><bean:message key="label.unitPrice"/></th>
    <th align="left"><bean:message key="label.net"/></th>
    <th align="left"><bean:message key="label.vatRate"/></th>
    <th align="left"><bean:message key="label.vat"/></th>
  </tr>
  
<logic:iterate id="bookingDate" name="InvoiceViewFormMgr" property="invoice.bookingDateUserApplicantEntities">
  <tr>
    <th align="left" class="label" colspan="6">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>
      <bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
      <bean:write name="bookingDate" property="applicantFirstName"/>
      <bean:write name="bookingDate" property="applicantLastName"/>
      <bean:write name="bookingDate" property="agencyName"/>
    </th>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
    </td>
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
<bean:write name="bookingDate" property="commissionVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="commissionVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
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
<bean:write name="bookingDate" property="payRateVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="payRateVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
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
<bean:write name="bookingDate" property="wtdVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wtdVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
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
<bean:write name="bookingDate" property="niVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="niVatValue" format="#,##0.00" />
    </td>
  </tr>
  <logic:iterate id="bookingDateExpense" name="bookingDate" property="bookingDateExpenses" type="com.helmet.bean.BookingDateExpenseUser">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
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
<bean:write name="bookingDateExpense" property="expenseVatRate" format="#0.00"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="vatValue" format="#0.00"/>
    </td>
  </tr>
  </logic:iterate>
  <tr><th align="left" colspan="6" bgcolor="#000000" height="3"></th></tr>
</logic:iterate>

  <tr>
    <td align="left" colspan="2" rowspan="3">&nbsp;</td>
    <th align="left" colspan="3"><bean:message key="label.totalNetAmount" /></th>
    <td align="right" ><bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.totalNetValue" format="#,##0.00" /></td>
  </tr>
  <tr>
    <th align="left" colspan="3"><bean:message key="label.totalVatAmount" /></th>
    <td align="right" ><bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.vatValue" format="#,##0.00" /></td>
  </tr>
  <tr>
    <th align="left" colspan="3"><bean:message key="label.totalUppercase" /></th>
    <td align="right" ><bean:message key="label.currencySymbol"/><bean:write name="InvoiceViewFormMgr" property="invoice.totalValue" format="#,##0.00" /></td>
  </tr>
</table>

<br/>

<table class="simple" width="100%">
<logic:iterate id="agencyId" name="InvoiceViewFormMgr" property="invoice.agencyIds" indexId="agencyIndex" type="java.lang.Integer">
  <bean:define id="invoice" name="InvoiceViewFormMgr" property="invoice" type="com.helmet.bean.InvoiceEntity"/>
  <%
  java.util.List<Integer> applicantIds = invoice.getApplicantIdsForAgency(agencyId);
  pageContext.setAttribute("applicantIds", applicantIds);
  %>
  <bean:define id="firstForAgency" value="true"/>
  <logic:iterate id="applicantId" name="applicantIds" indexId="applicantIndex" type="java.lang.Integer">
    <bean:define id="firstForApplicant" value="true"/>
    <logic:iterate id="bookingDate" name="InvoiceViewFormMgr" property="invoice.bookingDateUserApplicantEntities">
	  <logic:equal name="bookingDate" property="agencyId" value="<%= agencyId.toString() %>">
        <logic:equal name="firstForAgency" value="true">
		  <%/* New Agency */%>
		  <tr><th align="left" colspan="6"><bean:write name="bookingDate" property="agencyName"/></th></tr>	    
          <bean:define id="firstForAgency" value="false"/>
        </logic:equal>
	    <logic:equal name="bookingDate" property="applicantId" value="<%= applicantId.toString() %>">
          <logic:equal name="firstForApplicant" value="true">
	  	    <%/* New Applicant */%>
		    <tr><th align="left" colspan="6"><bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/></th></tr>	    
	        <bean:define id="firstForApplicant" value="false"/>
	      </logic:equal>
    	
  <tr>
    <th align="left" class="label" colspan="6">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>
      <bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
      - 
	  <bean:write name="bookingDate" property="jobProfileName"/>
      (<bean:write name="bookingDate" property="locationName"/>,
      <bean:write name="bookingDate" property="siteName"/>)
	  <bean:write name="bookingDate" property="shiftName"/>
    </th>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
    </td>
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
<bean:write name="bookingDate" property="commissionVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="commissionVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
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
<bean:write name="bookingDate" property="payRateVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="payRateVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
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
<bean:write name="bookingDate" property="wtdVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wtdVatValue" format="#,##0.00" />
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
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
<bean:write name="bookingDate" property="niVatRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="niVatValue" format="#,##0.00" />
    </td>
  </tr>
  <logic:iterate id="bookingDateExpense" name="bookingDate" property="bookingDateExpenses" type="com.helmet.bean.BookingDateExpenseUser">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
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
<bean:write name="bookingDateExpense" property="expenseVatRate" format="#0.00"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="vatValue" format="#0.00"/>
    </td>
  </tr>
  </logic:iterate>
  <tr><th align="left" colspan="6" bgcolor="#000000" height="3"></th></tr>
  	    
  	    </logic:equal>    
	  </logic:equal>    
    </logic:iterate>
  </logic:iterate>
</logic:iterate>
</table>

--%>
