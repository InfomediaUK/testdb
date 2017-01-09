<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<html:form action="/orderStaffFinish.do" focus="submitButton" onsubmit="return singleSubmit();">
<html:hidden property="page" value="10"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <logic:empty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.newBooking"/>
      </logic:empty>
      <logic:notEmpty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.editBooking"/>
      </logic:notEmpty>
      -&nbsp;<bean:message key="title.orderStaff10"/>
    </td>
    <td align="left" valign="top">
		  <jsp:include page="orderStaffButtons.jsp" flush="true" >
		    <jsp:param name="nextButtonKey" value="button.finish" />
		  </jsp:include>
    </td>
  </tr>
</html:form>
</table>
<hr/>
<html:errors />
