<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.jobProfileView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="JobProfileViewFormAdmin" paramProperty="client.clientId"><bean:write name="JobProfileViewFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="JobProfileViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobFamilyView">
      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="JobProfileViewFormAdmin" paramProperty="jobFamily.jobFamilyId"><bean:write name="JobProfileViewFormAdmin" property="jobFamily.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobFamilyView">
      <bean:write name="JobProfileViewFormAdmin" property="jobFamily.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobFamily.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobFamily.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobFamily.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobSubFamily"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="jobSubFamilyView">
      <html:link forward="jobSubFamilyView" paramId="jobSubFamily.jobSubFamilyId" paramName="JobProfileViewFormAdmin" paramProperty="jobSubFamily.jobSubFamilyId"><bean:write name="JobProfileViewFormAdmin" property="jobSubFamily.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobSubFamilyView">
      <bean:write name="JobProfileViewFormAdmin" property="jobSubFamily.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobSubFamily.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobSubFamily.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobSubFamily.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobProfile"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.nhsAssignment"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.nhsAssignment"/></td>
  </tr>
<%--
  <tr>
    <td align="left"><bean:message key="label.rate"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.rate"/></td>
  </tr>
--%>
  <tr>
    <td align="left"><bean:message key="label.autoFillDefault"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.autoFill"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.documentURL"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.documentURL"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobProfileViewFormAdmin" property="jobProfile.active"/></td>
  </tr>
</table>

<logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
<mmj-admin:hasAccess forward="jobProfileEdit">
  <html:link forward="jobProfileEdit" paramId="jobProfile.jobProfileId" paramName="JobProfileViewFormAdmin" paramProperty="jobProfile.jobProfileId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobProfileDelete">
  <html:link forward="jobProfileDelete" paramId="jobProfile.jobProfileId" paramName="JobProfileViewFormAdmin" paramProperty="jobProfile.jobProfileId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
<br/>
<br/>
<bean:message key="title.grades"/>
<br/>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="JobProfileViewFormAdmin" property="jobProfile.jobProfileGradeUsers">
			<html:form action="jobProfileGradeDelete.do">
			<html:hidden name="JobProfileViewFormAdmin" property="jobProfile.jobProfileId" />
		  <%
		    int i = 0;
		  %>
		  <logic:iterate id="jobProfileGradeUser" name="JobProfileViewFormAdmin" property="jobProfile.jobProfileGradeUsers">
        <% 
        if (i == 3) {
          i = 0;
        %>
        &nbsp;<br/>
        <% 
        }
        %>
		    <logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
		    <mmj-admin:hasAccess forward="jobProfileGradeDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="jobProfileGradeUser" property="jobProfileGradeId" />,<bean:write name="jobProfileGradeUser" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="gradeView">
		      <html:link forward="gradeView" paramId="grade.gradeId" paramName="jobProfileGradeUser" paramProperty="gradeId"><bean:write name="jobProfileGradeUser" property="gradeName"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="gradeView">
 			    <bean:write name="jobProfileGradeUser" property="gradeName"/>
		    </mmj-admin:hasNoAccess>   
		    <%
		      i++;
		    %>
		  </logic:iterate>
			<logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
			<mmj-admin:hasAccess forward="jobProfileGradeDelete">
			<br/><br/>
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
			<logic:notEmpty name="JobProfileViewFormAdmin" property="jobProfile.grades">
			<html:form action="jobProfileGradeNew.do">
			<html:hidden name="JobProfileViewFormAdmin" property="jobProfile.jobProfileId" />
		  <%
		    int e = 0;
		  %>
		  <logic:iterate id="grade" name="JobProfileViewFormAdmin" property="jobProfile.grades">
        <% 
        if (e == 3) {
          e = 0;
        %>
        &nbsp;<br/>
        <% 
        }
        %>
		    <logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
		    <mmj-admin:hasAccess forward="jobProfileGradeNew">
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
		    <%
		      e++;
		    %>
		  </logic:iterate>
			<logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
			<mmj-admin:hasAccess forward="jobProfileGradeNew">
			<br/><br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>

<br/>
<br/>
<bean:message key="title.agencies"/>
<br/>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="JobProfileViewFormAdmin" property="jobProfile.clientAgencyJobProfileUsers">
			<html:form action="clientAgencyJobProfileDelete.do">
			<html:hidden name="JobProfileViewFormAdmin" property="jobProfile.jobProfileId" />
		  <logic:iterate id="clientAgencyJobProfileUser" name="JobProfileViewFormAdmin" property="jobProfile.clientAgencyJobProfileUsers">
		    <logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
		    <mmj-admin:hasAccess forward="clientAgencyJobProfileDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="clientAgencyJobProfileUser" property="clientAgencyJobProfileId" />,<bean:write name="clientAgencyJobProfileUser" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="agencyView">
		      <html:link forward="agencyView" paramId="agency.agencyId" paramName="clientAgencyJobProfileUser" paramProperty="agencyId"><bean:write name="clientAgencyJobProfileUser" property="agencyName"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="agencyView">
 			    <bean:write name="clientAgencyJobProfileUser" property="agencyName"/>
		    </mmj-admin:hasNoAccess>   
		    <mmj-admin:hasAccess forward="clientAgencyJobProfileView">
		      &nbsp;<html:link forward="clientAgencyJobProfileView" paramId="clientAgencyJobProfile.clientAgencyJobProfileId" paramName="clientAgencyJobProfileUser" paramProperty="clientAgencyJobProfileId"><bean:write name="clientAgencyJobProfileUser" property="percentage"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="clientAgencyJobProfileView">
 			    &nbsp;<bean:write name="clientAgencyJobProfileUser" property="percentage"/>
		    </mmj-admin:hasNoAccess>
		    <logic:notEmpty name="clientAgencyJobProfileUser" property="sendEmailAddress">
		      <bean:write name="clientAgencyJobProfileUser" property="sendEmailAddress"/>
		    </logic:notEmpty>
		    <logic:notEmpty name="clientAgencyJobProfileUser" property="masterVendorName">
		      <bean:write name="clientAgencyJobProfileUser" property="masterVendorName"/>
		    </logic:notEmpty>
        &nbsp;<br/>
		  </logic:iterate>
			<logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
			<mmj-admin:hasAccess forward="clientAgencyJobProfileDelete">
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
			<logic:notEmpty name="JobProfileViewFormAdmin" property="jobProfile.clientAgencyUsers">
			<html:form action="clientAgencyJobProfileNew.do">
			<html:hidden name="JobProfileViewFormAdmin" property="jobProfile.jobProfileId" />
		  <logic:iterate id="clientAgencyUser" name="JobProfileViewFormAdmin" property="jobProfile.clientAgencyUsers">
		    <logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
		    <mmj-admin:hasAccess forward="clientAgencyJobProfileNew">
		      <html:multibox property="selectedItems" >
		        <bean:write name="clientAgencyUser" property="clientAgencyId" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="agencyView">
		      <html:link forward="agencyView" paramId="agency.agencyId" paramName="clientAgencyUser" paramProperty="agencyId"><bean:write name="clientAgencyUser" property="agencyName"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="agencyView">
 			    <bean:write name="clientAgencyUser" property="agencyName"/>
		    </mmj-admin:hasNoAccess>   
        &nbsp;<br/>
		  </logic:iterate>
			<logic:equal name="JobProfileViewFormAdmin" property="jobProfile.active" value="true">
			<mmj-admin:hasAccess forward="clientAgencyJobProfileNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>