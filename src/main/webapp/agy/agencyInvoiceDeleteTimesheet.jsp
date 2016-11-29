<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/agencyInvoiceDeleteTimesheetProcess.do" onsubmit="return singleSubmit();">
<html:hidden property="agencyInvoice.agencyInvoiceId" />
<html:hidden property="agencyInvoice.noOfChanges" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.agencyInvoiceDeleteTimesheet"/>
		</td>
    <mmj-agy:hasAccess forward="agencyInvoiceDeleteTimesheet">
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</mmj-agy:hasAccess>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.timesheetFilename"/></th>
    <td align="left" width="65%">
      <bean:write name="AgencyInvoiceDeleteTimesheetFormAgy" property="agencyInvoice.timesheetFilename"/>
    </td>
  </tr>
</html:form>
</table>