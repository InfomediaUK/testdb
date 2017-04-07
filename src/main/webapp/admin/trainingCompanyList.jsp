<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.trainingCompanyList"/>

<br/>
<br/>

<mmj-admin:hasAccess forward="trainingCompanyNew">
<html:link forward="trainingCompanyNew"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="trainingCompanyOrder">
&nbsp;
<html:link forward="trainingCompanyOrder"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>

<logic:present name="ListFormAdmin" property="list">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.address" /></th>
    <th align="left"><bean:message key="label.country" /></th>
    <th align="left"><bean:message key="label.active" /></th>
    <th align="left"><bean:message key="label.order" /></th>
  </tr>
  </thead>
  <logic:iterate id="trainingCompany" name="ListFormAdmin" property="list" type="com.helmet.bean.TrainingCompanyUserEntity">
	<bean:define id="trClass" value="trainingCompany"/>
	<logic:notEqual name="trainingCompany" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="trainingCompanyView">
      <html:link forward="trainingCompanyView" paramId="trainingCompany.trainingCompanyId" paramName="trainingCompany" paramProperty="trainingCompanyId"><bean:write name="trainingCompany" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="trainingCompanyView">
      <bean:write name="trainingCompany" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="trainingCompany" property="code"/>
    </td>
    <td align="left">
      <bean:write name="trainingCompany" property="address.fullAddress"/>
    </td>
    <td align="left">
      <bean:write name="trainingCompany" property="countryName"/>
    </td>
    <td align="left">
      <bean:write name="trainingCompany" property="active"/>
    </td>    
    <td align="left">
      <bean:write name="trainingCompany" property="displayOrder"/>
    </td>    
  </tr>
  <logic:equal name="trainingCompany" property="hasActiveTrainingCompanyCourses" value="true">
  <logic:iterate id="trainingCompanyCourse" name="trainingCompany" property="trainingCompanyCourseUsers" type="com.helmet.bean.TrainingCompanyCourseUser">
  <tr>
    <td colspan="6">
      <table class="simple">
        <tr>
          <td>
            &nbsp;
          </td>
          <td>
		    <mmj-admin:hasAccess forward="trainingCompanyCourseView">
		      <html:link forward="trainingCompanyCourseView" paramId="trainingCompanyCourseUser.trainingCompanyCourseId" paramName="trainingCompanyCourse" paramProperty="trainingCompanyCourseId"><bean:write name="trainingCompanyCourse" property="nameWithOnline"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="trainingCompanyCourseView">
 			  <bean:write name="trainingCompanyCourse" property="nameWithOnline"/>
		    </mmj-admin:hasNoAccess>   
          </td>
        </tr>
      </table>
    </td>
  </tr>
  </logic:iterate>
  </logic:equal>
  </logic:iterate>
</table>
</logic:present>
