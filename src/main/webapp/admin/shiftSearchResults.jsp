<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>
<jsp:include page="shiftSearch.jsp" flush="true"/>

<br/>

<display:table name="ShiftSearchFormAdmin.list" id="bookingDate" class="simple" 
		export="true" 
		requestURI="shiftSearchProcess.do?refresh=refresh&new=false"
		decorator="org.displaytag.decorator.TotalTableDecorator">

  
  <display:column titleKey="label.bookingNo" property="bookingId" media="excel xml csv" />
  <display:column titleKey="label.bookingNo" media="html" sortable="true">
    <c:url value="/mgr/bookingViewSummary.do" var="bookingUrl">
	  <c:param name="booking.bookingId" value="${bookingDate.bookingId}"/>
	  <c:param name="clientCode" value="${bookingDate.clientCode}"/>
	</c:url>
	<a href="<c:out value="${bookingUrl}"/>"><c:out value="${bookingDate.bookingId}"/></a>
  </display:column>
  <display:column titleKey="label.shiftNo" property="bookingDateId" sortable="true" />
  <display:column titleKey="label.date" property="bookingDate" format="{0,date,EEE, dd MMM yy}" sortable="true" />
  <display:column titleKey="label.client" property="clientName" sortable="true" href="clientView.do" paramId="client.clientId" paramProperty="clientId" />
  <display:column titleKey="label.client" property="clientCode" sortable="true" />
  <display:column titleKey="label.site" property="siteName" sortable="true" href="siteView.do" paramId="site.siteId" paramProperty="siteId" />
  <display:column titleKey="label.location" property="locationName" sortable="true" href="locationView.do" paramId="location.locationId" paramProperty="locationId" />
  <display:column titleKey="label.jobProfile" property="jobProfileName" sortable="true" href="jobProfileView.do" paramId="jobProfile.jobProfileId" paramProperty="jobProfileId" />
  <display:column titleKey="label.bttCode" sortable="true"><c:out value="${bookingDate.jobFamilyCode}.${bookingDate.jobSubFamilyCode}.${bookingDate.jobProfileCode}"/></display:column>
  <display:column titleKey="label.grade" property="gradeName" sortable="true" href="gradeView.do" paramId="grade.gradeId" paramProperty="gradeId" />
  <display:column titleKey="label.agency" property="agencyName" sortable="true" href="agencyView.do" paramId="agency.agencyId" paramProperty="agencyId">
    <c:if test="${empty bookingDate.agencyCode}">
      <!-- shouldn't be a link if no agency -->
      &nbsp;
    </c:if>
    <c:if test="${not empty bookingDate.agencyCode}">
      <c:out value="${bookingDate.agencyName}"/>
    </c:if>
  </display:column>
  <display:column titleKey="label.agency" property="agencyCode" sortable="true" />

  <display:column titleKey="label.applicant" sortable="true"><c:out value="${bookingDate.applicantFirstName} ${bookingDate.applicantLastName}"/></display:column>
 
  <display:column titleKey="label.activatedShort" property="activated" sortable="true" />
  <display:column titleKey="label.noOfHours" property="workedNoOfHours" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.hasUpliftShort" property="hasUplift" sortable="true" />

  <display:column titleKey="label.chargeRate" property="chargeRate" format="{0,number, #,##0.00}" sortable="true"/>
  <display:column titleKey="label.payRate" property="payRate" format="{0,number, #,##0.00}" sortable="true"/>
  <display:column titleKey="label.wtdPercentage" property="wtdPercentage" format="{0,number, #,##0.00}%" sortable="true"/>
  <display:column titleKey="label.wtdRate" property="wtdRate" format="{0,number, #,##0.00}" sortable="true"/>
  <display:column titleKey="label.niPercentage" property="niPercentage" format="{0,number, #,##0.00}%" sortable="true"/>
  <display:column titleKey="label.niRate" property="niRate" format="{0,number, #,##0.00}" sortable="true"/>
  <display:column titleKey="label.commRate" property="commissionRate" format="{0,number, #,##0.00}" sortable="true"/>

  <display:column titleKey="label.charge" property="workedChargeRateValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.pay" property="workedPayRateValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.wtd" property="workedWtdValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.ni" property="workedNiValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.comm" property="workedCommissionValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>

  <display:column titleKey="label.charge" property="chargeRateVatRate" format="{0,number, #,##0.00}" sortable="true"/>
  <display:column titleKey="label.pay" property="payRateVatRate" format="{0,number, #,##0.00}" sortable="true"/>
  <display:column titleKey="label.wtd" property="wtdVatRate" format="{0,number, #,##0.00}" sortable="true"/>
  <display:column titleKey="label.ni" property="niVatRate" format="{0,number, #,##0.00}" sortable="true"/>
  <display:column titleKey="label.comm" property="commissionVatRate" format="{0,number, #,##0.00}" sortable="true"/>

  <display:column titleKey="label.charge" property="workedChargeRateVatValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.pay" property="workedPayRateVatValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.wtd" property="workedWtdVatValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.ni" property="workedNiVatValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.comm" property="workedCommissionVatValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>

  <display:column titleKey="label.exp" property="expenseValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.vat" property="expenseVatValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>

  <display:column titleKey="label.vat" property="totalVatValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true"/>
  <display:column titleKey="label.total" property="workedTotalValue" format="{0,number, #,##0.00}" total="true" nulls="true" sortable="true" />
  <display:column titleKey="label.shiftStatus" sortable="true" >
    <bean:message name="bookingDate" property="statusDescriptionKey"/>    
  </display:column>
  <display:column titleKey="label.timesheetStatus" sortable="true" >
    <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>    
  </display:column>
  <display:column titleKey="label.invoiceNo" property="agencyInvoiceId" sortable="true" />

</display:table>
 