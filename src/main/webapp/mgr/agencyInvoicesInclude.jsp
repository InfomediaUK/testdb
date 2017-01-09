<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<bean:parameter id="theFormMgr" name="theFormMgr" value="ListFormMgr"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<bean:parameter id="showCheckbox" name="showCheckbox" value="false"/>
<logic:notPresent name="<%= theFormMgr %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormMgr %>" property="<%= theList %>">
<logic:empty name="<%= theFormMgr %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="<%= theFormMgr %>" property="<%= theList %>">
<logic:equal name="showCheckbox" value="true">
<bean:define id="aiList" name="<%= theFormMgr %>" property="<%= theList %>" type="java.util.List" />
<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers
function toggle(checked) {
  for (i = 0; i < <%= aiList.size() %>; i++) {
    document.forms["AgencyInvoicesFormMgr"].elements["ai" + i].checked = checked;
  }
}
// end hiding contents from old browsers  -->
</script>
</logic:equal>
<table class="simple" width="100%">
<thead>
  <tr>
    <th align="left">
    <logic:equal name="showCheckbox" value="true">
    <input type="checkbox" border="0" title="<bean:message key="text.toggle"/>" onclick="javascript:toggle(this.checked)"/>
    </logic:equal>
    <bean:message key="label.noDot"/>
    </th>
    <th align="left">
			<bean:message key="label.date"/>
    </th>
    <th align="left">
			<bean:message key="label.agency"/>
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
  </tr>
</thead>
<logic:iterate id="agencyInvoice" name="<%= theFormMgr %>" property="<%= theList %>" type="com.helmet.bean.AgencyInvoiceUser" indexId="agencyInvoiceIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
  <logic:equal name="showCheckbox" value="true">
    <bean:define id="renderCheckbox" value="false"/>
    <logic:equal name="agencyInvoice" property="canBeAuthorized" value="true">
      <bean:define id="renderCheckbox" value="true"/>
    </logic:equal>
    <logic:equal name="agencyInvoice" property="canBePaid" value="true">
      <bean:define id="renderCheckbox" value="true"/>
    </logic:equal>
    <logic:equal name="renderCheckbox" value="true">
			<html:multibox name="<%= theFormMgr %>" property="selectedAgencyInvoices" styleId="<%= "ai" + agencyInvoiceIndex %>">
			  <bean:write name="agencyInvoice" property="agencyInvoiceId"/>
			</html:multibox>
	  </logic:equal>
  </logic:equal>
		<mmj-mgr:hasAccess forward="agencyInvoiceView">
		  <html:link forward="agencyInvoiceView" paramId="agencyInvoice.agencyInvoiceId" paramName="agencyInvoice" paramProperty="agencyInvoiceId" titleKey="title.agencyInvoiceView">
  	    <bean:write name="agencyInvoice" property="agencyInvoiceId" />
		  </html:link>  	  
	  </mmj-mgr:hasAccess>	  
		<mmj-mgr:hasNoAccess forward="agencyInvoiceView">
  	    <bean:write name="agencyInvoice" property="agencyInvoiceId" />
   	</mmj-mgr:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="agencyInvoice" property="creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
    <td align="left">
 			<bean:write name="agencyInvoice" property="agencyName"/>
 			(<bean:write name="agencyInvoice" property="agencyCode"/>)
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
      <bean:message key="label.currencySymbol"/><bean:write name="agencyInvoice" property="wageRateValue" format="#,##0.00"/>
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
  </tr>
</logic:iterate>
<logic:equal name="showTotals" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="3"><bean:message key="label.total"/></th>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalPayRateValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalWtdValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalNiValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalWageRateValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalCommissionValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalChargeRateValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalExpenseValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalDiscountValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalVatValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormMgr %>" property="totalTotal" format="#,##0.00"/></td>
		<td align="right"><bean:write name="<%= theFormMgr %>" property="totalHours" format="#0.00"/></td>
  </tr>
</logic:equal>
</table>
</logic:notEmpty>
</logic:present>
