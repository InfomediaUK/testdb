<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/agencyInvoiceEditProcess.do" focus="agencyInvoice.reference" onsubmit="return singleSubmit();">
<html:hidden property="agencyInvoice.agencyInvoiceId" />
<html:hidden property="agencyInvoice.noOfChanges" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.agencyInvoiceEdit"/>
		</td>
    <mmj-agy:hasAccess forward="agencyInvoiceEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.reference"/></th>
    <td align="left" width="65%"><html:text property="agencyInvoice.reference"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <th align="left"><bean:message key="label.discount"/></th>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.discountText"/></th>
    <td align="left"><html:text property="agencyInvoice.discountText"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.discountValue"/></th>
    <td align="left"><html:text property="agencyInvoice.discountValue"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.discountVatRate"/></th>
    <td align="left"><html:text property="agencyInvoice.discountVatRate"/></td>
  </tr>
</html:form>
</table>