<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:errors/>
<table cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td align="left" valign="top" width="75%">
			<table class="simple" width="100%">
			  <tr>
			    <th align="left" width="35%"><bean:message key="label.firstName"/></th>
			    <td align="left" width="65%"><bean:write name="ApplicantFormAgy" property="applicant.user.firstName"/></td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.lastName"/></th>
			    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.user.lastName"/></td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
			    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.user.emailAddress"/></td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.mobileNumber"/></th>
			    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.mobileNumber"/></td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.dateOfBirth"/></th>
			    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.dateOfBirth" formatKey="format.mediumDateFormat" /></td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.niNumber"/></th>
			    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.niNumber"/></td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.gender"/></th>
			    <td align="left">
					  <logic:equal name="ApplicantFormAgy" property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlMale %>">
					    <bean:message key="label.male"/>
					  </logic:equal>
					  <logic:equal name="ApplicantFormAgy" property="applicant.gender" value="<%= com.helmet.persistence.Constants.sqlFemale %>">
					    <bean:message key="label.female"/>
					  </logic:equal>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.discipline"/></th>
			    <td align="left"><bean:write name="ApplicantFormAgy" property="applicant.reference"/></td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.professionalReference"/></th>
			    <td align="left">
  		      <bean:write name="ApplicantFormAgy" property="applicant.hpcNumber"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.limitedCompany"/></th>
			    <td align="left">
			    	<logic:empty name="ApplicantFormAgy" property="applicant.limitedCompanyName">
			    	  &nbsp;
			    	</logic:empty>
			    	<logic:notEmpty name="ApplicantFormAgy" property="applicant.limitedCompanyName">
				    	<bean:write name="ApplicantFormAgy" property="applicant.limitedCompanyName"/>
				    </logic:notEmpty>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.hideMoney"/></th>
			    <td align="left">
			      <logic:equal name="ApplicantFormAgy" property="applicant.hideMoney" value="true">
							<bean:message key="label.yes"/>
			      </logic:equal>
			      <logic:notEqual name="ApplicantFormAgy" property="applicant.hideMoney" value="true">
							<bean:message key="label.no"/>
			      </logic:notEqual>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label"><bean:message key="label.canToggleHideMoney"/></th>
			    <td align="left">
			      <logic:equal name="ApplicantFormAgy" property="applicant.canToggleHideMoney" value="true">
							<bean:message key="label.yes"/>
			      </logic:equal>
			      <logic:notEqual name="ApplicantFormAgy" property="applicant.canToggleHideMoney" value="true">
							<bean:message key="label.no"/>
			      </logic:notEqual>
			    </td>
			  </tr>
			</table>
    </td>
    <td align="center" valign="top" width="25%">
<logic:empty name="ApplicantFormAgy" property="applicant.photoFilename" >
      <bean:message key="text.noPhotoAvailable"/>
</logic:empty>
<logic:notEmpty name="ApplicantFormAgy" property="applicant.photoFilename" >
      <bean:define id="photoFileUrl" name="ApplicantFormAgy" property="applicant.photoFileUrl" type="java.lang.String" />
      <html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" height="180" />
</logic:notEmpty>
    </td>
  </tr>
</table>