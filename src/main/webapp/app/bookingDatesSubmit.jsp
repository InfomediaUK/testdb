<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/bookingDatesSubmitProcess.do" onsubmit="return singleSubmit();">
  <html:hidden property="bookingDateIdStrs" />
  <tr>
	<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingDatesSubmit"/>
	</td>
    <logic:notEmpty name="BookingDatesFormApp" property="bookingGradeApplicant.bookingGradeApplicantDateUserEntities" >
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="confirmButton" titleKey="link.title.confirmSubmit"><bean:message key="button.confirm"/></html:submit>
    </td>
    </logic:notEmpty>
  </tr>
</html:form>
</table>
<mmj-app:applicant var="applicant"/>
<bean:define id="hideMoney" name="applicant" property="hideMoney"/>
<bean:define id="expenseCount" name="BookingDatesFormApp" property="bookingGradeApplicant.bookingExpensesCount.value"/>
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormApp" value="BookingDatesFormApp"/>
  <jsp:param name="theList" value="bookingGradeApplicant.bookingGradeApplicantDateUserEntities"/>
  <jsp:param name="theUpliftFactors" value="bookingGradeApplicant.upliftFactors"/>
  <jsp:param name="showCheckbox" value="false"/>
  <jsp:param name="expenseCount" value="<%= expenseCount %>"/>
  <jsp:param name="showTotals" value="true"/>
  <jsp:param name="hideMoney" value="<%= hideMoney %>"/>
  <jsp:param name="show" value="NOTWEEKLY"/>
</jsp:include>
