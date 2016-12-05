<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.upliftMinutesEdit"/>
<mmj-admin:hasAccess forward="upliftView">
  &nbsp;<html:link forward="upliftView" paramId="uplift.upliftId" paramName="UpliftMinutesFormAdmin" paramProperty="uplift.upliftId"><bean:message key="link.uplift"/></html:link>
</mmj-admin:hasAccess>

<br/>
<br/>
<html:errors/>

<html:form action="upliftMinutesEditProcess.do">
<html:hidden property="uplift.upliftId"/>
<table>
  <tr>
    <th align="left"><bean:message key="label.time" /></th>
    <th align="right"><bean:message key="label.upliftFactor" /></th>
    <th align="right"><bean:message key="label.upliftValue" /></th>
  </tr>
	<logic:iterate id="upliftMinuteUser" name="UpliftMinutesFormAdmin" property="list" type="com.helmet.bean.UpliftMinuteUser" indexId="index">
  <tr>
    <td align="left">
      <bean:write name="upliftMinuteUser" property="upliftHour" format="00"/>:<bean:write name="upliftMinuteUser" property="upliftMinute" format="00"/>
    </td>
    <td align="right">
      <input type="text" size="8" name="upliftFactor-<bean:write name="upliftMinuteUser" property="upliftMinuteId" />" value="<bean:write name="upliftMinuteUser" property="upliftFactor" />">
    </td>
    <td align="right">
      <input type="text" size="8" name="upliftValue-<bean:write name="upliftMinuteUser" property="upliftMinuteId" />" value="<bean:write name="upliftMinuteUser" property="upliftValue" />">
      <input type="hidden" name="noOfChanges-<bean:write name="upliftMinuteUser" property="upliftMinuteId" />" value="<bean:write name="upliftMinuteUser" property="noOfChanges" />">
    </td>
  </tr>
	</logic:iterate>
  <tr>
    <td colspan="3" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>
</html:form>
