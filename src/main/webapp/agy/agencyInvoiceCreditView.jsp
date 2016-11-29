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
      <bean:message key="title.agencyInvoiceCreditView"/>
	</td>
  </tr>
</table>

<logic:notEmpty name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoiceCredit">

<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.creditNo"/></th>
    <td align="left" width="75%">
      <bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoiceCredit.agencyInvoiceCreditId"/>&nbsp;
      
<bean:define id="agencyInvoiceCredit" name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoiceCredit" type="com.helmet.bean.AgencyInvoiceCredit"/>
<%
  String fileName = "aic" + agencyInvoiceCredit.getAgencyInvoiceCreditId();
  String tempFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getTempFileFolder() + "/" + fileName + ".pdf";
%>
			<html:link href="<%= tempFilePath %>" target="pdf">
			  <bean:message key="link.view"/>
			</html:link>
      
			<mmj-agy:hasAccess forward="sendInvoiceCreditEmail">
			  &nbsp;
			  <html:link forward="sendInvoiceCreditEmail" paramId="agencyInvoiceCredit.agencyInvoiceCreditId" paramName="AgencyInvoiceCreditViewFormAgy" paramProperty="agencyInvoiceCredit.agencyInvoiceCreditId" titleKey="title.sendInvoiceCreditEmail">
			    <bean:message key="link.sendInvoiceCreditEmail"/>
			  </html:link>
			</mmj-agy:hasAccess>	  

    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.reasonText"/></th>
    <td align="left">
      <bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoiceCredit.reasonText"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.creditedBy"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoice.creditedByFirstName"/>
      <bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoice.creditedByLastName"/>
	    (<bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoice.creditedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>

  <tr>
    <th align="left"><bean:message key="label.client"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoice.clientName" />
      (<bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoice.clientCode" />)
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceNo"/></th>
    <td align="left">
      <mmj-agy:hasAccess forward="agencyInvoiceView">
      <html:link forward="agencyInvoiceView" paramId="agencyInvoice.agencyInvoiceId" paramName="AgencyInvoiceCreditViewFormAgy" paramProperty="agencyInvoiceCredit.agencyInvoiceId">
        <bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoiceCredit.agencyInvoiceId"/>
      </html:link>
      </mmj-agy:hasAccess>
      <mmj-agy:hasNoAccess forward="agencyInvoiceView">
        <bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoiceCredit.agencyInvoiceId"/>
      </mmj-agy:hasNoAccess>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceTotalValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceCreditViewFormAgy" property="agencyInvoice.totalValue" format="#,##0.00"/>
    </td>
  </tr>
</table>

<br/>

</logic:notEmpty>





