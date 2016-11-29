<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<bean:parameter id="weekToShow" name="weekToShow" value="0"/>
<bean:parameter id="theForm" name="theForm" value=""/>
<bean:parameter id="theList" name="theList" value="list"/>

<%
// The following code gets the URL (after ContextPath) including ALL parameters but NOT the weekToShow 
// parameter which is added with its new values for the 'navigation links'...
String theAction = (String)request.getAttribute("javax.servlet.forward.request_uri");
theAction = theAction.substring(theAction.lastIndexOf("/"));
java.util.Enumeration<String> paramNames = request.getParameterNames();
StringBuffer parameters = new StringBuffer();
while (paramNames.hasMoreElements())
{
  String paramName = paramNames.nextElement();
  String[] paramValues = request.getParameterValues(paramName);
  for (int i = 0; i < paramValues.length; i++) 
  {
    if (!paramName.equals("weekToShow") && !paramName.equals("theForm") && !paramName.equals("theList"))
    {
      // NOT weekToShow, theForm or theList, so add it.
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
if (parameters.length() > 0)
{
  // Add the existing parameters to the action.
  theAction = theAction + "?" + parameters.toString();
}
%>

<logic:present name="<%= theForm %>" property="<%= theList %>" >
  <table class="tabs" width="100%">
    <tr>
      <bean:define id="tabClass" value="tabSelected"/>
      <td align="center" class="<bean:write name="tabClass"/>" width="25%">
        <c:set var="fourWeeksBack" value="${weekToShow - 4}" scope="page"/>
        <c:set var="twoWeeksBack" value="${weekToShow - 2}" scope="page"/>
        <c:set var="previousWeek" value="${weekToShow - 1}" scope="page"/>
        <c:set var="currentWeek" value="0" scope="page"/>
        <c:set var="nextWeek" value="${weekToShow + 1}" scope="page"/>
        <c:set var="twoWeeksForward" value="${weekToShow + 2}" scope="page"/>
        <c:set var="fourWeeksForward" value="${weekToShow + 4}" scope="page"/>
        <html:link page="<%= theAction %>" paramId="weekToShow" paramName="fourWeeksBack" paramScope="page" titleKey="title.showFourWeeksBack">&lt;&lt;&lt;&lt;</html:link>
        &nbsp;
        <html:link page="<%= theAction %>" paramId="weekToShow" paramName="twoWeeksBack" paramScope="page" titleKey="title.showTwoWeeksBack">&lt;&lt;</html:link>
        &nbsp;
        <html:link page="<%= theAction %>" paramId="weekToShow" paramName="previousWeek" paramScope="page" titleKey="title.showPreviousWeek">&lt;</html:link>
        <html:link page="<%= theAction %>" paramId="weekToShow" paramName="currentWeek" paramScope="page"  titleKey="title.showCurrentWeek"><bean:message key="link.weekly"/></html:link>
        <html:link page="<%= theAction %>" paramId="weekToShow" paramName="nextWeek" paramScope="page" titleKey="title.showNextWeek">&gt;</html:link>
        &nbsp;
        <html:link page="<%= theAction %>" paramId="weekToShow" paramName="twoWeeksForward" paramScope="page" titleKey="title.showTwoWeeksForward">&gt;&gt;</html:link>
        &nbsp;
        <html:link page="<%= theAction %>" paramId="weekToShow" paramName="fourWeeksForward" paramScope="page" titleKey="title.showFourWeeksForward">&gt;&gt;&gt;&gt;</html:link>
      </td>
      <td align="center" class="tabInvisibleClass" width="50%">
        <bean:write name="<%= theForm %>" property="startDate" formatKey="format.longDateFormat" />
        -
        <bean:write name="<%= theForm %>" property="endDate" formatKey="format.longDateFormat" />
      </td>
      <td align="center" class="tabInvisibleClass" width="25%">
        &nbsp;
      </td>
    </tr>
  </table>
</logic:present>
