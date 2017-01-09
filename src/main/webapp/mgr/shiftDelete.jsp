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
<bean:message key="title.shiftDelete"/>
		</td>
		<html:form action="shiftDeleteProcess.do" onsubmit="return singleSubmit();">
		<mmj-mgr:hasAccess forward="shiftDelete">
    <html:hidden property="shift.locationId"/>
		<html:hidden property="shift.shiftId"/>
		<html:hidden property="shift.noOfChanges"/>
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
    <td align="left" width="65%"><bean:write name="ShiftFormMgr" property="shift.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.description"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.description"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.startTime"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.startTime" format="HH:mm"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.endTime"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.endTime" format="HH:mm"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.breakStartTime"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.breakStartTime" format="HH:mm"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.breakNoOfHours"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.breakNoOfHours" format="#0.00"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.breakEndTime"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.breakEndTime" format="HH:mm"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.noOfHours"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.noOfHours" format="#0.00"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.upliftFactor"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.upliftFactor" format="#0.00"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.upliftValue"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.upliftValue" format="#0.00"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.useShiftUplift"/></th>
    <td align="left">
    <logic:equal name="ShiftFormMgr" property="shift.useShiftUplift" value="true">
  	  <bean:message key="label.yes"/>
    </logic:equal>
    <logic:notEqual name="ShiftFormMgr" property="shift.useShiftUplift" value="true">
      <bean:message key="label.no"/>
    </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.overtimeNoOfHours"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.overtimeNoOfHours" format="#0.00"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.overtimeUpliftFactor"/></th>
    <td align="left"><bean:write name="ShiftFormMgr" property="shift.overtimeUpliftFactor" format="#0.00"/></td>
  </tr>
</table>