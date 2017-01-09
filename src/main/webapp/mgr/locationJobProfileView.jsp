<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.locationJobProfileView"/>
	</td>
<mmj-mgr:hasAccess forward="locationJobProfileEdit">
    <html:form action="/locationJobProfileEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="locationJobProfile.locationJobProfileId" value="<bean:write name="LocationJobProfileFormMgr" property="locationJobProfile.locationJobProfileId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
    <html:form action="/locationJobProfileView.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="locationJobProfile.locationId" value="<bean:write name="LocationJobProfileFormMgr" property="locationJobProfile.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.budget"/></th>
    <td align="left" width="65%"><bean:write name="LocationJobProfileFormMgr" property="locationJobProfile.budget" format="#,##0.00" /></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.rate"/></th>
    <td align="left"><bean:write name="LocationJobProfileFormMgr" property="locationJobProfile.rate" format="#,##0.00" /></td>
  </tr>
</table>
