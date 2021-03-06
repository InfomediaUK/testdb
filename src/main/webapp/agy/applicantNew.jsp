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
<html:form action="/applicantNewProcess.do" focus="applicant.user.firstName" enctype="multipart/form-data" onsubmit="javascript:cal.submitFlatDates(); return singleSubmit();">
  <html:hidden property="unavailableDates" styleId="datesId" />
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.applicantNew"/>
		</td>
    <mmj-agy:hasAccess forward="applicantNew">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
  </tr>
</table>
  <html:errors/>
  <jsp:include page="applicantCommon.jsp" flush="true">
    <jsp:param name="applicantAction" value="new"/>
  </jsp:include>
</html:form>
