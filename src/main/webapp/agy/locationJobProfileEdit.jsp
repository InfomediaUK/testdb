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
<html:form action="/locationJobProfileEditProcess.do" focus="locationJobProfile.rate" onsubmit="return singleSubmit();">
<html:hidden property="locationJobProfile.locationId"/>
<html:hidden property="locationJobProfile.locationJobProfileId"/>
<html:hidden property="locationJobProfile.noOfChanges"/>
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.locationJobProfileEdit"/>
	</td>
    <mmj-agy:hasAccess forward="locationJobProfileEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.budget"/></th>
    <td align="left" width="65%"><bean:write name="LocationJobProfileFormAgy" property="locationJobProfile.budget" format="#,##0.00" /></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.rate"/></th>
    <td align="left"><html:text property="locationJobProfile.rate"/></td>
  </tr>
</html:form>
</table>
