<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<%-- title and buttons --%>
<jsp:include page="bookingViewHeader.jsp" flush="true">
  <jsp:param name="isView" value="true"/>
</jsp:include>
<%-- tabs --%>
<jsp:include page="bookingViewTabs.jsp" flush="true">
  <jsp:param name="tab" value="applicants"/>
</jsp:include>
<%-- applicants --%>

<logic:notPresent name="BookingViewFormMgr" property="booking.bookingGradeApplicantUsers">
  <br/>
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="BookingViewFormMgr" property="booking.bookingGradeApplicantUsers">
<logic:empty name="BookingViewFormMgr" property="booking.bookingGradeApplicantUsers">
  <br/>
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="BookingViewFormMgr" property="booking.bookingGradeApplicantUsers" >
<table class="simple" width="100%">
  <tr>
    <th align="left" class="label">
      <bean:message key="label.name"/>
      (<bean:message key="label.agency"/>)
    </th>
    <th align="left" class="label">
      <bean:message key="label.gender"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.dob"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.grade"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.rate"/>
    </th>
    <th align="left" class="label">
      <bean:message key="label.status"/>
    </th>
    <logic:equal name="BookingViewFormMgr" property="booking.cvRequired" value="true">
    <th align="left" class="label">
      <bean:message key="label.document"/>
    </th>
    </logic:equal>
  </tr>
  <logic:iterate name="BookingViewFormMgr" property="booking.bookingGradeApplicantUsers" id="bookingGradeApplicant" >
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
			<mmj-mgr:hasAccess forward="bookingGradeApplicantView">
			<html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="bookingGradeApplicant" paramProperty="bookingGradeApplicantId">
      <bean:write name="bookingGradeApplicant" property="applicantFirstName" />&nbsp;<bean:write name="bookingGradeApplicant" property="applicantLastName" />
			</html:link>
			</mmj-mgr:hasAccess>
			<mmj-mgr:hasNoAccess forward="bookingGradeApplicantView">
      <bean:write name="bookingGradeApplicant" property="applicantFirstName" />&nbsp;<bean:write name="bookingGradeApplicant" property="applicantLastName" />
			</mmj-mgr:hasNoAccess>
      (<bean:write name="bookingGradeApplicant" property="agencyName" />)
    </td>
    <td align="left">
      <bean:message name="bookingGradeApplicant" property="applicantGenderDescriptionKey"/>
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="applicantDateOfBirth" formatKey="format.mediumDateFormat" />
    </td>
    <td align="left">
      <bean:write name="bookingGradeApplicant" property="gradeName" />
    </td>
    <td align="left">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicant" property="rate" format="#,##0.00" />
    </td>
    <td align="left">
      <bean:message name="bookingGradeApplicant" property="statusDescriptionKey"/>
    </td>
    <logic:equal name="BookingViewFormMgr" property="booking.cvRequired" value="true">
    <td align="left">
    <logic:empty name="bookingGradeApplicant" property="filename">
      &nbsp;
    </logic:empty>
    <logic:notEmpty name="bookingGradeApplicant" property="filename">
      <bean:define id="documentUrl" name="bookingGradeApplicant" property="documentUrl" type="java.lang.String" />
      <html:link href="<%= request.getContextPath() + documentUrl %>" target="_blank"><bean:message key="link.viewFile"/></html:link>
    </logic:notEmpty>
    </td>
    </logic:equal>
  </tr>
  </logic:iterate> 
</table>
</logic:notEmpty>
</logic:present>

<jsp:include page="bookingViewFooter.jsp" flush="true">
  <jsp:param name="xxx" value="xxx"/>
</jsp:include>
