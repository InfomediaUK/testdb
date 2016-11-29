<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="subcontractInvoiceUser" name="SubcontractInvoiceFormAgy"  property="subcontractInvoiceUser" type="com.helmet.bean.SubcontractInvoiceUser"/>
<%
	String fileName = subcontractInvoiceUser.getSubcontractInvoiceFileName();
	String filePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + 
	                  subcontractInvoiceUser.getFromAgencyId() + "/a" + subcontractInvoiceUser.getToAgencyId() + "/" + fileName;
  String relatedFileName = subcontractInvoiceUser.getRelatedSubcontractInvoiceFileName();
  String relatedFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getNhsInvoiceFileFolder() + "/a" + 
                    subcontractInvoiceUser.getFromAgencyId() + "/a" + subcontractInvoiceUser.getToAgencyId() + "/" + relatedFileName;
  String remittanceAdviceFileName = subcontractInvoiceUser.getRemittanceAdvice();
  String remittanceAdviceFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getNhsPaymentsFileFolder() + "/a" + subcontractInvoiceUser.getToAgencyId() + "/" + remittanceAdviceFileName;
%>
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
			<td align="left" valign="middle" class="title">
<logic:greaterThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
			  <bean:message key="title.subcontractInvoiceView"/>
</logic:greaterThan>			  
<logic:lessThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
			  <bean:message key="title.subcontractCreditNoteView"/>
</logic:lessThan>			  
			</td>
<mmj-agy:hasAccess forward="subcontractInvoiceDelete">
<logic:empty  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.sentDate" >
  <logic:greaterThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
      <td align="right" valign="middle" width="80">
  <html:form action="/subcontractInvoiceDelete.do" onsubmit="return singleSubmit();">
        <html:hidden name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" />
        <html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit>
  </html:form>
      </td>
  </logic:greaterThan>
</logic:empty>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="subcontractInvoiceReissue">
      <td align="right" valign="middle" width="80">
  <html:form action="/subcontractInvoiceReissue.do" onsubmit="return singleSubmit();">
        <html:hidden name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" />
        <html:submit styleClass="titleButton"><bean:message key="button.reissue"/></html:submit>
  </html:form>
      </td>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="subcontractInvoiceSend">
	    <td align="right" valign="middle" width="80">
  <html:form action="/subcontractInvoiceSend.do" onsubmit="return singleSubmit();">
        <html:hidden name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" />
	      <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
	</html:form>
	    </td>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="subcontractInvoicePaid">
<logic:notEmpty  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.sentDate" >
	    <td align="right" valign="middle" width="80">
  <html:form action="/subcontractInvoicePaid.do" onsubmit="return singleSubmit();">
        <html:hidden name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" />
	      <html:submit styleClass="titleButton"><bean:message key="button.paid"/></html:submit>
	</html:form>
	    </td>
</logic:notEmpty>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="subcontractInvoiceCredit">
<logic:notEmpty  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.sentDate" >
  <logic:greaterThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
    <logic:equal  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.relatedSubcontractInvoiceId" value="0" >
	    <td align="right" valign="middle" width="80">
  <html:form action="/subcontractInvoiceCredit.do" onsubmit="return singleSubmit();">
        <html:hidden name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" />
	      <html:submit styleClass="titleButton"><bean:message key="button.credit"/></html:submit>
	</html:form>
	    </td>
	  </logic:equal>
	</logic:greaterThan>
</logic:notEmpty>
</mmj-agy:hasAccess>
	  </tr>
	</table>
	<table class="simple" width="100%">
	  <tr>
	    <th align="left">From Agency</th>
	    <td align="left"><bean:write  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.fromAgencyName"/></td>
	  </tr>
	  <tr>
	    <th align="left">
<logic:greaterThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
	      Invoice Number
</logic:greaterThan>
<logic:lessThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
			  Credit Note Number
</logic:lessThan>			  
	    </th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceNumber"/>&nbsp;
        <html:link href="<%= filePath %>" target="pdf"><bean:message key="link.view"/></html:link>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">
<logic:greaterThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
	      Invoice/Tax Date
</logic:greaterThan>
<logic:lessThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
			  Credit/Tax Date
