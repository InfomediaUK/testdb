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
<bean:message key="title.reasonForRequestView"/>
		</td>
<mmj-agy:hasAccess forward="reasonForRequestEdit">
    <html:form action="/reasonForRequestEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="reasonForRequest.reasonForRequestId" value="<bean:write name="ReasonForRequestFormAgy" property="reasonForRequest.reasonForRequestId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="reasonForRequestDelete">
    <html:form action="/reasonForRequestDelete.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="reasonForRequest.reasonForRequestId" value="<bean:write name="ReasonForRequestFormAgy" property="reasonForRequest.reasonForRequestId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="reasonForRequestView">
    <html:form action="/reasonForRequestView.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="reasonForRequest.clientId" value="<bean:write name="ReasonForRequestFormAgy" property="reasonForRequest.clientId"/>"/>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
</mmj-agy:hasAccess>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><bean:write name="ReasonForRequestFormAgy" property="reasonForRequest.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><bean:write name="ReasonForRequestFormAgy" property="reasonForRequest.code"/></td>
  </tr>
</table>
