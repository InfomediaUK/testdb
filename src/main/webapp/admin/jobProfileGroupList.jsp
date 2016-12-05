<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<h2>SHOULD NEVER GET HERE !!!</h2>

<bean:message key="title.jobProfileGroupList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="jobProfileGroupNew">
<html:link forward="jobProfileGroupNew"><bean:message key="link.new"/></html:link>
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
  <logic:iterate id="jobProfileGroup" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="jobProfileGroup"/>
	<logic:notEqual name="jobProfileGroup" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="jobProfileGroupView">
      <html:link forward="jobProfileGroupView" paramId="jobProfileGroup.jobProfileGroupId" paramName="jobProfileGroup" paramProperty="jobProfileGroupId"><bean:write name="jobProfileGroup" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobProfileGroupView">
      <bean:write name="jobProfileGroup" property="name"/>
    </mmj-admin:hasNoAccess>   
    </td>
  </tr>
</logic:iterate>
</table>
</logic:present>
