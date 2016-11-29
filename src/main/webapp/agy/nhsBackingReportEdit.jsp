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

<html:form action="/nhsBackingReportEditProcess.do" enctype="multipart/form-data" onsubmit="return singleSubmit();">
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBackingReportEdit" />
		</td>
<mmj-agy:hasAccess forward="nhsBackingReportEdit">
    <td align="right" valign="middle" width="75">
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.nhsBackingReportId"/>
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractAgencyId"/>
		    <html:hidden name="NhsBackingReportFormAgy" property="canChangeName"/>
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.documentationFileName"/>
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.rejectedDocumentationFileName"/>
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractValue"/>
  <logic:equal name="NhsBackingReportFormAgy" property="canChangeName" value="false">
		    <html:hidden name="NhsBackingReportFormAgy" property="nhsBackingReportUser.name"/>
  </logic:equal>
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
<logic:equal name="NhsBackingReportFormAgy" property="canChangeName" value="true">
     <html:text name="NhsBackingReportFormAgy" property="nhsBackingReportUser.name" />
</logic:equal>
<logic:notEqual name="NhsBackingReportFormAgy" property="canChangeName" value="true">
  <logic:notEmpty  name="NhsBackingReportFormAgy" property="nhsInvoiceFile">
	  <html:link href="<%= nhsInvoiceFileUrl %>" target="pdf" titleKey="title.viewNhsInvoice">
	      <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.name"/>
	  </html:link>
	      - <bean:write name="NhsBackingReportFormAgy" property="nhsInvoiceFileDate"  format="dd-MMM-yyyy hh:mm:ss"/>
	</logic:notEmpty>      
	<logic:empty  name="NhsBackingReportFormAgy" property="nhsInvoiceFile">
	      <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.name"/>
	</logic:empty>      
</logic:notEqual>
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.client"/></th>
    <td align="left">
	    <bean:define id="clients" name="NhsBackingReportFormAgy" property="clientAgencyUserList"/>
	    <html:select name="NhsBackingReportFormAgy" property="nhsBackingReportUser.clientId">
	      <html:options collection="clients" labelProperty="clientName" property="clientId" />
	    </html:select>
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.startDate"/></th>
    <td align="left">
      <html:text name="NhsBackingReportFormAgy" property="startDateStr" styleId="startDate" title="dd/MM/yyyy"/>
			<input type="reset" value=" ... " id="startDateButton">
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.endDate"/></th>
    <td align="left">
      <html:text name="NhsBackingReportFormAgy" property="endDateStr" styleId="endDate" title="dd/MM/yyyy"/>
			<input type="reset" value=" ... " id="endDateButton">
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.agreedValue" /></th>
    <td align="left"><html:text name="NhsBackingReportFormAgy" property="nhsBackingReportUser.agreedValue" /></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.paidValue" /></th>
    <td align="left"><html:text name="NhsBackingReportFormAgy" property="nhsBackingReportUser.paidValue" /></td>
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
</logic:greaterThan>  
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.documentationSent"/></th>
    <td align="left"><bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.subcontractDocumentationSentDate" format="EEE dd-MMM-yyyy"/></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.completeDate"/></th>
    <td align="left">
      <html:text name="NhsBackingReportFormAgy" property="completeDateStr" styleId="completeDate" title="dd/MM/yyyy"/>
			<input type="reset" value=" ... " id="completeDateButton">
    </td>
  </tr>
  <tr>
    <th width="25%" valign="top" align="left" class="label">
      <bean:message key="label.comment"/><br />
    </th>
    <td align="left"><html:textarea name="NhsBackingReportFormAgy" property="nhsBackingReportUser.comment" styleId="message" cols="75" rows="12" /></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.documentation" /></th>
    <td align="left">
      <html:file property="nhsBackingReportDocumentationFormFile" size="50" />&nbsp; 
<logic:notEmpty  name="NhsBackingReportFormAgy" property="nhsBackingReportDocumentationFile">
  <html:link href="<%= nhsBackingReportDocumentationFileUrl %>" target="pdf" titleKey="title.viewNhsBackingReportDocumentation">
      <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportUser.documentationFileName"/>
  </html:link>
      (<bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportDocumentationFileSize"  format="##,##0"/>kb)
      - <bean:write name="NhsBackingReportFormAgy" property="nhsBackingReportDocumentationFileDate"  format="dd-MMM-yyyy hh:mm:ss"/>
</logic:notEmpty>      
    </td>
  </tr>

  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.rejectedDocumentation" /></th>
    <td align="left">
      <html:file property="nhsBackingReportRejectedDocumentationFormFile" size="50" />&nbsp; 
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

<script type="text/javascript">//<![CDATA[
  var cal3 = Zapatec.Calendar.setup({
		    firstDay          : 1,
		    showOthers        : true,
		    step              : 1,
		    electric          : false,
		    inputField        : "startDate",
		    button            : "startDateButton",
		    ifFormat          : "%d/%m/%Y",
		    daFormat          : "%d/%m/%Y",
        showsTime         : false
		  });
      Zapatec.Calendar.setup({
		    firstDay          : 1,
		    showOthers        : true,
		    step              : 1,
		    electric          : false,
		    inputField        : "endDate",
		    button            : "endDateButton",
		    ifFormat          : "%d/%m/%Y",
		    daFormat          : "%d/%m/%Y",
        showsTime         : false
		  });
      Zapatec.Calendar.setup({
		    firstDay          : 1,
		    showOthers        : true,
		    step              : 1,
		    electric          : false,
		    inputField        : "completeDate",
		    button            : "completeDateButton",
		    ifFormat          : "%d/%m/%Y",
		    daFormat          : "%d/%m/%Y",
        showsTime         : false
		  });
//]]></script>
</html:form>
