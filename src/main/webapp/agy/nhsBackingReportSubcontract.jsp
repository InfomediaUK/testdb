<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="nhsInvoiceFile" name="NhsBackingReportFormAgy" property="nhsInvoiceFile" type="java.lang.String"/>
<bean:define id="nhsBackingReportFile" name="NhsBackingReportFormAgy" property="nhsBackingReportFile" type="java.lang.String"/>
<% 
String nhsInvoiceFileUrl = request.getContextPath() + nhsInvoiceFile;
String nhsBackingReportFileUrl = request.getContextPath() + nhsBackingReportFile;
%>

<html:form action="/nhsBackingReportSubcontractProcess.do" onsubmit="return singleSubmit();">
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBackingReportSubcontract" />
		</td>
<mmj-agy:hasAccess forward="nhsBackingReportEdit">
    <td align="right" valign="middle" width="75">
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.nhsBackingReportId"/>
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractAgencyId"/>
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.noOfChanges"/>
		    <html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit>
    </td>
</mmj-agy:hasAccess>
  </tr>
</table>
<html:errors/>
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
    <td align="left"><html:text name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractValue" /></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.subcontractPaidDate"/></th>
    <td align="left">
      <html:text name="NhsBackingReportFormAgy" property="subcontractPaidDateStr" styleId="subcontractPaidDate" title="dd/MM/yyyy"/>
			<input type="reset" value=" ... " id="subcontractPaidDateButton">
    </td>
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
<logic:notEmpty  name="NhsBackingReportFormAgy" property="nhsBackingReportFile">
  <html:link href="<%= nhsBackingReportFileUrl %>" target="pdf" titleKey="title.viewNhsBackingReportDocumentation">
      <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.documentationFileName"/>
  </html:link>
      (<bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportFileSize"  format="##,##0"/>kb)
      - <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportFileDate"  format="dd-MMM-yyyy hh:mm:ss"/>
</logic:notEmpty>      
<logic:empty  name="NhsBackingReportFormAgy" property="nhsBackingReportFile">
      Not Found
</logic:empty>      
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.tradeshiftDocumentId"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.tradeshiftDocumentId" /></td>
</table>

<script type="text/javascript">//<![CDATA[
  var cal3 = Zapatec.Calendar.setup({
          firstDay          : 1,
          showOthers        : true,
          step              : 1,
          electric          : false,
          inputField        : "subcontractPaidDate",
          button            : "subcontractPaidDateButton",
          ifFormat          : "%d/%m/%Y",
          daFormat          : "%d/%m/%Y",
          showsTime         : false
        });
//]]></script>
</html:form>
