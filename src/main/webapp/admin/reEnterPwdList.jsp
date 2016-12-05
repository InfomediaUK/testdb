<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.reEnterPwdList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="reEnterPwdNew" >
<html:link forward="reEnterPwdNew"><bean:message key="link.new"/></html:link>
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
  <logic:iterate id="reEnterPwd" name="ListFormAdmin" property="list">
	<bean:define id="trClass" value="reEnterPwd"/>
	<logic:notEqual name="reEnterPwd" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="reEnterPwdView" >
      <html:link forward="reEnterPwdView" paramId="reEnterPwd.reEnterPwdId" paramName="reEnterPwd" paramProperty="reEnterPwdId"><bean:write name="reEnterPwd" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="reEnterPwdView" >
      <bean:write name="reEnterPwd" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  </logic:iterate>
</table>
</logic:present>
