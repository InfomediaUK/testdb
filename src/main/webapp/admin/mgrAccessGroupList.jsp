<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.mgrAccessGroupList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="mgrAccessGroupNew">
<html:link forward="mgrAccessGroupNew"><bean:message key="link.new"/></html:link>
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
  <logic:iterate id="mgrAccessGroup" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="mgrAccessGroup"/>
	<logic:notEqual name="mgrAccessGroup" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="mgrAccessGroupView">
      <html:link forward="mgrAccessGroupView" paramId="mgrAccessGroup.mgrAccessGroupId" paramName="mgrAccessGroup" paramProperty="mgrAccessGroupId"><bean:write name="mgrAccessGroup" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="mgrAccessGroupView">
      <bean:write name="mgrAccessGroup" property="name"/>
    </mmj-admin:hasNoAccess>   
    </td>
  </tr>
</logic:iterate>
</table>
</logic:present>
