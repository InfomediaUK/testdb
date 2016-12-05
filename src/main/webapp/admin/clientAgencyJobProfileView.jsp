<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.clientAgencyJobProfileView"/>

<br/>
<br/>
<html:errors/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="ClientAgencyJobProfileViewFormAdmin" paramProperty="client.clientId"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="ClientAgencyJobProfileViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.agency"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="agencyView">
      <html:link forward="agencyView" paramId="agency.agencyId" paramName="ClientAgencyJobProfileViewFormAdmin" paramProperty="agency.agencyId"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="agency.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="agencyView">
      <bean:write name="ClientAgencyJobProfileViewFormAdmin" property="agency.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="agency.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="agency.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="agency.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="agency.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobFamilyView">
      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="ClientAgencyJobProfileViewFormAdmin" paramProperty="jobFamily.jobFamilyId"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobFamily.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobFamilyView">
      <bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobFamily.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobFamily.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobFamily.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobFamily.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobSubFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobSubFamilyView">
      <html:link forward="jobSubFamilyView" paramId="jobSubFamily.jobSubFamilyId" paramName="ClientAgencyJobProfileViewFormAdmin" paramProperty="jobSubFamily.jobSubFamilyId"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobSubFamily.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobSubFamilyView">
      <bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobSubFamily.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobSubFamily.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobSubFamily.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobSubFamily.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobProfile"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobProfileView">
      <html:link forward="jobProfileView" paramId="jobProfile.jobProfileId" paramName="ClientAgencyJobProfileViewFormAdmin" paramProperty="jobProfile.jobProfileId"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobProfile.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobProfileView">
      <bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobProfile.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobProfile.code"/></td>
  </tr>
<%-- NOW held at locationJobProfile 
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobProfile.rate"/></td>
  </tr>
--%>
  <tr>
    <td align="left"><bean:message key="label.documentURL"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobProfile.documentURL"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobProfile.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="jobProfile.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.clientAgencyJobProfile"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.percentage"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.percentage"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.sendEmailAddress"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.sendEmailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.masterVendorName"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.masterVendorName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.active"/></td>
  </tr>
</table>

<logic:equal name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.active" value="true">
<mmj-admin:hasAccess forward="clientAgencyJobProfileEdit" >
  <html:link forward="clientAgencyJobProfileEdit" paramId="clientAgencyJobProfile.clientAgencyJobProfileId" paramName="ClientAgencyJobProfileViewFormAdmin" paramProperty="clientAgencyJobProfile.clientAgencyJobProfileId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>

<br/>
<br/>

<bean:message key="title.gradeList"/>
<br/>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.clientAgencyJobProfileGradeUsers">
			<html:form action="clientAgencyJobProfileGradeDelete.do">
			<html:hidden name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.clientAgencyJobProfileId" />
		  <logic:iterate id="clientAgencyJobProfileGradeUser" name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.clientAgencyJobProfileGradeUsers">
		    <logic:equal name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.active" value="true">
		    <mmj-admin:hasAccess forward="clientAgencyJobProfileGradeDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="clientAgencyJobProfileGradeUser" property="clientAgencyJobProfileGradeId" />,<bean:write name="clientAgencyJobProfileGradeUser" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="gradeView">
		      <html:link forward="gradeView" paramId="grade.gradeId" paramName="clientAgencyJobProfileGradeUser" paramProperty="gradeId"><bean:write name="clientAgencyJobProfileGradeUser" property="gradeName"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="gradeView">
 			    <bean:write name="clientAgencyJobProfileGradeUser" property="gradeName"/>
		    </mmj-admin:hasNoAccess>   
		    <mmj-admin:hasAccess forward="clientAgencyJobProfileGradeView">
		      &nbsp;<html:link forward="clientAgencyJobProfileGradeView" paramId="clientAgencyJobProfileGrade.clientAgencyJobProfileGradeId" paramName="clientAgencyJobProfileGradeUser" paramProperty="clientAgencyJobProfileGradeId"><bean:write name="clientAgencyJobProfileGradeUser" property="rate" format="#0.00"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="clientAgencyJobProfileGradeView">
 			    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="rate" format="#0.00"/>
		    </mmj-admin:hasNoAccess>
		    &nbsp;<html:checkbox name="clientAgencyJobProfileGradeUser" property="available" disabled="true" />
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="payRate" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="wtdPercentage" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="niPercentage" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="wtdValue" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="niValue" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="commission" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="chargeRateVatRate" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="payRateVatRate" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="wtdVatRate" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="niVatRate" format="#0.00"/>
		    &nbsp;<bean:write name="clientAgencyJobProfileGradeUser" property="commissionVatRate" format="#0.00"/>
        &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.active" value="true">
			<mmj-admin:hasAccess forward="clientAgencyJobProfileGradeDelete">
			<br/>
			<html:submit><bean:message key="button.exclude"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
    <td valign="top">
      &nbsp;
    </td>
    <td valign="top">
			<bean:message key="title.excluded"/>
			<logic:notEmpty name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.grades">
			<html:form action="clientAgencyJobProfileGradeNew.do">
			<html:hidden name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.clientAgencyJobProfileId" />
		  <logic:iterate id="grade" name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.grades">
		    <logic:equal name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.active" value="true">
		    <mmj-admin:hasAccess forward="clientAgencyJobProfileGradeNew">
		      <html:multibox property="selectedItems" >
		        <bean:write name="grade" property="gradeId" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="gradeView">
		      <html:link forward="gradeView" paramId="grade.gradeId" paramName="grade" paramProperty="gradeId"><bean:write name="grade" property="name"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="gradeView">
 			    <bean:write name="grade" property="name"/>
		    </mmj-admin:hasNoAccess>   
		    &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="ClientAgencyJobProfileViewFormAdmin" property="clientAgencyJobProfile.active" value="true">
			<mmj-admin:hasAccess forward="clientAgencyJobProfileGradeNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>
