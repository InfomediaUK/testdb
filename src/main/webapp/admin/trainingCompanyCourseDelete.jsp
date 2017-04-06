<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.trainingCompanyCourseDelete"/>

<br/>
<br/>

<html:errors/>
<html:form action="trainingCompanyCourseDeleteProcess.do">
<html:hidden property="trainingCompanyCourseUser.trainingCompanyCourseId"/>
<html:hidden property="trainingCompanyCourseUser.noOfChanges"/>
<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.trainingCompany"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
      <bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.trainingCompanyName"/>
    </td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.trainingCourse"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
      <bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.trainingCourseName"/>
    </td>
  </tr>  
  <tr>
    <th align="left" colspan="2"><bean:message key="label.trainingCompanyCourse"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
	  <bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.name"/>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.online"/></td>
    <td align="left">
<logic:equal name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.online" value="true">
      <bean:message key="label.yes" />
</logic:equal>
<logic:notEqual name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.online" value="true">
      <bean:message key="label.no" />
</logic:notEqual>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>
</html:form>

