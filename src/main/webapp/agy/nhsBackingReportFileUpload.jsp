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
<html:form action="/nhsBackingReportFileUploadProcess.do" focus="nhsBackingReportFormFile" enctype="multipart/form-data" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBackingReportFileUpload"/>
		</td>
    <mmj-agy:hasAccess forward="nhsBackingReportFileUpload">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.upload"/></html:submit></td>
		</mmj-agy:hasAccess>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th width="25%" align="left" class="label">
      <bean:message key="label.client"/>
    </th>
    <td>
      <bean:define id="clients" name="NhsBackingReportFileUploadFormAgy" property="list"/>
      <html:select name="NhsBackingReportFileUploadFormAgy" property="client.clientId">
        <html:options collection="clients" labelProperty="listName" property="clientId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <th width="25%" align="left" class="label"><bean:message key="label.nhsBackingReportFile"/></th>
    <td align="left"><html:file name="NhsBackingReportFileUploadFormAgy" property="nhsBackingReportFormFile" size="50" tabindex="1" /></td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      &nbsp;
    </td>
  </tr>
  <tr>
    <td align="left" colspan="2">
      Example:-<br />
      <html:textarea name="NhsBackingReportFileUploadFormAgy" property="nhsBackingReportExampleText" style="width:100%" styleId="message" cols="100" rows="12" disabled="true" />      
    </td>
  </tr>
</table>
</html:form>
