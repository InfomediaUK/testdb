<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/subcontractInvoicePaymentsFileUploadProcess.do" focus="nhsPaymentFormFile" enctype="multipart/form-data" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.subcontractInvoicePaymentsFileUpload"/>
		</td>
    <mmj-agy:hasAccess forward="subcontractInvoicePaymentsFileUpload">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.upload"/></html:submit></td>
		</mmj-agy:hasAccess>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.subcontractInvoicePaymentsFile"/></th>
    <td align="left"><html:file name="SubcontractInvoicePaymentsFileUploadFormAgy" property="subcontractInvoicePaymentsFormFile" size="50" tabindex="1" /></td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      &nbsp;
    </td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      Example:-<br />
      <html:textarea name="SubcontractInvoicePaymentsFileUploadFormAgy" property="subcontractInvoicePaymentsExampleText" style="width:100%" styleId="message" cols="100" rows="12" disabled="true" />      
    </td>
  </tr>
</table>
</html:form>
