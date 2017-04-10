<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.disciplineCategoryTrainingView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.disciplineCategory"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="disciplineCategoryView">
      <html:link forward="disciplineCategoryView" paramId="disciplineCategory.disciplineCategoryId" paramName="DisciplineCategoryTrainingViewFormAdmin" paramProperty="disciplineCategory.disciplineCategoryId"><bean:write name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategory.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="disciplineCategoryView">
      <bean:write name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategory.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategory.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategory.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.trainingCourse"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
	    <mmj-admin:hasAccess forward="trainingView">
	      <html:link forward="trainingView" paramId="training.trainingId" paramName="DisciplineCategoryTrainingViewFormAdmin" paramProperty="disciplineCategoryTraining.trainingId"><bean:write name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategoryTraining.trainingName"/></html:link>
	    </mmj-admin:hasAccess>
	    <mmj-admin:hasNoAccess forward="trainingView">
			    <bean:write name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategoryTraining.trainingName"/>
	    </mmj-admin:hasNoAccess>   
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.mandatory"/></td>
    <td align="left">
<logic:equal name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategoryTraining.mandatory" value="true">
      <bean:message key="label.yes" />
</logic:equal>
<logic:notEqual name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategoryTraining.mandatory" value="true">
      <bean:message key="label.no" />
</logic:notEqual>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategoryTraining.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategoryTraining.active"/></td>
  </tr>
</table>

<logic:equal name="DisciplineCategoryTrainingViewFormAdmin" property="disciplineCategoryTraining.active" value="true">
<mmj-admin:hasAccess forward="disciplineCategoryTrainingEdit" >
  <html:link forward="disciplineCategoryTrainingEdit" paramId="disciplineCategoryTraining.disciplineCategoryTrainingId" paramName="DisciplineCategoryTrainingViewFormAdmin" paramProperty="disciplineCategoryTraining.disciplineCategoryTrainingId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>
