<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.agyAccessGroupDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="agyAccessGroupDeleteProcess.do">

<html:hidden property="agency.name" /> <%-- to preserve value --%>
<html:hidden property="agency.countryName" /> <%-- to preserve value --%>
<html:hidden property="agyAccessGroup.agencyId" />

<html:hidden property="agyAccessGroup.agyAccessGroupId"/>
<html:hidden property="agyAccessGroup.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.agency"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agency.name"/> (<bean:write name="AgyAccessGroupViewFormAdmin" property="agency.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.name" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
