<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.trainingCompanyCourseNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="trainingCompanyCourseNewProcess.do" focus="trainingCompanyCourseUser.name">
<html:hidden property="trainingCompanyCourseUser.trainingCompanyId"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.trainingCompany"/></td>
    <td align="left"><bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.trainingCompanyName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.trainingCourse"/></td>
    <td align="left">
      <mmj:trainingCourseList var="trainingCourseList" />
      <html:select property="trainingCompanyCourseUser.trainingCourseId">
	      <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
        <html:options collection="trainingCourseList" labelProperty="nameWithCode" property="trainingCourseId" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="trainingCompanyCourseUser.name" size="60" maxlength="80" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.online"/></td>
    <td align="left">
      <html:radio property="trainingCompanyCourseUser.online" value="true" styleId="online" />
      <label for="online">
        <bean:message key="label.yes" />
      </label>
      <html:radio property="trainingCompanyCourseUser.online" value="false" styleId="online" />
      <label for="online">
        <bean:message key="label.no" />
      </label>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
