<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.compliancyTestDelete"/>
<br/>
<br/>
<html:errors/>
<html:form action="compliancyTestDeleteProcess.do" focus="compliancyTest.property">
<html:hidden property="compliancyTest.compliancyTestId"/>
<html:hidden property="compliancyTest.noOfChanges"/>
<table>
  <tr>
    <td align="left"><bean:message key="label.property"/></td>
    <td align="left"><bean:write name="CompliancyTestFormAdmin" property="compliancyTest.property"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.value"/></td>
    <td align="left"><bean:write name="CompliancyTestFormAdmin" property="compliancyTest.value"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.requiredDocumentText"/></td>
    <td align="left"><bean:write name="CompliancyTestFormAdmin" property="compliancyTest.requiredDocumentText"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="CompliancyTestFormAdmin" property="compliancyTest.displayOrder"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
