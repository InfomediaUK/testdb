<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.administratorView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.fullName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.login"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.showPageHelp"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.showPageHelp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.superUser"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.user.superUser"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AdministratorViewFormAdmin" property="administrator.active"/></td>
  </tr>
</table>

<logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
<mmj-admin:hasAccess forward="administratorEdit">
  <html:link forward="administratorEdit" paramId="administrator.administratorId" paramName="AdministratorViewFormAdmin" paramProperty="administrator.administratorId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="administratorDelete">
  <html:link forward="administratorDelete" paramId="administrator.administratorId" paramName="AdministratorViewFormAdmin" paramProperty="administrator.administratorId"><bean:message key="link.delete"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="administratorResetPwd">
  <html:link forward="administratorResetPwd" paramId="administrator.administratorId" paramName="AdministratorViewFormAdmin" paramProperty="administrator.administratorId"><bean:message key="link.resetPwd"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="administratorResetSecretWord">
  <html:link forward="administratorResetSecretWord" paramId="administrator.administratorId" paramName="AdministratorViewFormAdmin" paramProperty="administrator.administratorId"><bean:message key="link.resetSecretWord"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>

<%-- GROUP ACCESS DETAILS --%>

<br/>
<br/>

<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
      <logic:notEmpty name="AdministratorViewFormAdmin" property="administrator.administratorAccessGroupUsers">
			<html:form action="administratorAccessGroupDelete.do">
			<html:hidden name="AdministratorViewFormAdmin" property="administrator.administratorId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="administratorAccessGroupUser" name="AdministratorViewFormAdmin" property="administrator.administratorAccessGroupUsers">
			  <tr class="client">
			    <td align="left">
			    <logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
			    <mmj-admin:hasAccess forward="administratorAccessGroupDelete">
			      <html:multibox property="selectedItems" >
			        <bean:write name="administratorAccessGroupUser" property="administratorAccessGroupId" />,<bean:write name="administratorAccessGroupUser" property="noOfChanges" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="adminAccessGroupView">
			      <html:link forward="adminAccessGroupView" paramId="adminAccessGroup.adminAccessGroupId" paramName="administratorAccessGroupUser" paramProperty="adminAccessGroupId"><bean:write name="administratorAccessGroupUser" property="adminAccessGroupName"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="adminAccessGroupView">
  			    <bean:write name="administratorAccessGroupUser" property="adminAccessGroupName"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
			<mmj-admin:hasAccess forward="administratorAccessGroupDelete">
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
			<logic:notEmpty name="AdministratorViewFormAdmin" property="administrator.adminAccessGroups">
			<html:form action="administratorAccessGroupNew.do">
			<html:hidden name="AdministratorViewFormAdmin" property="administrator.administratorId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="adminAccessGroup" name="AdministratorViewFormAdmin" property="administrator.adminAccessGroups">
			  <tr class="client">
			    <td align="left">
					<logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
					<mmj-admin:hasAccess forward="administratorAccessGroupNew">
			      <html:multibox property="selectedItems" >
			        <bean:write name="adminAccessGroup" property="adminAccessGroupId" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="adminAccessGroupView">
			      <html:link forward="adminAccessGroupView" paramId="adminAccessGroup.adminAccessGroupId" paramName="adminAccessGroup" paramProperty="adminAccessGroupId"><bean:write name="adminAccessGroup" property="name"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="adminAccessGroupView">
  			    <bean:write name="adminAccessGroup" property="name"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
			<mmj-admin:hasAccess forward="administratorAccessGroupNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>

<%-- INDIVIDUAL ACCESS DETAILS --%>

<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
      <logic:notEmpty name="AdministratorViewFormAdmin" property="administrator.administratorAccessUsers">
      <html:form action="administratorAccessDelete.do">
			<html:hidden name="AdministratorViewFormAdmin" property="administrator.administratorId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="administratorAccessUser" name="AdministratorViewFormAdmin" property="administrator.administratorAccessUsers">
			  <tr class="client">
			    <td align="left">
			    <logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
			    <mmj-admin:hasAccess forward="administratorAccessDelete">
			      <html:multibox property="selectedItems" >
			        <bean:write name="administratorAccessUser" property="administratorAccessId" />,<bean:write name="administratorAccessUser" property="noOfChanges" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="adminAccessView">
			      <html:link forward="adminAccessView" paramId="adminAccess.adminAccessId" paramName="administratorAccessUser" paramProperty="adminAccessId"><bean:write name="administratorAccessUser" property="adminAccessName"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="adminAccessView">
  			    <bean:write name="administratorAccessUser" property="adminAccessName"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
			<mmj-admin:hasAccess forward="administratorAccessDelete">
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
      <logic:notEmpty name="AdministratorViewFormAdmin" property="administrator.adminAccesses">
			<html:form action="administratorAccessNew.do">
			<html:hidden name="AdministratorViewFormAdmin" property="administrator.administratorId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="adminAccess" name="AdministratorViewFormAdmin" property="administrator.adminAccesses">
			  <tr class="client">
			    <td align="left">
					<logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
					<mmj-admin:hasAccess forward="administratorAccessNew">
			      <html:multibox property="selectedItems" >
			        <bean:write name="adminAccess" property="adminAccessId" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="adminAccessView">
			      <html:link forward="adminAccessView" paramId="adminAccess.adminAccessId" paramName="adminAccess" paramProperty="adminAccessId"><bean:write name="adminAccess" property="name"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="adminAccessView">
  			    <bean:write name="adminAccess" property="name"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="AdministratorViewFormAdmin" property="administrator.active" value="true">
			<mmj-admin:hasAccess forward="administratorAccessNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>
