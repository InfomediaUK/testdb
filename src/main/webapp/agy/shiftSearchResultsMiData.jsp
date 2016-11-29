<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<jsp:include page="shiftSearchMiData.jsp" flush="true"/>
<bean:define id="list" name="ShiftSearchFormAgy" property="list" type="java.util.List"/>

<bean:define id="showCheckbox" value="false"/>
<bean:define id="formAction" value="/bookingGradeViewSummary.do"/>

<logic:equal name="ShiftSearchFormAgy" property="canMultiActivate" value="true">
  <mmj-agy:hasAccess forward="bookingDatesActivate">
  <bean:define id="showCheckbox" value="true"/>
  <bean:define id="formAction" value="/bookingDatesActivate.do"/>
  </mmj-agy:hasAccess>
</logic:equal>
<logic:equal name="ShiftSearchFormAgy" property="canMultiAuthorize" value="true">
	<mmj-agy:hasAccess forward="bookingDatesAuthorize">
  <bean:define id="showCheckbox" value="true"/>
  <bean:define id="formAction" value="/bookingDatesAuthorize.do"/>
  </mmj-agy:hasAccess>
</logic:equal>
<logic:equal name="ShiftSearchFormAgy" property="canMultiInvoice" value="true">
	<mmj-agy:hasAccess forward="bookingDatesInvoice">
  <bean:define id="showCheckbox" value="true"/>
  <bean:define id="formAction" value="/bookingDatesInvoice.do"/>
  </mmj-agy:hasAccess>
</logic:equal>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="<%= formAction %>" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="text.searchResults"/>&nbsp;(<%=list.size() %>)

<logic:equal name="ShiftSearchFormAgy" property="canPrint" value="true">
<a href="javascript:doSearchAndSubmit('ShiftSearchFormAgy', 'shiftSearchProcessPDF.do')">
<bean:message key="link.pdf"/>
</a>
</logic:equal>

    </td>
<logic:present name="ShiftSearchFormAgy" property="list">
  <logic:notEmpty name="ShiftSearchFormAgy" property="list">
		<logic:equal name="ShiftSearchFormAgy" property="canMultiActivate" value="true">
		  <mmj-agy:hasAccess forward="bookingDatesActivate">
		  <td align="right" valign="middle" width="75">
		    <html:submit styleClass="titleButton"><bean:message key="button.activate"/></html:submit>
		  </td>
		  </mmj-agy:hasAccess>
		</logic:equal>
		<logic:equal name="ShiftSearchFormAgy" property="canMultiAuthorize" value="true">
			<mmj-agy:hasAccess forward="bookingDatesAuthorize">
		  <td align="right" valign="middle" width="75">
		    <html:submit styleClass="titleButton"><bean:message key="button.authorize"/></html:submit>
		  </td>
		  </mmj-agy:hasAccess>
		</logic:equal>
		<logic:equal name="ShiftSearchFormAgy" property="canMultiInvoice" value="true">
			<mmj-agy:hasAccess forward="bookingDatesInvoice">
		  <td align="right" valign="middle" width="75">
		    <html:submit styleClass="titleButton"><bean:message key="button.invoice"/></html:submit>
		  </td>
		  </mmj-agy:hasAccess>
		</logic:equal>
  </logic:notEmpty>
</logic:present>
  </tr>
</table>

<logic:present name="ShiftSearchFormAgy" property="list">
  <logic:notEmpty name="ShiftSearchFormAgy" property="list">
<%-- tabs --%>
<jsp:include page="shiftsIncludeMiData.jsp" flush="true">
  <jsp:param name="theFormAgy" value="ShiftSearchFormAgy"/>
  <jsp:param name="showCheckbox" value="<%= showCheckbox %>"/>
</jsp:include>
  </logic:notEmpty>
</logic:present>
 
 </html:form>
 