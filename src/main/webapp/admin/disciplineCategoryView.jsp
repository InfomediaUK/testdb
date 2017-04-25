<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.disciplineCategoryView"/>
<br/>
<br/>
<html:errors/>
<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.regulator"/></td>
    <td align="left"><bean:write name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.regulatorCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.undertakesEPP"/></td>
    <td align="left"><bean:write name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.undertakesEPP"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.displayOrder"/></td>
  </tr>
</table>

<mmj-admin:hasAccess forward="disciplineCategoryEdit" >
  <html:link forward="disciplineCategoryEdit" paramId="disciplineCategory.disciplineCategoryId" paramName="DisciplineCategoryViewFormAdmin" paramProperty="disciplineCategory.disciplineCategoryId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="disciplineCategoryDelete" >
  <html:link forward="disciplineCategoryDelete" paramId="disciplineCategory.disciplineCategoryId" paramName="DisciplineCategoryViewFormAdmin" paramProperty="disciplineCategory.disciplineCategoryId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>

<br/>
<br/>
<bean:message key="title.trainingCourseList"/>
<br/>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
<logic:notEmpty name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.disciplineCategoryTrainingUsers">
	  <html:form action="disciplineCategoryTrainingDelete.do">
		<html:hidden name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.disciplineCategoryId" />
  <logic:iterate id="disciplineCategoryTraining" name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.disciplineCategoryTrainingUsers">
		    <logic:equal name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.active" value="true">
		    <mmj-admin:hasAccess forward="disciplineCategoryTrainingDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="disciplineCategoryTraining" property="disciplineCategoryTrainingId" />,<bean:write name="disciplineCategoryTraining" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>

		    <mmj-admin:hasAccess forward="disciplineCategoryTrainingView">
		      <html:link forward="disciplineCategoryTrainingView" paramId="disciplineCategoryTraining.disciplineCategoryTrainingId" paramName="disciplineCategoryTraining" paramProperty="disciplineCategoryTrainingId"><bean:write name="disciplineCategoryTraining" property="trainingNameWithMandatory"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="disciplineCategoryTrainingView">
 			  <bean:write name="disciplineCategoryTraining" property="trainingNameWithMandatory"/>
		    </mmj-admin:hasNoAccess>   
        &nbsp;<br/>  
  </logic:iterate>

  <logic:equal name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.active" value="true">
	<mmj-admin:hasAccess forward="disciplineCategoryTrainingDelete">
		<br/>
		<html:submit><bean:message key="button.exclude"/></html:submit>
	</mmj-admin:hasAccess>
  </logic:equal>
	  </html:form>
</logic:notEmpty>
    </td>
    <td valign="top">
      &nbsp;
    </td>
    <td valign="top">
	  <bean:message key="title.excluded"/>
<logic:notEmpty name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.trainingCourses">
	  <html:form action="disciplineCategoryTrainingNew.do">
		<html:hidden name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.disciplineCategoryId" />
		  <logic:iterate id="training" name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.trainingCourses">
		    <logic:equal name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.active" value="true">
		    <mmj-admin:hasAccess forward="disciplineCategoryTrainingNew">
		      <html:multibox property="selectedItems" >
		        <bean:write name="training" property="trainingCourseId" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="trainingView">
		      <html:link forward="trainingView" paramId="training.trainingCourseId" paramName="training" paramProperty="trainingCourseId"><bean:write name="training" property="name"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="trainingView">
 			    <bean:write name="training" property="name"/>
		    </mmj-admin:hasNoAccess>   
		    &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="DisciplineCategoryViewFormAdmin" property="disciplineCategory.active" value="true">
			<mmj-admin:hasAccess forward="disciplineCategoryTrainingNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
	  </html:form>			
</logic:notEmpty>
    </td>
  </tr>
</table>


