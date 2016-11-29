<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="subcontractInvoiceUser" name="SubcontractInvoiceCreditFormAgy"  property="subcontractInvoiceUser" type="com.helmet.bean.SubcontractInvoiceUser"/>
<%
  String fileName = subcontractInvoiceUser.getSubcontractInvoiceFileName();
  String filePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + 
                    subcontractInvoiceUser.getFromAgencyId() + "/a" + subcontractInvoiceUser.getToAgencyId() + "/" + fileName;
%>
<html:form action="/subcontractInvoiceCreditConfirm.do" onsubmit="return singleSubmit();">
  <html:hidden name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" />
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
			<td align="left" valign="middle" class="title"><bean:message key="title.subcontractInvoiceCredit"/></td>
<mmj-agy:hasAccess forward="subcontractInvoiceCredit">
	    <td align="right" valign="middle" width="80">
        <html:submit  styleClass="titleButton"><bean:message key="button.credit"/></html:submit>
	    </td>
</mmj-agy:hasAccess>
	  </tr>
	</table>
	<table class="simple" width="100%">
	  <tr>
	    <th width="25%" align="left" class="label"><bean:message key="label.creditDate"/></th>
	    <td align="left">
        <html:text name="SubcontractInvoiceCreditFormAgy" property="creditDateStr" styleId="creditDate" title="dd/MM/yyyy"/>
			  <input type="reset" value=" ... " id="creditDateButton">
	    </td>
	  </tr>
		<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	    <td align="left" colspan="2">
	      Notes:-<br />
	      <html:textarea name="SubcontractInvoiceCreditFormAgy" property="notes" style="width:100%" styleId="message" cols="100" rows="12" disabled="false" />      
	    </td>
	  </tr>
	</table>	
	<table class="simple" width="100%">
	  <tr>
	    <th align="left">From Agency</th>
	    <td align="left"><bean:write  name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.fromAgencyName"/></td>
	  </tr>
	  <tr>
	    <th align="left">Invoice Number</th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" format="S000000"/>&nbsp;
        <html:link href="<%= filePath %>" target="pdf"><bean:message key="link.view"/></html:link>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Invoice/Tax Date</th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.date" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.client"/></th>
	    <td align="left"><bean:write  name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.clientNhsName"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.ward"/></th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.siteNhsLocation"/>,&nbsp;<bean:write  name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.locationNhsWard"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Assignment</th>
	    <td align="left"><bean:write name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.jobProfileNhsAssignment"/></td>
	  </tr>
	  <tr>
	    <th align="left">Period</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.startDate" formatKey="format.mediumDateFormat"/>&nbsp;-&nbsp;
	      <bean:write name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.endDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Sent</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.sentDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Paid</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.paidDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	</table>
	<table class="simple" width="100%">
    <thead>
    <tr>
      <th align="left">Bank Request</th>
      <th align="left">Date</th>
      <th align="left">Worked</th>
      <th align="right">Hours</th>
      <th align="left" >Worker</th>
      <th align="right"><bean:message key="label.rate"/></th>
      <th align="right"><bean:message key="label.value"/></th>
    </tr>
    </thead>
<logic:iterate id="subcontractInvoiceItem" name="SubcontractInvoiceCreditFormAgy" property="list" indexId="subcontractInvoiceItemIndex" type="com.helmet.bean.SubcontractInvoiceItem">
		<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
			<td align="left" >
        <bean:write name="subcontractInvoiceItem" property="bankReqNum"/>
			</td>
			<td align="left" >
				<bean:write name="subcontractInvoiceItem" property="date" formatKey="format.longDateFormat"/>
			</td>
			<td align="left" >
				<bean:write name="subcontractInvoiceItem" property="startTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="subcontractInvoiceItem" property="endTime" format="HH:mm"/>
			</td>
			<td align="right" >
				<bean:write name="subcontractInvoiceItem" property="noOfHours" format="##0.00"/>
			</td>
			<td align="left" >
				<bean:write name="subcontractInvoiceItem" property="staffName"/>
			</td>
			<td align="right" >
				<bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItem" property="rate" format="#0.00"/>
			</td>
			<td align="right" >
				<bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItem" property="value" format="#,##0.00"/>
			</td>
	  </tr>		
  <logic:notEmpty  name="subcontractInvoiceItem" property="comment" >
		<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
			<td align="left" colspan="7">
			  <bean:write name="subcontractInvoiceItem" property="comment"/>
			</td>
		</tr>
  </logic:notEmpty>
</logic:iterate>
		<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
			<td align="right" colspan="6">
				Invoice Total
			</td>
			<td align="right" >
				<bean:message key="label.currencySymbol"/><bean:write name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.value" format="#,##0.00"/>
			</td>
	  </tr>		
  <logic:notEmpty  name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.notes" >
		<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	    <td align="left" colspan="7">
	      Invoice Notes:-<br />
	      <html:textarea name="SubcontractInvoiceCreditFormAgy" property="subcontractInvoiceUser.notes" style="width:100%" styleId="message" cols="100" rows="12" disabled="true" />      
	    </td>
	  </tr>
  </logic:notEmpty>
</table>	
<script type="text/javascript">//<![CDATA[
  var cal1 = Zapatec.Calendar.setup({
		    firstDay          : 1,
		    showOthers        : true,
		    step              : 1,
		    electric          : false,
		    inputField        : "creditDate",
		    button            : "creditDateButton",
		    ifFormat          : "%d/%m/%Y",
		    daFormat          : "%d/%m/%Y",
        showsTime         : false
		  });
//]]></script>
</html:form>
