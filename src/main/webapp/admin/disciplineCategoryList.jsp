<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.disciplineCategoryList"/>

<br/>
<br/>
<%-- --%>
<mmj-admin:hasAccess forward="disciplineCategoryNew" >
<html:link forward="disciplineCategoryNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>
<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.regulator" /></th>
    <th align="left"><bean:message key="label.active" /></th>
    <th align="left"><bean:message key="label.order" /></th>
  </tr>
  </thead>
  <logic:iterate id="disciplineCategory" name="ListFormAdmin" property="list" type="com.helmet.bean.DisciplineCategoryUser">
	<bean:define id="trClass" value="disciplineCategory"/>
	<logic:notEqual name="disciplineCategory" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="disciplineCategoryView" >
      <html:link forward="disciplineCategoryView" paramId="disciplineCategory.disciplineCategoryId" paramName="disciplineCategory" paramProperty="disciplineCategoryId"><bean:write name="disciplineCategory" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="disciplineCategoryView" >
      <bean:write name="disciplineCategory" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="disciplineCategory" property="code"/>
    </td>
    <td align="left">
      <bean:write name="disciplineCategory" property="regulatorCode"/>
    </td>
    <td align="left">
      <bean:write name="disciplineCategory" property="active"/>
    </td>    
    <td align="left">
      <bean:write name="disciplineCategory" property="displayOrder"/>
    </td>    
  </tr>
  </logic:iterate>
</table>
</logic:present>

