<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <html:form action="/applicantDeleteAgencyWorkerChecklistFileProcess.do" onsubmit="return singleSubmit();">
	<html:hidden property="applicantId" />
	<html:hidden property="filename" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.applicantDeleteAgencyWorkerChecklistFile"/>
		</td>
	<mmj-agy:hasAccess forward="applicantEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
	</mmj-agy:hasAccess>
  </tr>
  </html:form>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%">
      <bean:write name="ApplicantDeleteAgencyWorkerChecklistFileFormAgy" property="agencyWorkerChecklistFile.bookingId"/>&nbsp;
      <bean:write name="ApplicantDeleteAgencyWorkerChecklistFileFormAgy" property="agencyWorkerChecklistFile.clientName"/>&nbsp;
			<bean:write name="ApplicantDeleteAgencyWorkerChecklistFileFormAgy" property="agencyWorkerChecklistFile.fileDate" filter="false"  formatKey="format.mediumDateTimeFormat"/>
    </th>
    <td align="left" width="65%">
      <bean:write name="ApplicantDeleteAgencyWorkerChecklistFileFormAgy" property="filename"/>
    </td>
  </tr>
</table>