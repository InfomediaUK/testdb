<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="nhsBackingReportUser" name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportUser" type="com.helmet.bean.NhsBackingReportUser"/>
<bean:define id="subcontractInvoiceFile" name="NhsBackingReportShiftListFormAgy" property="subcontractInvoiceFile" type="java.lang.String"/>
<bean:define id="nhsInvoiceFile" name="NhsBackingReportShiftListFormAgy" property="nhsInvoiceFile" type="java.lang.String"/>
<bean:define id="nhsBackingReportDocumentationFile" name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportDocumentationFile" type="java.lang.String"/>
<bean:define id="nhsBackingReportRejectedDocumentationFile" name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportRejectedDocumentationFile" type="java.lang.String"/>
<% 
String subcontractInvoiceFileUrl = request.getContextPath() + subcontractInvoiceFile;
String nhsInvoiceFileUrl = request.getContextPath() + nhsInvoiceFile;
String nhsBackingReportDocumentationFileUrl = request.getContextPath() + nhsBackingReportDocumentationFile;
String nhsBackingReportRejectedDocumentationFileUrl = request.getContextPath() + nhsBackingReportRejectedDocumentationFile;
Integer noOfColumns = new Integer(nhsBackingReportUser.getSubcontractAgencyId().equals(0) ? 7 : 9);
%>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:define id="list" name="NhsBackingReportShiftListFormAgy" property="list" type="java.util.List"/>
		  <bean:message key="title.nhsBackingReportShiftList"/>&nbsp;(<%=list.size() %>)
		</td>

	<mmj-agy:hasAccess forward="nhsBackingReportDocumentationSend">
	    <td align="right" valign="middle" width="75">
	  <html:form action="nhsBackingReportDocumentationSend.do" onsubmit="return singleSubmit();">
	      <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
		  <html:hidden name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportUser.nhsBackingReportId"/>
	  </html:form>
	    </td>
	</mmj-agy:hasAccess>

<logic:notEmpty name="NhsBackingReportShiftListFormAgy" property="list">
	<mmj-agy:hasAccess forward="nhsBackingReportInvoice">
	    <td align="right" valign="middle" width="75">
	  <html:form action="nhsBackingReportInvoice.do" onsubmit="return singleSubmit();">
	      <html:submit styleClass="titleButton"><bean:message key="button.invoice"/></html:submit>
			  <html:hidden name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportUser.nhsBackingReportId"/>
			  <html:hidden name="NhsBackingReportShiftListFormAgy" property="nhsLocation"/>
			  <html:hidden name="NhsBackingReportShiftListFormAgy" property="nhsWard"/>
	  </html:form>
	    </td>
	</mmj-agy:hasAccess>
</logic:notEmpty>
<logic:notEmpty name="NhsBackingReportShiftListFormAgy" property="nhsInvoiceFile">
  <mmj-agy:hasAccess forward="nhsBackingReportInvoiceTradeshiftUpload">
      <td>
        &nbsp;
      </td>
      <td align="right" valign="middle" width="75">
    <html:form action="nhsBackingReportInvoiceTradeshiftUpload.do" onsubmit="return singleSubmit();">
        <html:submit styleClass="titleButton">Tradeshift</html:submit>
        <html:hidden name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportUser.nhsBackingReportId"/>
        <html:hidden name="NhsBackingReportShiftListFormAgy" property="nhsLocation"/>
        <html:hidden name="NhsBackingReportShiftListFormAgy" property="nhsWard"/>
    </html:form>
      </td>
  </mmj-agy:hasAccess>
</logic:notEmpty>
  </tr>
</table>
<table class="simple" width="100%" border="1">
  <thead>
    <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.client" /></th>
    <th align="left"><bean:message key="label.startDate" /></th>
    <th align="left"><bean:message key="label.endDate" /></th>
    <th align="right"><bean:message key="label.agreed" /></th>
    <th align="right"><bean:message key="label.paid" /></th>
    <th align="right"><bean:message key="label.complete" /></th>
<logic:greaterThan  name="nhsBackingReportUser" property="subcontractAgencyId" value="0">
    <th align="right"><bean:message key="label.sub" /></th>
    <th align="right"><bean:message key="label.subPaid" /></th>
</logic:greaterThan>
    </tr>
  </thead>
  <tr>
    <td align="left">
<mmj-agy:hasAccess forward="nhsBackingReportView" >
      <html:link forward="nhsBackingReportView" paramId="nhsBackingReportUser.nhsBackingReportId" paramName="nhsBackingReportUser" paramProperty="nhsBackingReportId" titleKey="title.nhsBackingReportView"><bean:write name="nhsBackingReportUser" property="name"/></html:link>
</mmj-agy:hasAccess>
<mmj-agy:hasNoAccess forward="nhsBackingReportView" >
      <bean:write name="nhsBackingReportUser" property="name"/>
</mmj-agy:hasNoAccess>
    </td>
    <td align="left"><bean:write name="nhsBackingReportUser" property="clientName"/></td>
    <td align="left"><bean:write name="nhsBackingReportUser" property="startDate" formatKey="format.longDateFormat"/></td>
    <td align="left"><bean:write name="nhsBackingReportUser" property="endDate" formatKey="format.longDateFormat"/></td>
    <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="agreedValue" format="#,##0.00"/></td>
    <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="paidValue" format="#,##0.00"/></td>
    <td align="right"><bean:write name="nhsBackingReportUser" property="completeDate" formatKey="format.longDateFormat"/></td>
