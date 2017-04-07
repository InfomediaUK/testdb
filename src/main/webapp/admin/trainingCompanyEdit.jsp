<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.trainingCompanyEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="trainingCompanyEditProcess.do" focus="trainingCompany.name" enctype="multipart/form-data">

<html:hidden property="trainingCompany.trainingCompanyId"/>
<html:hidden property="trainingCompany.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="trainingCompany.name" size="60" maxlength="80" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="trainingCompany.code" size="10" maxlength="10" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><html:text property="trainingCompany.address.address1"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="trainingCompany.address.address2"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="trainingCompany.address.address3"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="trainingCompany.address.address4"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.postalCode"/></td>
    <td align="left"><html:text property="trainingCompany.address.postalCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="trainingCompany.address.countryId" >
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.telephoneNumber"/></td>
    <td align="left"><html:text property="trainingCompany.telephoneNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.faxNumber"/></td>
    <td align="left"><html:text property="trainingCompany.faxNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><html:text property="trainingCompany.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.websiteAddress"/></td>
    <td align="left"><html:text property="trainingCompany.websiteAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.vatNumber"/></td>
    <td align="left"><html:text property="trainingCompany.vatNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.reference"/></td>
    <td align="left"><html:text property="trainingCompany.reference"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.trainingCompanyFreeText"/></td>
    <td align="left"><html:textarea property="trainingCompany.freeText" cols="75" rows="10"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><html:text property="trainingCompany.displayOrder" size="10" /></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
