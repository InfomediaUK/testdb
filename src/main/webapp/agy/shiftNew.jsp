<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/shiftNewProcess.do" focus="shift.name" onsubmit="return singleSubmit();">
<html:hidden property="shift.locationId" />
<html:hidden property="shift.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.shiftNew"/>
		</td>
    <mmj-agy:hasAccess forward="shiftNew">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><html:text property="shift.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.description"/></th>
    <td align="left"><html:text property="shift.description"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><html:text property="shift.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.startTime"/></th>
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
    <th align="left"><bean:message key="label.endTime"/></th>
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
    <th align="left"><bean:message key="label.breakStartTime"/></th>
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
    <th align="left"><bean:message key="label.breakEndTime"/></th>
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
    <th align="left"><bean:message key="label.upliftFactor"/></th>
    <td align="left"><html:text property="shift.upliftFactor"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.upliftValue"/></th>
    <td align="left"><html:text property="shift.upliftValue"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.useShiftUplift"/></th>
    <td align="left"><html:checkbox property="shift.useShiftUplift"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.overtimeNoOfHours"/></th>
    <td align="left"><html:text property="shift.overtimeNoOfHours"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.overtimeUpliftFactor"/></th>
    <td align="left"><html:text property="shift.overtimeUpliftFactor"/></td>
  </tr>
</html:form>
</table>
  