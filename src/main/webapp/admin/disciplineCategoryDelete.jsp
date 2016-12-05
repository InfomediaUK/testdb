<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.disciplineCategoryDelete"/>
<br/>
<br/>
<html:errors/>
<html:form action="disciplineCategoryDeleteProcess.do" focus="disciplineCategory.name">
<html:hidden property="disciplineCategory.disciplineCategoryId"/>
<html:hidden property="disciplineCategory.noOfChanges"/>
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
    <td align="left"><bean:message key="label.registersWithHPC"/></td>
    <td align="left"><bean:write name="DisciplineCategoryFormAdmin" property="disciplineCategory.registersWithHPC"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="DisciplineCategoryFormAdmin" property="disciplineCategory.displayOrder"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
