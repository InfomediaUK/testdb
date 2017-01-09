<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:define id="list" name="AgencyInvoiceListFormMgr" property="list" type="java.util.List"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/agencyInvoicesAuthorize.do" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:parameter id="status" name="status" value="-1"/>
<logic:notEqual name="status" value="-1">
	<bean:message key="<%= com.helmet.bean.AgencyInvoice.AGENCYINVOICE_STATUS_DESCRIPTION_KEYS[Integer.parseInt(status)] %>"/>&nbsp;<bean:message key="title.invoices"/>
</logic:notEqual>
<logic:equal name="status" value="-1">
	<bean:message key="text.all"/>
</logic:equal>
&nbsp;(<%=list.size() %>)
		</td>
		<bean:define id="showCheckbox" value="false"/>
    <mmj-mgr:hasAccess forward="agencyInvoicesAuthorize">
		<bean:define id="showCheckbox" value="true"/>
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="titleButton"><bean:message key="button.authorize"/></html:submit>
    </td>
    </mmj-mgr:hasAccess>
  </tr>
</table>
<jsp:include page="agencyInvoicesInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="AgencyInvoiceListFormMgr"/>
  <jsp:param name="showCheckbox" value="<%= showCheckbox %>"/>
</jsp:include>
</html:form>