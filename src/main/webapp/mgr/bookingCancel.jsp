<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include flush="true" page="bookingViewShifts.jsp">
	<jsp:param name="isView" value="false"/>
	<jsp:param name="isCancel" value="true"/>
	<jsp:param name="isSubmit" value="false"/>
</jsp:include>