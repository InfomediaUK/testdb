<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.agencyNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="agencyNewProcess.do" focus="agency.name">

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="agency.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><html:text property="agency.address.address1"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="agency.address.address2"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="agency.address.address3"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="agency.address.address4"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.postalCode"/></td>
    <td align="left"><html:text property="agency.address.postalCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="agency.address.countryId" >
        <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="agency.code"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
