<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.jobProfileGroupView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="JobProfileGroupViewFormAdmin" paramProperty="client.clientId"><bean:write name="JobProfileGroupViewFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="JobFamilyViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="JobProfileGroupViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="JobProfileGroupViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="JobProfileGroupViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobProfileGroupViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.jobProfileGroup"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.active"/></td>
  </tr>
</table>

<logic:equal name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.active" value="true">
<mmj-admin:hasAccess forward="jobProfileGroupEdit">
  <html:link forward="jobProfileGroupEdit" paramId="jobProfileGroup.jobProfileGroupId" paramName="JobProfileGroupViewFormAdmin" paramProperty="jobProfileGroup.jobProfileGroupId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobProfileGroupDelete">
  <html:link forward="jobProfileGroupDelete" paramId="jobProfileGroup.jobProfileGroupId" paramName="JobProfileGroupViewFormAdmin" paramProperty="jobProfileGroup.jobProfileGroupId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>
<br/>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
      <logic:notEmpty name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.jobProfileGroupItemUsers">
			<html:form action="jobProfileGroupItemDelete.do">
			<html:hidden name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.jobProfileGroupId" />
		  <bean:define id="jobFamilyName" value=""/>
		  <bean:define id="jobSubFamilyName" value=""/>
		
		  <logic:iterate id="jobProfileGroupItemUser" name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.jobProfileGroupItemUsers" indexId="indexId">
		    <logic:notEqual name="jobProfileGroupItemUser" property="jobFamilyName" value="<%= jobFamilyName %>">
			    <mmj-admin:hasAccess forward="jobFamilyView">
			      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="jobProfileGroupItemUser" paramProperty="jobFamilyId"><bean:write name="jobProfileGroupItemUser" property="jobFamilyName"/> (<bean:write name="jobProfileGroupItemUser" property="jobFamilyCode"/>)</html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="jobFamilyView">
			      <bean:write name="jobProfileGroupItemUser" property="jobFamilyName"/> (<bean:write name="jobProfileGroupItemUser" property="jobFamilyCode"/>)
			    </mmj-admin:hasNoAccess>   
		      <br/>
		      <bean:define id="jobFamilyName" name="jobProfileGroupItemUser" property="jobFamilyName" type="java.lang.String"/>
				  <bean:define id="jobSubFamilyName" value=""/>
		    </logic:notEqual>
		    <logic:notEqual name="jobProfileGroupItemUser" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
          <html:img src="images/indent.gif" width="15" height="9"/>
			    <mmj-admin:hasAccess forward="jobSubFamilyView">
			      <html:link forward="jobSubFamilyView" paramId="jobSubFamily.jobSubFamilyId" paramName="jobProfileGroupItemUser" paramProperty="jobSubFamilyId"><bean:write name="jobProfileGroupItemUser" property="jobSubFamilyName"/> (<bean:write name="jobProfileGroupItemUser" property="jobSubFamilyCode"/>)</html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="jobSubFamilyView">
			      <bean:write name="jobProfileGroupItemUser" property="jobSubFamilyName"/> (<bean:write name="jobProfileGroupItemUser" property="jobSubFamilyCode"/>)
			    </mmj-admin:hasNoAccess>   
		      <br/>
		      <bean:define id="jobSubFamilyName" name="jobProfileGroupItemUser" property="jobSubFamilyName" type="java.lang.String"/>
				  <bean:define id="jobProfileName" value=""/>
		    </logic:notEqual>
        <html:img src="images/trans.gif" width="15" height="9"/>
        <html:img src="images/indent.gif" width="15" height="9"/>
		    <logic:equal name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.active" value="true">
		    <mmj-admin:hasAccess forward="jobProfileGroupItemDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="jobProfileGroupItemUser" property="jobProfileGroupItemId" />,<bean:write name="jobProfileGroupItemUser" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="jobProfileView">
		      <html:link forward="jobProfileView" paramId="jobProfile.jobProfileId" paramName="jobProfileGroupItemUser" paramProperty="jobProfileId"><bean:write name="jobProfileGroupItemUser" property="jobProfileName"/>  (<bean:write name="jobProfileGroupItemUser" property="jobProfileCode"/>)</html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="jobProfileView">
          <bean:write name="jobProfileGroupItemUser" property="jobProfileName"/>  (<bean:write name="jobProfileGroupItemUser" property="jobProfileCode"/>)
		    </mmj-admin:hasNoAccess> 
		    &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.active" value="true">
			<mmj-admin:hasAccess forward="jobProfileGroupItemDelete">
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
      <logic:notEmpty name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.jobProfileUsers">
			<html:form action="jobProfileGroupItemNew.do">
			<html:hidden name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.jobProfileGroupId" />
		  <bean:define id="jobFamilyName" value=""/>
		  <bean:define id="jobSubFamilyName" value=""/>
		  <logic:iterate id="jobProfile" name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.jobProfileUsers">
		    <logic:notEqual name="jobProfile" property="jobFamilyName" value="<%= jobFamilyName %>">
			    <mmj-admin:hasAccess forward="jobFamilyView">
			      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="jobProfile" paramProperty="jobFamilyId"><bean:write name="jobProfile" property="jobFamilyName"/> (<bean:write name="jobProfile" property="jobFamilyCode"/>)</html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="jobFamilyView">
			      <bean:write name="jobProfile" property="jobFamilyName"/> (<bean:write name="jobProfile" property="jobFamilyCode"/>)
			    </mmj-admin:hasNoAccess>   
		      <br/>
		      <bean:define id="jobFamilyName" name="jobProfile" property="jobFamilyName" type="java.lang.String"/>
				  <bean:define id="jobSubFamilyName" value=""/>
		    </logic:notEqual>
		    <logic:notEqual name="jobProfile" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
          <html:img src="images/indent.gif" width="15" height="9"/>
			    <mmj-admin:hasAccess forward="jobSubFamilyView">
			      <html:link forward="jobSubFamilyView" paramId="jobSubFamily.jobSubFamilyId" paramName="jobProfile" paramProperty="jobSubFamilyId"><bean:write name="jobProfile" property="jobSubFamilyName"/> (<bean:write name="jobProfile" property="jobSubFamilyCode"/>)</html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="jobSubFamilyView">
			      <bean:write name="jobProfile" property="jobSubFamilyName"/> (<bean:write name="jobProfile" property="jobSubFamilyCode"/>)
			    </mmj-admin:hasNoAccess>   
		      <br/>
		      <bean:define id="jobSubFamilyName" name="jobProfile" property="jobSubFamilyName" type="java.lang.String"/>
				  <bean:define id="jobProfileName" value=""/>
		    </logic:notEqual>
        <html:img src="images/trans.gif" width="15" height="9"/>
        <html:img src="images/indent.gif" width="15" height="9"/>
		    <logic:equal name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.active" value="true">
		    <mmj-admin:hasAccess forward="jobProfileGroupItemNew">
		      <html:multibox property="selectedItems" >
		        <bean:write name="jobProfile" property="jobProfileId" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="jobProfileView">
		      <html:link forward="jobProfileView" paramId="jobProfile.jobProfileId" paramName="jobProfile" paramProperty="jobProfileId"><bean:write name="jobProfile" property="name"/> (<bean:write name="jobProfile" property="code"/>)</html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="jobProfileView">
		      <bean:write name="jobProfile" property="name"/> (<bean:write name="jobProfile" property="code"/>)
		    </mmj-admin:hasNoAccess>   
		    &nbsp;<br/>
		  </logic:iterate>
			<logic:equal name="JobProfileGroupViewFormAdmin" property="jobProfileGroup.active" value="true">
			<mmj-admin:hasAccess forward="jobProfileGroupItemNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>