<logic:greaterThan  name="nhsBackingReportUser" property="subcontractAgencyId" value="0">
    <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="nhsBackingReportUser" property="subcontractValue" format="#,##0.00"/></td>
    <td align="right"><bean:write name="nhsBackingReportUser" property="subcontractPaidDate" formatKey="format.longDateFormat"/></td>
</logic:greaterThan>
  </tr>
<logic:notEmpty  name="nhsBackingReportUser" property="comment">
  <tr>
    <td colspan="<%= noOfColumns %>" align="left"><bean:write name="nhsBackingReportUser" property="comment"/></td>
  </tr>
</logic:notEmpty>
  <tr>
    <td colspan="<%= noOfColumns %>" align="left">
<mmj-agy:hasAccess forward="nhsBackingReportSubcontract">
	<logic:greaterThan  name="nhsBackingReportUser" property="subcontractAgencyId" value="0">
	  <logic:notEmpty  name="NhsBackingReportShiftListFormAgy" property="subcontractInvoiceFile">
		  <bean:define id="subcontractInvoiceLinkTitle" name="NhsBackingReportShiftListFormAgy" property="subcontractInvoiceLinkTitle" type="java.lang.String"/>
		  <bean:message key="label.subcontractInvoice" />:&nbsp;
		  <html:link href="<%= subcontractInvoiceFileUrl %>" target="pdf" title="<%= subcontractInvoiceLinkTitle %>">
		    <bean:write name="nhsBackingReportUser" property="name"/>SUB.pdf
		  </html:link>
		  &nbsp;&nbsp;
	  </logic:notEmpty> 
	</logic:greaterThan>
</mmj-agy:hasAccess>     
<mmj-agy:hasAccess forward="nhsBackingReportInvoice">
</mmj-agy:hasAccess>	     
	<logic:notEmpty  name="NhsBackingReportShiftListFormAgy" property="nhsInvoiceFile">
	  <bean:define id="nhsInvoiceLinkTitle" name="NhsBackingReportShiftListFormAgy" property="nhsInvoiceLinkTitle" type="java.lang.String"/>
	  <bean:message key="label.invoice" />:&nbsp;
	  <html:link href="<%= nhsInvoiceFileUrl %>" target="pdf" title="<%= nhsInvoiceLinkTitle %>">
	    <bean:write name="NhsBackingReportShiftListFormAgy" property="nhsInvoiceFileName"/>
	  </html:link>
	  &nbsp;&nbsp;
	</logic:notEmpty>      
	<logic:empty  name="NhsBackingReportShiftListFormAgy" property="nhsInvoiceFile">
	      <bean:message key="label.invoiceNotCreated" />.&nbsp;&nbsp;
	</logic:empty> 
<logic:notEmpty  name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportDocumentationFile">
  <bean:define id="nhsBackingReportDocumentationLinkTitle" name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportDocumentationLinkTitle" type="java.lang.String"/>
  <bean:message key="label.documentation" />:&nbsp;
  <html:link href="<%= nhsBackingReportDocumentationFileUrl %>" target="pdf" title="<%= nhsBackingReportDocumentationLinkTitle %>">
    <bean:write name="nhsBackingReportUser" property="documentationFileName"/>
  </html:link>
  &nbsp;&nbsp;
</logic:notEmpty>      
<logic:empty  name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportDocumentationFile">
      <bean:message key="label.documentationNotUploaded" />.&nbsp;&nbsp;
</logic:empty>

<logic:notEmpty  name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportRejectedDocumentationFile">
  <bean:define id="nhsBackingReportRejectedDocumentationLinkTitle" name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportRejectedDocumentationLinkTitle" type="java.lang.String"/>
  <bean:message key="label.rejectedDocumentation" />:&nbsp;
  <html:link href="<%= nhsBackingReportRejectedDocumentationFileUrl %>" target="pdf" title="<%= nhsBackingReportRejectedDocumentationLinkTitle %>">
    <bean:write name="nhsBackingReportUser" property="rejectedDocumentationFileName"/>
  </html:link>
</logic:notEmpty>      
<logic:empty  name="NhsBackingReportShiftListFormAgy" property="nhsBackingReportRejectedDocumentationFile">
      <bean:message key="label.rejectedDocumentationNotUploaded" />.
</logic:empty> 
 
<logic:notEmpty  name="nhsBackingReportUser" property="tradeshiftDocumentId" >
      &nbsp;&nbsp;<bean:message key="label.tradeshiftDocumentId" />:&nbsp;<bean:write name="nhsBackingReportUser" property="tradeshiftDocumentId"/>
</logic:notEmpty>      
<logic:notEmpty  name="nhsBackingReportUser" property="subcontractDocumentationSentDate" >
      &nbsp;&nbsp;<bean:message key="label.sent" />:&nbsp;<bean:write name="nhsBackingReportUser" property="subcontractDocumentationSentDate" formatKey="format.longDateFormat"/>
</logic:notEmpty>      
    </td>
  </tr>  
</table>
<jsp:include page="shiftsIncludeByLocation.jsp" flush="true">
  <jsp:param name="theFormAgy" value="NhsBackingReportShiftListFormAgy"/>
</jsp:include>
<logic:greaterThan  name="nhsBackingReportUser" property="subcontractAgencyId" value="0">
  * Subcontracted Staff
</logic:greaterThan>
