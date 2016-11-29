<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="weekToShow" name="ShiftListFormAgy" property="weekToShow" />
<%
// Was: String theAction = "/shiftListSubmitted.do?workedStatus=1";
// The following code gets the URL (after ContextPath) including ALL parameters but NOT the weekToShow 
// parameter which is added with its new values for the 'navigation links'...
String theAction = request.getAttribute("javax.servlet.forward.request_uri") + "?";
theAction = theAction.substring(theAction.lastIndexOf("/"));
java.util.Enumeration<String> paramNames = request.getParameterNames();
StringBuffer parameters = new StringBuffer();
while (paramNames.hasMoreElements())
{
  String paramName = paramNames.nextElement();
  String[] paramValues = request.getParameterValues(paramName);
  for (int i = 0; i < paramValues.length; i++) 
  {
    if (!paramName.equals("weekToShow"))
    {
      // NOT weekToShow, so add it.
      if (parameters.length() > 0)
      {
        // Not first time through, so add the ampersand...
        parameters.append("&");
      }
      String paramValue = paramValues[i];
      parameters.append(paramName);
      parameters.append("=");
      parameters.append(paramValue);
    }
  }
}
theAction = theAction + parameters.toString();
%>
<bean:define id="showCheckbox" value="false"/>
<bean:define id="formAction" value="/home.do"/>
<mmj-agy:hasAccess forward="bookingDatesAuthorize">
<bean:define id="showCheckbox" value="true"/>
<bean:define id="formAction" value="/bookingDatesAuthorize.do"/>
</mmj-agy:hasAccess>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="<%= formAction %>" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:define id="list" name="ShiftListFormAgy" property="list" type="java.util.List"/>
<bean:message key="title.shiftListSubmitted"/>&nbsp;(<%=list.size() %>)
		</td>
    <mmj-agy:hasAccess forward="bookingDatesAuthorize">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="titleButton"><bean:message key="button.authorize"/></html:submit>
    </td>
    </mmj-agy:hasAccess>
  </tr>
</table>

<jsp:include page="weekToShowNavigationTab.jsp" flush="true">
  <jsp:param name="theForm" value="ShiftListFormAgy"/>
  <jsp:param name="weekToShow" value="<%= weekToShow %>"/>
</jsp:include>

<jsp:include page="shiftsIncludeByLocation.jsp" flush="true">
  <jsp:param name="theFormAgy" value="ShiftListFormAgy"/>
  <jsp:param name="showCheckbox" value="<%= showCheckbox %>"/>
</jsp:include>
</html:form>
