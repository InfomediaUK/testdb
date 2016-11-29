<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%
String actionPath = "/shiftListOnNhsBackingReport.do";
%>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
			<bean:message key="title.shiftListOnNhsBackingReport"/>
			<bean:define id="list" name="ShiftListNhsBackingReportFormAgy" property="list" type="java.util.List"/>
			(<bean:write name="ShiftListNhsBackingReportFormAgy" property="recordCount"/>)
		</td>
  </tr>
</table>

<logic:notPresent name="ShiftListNhsBackingReportFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>

<logic:present name="ShiftListNhsBackingReportFormAgy" property="list">
  <logic:empty name="ShiftListNhsBackingReportFormAgy" property="list">
    <bean:message key="text.noDetails"/>
  </logic:empty>
  <logic:notEmpty name="ShiftListNhsBackingReportFormAgy" property="list">
    <logic:greaterThan name="ShiftListNhsBackingReportFormAgy" property="pageCount" value="1">
		<jsp:include page="pagingInclude.jsp" flush="true">
		  <jsp:param name="actionPath" value="<%= actionPath %>"/>
		  <jsp:param name="formName" value="ShiftListNhsBackingReportFormAgy"/>
		</jsp:include>
    </logic:greaterThan>
		<jsp:include page="shiftsInclude.jsp" flush="true">
		  <jsp:param name="theFormAgy" value="ShiftListNhsBackingReportFormAgy"/>
		  <jsp:param name="showTotals" value="false"/>
		</jsp:include>
	</logic:notEmpty>
</logic:present>

