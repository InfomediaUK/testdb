<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<mmj-agy:consultant var="consultant"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/agencyInvoiceCreditProcess.do" focus="reasonText" onsubmit="return singleSubmit();">
<html:hidden property="agencyInvoice.agencyInvoiceId" />
<html:hidden property="agencyInvoice.noOfChanges" />
  <tr>
	<td align="left" valign="middle" class="title">
	  <bean:message key="title.agencyInvoiceCredit"/>
	</td>
    <mmj-agy:hasAccess forward="agencyInvoiceCredit">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </td>
    <td align="right" valign="middle" width="75">
      <html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel>
    </td>
	</mmj-agy:hasAccess>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.reasonText"/></th>
    <td align="left" width="75%"colspan="2">
  	  <html:text property="reasonText" size="75" />
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceNo"/></th>
    <td align="left" width="100%">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.agencyInvoiceId"/>

<bean:define id="agencyInvoice" name="AgencyInvoiceCreditFormAgy" property="agencyInvoice" type="com.helmet.bean.AgencyInvoice"/>
<%
  String fileName = "ai" + agencyInvoice.getAgencyInvoiceId();
  String tempFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getTempFileFolder() + "/" + fileName + ".pdf";
  String bothFileName = "ai" + agencyInvoice.getAgencyInvoiceId() + "ts";
  String bothTempFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getTempFileFolder() + "/" + bothFileName + ".pdf";
%>
<html:link href="<%= tempFilePath %>" target="pdf">
  <bean:message key="link.view"/>
</html:link>

<logic:notEmpty name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.timesheetFilename">
	<html:link href="<%= bothTempFilePath %>" target="both">
	  <bean:message key="link.viewBoth"/>
	</html:link>
</logic:notEmpty>

<%--
			<mmj-agy:hasAccess forward="sendInvoiceEmail">
			  <html:link forward="sendInvoiceEmail" paramId="agencyInvoice.agencyInvoiceId" paramName="AgencyInvoiceCreditFormAgy" paramProperty="agencyInvoice.agencyInvoiceId" titleKey="title.sendInvoiceEmail">
			    <bean:message key="link.sendInvoiceEmail"/>
			  </html:link>
			</mmj-agy:hasAccess>	  
--%>

    </td>

    <td rowspan="2" align="right">
	  <html:link href="http://www.adobe.com/products/acrobat/readstep2.html" target="_blank">
        <img src="<%= request.getContextPath() %>/images/getAdobeReader.gif" width="112" height="33" align="middle"/>
      </html:link>
    </td>

  </tr>
  <tr>
    <th align="left"><bean:message key="label.timesheet"/></th>
    <td align="left" colspan="2">
    <logic:notEmpty name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.timesheetFilename">
	  <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.timesheetFilename"/>
      <bean:define id="timesheetUrl" name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.timesheetUrl" type="java.lang.String" />
      <html:link href="<%= request.getContextPath() + timesheetUrl %>" target="timesheet">
        <bean:message key="link.view"/>
      </html:link>
      <logic:notEqual name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.timesheetExists" value="true">
        <bean:message key="label.missing"/>
      </logic:notEqual>
<%--
      <mmj-agy:hasAccess forward="agencyInvoiceDeleteTimesheet">
      <html:link forward="agencyInvoiceDeleteTimesheet" paramId="agencyInvoice.agencyInvoiceId" paramName="AgencyInvoiceCreditFormAgy" paramProperty="agencyInvoice.agencyInvoiceId">
        <bean:message key="link.delete"/>
      </html:link>
	  </mmj-agy:hasAccess>
--%>
	</logic:notEmpty>
<%--
    <mmj-agy:hasAccess forward="agencyInvoiceUploadTimesheet">
      <html:link forward="agencyInvoiceUploadTimesheet" paramId="agencyInvoice.agencyInvoiceId" paramName="AgencyInvoiceCreditFormAgy" paramProperty="agencyInvoice.agencyInvoiceId">
        <bean:message key="link.upload"/>
      </html:link>
    </mmj-agy:hasAccess>
--%>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.client"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.clientName" />
      (<bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.clientCode" />)
    </td>
  </tr>
  
  <tr>
    <th align="left"><bean:message key="label.reference"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.reference"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.created"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoicePayRateValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.payRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceWtdValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.wtdValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceNiValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.niValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceWageRateValue"/></th>
    <td align="left" colspan="2">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.wageRateValue" format="#,##0.00"/>
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceCommissionValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.commissionValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceChargeRateValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.chargeRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <logic:greaterThan name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.expenseValue" value="0">
  <tr>
    <th align="left"><bean:message key="label.invoiceExpenseValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.expenseValue" format="#,##0.00"/>
    </td>
  </tr>
  </logic:greaterThan>
  <logic:greaterThan name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.discountValue" value="0">
  <tr>
    <th align="left"><bean:message key="label.invoiceDiscountValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/>-<bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.discountValue" format="#,##0.00"/>
      (<bean:message key="label.currencySymbol"/>-<bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.discountVatValue" format="#,##0.00"/>)
			<bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.discountText"/>
    </td>
  </tr>
  </logic:greaterThan>
  <tr>
    <th align="left"><bean:message key="label.invoiceVatValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.vatValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceTotalValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.totalValue" format="#,##0.00"/>
    </td>
  </tr>

  <tr>
    <th align="left"><bean:message key="label.noOfHours"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.noOfHours" format="#,##0.00"/>
    </td>
  </tr>
<%--
  <tr>
    <th align="left"><bean:message key="label.feeValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.feeValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.paymentTerms"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.paymentTerms" format="#,##0.00"/>
    </td>
  </tr>
--%>
  <tr>
    <th align="left"><bean:message key="label.status"/></th>
    <td align="left" colspan="2">
      <bean:message name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.statusDescriptionKey" />
<%--
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.submittedByConsultantId" />
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.submittedTimestamp" />
--%>
  </tr>
<logic:notEmpty name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.submittedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.submittedBy"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.submittedByFirstName"/>
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.submittedByLastName"/>
	    (<bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.submittedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.authorizedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.authorizedBy"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.authorizedByFirstName"/>
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.authorizedByLastName"/>
	    (<bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.authorizedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.paidTimestamp">
  <tr>
    <th align="left"><bean:message key="label.paidBy"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.paidByFirstName"/>
      <bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.paidByLastName"/>
	    (<bean:write name="AgencyInvoiceCreditFormAgy" property="agencyInvoice.paidTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
</html:form>
</table>