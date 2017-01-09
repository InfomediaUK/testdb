<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:define id="list" name="ShiftListFormMgr" property="list" type="java.util.List"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/bookingDatesAuthorize.do" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:parameter id="bookingDateStatus" name="bookingDateStatus" value="-1"/>
<bean:parameter id="workedStatus" name="workedStatus" value="-1"/>
<logic:notEqual name="bookingDateStatus" value="-1">
	<bean:message key="<%= com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_DESCRIPTION_KEYS[Integer.parseInt(bookingDateStatus)] %>"/>&nbsp;<bean:message key="title.shifts"/>
</logic:notEqual>
<logic:notEqual name="workedStatus" value="-1">
	<bean:message key="<%= com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_DESCRIPTION_KEYS[Integer.parseInt(workedStatus)] %>"/>&nbsp;<bean:message key="title.timesheets"/>
</logic:notEqual>
<logic:equal name="bookingDateStatus" value="-1">
  <logic:equal name="workedStatus" value="-1">
    <%/* should never be seen */%>
		<bean:message key="text.all"/>
  </logic:equal>
</logic:equal>
&nbsp;(<%=list.size() %>)
		</td>
		<bean:define id="showCheckbox" value="false"/>
    <mmj-mgr:hasAccess forward="bookingDatesAuthorize">
		<bean:define id="showCheckbox" value="true"/>
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="titleButton"><bean:message key="button.authorize"/></html:submit>
    </td>
    </mmj-mgr:hasAccess>
  </tr>
</table>
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="ShiftListFormMgr"/>
  <jsp:param name="showCheckbox" value="<%= showCheckbox %>"/>
</jsp:include>
</html:form>