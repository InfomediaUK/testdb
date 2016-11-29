<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:define id="bookingDates" name="OrderStaffCopyFormAgy" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
<%
com.helmet.bean.BookingDate minDate = bookingDates[0];
com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
pageContext.setAttribute("minDate", minDate);
pageContext.setAttribute("maxDate", maxDate);
%>

<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.orderStaffCopyStep10"/>
      -&nbsp;<bean:message key="title.orderStaffCopy10"/>
    </td>
    <td align="left" valign="top">
<html:form action="/orderStaffCopyFinish.do" onsubmit="return singleSubmit();">
<html:hidden property="page" value="7"/>
<jsp:include page="orderStaffCopyButtons.jsp" flush="true" >
	<jsp:param name="nextButtonKey" value="button.finish" />
  <jsp:param name="backButtonTabIndex" value="1" />
  <jsp:param name="nextButtonTabIndex" value="2" />
</jsp:include>
</html:form>
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<table width="100%">
  <tr>
    <td>
			<div class="tabber">
			  <div class="tabbertab">
				  <h2>Summary</h2>
		      <jsp:include page="orderStaffSummary.jsp" flush="true" >
		        <jsp:param name="formName" value="OrderStaffCopyFormAgy" />
		      </jsp:include>
 				</div>
			  <div class="tabbertab">
				  <h2>Shifts</h2>
		      <jsp:include page="orderStaffShifts.jsp" flush="true" >
		        <jsp:param name="formName" value="OrderStaffCopyFormAgy" />
		      </jsp:include>
 				</div>
			  <div class="tabbertab">
				  <h2>Agencies</h2>
		      <jsp:include page="orderStaffAgencies.jsp" flush="true" >
		        <jsp:param name="formName" value="OrderStaffCopyFormAgy" />
		      </jsp:include>
 				</div>
			</div>
	  </td>
	</tr>
</table>
    