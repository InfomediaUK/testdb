<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/bookingDatesAuthorizeProcess.do" onsubmit="return singleSubmit();">
  <html:hidden property="bookingDateIdStrs" />
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDatesAuthorize"/>
		</td>
    <logic:notEmpty name="BookingDatesFormMgr" property="list">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </td>
    </logic:notEmpty>
  </tr>

<%--
  <tr>
	<td align="left" valign="middle" class="title">
      <bean:message key="label.pwd"/>
	</td>
	<td align="left" valign="middle" class="title">
      <html:text property="pwd"/>
	</td>
  </tr>
--%>

</html:form>
</table>
<html:errors/>
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="BookingDatesFormMgr"/>
  <jsp:param name="theList" value="list"/>
  <jsp:param name="showHrs" value="true"/>
  <jsp:param name="showRRP" value="true"/>
  <jsp:param name="showAgreed" value="true"/>
  <jsp:param name="showActuals" value="true"/>
  <jsp:param name="showVat" value="false"/>
  <jsp:param name="showTotals" value="true"/>
</jsp:include>
