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
<html:form action="/siteEditProcess.do" focus="site.name" onsubmit="return singleSubmit();">
<html:hidden property="site.clientId"/>
<html:hidden property="site.siteId"/>
<html:hidden property="site.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.siteEdit"/>
		</td>
    <mmj-agy:hasAccess forward="siteEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><html:text property="site.name" maxlength="50" size="50"/></td>
  </tr>
  <tr>
    <th align="left" width="35%"><bean:message key="label.nhsLocation"/></th>
    <td align="left" width="65%"><html:text property="site.nhsLocation" maxlength="50" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.address"/></th>
    <td align="left"><html:text property="site.address.address1" maxlength="200" size="50"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="site.address.address2" maxlength="200" size="50"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="site.address.address3" maxlength="200" size="50"/></td>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
    <td align="left"><html:text property="site.address.address4" maxlength="200" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.postalCode"/></th>
    <td align="left"><html:text property="site.address.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.country"/></th>
    <td align="left">
      <mmj:countryList var="countryList" />
      <html:select property="site.address.countryId" >
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <%/* probably shouldn't be allowed to change this - here for now for the spare ones */%>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><html:text property="site.code"/></td>
  </tr>
</html:form>
</table>
  