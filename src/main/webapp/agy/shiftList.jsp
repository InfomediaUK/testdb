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
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
		
<bean:parameter id="bookingGradeStatus" name="bookingGradeStatus" value="-1"/>
<bean:parameter id="bookingDateStatus" name="bookingDateStatus" value="-1"/>
<bean:parameter id="workedStatus" name="workedStatus" value="-1"/>
<bean:parameter id="unViewed" name="unViewed" value=""/>
<logic:notEqual name="bookingGradeStatus" value="-1">
  <bean:message key="<%= com.helmet.bean.BookingGrade.BOOKINGGRADE_STATUS_DESCRIPTION_KEYS[Integer.parseInt(bookingGradeStatus)] %>"/>
</logic:notEqual>
<logic:notEqual name="bookingDateStatus" value="-1">
  <bean:message key="<%= com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_DESCRIPTION_KEYS[Integer.parseInt(bookingDateStatus)] %>"/>
</logic:notEqual>
<logic:notEqual name="workedStatus" value="-1">
  <bean:message key="<%= com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_DESCRIPTION_KEYS[Integer.parseInt(workedStatus)] %>"/>
</logic:notEqual>
<logic:equal name="unViewed" value="true">
  <bean:message key="link.new"/>
</logic:equal>
<logic:equal name="bookingGradeStatus" value="-1">
  <logic:equal name="bookingDateStatus" value="-1">
    <logic:equal name="workedStatus" value="-1">
      <logic:equal name="unViewed" value="">
        <%/* should never be seen */%>
        <bean:message key="text.all"/>
      </logic:equal>
    </logic:equal>
  </logic:equal>
</logic:equal>
<bean:define id="list" name="ShiftListFormAgy" property="list" type="java.util.List"/>
<bean:message key="title.shifts"/>&nbsp;(<%=list.size() %>)
		</td>
  </tr>
</table>

<jsp:include page="weekToShowNavigationTab.jsp" flush="true">
  <jsp:param name="theForm" value="ShiftListFormAgy"/>
  <jsp:param name="weekToShow" value="<%= weekToShow %>"/>
</jsp:include>

<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormAgy" value="ShiftListFormAgy"/>
</jsp:include>

