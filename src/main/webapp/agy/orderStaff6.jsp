<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="OrderStaffFormAgy" property="clientAgencyJobProfileUsers">
<br/>
<br/>
<bean:message key="error.noAgencies"/>
</logic:empty>
<logic:notEmpty name="OrderStaffFormAgy" property="clientAgencyJobProfileUsers">
  <bean:define id="bookingDates" name="OrderStaffFormAgy" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
  <%
  com.helmet.bean.BookingDate minDate = bookingDates[0];
  com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
  pageContext.setAttribute("minDate", minDate);
  pageContext.setAttribute("maxDate", maxDate);
  %>
	<html:form action="/orderStaff7.do" focus="rate0-0" onsubmit="return singleSubmit();">
		<html:hidden property="page" value="6"/>
		<table>
		  <tr>
		    <td align="left" valign="middle" width="250">
		      <logic:empty name="OrderStaffFormAgy" property="booking.bookingId">
		      <bean:message key="title.newBooking"/>
		      </logic:empty>
		      <logic:notEmpty name="OrderStaffFormAgy" property="booking.bookingId">
		      <bean:message key="title.editBooking"/>
		      </logic:notEmpty>
		      -&nbsp;<bean:message key="title.orderStaff6"/>
		    </td>
		    <td align="left" valign="top">
		      <jsp:include page="orderStaffButtons.jsp" flush="true" />
		    </td>
		  </tr>
		</table>
		<hr/>
		<html:errors />
		<table border="0" width="100%">
		  <tr>
		    <td width="50%" valign="top">
					<table class="simple" width="250">
					  <tr>
					    <th class="label" align="left">
					      <bean:message key="label.totalHours" />
					    </th>
					    <td align="right">
					      <bean:write name="OrderStaffFormAgy" property="totalHours" format="#0.00"/>
					    </td>
					  </tr>
					  <tr>
					    <th class="label" align="left">
					      <bean:message key="label.rrpRate" />
					    </th>
					    <td align="right">
					    <mmj-agy:hasAccess forward="canChangeRRP">
					      <html:text property="hourlyRate"/>
					    </mmj-agy:hasAccess>
					    <mmj-agy:hasNoAccess forward="canChangeRRP">
					      <bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="hourlyRate" format="#,##0.00"/>
					      <html:hidden property="hourlyRate"/>
					    </mmj-agy:hasNoAccess>
					    </td>
					  </tr>
					  <tr>
					    <th class="label" align="left">
					      <bean:message key="label.rrpValue" />
					    </th>
					    <td align="right">
					      <bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="rrp" format="#,##0.00"/>
					    </td>
					  </tr>
					</table> 
					<br/> 
					<bean:define id="totalHours" name="OrderStaffFormAgy" property="totalHours" type="java.math.BigDecimal"/>
					<table class="simple">
					  <tr>
					    <th class="label" align="left" valign="middle">
					      <bean:message key="label.grade"/>
					    </th><logic:iterate id="clientAgencyJobProfileUser" name="OrderStaffFormAgy" property="clientAgencyJobProfileUsers"><%/* loop through agencies */%>
					    <th class="label" align="center" colspan="3">
					      <bean:write name="clientAgencyJobProfileUser" property="agencyName"/>
						    <logic:notEmpty name="clientAgencyJobProfileUser" property="masterVendorName">
					        <br/>
						      (<bean:write name="clientAgencyJobProfileUser" property="masterVendorName"/>)
						    </logic:notEmpty>
					      <br/>
					      <bean:write name="clientAgencyJobProfileUser" property="percentage" format="0.00"/>%
					    </th></logic:iterate>
					  </tr>
					  <tr>
					    <th class="label" align="left" valign="middle">&nbsp;</th>
					    <logic:iterate id="clientAgencyJobProfileUser" name="OrderStaffFormAgy" property="clientAgencyJobProfileUsers">
					    <th class="label" align="left" valign="middle">&nbsp;</th>
					    <th class="label" align="center" valign="middle"><bean:message key="label.value"/></th>
					    <th class="label" align="center" valign="middle"><bean:message key="label.rate"/></th>
					    </logic:iterate>  
					  </tr>
					  <logic:iterate id="grade" name="OrderStaffFormAgy" property="grades" type="com.helmet.bean.Grade" indexId="gradeIndex"><%/* loop through grades */%>
					  <tr>
					    <th class="label" align="left">
					      <bean:write name="grade" property="name"/>
					    </th>
					    <%-- ASSUMED - the order of the vector is the same as the order of grades !!!! --%>
					    <logic:iterate id="outer" name="OrderStaffFormAgy" property="clientAgencyJobProfileGrades" offset='<%= "gradeIndex" %>' length="1" indexId="outerIndex">
					      <logic:iterate id="clientAgencyJobProfileGrade" name="outer" type="com.helmet.bean.ClientAgencyJobProfileGrade" indexId="innerIndex">
					        <%/* loop through clientAgencyJobProfileGrades - check for the correct grade */%>
					<%--
					       <c:if test="${clientAgencyJobProfileGrade.gradeId eq grade.gradeId}">
					--%>
					      <logic:present name="clientAgencyJobProfileGrade">
					      <td align="center">
					      <html:radio tabindex="1" property='<%= "clientAgencyJobProfileGradeUserArray[" + innerIndex + "].clientAgencyJobProfileGradeId" %>' idName="clientAgencyJobProfileGrade" value="clientAgencyJobProfileGradeId" title="<%= String.valueOf(clientAgencyJobProfileGrade.getRank()) %>"  styleId='<%= "rate" + outerIndex + "-" + innerIndex %>'/>
					      </td>
					      <label for="<%= "rate" + outerIndex + "-" + innerIndex %>">
					      <td align="right">
					      &nbsp;<bean:message key="label.currencySymbol"/><fmt:formatNumber value="${clientAgencyJobProfileGrade.value}" pattern="#,##0.00"/>
					      </td>
					      <td align="right">
					      &nbsp;<bean:message key="label.currencySymbol"/><fmt:formatNumber value="${clientAgencyJobProfileGrade.rate}" pattern="#,##0.00"/>
					      </td>
					      </label>
					      </logic:present>
					      <logic:notPresent name="clientAgencyJobProfileGrade">
					      <td align="center" colspan="3">
					        &nbsp;
					      </td>
					      </logic:notPresent>
					<%--
					       </c:if>
					--%>
					      </logic:iterate>
					    </logic:iterate>
					  </tr>
					  </logic:iterate>
					</table>
	    </td>
	    <td class="simple" width="50%" valign="top">
	      <table class="simple">
	        <tr>
	          <th class="label">
		          <bean:message key="label.client"/>
	          </th>
	          <td>
	            <bean:write name="OrderStaffFormAgy" property="client.name"/>&nbsp;(<bean:write name="OrderStaffFormAgy" property="client.code"/>)
	          </td>
	        </tr>
	        <tr>
	          <th class="label">
	            <bean:message key="label.reason"/>
	          </th>
	          <td>
	            <bean:write name="OrderStaffFormAgy" property="reasonForRequest.name"/>&nbsp;-
	            <bean:write name="OrderStaffFormAgy" property="reasonForRequestText"/>
	          </td>
	        </tr>
	        <tr>
	          <th class="label">
	            <bean:message key="label.location"/>
	          </th>
	          <td>
					    <bean:write name="OrderStaffFormAgy" property="location.name"/>,
					    <bean:write name="OrderStaffFormAgy" property="location.siteName"/>
				      <logic:notEmpty name="OrderStaffFormAgy" property="location.description">
				  	    (<bean:write name="OrderStaffFormAgy" property="location.description"/>)
				      </logic:notEmpty>
	          </td>
	        </tr>
	        <tr>
	          <th class="label" valign="top">
	            <bean:message key="label.jobProfile"/>
	          </th>
	          <td>
	            <bean:write name="OrderStaffFormAgy" property="jobProfile.name"/>
	            (<bean:write name="OrderStaffFormAgy" property="jobProfile.jobFamilyCode"/>.<bean:write name="OrderStaffFormAgy" property="jobProfile.jobSubFamilyCode"/>.<bean:write name="OrderStaffFormAgy" property="jobProfile.code"/>)
	          </td>
	        </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.start"/>
				    </th>
				    <td align="left">
				      <bean:write name="minDate" property="bookingDate" formatKey="format.longDateFormat"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.end"/>
				    </th>
				    <td align="left">
				      <bean:write name="maxDate" property="bookingDate" formatKey="format.longDateFormat"/>
				    </td>
				  </tr>
	        <tr>
	          <th align="left" class="label">
	            <bean:message key="label.days"/>
	          </th>
	          <td>
	            <bean:write name="OrderStaffFormAgy" property="noOfDates"/>
	          </td>
	        </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.totalHours" />
				    </th>
				    <td align="left">
				      <bean:write name="OrderStaffFormAgy" property="totalHours" format="#0.00"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
							<bean:message key="label.rrpRate"/>
				    </th>
				    <td align="left">
							<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="hourlyRate" format="#,##0.00" />
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
							<bean:message key="label.rrpValue"/>
				    </th>
				    <td align="left">
							<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="rrp" format="#,##0.00" />
				    </td>
				  </tr>
	      </table>
		    </td>
		  </tr>
		</table>    
	</html:form>
</logic:notEmpty>