<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<bean:parameter id="isView" name="isView" value="true"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>

<bean:parameter id="tab" name="tab" value="timesheets"/>

<bean:parameter id="showApplicants" name="showApplicants" value="false"/>

<%-- title and buttons --%>
<jsp:include page="bookingGradeViewHeader.jsp" flush="true">
  <jsp:param name="isView" value="<%= isView %>"/>
  <jsp:param name="isCancel" value="<%= isCancel %>"/>
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<%-- tabs --%>
<jsp:include page="bookingGradeViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<br/>
TODO
