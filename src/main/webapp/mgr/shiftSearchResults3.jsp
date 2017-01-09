<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<jsp:include page="shiftSearch.jsp" flush="true"/>

<bean:define id="list" name="ShiftSearchFormMgr" property="list" type="java.util.List"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="text.searchResults"/>&nbsp;(<%=list.size() %>)
    </td>
  </tr>
</table>

<%-- tabs --%>
<jsp:include page="shiftSearchResultsTabs.jsp" flush="true">
  <jsp:param name="tab" value="applicants"/>
</jsp:include>
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="ShiftSearchFormMgr"/>
</jsp:include>