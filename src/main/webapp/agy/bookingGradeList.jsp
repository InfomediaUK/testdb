<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:parameter id="bookingGradeStatus" name="bookingGradeStatus" value="-1"/>
<bean:parameter id="viewed" name="viewed" value=""/>
<logic:notEqual name="bookingGradeStatus" value="-1">
  <bean:message key="<%= com.helmet.bean.BookingGrade.BOOKINGGRADE_STATUS_DESCRIPTION_KEYS[Integer.parseInt(bookingGradeStatus)] %>"/>&nbsp;
</logic:notEqual>
<logic:equal name="viewed" value="false">
  <bean:message key="link.new"/>&nbsp;
</logic:equal>
<logic:equal name="bookingGradeStatus" value="-1">
  <logic:equal name="viewed" value="">
  <%/* should never be seen */%>
  <bean:message key="text.all"/>&nbsp;
  </logic:equal>
</logic:equal>
<bean:define id="list" name="BookingGradeListFormAgy" property="list" type="java.util.List"/>
<bean:message key="title.bookings"/>&nbsp;(<%=list.size() %>)
		</td>
  </tr>
</table>

<jsp:include page="bookingGradesInclude.jsp" flush="true">
  <jsp:param name="theFormAgy" value="BookingGradeListFormAgy"/>
</jsp:include>
