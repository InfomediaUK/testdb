<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include flush="true" page="bookingViewSummary.jsp">
	<jsp:param name="isView" value="false"/>
	<jsp:param name="isCancel" value="false"/>
	<jsp:param name="isSubmit" value="true"/>
</jsp:include>