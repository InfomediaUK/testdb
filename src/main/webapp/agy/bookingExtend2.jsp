<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:parameter id="formAction" name="formAction" value="/bookingExtend3.do"/>

<%-- focusControl stuff --%>
<bean:define id="focusControl" value="shift0"/>
<logic:greaterThan name="BookingExtendFormAgy" property="noOfDates" value="1">
  <bean:define id="focusControl" value="bookingDates[0].shiftId"/>
</logic:greaterThan>

<html:form action="<%= formAction %>" focus="<%= focusControl %>" onsubmit="return singleSubmit();">
<html:hidden property="page" value="2"/>

<html:hidden property="bookingGrade.bookingGradeId"/>
<html:hidden property="bookingGrade.bookingId"/>

<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.extendBooking"/>
      -&nbsp;<bean:message key="title.bookingExtend2"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="orderStaffButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<logic:equal name="BookingExtendFormAgy" property="noOfDates" value="1">
<table class="radio">
<logic:iterate id="shift" name="BookingExtendFormAgy" property="shifts" indexId="shiftIndex">
  <tr>
    <td>
<html:radio tabindex="1" property='<%= "bookingDates[" + 0 + "].shiftId" %>' idName="shift" value="shiftId" styleId='<%= "shift" + shiftIndex %>' />
    </td>
    <label for="<%= "shift" + shiftIndex %>">
    <td>
  <bean:write name="shift" property="name"/>
    </td>
    <td>
  <bean:write name="shift" property="startTime" format="HH:mm"/>-<bean:write name="shift" property="endTime" format="HH:mm"/>
    </td>
    <td align="right">
  <bean:write name="shift" property="noOfHours" format="#0.00"/>
    </td>
    <td align="right">
  <bean:write name="shift" property="breakNoOfHours" format="#0.00"/>
    </td>
    </label>
  </tr>
</logic:iterate>
</table>
</logic:equal>
<%-- multiple dates --%>
<logic:greaterThan name="BookingExtendFormAgy" property="noOfDates" value="1">
<table>
<logic:iterate id="bookingDate" name="BookingExtendFormAgy" property="bookingDates" indexId="bookingDateIndex">
  <tr>
    <td align="left">
<fmt:formatDate value="${bookingDate.bookingDate}" pattern="EEE, dd MMM yyyy" />
    </td>
    <td align="left">
<html:select tabindex="1" property='<%= "bookingDates[" + bookingDateIndex + "].shiftId" %>'>
	<logic:iterate id="shift" name="BookingExtendFormAgy" property="shifts" indexId="shiftIndex" type="com.helmet.bean.Shift">
    <bean:define id="shiftId"  name="shift" property="shiftId" type="java.lang.Integer"/>
	  <html:option value="<%= shiftId.toString() %>">
	  <bean:write name="shift" property="name"/>
    <bean:write name="shift" property="startTime" format="HH:mm"/>-<bean:write name="shift" property="endTime" format="HH:mm"/>
	  <bean:write name="shift" property="noOfHours" format="#0.00"/>
	  <bean:write name="shift" property="breakNoOfHours" format="#0.00"/>
	  </html:option>
	</logic:iterate>
</html:select>
    </td>
  </tr>
</logic:iterate>
</table>
</logic:greaterThan>

</html:form>
