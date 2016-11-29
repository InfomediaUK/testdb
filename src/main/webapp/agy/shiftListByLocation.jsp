<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="bookingGradeStatus" name="bookingGradeStatus" value="-1"/>
<bean:parameter id="bookingDateStatus" name="bookingDateStatus" value="-1"/>
<bean:parameter id="workedStatus" name="workedStatus" value="-1"/>
<bean:parameter id="unViewed" name="unViewed" value=""/>
<bean:define id="weekToShow" name="ShiftListFormAgy" property="weekToShow" />
<bean:define id="showCheckbox" value="false"/>
<bean:define id="formAction" value="/home.do"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<logic:equal name="workedStatus" value="2">
  <%-- Authorised. --%>
   <mmj-agy:hasAccess forward="bookingDatesAddRemoveBackingReport">
     <bean:define id="showCheckbox" value="true"/>
     <bean:define id="formAction" value="/bookingDatesAddRemoveBackingReport.do"/>
   </mmj-agy:hasAccess>
</logic:equal>
  <html:form action="<%= formAction %>" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
		
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
<logic:equal name="workedStatus" value="2">
  <%-- Authorised. --%>
    <mmj-agy:hasAccess forward="bookingDatesAddRemoveBackingReport">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="titleButton"><bean:message key="button.markAsPaid"/></html:submit>
    </td>
    </mmj-agy:hasAccess>
</logic:equal>    
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

