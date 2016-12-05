<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.siteEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="siteEditProcess.do" focus="site.name">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="site.clientId" />

<html:hidden property="site.siteId" />
<html:hidden property="site.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="SiteFormAdmin" property="client.name"/> (<bean:write name="SiteFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="site.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><html:text property="site.address.address1"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="site.address.address2"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="site.address.address3"/></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left"><html:text property="site.address.address4"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.postalCode"/></td>
    <td align="left"><html:text property="site.address.postalCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="site.address.countryId" >
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="site.code"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
