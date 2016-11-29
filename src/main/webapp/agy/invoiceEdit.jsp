<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/invoiceEditProcess.do" focus="invoiceAgency.reference" onsubmit="return singleSubmit();">
<html:hidden property="invoiceAgency.invoiceId" />
<html:hidden property="invoiceAgency.invoiceAgencyId" />
<html:hidden property="invoiceAgency.noOfChanges" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.invoiceEdit"/>
		</td>
    <mmj-agy:hasAccess forward="invoiceEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.reference"/></th>
    <td align="left" width="65%"><html:text property="invoiceAgency.reference"/></td>
  </tr>
</html:form>
</table>