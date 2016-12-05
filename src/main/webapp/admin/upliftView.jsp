<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.upliftMinutesView"/>
<mmj-admin:hasAccess forward="upliftList">
  &nbsp;<html:link forward="upliftList" paramId="client.clientId" paramName="UpliftFormAdmin" paramProperty="client.clientId"><bean:message key="link.upliftList"/></html:link>
</mmj-admin:hasAccess>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="UpliftFormAdmin" paramProperty="client.clientId"><bean:write name="UpliftFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="JobFamilyViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2">
      <bean:message key="label.uplift"/>
    </th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftDay"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftDay"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftHour"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftHour"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftMinutePeriod"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftMinutePeriod"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftFactor"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftFactor"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftValue"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.upliftValue"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="UpliftFormAdmin" property="uplift.active"/></td>
  </tr>
</table>

<logic:equal name="UpliftFormAdmin" property="uplift.active" value="true">
<mmj-admin:hasAccess forward="upliftEdit">
  <html:link forward="upliftEdit" paramId="uplift.upliftId" paramName="UpliftFormAdmin" paramProperty="uplift.upliftId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="upliftDelete">
  <html:link forward="upliftDelete" paramId="uplift.upliftId" paramName="UpliftFormAdmin" paramProperty="uplift.upliftId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>
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
<mmj-admin:hasAccess forward="upliftMinutesEdit">
  <html:link forward="upliftMinutesEdit" paramId="uplift.upliftId" paramName="UpliftFormAdmin" paramProperty="uplift.upliftId"><bean:message key="link.edit"/></html:link>
</mmj-admin:hasAccess>
</logic:present>
