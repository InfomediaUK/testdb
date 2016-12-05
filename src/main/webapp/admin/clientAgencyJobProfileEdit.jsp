<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.clientAgencyJobProfileEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="clientAgencyJobProfileEditProcess.do" focus="clientAgencyJobProfile.percentage">

<html:hidden property="clientAgencyJobProfile.jobProfileId"/>

<html:hidden property="clientAgencyJobProfile.clientAgencyJobProfileId"/>
<html:hidden property="clientAgencyJobProfile.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.percentage"/></td>
    <td align="left"><html:text property="clientAgencyJobProfile.percentage"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.sendEmailAddress"/></td>
    <td align="left"><html:text property="clientAgencyJobProfile.sendEmailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.masterVendorName"/></td>
    <td align="left"><html:text property="clientAgencyJobProfile.masterVendorName"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
