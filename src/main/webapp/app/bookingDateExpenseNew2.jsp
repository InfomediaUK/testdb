<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="BookingDateExpenseFormApp" property="list">
<br/>
<br/>
<bean:message key="error.noExpense"/>
</logic:empty>
<logic:notEmpty name="BookingDateExpenseFormApp" property="list" >
<html:form action="/bookingDateExpenseNew3.do" focus="qtyStr" onsubmit="return singleSubmit();">
<html:hidden property="page" value="2"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingDateExpenseNewStep2"/>&nbsp;-&nbsp;<bean:message key="title.bookingDateExpenseNew2"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="bookingDateExpenseNewButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<table class="simple" width="100%">
  <tr>
    <th align="left">
    <logic:equal name="BookingDateExpenseFormApp" property="bookingExpense.isMultiplier" value="true">
      <bean:message key="label.qty"/>
    </logic:equal>
    <logic:notEqual name="BookingDateExpenseFormApp" property="bookingExpense.isMultiplier" value="true">
      <bean:message key="label.value"/>
      (<bean:message key="label.currencySymbol"/>)
    </logic:notEqual>
    </th>
    <td align="left">
      <html:text property="qtyStr" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label" nowrap="true">
      <bean:message key="label.comment"/>&nbsp;
    </th>
    <td align="left">
      <html:textarea property="text" cols="65" rows="3" tabindex="2"/>
    </td>
  </tr>
</table>
</html:form>
</logic:notEmpty>
