<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.trainingCompanyView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.trainingCompany"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.telephoneNumber"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.telephoneNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.faxNumber"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.faxNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.websiteAddress"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.websiteAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.vatNumber"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.vatNumber"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.reference"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.reference"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.trainingCompanyFreeText"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.freeText"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.displayOrder"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="TrainingCompanyViewFormAdmin" property="trainingCompany.active"/></td>
  </tr>
</table>

<mmj-admin:hasAccess forward="trainingCompanyEdit">
  <html:link forward="trainingCompanyEdit" paramId="trainingCompany.trainingCompanyId" paramName="TrainingCompanyViewFormAdmin" paramProperty="trainingCompany.trainingCompanyId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<logic:equal name="TrainingCompanyViewFormAdmin" property="trainingCompany.active" value="true">
<mmj-admin:hasAccess forward="trainingCompanyDelete">
  <html:link forward="trainingCompanyDelete" paramId="trainingCompany.trainingCompanyId" paramName="TrainingCompanyViewFormAdmin" paramProperty="trainingCompany.trainingCompanyId"><bean:message key="link.delete"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>
<br/>
<br/>
<bean:message key="title.trainingCompanyCourseList"/>
<br/>
<br/>
<logic:equal name="TrainingCompanyViewFormAdmin" property="trainingCompany.active" value="true">
  <mmj-admin:hasAccess forward="trainingCompanyCourseNew" >
    <html:link forward="trainingCompanyCourseNew" paramId="trainingCompanyCourseUser.trainingCompanyId" paramName="TrainingCompanyViewFormAdmin" paramProperty="trainingCompany.trainingCompanyId"><bean:message key="link.new"/></html:link>
    <br/>
    <br/>
  </mmj-admin:hasAccess>
</logic:equal>
<logic:present name="TrainingCompanyViewFormAdmin" property="listTrainingCompanyCourseUser">
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.trainingCompanyCourse" /></th>
    <th align="left"><bean:message key="label.trainingCourse" /></th>
    <th align="left"><bean:message key="label.online" /></th>
    <th align="left"><bean:message key="label.active" /></th>
    <th align="left"><bean:message key="label.order" /></th>
  </tr>
  </thead>
  <logic:iterate id="trainingCompanyCourse" name="TrainingCompanyViewFormAdmin" property="listTrainingCompanyCourseUser">
	<bean:define id="trClass" value="trainingCompanyCourse"/>
	<logic:notEqual name="trainingCompanyCourse" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="trainingCompanyCourseView" >
      <html:link forward="trainingCompanyCourseView" paramId="trainingCompanyCourseUser.trainingCompanyCourseId" paramName="trainingCompanyCourse" paramProperty="trainingCompanyCourseId"><bean:write name="trainingCompanyCourse" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="trainingCompanyCourseView" >
      <bean:write name="trainingCompanyCourse" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="trainingCompanyCourse" property="trainingCourseName"/>
    </td>    
    <td align="left">
<logic:equal name="trainingCompanyCourse" property="online" value="true">
      <bean:message key="label.yes" />
</logic:equal>
<logic:notEqual name="trainingCompanyCourse" property="online" value="true">
      <bean:message key="label.no" />
</logic:notEqual>
    </td>    
    <td align="left">
      <bean:write name="trainingCompanyCourse" property="active"/>
    </td>    
    <td align="left">
      <bean:write name="trainingCompanyCourse" property="displayOrder"/>
    </td>    
  </tr>
  </logic:iterate>
</table>
</logic:present>