</logic:lessThan>			  
	    </th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.date" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.client"/></th>
	    <td align="left"><bean:write  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.clientNhsName"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.ward"/></th>
	    <td align="left">
	      <bean:write  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.siteNhsLocation"/>,&nbsp;<bean:write  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.locationNhsWard"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Assignment</th>
	    <td align="left"><bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.jobProfileNhsAssignment"/></td>
	  </tr>
	  <tr>
	    <th align="left">Period</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.startDate" formatKey="format.mediumDateFormat"/>&nbsp;-&nbsp;
	      <bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.endDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Sent</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.sentDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Paid</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.paidDate" formatKey="format.mediumDateFormat"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Remittance Advice</th>
	    <td align="left">
    <mmj-agy:hasAccess forward="subcontractInvoicesForRemittanceAdvice" >
      <html:link forward="subcontractInvoicesForRemittanceAdvice" paramId="remittanceAdvice" paramName="SubcontractInvoiceFormAgy" paramProperty="subcontractInvoiceUser.remittanceAdvice" titleKey="title.subcontractInvoicesForRemittanceAdvice"><bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.remittanceAdvice" /></html:link>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="subcontractInvoicesForRemittanceAdvice" >
      <bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.remittanceAdvice" />
    </mmj-agy:hasNoAccess>
<logic:equal  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.remittanceAdviceIsFile" value="true" >
        &nbsp;<html:link href="<%= remittanceAdviceFilePath %>" target="text"><bean:message key="link.view"/></html:link>
</logic:equal>        
	    </td>
	  </tr>
	  <tr>
	    <th align="left">Number of Bookings</th>
	    <td align="left">
	      <bean:write name="SubcontractInvoiceFormAgy" property="noOfNhsBookings"/>
	    </td>
	  </tr>
<logic:notEqual  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.relatedSubcontractInvoiceId" value="0" >
	  <tr>
	    <th align="left">
<logic:greaterThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.relatedSubcontractInvoiceId" value="0" >
	      Related Invoice
</logic:greaterThan>
<logic:lessThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.relatedSubcontractInvoiceId" value="0" >
			  Related Credit Note
</logic:lessThan>			  
	    </th>
	    <td align="left">
        <html:link forward="subcontractInvoiceView" paramId="subcontractInvoiceUser.subcontractInvoiceId" paramName="SubcontractInvoiceFormAgy" paramProperty="subcontractInvoiceUser.relatedSubcontractInvoiceId" >
	        <bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.relatedSubcontractInvoiceNumber"/>
	      </html:link>&nbsp;
        <html:link href="<%= relatedFilePath %>" target="pdf"><bean:message key="link.view"/></html:link>
	    </td>
	  </tr>
</logic:notEqual>
<logic:equal  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.active" value="false" >
	  <tr>
	    <th align="left">Active</th>
	    <td align="left">
	      <b>DELETED</b>
	    </td>
	  </tr>
</logic:equal>
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
<logic:iterate id="subcontractInvoiceItem" name="SubcontractInvoiceFormAgy" property="list" indexId="subcontractInvoiceItemIndex" type="com.helmet.bean.SubcontractInvoiceItem">
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
<logic:lessThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
				  <bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItem" property="rate" format="#0.00"/>
</logic:lessThan>
<logic:greaterThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
<logic:empty  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.sentDate" >
        <html:link forward="subcontractInvoiceItemEdit" paramId="subcontractInvoiceItemUser.subcontractInvoiceItemId" paramName="subcontractInvoiceItem" paramProperty="subcontractInvoiceItemId" >
				  <bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItem" property="rate" format="#0.00"/>
        </html:link>
</logic:empty>        
<logic:notEmpty  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.sentDate" >
				  <bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoiceItem" property="rate" format="#0.00"/>
</logic:notEmpty>
</logic:greaterThan>        
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
<logic:greaterThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
	      Invoice Total
</logic:greaterThan>
<logic:lessThan  name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.subcontractInvoiceId" value="0" >
			  Credit Note Total
</logic:lessThan>			  
			</td>
			<td align="right" >
				<bean:message key="label.currencySymbol"/><bean:write name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.value" format="#,##0.00"/>
			</td>
	  </tr>		
<logic:notEmpty name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.notes">
		<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	    <td align="left" colspan="7">
	      Notes:-<br />
	      <html:textarea name="SubcontractInvoiceFormAgy" property="subcontractInvoiceUser.notes" style="width:100%" styleId="message" cols="100" rows="12" disabled="true" />      
	    </td>
	  </tr>
</logic:notEmpty>
</table>	
