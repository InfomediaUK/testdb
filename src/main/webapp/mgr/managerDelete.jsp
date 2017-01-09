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
<bean:message key="title.managerDelete"/>
		</td>
		<html:form action="managerDeleteProcess.do" onsubmit="return singleSubmit();">
		<mmj-mgr:hasAccess forward="managerDelete">
		<html:hidden property="manager.managerId"/>
		<html:hidden property="manager.noOfChanges"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</mmj-mgr:hasAccess>
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
