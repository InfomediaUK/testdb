<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.clientAgencyJobProfileGradeEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="clientAgencyJobProfileGradeEditProcess.do" focus="clientAgencyJobProfileGrade.rate">

<html:hidden property="clientAgencyJobProfileGrade.clientAgencyJobProfileId"/>

<html:hidden property="clientAgencyJobProfileGrade.clientAgencyJobProfileGradeId"/>
<html:hidden property="clientAgencyJobProfileGrade.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.rate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.payRate"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.payRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.wtdPercentage"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.wtdPercentage"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.niPercentage"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.niPercentage"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.chargeRateVatRate"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.chargeRateVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.payRateVatRate"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.payRateVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.wtdVatRate"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.wtdVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.niVatRate"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.niVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.commissionVatRate"/></td>
    <td align="left"><html:text property="clientAgencyJobProfileGrade.commissionVatRate"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.available"/></td>
    <td align="left"><html:checkbox property="clientAgencyJobProfileGrade.available"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
