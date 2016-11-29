<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include flush="true" page="bookingGradeApplicantView.jsp">
	<jsp:param name="isView" value="false"/>
	<jsp:param name="isSubmit" value="false"/>
	<jsp:param name="isAccept" value="false"/>
	<jsp:param name="isRefuse" value="false"/>
	<jsp:param name="isWithdraw" value="true"/>
</jsp:include>
