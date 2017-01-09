<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">

<html:form action="/managerAccessGroupRemoveProcess.do" onsubmit="return singleSubmit();">
<html:hidden name="ManagerViewFormMgr" property="manager.managerId"/>
<html:hidden name="ManagerViewFormMgr" property="manager.noOfChanges"/>
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.managerAccessGroupRemove"/>
	</td>
    <mmj-mgr:hasAccess forward="managerAccessGroupAdd">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.remove"/></html:submit></td>
	</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<logic:notEmpty name="ManagerViewFormMgr" property="manager.managerAccessGroupUsers">
  <logic:iterate id="managerAccessGroup" name="ManagerViewFormMgr" property="manager.managerAccessGroupUsers" type="com.helmet.bean.ManagerAccessGroupUser">
    <html:multibox property="selectedItems" >
        <bean:write name="managerAccessGroup" property="managerAccessGroupId" />,<bean:write name="managerAccessGroup" property="noOfChanges" />
    </html:multibox>
    <bean:write name="managerAccessGroup" property="mgrAccessGroupName"/>
    <br/>
  </logic:iterate>
</logic:notEmpty>

</html:form>
