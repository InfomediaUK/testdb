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

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="DisciplineCategoryFormAdmin" property="disciplineCategory.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="DisciplineCategoryFormAdmin" property="disciplineCategory.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.regulator"/></td>
    <td align="left"><bean:write name="DisciplineCategoryFormAdmin" property="disciplineCategory.regulatorCode"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.undertakesEPP"/></td>
    <td align="left"><bean:write name="DisciplineCategoryFormAdmin" property="disciplineCategory.undertakesEPP"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="DisciplineCategoryFormAdmin" property="disciplineCategory.displayOrder"/></td>
  </tr>
</table>

<mmj-admin:hasAccess forward="disciplineCategoryEdit" >
  <html:link forward="disciplineCategoryEdit" paramId="disciplineCategory.disciplineCategoryId" paramName="DisciplineCategoryFormAdmin" paramProperty="disciplineCategory.disciplineCategoryId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="disciplineCategoryDelete" >
  <html:link forward="disciplineCategoryDelete" paramId="disciplineCategory.disciplineCategoryId" paramName="DisciplineCategoryFormAdmin" paramProperty="disciplineCategory.disciplineCategoryId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
