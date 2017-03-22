<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.compliancyTestList"/>

<br/>
<br/>
<%-- --%>
<mmj-admin:hasAccess forward="compliancyTestNew" >
<html:link forward="compliancyTestNew"><bean:message key="link.new"/></html:link>
<br/>
<br/>
</mmj-admin:hasAccess>
<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.property" /></th>
    <th align="left"><bean:message key="label.value" /></th>
    <th align="left"><bean:message key="label.requiredDocumentText" /></th>
    <th align="left"><bean:message key="label.active" /></th>
    <th align="left"><bean:message key="label.order" /></th>
  </tr>
  </thead>
  <logic:iterate id="compliancyTest" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="compliancyTest"/>
	<logic:notEqual name="compliancyTest" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="compliancyTestView" >
      <html:link forward="compliancyTestView" paramId="compliancyTest.compliancyTestId" paramName="compliancyTest" paramProperty="compliancyTestId"><bean:write name="compliancyTest" property="property"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="compliancyTestView" >
      <bean:write name="compliancyTest" property="property"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="compliancyTest" property="value"/>
    </td>
    <td align="left">
      <bean:write name="compliancyTest" property="requiredDocumentText"/>
    </td>
    <td align="left">
      <bean:write name="compliancyTest" property="active"/>
    </td>    
    <td align="left">
      <bean:write name="compliancyTest" property="displayOrder"/>
    </td>    
  </tr>
  </logic:iterate>
</table>
</logic:present>

