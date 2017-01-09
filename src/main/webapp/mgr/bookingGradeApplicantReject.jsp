<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include page="bookingGradeApplicantView.jsp" flush="true">
  <jsp:param name="isView" value="false"/>
  <jsp:param name="isOffer" value="false"/>
  <jsp:param name="isReject" value="true"/>
  <jsp:param name="isActivate" value="false"/>
</jsp:include>