<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.agyAccessGroupView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.agency"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="agencyView">
      <html:link forward="agencyView" paramId="agency.agencyId" paramName="AgyAccessGroupViewFormAdmin" paramProperty="agency.agencyId"><bean:write name="AgyAccessGroupViewFormAdmin" property="agency.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="agencyView">
      <bean:write name="AgyAccessGroupViewFormAdmin" property="agency.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agency.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agency.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agency.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agency.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.agyAccessGroup"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.active"/></td>
  </tr>
</table>

<logic:equal name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.active" value="true">
<mmj-admin:hasAccess forward="agyAccessGroupEdit">
  <html:link forward="agyAccessGroupEdit" paramId="agyAccessGroup.agyAccessGroupId" paramName="AgyAccessGroupViewFormAdmin" paramProperty="agyAccessGroup.agyAccessGroupId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="agyAccessGroupDelete">
  <html:link forward="agyAccessGroupDelete" paramId="agyAccessGroup.agyAccessGroupId" paramName="AgyAccessGroupViewFormAdmin" paramProperty="agyAccessGroup.agyAccessGroupId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess> 
</logic:equal>

<br/>
<br/>

<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.agyAccessGroupItemUsers">
      <html:form action="agyAccessGroupItemDelete">
			<html:hidden name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.agyAccessGroupId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="agyAccessGroupItemUser" name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.agyAccessGroupItemUsers">
			  <tr class="agency">
			    <td align="left">
			    <logic:equal name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.active" value="true">
			    <mmj-admin:hasAccess forward="agyAccessGroupItemDelete">
			      <html:multibox property="selectedItems" >
			        <bean:write name="agyAccessGroupItemUser" property="agyAccessGroupItemId" />,<bean:write name="agyAccessGroupItemUser" property="noOfChanges" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="agyAccessView">
			      <html:link forward="agyAccessView" paramId="agyAccess.agyAccessId" paramName="agyAccessGroupItemUser" paramProperty="agyAccessId"><bean:write name="agyAccessGroupItemUser" property="agyAccessName"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="agyAccessView">
  			    <bean:write name="agyAccessGroupItemUser" property="agyAccessName"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.active" value="true">
			<mmj-admin:hasAccess forward="agyAccessGroupItemDelete">
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
      <logic:notEmpty name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.agyAccesses">
			<html:form action="agyAccessGroupItemNew">
			<html:hidden name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.agyAccessGroupId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="agyAccess" name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.agyAccesses">
			  <tr class="agency">
			    <td align="left">

					<logic:equal name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.active" value="true">
					<mmj-admin:hasAccess forward="agyAccessGroupItemNew">
			      <html:multibox property="selectedItems" >
			        <bean:write name="agyAccess" property="agyAccessId" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="agyAccessView">
			      <html:link forward="agyAccessView" paramId="agyAccess.agyAccessId" paramName="agyAccess" paramProperty="agyAccessId"><bean:write name="agyAccess" property="name"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="agyAccessView">
  			    <bean:write name="agyAccess" property="name"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="AgyAccessGroupViewFormAdmin" property="agyAccessGroup.active" value="true">
			<mmj-admin:hasAccess forward="agyAccessGroupItemNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>
