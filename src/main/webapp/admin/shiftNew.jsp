<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.shiftNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="shiftNewProcess.do" focus="shift.name">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="site.name" /> <%-- to preserve value --%>
<html:hidden property="site.countryName" /> <%-- to preserve value --%>
<html:hidden property="location.name" /> <%-- to preserve value --%>

<html:hidden property="shift.locationId" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="client.name"/> (<bean:write name="ShiftFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.site"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="site.name"/> (<bean:write name="ShiftFormAdmin" property="site.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.location"/></td>
    <td align="left"><bean:write name="ShiftFormAdmin" property="location.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="shift.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><html:text property="shift.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="shift.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.startTime"/></td>
    <td align="left">
      <html:select property="shiftStartHour">
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
      </html:select>:<html:select property="shiftStartMinute">
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
    <td align="left"><bean:message key="label.endTime"/></td>
    <td align="left">
      <html:select property="shiftEndHour">
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
      </html:select>:<html:select property="shiftEndMinute">
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
    <td align="left"><bean:message key="label.breakStartTime"/></td>
    <td align="left">
      <html:select property="shiftBreakStartHour">
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
      </html:select>:<html:select property="shiftBreakStartMinute">
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
    <td align="left"><bean:message key="label.breakEndTime"/></td>
    <td align="left">
      <html:select property="shiftBreakEndHour">
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
      </html:select>:<html:select property="shiftBreakEndMinute">
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
    <td align="left"><bean:message key="label.upliftFactor"/></td>
    <td align="left"><html:text property="shift.upliftFactor"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftValue"/></td>
    <td align="left"><html:text property="shift.upliftValue"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.useShiftUplift"/></td>
    <td align="left"><html:checkbox property="shift.useShiftUplift"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.overtimeNoOfHours"/></td>
    <td align="left"><html:text property="shift.overtimeNoOfHours"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.overtimeUpliftFactor"/></td>
    <td align="left"><html:text property="shift.overtimeUpliftFactor"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
