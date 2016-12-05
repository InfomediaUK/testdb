<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.locationJobProfileEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="locationJobProfileEditProcess.do" focus="locationJobProfile.rate">

<html:hidden property="locationJobProfile.clientName" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.siteName" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.locationName" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.jobFamilyName" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.jobSubFamilyName" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.jobProfileName" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.clientCode" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.siteCode" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.locationCode" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.jobFamilyCode" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.jobSubFamilyCode" /> <%-- to preserve value --%>
<html:hidden property="locationJobProfile.jobProfileCode" /> <%-- to preserve value --%>

<html:hidden property="locationJobProfile.locationJobProfileId"/>
<html:hidden property="locationJobProfile.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.clientName"/> (<bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.clientCode"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.site"/></td>
    <td align="left"><bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.siteName"/> (<bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.siteCode"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.location"/></td>
    <td align="left"><bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.locationName"/> (<bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.locationCode"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobFamily"/></td>
    <td align="left"><bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.jobFamilyName"/> (<bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.jobFamilyCode"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobSubFamily"/></td>
    <td align="left"><bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.jobSubFamilyName"/> (<bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.jobSubFamilyCode"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobProfile"/></td>
    <td align="left"><bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.jobProfileName"/> (<bean:write name="LocationJobProfileEditFormAdmin" property="locationJobProfile.jobProfileCode"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><html:text property="locationJobProfile.rate"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
