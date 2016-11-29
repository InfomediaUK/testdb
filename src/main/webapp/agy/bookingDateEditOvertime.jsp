<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/bookingDateEditOvertimeProcess.do" focus="bookingDate.shiftOvertimeNoOfHours" onsubmit="return singleSubmit();">
<html:hidden property="bookingDate.bookingDateId" />
<html:hidden property="bookingDate.bookingGradeId" />
<html:hidden property="bookingDate.noOfChanges" />
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.bookingDateEditOvertime"/>
		</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.overtimeNoOfHours" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingDate.shiftOvertimeNoOfHours" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" width="25%">
      <bean:message key="label.overtimeUpliftFactor" />
    </th>
    <td align="left" width="100%">
      <html:text property="bookingDate.shiftOvertimeUpliftFactor" tabindex="1"/>
    </td>
  </tr>
</html:form>
</table>