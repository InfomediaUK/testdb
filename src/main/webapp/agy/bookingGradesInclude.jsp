<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:parameter id="theFormAgy" name="theFormAgy" value="ListFormAgy"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<mmj-agy:consultant var="consultant"/>
<!-- included -->
<logic:notPresent name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormAgy %>" property="<%= theList %>">
<logic:empty name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="<%= theFormAgy %>" property="<%= theList %>">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.noDot"/></th>
    <th align="left"><bean:message key="label.start"/></th>
    <th align="left"><bean:message key="label.end"/></th>
    <th align="center"><bean:message key="label.dys"/></th>
    <th align="left"><bean:message key="label.shift"/></th>
    <th align="left"><bean:message key="label.jobProfile"/></th>
    <th align="left"><bean:message key="label.grade"/></th>
    <th align="left"><bean:message key="label.client"/></th>
    <th align="left"><bean:message key="label.location"/></th>
    <th align="left"><bean:message key="label.hrs"/></th>
    <th align="left"><bean:message key="label.rate"/></th>
    <th align="left"><bean:message key="label.value"/></th>
    <th align="center"><bean:message key="label.sa"/></th>
    <th align="center"><bean:message key="label.af"/></th>
    <th align="left"><bean:message key="label.status"/></th>
  </tr>
  </thead>
  <logic:iterate id="bookingGrade" name="<%= theFormAgy %>" property="<%= theList %>" indexId="bookingGradeIndex">
  <bean:define id="jobProfileName" name="bookingGrade" property="jobProfileName" type="java.lang.String" />
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" valign="top">
	    <html:link forward="bookingGradeViewSummary" paramId="bookingGrade.bookingGradeId" paramName="bookingGrade" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewSummary"><bean:write name="bookingGrade" property="bookingId" format="#000"/></html:link>
    </td>
    <td align="left" valign="top"><bean:write name="bookingGrade" property="minBookingDate" formatKey="format.longDateFormatyy"/></td>
    <td align="left" valign="top"><bean:write name="bookingGrade" property="maxBookingDate" formatKey="format.longDateFormatyy"/></td>
    <td align="right" valign="top"><bean:write name="bookingGrade" property="noOfBookingDates"/></td>
    <td align="left" valign="top">
	    <html:link forward="bookingGradeViewShifts" paramId="bookingGrade.bookingGradeId" paramName="bookingGrade" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewShifts">
	    <logic:empty name="bookingGrade" property="shiftName">
	      <bean:message key="label.varied"/>
	    </logic:empty>
	    <logic:notEmpty name="bookingGrade" property="shiftName">
	      <bean:write name="bookingGrade" property="shiftName"/>
	    </logic:notEmpty>
      </html:link>
    </td>
    <td align="left" valign="top">
	    <html:link forward="bookingGradeViewApplicants" paramId="bookingGrade.bookingGradeId" paramName="bookingGrade" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewApplicants"><%= org.apache.commons.lang3.StringUtils.abbreviate(jobProfileName, 25) %></html:link>
    </td>
    <td align="left" valign="top">
      <bean:write name="bookingGrade" property="gradeName"/>
    </td>
    <td align="left" valign="top">
      <bean:write name="bookingGrade" property="clientName"/>
    </td>
    <td align="left" valign="top" title="<bean:write name="bookingGrade" property="locationName"/>, <bean:write name="bookingGrade" property="siteName"/>"><bean:write name="bookingGrade" property="locationName"/></td>
    <td align="right" valign="top">
			<bean:write name="bookingGrade" property="bookingNoOfHours" format="#0.00" />
    </td>
    <td align="right" valign="top">
			<bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="rate" format="#,##0.00" />&nbsp;
			<bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="payRate" format="#,##0.00" />
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
			  &nbsp;<bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="wageRate" format="#,##0.00" />
			</logic:equal>
    </td>
    <td align="right" valign="top">
			<bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="value" format="#,##0.00" />&nbsp;
      (<bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="payRateValue" format="#,##0.00"/>)
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
        &nbsp;(<bean:message key="label.currencySymbol"/><bean:write name="bookingGrade" property="wageRateValue" format="#,##0.00"/>)
      </logic:equal>
    </td>
    <td align="center" valign="middle">
    <bean:define id="singleCandidate" name="bookingGrade" property="singleCandidate" type="java.lang.Boolean" />
	  <logic:equal name="bookingGrade" property="singleCandidate" value="true">
	    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" alt="<%= singleCandidate.toString() %>"/>
	  </logic:equal>
	  <logic:notEqual name="bookingGrade" property="singleCandidate" value="true">
	    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" alt="<%= singleCandidate.toString() %>"/>
	  </logic:notEqual>
	  </td>
    <td align="center" valign="middle">
    <bean:define id="autoFill" name="bookingGrade" property="autoFill" type="java.lang.Boolean" />
	  <logic:equal name="bookingGrade" property="autoFill" value="true">
	    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" alt="<%= autoFill.toString() %>"/>
	  </logic:equal>
	  <logic:notEqual name="bookingGrade" property="autoFill" value="true">
	    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" alt="<%= autoFill.toString() %>"/>
	  </logic:notEqual>
	  </td>
	  <td align="left" valign="top"><bean:message name="bookingGrade" property="statusDescriptionKey"/></td>
  </tr>
  </logic:iterate>
<%--
  <logic:equal name="showTotals" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="7"><bean:message key="label.total"/></th>
		<td align="right"><bean:write name="<%= theFormAgy %>" property="totalHours" format="#0.00"/></td>
		<th align="right">&nbsp</th>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalRRPValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalAgreedValue" format="#,##0.00"/></td>
		<td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalActualValue" format="#,##0.00"/></td>
		<td align="right"><bean:write name="<%= theFormAgy %>" property="totalActualHours" format="#0.00"/></td>
		<th align="right" colspan="3">&nbsp</th>
  </tr>
  </logic:equal>
--%>
</table>
</logic:notEmpty>
</logic:present>