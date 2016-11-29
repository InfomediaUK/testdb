<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:parameter id="status" name="status" value="-1"/>
<logic:notEqual name="status" value="-1">
	<bean:message key="<%= com.helmet.bean.AgencyInvoice.AGENCYINVOICE_STATUS_DESCRIPTION_KEYS[Integer.parseInt(status)] %>"/>&nbsp;<bean:message key="title.invoices"/>
</logic:notEqual>
<logic:equal name="status" value="-1">
	<bean:message key="text.all"/>
</logic:equal>
<bean:define id="list" name="AgencyInvoiceListFormAgy" property="list" type="java.util.List"/>
&nbsp;(<%=list.size() %>)
		</td>
  </tr>
</table>

<jsp:include page="agencyInvoicesInclude.jsp" flush="true">
  <jsp:param name="theFormAgy" value="AgencyInvoiceListFormAgy"/>
</jsp:include>
