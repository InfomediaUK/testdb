<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="BookingDateExpenseFormApp" property="list">
<br/>
<br/>
<bean:message key="error.noExpense"/>
</logic:empty>
<logic:notEmpty name="BookingDateExpenseFormApp" property="list" >
<html:form action="/bookingDateExpenseNew4.do" focus="document" enctype="multipart/form-data" onsubmit="return singleSubmit();">
<html:hidden property="page" value="3"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingDateExpenseNewStep3"/>&nbsp;-&nbsp;<bean:message key="title.bookingDateExpenseNew3"/>
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
      <bean:message key="label.uploadReceipt" />
    </th>
    <td align="left">
      <html:file property="document" size="30" />
    </td>
  </tr>
</table>
</html:form>
</logic:notEmpty>