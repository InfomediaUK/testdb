<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/gradeEditProcess.do" focus="grade.name" onsubmit="return singleSubmit();">
<html:hidden property="grade.gradeId"/>
<html:hidden property="grade.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.gradeEdit"/>
		</td>
    <mmj-mgr:hasAccess forward="gradeEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><html:text property="grade.name"/></td>
  </tr>
</html:form>
</table>
