<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="weekToShow" name="BookingsFormAgy" property="weekToShow" />

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:define id="list" name="BookingsFormAgy" property="list" type="java.util.List"/>
      <bean:message key="title.bookings"/>&nbsp;(<%=list.size() %>)
		</td>
  </tr>
</table>
<logic:notPresent name="BookingsFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="NhsBookingsFormAgy" property="list">
	<html:errors/>
</logic:present>

<jsp:include page="weekToShowNavigationTab.jsp" flush="true">
  <jsp:param name="theForm" value="BookingsFormAgy"/>
  <jsp:param name="weekToShow" value="<%= weekToShow %>"/>
</jsp:include>

<logic:present name="BookingsFormAgy" property="list">
  <logic:empty name="BookingsFormAgy" property="list">
    <bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="BookingsFormAgy" property="list">
		<jsp:include page="bookingGradesInclude.jsp" flush="true">
		  <jsp:param name="theFormAgy" value="BookingsFormAgy"/>
		</jsp:include>
  </logic:notEmpty>
</logic:present>