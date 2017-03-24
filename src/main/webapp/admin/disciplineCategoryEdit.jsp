<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.disciplineCategoryEdit"/>
<br/>
<br/>
<html:errors/>
<html:form action="disciplineCategoryEditProcess.do" focus="disciplineCategory.name">
	<html:hidden name="DisciplineCategoryFormAdmin" property="disciplineCategory.disciplineCategoryId"/>
	<html:hidden name="DisciplineCategoryFormAdmin" property="disciplineCategory.noOfChanges"/>
	<table>
	  <tr>
	    <td align="left"><bean:message key="label.name"/></td>
	    <td align="left"><html:text name="DisciplineCategoryFormAdmin" property="disciplineCategory.name" size="60" /></td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.code"/></td>
	    <td align="left"><html:text name="DisciplineCategoryFormAdmin" property="disciplineCategory.code" size="60" /></td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.regulator"/></td>
	    <td align="left">
	      <mmj:regulatorList var="regulatorList" />
	      <html:select property="disciplineCategory.regulatorId" tabindex="14">
		      <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
	        <html:options collection="regulatorList" labelProperty="nameWithCode" property="regulatorId" />
	      </html:select>
	    </td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.undertakesEPP"/></td>
	    <td align="left"><html:checkbox name="DisciplineCategoryFormAdmin" property="disciplineCategory.undertakesEPP" /></td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.displayOrder"/></td>
	     <td align="left"><html:text name="DisciplineCategoryFormAdmin" property="disciplineCategory.displayOrder" size="10" /></td>
	   </tr>
	  <tr>
	    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
	  </tr>
	</table>
</html:form>

