<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.trainingCompanyView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.trainingCompany"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.telephoneNumber"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.telephoneNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.faxNumber"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.faxNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.websiteAddress"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.websiteAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.active"/></td>
  </tr>
</table>

<logic:equal name="TrainingCompanyViewFormAdmin" property="trainingCompany.active" value="true">
<mmj-admin:hasAccess forward="trainingCompanyEdit">
  <html:link forward="trainingCompanyEdit" paramId="trainingCompany.trainingCompanyId" paramName="TrainingCompanyViewFormAdmin" paramProperty="trainingCompany.trainingCompanyId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="trainingCompanyDelete">
  <html:link forward="trainingCompanyDelete" paramId="trainingCompany.trainingCompanyId" paramName="TrainingCompanyViewFormAdmin" paramProperty="trainingCompany.trainingCompanyId"><bean:message key="link.delete"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>

