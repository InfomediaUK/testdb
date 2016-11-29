<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<jsp:include page="agencyInvoiceSearch.jsp" flush="true"/>
<bean:define id="list" name="AgencyInvoiceSearchFormAgy" property="list" type="java.util.List"/>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="text.searchResults"/>&nbsp;(<%=list.size() %>)

    </td>
  </tr>
</table>

<jsp:include page="agencyInvoicesInclude.jsp" flush="true">
  <jsp:param name="theFormAgy" value="AgencyInvoiceSearchFormAgy"/>
</jsp:include>
 