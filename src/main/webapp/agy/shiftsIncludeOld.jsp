<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="theFormAgy" name="theFormAgy" value="ShiftListFormAgy"/>
<bean:parameter id="theList" name="theList" value="list"/>
<mmj-agy:consultant var="consultant"/>

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
    <th align="center">
			<bean:message key="label.shiftNo"/>
    </th>
    <th align="left">
			<bean:message key="label.shift"/>
    </th>
    <th align="left">
			<bean:message key="label.time"/>
    </th>
    <th align="left">
			<bean:message key="label.break"/>
    </th>
    <th align="left">
			<bean:message key="label.hrs"/>
    </th>
    <th align="right">
			<bean:message key="label.value"/>
    </th>
    <th align="left">
			<bean:message key="label.status"/>
    </th>
  </tr>
</thead>
<logic:iterate id="bookingDate" name="<%= theFormAgy %>" property="<%= theList %>" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
      <!-- index <bean:write name="bookingDateIndex"/> -->
 			<mmj-agy:hasAccess forward="bookingGradeView">
       <html:link forward="bookingGradeViewSummary" paramId="bookingGrade.bookingGradeId" paramName="bookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewSummary">    			
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
 			</html:link>
 			</mmj-agy:hasAccess>
 			<mmj-agy:hasNoAccess forward="bookingGradeView">
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
 			</mmj-agy:hasNoAccess>
    </td>
    <td align="left" colspan="6" width="100%">
 			<mmj-agy:hasAccess forward="bookingGradeView">
      <html:link forward="bookingGradeViewApplicants" paramId="bookingGrade.bookingGradeId" paramName="bookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewApplicants">    			
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			</html:link>
 			</mmj-agy:hasAccess>
 			<mmj-agy:hasNoAccess forward="bookingGradeView">
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			</mmj-agy:hasNoAccess>
      <bean:write name="bookingDate" property="gradeName"/>
      (<bean:write name="bookingDate" property="locationName"/>,&nbsp;<bean:write name="bookingDate" property="siteName"/>)
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left" nowrap="true">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>&nbsp;
    </td>
    <td align="left">
 			<mmj-agy:hasAccess forward="bookingGradeView">
      <html:link forward="bookingGradeViewShifts" paramId="bookingGrade.bookingGradeId" paramName="bookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewShifts">    			
			<bean:write name="bookingDate" property="shiftName"/>
 			</html:link>
 			</mmj-agy:hasAccess>
 			<mmj-agy:hasNoAccess forward="bookingGradeView">
			<bean:write name="bookingDate" property="shiftName"/>
 			</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
     <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
    </td>
    <td align="left">
      <bean:write name="bookingDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftBreakEndTime" format="HH:mm"/>
      (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
    </td>
    <td align="right">
      <bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRateValue" format="#,##0.00"/>
      &nbsp;(<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="payRateValue" format="#,##0.00"/>)
      <logic:equal name="consultant" property="canViewWages" value="true">
        &nbsp;(<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wageRateValue" format="#,##0.00"/>)
      </logic:equal>
    </td>
    <td align="left">
      <bean:message name="bookingDate" property="statusDescriptionKey"/>
    </td>
  </tr>
</logic:iterate>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left" colspan="4"><bean:message key="label.total"/></th>
	<td align="right"><bean:write name="<%= theFormAgy %>" property="totalHours" format="#0.00"/></td>
	<td align="right">
	<bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalChargeRateValue" format="#,##0.00"/>
	(<bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalPayRateValue" format="#,##0.00"/>)
	(<bean:message key="label.currencySymbol" /><bean:write name="<%= theFormAgy %>" property="totalWageRateValue" format="#,##0.00"/>)
	</td>
	<th align="right">&nbsp;</th>
  </tr>
</table>

</logic:notEmpty>
</logic:present>