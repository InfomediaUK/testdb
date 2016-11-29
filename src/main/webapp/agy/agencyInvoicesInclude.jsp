<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="theFormAgy" name="theFormAgy" value="ListFormAgy"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<mmj-agy:consultant var="consultant"/>
<logic:notPresent name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormAgy %>" property="<%= theList %>">
<logic:empty name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="<%= theFormAgy %>" property="<%= theList %>">
<table class="simple" width="100%">
<thead>
  <tr>
    <th align="left">
    <bean:message key="label.noDot"/>
    </th>
    <th align="left">
			<bean:message key="label.date"/>
    </th>
    <th align="left">
			<bean:message key="label.client"/>
    </th>
    <th align="left"><bean:message key="label.invoicePayRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceWtdValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceNiValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceWageRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceCommissionValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceChargeRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceExpenseValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceDiscountValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceVatValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceTotalValueShort"/></th>
    <th align="left"><bean:message key="label.noOfHours"/></th>
    <th align="left"><bean:message key="label.status"/></th>
  </tr>
</thead>
<logic:iterate id="agencyInvoice" name="<%= theFormAgy %>" property="<%= theList %>" type="com.helmet.bean.AgencyInvoiceUser" indexId="agencyInvoiceIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
		<mmj-agy:hasAccess forward="agencyInvoiceView">
		  <html:link forward="agencyInvoiceView" paramId="agencyInvoice.agencyInvoiceId" paramName="agencyInvoice" paramProperty="agencyInvoiceId" titleKey="title.agencyInvoiceView">
  	    <bean:write name="agencyInvoice" property="agencyInvoiceId" />
		  </html:link>  	  
	  </mmj-agy:hasAccess>	  
		<mmj-agy:hasNoAccess forward="agencyInvoiceView">
  	    <bean:write name="agencyInvoice" property="agencyInvoiceId" />
   	</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="agencyInvoice" property="creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
    <td align="left">
 			<bean:write name="agencyInvoice" property="clientName"/>
 			(<bean:write name="agencyInvoice" property="clientCode"/>)
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="payRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="wtdValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="niValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="wageRateValue" format="#,##0.00"/>
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="commissionValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="chargeRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="expenseValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="discountValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="vatValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="totalValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:write name="agencyInvoice" property="noOfHours" format="#,##0.00"/>
    </td>
    <td align="left">
      <bean:message name="agencyInvoice" property="statusDescriptionKey"/>
    </td>
  </tr>
</logic:iterate>
<logic:equal name="showTotals" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="3"><bean:message key="label.total"/></th>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalPayRateValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalWtdValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalNiValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalWageRateValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalCommissionValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalChargeRateValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalExpenseValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalDiscountValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalVatValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalTotal" format="#,##0.00"/></td>
		<td align="right"><bean:write name="<%= theFormAgy %>" property="totalHours" format="#0.00"/></td>
    <th align="left">&nbsp;</th>
  </tr>
</logic:equal>
</table>
</logic:notEmpty>
</logic:present>
