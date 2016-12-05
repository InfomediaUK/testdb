<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.countryDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="countryDeleteProcess.do" focus="country.name">

<html:hidden property="country.countryId"/>
<html:hidden property="country.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="CountryFormAdmin" property="country.name" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.isoCode"/></td>
    <td align="left"><bean:write name="CountryFormAdmin" property="country.isoCode" /></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
