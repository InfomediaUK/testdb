<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.trainingCourseView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="TrainingCourseFormAdmin" property="trainingCourse.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="TrainingCourseFormAdmin" property="trainingCourse.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="TrainingCourseFormAdmin" property="trainingCourse.displayOrder"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="TrainingCourseFormAdmin" property="trainingCourse.active"/></td>
  </tr>
</table>

<mmj-admin:hasAccess forward="trainingCourseEdit" >
  <html:link forward="trainingCourseEdit" paramId="trainingCourse.trainingCourseId" paramName="TrainingCourseFormAdmin" paramProperty="trainingCourse.trainingCourseId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<logic:equal name="TrainingCourseFormAdmin" property="trainingCourse.active" value="true">
<mmj-admin:hasAccess forward="trainingCourseDelete" >
  <html:link forward="trainingCourseDelete" paramId="trainingCourse.trainingCourseId" paramName="TrainingCourseFormAdmin" paramProperty="trainingCourse.trainingCourseId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
