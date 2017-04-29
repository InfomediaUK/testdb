<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.helmet.bean.Applicant" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<bean:define id="applicantTrainingCourseUser" name="ApplicantTrainingCourseViewFormAgy" property="applicantTrainingCourseUser" type="com.helmet.bean.ApplicantTrainingCourse"/>

<html:form action="/applicantTrainingCourseDeleteProcess.do" onsubmit="return singleSubmit();">
  <html:hidden name="ApplicantTrainingCourseViewFormAgy" property="applicantTrainingCourseUser.applicantTrainingCourseId" />
  <html:hidden name="ApplicantTrainingCourseViewFormAgy" property="applicantTrainingCourseUser.applicantId" />
  <html:hidden name="ApplicantTrainingCourseViewFormAgy" property="applicantTrainingCourseUser.noOfChanges" />
  <html:hidden name="ApplicantTrainingCourseViewFormAgy" property="applicantTrainingCourseUser.documentationFileName" />
<table cellpadding="0" cellspacing="0" width="100%" height="30" border="0">
  <tr>
	  <td align="left" valign="middle" class="title">
      <bean:message key="title.applicantTrainingCourseDelete"/>
    </td>
    <td align="right" valign="middle" width="80">
      <html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit>
    </td>
    <td align="right" valign="middle" width="80">
      <html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel>
    </td>
  </tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td width="75%">
  		<table class="simple" width="100%">
		    <tr>
		      <th align="left" class="label"><bean:message key="label.firstName"/></th>
		      <td align="left"><bean:write name="applicantTrainingCourseUser" property="applicantFirstName"/></td>
		    </tr>
		    <tr>
		      <th align="left" class="label"><bean:message key="label.lastName"/></th>
		      <td align="left"><bean:write name="applicantTrainingCourseUser" property="applicantLastName"/></td>
		   	</tr>
		    <tr>
		      <th align="left" class="label"><bean:message key="label.nhsStaffName"/></th>
		      <td align="left"><bean:write name="applicantTrainingCourseUser" property="applicantNhsStaffName"/></td>
		    </tr>
		    <tr>
		      <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
		      <td align="left">
             <bean:write name="applicantTrainingCourseUser" property="applicantEmailAddress"/>
		      </td>
		    </tr>
		    <tr>
		      <th align="left" class="label">
		        <bean:message key="label.disciplineCategory"/>
		      </th>
		      <td align="left">
			    <bean:write name="applicantTrainingCourseUser" property="disciplineCategoryName"/>
		      </td>
		    </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.trainingCourse"/>
			    </th>
			    <td>
            <bean:write name="applicantTrainingCourseUser" property="trainingCompanyCourseName" />
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.startDate"/>
			    </th>
			    <td>
            <bean:write name="applicantTrainingCourseUser" property="startDate" formatKey="format.mediumDateFormat" />
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.endDate"/>
			    </th>
			    <td>
            <bean:write name="applicantTrainingCourseUser" property="endDate" formatKey="format.mediumDateFormat" />
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.documentation" />
			    </th>
			    <td align="left">
			      <bean:write name="applicantTrainingCourseUser" property="documentationFileName"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label" valign="top"><bean:message key="label.comment"/></th>
			    <td align="left">
			      <html:textarea name="applicantTrainingCourseUser" property="comment" cols="60" rows="4" disabled="true"/>
			    </td>
			  </tr>
			</table>
    </td>
    <td width="25%" valign="top" align="center">
      &nbsp;
    </td>
  </tr>
</table>
</html:form>


