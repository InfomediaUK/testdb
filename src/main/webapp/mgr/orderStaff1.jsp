<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ 
taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ 
taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<logic:empty name="OrderStaffFormMgr" property="list">
<br/>
<br/>
<bean:message key="error.noReasonsForRequest"/>
</logic:empty>
<logic:notEmpty name="OrderStaffFormMgr" property="list">
<html:form action="/orderStaff2.do" focus="r4r0" onsubmit="return singleSubmit();">
<html:hidden property="page" value="1"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <logic:empty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.newBooking"/>
      </logic:empty>
      <logic:notEmpty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.editBooking"/>
      </logic:notEmpty>
      -&nbsp;<bean:message key="title.orderStaff1"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="orderStaffButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<table class="radio">
<logic:iterate id="reasonForRequest" name="OrderStaffFormMgr" property="list" indexId="reasonForRequestIndex">
  <tr>
    <td>
      <html:radio tabindex="1" property="reasonForRequest.reasonForRequestId" idName="reasonForRequest" value="reasonForRequestId" styleId='<%= "r4r" + reasonForRequestIndex %>' />
    </td>
    <td>
		  <label for="<%= "r4r" + reasonForRequestIndex %>">
		  <bean:write name="reasonForRequest" property="name"/>
		  </label>
    </td>
  </tr>
</logic:iterate>
</table>
<br/>
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label" nowrap="true">
      <bean:message key="label.reasonForRequest"/>&nbsp;
    </th>
    <td align="left" width="100%">
      <html:text property="reasonForRequestText" size="75" tabindex="2" />
    </td>
  </tr>
</table>
</html:form>
</logic:notEmpty>