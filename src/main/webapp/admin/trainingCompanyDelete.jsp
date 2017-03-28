<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.trainingCompanyDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="trainingCompanyDeleteProcess.do" focus="trainingCompany.name">

<html:hidden property="trainingCompany.trainingCompanyId"/>
<html:hidden property="trainingCompany.noOfChanges"/>

<table>
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
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
