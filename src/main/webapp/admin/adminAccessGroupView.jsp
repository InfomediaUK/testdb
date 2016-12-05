<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.adminAccessGroupView"/>

<br/>
<br/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.active"/></td>
  </tr>
</table>

<logic:equal name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.active" value="true">
<mmj-admin:hasAccess forward="adminAccessGroupEdit">
  <html:link forward="adminAccessGroupEdit" paramId="adminAccessGroup.adminAccessGroupId" paramName="AdminAccessGroupViewFormAdmin" paramProperty="adminAccessGroup.adminAccessGroupId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="adminAccessGroupDelete">
  <html:link forward="adminAccessGroupDelete" paramId="adminAccessGroup.adminAccessGroupId" paramName="AdminAccessGroupViewFormAdmin" paramProperty="adminAccessGroup.adminAccessGroupId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>

<br/>
<br/>

<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.adminAccessGroupItemUsers">
      <html:form action="adminAccessGroupItemDelete">
			<html:hidden name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.adminAccessGroupId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="adminAccessGroupItemUser" name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.adminAccessGroupItemUsers">
			  <tr class="client">
			    <td align="left">
			    <logic:equal name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.active" value="true">
			    <mmj-admin:hasAccess forward="adminAccessGroupItemDelete">
			      <html:multibox property="selectedItems" >
			        <bean:write name="adminAccessGroupItemUser" property="adminAccessGroupItemId" />,<bean:write name="adminAccessGroupItemUser" property="noOfChanges" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="adminAccessView">
			      <html:link forward="adminAccessView" paramId="adminAccess.adminAccessId" paramName="adminAccessGroupItemUser" paramProperty="adminAccessId"><bean:write name="adminAccessGroupItemUser" property="adminAccessName"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="adminAccessView">
  			    <bean:write name="adminAccessGroupItemUser" property="adminAccessName"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.active" value="true">
			<mmj-admin:hasAccess forward="adminAccessGroupItemDelete">
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
      <logic:notEmpty name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.adminAccesses">
			<html:form action="adminAccessGroupItemNew">
			<html:hidden name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.adminAccessGroupId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="adminAccess" name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.adminAccesses">
			  <tr class="client">
			    <td align="left">

					<logic:equal name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.active" value="true">
					<mmj-admin:hasAccess forward="adminAccessGroupItemNew">
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
			<logic:equal name="AdminAccessGroupViewFormAdmin" property="adminAccessGroup.active" value="true">
			<mmj-admin:hasAccess forward="adminAccessGroupItemNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>
