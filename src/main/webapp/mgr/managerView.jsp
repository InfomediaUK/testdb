<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.managerView"/>

<mmj-mgr:hasAccess forward="managerResetSecretWord">
<html:link forward="managerResetSecretWord" paramId="manager.managerId" paramName="ManagerViewFormMgr" paramProperty="manager.managerId"><bean:message key="link.resetSecretWord"/></html:link>
</mmj-mgr:hasAccess>

<mmj-mgr:hasAccess forward="managerResetPassword">
<html:link forward="managerResetPassword" paramId="manager.managerId" paramName="ManagerViewFormMgr" paramProperty="manager.managerId"><bean:message key="link.resetPassword"/></html:link>
</mmj-mgr:hasAccess>

		</td>
<mmj-mgr:hasAccess forward="managerEdit">
    <html:form action="/managerEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="manager.managerId" value="<bean:write name="ManagerViewFormMgr" property="manager.managerId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="managerDelete">
    <html:form action="/managerDelete.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="manager.managerId" value="<bean:write name="ManagerViewFormMgr" property="manager.managerId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
    <html:form action="/managerView.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.firstName"/></th>
    <td align="left" width="65%"><bean:write name="ManagerViewFormMgr" property="manager.user.firstName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.lastName"/></th>
    <td align="left"><bean:write name="ManagerViewFormMgr" property="manager.user.lastName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.emailAddress"/></th>
    <td align="left"><bean:write name="ManagerViewFormMgr" property="manager.user.emailAddress"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.login"/></th>
    <td align="left"><bean:write name="ManagerViewFormMgr" property="manager.user.login"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.showPageHelp"/></th>
    <td align="left">
      <logic:equal name="ManagerViewFormMgr" property="manager.user.showPageHelp" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ManagerViewFormMgr" property="manager.user.showPageHelp" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.superUser"/></th>
    <td align="left">
      <logic:equal name="ManagerViewFormMgr" property="manager.user.superUser" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ManagerViewFormMgr" property="manager.user.superUser" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
</table>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <td align="left" valign="middle" class="title">
      <bean:message key="title.locationManagerJobProfileList"/>
	</td>
<mmj-mgr:hasAccess forward="locationManagerJobProfileAdd">
    <html:form action="/locationManagerJobProfileAdd.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="manager.managerId" value="<bean:write name="ManagerViewFormMgr" property="manager.managerId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.add"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="locationManagerJobProfileRemove">
    <html:form action="/locationManagerJobProfileRemove.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="manager.managerId" value="<bean:write name="ManagerViewFormMgr" property="manager.managerId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.remove"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
  </tr>
</table>

<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.site" /></th>
    <th align="left"><bean:message key="label.location" /></th>
    <th align="left"><bean:message key="label.jobFamily" /></th>
    <th align="left"><bean:message key="label.jobSubFamily" /></th>
    <th align="left"><bean:message key="label.jobProfile" /></th>
  </tr>
  </thead>
  <bean:define id="siteName" value=""/>
  <bean:define id="locationName" value=""/>
  <bean:define id="jobFamilyName" value=""/>
  <bean:define id="jobSubFamilyName" value=""/>
  <logic:iterate id="locationManagerJobProfileUser" name="ManagerViewFormMgr" property="manager.locationManagerJobProfileUsers" indexId="indexId">
  <tr>
    <td align="left">
    <logic:equal name="locationManagerJobProfileUser" property="siteName" value="<%= siteName %>">
  		&nbsp;
	</logic:equal>
    <logic:notEqual name="locationManagerJobProfileUser" property="siteName" value="<%= siteName %>">
      <bean:write name="locationManagerJobProfileUser" property="siteName"/>
      <logic:notEmpty name="locationManagerJobProfileUser" property="siteCode">
        (<bean:write name="locationManagerJobProfileUser" property="siteCode"/>)
  	  </logic:notEmpty>
      <bean:define id="siteName" name="locationManagerJobProfileUser" property="siteName" type="java.lang.String"/>
	  <bean:define id="locationName" value=""/>
	  <bean:define id="jobFamilyName" value=""/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
	</td>
    <td align="left">
    <logic:equal name="locationManagerJobProfileUser" property="locationName" value="<%= locationName %>">
  		&nbsp;
	</logic:equal>
    <logic:notEqual name="locationManagerJobProfileUser" property="locationName" value="<%= locationName %>">
      <bean:write name="locationManagerJobProfileUser" property="locationName"/>
      <logic:notEmpty name="locationManagerJobProfileUser" property="locationCode">
        (<bean:write name="locationManagerJobProfileUser" property="locationCode"/>)
  	  </logic:notEmpty>
      <bean:define id="locationName" name="locationManagerJobProfileUser" property="locationName" type="java.lang.String"/>
	  <bean:define id="jobFamilyName" value=""/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
	</td>
    <td align="left">
    <logic:equal name="locationManagerJobProfileUser" property="jobFamilyName" value="<%= jobFamilyName %>">
  		&nbsp;
	</logic:equal>
    <logic:notEqual name="locationManagerJobProfileUser" property="jobFamilyName" value="<%= jobFamilyName %>">
      <bean:write name="locationManagerJobProfileUser" property="jobFamilyName"/>
      <logic:notEmpty name="locationManagerJobProfileUser" property="jobFamilyCode">
        (<bean:write name="locationManagerJobProfileUser" property="jobFamilyCode"/>)
  	  </logic:notEmpty>
      <bean:define id="jobFamilyName" name="locationManagerJobProfileUser" property="jobFamilyName" type="java.lang.String"/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
	</td>
	<td align="left">
    <logic:equal name="locationManagerJobProfileUser" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
 		&nbsp;
	</logic:equal>
    <logic:notEqual name="locationManagerJobProfileUser" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
      <bean:write name="locationManagerJobProfileUser" property="jobSubFamilyName"/>
      <logic:notEmpty name="locationManagerJobProfileUser" property="jobSubFamilyCode">
	    (<bean:write name="locationManagerJobProfileUser" property="jobSubFamilyCode"/>)
      </logic:notEmpty>
      <bean:define id="jobSubFamilyName" name="locationManagerJobProfileUser" property="jobSubFamilyName" type="java.lang.String"/>
	  <bean:define id="jobProfileName" value=""/>
    </logic:notEqual>
	</td>
    <td align="left">
      <bean:write name="locationManagerJobProfileUser" property="jobProfileName"/>
      <logic:notEmpty name="locationManagerJobProfileUser" property="jobProfileCode">
        (<bean:write name="locationManagerJobProfileUser" property="jobProfileCode"/>)
      </logic:notEmpty>
    </td>
  </tr>
  </logic:iterate>
</table>


<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <td align="left" valign="middle" class="title">
      <bean:message key="title.managerAccessGroupList"/>
	</td>
<mmj-mgr:hasAccess forward="managerAccessGroupAdd">
    <html:form action="/managerAccessGroupAdd.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="manager.managerId" value="<bean:write name="ManagerViewFormMgr" property="manager.managerId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.add"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="managerAccessGroupRemove">
    <html:form action="/managerAccessGroupRemove.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="manager.managerId" value="<bean:write name="ManagerViewFormMgr" property="manager.managerId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.remove"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
  </tr>
</table>

<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
  </tr>
  </thead>
  <logic:iterate id="managerAccessGroupUser" name="ManagerViewFormMgr" property="manager.managerAccessGroupUsers" indexId="indexId">
  <tr>
    <td align="left">
      <bean:write name="managerAccessGroupUser" property="mgrAccessGroupName"/>
    </td>
  </tr>
  </logic:iterate>
</table>





