<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/bookingDatesInvoiceProcess.do" onsubmit="return singleSubmit();">
  <html:hidden property="bookingDateIdStrs" />
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDatesInvoice"/>
		</td>
    <logic:notEmpty name="BookingDatesFormAgy" property="list">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit>
    </td>
    </logic:notEmpty>
  </tr>
</html:form>
</table>
<html:errors/>
<logic:notEmpty name="BookingDatesFormAgy" property="list">
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormAgy" value="BookingDatesFormAgy"/>
  <jsp:param name="showHrs" value="false"/>
  <jsp:param name="showAgreed" value="false"/>
  <jsp:param name="showVat" value="true"/>
</jsp:include>
</logic:notEmpty>
