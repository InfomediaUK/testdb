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
<bean:message key="title.publicHolidayDelete"/>
		</td>
		<html:form action="publicHolidayDeleteProcess.do" onsubmit="return singleSubmit();">
		<mmj-mgr:hasAccess forward="publicHolidayDelete">
		<html:hidden property="publicHoliday.publicHolidayId"/>
		<html:hidden property="publicHoliday.noOfChanges"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><bean:write name="PublicHolidayFormMgr" property="publicHoliday.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.date"/></th>
    <td align="left"><bean:write name="PublicHolidayFormMgr" property="publicHoliday.phDate" formatKey="format.longDateFormat"/></td>
  </tr></table>