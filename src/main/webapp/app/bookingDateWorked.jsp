<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="bookingDateWorkedProcess.do" focus="workedStartHour" onsubmit="return singleSubmit();">
<html:hidden property="bookingDate.bookingId" />
<html:hidden property="bookingDate.bookingDateId" />
<html:hidden property="bookingDate.jobProfileName" />
<html:hidden property="bookingDate.locationName" />
<html:hidden property="bookingDate.siteName" />
<html:hidden property="bookingDate.bookingDate" />
<html:hidden property="bookingDate.shiftName" />
<html:hidden property="bookingDate.shiftStartTime" />
<html:hidden property="bookingDate.shiftEndTime" />
<logic:notEmpty name="BookingDateFormApp" property="bookingDate.shiftBreakStartTime">
<%-- don't show if no break as this give beanutils.populate error trying to populate times and a bigdecimal with "" --%>
<html:hidden property="bookingDate.shiftBreakStartTime" />
<html:hidden property="bookingDate.shiftBreakEndTime" />
<html:hidden property="bookingDate.shiftBreakNoOfHours" />
</logic:notEmpty>
<html:hidden property="bookingDate.shiftNoOfHours" />
<html:hidden property="bookingDate.status" />
<html:hidden property="bookingDate.workedStatus" />
<html:hidden property="bookingDate.rejectText" />
<html:hidden property="bookingDate.noOfChanges" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.bookingDateWorked"/>
		</td>
    <td align="right" valign="middle" width="75">
	    <html:submit styleClass="titleButton" tabindex="1">
	      <bean:message key="button.save"/>
	    </html:submit>
    </td>
  </tr>
</table>
<html:errors/>
<script language="javascript" type="text/javascript">
<!--
function noBreak(field) {
  if (field.value == "") {
    setItsValue('workedBreakStartHour', '');
    setItsValue('workedBreakStartMinute', '');
    setItsValue('workedBreakEndHour', '');
    setItsValue('workedBreakEndMinute', '');
//    BookingDateFormApp.workedBreakStartHour.selectedIndex = 0;
//    BookingDateFormApp.workedBreakStartMinute.selectedIndex = 0;
//    BookingDateFormApp.workedBreakEndHour.selectedIndex = 0;
//    BookingDateFormApp.workedBreakEndMinute.selectedIndex = 0;
  }
}

function notWorked(field) {
  if (field.value == "") {
    setItsValue('workedStartHour', '');
    setItsValue('workedStartMinute', '');
    setItsValue('workedEndHour', '');
    setItsValue('workedEndMinute', '');
//    BookingDateFormApp.workedStartHour.selectedIndex = 0;
//    BookingDateFormApp.workedStartMinute.selectedIndex = 0;
//    BookingDateFormApp.workedEndHour.selectedIndex = 0;
//    BookingDateFormApp.workedEndMinute.selectedIndex = 0;
    noBreak(field);
  }
}
// -->
</script>
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" width="30%">
      <bean:message key="label.shiftNo"/>
    </th>
    <td align="left" width="70%">
    <html:link forward="home">
      <bean:write name="BookingDateFormApp" property="bookingDate.bookingId" format="#000" />.<bean:write name="BookingDateFormApp" property="bookingDate.bookingDateId" format="#000" />
    </html:link>    
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.jobProfile"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateFormApp" property="bookingDate.jobProfileName" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.location"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateFormApp" property="bookingDate.clientName" />,
      <bean:write name="BookingDateFormApp" property="bookingDate.locationName" />,
      <bean:write name="BookingDateFormApp" property="bookingDate.siteName" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.date"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateFormApp" property="bookingDate.bookingDate" formatKey="format.longDateFormat" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.shift"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateFormApp" property="bookingDate.shiftName" />
	  </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.time"/>
    </th>
    <td align="left">
<logic:equal name="BookingDateFormApp" property="bookingDate.undefinedShift" value="true">
      Undefined
</logic:equal>
<logic:notEqual name="BookingDateFormApp" property="bookingDate.undefinedShift" value="true">
	    <bean:write name="BookingDateFormApp" property="bookingDate.shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateFormApp" property="bookingDate.shiftEndTime" format="HH:mm"/>
</logic:notEqual>
	  </td>
	</tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.break"/>
    </th>
	  <td align="left">
<logic:equal name="BookingDateFormApp" property="bookingDate.undefinedShift" value="true">
      &nbsp;
