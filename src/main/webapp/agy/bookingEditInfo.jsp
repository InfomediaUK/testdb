<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/bookingEditInfoProcess.do" focus="bookingGrade.duration" onsubmit="return singleSubmit();">
<html:hidden property="bookingGrade.bookingId" />
<html:hidden property="bookingGrade.bookingGradeId" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.bookingEditInfo"/>
		</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.duration" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.duration" size="30" maxlength="100" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.reference" />
    </th>
    <td align="left">
      <html:text property="bookingGrade.bookingReference" maxlength="20" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label">
      <bean:message key="label.contact" />
    </th>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactName" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.contactName" size="30" maxlength="50" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactJobTitle" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.contactJobTitle" size="30" maxlength="50" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactEmailAddress" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.contactEmailAddress" size="30" maxlength="320" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactTelephoneNumber" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.contactTelephoneNumber" size="30" maxlength="20" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label">
      <bean:message key="label.accountContact" />
    </th>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.accountContactName" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.accountContactName" size="30" maxlength="50" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.accountContactAddress" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.accountContactAddress.address1" size="30" maxlength="200" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      &nbsp;
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.accountContactAddress.address2" size="30" maxlength="200" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      &nbsp;
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.accountContactAddress.address3" size="30" maxlength="200" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      &nbsp;
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.accountContactAddress.address4" size="30" maxlength="200" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.accountContactPostalCode" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.accountContactAddress.postalCode" maxlength="20" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.accountContactCountry" />
    </th>
    <td align="left" width="100%">
      <mmj:countryList var="countryList" />
      <html:select property="bookingGrade.accountContactAddress.countryId" tabindex="1">
        <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
        <html:options collection="countryList" labelProperty="name" property="countryId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.accountContactEmailAddress" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingGrade.accountContactEmailAddress" size="30" tabindex="1"/>
    </td>
  </tr>
</html:form>
</table>