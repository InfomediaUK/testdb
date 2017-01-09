<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<jsp:include page="bookingSearch.jsp" flush="true"/>
<br/>
<bean:define id="list" name="BookingSearchFormMgr" property="list" type="java.util.List"/>
<bean:message key="text.searchResults"/>&nbsp;(<%=list.size() %>)
<br/>
<br/>
<jsp:include page="bookingsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="BookingSearchFormMgr"/>
  <jsp:param name="showTotals" value="true"/>
</jsp:include>