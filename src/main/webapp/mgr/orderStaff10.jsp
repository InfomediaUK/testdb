<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:parameter id="tab" name="tab" value="summary"/>
<jsp:include page="orderStaffHeader.jsp" flush="true"/>
<%-- tabs --%>
<jsp:include page="bookingViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
  <jsp:param name="isInsertOrEdit" value="true"/>
</jsp:include>
<%-- xxx --%>
<jsp:include page="orderStaffDetail.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>