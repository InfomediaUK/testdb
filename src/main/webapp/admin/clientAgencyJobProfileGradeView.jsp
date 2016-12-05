<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.clientAgencyJobProfileGradeView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.rate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.payRate"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.payRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.wtdPercentage"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.wtdPercentage"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.niPercentage"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.niPercentage"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.chargeRateVatRate"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.chargeRateVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.payRateVatRate"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.payRateVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.wtdVatRate"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.wtdVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.niVatRate"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.niVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.commissionVatRate"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.commissionVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.available"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.available"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.active"/></td>
  </tr>
</table>

<logic:equal name="ClientAgencyJobProfileGradeViewFormAdmin" property="clientAgencyJobProfileGrade.active" value="true">
<mmj-admin:hasAccess forward="clientAgencyJobProfileGradeEdit" >
  <html:link forward="clientAgencyJobProfileGradeEdit" paramId="clientAgencyJobProfileGrade.clientAgencyJobProfileGradeId" paramName="ClientAgencyJobProfileGradeViewFormAdmin" paramProperty="clientAgencyJobProfileGrade.clientAgencyJobProfileGradeId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>
