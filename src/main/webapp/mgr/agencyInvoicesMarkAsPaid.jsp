<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/agencyInvoicesMarkAsPaidProcess.do" onsubmit="return singleSubmit();">
  <html:hidden property="agencyInvoiceIdStrs" />
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.agencyInvoicesMarkAsPaid"/>
		</td>
    <logic:notEmpty name="AgencyInvoicesFormMgr" property="list">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </td>
    </logic:notEmpty>
  </tr>
</html:form>
</table>
<html:errors/>
<jsp:include page="agencyInvoicesInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="AgencyInvoicesFormMgr"/>
  <jsp:param name="theList" value="list"/>
</jsp:include>