</logic:equal>
<logic:notEqual name="BookingDateFormApp" property="bookingDate.undefinedShift" value="true">
	    <bean:write name="BookingDateFormApp" property="bookingDate.shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="BookingDateFormApp" property="bookingDate.shiftBreakEndTime" format="HH:mm"/>
	    (<bean:write name="BookingDateFormApp" property="bookingDate.shiftBreakNoOfHours" format="#0.00"/>)
</logic:notEqual>
	  </td>
	</tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.hours"/>
    </th>
	  <td align="left">
<logic:equal name="BookingDateFormApp" property="bookingDate.undefinedShift" value="true">
      &nbsp;
</logic:equal>
<logic:notEqual name="BookingDateFormApp" property="bookingDate.undefinedShift" value="true">
	    <bean:write name="BookingDateFormApp" property="bookingDate.shiftNoOfHours" format="#0.00"/>
</logic:notEqual>
	  </td>
  </tr>
<%-- SHOULD be showing wageRate and wageRateValue 
  <tr>
    <th align="left" class="label">
      <bean:message key="label.agreed"/>
    </th>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="BookingDateFormApp" property="bookingDate.payRateValue" format="#,##0.00" />
      <bean:write name="BookingDateFormApp" property="bookingDate.wtdPercentage" format="#,##0.00" />
      <bean:write name="BookingDateFormApp" property="bookingDate.niPercentage" format="#,##0.00" />
    </td>
  </tr>
