<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include flush="true" page="bookingDateView.jsp">
	<jsp:param name="isView" value="false"/>
	<jsp:param name="isCancel" value="false"/>
	<jsp:param name="isActivate" value="true"/>
	<jsp:param name="isAuthorize" value="false"/>
	<jsp:param name="isReject" value="false"/>
	<jsp:param name="isInvoice" value="false"/>
</jsp:include>