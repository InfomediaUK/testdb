<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.trainingCompanyCourseView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.trainingCompany"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="trainingCompanyView">
      <html:link forward="trainingCompanyView" paramId="trainingCompany.trainingCompanyId" paramName="TrainingCompanyCourseFormAdmin" paramProperty="trainingCompanyCourseUser.trainingCompanyId"><bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.trainingCompanyName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="trainingCompanyView">
      <bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.trainingCompanyName"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.trainingCourse"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="trainingCourseView">
      <html:link forward="trainingCourseView" paramId="trainingCourse.trainingCourseId" paramName="TrainingCompanyCourseFormAdmin" paramProperty="trainingCompanyCourseUser.trainingCourseId"><bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.trainingCourseName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="trainingCourseView">
      <bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.trainingCourseName"/>
    </mmj-admin:hasNoAccess>
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
    <td align="left"><bean:message key="label.order"/></td>
    <td align="left">
	  <bean:write name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.displayOrder"/>
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
</table>

<mmj-admin:hasAccess forward="trainingCompanyCourseEdit" >
  <html:link forward="trainingCompanyCourseEdit" paramId="trainingCompanyCourseUser.trainingCompanyCourseId" paramName="TrainingCompanyCourseFormAdmin" paramProperty="trainingCompanyCourseUser.trainingCompanyCourseId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<logic:equal name="TrainingCompanyCourseFormAdmin" property="trainingCompanyCourseUser.active" value="true">
<mmj-admin:hasAccess forward="trainingCompanyCourseDelete" >
  <html:link forward="trainingCompanyCourseDelete" paramId="trainingCompanyCourseUser.trainingCompanyCourseId" paramName="TrainingCompanyCourseFormAdmin" paramProperty="trainingCompanyCourseUser.trainingCompanyCourseId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
