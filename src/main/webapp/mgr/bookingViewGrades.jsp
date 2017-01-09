<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<%-- title and buttons --%>
<jsp:include page="bookingViewHeader.jsp" flush="true">
  <jsp:param name="isView" value="true"/>
</jsp:include>
<%-- tabs --%>
<jsp:include page="bookingViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="grades"/>
</jsp:include>
<%-- grades --%>
<table class="simple" width="100%">
  <tr>
    <th align="left">
			<bean:message key="label.agency"/>
    </th>
    <th align="right">
			<bean:message key="label.percentage"/>
    </th>
    <th align="left">
			<bean:message key="label.grade"/>
    </th>
    <th align="right">
			<bean:message key="label.value"/>
    </th>
    <th align="right">
			<bean:message key="label.rate"/>
    </th>
    <logic:equal name="BookingViewFormMgr" property="booking.draft" value="false">
    <th align="left">
			<bean:message key="label.sendTimestamp"/>
    </th>
    <th align="left">
			<bean:message key="label.sentTimestamp"/>
    </th>
    <th align="left">
			<bean:message key="label.status"/>
    </th>
    </logic:equal>
  </tr>
  <logic:iterate id="bookingGrade" name="BookingViewFormMgr" property="booking.bookingGradeUsers" type="com.helmet.bean.BookingGradeUser">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
			<bean:write name="bookingGrade" property="agencyName"/>
    </td>
    <td align="right">
			<bean:write name="bookingGrade" property="percentage" format="0.00"/>%
    </td>
    <td align="left">
			<bean:write name="bookingGrade" property="gradeName"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="value" format="#,##0.00" />
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="rate" format="#,##0.00" />
<%--
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="payRate" format="#,##0.00" />
      <bean:write name="bookingGrade" property="wtdPercentage" format="#,##0.00" />
      <bean:write name="bookingGrade" property="niPercentage" format="#,##0.00" />
      <bean:write name="bookingGrade" property="commission" format="#,##0.00" />
      <bean:write name="bookingGrade" property="commissionVatRate" format="#,##0.00" />
      <bean:write name="bookingGrade" property="payRateVatRate" format="#,##0.00" />
      <bean:write name="bookingGrade" property="wtdVatRate" format="#,##0.00" />
      <bean:write name="bookingGrade" property="niVatRate" format="#,##0.00" />
--%>
    </td>
    <logic:equal name="BookingViewFormMgr" property="booking.draft" value="false">
    <td align="left">
			<bean:write name="bookingGrade" property="sendTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
    <td align="left">
			<bean:write name="bookingGrade" property="sentTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
    <td align="left">
      <bean:message name="bookingGrade" property="statusDescriptionKey"/>
<%--
      <html:link forward="bookingGradeSubmit" paramId="bookingGrade.bookingGradeId" paramName="bookingGrade" paramProperty="bookingGradeId">
        <bean:message name="bookingGrade" property="statusDescriptionKey"/>
      </html:link>
      (<bean:write name="bookingGrade" property="sentStatus"/>)
--%>
    </td>
    </logic:equal>
  </tr>
  </logic:iterate>
</table> 

<jsp:include page="bookingViewFooter.jsp" flush="true">
  <jsp:param name="xxx" value="xxx"/>
</jsp:include>
