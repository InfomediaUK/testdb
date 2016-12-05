<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.mgrAccessGroupView"/>

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
      <html:link forward="clientView" paramId="client.clientId" paramName="MgrAccessGroupViewFormAdmin" paramProperty="client.clientId"><bean:write name="MgrAccessGroupViewFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="MgrAccessGroupViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="MgrAccessGroupViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="MgrAccessGroupViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="MgrAccessGroupViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="MgrAccessGroupViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.mgrAccessGroup"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.active"/></td>
  </tr>
</table>

<logic:equal name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.active" value="true">
<mmj-admin:hasAccess forward="mgrAccessGroupEdit">
  <html:link forward="mgrAccessGroupEdit" paramId="mgrAccessGroup.mgrAccessGroupId" paramName="MgrAccessGroupViewFormAdmin" paramProperty="mgrAccessGroup.mgrAccessGroupId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="mgrAccessGroupDelete">
  <html:link forward="mgrAccessGroupDelete" paramId="mgrAccessGroup.mgrAccessGroupId" paramName="MgrAccessGroupViewFormAdmin" paramProperty="mgrAccessGroup.mgrAccessGroupId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>

<br/>
<br/>

<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.mgrAccessGroupItemUsers">
      <html:form action="mgrAccessGroupItemDelete">
			<html:hidden name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.mgrAccessGroupId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="mgrAccessGroupItemUser" name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.mgrAccessGroupItemUsers">
			  <tr class="client">
			    <td align="left">
			    <logic:equal name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.active" value="true">
			    <mmj-admin:hasAccess forward="mgrAccessGroupItemDelete">
			      <html:multibox property="selectedItems" >
			        <bean:write name="mgrAccessGroupItemUser" property="mgrAccessGroupItemId" />,<bean:write name="mgrAccessGroupItemUser" property="noOfChanges" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="mgrAccessView">
			      <html:link forward="mgrAccessView" paramId="mgrAccess.mgrAccessId" paramName="mgrAccessGroupItemUser" paramProperty="mgrAccessId"><bean:write name="mgrAccessGroupItemUser" property="mgrAccessName"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="mgrAccessView">
  			    <bean:write name="mgrAccessGroupItemUser" property="mgrAccessName"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.active" value="true">
			<mmj-admin:hasAccess forward="mgrAccessGroupItemDelete">
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
      <logic:notEmpty name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.mgrAccesses">
			<html:form action="mgrAccessGroupItemNew">
			<html:hidden name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.mgrAccessGroupId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="mgrAccess" name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.mgrAccesses">
			  <tr class="client">
			    <td align="left">

					<logic:equal name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.active" value="true">
					<mmj-admin:hasAccess forward="mgrAccessGroupItemNew">
			      <html:multibox property="selectedItems" >
			        <bean:write name="mgrAccess" property="mgrAccessId" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="mgrAccessView">
			      <html:link forward="mgrAccessView" paramId="mgrAccess.mgrAccessId" paramName="mgrAccess" paramProperty="mgrAccessId"><bean:write name="mgrAccess" property="name"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="mgrAccessView">
  			    <bean:write name="mgrAccess" property="name"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="MgrAccessGroupViewFormAdmin" property="mgrAccessGroup.active" value="true">
			<mmj-admin:hasAccess forward="mgrAccessGroupItemNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>
