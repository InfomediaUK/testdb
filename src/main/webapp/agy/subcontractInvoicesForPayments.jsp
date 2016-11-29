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
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.subcontractInvoicesForPayments"/>:&nbsp;<bean:write name="SubcontractInvoicesForPaymentsFormAgy" property="subcontractInvoicePaymentsFilename"/>
		</td>
  </tr>
</table>
<logic:present name="SubcontractInvoicesForPaymentsFormAgy" property="list">
  <logic:empty name="SubcontractInvoicesForPaymentsFormAgy" property="list">
    <bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="SubcontractInvoicesForPaymentsFormAgy" property="list">

     <table class="simple" width="100%" border="1">
      <thead>
      <tr>
        <th align="left">Invoice</th>
        <th align="left"><bean:message key="label.client" /></th>
        <th align="left"><bean:message key="label.startDate" /></th>
        <th align="left"><bean:message key="label.endDate" /></th>
        <th align="left"><bean:message key="label.paidDate" /></th>
        <th align="right"><bean:message key="label.value" /></th>
        <th align="left"><bean:message key="label.notes" /></th>
      </tr>
      </thead>
<logic:iterate id="subcontractInvoiceUser" name="SubcontractInvoicesForPaymentsFormAgy" property="list" type="com.helmet.bean.SubcontractInvoiceUser">
      <tr>
        <td align="left">
        <mmj-agy:hasAccess forward="subcontractInvoiceView" >
          <html:link forward="subcontractInvoiceView" paramId="subcontractInvoiceUser.subcontractInvoiceId" paramName="subcontractInvoiceUser" paramProperty="subcontractInvoiceId" titleKey="title.subcontractInvoiceView"><bean:write name="subcontractInvoiceUser" property="subcontractInvoiceNumber"/></html:link>
        </mmj-agy:hasAccess>
        <mmj-agy:hasNoAccess forward="subcontractInvoiceView" >
          <bean:write name="subcontractInvoiceUser" property="subcontractInvoiceNumber"/>
        </mmj-agy:hasNoAccess>
        </td>
        <td align="left"><bean:write name="subcontractInvoiceUser" property="clientName"/></td>
        <td align="left"><bean:write name="subcontractInvoiceUser" property="startDate" formatKey="format.longDateFormat"/></td>
        <td align="left"><bean:write name="subcontractInvoiceUser" property="endDate" formatKey="format.longDateFormat"/></td>
        <td align="left"><bean:write name="subcontractInvoiceUser" property="paidDate" formatKey="format.longDateFormat"/></td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceUser" property="value" format="#,##0.00"/></td>
        <td align="left"><bean:write name="subcontractInvoiceUser" property="notes"/></td>
      </tr>
</logic:iterate>
      <tr>
        <td align="right" colspan="5">Total Value</td>
        <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="SubcontractInvoicesForPaymentsFormAgy" property="totalValue" format="#,##0.00"/></td>
        <td align="left">&nbsp;</td>
      </tr>
    </table>
  
  </logic:notEmpty>
</logic:present>
  