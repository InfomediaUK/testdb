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
<bean:message key="title.dressCodeView"/>
		</td>
<mmj-mgr:hasAccess forward="dressCodeEdit">
    <html:form action="/dressCodeEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="dressCode.dressCodeId" value="<bean:write name="DressCodeFormMgr" property="dressCode.dressCodeId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="dressCodeDelete">
    <html:form action="/dressCodeDelete.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="dressCode.dressCodeId" value="<bean:write name="DressCodeFormMgr" property="dressCode.dressCodeId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
    <html:form action="/dressCodeView.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="dressCode.locationId" value="<bean:write name="DressCodeFormMgr" property="dressCode.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><bean:write name="DressCodeFormMgr" property="dressCode.name"/></td>
  </tr>
</table>
