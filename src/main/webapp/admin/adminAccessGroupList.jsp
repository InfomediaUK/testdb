<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.adminAccessGroupList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="adminAccessGroupNew">
<html:link forward="adminAccessGroupNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>

<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
  </tr>
  </thead>
  <logic:iterate id="adminAccessGroup" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="adminAccessGroup"/>
	<logic:notEqual name="adminAccessGroup" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="adminAccessGroupView">
      <html:link forward="adminAccessGroupView" paramId="adminAccessGroup.adminAccessGroupId" paramName="adminAccessGroup" paramProperty="adminAccessGroupId"><bean:write name="adminAccessGroup" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="adminAccessGroupView">
      <bean:write name="adminAccessGroup" property="name"/>
    </mmj-admin:hasNoAccess>   
    </td>
  </tr>
</logic:iterate>
</table>
</logic:present>
