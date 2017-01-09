<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include page="bookingViewShifts.jsp" flush="true">
  <jsp:param name="tab" value="timesheets"/>
  <jsp:param name="showApplicants" value="true"/>
</jsp:include>   