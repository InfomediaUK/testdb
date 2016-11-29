<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<mmj-agy:consultant var="consultant"/>
<logic:empty name="BookingGradeApplicantFormAgy" property="list">
<br/>
<br/>
<bean:message key="error.noDates"/>
</logic:empty>
<logic:notEmpty name="BookingGradeApplicantFormAgy" property="list" >
<html:form action="/bookingGradeApplicantNew4.do" focus="bd0" onsubmit="return singleSubmit();">
<html:hidden property="page" value="3"/>
<html:hidden property="selectedBookingDates" value="0"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingGradeApplicantNewStep3"/>&nbsp;-&nbsp;<bean:message key="title.bookingGradeApplicantNew3"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="bookingGradeApplicantNewButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<bean:define id="singleCandidate" name="BookingGradeApplicantFormAgy" property="bookingGrade.singleCandidate" type="java.lang.Boolean" />
<html:errors />
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left" valign="middle">&nbsp;</th>
    <th align="left"><bean:message key="label.shiftNo"/></th>
    <th align="left"><bean:message key="label.date"/></th>
    <th align="left"><bean:message key="label.shift"/></th>
    <th align="left"><bean:message key="label.times"/></th>
    <th align="left"><bean:message key="label.break"/></th>
    <th align="left"><bean:message key="label.hrs"/></th>
    <th align="left"><bean:message key="label.charge"/></th>
    <th align="left"><bean:message key="label.wage"/></th>
    <th align="left"><bean:message key="label.status"/></th>
  </tr>
  </thead>


<logic:iterate name="BookingGradeApplicantFormAgy" property="list" id="bookingDate" indexId="bookingDateIndex" type="com.helmet.bean.BookingDateUser">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left">
	<html:multibox tabindex="1" property="selectedBookingDates" styleId="<%= \"bd\" + bookingDateIndex %>" disabled="<%= singleCandidate.booleanValue() || !bookingDate.getCanBeAppliedFor() %>">
	  <bean:write name="bookingDate" property="bookingDateId"/>
	</html:multibox>
	  </td>
	<label for="<%= "bd" + bookingDateIndex %>">
	  <td align="left">
	<bean:write name="bookingDate" property="bookingId" format="000" />.<bean:write name="bookingDate" property="bookingDateId" format="000" />
	  </td>
	  <td align="left">
	    <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormatyy" />
	  </td>
	  <td align="left">
	<bean:write name="bookingDate" property="shiftName"/>
	  </td>
	  <td align="left">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        Undefined
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
      </logic:notEqual>
	  </td>
	  <td align="left">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <bean:write name="bookingDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftBreakEndTime" format="HH:mm"/>
        (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
      </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
      </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
  	    <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRateValue" format="#,##0.00"/>
  	  </logic:notEqual>
	  </td>
	  <td align="right">
      <logic:equal name="bookingDate" property="undefinedShift" value="true">
        &nbsp;
      </logic:equal>
      <logic:notEqual name="bookingDate" property="undefinedShift" value="true">
        <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
  	      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/>
  	    </logic:equal>
	      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
	        &nbsp;
	      </logic:equal>
  	  </logic:notEqual>
	  </td>
<%--
	  <td align="right">
	<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="payRateValue" format="#,##0.00"/>
	  </td>
--%>
	  <td align="left">
	<bean:message name="bookingDate" property="statusDescriptionKey"/>
	  </td>
	</label>
	<logic:equal name="singleCandidate" value="true">
  <html:hidden property="selectedBookingDates" value="<%= bookingDate.getBookingDateId().toString() %>"/>
	</logic:equal>
	</tr>
</logic:iterate>
<logic:equal name="BookingGradeApplicantFormAgy" property="bookingGrade.undefinedShift" value="true">
  <tr>
    <th align="left" colspan="6">
      <bean:message key="label.total"/>
    </th>
    <td align="right">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.bookingNoOfHours" format="#0.00" />
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.value" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.wageRateValue" format="#,##0.00" />
      </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
    </td>
    <th align="left">
      &nbsp
    </th>
  </tr>
</logic:equal>
</table>
</html:form>
</logic:notEmpty>
