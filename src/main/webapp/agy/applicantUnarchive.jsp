<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <td align="left" valign="middle" class="title">
<bean:message key="title.applicantUnarchive"/>
    </td>
<logic:equal name="ApplicantFormAgy" property="applicant.active" value="true">
<mmj-agy:hasAccess forward="applicantUnarchive">
    <html:form action="applicantUnarchiveProcess.do" onsubmit="return singleSubmit();">
    <html:hidden property="applicant.applicantId" />
    <html:hidden property="applicant.noOfChanges" />
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
</logic:equal>
  </tr>
</table>
<jsp:include page="applicantConfirmView.jsp" flush="true"/>
