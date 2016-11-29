<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="clients" name="NhsBackingReportFormAgy" property="clientAgencyUserList"/>

<html:form action="/nhsBackingReportNewProcess.do" enctype="multipart/form-data" onsubmit="return singleSubmit();">
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBackingReportNew" />
		</td>
<mmj-agy:hasAccess forward="nhsBackingReportNew">
    <td align="right" valign="middle" width="75">
		  <html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit>
    </td>
</mmj-agy:hasAccess>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.name"/></th>
    <td align="left"><html:text name="NhsBackingReportFormAgy" property="nhsBackingReportUser.name" /></td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.client"/></th>
    <td align="left">
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
      <html:file property="nhsBackingReportDocumentationFormFile" size="50" />&nbsp;(Backing Report Documentation PDF)
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.rejectedDocumentation" /></th>
    <td align="left">
      <html:file property="nhsBackingReportRejectedDocumentationFormFile" size="50" />&nbsp;(Backing Report Rejected Documentation PDF)
    </td>
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
