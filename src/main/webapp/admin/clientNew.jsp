<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.clientNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="clientNewProcess.do" focus="client.name">

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="client.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><html:text property="client.address.address1"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.address.address2"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.address.address3"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="client.address.address4"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.postalCode"/></td>
    <td align="left"><html:text property="client.address.postalCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="client.address.countryId" >
        <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.secretWordAtLogin"/></td>
    <td align="left"><html:checkbox property="client.secretWordAtLogin"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.autoActivateDefault"/></td>
    <td align="left"><html:checkbox property="client.autoActivateDefault"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftCommission"/></td>
    <td align="left"><html:checkbox property="client.upliftCommission"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
