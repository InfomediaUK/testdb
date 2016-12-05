<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.upliftEdit"/>
<mmj-admin:hasAccess forward="upliftList">
      <html:link forward="upliftList" paramId="client.clientId" paramName="UpliftFormAdmin" paramProperty="client.clientId"><bean:message key="link.upliftList"/></html:link>
</mmj-admin:hasAccess>

<br/>
<br/>

<html:errors/>

<html:form action="upliftEditProcess.do" focus="uplift.upliftDay">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="uplift.clientId" />

<html:hidden property="uplift.upliftId"/>
<html:hidden property="uplift.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="client.name"/> (<bean:write name="UpliftFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftDay"/></td>
    <td align="left"><html:text property="uplift.upliftDay"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftHour"/></td>
    <td align="left"><html:text property="uplift.upliftHour"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftMinutePeriod"/></td>
    <td align="left">
      <html:select name="UpliftFormAdmin" property="uplift.upliftMinutePeriod" >
        <html:option value="0">-----</html:option>
        <html:option value="1">1</html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftFactor"/></td>
    <td align="left"><html:text property="uplift.upliftFactor"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftValue"/></td>
    <td align="left"><html:text property="uplift.upliftValue"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
<br />
<br />
<logic:present name="UpliftFormAdmin" property="list" >
<table>
  <tr>
    <th align="left"><bean:message key="label.time" /></th>
    <th align="right"><bean:message key="label.upliftFactor" /></th>
    <th align="right"><bean:message key="label.upliftValue" /></th>
  </tr>
	<logic:iterate id="upliftMinuteUser" name="UpliftFormAdmin" property="list" type="com.helmet.bean.UpliftMinuteUser">
  <tr>
    <td align="left"><bean:write name="upliftMinuteUser" property="upliftHour" format="00" />:<bean:write name="upliftMinuteUser" property="upliftMinute" format="00"/></td>
    <td align="right"><bean:write name="upliftMinuteUser" property="upliftFactor" /></td>
    <td align="right"><bean:write name="upliftMinuteUser" property="upliftValue" /></td>
  </tr>
	</logic:iterate>
</table>
</logic:present>
