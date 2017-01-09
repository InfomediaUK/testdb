<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:define id="list" name="ShiftListFormMgr" property="list" type="java.util.List"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.shiftsOutstanding"/>&nbsp;(<%=list.size() %>)
    </td>
  </tr>
</table>

<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="ShiftListFormMgr"/>
</jsp:include>