--%>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.startTime"/>
    </th>
    <td align="left">
      <html:select property="workedStartHour" styleId="workedStartHour" onchange="javascript:notWorked(this)">
        <html:option value=""><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="1">01</html:option>
        <html:option value="2">02</html:option>
        <html:option value="3">03</html:option>
        <html:option value="4">04</html:option>
        <html:option value="5">05</html:option>
        <html:option value="6">06</html:option>
        <html:option value="7">07</html:option>
        <html:option value="8">08</html:option>
        <html:option value="9">09</html:option>
        <html:option value="10">10</html:option>
        <html:option value="11">11</html:option>
        <html:option value="12">12</html:option>
        <html:option value="13">13</html:option>
        <html:option value="14">14</html:option>
        <html:option value="15">15</html:option>
        <html:option value="16">16</html:option>
        <html:option value="17">17</html:option>
        <html:option value="18">18</html:option>
        <html:option value="19">19</html:option>
        <html:option value="20">20</html:option>
        <html:option value="21">21</html:option>
        <html:option value="22">22</html:option>
        <html:option value="23">23</html:option>
      </html:select>:<html:select property="workedStartMinute" styleId="workedStartMinute" onchange="javascript:notWorked(this)">
        <html:option value=""><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="5">05</html:option>
        <html:option value="10">10</html:option>
        <html:option value="15">15</html:option>
        <html:option value="20">20</html:option>
        <html:option value="25">25</html:option>
        <html:option value="30">30</html:option>
        <html:option value="35">35</html:option>
        <html:option value="40">40</html:option>
        <html:option value="45">45</html:option>
        <html:option value="50">50</html:option>
        <html:option value="55">55</html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.endTime"/>
    </th>
    <td align="left">
      <html:select property="workedEndHour" styleId="workedEndHour" onchange="javascript:notWorked(this)">
        <html:option value=""><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="1">01</html:option>
        <html:option value="2">02</html:option>
        <html:option value="3">03</html:option>
        <html:option value="4">04</html:option>
        <html:option value="5">05</html:option>
        <html:option value="6">06</html:option>
        <html:option value="7">07</html:option>
        <html:option value="8">08</html:option>
        <html:option value="9">09</html:option>
        <html:option value="10">10</html:option>
        <html:option value="11">11</html:option>
        <html:option value="12">12</html:option>
        <html:option value="13">13</html:option>
        <html:option value="14">14</html:option>
        <html:option value="15">15</html:option>
        <html:option value="16">16</html:option>
        <html:option value="17">17</html:option>
        <html:option value="18">18</html:option>
        <html:option value="19">19</html:option>
        <html:option value="20">20</html:option>
        <html:option value="21">21</html:option>
        <html:option value="22">22</html:option>
        <html:option value="23">23</html:option>
      </html:select>:<html:select property="workedEndMinute" styleId="workedEndMinute" onchange="javascript:notWorked(this)">
        <html:option value=""><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="5">05</html:option>
        <html:option value="10">10</html:option>
        <html:option value="15">15</html:option>
        <html:option value="20">20</html:option>
        <html:option value="25">25</html:option>
        <html:option value="30">30</html:option>
        <html:option value="35">35</html:option>
        <html:option value="40">40</html:option>
        <html:option value="45">45</html:option>
        <html:option value="50">50</html:option>
        <html:option value="55">55</html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.breakStartTime"/>
    </th>
    <td align="left">
      <html:select property="workedBreakStartHour" styleId="workedBreakStartHour" onchange="javascript:noBreak(this)">
        <html:option value=""><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="1">01</html:option>
        <html:option value="2">02</html:option>
        <html:option value="3">03</html:option>
        <html:option value="4">04</html:option>
        <html:option value="5">05</html:option>
        <html:option value="6">06</html:option>
        <html:option value="7">07</html:option>
        <html:option value="8">08</html:option>
        <html:option value="9">09</html:option>
        <html:option value="10">10</html:option>
        <html:option value="11">11</html:option>
        <html:option value="12">12</html:option>
        <html:option value="13">13</html:option>
        <html:option value="14">14</html:option>
        <html:option value="15">15</html:option>
        <html:option value="16">16</html:option>
        <html:option value="17">17</html:option>
        <html:option value="18">18</html:option>
        <html:option value="19">19</html:option>
        <html:option value="20">20</html:option>
        <html:option value="21">21</html:option>
        <html:option value="22">22</html:option>
        <html:option value="23">23</html:option>
      </html:select>:<html:select property="workedBreakStartMinute" styleId="workedBreakStartMinute" onchange="javascript:noBreak(this)">
        <html:option value=""><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="5">05</html:option>
        <html:option value="10">10</html:option>
        <html:option value="15">15</html:option>
        <html:option value="20">20</html:option>
        <html:option value="25">25</html:option>
        <html:option value="30">30</html:option>
        <html:option value="35">35</html:option>
        <html:option value="40">40</html:option>
        <html:option value="45">45</html:option>
        <html:option value="50">50</html:option>
        <html:option value="55">55</html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.breakEndTime"/>
    </th>
    <td align="left">
      <html:select property="workedBreakEndHour" styleId="workedBreakEndHour" onchange="javascript:noBreak(this)">
        <html:option value=""><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="1">01</html:option>
        <html:option value="2">02</html:option>
        <html:option value="3">03</html:option>
        <html:option value="4">04</html:option>
        <html:option value="5">05</html:option>
        <html:option value="6">06</html:option>
        <html:option value="7">07</html:option>
        <html:option value="8">08</html:option>
        <html:option value="9">09</html:option>
        <html:option value="10">10</html:option>
        <html:option value="11">11</html:option>
        <html:option value="12">12</html:option>
        <html:option value="13">13</html:option>
        <html:option value="14">14</html:option>
        <html:option value="15">15</html:option>
        <html:option value="16">16</html:option>
        <html:option value="17">17</html:option>
        <html:option value="18">18</html:option>
        <html:option value="19">19</html:option>
        <html:option value="20">20</html:option>
        <html:option value="21">21</html:option>
        <html:option value="22">22</html:option>
        <html:option value="23">23</html:option>
      </html:select>:<html:select property="workedBreakEndMinute" styleId="workedBreakEndMinute" onchange="javascript:noBreak(this)">
        <html:option value=""><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="5">05</html:option>
        <html:option value="10">10</html:option>
        <html:option value="15">15</html:option>
        <html:option value="20">20</html:option>
        <html:option value="25">25</html:option>
        <html:option value="30">30</html:option>
        <html:option value="35">35</html:option>
        <html:option value="40">40</html:option>
        <html:option value="45">45</html:option>
        <html:option value="50">50</html:option>
        <html:option value="55">55</html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="label.comment"/>
    </th>
    <td align="left">
  		<html:textarea property="bookingDate.comment" cols="50" rows="4" />
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.status"/>
    </th>
    <td align="left">
	    <bean:message name="BookingDateFormApp" property="bookingDate.workedStatusDescriptionKey"/>
    </td>
  </tr>
  <logic:equal name="BookingDateFormApp" property="bookingDate.workedStatusIsRejected" value="true">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.rejectText"/>
    </th>
    <td align="left">
      <bean:write name="BookingDateFormApp" property="bookingDate.rejectText"/>
    </td>
  </tr>
	</logic:equal>
</html:form>
</table>
  