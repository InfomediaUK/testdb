<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="subcontractInvoiceUser" name="SubcontractInvoiceSendFormAgy"  property="subcontractInvoiceUser" type="com.helmet.bean.SubcontractInvoiceUser"/>

<html:form action="/subcontractInvoiceSendProcess.do" focus="toEmailAddress" onsubmit="return singleSubmit();">
  <html:hidden name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" />
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
			<td align="left" valign="middle" class="title">
<logic:greaterThan  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
			  <bean:message key="title.subcontractInvoiceSend"/>
</logic:greaterThan>			  
<logic:lessThan  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
			  <bean:message key="title.subcontractCreditNoteSend"/>
</logic:lessThan>			  
			</td>
	    <td align="right" valign="middle" width="80">
	      <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
	    </td>
	  </tr>
	</table>
	<html:errors/>
	<table class="simple" width="100%">
	  <tr>
	    <th align="left">To Email Address</th>
	    <td align="left">
	      <html:text  name="SubcontractInvoiceSendFormAgy" property="toEmailAddress" size="100"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">From Email Address</th>
	    <td align="left">
	      <html:text  name="SubcontractInvoiceSendFormAgy" property="fromEmailAddress" size="100"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">CC Email Address</th>
	    <td align="left">
	      <html:text  name="SubcontractInvoiceSendFormAgy" property="ccEmailAddress" size="100"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">BCC Email Address</th>
	    <td align="left">
	      <html:text  name="SubcontractInvoiceSendFormAgy" property="bccEmailAddress" size="100"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.subject"/></th>
	    <td align="left">
	      <html:text name="SubcontractInvoiceSendFormAgy" property="subject" size="100" />
	    </td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.message"/></th>
	    <td align="left">
	      <html:textarea property="message" style="width:100%" styleId="message" rows="12" />
	    </td>
	  </tr>
	  <tr>
	    <th align="left">From Agency</th>
	    <td align="left"><bean:write  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.fromAgencyName"/></td>
	  </tr>
	  <tr>
	    <th align="left">
<logic:greaterThan  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
	      Invoice Number
</logic:greaterThan>
<logic:lessThan  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
	      Credit Note Number
</logic:lessThan>
	    </th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.subcontractInvoiceNumber"/>&nbsp;
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Invoice/Tax Date</th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.date" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.client"/></th>
	    <td align="left"><bean:write  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.clientNhsName"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.ward"/></th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.siteNhsLocation"/>,&nbsp;<bean:write  name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.locationNhsWard"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Assignment</th>
	    <td align="left"><bean:write name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.jobProfileNhsAssignment"/></td>
	  </tr>
	  <tr>
	    <th align="left">Period</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.startDate" formatKey="format.mediumDateFormat"/>&nbsp;-&nbsp;
	      <bean:write name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.endDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Sent</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.sentDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Paid</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.paidDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	</table>

</html:form>
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
<logic:iterate id="subcontractInvoiceItem" name="SubcontractInvoiceSendFormAgy" property="list" indexId="subcontractInvoiceItemIndex" type="com.helmet.bean.SubcontractInvoiceItem">
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
				<bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItem" property="rate" format="#,##0.00"/>
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
				<bean:message key="label.currencySymbol"/><bean:write name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.value" format="#,##0.00"/>
			</td>
	  </tr>		
<logic:notEmpty name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.notes">
		<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	    <td align="left" colspan="7">
	      Notes:-<br />
	      <html:textarea name="SubcontractInvoiceSendFormAgy" property="subcontractInvoiceUser.notes" style="width:100%" styleId="message" cols="100" rows="12" disabled="true" />      
	    </td>
	  </tr>
</logic:notEmpty>
</table>	

