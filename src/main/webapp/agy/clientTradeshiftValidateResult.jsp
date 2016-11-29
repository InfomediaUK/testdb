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
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.clientTradeshiftValidateResult"/>
		</td>
  </tr>
</table>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.companyName"/></th>
    <td align="left" width="75%"><bean:write name="ClientTradeshiftValidateResultFormAgy" property="companyName"/></td>
  </tr>
  <tr>
    <th align="left" width="25%"><bean:message key="label.country"/></th>
    <td align="left" width="75%"><bean:write name="ClientTradeshiftValidateResultFormAgy" property="country"/></td>
  </tr>
  <tr>
    <th align="left" width="25%"><bean:message key="label.state"/></th>
    <td align="left" width="75%"><bean:write name="ClientTradeshiftValidateResultFormAgy" property="state"/></td>
  </tr>
<logic:notEmpty name="ClientTradeshiftValidateResultFormAgy" property="identifiers">
  <logic:iterate id="identifier" name="ClientTradeshiftValidateResultFormAgy" property="identifiers" indexId="identifierIndex">
  <tr>
    <th align="left" width="25%"><bean:write name="identifier" property="scheme"/></th>
    <td align="left" width="75%"><bean:write name="identifier" property="value"/></td>
  </tr>
  </logic:iterate>
</logic:notEmpty>  
  <tr>
    <th align="left" width="25%"><bean:message key="label.street"/></th>
    <td align="left" width="75%"><bean:write name="ClientTradeshiftValidateResultFormAgy" property="street"/></td>
  </tr>
  <tr>
    <th align="left" width="25%"><bean:message key="label.locality"/></th>
    <td align="left" width="75%"><bean:write name="ClientTradeshiftValidateResultFormAgy" property="locality"/></td>
  </tr>
  <tr>
    <th align="left" width="25%"><bean:message key="label.city"/></th>
    <td align="left" width="75%"><bean:write name="ClientTradeshiftValidateResultFormAgy" property="city"/></td>
  </tr>
  <tr>
    <th align="left" width="25%"><bean:message key="label.zip"/></th>
    <td align="left" width="75%"><bean:write name="ClientTradeshiftValidateResultFormAgy" property="zip"/></td>
  </tr>
</table>