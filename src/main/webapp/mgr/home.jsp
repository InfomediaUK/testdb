<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.home"/>
		</td>
  </tr>
</table>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.unfilledBookingsToday"/>
		</td>
  </tr>
</table>
<jsp:include page="bookingsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="HomeFormMgr"/>
  <jsp:param name="theList" value="unfilledBookings"/>
  <jsp:param name="showOnlyRRP" value="true"/>
  <jsp:param name="showTotals" value="false"/>
</jsp:include>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.unfilledShiftsToday"/> 
		</td>
  </tr>
</table>
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="HomeFormMgr"/>
  <jsp:param name="theList" value="unfilledShifts"/>
  <jsp:param name="showHrs" value="true"/>
  <jsp:param name="showRRP" value="true"/>
  <jsp:param name="showAgreed" value="false"/>
  <jsp:param name="showActuals" value="false"/>
  <jsp:param name="showVat" value="false"/>
  <jsp:param name="showTotals" value="false"/>
</jsp:include>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.filledShiftsToday"/> 
		</td>
  </tr>
</table>
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="HomeFormMgr"/>
  <jsp:param name="theList" value="filledShifts"/>
  <jsp:param name="showHrs" value="true"/>
  <jsp:param name="showRRP" value="true"/>
  <jsp:param name="showAgreed" value="true"/>
  <jsp:param name="showActuals" value="true"/>
  <jsp:param name="showVat" value="false"/>
  <jsp:param name="showTotals" value="false"/>
</jsp:include>

