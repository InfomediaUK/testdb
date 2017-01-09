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

<html:form action="/managerAccessGroupAddProcess.do" onsubmit="return singleSubmit();">
<html:hidden name="ManagerViewFormMgr" property="manager.managerId"/>
<html:hidden name="ManagerViewFormMgr" property="manager.noOfChanges"/>
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.managerAccessGroupAdd"/>
	</td>
    <mmj-mgr:hasAccess forward="managerAccessGroupAdd">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.add"/></html:submit></td>
	</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<logic:notEmpty name="ManagerViewFormMgr" property="manager.mgrAccessGroups">
  <logic:iterate id="mgrAccessGroup" name="ManagerViewFormMgr" property="manager.mgrAccessGroups" type="com.helmet.bean.MgrAccessGroup">
    <html:multibox property="selectedItems" >
        <bean:write name="mgrAccessGroup" property="mgrAccessGroupId" />
    </html:multibox>
    <bean:write name="mgrAccessGroup" property="name"/>
    <br/>
  </logic:iterate>
</logic:notEmpty>

</html:form>
