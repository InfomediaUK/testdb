<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%
String applicantIndexLetter = (String)session.getAttribute("applicantIndexLetter");
applicantIndexLetter = applicantIndexLetter == null ? "A" : applicantIndexLetter;
String indexLetter = request.getParameter("indexLetter") == null ? applicantIndexLetter : request.getParameter("indexLetter");
session.setAttribute("applicantIndexLetter", indexLetter);
%>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:define id="list" name="ListFormAgy" property="list" type="java.util.List"/>
<bean:message key="text.all"/>&nbsp;<bean:message key="title.applicants"/>&nbsp;(<%=list.size() %>)&nbsp;<%= indexLetter %>
		</td>
    <mmj-agy:hasAccess forward="applicantNew">
    <html:form action="/applicantNew.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
		</mmj-agy:hasAccess>
  </tr>
</table>

<%-- tabs --%>
<bean:parameter id="tab" name="tab" value="<%= indexLetter %>"/>

<jsp:include page="applicantListTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<logic:notPresent name="ListFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ListFormAgy" property="list">
<logic:empty name="ListFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ListFormAgy" property="list">

<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name"/></th>
    <th align="left"><bean:message key="label.emailAddress"/></th>
    <th align="left"><bean:message key="label.mobile"/></th>
    <th align="left"><bean:message key="label.availability"/></th>
    <th align="left"><bean:message key="label.disciplineCategory"/></th>
    <th align="left"><bean:message key="label.clientGroup"/></th>
    <th align="left"><bean:message key="label.professionalReference"/></th>
    <th align="center"><bean:message key="label.ftw"/></th>
    <th align="center"><bean:message key="label.crb"/></th>
    <th align="center"><bean:message key="label.mt"/></th>
    <th align="center">SC</th>
    <th align="center"><bean:message key="label.compliant"/></th>
  </tr>
  </thead>
  <logic:iterate id="applicant" name="ListFormAgy" property="list" indexId="applicantIndex" type="com.helmet.bean.Applicant">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">    
    <mmj-agy:hasAccess forward="applicantView">
      <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
        <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
      </html:link>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="applicantView">
        <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
    </mmj-agy:hasNoAccess>
	  <logic:notEmpty name="applicant" property="user.nhsStaffName">
	    *
	  </logic:notEmpty>
    </td>
    <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
    <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>   
    <td align="left"><bean:write name="applicant" property="availabilityDate" formatKey="format.mediumDateFormat"/></td>   
    <td align="left"><bean:write name="applicant" property="disciplineCategoryName"/></td>   
    <td align="left">
	  <logic:equal name="applicant" property="clientGroup" value="1">
        <bean:message key="label.adults"/>
    </logic:equal>
		<logic:equal name="applicant" property="clientGroup" value="2">
        <bean:message key="label.paeds"/>
    </logic:equal>
    </td>   
    <td align="left"><bean:write name="applicant" property="hpcNumber"/></td>   
    <td align="center">
			<logic:equal name="applicant" property="hasCurrentFitToWork" value="true">
		    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
		  </logic:equal>
		  <logic:notEqual name="applicant" property="hasCurrentFitToWork" value="true">
		    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
		  </logic:notEqual>
    </td>
    <td align="center">
			<logic:equal name="applicant" property="hasCurrentCRB" value="true">
		    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
		  </logic:equal>
			<logic:notEqual name="applicant" property="hasCurrentCRB" value="true">
		    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
		  </logic:notEqual>
    </td>
    <td align="center">
			<logic:equal name="applicant" property="hasCurrentTraining" value="true">
		    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
		  </logic:equal>
			<logic:notEqual name="applicant" property="hasCurrentTraining" value="true">
		    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
		  </logic:notEqual>
    </td>
    <td align="center">
			<logic:greaterThan name="applicant" property="originalAgencyId" value="0">
		    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
		  </logic:greaterThan>
			<logic:equal name="applicant" property="originalAgencyId" value="0">
		    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
		  </logic:equal>
    </td>
    <td align="center">
			<logic:equal name="applicant" property="compliant" value="true">
		    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
		  </logic:equal>
			<logic:notEqual name="applicant" property="compliant" value="true">
		    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
		  </logic:notEqual>
    </td>
  </tr>
  </logic:iterate>
</table>
* Has NHS Staff Name
</logic:notEmpty>
</logic:present>