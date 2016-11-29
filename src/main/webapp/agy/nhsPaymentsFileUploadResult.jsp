<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="payments" name="NhsPaymentsFileUploadFormAgy" property="payments" type="com.helmet.bean.nhs.Payments"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/nhsPaymentsFileAccept.do" onsubmit="return singleSubmit();">
  <tr>
    <td align="left" valign="middle" class="title">
      <bean:message key="title.nhsPaymentsFileUploadResult"/>:&nbsp;<bean:write name="NhsPaymentsFileUploadFormAgy" property="nhsPaymentsFilename" />
    </td>
<logic:equal name="payments" property="canAccept" value="true">
  <mmj-agy:hasAccess forward="nhsPaymentsFileUpload">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="titleButton"><bean:message key="button.accept"/></html:submit>
    </td>
    <html:hidden name="NhsPaymentsFileUploadFormAgy" property="accept"/>
    <html:hidden name="NhsPaymentsFileUploadFormAgy" property="client.clientId"/>
  </mmj-agy:hasAccess>
</logic:equal>
  </tr>
</html:form>
</table>
<html:errors/>
<table class="simple" width="100%">
  <thead>
	  <tr>
	    <th align="left" colspan="6">
	      Client
	    </th>
	    <th align="right">
	      Payment
	    </th>
	    <th>
	      &nbsp;
	    </th>
	  </tr>
	</thead>
<logic:iterate id="clientPayment" name="payments" property="clientPaymentList" type="com.helmet.bean.nhs.ClientPayment" indexId="clientPaymentIndex">
    <tr>
	    <td align="left" colspan="6">
	      <bean:write name="clientPayment" property="client.name" />
	    </td>
	    <td align="right">
	      <bean:message key="label.currencySymbol"/><bean:write name="clientPayment" property="payment" format="#,##0.00" />
	    </td>
	    <td>
	      &nbsp;
	    </td>
    </tr>
</logic:iterate>
	  <tr>
	    <td colspan="6" align="right">
	      Total Payment
	    </td>
	    <td align="right">
	      <bean:message key="label.currencySymbol"/><bean:write name="payments" property="totalPayment" format="#,##0.00" />
	    </td>
	    <td>
	      &nbsp;
	    </td>
	  </tr>
  <thead>
    <tr>
      <th align="left">
        Client
      </th>
      <th align="left">
        Date
      </th>
      <th align="left">
        Backing Report
      </th>
      <th align="right">
        Paid
      </th>
      <th align="right">
        Completed
      </th>
      <th align="right">
        Agreed
      </th>
      <th align="right">
        Payment
      </th>
      <th align="right">
        Status
      </th>
    <tr>
  </thead>  
<logic:iterate id="paymentLine" name="payments" property="paymentLines" type="com.helmet.bean.nhs.PaymentLine" indexId="paymentLineIndex">
  <tr>
    <td>
      <bean:write name="paymentLine" property="clientName" />
    </td>
    <td>
      <bean:write name="paymentLine" property="date" formatKey="format.nhsDateFormat"/>
    </td>
    <td>
  <logic:empty name="paymentLine" property="nhsBackingReport" >
      <bean:write name="paymentLine" property="documentName" />
  </logic:empty>
  <logic:notEmpty name="paymentLine" property="nhsBackingReport" >
    <mmj-agy:hasAccess forward="nhsBackingReportView" >
      <html:link forward="nhsBackingReportView" paramId="nhsBackingReportUser.nhsBackingReportId" paramName="paymentLine" paramProperty="nhsBackingReport.nhsBackingReportId" titleKey="title.nhsBackingReportView"><bean:write name="paymentLine" property="documentName"/></html:link>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="nhsBackingReportView" >
      <bean:write name="paymentLine" property="documentName" />
    </mmj-agy:hasNoAccess>
  </logic:notEmpty>
    </td>
    <td align="right">
  <logic:empty name="paymentLine" property="nhsBackingReport" >
      &nbsp;
  </logic:empty>
  <logic:notEmpty name="paymentLine" property="nhsBackingReport" >
      <bean:message key="label.currencySymbol"/><bean:write name="paymentLine" property="nhsBackingReport.paidValue" format="#,##0.00" />
  </logic:notEmpty>
    </td>
    <td align="right">
  <logic:empty name="paymentLine" property="nhsBackingReport" >
      &nbsp;
  </logic:empty>
  <logic:notEmpty name="paymentLine" property="nhsBackingReport" >
      <bean:write name="paymentLine" property="nhsBackingReport.completeDate" formatKey="format.nhsDateFormat" />
  </logic:notEmpty>
    </td>
    <td align="right">
  <logic:empty name="paymentLine" property="nhsBackingReport" >
      &nbsp;
  </logic:empty>
  <logic:notEmpty name="paymentLine" property="nhsBackingReport" >
      <bean:message key="label.currencySymbol"/><bean:write name="paymentLine" property="nhsBackingReport.agreedValue" format="#,##0.00" />
  </logic:notEmpty>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="paymentLine" property="payment" format="#,##0.00" />
    </td>
    <td align="right">
      <bean:write name="paymentLine" property="status" />
    </td>
  </tr>
  <logic:equal name="paymentLine" property="valid" value="false">
    <logic:iterate id="paymentLineError" name="paymentLine" property="errors" type="com.helmet.bean.nhs.PaymentLineError" indexId="errorIndex">
  <tr>
    <td colspan="8" class="nhsUploadError">
      ***&nbsp;<bean:write name="paymentLineError"  property="message" />&nbsp;***&nbsp;
    </td>
  </tr>  
    </logic:iterate>
  </logic:equal> 
</logic:iterate>
</table>
