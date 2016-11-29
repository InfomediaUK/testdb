<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="showAll" name="ApplicantWorkingListFormAgy" property="showAll" type="java.lang.Boolean" />
<bean:define id="weekToShow" name="ApplicantWorkingListFormAgy" property="weekToShow" />
<bean:define id="date" name="ApplicantWorkingListFormAgy" property="startDate" />
<%
String weekAction = "/applicantWorkingList.do?showAll=" + showAll;
String showAction = showAll.equals(true) ? "/applicantWorkingList.do?showAll=false" : "/applicantWorkingList.do?showAll=true";
String showTitle  = showAll.equals(true) ? "title.showBookingsOnly" : "title.showAll";
String showLink   = showAll.equals(true) ? "link.showBookingsOnly"  : "link.showAll";
%>

<table cellpadding="0" cellspacing="0" width="100%" height="30" border="0">
  <tr>
		<td align="left" valign="middle" class="title" width="50%">
			<bean:message key="title.applicantWorkingList"/>
			<bean:define id="list" name="ApplicantWorkingListFormAgy" property="list" type="java.util.List"/>:&nbsp;
			<bean:write name="ApplicantWorkingListFormAgy" property="startDate" formatKey="format.longDateFormat" />
			-
			<bean:write name="ApplicantWorkingListFormAgy" property="endDate" formatKey="format.longDateFormat" />
		</td>
		<td align="left" valign="middle" width="50%">
			<c:set var="lastWeek" value="${weekToShow - 1}" scope="page"/>
			<c:set var="nextWeek" value="${weekToShow + 1}" scope="page"/>
      <html:link page="<%= weekAction %>" paramId="weekToShow" paramName="lastWeek" paramScope="page" titleKey="title.showPreviousWeek">&lt;</html:link>
      <html:link page="<%= weekAction %>" titleKey="title.showCurrentWeek"><bean:message key="link.current"/></html:link>
      <html:link page="<%= weekAction %>" paramId="weekToShow" paramName="nextWeek" paramScope="page" titleKey="title.showNextWeek">&gt;</html:link>
      &nbsp;&nbsp;&nbsp;
      <html:link page="<%= showAction %>" titleKey="<%= showTitle %>"><bean:message key="<%= showLink %>"/></html:link>
		</td>
  </tr>
</table>

<logic:notPresent name="ApplicantWorkingListFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ApplicantWorkingListFormAgy" property="list">
<logic:empty name="ApplicantWorkingListFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ApplicantWorkingListFormAgy" property="list">

<table class="simple" width="100%" border="1">
  <thead>
  <tr>
    <th align="left" width="15%"><bean:message key="label.name"/></th>
  <logic:iterate id="day" name="ApplicantWorkingListFormAgy" property="listDate" indexId="dateIndex" type="java.util.Date">
			<th align="left"><bean:write name="day" format="dd/MM" /></th>
  </logic:iterate>
  </tr>
  </thead>
  <logic:iterate id="applicantWorking" name="ApplicantWorkingListFormAgy" property="list" indexId="applicantIndex" type="com.helmet.application.agy.ApplicantWorking">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left" width="15%">    
    <mmj-agy:hasAccess forward="applicantView">
      <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicantWorking" paramProperty="applicantId" >
        <bean:write name="applicantWorking" property="firstName"/>
        <bean:write name="applicantWorking" property="lastName"/>
      </html:link>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="applicantView">
        <bean:write name="applicantWorking" property="firstName"/>
        <bean:write name="applicantWorking" property="lastName"/>
    </mmj-agy:hasNoAccess>
    </td>
  <logic:iterate id="applicantWorkingDate" name="applicantWorking" property="listWorkingDate" indexId="dateIndex" type="com.helmet.application.agy.ApplicantWorkingDate">
   <logic:notEqual name="applicantWorkingDate" property="hoursWorked" value="0">
     <logic:equal name="applicantWorkingDate" property="workedStatusIsInvoiced" value="true">    
     <td class="invoiced">
     </logic:equal>
     <logic:notEqual name="applicantWorkingDate" property="workedStatusIsInvoiced" value="true">    
     <td<logic:equal name="applicantWorkingDate" property="unavailable" value="true"> class="unavailable"</logic:equal>>
     </logic:notEqual>
	    <bean:write name="applicantWorkingDate" property="hoursWorked" format="#0.00"/>
	    <bean:write name="applicantWorkingDate" property="finishedPeriodCode" />
    </td>
	 </logic:notEqual>
   <logic:equal name="applicantWorkingDate" property="hoursWorked" value="0">
     <logic:equal name="applicantWorkingDate" property="hoursScheduled" value="0">
    <td<logic:equal name="applicantWorkingDate" property="unavailable" value="true"> class="unavailable"</logic:equal>>
	    &nbsp;
    </td>
	   </logic:equal>
     <logic:notEqual name="applicantWorkingDate" property="hoursScheduled" value="0">
    <td class="scheduled<logic:equal name="applicantWorkingDate" property="unavailable" value="true"> unavailable</logic:equal>">
	    <bean:write name="applicantWorkingDate" property="hoursScheduled" format="#0.00"/>
	    <bean:write name="applicantWorkingDate" property="finishedPeriodCode" />
    </td>
	   </logic:notEqual>
	 </logic:equal>
  </logic:iterate>
  </tr>
  </logic:iterate>
</table>
</logic:notEmpty>
</logic:present>