<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="subcontractInvoiceFile" name="NhsBackingReportFormAgy" property="subcontractInvoiceFile" type="java.lang.String"/>
<bean:define id="nhsInvoiceFile" name="NhsBackingReportFormAgy" property="nhsInvoiceFile" type="java.lang.String"/>
<bean:define id="nhsBackingReportDocumentationFile" name="NhsBackingReportFormAgy" property="nhsBackingReportDocumentationFile" type="java.lang.String"/>
<bean:define id="nhsBackingReportRejectedDocumentationFile" name="NhsBackingReportFormAgy" property="nhsBackingReportRejectedDocumentationFile" type="java.lang.String"/>
<% 
String subcontractInvoiceFileUrl = request.getContextPath() + subcontractInvoiceFile;
String nhsInvoiceFileUrl = request.getContextPath() + nhsInvoiceFile;
String nhsBackingReportDocumentationFileUrl = request.getContextPath() + nhsBackingReportDocumentationFile;
String nhsBackingReportRejectedDocumentationFileUrl = request.getContextPath() + nhsBackingReportRejectedDocumentationFile;
%>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBackingReportView"/>
		</td>
<mmj-agy:hasAccess forward="nhsBackingReportSubcontract">
  <logic:greaterThan  name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractAgencyId" value="0">
    <html:form action="/nhsBackingReportSubcontract.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="nhsBackingReportUser.nhsBackingReportId" value="<bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.nhsBackingReportId"/>"/>
    <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton">Subcontract</html:submit></td>
    </html:form>
  </logic:greaterThan>  
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBackingReportEdit">
    <html:form action="/nhsBackingReportEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="nhsBackingReportUser.nhsBackingReportId" value="<bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.nhsBackingReportId"/>"/>
    <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="nhsBackingReportDelete">
  <html:form action="/nhsBackingReportDelete.do" onsubmit="return singleSubmit();">
    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.nhsBackingReportId"/>
    <td align="right" valign="middle" width="80"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
  </html:form>
</mmj-agy:hasAccess>
  </tr>
</table>

<table class="simple" width="100%">
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.name"/></th>
    <td align="left">
	<logic:notEmpty  name="NhsBackingReportFormAgy" property="nhsInvoiceFile">
	  <html:link href="<%= nhsInvoiceFileUrl %>" target="pdf" titleKey="title.viewNhsInvoice">
	      <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.name"/>
	  </html:link>
	      - <bean:write name="NhsBackingReportFormAgy" property="nhsInvoiceFileDate"  format="dd-MMM-yyyy hh:mm:ss"/>
	</logic:notEmpty>      
	<logic:empty  name="NhsBackingReportFormAgy" property="nhsInvoiceFile">
	      <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.name"/>
	</logic:empty>
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.client"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.clientName"/></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.startDate"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.startDate"  format="EEE dd-MMM-yyyy"/></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.endDate"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.endDate" format="EEE dd-MMM-yyyy"/></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.agreedValue" /></th>
    <td align="left"><bean:message key="label.currencySymbol"/><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.agreedValue" format="#,##0.00"/></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.paidValue" /></th>
    <td align="left"><bean:message key="label.currencySymbol"/><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.paidValue" format="#,##0.00"/></td>
  </tr>
<logic:greaterThan  name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractAgencyId" value="0">
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.subcontractValue" /></th>
    <td align="left"><bean:message key="label.currencySymbol"/><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractValue" format="#,##0.00"/></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.subcontractPaidDate"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractPaidDate" format="EEE dd-MMM-yyyy"/></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.subcontractInvoice"/></th>
    <td align="left">
  <logic:notEmpty  name="NhsBackingReportFormAgy" property="subcontractInvoiceFile">
    <html:link href="<%= subcontractInvoiceFileUrl %>" target="pdf" titleKey="title.viewSubcontractInvoice">
        <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.name"/>SUB
    </html:link>
        - <bean:write name="NhsBackingReportFormAgy" property="subcontractInvoiceFileDate"  format="dd-MMM-yyyy hh:mm:ss"/>
  </logic:notEmpty>      
  <logic:empty  name="NhsBackingReportFormAgy" property="subcontractInvoiceFile">
        &nbsp;
  </logic:empty>
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.documentationSent"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractDocumentationSentDate" format="EEE dd-MMM-yyyy"/></td>
  </tr>
</logic:greaterThan>  
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.completeDate"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.completeDate" format="EEE dd-MMM-yyyy"/></td>
  </tr>
  <tr>
    <th width="25%" valign="top" align="left" class="label">
      <bean:message key="label.comment"/><br />
    </th>
    <td align="left"><html:textarea name="NhsBackingReportFormAgy" property="nhsBackingReportUser.comment" styleId="message" cols="75" rows="12" disabled="true" /></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.documentation" /></th>
    <td align="left">
<logic:notEmpty  name="NhsBackingReportFormAgy" property="nhsBackingReportDocumentationFile">
  <html:link href="<%= nhsBackingReportDocumentationFileUrl %>" target="pdf" titleKey="title.viewNhsBackingReportDocumentation">
      <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.documentationFileName"/>
  </html:link>
      (<bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportDocumentationFileSize"  format="##,##0"/>kb)
      - <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportDocumentationFileDate"  format="dd-MMM-yyyy hh:mm:ss"/>
</logic:notEmpty>      
<logic:empty  name="NhsBackingReportFormAgy" property="nhsBackingReportDocumentationFile">
      Not Found
</logic:empty>      
    </td>
  </tr>

  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.rejectedDocumentation" /></th>
    <td align="left">
<logic:notEmpty  name="NhsBackingReportFormAgy" property="nhsBackingReportRejectedDocumentationFile">
  <html:link href="<%= nhsBackingReportRejectedDocumentationFileUrl %>" target="pdf" titleKey="title.viewNhsBackingReportRejectedDocumentation">
      <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.rejectedDocumentationFileName"/>
  </html:link>
      (<bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportRejectedDocumentationFileSize"  format="##,##0"/>kb)
      - <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportRejectedDocumentationFileDate"  format="dd-MMM-yyyy hh:mm:ss"/>
</logic:notEmpty>      
    </td>
  </tr>

  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.tradeshiftDocumentId"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.tradeshiftDocumentId" /></td>
  </tr>
</table>
