<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.clientAgencyEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="clientAgencyEditProcess.do" focus="clientAgency.feePerShift" >

<html:hidden property="clientAgency.clientAgencyId"/>
<html:hidden property="clientAgency.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.feePerShift"/></td>
    <td align="left"><html:text property="clientAgency.feePerShift"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.feePerHour"/></td>
    <td align="left"><html:text property="clientAgency.feePerHour"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.feePercentage"/></td>
    <td align="left"><html:text property="clientAgency.feePercentage"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.paymentTerms"/></td>
    <td align="left"><html:text property="clientAgency.paymentTerms"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
