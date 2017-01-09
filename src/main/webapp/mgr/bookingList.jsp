<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:define id="list" name="BookingListFormMgr" property="list" type="java.util.List"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:parameter id="bookingStatus" name="bookingStatus" value="-1"/>
<bean:parameter id="workedStatus" name="workedStatus" value="-1"/>
<bean:parameter id="bookingGradeApplicantDateStatus" name="bookingGradeApplicantDateStatus" value="-1"/>
<logic:notEqual name="bookingStatus" value="-1">
  <bean:message key="<%= com.helmet.bean.Booking.BOOKING_STATUS_DESCRIPTION_KEYS[Integer.parseInt(bookingStatus)] %>"/>&nbsp;<bean:message key="title.bookings"/>
</logic:notEqual>
<logic:notEqual name="workedStatus" value="-1">
	<bean:message key="<%= com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_DESCRIPTION_KEYS[Integer.parseInt(workedStatus)] %>"/>&nbsp;<bean:message key="title.timesheets"/>
</logic:notEqual>
<logic:notEqual name="bookingGradeApplicantDateStatus" value="-1">
	<bean:message key="<%= com.helmet.bean.BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_DESCRIPTION_KEYS[Integer.parseInt(bookingGradeApplicantDateStatus)] %>"/>&nbsp;<bean:message key="title.applicants"/>
</logic:notEqual>
<logic:equal name="bookingDateStatus" value="-1">
  <logic:equal name="workedStatus" value="-1">
    <logic:equal name="bookingGradeApplicantDateStatus" value="-1">
      <%/* should never be seen */%>
		  <bean:message key="text.all"/>
		</logic:equal>
  </logic:equal>
</logic:equal>
&nbsp;(<%=list.size() %>)
		</td>
  </tr>
</table>
<jsp:include page="bookingsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="BookingListFormMgr"/>
  <jsp:param name="showTotals" value="true"/>
</jsp:include>