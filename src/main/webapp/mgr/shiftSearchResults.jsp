<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<jsp:include page="shiftSearch.jsp" flush="true"/>
<bean:define id="list" name="ShiftSearchFormMgr" property="list" type="java.util.List"/>

<bean:define id="showCheckbox" value="false"/>
<bean:define id="formAction" value="/home.do"/>
<logic:equal name="ShiftSearchFormMgr" property="canMultiActivate" value="true">
  <mmj-mgr:hasAccess forward="bookingDatesActivate">
  <bean:define id="showCheckbox" value="true"/>
  <bean:define id="formAction" value="/bookingDatesActivate.do"/>
  </mmj-mgr:hasAccess>
</logic:equal>
<logic:equal name="ShiftSearchFormMgr" property="canMultiAuthorize" value="true">
	<mmj-mgr:hasAccess forward="bookingDatesAuthorize">
  <bean:define id="showCheckbox" value="true"/>
  <bean:define id="formAction" value="/bookingDatesAuthorize.do"/>
  </mmj-mgr:hasAccess>
</logic:equal>
<logic:equal name="ShiftSearchFormMgr" property="canMultiInvoice" value="true">
	<mmj-mgr:hasAccess forward="bookingDatesInvoice">
  <bean:define id="showCheckbox" value="true"/>
  <bean:define id="formAction" value="/bookingDatesInvoice.do"/>
  </mmj-mgr:hasAccess>
</logic:equal>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="<%= formAction %>" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="text.searchResults"/>&nbsp;(<%=list.size() %>)
    </td>
<logic:present name="ShiftSearchFormMgr" property="list">
  <logic:notEmpty name="ShiftSearchFormMgr" property="list">
		<logic:equal name="ShiftSearchFormMgr" property="canMultiActivate" value="true">
		  <mmj-mgr:hasAccess forward="bookingDatesActivate">
		  <td align="right" valign="middle" width="75">
		    <html:submit styleClass="titleButton"><bean:message key="button.activate"/></html:submit>
		  </td>
		  </mmj-mgr:hasAccess>
		</logic:equal>
		<logic:equal name="ShiftSearchFormMgr" property="canMultiAuthorize" value="true">
			<mmj-mgr:hasAccess forward="bookingDatesAuthorize">
		  <td align="right" valign="middle" width="75">
		    <html:submit styleClass="titleButton"><bean:message key="button.authorize"/></html:submit>
		  </td>
		  </mmj-mgr:hasAccess>
		</logic:equal>
		<logic:equal name="ShiftSearchFormMgr" property="canMultiInvoice" value="true">
			<mmj-mgr:hasAccess forward="bookingDatesInvoice">
		  <td align="right" valign="middle" width="75">
		    <html:submit styleClass="titleButton"><bean:message key="button.invoice"/></html:submit>
		  </td>
		  </mmj-mgr:hasAccess>
		</logic:equal>
  </logic:notEmpty>
</logic:present>
  </tr>
</table>

<logic:present name="ShiftSearchFormMgr" property="list">
  <logic:notEmpty name="ShiftSearchFormMgr" property="list">
<%-- tabs --%>
<jsp:include page="shiftSearchResultsTabs.jsp" flush="true">
  <jsp:param name="tab" value="summary"/>
</jsp:include>
<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormMgr" value="ShiftSearchFormMgr"/>
  <jsp:param name="showCheckbox" value="<%= showCheckbox %>"/>
</jsp:include>
  </logic:notEmpty>
</logic:present>

</html:form>
 