<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.consultantView"/>

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
      <html:link forward="agencyView" paramId="agency.agencyId" paramName="ConsultantViewFormAdmin" paramProperty="agency.agencyId"><bean:write name="ConsultantViewFormAdmin" property="agency.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="agencyView">
      <bean:write name="ConsultantViewFormAdmin" property="agency.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="agency.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="agency.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="agency.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.consultant"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.fullName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.emailAddress"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.emailAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.login"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.login"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.jobTitle"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.jobTitle"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.canViewDocuments"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.canViewDocuments"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.canViewWages"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.canViewWages"/></td>
  </tr>
  <tr>
      <bean:define id="signatureFileUrl" name="ConsultantViewFormAdmin" property="consultant.signatureFileUrl" type="java.lang.String" />
      <% 
      String fileUrl = request.getContextPath() + signatureFileUrl;
      %>
    <td align="left">Signature</td>
    <td align="left">
<logic:present name="ConsultantViewFormAdmin" property="consultant.signatureFilename">
      <img src="<%= fileUrl %>" />
</logic:present>
<logic:notPresent name="ConsultantViewFormAdmin" property="consultant.signatureFilename">
      <bean:message key="label.uploadSignatureImage" />
</logic:notPresent>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.showPageHelp"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.showPageHelp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.superUser"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.user.superUser"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ConsultantViewFormAdmin" property="consultant.active"/></td>
  </tr>
</table>

<logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
<mmj-admin:hasAccess forward="consultantEdit">
  <html:link forward="consultantEdit" paramId="consultant.consultantId" paramName="ConsultantViewFormAdmin" paramProperty="consultant.consultantId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="consultantDelete">
  <html:link forward="consultantDelete" paramId="consultant.consultantId" paramName="ConsultantViewFormAdmin" paramProperty="consultant.consultantId"><bean:message key="link.delete"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="consultantResetPwd">
  <html:link forward="consultantResetPwd" paramId="consultant.consultantId" paramName="ConsultantViewFormAdmin" paramProperty="consultant.consultantId"><bean:message key="link.resetPwd"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="consultantResetSecretWord">
  <html:link forward="consultantResetSecretWord" paramId="consultant.consultantId" paramName="ConsultantViewFormAdmin" paramProperty="consultant.consultantId"><bean:message key="link.resetSecretWord"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>

<br/>
<br/>

<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
      <logic:notEmpty name="ConsultantViewFormAdmin" property="consultant.consultantAccessGroupUsers">
			<html:form action="consultantAccessGroupDelete.do">
			<html:hidden name="ConsultantViewFormAdmin" property="consultant.consultantId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="consultantAccessGroupUser" name="ConsultantViewFormAdmin" property="consultant.consultantAccessGroupUsers">
			  <tr class="agency">
			    <td align="left">
			    <logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
			    <mmj-admin:hasAccess forward="consultantAccessGroupDelete">
			      <html:multibox property="selectedItems" >
			        <bean:write name="consultantAccessGroupUser" property="consultantAccessGroupId" />,<bean:write name="consultantAccessGroupUser" property="noOfChanges" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="agyAccessGroupView">
			      <html:link forward="agyAccessGroupView" paramId="agyAccessGroup.agyAccessGroupId" paramName="consultantAccessGroupUser" paramProperty="agyAccessGroupId"><bean:write name="consultantAccessGroupUser" property="agyAccessGroupName"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="agyAccessGroupView">
  			    <bean:write name="consultantAccessGroupUser" property="agyAccessGroupName"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
			<mmj-admin:hasAccess forward="consultantAccessGroupDelete">
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
			<logic:notEmpty name="ConsultantViewFormAdmin" property="consultant.agyAccessGroups">
			<html:form action="consultantAccessGroupNew.do">
			<html:hidden name="ConsultantViewFormAdmin" property="consultant.consultantId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="agyAccessGroup" name="ConsultantViewFormAdmin" property="consultant.agyAccessGroups">
			  <tr class="agency">
			    <td align="left">
					<logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
					<mmj-admin:hasAccess forward="consultantAccessGroupNew">
			      <html:multibox property="selectedItems" >
			        <bean:write name="agyAccessGroup" property="agyAccessGroupId" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="agyAccessGroupView">
			      <html:link forward="agyAccessGroupView" paramId="agyAccessGroup.agyAccessGroupId" paramName="agyAccessGroup" paramProperty="agyAccessGroupId"><bean:write name="agyAccessGroup" property="name"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="agyAccessGroupView">
  			    <bean:write name="agyAccessGroup" property="name"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
			<mmj-admin:hasAccess forward="consultantAccessGroupNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>

<%/* INDIVIDUAL ACCESS DETAILS */%>

<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
      <logic:notEmpty name="ConsultantViewFormAdmin" property="consultant.consultantAccessUsers">
      <html:form action="consultantAccessDelete.do">
			<html:hidden name="ConsultantViewFormAdmin" property="consultant.consultantId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="consultantAccessUser" name="ConsultantViewFormAdmin" property="consultant.consultantAccessUsers">
			  <tr class="agency">
			    <td align="left">
			    <logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
			    <mmj-admin:hasAccess forward="consultantAccessDelete">
			      <html:multibox property="selectedItems" >
			        <bean:write name="consultantAccessUser" property="consultantAccessId" />,<bean:write name="consultantAccessUser" property="noOfChanges" />
			      </html:multibox>
			    </mmj-admin:hasAccess>
			    </logic:equal>
			    <mmj-admin:hasAccess forward="agyAccessView">
			      <html:link forward="agyAccessView" paramId="agyAccess.agyAccessId" paramName="consultantAccessUser" paramProperty="agyAccessId"><bean:write name="consultantAccessUser" property="agyAccessName"/></html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="agyAccessView">
  			    <bean:write name="consultantAccessUser" property="agyAccessName"/>
			    </mmj-admin:hasNoAccess>   
			    </td>
			  </tr>
			  </logic:iterate>
			</table>
			<logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
			<mmj-admin:hasAccess forward="consultantAccessDelete">
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
      <logic:notEmpty name="ConsultantViewFormAdmin" property="consultant.agyAccesses">
			<html:form action="consultantAccessNew.do">
			<html:hidden name="ConsultantViewFormAdmin" property="consultant.consultantId" />
			<table class="simple">
			  <thead>
			  <tr>
			    <th align="left"><bean:message key="label.name" /></th>
			  </tr>
			  </thead>
			  <logic:iterate id="agyAccess" name="ConsultantViewFormAdmin" property="consultant.agyAccesses">
			  <tr class="agency">
			    <td align="left">
					<logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
					<mmj-admin:hasAccess forward="consultantAccessNew">
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
			<logic:equal name="ConsultantViewFormAdmin" property="consultant.active" value="true">
			<mmj-admin:hasAccess forward="consultantAccessNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>

