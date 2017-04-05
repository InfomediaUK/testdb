<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.compliancyTestEdit"/>
<br/>
<br/>
<html:errors/>
<html:form action="compliancyTestEditProcess.do" focus="compliancyTest.property">
	<html:hidden name="CompliancyTestFormAdmin" property="compliancyTest.compliancyTestId"/>
	<html:hidden name="CompliancyTestFormAdmin" property="compliancyTest.noOfChanges"/>
	<table>
	  <tr>
	    <td align="left"><bean:message key="label.property"/></td>
	    <td align="left"><html:text name="CompliancyTestFormAdmin" property="compliancyTest.property" size="60" maxSize="50" /></td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.value"/></td>
	    <td align="left"><html:text name="CompliancyTestFormAdmin" property="compliancyTest.value" size="60" maxSize="30" /></td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.requiredDocumentText"/></td>
	    <td align="left"><html:text name="CompliancyTestFormAdmin" property="compliancyTest.requiredDocumentText" size="60" maxSize="200"/></td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.displayOrder"/></td>
	     <td align="left"><html:text name="CompliancyTestFormAdmin" property="compliancyTest.displayOrder" size="10" /></td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
	  </tr>
	</table>
</html:form>

