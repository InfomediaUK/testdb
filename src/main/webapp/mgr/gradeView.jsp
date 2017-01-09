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
<bean:message key="title.gradeView"/>
		</td>
<logic:equal name="GradeFormMgr" property="grade.active" value="true">
<mmj-mgr:hasAccess forward="gradeEdit">
    <html:form action="/gradeEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="grade.gradeId" value="<bean:write name="GradeFormMgr" property="grade.gradeId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="gradeDelete">
    <html:form action="/gradeDelete.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="grade.gradeId" value="<bean:write name="GradeFormMgr" property="grade.gradeId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
</logic:equal>  
    <html:form action="/gradeView.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><bean:write name="GradeFormMgr" property="grade.name"/></td>
  </tr>
</table>
